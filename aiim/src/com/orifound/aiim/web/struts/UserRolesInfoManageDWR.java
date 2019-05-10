package com.orifound.aiim.web.struts;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

public class UserRolesInfoManageDWR{

	@Autowired
	private IUserRolesInfoManageService userRolesInfoManageService;

	/**
	 * Ϊָ���Ľ�ɫ����û�
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRolesInfoByRoleID(Integer[] UserIDS,int roleID,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		System.out.println("userIDs[0]----->"+UserIDS[0]);
		System.out.println("roleID----->"+roleID);
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				for (int i = 0; i < UserIDS.length; i++) {
					UserRolesInfo userRolesInfo = new UserRolesInfo();
					userRolesInfo.setUserID(UserIDS[i]);
					userRolesInfo.setRolesID(roleID);
					if (userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����û���ɫ��Ϣʧ�ܣ�");
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return pFlag;
	}
	
	/**
	 * Ϊָ�����û���ӽ�ɫ
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRolesInfoByUserID(Integer[] RoleIDS, int userID, HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			// ��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				for (int i = 0; i < RoleIDS.length; i++) {
					UserRolesInfo userRolesInfo = new UserRolesInfo();
					userRolesInfo.setRolesID(RoleIDS[i]);
					userRolesInfo.setUserID(userID);
					if (userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�����û���ɫ��Ϣʧ�ܣ�");
					}
				}

			}

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return pFlag;
	}
	
	/**
	 * ɾ���û���ɫ��Ϣ
	 * @param ID
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean delUserRoles(int ID, HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRolesInfo userRolesInfo = new UserRolesInfo();
		try {
			if (pFlag) {
				userRolesInfo.setID(ID);
				if (userRolesInfoManageService.deleteUserRolesInfo(userRolesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ���û���ɫ��Ϣʧ�ܣ�");
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return pFlag;
	}
	
}
