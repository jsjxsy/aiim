package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.dal.dao.ICurrentTransferBatNoDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesArchvTypeDetailsDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDao;
import com.orifound.aiim.dal.dao.IPaperTransferBatchesDetailsDao;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.entity.ArchivesBoxLabel;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.CurrentContentID;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����ƽ��������ʵ����
 *
 */
public class PaperTransferManageServiceImpl implements IPaperTransferManageService {
	
	/**
	 * �����鵵�������еĵ�����Ϣ����������
	 */
	@Autowired
	private IArchivesInfoWorkingDao archivesInfoWorkingDao = null;
	
	/**
	 *  ֽ�ʵ����ƽ����α�����ݷ��ʶ���
	 */
	@Autowired
	private IPaperTransferBatchesDao paperTransferBatchesDao = null;

	/**
	 * ������ϸ�������ݷ��ʶ���
	 */
	@Autowired
	private IPaperTransferBatchesDetailsDao paperTransferBatchesDetailsDao = null;

	/**
	 * ��ǰ�ƽ�������Ϣ ������ݷ��ʶ���
	 */
	@Autowired
	private ICurrentTransferBatNoDao currentTransferBatNoDao = null;

	/**
	 * ֽ�ʵ����ƽ����ε���������ϸ������DAO�ӿڶ���
	 */
	@Autowired
	private IPaperTransferBatchesArchvTypeDetailsDao paperTransferBatchesArchvTypeDetailsDao; 
	
	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService;
	
	/**
	 * ��ǰ����Ź������ӿ�
	 */
	@Autowired
	private ICurrentContentIDManageService currentContentIDManageService;
	
	/**
	 * ��ǰ����Ź������ӿ�
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;

	/**
	 * �ⷿ������ϢDAO
	 */
	@Autowired
	private IStoreroomArchivesInfoDao storeroomArchivesInfoDao;
	
	@Override
	public boolean statPaperCheckBackCountAndInputCheckReslut(int[] userIDs, Map<Integer, ArchivesTypeEx> archivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx count = new IntegerEx();
		try {
			//�������ע��
			pErrPos = 1;
		
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�����鵵�������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}
			}

			if (pFlag) {
				if (archivesTypeExs == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣδ��ֵ");
				}
			}
			
