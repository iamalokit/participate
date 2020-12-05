package com.alokit.participate.core.exception;

import com.alokit.participate.core.response.IResultCode;

public class ApiException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IResultCode resultCode;
	
	public ApiException(IResultCode resultCode) {
		super(resultCode.getMessage());
		this.resultCode = resultCode;
	}
	
	public ApiException(String message) {
		super(message);
	}
	
	public ApiException(Throwable cause) {
		super(cause);
	}
	
	public ApiException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public IResultCode getResultCode() {
		return resultCode;
	}
}
