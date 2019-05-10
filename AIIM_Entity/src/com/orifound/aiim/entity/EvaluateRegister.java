package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * 考核登记表
 */
public class EvaluateRegister
{
    /**
     * 构造函数
     */
    public EvaluateRegister()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param iD 考核记录编号
	* @param years 考核年度
	* @param userID 被考核人编号
	* @param dutyID 被考核人职务编号
	* @param score 考核分数
	* @param levelID 考核等级
	* @param content 考核评价内容
	* @param registerDate 考核登记日期
	* @param checkerUserID 考核人编号
	* @param checkerDutyID 考核人职务
	*/
	public EvaluateRegister(int iD,String years,int userID,int dutyID,int score,int levelID,String content,Date registerDate,int checkerUserID,int checkerDutyID)
	{
		// Table Name: EvaluateRegister
		// Columns List,Can Used in SELECT SQL: ID,Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID
		// Columns List,Can Used in INSERT SQL: :ID,:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Years=:Years,UserID=:UserID,DutyID=:DutyID,Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID

		this(years, userID, dutyID, score, levelID, content, registerDate, checkerUserID, checkerDutyID);
		this.iD = iD;
	}
	
	/**
	* 带字段参数的构造函数
	* @param years 考核年度
	* @param userID 被考核人编号
	* @param dutyID 被考核人职务编号
	* @param score 考核分数
	* @param levelID 考核等级
	* @param content 考核评价内容
	* @param registerDate 考核登记日期
	* @param checkerUserID 考核人编号
	* @param checkerDutyID 考核人职务
	*/
	public EvaluateRegister(String years,int userID,int dutyID,int score,int levelID,String content,Date registerDate,int checkerUserID,int checkerDutyID)
	{
		// Table Name: EvaluateRegister
		// Columns List,Can Used in SELECT SQL: ID,Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID
		// Columns List,Can Used in INSERT SQL: :ID,:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Years=:Years,UserID=:UserID,DutyID=:DutyID,Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID

		this.years = years;
		this.userID = userID;
		this.dutyID = dutyID;
		this.score = score;
		this.levelID = levelID;
		this.content = content;
		this.registerDate = registerDate;
		this.checkerUserID = checkerUserID;
		this.checkerDutyID = checkerDutyID;
	}
	
	/**
	* 带字段参数的构造函数
	* @param years 考核年度
	* @param userID 被考核人编号
	* @param dutyID 被考核人职务编号
	* @param score 考核分数
	* @param levelID 考核等级
	* @param content 考核评价内容
	* @param registerDate 考核登记日期
	* @param checkerUserID 考核人编号
	* @param checkerDutyID 考核人职务
	*/
	public EvaluateRegister(int iD,int score,int levelID,String content,Date registerDate,int checkerUserID,int checkerDutyID)
	{
		// Table Name: EvaluateRegister
		// Columns List,Can Used in SELECT SQL: ID,Years,UserID,DutyID,Score,LevelID,Content,RegisterDate,CheckerUserID,CheckerDutyID
		// Columns List,Can Used in INSERT SQL: :ID,:Years,:UserID,:DutyID,:Score,:LevelID,:Content,:RegisterDate,:CheckerUserID,:CheckerDutyID
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Years=:Years,UserID=:UserID,DutyID=:DutyID,Score=:Score,LevelID=:LevelID,Content=:Content,RegisterDate=:RegisterDate,CheckerUserID=:CheckerUserID,CheckerDutyID=:CheckerDutyID

		this.iD = iD;
		this.score = score;
		this.levelID = levelID;
		this.content = content;
		this.registerDate = registerDate;
		this.checkerUserID = checkerUserID;
		this.checkerDutyID = checkerDutyID;
	}
	
