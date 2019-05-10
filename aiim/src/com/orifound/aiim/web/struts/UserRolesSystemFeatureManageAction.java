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
	 * 获取用户档案分类授权树
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
		List<UserRolesArchivesSecrecy> distArchivesSecrecys = new  ArrayList<UserRolesArchivesSecrecy>();//获取指定用户的档案分类权限
		HttpServletRequest request = ServletActionContext.getRequest();
		int pRoleID = 0;
		try {
			//开始处理 1...
			pErrPos = 1;
			
		String roleID =request.getParameter("RoleID");
		pRoleID=Integer.parseInt(roleID);
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				
				if (userRolesSystemFeatureManageService.findUserRolesSystemFeatureByRoleID(pRoleID, pUserRolesSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					result = "error";
				}
				
				pErrPos = 3;
				if (systemFeatureManageService.findSystemFeatures(srcSystemFeatures, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取所有用户的档案分类权限失败:");
					result = "error";
				}
				String tree = CreateTreeUtil.getUserRolesSystemFeatureCheckBoxTree(srcSystemFeatures,pUserRolesSystemFeatures);
				request.setAttribute("tree", tree);
				
				
				request.setAttribute("type", "userrole");
				request.setAttribute("RoleID", pRoleID);
				
				if (pFlag) {
					if (userRolesArchivesSecrecyManageService.findUserRolesArchivesSecrecyByRoleID(pRoleID, distArchivesSecrecys, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据唯一标识查找UserRolesArchivesSecrecy信息失败：");
					}
				}
				
				request.setAttribute("distArchivesSecrecys", distArchivesSecrecys);
				LinkedHashMap<Integer, ArchivesSecrecy> srcArchivesSecrecys = SystemInitializer.getInstance().getArchivesSecrecys();
				request.setAttribute("srcArchivesSecrecys",srcArchivesSecrecys);
				result = "success";
			}
		} catch (Exception e) {
			//异常错误
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return result;
	}
	
	/**
	 * 修改指定角色的档案分类权限
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
			//开始处理 1...
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
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				//先删除已有所有的档案分类权限记录
					if (userRolesSystemFeatureManageService.deleteUserRolesSystemFeaturesByRoleID(pRoleID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					}
					if (userRolesSystemFeatureManageService.saveUserRolesSystemFeatures(userRolesSystemFeatures, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					}
				}
				
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
}
