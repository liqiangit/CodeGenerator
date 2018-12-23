package com.liqiangit.cg.swing.demo;
 
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.util.concurrent.BrokenBarrierException;
 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
 
public class Tab1 extends JFrame implements ActionListener{
	JTable jt1;
	DefaultTableModel tableM;
	JScrollPane jsp1;
	JButton jb1,jb2,jb3,jb4;
	JPanel jp1;
	public static void main(String[] args) {
		new Tab1();
	}
	Tab1(){
		String[] name = new String[]{"列1","列2","列3","列4","列5"};
		String[][] data = new String[5][5];
		int value = 1;
		for (int i=0; i<data.length; i++){
			for(int j=0; j<data[i].length; j++){
				data[i][j] = String.valueOf(value++);
			}
		}
		//初始化表格模型，设置表头和数据
		tableM = new DefaultTableModel(data, name);
		//初始化表格，与模型建立联系
		jt1 = new JTable(tableM);
		//把表格放入滚动窗中
		jsp1 = new JScrollPane(jt1);
	
		
		jb1 = new JButton("添加列");
		jb2 = new JButton("添加行");
		jb3 = new JButton("删除列");
		jb4 = new JButton("删除行");
		jp1 = new JPanel();
		this.add(jp1, BorderLayout.SOUTH);
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		jp1.add(jb4);
		this.add(jsp1);
		
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jb4.addActionListener(this);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	//进行增加，删除表格都是对表格模型操作，与table无关
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(jb1)){
			tableM.addColumn("新增列");
		}
		//新增一行
		if (e.getSource().equals(jb2)){
			tableM.addRow(new Vector());
		}
		/**
		 * 删除列 做法： 先通过getColumnCount获取表格模型列数，通过 setColumnCount设置
		 * setColumnCount如果新大小   小于   当前大小，则将丢弃索引 columnCount 处及其之后的所有列
		 */
		if (e.getSource().equals(jb3)){
			int col = tableM.getColumnCount()-1;
			tableM.setColumnCount(col);
		}
		if (e.getSource().equals(jb4)){
			int row = tableM.getRowCount()-1;
			if(row < 0){
				return ;
			}
			tableM.removeRow(row);
		}
	}
}
