package com.z.multilevellistview.bean;

/**
 * @version 1.0.0.1
 * @author ZJinJi
 */
public class MultiLevelBean {
	//element id 元素ID
	public int id;
	//item's content Item内容描述
	public String content;
	//parent element id 父元素ID
	public int parend_id;
	//level 层级
	public int level;
	//has childview 是否有子View
	public boolean hasChildView;
	//item is expanded 当前item是否已展开
	public boolean isExpanded;
	//item的行首缩进基数
	private int indentation;
	//root item 跟Item
	public static final int NO_PARENT = -1;
	//top level item 顶部Item
	public static final int TOP_LEVEL = 0;
	public MultiLevelBean(int id, String content, int parend_id, int level, boolean hasChildView, boolean isExpanded) {
		indentation = 30;
		
		this.id = id;
		this.content = content;
		this.parend_id = parend_id;
		this.level = level;
		this.hasChildView = hasChildView;
		this.isExpanded = isExpanded;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getParend_id() {
		return parend_id;
	}
	public void setParend_id(int parend_id) {
		this.parend_id = parend_id;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public boolean isHasChildView() {
		return hasChildView;
	}
	public void setHasChildView(boolean hasChildView) {
		this.hasChildView = hasChildView;
	}
	public boolean isExpanded() {
		return isExpanded;
	}
	public void setExpanded(boolean isExpanded) {
		this.isExpanded = isExpanded;
	}
	public int getIndentation() {
		return indentation;
	}
	public void setIndentation(int indentation) {
		this.indentation = indentation;
	}
}
