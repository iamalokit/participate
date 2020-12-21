package com.alokit.participate.service.impl;

import java.util.ArrayList;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alokit.participate.core.exception.Asserts;
import com.alokit.participate.core.security.util.JwtTokenUtil;
import com.alokit.participate.core.util.RequestUtil;
import com.alokit.participate.dao.AdminRoleRelationDao;
import com.alokit.participate.dto.AdminUserDetails;
import com.alokit.participate.dto.RequestAdminRegister;
import com.alokit.participate.dto.RequestUpdateAdminPassword;
import com.alokit.participate.mapper.AdminLoginLogMapper;
import com.alokit.participate.mapper.AdminMapper;
import com.alokit.participate.mapper.AdminRoleRelationMapper;
import com.alokit.participate.model.Admin;
import com.alokit.participate.model.AdminExample;
import com.alokit.participate.model.AdminLoginLog;
import com.alokit.participate.model.AdminRole;
import com.alokit.participate.model.AdminRoleRelation;
import com.alokit.participate.model.AdminRoleRelationExample;
import com.alokit.participate.model.Resource;
import com.alokit.participate.service.AdminCacheService;
import com.alokit.participate.service.AdminService;
import com.github.pagehelper.PageHelper;

@Service
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

	@Autowired
	private AdminCacheService adminCacheService;

	@Autowired
	private AdminRoleRelationMapper adminRoleRelationMapper;

	@Autowired
	private AdminRoleRelationDao adminRoleRelationDao;

	@Override
	public Admin getAdminByUsername(String username) {
		Admin admin = adminCacheService.getAdmin(username);
		if (admin != null) {
			return admin;
		}
		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andUsernameEqualTo(username);
		List<Admin> adminList = adminMapper.selectByExample(adminExample);
		if (adminList != null && adminList.size() > 0) {
			admin = adminList.get(0);
			adminCacheService.setAdmin(admin);
			return admin;
		}

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
		if (adminList.size() > 0) {
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
			if (!passwordEncoder.matches(password, userDetails.getPassword())) {
				Asserts.fail("Incorrect password");
			}

			if (!userDetails.isEnabled()) {
				Asserts.fail("User is not enabled");
			}

			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
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
		if (admin == null) {
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
		return jwtTokenUtil.refreshHeadToken(oldToken);
	}

	@Override
	public Admin getItem(Long id) {
		return adminMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Admin> list(String keyword, Integer pageSize, Integer pageNum) {
		PageHelper.startPage(pageNum, pageSize);
		AdminExample example = new AdminExample();
		AdminExample.Criteria criteria = example.createCriteria();
		if (!"".equals(keyword)) {
			criteria.andUsernameLike("%" + keyword + "%");
			example.or(example.createCriteria().andNickNameLike("%" + keyword + "%"));
		}

		return adminMapper.selectByExample(example);
	}

	@Override
	public int update(Long id, Admin admin) {
		admin.setId(id);
		Admin rawAdmin = adminMapper.selectByPrimaryKey(id);
		if (rawAdmin.getPassword().equals(admin.getPassword())) {
			admin.setPassword(null);
		} else {
			if ("".equalsIgnoreCase(admin.getPassword())) {
				admin.setPassword(null);
			} else {
				admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			}
		}
		int count = adminMapper.updateByPrimaryKeySelective(admin);
		adminCacheService.delAdmin(id);
		return count;
	}

	@Override
	public int delete(Long id) {
		adminCacheService.delAdmin(id);
		int count = adminMapper.deleteByPrimaryKey(id);
		adminCacheService.delResourceList(id);
		return count;
	}

	@Override
	public int updateRole(Long adminId, List<Long> roleIds) {
		int count = roleIds == null ? 0 : roleIds.size();
		AdminRoleRelationExample adminRoleRelationExample = new AdminRoleRelationExample();
		adminRoleRelationExample.createCriteria().andAdminIdEqualTo(adminId);
		adminRoleRelationMapper.deleteByExample(adminRoleRelationExample);

		if (roleIds != null && !roleIds.isEmpty()) {
			List<AdminRoleRelation> list = new ArrayList<>();
			for (Long roleId : roleIds) {
				AdminRoleRelation roleRelation = new AdminRoleRelation();
				roleRelation.setAdminId(adminId);
				roleRelation.setRoleId(roleId);
				list.add(roleRelation);
			}

			adminRoleRelationDao.insertList(list);

		}

		adminCacheService.delResourceList(adminId);
		return count;
	}

	@Override
	public List<AdminRole> getRoleList(Long adminId) {
		return adminRoleRelationDao.getRoleList(adminId);
	}

	@Override
	public int updatePassword(RequestUpdateAdminPassword requestUpdateAdminPassword) {
		AdminExample adminExample = new AdminExample();
		adminExample.createCriteria().andUsernameEqualTo(requestUpdateAdminPassword.getUsername());
		List<Admin> adminList = adminMapper.selectByExample(adminExample);
		if (adminList == null || adminList.isEmpty()) {
			return -2;
		}
		Admin admin = adminList.get(0);

		if (!passwordEncoder.matches(requestUpdateAdminPassword.getOldPassword(), admin.getPassword())) {
			return -3;
		}
		
		admin.setPassword(passwordEncoder.encode(requestUpdateAdminPassword.getNewPassword()));
        adminMapper.updateByPrimaryKey(admin);
        adminCacheService.delAdmin(admin.getId());
        return 1;

	}

	@Override
	public UserDetails loadUserByUsername(String username) {
		Admin admin = getAdminByUsername(username);
		if (admin != null) {
			List<Resource> resourceList = getResourceList(admin.getId());
			return new AdminUserDetails(admin, resourceList);
		}
		throw new UsernameNotFoundException("User does not exist in the system");
	}

	@Override
	public List<Resource> getResourceList(Long adminId) {
		List<Resource> resourceList = adminCacheService.getResourceList(adminId);
		if (resourceList != null && !resourceList.isEmpty()) {
			return resourceList;
		}

		resourceList = adminRoleRelationDao.getResourceList(adminId);
		if (resourceList != null && !resourceList.isEmpty()) {
			adminCacheService.setResourceList(adminId, resourceList);
		}
		return resourceList;
	}

}
