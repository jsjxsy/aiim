package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IDutyManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;

import com.orifound.aiim.bll.service.IUserRolesInfoManageService;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.web.testDate.ReturnTree;

public class UserInfoManageAction extends ActionSupport implements ModelDriven<UserInfo> {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户信息管理服务
	 */
	private IUserInfoManageService userInfoManageService ;


	public IUserInfoManageService getUserInfoManageService() {
		return userInfoManageService;
	}

	public void setUserInfoManageService(IUserInfoManageService userInfoManageService) {
		this.userInfoManageService = userInfoManageService;
	}

	/**
	 * 部门信息管理服务
	 */
	private IDepartmentInfoManageService departmentInfoManageService;//注入部门管理
	

	public IDepartmentInfoManageService getDepartmentInfoManageService() {
		return departmentInfoManageService;
	}

	public void setDepartmentInfoManageService(IDepartmentInfoManageService departmentInfoManageService) {
		this.departmentInfoManageService = departmentInfoManageService;
	}

	/**
	 * 职务信息数据字典管理服务
	 */
	private IDutyManageService dutyManageService;
	
	
	public IDutyManageService getDutyManageService() {
		return dutyManageService;
	}

	public void setDutyManageService(IDutyManageService dutyManageService) {
		this.dutyManageService = dutyManageService;
	}

	private IUserRolesInfoManageService userRolesInfoManageService;
	
	public IUserRolesInfoManageService getUserRolesInfoManageService() {
		return userRolesInfoManageService;
	}
	
	public void setUserRolesInfoManageService(IUserRolesInfoManageService userRolesInfoManageService) {
		this.userRolesInfoManageService = userRolesInfoManageService;
	}
	
	private UserInfo userInfo = new UserInfo();	
	
	public UserInfo getModel(){
		return userInfo;
	}
	
	private String queryRealName = "";//真实姓名：用于查询
	private int[] userIDs={};  //用户编号列表
	private DataPageInfo  dataPageInfo= new DataPageInfo();
	public int[] getUserIDs() {
		return userIDs;
	}
	public void setUserIDs(int[] userIDs) {
		this.userIDs = userIDs;
	}
	
