package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;



import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;

public class UserRightArchivesSecrecyManageDWR{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService;
	
	public IUserRightArchivesSecrecyManageService getUserRightArchivesSecrecyManageService() {
		return userRightArchivesSecrecyManageService;
	}

	public void setUserRightArchivesSecrecyManageService(IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService) {
		this.userRightArchivesSecrecyManageService = userRightArchivesSecrecyManageService;
	}

	/**
	 * 修改指定用户的档案密级权限
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRightArchivesSecrecy(Integer[] IDS,int UserID,HttpSession session ) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserRightArchivesSecrecy> userRightArchivesSecrecys = new ArrayList<UserRightArchivesSecrecy>();
			for (int i = 0; i < IDS.length; i++) {
					UserRightArchivesSecrecy userRightArchivesSecrecy = new UserRightArchivesSecrecy();
					userRightArchivesSecrecy.setUserID(UserID);
					userRightArchivesSecrecy.setSecrecyID(IDS[i]);
					userRightArchivesSecrecys.add(userRightArchivesSecrecy);
			}
		
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				//先删除已有所有的档案分类权限记录
					if (userRightArchivesSecrecyManageService.deleteUserRightArchivesSecrecyByUserID(UserID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "获取指定用户的档案分类权限失败:");
					}
					UserInfo userInfo = new UserInfo();
					userInfo.setUserID(UserID);
					if (userRightArchivesSecrecyManageService.saveRightArchivesSecrecysForUser(userInfo,userRightArchivesSecrecys, pErrInfo)== false) {
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
