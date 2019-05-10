package com.orifound.aiim.entity;

    
/**
 * ��֤���ͱ�
 */
public class CertificateType
{
    /**
     * ���캯��
     */
    public CertificateType()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ��֤���ͱ��
	* @param name ��֤��������
	* @param generalPrice ��ͨ����
	* @param expressPrice �Ӽ�����
	* @param gradeFlag �ɼ�֤����־
	* @param templateFileName ģ���ļ���
	* @param remark ��ע
	*/
	public CertificateType(int iD,String name,double generalPrice,double expressPrice,boolean gradeFlag,String templateFileName,String remark)
	{
		// Table Name: DD_CertificateType
		// Columns List,Can Used in SELECT SQL: ID,Name,GeneralPrice,ExpressPrice,GradeFlag,TemplateFileName,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:GeneralPrice,:ExpressPrice,:GradeFlag,:TemplateFileName,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,GeneralPrice=:GeneralPrice,ExpressPrice=:ExpressPrice,GradeFlag=:GradeFlag,TemplateFileName=:TemplateFileName,Remark=:Remark

		this.iD = iD;
		this.name = name;
		this.generalPrice = generalPrice;
		this.expressPrice = expressPrice;
		this.gradeFlag = gradeFlag;
		this.templateFileName = templateFileName;
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
	 * ��֤���ͱ��
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ����֤���ͱ��
	 * @return ��֤���ͱ��
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ����֤���ͱ��
	 * @param iD ��֤���ͱ��
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * ��֤��������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ����֤��������
	 * @return ��֤��������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ����֤��������
	 * @param name ��֤��������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * ��ͨ����
	 */
	private double generalPrice=0;

	/**
	 * ��ȡ����ֵ����ͨ����
	 * @return ��ͨ����
	 */
	public double getGeneralPrice()
	{
		return generalPrice;
	}

	/**
	 * ��������ֵ����ͨ����
	 * @param generalPrice ��ͨ����
	 */
	public void setGeneralPrice(double generalPrice)
	{
		this.generalPrice = generalPrice;
	}

	/**
	 * �Ӽ�����
	 */
	private double expressPrice=0;

	/**
	 * ��ȡ����ֵ���Ӽ�����
	 * @return �Ӽ�����
	 */
	public double getExpressPrice()
	{
		return expressPrice;
	}

	/**
	 * ��������ֵ���Ӽ�����
	 * @param expressPrice �Ӽ�����
	 */
	public void setExpressPrice(double expressPrice)
	{
		this.expressPrice = expressPrice;
	}

	/**
	 * �ɼ�֤����־
	 */
	private boolean gradeFlag=false;

	/**
	 * ��ȡ����ֵ���ɼ�֤����־
	 * @return �ɼ�֤����־
	 */
	public boolean getGradeFlag()
	{
		return gradeFlag;
	}

	/**
	 * ��������ֵ���ɼ�֤����־
	 * @param gradeFlag �ɼ�֤����־
	 */
	public void setGradeFlag(boolean gradeFlag)
	{
		this.gradeFlag = gradeFlag;
	}

	/**
	 * ģ���ļ���
	 */
	private String templateFileName=null;

	/**
	 * ��ȡ����ֵ��ģ���ļ���
	 * @return ģ���ļ���
	 */
	public String getTemplateFileName()
	{
		return templateFileName;
	}

	/**
	 * ��������ֵ��ģ���ļ���
	 * @param templateFileName ģ���ļ���
	 */
	public void setTemplateFileName(String templateFileName)
	{
		this.templateFileName = templateFileName;
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
	public CertificateType clone()
	{
		CertificateType item = new CertificateType(iD,name,generalPrice,expressPrice,gradeFlag,templateFileName,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param certificateType ָ���Ķ���Դ
	*/
	public void cloneFrom(CertificateType certificateType)
	{
		this.iD = certificateType.getID();
		this.name = certificateType.getName();
		this.generalPrice = certificateType.getGeneralPrice();
		this.expressPrice = certificateType.getExpressPrice();
		this.gradeFlag = certificateType.getGradeFlag();
		this.templateFileName = certificateType.getTemplateFileName();
		this.remark = certificateType.getRemark();
		this.keyInCol = certificateType.getKeyInCol();
		this.tag = certificateType.getTag();
	}


    
}



