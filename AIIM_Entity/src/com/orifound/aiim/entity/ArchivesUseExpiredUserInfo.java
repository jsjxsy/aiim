/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 档案利用过期的用户信息对象类<br>
 * 存储那些有档案过期未还的利用人信息及其未还档案的数量信息
 *
 */
public class ArchivesUseExpiredUserInfo {

	/**
	 * 利用人真实姓名
	 */
	private String userRealName=null;

	/**
	 * 获取属性值：利用人真实姓名
	 * @return 利用人真实姓名
	 */
	public String getUserRealName()
	{
		return userRealName;
	}

	/**
	 * 设置属性值：利用人真实姓名
	 * @param userRealName 利用人真实姓名
	 */
	public void setUserRealName(String userRealName)
	{
		this.userRealName = userRealName;
	}
	
	/**
	 * 利用人所属单位名称
	 */
	private String userDepartment=null;

	/**
	 * 获取属性值：利用人所属单位名称
	 * @return 利用人所属单位名称
	 */
	public String getUserDepartment()
	{
		return userDepartment;
	}

	/**
	 * 设置属性值：利用人所属单位名称
	 * @param userDepartment 利用人所属单位名称
	 */
	public void setUserDepartment(String userDepartment)
	{
		this.userDepartment = userDepartment;
	}

	/**
	 * 过期未还的档案数量
	 */
	private int expiredArchivesCount = 0;

	/**
	 * 设置属性值：过期未还的档案数量
	 * @param expiredArchivesCount 过期未还的档案数量
	 */
	public void setExpiredArchivesCount(int expiredArchivesCount) {
		this.expiredArchivesCount = expiredArchivesCount;
	}

	/**
	 * 获取属性值：过期未还的档案数量
	 * @return 过期未还的档案数量
	 */
	public int getExpiredArchivesCount() {
		return expiredArchivesCount;
	}

	
}
