package com.alokit.participate.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestUpdateAdminPassword {
	
	@NotEmpty
	@NotNull
	private String username;
	
	@NotEmpty
	@NotNull
	private String oldPassword;
	
	@NotEmpty
	@NotNull
	private String newPassword;

	public String getUsername() {
		return username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}
	
}
