package com.z.multilevellistview.settings;

import android.view.View;

import com.z.multilevellistview.bean.MultiLevelBean;
/**
 * Description:set item indentation's settings
 * @version 1.0.0.1
 * @author ZJinJi
 */
public class PaddingSettings {
	public MultiLevelBean element; 
	public View leftView;
	public View arrowView;
	public PaddingSettings(MultiLevelBean element, View leftView, View arrowView) {
		this.element = element;
		this.leftView = leftView;
		this.arrowView = arrowView;
	}
}
