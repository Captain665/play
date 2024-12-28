package v1.employee;

import common.company.CompanyModel;
import common.employee.EmployeeBuilder;
import common.employee.EmployeeModel;
import common.employee.EmployeeSalary;
import common.employee.resources.EmployeeResource;
import jakarta.inject.Inject;
import play.Logger;
import v1.company.CompanyRepository;

import java.math.BigDecimal;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;

public class EmployeeResourceHandler {
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
				.setSalary(calculateEmployeeSalary(resource.getSalaryStructure()))
				.build();
		return repository.createOrUpdate(model).thenComposeAsync(
				responseModel -> {
					EmployeeResource employeeResource = new EmployeeResource(responseModel);
					employeeResource.setCompanyDetails(companyModel);
					return supplyAsync(() -> employeeResource);
				}
		);
	}

	private EmployeeSalary calculateEmployeeSalary(EmployeeSalary employeeSalary) {
		BigDecimal baseAmount = employeeSalary.getBaseAmount() != null ? employeeSalary.getBaseAmount() : BigDecimal.ZERO;
		BigDecimal hra = employeeSalary.getHra() != null ? employeeSalary.getHra() : BigDecimal.ZERO;
		BigDecimal pf = employeeSalary.getPf() != null ? employeeSalary.getPf() : BigDecimal.ZERO;
		BigDecimal medical = employeeSalary.getMedical() != null ? employeeSalary.getMedical() : BigDecimal.ZERO;
		BigDecimal tax = employeeSalary.getTax() != null ? employeeSalary.getTax() : BigDecimal.ZERO;
		BigDecimal totalAmount = baseAmount.add(hra).add(pf).add(medical).subtract(tax);
		employeeSalary.setTotalAmount(totalAmount);
		return employeeSalary;
	}
}
