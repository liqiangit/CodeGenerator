package com.liqiangit.cg.swing.demo;

import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

class MyComboBoxEditor extends DefaultCellEditor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
 
	public MyComboBoxEditor(String[] items) {
		super(new JComboBox(items));
	}
}