	/**
	* 带字段参数的构造函数
	* @param years 考核年度
	* @param userID 被考核人编号
	* @param dutyID 被考核人职务编号
	*/
	public EvaluateRegister(String years,int userID,int dutyID)
	{
		this.years = years;
		this.userID = userID;
		this.dutyID = dutyID;
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
	 * 考核记录编号
	 */
	private int iD=0;

	/**
	 * 获取属性值：考核记录编号
	 * @return 考核记录编号
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * 设置属性值：考核记录编号
	 * @param iD 考核记录编号
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * 考核年度
	 */
	private String years=null;

	/**
	 * 获取属性值：考核年度
	 * @return 考核年度
	 */
	public String getYears()
	{
		return years;
	}

	/**
	 * 设置属性值：考核年度
	 * @param years 考核年度
	 */
	public void setYears(String years)
	{
		this.years = years;
	}

	/**
	 * 被考核人编号
	 */
	private int userID=0;

	/**
	 * 获取属性值：被考核人编号
	 * @return 被考核人编号
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * 设置属性值：被考核人编号
	 * @param userID 被考核人编号
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * 被考核人职务编号
	 */
	private int dutyID=0;

	/**
	 * 获取属性值：被考核人职务编号
	 * @return 被考核人职务编号
	 */
	public int getDutyID()
	{
		return dutyID;
	}

	/**
	 * 设置属性值：被考核人职务编号
	 * @param dutyID 被考核人职务编号
	 */
	public void setDutyID(int dutyID)
	{
		this.dutyID = dutyID;
	}

	/**
	 * 考核分数
	 */
	private int score=0;

	/**
	 * 获取属性值：考核分数
	 * @return 考核分数
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * 设置属性值：考核分数
	 * @param score 考核分数
	 */
	public void setScore(int score)
	{
		this.score = score;
	}

	/**
	 * 考核等级
	 */
	private int levelID=0;

	/**
	 * 获取属性值：考核等级
	 * @return 考核等级
	 */
	public int getLevelID()
	{
		return levelID;
	}

	/**
	 * 设置属性值：考核等级
	 * @param levelID 考核等级
	 */
	public void setLevelID(int levelID)
	{
		this.levelID = levelID;
	}

	/**
	 * 考核评价内容
	 */
	private String content=null;

	/**
	 * 获取属性值：考核评价内容
	 * @return 考核评价内容
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * 设置属性值：考核评价内容
	 * @param content 考核评价内容
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * 考核登记日期
	 */
	private Date registerDate;

	/**
	 * 获取属性值：考核登记日期
	 * @return 考核登记日期
	 */
	public Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * 设置属性值：考核登记日期
	 * @param registerDate 考核登记日期
	 */
	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * 考核人编号
	 */
	private int checkerUserID=0;

	/**
	 * 获取属性值：考核人编号
	 * @return 考核人编号
	 */
	public int getCheckerUserID()
	{
		return checkerUserID;
	}

	/**
	 * 设置属性值：考核人编号
	 * @param checkerUserID 考核人编号
	 */
	public void setCheckerUserID(int checkerUserID)
	{
		this.checkerUserID = checkerUserID;
	}

	/**
	 * 考核人职务
	 */
	private int checkerDutyID=0;

	/**
	 * 获取属性值：考核人职务
	 * @return 考核人职务
	 */
	public int getCheckerDutyID()
	{
		return checkerDutyID;
	}

	/**
	 * 设置属性值：考核人职务
	 * @param checkerDutyID 考核人职务
	 */
	public void setCheckerDutyID(int checkerDutyID)
	{
		this.checkerDutyID = checkerDutyID;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public EvaluateRegister clone()
	{
		EvaluateRegister item = new EvaluateRegister(iD,years,userID,dutyID,score,levelID,content,registerDate,checkerUserID,checkerDutyID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param evaluateRegister 指定的对象源
	*/
	public void cloneFrom(EvaluateRegister evaluateRegister)
	{
		this.iD = evaluateRegister.getID();
		this.years = evaluateRegister.getYears();
		this.userID = evaluateRegister.getUserID();
		this.dutyID = evaluateRegister.getDutyID();
		this.score = evaluateRegister.getScore();
		this.levelID = evaluateRegister.getLevelID();
		this.content = evaluateRegister.getContent();
		this.registerDate = evaluateRegister.getRegisterDate();
		this.checkerUserID = evaluateRegister.getCheckerUserID();
		this.checkerDutyID = evaluateRegister.getCheckerDutyID();
		this.keyInCol = evaluateRegister.getKeyInCol();
		this.tag = evaluateRegister.getTag();
	}
    
}



