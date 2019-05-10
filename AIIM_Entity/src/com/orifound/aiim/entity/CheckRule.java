package com.orifound.aiim.entity;

    
/**
 * 校验规则数据字典表的实体类
 */
public class CheckRule
{
    /**
     * 构造函数
     */
    public CheckRule()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 校验规则编号
	* @param name 校验规则名称
	* @param maskString 掩码字符串
	* @param regExpressionString 正则表达式
	*/
	public CheckRule(int iD,String name,String maskString,String regExpressionString)
	{
		// Table Name: DD_CheckRule
		// Columns List,Can Used in SELECT SQL: ID,Name,MaskString,RegExpressionString
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:MaskString,:RegExpressionString
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,MaskString=:MaskString,RegExpressionString=:RegExpressionString

		this(name, maskString, regExpressionString);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param name 校验规则名称
	* @param maskString 掩码字符串
	* @param regExpressionString 正则表达式
	*/
	public CheckRule(String name,String maskString,String regExpressionString)
	{
		this.name = name;
		this.maskString = maskString;
		this.regExpressionString = regExpressionString;
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
	 * 校验规则编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：校验规则编号
	 * @return 校验规则编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：校验规则编号
	 * @param iD 校验规则编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 校验规则名称
	 */
	private String name=null;

	/**
	 * 获取属性值：校验规则名称
	 * @return 校验规则名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：校验规则名称
	 * @param name 校验规则名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 掩码字符串
	 */
	private String maskString=null;

	/**
	 * 获取属性值：掩码字符串
	 * @return 掩码字符串
	 */
	public String getMaskString()
	{
		return maskString;
	}

	/**
	 * 设置属性值：掩码字符串
	 * @param maskString 掩码字符串
	 */
	public void setMaskString(String maskString)
	{
		this.maskString = maskString;
	}

	/**
	 * 正则表达式
	 */
	private String regExpressionString=null;

	/**
	 * 获取属性值：正则表达式
	 * @return 正则表达式
	 */
	public String getRegExpressionString()
	{
		return regExpressionString;
	}

	/**
	 * 设置属性值：正则表达式
	 * @param regExpressionString 正则表达式
	 */
	public void setRegExpressionString(String regExpressionString)
	{
		this.regExpressionString = regExpressionString;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public CheckRule clone()
	{
		CheckRule item = new CheckRule(iD,name,maskString,regExpressionString);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param checkRule 指定的对象源
	*/
	public void cloneFrom(CheckRule checkRule)
	{
		this.iD = checkRule.getID();
		this.name = checkRule.getName();
		this.maskString = checkRule.getMaskString();
		this.regExpressionString = checkRule.getRegExpressionString();
	}

    
}



