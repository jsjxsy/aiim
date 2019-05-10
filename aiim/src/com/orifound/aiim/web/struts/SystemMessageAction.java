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
 * 系统消息管理Action
 * @author Administrator
 *
 */
public class SystemMessageAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	/**
	 * 返回的结果URL
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
	
	private SystemMessageQueryCondition systemMessageQueryCondition = new SystemMessageQueryCondition(); //系统消息查询条件

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
	 * 获取系统消息树
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
			//开始处理 1...
			pErrPos = 1;
			//获取用户-我的工作空间-菜单
			UserInfo userInfo = (UserInfo)session.getAttribute("userInfo");
			SystemFeature systemFeature = userInfo.getSystemMenus().get("我的工作空间");
			//获取用户-我的工作空间-子菜单
			LinkedHashMap<String, SystemFeature>   systemFeatures =systemFeature.getChildSystemFeatures();
			
			String tree = CreateTreeUtil.getSystemMessageTree(systemFeatures);
			
			request.setAttribute("tree",tree);
			request.setAttribute("systemMessage", "systemMessageAction_findMessagesByConditions.action?UserID="+userInfo.getUserID());
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}
	
	
	/**
	 * 查看系统消息详细情况
	 * @return
	 * @throws Exception
	 */
	public String findMessageByID() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		int pID = 0;//系统融消息编号
		SystemMessage systemMessage = new SystemMessage();
		MessageType pMessageType = new MessageType();
		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				 pID = Integer.parseInt(request.getParameter("ID")); 
				if (pID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}

			//开始处理2...
			if (pFlag) {
				if (systemMessageService.findSystemMessageByID(pID, systemMessage, pErrInfo) == false) {
					resultStr = "/error.jsp";
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据系统消息唯一编号查找系统消息失败:");
				}else{
					request.setAttribute("systemMessage", systemMessage);
				}
				
				if (pFlag) {
					if (systemMessageService.updateSystemMessage(systemMessage, pErrInfo) == false) {
						resultStr = "/error.jsp";
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据ID号更新系统消息字段");
					}
				}
				
				if (pFlag) {
					if (messageTypeManageService.findMessageTypeByID(systemMessage.getMsgTypeID(), pMessageType, pErrInfo) == false) {
						resultStr = "/error.jsp";
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据唯一标识查找系统消息类型信息失败：");
					}else{
						request.setAttribute("messageType", pMessageType);
					}
					
				}
				resultStr = "/XTXX/Work_Info_show.jsp";
			}
		} catch (Exception e) {
			resultStr = "/error.jsp";
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}

	
	/**
	 * 按条件查找当前用户系统消息
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				pUserID = Integer.parseInt(request.getParameter("UserID")); 
				if (pUserID <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("用户编号非法为空！");
				}
			}
			//每页显示大小
			dataPageInfo.setPageSize(10);
			//开始处理2...
			if (pFlag) {
				if (systemMessageService.findSystemMessagesByUserID(pUserID, systemMessageQueryCondition, dataPageInfo, systemMessages, pErrInfo) == false) {
						resultStr = "/error.jsp";
					    pFlag = false;
						pErrInfo.getContent().insert(0, "查找发送给指定用户的所有满足查询条件的系统消息失败：");		
					}else{
						request.setAttribute("systemMessages", systemMessages);
						request.setAttribute("UserID", pUserID);
						resultStr = "/XTXX/systemMessage.jsp";
					}
						
			}
		} catch (Exception e) {
			//异常错误
			resultStr = "/error.jsp";
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}

	
	/**
	 * 删除系统消息
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
			//开始处理 1...
			pErrPos = 1;
			//获取要删除系统消息集合
			for (int i = 0; i < IDS.length; i++) {
				SystemMessage systemMessage = new SystemMessage();
				systemMessage.setID(IDS[i]);
				systemMessages.add(systemMessage);
			}

			//开始处理2...
			if (pFlag) {
				if (systemMessageService.deleteSystemMessage(systemMessages, pErrInfo) == false) {
					resultStr = "/error.jsp";
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除指定的系统消息失败:");
				}else{
					resultStr = "/XTXX/systemMessage.jsp";
				}
				
			}
		} catch (Exception e) {
			//异常错误
			resultStr = "/error.jsp";
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		return SUCCESS;
	}
}
