package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.impl.DepartmentInfoManageServiceImpl;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

public class DepartmentManageAction extends ActionSupport implements
		ModelDriven<DepartmentInfo> {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7313535545645987410L;
	
	private IDepartmentInfoManageService departmentInfoManageService = new DepartmentInfoManageServiceImpl();
	
	


	public IDepartmentInfoManageService getDepartmentInfoManageService() {
		return departmentInfoManageService;
	}


	public void setDepartmentInfoManageService(
			IDepartmentInfoManageService departmentInfoManageService) {
		this.departmentInfoManageService = departmentInfoManageService;
	}




	private DepartmentInfo departmentInfo = new DepartmentInfo();
	public DepartmentInfo getModel(){
		return departmentInfo;
	}
	
	private int[] departIDs={};//����ID���ϣ���������ɾ��������Ϣ
	
	public int[] getDepartIDs() {
		return departIDs;
	}
	
	public void setDepartIDs(int[] departIDs) {
		this.departIDs = departIDs;
	}
	
	
	/**
	 * ����ɾ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String deleteDepartmentInfos() throws Exception{
		String result = "";	
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		
		DepartmentInfo depart = null;
		try {			
			pErrPos = 1;
			
			//����ҵ���߼���ɾ��ѡ�еĲ�����Ϣ
			if (pFlag) {
				for(int i = 0;i<departIDs.length;i++){
					depart = new DepartmentInfo();
					depart.setID(departIDs[i]);
					if(departmentInfoManageService.deleteDepartmentInfo(departmentInfo, pErrInfo)==false){
                       System.out.println("departID: " + depart.getID() +" ɾ���ɹ���");
						result = "success";
					}else{
						pFlag = false;//����ҵ���߼�������ʶΪʧ��
					}
				}
			}	
			
			pErrPos = 2;
			//����ҵ���߼������Ҳ�ѯ�����û���Ϣ
			if (pFlag) {
				{
					//���ɲ�������
					setDepartmentInfos(departmentInfos);

					result = "success";
				}

			}
			
			pErrPos = 3;
			//����ѯ�������Ϣ���ص�ҳ��
			if(pFlag){
				request.setAttribute("departmentInfos",departmentInfos);
				request.setAttribute("recordSize",departmentInfos.size());
			}
		} catch (Exception e) {
			result = "error";
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
		return result;
	}
	
	
	/**
	 * ���µ�ǰ�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateDepartmentInfo() throws Exception{
		String result = "";
		String message ="";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		//UserInfo userInfo = new UserInfo();
		try {
			//��ȡ�û�ID
			pErrPos = 1;			
			
			//����ҵ���߼������²�����Ϣ
			if (pFlag) {
				if(departmentInfoManageService.updateDepartmentInfo(departmentInfo, pErrInfo)==false){					
System.out.println("���²�����Ϣ��"+"ID:" + departmentInfo.getID()+ " name: "+departmentInfo.getName()+" DutyID:"+ departmentInfo.getRemark());
					message = "���³ɹ���";	
					result = "success";
				}else{
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
				}
			}			
			pErrPos = 2;
			//����ѯ�������Ϣ���ص�ҳ��
			if(pFlag){
				//request.setAttribute("userInfo",userInfo);
				request.setAttribute("message",message);
			}
		} catch (Exception e) {
			result = "error";
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
		return result;
		
	}
	
	
	/**
	 * ���浱ǰ�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String saveDepartmentInfo() throws Exception{
		String result = "";
		String message ="";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		try {
			
			pErrPos = 1;			
			
			//����ҵ���߼������沿����Ϣ
			if (pFlag) {
				if(departmentInfoManageService.saveDepartmentInfo(departmentInfo, pErrInfo)==false){
					departmentInfo.setID((int)((Math.random()*100)));
System.out.println("���沿����Ϣ��"+"ID:" + departmentInfo.getID()+ " name: "+departmentInfo.getName()+" DutyID:"+ departmentInfo.getRemark());					
					message = "����ɹ���";	
					result = "success";
				}else{
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
				}
			}			
			pErrPos = 2;
			//����ѯ�������Ϣ���ص�ҳ��
			if(pFlag){
				request.setAttribute("message",message);
			}
		} catch (Exception e) {
			result = "error";
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
		return result;
		
	}
	
	
	
//	
//	/**
//	 * ͨ����ʵ������ѯ�û���Ϣ
//	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
//	 * @return ����ɹ�����true�����򷵻�false
//	 */
//	public String findUserByRealName() throws Exception {
//		String result = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		ErrInfo pErrInfo = new ErrInfo();
//		Throwable throwable = new Throwable();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		Map<Integer, UserInfo> userInfos = new HashMap<Integer, UserInfo>();
//		try {
//			//��ʼ���� 1...
//			pErrPos = 1;
//			
//
//			//����ҵ���߼�������������ѯ�û���Ϣ����
//			if (pFlag) {
//				if(userInfoManageService.findUserByRealName(queryRealName, userInfos, pErrInfo)){
//					//���ɲ�������
//					setUserInfos(userInfos);
//System.out.println("queryRealName: " +queryRealName +"userNum : "+ userInfos.size());
//					result = "success";
//				}else{
//					pFlag = false;//����ҵ���߼�������ʶΪʧ��
//				}
//
//			}			
//			pErrPos = 2;
//			//����ѯ�������Ϣ���ص�ҳ��
//			if(pFlag){
//				request.setAttribute("userInfos",userInfos);	
//				request.setAttribute("recordSize",userInfos.size());
//			}
//		} catch (Exception e) {
//			result = "error";
//			pFlag = false;
//			pErrInfo.getContent().append(e.toString());
//			pErrInfo.setException(e);
//		} finally {
//			if (pFlag == false && pErrInfo.getContent().length() > 0) {
//				StackTraceElement[] stackTraceElements = throwable
//						.getStackTrace();
//				StringBuilder tempBuilder = new StringBuilder(
//						stackTraceElements[0].getClassName());
//				tempBuilder.append(".");
//				tempBuilder.append(stackTraceElements[0].getMethodName());
//				tempBuilder.append("-->");
//				tempBuilder.append(" ErrPos: ");
//				tempBuilder.append(pErrPos);
//				tempBuilder.append(", ");
//
//				pErrInfo.getContent().insert(0, tempBuilder.toString());
//				tempBuilder = null;
//			}
//		}
//		return result;
//	}
//	
	
	/**
	 * ͨ���û���Ų��Ҳ�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findDepartmentInfoByID() throws Exception{
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		try {
			//��ȡ�û�ID
			pErrPos = 1;			
			int ID = Integer.parseInt(request.getParameter("ID"));
			if(ID==0){
				pFlag = false;
			}
			
			pErrPos = 2;
			//����ҵ���߼�������������ѯ������Ϣ����
			if (pFlag) {
				{
				departmentInfo.setID(ID);
				departmentInfo.setName("���Ų�����"+ID);
				departmentInfo.setRemark("��ע��Ϣ"+ ID);
				result = "success";
				}
			}
		} catch (Exception e) {
			result = "error";
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
		return result;
		
	}
	
	
	
	//��ѯ���в�����Ϣ
	public String findAllDepartmentInfos() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		try {
			//��ȡ�û�ID
			pErrPos = 1;			
		
			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				{
					setDepartmentInfos(departmentInfos);
System.out.println("��ѯ���в��ųɹ���");
					result = "success";
				}
			}	
			
			pErrPos = 2;
			//����ѯ�������Ϣ���ص�ҳ��
			if(pFlag){
				request.setAttribute("departmentInfos",departmentInfos);
				request.setAttribute("recordSize",departmentInfos.size());
			}
		} catch (Exception e) {
			result = "error";
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return result;
	}

	
	//���ɲ�������
	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos){
		DepartmentInfo departmentInfo = null;	
		for(int i = 1;i<10;i++){
			departmentInfo = new DepartmentInfo();
			departmentInfo.setID(200+i);
			departmentInfo.setName("���Բ���" + i );
			departmentInfo.setRemark("��ע��Ϣ"+i );
			departmentInfos.add(departmentInfo);
		}
	}
	
	
	
	
}
