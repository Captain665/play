import com.google.inject.AbstractModule;
import v1.company.CompanyRepository;
import v1.company.JPACompanyRepository;
import v1.employee.EmployeeRepository;
import v1.employee.JPAEmployeeRepository;

public class Module extends AbstractModule {

	@Override
	protected void configure() {
		bind(EmployeeRepository.class).to(JPAEmployeeRepository.class);
		bind(CompanyRepository.class).to(JPACompanyRepository.class);
	}

}
