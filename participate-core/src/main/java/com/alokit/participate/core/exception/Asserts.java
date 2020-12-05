package com.alokit.participate.core.exception;

import com.alokit.participate.core.response.IResultCode;

public class Asserts {
	public static void fail(String message) {
		throw new ApiException(message);
	}
	
	public static void fail(IResultCode resultCode) {
		throw new ApiException(resultCode);
	}
}
