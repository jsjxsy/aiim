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
	 * �޸�ָ���û��ĵ����ܼ�Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRightArchivesSecrecy(Integer[] IDS,int UserID,HttpSession session ) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRightArchivesSecrecy> userRightArchivesSecrecys = new ArrayList<UserRightArchivesSecrecy>();
			for (int i = 0; i < IDS.length; i++) {
					UserRightArchivesSecrecy userRightArchivesSecrecy = new UserRightArchivesSecrecy();
					userRightArchivesSecrecy.setUserID(UserID);
					userRightArchivesSecrecy.setSecrecyID(IDS[i]);
					userRightArchivesSecrecys.add(userRightArchivesSecrecy);
			}
		
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRightArchivesSecrecyManageService.deleteUserRightArchivesSecrecyByUserID(UserID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					UserInfo userInfo = new UserInfo();
					userInfo.setUserID(UserID);
					if (userRightArchivesSecrecyManageService.saveRightArchivesSecrecysForUser(userInfo,userRightArchivesSecrecys, pErrInfo)== false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
				}
				
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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
