package v1.employee;

import com.fasterxml.jackson.databind.JsonNode;
import common.ApiResponse.ApiSuccess;
import play.Logger;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.concurrent.CompletableFuture.supplyAsync;
import static play.mvc.Results.ok;

public class EmployeeController {

	Logger.ALogger logger = Logger.of("v1.employee.EmployeeController");

	public CompletionStage<Result> create(Http.Request request) {
		JsonNode json = request.body().asJson();
		logger.info("[ " + request.id() + "] " + " json " + json);
		return supplyAsync(() -> ok(Json.toJson(new ApiSuccess("response is ok"))));
	}
}
