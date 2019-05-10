package com.orifound.aiim.entity;
  
/**
 * 教职工档案材料信息表实体类
 */
public class TeacherDocsInfo
{
    /**
     * 构造函数
     */
    public TeacherDocsInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 编号
	* @param nBXH 内部序号
	* @param docTypeID 材料分类编号
	* @param orderID 分类序号
	* @param docName 材料名称
	* @param formationDate 档案形成日期
	* @param copys 份数
	* @param pages 页数
	* @param remark 备注
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
	* 带字段参数的构造函数
	* @param iD 编号
	* @param nBXH 内部序号
	* @param docTypeID 材料分类编号
	* @param orderID 分类序号
	* @param docName 材料名称
	* @param formationDate 档案形成日期
	* @param copys 份数
	* @param pages 页数
	* @param remark 备注
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
	private int ID=0;

	/**
	 * 获取属性值：编号
	 * @return 编号
	 */
	public int getID()
	{
		return ID;
	}

	/**
	 * 设置属性值：编号
	 * @param iD 编号
	 */
	public void setID(int ID)
	{
		this.ID = ID;
	}

	/**
	 * 内部序号
	 */
	private int NBXH=0;

	/**
	 * 获取属性值：内部序号
	 * @return 内部序号
	 */
	public int getNBXH()
	{
		return NBXH;
	}

	/**
	 * 设置属性值：内部序号
	 * @param nBXH 内部序号
	 */
	public void setNBXH(int NBXH)
	{
		this.NBXH = NBXH;
	}

	/**
	 * 材料分类编号
	 */
	private int docTypeID=0;

	/**
	 * 获取属性值：材料分类编号
	 * @return 材料分类编号
	 */
	public int getDocTypeID()
	{
		return docTypeID;
	}

	/**
	 * 设置属性值：材料分类编号
	 * @param docTypeID 材料分类编号
	 */
	public void setDocTypeID(int docTypeID)
	{
		this.docTypeID = docTypeID;
	}

	/**
	 * 分类序号
	 */
	private int orderID=0;

	/**
	 * 获取属性值：分类序号
	 * @return 分类序号
	 */
	public int getOrderID()
	{
		return orderID;
	}

	/**
	 * 设置属性值：分类序号
	 * @param orderID 分类序号
	 */
	public void setOrderID(int orderID)
	{
		this.orderID = orderID;
	}

	/**
	 * 材料名称
	 */
	private String docName=null;

	/**
	 * 获取属性值：材料名称
	 * @return 材料名称
	 */
	public String getDocName()
	{
		return docName;
	}

	/**
	 * 设置属性值：材料名称
	 * @param docName 材料名称
	 */
	public void setDocName(String docName)
	{
		this.docName = docName;
	}

	/**
	 * 档案形成日期
	 */
	private String formationDate=null;

	/**
	 * 获取属性值：档案形成日期
	 * @return 档案形成日期
	 */
	public String getFormationDate()
	{
		return formationDate;
	}

	/**
	 * 设置属性值：档案形成日期
	 * @param formationDate 档案形成日期
	 */
	public void setFormationDate(String formationDate)
	{
		this.formationDate = formationDate;
	}

	/**
	 * 份数
	 */
	private int copys=0;

	/**
	 * 获取属性值：份数
	 * @return 份数
	 */
	public int getCopys()
	{
		return copys;
	}

	/**
	 * 设置属性值：份数
	 * @param copys 份数
	 */
	public void setCopys(int copys)
	{
		this.copys = copys;
	}

	/**
	 * 页数
	 */
	private int pages=0;

	/**
	 * 获取属性值：页数
	 * @return 页数
	 */
	public int getPages()
	{
		return pages;
	}

	/**
	 * 设置属性值：页数
	 * @param pages 页数
	 */
	public void setPages(int pages)
	{
		this.pages = pages;
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
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public TeacherDocsInfo clone()
	{
		TeacherDocsInfo item = new TeacherDocsInfo(ID,NBXH,docTypeID,orderID,docName,formationDate,copys,pages,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param teacherDocsInfo 指定的对象源
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
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
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



