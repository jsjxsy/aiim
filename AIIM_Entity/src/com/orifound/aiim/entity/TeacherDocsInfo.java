package com.orifound.aiim.entity;
  
/**
 * ��ְ������������Ϣ��ʵ����
 */
public class TeacherDocsInfo
{
    /**
     * ���캯��
     */
    public TeacherDocsInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param nBXH �ڲ����
	* @param docTypeID ���Ϸ�����
	* @param orderID �������
	* @param docName ��������
	* @param formationDate �����γ�����
	* @param copys ����
	* @param pages ҳ��
	* @param remark ��ע
	*/
	public TeacherDocsInfo(int ID,int NBXH,int docTypeID,int orderID,String docName,String formationDate,int copys,int pages,String remark)
	{
		// Table Name: TeacherDocsInfo
		// Columns List,Can Used in SELECT SQL: ID,NBXH,DocTypeID,OrderID,DocName,FormationDate,Copys,Pages,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:NBXH,:DocTypeID,:OrderID,:DocName,:FormationDate,:Copys,:Pages,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,NBXH=:NBXH,DocTypeID=:DocTypeID,OrderID=:OrderID,DocName=:DocName,FormationDate=:FormationDate,Copys=:Copys,Pages=:Pages,Remark=:Remark

		this.ID = ID;
		this.NBXH = NBXH;
		this.docTypeID = docTypeID;
		this.orderID = orderID;
		this.docName = docName;
		this.formationDate = formationDate;
		this.copys = copys;
		this.pages = pages;
		this.remark = remark;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���
	* @param nBXH �ڲ����
	* @param docTypeID ���Ϸ�����
	* @param orderID �������
	* @param docName ��������
	* @param formationDate �����γ�����
	* @param copys ����
	* @param pages ҳ��
	* @param remark ��ע
	*/
	public TeacherDocsInfo(String gzh,String xm,int docTypeID,int orderID,String docName,String formationDate,int copys,int pages,String remark)
	{
		// Table Name: TeacherDocsInfo
		// Columns List,Can Used in SELECT SQL: ID,NBXH,DocTypeID,OrderID,DocName,FormationDate,Copys,Pages,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:NBXH,:DocTypeID,:OrderID,:DocName,:FormationDate,:Copys,:Pages,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,NBXH=:NBXH,DocTypeID=:DocTypeID,OrderID=:OrderID,DocName=:DocName,FormationDate=:FormationDate,Copys=:Copys,Pages=:Pages,Remark=:Remark
		this.gzh = gzh;
		this.xm = xm;
		this.docTypeID = docTypeID;
		this.orderID= orderID;
		this.docName = docName;
		this.formationDate = formationDate;
		this.copys = copys;
		this.pages = pages;
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
	 * ���
	 */
	private int ID=0;

	/**
	 * ��ȡ����ֵ�����
	 * @return ���
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * ��������ֵ�����
	 * @param iD ���
	 */
	public void setID(int ID)
	{
		this.ID = ID;
	}

	/**
	 * �ڲ����
	 */
	private int NBXH=0;

	/**
	 * ��ȡ����ֵ���ڲ����
	 * @return �ڲ����
	 */
	public int getNBXH()
	{
		return NBXH;
	}

	/**
	 * ��������ֵ���ڲ����
	 * @param nBXH �ڲ����
	 */
	public void setNBXH(int NBXH)
	{
		this.NBXH = NBXH;
	}

	/**
	 * ���Ϸ�����
	 */
	private int docTypeID=0;

	/**
	 * ��ȡ����ֵ�����Ϸ�����
	 * @return ���Ϸ�����
	 */
	public int getDocTypeID()
	{
		return docTypeID;
	}

	/**
	 * ��������ֵ�����Ϸ�����
	 * @param docTypeID ���Ϸ�����
	 */
	public void setDocTypeID(int docTypeID)
	{
		this.docTypeID = docTypeID;
	}

	/**
	 * �������
	 */
	private int orderID=0;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * ��������ֵ���������
	 * @param orderID �������
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * ��������
	 */
	private String docName=null;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getDocName()
	{
		return docName;
	}

	/**
	 * ��������ֵ����������
	 * @param docName ��������
	 */
	public void setDocName(String docName)
	{
		this.docName = docName;
	}

	/**
	 * �����γ�����
	 */
	private String formationDate=null;

	/**
	 * ��ȡ����ֵ�������γ�����
	 * @return �����γ�����
	 */
	public String getFormationDate()
	{
		return formationDate;
	}

	/**
	 * ��������ֵ�������γ�����
	 * @param formationDate �����γ�����
	 */
	public void setFormationDate(String formationDate)
	{
		this.formationDate = formationDate;
	}

	/**
	 * ����
	 */
	private int copys=0;

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public int getCopys()
	{
		return copys;
	}

	/**
	 * ��������ֵ������
	 * @param copys ����
	 */
	public void setCopys(int copys)
	{
		this.copys = copys;
	}

	/**
	 * ҳ��
	 */
	private int pages=0;

	/**
	 * ��ȡ����ֵ��ҳ��
	 * @return ҳ��
	 */
	public int getPages()
	{
		return pages;
	}

	/**
	 * ��������ֵ��ҳ��
	 * @param pages ҳ��
	 */
	public void setPages(int pages)
	{
		this.pages = pages;
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

	private String gzh;
			
    public String getGzh() {
		return gzh;
	}

	public void setGzh(String gzh) {
		this.gzh = gzh;
	}

	private String xm;
    
    public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public TeacherDocsInfo clone()
	{
		TeacherDocsInfo item = new TeacherDocsInfo(ID,NBXH,docTypeID,orderID,docName,formationDate,copys,pages,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param teacherDocsInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(TeacherDocsInfo teacherDocsInfo)
	{
		this.ID = teacherDocsInfo.getID();
		this.NBXH = teacherDocsInfo.getNBXH();
		this.docTypeID = teacherDocsInfo.getDocTypeID();
		this.orderID = teacherDocsInfo.getOrderID();
		this.docName = teacherDocsInfo.getDocName();
		this.formationDate = teacherDocsInfo.getFormationDate();
		this.copys = teacherDocsInfo.getCopys();
		this.pages = teacherDocsInfo.getPages();
		this.remark = teacherDocsInfo.getRemark();
		this.keyInCol = teacherDocsInfo.getKeyInCol();
		this.tag = teacherDocsInfo.getTag();
	}

//	/**
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class TeacherDocsInfoMapper implements RowMapper<TeacherDocsInfo>
//	{
//		
//		@Override
//		public TeacherDocsInfo mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int iD = rs.getInt("ID");
//			int nBXH = rs.getInt("NBXH");
//			int docTypeID = rs.getInt("DocTypeID");
//			int orderID = rs.getInt("OrderID");
//			String docName = rs.getString("DocName");
//			String formationDate = rs.getString("FormationDate");
//			int copys = rs.getInt("Copys");
//			int pages = rs.getInt("Pages");
//			String remark = rs.getString("Remark");
//			
//			return new TeacherDocsInfo(iD,nBXH,docTypeID,orderID,docName,formationDate,copys,pages,remark);
//		}
//	}

    
}



