package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ISystemFeatureManageService;
import com.orifound.aiim.bll.service.IUserRolesArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IUserRolesSystemFeatureManageService;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;
import com.orifound.aiim.entity.UserRolesSystemFeature;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRolesSystemFeatureManageAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRolesSystemFeatureManageService userRolesSystemFeatureManageService;
	


	public IUserRolesSystemFeatureManageService getUserRolesSystemFeatureManageService() {
		return userRolesSystemFeatureManageService;
	}



	public void setUserRolesSystemFeatureManageService(IUserRolesSystemFeatureManageService userRolesSystemFeatureManageService) {
		this.userRolesSystemFeatureManageService = userRolesSystemFeatureManageService;
	}

	private ISystemFeatureManageService systemFeatureManageService;

	public ISystemFeatureManageService getSystemFeatureManageService() {
		return systemFeatureManageService;
	}



	public void setSystemFeatureManageService(ISystemFeatureManageService systemFeatureManageService) {
		this.systemFeatureManageService = systemFeatureManageService;
	}

	
	
	private IUserRolesArchivesSecrecyManageService userRolesArchivesSecrecyManageService;

	public IUserRolesArchivesSecrecyManageService getUserRolesArchivesSecrecyManageService() {
		return userRolesArchivesSecrecyManageService;
	}



	public void setUserRolesArchivesSecrecyManageService(IUserRolesArchivesSecrecyManageService userRolesArchivesSecrecyManageService) {
		this.userRolesArchivesSecrecyManageService = userRolesArchivesSecrecyManageService;
	}



	/**
	 * ��ȡ�û�����������Ȩ��
	 */
	public String getUserRolesSystemFeatureTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserRolesSystemFeature> pUserRolesSystemFeatures  = new  ArrayList<UserRolesSystemFeature>();
		LinkedHashMap<String, SystemFeature> srcSystemFeatures = new  LinkedHashMap<String, SystemFeature>();
		List<UserRolesArchivesSecrecy> distArchivesSecrecys = new  ArrayList<UserRolesArchivesSecrecy>();//��ȡָ���û��ĵ�������Ȩ��
		HttpServletRequest request = ServletActionContext.getRequest();
		int pRoleID = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
		String roleID =request.getParameter("RoleID");
		pRoleID=Integer.parseInt(roleID);
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				
				if (userRolesSystemFeatureManageService.findUserRolesSystemFeatureByRoleID(pRoleID, pUserRolesSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					result = "error";
				}
				
				pErrPos = 3;
				if (systemFeatureManageService.findSystemFeatures(srcSystemFeatures, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�����û��ĵ�������Ȩ��ʧ��:");
					result = "error";
				}
				String tree = CreateTreeUtil.getUserRolesSystemFeatureCheckBoxTree(srcSystemFeatures,pUserRolesSystemFeatures);
				request.setAttribute("tree", tree);
				
				
				request.setAttribute("type", "userrole");
				request.setAttribute("RoleID", pRoleID);
				
				if (pFlag) {
					if (userRolesArchivesSecrecyManageService.findUserRolesArchivesSecrecyByRoleID(pRoleID, distArchivesSecrecys, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����Ψһ��ʶ����UserRolesArchivesSecrecy��Ϣʧ�ܣ�");
					}
				}
				
				request.setAttribute("distArchivesSecrecys", distArchivesSecrecys);
				LinkedHashMap<Integer, ArchivesSecrecy> srcArchivesSecrecys = SystemInitializer.getInstance().getArchivesSecrecys();
				request.setAttribute("srcArchivesSecrecys",srcArchivesSecrecys);
				result = "success";
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
	
	/**
	 * �޸�ָ����ɫ�ĵ�������Ȩ��
	 * @return
	 * @throws Exception
	 */
	public String modifyUserRolesSystemFeature() throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			int pRoleID = 0;
			HttpServletRequest request = ServletActionContext.getRequest();
			
			if (pFlag) {
				pRoleID = Integer.parseInt(request.getParameter("RoleID"));
			}
			String[] IDS = request.getParameterValues("ID");
			List<UserRolesSystemFeature> userRolesSystemFeatures = new ArrayList<UserRolesSystemFeature>();
			for (int i = 0; i < IDS.length; i++) {
				int featureID=Integer.parseInt(IDS[i]);
				UserRolesSystemFeature userRolesSystemFeature = new UserRolesSystemFeature();
				userRolesSystemFeature.setRolesID(pRoleID);
				userRolesSystemFeature.setFeatureID(featureID);
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
		return SUCCESS;
	}
	
}
