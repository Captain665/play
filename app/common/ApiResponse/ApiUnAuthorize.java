package common.ApiResponse;

import common.enums.ResponseStatus;

public class ApiUnAuthorize extends ApiResponse {
	public ApiUnAuthorize(String message, Object error) {
		super(ResponseStatus.unauthorized, message, null, error);
	}
}
