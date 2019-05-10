package com.orifound.aiim.web.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.entity.ErrInfo;

import com.orifound.aiim.entity.UserInfo;

/**
 * UserAction
 * 
 * @author ���
 * 
 */
public class UserLoginAction extends ActionSupport {

	// ʹ��Log4j
	static Log logger = LogFactory.getLog(UserLoginAction.class);

	private static final long serialVersionUID = 1L;
	// �û���
	private String userName;
	// ����
	private String password;
	// �����½ҵ���߼���
	@Autowired
	private IUserInfoManageService userInfoManageService;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * ��½Action �����û���½����
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String login() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		

		String forWard = "";
		String message = "";

		// �õ�request��session����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			userInfo.setUserPWD(password);

			pErrPos = 1;
			
			pFlag = userInfoManageService.login(userInfo, pErrInfo);

			if (pFlag) {				
				
				// ���û���Ϣ������session�Ự��
				session.setAttribute("userInfo", userInfo);
    			forWard = SUCCESS;
			} else{
				System.out.println(pErrInfo.toString());
			}
			
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
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
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					request.setAttribute("pErrInfo", pErrInfo);
					forWard = "error";
				}else{
					message = "<Script>alert('" + pErrInfo.toShortString() + "!')</Script>";
					request.setAttribute("message", message);
					forWard = "toLogin";
				}
			}
		}
		return forWard;
	}

	
	/**
	 * ������½Action �����û���½����
	 * 
	 * @return String
	 * @throws Exception
	 */

	public String loginWithAnonymous() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String forWard = "";
		String message = "";
		
		// �õ�request��session����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try {
			UserInfo userInfo = new UserInfo();

			pErrPos = 1;
			
			pFlag = userInfoManageService.loginWithAnonymous(userInfo, pErrInfo);
			
			if (pFlag) {
				
				// ���û���Ϣ������session�Ự��
				session.setAttribute("userInfo", userInfo);
    			session.setAttribute("UCLKey","/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action");
    			forWard = SUCCESS;
			} 
			
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
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
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					request.setAttribute("pErrInfo", pErrInfo);
					forWard = "error";
				}else{
					message = "<Script>alert('" + pErrInfo.toShortString() + "!')</Script>";
					request.setAttribute("message", message);
					forWard = "toLogin";
				}
			}
		}
		return forWard;
	}
	
	
	/**
	 * �û��˳�
	 * 
	 * @return String
	 */
	public String loginOut() throws Exception {
		// �õ���ǰsession
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		// ���session������ɾ��session�е�userInfo��������session
		if (session != null) {
			session.removeAttribute("userInfo");
			session.invalidate();
		}
		return "out";
	}
}
