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
 * <p>Title: JfreeChartFactory JfreeChart工厂类  </p>
 * <p>Description: 用于生成图片统计数据</p>
 * <p>Copyright: Copyright (c) 2002-2012</p>
 * <p>Company: 东方基业 ORIFOUND</p>
 * @author ORIFOUND
 * @version 1.0 2010-6-21 下午07:35:42
 */

public class JfreeChartFactory {

	/**
	 * 图表展示的所有项
	 */
	private String[] columnKeys;

	/**
	 * 每项显示的属性
	 */
	private String[] rowKeys;

	/**
	 * 显示数据值
	 */
	private double[][] data;
	
	/**
	 * 图片的标题数字
	 * 顺序为总标题、X轴标题、Y轴标题
	 * 
	 */
	private String[] titles;

	private static JfreeChartFactory jfreeChartFactory = new JfreeChartFactory();

	private JfreeChartFactory() {
	}

	/**
	 * 获取实例
	 * <p>
	 * 单例模式生成实例
	 * </p>
	 * 
	 * @return JfreeChartFactory
	 */
	public static JfreeChartFactory getInstance() {
		return jfreeChartFactory;
	}
	
	/**
	 * 最终生成结果创建
	 * @return JFreeChart
	 */
	public JFreeChart createChart() {
		setColumnKeys(columnKeys);
		setRowKeys(rowKeys);
		setData(data);
		//构建标题数组 当标题属性不全时显示空
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
	 * 最终生成结果创建
	 * @param color 生成图片背景色设置
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
	 * 生成JFreeChart
	 * 
	 * @param title
	 *            图表标题
	 * @param categoryAxisLabel
	 *            X轴标题
	 * @param valueAxisLabel
	 *            Y轴标题
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
	 * 装饰图表
	 * <p> 设置所有标题的字体样式 </p>
	 * @param chart
	 */
	private void adornJFreeChart(JFreeChart chart) {
		CategoryPlot plot = chart.getCategoryPlot();
		CategoryAxis domainAxis = plot.getDomainAxis();// x轴
		ValueAxis numberaxis = plot.getRangeAxis(); // y轴
		TextTitle t = chart.getTitle();
		t.setFont(new Font("宋体", Font.BOLD, 40));// 标题文字

		domainAxis.setLabelFont(new Font("宋体", Font.BOLD, 20));// x轴标题文字
		domainAxis.setTickLabelFont(new Font("黑体", Font.BOLD, 10));// x轴坐标上文字
		domainAxis.setCategoryLabelPositionOffset(10);	//图表横轴与标签的距离(10像素)
		domainAxis.setCategoryMargin(0.2);	//图表横轴与标签的距离20%
		
		//设置x轴斜体显示
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
		
		plot.setDomainAxis(domainAxis);

		numberaxis.setLabelFont(new Font("宋体", Font.BOLD, 20));// y轴标题文字
		numberaxis.setTickLabelFont(new Font("黑体", Font.BOLD, 10));// y轴坐标上文字
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis(); 
		numberAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits()); 

		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 10));// 图例文字
		
		ValueAxis rangeAxis = plot.getRangeAxis();
		// 设置最高的一个 Item 与图片顶端的距离
		rangeAxis.setUpperMargin(0.15);
		// 设置最低的一个 Item 与图片底端的距离
		rangeAxis.setLowerMargin(0.15);
		rangeAxis.setLowerBound(0);
		
		plot.setRangeAxis(rangeAxis);
		
		//设置透明度
		plot.setForegroundAlpha(0.8f);

		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseOutlinePaint(Color.BLACK);
		// 设置 Wall 的颜色
		renderer.setWallPaint(Color.gray);
		// 设置每个柱的颜色
		renderer.setSeriesPaint(0, new Color(0, 0, 255));
		renderer.setSeriesPaint(1, new Color(0, 100, 255));
		renderer.setSeriesPaint(2, Color.GREEN);
		
		// 设置每项多个平行柱的之间距离
		renderer.setItemMargin(0.1);
		
//		plot.setRenderer(renderer);

	}
	
	/**
	 * 显示数值(柱状图)
	 * <p>
	 * 显示每个柱的数值，并修改该数值的字体属性
	 * </p>
	 * 
	 * @param chart
	 * @param dataset
	 */
	@SuppressWarnings("deprecation")
	private void showValue(JFreeChart chart, CategoryDataset dataset) {
		CategoryPlot categoryplot = chart.getCategoryPlot();
		BarRenderer barrenderer = (BarRenderer) categoryplot.getRenderer(); // 获得renderer
		
//		设置bar的最小宽度，以保证能显示数值 
		barrenderer.setMinimumBarLength(0.02);
//		最大宽度 
		barrenderer.setMaximumBarWidth(0.25);
		
		barrenderer.setItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barrenderer.setItemLabelsVisible(true);

	}
	
	/**
	 * 编码过滤
	 * <p>解决中文乱码问题</p>
	 * @param chart
	 */
	private void encodingFilter(JFreeChart chart) {
		TextTitle textTitle = chart.getTitle();
		textTitle.setFont(new Font("宋体", Font.PLAIN, 20)); // 标题文字乱码
		chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 12)); // 图例文字乱码
	}

	public static void main(String[] args) {
		JfreeChartFactory jfreeChartFactory = JfreeChartFactory.getInstance();
		double[][] data = { { 100, 200, 300 }, { 20, 60, 100 } };
		String[] columnKeys = { "张三", "李四", "王五" };
		
		String[] rowKeys = { "审核", "待审核" };
		jfreeChartFactory.setColumnKeys(columnKeys);
		jfreeChartFactory.setRowKeys(rowKeys);
		jfreeChartFactory.setData(data);
		JFreeChart chart = jfreeChartFactory.createJFreeChart("审核情况监测", "人员姓名","数量");
		ChartFrame frame = new ChartFrame("工作动态监测", chart, true);
		frame.pack();
		frame.setVisible(true);
	}
	
	/**
	 * 字符串表达的颜色值转换成java.awt.Color 实例
	 * @param c
	 * @return
	 */
	public Color parseToColor(final String color) {
		//取出颜色字符串中的"#"
		String localColor = color;
		if(StringTool.checkNull(color) && color.indexOf("#")>=0) {
			localColor = color.replace("#", "");
		}
	    Color convertedColor = Color.ORANGE;
	    try {
	        convertedColor = new Color(Integer.parseInt(localColor, 16));
	    } catch(NumberFormatException e) {
	    	try {
				throw new Exception("JfreeChartFactory 字符串表达的颜色值转换成java.awt.Color 实例失败", e);
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