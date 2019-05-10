package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IArchivesInfoQueryService;
import com.orifound.aiim.bll.service.IAttachedFileManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IUserDefinedSearchManageService;
import com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.AttachedFileAccessControlServiceImpl;
import com.orifound.aiim.bll.service.impl.UseScopesAccessControlServiceImpl;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserDefinedSearch;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.testDate.ReturnTree;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;
import com.orifound.aiim.web.util.WebCommonUtil;
import com.orifound.commons.acservice.IAttachedFileAccessControlService;
import com.orifound.commons.acservice.IUseScopesAccessControlService;

/**
 * 综合查询Action
 * @author Administrator
 *
 */
public class IntegratedQueryAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	
	static Log logger = LogFactory.getLog(IntegratedQueryAction.class);
	/**
	 * 分页信息类
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	/**
	 * 通过注解，注入划控访问控制管理服务
	 */
	private IUseScopesAccessControlService useScopesAccessControlService;
	
	/**
	 * 通过注解，注入系统配置管理服务
	 */
	@Autowired
	private IConfigManageService configManageService ;

	/**
	 * 通过注解，注入档案查询服务类
	 */
	@Autowired
	private IArchivesInfoQueryService archivesInfoQueryService;
	
	/**
	 * 通过注解，注入用户自定义查询管理服务类
	 */
	@Autowired
	private IUserDefinedSearchManageService  userDefinedSearchManageService;
	
	/**
	 * 档案原文电子文件访问控制管理服务类
	 */
	@Autowired
	private IAttachedFileAccessControlService attachedFileAccessControlService;
	
	/**
	 * 通过注解，注入原文管理服务类
	 */
	@Autowired
	private IAttachedFileManageService attachedFileManageService; 

	/**
	 * 分类查询:得到档案分类树
	 * @return 
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action")
	public String getArchivesTypeTree()throws Exception{
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//从application中取出所有档案分类
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
			
			
			//将档案分类集合构造出生成树所需的javascript代码
			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
			
			//将数据通过request传到页面
			request.setAttribute("tree", tree);
			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return SUCCESS;
	}
	
	/**
	 * 关键字查询:得到档案分类树
	 * @return 
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action")
//	public String getKeyQueryArchivesTypeTree()throws Exception{
//		String htmlCode = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		Throwable throwable = new Throwable();
//		ErrInfo pErrInfo = new ErrInfo();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		ServletContext application = ServletActionContext.getServletContext();
//		
//		try {		
//			pErrPos = 1;			
//			//从application中取出所有档案分类
//			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
//						
//			//将档案分类集合构造出生成树所需的javascript代码
//			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
//			
//			//将数据通过request传到页面
//			request.setAttribute("tree", tree);
//			
//			request.setAttribute("proceseAction", "integratedQueryAction_findArchivesByKeyWord.action");
//			
//		} catch (Exception e) {
//			//异常错误
//			pFlag = false;
//			pErrInfo.getContent().append(e.toString());
//			pErrInfo.setException(e);
//		} finally {
//			//拼接详细的错误描述信息，包括类名/方法名/错误位置
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
//				
//				if(pErrInfo.getException() != null){
//					logger.error(pErrInfo.toString());
//					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
//				}else{
//					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
//				}
//				request.setAttribute("htmlCode",htmlCode);
//			}
//		}
//		return SUCCESS;
//	}
	
	/**
	 * 自定义条件查询 : 得到指定用户所有自定义的查询树
	 * @return
	 * @throws Exception
	 */
	public String getUserDefinedSearchTree() throws Exception{
		String htmlCode = "";
		String forWard = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<UserDefinedSearch> userDefinedSearchs = new ArrayList<UserDefinedSearch>();
		
		try {		

			pErrPos = 1;
			//检查用户是否存在
			if(userInfo==null){
				pFlag = false;				
			}

			//调用业务逻辑
			if(pFlag){
				pErrPos = 2;
				if(userDefinedSearchManageService.findUserDefinedSearchsByUserID(userInfo.getUserID(), userDefinedSearchs, pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().insert(0,"获取用户自定义的查询条件失败：");
				}else{
					forWard = "toDefinedQuery";
				}	
			}
	
			//将档案分类集合构造出生成树所需的javascript代码
			String tree  = CreateTreeUtil.getArchivesUserDefinedSearchTree(userDefinedSearchs);

			//将数据通过request传到页面
			request.setAttribute("tree", tree);
			
			request.setAttribute("proceseAction", "integratedQueryAction_findArchivesByDefinedQuery.action");
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return forWard;
		
	} 
	

	
	/**
	 * 跨门类查询: 得到带CheckBox的档案类型树	 
	 * @return
	 * @throws Exception
	 */
//	@UCLkey(value = "guest")
	public String getArchivesTypeTreeWithCheckBox() throws Exception{	
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//从application中取出所有档案分类
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
		
			//将档案分类集合构造出生成树所需的javascript代码
			String tree  = CreateTreeUtil.getArchivesTypeCheckBoxTree(archivesTypes);		
			
			//将数据通过request传到页面
			request.setAttribute("tree", tree);			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return "checkBox_Tree";
	}
	
	/**
	 * 跨门类查询: 得到带CheckBox的档案类型树	 
	 * @return
	 * @throws Exception
	 */
//	@UCLkey(value = "guest")
	public String getDefinedQueryArchivesTypeTreeWithCheckBox() throws Exception{	
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//从application中取出所有档案分类
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
			
			//将档案分类集合构造出生成树所需的javascript代码
			String tree  = CreateTreeUtil.getArchivesTypeCheckBoxTree(archivesTypes);		
			
			//将数据通过request传到页面
			request.setAttribute("tree", tree);			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return "toDlg_DefinedQuery";
	}
	
	/**
	 * 自定义条件查询<br/>
	 * 得到当前的用户的自定义条件查询树
	 * @return
	 * @throws Exception
	 */
	//@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getConditionTree.action")//新添
//	public String getConditionTree() throws Exception{
//        HttpServletRequest request = ServletActionContext.getRequest();
//		
//		String tree = ReturnTree.getConditionTree();
//		
//		request.setAttribute("tree", tree);
//		request.setAttribute("proceseAction", "");
//		
//		return "";
//	}
	
	
	
	
	/**
	 * 分类查询-->单击树节点事件：得到指定分类下可查询的数据项
	 */
	public String getFindHtmlCodeBuyArchivesTypeId() throws Exception {
		System.out.println("getFindHtmlCodeBuyArchivesTypeId is successful!!!!");
		String htmlCode = "";		
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId;//档案分类编号	
	
		try {
			
			pErrPos = 1;
			//数据验证
			if( request.getParameter("nodeId")==null || "".equals(request.getParameter("nodeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("找不到档案分类编号，nodeId传递失败");
			}
			
			
			//根据档案类别生成相应的查询条件
			if(pFlag){
				pErrPos = 2;
				archivesTypeId = Integer.parseInt(request.getParameter("nodeId"));
				htmlCode = this.getHtmlCodeByType(archivesTypeId,null);
				//将参数传至页面
				request.setAttribute("htmlCode", htmlCode);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
				request.setAttribute("archivesTypeId", archivesTypeId);
				request.setAttribute("initialPage",true );
			}			
			
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				System.out.println("error:"+pErrInfo.toString());
			}
		}
		return "toFLCX";
	}

	/**
	 * 处理跨门类查询树单击事件，得到分类ID 
	 *  得到此分类下可查询的数据项
	 * @param pErrInfo 返回处理失败的错误原因描述
	 * @return
	 */
	public String getFindHtmlCodeBuyArchivesTypeIds(int [] archivesTypeIds,HttpSession session) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String htmlCode = "";
		List<ArchivesType> archivesTypes = null;
		TreeMap<String, ArchivesTypeDataItem> archivesTypeDataItems = null;
		try {
			pErrPos = 1;
			//HttpSession session = ServletActionContext.getRequest().getSession(false);
			session.setAttribute("archivesTypeIds", archivesTypeIds);			
			
			
			if(archivesTypeIds == null || archivesTypeIds.length == 0){
				pFlag = false;
				pErrInfo.getContent().append("未选择档案类型！");
			}

			//构造ArchivesType集合并调用业务逻辑
			if(pFlag){
				pErrPos = 2;
				archivesTypes = new ArrayList<ArchivesType>();
				ArchivesType archivesType = null;
				for (int archivesTypeId : archivesTypeIds) {
					archivesType = new ArchivesType();
					archivesType.setID(archivesTypeId);
					archivesTypes.add(archivesType);
				}
				archivesTypeDataItems = new TreeMap<String, ArchivesTypeDataItem>();
			}
			
			if(pFlag){
				pErrPos = 3;
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(archivesTypeDataItems, null);
			}
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
			}
		}
		return htmlCode;
	}
	
	/**
	 * 分类查询--> 
	 * 简单查询:根据档案类型和条件查找档案信息<br/>
	 * 高级查询:根据档案类型和条件查找档案信息<br/>
	 * @return 档案信息列表
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_findArchivesByCondition.action")//新添
	@SuppressWarnings("unchecked")
	public String findArchivesByCondition() throws Exception{
		int pErrPos = 0;
		boolean pFlag= true;
		boolean pAdvanceQueryFlag = false;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String advanceQueryCondition = "";
		String forWard = "";
		String message = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();		
		boolean inputTrue = false;//输入为空标识，用于过滤掉那些无输入的字段
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		StringBuilder querySQL = new StringBuilder();//将查询条件集合转化成SQL语句的WHERE条件
	

		try{
			
			//数据验证		
			if(archivesInfoQueryService==null){
				pFlag = false;
				pErrInfo.getContent().append("综合查询服务类注入失败。");
			}
			
			//检查并获取高级查询条件			
			if(request.getParameter("advanceQueryCondition") == null || "".equals(request.getParameter("advanceQueryCondition"))){
				advanceQueryCondition = "";
			}else{//接收参数advanceQueryCondition成功
				advanceQueryCondition = request.getParameter("advanceQueryCondition").trim();
			}
						
			pErrPos = 1;
			//根据档案分类编号，构造档案分类对象			
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号获取失败！");
			}else{//接收参数archivesTypeId成功
				archivesType = new ArchivesType();
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
				//archivesType.setID(archivesTypeId);
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
			}
			
			//判断当前所选择的档案分类是不是最末节点，分别处理这两类事件
			if (pFlag) {				
				if(archivesType.getEndFlag()==true){//当前点击的是最末节点
					archivesTypes.add(archivesType);				
				}else{//当前点击的不是末节点，就获该节点下的所有档案分类对象
					Map<Integer, ArchivesType> endArchivesTypes = new HashMap<Integer, ArchivesType>();
					//从该用户中获取档案分类，取当前选择分类的子节点，通过工具方法取其下所有末点集合
					CommonUtil.getEndArchivesTypesByTree(userInfo.getArchivesTypes().get(archivesTypeId).getChildArchivesTypes(), endArchivesTypes, pErrInfo);
					archivesTypes.addAll(endArchivesTypes.values());
				}
			}
			
			//访问控制，检查指定的档案资源分类是否可以访问
			if (pFlag) {
				for (ArchivesType type : archivesTypes) {
					if(userInfo.getAccessControlService().checkArchivesTypeACL(type.getID())==false){
						pFlag = false;
						pErrInfo.getContent().append("用户无权访问" + type.getFullName() + "档案分类");
					}
				}				
			}
						
			//判断查询方式：简单查询/高级查询
			if(pFlag){
				pErrPos = 2;
				if("".equals(advanceQueryCondition)){//如果高级查询字符串为空，则说明是简单查询
					pAdvanceQueryFlag = false;
				}else{//若不为空，则说明是高级查询
					pAdvanceQueryFlag = true;
				}				
			}			
			
			//如果是简单查询就构造条件集合对象；如果是高级查询，此过程省略
			if(pFlag){
				pErrPos = 3;
				if(pAdvanceQueryFlag == false){				
					archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
					ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
					Enumeration enumeration = request.getParameterNames();//取出所有的页面传入的条件的名字数组
					String [] values = null;//保存某个数据项里面的vlue值
					ArchivesTypeDataItem dataItem = null;
					String parameterName = null;
					
					while(enumeration.hasMoreElements()){
						inputTrue = false;
						parameterName = (String)enumeration.nextElement();//取出条件的名字					
						//过滤隐藏参数archivesTypeId\dataPageInfo.currentPage\advanceQueryCondition(用于传递高级查询条件)
						if( !"advanceQueryCondition".equals(parameterName) && !"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)){
							values = request.getParameterValues(parameterName);//取出条件所对应的值 该值为dataItem的Id
							dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
																
							//过滤输入为空的查询条件
							for(int i=0;i<values.length;i++){
								if(!"".equals(values[i])){	
									inputTrue = true;
								}
							}
							
							//将有输入数据的查询条件实例化成archivesInfoQueryCondition对象并加到archivesInfoQueryConditions集合中
							if(inputTrue){
								archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);//构造archivesInfoQueryCondition对象
								
								if(values.length>1){
									archivesInfoQueryCondition.setMinValue(values[0]);
									archivesInfoQueryCondition.setMaxValue(values[1]);
								}else{
									archivesInfoQueryCondition.setValue(values[0]);
								}
								archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);
							}
							
						}
					}
				}
			}
			
			//检查查询条件并进行相关修复处理
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkArchivesInfoUseQueryConditions(new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "检查查询条件信息失败: ");
				}
			}
			
			//将查询条件转换成SQL语句中的WHERE条件
			if (pFlag) {
				pErrPos = 5;
				getSqlForArchivesInfoInputQueryConditions(new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), querySQL, pErrInfo);
			}
			
			//调用业务逻辑
			if(pFlag){	
				pErrPos = 6;
				dataPageInfo.setPageSize(10);
				if(pAdvanceQueryFlag){//高级查询
					if(archivesInfoQueryService.queryClassified(userInfo, archivesTypes,advanceQueryCondition , dataPageInfo, archivesInfos, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("分类查询：高级查询失败！");
					}					
				}else{//简单查询
					//if(archivesInfoQueryService.queryClassified(userInfo,archivesTypes, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), dataPageInfo, archivesInfos, pErrInfo)==false){
					if(archivesInfoQueryService.queryClassified(userInfo,archivesTypes, querySQL.toString(), dataPageInfo, archivesInfos, pErrInfo)==false){	
						pFlag = false;
						pErrInfo.getContent().append("分类查询：简单查询失败！");
					}
				}	
			}
			
			
			//将业务逻辑获取的结果返回给页面 			
	        if(pFlag){
	        	pErrPos = 7;
				request.setAttribute("htmlCode", this.getHtmlCodeByType(archivesTypeId,archivesInfoQueryConditions));
				request.setAttribute("archivesInfos", archivesInfos);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesType.getID()).getDataItemsForListDisplay());
				request.setAttribute("archivesTypeId", archivesTypeId);
				request.setAttribute("advanceQueryCondition", advanceQueryCondition);
				forWard  = "toFLCX";//跳转到分类查询页面
			}
		}catch(Exception e){
			//其他异常错误			
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		}finally{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.warn(pErrInfo.toString());//记录日志
					request.setAttribute("pErrInfo",pErrInfo);
					forWard  = "error";
				}else{
					message = pErrInfo.toShortString();
					request.setAttribute("htmlCode", this.getHtmlCodeByType(archivesTypeId,archivesInfoQueryConditions));
					request.setAttribute("errMsg", message);
					forWard  = "toFLCX";
				}
			}
		}
		return forWard;
	}

	/**
	 * 根据QuerySQL查询条件和档案分类查询档案信息	<br>
	 * 用于跨门类查询和自定义条件查询中查看各档案分类的详细信息
	 * @return
	 * @throws Exception
	 */
	public String findArchivesByQuerySQL() throws Exception{
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();	
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String forWard = "";
		boolean pFlag = true;
		int pErrPos = 0;		
		String htmlCode = "";		
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		UserInfo userInfo = null;
		String querySQL = ""; //查询条件
		String keyWordSQL = "";//用于查询的关键字
		String whereSQL = "";//用于查询的WHERE 语句
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);//工具类获得userInfo实体

		try {
		
			pErrPos = 1;	
			//获取档案分类编号
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("获取档案分类编号失败！");
			}else{					
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
			}				
			//验证
			if(request.getParameter("keyWordSQL") == null && request.getParameter("querySQL") == null ){
				pFlag = false;
				pErrInfo.getContent().append("用于查询的关键字或查询条件非法为空！");
			}
			
			//获取querySQL查询条件
			if(request.getParameter("querySQL") != null &&  !"".equals(request.getParameter("querySQL").trim()) ){
				querySQL = request.getParameter("querySQL").trim();
			}	
			
			//获取keyWordSQL关键字
			if( request.getParameter("keyWordSQL") != null && !"".equals(request.getParameter("keyWordSQL").trim()) ){
				keyWordSQL = request.getParameter("keyWordSQL").trim();
			}	
			
			
			//构造对象archivesTypes
			if(pFlag){
				pErrPos = 2;				 
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
				archivesTypes.add(archivesType);
			}
			
			//调用业务逻辑
			if(pFlag){
				pErrPos = 3;
				//设置页面每页显示的记录数
				dataPageInfo.setPageSize(10);
				if( !"".equals(querySQL) ){//通过查询条件查询
					whereSQL = querySQL;
				}else{//通过关键字检索查询
					StringBuilder stringBuilderSQL = new StringBuilder();
					getKeyQuerySQL(keyWordSQL, archivesType.getDataItemsForUseQuery(), stringBuilderSQL, pErrInfo);
					whereSQL = stringBuilderSQL.toString();
				}
				
				//调用通过SQL条件查询指定档案信息
				if(archivesInfoQueryService.queryClassified(userInfo, archivesTypes,whereSQL , dataPageInfo, archivesInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("根据SQL条件查询档案信息失败！");
				}				
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("dataPageInfo", dataPageInfo);
				request.setAttribute("archivesInfos",archivesInfos);
				request.setAttribute("archivesType", archivesType);
				request.setAttribute("querySQL", querySQL);
				request.setAttribute("keyWordSQL",keyWordSQL);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
				forWard  = "toListDetail";//跳转到查看指定结果的详细档案信息
			}			
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return forWard;		
	}


	
	/**
	 * 跨门类查询: -> 获取各档案分类下满足条件的记录数
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getCountInfoByArchivesTypes() throws Exception{
		String forWard = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
		String htmlCode = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		boolean inputTrue = false;//输入为空标识，用于过滤掉那些无输入的字段
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		String[] strArchivesTypeIds = {};//档案分类串
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		

		try {
			//获取参数：archivesTypeIdstr档案分类串；strNameValues：查询条件串
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号集合获取失败！");
			}else{//接收参数archivesTypeIdstr成功
				System.out.println("archivesTypeIdstr: "+request.getParameter("archivesTypeIdstr"));
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//取第一个档案分类编号
			}	
			
			//获取档案分类,并加入到档案分类集合中
			if (pFlag) {
				pErrPos = 2;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);					
				}
			}
			
			//构造条件集合对象
			if(pFlag){	
				pErrPos = 3;						
				archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
				Enumeration enumeration = request.getParameterNames();//取出所有的页面传入的条件的名字数组
				String [] values = null;//保存某个数据项里面的vlue值
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				
				while(enumeration.hasMoreElements()){
					inputTrue = false;
					parameterName = (String)enumeration.nextElement();//取出条件的名字					
					//过滤隐藏参数archivesTypeId\dataPageInfo.currentPage\advanceQueryCondition(用于传递高级查询条件)
					if(!"archivesTypeIdstr".equals(parameterName)){
						values = request.getParameterValues(parameterName);//取出条件所对应的值 该值为dataItem的Id
						dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
															
						//过滤输入为空的查询条件
						for(int i=0;i<values.length;i++){
							if(!"".equals(values[i])){	
								inputTrue = true;
							}
						}
						
						//将有输入数据的查询条件实例化成archivesInfoQueryCondition对象并加到archivesInfoQueryConditions集合中
						if(inputTrue){
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);//构造archivesInfoQueryCondition对象
						
							if(values.length>1){
								archivesInfoQueryCondition.setMinValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								archivesInfoQueryCondition.setMaxValue(new String(values[1].getBytes("ISO8859-1"),"utf-8"));
							}else{
								archivesInfoQueryCondition.setValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);
						}
						
					}
				}				
			}
			
			
			//调用业务逻辑执行查询操作
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().append("查询满足条件的各档案分类的数量失败。");
				}else{
					forWard = "toKMLCX";
				}
			}
			
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//设置结果的数量
				request.setAttribute("beginFlag", false);
				request.setAttribute("querySQL", querySQL.toString());
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));
			}			
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}

		return forWard;
		
	}
	
	/**
	 * 关键字检索（位置：跨门类中）<br>
	 * 根据匹配所有可查询字段，统计所有门类中档案数量
	 * @return
	 * @throws Exception
	 */
	public String findArchivesCountsByKeyWord() throws Exception{

		String htmlCode = "";		
		String forWord = "toKMLCX";
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean pFlag = true;
		int pErrPos = 0;
		ArchivesType archivesType = new ArchivesType();
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId = 0;//档案分类编号	
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();	
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;
		//StringBuilder querySQL = new StringBuilder();//用于查询的SQL语句
		String keyWord = "";
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		String[] strArchivesTypeIds = {};//档案分类串
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		
		try {			
			//获取参数：archivesTypeIdstr档案分类串；strNameValues：查询条件串
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号集合获取失败！");
			}else{//接收参数archivesTypeIdstr成功
				System.out.println("archivesTypeIdstr: "+request.getParameter("archivesTypeIdstr"));
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//取第一个档案分类编号
			}	
			
			//获取关键字
			if(request.getParameter("keyWord") == null){
				pFlag = false;
				pErrInfo.getContent().append("获取关键字失败！");
			}else{
				keyWord = request.getParameter("keyWord").trim();
			}
			
			//获取档案分类,并加入到档案分类集合中
			if (pFlag) {
				pErrPos = 2;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);
				}
			}
			
			//调用业务逻辑
			if(pFlag){
				pErrPos = 3;
				if(archivesInfoQueryService.queryCrossClassifiedByKeyWord(userInfo, archivesTypes, keyWord, archivesTypeCountInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "关键字检索失败！");
				}else{
					System.out.println("archivesTypeCountInfos.size():"+archivesTypeCountInfos.size());
					if(archivesTypeCountInfos.size()>0){
						System.out.println(archivesTypeCountInfos.get(0).getCountNum()+":::"+archivesTypeCountInfos.get(0).getQuerySQL());
					}
				}
			}
			
			//根据档案类别生成相应的查询条件
			if(pFlag){
				pErrPos = 3;
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//设置结果的数量
				request.setAttribute("keyWordSQL", request.getParameter("keyWord"));
				
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));				
				request.setAttribute("beginFlag", false);
	//			request.setAttribute("querySQL", querySQL.toString());
		//		request.setAttribute("resultSize",archivesInfos.size());//记录的数量
		//		request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
		//		request.setAttribute("archivesTypeId", archivesTypeId);
			}	
			
			
