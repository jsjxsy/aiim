package com.orifound.aiim.web.struts;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRolesInfo;

public class UserRolesInfoManageDWR{

	@Autowired
	private IUserRolesInfoManageService userRolesInfoManageService;

	/**
	 * 为指定的角色添加用户
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRolesInfoByRoleID(Integer[] UserIDS,int roleID,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		System.out.println("userIDs[0]----->"+UserIDS[0]);
		System.out.println("roleID----->"+roleID);
		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				for (int i = 0; i < UserIDS.length; i++) {
					UserRolesInfo userRolesInfo = new UserRolesInfo();
					userRolesInfo.setUserID(UserIDS[i]);
					userRolesInfo.setRolesID(roleID);
					if (userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存用户角色信息失败：");
					}
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
	 * 为指定的用户添加角色
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRolesInfoByUserID(Integer[] RoleIDS, int userID, HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			// 开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				for (int i = 0; i < RoleIDS.length; i++) {
					UserRolesInfo userRolesInfo = new UserRolesInfo();
					userRolesInfo.setRolesID(RoleIDS[i]);
					userRolesInfo.setUserID(userID);
					if (userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存用户角色信息失败：");
					}
				}

			}

		} catch (Exception e) {
			// 异常错误
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
	 * 删除用户角色信息
	 * @param ID
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public boolean delUserRoles(int ID, HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRolesInfo userRolesInfo = new UserRolesInfo();
		try {
			if (pFlag) {
				userRolesInfo.setID(ID);
				if (userRolesInfoManageService.deleteUserRolesInfo(userRolesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除用户角色信息失败：");
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
