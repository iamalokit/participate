package com.alokit.participate.service;

import java.util.List;

import com.alokit.participate.model.Admin;
import com.alokit.participate.model.Resource;

public interface AdminCacheService {
	void delAdmin(Long adminId);

	void delResourceList(Long adminId);

	void delResourceListByRole(Long roleId);

	void delResourceListByRoleIds(List<Long> roleIds);

	void delResourceListByResource(Long resourceId);

	Admin getAdmin(String username);

	void setAdmin(Admin admin);

	List<Resource> getResourceList(Long adminId);

	void setResourceList(Long adminId, List<Resource> resourceList);
}