//			
//			//如果keyWord为null，则不执行查询操作
//			if(request.getParameter("keyWord")!=null){
//				keyWord = request.getParameter("keyWord");
//				
//				dataItemsForUseQuery = archivesType.getDataItemsForUseQuery();
//				if(getKeyQuerySQL(keyWord, dataItemsForUseQuery, querySQL, pErrInfo)==false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0,"构造SQL语句出错！");
//				}
//				
//				//调用业务逻辑
//				if (pFlag) {
//					dataPageInfo.setPageSize(10);
//					if(archivesInfoQueryService.queryClassified(userInfo, archivesType,querySQL.toString() , dataPageInfo, archivesInfos, pErrInfo)==false){
//						pFlag = false;
//						pErrInfo.getContent().insert(0,"通过SQL语句查询档案信息失败！");
//					}
//				}
//					
//			}
//			
//			//根据档案类别生成相应的查询条件
//			if(pFlag){
//				pErrPos = 3;
//				request.setAttribute("keyWord", request.getParameter("keyWord"));
//				request.setAttribute("archivesInfos", archivesInfos);
//				request.setAttribute("resultSize",archivesInfos.size());//记录的数量
//				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
//				request.setAttribute("archivesTypeId", archivesTypeId);
//			}		

		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				System.out.println("ERROR:"+pErrInfo.toString());
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return forWord;
	}
	
	
	
	/**
	 * 自定义条件查询: -> 新增自定义条件查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addUserDefinedQuery() throws Exception{
		
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
		String htmlCode = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		boolean inputTrue = false;//输入为空标识，用于过滤掉那些无输入的字段
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		String[] strArchivesTypeIds = {};//档案分类串
		String queryName = "";//名称
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();

		try {
			//获取参数：archivesTypeIdstr档案分类串；strNameValues：查询条件串
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号集合获取失败！");
			}else{//接收参数archivesTypeIdstr成功
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//取第一个档案分类编号
			}	
			
			if(request.getParameter("queryName") == null || "".equals(request.getParameter("queryName"))){
				pFlag = false;
				pErrInfo.getContent().append("自定义条件名称不能为空！");
			}else{//接收参数archivesTypeIdstr成功
				queryName = request.getParameter("queryName");
			}	
			
			//构造用户自定义条件查询对象
			if (pFlag) {
				pErrPos = 2;
				userDefinedSearch.setName(queryName);
				userDefinedSearch.setArchivesIDString(request.getParameter("archivesTypeIdstr"));
				userDefinedSearch.setUserID(userInfo.getUserID());			
			}
			
			
			//检查自定义条件名称是否存在
			if (pFlag) {
				pErrPos = 3;
				if(userDefinedSearchManageService.checkQueryNameExist(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "");
				}else{
					if(userDefinedSearch.getID()!=0){
						pFlag = false;
						pErrInfo.getContent().insert(0,"'" + queryName + "'" + "该名称已经存在，请改换名称再试！");
					}
				}
				
			}
			
			
			//获取档案分类,并加入到档案分类集合中
			if (pFlag) {
				pErrPos = 4;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);
				}
			}
	
			
			//构造条件集合对象
			if(pFlag){
				pErrPos = 5;
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;				
				Enumeration enumeration = request.getParameterNames();//取出所有的页面传入的条件的名字数组
				String [] values = null;//保存某个数据项里面的vlue值
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				
				while(enumeration.hasMoreElements()){
					inputTrue = false;
					parameterName = (String)enumeration.nextElement();//取出条件的名字
					if(!"archivesTypeIdstr".equals(parameterName) && !"queryName".equals(parameterName) && !"archivesTypeId".equals(parameterName)){
						values = request.getParameterValues(parameterName);//取出条件所对应的值 该值为dataItem的Id
												
						//过滤输入为空的查询条件
						for(int i=0;i<values.length;i++){
							System.out.println(":"+values[i]+":");
							if(!"".equals(values[i])){							
								inputTrue = true;
							}
						}
						
						//将有输入数据的查询条件实例化成archivesInfoQueryCondition对象并加到archivesInfoQueryConditions集合中
						if(inputTrue){
							dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);//构造archivesInfoQueryCondition对象
							
							if(values.length>1){
								archivesInfoQueryCondition.setMinValue(values[0]);
								archivesInfoQueryCondition.setMaxValue(values[1]);
								//archivesInfoQueryCondition.setMinValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								//archivesInfoQueryCondition.setMaxValue(new String(values[1].getBytes("ISO8859-1"),"utf-8"));
							}else{
								//archivesInfoQueryCondition.setValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);
						}	
					}
				}				
			}
			
			
			//调用业务逻辑获取SQL语句
			if (pFlag) {
				pErrPos = 6;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;				
					pErrInfo.getContent().insert(0,"生成SQL语句失败！");
				}else{
					//将SQL字符串赋到用户自定义条件查询对象中
					userDefinedSearch.setWhereString(querySQL.toString());					
				}
			}
			
			
			
			//调用业务逻辑添加自定义条件查询
			if (pFlag) {
				pErrPos = 7;
				if(userDefinedSearchManageService.addUserDefinedSearch(userDefinedSearch, pErrInfo)==false){
					pFlag = false;				
					pErrInfo.getContent().insert(0,"增加自定义条件查询失败！");
				}
			}

			//Ajax调用，返回执行结果到页面
			if(pFlag){
				ServletActionContext.getResponse().getWriter().print("添加成功！");
			}else{
				ServletActionContext.getResponse().getWriter().print("添加失败！" + pErrInfo.toShortString());
			}
			
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}

		return null;
		
	}
	
	
	
	
	
	/**
	 * 根据指定档案分类编号查找可用于高级查询的数据项集合
	 * @return
	 * @throws Exception
	 */
	public String getAdvanceQueryItems() throws Exception{
	
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//页面去向
	//	String message = "";//用于返回给页面错误信息
		int archivesTypeId = 0;
		
		try {
			pErrPos = 1;
			//根据档案分类编号，构造档案分类对象			
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号获取失败！");
			}else{//接收参数archivesTypeId成功
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
			}

			
			//将获取的数据返回到页面
			if(pFlag){	
				pErrPos = 2;
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery());
				request.setAttribute("archivesTypeId",archivesTypeId);
				forWard = "toAdvance";
				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
				
		return forWard;
	}
	

	/**
	 * 跨门类查询 -->获取公共查询字段
	 * @return
	 * @throws Exception
	 */
	public String getPublicQueryItems() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//页面去向
		String[] str_archivesTypeIds = {};
		Map<String,ArchivesTypeDataItem> archivesTypeDataItems = null;
		List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList = new ArrayList<Map<String,ArchivesTypeDataItem>>();
		Map<String, ArchivesTypeDataItem> dataItems =  new LinkedHashMap<String, ArchivesTypeDataItem> ();
		
		try {
			pErrPos = 1;
			//根据档案分类编号列表，构造档案分类对象			
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号获取失败！");
			}else{//接收参数archivesTypeId成功
				str_archivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
			}
	
			
			//根据分类编号列表，构造档案分类对象集合
			if(pFlag){
				pErrPos = 2;
				for (int i=0 ;i<str_archivesTypeIds.length;i++) {
					archivesTypeDataItems =  SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i])).getDataItemsForUseQuery();
					archivesTypeDataItemsList.add(archivesTypeDataItems);//将该档案分类下的利用查询数据项集合加到列表中
				}
				
				//调用工具方法获取公共的数据项
				if(getPublicDataItems(archivesTypeDataItemsList,dataItems,pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("获取各档案分类的公共数据项失败！");
				}			
			}
			
			if (pFlag) {
				pErrPos = 3;
				//通过数据项构造查询HTML代码
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);			
			}			
			
			//将获取的数据返回到页面
			if(pFlag){		
				pErrPos = 4;
				request.setAttribute("archivesTypeId", str_archivesTypeIds[0]);
				request.setAttribute("htmlCode", htmlCode);
				forWard = "toSimple";				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
			}
		}				
		return forWard;
	}
	
	/**
	 * 自定义条件查询-->获取公共查询字段
	 * @return
	 * @throws Exception
	 */
	public String getPublicQueryItemsForDefinedQuery() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//页面去向
		String[] str_archivesTypeIds = {};
		Map<String,ArchivesTypeDataItem> archivesTypeDataItems = null;
		List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList = new ArrayList<Map<String,ArchivesTypeDataItem>>();
		Map<String, ArchivesTypeDataItem> dataItems =  new LinkedHashMap<String, ArchivesTypeDataItem> ();
		
		try {
			pErrPos = 1;
			//根据档案分类编号列表，构造档案分类对象			
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("档案分类编号获取失败！");
			}else{//接收参数archivesTypeId成功
				str_archivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
			}
	
			
			//根据分类编号列表，构造档案分类对象集合
			if(pFlag){
				pErrPos = 2;
				for (int i=0 ;i<str_archivesTypeIds.length;i++) {
					archivesTypeDataItems =  SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i])).getDataItemsForUseQuery();
					archivesTypeDataItemsList.add(archivesTypeDataItems);//将该档案分类下的利用查询数据项集合加到列表中
				}
				
				//调用工具方法获取公共的数据项
				if(getPublicDataItems(archivesTypeDataItemsList,dataItems,pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("获取各档案分类的公共数据项失败！");
				}			
			}
			
			if (pFlag) {
				pErrPos = 3;
				//通过数据项构造查询HTML代码
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);			
			}			
			
			//将获取的数据返回到页面
			if(pFlag){				
				request.setAttribute("archivesTypeId", str_archivesTypeIds[0]);
				request.setAttribute("htmlCode", htmlCode);
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));
				forWard = "toDefinedQueryResult";
				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
			}
		}				
		return forWard;
	}

	
	/**
	 * 用户自定义条件查询 <br>
	 * 查询出各档案分类的数量
	 * @return
	 * @throws Exception
	 */
	public String findArchivesByDefinedQuery() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//页面去向
		String[] str_archivesTypeIds = {};
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		int definedSearchID = 0;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		StringBuilder querySQL = new StringBuilder();// 查询条件的SQL片段
	
		
		try {
			
			pErrPos = 1;
			//数据验证
			if( request.getParameter("nodeId")==null || "".equals(request.getParameter("nodeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("找不到用户自定义查询条件编号，nodeId传递失败");
			}
			
			
			
			//调用业务逻辑，获取用户自定义查询条件对象
			if(pFlag){
				pErrPos = 2;
				definedSearchID = Integer.parseInt(request.getParameter("nodeId"));
				//将编号保存到对象中
				userDefinedSearch.setID(definedSearchID);
				if (userDefinedSearchManageService.findUserDefinedSearchByID(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找用户自定义查询条件失败：");
				}
			}
			
			
			//构造查询条件和部门集合
			if (pFlag) {	
				pErrPos = 3;
				
				//保存查询条件
				querySQL.append(userDefinedSearch.getWhereString());
				
				//保存需查询的档案分类
				str_archivesTypeIds = userDefinedSearch.getArchivesIDString().split(":");
				for (int i = 0; i < str_archivesTypeIds.length; i++) {
					//从系统初始化中获取档案类型对象
					ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i]));
					archivesTypes.add(archivesType);					
				}
			}
			
			
			//调用业务逻辑执行查询操作
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().append("查询满足条件的各档案分类的数量失败。");
				}else{
					forWard = "toUserDefinedQuery";
				}
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//设置结果的数量
				request.setAttribute("beginFlag", false);
				request.setAttribute("querySQL", querySQL.toString());
				request.setAttribute("archivesTypeIdstr", userDefinedSearch.getArchivesIDString());
				
			}	
			
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			forWard = "error";
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
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}				
		return forWard;
	}
	
	/**
	 * 根据档案分类编号和NBXH显示档案信息<br>包括档案的电子原文件列表
	 * archivesTypeId,NBXH,
	 * @return
	 * @throws Exception
	 */
	public String findArchivesInfoByNBXH() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		String forWard = "";//页面去向
		ArchivesType archivesType = new ArchivesType();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		ArchivesInfo archivesInfo = new ArchivesInfo();
		int NBXH = 0;
		boolean viewAttachedFileFlag = false;//访问原文标志
		List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles = new ArrayList<ArchivesInfoAttachedFile>(); 
		String htmlCode = "";

		try {
			
			pErrPos = 1;
			//获取档案分类信息
			if( request.getParameter("archivesTypeId")==null || "".equals(request.getParameter("archivesTypeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("获取档案分类编号失败");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeId")));
			}
			
			//获取NBXH
			if( request.getParameter("NBXH")==null || "".equals(request.getParameter("NBXH")) ){
				pFlag = false;
				pErrInfo.getContent().append("获取档案的编号失败");
			}else{
				NBXH = Integer.parseInt(request.getParameter("NBXH"));
			}

			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesType.getID()).getDataItemsForInput();
			if(dataItems == null){
				pErrPos = 2;
				pFlag = false;
				pErrInfo.getContent().append("此分类下可著录数据项为空！");
			}
			
			//获取档案信息，并生成用于在页面上显示的HTML代码
			if(pFlag){
				pErrPos = 3;	
				archivesInfo = new ArchivesInfo(archivesType);
				//获取档案信息
				if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, NBXH, archivesInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据内部序号查找档案信息失败：");
				}
				//生成页面上显示的，带有数据的HTML字符串
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
			}			
			
			//访问档案基本信息的权限控制
			if (pFlag) {
				pErrPos = 4;
				//可能会出现问题，由于注入而引起的
				StringBuilder errInfo = new StringBuilder();
				//检查用户是否有权访问档案
				if(userInfo.getAccessControlService().checkArchivesInfoACL(archivesType.getID(), NBXH, archivesInfo.getSecrecyID(), userInfo.getUserID(), useScopesAccessControlService, errInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, errInfo.toString());
				}
			}
		   System.out.println("----->"+archivesInfo.getSecrecyID());
			//获取原文电子文件集合
			if (pFlag) {
				pErrPos = 5;
				if(attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, NBXH, archivesInfoAttachedFiles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取指定文件的原文电子文件信息失败：");
				}
			}
			
			//获取归档后的原文电子文件的存放路径
			if (pFlag) {
				pErrPos = 6;
				if(attachedFileManageService.getSavedArchivesInfoAttachedFilesSavePath(archivesType, archivesInfoAttachedFiles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取归档后的原文电子文件的存放路径失败：");
				}
			}
			
//			List<Config> pConfigs = new ArrayList<Config>();
//			//从配置文件中获取原文电子文件的根目录
//			if (pFlag) {
//				pErrPos = 6;
//				if(configManageService.findConfigByConfigType("AttachedFileReSavedForUsePath", pConfigs, pErrInfo)==false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "从配置文件中获取原文电子文件的根目录失败：");
//				}else{
//					if(pConfigs.size()<1){
//						pFlag = false;
//						pErrInfo.getContent().append("配置文件没有配置原文电子文件的根目录！");
//					}
//				}
//			}
//			
//			
//			//设置原文电子文件的存储路径
//			if (pFlag) {
//				pErrPos = 7;				
//				for (ArchivesInfoAttachedFile archivesInfoAttachedFile : archivesInfoAttachedFiles) {
//					String attachedFileReSavedForUsePath = "";//原文电子文件的路径
//					//1：取根目录
//					for (Config config : pConfigs) {
//						//System.out.println(config.getMaxValue());
////						//System.out.println(config.getMinValue());
////						int max = Integer.parseInt(config.getMaxValue());
////						int min = Integer.parseInt(config.getMinValue());
////						int target =Integer.parseInt( new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime()));
////System.out.println(max+":"+min +":"+target);
//						Date maxDate = new SimpleDateFormat("yyyyMMdd").parse(config.getMaxValue());
//						Date minDate =  new SimpleDateFormat("yyyyMMdd").parse(config.getMinValue());
//						if(archivesInfoAttachedFile.getResaveTime().after(minDate) && archivesInfoAttachedFile.getResaveTime().before(maxDate)){
//						//if(target>min && target<max){
//							attachedFileReSavedForUsePath = config.getConfigValue();
//						}
//					}	
//					//取文件的相对目录
//					if ("".equals(attachedFileReSavedForUsePath)) {
//						pFlag = false;
//						pErrInfo.getContent().append("没有找到原文电子文件的根目录！");
//					}else{
//						//2：二级目录 :分类/时间/1_78_G01-2010-DQ11-27.pdf
//						attachedFileReSavedForUsePath += archivesType.getAttachedFileSavePath() ;
//						attachedFileReSavedForUsePath += "\\" + new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime());
//						attachedFileReSavedForUsePath += "\\" + archivesInfoAttachedFile.getResaveFileName();
//						//将文件的路经设置到原文信息对象的tag属性
//						archivesInfoAttachedFile.setTag(attachedFileReSavedForUsePath);
//						System.out.println(archivesInfoAttachedFile.getTag());
//					}					
//				}
//			}
			
			//原文电子文件的访问权限控制
			if (pFlag) {
				pErrPos = 8;
				//IAttachedFileAccessControlService attachedFileAccessControlService = new AttachedFileAccessControlServiceImpl();
				if(attachedFileAccessControlService.checkArchivesAttachedFileACL(archivesType.getID(), NBXH, userInfo.getUserID()) == true){
					viewAttachedFileFlag = true;
				}
			}
			
			//将需要的数据返回到页面
			if (true) {
				System.out.println(htmlCode);
				request.setAttribute("pFlag", pFlag);             
				request.setAttribute("archivesInfo",archivesInfo);
				request.setAttribute("viewAttachedFileFlag",viewAttachedFileFlag);
				request.setAttribute("archivesInfoAttachedFiles",archivesInfoAttachedFiles);
				request.setAttribute("attachedFileSize", archivesInfoAttachedFiles.size());
				request.setAttribute("htmlCode",htmlCode);
				forWard = "toItem";//指定要返回的页面
			}			
			
		} catch (Exception e) {			
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				forWard = "error";//当出现错误就返回到错误页面
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
System.out.println(pErrInfo.toString());
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}
	
	
	
	//工具方法
	/**
	 * 根据档案类别生成页面html查询代码
	 */
	private String getHtmlCodeByType(int archivesTypeId,Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String htmlCode = "";

		//得到application对象
		ServletContext application = ServletActionContext.getServletContext();

		//得到html代码
		SystemInitializer systemInitializer = (SystemInitializer) application.getAttribute("systemInitializer");
		Map<String, ArchivesTypeDataItem> dataItems = null;
		if( systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery()== null){
			htmlCode = "<script>alert('没有可查询的数据项！');</script>";
		}else{
			dataItems = systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();
			htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions);
		}
		return htmlCode;
	}
	
	
	
	
	
	
	////////////  DWR  ////////////
	
	/**
	 * 根据内部序号查询指定案卷的卷内文件信息集合
	 * 
	 * @param archivesInfo
	 *            待查的案卷信息
	 * @return archivesInfos 查询到的卷内文件信息集合
	 */
	public List<ArchivesInfo> findChildArchivesInfosByNBXH(int archivesTypeID,int NBXH,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<ArchivesInfo> archivesInfos = null;
		ArchivesType archivesType = null;
		
		try {
			pErrPos = 1;
			if (archivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("获得档案类型失败");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("获得档案内部序号失败");
				}
			}
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if(archivesInfoQueryService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"获得档案卷内文件失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
		}
		System.out.println(archivesInfos.size());
		return archivesInfos;
	}

	/**
	 * 验证高级查询待加入条件的合法性
	 * @param archivesTypeId 档案分类编号
	 * @param columnName 字段名
	 * @param txtValue 输入的值，字符串或数字
	 */
	public String validateAdvanceQueryInput(int archivesTypeId,String columnName,String txtValue, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//返回执行结果,验证通过时返回结果为“”，不通过则返回错误提示信息
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			pErrPos = 1;
		
			//验证档案分类编号是否已赋值
			if(pFlag){				
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案分类信息失败！");
				}			
			}
			
			
			//根据columnName对应的数据类型，效验其输入的合法性。
			if(pFlag){
				pErrPos = 2;
				//整数效验
				if(SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getColumnDataType()==EnumColumnDataType.整数){
					if(CommonUtil.isInteger(txtValue)==false){//如果输入的值不是整数
						pFlag = false;
						//pErrInfo.getContent().append("您输入数字格式不对，请输入正确的数字！");
						String displayText = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getDisplayText();
						str_Result ="'"+ displayText+"'字格式错误，请输入正确的数字！";

					}
				}
				
				//日期效验
				if(SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getColumnDataType()==EnumColumnDataType.日期时间){
					if(CommonUtil.isDate(txtValue)==false){
						pFlag = false;
						String displayText1 = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getDisplayText();
						//pErrInfo.getContent().append("您输入日期的格式错误，请输入正确的日期(yyyy-MM-dd)！");
						str_Result = "'"+displayText1+"'格式错误，请输入正确的日期(yyyy-MM-dd)！";
					}
				}
			}
			
		} catch (Exception e) {
			
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
		

	 /**
	 * 删除编号指定的自定义条件查询
	 * @param queryID
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public String deleteDefinedQueryByID(int queryID, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//返回执行结果,验证通过时返回结果为“”，不通过则返回错误提示信息
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();
		
		
		try {
			pErrPos = 1;
		
			//验证档案分类编号是否已赋值
			if(queryID == 0){
				pFlag = false;
				pErrInfo.getContent().append("获取用户自定义条件查询编号失败！");
			}else{
				userDefinedSearch.setID(queryID);
			}	
			
			//调用业务逻辑，执行删除操作
			if (pFlag) {
				pErrPos = 2;
				if(userDefinedSearchManageService.deleteUserDefinedSearch(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除编号指定的用户自定义条件查询失败！");					
				}
			}
			
		} catch (Exception e) {
			
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				str_Result = pErrInfo.toShortString();
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
	
	
	/**
	 * 验证跨门类查询中的输入的条件组合串
	 * @param archivesTypeId 档案分类编号
	 * @param columnName 字段名
	 * @param txtValue 输入的值，字符串或数字
	 */
	public String validateQueryInputStr(String strNameValues, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//返回执行结果,验证通过时返回结果为“”，不通过则返回错误提示信息
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId = 0;	//档案分类编号
		String[] nameValues = {};//名称-值对 数组
		try {
			pErrPos = 1;
			nameValues = strNameValues.split(";");
			//取archivesTypeId值，第一个参数是名是archivesTypeId
			archivesTypeId = Integer.parseInt(nameValues[0].split(":")[1]);
			
			//验证数据
			if(pFlag){
				pErrPos = 2;
				for(int i = 1;i<nameValues.length;i++){
					if(nameValues[i].split(":").length==2){
						//逐条验证，将错误提示合并在一起
						str_Result += validateAdvanceQueryInput(archivesTypeId, nameValues[i].split(":")[0],nameValues[i].split(":")[1], session);
				
					}
				}
			}			
		
		} catch (Exception e) {
			
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//异常和错误处理
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
	

	/**
	 * 获取关键字检索的SQL语句
	 * @param keyWord 用户输入的关键字
	 * @param dataItemsForUseQuery 可用于查询的字段
	 * @param querySQL	返回SQL语句
	 * @param pErrInfo 返回错误信息
	 * @return
	 */
	private boolean getKeyQuerySQL(String keyWord,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery,StringBuilder querySQL, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//如果输入为空，则查询所有档案，querySQL也为空串
			if("".equals(keyWord.trim())){
				return true;
			}
			
			pErrPos = 1;
			//检查各档案分类的数据项集合的集合是否已初始化
			if(dataItemsForUseQuery==null){
				pFlag = false;
				pErrInfo.getContent().append("各档案分类的数据项集合的集合未初始化");
			}
			
			if (pFlag) {
				Iterator<String> columnNames = dataItemsForUseQuery.keySet().iterator();			
				int index = 0;//记录是否是第一列
				//querySQL格式：AND (Title LIKE '%xx%' OR Ftm LIKE '%xx%')
				while(columnNames.hasNext()){
					String columnName = columnNames.next();
					index ++;
					if(index==1){
						querySQL.append( " AND ( " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}else{
						querySQL.append( " OR " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}
				}
				querySQL.append(")");
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
	 * 获取各档案分类中的公共数据项
	 * @param archivesTypeDataItemsList 各档案分类的数据项集合的集合（结构：List< Map<> >）
	 * @param dataItems 返回公共的数据项
	 * @param pErrInfo 
	 * @return
	 */
	private boolean getPublicDataItems(List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList , Map<String, ArchivesTypeDataItem> dataItems , ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			pErrPos = 1;
			//检查各档案分类的数据项集合的集合是否已初始化
			if(archivesTypeDataItemsList==null){
				pFlag = false;
				pErrInfo.getContent().append("各档案分类的数据项集合的集合未初始化");
			}
			
			//检查公共的数据项是否已初始化
			if(dataItems==null){
				pFlag = false;
				pErrInfo.getContent().append("公共的数据项集合未初始化");
			}
			
			
			//获取所有档案分类中的公共数据项
			if (pFlag) {
				pErrPos = 2;
				if(archivesTypeDataItemsList.size()==1){//当只选择了一个分类时
					dataItems.putAll(archivesTypeDataItemsList.get(0));
				}else{
					//将第一个档案分类的所有数据项与其它所有分类的数据项进行一一匹配
					String columnName = "";//key值
					Iterator<String> iterator = archivesTypeDataItemsList.get(0).keySet().iterator();					
					while(iterator.hasNext()){
						int numFlag = 1;
						columnName = iterator.next();
						
						//用columnName与其它所有分类下所有数据项进行比较
						for(int i = 1;i<archivesTypeDataItemsList.size();i++){
							Iterator<String> targetIterator = archivesTypeDataItemsList.get(i).keySet().iterator();
							while (targetIterator.hasNext()) {
								if(columnName.equals(targetIterator.next())){//有相同的key值，标志加1
									numFlag++;									
								}								
							}
						}
						
						//如果该数据项在每个档案分类下都有的话就将其添加到结果集中
						if (numFlag == archivesTypeDataItemsList.size()) {//每个都存在时标志应为档案分类的个数
							dataItems.put(columnName, archivesTypeDataItemsList.get(0).get(columnName));
						}	
					}					
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
	 * 构造档案信息著录查询条件的SQL片段
	 * 
	 * @param archivesInfoQueryConditions
	 *            档案著录查询条件集合
	 * @param querySQL
	 *            返回构造好的查询条件SQL片段
	 * @param pErrInfo
	 *            处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		

		try
		{
			//如果没有数据就返回空串
			if(archivesInfoQueryConditions == null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else{
				for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
				{
					// 如果是范围查询，则构造最小值和最大值条件
					if (item.getDataItem().getRangeQueryFlag())
					{
						// 无需单引号的情况
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件范围
							querySQL.append(" BETWEEN ");
							querySQL.append(item.getMinValue());
							querySQL.append(" AND ");
							querySQL.append(item.getMaxValue());
						}
						// 需要单引号的情况
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件范围
							querySQL.append(" BETWEEN ");
							querySQL.append("'");
							querySQL.append(item.getMinValue());
							querySQL.append("'");
							querySQL.append(" AND ");
							querySQL.append("'");
							querySQL.append(item.getMaxValue());
							querySQL.append("'");
						}
					}
					// 如果不是范围查询，则构造值条件
					else
					{
						// 无需单引号的情况
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.实数 || item.getDataItem().getColumnDataType() == EnumColumnDataType.整数
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.布尔值)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件
							querySQL.append(" = "); // 数字类型缺省使用精确匹配
							querySQL.append(item.getValue());
						}
						// 需要单引号的情况
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间 || item.getDataItem().getColumnDataType() == EnumColumnDataType.字符串
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.文本)
						{
							// 拼接逻辑运行符 AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// 拼接字段名
							querySQL.append(item.getDataItem().getColumnName());
							// 拼接条件
							if (item.getDataItem().getColumnDataType() == EnumColumnDataType.日期时间)
							{
								querySQL.append(" = "); // 日期类型缺省使用精确匹配
								querySQL.append("'");
								querySQL.append(item.getValue());
								querySQL.append("'");
							}
							else
							{
								querySQL.append(" LIKE "); // 字符类型缺省使用模糊匹配
								querySQL.append("'%");
								querySQL.append(item.getValue());
								querySQL.append("%'");
							}
						}
					}
				}
			}
			
			
		}
		catch (Exception e)
		{
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		System.out.println("getSqlForArchivesInfoInputQueryConditions: querySQL:" + querySQL);
		return pFlag;
	}

	
	
	
	
	/**
	 * 测试用
	 * @return
	 * @throws Exception
	 */
	public String test() throws Exception{
		System.out.println("test-->>>>");
		return "error";
	}
	
	
	
	
}
