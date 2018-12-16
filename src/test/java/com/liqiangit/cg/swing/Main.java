package com.liqiangit.cg.swing;

import java.util.ArrayList;
import java.util.List;

import com.liqiangit.cg.UIUtils;
import com.liqiangit.cg.generator.easyui.EasyuiUIcg;
import com.liqiangit.cg.model.UIFile;
import com.liqiangit.cg.model.UIPanel;
import com.liqiangit.cg.model.UIParam;
import com.liqiangit.cg.model.UIParams;

public class Main {
	public static void main(String[] args) throws Exception {
		List<UIParam> uiParams = new ArrayList<UIParam>();
		UIParam uiParam = new UIParam();
		uiParam.setName("userName");
		uiParam.setType("text");
		uiParam.setLabel("姓名");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);

		uiParam = new UIParam();
		uiParam.setName("gender");
		uiParam.setType("combobox");
		uiParam.setLabel("性别");
		uiParam.setUrl("../gender.json");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);
		
		uiParam = new UIParam();
		uiParam.setName("userName");
		uiParam.setType("text");
		uiParam.setLabel("年龄");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);

		uiParam = new UIParam();
		uiParam.setName("gender");
		uiParam.setType("combobox");
		uiParam.setLabel("爱好");
		uiParam.setUrl("../gender.json");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);
		
		uiParam = new UIParam();
		uiParam.setName("userName");
		uiParam.setType("text");
		uiParam.setLabel("手机号");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);

		uiParam = new UIParam();
		uiParam.setName("gender");
		uiParam.setType("combobox");
		uiParam.setLabel("部门");
		uiParam.setUrl("../gender.json");
		uiParam.setFormShow(true);
		uiParam.setDetailShow(true);
		uiParam.setSearchShow(true);
		uiParam.setTableShow(true);
		uiParams.add(uiParam);

		UIParams params = new UIParams();
		params.setEntity("person");
		params.setFormColumns(2);
		params.setUiParams(uiParams);
		UIPanel uiPanel = UIUtils.convert(params);
		EasyuiUIcg easyuiUIcg = new EasyuiUIcg();
		UIFile uiFile = easyuiUIcg.generate(uiPanel);
//		System.out.println(uiFile.getFormPanel());
		System.out.println(uiFile.getSearchPanel());
		System.out.println(uiFile.getTablePanel());
//		System.out.println();
//		System.out.println();
//		System.out.println();
//		System.out.println("--------------------------------------------------------------------");
//		System.out.println();
//		System.out.println();
//		System.out.println();
	}
}
