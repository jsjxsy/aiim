package com.orifound.aiim.entity;

import java.util.Date;

/**
 * ������֤�շ���Ϣ���ʵ����
 */
public class ArchivesCertificateCharge {
	/**
	 * ���캯��
	 */
	public ArchivesCertificateCharge() {
	}

	/**
	 * ���ֶβ����Ĺ��캯��
	 * 
	 * @param certificateID
	 *            ��֤ҵ��ǼǱ��
	 * @param shouldCharge
	 *            Ӧ�ɽ��
	 * @param realCharge
	 *            ʵ���շ�
	 * @param chargeTime
	 *            �ɷ�ʱ��
	 * @param managerUserID
	 *            �����˱��
	 * @param remark
	 *            ��ע
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
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol = null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * ���Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag = null;

	/**
	 * ��ȡ����ֵ�����Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * ��������ֵ�����Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * ��֤ҵ��ǼǱ��
	 */
	private int certificateID = 0;

	/**
	 * ��ȡ����ֵ����֤ҵ��ǼǱ��
	 */
	public int getCertificateID() {
		return certificateID;
	}

	/**
	 * ��������ֵ����֤ҵ��ǼǱ��
	 */
	public void setCertificateID(int certificateID) {
		this.certificateID = certificateID;
	}

	/**
	 * Ӧ�ɽ��
	 */
	private double shouldCharge = 0;

	/**
	 * ��ȡ����ֵ��Ӧ�ɽ��
	 */
	public double getShouldCharge() {
		return shouldCharge;
	}

	/**
	 * ��������ֵ��Ӧ�ɽ��
	 */
	public void setShouldCharge(double shouldCharge) {
		this.shouldCharge = shouldCharge;
	}

	/**
	 * ʵ���շ�
	 */
	private double realCharge = 0;

	/**
	 * ��ȡ����ֵ��ʵ���շ�
	 */
	public double getRealCharge() {
		return realCharge;
	}

	/**
	 * ��������ֵ��ʵ���շ�
	 */
	public void setRealCharge(double realCharge) {
		this.realCharge = realCharge;
	}

	/**
	 * �ɷ�ʱ��
	 */
	private Date chargeTime;

	/**
	 * ��ȡ����ֵ���ɷ�ʱ��
	 */
	public Date getChargeTime() {
		return chargeTime;
	}

	/**
	 * ��������ֵ���ɷ�ʱ��
	 */
	public void setChargeTime(Date chargeTime) {
		this.chargeTime = chargeTime;
	}

	/**
	 * �����˱��
	 */
	private int managerUserID = 0;

	/**
	 * ��ȡ����ֵ�������˱��
	 */
	public int getManagerUserID() {
		return managerUserID;
	}

	/**
	 * ��������ֵ�������˱��
	 */
	public void setManagerUserID(int managerUserID) {
		this.managerUserID = managerUserID;
	}

	/**
	 * ��ע
	 */
	private String remark = "";

	/**
	 * ��ȡ����ֵ����ע
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * ��������ֵ����ע
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * clone
	 * 
	 * @return ��¡��ǰ����ʵ����õ����¶���
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
