package com.alokit.participate.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestCreateEvent{

	@NotNull(message = "eventName cannot be null")
	@NotEmpty(message = "eventName cannot be empty")
	private String eventName;

	@NotNull(message = "eventType cannot be null")
	@NotEmpty(message = "eventType cannot be empty")
	private String eventType;

	@NotNull(message = "eventCategory cannot be null")
	private Long eventCategoryId;
	
	@NotNull(message = "participantType cannot be null")
	@NotEmpty(message = "participantType cannot be empty")
	private String participantType;
	
	

	@NotNull(message = "eventDescription cannot be null")
	@NotEmpty(message = "eventDescription cannot be empty")
	private String eventDescription;
	
	
//	@NotNull(message = "eventDate cannot be null")
//	@NotEmpty(message = "eventDate cannot be empty")
//	private Map<String, List<TimeSlot>> eventDate;
	
	@NotNull(message = "eventDate cannot be null")
	private Date eventDate;

	

	@NotNull(message = "eventLocation cannot be null")
	private RequestCreateEventLocation eventLocation;

	@NotNull(message = "eventCost cannot be null")
	@NotEmpty(message = "eventCost cannot be empty")
	private String eventCost;

	@NotNull(message = "createdBy cannot be null")
	private Long createdBy;

	@NotNull(message = "createTime cannot be null")
	private Date createTime;

	public Date getCreateTime() {
		return createTime;
	}

	public String getEventName() {
		return eventName;
	}

	public String getEventType() {
		return eventType;
	}

	public Long getEventCategoryId() {
		return eventCategoryId;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public String getEventCost() {
		return eventCost;
	}

	public Long getCreatedBy() {
		return createdBy;
	}
//
//	public Map<String, List<TimeSlot>> getEventDate() {
//		return eventDate;
//	}

	public RequestCreateEventLocation getEventLocation() {
		return eventLocation;
	}
	
	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	
	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}
}
