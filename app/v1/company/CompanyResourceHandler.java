package v1.company;

import common.company.CompanyModel;
import common.company.resources.CompanyResponseResource;
import common.employee.resources.EmployeeResponseResource;
import jakarta.inject.Inject;
import play.api.libs.json.Json;
import v1.employee.EmployeeRepository;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class CompanyResourceHandler {

	private final CompanyRepository repository;
	private final EmployeeRepository employeeRepository;

	@Inject
	public CompanyResourceHandler(CompanyRepository repository, EmployeeRepository employeeRepository) {
		this.repository = repository;
		this.employeeRepository = employeeRepository;
	}

	public CompletionStage<CompanyResponseResource> getCompanyDetails(Long companyId) {
		return repository.getDetailById(companyId)
				.thenComposeAsync(companyModel -> {
					if (companyModel != null) {
						return employeeRepository.getEmployeeList(companyId)
								.thenApplyAsync(employeeModels -> {
									System.out.println("run here ................");
									CompanyResponseResource companyResponseResource = new CompanyResponseResource(companyModel);
									companyResponseResource.setEmployeeDetails(employeeModels.stream().map(EmployeeResponseResource::new).toList());
									companyResponseResource.setNumberOfEmployee(BigInteger.valueOf(employeeModels.size()));
									return companyResponseResource;
								});
					}
					return CompletableFuture.completedFuture(null);
				});
	}
}
