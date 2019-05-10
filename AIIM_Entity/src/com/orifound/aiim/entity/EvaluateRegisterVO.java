package com.orifound.aiim.entity;

import java.util.ArrayList;
import java.util.List;


/**
 * 考核登记信息显示类
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
	 * 考核记录编号
	 */
	private int iD=0;

	/**
	 * 真实姓名
	 */
	private String realName=null;
	
	/**
	 * 职务名称
	 */
	private String dutyName=null;
	
	/**
	 * 考核年度
	 */
	private String years=null;
	
	/**
	 * 考核分数
	 */
	private Integer score= null;
	
	/**
	 * 考核等级名称
	 */
	private String evaluateLevelName=null;
	
	/**
	 * 考核登记信息
	 */
	private EvaluateRegister evaluateRegister = null;
	
	/**
	 * 考核明细信息集合
	 */
	private List<EvaluateDetails> details = new ArrayList<EvaluateDetails>();
	
	/**
	 * 特定年度的考核记录数
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
	 * 获取属性值：考核分数
	 * @return 考核分数
	 */
	public Integer getScore()
	{
		return score;
	}

	/**
	 * 设置属性值：考核分数
	 * @param score 考核分数
	 */
	public void setScore(Integer score)
	{
		this.score = score;
	}

	/**
	 * 获取属性值：真实姓名
	 * @return 真实姓名
	 */
	public String getRealName()
	{
		return realName;
	}

	/**
	 * 设置属性值：真实姓名
	 * @param realName 真实姓名
	 */
	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	/**
	 * 获取属性值：职务名称
	 * @return score 职务名称
	 */
	public String getDutyName() {
		return dutyName;
	}

	/**
	 * 设置属性值：职务名称
	 * @param score 职务名称
	 */
	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	/**
	 * 获取属性值：考核等级名称
	 * @return 考核等级名称
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
	 * 设置属性值：考核等级名称
	 * @param score 考核等级名称
	 */
	public void setEvaluateLevelName(String evaluateLevelName) {
		this.evaluateLevelName = evaluateLevelName;
	}
	
	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public EvaluateRegisterVO clone()
	{
		EvaluateRegisterVO item = new EvaluateRegisterVO(iD, realName, dutyName, score, evaluateLevelName, years);

		return item;
	}

	/**
	* 从指定对象克隆，复制所有属性值
	* @param evaluateLevel 指定的对象源
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