package com.orifound.aiim.entity;

    
/**
 * ����ͳ�ƽ�����������
 */
public class ReportResultWorkProcedure extends StatReport
{
    /**
     * ���캯��
     */
    public ReportResultWorkProcedure()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param userID �û����
	* @param realName ��ʵ����
	* @param inputCount ��¼��Ŀ��
	* @param inputSubmitCount ��¼�ύ������Ŀ��
	* @param paperTrans1Count ʵ�ﵵ�������ƽ�����
	* @param paperCheck1Count ҵ��ָ���ҽ����������
	* @param paperTrans2Count ʵ�ﵵ�������ƽ�����
	* @param paperCheck2Count ���������ҽ����������
	*/
	public ReportResultWorkProcedure(int iD,int reportID,int userID,String realName,int inputCount,int inputSubmitCount,int paperTrans1Count,int paperCheck1Count,int paperTrans2Count,int paperCheck2Count)
	{
		// Table Name: ReportResult_WorkProcedure
		// Columns List,Can Used in SELECT SQL: ID,ReportID,UserID,RealName,InputCount,InputSubmitCount,PaperTrans1Count,PaperCheck1Count,PaperTrans2Count,PaperCheck2Count
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:UserID,:RealName,:InputCount,:InputSubmitCount,:PaperTrans1Count,:PaperCheck1Count,:PaperTrans2Count,:PaperCheck2Count
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,UserID=:UserID,RealName=:RealName,InputCount=:InputCount,InputSubmitCount=:InputSubmitCount,PaperTrans1Count=:PaperTrans1Count,PaperCheck1Count=:PaperCheck1Count,PaperTrans2Count=:PaperTrans2Count,PaperCheck2Count=:PaperCheck2Count

		this.iD = iD;
		this.reportID = reportID;
		this.userID = userID;
		this.realName = realName;
		this.inputCount = inputCount;
		this.inputSubmitCount = inputSubmitCount;
		this.paperTrans1Count = paperTrans1Count;
		this.paperCheck1Count = paperCheck1Count;
		this.paperTrans2Count = paperTrans2Count;
		this.paperCheck2Count = paperCheck2Count;
	}

	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��¼��Ψһ���
	* @param reportID ������
	* @param userID �û����
	* @param realName ��ʵ����
	* @param inputCount ��¼��Ŀ��
	* @param inputSubmitCount ��¼�ύ������Ŀ��
	* @param paperTrans1Count ʵ�ﵵ�������ƽ�����
	* @param paperCheck1Count ҵ��ָ���ҽ����������
	* @param paperTrans2Count ʵ�ﵵ�������ƽ�����
	* @param paperCheck2Count ���������ҽ����������
	*/
	public ReportResultWorkProcedure(int iD,int reportID,String reportTitle,int userID,String realName,int inputCount,int inputSubmitCount,int paperTrans1Count,int paperCheck1Count,int paperTrans2Count,int paperCheck2Count)
	{
		// Table Name: ReportResult_WorkProcedure
		// Columns List,Can Used in SELECT SQL: ID,ReportID,UserID,RealName,InputCount,InputSubmitCount,PaperTrans1Count,PaperCheck1Count,PaperTrans2Count,PaperCheck2Count
		// Columns List,Can Used in INSERT SQL: :ID,:ReportID,:UserID,:RealName,:InputCount,:InputSubmitCount,:PaperTrans1Count,:PaperCheck1Count,:PaperTrans2Count,:PaperCheck2Count
		// Columns List,Can Used in UPDATE SQL: ID=:ID,ReportID=:ReportID,UserID=:UserID,RealName=:RealName,InputCount=:InputCount,InputSubmitCount=:InputSubmitCount,PaperTrans1Count=:PaperTrans1Count,PaperCheck1Count=:PaperCheck1Count,PaperTrans2Count=:PaperTrans2Count,PaperCheck2Count=:PaperCheck2Count
		super.setReportTitle(reportTitle);
		this.iD = iD;
		this.reportID = reportID;
		this.userID = userID;
		this.realName = realName;
		this.inputCount = inputCount;
		this.inputSubmitCount = inputSubmitCount;
		this.paperTrans1Count = paperTrans1Count;
		this.paperCheck1Count = paperCheck1Count;
		this.paperTrans2Count = paperTrans2Count;
		this.paperCheck2Count = paperCheck2Count;
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
	 * ��¼��Ψһ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����¼��Ψһ���
	 * @return ��¼��Ψһ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����¼��Ψһ���
	 * @param iD ��¼��Ψһ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ������
	 */
	private int reportID=0;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public int getReportID()
	{
		return reportID;
	}

	/**
	 * ��������ֵ��������
	 * @param reportID ������
	 */
	public void setReportID(int reportID)
	{
		this.reportID = reportID;
	}

	/**
	 * �û����
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ���û����
	 * @return �û����
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ���û����
	 * @param userID �û����
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * ��ʵ����
	 */
	private String realName=null;

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param realName ��ʵ����
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * ��¼��Ŀ��
	 */
	private int inputCount=0;

	/**
	 * ��ȡ����ֵ����¼��Ŀ��
	 * @return ��¼��Ŀ��
	 */
	public int getInputCount()
	{
		return inputCount;
	}

	/**
	 * ��������ֵ����¼��Ŀ��
	 * @param inputCount ��¼��Ŀ��
	 */
	public void setInputCount(int inputCount)
	{
		this.inputCount = inputCount;
	}

	/**
	 * ��¼�ύ������Ŀ��
	 */
	private int inputSubmitCount=0;

	/**
	 * ��ȡ����ֵ����¼�ύ������Ŀ��
	 * @return ��¼�ύ������Ŀ��
	 */
	public int getInputSubmitCount()
	{
		return inputSubmitCount;
	}

	/**
	 * ��������ֵ����¼�ύ������Ŀ��
	 * @param inputSubmitCount ��¼�ύ������Ŀ��
	 */
	public void setInputSubmitCount(int inputSubmitCount)
	{
		this.inputSubmitCount = inputSubmitCount;
	}

	/**
	 * ʵ�ﵵ�������ƽ�����
	 */
	private int paperTrans1Count=0;

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ�������ƽ�����
	 * @return ʵ�ﵵ�������ƽ�����
	 */
	public int getPaperTrans1Count()
	{
		return paperTrans1Count;
	}

	/**
	 * ��������ֵ��ʵ�ﵵ�������ƽ�����
	 * @param paperTrans1Count ʵ�ﵵ�������ƽ�����
	 */
	public void setPaperTrans1Count(int paperTrans1Count)
	{
		this.paperTrans1Count = paperTrans1Count;
	}

	/**
	 * ҵ��ָ���ҽ����������
	 */
	private int paperCheck1Count=0;

	/**
	 * ��ȡ����ֵ��ҵ��ָ���ҽ����������
	 * @return ҵ��ָ���ҽ����������
	 */
	public int getPaperCheck1Count()
	{
		return paperCheck1Count;
	}

	/**
	 * ��������ֵ��ҵ��ָ���ҽ����������
	 * @param paperCheck1Count ҵ��ָ���ҽ����������
	 */
	public void setPaperCheck1Count(int paperCheck1Count)
	{
		this.paperCheck1Count = paperCheck1Count;
	}

	/**
	 * ʵ�ﵵ�������ƽ�����
	 */
	private int paperTrans2Count=0;

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ�������ƽ�����
	 * @return ʵ�ﵵ�������ƽ�����
	 */
	public int getPaperTrans2Count()
	{
		return paperTrans2Count;
	}

	/**
	 * ��������ֵ��ʵ�ﵵ�������ƽ�����
	 * @param paperTrans2Count ʵ�ﵵ�������ƽ�����
	 */
	public void setPaperTrans2Count(int paperTrans2Count)
	{
		this.paperTrans2Count = paperTrans2Count;
	}

	/**
	 * ���������ҽ����������
	 */
	private int paperCheck2Count=0;

	/**
	 * ��ȡ����ֵ�����������ҽ����������
	 * @return ���������ҽ����������
	 */
	public int getPaperCheck2Count()
	{
		return paperCheck2Count;
	}

	/**
	 * ��������ֵ�����������ҽ����������
	 * @param paperCheck2Count ���������ҽ����������
	 */
	public void setPaperCheck2Count(int paperCheck2Count)
	{
		this.paperCheck2Count = paperCheck2Count;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ReportResultWorkProcedure clone()
	{
		ReportResultWorkProcedure item = new ReportResultWorkProcedure(iD,reportID,userID,realName,inputCount,inputSubmitCount,paperTrans1Count,paperCheck1Count,paperTrans2Count,paperCheck2Count);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param reportResultWorkProcedure ָ���Ķ���Դ
	*/
	public void cloneFrom(ReportResultWorkProcedure reportResultWorkProcedure)
	{
		this.iD = reportResultWorkProcedure.getID();
		this.reportID = reportResultWorkProcedure.getReportID();
		this.userID = reportResultWorkProcedure.getUserID();
		this.realName = reportResultWorkProcedure.getRealName();
		this.inputCount = reportResultWorkProcedure.getInputCount();
		this.inputSubmitCount = reportResultWorkProcedure.getInputSubmitCount();
		this.paperTrans1Count = reportResultWorkProcedure.getPaperTrans1Count();
		this.paperCheck1Count = reportResultWorkProcedure.getPaperCheck1Count();
		this.paperTrans2Count = reportResultWorkProcedure.getPaperTrans2Count();
		this.paperCheck2Count = reportResultWorkProcedure.getPaperCheck2Count();
		this.keyInCol = reportResultWorkProcedure.getKeyInCol();
		this.tag = reportResultWorkProcedure.getTag();
	}


    
}




