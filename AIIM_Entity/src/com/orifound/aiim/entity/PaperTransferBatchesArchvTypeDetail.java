package com.orifound.aiim.entity;

    
/**
 * ֽ�ʵ����ƽ����ε���������ϸ������ʵ����
 */
public class PaperTransferBatchesArchvTypeDetail
{
    /**
     * ���캯��
     */
    public PaperTransferBatchesArchvTypeDetail()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param transferBatNo ֽ�ʵ����ƽ����κ�
	* @param archivesTypeID ����������
	* @param transferTotal �ͳ�����������
	* @param receiveTotal ���յ���������
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
	 * ���
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ֽ�ʵ����ƽ����κ�
	 */
	private String transferBatNo=null;

	/**
	 * ��ȡ����ֵ��ֽ�ʵ����ƽ����κ�
	 * @return ֽ�ʵ����ƽ����κ�
	 */
	public String getTransferBatNo()
	{
		return transferBatNo;
	}

	/**
	 * ��������ֵ��ֽ�ʵ����ƽ����κ�
	 * @param transferBatNo ֽ�ʵ����ƽ����κ�
	 */
	public void setTransferBatNo(String transferBatNo)
	{
		this.transferBatNo = transferBatNo;
	}

	/**
	 * ����������
	 */
	private int archivesTypeID=0;

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		this.archivesTypeID = archivesTypeID;
	}

	/**
	 * �ͳ�����������
	 */
	private int transferTotal=0;

	/**
	 * ��ȡ����ֵ���ͳ�����������
	 * @return �ͳ�����������
	 */
	public int getTransferTotal()
	{
		return transferTotal;
	}

	/**
	 * ��������ֵ���ͳ�����������
	 * @param transferTotal �ͳ�����������
	 */
	public void setTransferTotal(int transferTotal)
	{
		this.transferTotal = transferTotal;
	}

	/**
	 * ���յ���������
	 */
	private int receiveTotal=0;

	/**
	 * ��ȡ����ֵ�����յ���������
	 * @return ���յ���������
	 */
	public int getReceiveTotal()
	{
		return receiveTotal;
	}

	/**
	 * ��������ֵ�����յ���������
	 * @param receiveTotal ���յ���������
	 */
	public void setReceiveTotal(int receiveTotal)
	{
		this.receiveTotal = receiveTotal;
	}

	/**
	 * �Ƿ��Ѿ����ɵ��ű�־
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public PaperTransferBatchesArchvTypeDetail clone()
	{
		PaperTransferBatchesArchvTypeDetail item = new PaperTransferBatchesArchvTypeDetail(iD,transferBatNo,archivesTypeID,transferTotal,receiveTotal, archivesIDMaked);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param paperTransferBatchesArchvTypeDetail ָ���Ķ���Դ
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
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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



