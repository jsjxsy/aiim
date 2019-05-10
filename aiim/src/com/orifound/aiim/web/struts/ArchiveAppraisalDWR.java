package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService;
import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �������� DWR
 *
 */
public class ArchiveAppraisalDWR {
	
	/**
	 * ע�뵵�����ֿ��Ƽ�����ϸ������ʵ�������������
	 */
	@Autowired
	private IAppraisalUseScopesDetailManageService appraisalUseScopesDetailManageService;
	
	/**
	 * ע�뵵���鵵��Ϣ�Ĺ���������
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * ע�뵵������/���ż�����ϸ������ʵ�������������
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * ���ݵ����ڲ���Ų��ҵ���������ϸ��Ϣ
	 * @param NBXH �ڲ����
	 * @return AppraisalUseScopesDetail  ����������ϸ��Ϣ
	 */ 
	public AppraisalUseScopesDetail findAppraisalUseScopesDetailByByNBXH(int archivesTypeID, int NBXH ,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		AppraisalUseScopesDetail appraisalUseScopesDetail = null;
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo ) == false) {
				pFlag = false;
			}

			//���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ
			if (pFlag) {
				pErrPos = 2;
				appraisalUseScopesDetail = new AppraisalUseScopesDetail();
				if (appraisalUseScopesDetailManageService.findAppraisalUseScopesDetailByByNBXH(archivesTypeID, NBXH, appraisalUseScopesDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ ʧ�ܣ�");
				}
			}
			
			//��������ڻ��ؼ�¼ ���ؿ�
			if(appraisalUseScopesDetail.getID() <=0) {
				appraisalUseScopesDetail = null;
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
		System.out.println(pErrInfo.toString());
		return appraisalUseScopesDetail;
	}
	
	/**
	 * ��������->���ؼ����Ǽ�
	 * @param NBXH �ڲ����
	 * @param archivesTypeID ��������id
	 * @param appraisalReason ����ԭ��
	 * @param appraisalDate ��������
	 * @param appraisalPersion ������
	 * @param managerUserID ������id
	 * @param roleIds ���ؽ�ɫid����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean controlAreaRegister(int NBXH,int archivesTypeID,String appraisalReason,String appraisalDate,String appraisalPersion,int managerUserID,Integer[] roleIds,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}

			ArchivesInfo archivesInfo = new ArchivesInfo();
			
			//��ѯ������Ϣ
			if (pFlag) {
				pErrPos = 2;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(archivesTypeID);
				
				//�����ڲ���Ų��ҵ�����Ϣ
				if (archivesInfoSavedManageService.findByNBXH(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ�����Ϣ	ʧ�ܣ�");
				}
			}
			
			//���滮�ؼ�����ϸ��Ϣ
			if (pFlag) {
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(appraisalDate);
				AppraisalUseScopesDetail appraisalUseScopesDetail = new AppraisalUseScopesDetail(archivesTypeID, NBXH, archivesInfo.getArchivesID(),
						archivesInfo.getTitle(), appraisalReason, date, null, appraisalPersion, managerUserID, null);
				//���õ����γɲ���
				appraisalUseScopesDetail.setFormationDepartmentID(archivesInfo.getFormationDepartmentID());
				//�������ԣ����ؽ�ɫid���� 
				appraisalUseScopesDetail.setRoleIds(Arrays.asList(roleIds));
				
				if (appraisalUseScopesDetailManageService.saveOrUpdateAppraisalUseScopesDetail(appraisalUseScopesDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���һ���µĵ������ֿ��Ƽ�����ϸ������ʵ���� ʧ�ܣ�");
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
	
	/**
	 * ���ݻ��ؼ�����ȡ��Ȩ�Ľ�ɫ�����б�
	 * @param AppraisalUseScopesDetailsId
	 * @return List<String> ���ؽ�ɫ���Ƽ���
	 */
	public List<String> findRoleNamesByControlArea(int AppraisalUseScopesDetailsId,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<String> roleNames = null;
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ����
			if (pFlag) {
				pErrPos = 2;
				roleNames = new ArrayList<String>();
				if (appraisalUseScopesDetailManageService.findRoleNamesById(AppraisalUseScopesDetailsId, roleNames , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR �����������ؼ�����Ϣ ����id��ȡ��Ȩ�����н�ɫ���� ʧ�ܣ�");
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
		if(roleNames!=null && roleNames.size()>=1) {
			return roleNames;
		} else {
			return null;
		}
	}
	
	/**
	 * ȡ������/��������
	 * @param archivesTypeID ��������id
	 * @param NBXH	�����ڲ����
	 * @param publicFlag ���ű�־
	 * @return boolean �����Ƿ�����ɹ�
	 */
	public boolean cancelPublicOpenRegister(int archivesTypeID, int NBXH, int publicFlag,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForAppraisalUseScopesDetail(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(archivesTypeID);
				if (appraisalPublicDetailManageService.deleteAppraisalPublicDetail(archivesType, NBXH, publicFlag, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR ��������->ȡ������/�������� ɾ��ָ���������ͺ��ڲ���ŵĵ������ż�����Ϣ ʧ�ܣ�");
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

		System.out.println(pErrInfo.toString());
		return pFlag;
	}
	
	/**
	 * ��鵵�����ֿ��Ƽ�����ϸ������ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForAppraisalUseScopesDetail(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (appraisalUseScopesDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�������ֿ��Ƽ�����ϸ������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 2;
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�����鵵��Ϣ���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			pErrPos = 3;
			if (appraisalPublicDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"��������/���ż�����ϸ������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
}