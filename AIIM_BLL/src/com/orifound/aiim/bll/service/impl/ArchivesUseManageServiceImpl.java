/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseManageService;
import com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesUseRegisterDao;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.CurrentRequestID;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * �������ù������ʵ����
 *
 */
public class ArchivesUseManageServiceImpl implements IArchivesUseManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesUseManageServiceImpl() {

	}
	
	/**
	 * �������õǼǱ�����ݷ��ʲ�
	 */
	private IArchivesUseRegisterDao archivesUseRegisterDao = null;

	public IArchivesUseRegisterDao getArchivesUseRegisterDao() {
		return archivesUseRegisterDao;
	}

	public void setArchivesUseRegisterDao(IArchivesUseRegisterDao archivesUseRegisterDao) {
		this.archivesUseRegisterDao = archivesUseRegisterDao;
	}
	
	/**
	 * �ⷿ������Ϣ���������
	 */
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService = null;
	
	public IStoreroomArchivesInfoManageService getStoreroomArchivesInfoManageService() {
		return storeroomArchivesInfoManageService;
	}

	public void setStoreroomArchivesInfoManageService(IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService) {
		this.storeroomArchivesInfoManageService = storeroomArchivesInfoManageService;
	}

	/**
	 * ʵ�ﵵ�����ó�ȥ��ϸ���������
	 */
	private IArchivesUseOutInfoManageService archivesUseOutInfoManageService = null;
	
	
	public IArchivesUseOutInfoManageService getArchivesUseOutInfoManageService() {
		return archivesUseOutInfoManageService;
	}

	public void setArchivesUseOutInfoManageService(IArchivesUseOutInfoManageService archivesUseOutInfoManageService) {
		this.archivesUseOutInfoManageService = archivesUseOutInfoManageService;
	}

	/**
	 * ���ҵ�����������ݷ������Ƿ�ע��ɹ���DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesUseRegisterDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������õǼǱ�����ݷ���ע��ʧ�ܣ������Ƿ��н�������ע���ֵ��");
			}
			
			if (archivesUseOutInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("ʵ�ﵵ�����ó�ȥ��ϸ���������ע��ʧ�ܣ������Ƿ��н�������ע���ֵ��");
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
		}

		return pFlag;
	}
	
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#checkArchivesUseRequestDetail(com.orifound.aiim.entity.ArchivesUseRequestDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean checkArchivesUseRequestDetail(
			ArchivesUseRequestDetail archivesUseRequestDetail, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findExpiringArchivesUseInfos(com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findExpiringArchivesUseRegister(int dayNum,DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (archivesUseRegisterDao.findAllExpiringRegister(dayNum, dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���н�Ҫ�������õǼ���Ϣʧ��: ");
					
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

	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseExpiredUserInfos(com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseExpiredUseRegister(DataPageInfo dataPageInfo,List<ArchivesUseRegister> archivesUseRegisters,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (archivesUseRegisterDao.findAllExpiredRegister(dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���й������õǼ���Ϣʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfoByID(int, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfoByID(int archivesUseInfoID,
			ArchivesUseOutInfo archivesUseInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfos(com.orifound.aiim.entity.ArchivesUseInfoQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfos(
			ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition,
			DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseInfos,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseInfosByPersonID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfosByPersonID(int archivesUsePersonID,
			List<ArchivesUseOutInfo> archivesUseInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUsePersonInfoByPersonID(int, com.orifound.aiim.entity.ArchivesUsePersonInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUsePersonInfoByPersonID(int archivesUsePersonID,
			ArchivesUsePersonInfo archivesUsePersonInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRegister(java.lang.String, com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRegister(	ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ñ�Ų�ѯ�������õǼ���Ϣʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRegisters(com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRegisters(
			ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition,
			DataPageInfo dataPageInfo,
			List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		String querySQL = "";
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ݲ�ѯ����������SQL��ѯ����
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseRegisterQueryCondition == null) {
					pFlag = false;
					pErrInfo.getContent().append("���ò�ѯ����δ��ʼ����");
				}else{
					querySQL = getQuerySQL(archivesUseRegisterQueryCondition);
				}				
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (archivesUseRegisterDao.findByConditions(querySQL, dataPageInfo, archivesUseRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������������ѯ�������õǼ���Ϣʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRequestDetails(com.orifound.aiim.entity.EnumCheckResult, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRequestDetails(
			EnumCheckResult enumCheckResult, DataPageInfo dataPageInfo,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#findArchivesUseRequests(com.orifound.aiim.entity.ArchivesUseRequestQueryCondition, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseRequests(
			ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition,
			DataPageInfo dataPageInfo,
			List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateArchivesUseRequestBatchNo(com.orifound.aiim.entity.CurrentRequestID, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateArchivesUseRequestBatchNo(
			CurrentRequestID currentRequestID, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateFetchPaperList(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateFetchPaperList(
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#generateFetchPaperList(com.orifound.aiim.entity.ArchivesUseRequest, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean generateFetchPaperList(
			ArchivesUseRequest archivesUseRequest,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#registerArchivesUse(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesUseRegister(ArchivesUseRegister archivesUseRegister,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseRegisterDao.add(archivesUseRegister, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӵ������õǼ���Ϣʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#renewArchivesByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean renewArchivesByBarcode(int daysNum,ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿڣ���ȡ������������Ӧ�ĵ���
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݵ����������ѯ���ó�ȥ��ϸʧ��: ");
				}
			}
			
			if(pFlag){
				pErrPos = 3;
				//����
				archivesUseRegister.setID(archivesUseOutInfo.getUseRegID());
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������õǼǱ�Ų������õǼ���Ϣʧ�ܣ�");
				}else{
					archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
				}
			}
			
			//��֤�����Ƿ��������
			if (pFlag) {
				pErrPos = 3;
				if(archivesUseOutInfo.getNBXH()==0){
					pFlag = false;
					pErrInfo.getContent().insert(0, "û���ҵ�������'"+archivesUseOutInfo.getArchivesBarcode()+"'��ָ���Ľ赵��Ϣ��");
				}else if(archivesUseOutInfo.getBorrowFlag() == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ϊ��"+archivesUseOutInfo.getTitle()+"���ĵ��������÷�ʽΪ�鵵���鵵����ʱ���������裡");
				}
			}
			
			//����ҵ���߼������¹黹ʱ��,�������ҵ��
			if(pFlag){
				pErrPos = 4;	
				//�����µĹ黹ʱ��
				Date date =new Date((archivesUseOutInfo.getShouldReturnDate().getTime()+(long)daysNum*24*60*60*1000));
				archivesUseOutInfo.setShouldReturnDate(date);//�����µĹ黹����
				if(archivesUseOutInfoManageService.updateShouldReturnDate(archivesUseOutInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���裺���µ����黹ʱ��ʧ�ܣ�");
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
System.out.println(pErrInfo.toString());
			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#returnArchivesByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesUseInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean returnArchivesByBarcode(	ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿڣ���ȡ������������Ӧ�ĵ���
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݵ����������ѯ���ó�ȥ��ϸ��Ϣʧ��: ");
				}
			}
			
				
			//����DAO�ӿڣ���ȡ�Ǽ���Ϣ
			if(pFlag){
				pErrPos = 3;
				//����
				archivesUseRegister.setID(archivesUseOutInfo.getUseRegID());
				if (archivesUseRegisterDao.findByID(archivesUseRegister, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������õǼǱ�Ų������õǼ���Ϣʧ�ܣ�");
				}else{
					//����ȡ�ĵǼ���Ϣ��ӵ�������ϸ��Ϣ��ȥ
					archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
				}
			}

			//����ҵ���߼������¿ⷿ״̬�������ó�ȥ���еļ�¼ת�Ƶ����ù黹����,��ɹ黹ҵ��
			if(pFlag){
				pErrPos = 4;
				if(archivesUseOutInfoManageService.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�黹����ʧ�ܣ�");
				}else{
					//���¿ⷿ������Ϣ����ָ�������뵵���Ĺݲ�״̬��Ϊ�ɱ�����״̬
					StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
					storeroomArchivesInfo.setArchivesBarcode(archivesUseOutInfo.getArchivesBarcode());
					storeroomArchivesInfo.setStoreStatus(EnumStoreStatus.�ɱ�����);
					storeroomArchivesInfoManageService.updateStoreStatusByArchivesBarcode(storeroomArchivesInfo, pErrInfo);
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
System.out.println(pErrInfo.toString());
			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#submitArchivesUseRequest(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesUseRequest, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean submitArchivesUseRequest(UserInfo userInfo,
			ArchivesUseRequest archivesUseRequest,
			List<ArchivesUseRequestDetail> archivesUseRequestDetails,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#unregisterArchivesUse(com.orifound.aiim.entity.ArchivesUseRegister, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean unregisterArchivesUse(
			ArchivesUseRegister archivesUseRegister, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseManageService#unregisterArchivesUse(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean unregisterArchivesUse(
			List<ArchivesUseRegister> archivesUseRegisters, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/***
	 * �������ò�ѯ��������SQL
	 * @param archivesUseRegisterQueryCondition �������ò�ѯ����
	 * @return querySQL ���ع����SQL��䣬��and��ʼ 
	 */
	private String getQuerySQL(ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition){
			String querySQL = "";	
		
			//�赵/�鵵��ʶ
			if(archivesUseRegisterQueryCondition.getBorrowFlag()==true){
				querySQL += " AND BorrowFlag= 1";
			}else{
				querySQL += " AND BorrowFlag= 0";
			}
			
			//֤���Ų�ѯ
			if (archivesUseRegisterQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseRegisterQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseRegisterQueryCondition.getIDCardNo().trim() + "%'";
			}

			//������ʼʱ��
			if(archivesUseRegisterQueryCondition.getUseDateBegin()!=null ){
				querySQL += " AND UseDate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRegisterQueryCondition.getUseDateBegin() )   + "'";
			}
			
			//���ý���ʱ��
			if(archivesUseRegisterQueryCondition.getUseDateEnd()!=null ){
				querySQL += " AND UseDate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRegisterQueryCondition.getUseDateEnd() )   + "'";
			}
			
			//��������
			if(archivesUseRegisterQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseRegisterQueryCondition.getUserDepartment().trim())){
				querySQL += " AND Department LIKE '%" + archivesUseRegisterQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//����
			if(archivesUseRegisterQueryCondition.getUserRealName()!=null && !"".equals(archivesUseRegisterQueryCondition.getUserRealName().trim())){
				querySQL += " AND Name LIKE '%" + archivesUseRegisterQueryCondition.getUserRealName().trim() + "%'";
			}
		return querySQL;
	}

}
