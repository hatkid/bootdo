package com.bootdo.common.utils;

import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 查询参数
 */
public class Query extends LinkedHashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	// 
	private int offset;
	// 每页条数
	private int limit;

	// 页码
	private int page;

	public Query(Map<String, Object> params) {
		this.putAll(params);
		// 分页参数
		if (StringUtils.isEmpty(params.get("offset"))) {
			this.offset = 1;
		} else {
			this.offset = Integer.parseInt(params.get("offset").toString());
		}
		if (StringUtils.isEmpty(params.get("limit"))) {
			this.limit = 1;
		} else {
			this.limit = Integer.parseInt(params.get("limit").toString());
		}


		this.put("offset", offset);
		this.put("page", offset / limit + 1);
		this.put("limit", limit);
		this.page = offset / limit + 1;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.put("offset", offset);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
}
