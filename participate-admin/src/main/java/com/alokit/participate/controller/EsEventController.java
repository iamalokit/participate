package com.alokit.participate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alokit.participate.core.response.ApiPage;
import com.alokit.participate.core.response.ApiResponse;
import com.alokit.participate.model.Event;
import com.alokit.participate.service.EsEventService;


@Controller
@RequestMapping("/esEvent")
public class EsEventController {

//	@Autowired
//	private EsEventService esEventService;

	@RequestMapping(value = "/importAllEvents", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> importAllEvents() {
//		int eventCount = esEventService.importAll();
//		return ApiResponse.success(eventCount);
		return ApiResponse.success(0);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<Object> delete(@PathVariable String eventId) {
//		esEventService.delete(eventId);
		return ApiResponse.success(null);
	}

	@RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Object> delete(@RequestParam("ids") List<String> ids) {
//		esEventService.delete(ids);
		return ApiResponse.success(null);
	}

	@RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Event> create(@PathVariable String id) {
//		Event esEvent = esEventService.create(id);
//		if (esEvent != null) {
//			return ApiResponse.success(esEvent);
//		} else {
//			return ApiResponse.failed();
//		}
		return null;
	}

	@RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ApiPage<Event>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<Event> esProductPage = esEventService.search(keyword, pageNum, pageSize);
//        return ApiResponse.success(ApiPage.restPage(esProductPage));
		return null;
    }

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public ApiResponse<ApiPage<Event>> search(@RequestParam(required = false) String keyword,
			@RequestParam(required = false) Long brandId, @RequestParam(required = false) Long productCategoryId,
			@RequestParam(required = false, defaultValue = "0") Integer pageNum,
			@RequestParam(required = false, defaultValue = "5") Integer pageSize,
			@RequestParam(required = false, defaultValue = "0") Integer sort) {
//		Page<Event> esProductPage = esEventService.search(keyword, brandId, productCategoryId, pageNum, pageSize,
//				sort);
//		return ApiResponse.success(ApiPage.restPage(esProductPage));
		return null;
	}
	
	@RequestMapping(value = "/recommend/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ApiPage<Event>> recommend(@PathVariable Long id,
                                                         @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                         @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        Page<Event> esProductPage = esEventService.recommend(id, pageNum, pageSize);
//        return ApiResponse.success(ApiPage.restPage(esProductPage));
		return null;
    }
	
//    @RequestMapping(value = "/search/relate", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<EsProductRelatedInfo> searchRelatedInfo(@RequestParam(required = false) String keyword) {
//        EsProductRelatedInfo productRelatedInfo = esProductService.searchRelatedInfo(keyword);
//        return CommonResult.success(productRelatedInfo);
//    }
}
