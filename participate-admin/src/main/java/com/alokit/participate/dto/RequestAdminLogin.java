package com.alokit.participate.dto;

import javax.validation.constraints.NotEmpty;

public class RequestAdminLogin {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
