package com.liqiangit.cg.swing;

import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class MyDefaultTableModel extends DefaultTableModel {
	Class[] typeArray = { Object.class, Object.class, Object.class, Object.class, Object.class, Object.class,
			Boolean.class, Boolean.class, Boolean.class, Boolean.class, Object.class, JComboBox.class, Boolean.class,
			Object.class, Boolean.class };

	// "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", "表单显示", "列表显示", "查询显示",
	// "详情显示", "查询方式", "校验规则",
	// "必填", "长度", "是否主键"
	public MyDefaultTableModel(Object[][] data, Object[] columnNames) {
		super(data, columnNames);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// 返回每一列的数据类型
		return typeArray[columnIndex];
	}

	@Override
	public Object getValueAt(int row, int column) {
		return super.getValueAt(row, column);
	}

}
