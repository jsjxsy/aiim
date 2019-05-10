package com.orifound.aiim.entity;

/**
 * 考评明细情况表
 */
public class EvaluateDetails
{
    /**
     * 构造函数
     */
    public EvaluateDetails()
    {
        
    }
    
	/**
	* 带字段参数的构造函数
	* @param evaluateRegID 考核记录编号
	* @param evaluateItemID 考核项编号
	* @param score 考核分数
	*/
	public EvaluateDetails(int evaluateRegID,int evaluateItemID,int score)
	{
		// Table Name: EvaluateDetails
		// Columns List,Can Used in SELECT SQL: EvaluateRegID,EvaluateItemID,Score
		// Columns List,Can Used in INSERT SQL: :EvaluateRegID,:EvaluateItemID,:Score
		// Columns List,Can Used in UPDATE SQL: EvaluateRegID=:EvaluateRegID,EvaluateItemID=:EvaluateItemID,Score=:Score

		this.evaluateRegID = evaluateRegID;
		this.evaluateItemID = evaluateItemID;
		this.score = score;
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
	private int evaluateRegID=0;

	/**
	 * 获取属性值：考核记录编号
	 * @return 考核记录编号
	 */
	public int getEvaluateRegID()
	{
		return evaluateRegID;
	}

	/**
	 * 设置属性值：考核记录编号
	 * @param evaluateRegID 考核记录编号
	 */
	public void setEvaluateRegID(int evaluateRegID)
	{
		this.evaluateRegID = evaluateRegID;
	}

	/**
	 * 考核项编号
	 */
	private int evaluateItemID=0;

	/**
	 * 获取属性值：考核项编号
	 * @return 考核项编号
	 */
	public int getEvaluateItemID()
	{
		return evaluateItemID;
	}

	/**
	 * 设置属性值：考核项编号
	 * @param evaluateItemID 考核项编号
	 */
	public void setEvaluateItemID(int evaluateItemID)
	{
		this.evaluateItemID = evaluateItemID;
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
	 * 考核项最大分数
	 */
	public int maxScore;

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public EvaluateDetails clone()
	{
		EvaluateDetails item = new EvaluateDetails(evaluateRegID,evaluateItemID,score);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param evaluateDetails 指定的对象源
	*/
	public void cloneFrom(EvaluateDetails evaluateDetails)
	{
		this.evaluateRegID = evaluateDetails.getEvaluateRegID();
		this.evaluateItemID = evaluateDetails.getEvaluateItemID();
		this.score = evaluateDetails.getScore();
		this.keyInCol = evaluateDetails.getKeyInCol();
		this.tag = evaluateDetails.getTag();
	}

//	/**
//	 * 查询结果集到实体类的映射器，该类可用于DAO实现类中
//	 * 
//	 */
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import org.springframework.jdbc.core.RowMapper;
//	private class EvaluateDetailsMapper implements RowMapper<EvaluateDetails>
//	{
//		
//		@Override
//		public EvaluateDetails mapRow(ResultSet rs, int rowNum) throws SQLException
//		{
//			int evaluateRegID = rs.getInt("EvaluateRegID");
//			int evaluateItemID = rs.getInt("EvaluateItemID");
//			int score = rs.getInt("Score");
//			
//			return new EvaluateDetails(evaluateRegID,evaluateItemID,score);
//		}
//	}

    
}



