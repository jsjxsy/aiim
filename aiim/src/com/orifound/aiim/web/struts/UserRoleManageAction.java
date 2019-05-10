package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import com.opensymphony.xwork2.ModelDriven;
import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.UserRolesInfo;

public class UserRoleManageAction extends ActionSupport implements ModelDriven<UserRole> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserRole userRole = new UserRole();
	@Override
	public UserRole getModel() {
		// TODO Auto-generated method stub
		return userRole;
	}

	private IUserRoleManageService userRoleManageService;
	
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}
	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	
	private IUserRolesInfoManageService userRolesInfoManageService;
	
	public IUserRolesInfoManageService getUserRolesInfoManageService() {
		return userRolesInfoManageService;
	}
	
	public void setUserRolesInfoManageService(IUserRolesInfoManageService userRolesInfoManageService) {
		this.userRolesInfoManageService = userRolesInfoManageService;
	}
	
	//���ܽ�ɫ�б�ID
	private int[] ids;
	
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	/**
	 * 
	 * @param dataPageInfo 
	 * @return
	 * @throws Exception
	 */
	public String findAllUserRoles() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
		
			if (userRoleManageService.findUserRoles(userRoles, pErrInfo)== false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ�û�ʧ��:");
			}else{
				request.setAttribute("userRoles", userRoles);
				result = "success";
			}
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delUserRoles() throws Exception {
		String result = "";
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
				for(int i = 0;i<ids.length;i++){
					userRole = new UserRole();
					userRole.setID(ids[i]);
					if(userRoleManageService.deleteUserRole(userRole, pErrInfo) == false){
						result = "error";
					}else{
						result = "success";
					}
				}
			}	
		} catch (Exception e) {
			result = "error";
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
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyUserRole() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int pID = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			String iD = request.getParameter("ID");
			if (pFlag) {
				if (iD == null || iD.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ��!");
				}else{
					pID = Integer.parseInt(iD);
				}
				
			}
			
			if (pFlag) {
				if (userRoleManageService.findUserRoleByID(pID, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݽ�ɫ��Ų�ѯ��ɫʧ�ܣ�");
				}
			}
			request.setAttribute("userRole", userRole);

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;

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
		return SUCCESS;
	}
	
	
	public String updateUserRole() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
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
		return SUCCESS;
	}
	
	/**
	 * ���߽�ɫ���Ҳ����û���ɫ��Ϣ���еĽ�ɫ��Ϣ
	 */
	public String findRoleInfosNotInUserID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		int pUserID = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			String UserID = request.getParameter("UserID");
			
			if (pFlag) {
				if (UserID == null || UserID.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("��ɫ��ŷǷ�Ϊ��!");
				}else{
					pUserID = Integer.parseInt(UserID);
				}
				
			}
			if (userRoleManageService.findRoleInfosNotInUserID(pUserID, userRoles, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ�û�ʧ��:");
			}else{
				request.setAttribute("userRoles", userRoles);
				request.setAttribute("UserID", pUserID);
				result = "success";
			}
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delUserRolesInfo() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();	
		UserRolesInfo userRolesInfo = new UserRolesInfo();
		List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
		int ID = 0;
		try {
			//��ʼ���� 1...
			//����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			pErrPos = 1;
			String iD = request.getParameter("ID");
			if (pFlag) {
				if (iD == null || iD.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ɫ��Ϣ��ŷǷ�Ϊ�գ�");
				} else {
					ID = Integer.parseInt(iD);
				}
			}
			if (pFlag) {
				userRolesInfo.setID(ID);
				if (userRolesInfoManageService.deleteUserRolesInfo(userRolesInfo, pErrInfo) == false) {
					System.out.println("ID: " + userRolesInfo.getID() + " ɾ��ʧ�ܣ�");
					result = "error";
				} 
			}
			
			int pUserID = Integer.parseInt(request.getParameter("UserID"));
			if (pFlag) {
				if (userRolesInfoManageService.findUserRolesInfosByUserID(pUserID, userRolesInfos, pErrInfo) == false) {
					result = "error";
				} 
			}
			request.setAttribute("userRolesInfos", userRolesInfos);
			result = "success";
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
		return result;
	}
}
