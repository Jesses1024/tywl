package com.puxintech.tywl.util;

import org.springframework.util.Assert;

import lombok.Data;

@Data
public class PageRequest {

	public static final String ASC = "ASC";

	public static final String DESC = "DESC";

	private final Integer page;

	private final Integer size;

	private final String sortBy;

	private final Boolean ascending;

	public PageRequest(Integer page, Integer size, String sortBy, Boolean ascending) {
		Assert.isTrue(page >= 0, "page 不能小于 0");
		Assert.isTrue(size > 0, "size 不能小于 1");
		this.page = page;
		this.size = size;
		this.sortBy = sortBy;
		this.ascending = ascending;
	}

	public int getOffset() {
		return page * size;
	}

	public String getDirection() {
		return ascending ? ASC : DESC;
	}
}
