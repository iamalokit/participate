package com.alokit.participate.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.alokit.participate.model.Event;


public interface EsEventService {
	
	int importAll();
	
	void delete(String eventId);
	
	Event create(String eventId);
	
	void delete(List<String> eventIds);
	
	Page<Event> search(String keyword, Integer pageNum, Integer pageSize);
	
	Page<Event> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize,Integer sort);

	Page<Event> recommend(Long id, Integer pageNum, Integer pageSize);
	
//	EsEventRelatedDetails searchRelatedInfo(String keyword);
}
