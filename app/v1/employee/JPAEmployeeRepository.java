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
		return supplyAsync(() -> wrap(em -> {
			EmployeeSalary salaryModel = model.getSalary();
			List<AssetModel> assetModelList = model.getAsset();

			TypedQuery<EmployeeModel> query = em.createQuery("Select m from EmployeeModel m where m.mobile = :mobile", EmployeeModel.class).setParameter("mobile", model.getMobile());
			try {
				EmployeeModel employeeModelInDb = query.setMaxResults(1).getSingleResult();

				EmployeeSalary updatedSalaryModel = updateSalaryModel(employeeModelInDb, salaryModel, em);
				List<AssetModel> assetModels = updateAssetModel(employeeModelInDb, assetModelList, em);
				EmployeeModel model1 = updateEmployeeModel(model, employeeModelInDb, em);

				model1.setAssets(assetModels);
				model1.setSalary(updatedSalaryModel);
				return model1;
			} catch (NoResultException e) {
				model.setActive(true);
				model.setNewUser(true);
				EmployeeModel employeeModel = insert(em, model);
				employeeModel.setAssets(assetModelList.stream().map(assetModel -> {
					assetModel.setEmployee(employeeModel);
					return insert(em, assetModel);
				}).toList());
				salaryModel.setEmployee(employeeModel);
				employeeModel.setSalary(insert(em, salaryModel));
				return employeeModel;
			}
		}));
	}

	private EmployeeModel updateEmployeeModel(EmployeeModel requestModel, EmployeeModel employeeModelInDb, EntityManager em) {
		requestModel.setId(employeeModelInDb.getId());
		requestModel.setNewUser(false);
		return em.merge(requestModel);
	}

	private EmployeeSalary updateSalaryModel(EmployeeModel employeeModel, EmployeeSalary employeeSalaryModel, EntityManager em) {
		employeeSalaryModel.setEmployee(employeeModel);
		employeeSalaryModel.setId(employeeModel.getSalary().getId());
		return em.merge(employeeSalaryModel);
	}

	private List<AssetModel> updateAssetModel(EmployeeModel employeeModel, List<AssetModel> assetModel, EntityManager em) {
		deleteAssetModelInDb(employeeModel.getId(), em);
		return assetModel.stream().map(model -> {
			model.setEmployee(employeeModel);
			return insert(em, model);
		}).toList();
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
