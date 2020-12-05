package com.alokit.participate.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alokit.participate.dto.EventCategoryWithSubCategories;
import com.alokit.participate.dto.RequestEventCategory;
import com.alokit.participate.model.EventCategory;

public interface EventCategoryService {

    @Transactional
    int create(RequestEventCategory requestEventCategory);
    
    @Transactional
    int update(Long id, RequestEventCategory requestEventCategory);

    List<EventCategory> getList(Long parentId, Integer pageSize, Integer pageNum);

    int delete(Long id);

    EventCategory getItem(Long id);

    int updateNavStatus(List<Long> ids, Integer navStatus);

    int updateShowStatus(List<Long> ids, Integer showStatus);

    List<EventCategoryWithSubCategories> listWithChildren();
}
