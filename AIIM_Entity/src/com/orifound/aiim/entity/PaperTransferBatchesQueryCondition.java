/**
 * 
 */
package com.orifound.aiim.entity;

import java.util.Date;

/**
 * 实物档案移交批次信息的查询条件对象类
 *
 */
public class PaperTransferBatchesQueryCondition {
	
	/**
	 * 构造函数
	 */
	public PaperTransferBatchesQueryCondition() {
		
	}
	
	/**
	 * 移交日期范围的起始日期
	 */
	private Date transferDateBegin = null;

	/**
	 * 设置属性值：移交日期范围的起始日期
	 * @param transferDate 移交日期范围的起始日期
	 */
	public void setTransferDateBegin(Date transferDateBegin) {
		this.transferDateBegin = transferDateBegin;
	}

	/**
	 * 获取属性值：移交日期范围的起始日期
	 * @return 移交日期范围的起始日期
	 */
	public Date getTransferDateBegin() {
		return transferDateBegin;
	}

	/**
	 * 移交日期范围的截止日期
	 */
	private Date transferDateEnd = null;

	/**
	 * 设置属性值：移交日期范围的截止日期
	 * @param transferDateEnd 移交日期范围的截止日期
	 */
	public void setTransferDateEnd(Date transferDateEnd) {
		this.transferDateEnd = transferDateEnd;
	}

	/**
	 * 获取属性值：移交日期范围的截止日期
	 * @return 移交日期范围的截止日期
	 */
	public Date getTransferDateEnd() {
		return transferDateEnd;
	}

	

	
}
