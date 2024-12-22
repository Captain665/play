package v1.employee;

import play.libs.Json;
import play.mvc.Result;

import static play.mvc.Results.ok;

public class EmployeeController {

	public Result create() {
		return ok(Json.toJson("ok"));
	}
}
