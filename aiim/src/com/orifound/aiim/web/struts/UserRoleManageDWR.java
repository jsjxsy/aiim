package com.orifound.aiim.web.struts;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpSession;



import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;

public class UserRoleManageDWR{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IUserRoleManageService userRoleManageService;
	
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}
	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	
	/**
	 * 添加用户
	 * @return
	 * @throws Exception
	 */
	public boolean addUserRole(Map<String,String> formMap,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole = new UserRole();
		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (this.getUserRole(formMap, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append( "获取角色失败：");
				}
			}
			
			if (pFlag) {
				if (userRoleManageService.saveUserRole(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有角色失败：");
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
	
	/**获取userinfo对象
	 * @param formMap
	 * @param userInfo
	 * @param pErrInfo
	 * @return
	 */
	private boolean getUserRole(Map<String,String> formMap, UserRole userRole,ErrInfo  pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				if(key.equals("iD")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setID(Integer.parseInt((formMap.get(key))));
				}
				if(key.equals("name")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setName(formMap.get(key));
				}
				if(key.equals("remark")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userRole.setRemark(formMap.get(key));
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
	
	public boolean updateUserRole(Map<String,String> formMap,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole = new UserRole();
		try {
			//开始处理 1...
			pErrPos = 1;
			
			if (pFlag) {
				if (this.getUserRole(formMap, userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append( "获取角色失败：");
				}
			}
			
			if (pFlag) {
				if (userRole.getID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空！");
				}
				
				if (userRole.getName() == null || userRole.getName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("角色名称非法为空！");
				}
				
				
				if (userRole.getRemark() == null || userRole.getRemark().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("角色备注非法为空！");
				}
				
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userRoleManageService.updateUserRole(userRole, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("修改角色失败！");
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
	 * 
	 * @return
	 * @throws Exception
	 */
	public boolean delUserRoles(Integer[] IDS,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserRole userRole= null;
		try {
			//开始处理 1...
			//调用业务逻辑：删除选中的用户信息
			pErrPos = 1;
			if (pFlag) {
				for(int i = 0;i<IDS.length;i++){
					userRole = new UserRole();
					userRole.setID(IDS[i]);
					if(userRoleManageService.deleteUserRole(userRole, pErrInfo) == false){
						pFlag = false;
						pErrInfo.getContent().insert(0, "删除角色失败：");
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
}
