/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������Ϣ���DAO�ӿڶ���
 *
 */
public interface IDepartmentInfoDao
{

	/**
	 * Dao�ӿڶ��壺��Ӳ���
	 * @param departmentInfo Ҫ��ӵĲ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean save(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ĳ���
	 * @param departmentInfo Ҫɾ���Ĳ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ĳ���
	 * @param departmentInfo Ҫ���µĲ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(DepartmentInfo departmentInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺�������еĲ���
	 * @param departmentInfos ���ز��ҳɹ��Ĳ��ż���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findAll(List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺���ݲ��ű�Ų��Ҳ���
	 * @param pID ָ���Ĳ��ű��
	 * @param departmentInfo ���ز��ҳɹ��Ĳ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(int pID, DepartmentInfo departmentInfo, ErrInfo pErrInfo);

}
