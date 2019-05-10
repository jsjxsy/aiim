/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.dal.dao.sqlserver.impl.ArchivesInfoWorkingDaoImpl;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����鵵��Ϣ�Ĺ������ʵ����
 *
 */
public class ArchivesInfoSavedManageServiceImpl implements IArchivesInfoSavedManageService {
	
	/**
	 *  ע�뵵����Ϣ�鵵���DAO
	 */
	@Autowired
	private IArchivesInfoSavedDao archivesInfoSavedDao;
	
	/**
	 * ע���ټ�����ϸ�������������
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailManageService appraisalKeepDestroyDetailManageService;
	
	/**
	 * ע�뵵������/���ż�����ϸ������ʵ�������������
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * 
	 */
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * 
	 */
	@Autowired
	private IArchivesInfoWorkingDao archivesInfoWorkingDao;
	/**
	 * ���캯��
	 */
	public ArchivesInfoSavedManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesInfoSavedManageServiceImpl(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * ��鵵���鵵��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForarchivesInfoSaved(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoSavedDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�����鵵��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoSavedManageService#queryClassifiedForAppraisal(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean queryClassifiedForAppraisal(ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}
			
			//����ѯ��������������޸�����
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoUseQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ѯ������Ϣʧ��: ");
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}

			if (pFlag) {
				pErrPos = 2;
				if (pFlag) {
					
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
  
	@Override
	public boolean queryClassifiedForSaveDestroyAppraisal(
			ArchivesType archivesType, int FormationDepartmentID,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.queryClassifiedForSaveDestroyAppraisal(archivesType, FormationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��������->��ټ�������ѯָ���������͵Ĺ��ڵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateBatchRetentionPeriod(UserInfo userInfo, ArchivesType archivesType, Map<Integer[], Integer> saveArchives, List<Integer[]> destoryArchives, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				//������Ӵ�ټ�����ϸ���
				if (appraisalKeepDestroyDetailManageService.saveBatch(userInfo , archivesType, batchArchives, opinion, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ������Ӵ�ټ�����ϸ��� ʧ�ܣ�");
				}
			}
			

			
			if (pFlag) {
				//��������->��ټ����Ǽǣ��������µ�����Ϣ�ı�������
				pErrPos = 2;
				if (archivesInfoSavedDao.updateBatchRetentionPeriod(archivesType, saveArchives, destoryArchives, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��������->��ټ����Ǽǣ��������µ�����Ϣ�ı������� ʧ�ܣ�");
				}
			}
			
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean saveArchivesInfos(int NBXH, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//�Ƚ�������Ϣ��working����ɾ��
			if (pFlag) {
				if (this.deleteByNBXH(archivesType, new int[]{NBXH}, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ��");
				}
			}
			
			//�������ļ����ɵ���
			if (pFlag) {
				if (archivesInfoWorkingDao.updateSubArchivesID(NBXH, archivesType, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ļ����ɵ���ʧ��: ");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.save(archivesType, NBXH, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�鵵ʧ��: ");
				}
			}
			
			//��������Ϣ��working����ɾ��
			if (pFlag) {
				if (archivesInfoWorkingManageService.deleteParentAndChild(archivesType, NBXH, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean queryClassifiedForOpenAppraisal(UserInfo userInfo, ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,String isOpen,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û��Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//��⹫����־�Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(isOpen) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�־�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������->������������ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoSavedDao.queryClassifiedForOpenAppraisal(userInfo, archivesType, archivesInfoQueryConditions,isOpen, dataPageInfo, archivesInfos, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean queryClassifiedForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			String isPublic, DataPageInfo dataPageInfo,
			List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û��Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//��⿪�ű�־�Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�־�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������->���ż�������ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoSavedDao.queryClassifiedForPublicAppraisal(userInfo, archivesType, archivesInfoQueryConditions,isPublic, dataPageInfo, archivesInfos, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateBatchForPublicAppraisal(UserInfo userInfo, ArchivesType archivesType, List<Integer> archivesNBXHs, String isPublic, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û��Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//��⿪�ű�־�Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�־�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������->���ż��� ������ӵ������ż�����Ϣ
			if (pFlag) {
				if (appraisalPublicDetailManageService.saveBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, opinion, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ż��� ������ӵ������ż�����Ϣ ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				//��������->���ż��� �������µ���������Ϣ
				if (archivesInfoSavedDao.updateBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��������->���ż��� �������µ���������Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByBarcode(ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.findByBarcode( archivesType,  barcode,  archivesInfo,  pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������ѯ������Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateArchivesBarcode(String archivesFondsID, ArchivesType archivesType, String barcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.updateArchivesBarcode(archivesFondsID, archivesType, barcode, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������ѯ������Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean updateBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			String isPublic,Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û��Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//��⿪�ű�־�Ƿ�Ϊ��
			if (pFlag) {
				if (StringTool.checkNull(isPublic) == false) {
					pFlag = false;
					pErrInfo.getContent().append("������־�Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				//��������->�������� ������ӵ�������������Ϣ
				if (appraisalPublicDetailManageService.saveBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, opinion, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��������->�������� ������ӵ�������������Ϣ ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				//��������->�������� �������µ����ܼ���Ϣ
				if (archivesInfoSavedDao.updateBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, isPublic, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ��������->�������� �������µ����ܼ���Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean queryClassifiedForControlAreaAppraisal(UserInfo userInfo,
			ArchivesType archivesType,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û��Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo == null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				//��������->���ؼ�������ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedDao.queryClassifiedForControlAreaAppraisal(userInfo, archivesType, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������->���ؼ�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByArchivesID(String archivesID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedDao.findByArchivesID(archivesID,archivesType, archivesInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ������Ϣʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByNBXH(int pNBXH, ArchivesType archivesType,
			ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����ڲ�����Ƿ�Ϊ��
			if (pFlag) {
				if (pNBXH<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//�����ڲ���Ų��ҵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.findByNBXH(pNBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean deleteByNBXH(ArchivesType archivesType, int[] nbxhs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����ڲ�����Ƿ�Ϊ��
			if (pFlag) {
				if (nbxhs.length == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//�����ڲ���Ų��ҵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.deleteByNBXH(archivesType,nbxhs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean savedCallBack(int[] nBXHS,UserInfo userInfo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
	
			//��������Ϣ��saved���Ƶ�working��
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.checkOutArchivesInfo(archivesType,nBXHS,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������Ϣ��saved���Ƶ�working��ʧ��: ");
				}
			}
			
			//ɾ��saved��ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 3;
				if (this.deleteByNBXH(archivesType,nBXHS,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ��: ");
				}
			}

			
			//���õ���������״̬
			if (pFlag) {
				pErrPos = 4;
				if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(),nBXHS ,userInfo.getUserID(),EnumWorkFlowStatus.�鵵��Ϣ����޸�, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������ʧ��");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean findChildrenByNBXH(int pNBXH, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForarchivesInfoSaved(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��⵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
				}
			}
			
			//����ڲ����Ϊ��
			if (pFlag) {
				if (pNBXH<=0) {
					pFlag = false;
					pErrInfo.getContent().append("�ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}else {
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}

			//�����ڲ���Ų��ҵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ�����Ϣ ʧ�ܣ�");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
}
