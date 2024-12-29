package v1.employee;

import common.employee.EmployeeModel;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface EmployeeRepository {
	public CompletionStage<EmployeeModel> createOrUpdate(EmployeeModel model);

	public CompletionStage<List<EmployeeModel>> getEmployeeList(Long companyId);
}
