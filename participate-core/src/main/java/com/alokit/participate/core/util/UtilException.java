package com.alokit.participate.core.util;

public class UtilException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4890429619321451050L;
	
	public UtilException(Throwable e) {
		super(e.getMessage(), e);
	}
	
	public UtilException(String message) {
		super(message);
	}
	
	public UtilException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}
