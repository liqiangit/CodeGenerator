package com.liqiangit.cg.model;

public class UIParam {
	/**
	 * id
	 */
	private String id;
	/**
	 * 字段名称
	 */
	private String name;
	/**
	 * 字段备注
	 */
	private String label;
	/**
	 * 国际化
	 */
	private String labelMessage;
	/**
	 * 控件类型
	 */
	private String tag;
	/**
	 * 控件类型
	 */
	private String type;
	/**
	 * 字典
	 */
	private String url;
	/**
	 * 校验规则
	 */
	private String validatorRule;
	/**
	 * 必填
	 */
	private Boolean required = false;
	/**
	 * 长度
	 */
	private String length;
	/**
	 * 表单显示
	 */
	private Boolean formShow = false;
	/**
	 * 列表显示
	 */
	private Boolean tableShow = false;
	/**
	 * 查询显示
	 */
	private Boolean searchShow = false;
	/**
	 * 详情显示
	 */
	private Boolean detailShow = false;
	/**
	 * 是否主键
	 */
	private Boolean isPk = false;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabelMessage() {
		return labelMessage;
	}

	public void setLabelMessage(String labelMessage) {
		this.labelMessage = labelMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getFormShow() {
		return formShow;
	}

	public void setFormShow(Boolean formShow) {
		this.formShow = formShow;
	}

	public Boolean getTableShow() {
		return tableShow;
	}

	public void setTableShow(Boolean tableShow) {
		this.tableShow = tableShow;
	}

	public Boolean getSearchShow() {
		return searchShow;
	}

	public void setSearchShow(Boolean searchShow) {
		this.searchShow = searchShow;
	}

	public Boolean getDetailShow() {
		return detailShow;
	}

	public void setDetailShow(Boolean detailShow) {
		this.detailShow = detailShow;
	}

	public String getValidatorRule() {
		return validatorRule;
	}

	public void setValidatorRule(String validatorRule) {
		this.validatorRule = validatorRule;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public Boolean getIsPk() {
		return isPk;
	}

	public void setIsPk(Boolean isPk) {
		this.isPk = isPk;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
