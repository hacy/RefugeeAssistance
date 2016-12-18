package com.crossover.hackhathon.controller;

import java.util.List;

import com.google.common.collect.Lists;

public class RefugeeAssistItemListTransport {

	private Integer pageSize;
	private Long totalItemCount;	
	private List<RefugeeAssistEntityTransport> items;
	
	public RefugeeAssistItemListTransport() {
		items = Lists.newArrayList();
	}
	
	public void addUser(RefugeeAssistEntityTransport itemTransport) {
		items.add(itemTransport);		
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotalItemCount() {
		return totalItemCount;
	}

	public void setTotalItemCount(Long totalItemCount) {
		this.totalItemCount = totalItemCount;
	}

	public List<RefugeeAssistEntityTransport> getItems() {
		return items;
	}

	public void setItems(List<RefugeeAssistEntityTransport> items) {
		this.items = items;
	}	
		
}

