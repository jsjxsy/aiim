package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.ISystemFeatureManageService;
import com.orifound.aiim.bll.service.IUserRightArchivesSecrecyManageService;
import com.orifound.aiim.bll.service.IUserRightSystemFeatureManageService;
import com.orifound.aiim.entity.ArchivesSecrecy;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserRightArchivesSecrecy;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class UserRightSystemFeatureManageAction extends ActionSupport{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUserRightSystemFeatureManageService userRightSystemFeatureManageService;
	


	public IUserRightSystemFeatureManageService getUserRightSystemFeatureManageService() {
		return userRightSystemFeatureManageService;
	}



	public void setUserRightSystemFeatureManageService(IUserRightSystemFeatureManageService userRightSystemFeatureManageService) {
		this.userRightSystemFeatureManageService = userRightSystemFeatureManageService;
	}

	private ISystemFeatureManageService systemFeatureManageService;
	
	public ISystemFeatureManageService getSystemFeatureManageService() {
		return systemFeatureManageService;
	}


	public void setSystemFeatureManageService(ISystemFeatureManageService systemFeatureManageService) {
		this.systemFeatureManageService = systemFeatureManageService;
	}
	
	
	private IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService; 

	public IUserRightArchivesSecrecyManageService getUserRightArchivesSecrecyManageService() {
		return userRightArchivesSecrecyManageService;
	}



	public void setUserRightArchivesSecrecyManageService(IUserRightArchivesSecrecyManageService userRightArchivesSecrecyManageService) {
		this.userRightArchivesSecrecyManageService = userRightArchivesSecrecyManageService;
	}

	/**
	 * ��ȡ�û�ϵͳ������Ȩ��
	 */
	public String getUserRightSystemFeatureTree() throws Exception {
		// TODO Auto-generated method stub
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		LinkedHashMap<String, SystemFeature> distSystemFeatures = new LinkedHashMap<String, SystemFeature>();// ��ȡָ���û��ĵ�������Ȩ��
		LinkedHashMap<String, SystemFeature> srcSystemFeatures = new LinkedHashMap<String, SystemFeature>();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<UserRightArchivesSecrecy> distArchivesSecrecys = new ArrayList<UserRightArchivesSecrecy>();
		int pUserID = 0;
		try {
			// ��ʼ���� 1...
			pErrPos = 1;

			String userID = request.getParameter("UserID");
			pUserID = Integer.parseInt(userID);
			// ��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userRightSystemFeatureManageService.findRightSystemFeaturesByUserID(pUserID, distSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ���û���ϵͳ����Ȩ��ʧ��:");
					result = "error";
				}

				pErrPos = 3;
				if (systemFeatureManageService.findSystemFeatures(srcSystemFeatures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡ�����û���ϵͳ����Ȩ��ʧ��:");
					result = "error";
				}

			}

			String tree = CreateTreeUtil.getUserRightSystemFeatureCheckBoxTree(srcSystemFeatures, distSystemFeatures);
			request.setAttribute("tree", tree);
			request.setAttribute("UserID", pUserID);
			request.setAttribute("type", "user");

			// ��ȡָ���û��ĵ����ܼ�Ȩ��
			if (pFlag) {
				if (userRightArchivesSecrecyManageService.findRightArchivesSecrecysByUserID(pUserID, distArchivesSecrecys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ���û��ĵ����ܼ�Ȩ��ʧ�ܣ�");
				}
			}
			request.setAttribute("distArchivesSecrecys", distArchivesSecrecys);
			LinkedHashMap<Integer, ArchivesSecrecy> srcArchivesSecrecys=SystemInitializer.getInstance().getArchivesSecrecys();//��ȡ���еĵ����ܼ�Ȩ��
			request.setAttribute("srcArchivesSecrecys", srcArchivesSecrecys);
			result = "success";
		} catch (Exception e) {
			// �쳣����
			result = "error";
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}
		return result;
	}

}
