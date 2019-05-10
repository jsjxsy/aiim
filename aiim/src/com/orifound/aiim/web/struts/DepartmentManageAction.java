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
	
	private int[] departIDs={};//部门ID集合，用于批量删除部门信息
	
	public int[] getDepartIDs() {
		return departIDs;
	}
	
	public void setDepartIDs(int[] departIDs) {
		this.departIDs = departIDs;
	}
	
	
	/**
	 * 批量删除部门信息
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
			
			//调用业务逻辑：删除选中的部门信息
			if (pFlag) {
				for(int i = 0;i<departIDs.length;i++){
					depart = new DepartmentInfo();
					depart.setID(departIDs[i]);
					if(departmentInfoManageService.deleteDepartmentInfo(departmentInfo, pErrInfo)==false){
                       System.out.println("departID: " + depart.getID() +" 删除成功！");
						result = "success";
					}else{
						pFlag = false;//调用业务逻辑出错，标识为失败
					}
				}
			}	
			
			pErrPos = 2;
			//调用业务逻辑：查找查询所有用户信息
			if (pFlag) {
				{
					//生成测试数据
					setDepartmentInfos(departmentInfos);

					result = "success";
				}

			}
			
			pErrPos = 3;
			//将查询结果等信息返回到页面
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
	 * 更新当前用户信息
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
			//获取用户ID
			pErrPos = 1;			
			
			//调用业务逻辑：更新部门信息
			if (pFlag) {
				if(departmentInfoManageService.updateDepartmentInfo(departmentInfo, pErrInfo)==false){					
System.out.println("更新部门信息："+"ID:" + departmentInfo.getID()+ " name: "+departmentInfo.getName()+" DutyID:"+ departmentInfo.getRemark());
					message = "更新成功！";	
					result = "success";
				}else{
					pFlag = false;//调用业务逻辑出错，标识为失败
				}
			}			
			pErrPos = 2;
			//将查询结果等信息返回到页面
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
	 * 保存当前用户信息
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
			
			//调用业务逻辑：保存部门信息
			if (pFlag) {
				if(departmentInfoManageService.saveDepartmentInfo(departmentInfo, pErrInfo)==false){
					departmentInfo.setID((int)((Math.random()*100)));
System.out.println("保存部门信息："+"ID:" + departmentInfo.getID()+ " name: "+departmentInfo.getName()+" DutyID:"+ departmentInfo.getRemark());					
					message = "保存成功！";	
					result = "success";
				}else{
					pFlag = false;//调用业务逻辑出错，标识为失败
				}
			}			
			pErrPos = 2;
			//将查询结果等信息返回到页面
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
//	 * 通过真实姓名查询用户信息
//	 * @param pErrInfo 处理失败的错误原因描述
//	 * @return 处理成功返回true，否则返回false
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
//			//开始处理 1...
//			pErrPos = 1;
//			
//
//			//调用业务逻辑：根据条件查询用户信息集合
//			if (pFlag) {
//				if(userInfoManageService.findUserByRealName(queryRealName, userInfos, pErrInfo)){
//					//生成测试数据
//					setUserInfos(userInfos);
//System.out.println("queryRealName: " +queryRealName +"userNum : "+ userInfos.size());
//					result = "success";
//				}else{
//					pFlag = false;//调用业务逻辑出错，标识为失败
//				}
//
//			}			
//			pErrPos = 2;
//			//将查询结果等信息返回到页面
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
	 * 通过用户编号查找部门信息
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
			//获取用户ID
			pErrPos = 1;			
			int ID = Integer.parseInt(request.getParameter("ID"));
			if(ID==0){
				pFlag = false;
			}
			
			pErrPos = 2;
			//调用业务逻辑：根据条件查询部门信息集合
			if (pFlag) {
				{
				departmentInfo.setID(ID);
				departmentInfo.setName("部门测试名"+ID);
				departmentInfo.setRemark("备注信息"+ ID);
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
	
	
	
	//查询所有部门信息
	public String findAllDepartmentInfos() throws Exception {
		String result = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		try {
			//获取用户ID
			pErrPos = 1;			
		
			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				{
					setDepartmentInfos(departmentInfos);
System.out.println("查询所有部门成功！");
					result = "success";
				}
			}	
			
			pErrPos = 2;
			//将查询结果等信息返回到页面
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

	
	//生成测试数据
	public void setDepartmentInfos(List<DepartmentInfo> departmentInfos){
		DepartmentInfo departmentInfo = null;	
		for(int i = 1;i<10;i++){
			departmentInfo = new DepartmentInfo();
			departmentInfo.setID(200+i);
			departmentInfo.setName("测试部门" + i );
			departmentInfo.setRemark("备注信息"+i );
			departmentInfos.add(departmentInfo);
		}
	}
	
	
	
	
}
