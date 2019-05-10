package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.orifound.aiim.bll.service.IUserRolesSystemFeatureManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesSystemFeature;

public class UserRolesSystemFeatureManageDWR {

	private IUserRolesSystemFeatureManageService userRolesSystemFeatureManageService;
	
	public IUserRolesSystemFeatureManageService getUserRolesSystemFeatureManageService() {
		return userRolesSystemFeatureManageService;
	}

	public void setUserRolesSystemFeatureManageService(IUserRolesSystemFeatureManageService userRolesSystemFeatureManageService) {
		this.userRolesSystemFeatureManageService = userRolesSystemFeatureManageService;
	}

	/**
	 * �޸�ָ����ɫ�ĵ�������Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRolesSystemFeature(Integer[] IDS,int pRoleID, HttpSession session) throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRolesSystemFeature> userRolesSystemFeatures = new ArrayList<UserRolesSystemFeature>();
			for (int i = 0; i < IDS.length; i++) {
				UserRolesSystemFeature userRolesSystemFeature = new UserRolesSystemFeature();
				userRolesSystemFeature.setRolesID(pRoleID);
				userRolesSystemFeature.setFeatureID(IDS[i]);
				userRolesSystemFeatures.add(userRolesSystemFeature);
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRolesSystemFeatureManageService.deleteUserRolesSystemFeaturesByRoleID(pRoleID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					if (userRolesSystemFeatureManageService.saveUserRolesSystemFeatures(userRolesSystemFeatures, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
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
