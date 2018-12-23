package com.liqiangit.cg.generator.easyui;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.liqiangit.cg.TextUtil;
import com.liqiangit.cg.generator.UIcg;
import com.liqiangit.cg.model.Panel;
import com.liqiangit.cg.model.UIFile;
import com.liqiangit.cg.model.UIPanel;
import com.liqiangit.cg.model.UIParam;

//转不了
//Map<String, Object> map=new HashMap<String, Object>();
//try {
//	BeanUtils.copyProperties(map, uiParam);
//} catch (Exception e) {
//	e.printStackTrace();
//} 
//Map<String, String> map=BeanUtils.describe(uiParam);
//转不了
//Map<String, Object> map=new HashMap<String, Object>();
//try {
//	BeanUtils.populate(uiParam, map);
//} catch (Exception e) {
//	e.printStackTrace();
//} 
public class EasyuiUIcg implements UIcg {

	public UIFile generate(UIPanel uiPanel) {
		UIFile uiFile = new UIFile();
		{
			List<UIParam> tableList = uiPanel.getTableList();
			Panel table = getTable(uiPanel, tableList,"table");
//			String tableStr = table.toString();
			uiFile.setTablePanel(table);
		}
		{
			List<UIParam> formList = uiPanel.getFormList();
			Integer formColumns = uiPanel.getFormColumns();
			Panel table = getForm(uiPanel, formList, formColumns,"form");
			uiFile.setFormPanel(table);
		}
		{
			List<UIParam> formList = uiPanel.getSearchList();
			Integer formColumns = uiPanel.getSearchColumns();
			Panel table = getForm(uiPanel, formList, formColumns,"search");
			uiFile.setSearchPanel(table);
		}
		{
			List<UIParam> formList = uiPanel.getDetailList();
			Integer formColumns = uiPanel.getDetailColumns();
			Panel table = getForm(uiPanel, formList, formColumns,"detail");
			uiFile.setDetailPanel(table);
		}
		Panel listPanel=new Panel();
		listPanel.addChild(uiFile.getSearchPanel());
		listPanel.addChild(uiFile.getTablePanel());
		uiFile.setListPanel(listPanel);
		return uiFile;
	}

	private Panel getForm(UIPanel uiPanel, List<UIParam> formList, Integer formColumns, String tag) {
		Panel tablePanel=new Panel();
		UIParam param=new UIParam();
		param.setTag(tag+"-start");
		String tagStart=render(param, uiPanel);
		tablePanel.setTagStart(tagStart);
		
		param=new UIParam();
		param.setTag(tag+"-end");
		String tagEnd=render(param, uiPanel);
		tablePanel.setTagEnd(tagEnd);
		Panel table = new Panel();
		tablePanel.addChild(table);
		Tr currentTr = null;
		for (int i = 0; i < formList.size(); i++) {
			if (i % formColumns == 0) {
				currentTr = new Tr();
				table.addChild(currentTr);
			}
			UIParam uiParam = formList.get(i);
			if (StringUtils.isEmpty(uiParam.getLabelMessage())) {
				uiParam.setLabelMessage(String.format("%s_edit_%s", uiPanel.getEntity(), uiParam.getName()));
			}
			if (StringUtils.isEmpty(uiParam.getId())) {
				uiParam.setId(String.format("%s_edit_%s", uiPanel.getEntity(), uiParam.getName()));
			}
			uiParam.setLabelMessage(String.format("#{%s}", uiParam.getLabelMessage()));
			String ui = render(uiParam,uiPanel);
			Panel panel = new Panel();
			panel.setTagStart(ui);
			currentTr.addChild(panel);
			// formStrBuilder.append(ui);
		}
		return tablePanel;
	}

	private Panel getTable(UIPanel uiPanel, List<UIParam> tableList, String tag) {
		Panel tablePanel=new Panel();
		UIParam param=new UIParam();
		param.setTag(tag+"-start");
		String tagStart=render(param, uiPanel);
		tablePanel.setTagStart(tagStart);
		
		param=new UIParam();
		param.setTag(tag+"-end");
		String tagEnd=render(param, uiPanel);
		tablePanel.setTagEnd(tagEnd);
		Panel table = new Panel();
		tablePanel.addChild(table);
		for (int i = 0; i < tableList.size(); i++) {
			UIParam uiParam = tableList.get(i);

			if (StringUtils.isEmpty(uiParam.getLabelMessage())) {
				uiParam.setLabelMessage(String.format("%s_list_%s", uiPanel.getEntity(), uiParam.getName()));
			}
			if (StringUtils.isEmpty(uiParam.getId())) {
				uiParam.setId(String.format("%s_list_%s", uiPanel.getEntity(), uiParam.getName()));
			}
			uiParam.setLabelMessage(String.format("#{%s}", uiParam.getLabelMessage()));
			String ui = render(uiParam,uiPanel);
			Panel panel = new Panel();
			panel.setTagStart(ui);
			table.addChild(panel);
			// tableStrBuilder.append(ui);
		}
		return tablePanel;
	}

	private String render(UIParam uiParam,UIPanel uiPanel) {
		Map<String, Object> map = TextUtil.transBean2Map(uiParam);
		map.put("entity", uiPanel.getEntity());
		map.put("pk", "id");
		if(uiPanel.getPk()!=null){
			map.put("pk", uiPanel.getPk().getName());
		}
		String templePath = String.format("code_template/ui/easyui/%s.vm", uiParam.getTag());
		String ui = TextUtil.velocityMerge(templePath, map);
		return ui;
	}

}
