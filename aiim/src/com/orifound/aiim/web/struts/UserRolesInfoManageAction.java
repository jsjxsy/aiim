package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IDutyManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.bll.service.IUserRoleManageService;
import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.bll.service.impl.UserInfoManageServiceImpl;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserRole;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRolesInfoManageAction extends ActionSupport implements ModelDriven<UserRolesInfo>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserRolesInfo userRolesInfo = new UserRolesInfo();
	
	public UserRolesInfo getModel() {
		// TODO Auto-generated method stub
		return userRolesInfo;
	}
	
	private IUserRolesInfoManageService userRolesInfoManageService;
	
	public IUserRolesInfoManageService getUserRolesInfoManageService() {
		return userRolesInfoManageService;
	}
	
	public void setUserRolesInfoManageService(IUserRolesInfoManageService userRolesInfoManageService) {
		this.userRolesInfoManageService = userRolesInfoManageService;
	}
	
	private IUserRoleManageService userRoleManageService;
	
	public IUserRoleManageService getUserRoleManageService() {
		return userRoleManageService;
	}

	public void setUserRoleManageService(IUserRoleManageService userRoleManageService) {
		this.userRoleManageService = userRoleManageService;
	}

	//接受角色列表ID
	private int[] IDs;
	
	public int[] getIDs() {
		return IDs;
	}
	public void setIDs(int[] iDs) {
		IDs = iDs;
	}
	
	private IUserInfoManageService userInfoManageService = new UserInfoManageServiceImpl();

	public IUserInfoManageService getUserInfoManageService() {
		return userInfoManageService;
	}
	public void setUserInfoManageService(IUserInfoManageService userInfoManageService) {
		this.userInfoManageService = userInfoManageService;
	}
	
	private IDepartmentInfoManageService departmentInfoManageService;//注入部门管理
	
	public IDepartmentInfoManageService getDepartmentInfoManageService() {
		return departmentInfoManageService;
	}
	public void setDepartmentInfoManageService(IDepartmentInfoManageService departmentInfoManageService) {
		this.departmentInfoManageService = departmentInfoManageService;
	}
	
	private IDutyManageService dutyManageService;
	
	public IDutyManageService getDutyManageService() {
		return dutyManageService;
	}
	public void setDutyManageService(IDutyManageService dutyManageService) {
		this.dutyManageService = dutyManageService;
	}
	
	private DataPageInfo  dataPageInfo= new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	/**
	 * 
	 * @param dataPageInfo 
	 * @return
	 * @throws Exception
	 */
	public String findAllUserRolesByRoleID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
		int pRoleID = 0;
		try {
			//开始处理 1...
			pErrPos = 1;
			String RoleID = request.getParameter("ID");
			
			if (pFlag) {
				if (RoleID == null || RoleID.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空!");
				}else{
					pRoleID=Integer.parseInt(RoleID);
				}
				
			}
			if (userRolesInfoManageService.findUserRolesInfosByRoleID(pRoleID, userRolesInfos, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询用户失败:");
			}else{
				request.setAttribute("userRolesInfos", userRolesInfos);
				request.setAttribute("rolesID",pRoleID);
				result = "success";
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
	
	/**
	 * 
	 * @param dataPageInfo 
	 * @return
	 * @throws Exception
	 */
	public String findAllUserRolesByUserID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
		int pUserID = 0;
		try {
			//开始处理 1...
			pErrPos = 1;
			String UserID = request.getParameter("UserID");
			
			if (pFlag) {
				if (UserID == null || UserID.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("角色编号非法为空!");
				}else{
					pUserID=Integer.parseInt(UserID);
				}
				
			}
			if (userRolesInfoManageService.findUserRolesInfosByUserID(pUserID, userRolesInfos, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询用户失败:");
			}else{
				request.setAttribute("userRolesInfos", userRolesInfos);
				request.setAttribute("UserID",pUserID);
				result = "success";
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
	
	/**
	 * 添加用户角色
	 * @return
	 * @throws Exception
	 */
	public String addUserRoles() throws Exception {
		String result="";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
	
		
		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo) == false) {
					pFlag = false;
					result = "error";
					pErrInfo.getContent().insert(0, "查询所有角色失败：");
				}else {
					result="success";
				}
			}

		} catch (Exception e) {
			//异常错误
			pFlag = false;
			result = "error";
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			result = "error";
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
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String delUserRoles() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();	
		List<UserRolesInfo> userRolesInfos = new ArrayList<UserRolesInfo>();
		int ID = 0;
		try {
			//开始处理 1...
			//调用业务逻辑：删除选中的用户信息
			pErrPos = 1;
			String iD = request.getParameter("ID");
			if (pFlag) {
				if (iD == null || iD.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户角色信息编号非法为空！");
				} else {
					ID = Integer.parseInt(iD);
				}
			}
			if (pFlag) {
				userRolesInfo.setID(ID);
				if (userRolesInfoManageService.deleteUserRolesInfo(userRolesInfo, pErrInfo) == false) {
					System.out.println("ID: " + userRolesInfo.getID() + " 删除失败！");
					result = "error";
				} 
			}
			int pRoleID = Integer.parseInt(request.getParameter("RoleID"));
			if (pFlag) {
				if (userRolesInfoManageService.findUserRolesInfosByRoleID(pRoleID, userRolesInfos, pErrInfo) == false) {
					result = "error";
				} 
			}
			request.setAttribute("userRolesInfos", userRolesInfos);
			request.setAttribute("RoleID", pRoleID);
		    result = "success";
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyUserRole() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;

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
		return super.execute();
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	public String updateUserRole() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;

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
		return super.execute();
	}
	
	
	/**
	 * 获取角色用户树
	 * @return
	 * @throws Exception
	 */
	public String getUserRolesInfoTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRole> userRoles = new ArrayList<UserRole>();
		try {
			//获取所有角色信息
			pErrPos = 1;
			if (pFlag) {
				if (userRoleManageService.findUserRoles(userRoles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有角色信息失败: ");
				}
			}


			//获取每个角色下面拥有的用户信息
			if (pFlag) {
				pErrPos = 2;
				for (UserRole userRole : userRoles) {
					List<UserRolesInfo> users=new ArrayList<UserRolesInfo>();
					if (userRolesInfoManageService.findUserRolesInfosByRoleID(userRole.getID(), users, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查找指定角色拥有的用户信息失败: ");
					}else {
						userRole.setHasUsers(users);
					}
				}
				
			}
			
			String tree=CreateTreeUtil.getUserRolesInfoTree(userRoles);
			request.setAttribute("tree", tree);
			request.setAttribute("userRoles", "getUserRolesSystemFeatureTree.action");
			request.setAttribute("userInfos", "getUserRightSystemFeatureTree.action");
			result = "success";
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
		return result;
	}
	
	
	
}
