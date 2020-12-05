package com.alokit.participate.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class RequestGetEventDetails {
	@NotNull
	@NotEmpty
	String eventId;

	public String getEventId() {
		return eventId;
	}
	
}
