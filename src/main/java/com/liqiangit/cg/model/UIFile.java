package com.liqiangit.cg.model;

import com.liqiangit.cg.ui.Panel;

public class UIFile {
	private Panel listPanel;
	private Panel searchPanel;
	private Panel tablePanel;
	private Panel formPanel;
	private Panel detailPanel;

	public Panel getListPanel() {
		return listPanel;
	}

	public void setListPanel(Panel listPanel) {
		this.listPanel = listPanel;
	}

	public Panel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(Panel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public Panel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(Panel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public Panel getFormPanel() {
		return formPanel;
	}

	public void setFormPanel(Panel formPanel) {
		this.formPanel = formPanel;
	}

	public Panel getDetailPanel() {
		return detailPanel;
	}

	public void setDetailPanel(Panel detailPanel) {
		this.detailPanel = detailPanel;
	}
}
