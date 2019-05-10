/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;


/**
 * ������Ϣ�������Ľӿڶ���
 *
 */
public interface IDepartmentInfoManageService {

	/**
	 * ���ݲ��ű�Ų��Ҳ�����Ϣ
	 * @param departmentID ���ű��
	 * @param departmentInfo ���ز��ҳɹ��Ĳ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDepartmentInfoByID(int departmentID,DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ����ϵͳ�����в�����Ϣ
	 * @param departmentInfos ���ز��ҳɹ������в�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findDepartmentInfos(List<DepartmentInfo> departmentInfos,ErrInfo pErrInfo);
	
	/**
	 * ���һ������
	 * @param departmentInfo Ҫ��ӵĲ�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ����ָ��������Ϣ
	 * @param departmentInfo Ҫ���µĲ�����Ϣ���䲿�ű�����Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ɾ��ָ��������Ϣ
	 * @param departmentInfo Ҫɾ���Ĳ�����Ϣ���䲿�ű�����Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteDepartmentInfo(DepartmentInfo departmentInfo,ErrInfo pErrInfo);
	
	/**
	 * ��������->��ټ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ
	 * @param departmentInfos ���ز��ҳɹ������в�����Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findFormationDepartments(List<DepartmentInfo> formationDepartments,ErrInfo pErrInfo);
	
}
