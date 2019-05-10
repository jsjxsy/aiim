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

import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.bll.util.ArchivesInfoFactory;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;

/**
 * 档案管理-文件管理 控制类(Action)
 * 
 * @author
 */
public class ArchivesInfoManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(ArchivesInfoManageAction.class);

	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService; // 文件管理业务类

	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	private DataPageInfo dataPageInfo = new DataPageInfo();// 分页实体BEAN

	private int[] NBXHS;// 批量操作需要接收的内部序号

	private String reslutStr;// 返回结果

	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	public int[] getNBXHS() {
		return NBXHS;
	}

	public void setNBXHS(int[] nbxhs) {
		NBXHS = nbxhs;
	}

	public String getReslutStr() {
		return reslutStr;
	}

	public void setReslutStr(String reslutStr) {
		this.reslutStr = reslutStr;
	}

	/**
	 * 获取档案分类树<br/> 
	 * 文件管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getArchivesTypeTree() throws Exception {
		HttpServletRequest request = this.getRequest();

		UserInfo userInfo = (UserInfo) this.getSession(false).getAttribute("userInfo");

		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(archivesTypes);

		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction","archivesInfoManageAction_getHtmlCodeForInputQuery.action?fileType=0");

		return "success";
	}

	/**
	 * 获取档案分类树<br/> 
	 * 著录审核档案分类树
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getArchivesTypeTreeForZLSH() throws Exception {
		System.out.println("-----得到档案分类树------------------------");
		
		HttpServletRequest request = this.getRequest();

		UserInfo userInfo = (UserInfo) this.getSession(false).getAttribute("userInfo");

		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(archivesTypes);

		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction","archivesInfoManageAction_getArchivesInfosForZLSH.action");

		reslutStr = "/DAGL/ZLSHTree.jsp";
		return SUCCESS;
	}

	
	/**
	 * 查询已提交送审的档案信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 
	 */
	public String getArchivesInfosForZLSH() throws Exception{
		System.out.println("-------查找提交送审的档案信息--------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		System.out.println("archivesTypeID： "+getParameterForInt("archivesTypeID"));
		
		ArchivesType archivesType = null;
		UserInfo userInfo = null;
		List<ArchivesInfo> archivesInfos = null;
		try {
			pErrPos = 1;
			userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
			int archivesTypeID = getParameterForInt("archivesTypeID");
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			
			dataPageInfo.setPageSize(10);
			
			if (pFlag) {
				pErrPos = 2;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if(archivesInfoWorkingManageService.findInputCheckNeedArchivesInfos(userInfo.getChargeDepartmentIDs(), archivesType, dataPageInfo, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询待著录的档案信息失败");
				}
			}
			
			reslutStr = "/DAGL/ZLSH.jsp";
			setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			setAttribute("archivesInfos", archivesInfos);
			setAttribute("archivesTypeID", archivesTypeID);
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
				
				if (pErrInfo.getException() != null) {
					setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				}else{
					setAttribute("message", "<script>alert(\"" + pErrInfo.toShortString()+ "!\");</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 获取档案信息分类树 <br/> 
	 * 案卷管理
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getArchivesTypeTree_AJ() throws Exception {
		HttpServletRequest request = this.getRequest();
		UserInfo userInfo = (UserInfo) this.getSession(false).getAttribute("userInfo");

		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(archivesTypes);

		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction","archivesInfoManageAction_getHtmlCodeForInputQuery.action?fileType=1");

		return "success";
	}

	/**
	 * 根据分类得到档案著录页面查询表单的HTML代码
	 * 
	 * @param
	 * @return
	 */
	public String getHtmlCodeForInputQuery() throws Exception {
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		int archivesTypeID = 0;
		int fileType = 0;
		String htmlCode = "";
		Map<String, ArchivesTypeDataItem> dataItems = null;
		UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
		try {
			pErrPos = 1;

			// if(request.getParameter("archivesTypeID") == null){
			// pFlag = false;
			// pErrInfo.getContent().append("archivesTypeID丢失！");
			// }
			//			
			// if (pFlag) {
			// pErrPos = 2;
			// if(request.getParameter("fileType") == null){
			// pFlag = false;
			// pErrInfo.getContent().append("fileType丢失！");
			// }
			// }

			// if (pFlag) {
			// pErrPos = 3;
			archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			fileType = Integer.parseInt(request.getParameter("fileType"));
			switch (fileType) {
			case 0:
				reslutStr = "/DAGL/fileManage.jsp";
				break;
			case 1:
				reslutStr = "/DAGL/filesManage.jsp";
				break;
			case 5:
				reslutStr = "/DAGL/findParent.jsp";
				break;
			}
			// }

			// 判断是否有可用于著录查询的数据项
			if (pFlag) {
				pErrPos = 4;
				dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForInputQuery();
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("此分类下没有可用著录查询的数据项！");
				}
			}

			// 得到html代码
			if (pFlag) {
				pErrPos = 5;
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);
			}

			
			IntegerEx archivesInfosSum = null;
			if (pFlag) {
				pErrPos = 4;
				archivesInfosSum = new IntegerEx();
				if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,false, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"统计当前移交批次中的档案信息数量失败");
				}else{
					request.setAttribute("sum", archivesInfosSum.getValue());
				}
			}
			
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("archivesTypeID", archivesTypeID);
			request.setAttribute("fileType", fileType);

		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					htmlCode = "<script>alert(\"" + pErrInfo.toShortString()+ "!\");</script>";
					request.setAttribute("htmlCode", htmlCode);
				}
				logger.error(pErrInfo.getContent());
			}
			throwable = null;
		}

		return SUCCESS;
	}

	/**
	 * 根据页面条件查询已著录完成的档案信息<br/> 
	 * 适用功能档案管理
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByCondition() throws Exception {
		System.out.println("--查询著录完成的档案信息！---------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		List<ArchivesInfo> archivesInfos = null;
		Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		Map<String, ArchivesTypeDataItem> dataItems = null;
		UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
		try {
			//获得页面参数
			pErrPos = 1;
			int archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeID"));
			int fileType = Integer.parseInt(request.getParameter("fileType"));
			int[] userIds = ((UserInfo) this.getSession(false).getAttribute("userInfo")).getChargeUserIDs();
			
			System.out.println("archivesTypeId: "+archivesTypeId);
			System.out.println("fileType: "+fileType);
			System.out.println("userIds: "+userIds);
			
			switch (fileType) {
			case 0:
				reslutStr = "/DAGL/fileManage.jsp";
				break;
			case 1:
				reslutStr = "/DAGL/filesManage.jsp";
				break;
			}

			ArchivesType archivesType = new ArchivesType(archivesTypeId);

			dataPageInfo.setPageSize(20);

			pErrPos = 2;
			archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
			ArchivesInfoQueryCondition archivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// 取出所有的页面传入的条件的名字数组
			String[] values = null;// 保存某个数据项里面的vlue值
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//从系统数据中取出可用于著录查询的著录项
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForInputQuery();

			
			//开始构造查询条件集合
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// 取出条件的名字
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// 取出条件所对应的值
					// 该值为dataItem的Id

					dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem
					
					if (dataItem != null && values.length > 1) {//两个值
						
						if (values[0] != "" && values[1] != "") {//都不为空
							// 把值放入archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						} else if(values[0] == "" && values[1] == ""){//都为空不做处理
							

						}else{//有一为空
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}
						
					}else if(dataItem != null && values[0] != ""){//只有一个值
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
					}
				}
			}

			// 调用业务逻辑
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(fileType),
																		EnumWorkFlowStatus.著录完成, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
			}

			IntegerEx archivesInfosSum = null;
			if (pFlag) {
				pErrPos = 4;
				archivesInfosSum = new IntegerEx();
				if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,false, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"统计当前移交批次中的档案信息数量失败");
				}else{
					request.setAttribute("sum", archivesInfosSum.getValue());
				}
			}
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());

			request.setAttribute("archivesInfos", archivesInfos);
			request.setAttribute("archivesTypeID", archivesTypeId);
			request.setAttribute("fileType", fileType);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions));
		} catch (Exception e) {
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// 销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}

	/**
	 * 批量删除档案著录信息<br/>
	 * ajax调用
	 */
	public String deleteArchivesInfos() throws Exception {
		System.out.println("--删除档案信息----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息

		HttpServletRequest request = this.getRequest();
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		ArchivesInfo archivesInfo = null;
		int fileType = 0;
		int archivesTypeID = 0;
		
		try {
			//获得页面参数
			pErrPos = 1;
			try {
				fileType = Integer.parseInt(request.getParameter("fileType"));
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			} catch (Exception e) {
				pErrInfo.getContent().append("获取参数失败！");
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXHS == null || NBXHS.length<=0) {
					pErrInfo.getContent().append("请选择要删除的档案信息！");
					pFlag = false;
				}
			}

			//构造业务逻辑所需参数
			if (pFlag) {
				pErrPos = 3;

				// 构造archivesType
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

				// 构造archivesInfos集合
				archivesInfos = new ArrayList<ArchivesInfo>();
				for (int nbxh : NBXHS) {
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(nbxh);
					archivesInfos.add(archivesInfo);
				}
			}

			// 调用业务逻辑删除档案信息
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoWorkingManageService.deleteArchivesInfos(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("删除档案信息失败");
				}
			}

			// 调用成功
			if (pFlag) {
				pErrPos = 5;
				HttpServletResponse response = this.getResponse();
				response.getWriter().print("删除成功！");
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

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			// 销毁局部变量
			throwable = null;
		}
		return null;
	}

	/**
	 * 批量提交审核著录信息<br/>
	 * ajax调用
	 * @return
	 * @throws Exception
	 */
	public String submitToInputCheck() throws Exception {
		System.out.println("--提交送审----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息

		HttpServletRequest request = this.getRequest();
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		ArchivesInfo archivesInfo = null;
		int archivesTypeID = 0;
		int fileType = 0;
		
		EnumArchivesInfoType enumArchivesInfoType;
		try {

			pErrPos = 1;
			int userId = ((UserInfo)this.getSession(false).getAttribute("userInfo")).getUserID();

			//取得页面参数
			pErrPos = 2;
			try {
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			} catch (Exception e) {
				pFlag = false;
				pErrInfo.getContent().append("获得参数失败！");
			}

			if (request.getParameter("fileType") != null) {
				fileType = Integer.parseInt(request.getParameter("fileType"));
				enumArchivesInfoType = EnumArchivesInfoType.getEnumElement(fileType);
			}else{
				enumArchivesInfoType = null;
			}
			
			if (pFlag) {
				pErrPos = 3;
				if (NBXHS == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要提交送审的档案！");
				}
			}

			//构造archivesType和archivesInfos集合
			if (pFlag) {
				pErrPos = 4;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

				archivesInfos = new ArrayList<ArchivesInfo>();
				for (int nbxh : NBXHS) {
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(nbxh);
					archivesInfos.add(archivesInfo);
				}
			}

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 5;
				if(archivesInfoWorkingManageService.submitToInputCheck(userId, archivesType,enumArchivesInfoType , archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"提交送审失败");
				}
			}

			//调用成功
			if (pFlag) {
				pErrPos = 6;
				ServletActionContext.getResponse().getWriter().print("提交成功！");
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

				// 如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}

			// 销毁局部变量
			throwable = null;
		}
		return null;
	}

	/**
	 * 批量卷拆<br/>
	 * ajax调用
	 * @return
	 * @throws Exception
	 */
	public String brokeArchivesInfo() throws Exception {
		System.out.println("-批量卷拆----------------------------");
		boolean pFlag = true;
		pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息

		HttpServletRequest request = this.getRequest();
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		ArchivesInfo archivesInfo = null;
		int archivesTypeID = 0;
		UserInfo userInfo = null;
		try {
			pErrPos = 1;
			 userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
			
			//获得页面参数
			if (request.getParameter("archivesTypeID")==null || request.getParameter("archivesTypeID")==""){
				pFlag = false;
				pErrInfo.getContent().append("获得参数archivesTypeID失败！");
			}
			else{
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			}

			if (pFlag) {
				pErrPos = 3;
				if (NBXHS == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要拆卷的档案！");
				}
			}

			//构造业务逻辑所需参数
			if (pFlag) {
				pErrPos = 4;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

				archivesInfos = new ArrayList<ArchivesInfo>();
				for (int nbxh : NBXHS) {
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID,nbxh);
					archivesInfos.add(archivesInfo);
				}
			}

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 5;
				if (archivesInfoWorkingManageService.brokeArchivesInfo(archivesType, archivesInfos,userInfo.getUserID(), pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"拆卷失败: ");
				}
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			// 销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 归档信息打回
	 * @return
	 * @throws Exception
	 */
	public String savedCallBack()throws Exception{
		System.out.println("--------------------------归档信息打回！-----------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		ArchivesType archivesType = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
			int archivesTypeID = Integer.parseInt(this.getRequest().getParameter("archivesTypeId"));
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				if (archivesInfoSavedManageService.savedCallBack(NBXHS,userInfo,archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "打回档案信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.print("成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
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
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 获得归档信息修改树
	 * @return
	 * @throws Exception
	 */
	public String getArchivesTypeTreeForSavedEdit()throws Exception{
		HttpServletRequest request = this.getRequest();
		UserInfo userInfo = (UserInfo) this.getSession(false).getAttribute("userInfo");

		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(archivesTypes);

		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction","archivesInfoManageAction_getHtmlCodeForUseQuery.action");
		reslutStr = "/DAGL/left1.jsp";
		return SUCCESS;
	}
	
	/**
	 * 根据分类得到档案修改页面查询表单的HTML代码
	 * 
	 * @param
	 * @return
	 */
	public String getHtmlCodeForUseQuery() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		int archivesTypeID = 0;
		String htmlCode = "";
		Map<String, ArchivesTypeDataItem> dataItems = null;
		try {
			pErrPos = 1;
			archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));

			// 判断是否有可用于著录查询的数据项
			if (pFlag) {
				pErrPos = 4;
				dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForUseQuery();
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("此分类下没有可用著录查询的数据项！");
				}
			}

			// 得到html代码
			if (pFlag) {
				pErrPos = 5;
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);
			}
			reslutStr = "/DAGL/savedArchivesEdit.jsp";
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("archivesTypeID", archivesTypeID);
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// 拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					htmlCode = "<script>alert(\"" + pErrInfo.toShortString() + "!\");</script>";
					request.setAttribute("htmlCode", htmlCode);
				}
				logger.error(pErrInfo.getContent());
			}
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据页面条件查询已著录完成的档案信息<br/> 
	 * 适用功能档案管理
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByConditionForEdit() throws Exception {
		System.out.println("--查询著录完成的档案信息！---------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		List<ArchivesInfo> archivesInfos = null;
		Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		Map<String, ArchivesTypeDataItem> dataItems = null;
		//UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
		try {
			//获得页面参数
			pErrPos = 1;
			int archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeID"));
			int[] userIds = ((UserInfo) this.getSession(false).getAttribute("userInfo")).getChargeUserIDs();
			System.out.println("archivesTypeId: "+archivesTypeId);
			System.out.println("userIds: "+userIds);
			ArchivesType archivesType = new ArchivesType(archivesTypeId);

			dataPageInfo.setPageSize(20);

			pErrPos = 2;
			archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
			ArchivesInfoQueryCondition archivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// 取出所有的页面传入的条件的名字数组
			String[] values = null;// 保存某个数据项里面的vlue值
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//从系统数据中取出可用于著录查询的著录项
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();

			
			//开始构造查询条件集合
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// 取出条件的名字
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// 取出条件所对应的值
					// 该值为dataItem的Id

					dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem
					
					if (dataItem != null && values.length > 1) {//两个值
						
						if (values[0] != "" && values[1] != "") {//都不为空
							// 把值放入archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						} else if(values[0] == "" && values[1] == ""){//都为空不做处理
							

						}else{//有一为空
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}
						
					}else if(dataItem != null && values[0] != ""){//只有一个值
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
					}
				}
			}

			// 调用业务逻辑
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(-1),
																		EnumWorkFlowStatus.归档信息打回修改, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
			}
			reslutStr = "/DAGL/savedArchivesEdit.jsp";
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
			request.setAttribute("archivesInfos", archivesInfos);
			request.setAttribute("archivesTypeID", archivesTypeId);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions));
		} catch (Exception e) {
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\"" + pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// 销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案归档
	 */
	public String placeOnArchives() throws Exception{
		System.out.println("------------归档------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			//开始处理 1...
			pErrPos = 1;
			int archivesTypeID = Integer.parseInt(this.getRequest().getParameter("archivesTypeID"));
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			
			System.out.println("archivesTypeID: " + archivesTypeID);
			System.out.println("NBXHS数组的长度： " + NBXHS.length);
			//调用业务逻辑归档
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedManageService.saveArchivesInfos(NBXHS[0],archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条件查询档案信息失败： ");
					System.out.println(pErrInfo.toString());
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
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 得到要插卷的案卷信息<br/> 
	 * 适用功能档案管理
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByConditionForChaJuan() throws Exception {
		System.out.println("--查询著录完成的档案信息！---------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		List<ArchivesInfo> archivesInfos = null;
		List<ArchivesInfo> archivesInfos1 = null;
		Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		Map<String, ArchivesTypeDataItem> dataItems = null;
		//UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
		try {
			//获得页面参数
			pErrPos = 1;
			int archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeID"));
			int[] userIds = ((UserInfo) this.getSession(false).getAttribute("userInfo")).getChargeUserIDs();
			
			System.out.println("archivesTypeId: "+archivesTypeId);
			System.out.println("userIds: "+userIds);
			
			
			ArchivesType archivesType = new ArchivesType(archivesTypeId);

			dataPageInfo.setPageSize(20);

			pErrPos = 2;
			archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
			ArchivesInfoQueryCondition archivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// 取出所有的页面传入的条件的名字数组
			String[] values = null;// 保存某个数据项里面的vlue值
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//从系统数据中取出可用于著录查询的著录项
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForInputQuery();

			
			//开始构造查询条件集合
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// 取出条件的名字
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// 取出条件所对应的值
					// 该值为dataItem的Id

					dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem
					
					if (dataItem != null && values.length > 1) {//两个值
						
						if (values[0] != "" && values[1] != "") {//都不为空
							// 把值放入archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						} else if(values[0] == "" && values[1] == ""){//都为空不做处理
							

						}else{//有一为空
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}
						
					}else if(dataItem != null && values[0] != ""){//只有一个值
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
					}
				}
			}

			// 调用业务逻辑
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(1),
																		EnumWorkFlowStatus.著录完成, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
			}

			if (pFlag) {
				pErrPos = 4;
				archivesInfos1 = new ArrayList<ArchivesInfo>();
				if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(1),
																		EnumWorkFlowStatus.归档信息打回修改, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos1,
																		pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
				}
			}
			
			if (pFlag) {
				for (ArchivesInfo archivesInfo : archivesInfos1) {
					if(!archivesInfos.contains(archivesInfo)){
						archivesInfos.add(archivesInfo);
					}
				}
			}
			
			reslutStr = "/DAGL/findParent.jsp";
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
			request.setAttribute("archivesInfos", archivesInfos);
			request.setAttribute("archivesTypeID", archivesTypeId);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions));
		} catch (Exception e) {
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// 销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 插卷
	 */
	public String insertFileToArchives()throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = this.getRequest();
		try {
			//获得页面参数
			pErrPos = 1;
			int parentNBXH = Integer.parseInt(request.getParameter("parentNBXH"));
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");

			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			//调用业务逻辑插卷
			if (pFlag) {
				if(archivesInfoWorkingManageService.insertFileToArchives(userInfo.getUserID(),archivesType,parentNBXH,NBXHS,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "插卷失败: ");
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
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
}
