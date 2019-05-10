package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * ��ӡҳ�����ò���
 *
 */
public class PrintPageSet {
	
	public PrintPageSet() {
		init();
	}
	
	/**
	 * ҳ�߾൥λ
	 * 25.4mm
	 */
	private double marginUnit = 25.4;

	/**
	 * ҳ������
	 */
	private String pageNumberId;
	
	/**
	 * ��ӡҳ��
	 */
	private String pageNumberContent = "ҳ��:&p/&P";
	
	/**
	 * ҳ������ѡ��Map����
	 */
	private Map<String, String> pageNumber;
	
	/**
	 * �и�
	 * Ĭ��ֵ��40����
	 */
	private int rowHeight;
	
	/**
	 * ��������
	 * Ĭ��ֵ��50����
	 */
	private int titleHeight;
	
	/**
	 * ����
	 */
	private String title;
	
	/**
	 * ��߾�
	 * Ĭ��ֵ��10����
	 */
	private int margin_left;
	
	/**
	 * �ϱ߾�
	 * Ĭ��ֵ��10����
	 */
	private int margin_top;
	
	/**
	 * �ұ߾�
	 * Ĭ��ֵ��10����
	 */
	private int margin_right;
	
	/**
	 * �±߾�
	 * Ĭ��ֵ��10����
	 */
	private int margin_bottom;
	
	/**
	 * ҳü
	 */
	private ViewTitle header;
	
	/**
	 * ҳ��
	 */
	private ViewTitle footer;
	
	/**
	 * Ŀ¼���
	 */
	private Map<String, String> outlineBorder;
	
	/**
	 * Ŀ¼���ֵ
	 */
	private String outlineBorderId;
	
	/**
	 * ���ò�����ʼ��
	 */
	private void init() {
		header = new ViewTitle();
		footer = new ViewTitle();
		
		pageNumber = new LinkedHashMap<String, String>();
		pageNumber.put("none", "===����ʾҳ��===");
		pageNumber.put("header_left", "ҳü���");
		pageNumber.put("header_center", "ҳü�м�");
		pageNumber.put("header_right", "ҳü�Ҳ�");
		pageNumber.put("footer_left", "ҳ�����");
		pageNumber.put("footer_center", "ҳ���м�");
		pageNumber.put("footer_right", "ҳ���Ҳ�");
		
		//Ĭ�ϣ�����
		pageNumberId = "none";
		
		outlineBorder = new LinkedHashMap<String, String>();
		outlineBorder.put("thick", "�Ӵ�");
		outlineBorder.put("thin", "��ͨ");
		outlineBorder.put("none", "�޿�");
		outlineBorderId = "thin";
		
		rowHeight = 40;
		titleHeight = 50;
		margin_left = 25;
		margin_top = 25;
		margin_right = 25;
		margin_bottom = 25;
	}

	public String getPageNumberId() {
		return pageNumberId;
	}

	public void setPageNumberId(String pageNumberId) {
		this.pageNumberId = pageNumberId;
	}

	public Map<String, String> getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Map<String, String> pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getRowHeight() {
		return rowHeight;
	}

	public void setRowHeight(int rowHeight) {
		this.rowHeight = rowHeight;
	}

	public int getTitleHeight() {
		return titleHeight;
	}

	public void setTitleHeight(int titleHeight) {
		this.titleHeight = titleHeight;
	}

	public int getMargin_left() {
		return margin_left;
	}

	public void setMargin_left(int marginLeft) {
		margin_left = marginLeft;
	}

	public int getMargin_top() {
		return margin_top;
	}

	public void setMargin_top(int marginTop) {
		margin_top = marginTop;
	}

	public int getMargin_right() {
		return margin_right;
	}

	public void setMargin_right(int marginRight) {
		margin_right = marginRight;
	}

	public int getMargin_bottom() {
		return margin_bottom;
	}

	public void setMargin_bottom(int marginBottom) {
		margin_bottom = marginBottom;
	}

	public double getMarginUnit() {
		return marginUnit;
	}

	public void setMarginUnit(double marginUnit) {
		this.marginUnit = marginUnit;
	}

	public ViewTitle getHeader() {
		return header;
	}

	public void setHeader(ViewTitle header) {
		this.header = header;
	}

	public ViewTitle getFooter() {
		return footer;
	}

	public void setFooter(ViewTitle footer) {
		this.footer = footer;
	}

	public Map<String, String> getOutlineBorder() {
		return outlineBorder;
	}

	public void setOutlineBorder(Map<String, String> outlineBorder) {
		this.outlineBorder = outlineBorder;
	}

	public String getOutlineBorderId() {
		return outlineBorderId;
	}

	public void setOutlineBorderId(String outlineBorderId) {
		this.outlineBorderId = outlineBorderId;
	}

	public String getPageNumberContent() {
		return pageNumberContent;
	}

	public void setPageNumberContent(String pageNumberContent) {
		this.pageNumberContent = pageNumberContent;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "PrintPageSet [footer=" + footer + ", margin_bottom="
				+ margin_bottom + ", margin_left=" + margin_left
				+ ", margin_right=" + margin_right + ", margin_top="
				+ margin_top + ", pageNumberId=" + pageNumberId
				+ ", rowHeight=" + rowHeight + ", title=" + title
				+ ", titleHeight=" + titleHeight + "]";
	}

}