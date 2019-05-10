package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ������֤��ϸ������ʵ����
 */
public class ArchivesCertificateInfo
{
    /**
     * ���캯��
     */
    public ArchivesCertificateInfo()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤���ϱ��
	* @param certificateRegID ��֤ҵ��ǼǱ��
	* @param certificateTypeID ��֤���ͱ��
	* @param total ֤������
	* @param expressFlag �Ӽ������־
	* @param certificateSN ��֤���к�
	* @param xH ѧ��
	* @param dealedFlag ������ɱ�־
	* @param fileUploadDate ֤���ļ��ϴ�����
	* @param certificateFileName ֤���ļ�����
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
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤���ϱ��
	* @param certificateRegID ��֤ҵ��ǼǱ��
	* @param certificateTypeID ��֤���ͱ��
	* @param total ֤������
	* @param expressFlag �Ӽ������־
	* @param certificateSN ��֤���к�
	* @param xH ѧ��
	* @param dealedFlag ������ɱ�־
	* @param fileUploadDate ֤���ļ��ϴ�����
	* @param certificateFileName ֤���ļ�����
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
	 * ��֤���ϱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����֤���ϱ��
	 * @return ��֤���ϱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����֤���ϱ��
	 * @param iD ��֤���ϱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��֤ҵ��ǼǱ��
	 */
	private int certificateRegID=0;

	/**
	 * ��ȡ����ֵ����֤ҵ��ǼǱ��
	 * @return ��֤ҵ��ǼǱ��
	 */
	public int getCertificateRegID()
	{
		return certificateRegID;
	}

	/**
	 * ��������ֵ����֤ҵ��ǼǱ��
	 * @param certificateRegID ��֤ҵ��ǼǱ��
	 */
	public void setCertificateRegID(int certificateRegID)
	{
		this.certificateRegID = certificateRegID;
	}
	
	/**
	 * ��֤�Ǽ���������
	 */
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ��֤���ͱ��
	 */
	private int certificateTypeID=0;

	/**
	 * ��ȡ����ֵ����֤���ͱ��
	 * @return ��֤���ͱ��
	 */
	public int getCertificateTypeID()
	{
		return certificateTypeID;
	}

	/**
	 * ��������ֵ����֤���ͱ��
	 * @param certificateTypeID ��֤���ͱ��
	 */
	public void setCertificateTypeID(int certificateTypeID)
	{
		this.certificateTypeID = certificateTypeID;
	}

	/**
	 * ֤������
	 */
	private int total=0;

	/**
	 * ��ȡ����ֵ��֤������
	 * @return ֤������
	 */
	public int getTotal()
	{
		return total;
	}

	/**
	 * ��������ֵ��֤������
	 * @param total ֤������
	 */
	public void setTotal(int total)
	{
		this.total = total;
	}

	/**
	 * �Ӽ������־
	 */
	private boolean expressFlag=false;

	/**
	 * ��ȡ����ֵ���Ӽ������־
	 * @return �Ӽ������־
	 */
	public boolean getExpressFlag()
	{
		return expressFlag;
	}

	/**
	 * ��������ֵ���Ӽ������־
	 * @param expressFlag �Ӽ������־
	 */
	public void setExpressFlag(boolean expressFlag)
	{
		this.expressFlag = expressFlag;
	}

	/**
	 * ��֤���к�
	 */
	private String certificateSN=null;

	/**
	 * ��ȡ����ֵ����֤���к�
	 * @return ��֤���к�
	 */
	public String getCertificateSN()
	{
		return certificateSN;
	}

	/**
	 * ��������ֵ����֤���к�
	 * @param certificateSN ��֤���к�
	 */
	public void setCertificateSN(String certificateSN)
	{
		this.certificateSN = certificateSN;
	}


	/**
	 * ��֤��������
	 */
	private String certificateTypeName;
	
	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}
	
	/**
	 * ģ���ļ��� 
	 */
	private String templateFileName;
	
	/**
	 * �ɼ�֤����־
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
	 * ѧ��
	 */
	private String xH=null;

	/**
	 * ��ȡ����ֵ��ѧ��
	 * @return ѧ��
	 */
	public String getXH()
	{
		return xH;
	}

	/**
	 * ��������ֵ��ѧ��
	 * @param xH ѧ��
	 */
	public void setXH(String xH)
	{
		this.xH = xH;
	}

	/**
	 * ������ɱ�־
	 */
	private boolean dealedFlag=false;

	/**
	 * ��ȡ����ֵ��������ɱ�־
	 * @return ������ɱ�־
	 */
	public boolean getDealedFlag()
	{
		return dealedFlag;
	}

	/**
	 * ��������ֵ��������ɱ�־
	 * @param dealedFlag ������ɱ�־
	 */
	public void setDealedFlag(boolean dealedFlag)
	{
		this.dealedFlag = dealedFlag;
	}

	/**
	 * ֤���ļ��ϴ�����
	 */
	private Date fileUploadDate;

	/**
	 * ��ȡ����ֵ��֤���ļ��ϴ�����
	 * @return ֤���ļ��ϴ�����
	 */
	public Date getFileUploadDate()
	{
		return fileUploadDate;
	}

	/**
	 * ��������ֵ��֤���ļ��ϴ�����
	 * @param fileUploadDate ֤���ļ��ϴ�����
	 */
	public void setFileUploadDate(Date fileUploadDate)
	{
		this.fileUploadDate = fileUploadDate;
	}

	/**
	 * ֤���ļ�����
	 */
	private String certificateFileName=null;

	/**
	 * ��ȡ����ֵ��֤���ļ�����
	 * @return ֤���ļ�����
	 */
	public String getCertificateFileName()
	{
		return certificateFileName;
	}

	/**
	 * ��������ֵ��֤���ļ�����
	 * @param certificateFileName ֤���ļ�����
	 */
	public void setCertificateFileName(String certificateFileName)
	{
		this.certificateFileName = certificateFileName;
	}
	

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesCertificateInfo clone()
	{
		ArchivesCertificateInfo item = new ArchivesCertificateInfo(iD,certificateRegID,certificateTypeID,total,expressFlag,certificateSN,xH,dealedFlag,fileUploadDate,certificateFileName);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesCertificateInfo ָ���Ķ���Դ
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