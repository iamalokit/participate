package com.alokit.participate.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alokit.participate.core.response.ApiPage;
import com.alokit.participate.core.response.ApiResponse;
import com.alokit.participate.dto.RequestAdminLogin;
import com.alokit.participate.dto.RequestAdminRegister;
import com.alokit.participate.dto.RequestUpdateAdminPassword;
import com.alokit.participate.model.Admin;
import com.alokit.participate.model.AdminRole;
import com.alokit.participate.service.AdminService;
import com.alokit.participate.service.RoleService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@Value("${jwt.tokenHeader}")
	private String tokenHeader;

	@Value("${jwt.tokenHeader}")
	private String tokenHead;

	@Autowired
	private AdminService adminService;

	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Admin> register(@Validated @RequestBody RequestAdminRegister requestAdminRegister) {
		Admin admin = adminService.register(requestAdminRegister);
		if (admin == null) {
			return ApiResponse.failed();
		}
		return ApiResponse.success(admin);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Object> login(@Validated @RequestBody RequestAdminLogin requestAdminLogin) {
		String token = adminService.login(requestAdminLogin.getUsername(), requestAdminLogin.getPassword());
		if (token == null) {
			return ApiResponse.validateFailed("Invalid Token");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", token);
		tokenMap.put("tokenHead", tokenHead);
		return ApiResponse.success(tokenMap);
	}

	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<Object> refreshToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String refreshToken = adminService.refreshToken(token);
		if (refreshToken == null) {
			return ApiResponse.failed("Unable to refresh token");
		}
		Map<String, String> tokenMap = new HashMap<>();
		tokenMap.put("token", refreshToken);
		tokenMap.put("tokenHead", tokenHead);
		return ApiResponse.success(tokenMap);
	}

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse getAdminInfo(Principal principal) {
		if (principal == null) {
			return ApiResponse.unauthorized(null);
		}
		String username = principal.getName();
		Admin admin = adminService.getAdminByUsername(username);
		Map<String, Object> data = new HashMap<>();
		data.put("username", admin.getUsername());
		data.put("menus", roleService.getMenuList(admin.getId()));
		data.put("icon", admin.getIcon());
		List<AdminRole> roleList = adminService.getRoleList(admin.getId());
		if (!roleList.isEmpty()) {
			List<String> roles = roleList.stream().map(AdminRole::getName).collect(Collectors.toList());
			data.put("roles", roles);
		}
		return ApiResponse.success(data);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> logout() {
		return ApiResponse.success(null);
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<ApiPage<Admin>> list(@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<Admin> adminList = adminService.list(keyword, pageSize, pageNum);
		return ApiResponse.success(ApiPage.restPage(adminList));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<Admin> getItem(@PathVariable Long id) {
		Admin admin = adminService.getItem(id);
		return ApiResponse.success(admin);
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> update(@PathVariable Long id, @RequestBody Admin admin) {
		int count = adminService.update(id, admin);
		if (count > 0) {
			return ApiResponse.success(count);
		}
		return ApiResponse.failed();
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Object> updatePassword(
			@Validated @RequestBody RequestUpdateAdminPassword requestUpdateAdminPassword) {
		int status = adminService.updatePassword(requestUpdateAdminPassword);
		if (status > 0) {
			return ApiResponse.success(status);
		} else if (status == -1) {
			return ApiResponse.failed("Request is invalid");
		} else if (status == -2) {
			return ApiResponse.failed("User does not exist in the system");
		} else if (status == -3) {
			return ApiResponse.failed("Old Password do not match");
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> delete(@PathVariable Long id) {
		int count = adminService.delete(id);
		if (count > 0) {
			return ApiResponse.success(count);
		}
		return ApiResponse.failed();
	}

	@RequestMapping(value = "/updateStatus/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status) {
		Admin admin = new Admin();
		admin.setStatus(status);
		int count = adminService.update(id, admin);
		if (count > 0) {
			return ApiResponse.success(count);
		}
		return ApiResponse.failed();
	}

	@RequestMapping(value = "/role/update", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> updateRole(@RequestParam("adminId") Long adminId,
			@RequestParam("roleIds") List<Long> roleIds) {
		int count = adminService.updateRole(adminId, roleIds);
		if (count >= 0) {
			return ApiResponse.success(count);
		}
		return ApiResponse.failed();
	}

	@RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List<AdminRole>> getRoleList(@PathVariable Long adminId) {
		List<AdminRole> roleList = adminService.getRoleList(adminId);
		return ApiResponse.success(roleList);
	}
}
