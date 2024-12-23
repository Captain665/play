package v1.employee;

import common.assets.AssetModel;
import common.company.CompanyModel;
import common.employee.EmployeeModel;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import play.db.jpa.JPAApi;

import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class JPAEmployeeRepository implements EmployeeRepository {
	public final JPAApi jpaApi;

	@Inject
	public JPAEmployeeRepository(JPAApi jpaApi) {
		this.jpaApi = jpaApi;
	}

	@Override
	public CompletionStage<EmployeeModel> createOrUpdate(EmployeeModel model) {
		return wrap(entityManager -> {
			List<AssetModel> assetModelList = model.getAsset();
			EmployeeModel employeeModel = insert(entityManager, model);

			if (!assetModelList.isEmpty()) {
				List<AssetModel> asset = assetModelList.stream().map(
						companyAsset -> {
							companyAsset.setEmployeeId(employeeModel);
							return insert(entityManager, companyAsset);
						}
				).toList();
				employeeModel.setAssetId(asset);
			}
			return supplyAsync(() -> employeeModel);
		});
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

}
