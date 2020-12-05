package com.alokit.participate.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.alokit.participate.dto.RequestCreateEventLocation;
import com.alokit.participate.model.EventLocation;

public interface EventLocationService {

    List<EventLocation> listAllLocations();

    int createLocation(RequestCreateEventLocation pmsBrandParam);

    @Transactional
    int updateLocation(Long id, RequestCreateEventLocation pmsBrandParam);

    int deleteLocation(Long id);

    int deleteLocations(List<Long> ids);

    List<EventLocation> listLocations(String keyword, int pageNum, int pageSize);

    EventLocation getLocation(Long id);

    int updateShowStatus(List<Long> ids, Integer showStatus);

//    int updateFactoryStatus(List<Long> ids, Integer factoryStatus);


}
