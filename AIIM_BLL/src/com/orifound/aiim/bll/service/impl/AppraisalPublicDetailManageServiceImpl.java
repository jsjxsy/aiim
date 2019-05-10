/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IAppraisalPublicDetailDao;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;

/**
 * ��������/���ż�����ϸ������ʵ������������
 *
 */
public class AppraisalPublicDetailManageServiceImpl implements IAppraisalPublicDetailManageService {
	
	/**
	 * ע�뵵������/����������DAO
	 */
	@Autowired
	private IAppraisalPublicDetailDao appraisalPublicDetailDao;
	
	/**
	 * ���캯��
	 */
	public AppraisalPublicDetailManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public AppraisalPublicDetailManageServiceImpl(IAppraisalPublicDetailDao appraisalPublicDetailDao) {
		this.appraisalPublicDetailDao = appraisalPublicDetailDao;
	}
	
	/**
	 * ��鵵������/�����������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForAppraisalPublicDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (appraisalPublicDetailDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("��������/�����������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#deleteAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteAppraisalPublicDetail(
			AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#findAppraisalPublicDetailByID(int, com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalPublicDetailByID(int pID, int publicFlag, AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���id�Ƿ�Ϊ��
			if (pFlag) {
				if (pID<=0) {
					pFlag = false;
					pErrInfo.getContent().append("id�����Ƿ�Ϊ�ա�");
				}
			}
			
			//��⵵������/���ż�����ϸ������ʵ������Ƿ�Ϊ��
			if (pFlag) {
				if (appraisalPublicDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("��������/���ż�����ϸ������ʵ�����Ƿ�Ϊ�ա�");
				}
			}
			

			if (pFlag) {
				pErrPos = 2;
				//����Ψһ��ʶ���ҵ�������/����������Ϣ
				if (appraisalPublicDetailDao.findByID(pID, publicFlag, appraisalPublicDetail, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Service ����Ψһ��ʶ���ҵ�������/����������Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#findAppraisalPublicDetails(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAppraisalPublicDetails(
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#saveAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveAppraisalPublicDetail(
			AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#saveBatchForPublicAppraisal(com.orifound.aiim.entity.UserInfo, com.orifound.aiim.entity.ArchivesType, java.util.List, java.util.Map, java.lang.String, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveBatchForPublicAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			Map<String, String> opinion, String isPublic, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
			
			//������������ڲ������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (archivesNBXHs==null || archivesNBXHs.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("���������ڲ������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				if ("0".equals(isPublic)) {
					//��������->���ż��� ������ӵ������ż�����Ϣ
					if (appraisalPublicDetailDao.saveBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, opinion,  pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Service ��������->���ż��� ������ӵ������ż�����Ϣ ʧ�ܣ�");
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
	 * @see com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService#updateAppraisalPublicDetail(com.orifound.aiim.entity.AppraisalPublicDetail, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateAppraisalPublicDetail( AppraisalPublicDetail appraisalPublicDetail, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean saveBatchForOpenAppraisal(UserInfo userInfo,
			ArchivesType archivesType, List<Integer> archivesNBXHs,
			Map<String, String> opinion, String isOpen, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
			
			//������������ڲ������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (archivesNBXHs==null || archivesNBXHs.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("���������ڲ������Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 4;
				
				if("0".equals(isOpen)) {
					//��������->�������� ������ӵ������ż�����Ϣ
					if (appraisalPublicDetailDao.saveBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, opinion, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Service ��������->�������� ������ӵ������ż�����Ϣ ʧ�ܣ�");
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
	public boolean findWithPage(Map<String, String> params, DataPageInfo dataPageInfo,
			List<AppraisalPublicDetail> appraisalPublicDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
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
				if (appraisalPublicDetails == null) {
					pFlag = false;
					pErrInfo.getContent().append("���������ż�����¼����Ϊ�ա�");
				}
			}
			
			//���ҳ���ѯ�����Ƿ�Ϊ��
			if (pFlag) {
				if (params == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ҳ���ѯ����Ϊ�ա�");
				}
			}
			
			//��鿪�ű�־�Ƿ�Ϊ��
			if (pFlag) {
				String PublicFlag = params.get("PublicFlag");
				if (StringTool.checkNull(PublicFlag) == false || PublicFlag.matches("[0-1]{1}")==false) {
					pFlag = false;
					pErrInfo.getContent().append("ҳ���ѯ���������ű�־�Ƿ�Ϊ�ջ�����0��1�����ֵ��");
				}
			}
			
			if (pFlag) {
				pErrPos = 2;
				//��ҳ��ѯ���ż����Ǽ���Ϣ
				
				//�ж��Ƿ��е�����������	��ѯ��һ�����������µ����е�������id
				String archivesTypeId = params.get("archivesTypeId");
				List<Integer> archivesTypeIds = null;
				if(StringTool.checkNull(archivesTypeId)) {
					archivesTypeIds = new ArrayList<Integer>();
					CommonUtil.getAllChildArchivesTypeId(archivesTypeIds, Integer.valueOf(""+archivesTypeId));
				}
				
				if (appraisalPublicDetailDao.findWithPage(archivesTypeIds, params, dataPageInfo, appraisalPublicDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ҳ��ѯ���ż����Ǽ���Ϣ ʧ�ܣ�");
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
	public boolean deleteAppraisalPublicDetail(ArchivesType archivesType, int NBXH, int publicFlag, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForAppraisalPublicDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��鵵������id
			if (pFlag) {
				if (archivesType==null || archivesType.getID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�������ͷǷ�Ϊ�գ�");
				}
			}
			
			//��鵵���ڲ����
			if (pFlag) {
				if (NBXH <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ�գ�");
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
				if (appraisalPublicDetailDao.delete(archivesType, NBXH, publicFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��������->ȡ���������� ɾ��ָ���������ͺ��ڲ���ŵĵ�������������Ϣ ʧ�ܣ�");
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
