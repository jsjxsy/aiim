package com.orifound.aiim.web.struts;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserChargeUserInfoManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserChargeUserInfo;

public class UserChargeUserInfoManageDWR {
	@Autowired
	private IUserChargeUserInfoManageService userChargeUserInfoManageService;
	
	public boolean saveUserChargeUserInfo(Integer[] ChargedUserID,int UserID,HttpSession session)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try
		{
			pErrPos = 1;
		
			//调用DAO接口
			if (pFlag)
			{   
				
				for (int i = 0; i < ChargedUserID.length; i++) {
					UserChargeUserInfo userChargeUserInfo = new UserChargeUserInfo();
					userChargeUserInfo.setChargedUserID(ChargedUserID[i]);
					userChargeUserInfo.setUserID(UserID);
					if (userChargeUserInfoManageService.saveUserChargeUserInfo(userChargeUserInfo, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取指定用户（编号）代工信息失败: ");
					}
					
				}
			}
		}
		catch (Exception e)
		{
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException()!=null)
				{
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
