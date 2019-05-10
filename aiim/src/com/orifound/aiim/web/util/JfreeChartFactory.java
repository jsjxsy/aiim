package com.orifound.aiim.web.util;

import java.awt.Color;
import java.awt.Font;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;

/**
 * <p>Title: JfreeChartFactory JfreeChart������  </p>
 * <p>Description: ��������ͼƬͳ������</p>
 * <p>Copyright: Copyright (c) 2002-2012</p>
 * <p>Company: ������ҵ ORIFOUND</p>
 * @author ORIFOUND
 * @version 1.0 2010-6-21 ����07:35:42
 */

public class JfreeChartFactory {

	/**
	 * ͼ��չʾ��������
	 */
	private String[] columnKeys;

	/**
	 * ÿ����ʾ������
	 */
	private String[] rowKeys;

	/**
	 * ��ʾ����ֵ
	 */
	private double[][] data;
	
	/**
	 * ͼƬ�ı�������
	 * ˳��Ϊ�ܱ��⡢X����⡢Y�����
	 * 
	 */
	private String[] titles;

	private static JfreeChartFactory jfreeChartFactory = new JfreeChartFactory();

	private JfreeChartFactory() {
	}

	/**
	 * ��ȡʵ��
	 * <p>
	 * ����ģʽ����ʵ��
	 * </p>
	 * 
	 * @return JfreeChartFactory
	 */
	public static JfreeChartFactory getInstance() {
		return jfreeChartFactory;
	}
	
	/**
	 * �������ɽ������
	 * @return JFreeChart
	 */
	public JFreeChart createChart() {
		setColumnKeys(columnKeys);
		setRowKeys(rowKeys);
		setData(data);
		//������������ ���������Բ�ȫʱ��ʾ��
		String[] safeTitles = {"","",""};
		int i = 0;
		for(String title : titles) {
			safeTitles[i] = title;
			i++;
		}
		JFreeChart chart = createJFreeChart(safeTitles[0], safeTitles[1],safeTitles[2]);
		return chart;
	}
	
	/**
	 * �������ɽ������
	 * @param color ����ͼƬ����ɫ����
	 * @return JFreeChart
	 */
	public JFreeChart createChart(String color) {
		JFreeChart chart = createChart();
		if(color!=null && !"".equals(color)) {
			chart.setBackgroundPaint(parseToColor(color));
		}
		return chart;
	}
	
	/**
	 * ����JFreeChart
	 * 
	 * @param title
	 *            ͼ�����
	 * @param categoryAxisLabel
	 *            X�����
	 * @param valueAxisLabel
	 *            Y�����
	 * @return JFreeChart
	 */
	private JFreeChart createJFreeChart(String title, String xTitle,
			String yTitle) {
		CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columnKeys, data);
		JFreeChart chart = ChartFactory.createBarChart3D(title, xTitle, yTitle,
				dataset, PlotOrientation.VERTICAL, true, false, false);
		
		adornJFreeChart(chart);
		showValue(chart, dataset);
		encodingFilter(chart);
		
		return chart;
	}
	

	/**
	 * װ��ͼ��
	 * <p> �������б����������ʽ </p>
	 * @param chart
	 */
	private void adornJFreeChart(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();// x��
		ValueAxis numberaxis = plot.getRangeAxis(); // y��
		TextTitle t = chart.getTitle();
		t.setFont(new Font("����", Font.BOLD, 40));// ��������

		domainAxis.setLabelFont(new Font("����", Font.BOLD, 20));// x���������
		domainAxis.setTickLabelFont(new Font("����", Font.BOLD, 10));// x������������
		domainAxis.setCategoryLabelPositionOffset(10);	//ͼ��������ǩ�ľ���(10����)
		domainAxis.setCategoryMargin(0.2);	//ͼ��������ǩ�ľ���20%
		
		//����x��б����ʾ
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		plot.setDomainAxis(domainAxis);

		numberaxis.setLabelFont(new Font("����", Font.BOLD, 20));// y���������
		numberaxis.setTickLabelFont(new Font("����", Font.BOLD, 10));// y������������
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis(); 
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 

		chart.getLegend().setItemFont(new Font("����", Font.BOLD, 10));// ͼ������
		
		ValueAxis rangeAxis = plot.getRangeAxis();
		// ������ߵ�һ�� Item ��ͼƬ���˵ľ���
		rangeAxis.setUpperMargin(0.15);
		// ������͵�һ�� Item ��ͼƬ�׶˵ľ���
		rangeAxis.setLowerMargin(0.15);
		rangeAxis.setLowerBound(0);
		
		plot.setRangeAxis(rangeAxis);
		
		//����͸����
		plot.setForegroundAlpha(0.8f);

		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseOutlinePaint(Color.BLACK);
		// ���� Wall ����ɫ
		renderer.setWallPaint(Color.gray);
		// ����ÿ��������ɫ
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(0, 100, 255));
		renderer.setSeriesPaint(2, Color.GREEN);
		
		// ����ÿ����ƽ������֮�����
		renderer.setItemMargin(0.1);
		
