package com.orifound.aiim.entity;

/**
 * 绩效考评项目字典表
 */
public class EvaluateItem
{
    /**
     * 构造函数
     */
    public EvaluateItem()
    {
        
    }
    
    /**
	* 带字段参数的构造函数
	* @param name 考评项名称
	* @param score 考评项分值
	* @param remark 备注
	*/
	public EvaluateItem(String name,int score,String remark)
	{
		// Table Name: DD_EvaluateItem
		// Columns List,Can Used in SELECT SQL: ID,Name,Score,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Score,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Score=:Score,Remark=:Remark

		this.name = name;
		this.score = score;
		this.remark = remark;
	}
    
	/**
	* 带字段参数的构造函数
	* @param iD 考评项编号
	* @param name 考评项名称
	* @param score 考评项分值
	* @param remark 备注
	*/
	public EvaluateItem(int iD,String name,int score,String remark)
	{
		// Table Name: DD_EvaluateItem
		// Columns List,Can Used in SELECT SQL: ID,Name,Score,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:Score,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,Score=:Score,Remark=:Remark

		this(name, score, remark);
		this.iD = iD;
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
	 * 考评项编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：考评项编号
	 * @return 考评项编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：考评项编号
	 * @param iD 考评项编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 考评项名称
	 */
	private String name=null;

	/**
	 * 获取属性值：考评项名称
	 * @return 考评项名称
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * 设置属性值：考评项名称
	 * @param name 考评项名称
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * 考评项分值
	 */
	private int score=0;

	/**
	 * 获取属性值：考评项分值
	 * @return 考评项分值
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * 设置属性值：考评项分值
	 * @param score 考评项分值
	 */
	public void setScore(int score)
	{
		this.score = score;
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
	public EvaluateItem clone()
	{
		EvaluateItem item = new EvaluateItem(iD,name,score,remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param evaluateItem 指定的对象源
	*/
	public void cloneFrom(EvaluateItem evaluateItem)
	{
		this.iD = evaluateItem.getID();
		this.name = evaluateItem.getName();
		this.score = evaluateItem.getScore();
		this.remark = evaluateItem.getRemark();
		this.keyInCol = evaluateItem.getKeyInCol();
		this.tag = evaluateItem.getTag();
	}
}