package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 档案出证明细情况表的实体类
 */
public class ArchivesCertificateInfo
{
    /**
     * 构造函数
     */
    public ArchivesCertificateInfo()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 出证材料编号
	* @param certificateRegID 出证业务登记编号
	* @param certificateTypeID 出证类型编号
	* @param total 证明份数
	* @param expressFlag 加急办理标志
	* @param certificateSN 出证序列号
	* @param xH 学号
	* @param dealedFlag 制作完成标志
	* @param fileUploadDate 证明文件上传日期
	* @param certificateFileName 证明文件名称
	*/
	public ArchivesCertificateInfo(int iD,int certificateRegID,int certificateTypeID,int total,boolean expressFlag,String certificateSN,String xH,boolean dealedFlag,Date fileUploadDate,String certificateFileName)
	{
		// Table Name: ArchivesCertificateInfo
		// Columns List,Can Used in SELECT SQL: ID,CertificateRegID,CertificateTypeID,Total,ExpressFlag,CertificateSN,XH,DealedFlag,FileUploadDate,CertificateFileName
		// Columns List,Can Used in INSERT SQL: :ID,:CertificateRegID,:CertificateTypeID,:Total,:ExpressFlag,:CertificateSN,:XH,:DealedFlag,:FileUploadDate,:CertificateFileName
		// Columns List,Can Used in UPDATE SQL: ID=:ID,CertificateRegID=:CertificateRegID,CertificateTypeID=:CertificateTypeID,Total=:Total,ExpressFlag=:ExpressFlag,CertificateSN=:CertificateSN,XH=:XH,DealedFlag=:DealedFlag,FileUploadDate=:FileUploadDate,CertificateFileName=:CertificateFileName

		this.iD = iD;
		this.certificateRegID = certificateRegID;
		this.certificateTypeID = certificateTypeID;
		this.total = total;
		this.expressFlag = expressFlag;
		this.certificateSN = certificateSN;
		this.xH = xH;
		this.dealedFlag = dealedFlag;
		this.fileUploadDate = fileUploadDate;
		this.certificateFileName = certificateFileName;
	}
	/**
	* 带字段参数的构造函数
	* @param iD 出证材料编号
	* @param certificateRegID 出证业务登记编号
	* @param certificateTypeID 出证类型编号
	* @param total 证明份数
	* @param expressFlag 加急办理标志
	* @param certificateSN 出证序列号
	* @param xH 学号
	* @param dealedFlag 制作完成标志
	* @param fileUploadDate 证明文件上传日期
	* @param certificateFileName 证明文件名称
	*/
	public ArchivesCertificateInfo(int iD,int certificateRegID,String name,int certificateTypeID,int total,boolean expressFlag,String certificateSN,String xH,boolean dealedFlag,Date fileUploadDate,String certificateFileName)
	{
		// Table Name: ArchivesCertificateInfo
		// Columns List,Can Used in SELECT SQL: ID,CertificateRegID,CertificateTypeID,Total,ExpressFlag,CertificateSN,XH,DealedFlag,FileUploadDate,CertificateFileName
		// Columns List,Can Used in INSERT SQL: :ID,:CertificateRegID,:CertificateTypeID,:Total,:ExpressFlag,:CertificateSN,:XH,:DealedFlag,:FileUploadDate,:CertificateFileName
		// Columns List,Can Used in UPDATE SQL: ID=:ID,CertificateRegID=:CertificateRegID,CertificateTypeID=:CertificateTypeID,Total=:Total,ExpressFlag=:ExpressFlag,CertificateSN=:CertificateSN,XH=:XH,DealedFlag=:DealedFlag,FileUploadDate=:FileUploadDate,CertificateFileName=:CertificateFileName

		this.iD = iD;
		this.certificateRegID = certificateRegID;
		this.name = name;
		this.certificateTypeID = certificateTypeID;
		this.total = total;
		this.expressFlag = expressFlag;
		this.certificateSN = certificateSN;
		this.xH = xH;
		this.dealedFlag = dealedFlag;
		this.fileUploadDate = fileUploadDate;
		this.certificateFileName = certificateFileName;
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
	 * 出证材料编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：出证材料编号
	 * @return 出证材料编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：出证材料编号
	 * @param iD 出证材料编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 出证业务登记编号
	 */
	private int certificateRegID=0;

	/**
	 * 获取属性值：出证业务登记编号
	 * @return 出证业务登记编号
	 */
	public int getCertificateRegID()
	{
		return certificateRegID;
	}

	/**
	 * 设置属性值：出证业务登记编号
	 * @param certificateRegID 出证业务登记编号
	 */
	public void setCertificateRegID(int certificateRegID)
	{
		this.certificateRegID = certificateRegID;
	}
	
	/**
	 * 出证登记类型名称
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 出证类型编号
	 */
	private int certificateTypeID=0;

	/**
	 * 获取属性值：出证类型编号
	 * @return 出证类型编号
	 */
	public int getCertificateTypeID()
	{
		return certificateTypeID;
	}

	/**
	 * 设置属性值：出证类型编号
	 * @param certificateTypeID 出证类型编号
	 */
	public void setCertificateTypeID(int certificateTypeID)
	{
		this.certificateTypeID = certificateTypeID;
	}

	/**
	 * 证明份数
	 */
	private int total=0;

	/**
	 * 获取属性值：证明份数
	 * @return 证明份数
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * 设置属性值：证明份数
	 * @param total 证明份数
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * 加急办理标志
	 */
	private boolean expressFlag=false;

	/**
	 * 获取属性值：加急办理标志
	 * @return 加急办理标志
	 */
	public boolean getExpressFlag()
	{
		return expressFlag;
	}

	/**
	 * 设置属性值：加急办理标志
	 * @param expressFlag 加急办理标志
	 */
	public void setExpressFlag(boolean expressFlag)
	{
		this.expressFlag = expressFlag;
	}

	/**
	 * 出证序列号
	 */
	private String certificateSN=null;

	/**
	 * 获取属性值：出证序列号
	 * @return 出证序列号
	 */
	public String getCertificateSN()
	{
		return certificateSN;
	}

	/**
	 * 设置属性值：出证序列号
	 * @param certificateSN 出证序列号
	 */
	public void setCertificateSN(String certificateSN)
	{
		this.certificateSN = certificateSN;
	}


	/**
	 * 出证类型名称
	 */
	private String certificateTypeName;
	
	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	
	/**
	 * 模板文件名 
	 */
	private String templateFileName;
	
	/**
	 * 成绩证明标志
	 */
	private boolean gradeFlag;

	public boolean getGradeFlag() {
		return gradeFlag;
	}

	public void setGradeFlag(boolean gradeFlag) {
		this.gradeFlag = gradeFlag;
	}

	public String getTemplateFileName() {
		return templateFileName;
	}

	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	/**
=======
	 * 学号
	 */
	private String xH=null;

	/**
	 * 获取属性值：学号
	 * @return 学号
	 */
	public String getXH()
	{
		return xH;
	}

	/**
	 * 设置属性值：学号
	 * @param xH 学号
	 */
	public void setXH(String xH)
	{
		this.xH = xH;
	}

	/**
	 * 制作完成标志
	 */
	private boolean dealedFlag=false;

	/**
	 * 获取属性值：制作完成标志
	 * @return 制作完成标志
	 */
	public boolean getDealedFlag()
	{
		return dealedFlag;
	}

	/**
	 * 设置属性值：制作完成标志
	 * @param dealedFlag 制作完成标志
	 */
	public void setDealedFlag(boolean dealedFlag)
	{
		this.dealedFlag = dealedFlag;
	}

	/**
	 * 证明文件上传日期
	 */
	private Date fileUploadDate;

	/**
	 * 获取属性值：证明文件上传日期
	 * @return 证明文件上传日期
	 */
	public Date getFileUploadDate()
	{
		return fileUploadDate;
	}

	/**
	 * 设置属性值：证明文件上传日期
	 * @param fileUploadDate 证明文件上传日期
	 */
	public void setFileUploadDate(Date fileUploadDate)
	{
		this.fileUploadDate = fileUploadDate;
	}

	/**
	 * 证明文件名称
	 */
	private String certificateFileName=null;

	/**
	 * 获取属性值：证明文件名称
	 * @return 证明文件名称
	 */
	public String getCertificateFileName()
	{
		return certificateFileName;
	}

	/**
	 * 设置属性值：证明文件名称
	 * @param certificateFileName 证明文件名称
	 */
	public void setCertificateFileName(String certificateFileName)
	{
		this.certificateFileName = certificateFileName;
	}
	

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesCertificateInfo clone()
	{
		ArchivesCertificateInfo item = new ArchivesCertificateInfo(iD,certificateRegID,certificateTypeID,total,expressFlag,certificateSN,xH,dealedFlag,fileUploadDate,certificateFileName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param archivesCertificateInfo 指定的对象源
	*/
	public void cloneFrom(ArchivesCertificateInfo archivesCertificateInfo)
	{
		this.iD = archivesCertificateInfo.getID();
		this.certificateRegID = archivesCertificateInfo.getCertificateRegID();
		this.certificateTypeID = archivesCertificateInfo.getCertificateTypeID();
		this.total = archivesCertificateInfo.getTotal();
		this.expressFlag = archivesCertificateInfo.getExpressFlag();
		this.certificateSN = archivesCertificateInfo.getCertificateSN();
		this.xH = archivesCertificateInfo.getXH();
		this.dealedFlag = archivesCertificateInfo.getDealedFlag();
		this.fileUploadDate = archivesCertificateInfo.getFileUploadDate();
		this.certificateFileName = archivesCertificateInfo.getCertificateFileName();
		this.keyInCol = archivesCertificateInfo.getKeyInCol();
		this.tag = archivesCertificateInfo.getTag();
	}
}