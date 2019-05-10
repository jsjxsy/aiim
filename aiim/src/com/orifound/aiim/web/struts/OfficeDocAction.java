package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.web.testDate.ReturnTree;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;

public class OfficeDocAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(OfficialArchivesInfoManageAction.class);
	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService; // 公文档案管理业务类
	
	/**
	 * 返回的json串
	 */
	private String jsonResult;
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	private int [] NBXHs;
	
	public int[] getNBXHs() {
		return NBXHs;
	}

	public void setNBXHs(int[] hs) {
		NBXHs = hs;
	}
	
	/**
	 * 分页对象
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * 获得文档模板类别树
	 * @return
	 * @throws Exception
	 */
	public String getTypeTreeForTemplatesManager() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tree = ReturnTree.getWDTypeTree();
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","officeDocAction_findTemplates.action");
		return SUCCESS;
	}
	
	/**
	 * 查找所有模板
	 * @return
	 * @throws Exception
	 */
	public String findTemplates() throws Exception{
	//	String massege = "";
		//boolean pFlag = false;
	//	ErrInfo pErrInfo = new ErrInfo();
	//	HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("find");
		dataPageInfo.setPageSize(10);
		dataPageInfo.setRowCount(100);
		return "toTemplates";
	}
	
	/**
	 * 删除模板 Ajax调用
	 * @return
	 * @throws Exception
	 */
	public String delTemplates() throws Exception{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

        System.out.println("delete");
		response.getWriter().print("删除成功！");
		return null;
	}

	/**
	 * 删除模板 Ajax调用
	 * @return
	 * @throws Exception
	 */
	public String addTemplate() throws Exception{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		System.out.println("add");
		response.getWriter().print("保存成功！");
		return null;
	}

	/**
	 * 获得收发文类别树 并指定处理函数
	 * @return
	 * @throws Exception
	 */
	public String getTypeTreeForDocManager() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tree = ReturnTree.getWDTypeTree();
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","officeDocAction_findDoc.action?type="+request.getParameter("type"));
		return "ownLeft";
	}
	
	/**
	 * 查询收发文管理
	 * @return
	 * @throws Exception
	 */
	public String findDoc() throws Exception{
		System.out.println("--------查找收发文---------------");
		String forWard = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		
		dataPageInfo.setPageSize(10);
		dataPageInfo.setRowCount(100);
		
		String type = request.getParameter("type");
	
		request.setAttribute("type", type);
		return forWard;
	}
	
	/**
	 * 添加收发文
	 * @return
	 * @throws Exception
	 */
	public String addDoc() throws Exception{
		System.out.println("--------保存收发文---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println(request.getParameter("NBXH"));
		String type = request.getParameter("type");
		System.out.println(type);
		if("SW".equals(type)){
			System.out.println("收文");
		}else if("FW".equals(type)){
			System.out.println("发文");
		}
		//request.setAttribute("type", type);
		return null;
	}
	
	/**
	 * 删除收发文
	 * @return
	 * @throws Exception
	 */
	public String delDoc() throws Exception{
		System.out.println("--------删除收发文---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		if("SW".equals(type)){
			System.out.println("收文");
		}else if("FW".equals(type)){
			System.out.println("发文");
		}
		
		if(NBXHs != null){
			for (int NBSH : NBXHs) {
				System.out.println(NBSH);
			}	
		}
		
		response.getWriter().print("删除成功！");
		request.setAttribute("type", type);
		return null;
	}
	
	
	/**
	 * 根据ID查找收发文
	 * @return
	 * @throws Exception
	 */
	public String findDocBuyId() throws Exception{
		System.out.println("--------按NBXH查找收发文---------------");
		HttpServletRequest request = ServletActionContext.getRequest();

		String type = request.getParameter("type");
        String NBXH = request.getParameter("NBXH");
        
        System.out.println(type+"  "+NBXH);
        jsonResult = "{'NBXH':'"+NBXH+"'}";
		request.setAttribute("type", type);
		return SUCCESS;
	}

	/**
	 * 归档
	 * @return
	 * @throws Exception
	 */
	public String archivingDoc() throws Exception{
		System.out.println("-------------公文归档-----------------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
        
		String type = request.getParameter("type");
		System.out.println(request.getParameter("archivesTypeId"));
		if("SW".equals(type)){
			System.out.println("收文");
		}else if("FW".equals(type)){
			System.out.println("发文");
		}
		
		if(NBXHs != null){
			for (int NBSH : NBXHs) {
				System.out.println(NBSH);
			}	
		}
		
		response.getWriter().print("归档成功！");
		return null;
	}
	
	/**
	 * 根据页面条件查询已著录完成的档案信息<br/> 
	 * 适用功能档案管理
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findOfficialArchivesInfosByCondition() throws Exception {
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String resultStr="";
		HttpServletRequest request = ServletActionContext.getRequest();
		List<OfficialArchivesInfo> officialArchivesInfos = null;
		Map<String, OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions = null;
		Map<String, ArchivesTypeDataItem> dataItems = null;
		Map<String, ArchivesTypeDataItem> dataItemsForAll = null;
		try {
			//获得页面参数
			pErrPos = 1;
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));

			
			OfficialArchivesType officialArchivesType = new OfficialArchivesType(officialArchivesTypeID);

			dataPageInfo.setPageSize(10);

			pErrPos = 2;
			officialArchivesInfoQueryConditions = new TreeMap<String, OfficialArchivesInfoQueryCondition>();
			OfficialArchivesInfoQueryCondition officialArchivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// 取出所有的页面传入的条件的名字数组
			String[] values = null;// 保存某个数据项里面的vlue值
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;
			//从系统数据中取出可用于著录查询的著录项
			dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForUseQuery();
			dataItemsForAll=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			
			//开始构造查询条件集合
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// 取出条件的名字
				if (!"officialArchivesTypeID".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// 取出条件所对应的值
					// 该值为dataItem的Id
					dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem
					if (dataItem != null && values.length > 1) {//两个值
						
						if (values[0] != "" && values[1] != "") {//都不为空
							// 把值放入archivesInfoQueryCondition
						    officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							officialArchivesInfoQueryCondition.setMinValue(values[0]);
							officialArchivesInfoQueryCondition.setMaxValue(values[1]);
							officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}else{//有一为空
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							if(values[0] == ""){
								officialArchivesInfoQueryCondition.setValue(values[1]);
							}else{
								officialArchivesInfoQueryCondition.setValue(values[0]);
							}
							officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}
						
					}else if(dataItem != null && values[0] != ""){//只有一个值
						officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
						officialArchivesInfoQueryCondition.setValue(values[0]);
						officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
					}
				}
			}
			
			//固定添加档案形成部门编号查询条件
			String FormationDepartmentID = request.getParameter("FormationDepartmentID");
			if(FormationDepartmentID == null || FormationDepartmentID.equals("")){
				pErrInfo.getContent().append("档案形成部门编号非法为空！");
			}
			
			dataItem=dataItemsForAll.get(EnumSystemDataItem.档案形成部门编号.getEnumValue());
			officialArchivesInfoQueryCondition=new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
			officialArchivesInfoQueryCondition.setValue(FormationDepartmentID);
			officialArchivesInfoQueryConditions.put(EnumSystemDataItem.档案形成部门编号.getEnumValue(), officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition对象
			//添加档案档案密级查询条件
			dataItem=dataItemsForAll.get(EnumSystemDataItem.档案密级编号.getEnumValue());
			officialArchivesInfoQueryCondition=new OfficialArchivesInfoQueryCondition(dataItem);
			String SecrecyID=String.valueOf(SystemInitializer.getInstance().getOpenArchivesSecrecy().getID());
			officialArchivesInfoQueryCondition.setValue(SecrecyID);
			officialArchivesInfoQueryConditions.put(EnumSystemDataItem.档案密级编号.getEnumValue(), officialArchivesInfoQueryCondition);
			
			
			
			
			// 调用业务逻辑
			pErrPos = 3;
			officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			if (officialArchivesInfoManageService.findOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID), 
					new ArrayList<OfficialArchivesInfoQueryCondition>(officialArchivesInfoQueryConditions.values()), dataPageInfo, officialArchivesInfos, pErrInfo) == false)
			{
				pFlag = false;
				resultStr = "error";
				pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
			}

			request.setAttribute("dataItems", SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("FormationDepartmentID", FormationDepartmentID);
			request.setAttribute("officialArchivesInfos", officialArchivesInfos);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, officialArchivesInfoQueryConditions));
			resultStr="success";
		} catch (Exception e) {
			resultStr = "error";
			// 异常错误
			e.printStackTrace();
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

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;

				if (pErrInfo.getException() != null) {
					logger.error(pErrInfo.getContent());
					request.setAttribute("pErrInfo", pErrInfo);
					resultStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""
							+ pErrInfo.toShortString() + "!\");</script>");
				}
			}
			// 销毁局部变量
			throwable = null;
		}
		return resultStr;
	}
	
	/**
	 * 根据分类得到公文著录页面查询表单的HTML代码
	 * 
	 * @param 
	 * @return string 跳转的页面
	 */
	public String getHtmlCodeForInputQuery() throws Exception {
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		Integer officialArchivesTypeID = null;
		Integer departmentInfoID = null;
		String reslutStr="";
		String htmlCode = "";
		Map<String, ArchivesTypeDataItem> dataItems = null;
		try {
		    //判断officialArchivesTypeID是否非法
			if(pFlag){
			 pErrPos = 1;
			 if(request.getParameter("officialArchivesTypeID") == null
			    &&request.getParameter("officialArchivesTypeID").equals("")){
			    pFlag = false;
			    pErrInfo.getContent().append("officialArchivesTypeID丢失！");
			  }else{
				  officialArchivesTypeID = Integer.parseInt(request
							.getParameter("officialArchivesTypeID"));
			  }
			}
			
			if(pFlag){
				 pErrPos = 2;
				 String strDepartmentInfoID=request.getParameter("FormationDepartmentID");
				 if(strDepartmentInfoID == null || strDepartmentInfoID.equals("")){
					 pFlag = false;
					 pErrInfo.getContent().append("FormationDepartmentID丢失！");
				  }else{
					  departmentInfoID = Integer.parseInt(request
								.getParameter("FormationDepartmentID"));
				  }
				}
		
			
			
			// 判断是否有可用于著录查询的数据项
			if (pFlag) {
				dataItems = SystemInitializer.getInstance()
						.getOfficialArchivesTypes().get(officialArchivesTypeID)
						.getDataItemsForUseQuery();
				
				pErrPos = 3;
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("此分类下没有可用著录查询的数据项！");
				}
			}

			// 得到html代码
			if (pFlag) {
				pErrPos = 4;
				htmlCode = GenerateHtmlCodeUtil.GenerateOfficialHtmlCode(dataItems,null);
			}
			request.setAttribute("dataItems", SystemInitializer.getInstance()
					.getOfficialArchivesTypes().get(officialArchivesTypeID)
					.getDataItemsForListDisplay());
			
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("FormationDepartmentID", departmentInfoID);
			reslutStr="success";

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

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;

				if (pErrInfo.getException() != null) {
					logger.error(pErrInfo.getContent());
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					htmlCode = "<script>alert(\"" + pErrInfo.toShortString()
							+ "!\");</script>";
					request.setAttribute("htmlCode", htmlCode);
				}
			}
			throwable = null;
		}

		return reslutStr;
	}

}
