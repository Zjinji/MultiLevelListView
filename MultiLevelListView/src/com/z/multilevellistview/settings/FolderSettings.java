package com.z.multilevellistview.settings;

import java.util.ArrayList;

import android.widget.BaseAdapter;

import com.z.multilevellistview.bean.MultiLevelBean;
/**
 * Description:When you wanna to fold item and unfold item,you must set the foldersettings.
 * @version 1.0.0.1
 * @author ZJinJi
 */
public class FolderSettings {
	public MultiLevelBean element;
	public ArrayList<MultiLevelBean> elements;
	public ArrayList<MultiLevelBean> elementsData;
	public int tempPosition;
	public BaseAdapter treeViewAdapter;
	public FolderSettings(MultiLevelBean element, ArrayList<MultiLevelBean> elements, ArrayList<MultiLevelBean> elementsData, int tempPosition,	BaseAdapter treeViewAdapter) {
		this.element = element;
		this.elements = elements;
		this.elementsData = elementsData;
		this.tempPosition = tempPosition;
		this.treeViewAdapter = treeViewAdapter;
	}
}
