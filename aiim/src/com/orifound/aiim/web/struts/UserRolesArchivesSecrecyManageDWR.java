package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserRolesArchivesSecrecyManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;

public class UserRolesArchivesSecrecyManageDWR extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRolesArchivesSecrecyManageService  userRolesArchivesSecrecyManageService ;
	
	public IUserRolesArchivesSecrecyManageService getUserRolesArchivesSecrecyManageService() {
		return userRolesArchivesSecrecyManageService;
	}


	public void setUserRolesArchivesSecrecyManageService(IUserRolesArchivesSecrecyManageService userRolesArchivesSecrecyManageService) {
		this.userRolesArchivesSecrecyManageService = userRolesArchivesSecrecyManageService;
	}



	/**
	 * �޸�ָ����ɫ�ĵ�������Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRolesArchivesSecrecy(Integer[] IDS,int RoleID,HttpSession session ) throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRolesArchivesSecrecy> userRolesArchivesSecrecys = new ArrayList<UserRolesArchivesSecrecy>();
			for (int i = 0; i < IDS.length; i++) {
				UserRolesArchivesSecrecy userRolesArchivesSecrecy = new UserRolesArchivesSecrecy();
				userRolesArchivesSecrecy.setRolesID(RoleID);
				userRolesArchivesSecrecy.setSecrecyID(IDS[i]);
				userRolesArchivesSecrecys.add(userRolesArchivesSecrecy);
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRolesArchivesSecrecyManageService.deleteUserRolesArchivesSecrecyByRoleID(RoleID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					if (userRolesArchivesSecrecyManageService.saveUserRolesArchivesSecrecyS(userRolesArchivesSecrecys, pErrInfo) == false) {
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
