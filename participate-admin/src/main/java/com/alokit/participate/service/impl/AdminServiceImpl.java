package com.alokit.participate.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alokit.participate.core.exception.Asserts;
import com.alokit.participate.core.security.util.JwtTokenUtil;
import com.alokit.participate.core.util.RequestUtil;
import com.alokit.participate.dto.RequestAdminRegister;
import com.alokit.participate.dto.RequestUpdateAdminPassword;
import com.alokit.participate.mapper.AdminLoginLogMapper;
import com.alokit.participate.mapper.AdminMapper;
import com.alokit.participate.model.Admin;
import com.alokit.participate.model.AdminExample;
import com.alokit.participate.model.AdminLoginLog;
import com.alokit.participate.model.AdminRole;
import com.alokit.participate.service.AdminService;

public class AdminServiceImpl implements AdminService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AdminService.class);
	
	@Autowired
	private AdminMapper adminMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private AdminLoginLogMapper adminLoginLogMapper;
	
	@Override
	public Admin getAdminByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin register(RequestAdminRegister requestAdminRegister) {
		Admin admin = new Admin();
		BeanUtils.copyProperties(requestAdminRegister, admin);
		admin.setCreateTime(new Date());
		admin.setStatus(1);
		AdminExample example = new AdminExample();
		example.createCriteria().andUsernameEqualTo(admin.getUsername());
		List<Admin> adminList = adminMapper.selectByExample(example);
		if(adminList.size() > 0) {
			return null;
		}
		
		String encodedPassword = passwordEncoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		adminMapper.insert(admin);
		return admin;
	}

	@Override
	public String login(String username, String password) {
		String token = null;
		try {
			UserDetails userDetails = loadUserByUsername(username);
			if(!passwordEncoder.matches(password, userDetails.getPassword())) {
				Asserts.fail("Incorrect password");
			}
			
			if(!userDetails.isEnabled()) {
				Asserts.fail("User is not enabled");
			}
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			token = jwtTokenUtil.generateToken(userDetails);
			insertLoginLog(username);
		} catch (AuthenticationException e) {
			LOGGER.warn("Exception occurred during login", e);
		}
		return token;
	}

	private void insertLoginLog(String username) {
		Admin admin = getAdminByUsername(username);
		if(admin == null) {
			return;
		}
		
		AdminLoginLog loginLog = new AdminLoginLog();
		loginLog.setAdminId(admin.getId());
		loginLog.setCreateTime(new Date());
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		loginLog.setIp(RequestUtil.getRequestIp(request));
		adminLoginLogMapper.insert(loginLog);
		
		
	}

	@Override
	public String refreshToken(String oldToken) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Admin getItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Admin> list(String keyword, Integer pageSize, Integer pageNum) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(Long id, Admin admin) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRole(Long adminId, List<Long> roleIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AdminRole> getRoleList(Long adminId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updatePassword(RequestUpdateAdminPassword requestUpdateAdminPassword) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
