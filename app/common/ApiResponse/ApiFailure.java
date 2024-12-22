package common.ApiResponse;

import common.enums.ResponseStatus;

public class ApiFailure extends ApiResponse {
	public ApiFailure(String message, Object error) {
		super(ResponseStatus.failure, message, null, error);
	}
}
