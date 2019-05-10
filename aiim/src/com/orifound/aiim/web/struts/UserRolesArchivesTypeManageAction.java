package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserRolesArchivesTypeManageService;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRolesArchivesType;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRolesArchivesTypeManageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRolesArchivesTypeManageService  userRolesArchivesTypeManageService;
	

	public IUserRolesArchivesTypeManageService getUserRolesArchivesTypeManageService() {
		return userRolesArchivesTypeManageService;
	}


	public void setUserRolesArchivesTypeManageService(IUserRolesArchivesTypeManageService userRolesArchivesTypeManageService) {
		this.userRolesArchivesTypeManageService = userRolesArchivesTypeManageService;
	}



	/**
	 * ��ȡ�û�����������Ȩ��
	 */
	public String getUserRolesArchivesTypeTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserRolesArchivesType> pUserRolesArchivesTypes = new  ArrayList<UserRolesArchivesType>();//��ȡָ���û��ĵ�������Ȩ��
		HttpServletRequest request = ServletActionContext.getRequest();
		int pRoleID = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
		String RoleID =request.getParameter("RoleID");
		pRoleID=Integer.parseInt(RoleID);
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				
				if (userRolesArchivesTypeManageService.findUserRolesArchivesTypeByRoleID(pRoleID, pUserRolesArchivesTypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					result = "error";
				}else {
					//��״�ṹ�ĵ������༯��
					LinkedHashMap<Integer, ArchivesType> srcArchivestypes=SystemInitializer.getInstance().getArchivesTypes();
					String tree = CreateTreeUtil.getUserRolesArchivesTypeCheckBoxTree(srcArchivestypes,pUserRolesArchivesTypes);
					request.setAttribute("tree", tree);
					request.setAttribute("RoleID", RoleID);
					result = "success";
				}

			}
		} catch (Exception e) {
			//�쳣����
			result = "error";
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
		return result;
	}
	
}
