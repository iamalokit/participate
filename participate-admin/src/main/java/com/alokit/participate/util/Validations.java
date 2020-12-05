package com.alokit.participate.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alokit.participate.dto.TimeSlot;

public class Validations {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Validations.class);
	
	public static boolean isValidEventDate(Map<String, List<TimeSlot>> mapEventDates) {
		boolean isValid = false;
		try {
			if(mapEventDates != null && !mapEventDates.isEmpty()) {
				Date date = null;
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				for(String eventDate: mapEventDates.keySet()) {
					date = dateFormat.parse(eventDate);
					if(date != null) {
						List<TimeSlot> timeSlots = mapEventDates.get(eventDate);
						if(isValidTimeSlots(timeSlots)) {
							isValid = true;
						}
					} else {
						LOGGER.error("Invalid event date format - " + eventDate);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while validating the event dates", e);
		}
		
		return isValid;
	}

	public static boolean isValidTimeSlots(List<TimeSlot> timeSlots) {
		try {
			if(timeSlots != null && !timeSlots.isEmpty()) {
				DateFormat dateFormat = new SimpleDateFormat("HH:mm");
				Date startTime = null;
				Date endTime = null;
				for(TimeSlot timeSlot: timeSlots) {
					startTime = dateFormat.parse(timeSlot.getStartTime());
					endTime = dateFormat.parse(timeSlot.getEndTime());
					// comparing two times 
					if(endTime.getTime() - startTime.getTime() <= 0) {
						LOGGER.error("Invalid time slot startTime - " + startTime.getTime() + " endTime" + endTime.getTime());
						return false;
					}
				}
			} else {
				LOGGER.error("timeSlots is empty or null");
			}
		} catch (Exception e) {
			LOGGER.error("Exception occurred while validating the time slots", e);
		}
		return true;
	}
}
