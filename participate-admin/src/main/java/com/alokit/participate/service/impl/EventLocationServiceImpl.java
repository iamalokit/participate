package com.alokit.participate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alokit.participate.dto.RequestCreateEventLocation;
import com.alokit.participate.mapper.EventLocationMapper;
import com.alokit.participate.model.EventLocation;
import com.alokit.participate.model.EventLocationExample;
import com.alokit.participate.service.EventLocationService;
import com.github.pagehelper.PageHelper;

@Service
public class EventLocationServiceImpl implements EventLocationService{
	
	@Autowired
	private EventLocationMapper eventLocationMapper;
	
	@Override
	public List<EventLocation> listAllLocations() {
		return eventLocationMapper.selectByExample(new EventLocationExample());
	}

	@Override
	public int createLocation(RequestCreateEventLocation requestCreateEventLocation) {
		EventLocation eventLocation = new EventLocation();
		BeanUtils.copyProperties(requestCreateEventLocation, eventLocation);
		return eventLocationMapper.insertSelective(eventLocation);
	}

	@Override
	public int updateLocation(Long id, RequestCreateEventLocation requestCreateEventLocation) {
		EventLocation eventLocation = new EventLocation();
		BeanUtils.copyProperties(requestCreateEventLocation, eventLocation);
		eventLocation.setId(id);
		return eventLocationMapper.updateByPrimaryKeySelective(eventLocation);
	}

	@Override
	public int deleteLocation(Long id) {
		return eventLocationMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int deleteLocations(List<Long> ids) {
		EventLocationExample locationExample = new EventLocationExample();
		locationExample.createCriteria().andIdIn(ids);
		return eventLocationMapper.deleteByExample(locationExample);
	}

	@Override
	public List<EventLocation> listLocations(String keyword, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EventLocationExample eventLocationExample = new EventLocationExample();
		eventLocationExample.setOrderByClause("sort desc");
		EventLocationExample.Criteria criteria = eventLocationExample.createCriteria();
		if(!"".equals(keyword)) {
			criteria.andLocationNameLike("%" + keyword + "%");
		}
		return eventLocationMapper.selectByExample(eventLocationExample);
	}

	@Override
	public EventLocation getLocation(Long id) {
		return eventLocationMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateShowStatus(List<Long> ids, Integer showStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

}
