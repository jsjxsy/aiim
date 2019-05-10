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
	private IUserInfoManageService userInfoManageService;//ҵ���߼��������
	
	/**
	 * ���浱ǰ�û���Ϣ
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
					pErrInfo.getContent().append("��ȡ�û�������Ϣʧ��");
				}
			}
			if (pFlag) {
				if(userInfoManageService.saveUserInfo(userInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����û���Ϣʧ�ܣ�");
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
	
	/**��ȡuserinfo����
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
	
	/**
	 * ���µ�ǰ�û���Ϣ
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
			//��ȡ�û�ID
			pErrPos = 1;			
			if (pFlag) {
				if (this.getUserInfo(formMap, userInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�û�������Ϣʧ��");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�û���ŷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserName()== null ||userInfo.getUserName().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ�û����ƷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getRealName()== null ||userInfo.getRealName().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ��ʵ���ƷǷ�Ϊ��!");
				}
			}
			
			if (pFlag) {
				if (userInfo.getUserPWD()== null ||userInfo.getUserPWD().equals("") ) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ����Ƿ�Ϊ��!");
				}
			}
			
			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				if(userInfoManageService.updateUserInfo(userInfo, pErrInfo)==false){	
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����û���Ϣʧ�ܣ�");
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
	 * �û���Ϣ
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
			//��ȡ�û�ID
			pErrPos = 1;			
			//����ҵ���߼�������������ѯ�û���Ϣ����
			dataPageInfo.setPageSize(10);
			userInfoQueryCondition.put("IDCardNo", IDCardNo);
			userInfoQueryCondition.put("RealName",RealName);
			if (userInfoManageService.findUserInfosByCondition(userInfoQueryCondition,dataPageInfo,userInfos, pErrInfo)== false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ�û�ʧ��:");
			}
			//��ȡ��ҳ��Ϣ
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
	 * ���µ�ǰ�û���Ϣ
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
			//��ȡ�û�ID
			pErrPos = 1;			
			userInfo.setUserID(UserID);
			userInfo.setUserPWD(password);
			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				if(userInfoManageService.modifyPassword(userInfo, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����û���Ϣʧ�ܣ�");
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
	 * ����û����Ƿ�Ψһ
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
			//��ȡ�û�ID
			pErrPos = 1;			
			userInfo.setUserName(UserName);
			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				if(userInfoManageService.checkUserNameExists(userInfo, exists, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR�м���û����Ƿ����ʧ��:");
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
	 * ���֤�����Ƿ�Ψһ
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
			//��ȡ�û�ID
			pErrPos = 1;			
			userInfo.setIDCardNo(IDCardNo);
			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				if(userInfoManageService.checkIDCardNoExists(userInfo, exists, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "DWR�м��֤�����Ƿ����ʧ��:");
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
