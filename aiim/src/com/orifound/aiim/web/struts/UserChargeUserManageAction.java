package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserChargeUserInfoManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;
import com.orifound.aiim.entity.UserInfo;

public class UserChargeUserManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private IUserChargeUserInfoManageService userChargeUserInfoManageService;
	
	public IUserChargeUserInfoManageService getUserChargeUserInfoManageService() {
		return userChargeUserInfoManageService;
	}

	public void setUserChargeUserInfoManageService(IUserChargeUserInfoManageService userChargeUserInfoManageService) {
		this.userChargeUserInfoManageService = userChargeUserInfoManageService;
	}
	
	private IUserInfoManageService userInfoManageService;

	public IUserInfoManageService getUserInfoManageService() {
		return userInfoManageService;
	}
	public void setUserInfoManageService(IUserInfoManageService userInfoManageService) {
		this.userInfoManageService = userInfoManageService;
	}
	
	/**
	 * 批量删除用户信息
	 * @return
	 * @throws Exception
	 */
	public String deleteUserChargeUser() throws Exception{
		String result = "";	
		String message = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		int ID = 0;
		int pUserID = 0;
		UserChargeUserInfo userChargeUserInfo = new UserChargeUserInfo();
		List<UserInfo> userInfos = new ArrayList<UserInfo>();
		try {
			//获取用户ID
			pErrPos = 1;	
			String iDString = request.getParameter("ID");
			if (iDString == null || iDString.equals("")) {
			    pFlag = false;
			    pErrInfo.getContent().append("用户代工ID编号非法为空！");
			}else{ 
				 ID = Integer.parseInt(iDString);	
			}
			//调用业务逻辑：删除选中的用户信息
			if (pFlag) {
				    userChargeUserInfo.setID(ID);
					if(userChargeUserInfoManageService.deleteUserChargeUserInfo(userChargeUserInfo, pErrInfo) == false){
						System.out.println("chargedUserID: " +userChargeUserInfo.getChargedUserID() +" 删除失败！");
						result = "error";
						message = "删除用户出错";
					}
			}	
			
			pErrPos = 2;
			//调用业务逻辑：查找查询所有用户信息
			String UserID = request.getParameter("UserID");
			if (pFlag) {
				if (UserID == null || UserID.equals("")) {
					pFlag = false;
				    pErrInfo.getContent().append("用户代工UserID编号非法为空！");
				}else{
					pUserID = Integer.parseInt(UserID); 
				}
			}
			if (pFlag) {
				if(userInfoManageService.findUserChargeUserInfosByUserID(pUserID, userInfos, pErrInfo)==false){
					result = "error";
				}
			}			
			pErrPos = 3;
			//将查询结果等信息返回到页面
			if(pFlag){
				result = "success";
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
	
}
