package v1.employee;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiSuccess;
import common.employee.resources.EmployeeResource;
import jakarta.inject.Inject;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class EmployeeController {

	private final Logger.ALogger logger = Logger.of("v1.employee.EmployeeController");
	private final EmployeeResourceHandler resourceHandler;

	@Inject
	public EmployeeController(EmployeeResourceHandler resourceHandler) {
		this.resourceHandler = resourceHandler;
	}

	public CompletionStage<Result> create(Http.Request request) {
		JsonNode json = request.body().asJson();
		EmployeeResource resource = Json.fromJson(json, EmployeeResource.class);
		logger.info("[ " + request.id() + "] " + " resource: " + resource);
		return resourceHandler.createOrUpdateEmployeeInfo(resource).thenApplyAsync(
				response -> {
					logger.info("[" + request.id() + "] " + " response: " + response);
					return ok(Json.toJson(new ApiSuccess(response)));
				}
		);
	}
}
