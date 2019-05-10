package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ͳ�Ʊ������ʵ����
 */
public class StatReport
{
    /**
     * ���캯��
     */
    public StatReport()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ͳ�Ʊ�����
	* @param reportName ͳ�Ʊ�������
	* @param reportTitle �������
	* @param statTemplateType ͳ��ģ������
	* @param parentID �ϼ����
	* @param parentFlag ͳ��ģ�����ڵ��ʶ
	* @param templateFlag ����ͳ��ģ���ʶ
	* @param reportFlag ͳ�Ʊ����ʶ
	* @param statYear ͳ�����
	* @param statBeginDate ͳ����ʼʱ��
	* @param statEndDate ͳ�ƽ���ʱ��
	* @param remark ��ע
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * ͳ�Ʊ�����
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��ͳ�Ʊ�����
	 * @return ͳ�Ʊ�����
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��ͳ�Ʊ�����
	 * @param iD ͳ�Ʊ�����
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ͳ�Ʊ�������
	 */
	private String reportName=null;

	/**
	 * ��ȡ����ֵ��ͳ�Ʊ�������
	 * @return ͳ�Ʊ�������
	 */
	public String getReportName()
	{
		return reportName;
	}

	/**
	 * ��������ֵ��ͳ�Ʊ�������
	 * @param reportName ͳ�Ʊ�������
	 */
	public void setReportName(String reportName)
	{
		this.reportName = reportName;
	}

	/**
	 * �������
	 */
	private String reportTitle=null;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public String getReportTitle()
	{
		return reportTitle;
	}

	/**
	 * ��������ֵ���������
	 * @param reportTitle �������
	 */
	public void setReportTitle(String reportTitle)
	{
		this.reportTitle = reportTitle;
	}

	/**
	 * ͳ��ģ������
	 */
	private int statTemplateType=0;

	/**
	 * ��ȡ����ֵ��ͳ��ģ������
	 * @return ͳ��ģ������
	 */
	public int getStatTemplateType()
	{
		return statTemplateType;
	}

	/**
	 * ��������ֵ��ͳ��ģ������
	 * @param statTemplateType ͳ��ģ������
	 */
	public void setStatTemplateType(int statTemplateType)
	{
		this.statTemplateType = statTemplateType;
	}

	/**
	 * �ϼ����
	 */
	private int parentID=0;

	/**
	 * ��ȡ����ֵ���ϼ����
	 * @return �ϼ����
	 */
	public int getParentID()
	{
		return parentID;
	}

	/**
	 * ��������ֵ���ϼ����
	 * @param parentID �ϼ����
	 */
	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	/**
	 * ͳ��ģ�����ڵ��ʶ
	 */
	private boolean parentFlag=false;

	/**
	 * ��ȡ����ֵ��ͳ��ģ�����ڵ��ʶ
	 * @return ͳ��ģ�����ڵ��ʶ
	 */
	public boolean getParentFlag()
	{
		return parentFlag;
	}

	/**
	 * ��������ֵ��ͳ��ģ�����ڵ��ʶ
	 * @param parentFlag ͳ��ģ�����ڵ��ʶ
	 */
	public void setParentFlag(boolean parentFlag)
	{
		this.parentFlag = parentFlag;
	}

	/**
	 * ����ͳ��ģ���ʶ
	 */
	private boolean templateFlag=false;

	/**
	 * ��ȡ����ֵ������ͳ��ģ���ʶ
	 * @return ����ͳ��ģ���ʶ
	 */
	public boolean getTemplateFlag()
	{
		return templateFlag;
	}

	/**
	 * ��������ֵ������ͳ��ģ���ʶ
	 * @param templateFlag ����ͳ��ģ���ʶ
	 */
	public void setTemplateFlag(boolean templateFlag)
	{
		this.templateFlag = templateFlag;
	}

	/**
	 * ͳ�Ʊ����ʶ
	 */
	private boolean reportFlag=false;

	/**
	 * ��ȡ����ֵ��ͳ�Ʊ����ʶ
	 * @return ͳ�Ʊ����ʶ
	 */
	public boolean getReportFlag()
	{
		return reportFlag;
	}

	/**
	 * ��������ֵ��ͳ�Ʊ����ʶ
	 * @param reportFlag ͳ�Ʊ����ʶ
	 */
	public void setReportFlag(boolean reportFlag)
	{
		this.reportFlag = reportFlag;
	}

	/**
	 * ͳ�����
	 */
	private String statYear=null;

	/**
	 * ��ȡ����ֵ��ͳ�����
	 * @return ͳ�����
	 */
	public String getStatYear()
	{
		return statYear;
	}

	/**
	 * ��������ֵ��ͳ�����
	 * @param statYear ͳ�����
	 */
	public void setStatYear(String statYear)
	{
		this.statYear = statYear;
	}

	/**
	 * ͳ����ʼʱ��
	 */
	private Date statBeginDate;

	/**
	 * ��ȡ����ֵ��ͳ����ʼʱ��
	 * @return ͳ����ʼʱ��
	 */
	public Date getStatBeginDate()
	{
		return statBeginDate;
	}

	/**
	 * ��������ֵ��ͳ����ʼʱ��
	 * @param statBeginDate ͳ����ʼʱ��
	 */
	public void setStatBeginDate(Date statBeginDate)
	{
		this.statBeginDate = statBeginDate;
	}

	/**
	 * ͳ�ƽ���ʱ��
	 */
	private Date statEndDate;

	/**
	 * ��ȡ����ֵ��ͳ�ƽ���ʱ��
	 * @return ͳ�ƽ���ʱ��
	 */
	public Date getStatEndDate()
	{
		return statEndDate;
	}

	/**
	 * ��������ֵ��ͳ�ƽ���ʱ��
	 * @param statEndDate ͳ�ƽ���ʱ��
	 */
	public void setStatEndDate(Date statEndDate)
	{
		this.statEndDate = statEndDate;
	}

	/**
	 * ��ע
	 */
	private String remark=null;

	/**
	 * ��ȡ����ֵ����ע
	 * @return ��ע
	 */
	public String getRemark()
	{
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 * @param remark ��ע
	 */
	public void setRemark(String remark)
	{
		this.remark = remark;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public StatReport clone()
	{
		StatReport item = new StatReport(iD,reportName,reportTitle,statTemplateType,parentID,parentFlag,templateFlag,reportFlag,statYear,statBeginDate,statEndDate,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param statReport ָ���Ķ���Դ
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



