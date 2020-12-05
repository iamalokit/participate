package com.alokit.participate.dto;

import java.util.List;

import com.alokit.participate.model.EventCategory;

public class EventCategoryWithSubCategories extends EventCategory{
	private List<EventCategory> subCategories;
}
