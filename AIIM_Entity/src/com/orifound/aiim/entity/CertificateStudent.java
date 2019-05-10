package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ��֤ѧ����Ϣ��
 */
public class CertificateStudent
{
    /**
     * ���캯��
     */
    public CertificateStudent()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param xH ѧ��
	* @param nameCN ������
	* @param nameEN Ӣ����
	* @param majorNameCN רҵ����������
	* @param collegeNameCN ѧԺ��������
	* @param entranceDate ��ѧ����
	* @param graduateDate ��ҵ����
	* @param gradeRecordDate �ɼ��Ǽ�����
	*/
	public CertificateStudent(String xH,String nameCN,String nameEN,String majorNameCN,String collegeNameCN,Date entranceDate,Date graduateDate,Date gradeRecordDate)
	{
		// Table Name: CertificateStudent
		// Columns List,Can Used in SELECT SQL: XH,NameCN,NameEN,MajorNameCN,CollegeNameCN,EntranceDate,GraduateDate,GradeRecordDate
		// Columns List,Can Used in INSERT SQL: :XH,:NameCN,:NameEN,:MajorNameCN,:CollegeNameCN,:EntranceDate,:GraduateDate,:GradeRecordDate
		// Columns List,Can Used in UPDATE SQL: XH=:XH,NameCN=:NameCN,NameEN=:NameEN,MajorNameCN=:MajorNameCN,CollegeNameCN=:CollegeNameCN,EntranceDate=:EntranceDate,GraduateDate=:GraduateDate,GradeRecordDate=:GradeRecordDate

		this.xH = xH;
		this.nameCN = nameCN;
		this.nameEN = nameEN;
		this.majorNameCN = majorNameCN;
		this.collegeNameCN = collegeNameCN;
		this.entranceDate = entranceDate;
		this.graduateDate = graduateDate;
		this.gradeRecordDate = gradeRecordDate;
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
	 * ������
	 */
	private String nameCN=null;

	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public String getNameCN()
	{
		return nameCN;
	}

	/**
	 * ��������ֵ��������
	 * @param nameCN ������
	 */
	public void setNameCN(String nameCN)
	{
		this.nameCN = nameCN;
	}

	/**
	 * Ӣ����
	 */
	private String nameEN=null;

	/**
	 * ��ȡ����ֵ��Ӣ����
	 * @return Ӣ����
	 */
	public String getNameEN()
	{
		return nameEN;
	}

	/**
	 * ��������ֵ��Ӣ����
	 * @param nameEN Ӣ����
	 */
	public void setNameEN(String nameEN)
	{
		this.nameEN = nameEN;
	}

	/**
	 * רҵ����������
	 */
	private String majorNameCN=null;

	/**
	 * ��ȡ����ֵ��רҵ����������
	 * @return רҵ����������
	 */
	public String getMajorNameCN()
	{
		return majorNameCN;
	}

	/**
	 * ��������ֵ��רҵ����������
	 * @param majorNameCN רҵ����������
	 */
	public void setMajorNameCN(String majorNameCN)
	{
		this.majorNameCN = majorNameCN;
	}

	/**
	 * ѧԺ��������
	 */
	private String collegeNameCN=null;

	/**
	 * ��ȡ����ֵ��ѧԺ��������
	 * @return ѧԺ��������
	 */
	public String getCollegeNameCN()
	{
		return collegeNameCN;
	}

	/**
	 * ��������ֵ��ѧԺ��������
	 * @param collegeNameCN ѧԺ��������
	 */
	public void setCollegeNameCN(String collegeNameCN)
	{
		this.collegeNameCN = collegeNameCN;
	}

	/**
	 * ��ѧ����
	 */
	private Date entranceDate;

	/**
	 * ��ȡ����ֵ����ѧ����
	 * @return ��ѧ����
	 */
	public Date getEntranceDate()
	{
		return entranceDate;
	}

	/**
	 * ��������ֵ����ѧ����
	 * @param entranceDate ��ѧ����
	 */
	public void setEntranceDate(Date entranceDate)
	{
		this.entranceDate = entranceDate;
	}

	/**
	 * ��ҵ����
	 */
	private Date graduateDate;

	/**
	 * ��ȡ����ֵ����ҵ����
	 * @return ��ҵ����
	 */
	public Date getGraduateDate()
	{
		return graduateDate;
	}

	/**
	 * ��������ֵ����ҵ����
	 * @param graduateDate ��ҵ����
	 */
	public void setGraduateDate(Date graduateDate)
	{
		this.graduateDate = graduateDate;
	}

	/**
	 * �ɼ��Ǽ�����
	 */
	private Date gradeRecordDate;

	/**
	 * ��ȡ����ֵ���ɼ��Ǽ�����
	 * @return �ɼ��Ǽ�����
	 */
	public Date getGradeRecordDate()
	{
		return gradeRecordDate;
	}

	/**
	 * ��������ֵ���ɼ��Ǽ�����
	 * @param gradeRecordDate �ɼ��Ǽ�����
	 */
	public void setGradeRecordDate(Date gradeRecordDate)
	{
		this.gradeRecordDate = gradeRecordDate;
	}
	
	/**
	 * ��֤��Ϣid
	 */
	private int certificateInfoID;

	public int getCertificateInfoID() {
		return certificateInfoID;
	}

	public void setCertificateInfoID(int certificateInfoID) {
		this.certificateInfoID = certificateInfoID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CertificateStudent clone()
	{
		CertificateStudent item = new CertificateStudent(xH,nameCN,nameEN,majorNameCN,collegeNameCN,entranceDate,graduateDate,gradeRecordDate);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param certificateStudent ָ���Ķ���Դ
	*/
	public void cloneFrom(CertificateStudent certificateStudent)
	{
		this.xH = certificateStudent.getXH();
		this.nameCN = certificateStudent.getNameCN();
		this.nameEN = certificateStudent.getNameEN();
		this.majorNameCN = certificateStudent.getMajorNameCN();
		this.collegeNameCN = certificateStudent.getCollegeNameCN();
		this.entranceDate = certificateStudent.getEntranceDate();
		this.graduateDate = certificateStudent.getGraduateDate();
		this.gradeRecordDate = certificateStudent.getGradeRecordDate();
		this.keyInCol = certificateStudent.getKeyInCol();
		this.tag = certificateStudent.getTag();
	}



    
}



