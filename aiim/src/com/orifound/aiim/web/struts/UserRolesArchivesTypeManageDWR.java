package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.orifound.aiim.bll.service.IUserRolesArchivesTypeManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesType;

public class UserRolesArchivesTypeManageDWR{

	
	private IUserRolesArchivesTypeManageService  userRolesArchivesTypeManageService;
	

	public IUserRolesArchivesTypeManageService getUserRolesArchivesTypeManageService() {
		return userRolesArchivesTypeManageService;
	}


	public void setUserRolesArchivesTypeManageService(IUserRolesArchivesTypeManageService userRolesArchivesTypeManageService) {
		this.userRolesArchivesTypeManageService = userRolesArchivesTypeManageService;
	}




	/**
	 * �޸�ָ����ɫ�ĵ�������Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRolesArchivesType(Integer[] ArchivesTypeIDS,int pRoleID,HttpSession session ) throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRolesArchivesType> userRolesArchivesTypes = new ArrayList<UserRolesArchivesType>();
			for (int i = 0; i < ArchivesTypeIDS.length; i++) {
					UserRolesArchivesType userRolesArchivesType = new UserRolesArchivesType();
					userRolesArchivesType.setRolesID(pRoleID);
					userRolesArchivesType.setArchivesTypeID(ArchivesTypeIDS[i]);
					userRolesArchivesTypes.add(userRolesArchivesType);
			}
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRolesArchivesTypeManageService.deleteUserRolesArchivesTypeByRoleID(pRoleID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					if (userRolesArchivesTypeManageService.saveUserRolesArchivesTypes(userRolesArchivesTypes, pErrInfo) == false) {
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
