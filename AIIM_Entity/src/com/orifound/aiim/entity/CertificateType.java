package com.orifound.aiim.entity;

    
/**
 * 出证类型表
 */
public class CertificateType
{
    /**
     * 构造函数
     */
    public CertificateType()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 出证类型编号
	* @param name 出证类型名称
	* @param generalPrice 普通单价
	* @param expressPrice 加急单价
	* @param gradeFlag 成绩证明标志
	* @param templateFileName 模板文件名
	* @param remark 备注
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
	 * 出证类型编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：出证类型编号
	 * @return 出证类型编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：出证类型编号
	 * @param iD 出证类型编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 出证类型名称
	 */
	private String name=null;

	/**
	 * 获取属性值：出证类型名称
	 * @return 出证类型名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：出证类型名称
	 * @param name 出证类型名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 普通单价
	 */
	private double generalPrice=0;

	/**
	 * 获取属性值：普通单价
	 * @return 普通单价
	 */
	public double getGeneralPrice()
	{
		return generalPrice;
	}

	/**
	 * 设置属性值：普通单价
	 * @param generalPrice 普通单价
	 */
	public void setGeneralPrice(double generalPrice)
	{
		this.generalPrice = generalPrice;
	}

	/**
	 * 加急单价
	 */
	private double expressPrice=0;

	/**
	 * 获取属性值：加急单价
	 * @return 加急单价
	 */
	public double getExpressPrice()
	{
		return expressPrice;
	}

	/**
	 * 设置属性值：加急单价
	 * @param expressPrice 加急单价
	 */
	public void setExpressPrice(double expressPrice)
	{
		this.expressPrice = expressPrice;
	}

	/**
	 * 成绩证明标志
	 */
	private boolean gradeFlag=false;

	/**
	 * 获取属性值：成绩证明标志
	 * @return 成绩证明标志
	 */
	public boolean getGradeFlag()
	{
		return gradeFlag;
	}

	/**
	 * 设置属性值：成绩证明标志
	 * @param gradeFlag 成绩证明标志
	 */
	public void setGradeFlag(boolean gradeFlag)
	{
		this.gradeFlag = gradeFlag;
	}

	/**
	 * 模板文件名
	 */
	private String templateFileName=null;

	/**
	 * 获取属性值：模板文件名
	 * @return 模板文件名
	 */
	public String getTemplateFileName()
	{
		return templateFileName;
	}

	/**
	 * 设置属性值：模板文件名
	 * @param templateFileName 模板文件名
	 */
	public void setTemplateFileName(String templateFileName)
	{
		this.templateFileName = templateFileName;
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

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CertificateType clone()
	{
		CertificateType item = new CertificateType(iD,name,generalPrice,expressPrice,gradeFlag,templateFileName,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param certificateType 指定的对象源
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



