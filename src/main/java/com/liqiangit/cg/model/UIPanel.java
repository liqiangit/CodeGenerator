package com.liqiangit.cg.model;

import java.util.ArrayList;
import java.util.List;

public class UIPanel {
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
	 * 主键
	 */
	private UIParam pk;
	/**
	 * 表单显示
	 */
	private List<UIParam> formList = new ArrayList<UIParam>();
	/**
	 * 列表显示
	 */
	private List<UIParam> tableList = new ArrayList<UIParam>();
	/**
	 * 查询显示
	 */
	private List<UIParam> searchList = new ArrayList<UIParam>();
	/**
	 * 详情显示
	 */
	private List<UIParam> detailList = new ArrayList<UIParam>();

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

	public UIParam getPk() {
		return pk;
	}

	public void setPk(UIParam pk) {
		this.pk = pk;
	}

	public List<UIParam> getFormList() {
		return formList;
	}

	public void setFormList(List<UIParam> formList) {
		this.formList = formList;
	}

	public List<UIParam> getTableList() {
		return tableList;
	}

	public void setTableList(List<UIParam> tableList) {
		this.tableList = tableList;
	}

	public List<UIParam> getSearchList() {
		return searchList;
	}

	public void setSearchList(List<UIParam> searchList) {
		this.searchList = searchList;
	}

	public List<UIParam> getDetailList() {
		return detailList;
	}

	public void setDetailList(List<UIParam> detailList) {
		this.detailList = detailList;
	}
}
