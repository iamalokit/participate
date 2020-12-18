package com.alokit.participate.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.alokit.participate.model.AdminRole;
import com.alokit.participate.model.AdminRoleRelation;
import com.alokit.participate.model.Resource;

public interface AdminRoleRelationDao {
	
	int insertList(@Param("list") List<AdminRoleRelation> adminRoleRelationList);

	List<AdminRole> getRoleList(@Param("adminId") Long adminId);

	List<Resource> getResourceList(@Param("adminId") Long adminId);
	
	List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
