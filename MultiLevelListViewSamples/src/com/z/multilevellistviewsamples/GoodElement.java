package com.z.multilevellistviewsamples;

import com.z.multilevellistview.bean.MultiLevelBean;

public class GoodElement extends MultiLevelBean{
	private String gc_name;
	private int gc_child;
	private int gc_ceng;
	
	public GoodElement(int id, String content, int parend_id, int level, boolean hasChildView, boolean isExpanded) {
		super(id, content, parend_id, level, hasChildView, isExpanded);
	}

	public String getGc_name() {
		return gc_name;
	}

	public void setGc_name(String gc_name) {
		this.gc_name = gc_name;
	}

	public int getGc_child() {
		return gc_child;
	}

	public void setGc_child(int gc_child) {
		this.gc_child = gc_child;
	}

	public int getGc_ceng() {
		return gc_ceng;
	}

	public void setGc_ceng(int gc_ceng) {
		this.gc_ceng = gc_ceng;
	}
	
}
