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
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPAEmployeeRepository implements EmployeeRepository {
	private final JPAApi jpaApi;

	@Inject
	public JPAEmployeeRepository(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	public CompletionStage<EmployeeModel> createOrUpdate(EmployeeModel model) {
		return supplyAsync(() -> wrap(entityManager -> {
			EmployeeModel employeeModel = employeeModelCreateOrUpdate(model, entityManager);
			EmployeeSalary updatedSalaryModel = salaryModelCreateOrUpdate(employeeModel, model.getSalary());
			employeeModel.setSalary(updatedSalaryModel);
			List<AssetModel> assetModelList = assetModelCreateOrUpdate(employeeModel, model.getAsset());
			employeeModel.setAssets(assetModelList);
			return employeeModel;

		}));
	}

	private EmployeeModel employeeModelCreateOrUpdate(EmployeeModel employeeModel, EntityManager em) {
		TypedQuery<EmployeeModel> query = em.createQuery("Select m from EmployeeModel m where m.mobile = :mobile", EmployeeModel.class).setParameter("mobile", employeeModel.getMobile());
		try {
			EmployeeModel modelInDb = query.setMaxResults(1).getSingleResult();
			employeeModel.setId(modelInDb.getId());
			employeeModel.setNewUser(false);
			employeeModel.setActive(true);
			return em.merge(employeeModel);
		} catch (NoResultException e) {
			System.out.println("run here " + employeeModel);
			employeeModel.setActive(true);
			employeeModel.setNewUser(true);
			return insert(em, employeeModel);
		}
	}

	private EmployeeSalary salaryModelCreateOrUpdate(EmployeeModel employeeModel, EmployeeSalary employeeSalaryModel) {
		return wrap(em -> {
			employeeSalaryModel.setEmployee(employeeModel);
			TypedQuery<EmployeeSalary> query = em.createQuery("Select m from EmployeeSalary m where m.employee.id = :employeeId", EmployeeSalary.class).setParameter("employeeId", employeeModel.getId());
			try {
				EmployeeSalary modelInDb = query.setMaxResults(1).getSingleResult();
				employeeSalaryModel.setId(modelInDb.getId());
				return em.merge(employeeSalaryModel);
			} catch (NoResultException e) {
				return insert(em, employeeSalaryModel);
			}
		});
	}

	private List<AssetModel> assetModelCreateOrUpdate(EmployeeModel employeeModel, List<AssetModel> assetModel) {
		return wrap(em -> {
			TypedQuery<AssetModel> query = em.createQuery("Select m from AssetModel m where m.employee.id = :employeeId", AssetModel.class).setParameter("employeeId", employeeModel.getId());
			try {
				List<AssetModel> modelInDb = query.getResultList();
				deleteAssetModelInDb(employeeModel.getId(), em);
				return assetModel.stream().map(model -> {
					model.setEmployee(employeeModel);
					return insert(em, model);
				}).collect(Collectors.toList());
			} catch (NoResultException e) {
				return assetModel.stream().map(model -> {
					model.setEmployee(employeeModel);
					return insert(em, model);
				}).collect(Collectors.toList());
			}
		});
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

	private AssetModel insert(EntityManager entityManager, AssetModel model) {
		return entityManager.merge(model);
	}

	private EmployeeSalary insert(EntityManager entityManager, EmployeeSalary salary) {
		return entityManager.merge(salary);
	}

}
