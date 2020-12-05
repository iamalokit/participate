package com.alokit.participate.minio;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alokit.participate.core.response.ApiResponse;

@Controller
@RequestMapping("/minio")
public class MinioController {
	
	@Autowired
	private MinioService minioService;
	
	@CrossOrigin
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<MinioUploadDto> upload(@Validated @RequestParam("file") MultipartFile file) {
		String filename = file.getOriginalFilename();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String objectName = sdf.format(new Date()) + "/" + filename;
		String url = minioService.uploadObject(objectName, file);
		if (url != null && !"".equals(url)) {
			MinioUploadDto minioUploadDto = new MinioUploadDto();
			minioUploadDto.setName(filename);
			minioUploadDto.setUrl(url);
			return ApiResponse.success(minioUploadDto);
		}
		return ApiResponse.failed();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public ApiResponse<Integer> delete(@RequestParam("objectName") String objectName) {
		boolean isDeleted = minioService.deleteObject(objectName);
		if (isDeleted) {
			return ApiResponse.success(null);
		}
		return ApiResponse.failed();
	}

}
