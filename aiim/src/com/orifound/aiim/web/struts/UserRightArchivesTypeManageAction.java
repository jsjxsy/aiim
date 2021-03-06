package com.orifound.aiim.web.struts;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRightArchivesTypeManageAction extends ActionSupport{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRightArchivesTypeManageService userRightArchivesTypeManageService;
	
	public IUserRightArchivesTypeManageService getUserRightArchivesTypeManageService() {
		return userRightArchivesTypeManageService;
	}

	public void setUserRightArchivesTypeManageService(IUserRightArchivesTypeManageService userRightArchivesTypeManageService) {
		this.userRightArchivesTypeManageService = userRightArchivesTypeManageService;
	}
	
	/**
	 * 获取用户档案分类授权树
	 */
	public String getUserRightArchivesTypeTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		LinkedHashMap<Integer,ArchivesType> distArchivestypes = new  LinkedHashMap<Integer,ArchivesType>();//获取指定用户的档案分类权限
		HttpServletRequest request = ServletActionContext.getRequest();
		int pUserID = 0;
		try {
			//开始处理 1...
			pErrPos = 1;
			
		String userID =request.getParameter("UserID");
		pUserID=Integer.parseInt(userID);
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				
				if (userRightArchivesTypeManageService.findRightArchivesTypeByUserID(pUserID, distArchivestypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					result = "error";
				}else {
					//树状结构的档案分类集合
					LinkedHashMap<Integer, ArchivesType> srcArchivestypes=SystemInitializer.getInstance().getArchivesTypes();
					String tree = CreateTreeUtil.getUserRightArchivesTypeCheckBoxTree(srcArchivestypes,distArchivestypes);
					request.setAttribute("tree", tree);
					request.setAttribute("UserID", pUserID);
					result = "success";
				}

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
	
}
