package com.liqiangit.cg.model;

public class UIFile {
	private Panel searchPanel;
	public Panel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(Panel searchPanel) {
		this.searchPanel = searchPanel;
	}

	private Panel tablePanel;
	private Panel formPanel;
	private Panel detailPanel;

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
