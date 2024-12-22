package common.ApiResponse;

import common.enums.ResponseStatus;

public class ApiResponse {

	public ResponseStatus status;
	public String message;
	public Object result;
	public Object error;


	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public ApiResponse(ResponseStatus status, String message, Object result, Object error) {
		this.status = status;
		this.message = message;
		this.result = result;
		this.error = error;
	}

}
