package com.alokit.participate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alokit.participate.dto.RequestCreateEvent;
import com.alokit.participate.mapper.EventLocationMapper;
import com.alokit.participate.mapper.EventMapper;
import com.alokit.participate.model.Event;
import com.alokit.participate.model.EventLocation;
import com.alokit.participate.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventMapper eventMapper;
	
	@Autowired
	private EventLocationMapper eventLocationMapper;

	@Override
	public int create(RequestCreateEvent requestCreateEvent) {
		Event event = new Event();
		BeanUtils.copyProperties(requestCreateEvent, event);
		// check if the event location exist by name
		// if not
		EventLocation eventLocation = new EventLocation();
		BeanUtils.copyProperties(requestCreateEvent.getEventLocation(), eventLocation);
		eventLocation.setCreateTime(event.getCreateTime());
		eventLocationMapper.insert(eventLocation);
        int count = eventMapper.insertSelective(event);
        return count;
	}

	@Override
	public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateNewStatus(List<Long> ids, Integer newStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Event> list(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(Long id) {
		return eventMapper.selectByPrimaryKey(id);
	}

}
