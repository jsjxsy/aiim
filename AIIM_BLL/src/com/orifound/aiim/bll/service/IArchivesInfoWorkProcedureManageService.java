/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �����鵵���̼�¼�������Ľӿڶ���
 *
 */
public interface IArchivesInfoWorkProcedureManageService
{

	/**
	 * ���һ���µĵ����鵵���̼�¼
	 * @param archivesWorkProcedure ����ӵĵ����鵵���̼�¼��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean saveArchivesInfoWorkProcedure(ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҵ����鵵���̼�¼��Ϣ
	 * @param archivesType �����������࣬�䵵�����������Ա��븳ֵ
	 * @param pNBXH ָ�����ڲ����
	 * @param archivesInfoWorkProcedures ���ز��ҳɹ��ĵ����鵵���̼�¼��Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findArchivesInfoWorkProcedureByNBXH(ArchivesType archivesType,int pNBXH, List<ArchivesInfoWorkProcedure> archivesInfoWorkProcedures, ErrInfo pErrInfo);
	
	
	/**
	 * ɾ��ָ���ĵ����鵵���̼�¼��Ϣ<br>
	 * ����ǰ���������������ļ���Ӧ�ĵ����鵵���̼�¼��ϢҲһ��ɾ��
	 * @param enumArchivesInfoType ������Ϣ���࣬�ǰ������ļ�
	 * @param archivesWorkProcedure Ҫɾ���ĵ����鵵���̼�¼��Ϣ���䵵�������š��ڲ�������Ա��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteArchivesInfoWorkProcedure(EnumArchivesInfoType enumArchivesInfoType, ArchivesInfoWorkProcedure archivesInfoWorkProcedure, ErrInfo pErrInfo);
}
