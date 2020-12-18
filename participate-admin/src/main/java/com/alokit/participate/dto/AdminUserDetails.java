package com.alokit.participate.dto;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alokit.participate.model.Admin;
import com.alokit.participate.model.Resource;

public class AdminUserDetails implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Admin admin;
	private List<Resource> resourceList;
	
	public AdminUserDetails(Admin admin, List<Resource> resourceList ) {
		this.admin = admin;
		this.resourceList = resourceList;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return resourceList.stream()
				.map(role -> new SimpleGrantedAuthority(role.getId() + ":" + role.getName()))
				.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return admin.getPassword();
	}

	@Override
	public String getUsername() {
		return admin.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return admin.getStatus().equals(1);
	}

}
