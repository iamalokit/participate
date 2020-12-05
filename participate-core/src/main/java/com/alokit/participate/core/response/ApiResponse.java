package com.alokit.participate.core.response;

public class ApiResponse<T> {
	private long code;
	private String message;
	private T data;
	
	protected ApiResponse() {
		
	}
	
	protected ApiResponse(long code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}
	

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
	
	public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }
	
	public static <T> ApiResponse<T> success(T data, String message) {
        return new ApiResponse<T>(ResultCode.SUCCESS.getCode(), message, data);
    }
	
	public static <T> ApiResponse<T> failed(IResultCode resultCode) {
        return new ApiResponse<T>(resultCode.getCode(), resultCode.getMessage(), null);
    }
	
	public static <T> ApiResponse<T> failed(IResultCode resultCode,String message) {
        return new ApiResponse<T>(resultCode.getCode(), message, null);
    }
	
	public static <T> ApiResponse<T> failed(String message) {
        return new ApiResponse<T>(ResultCode.FAILED.getCode(), message, null);
    }
	
	public static <T> ApiResponse<T> failed() {
        return failed(ResultCode.FAILED);
    }
	
	public static <T> ApiResponse<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }
	
	public static <T> ApiResponse<T> validateFailed(String message) {
        return new ApiResponse<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }
	
	public static <T> ApiResponse<T> unauthorized(T data) {
        return new ApiResponse<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }
	
	public static <T> ApiResponse<T> forbidden(T data) {
        return new ApiResponse<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
}
