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
import com.alokit.participate.dto.RequestCreateEventLocation;
import com.alokit.participate.model.EventLocation;
import com.alokit.participate.service.EventLocationService;

@Controller
@RequestMapping("/location")
public class EventLocationController {
    @Autowired
    private EventLocationService locationService;

    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<List<EventLocation>> getList() {
        return ApiResponse.success(locationService.listAllLocations());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Integer> create(@Validated @RequestBody RequestCreateEventLocation requestCreateEventLocation) {
        int count = locationService.createLocation(requestCreateEventLocation);
        if (count == 1) {
            return ApiResponse.success(count);
        } else {
            return ApiResponse.failed();
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Integer> update(@PathVariable("id") Long id,
                               @Validated @RequestBody RequestCreateEventLocation requestCreateEventLocation) {
        int count = locationService.updateLocation(id, requestCreateEventLocation);
        if (count == 1) {
            return ApiResponse.success(count);
        } else {
            return ApiResponse.failed();
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<Integer> delete(@PathVariable("id") Long id) {
        int count = locationService.deleteLocation(id);
        if (count == 1) {
            return ApiResponse.success(null);
        } else {
            return ApiResponse.failed();
        }
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<ApiPage<EventLocation>> getList(@RequestParam(value = "keyword", required = false) String keyword,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        List<EventLocation> brandList = locationService.listLocations(keyword, pageNum, pageSize);
        return ApiResponse.success(ApiPage.restPage(brandList));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ApiResponse<EventLocation> getItem(@PathVariable("id") Long id) {
        return ApiResponse.success(locationService.getLocation(id));
    }

    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public ApiResponse<Integer> deleteBatch(@RequestParam("ids") List<Long> ids) {
        int count = locationService.deleteLocations(ids);
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
        int count = locationService.updateShowStatus(ids, showStatus);
        if (count > 0) {
            return ApiResponse.success(count);
        } else {
            return ApiResponse.failed();
        }
    }
    
//  @RequestMapping(value = "/update/factoryStatus", method = RequestMethod.POST)
//  @ResponseBody
//  public ApiResponse<Integer> updateFactoryStatus(@RequestParam("ids") List<Long> ids,
//                                    @RequestParam("factoryStatus") Integer factoryStatus) {
//      int count = locationService.updateFactoryStatus(ids, factoryStatus);
//      if (count > 0) {
//          return ApiResponse.success(count);
//      } else {
//          return ApiResponse.failed();
//      }
//  }
}

