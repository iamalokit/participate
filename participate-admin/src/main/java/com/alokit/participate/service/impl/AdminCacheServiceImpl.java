package com.alokit.participate.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alokit.participate.core.cache.service.RedisService;
import com.alokit.participate.dao.AdminRoleRelationDao;
import com.alokit.participate.model.Admin;
import com.alokit.participate.model.Resource;
import com.alokit.participate.service.AdminCacheService;
import com.alokit.participate.service.AdminService;

@Service
public class AdminCacheServiceImpl implements AdminCacheService {

	@Autowired
	private AdminService adminService;

	@Autowired
	private RedisService redisService;
	
	@Autowired
	private AdminRoleRelationDao adminRoleRelationDao;

	@Value("${redis.database}")
	private String REDIS_DATABASE;

	@Value("${redis.key.admin}")
	private String REDIS_KEY_ADMIN;

	@Value("${redis.key.resourceList}")
	private String REDIS_KEY_RESOURCE_LIST;

	@Value("${redis.expire.common}")
	private Long REDIS_EXPIRE;

	@Override
	public void delResourceList(Long adminId) {
		String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + adminId;
		redisService.del(key);

	}

	@Override
	public void delResourceListByRole(Long roleId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delResourceListByRoleIds(List<Long> roleIds) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delResourceListByResource(Long resourceId) {
		List<Long> adminIdList = adminRoleRelationDao.getAdminIdList(resourceId);
		if(adminIdList != null && !adminIdList.isEmpty()) {
			String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
			List<String> keys = adminIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
			redisService.del(keys);
		}

	}

	@Override
	public Admin getAdmin(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setAdmin(Admin admin) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delAdmin(Long adminId) {
		Admin admin = adminService.getItem(adminId);
		if (admin != null) {
			String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + admin.getUsername();
			redisService.del(key);
		}
	}

	@Override
	public void setResourceList(Long adminId, List<Resource> resourceList) {
		String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
		redisService.set(key, resourceList, REDIS_EXPIRE);
	}

	@Override
	public List<Resource> getResourceList(Long adminId) {
		String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + adminId;
		return (List<Resource>) redisService.get(key);
	}

}
