package v1.company;

import common.ApiResponse.ApiFailure;
import common.ApiResponse.ApiSuccess;
import common.ApiResponse.ErrorCode;
import common.company.CompanyModel;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.badRequest;
import static play.mvc.Results.ok;

public class CompanyController {

	private final Logger.ALogger logger = Logger.of("v1.CompanyController");

	private final CompanyResourceHandler resourceHandler;

	@Inject
	public CompanyController(CompanyResourceHandler resourceHandler) {
		this.resourceHandler = resourceHandler;
	}

	public CompletionStage<Result> get(Http.Request request, Long id) {
		logger.info("[" + request.id() + "] " + " json : " + request.body().asJson() + " id : " + id);
		return resourceHandler.getCompanyDetails(id)
				.thenApplyAsync(response -> {
					if (response == null) {
						logger.info("[" + request.id() + "] " + " error: " + "company id not found");
						return badRequest(Json.toJson(new ApiFailure("company id not found", new ErrorCode(request.id(), "COMPANY_ID", "company id not found"))));
					}
					logger.info("[" + request.id() + "] " + " response: " + response);
					return ok(Json.toJson(new ApiSuccess(response)));
				});
	}
}
