package com.liqiangit.cg.model;

import java.util.ArrayList;
import java.util.List;

public class UIParams {
	/**
	 * 实体
	 */
	private String entity;
	/**
	 * 表单列数
	 */
	private Integer formColumns = 2;
	/**
	 * 详情列数
	 */
	private Integer detailColumns = 2;
	/**
	 * 查询框列数
	 */
	private Integer searchColumns = 2;
	/**
	 * 表格列数（-1表示无限）
	 */
	private Integer tableColumns = -1;
	/**
	 * 1 普通，2 左边树
	 */
	private Integer listLayout = 1;

	public Integer getListLayout() {
		return listLayout;
	}

	public void setListLayout(Integer listLayout) {
		this.listLayout = listLayout;
	}

	/**
	 * 表单显示
	 */
	private List<UIParam> uiParams = new ArrayList<UIParam>();

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public Integer getFormColumns() {
		return formColumns;
	}

	public void setFormColumns(Integer formColumns) {
		this.formColumns = formColumns;
	}

	public Integer getDetailColumns() {
		return detailColumns;
	}

	public void setDetailColumns(Integer detailColumns) {
		this.detailColumns = detailColumns;
	}

	public Integer getSearchColumns() {
		return searchColumns;
	}

	public void setSearchColumns(Integer searchColumns) {
		this.searchColumns = searchColumns;
	}

	public Integer getTableColumns() {
		return tableColumns;
	}

	public void setTableColumns(Integer tableColumns) {
		this.tableColumns = tableColumns;
	}

	public List<UIParam> getUiParams() {
		return uiParams;
	}

	public void setUiParams(List<UIParam> uiParams) {
		this.uiParams = uiParams;
	}

}
