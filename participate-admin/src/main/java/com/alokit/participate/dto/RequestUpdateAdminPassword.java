package com.alokit.participate.dto;

import javax.validation.constraints.NotEmpty;

public class RequestUpdateAdminPassword {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String oldPassword;
	
	@NotEmpty
	private String newPassword;
	
}
