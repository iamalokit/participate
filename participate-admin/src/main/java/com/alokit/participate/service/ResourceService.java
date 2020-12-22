package com.alokit.participate.service;

import java.util.List;

import com.alokit.participate.model.Resource;

public interface ResourceService {
	
	int create(Resource resource);
	
	int update(Long id, Resource resource);
	
	Resource getItem(Long id);
	
	int delete(Long id);
	
	List<Resource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize, Integer pageNum);
	
	List<Resource> listAll();
}
