package com.alokit.participate.controller;

import java.util.List;

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
import com.alokit.participate.dto.EventCategoryWithSubCategories;
import com.alokit.participate.dto.RequestEventCategory;
import com.alokit.participate.model.EventCategory;
import com.alokit.participate.service.EventCategoryService;

@Controller
@RequestMapping("/eventCategory")
public class EventCategoryController {
	
	@Autowired
	private EventCategoryService eventCategoryService;

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> create(@Validated @RequestBody RequestEventCategory requestEventCategory) {
		int count = eventCategoryService.create(requestEventCategory);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> update(@PathVariable Long id,
			@Validated @RequestBody RequestEventCategory requestEventCategory) {
		int count = eventCategoryService.update(id, requestEventCategory);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<ApiPage<EventCategory>> getList(@PathVariable Long parentId,
			@RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		List<EventCategory> eventCategoryList = eventCategoryService.getList(parentId, pageSize, pageNum);
		return ApiResponse.success(ApiPage.restPage(eventCategoryList));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<EventCategory> getItem(@PathVariable Long id) {
		EventCategory productCategory = eventCategoryService.getItem(id);
		return ApiResponse.success(productCategory);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> delete(@PathVariable Long id) {
		int count = eventCategoryService.delete(id);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/update/navStatus", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> updateNavStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam("navStatus") Integer navStatus) {
		int count = eventCategoryService.updateNavStatus(ids, navStatus);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/update/showStatus", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> updateShowStatus(@RequestParam("ids") List<Long> ids,
			@RequestParam("showStatus") Integer showStatus) {
		int count = eventCategoryService.updateShowStatus(ids, showStatus);
		if (count > 0) {
			return ApiResponse.success(count);
		} else {
			return ApiResponse.failed();
		}
	}

	@RequestMapping(value = "/list/withChildren", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<List<EventCategoryWithSubCategories>> listWithChildren() {
		List<EventCategoryWithSubCategories> list = eventCategoryService.listWithChildren();
		return ApiResponse.success(list);
	}
}
