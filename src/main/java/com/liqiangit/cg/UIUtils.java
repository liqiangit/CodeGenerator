package com.liqiangit.cg;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.liqiangit.cg.model.UIPanel;
import com.liqiangit.cg.model.UIParam;
import com.liqiangit.cg.model.UIParams;

public class UIUtils {
	/**
	 * 输入页面编辑列表，返回各个表单列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception 
	 */
	public static UIPanel convert(UIParams ui) throws Exception {
		UIPanel uiPanel = new UIPanel();
		BeanUtils.copyProperties(uiPanel, ui);
		List<UIParam> params=ui.getUiParams();
		List<UIParam> formList = new ArrayList<UIParam>();
		/**
		 * 列表显示
		 */
		List<UIParam> tableList = new ArrayList<UIParam>();
		/**
		 * 查询显示
		 */
		List<UIParam> searchList = new ArrayList<UIParam>();
		/**
		 * 详情显示
		 */
		List<UIParam> detailList = new ArrayList<UIParam>();
		for (UIParam param : params) {
			if (param.getIsPk()) {
				UIParam uiParam=new UIParam();
				BeanUtils.copyProperties(uiParam, param);
				uiParam.setTag("hidden");
				uiPanel.setPk(uiParam);
			}
			if (param.getFormShow()) {
				UIParam uiParam=new UIParam();
				BeanUtils.copyProperties(uiParam, param);
				uiParam.setTag(uiParam.getType());
				formList.add(uiParam);
			}
			if (param.getSearchShow()) {
				Integer searchType=param.getSearchType();
				if(searchType==1){
					UIParam uiParam=new UIParam();
					BeanUtils.copyProperties(uiParam, param);
					uiParam.setTag(uiParam.getType());
					uiParam.setName(uiParam.getName()+"Start");
					searchList.add(uiParam);
					uiParam=new UIParam();
					BeanUtils.copyProperties(uiParam, param);
					uiParam.setTag(uiParam.getType());
					uiParam.setLabel("至");
					uiParam.setLabelMessage("time_end");
					uiParam.setName(uiParam.getName()+"End");
					searchList.add(uiParam);
				}else{
					UIParam uiParam=new UIParam();
					BeanUtils.copyProperties(uiParam, param);
					uiParam.setTag(uiParam.getType());
					searchList.add(uiParam);
				}
			}
			if (param.getTableShow()) {
				UIParam uiParam=new UIParam();
				BeanUtils.copyProperties(uiParam, param);
				uiParam.setTag("td");
				tableList.add(uiParam);
			}
			if (param.getDetailShow()) {
				UIParam uiParam=new UIParam();
				BeanUtils.copyProperties(uiParam, param);
				uiParam.setTag("td");
				detailList.add(uiParam);
			}
		}
		uiPanel.setFormList(formList);
		uiPanel.setTableList(tableList);
		uiPanel.setSearchList(searchList);
		uiPanel.setDetailList(detailList);
		return uiPanel;
	}
}
