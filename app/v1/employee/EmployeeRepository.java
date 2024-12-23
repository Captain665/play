package v1.employee;

import common.employee.EmployeeModel;

import java.util.concurrent.CompletionStage;

public interface EmployeeRepository {
	public CompletionStage<EmployeeModel> createOrUpdate(EmployeeModel model);
}
