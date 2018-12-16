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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

//https://blog.csdn.net/dc15822445347/article/details/8515681
//https://blog.csdn.net/qq_36238595/article/details/72801594
//https://blog.csdn.net/hwj528/article/details/53817657
//https://bbs.csdn.net/topics/350173541
public class TableSortDemo extends JPanel {
	private boolean DEBUG = false;
	DefaultTableModel tableM;
	JTable table;
	JButton jButton;
	public TableSortDemo() {
		super(new GridLayout(2, 0));
//		MyTableModel model=new MyTableModel();
//		JTable table = new JTable(model);
//		 new Boolean(false), new Boolean(false), new Boolean(false)
		String[] name = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", "表单显示", "列表显示", "查询显示", "校验规则",
				"必填", "长度", "是否主键" };
		Object[][] data = {{ "序号", "2", "字段备注", "国际化", "控件类型", "字典", "","","", "校验规则",
			"必填", "长度", new Boolean(false) }};
//		int value = 1;
//		for (int i=0; i<data.length; i++){
//			for(int j=0; j<data[i].length; j++){
//				data[i][j] = String.valueOf(value++);
//			}
//		}
		//初始化表格模型，设置表头和数据
		tableM = new DefaultTableModel(data, name);
		table = new JTable(tableM);
		
		 Vector item = new Vector();
         item.add("1");
         item.add("2");
         item.add("3");
         item.add("4");
         item.add("5");
         item.add("5");
         JComboBox JComboBoxItem = new JComboBox(item);
         TableColumn  brandColumn = table.getColumnModel().getColumn(1);
         brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem)); 
         
         
         JCheckBox jCheckBox = new JCheckBox();
         jCheckBox.setText("");
         TableColumn  brandColumn2 = table.getColumnModel().getColumn(6);
         brandColumn2.setCellEditor(new DefaultCellEditor(jCheckBox)); 
         
//		Object[] rowData = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", new Boolean(false), new Boolean(false), new Boolean(false), "校验规则",
//				"必填", "长度", new Boolean(false) };
//		model.addRow(rowData);
		table.setPreferredScrollableViewportSize(new Dimension(500, 170));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		// Create the scroll pane and add the table to it.
//		JPanel jPanel=new JPanel();
//		jPanel.setLayout(new GridLayout(1, 0));
		jButton=new JButton("取值");
		jButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				int rowCount=table.getRowCount();
				int columnCount=table.getColumnCount();
				for (int i = 0; i < rowCount; i++) {
					for (int j = 0; j < columnCount; j++) {
						Object value=table.getValueAt(i, j);
						System.out.print(value+",");
					}
					System.out.println();
				}
			}
		});
//		jPanel.add(jButton);
//		jPanel.add(table);
		JScrollPane scrollPane = new JScrollPane(table);
		// Add the scroll pane to this panel.
		add(jButton);
		add(scrollPane);
	}

	class MyTableModel extends AbstractTableModel {
		private String[] columnNames = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", "表单显示", "列表显示", "查询显示", "校验规则",
				"必填", "长度", "是否主键" };
		private Object[][] data = {{ "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", new Boolean(false), new Boolean(false), new Boolean(false), "校验规则",
			"必填", "长度", new Boolean(false) }};

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			return data.length;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}

		/*
		 * JTable uses this method to determine the default renderer/ editor for
		 * each cell. If we didn't implement this method, then the last column
		 * would contain text ("true"/"false"), rather than a check box.
		 */
		public Class getColumnClass(int c) {
			return getValueAt(0, c).getClass();
		}

		/*
		 * Don't need to implement this method unless your table's editable.
		 */
		public boolean isCellEditable(int row, int col) {
			// Note that the data/cell address is constant,
			// no matter where the cell appears onscreen.
			if (col < 2) {
				return false;
			} else {
				return true;
			}
		}

		/*
		 * Don't need to implement this method unless your table's data can
		 * change.
		 */
		public void setValueAt(Object value, int row, int col) {
			if (DEBUG) {
				System.out.println("Setting value at " + row + "," + col + " to " + value + " (an instance of "
						+ value.getClass() + ")");
			}

			data[row][col] = value;
			// Normally, one should call fireTableCellUpdated() when
			// a value is changed. However, doing so in this demo
			// causes a problem with TableSorter. The tableChanged()
			// call on TableSorter that results from calling
			// fireTableCellUpdated() causes the indices to be regenerated
			// when they shouldn't be. Ideally, TableSorter should be
			// given a more intelligent tableChanged() implementation,
			// and then the following line can be uncommented.
			// fireTableCellUpdated(row, col);

			if (DEBUG) {
				System.out.println("New value of data:");
				printDebugData();
			}
		}

		private void printDebugData() {
			int numRows = getRowCount();
			int numCols = getColumnCount();

			for (int i = 0; i < numRows; i++) {
				System.out.print("    row " + i + ":");
				for (int j = 0; j < numCols; j++) {
					System.out.print("  " + data[i][j]);
				}
				System.out.println();
			}
			System.out.println("--------------------------");
		}
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be
	 * invoked from the event-dispatching thread.
	 */
	private static void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("TableSortDemo");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create and set up the content pane.
		TableSortDemo newContentPane = new TableSortDemo();
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
