package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * ���˵Ǽ���Ϣ��ʾ��
 * @author tyb
 *
 */
public class EvaluateRegisterVO {
	
	public EvaluateRegisterVO() {
		
	}
	
	public EvaluateRegisterVO(int iD, String realName, String dutyName,
			Integer score, String evaluateLevelName,String years) {
		super();
		this.iD = iD;
		this.realName = realName;
		this.dutyName = dutyName;
		this.score = score;
		this.evaluateLevelName = evaluateLevelName;
		this.years = years;
	}

	
	/**
	 * ���˼�¼���
	 */
	private int iD=0;

	/**
	 * ��ʵ����
	 */
	private String realName=null;
	
	/**
	 * ְ������
	 */
	private String dutyName=null;
	
	/**
	 * �������
	 */
	private String years=null;
	
	/**
	 * ���˷���
	 */
	private Integer score= null;
	
	/**
	 * ���˵ȼ�����
	 */
	private String evaluateLevelName=null;
	
	/**
	 * ���˵Ǽ���Ϣ
	 */
	private EvaluateRegister evaluateRegister = null;
	
	/**
	 * ������ϸ��Ϣ����
	 */
	private List<EvaluateDetails> details = new ArrayList<EvaluateDetails>();
	
	/**
	 * �ض���ȵĿ��˼�¼��
	 */
	private int evaluateCount ;

	public List<EvaluateDetails> getDetails() {
		return details;
	}

	public void setDetails(List<EvaluateDetails> details) {
		this.details = details;
	}

	public EvaluateRegister getEvaluateRegister() {
		return evaluateRegister;
	}

	public void setEvaluateRegister(EvaluateRegister evaluateRegister) {
		this.evaluateRegister = evaluateRegister;
	}
	
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
	 * ��ȡ����ֵ�����˷���
	 * @return ���˷���
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * ��������ֵ�����˷���
	 * @param score ���˷���
	 */
	public void setScore(Integer score)
	{
		this.score = score;
	}

	/**
	 * ��ȡ����ֵ����ʵ����
	 * @return ��ʵ����
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * ��������ֵ����ʵ����
	 * @param realName ��ʵ����
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * ��ȡ����ֵ��ְ������
	 * @return score ְ������
	 */
	public String getDutyName() {
		return dutyName;
	}

	/**
	 * ��������ֵ��ְ������
	 * @param score ְ������
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	/**
	 * ��ȡ����ֵ�����˵ȼ�����
	 * @return ���˵ȼ�����
	 */
	public String getEvaluateLevelName() {
		return evaluateLevelName;
	}
	
	public int getID() {
		return iD;
	}

	public void setID(int iD) {
		this.iD = iD;
	}

	/**
	 * ��������ֵ�����˵ȼ�����
	 * @param score ���˵ȼ�����
	 */
	public void setEvaluateLevelName(String evaluateLevelName) {
		this.evaluateLevelName = evaluateLevelName;
	}
	
	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public EvaluateRegisterVO clone()
	{
		EvaluateRegisterVO item = new EvaluateRegisterVO(iD, realName, dutyName, score, evaluateLevelName, years);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param evaluateLevel ָ���Ķ���Դ
	*/
	public void cloneFrom(EvaluateRegisterVO evaluateRegisterVO)
	{
		this.iD = evaluateRegisterVO.getID();
		this.realName = evaluateRegisterVO.getRealName();
		this.dutyName = evaluateRegisterVO.getDutyName();
		this.score = evaluateRegisterVO.getScore();
		this.evaluateLevelName = evaluateRegisterVO.getEvaluateLevelName();
		this.years = evaluateRegisterVO.getYears();
	}

	public int getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(int evaluateCount) {
		this.evaluateCount = evaluateCount;
	}
}