package com.liqiangit.cg.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.liqiangit.cg.Person;
import com.liqiangit.cg.model.UIFile;
import com.liqiangit.cg.model.UIPanel;
import com.liqiangit.cg.model.UIParam;
import com.liqiangit.cg.model.UIParams;
import com.liqiangit.cg.ui.Comment;
import com.liqiangit.cg.ui.easyui.EasyuiUIcg;
import com.liqiangit.cg.utils.OrderedProperties;
import com.liqiangit.cg.utils.ReflectionUtil;
import com.liqiangit.cg.utils.UIUtils;

//Java-Reflection反射-获取包括父类在内的所有字段
//https://blog.csdn.net/qq_32452623/article/details/54025185
//https://blog.csdn.net/ycb1689/article/details/8001435
//https://blog.csdn.net/dc15822445347/article/details/8515681
//https://blog.csdn.net/qq_36238595/article/details/72801594
//https://blog.csdn.net/hwj528/article/details/53817657
//https://bbs.csdn.net/topics/350173541
//使用Java Swing的JComboBox实现Html中Select的key-value功能
//https://blog.csdn.net/zzh87615/article/details/9849505
//https://blog.csdn.net/yaerfeng/article/details/7255204
//浅析JTable与TableModel、TableCellRenderer、TableCellEditor接口——使用JComboBox显示单元格的值 
//https://www.cnblogs.com/langtianya/archive/2012/09/04/2671173.html
//JTable中 表头中添加 JCheckBox 全选 反向选择 功能
//https://blog.csdn.net/ygzk123/article/details/7778095
/**
 * 
 * @author 李强
 */
