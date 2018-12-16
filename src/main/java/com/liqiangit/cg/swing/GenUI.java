/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.liqiangit.cg.swing;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;

/*
 * TableSortDemo.java requires no other files.
 */

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import com.liqiangit.cg.Person;
import com.liqiangit.cg.ReflectionUtil;
//Java-Reflection反射-获取包括父类在内的所有字段
//https://blog.csdn.net/qq_32452623/article/details/54025185
import com.liqiangit.cg.UIUtils;
import com.liqiangit.cg.generator.easyui.EasyuiUIcg;
import com.liqiangit.cg.model.UIFile;
import com.liqiangit.cg.model.UIPanel;
import com.liqiangit.cg.model.UIParam;
import com.liqiangit.cg.model.UIParams;

//https://blog.csdn.net/dc15822445347/article/details/8515681
//https://blog.csdn.net/qq_36238595/article/details/72801594
//https://blog.csdn.net/hwj528/article/details/53817657
//https://bbs.csdn.net/topics/350173541
public class GenUI extends JPanel {
	private boolean DEBUG = false;
	DefaultTableModel tableM;
	JTextField textField;
	JTable table;
	/**
	 * 清空列表
	 */
	JButton clearListButton;
	/**
	 * 生成HTML代码
	 */
	JButton generateButton;
	/**
	 * 加载字段
	 */
	JButton loadFieldsButton;

	public GenUI() {
		super(new GridLayout(5, 0));
		// MyTableModel model=new MyTableModel();
		// JTable table = new JTable(model);
		// new Boolean(false), new Boolean(false), new Boolean(false)
		String[] name = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", "表单显示", "列表显示", "查询显示", "校验规则", "必填", "长度",
				"是否主键" };
		Object[][] data = {
				{ "序号", "2", "字段备注", "国际化", "控件类型", "字典", "", "", "", "校验规则", "必填", "长度", new Boolean(false) } };
		// int value = 1;
		// for (int i=0; i<data.length; i++){
		// for(int j=0; j<data[i].length; j++){
		// data[i][j] = String.valueOf(value++);
		// }
		// }
		// 初始化表格模型，设置表头和数据
		tableM = new DefaultTableModel(null, name);
		table = new JTable(tableM);

		Vector item = new Vector();
		item.add("text");
		item.add("combobox");
		JComboBox JComboBoxItem = new JComboBox(item);
		TableColumn brandColumn = table.getColumnModel().getColumn(4);
		brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem));

		JCheckBox jCheckBox = new JCheckBox();
		jCheckBox.setText("");
		TableColumn brandColumn2 = table.getColumnModel().getColumn(6);
		brandColumn2.setCellEditor(new DefaultCellEditor(jCheckBox));

		// Object[] rowData = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", new
		// Boolean(false), new Boolean(false), new Boolean(false), "校验规则",
		// "必填", "长度", new Boolean(false) };
		// model.addRow(rowData);
		table.setPreferredScrollableViewportSize(new Dimension(500, 170));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		// Create the scroll pane and add the table to it.
		// JPanel jPanel=new JPanel();
		// jPanel.setLayout(new GridLayout(1, 0));
		textField = new JTextField();
		textField.setText(Person.class.getName());

		/**
		 * 清空列表
		 */
		// JButton clearListButton;
		/**
		 * 生成HTML代码
		 */
		// JButton generateButton;
		/**
		 * 加载字段
		 */
		// JButton loadFieldsButton;

		loadFieldsButton = new JButton("加载字段");
		loadFieldsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {

					public void run() {
						String clazz = textField.getText();
						try {
							List<Field> fields = ReflectionUtil.getFields(Class.forName(clazz));
							int i=0;
							for (Field field : fields) {
								i++;
								Object[] data = { "序号", field.getName(), "字段备注"+i, "国际化"+i, "控件类型", "字典", "", "", "",
										"校验规则", "必填", "长度", new Boolean(false) };
								tableM.addRow(data);
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				}).start();
			}
		});

		clearListButton = new JButton("清空列表");
		clearListButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {

					public void run() {
						int row = tableM.getRowCount()-1;
						for (int i = row; i >=0; i--) {
							tableM.removeRow(i);
						}
					}
				}).start();
			}
		});

		generateButton = new JButton("生成HTML代码");
		generateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {

					public void run() {
						List<UIParam> uiParams = new ArrayList<UIParam>();
						int rowCount = table.getRowCount();
						int columnCount = table.getColumnCount();
						String[][] values=new String[rowCount][columnCount];
						for (int i = 0; i < rowCount; i++) {
							for (int j = 0; j < columnCount; j++) {
								Object value = table.getValueAt(i, j);
//								System.out.print(value + ",");
								values[i][j]=String.valueOf(value);
							}
//							System.out.println();
						}
						for (int i = 0; i < rowCount; i++) {
							UIParam uiParam=new UIParam();
							uiParam.setName(values[i][1]);
							uiParam.setType("text");
							uiParam.setUrl("../gender.json");
							uiParam.setLabel(values[i][2]);
							uiParam.setFormShow(true);
							uiParam.setDetailShow(true);
							uiParam.setSearchShow(true);
							uiParam.setTableShow(true);
							uiParams.add(uiParam);
						}
						UIParams params = new UIParams();
						params.setEntity("person");
						params.setFormColumns(2);
						params.setUiParams(uiParams);
						generate(params);
					}
				}).start();

			}
		});
		// jPanel.add(jButton);
		// jPanel.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		// Add the scroll pane to this panel.
		add(textField);
		add(loadFieldsButton);
		add(clearListButton);
		add(generateButton);

		add(scrollPane);
	}
	public static void generate(UIParams params){
		try {
			UIPanel uiPanel = UIUtils.convert(params);
			EasyuiUIcg easyuiUIcg = new EasyuiUIcg();
			UIFile uiFile = easyuiUIcg.generate(uiPanel);
//		System.out.println(uiFile.getFormPanel());
			System.out.println(uiFile.getSearchPanel());
			System.out.println(uiFile.getTablePanel());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("页面自动生成器");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		GenUI newContentPane = new GenUI();
		newContentPane.setOpaque(true); // content panes must be opaque
		frame.setContentPane(newContentPane);

		// Display the window.
		frame.pack();
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
}
