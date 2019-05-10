package com.orifound.aiim.entity;

    
/**
 * 报表统计结果表－工作情况
 */
public class ReportResultWorkProcedure extends StatReport
{
    /**
     * 构造函数
     */
    public ReportResultWorkProcedure()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param userID 用户编号
	* @param realName 真实姓名
	* @param inputCount 著录条目数
	* @param inputSubmitCount 著录提交送审条目数
	* @param paperTrans1Count 实物档案馆外移交数量
	* @param paperCheck1Count 业务指导室接收审核数量
	* @param paperTrans2Count 实物档案馆内移交数量
	* @param paperCheck2Count 档案管理室接收审核数量
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
	* 带字段参数的构造函数
	* @param iD 记录的唯一编号
	* @param reportID 报表编号
	* @param userID 用户编号
	* @param realName 真实姓名
	* @param inputCount 著录条目数
	* @param inputSubmitCount 著录提交送审条目数
	* @param paperTrans1Count 实物档案馆外移交数量
	* @param paperCheck1Count 业务指导室接收审核数量
	* @param paperTrans2Count 实物档案馆内移交数量
	* @param paperCheck2Count 档案管理室接收审核数量
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
	 * 记录的唯一编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：记录的唯一编号
	 * @return 记录的唯一编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：记录的唯一编号
	 * @param iD 记录的唯一编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 报表编号
	 */
	private int reportID=0;

	/**
	 * 获取属性值：报表编号
	 * @return 报表编号
	 */
	public int getReportID()
	{
		return reportID;
	}

	/**
	 * 设置属性值：报表编号
	 * @param reportID 报表编号
	 */
	public void setReportID(int reportID)
	{
		this.reportID = reportID;
	}

	/**
	 * 用户编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：用户编号
	 * @return 用户编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：用户编号
	 * @param userID 用户编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 真实姓名
	 */
	private String realName=null;

	/**
	 * 获取属性值：真实姓名
	 * @return 真实姓名
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * 设置属性值：真实姓名
	 * @param realName 真实姓名
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * 著录条目数
	 */
	private int inputCount=0;

	/**
	 * 获取属性值：著录条目数
	 * @return 著录条目数
	 */
	public int getInputCount()
	{
		return inputCount;
	}

	/**
	 * 设置属性值：著录条目数
	 * @param inputCount 著录条目数
	 */
	public void setInputCount(int inputCount)
	{
		this.inputCount = inputCount;
	}

	/**
	 * 著录提交送审条目数
	 */
	private int inputSubmitCount=0;

	/**
	 * 获取属性值：著录提交送审条目数
	 * @return 著录提交送审条目数
	 */
	public int getInputSubmitCount()
	{
		return inputSubmitCount;
	}

	/**
	 * 设置属性值：著录提交送审条目数
	 * @param inputSubmitCount 著录提交送审条目数
	 */
	public void setInputSubmitCount(int inputSubmitCount)
	{
		this.inputSubmitCount = inputSubmitCount;
	}

	/**
	 * 实物档案馆外移交数量
	 */
	private int paperTrans1Count=0;

	/**
	 * 获取属性值：实物档案馆外移交数量
	 * @return 实物档案馆外移交数量
	 */
	public int getPaperTrans1Count()
	{
		return paperTrans1Count;
	}

	/**
	 * 设置属性值：实物档案馆外移交数量
	 * @param paperTrans1Count 实物档案馆外移交数量
	 */
	public void setPaperTrans1Count(int paperTrans1Count)
	{
		this.paperTrans1Count = paperTrans1Count;
	}

	/**
	 * 业务指导室接收审核数量
	 */
	private int paperCheck1Count=0;

	/**
	 * 获取属性值：业务指导室接收审核数量
	 * @return 业务指导室接收审核数量
	 */
	public int getPaperCheck1Count()
	{
		return paperCheck1Count;
	}

	/**
	 * 设置属性值：业务指导室接收审核数量
	 * @param paperCheck1Count 业务指导室接收审核数量
	 */
	public void setPaperCheck1Count(int paperCheck1Count)
	{
		this.paperCheck1Count = paperCheck1Count;
	}

	/**
	 * 实物档案馆内移交数量
	 */
	private int paperTrans2Count=0;

	/**
	 * 获取属性值：实物档案馆内移交数量
	 * @return 实物档案馆内移交数量
	 */
	public int getPaperTrans2Count()
	{
		return paperTrans2Count;
	}

	/**
	 * 设置属性值：实物档案馆内移交数量
	 * @param paperTrans2Count 实物档案馆内移交数量
	 */
	public void setPaperTrans2Count(int paperTrans2Count)
	{
		this.paperTrans2Count = paperTrans2Count;
	}

	/**
	 * 档案管理室接收审核数量
	 */
	private int paperCheck2Count=0;

	/**
	 * 获取属性值：档案管理室接收审核数量
	 * @return 档案管理室接收审核数量
	 */
	public int getPaperCheck2Count()
	{
		return paperCheck2Count;
	}

	/**
	 * 设置属性值：档案管理室接收审核数量
	 * @param paperCheck2Count 档案管理室接收审核数量
	 */
	public void setPaperCheck2Count(int paperCheck2Count)
	{
		this.paperCheck2Count = paperCheck2Count;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ReportResultWorkProcedure clone()
	{
		ReportResultWorkProcedure item = new ReportResultWorkProcedure(iD,reportID,userID,realName,inputCount,inputSubmitCount,paperTrans1Count,paperCheck1Count,paperTrans2Count,paperCheck2Count);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param reportResultWorkProcedure 指定的对象源
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