public class GenUI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel tableM;
	/**
	 * 类名
	 */
	JLabel classLabel;
	JTextField classTextField;
	/**
	 * 命名空间
	 */
	JLabel entityLabel;
	JTextField entityTextField;
	/**
	 * 表单列数
	 */
	JLabel formColumnsLabel;
	JTextField formColumnsTextField;

	/**
	 * 详情列数
	 */
	JLabel detailColumnsLabel;
	JTextField detailColumnsTextField;

	/**
	 * 查询框列数
	 */
	JLabel searchColumnsLabel;
	JTextField searchColumnsTextField;
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
	/**
	 * 导入数据
	 */
	JButton importButton;
	/**
	 * 导出数据
	 */
	JButton exportButton;
	JFileChooser fc;
	JFileChooser directoryFc;

	Vector<Item> typeItem = new Vector<Item>();
	Vector<Item> validatorRuleItem = new Vector<Item>();
	Vector<Item> searchTypeItem = new Vector<Item>();

	public GenUI() {
		super(new BorderLayout());
		initUI();
		initAction();
		initLayout();
	}

	private void initData() {
		loadItem(typeItem, "typeItem");
//		loadItem(searchTypeItem, "searchTypeItem");
		loadItem(validatorRuleItem, "validatorRuleItem");
		// typeItem.add(new Item("text", "文本框"));
		// typeItem.add(new Item("combobox", "下拉框"));
		//
		 searchTypeItem.add(new Item("0", "普通查询"));
		 searchTypeItem.add(new Item("1", "范围查询"));
		//
		// validatorRuleItem.add(new Item("email", "邮箱"));
		// validatorRuleItem.add(new Item("mobile", "手机号"));
		// validatorRuleItem.add(new Item("number", "数字"));
	}

	private void loadItem(Vector<Item> items, String typeItem) {
		// 此处要保证加载顺序
		Properties pro = new OrderedProperties();
		String filePath = "config/" + typeItem + "_zh_CN.properties";
		try {
			InputStream in = GenUI.class.getClassLoader().getResourceAsStream(filePath);
			pro.load(in);
			in.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(GenUI.this, filePath + "不存在！");
			e.printStackTrace();
		}
		for (Object key : pro.keySet()) {
			items.add(new Item(String.valueOf(key), pro.getProperty(String.valueOf(key))));
		}
		/**
		 * ResourceBundle bundle = ResourceBundle.getBundle("config/" +
		 * typeItem); for (String key : bundle.keySet()) {
		 * System.out.println(key); items.add(new Item(key,
		 * bundle.getString(key))); }
		 */
	}

	private void initUI() {
		UIDefaults defaults = UIManager.getDefaults();
		defaults.remove("SplitPane.border");
		defaults.remove("SplitPaneDivider.border");
		String[] name = { "序号", "字段名称", "字段备注", "国际化", "控件类型", "字典", "表单显示", "列表显示", "查询显示", "详情显示", "查询方式", "校验规则",
				"必填", "长度", "是否主键" };
		// 初始化表格模型，设置表头和数据
		tableM = new CheckTableModle(null, name);
		table = new JTable(tableM);
		table.getTableHeader().setDefaultRenderer(new CheckHeaderCellRenderer(table));
		initData();
		{
			// 控件类型
			JComboBox<Item> JComboBoxItem = new JComboBox<Item>(typeItem);
			TableColumn brandColumn = table.getColumnModel().getColumn(4);
			brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem));
		}
		{
			JComboBox<Item> JComboBoxItem = new JComboBox<Item>(searchTypeItem);
			TableColumn brandColumn = table.getColumnModel().getColumn(10);
			brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem));
		}
		{
			JComboBox<Item> JComboBoxItem = new JComboBox<Item>(validatorRuleItem);
			TableColumn brandColumn = table.getColumnModel().getColumn(11);
			brandColumn.setCellEditor(new DefaultCellEditor(JComboBoxItem));
		}
		{
			// 表单显示
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(6);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		{
			// 列表显示
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(7);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		{
			// 查询显示
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(8);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		{
			// 详情显示
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(9);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		{
			// 必填
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(12);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		{
			// 是否主键
			JCheckBox jCheckBox = new JCheckBox();
			TableColumn brandColumn = table.getColumnModel().getColumn(14);
			brandColumn.setCellEditor(new DefaultCellEditor(jCheckBox));
		}
		table.setPreferredScrollableViewportSize(new Dimension(500, 170));
		table.setFillsViewportHeight(true);
		table.setAutoCreateRowSorter(true);

		classLabel = new JLabel("类名");
		classTextField = new JTextField();
		classTextField.setText(Person.class.getName());

		entityLabel = new JLabel("命名空间");
		entityTextField = new JTextField();
		entityTextField.setText("person");

		formColumnsLabel = new JLabel("表单列数");
		formColumnsTextField = new JTextField();
		formColumnsTextField.setText("2");

		detailColumnsLabel = new JLabel("详情列数");
		detailColumnsTextField = new JTextField();
		detailColumnsTextField.setText("2");

		searchColumnsLabel = new JLabel("查询框列数");
		searchColumnsTextField = new JTextField();
		searchColumnsTextField.setText("4");
		FileSystemView fsv = FileSystemView.getFileSystemView(); // 注意了，这里重要的一句
		fc = new JFileChooser();
		fc.setCurrentDirectory(fsv.getHomeDirectory());// 得到桌面路径
		directoryFc = new JFileChooser();
		directoryFc.setCurrentDirectory(fsv.getHomeDirectory()); // 得到桌面路径
		directoryFc.setFileSelectionMode(JFileChooser.FILES_ONLY);
		directoryFc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		loadFieldsButton = new JButton("加载字段");
		clearListButton = new JButton("清空列表");
		generateButton = new JButton("生成HTML代码");
		importButton = new JButton("导入");
		exportButton = new JButton("导出");
	}

	JPanel leftPanel = new JPanel();

	private void initLayout() {
		JScrollPane scrollPane = new JScrollPane(table);
		GridBagLayout gridBagLayout = new GridBagLayout();
		JPanel rightPanel = new JPanel();
		rightPanel.add(loadFieldsButton);
		rightPanel.add(clearListButton);
		rightPanel.add(generateButton);
		rightPanel.add(importButton);
		rightPanel.add(exportButton);

		leftPanel.setLayout(gridBagLayout);
		addUI(classLabel, 1, 1);
		addUI(classTextField, 2, 1);
		addUI(rightPanel, 3, 1);

		addUI(entityLabel, 1, 2);
		addUI(entityTextField, 2, 2);

		addUI(formColumnsLabel, 1, 3);
		addUI(formColumnsTextField, 2, 3);

		addUI(detailColumnsLabel, 1, 4);
		addUI(detailColumnsTextField, 2, 4);

		addUI(searchColumnsLabel, 1, 5);
		addUI(searchColumnsTextField, 2, 5);

		add(leftPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void addUI(Component component, int gridx, int gridy) {
		GridBagConstraints gridBagConstraints = new GridBagConstraints();
		gridBagConstraints.gridx = gridx;
		gridBagConstraints.gridy = gridy;
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		// gridBagConstraints.gridwidth=gridx;
		if (gridx == 1) {
			// gridBagConstraints.fill = GridBagConstraints.NONE;
		}
		if (gridx == 2) {
			// gridBagConstraints.fill = GridBagConstraints.NONE;
		}
		if (gridx == 3) {
			gridBagConstraints.gridwidth = 4;
		}
		gridBagConstraints.fill = GridBagConstraints.BOTH;
		leftPanel.add(component, gridBagConstraints);
	}

	private void initAction() {
		loadFieldsButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {

					public void run() {
						String clazz = classTextField.getText();
						Class<?> class1 = null;
						try {
							class1 = Class.forName(clazz);
						} catch (ClassNotFoundException e) {
							JOptionPane.showMessageDialog(GenUI.this,
									"java.lang.ClassNotFoundException: " + e.getMessage());
							e.printStackTrace();
						}
						if (class1 == null) {
							return;
						}
						UIParams uiParams = getdata();
						List<UIParam> params = uiParams.getUiParams();
						Map<String, String> map = new HashMap<String, String>();
						for (UIParam uiParam : params) {
							map.put(uiParam.getName(), null);
						}
						List<Field> fields = ReflectionUtil.getFields(class1);
						int i = 0;
						for (Field field : fields) {
							String name = field.getName();
							if (map.containsKey(name)) {
								continue;
							}
							Comment comment = field.getAnnotation(Comment.class);
							String label = "字段备注" + i;
							if (comment != null) {
								label = comment.comment();
							}
							i++;
							Object[] data = { "" + i, name, label, "国际化" + i, "text", "", new Boolean(false),
									new Boolean(false), new Boolean(false), new Boolean(false), "0", "",
									new Boolean(false), "", new Boolean(false) };
							tableM.addRow(render(data));
						}

					}
				}).start();
			}
		});

		clearListButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {

					public void run() {
						clearTable();
					}
				}).start();
			}
		});

		generateButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {

					public void run() {
						UIParams params = getdata();
						try {
							UIFile uiFile = generate(params);
							int returnVal = directoryFc.showOpenDialog(GenUI.this);
							if (returnVal == JFileChooser.APPROVE_OPTION) {
								File file = directoryFc.getSelectedFile();
								try {
									FileUtils.writeStringToFile(new File(file, params.getEntity() + "List.html"),
											uiFile.getListPanel().toString(), "GB2312", false);
									FileUtils.writeStringToFile(new File(file, params.getEntity() + "Form.html"),
											uiFile.getFormPanel().toString(), "GB2312", false);
									FileUtils.writeStringToFile(new File(file, params.getEntity() + "Detail.html"),
											uiFile.getDetailPanel().toString(), "GB2312", false);
									JOptionPane.showMessageDialog(GenUI.this, String.format("生成了三个文件%s,%s,%s", params.getEntity() + "List.html",params.getEntity() + "Form.html",params.getEntity() + "Detail.html"));
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						} catch (Exception e) {
							JOptionPane.showMessageDialog(GenUI.this, "系统异常" + e.getMessage());
							e.printStackTrace();
						}
					}

				}).start();

			}
		});
		importButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {

					public void run() {
						clearTable();
						int returnVal = fc.showOpenDialog(GenUI.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();
							try {
								String str = FileUtils.readFileToString(file, "GB2312");
								UIParams params = JSON.parseObject(str, UIParams.class);
								entityTextField.setText(params.getEntity());
								formColumnsTextField.setText(params.getFormColumns() + "");
								detailColumnsTextField.setText(params.getDetailColumns() + "");
								searchColumnsTextField.setText(params.getSearchColumns() + "");

								List<UIParam> uiParams = params.getUiParams();
								int i = 0;
								for (UIParam uiParam : uiParams) {
									i++;
									Object[] data = { "" + i, uiParam.getName(), uiParam.getLabel(),
											uiParam.getLabelMessage(), uiParam.getType(), uiParam.getUrl(),
											uiParam.getFormShow(), uiParam.getTableShow(), uiParam.getSearchShow(),
											uiParam.getDetailShow(), uiParam.getSearchType(),
											uiParam.getValidatorRule(), uiParam.getRequired(), uiParam.getLength(),
											uiParam.getIsPk() };
									tableM.addRow(render(data));
								}

							} catch (Exception e) {
								JOptionPane.showMessageDialog(GenUI.this, file.getAbsolutePath() + "加载失败！");
								e.printStackTrace();
							}
						}
					}
				}).start();
			}
		});
		exportButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				new Thread(new Runnable() {

					public void run() {
						UIParams params = getdata();
						String str = JSON.toJSON(params).toString();
						int returnVal = fc.showOpenDialog(GenUI.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = fc.getSelectedFile();
							try {
								FileUtils.writeStringToFile(file, str, "GB2312", false);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
				}).start();

			}
		});
	}

	private void clearTable() {
		int row = tableM.getRowCount() - 1;
		for (int i = row; i >= 0; i--) {
			tableM.removeRow(i);
		}
	}

	public static UIFile generate(UIParams params) throws Exception {
		UIPanel uiPanel = UIUtils.convert(params);
		EasyuiUIcg easyuiUIcg = new EasyuiUIcg();
		UIFile uiFile = easyuiUIcg.generate(uiPanel);
		System.out.println(uiFile.getSearchPanel());
		System.out.println(uiFile.getTablePanel());
		return uiFile;
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

	private Object[] render(Object[] data) {
		data[4] = getValue(typeItem, String.valueOf(data[4]));
		data[10] = getValue(searchTypeItem, String.valueOf(data[10]));
		data[11] = getValue(validatorRuleItem, String.valueOf(data[11]));
		return data;
	}

	public String getKey(Vector<Item> vector, String value) {
		for (Item item : vector) {
			if (item.getValue().equals(value)) {
				return item.getKey();
			}
		}
		return value;
	}

	public String getValue(Vector<Item> vector, String key) {
		for (Item item : vector) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return key;
	}

	private UIParams getdata() {
		List<UIParam> uiParams = new ArrayList<UIParam>();
		int rowCount = table.getRowCount();
		int columnCount = table.getColumnCount();
		String[][] values = new String[rowCount][columnCount];
		for (int i = 0; i < rowCount; i++) {
			for (int j = 0; j < columnCount; j++) {
				Object value = table.getValueAt(i, j);
				String var = String.valueOf(value);
				if (j == 4) {
					var = getKey(typeItem, var);
				}
				if (j == 10) {
					var = getKey(searchTypeItem, var);
				}
				if (j == 11) {
					var = getKey(validatorRuleItem, var);
				}
				values[i][j] = var;
			}
		}
		for (int i = 0; i < rowCount; i++) {
			UIParam uiParam = new UIParam();
			uiParam.setName(values[i][1]);
			uiParam.setLabel(values[i][2]);
			uiParam.setLabelMessage(values[i][3]);
			uiParam.setType(values[i][4]);
			uiParam.setUrl(values[i][5]);
			uiParam.setFormShow(Boolean.valueOf(values[i][6]));
			uiParam.setDetailShow(Boolean.valueOf(values[i][7]));
			uiParam.setTableShow(Boolean.valueOf(values[i][8]));
			uiParam.setSearchShow(Boolean.valueOf(values[i][9]));
			uiParam.setSearchType(Integer.parseInt(values[i][10]));
			uiParam.setValidatorRule(values[i][11]);
			uiParam.setRequired(Boolean.valueOf(values[i][12]));
			uiParam.setLength(values[i][13]);
			uiParam.setIsPk(Boolean.valueOf(values[i][14]));
			uiParams.add(uiParam);
		}
		UIParams params = new UIParams();
		params.setEntity(entityTextField.getText());
		params.setFormColumns(Integer.parseInt(formColumnsTextField.getText()));
		params.setDetailColumns(Integer.parseInt(detailColumnsTextField.getText()));
		params.setSearchColumns(Integer.parseInt(searchColumnsTextField.getText()));
		params.setUiParams(uiParams);
		return params;
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
