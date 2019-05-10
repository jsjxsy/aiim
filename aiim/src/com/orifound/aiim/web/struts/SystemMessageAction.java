package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IMessageTypeManageService;
import com.orifound.aiim.bll.service.ISystemMessageService;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.MessageType;
import com.orifound.aiim.entity.SystemFeature;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.SystemMessageQueryCondition;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;

/**
 * ϵͳ��Ϣ����Action
 * @author Administrator
 *
 */
public class SystemMessageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	/**
	 * ���صĽ��URL
	 */
	private String resultStr;

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}
	
	private ISystemMessageService systemMessageService;
	
	public ISystemMessageService getSystemMessageService() {
		return systemMessageService;
	}

	public void setSystemMessageService(ISystemMessageService systemMessageService) {
		this.systemMessageService = systemMessageService;
	}
	
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	private SystemMessageQueryCondition systemMessageQueryCondition = new SystemMessageQueryCondition(); //ϵͳ��Ϣ��ѯ����

	public SystemMessageQueryCondition getSystemMessageQueryCondition() {
		return systemMessageQueryCondition;
	}

	public void setSystemMessageQueryCondition(SystemMessageQueryCondition systemMessageQueryCondition) {
		this.systemMessageQueryCondition = systemMessageQueryCondition;
	}
	
	private IMessageTypeManageService messageTypeManageService;
	
	public IMessageTypeManageService getMessageTypeManageService() {
		return messageTypeManageService;
	}

	public void setMessageTypeManageService(IMessageTypeManageService messageTypeManageService) {
		this.messageTypeManageService = messageTypeManageService;
	}

	private int[] IDS;
	
	public int[] getIDS() {
		return IDS;
	}

	public void setIDS(int[] iDS) {
		IDS = iDS;
	}

	/**
	 * ��ȡϵͳ��Ϣ��
	 * @return
	 * @throws Exception
	 */
	public String getSystemMessageTree() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//��ȡ�û�-�ҵĹ����ռ�-�˵�
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			SystemFeature systemFeature = userInfo.getSystemMenus().get("�ҵĹ����ռ�");
			//��ȡ�û�-�ҵĹ����ռ�-�Ӳ˵�
			LinkedHashMap<String, SystemFeature>   systemFeatures =systemFeature.getChildSystemFeatures();
			
			String tree = CreateTreeUtil.getSystemMessageTree(systemFeatures);
			
			request.setAttribute("tree",tree);
			request.setAttribute("systemMessage", "systemMessageAction_findMessagesByConditions.action?UserID="+userInfo.getUserID());
			
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}
	
	
	/**
	 * �鿴ϵͳ��Ϣ��ϸ���
	 * @return
	 * @throws Exception
	 */
	public String findMessageByID() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		int pID = 0;//ϵͳ����Ϣ���
		SystemMessage systemMessage = new SystemMessage();
		MessageType pMessageType = new MessageType();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				 pID = Integer.parseInt(request.getParameter("ID")); 
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				if (systemMessageService.findSystemMessageByID(pID, systemMessage, pErrInfo) == false) {
					resultStr = "/error.jsp";
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ϵͳ��ϢΨһ��Ų���ϵͳ��Ϣʧ��:");
				}else{
					request.setAttribute("systemMessage", systemMessage);
				}
				
				if (pFlag) {
					if (systemMessageService.updateSystemMessage(systemMessage, pErrInfo) == false) {
						resultStr = "/error.jsp";
						pFlag = false;
						pErrInfo.getContent().insert(0, "����ID�Ÿ���ϵͳ��Ϣ�ֶ�");
					}
				}
				
				if (pFlag) {
					if (messageTypeManageService.findMessageTypeByID(systemMessage.getMsgTypeID(), pMessageType, pErrInfo) == false) {
						resultStr = "/error.jsp";
						pFlag = false;
						pErrInfo.getContent().insert(0, "����Ψһ��ʶ����ϵͳ��Ϣ������Ϣʧ�ܣ�");
					}else{
						request.setAttribute("messageType", pMessageType);
					}
					
				}
				resultStr = "/XTXX/Work_Info_show.jsp";
			}
		} catch (Exception e) {
			resultStr = "/error.jsp";
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}

	
	/**
	 * ���������ҵ�ǰ�û�ϵͳ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findMessagesByConditions() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<SystemMessage> systemMessages = new ArrayList<SystemMessage>();
		HttpServletRequest request = ServletActionContext.getRequest();
		int pUserID = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				pUserID = Integer.parseInt(request.getParameter("UserID")); 
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("�û���ŷǷ�Ϊ�գ�");
				}
			}
			//ÿҳ��ʾ��С
			dataPageInfo.setPageSize(10);
			//��ʼ����2...
			if (pFlag) {
				if (systemMessageService.findSystemMessagesByUserID(pUserID, systemMessageQueryCondition, dataPageInfo, systemMessages, pErrInfo) == false) {
						resultStr = "/error.jsp";
					    pFlag = false;
						pErrInfo.getContent().insert(0, "���ҷ��͸�ָ���û������������ѯ������ϵͳ��Ϣʧ�ܣ�");		
					}else{
						request.setAttribute("systemMessages", systemMessages);
						request.setAttribute("UserID", pUserID);
						resultStr = "/XTXX/systemMessage.jsp";
					}
						
			}
		} catch (Exception e) {
			//�쳣����
			resultStr = "/error.jsp";
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}

	
	/**
	 * ɾ��ϵͳ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String delMessages() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<SystemMessage> systemMessages = new ArrayList<SystemMessage>();
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//��ȡҪɾ��ϵͳ��Ϣ����
			for (int i = 0; i < IDS.length; i++) {
				SystemMessage systemMessage = new SystemMessage();
				systemMessage.setID(IDS[i]);
				systemMessages.add(systemMessage);
			}

			//��ʼ����2...
			if (pFlag) {
				if (systemMessageService.deleteSystemMessage(systemMessages, pErrInfo) == false) {
					resultStr = "/error.jsp";
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��ָ����ϵͳ��Ϣʧ��:");
				}else{
					resultStr = "/XTXX/systemMessage.jsp";
				}
				
			}
		} catch (Exception e) {
			//�쳣����
			resultStr = "/error.jsp";
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}
}
