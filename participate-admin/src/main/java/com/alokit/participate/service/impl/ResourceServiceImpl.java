package com.alokit.participate.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alokit.participate.mapper.ResourceMapper;
import com.alokit.participate.model.Resource;
import com.alokit.participate.model.ResourceExample;
import com.alokit.participate.service.AdminCacheService;
import com.alokit.participate.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceMapper resourceMapper;
	
	@Autowired
	private AdminCacheService adminCacheService;
	
	@Override
	public int create(Resource resource) {
		resource.setCreateTime(new Date());
		return resourceMapper.insert(resource);
	}
	@Override
	public int update(Long id, Resource resource) {
		resource.setId(id);
		int count = resourceMapper.updateByPrimaryKeySelective(resource);
		adminCacheService.delResourceListByResource(id);
		return count;
	}
	@Override
	public Resource getItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize,
			Integer pageNum) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Resource> listAll() {
		return resourceMapper.selectByExample(new ResourceExample());
	}
	
}
