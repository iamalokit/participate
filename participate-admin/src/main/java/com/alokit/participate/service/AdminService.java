package com.alokit.participate.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import com.alokit.participate.dto.RequestAdminRegister;
import com.alokit.participate.dto.RequestUpdateAdminPassword;
import com.alokit.participate.model.Admin;
import com.alokit.participate.model.AdminRole;

public interface AdminService {
	
	Admin getAdminByUsername(String username);
	
	Admin register(RequestAdminRegister requestAdminRegister);
	
	String login(String username, String password);
	
	String refreshToken(String oldToken);
	
	Admin getItem(Long id);
	
	List<Admin> list(String keyword, Integer pageSize, Integer pageNum);
	
	int update(Long id, Admin admin);
	
	int delete(Long id);
	
	@Transactional
    int updateRole(Long adminId, List<Long> roleIds);
	
	List<AdminRole> getRoleList(Long adminId);
	
	
//	List<UmsResource> getResourceList(Long adminId);
	
	int updatePassword(RequestUpdateAdminPassword requestUpdateAdminPassword);
	
	UserDetails loadUserByUsername(String username);
}
