package com.alokit.participate.core.response;

import java.util.List;

import org.springframework.data.domain.Page;

public class ApiPage<T> {

	private Integer pageNum;

	private Integer pageSize;

	private Integer totalPage;

	private Long total;

	private List<T> list;

	public static <T> ApiPage<T> restPage(List<T> list) {
		ApiPage<T> result = new ApiPage<T>();
		
		// need to verify this 
//		PageInfo<T> pageInfo = new PageInfo<T>(list);
//		result.setTotalPage(pageInfo.getPages());
//		result.setPageNum(pageInfo.getPageNum());
//		result.setPageSize(pageInfo.getPageSize());
//		result.setTotal(pageInfo.getTotal());
//		result.setList(pageInfo.getList());
		return result;
	}

	public static <T> ApiPage<T> restPage(Page<T> pageInfo) {
		ApiPage<T> result = new ApiPage<T>();
		result.setTotalPage(pageInfo.getTotalPages());
		result.setPageNum(pageInfo.getNumber());
		result.setPageSize(pageInfo.getSize());
		result.setTotal(pageInfo.getTotalElements());
		result.setList(pageInfo.getContent());
		return result;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

}
