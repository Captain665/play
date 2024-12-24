package v1.employee;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.ApiResponse.ErrorCode;
import common.company.CompanyModel;
import common.employee.resources.EmployeeResource;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import v1.company.CompanyRepository;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class EmployeeController {

	private final Logger.ALogger logger = Logger.of("v1.employee.EmployeeController");
	private final EmployeeResourceHandler resourceHandler;
	private final CompanyRepository companyRepository;

	@Inject
	public EmployeeController(EmployeeResourceHandler resourceHandler, CompanyRepository companyRepository) {
		this.resourceHandler = resourceHandler;
		this.companyRepository = companyRepository;
	}

	public CompletionStage<Result> create(Http.Request request) {
		JsonNode json = request.body().asJson();
		EmployeeResource resource = Json.fromJson(json, EmployeeResource.class);
		logger.info("[ " + request.id() + "] " + " resource: " + resource);
		CompanyModel companyModel = companyRepository.getDetailById(resource.getCompany());
		if (companyModel == null) {
			return supplyAsync(() -> badRequest(Json.toJson(
					new ApiFailure("company id is not present",
							new ErrorCode(request.id(), "COMPANY_ID", "company id is not present")))));
		}
		return resourceHandler.createOrUpdateEmployeeInfo(resource, companyModel)
				.thenApplyAsync(
						response -> {
							logger.info("[" + request.id() + "] " + " response: " + response);
							return ok(Json.toJson(new ApiSuccess(response)));
						}
				);
	}
}
