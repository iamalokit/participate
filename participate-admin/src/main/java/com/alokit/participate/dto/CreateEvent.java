package com.alokit.participate.dto;

import java.util.Date;

public class CreateEvent {

	private String eventName;

	private String eventType;

	private Long eventCategoryId;

	private String participantType;

	private String eventDescription;
	
	private String eventLocation;

	private Date eventDate;

	private String eventCost;

	private Long createdBy;

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
	
	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
}
