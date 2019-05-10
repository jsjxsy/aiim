package com.orifound.aiim.web.struts;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;



import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

public class UserRoleManageDWR{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IUserRoleManageService userRoleManageService;
	
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}
	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	
	/**
	 * ����û�
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRole(Map<String,String> formMap,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole = new UserRole();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (this.getUserRole(formMap, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append( "��ȡ��ɫʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (userRoleManageService.saveUserRole(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���н�ɫʧ�ܣ�");
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
	
	/**��ȡuserinfo����
	 * @param formMap
	 * @param userInfo
	 * @param pErrInfo
	 * @return
	 */
	private boolean getUserRole(Map<String,String> formMap, UserRole userRole,ErrInfo  pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if(key.equals("iD")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setID(Integer.parseInt((formMap.get(key))));
				}
				if(key.equals("name")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setName(formMap.get(key));
				}
				if(key.equals("remark")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setRemark(formMap.get(key));
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
	
	public boolean updateUserRole(Map<String,String> formMap,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole = new UserRole();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (this.getUserRole(formMap, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append( "��ȡ��ɫʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				if (userRole.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ�գ�");
				}
				
				if (userRole.getName() == null || userRole.getName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ���ƷǷ�Ϊ�գ�");
				}
				
				
				if (userRole.getRemark() == null || userRole.getRemark().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ע�Ƿ�Ϊ�գ�");
				}
				
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRoleManageService.updateUserRole(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("�޸Ľ�ɫʧ�ܣ�");
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delUserRoles(Integer[] IDS,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole= null;
		try {
			//��ʼ���� 1...
			//����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			pErrPos = 1;
			if (pFlag) {
				for(int i = 0;i<IDS.length;i++){
					userRole = new UserRole();
					userRole.setID(IDS[i]);
					if(userRoleManageService.deleteUserRole(userRole, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "ɾ����ɫʧ�ܣ�");
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
}
