package com.alokit.participate.service;

import java.util.List;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alokit.participate.dto.RequestCreateEvent;
import com.alokit.participate.model.Event;


public interface EventService {
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED)
	int create(RequestCreateEvent requestCreateEvent);	
	
	Event getEvent(Long id);

//    PmsProductResult getUpdateInfo(Long id);

//    @Transactional
//    int update(Long id, PmsProductParam productParam);

//    List<Event> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum);

    @Transactional
    int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail);

    int updatePublishStatus(List<Long> ids, Integer publishStatus);

    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);

    int updateNewStatus(List<Long> ids, Integer newStatus);

    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

    List<Event> list(String keyword);
}
