package common.ApiResponse;

import common.enums.ResponseStatus;

public class ApiSuccess extends ApiResponse {
	public ApiSuccess(Object result) {
		super(ResponseStatus.success, null, result, null);
	}
}
