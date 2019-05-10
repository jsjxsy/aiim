package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightSystemFeature;

public class UserRightSystemFeatureManageDWR {
	
	private IUserRightSystemFeatureManageService userRightSystemFeatureManageService;
	
	public IUserRightSystemFeatureManageService getUserRightSystemFeatureManageService() {
		return userRightSystemFeatureManageService;
	}

	public void setUserRightSystemFeatureManageService(IUserRightSystemFeatureManageService userRightSystemFeatureManageService) {
		this.userRightSystemFeatureManageService = userRightSystemFeatureManageService;
	}

	/**
	 * �޸�ָ���û����û���ϵͳ����Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRightSystemFeature(Integer[] IDS,int pUserID,HttpSession session ) throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRightSystemFeature> userRightSystemFeatures = new ArrayList<UserRightSystemFeature>();
		
			for (int i = 0; i < IDS.length; i++) {
				UserRightSystemFeature userRightSystemFeature = new UserRightSystemFeature();
				userRightSystemFeature.setUserID(pUserID);
				userRightSystemFeature.setFeatureID(IDS[i]);
				userRightSystemFeatures.add(userRightSystemFeature);
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRightSystemFeatureManageService.deleteRightSystemFeaturesByUserID(pUserID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					UserInfo userInfo = new UserInfo();
					userInfo.setUserID(pUserID);
					if (userRightSystemFeatureManageService.saveRightSystemFeaturesForUser(userInfo, userRightSystemFeatures, pErrInfo) == false) {
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
