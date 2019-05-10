/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IAppraisalKeepDestroyDetailDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��ټ�����ϸ����������ʵ����
 * @author tyb
 *
 */
public class AppraisalKeepDestroyDetailManageServiceImpl implements IAppraisalKeepDestroyDetailManageService {
	
	/**
	 * ע���ټ�����ϸ������DAO
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailDao appraisalKeepDestroyDetailDao;
	
	/**
	 * ���캯��
	 */
	public AppraisalKeepDestroyDetailManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public AppraisalKeepDestroyDetailManageServiceImpl(IAppraisalKeepDestroyDetailDao appraisalKeepDestroyDetailDao) {
		this.appraisalKeepDestroyDetailDao = appraisalKeepDestroyDetailDao;
	}
	
	/**
	 * ����ټ�����ϸ������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForAppraisalKeepDestroyDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (appraisalKeepDestroyDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("��ټ�����ϸ������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#deleteAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#findAppraisalKeepDestroyDetailByID(int, com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalKeepDestroyDetailByID(int pID, AppraisalKeepDestroyDetail appraisalKeepDestroyDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//DoSomething
			if (pFlag) {
				if (appraisalKeepDestroyDetail == null) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "id�Ƿ�Ϊ�ա�");
				}
			}
			
			//���id�Ƿ�Ϊ��
			if (pFlag) {
				if (pID <=0 ) {
					pFlag = false;
					pErrInfo.getContent().append("id�Ƿ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��Ψһ��ʶ���Ҵ�ټ�����ϸ���
				if (appraisalKeepDestroyDetailDao.findByID(pID, appraisalKeepDestroyDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��Ψһ��ʶ���Ҵ�ټ�����ϸ��� ʧ�ܣ�");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#findAppraisalKeepDestroyDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalKeepDestroyDetails(
			List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#saveAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService#updateAppraisalKeepDestroyDetail(com.orifound.aiim.entity.AppraisalKeepDestroyDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalKeepDestroyDetail(
			AppraisalKeepDestroyDetail appraisalKeepDestroyDetail,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatch(UserInfo userInfo, ArchivesType archivesType, Map<Integer, Map<String, String>> batchArchives, Map<String, String> opinion, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����û���Ϣ�Ƿ�Ϊ��
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
			
			//�������������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (batchArchives==null || batchArchives.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				if (appraisalKeepDestroyDetailDao.saveBatch(userInfo, archivesType, batchArchives, opinion, pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ӵ�ټ�����ϸ��� ʧ�ܣ�");
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
	public boolean findWithPage(Map<String, String> params,
			DataPageInfo dataPageInfo,
			List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalKeepDestroyDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�жϷ�ҳ�����Ƿ�Ϊ��
			if (pFlag) {
				if(dataPageInfo == null || dataPageInfo.getPageSize()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("��������ҳ��Ƿ�Ϊ�ա�");
				}
			}
			
			//�жϴ�ټ�����¼���ϲ���
			if (pFlag) {
				if (appraisalKeepDestroyDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("��������ټ�����¼����Ϊ�ա�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//��ҳ��ѯ��ټ����Ǽ���Ϣ
				
				//�ж��Ƿ��е�����������	��ѯ��һ�����������µ����е�������id
				String archivesTypeId = params.get("archivesTypeId");
				List<Integer> archivesTypeIds = null;
				if(StringTool.checkNull(archivesTypeId)) {
					archivesTypeIds = new ArrayList<Integer>();
					CommonUtil.getAllChildArchivesTypeId(archivesTypeIds, Integer.valueOf(""+archivesTypeId));
				}
				
				if (appraisalKeepDestroyDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalKeepDestroyDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ҳ��ѯ��ټ����Ǽ���Ϣ ʧ�ܣ�");
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
