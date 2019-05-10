package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ҳü��ҳ�� ����ʵ��
 */
public class ViewTitle {
	
	public ViewTitle() {
		init();
	}
	
	/**
	 * �Ƿ���ʾ��־
	 */
	private boolean viewFlag;
	
	/**
	 * ��ʾ����
	 */
	private String viewContent = "";
	
	/**
	 * ��ʾλ��
	 */
	private String viewPosition;
	
	/**
	 * ������ʾλ��ѡ��
	 */
	private Map<String, String> viewPlace;
	
	/**
	 * ��ʼ������
	 */
	private void init() {
		viewPlace = new LinkedHashMap<String, String>();
		viewPlace.put("left", "���");
		viewPlace.put("center", "�м�");
		viewPlace.put("right", "�ұ�");
		//Ĭ�ϣ���ʾ����
		viewPosition = "left";
		//Ĭ�ϣ�����ʾҳü��ҳ��
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
