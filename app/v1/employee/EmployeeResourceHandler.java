package v1.employee;

import common.company.CompanyModel;
import common.employee.EmployeeBuilder;
import common.employee.EmployeeModel;
import common.employee.resources.EmployeeResource;
import jakarta.inject.Inject;
import play.Logger;
import v1.company.CompanyRepository;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class EmployeeResourceHandler {
	private final Logger.ALogger logger = Logger.of("v1.employee.EmployeeController.EmployeeResourceHandler");

	private final EmployeeRepository repository;

	@Inject
	public EmployeeResourceHandler(EmployeeRepository repository) {
		this.repository = repository;
	}

	public CompletionStage<EmployeeResource> createOrUpdateEmployeeInfo(EmployeeResource resource, CompanyModel companyModel) {
		EmployeeModel model = new EmployeeBuilder()
				.setFullName(resource.getFullName())
				.setMobile(resource.getMobile())
				.setEmailId(resource.getEmailId())
				.setGender(resource.getGender())
				.setJoiningDate(resource.getJoiningDate())
				.setResignDate(resource.getResignDate())
				.setRole(resource.getRole())
				.setLocation(resource.getLocation())
				.setCompany(companyModel)
				.setAssets(resource.getAssets())
				.build();
		return repository.createOrUpdate(model).thenComposeAsync(
				responseModel -> {
					EmployeeResource employeeResource = new EmployeeResource(responseModel);
					return supplyAsync(() -> employeeResource);
				}
		);
	}
}
