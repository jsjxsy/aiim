package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 页眉、页脚 配置实体
 */
public class ViewTitle {
	
	public ViewTitle() {
		init();
	}
	
	/**
	 * 是否显示标志
	 */
	private boolean viewFlag;
	
	/**
	 * 显示内容
	 */
	private String viewContent = "";
	
	/**
	 * 显示位置
	 */
	private String viewPosition;
	
	/**
	 * 标题显示位置选项
	 */
	private Map<String, String> viewPlace;
	
	/**
	 * 初始化设置
	 */
	private void init() {
		viewPlace = new LinkedHashMap<String, String>();
		viewPlace.put("left", "左边");
		viewPlace.put("center", "中间");
		viewPlace.put("right", "右边");
		//默认：显示居左
		viewPosition = "left";
		//默认：不显示页眉、页脚
		viewFlag = false;
	}

	public boolean isViewFlag() {
		return viewFlag;
	}

	public void setViewFlag(boolean viewFlag) {
		this.viewFlag = viewFlag;
	}

	public String getViewContent() {
		if(viewFlag) {
			return viewContent;
		} else {
			return "";
		}
	}

	public void setViewContent(String viewContent) {
		this.viewContent = viewContent;
	}

	public String getViewPosition() {
		if(viewFlag) {
			return viewPosition;
		} else {
			return "";
		}
	}

	public void setViewPosition(String viewPosition) {
		this.viewPosition = viewPosition;
	}

	public Map<String, String> getViewPlace() {
		return viewPlace;
	}

	public void setViewPlace(Map<String, String> viewPlace) {
		this.viewPlace = viewPlace;
	}

	@Override
	public String toString() {
		return "ViewTitle [viewContent=" + viewContent + ", viewFlag="
				+ viewFlag + ", viewPosition=" + viewPosition + "]";
	}
}
