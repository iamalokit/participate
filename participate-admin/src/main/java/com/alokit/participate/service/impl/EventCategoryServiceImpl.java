package com.alokit.participate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alokit.participate.dto.EventCategoryWithSubCategories;
import com.alokit.participate.dto.RequestEventCategory;
import com.alokit.participate.mapper.EventCategoryMapper;
import com.alokit.participate.model.EventCategory;
import com.alokit.participate.model.EventCategoryExample;
import com.alokit.participate.service.EventCategoryService;

@Service
public class EventCategoryServiceImpl implements EventCategoryService{
	
	@Autowired
	private EventCategoryMapper eventCategoryMapper;
	
	@Override
	public int create(RequestEventCategory requestEventCategory) {
		EventCategory eventCategory = new EventCategory();
		BeanUtils.copyProperties(requestEventCategory, eventCategory);
        if (eventCategory.getParentId() == 0) {
        	eventCategory.setLevel(0);
        } else {
        	EventCategory parentCategory = eventCategoryMapper.selectByPrimaryKey(eventCategory.getParentId());
            if (parentCategory != null) {
            	eventCategory.setLevel(parentCategory.getLevel() + 1);
            } else {
            	eventCategory.setLevel(0);
            }
        }
        
        int count = eventCategoryMapper.insertSelective(eventCategory);
        return count;
	}

	@Override
	public int update(Long id, RequestEventCategory requestEventCategory) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EventCategory> getList(Long parentId, Integer pageSize, Integer pageNum) {
		EventCategoryExample example = new EventCategoryExample();
		example.setOrderByClause("sort desc");
		example.createCriteria().andParentIdEqualTo(parentId);
		return eventCategoryMapper.selectByExample(example);
	}

	@Override
	public int delete(Long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public EventCategory getItem(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateNavStatus(List<Long> ids, Integer navStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateShowStatus(List<Long> ids, Integer showStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<EventCategoryWithSubCategories> listWithChildren() {
		// TODO Auto-generated method stub
		return null;
	}

}
