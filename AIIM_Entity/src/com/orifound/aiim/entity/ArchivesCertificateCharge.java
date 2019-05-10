package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 档案出证收费信息表的实体类
 */
public class ArchivesCertificateCharge {
	/**
	 * 构造函数
	 */
	public ArchivesCertificateCharge() {
	}

	/**
	 * 带字段参数的构造函数
	 * 
	 * @param certificateID
	 *            出证业务登记编号
	 * @param shouldCharge
	 *            应缴金额
	 * @param realCharge
	 *            实际收费
	 * @param chargeTime
	 *            缴费时间
	 * @param managerUserID
	 *            经办人编号
	 * @param remark
	 *            备注
	 */
	public ArchivesCertificateCharge(int certificateID, double shouldCharge,
			double realCharge, Date chargeTime, int managerUserID, String remark) {
		// Columns List,Can Used in SELECT SQL:
		// CertificateID,ShouldCharge,RealCharge,ChargeTime,ManagerUserID,Remark
		// Columns List,Can Used in INSERT SQL:
		// pCertificateID,pShouldCharge,pRealCharge,pChargeTime,pManagerUserID,pRemark
		// Columns List,Can Used in UPDATE SQL:
		// CertificateID=pCertificateID,ShouldCharge=pShouldCharge,RealCharge=pRealCharge,ChargeTime=pChargeTime,ManagerUserID=pManagerUserID,Remark=pRemark

		this.certificateID = certificateID;
		this.shouldCharge = shouldCharge;
		this.realCharge = realCharge;
		this.chargeTime = chargeTime;
		this.managerUserID = managerUserID;
		this.remark = remark;
	}

	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol = null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * 附加对象，可以用来保存一些附加信息
	 */
	private Object tag = null;

	/**
	 * 获取属性值：附加对象，可以用来保存一些附加信息
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 设置属性值：附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 出证业务登记编号
	 */
	private int certificateID = 0;

	/**
	 * 获取属性值：出证业务登记编号
	 */
	public int getCertificateID() {
		return certificateID;
	}

	/**
	 * 设置属性值：出证业务登记编号
	 */
	public void setCertificateID(int certificateID) {
		this.certificateID = certificateID;
	}

	/**
	 * 应缴金额
	 */
	private double shouldCharge = 0;

	/**
	 * 获取属性值：应缴金额
	 */
	public double getShouldCharge() {
		return shouldCharge;
	}

	/**
	 * 设置属性值：应缴金额
	 */
	public void setShouldCharge(double shouldCharge) {
		this.shouldCharge = shouldCharge;
	}

	/**
	 * 实际收费
	 */
	private double realCharge = 0;

	/**
	 * 获取属性值：实际收费
	 */
	public double getRealCharge() {
		return realCharge;
	}

	/**
	 * 设置属性值：实际收费
	 */
	public void setRealCharge(double realCharge) {
		this.realCharge = realCharge;
	}

	/**
	 * 缴费时间
	 */
	private Date chargeTime;

	/**
	 * 获取属性值：缴费时间
	 */
	public Date getChargeTime() {
		return chargeTime;
	}

	/**
	 * 设置属性值：缴费时间
	 */
	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	/**
	 * 经办人编号
	 */
	private int managerUserID = 0;

	/**
	 * 获取属性值：经办人编号
	 */
	public int getManagerUserID() {
		return managerUserID;
	}

	/**
	 * 设置属性值：经办人编号
	 */
	public void setManagerUserID(int managerUserID) {
		this.managerUserID = managerUserID;
	}

	/**
	 * 备注
	 */
	private String remark = "";

	/**
	 * 获取属性值：备注
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * 设置属性值：备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesCertificateCharge clone() {
		ArchivesCertificateCharge item = new ArchivesCertificateCharge(
				certificateID, shouldCharge, realCharge, chargeTime,
				managerUserID, remark);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

}
