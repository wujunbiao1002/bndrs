package com.bndrs.voice.common;

import java.util.List;

public class GridResult implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Long total;
	private List<?> rows;

	public GridResult() {
	}

	public GridResult(Long total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> rows) {
		this.rows = rows;
	}
}
