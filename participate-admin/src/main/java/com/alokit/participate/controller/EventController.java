package com.alokit.participate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alokit.participate.core.response.ApiPage;
import com.alokit.participate.core.response.ApiResponse;
import com.alokit.participate.dto.CreateEvent;
import com.alokit.participate.dto.EventQueryParams;
import com.alokit.participate.model.Event;
import com.alokit.participate.service.EventService;

@Controller
@RequestMapping(path = "/event")
public class EventController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;
	
	
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ApiPage<Event>> getList(EventQueryParams eventQueryParams,
                                                        @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<Event> eventList = eventService.list(eventQueryParams, pageSize, pageNum);
        return ApiResponse.success(ApiPage.restPage(eventList));
    }
	/**
	 * 
	 * @param requestCreateEvent
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> create(@Validated @RequestBody CreateEvent createEventParams) {
		int count = eventService.create(createEventParams);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<Event> getEvent(@PathVariable Long id) {
		Event event = eventService.getEvent(id);
		return ApiResponse.success(event);
	}
	
	/**
	 * 
	 * @param requestGetEventDetails
	 * @return
	 */
//	@PostMapping(path = "/getEventDetails")
//	@ResponseBody
//	public ApiResponse<Event> getEventDetails(@Validated @RequestBody RequestGetEventDetails requestGetEventDetails) {
//		Event event = null;
//		try {
//			event = eventService.findByEventId(requestGetEventDetails.getEventId());
//			if (event == null) {
//				LOGGER.error("Cannot find any event by eventId-" + requestGetEventDetails.getEventId());
//				return ApiResponse.failed("Cannot find this event");
//			}
//		} catch (Exception e) {
//			LOGGER.error("Exception occurred while getting the details of the event", e);
//			return ApiResponse.failed("Exception occurred while creating an event");
//		}
//		return ApiResponse.success(event);
//	}
	
	/**
	 * 
	 * @param requestGetEventDetails
	 * @return
	 */
//	@PostMapping(path = "/getEventsByUserLocation")
//	@ResponseBody
//	public ApiResponse<Event> getEventsByUserLocation(@Validated @RequestBody RequestGetEventDetails requestGetEventDetails) {
//		Event event = null;
//		try {
//			event = eventService.findByEventId(requestGetEventDetails.getEventId());
//			if (event == null) {
//				LOGGER.error("Cannot find any event by eventId-" + requestGetEventDetails.getEventId());
//			}
//		} catch (Exception e) {
//			LOGGER.error("Exception occurred while creating an event", e);
//		}
//		return ApiResponse.success(event);
//	}
	
	/**
	 * 
	 * @return
	 */
//	@GetMapping(path = "/all")
//	public @ResponseBody Iterable<Event> getAllEvents() {
//		return eventService.findAll();
//	}
}
