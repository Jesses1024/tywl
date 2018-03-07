package com.puxintech.tywl.util;

import java.util.List;

import lombok.Data;

@Data
public class Page<T> {

	private final List<T> list;

	private final Long total;

	private final Integer page;

	private final Integer size;

	private final String sortBy;

	private final Boolean ascending;

	public Page(List<T> list, long total, PageRequest page) {
		this.list = list;
		this.total = total;
		this.page = page == null ? null : page.getPage();
		this.size = page == null ? null : page.getSize();
		this.sortBy = page == null ? null : page.getSortBy();
		this.ascending = page == null ? null : page.getAscending();
	}

}
