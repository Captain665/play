package v1.employee;

import common.assets.AssetModel;
import common.employee.EmployeeModel;
import common.employee.EmployeeSalary;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

import play.db.jpa.JPAApi;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPAEmployeeRepository implements EmployeeRepository {
	private final JPAApi jpaApi;

	@Inject
	public JPAEmployeeRepository(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	public CompletionStage<EmployeeModel> createOrUpdate(EmployeeModel model) {
		return supplyAsync(() -> wrap(em -> {
			TypedQuery<EmployeeModel> query = em.createQuery("Select m from EmployeeModel m where m.mobile = :mobile", EmployeeModel.class).setParameter("mobile", model.getMobile());
			try {
				EmployeeModel employeeModelInDb = query.setMaxResults(1).getSingleResult();
				return updateEmployeeModel(model, employeeModelInDb, em);
			} catch (NoResultException e) {
				model.setActive(true);
				model.setNewUser(true);
				model.getSalary().setEmployee(model);
				model.getAssets().forEach(assetModel -> assetModel.setEmployee(model));
				return insert(em, model);
			}
		}));
	}

	@Override
	public CompletionStage<List<EmployeeModel>> getEmployeeList(Long companyId) {
		return supplyAsync(() -> wrap(em -> {
			TypedQuery<EmployeeModel> query = em.createQuery(
							"Select m from EmployeeModel m where m.company.id = :company_id and m.isActive is true", EmployeeModel.class)
					.setParameter("company_id", companyId);
			return query.getResultList();
		}));
	}

	private EmployeeModel updateEmployeeModel(EmployeeModel requestModel, EmployeeModel employeeModelInDb, EntityManager em) {
		deleteAssetModelInDb(employeeModelInDb.getId(), em);
		requestModel.setId(employeeModelInDb.getId());
		requestModel.getSalary().setEmployee(requestModel);
		requestModel.getSalary().setId(employeeModelInDb.getSalary().getId());
		requestModel.getAssets().forEach(assetModel -> assetModel.setEmployee(requestModel));
		requestModel.setActive(true);
		requestModel.setNewUser(false);
		return em.merge(requestModel);
	}

	@Transactional
	private void deleteAssetModelInDb(Long employeeId, EntityManager entityManager) {
		Query query = entityManager.createNativeQuery("DELETE from company_assets Where employee_id = :employeeId");
		query.setParameter("employeeId", employeeId);
		query.executeUpdate();
	}

	private <T> T wrap(Function<EntityManager, T> function) {
		return jpaApi.withTransaction(function);
	}

	private EmployeeModel insert(EntityManager entityManager, EmployeeModel model) {
		return entityManager.merge(model);
	}

}
