package com.orifound.aiim.web.struts;



import javax.servlet.http.HttpSession;


import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserChargeDepartmentInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;

public class UserChargeDepartmentManageDWR extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService;
	
	public IUserChargeDepartmentInfoManageService getUserChargeDepartmentInfoManageService() {
		return userChargeDepartmentInfoManageService;
	}

	public void setUserChargeDepartmentInfoManageService(IUserChargeDepartmentInfoManageService userChargeDepartmentInfoManageService) {
		this.userChargeDepartmentInfoManageService = userChargeDepartmentInfoManageService;
	}

	public boolean saveUserChargeDepartment(Integer[] DeparmentIDS,int UserID,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		try {
			//获取用户ID
			//调用业务逻辑：删除选中的用户信息
			if (pFlag) {
				 for (int i = 0; i < DeparmentIDS.length; i++) {
					 UserChargeDepartmentInfo userChargeDepartmentInfo = new UserChargeDepartmentInfo();
					 userChargeDepartmentInfo.setUserID(UserID);
					 userChargeDepartmentInfo.setDepartmentID(DeparmentIDS[i]);
					 if(userChargeDepartmentInfoManageService.saveUserChargeDepartmentInfo(userChargeDepartmentInfo, pErrInfo) == false){
						 	pFlag = false;
							System.out.println("DepartmentID: " +userChargeDepartmentInfo.getDepartmentID() +" 保存失败！");
						}
				}
			}	
		} catch (Exception e) {
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
		return pFlag;
	}

}
