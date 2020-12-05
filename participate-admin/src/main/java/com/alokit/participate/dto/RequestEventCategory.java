package com.alokit.participate.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class RequestEventCategory {

	private Long parentId;

	@NotEmpty
	private String name;

	private int level;

//    private String productUnit;
//    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")

//    private Integer navStatus;
//    @FlagValidator(value = {"0","1"},message = "状态只能为0或1")

//    private Integer showStatus;

	@Min(value = 0)
	private Integer sort;

	private String icon;

	private String description;

	public Long getParentId() {
		return parentId;
	}

	public String getName() {
		return name;
	}

	public Integer getSort() {
		return sort;
	}

	public String getIcon() {
		return icon;
	}

	public String getDescription() {
		return description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
