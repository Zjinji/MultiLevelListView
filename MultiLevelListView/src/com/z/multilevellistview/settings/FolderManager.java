package com.z.multilevellistview.settings;

import java.util.ArrayList;

import android.view.View;

import com.z.multilevellistview.bean.MultiLevelBean;
/**
 * Description:Utility for convenient work with Manager.
 * @version 1.0.0.1
 * @author ZJinJi
 */
public class FolderManager{
	
	public void foldOrUnFoldFunction(FolderSettings settingBean){
		if (settingBean.element.isExpanded()) {
			settingBean.element.setExpanded(false);
			ArrayList<MultiLevelBean> elementsToDel = new ArrayList<MultiLevelBean>();
			for (int i = settingBean.tempPosition + 1; i < settingBean.elements.size(); i++) {
				if (settingBean.element.getLevel() >= settingBean.elements.get(i).getLevel())
					break;
				elementsToDel.add(settingBean.elements.get(i));
			}
			settingBean.elements.removeAll(elementsToDel);
			settingBean.treeViewAdapter.notifyDataSetChanged();
		} else {
			settingBean.element.setExpanded(true);
			int i = 1;
			for (MultiLevelBean e : settingBean.elementsData) {
				if (e.getParend_id() == settingBean.element.getId()) {
					e.setExpanded(false);
					settingBean.elements.add(settingBean.tempPosition + i, e);
					i ++;
				}
			}
			settingBean.treeViewAdapter.notifyDataSetChanged();
		}
	}
	
	public void setPadding(PaddingSettings paddingSettingsBean){
		paddingSettingsBean.leftView.setPadding(paddingSettingsBean.element.getIndentation() * (paddingSettingsBean.element.getLevel()), paddingSettingsBean.arrowView.getPaddingTop(), paddingSettingsBean.arrowView.getPaddingRight(), paddingSettingsBean.arrowView.getPaddingBottom());
	}
	
	public void adapterFolderView(ArrowSettings arrowSettings){
		if (arrowSettings.element.isHasChildView() && !arrowSettings.element.isExpanded()) {
			arrowSettings.view.setImageResource(arrowSettings.resId);
			arrowSettings.view.setVisibility(View.VISIBLE);
		} else if (arrowSettings.element.isHasChildView() && arrowSettings.element.isExpanded()) {
			arrowSettings.view.setImageResource(arrowSettings.resIdDown);
			arrowSettings.view.setVisibility(View.VISIBLE);
		} else if (!arrowSettings.element.isHasChildView()) {
			arrowSettings.view.setImageResource(arrowSettings.resId);
			arrowSettings.view.setVisibility(View.INVISIBLE);
		}
	}

}