		   for (ArchivesTypeEx archivesTypeEx : archivesTypeExs.values()) {
			   //����ҵDAOͳ����¼���ͨ��
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��,"UserID1", count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ͳ����¼���ͨ��������ʧ��");
					}else{
						archivesTypeEx.setInputCheckPassArchivesInfoCount(count.getValue());
					}
			   }
			  
			 //����ҵDAOͳ����¼���ͨ��
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.ҵ��ָ������¼��˴��,"UserID1",count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ͳ����¼���δͨ��������ʧ��");
					}else{
						archivesTypeEx.setInputCheckBackArchivesInfoCount(count.getValue());
					}
			   }
			   
			   //����DAOͳ���ƽ�δͨ��������
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��,"UserID1",count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ͳ�ƽ������δͨ��������ʧ��");
					}else{
						archivesTypeEx.setPaperCheckBackArchivesInfoCount(count.getValue());
					}
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
	public boolean findArchivesInfosByEnumWorkFlowStatus(int[] userIDs, ArchivesType archivesType, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo,String userType,List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������ע��
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�����鵵�������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else if(userIDs.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}
			}

			if (pFlag) {
				if (archivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣδ��ֵ");
				}
			}
			
			if (pFlag) {
				if (archivesType.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣ���δ��ֵ");
				}
			}
			
			if (pFlag) {
				if (enumWorkFlowStatus == EnumWorkFlowStatus.NONE) {
					pFlag = false;
					pErrInfo.getContent().append("��ָ����������");
				}
			}
			
			if (pFlag) {
				if (dataPageInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ҳ����Ƿ�Ϊ��");
				}
			}
			
			if (pFlag) {
				//����DAO
				if(archivesInfoWorkingDao.findArchivesInfosByEnumWorkFlowStatus(userIDs, archivesType, enumWorkFlowStatus, dataPageInfo,userType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�����Ϣʧ��");
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
	public boolean staArchivesInfosSumByCurrentTransferBat(int userID, IntegerEx archivesInfosSum,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userID);
				}
			}
	
			if (pFlag) {
				if (archivesInfosSum == null) {
					pFlag = false;
					pErrInfo.getContent().append("ͳ�ƽ������Ƿ�Ϊ��");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������ʧ��");
				}
			}
			
			if(pFlag){
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					archivesInfosSum.setValue(0);
				}else{
					//����DAO
					if(paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum,paperTransferBatch,  pErrInfo)== false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "ͳ�Ƶ�ǰ���ε�����Ϣ����ʧ��");
					}
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
	public boolean addToPaperTransferBatchForOutside(UserInfo userInfo, ArchivesType archivesType, List<ArchivesInfo> archivesInfos,boolean insideFlag , ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		paperTransferBatch.setInsideFlag(insideFlag);//���ù����ƽ���־Ϊfalse
		
		int NBXHS [] = null;
		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
		
		try {
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			//����û����
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0��");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userInfo.getUserID());
					paperTransferBatch.setTransferDepartmentID(userInfo.getDepartmentID());
				}
			}
			
			//��������Ϣ
			if (pFlag) {
				if (archivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�գ������Ƿ�ֵ��");
				}else if(archivesType.getID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ��ŷǷ�Ϊ0�������Ƿ�ֵ��");
				}
			}
			
			//��鵵����Ϣ����
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ�����嵥�ĵ�����Ϣ���ϷǷ�Ϊ�գ������Ƿ��ʼ����");
				}else if(archivesInfos.size() <= 0){
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�����嵥�ĵ�����Ϣ��");
				}else{
					NBXHS = new int [archivesInfos.size()];
					for (int i = 0; i < archivesInfos.size(); i++) {
						NBXHS[i] = archivesInfos.get(i).getNBXH();
					}
				}
			}

			//����DAO���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������   ����������Ϣ 
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������ʧ��");
				}
			}
			
			//���û�ҵ����򴴽�
			if (pFlag) {
				pErrPos = 3;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					if (this.getTransferBatNo(userInfo.getUserID(), paperTransferBatch,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�õ����κ�ʧ��");
					}
					
					//���һ���µ�������Ϣ
					if (pFlag) {
						if (paperTransferBatchesDao.add(paperTransferBatch, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���������Ϣʧ��");
						}
					}
				}
			}

			//���������뵽����
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatchesDetailsDao.addPaperTransferBatchesDetails(archivesType,archivesInfos,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ƽ��嵥ʧ��");
				}
			}
			
			//���µ��������µ����������
			if (pFlag) {
				pErrPos = 5;
				paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
				paperTransferBatchesArchvTypeDetail.setTransferTotal(archivesInfos.size());
				if (insideFlag) {
					List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
					pPaperTransferBatchesArchvTypeDetails.add(paperTransferBatchesArchvTypeDetail);
					if(this.updatePaperTransferBatchesArchvTypeDetailForInside(paperTransferBatch, pPaperTransferBatchesArchvTypeDetails, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "���µ�����Ϣ������ϸ�����ʧ��");
					}
				}else{
					if(this.updatePaperTransferBatchesArchvTypeDetailForOutside(paperTransferBatchesArchvTypeDetail,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "���µ�����Ϣ������ϸ�����ʧ��");
					}
				}
			}
			
			//���µ���������״̬
			if (pFlag) {
				pErrPos = 6;
				if(archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), NBXHS, userInfo.getUserID(), EnumWorkFlowStatus.����������ƽ��嵥, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ���������״̬ʧ��: ");
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
	public boolean addToPaperTransferBatchForIntside(UserInfo userInfo, String[] paperTransferBatchNos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		paperTransferBatch.setInsideFlag(true);//���ù����ƽ���־
		
		List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		
		try {
			//����Ƿ��н���DAO����ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			//����û����
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ0��");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userInfo.getUserID());
					paperTransferBatch.setTransferDepartmentID(userInfo.getDepartmentID());
				}
			}
			
			//���κ������Ƿ�Ϊ�ջ�ֵ
			if (pFlag) {
				if (paperTransferBatchNos == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫ�����嵥��������Ϣ���ϷǷ�Ϊ�գ������Ƿ��ʼ����");
				}else if(paperTransferBatchNos.length == 0){
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�����嵥�����Ρ�");
				}
			}

			//����DAO���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������   ����������Ϣ 
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,true,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������ʧ��");
				}
			}
			
			//���û�ҵ����򴴽�
			if (pFlag) {
				pErrPos = 3;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					if (this.getTransferBatNo(userInfo.getUserID(), paperTransferBatch,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�õ����κ�ʧ��");
					}
					
					//���һ���µ�������Ϣ
					if (pFlag) {
						if (paperTransferBatchesDao.add(paperTransferBatch, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���������Ϣʧ��");
						}
					}
				}
			}

			//���γɲ��ŵ��ƽ����μ��뵽ҵ��ָ���ҵ��ƽ�������   д�������κ����ι����ı�
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatchesDetailsDao.addToPaperTransferBatchaddToPaperTransferBatch(paperTransferBatchNos,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ƽ��嵥ʧ��");
				}
			}
			
			//�������ε�״̬
			if (pFlag) {
				pErrPos = 5;
				for (String pPaperTransferBatchNo : paperTransferBatchNos) {
					if(paperTransferBatchesDao.updateBatState(pPaperTransferBatchNo, EnumPaperTransferBatchesDealStatus.��ӵ��ƽ��嵥, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "��������״̬ʧ��");
					}
				}
			}
			
			//���µ��������µ����������   ֱ��ȥ����ͳ�ƣ�
			if (pFlag) {
				pErrPos = 6;
				paperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
				if(this.updatePaperTransferBatchesArchvTypeDetailForInside(paperTransferBatch,paperTransferBatchesArchvTypeDetails,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ�����Ϣ������ϸ�����ʧ��");
				}
			}
			
			//�������µ���������״̬��д�빤������¼��
			if (pFlag) {
				pErrPos = 7;
				if(this.setFlagForWorkFlow(paperTransferBatch,paperTransferBatchesArchvTypeDetails, EnumWorkFlowStatus.����������ƽ��嵥, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ���������״̬ʧ��: ");
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
	public boolean removeArchiveSInfoFromQDList(int nBXH, ArchivesType archivesType, String transferBatNo, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
		paperTransferBatchesArchvTypeDetail.setTransferBatNo(transferBatNo);
		paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
		
		try {
			pErrPos = 1;
			//ɾ�������嵥��ϸ
			if (pFlag) {
				if(paperTransferBatchesDetailsDao.delete(transferBatNo,archivesType.getID(), nBXH, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
				}
			}
			
			//�޸�working���е����Ĺ�����״̬
			if (pFlag) {
				if(archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int[]{nBXH},userID, enumWorkFlowStatus, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������״̬ʧ��");
				}
			}
			
			//���Ҹ����η������ϸ��Ϣ
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
				}
			}
			
			//���¸����൵��������  ��������ͳ��Ϊͳ�ƵĽ��
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.update(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
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
	public boolean confirmTransferPaperForOutside(String paperTransferBatNo, UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		IntegerEx archivesInfosSum = new IntegerEx();
		
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			if (pFlag) {
				pErrPos = 2;
				paperTransferBatch.setBatNo(paperTransferBatNo);
				if (paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum,paperTransferBatch,  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͳ�������е���������ʧ��");
				}
			}
			
			if (pFlag) {
				//����DAO����������Ϣ��ȷ���ƽ�״̬
				pErrPos = 3;
				if(paperTransferBatchesDao.updateConfirmFlag(paperTransferBatNo, userInfo, archivesInfosSum.getValue(), pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ȷ���ƽ�ʧ��");
				}
			}
			
			//���ҵ�������
			if (pFlag) {
				pErrPos = 4;
				if (paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������ε���������ϸ��Ϣʧ��");
				}
			}
			
			//���ݵ����������õ���������״̬
			if (pFlag) {
				Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					if (archivesTypes.containsKey(paperTransferBatchesArchvTypeDetail.getArchivesTypeID())) {
						if (paperTransferBatchesDetailsDao.setFlagForWorkFlow(archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()), paperTransferBatch, userInfo.getUserID(), EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ�, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���ù�����ʧ��");
						}
					}
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
	public boolean confirmTransferPaperForInside(String paperTransferBatNo, UserInfo userInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		IntegerEx archivesInfosSum = new IntegerEx();
		
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}

			//ͳ�������е������������Ը����ͳ�����������
			if (pFlag) {
				pErrPos = 2;
				paperTransferBatch.setBatNo(paperTransferBatNo);
				if (paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBat(archivesInfosSum, paperTransferBatch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͳ�������е���������ʧ��");
				}
			}
			
			if (pFlag) {
				//����DAO����������Ϣ��ȷ���ƽ�״̬
				pErrPos = 3;
				if(paperTransferBatchesDao.updateConfirmFlag(paperTransferBatNo, userInfo, archivesInfosSum.getValue(), pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ȷ���ƽ�ʧ��");
				}
			}
			
			//���ҵ�������
			if (pFlag) {
				pErrPos = 4;
				if (paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������ε���������ϸ��Ϣʧ��");
				}
			}
			
			//���ݵ����������õ���������״̬
			if (pFlag) {
				Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					if (archivesTypes.containsKey(paperTransferBatchesArchvTypeDetail.getArchivesTypeID())) {
						if (paperTransferBatchesDetailsDao.setFlagForWorkFlow( paperTransferBatch, archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()),userInfo.getUserID(), EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ�, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���ù�����ʧ��");
						}
					}
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
	public boolean findPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo)== false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯʧ��");
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
	public boolean findPaperTransferBatchesForOutside(int departmentID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> pPaperTransferBatches,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�ƽ�������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao.findAll(new int []{departmentID}, enumPaperTransferBatchesDealStatus, pPaperTransferBatches,insideFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ƽ���������Ϣʧ��");
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
	public boolean findPaperTransferBatchesForOutside(int departmentID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition,DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�ƽ�������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (paperTransferBatchesDao.findByCondition(new int []{departmentID},  enumPaperTransferBatchesDealStatus,  paperTransferBatchesQueryCondition,  paperTransferBatches, dataPageInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ƽ���������Ϣʧ��");
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
	public boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus,List<PaperTransferBatch> paperTransferBatches, boolean insideFlag,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//����DAO��ȡָ���û��ƽ�������
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesDao.findPaperTransferBatchsByTransferUser(userID,enumPaperTransferBatchesDealStatus,paperTransferBatches, insideFlag, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ƽ��û������ƽ�����ʧ��");
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
	public boolean findPaperTransferBatchesForInside(int userID, EnumPaperTransferBatchesDealStatus enumPaperTransferBatchesDealStatus, PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition, DataPageInfo dataPageInfo, List<PaperTransferBatch> paperTransferBatches,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				if (paperTransferBatchesDao.findByCondition(enumPaperTransferBatchesDealStatus, new int[]{userID}, paperTransferBatchesQueryCondition, paperTransferBatches, dataPageInfo,insideFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ƽ���������Ϣʧ��");
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
	public boolean findPaperTransferBatchByBatchNo(String paperTransferBatNo, PaperTransferBatch paperTransferBatch, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//����������Ϣ.
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDao.findByBatNO(paperTransferBatNo, paperTransferBatch, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��������������Ϣʧ��");
				}
			}
			
			//�������η�����ϸ��Ϣ
			if (pFlag) {
                Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new LinkedHashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������η���������Ϣʧ��");
				}else{
					paperTransferBatch.setPaperTransferBatchesArchvTypeDetails(paperTransferBatchesArchvTypeDetails);
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
	public boolean findCurrentPaperTransferBatchesArchvTypeDetails(int userID, PaperTransferBatch paperTransferBatch, Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,boolean insideFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		//PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
		try {
			//�������ע��
			pErrPos = 1;
			if (paperTransferBatchesDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}else{
					paperTransferBatch.setBatNoCreateUserID(userID);
				}
			}
	
			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("ͳ�ƽ������Ƿ�Ϊ��");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				
				paperTransferBatch.setBatNoStatus(EnumPaperTransferBatchesDealStatus.δȷ���ƽ�.getEnumValue());
				
				if (paperTransferBatchesDao.findPaperTransferBatchByConfirmFlag(paperTransferBatch,insideFlag,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�ǰ�û��Ƿ���δȷ���ƽ�������ʧ��");
				}
			}
			
			if(pFlag){
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ǰ�û�û��δȷ���ƽ�������");
				}
			}	
			
			if (pFlag) {
				//����DAO
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�õ���ǰ���εķ�����Ϣʧ��");
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
	public boolean findPaperTransferBatchesDetails(String paperTransferBatNo, ArchivesType archivesType, List<PaperTransferBatchesDetail> paperTransferBatchesDetails, List<EnumCheckResult> enumCheckResults, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(paperTransferBatNo,archivesType.getID(),paperTransferBatchesDetails,enumCheckResults,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("��ѯ���ε�����ϸ��Ϣʧ��");
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
	public boolean registerPaperReceive(UserInfo userInfo, String batNo, List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
 
		PaperTransferBatch paperTransferBatch = null;
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			Map<Integer, ArchivesType> archivesTypes = SystemInitializer.getInstance().getPlaneArchivesTypes();

			//1������find�����ҳ������µķ�����ϸ��Ϣ
			if (pFlag) {
				paperTransferBatch = new PaperTransferBatch();
				paperTransferBatch.setBatNo(batNo);
				
				paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				if(paperTransferBatchesArchvTypeDetailsDao.findCurrentPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ���η�����ϸ��Ϣʧ��");
				}
			}
			
			//2��ƥ��
			if (pFlag) {
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails.values()) {
					for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail1 : pPaperTransferBatchesArchvTypeDetails) {
						if(paperTransferBatchesArchvTypeDetail1.getArchivesTypeID() == paperTransferBatchesArchvTypeDetail.getArchivesTypeID()){
							if(paperTransferBatchesArchvTypeDetail1.getTransferTotal() != paperTransferBatchesArchvTypeDetail.getTransferTotal()){
								pFlag = false;
								pErrInfo.getContent().insert(0,"���ࣨ"+archivesTypes.get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID()).getFullName()+"������������ƥ��");
							}
						}
					}
				}
			}
			
			//3����������Ϊ�Ǽ����״̬
			if (pFlag) {
				paperTransferBatch.setReceiveUserID(userInfo.getUserID());
				paperTransferBatch.setReceiveDepartmentID(userInfo.getDepartmentID());
				paperTransferBatch.setReceiveTime(new Date());
				if (paperTransferBatchesDao.updateForReceive(paperTransferBatch, EnumPaperTransferBatchesDealStatus.���յǼ����, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��������Ϊ�Ǽ����״̬ʧ��");
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
	public boolean generateArchivesID(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
		CurrentContentID currentContentID = null;
		List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		List<PaperTransferBatchesDetail> paperTransferBatchesDetails = null;
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//�жϽ�������Ƿ����
			if (pFlag) {
				paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatNo);
				if (paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���η���������Ϣʧ��");
				}else{
					if(paperTransferBatchesArchvTypeDetail.getArchivesIDMaked()){
						pFlag = false;
						pErrInfo.getContent().insert(0, "�÷����Ѿ����ɵ���");
					}
				}
			}
			
			//���Ҵ����η����Ƿ���δ��˵ĵ���
			if (pFlag) {
				pErrPos = 2;
				paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
				List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
				enumCheckResults.add(EnumCheckResult.��δ���);
				if (paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(paperTransferBatNo, archivesType.getID(), paperTransferBatchesDetails,enumCheckResults , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��δ��˵�������ϸ��Ϣʧ��");
				}
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (paperTransferBatchesDetails.size()>0) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�÷����л���δ��˵ĵ������������ɵ���");
				}
			}
			
			//���ɵ���
			if (pFlag) {
				pErrPos = 4;
				//�÷��൵������ȡ�����ŵ�ǰ׺ ����working���������ϸ���ѯ
				Map<Integer,String> archivesIDPrefixs = new HashMap<Integer, String>();
				if (paperTransferBatchesDetailsDao.findArchivesIDPrefixWhitOutArchivesID(paperTransferBatNo, archivesType, archivesIDPrefixs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ���ǰ׺ʧ��");
				}
				
				//�ж���û��ֵ
				if (pFlag) {
					pErrPos = 5;
					if (archivesIDPrefixs.size()>0) {
						for (Iterator<Integer> keys = archivesIDPrefixs.keySet().iterator();keys.hasNext();) {
							int NBXH = keys.next();
							String archivesIDPrefix = archivesIDPrefixs.get(NBXH);
							//�õ��ŵ�ǰ׺ȡ����������ֵ
							currentContentID = new CurrentContentID();
							pErrPos = 6;
							if (currentContentIDManageService.findCurrentContentIDByPrefix(archivesIDPrefix, currentContentID, pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "���ݵ���ǰ׺���Ұ�������ֵʧ��");
							}
							
							//���µ����İ���źͰ�����ı�
							if (pFlag) {
								pErrPos = 7;
								int contentIDTextLength = archivesType.getContentIDFormatLength();//ȡ���÷��స����ı��ĳ���
						    	StringBuffer contentIDTextBase = new StringBuffer();
						    	for(int i=0;i<contentIDTextLength;i++){
						    		contentIDTextBase.append("0");
						    	}
						    	int contentID = currentContentID.getContentID();
						    	
						    	String contentIDText = "";
					        	contentIDText = new Integer(contentID).toString();
					        	
					        	if(contentIDText.length() < contentIDTextLength){
					        		contentIDText =  (contentIDTextBase.replace((contentIDTextLength-contentIDText.length()), contentIDTextLength, contentIDText)).toString();
					        	}
					        	
								if (archivesInfoWorkingDao.updateContentIDText(currentContentID.getContentID(), contentIDText, NBXH, archivesType, pErrInfo) == false) {
					        		pFlag = false;
									pErrInfo.getContent().insert(0, "���°�����ı���Ϣʧ��");
								}
							}
							
							//���°���ŵ����ֵ
							if (pFlag) {
								pErrPos = 8;
								currentContentID.setContentID(currentContentID.getContentID()+1);
								if (currentContentIDManageService.updateCurrentContentID(currentContentID, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "���°�������ֵʧ��");
								}
							}
						}
					}
				}
			}
			
			//�������ݿ�working���������ϸ��ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 9;
				if (paperTransferBatchesDetailsDao.updateArchivesID(paperTransferBatNo, archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ���ʧ��");
				}
			}

			//���´����δ˷���ĵ���������״̬
			if (pFlag) {
				pErrPos = 10;
				if (paperTransferBatchesArchvTypeDetailsDao.updateArchivesIDMaked( paperTransferBatNo,  archivesType.getID(),  pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���´����δ˷���ĵ���������״̬ʧ��");
				}
			}
			
			//�����������Ƿ���δ���ɵ��ŵĵ��������Ϣ  ���û�������ô����ε�״̬Ϊ���ͨ��
			if (pFlag) {
				pErrPos = 11;
				paperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
				if (paperTransferBatchesArchvTypeDetailsDao.findByArchivesIDMaked( paperTransferBatNo,  archivesType.getID(), paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���´����δ˷���ĵ���������״̬ʧ��");
				}
			}
			
			//��������ͨ����־
			if (pFlag) {
				pErrPos = 12;
				if (paperTransferBatchesArchvTypeDetails.size() == 0) {
					if (this.checkFinish(paperTransferBatNo,archivesType,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�������ν������ͨ��״̬ʧ��");
					}
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
	public boolean checkFinish(String paperTransferBatNo, ArchivesType archivesType, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			pErrPos = 1;
			if (paperTransferBatchesDao.updateBatState(paperTransferBatNo, EnumPaperTransferBatchesDealStatus.����������, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"��������������״̬ʧ��");
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
	public boolean paperCheckPass(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String batNo, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��־������ϸ��Ϣ���״̬Ϊ�������ͨ��
			if (pFlag) {
				if (paperTransferBatchesDetailsDao.updateReceiveCheckResult(archivesType.getID(), batNo, archivesInfo.getNBXH(), enumCheckResult, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ����������ʧ��");
				}
			}
			
			//���·�����Ϣ��������
			if (pFlag) {
				PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
				paperTransferBatchesArchvTypeDetail.setTransferBatNo(batNo);
				paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
				if (paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���·�����Ϣ�µ�������ʧ��");
				}
			}
			
			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {
				EnumWorkFlowStatus enumWorkFlowStatus = null;
				if (enumCheckResult == EnumCheckResult.ҵ��ָ�������ͨ��) {
					enumWorkFlowStatus = EnumWorkFlowStatus.ҵ��ָ���ҽ������ͨ��;
					if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, enumWorkFlowStatus, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ����������ʧ��");
					}
					
				} else if (enumCheckResult == EnumCheckResult.�������������ͨ��) {
					enumWorkFlowStatus = EnumWorkFlowStatus.���������ҽ������ͨ��;
					if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, enumWorkFlowStatus, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ����������ʧ��");
					}
					
					//��������������Ƿ���δ��˵ĵ���
					List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
					List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
					enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
					if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(batNo, archivesType.getID(), paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ѯָ������������δ��˵ĵ�����Ϣʧ��");
					}
					
					//�������ε�״̬
					if (pFlag) {
						if (paperTransferBatchesDetails.size() == 0) {
							if (this.checkFinish(batNo,archivesType,pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "�������ν������ͨ��״̬ʧ��");
							}
						}
					}					
					
					//��������Ϣ�鵵��save��
					if (pFlag) {
						if (archivesInfoSavedManageService.saveArchivesInfos(archivesInfo.getNBXH(), archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "�鵵ʧ��");
						}
					}
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
	public boolean paperCheckBack(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String backReason, String batNo, EnumCheckResult enumCheckResult, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//��־������ϸ��Ϣ���״̬Ϊ�������ͨ��
			if (pFlag) {
				if (paperTransferBatchesDetailsDao.updateReceiveCheckResult(archivesType.getID(), batNo, archivesInfo.getNBXH(), enumCheckResult, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ������˴��ʧ��");
				}
			}
			
			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {
				EnumWorkFlowStatus enumWorkFlowStatus = null;
				if (enumCheckResult == EnumCheckResult.ҵ��ָ������˴��) {
					enumWorkFlowStatus = EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��;
				} else if (enumCheckResult == EnumCheckResult.������������˴��) {
					//��������������Ƿ���δ��˵ĵ���
					List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
					enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
					List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
					if(paperTransferBatchesDetailsDao.findByBatNoAndArchivesType(batNo, archivesType.getID(), paperTransferBatchesDetails, enumCheckResults, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ѯָ������������δ��˵ĵ�����Ϣʧ��");
					}
					
					//�������ν���������
					if (pFlag) {
						if (paperTransferBatchesDetails.size() == 0) {
							if (this.checkFinish(batNo,archivesType,pErrInfo) == false) {
								pFlag = false;
								pErrInfo.getContent().insert(0, "�������ν���������״̬ʧ��");
							}
						}
					}
					
					//��������Ϣ��saved���Ƶ�working��
					/*if (pFlag) {
						if (archivesInfoSavedDao.checkOutArchivesInfo(archivesType,new int []{archivesInfo.getNBXH()},pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��������Ϣ��saved���Ƶ�working��ʧ��");
						}
					}*/
					
					//ɾ��saved��ĵ�����Ϣ
				/*	if (pFlag) {
						if (archivesInfoSavedManageService.deleteByNBXH(archivesType,new int []{archivesInfo.getNBXH()},pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ��");
						}
					}*/
					
					enumWorkFlowStatus = EnumWorkFlowStatus.���������ҽ�����˴��;
				}
				
				//���·�����Ϣ��������
				if (pFlag) {
					PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
					paperTransferBatchesArchvTypeDetail.setTransferBatNo(batNo);
					paperTransferBatchesArchvTypeDetail.setArchivesTypeID(archivesType.getID());
					if (paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "���·�����Ϣ�µ�������ʧ��");
					}
				}
				
				//���·�����Ϣ��������
				if (pFlag) {
					if (paperTransferBatchesDao.updateTransferTotal(batNo,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�������ε���������ʧ��");
					}
				}
				
				//д���ԭ��
				if (pFlag) {	
					if (archivesInfoWorkingDao.updateArchivesBackReason(archivesInfo.getNBXH(),archivesType,backReason,pErrInfo)== false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���µ�����Ϣ���ԭ��ʧ��");
					}
				}
				
				//���õ���������״̬
				if (archivesInfoWorkingManageService.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()},userID,enumWorkFlowStatus, pErrInfo) == false) {
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

	
	
	
	/**
	 * �����γɲ��ż����ƽ��嵥      ���µ��������µ����������
	 * @param paperTransferBatchesArchvTypeDetail
	 * @param pErrInfo
	 * @return
	 */
	private boolean updatePaperTransferBatchesArchvTypeDetailForOutside(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail,ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����
			
			if (pFlag) {
				if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
				}
			}
				
			if (pFlag) {
				//����
				if (pPaperTransferBatchesArchvTypeDetail.getID() == 0) {
					if(paperTransferBatchesArchvTypeDetailsDao.add(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
					}
				}else{
					//����
//					pPaperTransferBatchesArchvTypeDetail.setTransferTotal(pPaperTransferBatchesArchvTypeDetail.getTransferTotal()+paperTransferBatchesArchvTypeDetail.getTransferTotal());
//					pPaperTransferBatchesArchvTypeDetail.setReceiveTotal(pPaperTransferBatchesArchvTypeDetail.getReceiveTotal()+paperTransferBatchesArchvTypeDetail.getReceiveTotal());
					
					if(paperTransferBatchesArchvTypeDetailsDao.update(pPaperTransferBatchesArchvTypeDetail, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
					}
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
	
	/**
	 * ҵ��ָ���Ҽ����ƽ��嵥���µ������η�����ϸ��Ϣ  �����ط�����ϸ��Ϣ
	 * @param paperTransferBatchNos
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param pErrInfo
	 * @return
	 */
	private boolean updatePaperTransferBatchesArchvTypeDetailForInside(PaperTransferBatch paperTransferBatch,List<PaperTransferBatchesArchvTypeDetail> pPaperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			//����daoͳ�Ƶ�ǰ���������еĵ���������Ϣ����������
			if (pFlag) {
				pErrPos = 2;
				if(paperTransferBatchesDetailsDao.staArchivesInfosSumByTransferBatArchvType(paperTransferBatch, pPaperTransferBatchesArchvTypeDetails, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͳ�����η��൵��������ʧ��");
				}
			}

			//ѭ�����η�����ϸ��Ϣ���ж��Ƿ��м�¼���еĸ��£�û�������
			if (pFlag) {
				pErrPos = 3;
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : pPaperTransferBatchesArchvTypeDetails) {
					paperTransferBatchesArchvTypeDetail.setTransferBatNo(paperTransferBatch.getBatNo());
		
						if(paperTransferBatchesArchvTypeDetailsDao.findByArchivesTypeAndBatNO(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
						}
					
						
					if (pFlag) {
						//����
						if (paperTransferBatchesArchvTypeDetail.getID() == 0) {
							if(paperTransferBatchesArchvTypeDetailsDao.add(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
							}
						}else{
							//����
							if(paperTransferBatchesArchvTypeDetailsDao.updateWhithSubBat(paperTransferBatchesArchvTypeDetail, pErrInfo) == false){
								pFlag = false;
								pErrInfo.getContent().insert(0,"����ֽ�ʵ����ƽ����ε���������ϸ���ʧ��");
							}
						}
					}
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0 ) {
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
	
	/**
	 * �õ��µ��ƽ����κ�   �Ե����γɲ��ź�ҵ��ָ���Ҿ�����
	 * @param userID �������εĵ�ǰ�û����
	 * @param paperTransferBatch ������������Ϣ
	 * @return ����ɹ����� true ʧ�ܷ��� false
	 */
	private boolean getTransferBatNo(int userID,PaperTransferBatch paperTransferBatch,ErrInfo pErrInfo){

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx currentNo = new IntegerEx();
		
		try {
			pErrPos = 1;
			//1������DAO�ҳ���ǰ�����ֵ�õ����κ� ���
			if (pFlag) {
				if (currentTransferBatNoDao.findCurrentNo(currentNo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�ǰ����������ֵʧ��");
				}
			}
			
			//2������DAO�������ݿ� update
			if (pFlag) {
				pErrPos = 2;
				if(currentTransferBatNoDao.update(userID,currentNo,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ�ǰ����������ֵʧ��");
				}
			}

			//3������DAO�������κ� findCurrentTransferBatNo
			if (pFlag) {
				pErrPos = 3;
				if(currentTransferBatNoDao.findCurrentTransferBatNo(userID,paperTransferBatch,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ������κ�ʧ��");
				}
			}

			//4������Ҳ���
			if (pFlag) {
				pErrPos = 4;
				if(paperTransferBatch.getBatNo() == null || "".equals(paperTransferBatch.getBatNo())){
					this.getTransferBatNo(userID, paperTransferBatch, pErrInfo);
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

	



	/**
	 * ҵ��ָ���Ҽ����ƽ��嵥�����õ���������״̬
	 * @param paperTransferBatch
	 * @param paperTransferBatchesArchvTypeDetails
	 * @param enumWorkFlowStatus
	 * @param pErrInfo
	 * @return
	 */
	private boolean setFlagForWorkFlow(PaperTransferBatch paperTransferBatch, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ArchivesType archivesType = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//ѭ���������ù�����״̬
			if (pFlag) {
				pErrPos = 2;
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetails) {
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(paperTransferBatchesArchvTypeDetail.getArchivesTypeID());
					if(paperTransferBatchesDetailsDao.setArchivesFlagForWorkFlow(paperTransferBatch,archivesType,enumWorkFlowStatus,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������ʧ��");
					}
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
	public boolean statPaperCheckBackCount(int[] userIDs, String userType,Map<Integer, ArchivesTypeEx> archivesTypeExs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		IntegerEx count = new IntegerEx();
		try {
			//�������ע��
			pErrPos = 1;
		
			pErrPos = 1;
			if (archivesInfoWorkingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�����鵵�������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (pFlag) {
				if (userIDs == null) {
					pFlag = false;
					pErrInfo.getContent().append("�û����δ��ֵ");
				}
			}

			if (pFlag) {
				if (archivesTypeExs == null) {
					pFlag = false;
					pErrInfo.getContent().append("Ҫͳ�Ƶĵ���������Ϣδ��ֵ");
				}
			}
			
		   for (ArchivesTypeEx archivesTypeEx : archivesTypeExs.values()) {
			   //����DAOͳ���ƽ�δͨ��������
			   if (pFlag) {
				   if (archivesInfoWorkingDao.statArchivesInfoCountByWorkFlowStatus(userIDs, archivesTypeEx, EnumWorkFlowStatus.���������ҽ�����˴��,userType,count, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ͳ�ƽ������δͨ��������ʧ��");
					}else{
						archivesTypeEx.setPaperCheckBackArchivesInfoCount(count.getValue());
					}
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
	public boolean archivesBoxing(List<String> barcodes,String archivesBoxBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
	
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//���ÿⷿDAO��
				if(storeroomArchivesInfoDao.updateArchivesBoxBarcode(barcodes, archivesBoxBarcode, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���¿ⷿ������Ϣ������ʧ��");
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
	public boolean findArchivesBoxLabel(String archivesBoxBarcode, ArchivesBoxLabel archivesBoxLabel, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<String> archivesIDs = new ArrayList<String>();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//����DAO�õ����ڱ�ǩ��ֹ��Χ
//			if (pFlag) {
//				pErrPos = 2;
//				if(storeroomArchivesInfoDao.findArchivesIDByBoxBarcode(archivesBoxBarcode, archivesIDs, pErrInfo) == false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "���Һ��ڵ���ʧ��");
//				}
//			}
			
			if (pFlag) {
				archivesBoxLabel.setMinArchivesID(archivesIDs.get(0));
				for (String archivesID : archivesIDs) {
					archivesBoxLabel.setMaxArchivesID(archivesID);
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
	public boolean pasteArchivesBarcode(String archivesID, ArchivesType archivesType, String barcode, ArchivesInfo archivesInfo,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				pErrPos = 2;
				if(archivesInfoSavedManageService.findByArchivesID(archivesID, archivesType,archivesInfo,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ������Ϣʧ��");
				}
			}
			
			if (pFlag) {
				if (archivesInfo.getNBXH() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("û�д˾���");
				}
			}
			
			StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
			if (pFlag) {
				if (storeroomArchivesInfoDao.find(archivesInfo.getNBXH(), archivesType.getID(),storeroomArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�ⷿ������Ϣʧ��");
				}
			}
			
			//��ʼ����2...
			if (pFlag) {
				if (pFlag) {
					pErrPos = 2;
					if(archivesInfoSavedManageService.updateArchivesBarcode(archivesID, archivesType,barcode,pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "���µ�������ʧ��");
					}
				}
				
				if (storeroomArchivesInfo.getNBXH() == 0) {
					if (pFlag) {
						pErrPos = 3;
						if (storeroomArchivesInfoDao.add(barcode, archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "�����ⷿ������Ϣʧ��");
						}
					}
				}else{
					if (pFlag) {
						pErrPos = 3;
						if (storeroomArchivesInfoDao.updateBarCode(barcode,storeroomArchivesInfo.getNBXH(), archivesType, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "���¿ⷿ������Ϣ����ʧ��");
						}
					}
				}
			}
				
			if (pFlag) {
				archivesInfo.setBarcode(barcode);
			}
//			if (pFlag) {
//				pErrPos = 4;
//				if(archivesInfoSavedManageService.findByBarcode(archivesType,barcode,archivesInfo,pErrInfo) == false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "��ѯ������Ϣʧ��");
//				}
//			}
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
	public boolean findTopArchivesByBatNoWhitOutBarcode(String batNo, int archivesTypeID, ArchivesInfo archivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferBatchesDetailsDao.findTopArchivesByBatNoWhitOutBarcode(batNo,archivesTypeID,archivesInfo,pErrInfo) == false) {
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
}
