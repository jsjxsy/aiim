package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;



import com.orifound.aiim.bll.service.IUserRightArchivesTypeManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRightArchivesType;

public class UserRightArchivesTypeManageDWR {


	
	private IUserRightArchivesTypeManageService userRightArchivesTypeManageService;
	
	public IUserRightArchivesTypeManageService getUserRightArchivesTypeManageService() {
		return userRightArchivesTypeManageService;
	}

	public void setUserRightArchivesTypeManageService(IUserRightArchivesTypeManageService userRightArchivesTypeManageService) {
		this.userRightArchivesTypeManageService = userRightArchivesTypeManageService;
	}

	/**
	 * �޸�ָ���û��ĵ�������Ȩ��
	 * @return
	 * @throws Exception
	 */
	public boolean modifyUserRightArchivesType(Integer[] ArchivesTypeIDS , int pUserID,HttpSession session){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserRightArchivesType> userRightArchivesTypes = new ArrayList<UserRightArchivesType>();
			for (int i = 0; i < ArchivesTypeIDS.length; i++) {
				UserRightArchivesType userRightArchivesType = new UserRightArchivesType();
				userRightArchivesType.setUserID(pUserID);
				userRightArchivesType.setArchivesTypeID(ArchivesTypeIDS[i]);
				userRightArchivesTypes.add(userRightArchivesType);
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				//��ɾ���������еĵ�������Ȩ�޼�¼
					if (userRightArchivesTypeManageService.deleteRightArchivesTypeByUserID(pUserID, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ�������Ȩ��ʧ��:");
					}
					UserInfo userInfo = new UserInfo();
					userInfo.setUserID(pUserID);
					if (userRightArchivesTypeManageService.saveRightArchivesTypesForUser(userInfo, userRightArchivesTypes, pErrInfo) == false) {
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
