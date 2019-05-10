package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 统计报表定义表实体类
 */
public class StatReport
{
    /**
     * 构造函数
     */
    public StatReport()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 统计报表编号
	* @param reportName 统计报表名称
	* @param reportTitle 报表标题
	* @param statTemplateType 统计模板类型
	* @param parentID 上级编号
	* @param parentFlag 统计模板分类节点标识
	* @param templateFlag 报表统计模板标识
	* @param reportFlag 统计报表标识
	* @param statYear 统计年度
	* @param statBeginDate 统计起始时间
	* @param statEndDate 统计结束时间
	* @param remark 备注
	*/
	public StatReport(int iD,String reportName,String reportTitle,int statTemplateType,int parentID,boolean parentFlag,boolean templateFlag,boolean reportFlag,String statYear,Date statBeginDate,Date statEndDate,String remark)
	{
		// Table Name: DD_StatReport
		// Columns List,Can Used in SELECT SQL: ID,ReportName,ReportTitle,StatTemplateType,ParentID,ParentFlag,TemplateFlag,ReportFlag,StatYear,StatBeginDate,StatEndDate,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:ReportName,:ReportTitle,:StatTemplateType,:ParentID,:ParentFlag,:TemplateFlag,:ReportFlag,:StatYear,:StatBeginDate,:StatEndDate,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportName=:ReportName,ReportTitle=:ReportTitle,StatTemplateType=:StatTemplateType,ParentID=:ParentID,ParentFlag=:ParentFlag,TemplateFlag=:TemplateFlag,ReportFlag=:ReportFlag,StatYear=:StatYear,StatBeginDate=:StatBeginDate,StatEndDate=:StatEndDate,Remark=:Remark

		this.iD = iD;
		this.reportName = reportName;
		this.reportTitle = reportTitle;
		this.statTemplateType = statTemplateType;
		this.parentID = parentID;
		this.parentFlag = parentFlag;
		this.templateFlag = templateFlag;
		this.reportFlag = reportFlag;
		this.statYear = statYear;
		this.statBeginDate = statBeginDate;
		this.statEndDate = statEndDate;
		this.remark = remark;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * 统计报表编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：统计报表编号
	 * @return 统计报表编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：统计报表编号
	 * @param iD 统计报表编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 统计报表名称
	 */
	private String reportName=null;

	/**
	 * 获取属性值：统计报表名称
	 * @return 统计报表名称
	 */
	public String getReportName()
	{
		return reportName;
	}

	/**
	 * 设置属性值：统计报表名称
	 * @param reportName 统计报表名称
	 */
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	/**
	 * 报表标题
	 */
	private String reportTitle=null;

	/**
	 * 获取属性值：报表标题
	 * @return 报表标题
	 */
	public String getReportTitle()
	{
		return reportTitle;
	}

	/**
	 * 设置属性值：报表标题
	 * @param reportTitle 报表标题
	 */
	public void setReportTitle(String reportTitle)
	{
		this.reportTitle = reportTitle;
	}

	/**
	 * 统计模板类型
	 */
	private int statTemplateType=0;

	/**
	 * 获取属性值：统计模板类型
	 * @return 统计模板类型
	 */
	public int getStatTemplateType()
	{
		return statTemplateType;
	}

	/**
	 * 设置属性值：统计模板类型
	 * @param statTemplateType 统计模板类型
	 */
	public void setStatTemplateType(int statTemplateType)
	{
		this.statTemplateType = statTemplateType;
	}

	/**
	 * 上级编号
	 */
	private int parentID=0;

	/**
	 * 获取属性值：上级编号
	 * @return 上级编号
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * 设置属性值：上级编号
	 * @param parentID 上级编号
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * 统计模板分类节点标识
	 */
	private boolean parentFlag=false;

	/**
	 * 获取属性值：统计模板分类节点标识
	 * @return 统计模板分类节点标识
	 */
	public boolean getParentFlag()
	{
		return parentFlag;
	}

	/**
	 * 设置属性值：统计模板分类节点标识
	 * @param parentFlag 统计模板分类节点标识
	 */
	public void setParentFlag(boolean parentFlag)
	{
		this.parentFlag = parentFlag;
	}

	/**
	 * 报表统计模板标识
	 */
	private boolean templateFlag=false;

	/**
	 * 获取属性值：报表统计模板标识
	 * @return 报表统计模板标识
	 */
	public boolean getTemplateFlag()
	{
		return templateFlag;
	}

	/**
	 * 设置属性值：报表统计模板标识
	 * @param templateFlag 报表统计模板标识
	 */
	public void setTemplateFlag(boolean templateFlag)
	{
		this.templateFlag = templateFlag;
	}

	/**
	 * 统计报表标识
	 */
	private boolean reportFlag=false;

	/**
	 * 获取属性值：统计报表标识
	 * @return 统计报表标识
	 */
	public boolean getReportFlag()
	{
		return reportFlag;
	}

	/**
	 * 设置属性值：统计报表标识
	 * @param reportFlag 统计报表标识
	 */
	public void setReportFlag(boolean reportFlag)
	{
		this.reportFlag = reportFlag;
	}

	/**
	 * 统计年度
	 */
	private String statYear=null;

	/**
	 * 获取属性值：统计年度
	 * @return 统计年度
	 */
	public String getStatYear()
	{
		return statYear;
	}

	/**
	 * 设置属性值：统计年度
	 * @param statYear 统计年度
	 */
	public void setStatYear(String statYear)
	{
		this.statYear = statYear;
	}

	/**
	 * 统计起始时间
	 */
	private Date statBeginDate;

	/**
	 * 获取属性值：统计起始时间
	 * @return 统计起始时间
	 */
	public Date getStatBeginDate()
	{
		return statBeginDate;
	}

	/**
	 * 设置属性值：统计起始时间
	 * @param statBeginDate 统计起始时间
	 */
	public void setStatBeginDate(Date statBeginDate)
	{
		this.statBeginDate = statBeginDate;
	}

	/**
	 * 统计结束时间
	 */
	private Date statEndDate;

	/**
	 * 获取属性值：统计结束时间
	 * @return 统计结束时间
	 */
	public Date getStatEndDate()
	{
		return statEndDate;
	}

	/**
	 * 设置属性值：统计结束时间
	 * @param statEndDate 统计结束时间
	 */
	public void setStatEndDate(Date statEndDate)
	{
		this.statEndDate = statEndDate;
	}

	/**
	 * 备注
	 */
	private String remark=null;

	/**
	 * 获取属性值：备注
	 * @return 备注
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * 设置属性值：备注
	 * @param remark 备注
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public StatReport clone()
	{
		StatReport item = new StatReport(iD,reportName,reportTitle,statTemplateType,parentID,parentFlag,templateFlag,reportFlag,statYear,statBeginDate,statEndDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param statReport 指定的对象源
	*/
	public void cloneFrom(StatReport statReport)
	{
		this.iD = statReport.getID();
		this.reportName = statReport.getReportName();
		this.reportTitle = statReport.getReportTitle();
		this.statTemplateType = statReport.getStatTemplateType();
		this.parentID = statReport.getParentID();
		this.parentFlag = statReport.getParentFlag();
		this.templateFlag = statReport.getTemplateFlag();
		this.reportFlag = statReport.getReportFlag();
		this.statYear = statReport.getStatYear();
		this.statBeginDate = statReport.getStatBeginDate();
		this.statEndDate = statReport.getStatEndDate();
		this.remark = statReport.getRemark();
		this.keyInCol = statReport.getKeyInCol();
		this.tag = statReport.getTag();
	}
    
}



