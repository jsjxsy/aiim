package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

public class UserInfoManageDWR {

	@Autowired
	private IUserInfoManageService userInfoManageService;//业务逻辑处理对象
	
	/**
	 * 保存当前用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean saveUserInfo(Map<String,String> formMap,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		UserInfo userInfo  = new UserInfo();;
		try {
			pErrPos = 1;
			if (pFlag) {
				if (this.getUserInfo(formMap, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户对象信息失败");
				}
			}
			if (pFlag) {
				if(userInfoManageService.saveUserInfo(userInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"保存用户信息失败：");
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
	
	/**获取userinfo对象
	 * @param formMap
	 * @param userInfo
	 * @param pErrInfo
	 * @return
	 */
	private boolean getUserInfo(Map<String,String> formMap, UserInfo userInfo,ErrInfo  pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			for (Iterator<String> iterator = formMap.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				
				if(key.equals("userID")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setUserID(Integer.parseInt(formMap.get(key)));
				}
				if(key.equals("userName")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setUserName(formMap.get(key));
				}
				if(key.equals("userPWD")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setUserPWD(formMap.get(key));
				}
				if(key.equals("realName")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setRealName(formMap.get(key));
				}
				if(key.equals("departmentID")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setDepartmentID(Integer.parseInt(formMap.get(key)));
				}
				if(key.equals("dutyID")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setDutyID(Integer.parseInt(formMap.get(key)));
				}
				if(key.equals("iDCardNo")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setIDCardNo((formMap.get(key)));
				}
				if(key.equals("email")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setEmail(formMap.get(key));
				}
				if(key.equals("tel")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setTel(formMap.get(key));
				}
				if(key.equals("address")&&!formMap.get(key).equals("")&&formMap.get(key)!= null){
					userInfo.setAddress(formMap.get(key));
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
		return pFlag;
	}
	
	/**
	 * 更新当前用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean updateUserInfo(Map<String,String> formMap,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		UserInfo userInfo = new UserInfo();
		try {
			//获取用户ID
			pErrPos = 1;			
			if (pFlag) {
				if (this.getUserInfo(formMap, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户对象信息失败");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户编号非法为空!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserName()== null ||userInfo.getUserName().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("获取用户名称非法为空!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getRealName()== null ||userInfo.getRealName().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("获取真实名称非法为空!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserPWD()== null ||userInfo.getUserPWD().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("获取密码非法为空!");
				}
			}
			
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.updateUserInfo(userInfo, pErrInfo)==false){	
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新用户信息失败：");
				}
			}			
			pErrPos = 2;
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
	
	/**
	 * 用户信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> findAllUserInfo(String IDCardNo,String RealName,DataPageInfo dataPageInfo, HttpSession session,HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<UserInfo> userInfos = new ArrayList<UserInfo>(); 
		Map<String, Object> userInfoQueryCondition = new HashMap<String, Object>();
		Map<String, Object> userInfoData =   new HashMap<String, Object>();
		try {
			//获取用户ID
			pErrPos = 1;			
			//调用业务逻辑：根据条件查询用户信息集合
			dataPageInfo.setPageSize(10);
			userInfoQueryCondition.put("IDCardNo", IDCardNo);
			userInfoQueryCondition.put("RealName",RealName);
			if (userInfoManageService.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo)== false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "查询用户失败:");
			}
			//获取分页信息
			pErrPos = 2;
			userInfoData.put("userInfos", userInfos);
			userInfoData.put("dataPageInfo", dataPageInfo);
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
		return userInfoData;
	}
	
	/**
	 * 更新当前用户信息
	 * @return
	 * @throws Exception
	 */
	public boolean modifyPassword(int UserID,String password,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		UserInfo userInfo = new UserInfo();
		try {
			//获取用户ID
			pErrPos = 1;			
			userInfo.setUserID(UserID);
			userInfo.setUserPWD(password);
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.modifyPassword(userInfo, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新用户信息失败：");
				}
			}			
			pErrPos = 2;
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
	
	/**
	 * 检查用户名是否唯一
	 * @return
	 * @throws Exception
	 */
	public IntegerEx checkUserName(String UserName,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		UserInfo userInfo = new UserInfo();
		IntegerEx exists = new IntegerEx();
		try {
			//获取用户ID
			pErrPos = 1;			
			userInfo.setUserName(UserName);
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.checkUserNameExists(userInfo, exists, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR中检查用户名是否存在失败:");
				}
			}			
			pErrPos = 2;
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
		return exists;
	}
	
	/**
	 * 检查证件号是否唯一
	 * @return
	 * @throws Exception
	 */
	public IntegerEx checkIDCardNo(String IDCardNo,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		UserInfo userInfo = new UserInfo();
		IntegerEx exists = new IntegerEx();
		try {
			//获取用户ID
			pErrPos = 1;			
			userInfo.setIDCardNo(IDCardNo);
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				if(userInfoManageService.checkIDCardNoExists(userInfo, exists, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR中检查证件号是否存在失败:");
				}
			}			
			pErrPos = 2;
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
		return exists;
	}
}
