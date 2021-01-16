package com.alokit.participate.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alokit.participate.dto.CreateEvent;
import com.alokit.participate.dto.EventQueryParams;
import com.alokit.participate.mapper.EventLocationMapper;
import com.alokit.participate.mapper.EventMapper;
import com.alokit.participate.model.Event;
import com.alokit.participate.model.EventExample;
import com.alokit.participate.model.EventLocation;
import com.alokit.participate.service.EventService;
import com.github.pagehelper.PageHelper;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventMapper eventMapper;
	
	@Autowired
	private EventLocationMapper eventLocationMapper;

	@Override
	public int create(CreateEvent requestCreateEvent) {
		Event event = new Event();
		BeanUtils.copyProperties(requestCreateEvent, event);
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

	@Override
	public List<Event> list(EventQueryParams eventQueryParams, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        EventExample eventExample = new EventExample();
        EventExample.Criteria criteria = eventExample.createCriteria();
//        criteria.andDeleteStatusEqualTo(0);
//        if (eventQueryParams.getPublishStatus() != null) {
//            criteria.andPublishStatusEqualTo(eventQueryParams.getPublishStatus());
//        }
//        if (eventQueryParams.getVerifyStatus() != null) {
//            criteria.andVerifyStatusEqualTo(eventQueryParams.getVerifyStatus());
//        }
//        if (!StringUtils.isEmpty(eventQueryParams.getKeyword())) {
//            criteria.andNameLike("%" + eventQueryParams.getKeyword() + "%");
//        }
//        if (!StringUtils.isEmpty(eventQueryParams.getProductSn())) {
//            criteria.andProductSnEqualTo(eventQueryParams.getProductSn());
//        }
//        if (eventQueryParams.getBrandId() != null) {
//            criteria.andBrandIdEqualTo(eventQueryParams.getBrandId());
//        }
//        if (eventQueryParams.getProductCategoryId() != null) {
//            criteria.andProductCategoryIdEqualTo(eventQueryParams.getProductCategoryId());
//        }
        return eventMapper.selectByExample(eventExample);
    }

}
