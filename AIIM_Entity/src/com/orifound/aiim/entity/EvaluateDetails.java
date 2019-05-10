package com.orifound.aiim.entity;

/**
 * ������ϸ�����
 */
public class EvaluateDetails
{
    /**
     * ���캯��
     */
    public EvaluateDetails()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param evaluateRegID ���˼�¼���
	* @param evaluateItemID ��������
	* @param score ���˷���
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
	 * ���˼�¼���
	 */
	private int evaluateRegID=0;

	/**
	 * ��ȡ����ֵ�����˼�¼���
	 * @return ���˼�¼���
	 */
	public int getEvaluateRegID()
	{
		return evaluateRegID;
	}

	/**
	 * ��������ֵ�����˼�¼���
	 * @param evaluateRegID ���˼�¼���
	 */
	public void setEvaluateRegID(int evaluateRegID)
	{
		this.evaluateRegID = evaluateRegID;
	}

	/**
	 * ��������
	 */
	private int evaluateItemID=0;

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public int getEvaluateItemID()
	{
		return evaluateItemID;
	}

	/**
	 * ��������ֵ����������
	 * @param evaluateItemID ��������
	 */
	public void setEvaluateItemID(int evaluateItemID)
	{
		this.evaluateItemID = evaluateItemID;
	}

	/**
	 * ���˷���
	 */
	private int score=0;

	/**
	 * ��ȡ����ֵ�����˷���
	 * @return ���˷���
	 */
	public int getScore()
	{
		return score;
	}

	/**
	 * ��������ֵ�����˷���
	 * @param score ���˷���
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	
	/**
	 * ������������
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
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public EvaluateDetails clone()
	{
		EvaluateDetails item = new EvaluateDetails(evaluateRegID,evaluateItemID,score);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param evaluateDetails ָ���Ķ���Դ
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
//	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
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



