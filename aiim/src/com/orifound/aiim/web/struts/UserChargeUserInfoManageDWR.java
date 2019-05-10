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
		
			//����DAO�ӿ�
			if (pFlag)
			{   
				
				for (int i = 0; i < ChargedUserID.length; i++) {
					UserChargeUserInfo userChargeUserInfo = new UserChargeUserInfo();
					userChargeUserInfo.setChargedUserID(ChargedUserID[i]);
					userChargeUserInfo.setUserID(UserID);
					if (userChargeUserInfoManageService.saveUserChargeUserInfo(userChargeUserInfo, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡָ���û�����ţ�������Ϣʧ��: ");
					}
					
				}
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
}
