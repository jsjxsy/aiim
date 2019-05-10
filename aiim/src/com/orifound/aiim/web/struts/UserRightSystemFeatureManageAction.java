package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ISystemFeatureManageService;
import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRightSystemFeatureManageAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRightSystemFeatureManageService userRightSystemFeatureManageService;
	


	public IUserRightSystemFeatureManageService getUserRightSystemFeatureManageService() {
		return userRightSystemFeatureManageService;
	}



	public void setUserRightSystemFeatureManageService(IUserRightSystemFeatureManageService userRightSystemFeatureManageService) {
		this.userRightSystemFeatureManageService = userRightSystemFeatureManageService;
	}

	private ISystemFeatureManageService systemFeatureManageService;
	
	public ISystemFeatureManageService getSystemFeatureManageService() {
		return systemFeatureManageService;
	}


	public void setSystemFeatureManageService(ISystemFeatureManageService systemFeatureManageService) {
		this.systemFeatureManageService = systemFeatureManageService;
	}
	
	
	private IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService; 

	public IUserRightArchivesSecrecyManageService getUserRightArchivesSecrecyManageService() {
		return userRightArchivesSecrecyManageService;
	}



	public void setUserRightArchivesSecrecyManageService(IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService) {
		this.userRightArchivesSecrecyManageService = userRightArchivesSecrecyManageService;
	}

	/**
	 * 获取用户系统功能授权树
	 */
	public String getUserRightSystemFeatureTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		LinkedHashMap<String, SystemFeature> distSystemFeatures = new LinkedHashMap<String, SystemFeature>();// 获取指定用户的档案分类权限
		LinkedHashMap<String, SystemFeature> srcSystemFeatures = new LinkedHashMap<String, SystemFeature>();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRightArchivesSecrecy> distArchivesSecrecys = new ArrayList<UserRightArchivesSecrecy>();
		int pUserID = 0;
		try {
			// 开始处理 1...
			pErrPos = 1;

			String userID = request.getParameter("UserID");
			pUserID = Integer.parseInt(userID);
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureManageService.findRightSystemFeaturesByUserID(pUserID, distSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定用户的系统功能权限失败:");
					result = "error";
				}

				pErrPos = 3;
				if (systemFeatureManageService.findSystemFeatures(srcSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取所有用户的系统功能权限失败:");
					result = "error";
				}

			}

			String tree = CreateTreeUtil.getUserRightSystemFeatureCheckBoxTree(srcSystemFeatures, distSystemFeatures);
			request.setAttribute("tree", tree);
			request.setAttribute("UserID", pUserID);
			request.setAttribute("type", "user");

			// 获取指定用户的档案密级权限
			if (pFlag) {
				if (userRightArchivesSecrecyManageService.findRightArchivesSecrecysByUserID(pUserID, distArchivesSecrecys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定用户的档案密级权限失败：");
				}
			}
			request.setAttribute("distArchivesSecrecys", distArchivesSecrecys);
			LinkedHashMap<Integer, ArchivesSecrecy> srcArchivesSecrecys=SystemInitializer.getInstance().getArchivesSecrecys();//获取所有的档案密级权限
			request.setAttribute("srcArchivesSecrecys", srcArchivesSecrecys);
			result = "success";
		} catch (Exception e) {
			// 异常错误
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return result;
	}

}
