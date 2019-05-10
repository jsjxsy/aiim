package com.orifound.aiim.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 打印页面设置参数
 *
 */
public class PrintPageSet {
	
	public PrintPageSet() {
		init();
	}
	
	/**
	 * 页边距单位
	 * 25.4mm
	 */
	private double marginUnit = 25.4;

	/**
	 * 页码设置
	 */
	private String pageNumberId;
	
	/**
	 * 打印页码
	 */
	private String pageNumberContent = "页码:&p/&P";
	
	/**
	 * 页码设置选项Map集合
	 */
	private Map<String, String> pageNumber;
	
	/**
	 * 行高
	 * 默认值：40像素
	 */
	private int rowHeight;
	
	/**
	 * 标题栏高
	 * 默认值：50像素
	 */
	private int titleHeight;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 左边距
	 * 默认值：10毫米
	 */
	private int margin_left;
	
	/**
	 * 上边距
	 * 默认值：10毫米
	 */
	private int margin_top;
	
	/**
	 * 右边距
	 * 默认值：10毫米
	 */
	private int margin_right;
	
	/**
	 * 下边距
	 * 默认值：10毫米
	 */
	private int margin_bottom;
	
	/**
	 * 页眉
	 */
	private ViewTitle header;
	
	/**
	 * 页脚
	 */
	private ViewTitle footer;
	
	/**
	 * 目录外框
	 */
	private Map<String, String> outlineBorder;
	
	/**
	 * 目录外框值
	 */
	private String outlineBorderId;
	
	/**
	 * 设置参数初始化
	 */
	private void init() {
		header = new ViewTitle();
		footer = new ViewTitle();
		
		pageNumber = new LinkedHashMap<String, String>();
		pageNumber.put("none", "===不显示页码===");
		pageNumber.put("header_left", "页眉左侧");
		pageNumber.put("header_center", "页眉中间");
		pageNumber.put("header_right", "页眉右侧");
		pageNumber.put("footer_left", "页脚左侧");
		pageNumber.put("footer_center", "页脚中间");
		pageNumber.put("footer_right", "页脚右侧");
		
		//默认：纵向
		pageNumberId = "none";
		
		outlineBorder = new LinkedHashMap<String, String>();
		outlineBorder.put("thick", "加粗");
		outlineBorder.put("thin", "普通");
		outlineBorder.put("none", "无框");
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