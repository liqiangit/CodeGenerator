package com.liqiangit.cg.model;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Panel {
	private String tagStart;

	public String getTagStart() {
		return tagStart;
	}

	public void setTagStart(String tagStart) {
		this.tagStart = tagStart;
	}

	public String getTagEnd() {
		return tagEnd;
	}

	public void setTagEnd(String tagEnd) {
		this.tagEnd = tagEnd;
	}

	private String tagEnd;
	private List<Panel> children = new ArrayList<Panel>();

	public void addChild(Panel panel) {
		children.add(panel);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		toString(builder);
		return builder.toString();
	}

	private void toString(StringBuilder builder) {
		if (StringUtils.isNotEmpty(tagStart)) {
			builder.append(tagStart).append("\r\n");
		}
		if (children != null) {
			for (Panel panel : children) {
				panel.toString(builder);
			}
		}
		if (StringUtils.isNotEmpty(tagEnd)) {
			builder.append(tagEnd).append("\r\n");
		}
	}

	public static void main(String[] args) {
		System.out.println(3 % 2);
		Panel panel = new Panel();
		panel.setTagStart("<div>");
		panel.setTagEnd("</div>");

		Panel panel2 = new Panel();
		panel2.setTagStart("text");
		panel.addChild(panel2);
		System.out.println(panel);
	}
}