//		plot.setRenderer(renderer);

	}
	
	/**
	 * ��ʾ��ֵ(��״ͼ)
	 * <p>
	 * ��ʾÿ��������ֵ�����޸ĸ���ֵ����������
	 * </p>
	 * 
	 * @param chart
	 * @param dataset
	 */
	@SuppressWarnings("deprecation")
	private void showValue(JFreeChart chart, CategoryDataset dataset) {
		CategoryPlot categoryplot = chart.getCategoryPlot();
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer(); // ���renderer
		
//		����bar����С��ȣ��Ա�֤����ʾ��ֵ 
		barrenderer.setMinimumBarLength(0.02);
//		����� 
		barrenderer.setMaximumBarWidth(0.25);
		
		barrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setItemLabelsVisible(true);

	}
	
	/**
	 * �������
	 * <p>���������������</p>
	 * @param chart
	 */
	private void encodingFilter(JFreeChart chart) {
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("����", Font.PLAIN, 20)); // ������������
		chart.getLegend().setItemFont(new Font("����", Font.PLAIN, 12)); // ͼ����������
	}

	public static void main(String[] args) {
		JfreeChartFactory jfreeChartFactory = JfreeChartFactory.getInstance();
		double[][] data = { { 100, 200, 300 }, { 20, 60, 100 } };
		String[] columnKeys = { "����", "����", "����" };
		
		String[] rowKeys = { "���", "�����" };
		jfreeChartFactory.setColumnKeys(columnKeys);
		jfreeChartFactory.setRowKeys(rowKeys);
		jfreeChartFactory.setData(data);
		JFreeChart chart = jfreeChartFactory.createJFreeChart("���������", "��Ա����","����");
		ChartFrame frame = new ChartFrame("������̬���", chart, true);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * �ַ���������ɫֵת����java.awt.Color ʵ��
	 * @param c
	 * @return
	 */
	public Color parseToColor(final String color) {
		//ȡ����ɫ�ַ����е�"#"
		String localColor = color;
		if(StringTool.checkNull(color) && color.indexOf("#")>=0) {
			localColor = color.replace("#", "");
		}
	    Color convertedColor = Color.ORANGE;
	    try {
	        convertedColor = new Color(Integer.parseInt(localColor, 16));
	    } catch(NumberFormatException e) {
	    	try {
				throw new Exception("JfreeChartFactory �ַ���������ɫֵת����java.awt.Color ʵ��ʧ��", e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	    }
	    return convertedColor;
	}


	public String[] getColumnKeys() {
		return columnKeys;
	}

	public void setColumnKeys(String[] columnKeys) {
		this.columnKeys = columnKeys;
	}

	public String[] getRowKeys() {
		return rowKeys;
	}

	public void setRowKeys(String[] rowKeys) {
		this.rowKeys = rowKeys;
	}

	public double[][] getData() {
		return data;
	}

	public void setData(double[][] data) {
		this.data = data;
	}

	public String[] getTitles() {
		return titles;
	}

	public void setTitles(String[] titles) {
		this.titles = titles;
	}
}