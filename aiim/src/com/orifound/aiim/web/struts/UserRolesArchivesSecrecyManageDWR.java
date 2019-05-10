package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;


import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserRolesArchivesSecrecyManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesArchivesSecrecy;

public class UserRolesArchivesSecrecyManageDWR extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRolesArchivesSecrecyManageService  userRolesArchivesSecrecyManageService ;
	
	public IUserRolesArchivesSecrecyManageService getUserRolesArchivesSecrecyManageService() {
		return userRolesArchivesSecrecyManageService;
	}


	public void setUserRolesArchivesSecrecyManageService(IUserRolesArchivesSecrecyManageService userRolesArchivesSecrecyManageService) {
		this.userRolesArchivesSecrecyManageService = userRolesArchivesSecrecyManageService;
	}



	/**
	 * 修改指定角色的档案分类权限
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRolesArchivesSecrecy(Integer[] IDS,int RoleID,HttpSession session ) throws Exception {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserRolesArchivesSecrecy> userRolesArchivesSecrecys = new ArrayList<UserRolesArchivesSecrecy>();
			for (int i = 0; i < IDS.length; i++) {
				UserRolesArchivesSecrecy userRolesArchivesSecrecy = new UserRolesArchivesSecrecy();
				userRolesArchivesSecrecy.setRolesID(RoleID);
				userRolesArchivesSecrecy.setSecrecyID(IDS[i]);
				userRolesArchivesSecrecys.add(userRolesArchivesSecrecy);
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				//先删除已有所有的档案分类权限记录
					if (userRolesArchivesSecrecyManageService.deleteUserRolesArchivesSecrecyByRoleID(RoleID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					}
					if (userRolesArchivesSecrecyManageService.saveUserRolesArchivesSecrecyS(userRolesArchivesSecrecys, pErrInfo) == false) {
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
		return pFlag;
	}
	
}
