package com.alokit.participate.dto;

public class EventQueryParams {

	private Integer publishStatus;
	private Integer verifyStatus;
	private String keyword;
	private String productSn;
	private Long productCategoryId;
	private Long brandId;

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public String getKeyword() {
		return keyword;
	}

	public String getProductSn() {
		return productSn;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public Long getBrandId() {
		return brandId;
	}
}
