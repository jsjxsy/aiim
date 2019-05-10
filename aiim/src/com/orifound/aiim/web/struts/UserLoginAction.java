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
 * @author 杨丰
 * 
 */
public class UserLoginAction extends ActionSupport {

	// 使用Log4j
	static Log logger = LogFactory.getLog(UserLoginAction.class);

	private static final long serialVersionUID = 1L;
	// 用户名
	private String userName;
	// 密码
	private String password;
	// 处理登陆业务逻辑类
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
	 * 登陆Action 处理用户登陆请求
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

		// 得到request和session对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setUserName(userName);
			userInfo.setUserPWD(password);

			pErrPos = 1;
			
			pFlag = userInfoManageService.login(userInfo, pErrInfo);

			if (pFlag) {				
				
				// 将用户信息保存在session会话中
				session.setAttribute("userInfo", userInfo);
    			forWard = SUCCESS;
			} else{
				System.out.println(pErrInfo.toString());
			}
			
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
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
	 * 匿名登陆Action 处理用户登陆请求
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
		
		// 得到request和session对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		
		try {
			UserInfo userInfo = new UserInfo();

			pErrPos = 1;
			
			pFlag = userInfoManageService.loginWithAnonymous(userInfo, pErrInfo);
			
			if (pFlag) {
				
				// 将用户信息保存在session会话中
				session.setAttribute("userInfo", userInfo);
    			session.setAttribute("UCLKey","/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action");
    			forWard = SUCCESS;
			} 
			
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
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
	 * 用户退出
	 * 
	 * @return String
	 */
	public String loginOut() throws Exception {
		// 得到当前session
		HttpSession session = ServletActionContext.getRequest().getSession(
				false);
		// 如果session存在则删除session中的userInfo对像并销毁session
		if (session != null) {
			session.removeAttribute("userInfo");
			session.invalidate();
		}
		return "out";
	}
}
