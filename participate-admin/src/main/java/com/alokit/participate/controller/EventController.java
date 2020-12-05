package com.alokit.participate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alokit.participate.core.response.ApiResponse;
import com.alokit.participate.dto.RequestCreateEvent;
import com.alokit.participate.model.Event;
import com.alokit.participate.service.EventService;

@Controller
@RequestMapping(path = "/event")
public class EventController {
	private static final Logger LOGGER = LoggerFactory.getLogger(EventController.class);

	@Autowired
	private EventService eventService;
	
	/**
	 * 
	 * @param requestCreateEvent
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> create(@Validated @RequestBody RequestCreateEvent requestCreateEvent) {
		int count = eventService.create(requestCreateEvent);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
//		Event event = new Event();
//		try {
//			event.setEventName(requestCreateEvent.getEventName());
//			event.setEventType(requestCreateEvent.getEventType());
//			event.setEventCategoryId(requestCreateEvent.getEventCategoryId());
//			event.setEventDescription(requestCreateEvent.getEventDescription());
//			Map<String, List<TimeSlot>> eventDate = requestCreateEvent.getEventDate();
//			if (Validations.isValidEventDate(eventDate)) {
//				ObjectMapper mapperObj = new ObjectMapper();
//				String strEventDates = mapperObj.writeValueAsString(eventDate);
//				event.setEventDate(strEventDates);
//			}
//			event.setEventCost(requestCreateEvent.getEventCost());
//			event.setCreateTime(requestCreateEvent.getCreateTime());
//			event.setCreatedBy(requestCreateEvent.getCreatedBy());
//			RequestCreateEventLocation requestEventLocation = requestCreateEvent.getEventLocation();
//			EventLocation eventLocation = eventLocationService
//					.findByLocationName(requestEventLocation.getLocationName());
//			if (eventLocation == null) {
//				eventLocation = new EventLocation();
//				eventLocation.setLocationName(requestEventLocation.getLocationName());
//				eventLocation.setAddressLine1(requestEventLocation.getAddressLine1());
//				eventLocation.setAddressLine2(requestEventLocation.getAddressLine2());
//				eventLocation.setCity(requestEventLocation.getCity());
//				eventLocation.setState(requestEventLocation.getState());
//				eventLocation.setCountry(requestEventLocation.getCountry());
//				eventLocation.setZipCode(requestEventLocation.getZipCode());
//				eventLocation.setCreatedBy(requestCreateEvent.getCreatedBy());
//				eventLocation.setCreateTime(requestCreateEvent.getCreateTime());
//				eventLocationService.save(eventLocation);
//			}
//			event.setEventLocationId(eventLocation.getId());
//			eventService.save(event);
//		} catch (Exception e) {
//			LOGGER.error("Exception occurred while creating an event", e);
//			return ApiResponse.failed("Exception occurred while creating an event");
//		}
//		return ApiResponse.success(event.getId());
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
