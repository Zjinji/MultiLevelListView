package com.z.multilevellistview.settings;

import android.widget.ImageView;

import com.z.multilevellistview.bean.MultiLevelBean;
/**
 * Description:the Settings of the right imageview of arrow
 * @version 1.0.0.1
 * @author ZJinJi
 */
public class ArrowSettings {
	public ImageView view;
	public MultiLevelBean element;
	public int resId;
	public int resIdDown;
	
	public ArrowSettings(ImageView view, MultiLevelBean element, int resId, int resIdDown) {
		this.view = view;
		this.element = element;
		this.resId = resId;
		this.resIdDown = resIdDown;
	}
	
}
