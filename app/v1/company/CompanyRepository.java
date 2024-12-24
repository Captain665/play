package v1.company;

import common.company.CompanyModel;

public interface CompanyRepository {

	public CompanyModel getDetailById(Long id);
}
