package com.orifound.aiim.entity;

    
/**
 * 纸质档案移交批次档案分类明细情况表的实体类
 */
public class PaperTransferBatchesArchvTypeDetail
{
    /**
     * 构造函数
     */
    public PaperTransferBatchesArchvTypeDetail()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param transferBatNo 纸质档案移交批次号
	* @param archivesTypeID 档案分类编号
	* @param transferTotal 送出档案总数量
	* @param receiveTotal 接收档案总数量
	*/
	public PaperTransferBatchesArchvTypeDetail(int iD,String transferBatNo,int archivesTypeID,int transferTotal,int receiveTotal,boolean archivesIDMaked)
	{
		// Table Name: PaperTransferBatchesArchvTypeDetails
		// Columns List,Can Used in SELECT SQL: ID,TransferBatNo,ArchivesTypeID,TransferTotal,ReceiveTotal
		// Columns List,Can Used in INSERT SQL: :ID,:TransferBatNo,:ArchivesTypeID,:TransferTotal,:ReceiveTotal
		// Columns List,Can Used in UPDATE SQL: ID=:ID,TransferBatNo=:TransferBatNo,ArchivesTypeID=:ArchivesTypeID,TransferTotal=:TransferTotal,ReceiveTotal=:ReceiveTotal

		this.iD = iD;
		this.transferBatNo = transferBatNo;
		this.archivesTypeID = archivesTypeID;
		this.transferTotal = transferTotal;
		this.receiveTotal = receiveTotal;
		this.archivesIDMaked = archivesIDMaked;
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
	 * 编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 纸质档案移交批次号
	 */
	private String transferBatNo=null;

	/**
	 * 获取属性值：纸质档案移交批次号
	 * @return 纸质档案移交批次号
	 */
	public String getTransferBatNo()
	{
		return transferBatNo;
	}

	/**
	 * 设置属性值：纸质档案移交批次号
	 * @param transferBatNo 纸质档案移交批次号
	 */
	public void setTransferBatNo(String transferBatNo)
	{
		this.transferBatNo = transferBatNo;
	}

	/**
	 * 档案分类编号
	 */
	private int archivesTypeID=0;

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * 送出档案总数量
	 */
	private int transferTotal=0;

	/**
	 * 获取属性值：送出档案总数量
	 * @return 送出档案总数量
	 */
	public int getTransferTotal()
	{
		return transferTotal;
	}

	/**
	 * 设置属性值：送出档案总数量
	 * @param transferTotal 送出档案总数量
	 */
	public void setTransferTotal(int transferTotal)
	{
		this.transferTotal = transferTotal;
	}

	/**
	 * 接收档案总数量
	 */
	private int receiveTotal=0;

	/**
	 * 获取属性值：接收档案总数量
	 * @return 接收档案总数量
	 */
	public int getReceiveTotal()
	{
		return receiveTotal;
	}

	/**
	 * 设置属性值：接收档案总数量
	 * @param receiveTotal 接收档案总数量
	 */
	public void setReceiveTotal(int receiveTotal)
	{
		this.receiveTotal = receiveTotal;
	}

	/**
	 * 是否已经生成档号标志
	 */
	private boolean archivesIDMaked;
	
	public boolean getArchivesIDMaked() {
		return archivesIDMaked;
	}

	public void setArchivesIDMaked(boolean archivesIDMaked) {
		this.archivesIDMaked = archivesIDMaked;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public PaperTransferBatchesArchvTypeDetail clone()
	{
		PaperTransferBatchesArchvTypeDetail item = new PaperTransferBatchesArchvTypeDetail(iD,transferBatNo,archivesTypeID,transferTotal,receiveTotal, archivesIDMaked);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param paperTransferBatchesArchvTypeDetail 指定的对象源
	*/
	public void cloneFrom(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail)
	{
		this.iD = paperTransferBatchesArchvTypeDetail.getID();
		this.transferBatNo = paperTransferBatchesArchvTypeDetail.getTransferBatNo();
		this.archivesTypeID = paperTransferBatchesArchvTypeDetail.getArchivesTypeID();
		this.transferTotal = paperTransferBatchesArchvTypeDetail.getTransferTotal();
		this.receiveTotal = paperTransferBatchesArchvTypeDetail.getReceiveTotal();
		this.keyInCol = paperTransferBatchesArchvTypeDetail.getKeyInCol();
		this.tag = paperTransferBatchesArchvTypeDetail.getTag();
		this.archivesIDMaked = paperTransferBatchesArchvTypeDetail.getArchivesIDMaked();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class PaperTransferBatchesArchvTypeDetailMapper implements RowMapper<PaperTransferBatchesArchvTypeDetail>
//	{
//		
//		@Override
//		public PaperTransferBatchesArchvTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			String transferBatNo = rs.getString("TransferBatNo");
//			int archivesTypeID = rs.getInt("ArchivesTypeID");
//			int transferTotal = rs.getInt("TransferTotal");
//			int receiveTotal = rs.getInt("ReceiveTotal");
//			
//			return new PaperTransferBatchesArchvTypeDetail(iD,transferBatNo,archivesTypeID,transferTotal,receiveTotal);
//		}
//	}

    
}



