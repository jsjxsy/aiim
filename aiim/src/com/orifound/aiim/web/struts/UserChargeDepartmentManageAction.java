package com.orifound.aiim.web.struts;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

public class UserChargeDepartmentManageAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService;
	
	public IUserChargeDepartmentInfoManageService getUserChargeDepartmentInfoManageService() {
		return userChargeDepartmentInfoManageService;
	}

	public void setUserChargeDepartmentInfoManageService(IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService) {
		this.userChargeDepartmentInfoManageService = userChargeDepartmentInfoManageService;
	}

	public String deleteUserChargeDepartment() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int pID = 0;
		UserChargeDepartmentInfo userChargeDepartmentInfo = new UserChargeDepartmentInfo();
		LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos = new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			String ID = request.getParameter("ID");
			if (ID == null || ID.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("���ű�ŷǷ�Ϊ�գ�");
			} else {
				pID = Integer.parseInt(ID);
			}
			// ����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			if (pFlag) {
				userChargeDepartmentInfo.setID(pID);
				if (userChargeDepartmentInfoManageService.deleteUserChargeDepartmentInfo(userChargeDepartmentInfo, pErrInfo) == false) {
					System.out.println("DepartmentID: " + userChargeDepartmentInfo.getID() + " ɾ��ʧ�ܣ�");
					result = "error";
				}
			}

			int pUserID = Integer.parseInt(request.getParameter("UserID"));
			// ����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			if (pFlag) {
				if (userChargeDepartmentInfoManageService.findUserChargeDepartmentInfosByUserID(pUserID, userChargeDepartmentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ�û�������ʧ�ܣ�");
					result = "error";
				}
			}
			request.setAttribute("userChargeDepartmentInfos", userChargeDepartmentInfos);
			request.setAttribute("UserID", pUserID);
			result = "success";
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	
	public String findUserChargeDepartmentByUserID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		int pUserID = 0;
		LinkedHashMap<Integer, UserChargeDepartmentInfo> userChargeDepartmentInfos = new LinkedHashMap<Integer, UserChargeDepartmentInfo>();
		try {
			// ��ȡ�û�ID
			pErrPos = 1;
			String UserID = request.getParameter("UserID");
			if (UserID == null || UserID.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
			} else {
				pUserID = Integer.parseInt(UserID);
			}
			// ����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			if (pFlag) {
				if (userChargeDepartmentInfoManageService.findUserChargeDepartmentInfosByUserID(pUserID, userChargeDepartmentInfos, pErrInfo) == false) {
					result = "error";
				} else {
					request.setAttribute("userChargeDepartmentInfos", userChargeDepartmentInfos);
					request.setAttribute("UserID", pUserID);
					result = "success";
				}
			}
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	
	public String findUserUnChargeDepartmentByUserID() throws Exception {
		String result = "";	
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		int pUserID = 0;
		List<UserChargeDepartmentInfo> userChargeDepartmentInfos = new ArrayList<UserChargeDepartmentInfo>();
		try {
			//��ȡ�û�ID
			pErrPos = 1;	
			String UserID = request.getParameter("UserID");
			if (UserID == null || UserID.equals("")) {
			    pFlag = false;
			    pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
			}else{
				pUserID = Integer.parseInt(UserID);	
			}
			//����ҵ���߼���ɾ��ѡ�е��û���Ϣ
			if (pFlag) {
					if(userChargeDepartmentInfoManageService.findAllUserUnChargeDepartmentInfos(userChargeDepartmentInfos, pErrInfo) == false){
						result = "error";
					}else{
						request.setAttribute("userChargeDepartmentInfos", userChargeDepartmentInfos);
						request.setAttribute("UserID", pUserID);
						result = "success";
					}
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

}
