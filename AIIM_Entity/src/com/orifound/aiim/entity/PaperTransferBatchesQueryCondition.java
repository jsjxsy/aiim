/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * ʵ�ﵵ���ƽ�������Ϣ�Ĳ�ѯ����������
 *
 */
public class PaperTransferBatchesQueryCondition {
	
	/**
	 * ���캯��
	 */
	public PaperTransferBatchesQueryCondition() {
		
	}
	
	/**
	 * �ƽ����ڷ�Χ����ʼ����
	 */
	private Date transferDateBegin = null;

	/**
	 * ��������ֵ���ƽ����ڷ�Χ����ʼ����
	 * @param transferDate �ƽ����ڷ�Χ����ʼ����
	 */
	public void setTransferDateBegin(Date transferDateBegin) {
		this.transferDateBegin = transferDateBegin;
	}

	/**
	 * ��ȡ����ֵ���ƽ����ڷ�Χ����ʼ����
	 * @return �ƽ����ڷ�Χ����ʼ����
	 */
	public Date getTransferDateBegin() {
		return transferDateBegin;
	}

	/**
	 * �ƽ����ڷ�Χ�Ľ�ֹ����
	 */
	private Date transferDateEnd = null;

	/**
	 * ��������ֵ���ƽ����ڷ�Χ�Ľ�ֹ����
	 * @param transferDateEnd �ƽ����ڷ�Χ�Ľ�ֹ����
	 */
	public void setTransferDateEnd(Date transferDateEnd) {
		this.transferDateEnd = transferDateEnd;
	}

	/**
	 * ��ȡ����ֵ���ƽ����ڷ�Χ�Ľ�ֹ����
	 * @return �ƽ����ڷ�Χ�Ľ�ֹ����
	 */
	public Date getTransferDateEnd() {
		return transferDateEnd;
	}

	

	
}
