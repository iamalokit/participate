package com.alokit.participate.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RequestAdminRegister {
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	private String icon;
	
	@Email
	private String email;
	
	private String nickName;
	
	private String note;
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getIcon() {
		return icon;
	}
	public String getEmail() {
		return email;
	}
	public String getNickName() {
		return nickName;
	}
	public String getNote() {
		return note;
	}
}
