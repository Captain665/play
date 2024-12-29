package v1.company;

import common.company.CompanyModel;

import java.util.concurrent.CompletionStage;

public interface CompanyRepository {

	public CompletionStage<CompanyModel> getDetailById(Long id);
}