	public String getQueryRealName() {
		return queryRealName;
	}
	public void setQueryRealName(String queryRealName) {
		this.queryRealName = queryRealName;
	}
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * 批量删除用户信息
	 * @return
	 * @throws Exception
	 */
	public String deleteUserInfos() throws Exception{
		String result = "";	
		String message = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		//Map<Integer, UserInfo> userInfos = new HashMap<Integer, UserInfo>();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		UserInfo user = null;
		try {
			//获取用户ID
			pErrPos = 1;			
			
			//调用业务逻辑：删除选中的用户信息
			if (pFlag) {
				for(int i = 0;i<userIDs.length;i++){
					user = new UserInfo();
					user.setUserID(userIDs[i]);
					if(userInfoManageService.deleteUserInfo(user, pErrInfo) == false){
						System.out.println("userID: " +user.getUserID() +" 删除失败！");
						result = "error";
						message = "删除用户出错";
					}else{
						result = "success";
					}
				}
			}	
			
			pErrPos = 2;
			//调用业务逻辑：查找查询所有用户信息
			if (pFlag) {
				if(userInfoManageService.findUsers(userInfos, pErrInfo)==false){
					//生成测试数据
					setUserInfos(userInfos);
					System.out.println("queryRealName: " +queryRealName +"userNum : "+ userInfos.size());
					result = "success";
				}else{
					pFlag = false;//调用业务逻辑出错，标识为失败
					result = "error";
				}

			}			
			pErrPos = 3;
			//将查询结果等信息返回到页面
			if(pFlag){
				request.setAttribute("userInfos",userInfos);
				request.setAttribute("recordSize",userInfos.size());
				request.setAttribute("message", message);
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
	 * 通过真实姓名查询用户信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findUserByRealName() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		//Map<Integer, UserInfo> userInfos = new HashMap<Integer, UserInfo>();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		try {
			//开始处理 1...
			pErrPos = 1;
			

			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.findUserByRealName(queryRealName, userInfos, pErrInfo)==false){
					//生成测试数据
					setUserInfos(userInfos);
							System.out.println("queryRealName: " +queryRealName +"userNum : "+ userInfos.size());
					result = "success";
				}else{
					pFlag = false;//调用业务逻辑出错，标识为失败
				}

			}			
			pErrPos = 2;
			//将查询结果等信息返回到页面
			if(pFlag){
				request.setAttribute("userInfos",userInfos);	
				request.setAttribute("recordSize",userInfos.size());
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
	 * 通过真实姓名查询用户信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findAllUsers() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		List<Duty> dutys = new ArrayList<Duty>();
		Map<String, Object> userInfoQueryCondition = new HashMap<String, Object>();
		try {
			//开始处理 1...
			pErrPos = 1;
			String RealName = request.getParameter("RealName");
			if(RealName != null && !RealName.equals("")){
				userInfoQueryCondition.put("RealName", RealName);
			}
			
			String DepartmentID = request.getParameter("DepartmentID");
			if(DepartmentID != null && !DepartmentID.equals("")){
				userInfoQueryCondition.put("DepartmentID", DepartmentID);
			}
			String DutyID = request.getParameter("DutyID");
			if(DutyID != null && !DutyID.equals("")){
				userInfoQueryCondition.put("DutyID", DutyID);
			}
			
			String IDCardNo = request.getParameter("IDCardNo");
			if(IDCardNo != null && !IDCardNo.equals("")){
				userInfoQueryCondition.put("IDCardNo", IDCardNo);
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("departmentInfos", departmentInfos);
				}
			}

			if (pFlag) {
				pErrPos = 3;
				if (dutyManageService.findDutys(dutys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("dutys", dutys);
				}
			}
			
			dataPageInfo.setPageSize(10);
			if (userInfoManageService.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo)== false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询用户失败:");
				result = "error";
			}else{
				request.setAttribute("userInfos", userInfos);
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
	 * 通过用户编号查找用户信息
	 * @return
	 * @throws Exception
	 */
	public String findUserInfoByUserID() throws Exception{
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		//UserInfo userInfo = new UserInfo();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		try {
			//获取用户ID
			pErrPos = 1;			
			int userID = Integer.parseInt(request.getParameter("userID"));
			if(userID==0){
				pFlag = false;
			}
			
			pErrPos = 2;
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.findUserByUserID(userID, userInfo, pErrInfo)==false){
					//生成测试数据
					int i =userID;
					userInfo.setAddress("湖北省武汉市测试地址" + i);
					userInfo.setDepartmentID(200 + i);
					userInfo.setDutyID(10+i);
					userInfo.setEmail("testMail"+i+"@126.com");
					userInfo.setIDCardNo(60000+i+"");
					userInfo.setIDCardTypeID(100+i);
					userInfo.setRealName("测试名"+i);
					userInfo.setTel("1891153289"+i);
					userInfo.setUserID(10000+i);
					userInfo.setUserName("userName"+i);
					
					userInfos.add(userInfo);
					System.out.println("查询用户成功！findUserInfoByUserID: " +userID+ "is successful!");
					result = "success";
				}else{
					pFlag = false;//调用业务逻辑出错，标识为失败
				}
			}			
			pErrPos = 2;
			//将查询结果等信息返回到页面
			if(pFlag){
				request.setAttribute("userInfos",userInfos);	
				request.setAttribute("recordSize",userInfos.size());
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

	
	
	public void setUserInfos(List<UserInfo> userInfos){
		UserInfo userInfo =null;		
		for(int i = 1;i<10;i++){
			userInfo = new UserInfo();
			userInfo.setAddress("湖北省武汉市测试地址"+ i);
			userInfo.setDepartmentID(100+i);
			userInfo.setDutyID(10+i);
			userInfo.setEmail("testMail"+i+"@126.com");
			userInfo.setIDCardNo(6000000+i+"");
			userInfo.setIDCardTypeID(100+i);
			userInfo.setRealName("测试名"+i);
			userInfo.setTel("1891153289"+i);
			userInfo.setUserID(10000+i);
			userInfo.setUserName("userName"+i);
			userInfos.add(userInfo);
		}
	}
    
	/**
	 * FeatureDescription
	 * 
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String getUserInfoTree() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		try {
			// 获取用户ID
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			String tree = ReturnTree.getUserInfoTree();
			request.setAttribute("tree", tree);
			request.setAttribute("userInfo", "userInfoManageAction_findAllUsers.action");
			request.setAttribute("userRoles", "userRoleManageAction_findAllUserRoles.action");
			request.setAttribute("userProxy", "userRolesInfoManageAction_UserProxyList.action");
			request.setAttribute("modifyPassword", "userInfoManageAction_modifyPassword.action");
			result = "success";
		} catch (Exception e) {
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
	 * 打开添加用户界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String addUserInfo() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		List<Duty> dutys = new ArrayList<Duty>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();

			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("departmentInfos", departmentInfos);
				}
			}

			if (pFlag) {
				pErrPos = 3;
				if (dutyManageService.findDutys(dutys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("dutys", dutys);
				}
			}
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
		}
		return result;
	}
	
	/**
	 * 打开修改用户界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyUserInfo() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		List<Duty> dutys = new ArrayList<Duty>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userIDString = request.getParameter("UserID"); 
			if (pFlag) {
				if (userIDString == null || userIDString.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false) {
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("departmentInfos", departmentInfos);
				}
			}

			if (pFlag) {
				pErrPos = 3;
				if (dutyManageService.findDutys(dutys, pErrInfo) == false) {
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("dutys", dutys);
				}
			}
			if (pFlag) {
				pErrPos = 4;
				int UserID =Integer.parseInt( userIDString);
				if (userInfoManageService.findUserByUserID(UserID, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户信息查询失败：");
				}else {
					request.setAttribute("userInfo", userInfo);
				}
			}
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
		}
		return result;
	}
	
	/**
	 * 被代理用户
	 * @return
	 * @throws Exception
	 */
	public String findUserChargeUserInfosByUserID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userIDString = request.getParameter("UserID"); 
			if (pFlag) {
				if (userIDString == null || userIDString.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}

			if (pFlag) {
				pErrPos = 4;
				int UserID =Integer.parseInt( userIDString);
				if (userInfoManageService.findUserChargeUserInfosByUserID(UserID, userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户信息查询失败：");
				}else {
					request.setAttribute("userInfos", userInfos);
					request.setAttribute("UserID", UserID);
				}
			}
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
		}
		return result;
	}
	
	/**
	 * 查询所有未被代理的用户
	 * @return
	 * @throws Exception
	 */
	public String findAllUserUnchargeUserInfos() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		Map<String, Object> userInfoQueryCondition = new HashMap<String, Object>();
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userIDString = request.getParameter("UserID"); 
			if (pFlag) {
				if (userIDString == null || userIDString.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
		
				String RealName = request.getParameter("RealName");
				if(RealName != null && !RealName.equals("")){
					userInfoQueryCondition.put("RealName", RealName);
				}
				
				String IDCardNo = request.getParameter("IDCardNo");
				if(IDCardNo != null && !IDCardNo.equals("")){
					userInfoQueryCondition.put("IDCardNo", IDCardNo);
				}
				
			
			if (pFlag) {
				pErrPos = 4;
				dataPageInfo.setPageSize(10);
				if (userInfoManageService.findAllUserUnchargeUserInfosByUserID(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "用户信息查询失败：");
				}else {
					request.setAttribute("userInfos", userInfos);
					int UserID =Integer.parseInt( userIDString);
					request.setAttribute("UserID", UserID);
				}
			}
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
		}
		return result;
	}
	
	/**
	 * 修改密码
	 * @return
	 * @throws Exception
	 */
	public String modifyPassword() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			 HttpSession session = ServletActionContext.getRequest().getSession();
			 UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			 request.setAttribute("userInfo", userInfo);
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
		}
		return result;
	}
	
	/**
	 * 删除用户下的角色
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
		UserRolesInfo userRolesInfo = new UserRolesInfo();
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
			int pUserID = Integer.parseInt(request.getParameter("UserID"));
			if (pFlag) {
				if (userRolesInfoManageService.findUserRolesInfosByUserID(pUserID, userRolesInfos, pErrInfo) == false) {
					result = "error";
				} 
			}
			request.setAttribute("userRolesInfos", userRolesInfos);
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
	 * 查找代理用户信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findUsersProxy() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		Map<String, Object> userInfoQueryCondition = new HashMap<String, Object>();
		try {
			//开始处理 1...
			pErrPos = 1;
			String RealName = request.getParameter("RealName");
			if(RealName != null && !RealName.equals("")){
				userInfoQueryCondition.put("RealName", RealName);
			}
			
			String DepartmentID = request.getParameter("DepartmentID");
			if(DepartmentID != null && !DepartmentID.equals("")){
				userInfoQueryCondition.put("DepartmentID", DepartmentID);
			}
				
			
			String IDCardNo = request.getParameter("IDCardNo");
			if(IDCardNo != null && !IDCardNo.equals("")){
				userInfoQueryCondition.put("IDCardNo", IDCardNo);
			}
			if (pFlag) {
				pErrPos = 2;
				if (departmentInfoManageService.findDepartmentInfos(departmentInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有部门失败:");
					result = "error";
				} else {
					request.setAttribute("departmentInfos", departmentInfos);
				}
			}
			dataPageInfo.setPageSize(10);
			if (userInfoManageService.findUserProxyInfosByCondition(userInfoQueryCondition, dataPageInfo, userInfos, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询用户失败:");
			}else{
				request.setAttribute("userInfos", userInfos);
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
	 * 查找不属于某一角色的用户
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findUseresNotInRoleID() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		Map<String, Object> userInfoQueryCondition = new HashMap<String, Object>();
		try {
			//开始处理 1...
			pErrPos = 1;
			String RealName = request.getParameter("RealName");
			if(RealName != null && !RealName.equals("")){
				userInfoQueryCondition.put("RealName", RealName);
			}
			
			String IDCardNo = request.getParameter("IDCardNo");
			if(IDCardNo != null && !IDCardNo.equals("")){
				userInfoQueryCondition.put("IDCardNo", IDCardNo);
			}
			
			int pRoleID = Integer.parseInt(request.getParameter("RoleID"));
			dataPageInfo.setPageSize(10);//设置每页显示多少条记录
			if (userInfoManageService.findUseresNotInRoleID(pRoleID, userInfoQueryCondition, dataPageInfo, userInfos, pErrInfo) == false) {
				result = "error";
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询为加入指定角色的用户失败:");
			}else{
				request.setAttribute("userInfos", userInfos);
				request.setAttribute("RoleID", pRoleID);
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
}
