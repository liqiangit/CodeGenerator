package com.liqiangit.cg.swing;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class CheckHeaderCellRenderer implements TableCellRenderer {
	CheckTableModle tableModel;
	JTableHeader tableHeader;
	final JCheckBox formShowBox;
	final JCheckBox tableShowBox;
	final JCheckBox searchShowBox;
	final JCheckBox detailShowBox;
	final JCheckBox requiredBox;

	public CheckHeaderCellRenderer(JTable table) {
		this.tableModel = (CheckTableModle) table.getModel();
		this.tableHeader = table.getTableHeader();
		formShowBox = new JCheckBox(tableModel.getColumnName(6));
		formShowBox.setSelected(false);

		tableShowBox = new JCheckBox(tableModel.getColumnName(7));
		tableShowBox.setSelected(false);

		searchShowBox = new JCheckBox(tableModel.getColumnName(8));
		searchShowBox.setSelected(false);

		detailShowBox = new JCheckBox(tableModel.getColumnName(9));
		detailShowBox.setSelected(false);

		requiredBox = new JCheckBox(tableModel.getColumnName(12));
		requiredBox.setSelected(false);

		tableHeader.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() > 0) {
					// 获得选中列
					int selectColumn = tableHeader.columnAtPoint(e.getPoint());
					if (selectColumn == 6) {
						boolean value = !formShowBox.isSelected();
						formShowBox.setSelected(value);
						tableModel.selectAllOrNull(value, selectColumn);
						tableHeader.repaint();
					}
					if (selectColumn == 7) {
						boolean value = !tableShowBox.isSelected();
						tableShowBox.setSelected(value);
						tableModel.selectAllOrNull(value, selectColumn);
						tableHeader.repaint();
					}
					if (selectColumn == 8) {
						boolean value = !searchShowBox.isSelected();
						searchShowBox.setSelected(value);
						tableModel.selectAllOrNull(value, selectColumn);
						tableHeader.repaint();
					}
					if (selectColumn == 9) {
						boolean value = !detailShowBox.isSelected();
						detailShowBox.setSelected(value);
						tableModel.selectAllOrNull(value, selectColumn);
						tableHeader.repaint();
					}
					if (selectColumn == 12) {
						boolean value = !requiredBox.isSelected();
						requiredBox.setSelected(value);
						tableModel.selectAllOrNull(value, selectColumn);
						tableHeader.repaint();
					}
				}
			}
		});
	}

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		String valueStr = (String) value;
		JLabel label = new JLabel(valueStr);
		label.setHorizontalAlignment(SwingConstants.CENTER); // 表头标签剧中
		formShowBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		formShowBox.setBorderPainted(true);

		tableShowBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		tableShowBox.setBorderPainted(true);

		searchShowBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		searchShowBox.setBorderPainted(true);

		detailShowBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		detailShowBox.setBorderPainted(true);

		requiredBox.setHorizontalAlignment(SwingConstants.CENTER);// 表头标签剧中
		requiredBox.setBorderPainted(true);
		JComponent component = label;
		if (column == 6) {
			component = formShowBox;
		}
		if (column == 7) {
			component = tableShowBox;
		}
		if (column == 8) {
			component = searchShowBox;
		}
		if (column == 9) {
			component = detailShowBox;
		}
		if (column == 12) {
			component = requiredBox;
		}
		component.setForeground(tableHeader.getForeground());
		component.setBackground(tableHeader.getBackground());
		component.setFont(tableHeader.getFont());
		component.setBorder(UIManager.getBorder("TableHeader.cellBorder"));

		return component;
	}

}
