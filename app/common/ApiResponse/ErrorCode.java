package common.ApiResponse;

public class ErrorCode {
	public Long traceId;
	public String errorCode;
	public String message;

	public Long getTraceId() {
		return traceId;
	}

	public void setTraceId(Long traceId) {
		this.traceId = traceId;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ErrorCode(Long traceId, String errorCode, String message) {
		this.traceId = traceId;
		this.errorCode = errorCode;
		this.message = message;
	}
}
