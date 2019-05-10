package com.orifound.aiim.entity;

import java.util.*;
    
/**
 * ���˵ǼǱ�
 */
public class EvaluateRegister
{
    /**
     * ���캯��
     */
    public EvaluateRegister()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD ���˼�¼���
	* @param years �������
	* @param userID �������˱��
	* @param dutyID ��������ְ����
	* @param score ���˷���
	* @param levelID ���˵ȼ�
	* @param content ������������
	* @param registerDate ���˵Ǽ�����
	* @param checkerUserID �����˱��
	* @param checkerDutyID ������ְ��
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
	* ���ֶβ����Ĺ��캯��
	* @param years �������
	* @param userID �������˱��
	* @param dutyID ��������ְ����
	* @param score ���˷���
	* @param levelID ���˵ȼ�
	* @param content ������������
	* @param registerDate ���˵Ǽ�����
	* @param checkerUserID �����˱��
	* @param checkerDutyID ������ְ��
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
	* ���ֶβ����Ĺ��캯��
	* @param years �������
	* @param userID �������˱��
	* @param dutyID ��������ְ����
	* @param score ���˷���
	* @param levelID ���˵ȼ�
	* @param content ������������
	* @param registerDate ���˵Ǽ�����
	* @param checkerUserID �����˱��
	* @param checkerDutyID ������ְ��
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
	* ���ֶβ����Ĺ��캯��
	* @param years �������
	* @param userID �������˱��
	* @param dutyID ��������ְ����
	*/
	public EvaluateRegister(String years,int userID,int dutyID)
	{
		this.years = years;
		this.userID = userID;
		this.dutyID = dutyID;
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
	private int iD=0;

	/**
	 * ��ȡ����ֵ�����˼�¼���
	 * @return ���˼�¼���
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ�����˼�¼���
	 * @param iD ���˼�¼���
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * �������
	 */
	private String years=null;

	/**
	 * ��ȡ����ֵ���������
	 * @return �������
	 */
	public String getYears()
	{
		return years;
	}

	/**
	 * ��������ֵ���������
	 * @param years �������
	 */
	public void setYears(String years)
	{
		this.years = years;
	}

	/**
	 * �������˱��
	 */
	private int userID=0;

	/**
	 * ��ȡ����ֵ���������˱��
	 * @return �������˱��
	 */
	public int getUserID()
	{
		return userID;
	}

	/**
	 * ��������ֵ���������˱��
	 * @param userID �������˱��
	 */
	public void setUserID(int userID)
	{
		this.userID = userID;
	}

	/**
	 * ��������ְ����
	 */
	private int dutyID=0;

	/**
	 * ��ȡ����ֵ����������ְ����
	 * @return ��������ְ����
	 */
	public int getDutyID()
	{
		return dutyID;
	}

	/**
	 * ��������ֵ����������ְ����
	 * @param dutyID ��������ְ����
	 */
	public void setDutyID(int dutyID)
	{
		this.dutyID = dutyID;
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
	 * ���˵ȼ�
	 */
	private int levelID=0;

	/**
	 * ��ȡ����ֵ�����˵ȼ�
	 * @return ���˵ȼ�
	 */
	public int getLevelID()
	{
		return levelID;
	}

	/**
	 * ��������ֵ�����˵ȼ�
	 * @param levelID ���˵ȼ�
	 */
	public void setLevelID(int levelID)
	{
		this.levelID = levelID;
	}

	/**
	 * ������������
	 */
	private String content=null;

	/**
	 * ��ȡ����ֵ��������������
	 * @return ������������
	 */
	public String getContent()
	{
		return content;
	}

	/**
	 * ��������ֵ��������������
	 * @param content ������������
	 */
	public void setContent(String content)
	{
		this.content = content;
	}

	/**
	 * ���˵Ǽ�����
	 */
	private Date registerDate;

	/**
	 * ��ȡ����ֵ�����˵Ǽ�����
	 * @return ���˵Ǽ�����
	 */
	public Date getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * ��������ֵ�����˵Ǽ�����
	 * @param registerDate ���˵Ǽ�����
	 */
	public void setRegisterDate(Date registerDate)
	{
		this.registerDate = registerDate;
	}

	/**
	 * �����˱��
	 */
	private int checkerUserID=0;

	/**
	 * ��ȡ����ֵ�������˱��
	 * @return �����˱��
	 */
	public int getCheckerUserID()
	{
		return checkerUserID;
	}

	/**
	 * ��������ֵ�������˱��
	 * @param checkerUserID �����˱��
	 */
	public void setCheckerUserID(int checkerUserID)
	{
		this.checkerUserID = checkerUserID;
	}

	/**
	 * ������ְ��
	 */
	private int checkerDutyID=0;

	/**
	 * ��ȡ����ֵ��������ְ��
	 * @return ������ְ��
	 */
	public int getCheckerDutyID()
	{
		return checkerDutyID;
	}

	/**
	 * ��������ֵ��������ְ��
	 * @param checkerDutyID ������ְ��
	 */
	public void setCheckerDutyID(int checkerDutyID)
	{
		this.checkerDutyID = checkerDutyID;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public EvaluateRegister clone()
	{
		EvaluateRegister item = new EvaluateRegister(iD,years,userID,dutyID,score,levelID,content,registerDate,checkerUserID,checkerDutyID);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param evaluateRegister ָ���Ķ���Դ
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



