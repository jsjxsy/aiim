package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IAppraisalKeepDestroyDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalPublicDetailManageService;
import com.orifound.aiim.bll.service.IAppraisalUseScopesDetailManageService;
import com.orifound.aiim.bll.service.IArchivesInfoSavedManageService;
import com.orifound.aiim.bll.service.IDepartmentInfoManageService;
import com.orifound.aiim.bll.service.IRetentionPeriodManageService;
import com.orifound.aiim.entity.AppraisalKeepDestroyDetail;
import com.orifound.aiim.entity.AppraisalPublicDetail;
import com.orifound.aiim.entity.AppraisalUseScopesDetail;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.RetentionPeriod;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;
import com.orifound.aiim.web.util.StringTool;

/**
 * 档案鉴定控制类（Action）
 * @author tyb
 *
 */
public class ArchiveAppraisalAction extends ActionSupport {
	
	private int pageSize = 3;

	private static final long serialVersionUID = 2611010082743690512L;
	
	static Log logger = LogFactory.getLog(ArchiveAppraisalAction.class);
	
	/**
	 * 注入 档案归档信息的管理服务对象
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * 注入部门信息管理服务对象
	 */
	@Autowired
	private IDepartmentInfoManageService departmentInfoManageService;
	
	/**
	 * 注入保管期限管理服务对象
	 */
	@Autowired
	private IRetentionPeriodManageService retentionPeriodManageService;
	
	/**
	 * 注入存毁鉴定明细情况管理服务对象
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailManageService appraisalKeepDestroyDetailManageService;
	
	/**
	 * 注入案公开/开放鉴定明细情况表的实体类管理服务对象
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * 注入档案划分控制鉴定明细情况表的实体类管理服务对象
	 */
	@Autowired
	private IAppraisalUseScopesDetailManageService appraisalUseScopesDetailManageService;
	
	
	// 分页实体BEAN
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	/**
	 * 类型类型id
	 * 1：存毁鉴定登记
	 * 2：开放鉴定登记
	 * 3：公开鉴定登记
	 * 4：划控鉴定登记
	 */
	private int switchRegisterId;
	
	/**
	 * 鉴定登记信息树 菜单节点值
	 * 1：存毁鉴定查询
	 * 2：开放鉴定查询
	 * 3：公开鉴定查询
	 * 4：划控鉴定查询
	 */
	private int nodeId;

	/**
	 * 保存Action跳转URL地址
	 */
	private String resultURL;
	
	/**
	 * 档案分类id
	 */
	private int archivesTypeId;
	
	/**
	 * 档案形成部门id
	 */
	private int formationDepartmentID;
	
	/**
	 * 档案形成部门集合
	 */
	private List<DepartmentInfo> formationDepartments = new ArrayList<DepartmentInfo>();
	
	/**
	 * 保管期限数据字典表
	 */
	private List<RetentionPeriod> retentionPeriods = new ArrayList<RetentionPeriod>();
	
	/**
	 * 所有档案一级分类信息
	 */
	private List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
	
	/**
	 * 鉴定日期
	 */
	private String AppraisalDate;
	
	/**
	 * 截止鉴定日期
	 */
	private String AppraisalDateEnd;
	
	/**
	 * 鉴定人
	 */
	private String AppraisalPersion;
	
	/**
	 * 鉴定依据
	 */
	private String AppraisalReason;
	
	/**
	 * 是否开放
	 */
	private String PublicFlag;
	
	/**
	 * 鉴定查询  档号id
	 */
	private String archivesID;
	
	/**
	 * 鉴定查询  题名
	 */
	private String title;
	
	/**
	 * 鉴定信息id
	 */
	private int appraisalDetailId;
	
	
	/**
	 * 分类查询:得到档案分类树
	 * @return String 
	 * @throws Exception
	 */
	public String getArchivesTypeTree() throws Exception {
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {		
			pErrPos = 1;			
			//获取所有档案分类
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			
			
			//将档案分类集合构造出生成树所需的javascript代码
			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
			
			//将数据通过request传到页面
			request.setAttribute("tree", tree);

			//设置档案类型菜单 链接地址
			StringBuffer proceseAction = new StringBuffer("archiveAppraisalAction_");
			
			//选择鉴定登记类型
			switch (switchRegisterId) {
			//存毁鉴定登记
			case 1:
				proceseAction.append("searchBeforesaveDestroyRegister").append(".action");
				break;
				
			//开放鉴定登记
			case 2:
				proceseAction.append("buildBeforePublicSearch").append(".action");
				break;
				
			//公开鉴定登记
			case 3:
				proceseAction.append("buildBeforeOpenSearch").append(".action");
				break;
				
			//划控鉴定登记	
			case 4:
				proceseAction.append("buildBeforeControlAreaSearch").append(".action");
				break;	
			//设置默认为存毁鉴定登记	
			default:
				proceseAction.append("searchBeforesaveDestroyRegister").append(".action");
				break;
			}
			request.setAttribute("proceseAction", proceseAction.toString());
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
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	
	/**
	 * 存毁鉴定登记前查询指定档案类型的过期档案
	 * @return String
	 */
	public String searchBeforesaveDestroyRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//查询指定档案分类的所有归期档案
			if (pFlag) {
				dataPageInfo.setPageSize(pageSize);
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查询指定档案类型的过期档案信息 失败：");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("archivesInfos", archivesInfos);
			}
			
			//获取档案形成部门集合、保存期限字典表、表单域、显示域
			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "开放登记前查询，构建页面参数失败：");
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

		resultURL = "/JDGL/saveDestroyRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 存毁鉴定 打印查询
	 * @return
	 */
	public String searchBeforeSaveDestroyPrint() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}
			LinkedHashMap<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(nodeId).getDataItemsForListDisplay();
			
			//插入需要打印显示的数据项
			Map<String, ArchivesTypeDataItem> viewDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
			//增加全宗号
			ArchivesTypeDataItem dataItem = dataItems.get(EnumSystemDataItem.全宗编号.getEnumValue());
			dataItem.setDisplayText("全宗号");
			dataItem.setInputEditBoxWidth(45);
			viewDataItems.put(EnumSystemDataItem.全宗编号.getEnumValue(), dataItem);
			
			//增加档号
			dataItem = dataItems.get(EnumSystemDataItem.档号.getEnumValue());
			dataItem.setInputEditBoxWidth(110);
			viewDataItems.put(EnumSystemDataItem.档号.getEnumValue(), dataItem);
			
			//增加题名
			dataItem = dataItems.get(EnumSystemDataItem.题名.getEnumValue());
			dataItem.setInputEditBoxWidth(150);
			dataItem.setKeyInCol("class='archivesinfoTitle'");
			viewDataItems.put(EnumSystemDataItem.题名.getEnumValue(), dataItem);
			
			//增加档案页数
			dataItem = dataItems.get(EnumSystemDataItem.档案页数.getEnumValue());
			dataItem.setDisplayText("页数");
			dataItem.setInputEditBoxWidth(30);
			viewDataItems.put(EnumSystemDataItem.档案页数.getEnumValue(), dataItem);
			
			//增加保管期限文本
			dataItem = dataItems.get(EnumSystemDataItem.保管期限文本.getEnumValue());
			dataItem.setDisplayText("保管期限");
			dataItem.setInputEditBoxWidth(60);
			viewDataItems.put(EnumSystemDataItem.保管期限文本.getEnumValue(), dataItem);
			
			//增加档案密级文本
			dataItem = dataItems.get(EnumSystemDataItem.档案密级文本.getEnumValue());
			dataItem.setDisplayText("密级");
			dataItem.setInputEditBoxWidth(40);
			viewDataItems.put(EnumSystemDataItem.档案密级文本.getEnumValue(), dataItem);
			
			request.setAttribute("dataItems", viewDataItems);

			//查询指定档案分类的所有归期档案
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
//				dataPageInfo.setCurrentPage(1);
//				dataPageInfo.setPageSize(12);
				//查询所有归期档案
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, null, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查询指定档案类型的过期档案信息 失败：");
				}
				ServletActionContext.getRequest().setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
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
		
		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroyPrint.jsp";
		return SUCCESS;
	}
	
	/**
	 * 存毁鉴定登记
	 * @return String
	 */
	public String saveDestroyRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//查询指定档案分类的所有归期档案
			if (pFlag) {
				dataPageInfo.setPageSize(pageSize);
				
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查询指定档案类型的过期档案信息 失败：");
				}
				
				String retentionPeriodId = null;
				String NBXH = null;
				Map<Integer[], Integer> saveArchives = new LinkedHashMap<Integer[], Integer>();
				Integer[] integers = new Integer[2];
				integers[0] = nodeId;
				List<Integer[]> destoryArchives = new ArrayList<Integer[]>();
				
				//批量插入存毁鉴定明细表参数
				Map<Integer, Map<String, String>> batchArchives = new LinkedHashMap<Integer, Map<String,String>>();
				Map<String, String> batchArchivesInner = new HashMap<String, String>();
				
				//循环 获取页面提交的内部序号以及保存期限 参数
				for(ArchivesInfo info : archivesInfos) {
					retentionPeriodId = request.getParameter("retentionPeriod"+info.getNBXH());
				
					System.out.println("retentionPeriodId="+retentionPeriodId);
					
					NBXH = request.getParameter("NBXH"+info.getNBXH());
					
					//判断是否设置保存期限
					if(StringTool.checkNull(retentionPeriodId)==false || StringTool.checkNull(NBXH)==false) {
						pFlag = false;
						pErrInfo.getContent().append("整个页面的保存期限没有全部设置，提交失败。");
						break;
					} else {
						//重新构建参数
						integers = new Integer[2];
						integers[0] = nodeId;
						integers[1] = info.getNBXH();
						
						//判断是否为销毁档案
						if(retentionPeriodId.equals("0")) {	//销毁档案
							destoryArchives.add(integers);
							batchArchivesInner.put("AppraisalDeletedFlag", "1");//销毁标志
						} else {
							saveArchives.put(integers, Integer.valueOf(retentionPeriodId));
							batchArchivesInner.put("AppraisalDeletedFlag", "0");//销毁标志
						}
						//新的保存期限id
						batchArchivesInner.put("NewRetentionPeriodID", retentionPeriodId);
						
						//插入一个存毁明细记录参数
						batchArchives.put(Integer.valueOf(NBXH), batchArchivesInner);
					}
				}
				
				for(Integer[] i : saveArchives.keySet()) {
					System.out.println("ar="+i[0]);
					System.out.println("nb"+i[1]);
					
					System.out.println(saveArchives.get(i));
				}
				
				if (pFlag) {
					UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
					
					Map<String, String> opinion = new HashMap<String, String>();
					
//					@param batchArchives 批量档案信息 键：内部序号、值Map集合：是否销毁AppraisalDeletedFlag、新保存期限ID(NewRetentionPeriodID)
//					@param opinion 公共参数 传递参数：	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
					//构建公共参数
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//批量更新档案的存毁信息
					if (pFlag) {
						if (archivesInfoSavedManageService.updateBatchRetentionPeriod(userInfo, archivesType, saveArchives, destoryArchives, batchArchives, opinion, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "Action批量更新档案信息的保存期限 失败：");
						}
					}
				}
				
			}
			
			
			//存毁鉴定完成后 分页查询第一页
			if (pFlag) {
				dataPageInfo.setCurrentPage(1);
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查询指定档案类型的过期档案信息 失败：");
				}
				
				request.setAttribute("archivesInfos", archivesInfos);
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "开放登记前查询，构建页面参数失败：");
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

System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/saveDestroyRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 开放鉴定登记查询前，构建页面参数
	 * @return String
	 */
	public String buildBeforePublicSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "开放登记前查询，构建页面参数失败：");
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
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 构建页面参数
	 * @return
	 */
	private boolean buildPageParams(Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			List<String> filterParams = new ArrayList<String>();
			filterParams.add("nodeId");
			filterParams.add("dataPageInfo.currentPage");
			filterParams.add("gotoPage");
			filterParams.add("preSelectRow");
			filterParams.add("AppraisalPersion");
			filterParams.add("AppraisalDate");
			filterParams.add("AppraisalReason");
			filterParams.add("PublicFlag");
			
			
			//正则过滤 一组参数
			filterParams.add("NBXH[0-9]*");
			filterParams.add("openFlag[0-9]*");
			
			if (buildArchivesInfoQueryConditions(nodeId, filterParams, archivesInfoQueryConditions , pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "构建指定档案类型档案查询条件对象类集合 失败：");
			}
			
			//根据档案类型编号设置：页面查询表单域、分页显示数据项。
			if (pFlag) {
				pErrPos = 2;
				if (this.setHtmlCodeByArchivesTypeId(nodeId, archivesInfoQueryConditions) == false) {
					pFlag = false;
					pErrInfo.getContent().append("根据档案类型编号设置页面参数 失败。");
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

		return pFlag;
	}
	
	/**
	 * 开放鉴定登记前 查询档案信息
	 * @return String
	 */
	public String searchBeforePublicRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			
			//构建页面参数
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "构建页面参数出错。");
				}
			}
			
			//构建指定档案类型档案查询条件对象类集合
			if (pFlag) {
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				//检测用户信息是否为空
				if (pFlag) {
					if (userInfo==null || userInfo.getUserID()<=0) {
						pFlag = false;
						pErrInfo.getContent().append("session用户信息非法为空。");
					}
				}
				
				//鉴定管理->开放鉴定：查询指定档案类型的档案信息
				if (pFlag) {
					ArchivesType archivesType = new ArchivesType();
					archivesType.setID(nodeId);
					dataPageInfo.setPageSize(pageSize);
					List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
					
					//分页查询指定档案类型的档案信息
					if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
					}
					request.setAttribute("archivesInfos", archivesInfos);
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
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 开放鉴定登记
	 * @return String
	 */
	public String publicRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//构建页面参数
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "构建页面参数出错。");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//检测用户信息是否为空
			if (pFlag) {
				pErrPos = 4;
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session用户信息非法为空。");
				}
			}
			
			if (pFlag) {
				pErrPos = 5;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//分页查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
				}
				
				pErrPos = 6;
				//需要进行操作的档案
				List<Integer> archivesNBXHs = new ArrayList<Integer>();
				String NBXH = "";
				String openFlag = "";
				if (pFlag && archivesInfos.size()>=1) {
					for(ArchivesInfo archivesInfo : archivesInfos) {
						NBXH = request.getParameter("NBXH"+archivesInfo.getNBXH());
						openFlag = request.getParameter("openFlag"+archivesInfo.getNBXH());
						
						//进行取消开放操作
						if ("1".equals(PublicFlag) && !StringTool.checkNull(openFlag)) {	
							archivesNBXHs.add(Integer.valueOf(NBXH));
						} 
						
						//进行开放操作
						if ("0".equals(PublicFlag) && StringTool.checkNull(openFlag) && openFlag.equals("on")) { 
							archivesNBXHs.add(Integer.valueOf(NBXH));
						}
					}
				}
				
				//执行开放鉴定登记操作
				if (pFlag && archivesNBXHs.size() >= 1) {
					pErrPos = 7;
					//记录开放鉴定明细
					Map<String, String> opinion = new HashMap<String, String>();
					
//						@param opinion 公共参数 传递参数：	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
					//构建公共参数
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//修改档案开放标志
					if (archivesInfoSavedManageService.updateBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, PublicFlag, opinion, pErrInfo) == false ) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定 批量更新档案开放信息 失败：");
					}
				}
			}
				
			//开放鉴定登记完成后：查询指定档案类型的档案信息
			if (pFlag) {
				pErrPos = 9;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				dataPageInfo.setCurrentPage(1);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//分页查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
				}
				request.setAttribute("archivesInfos", archivesInfos);
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

		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}

	/**
	 * 公开鉴定登记查询前，构建页面参数
	 * @return String
	 */
	public String buildBeforeOpenSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "开放登记前查询，构建页面参数失败：");
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
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 公开鉴定登记前 查询档案信息
	 * @return String
	 */
	public String searchBeforeOpenRegister(){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			
			//构建页面参数
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "构建页面参数出错。");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//检测用户信息是否为空
			if (pFlag) {
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session用户信息非法为空。");
				}
			}
			
			//鉴定管理->开放鉴定：查询指定档案类型的档案信息
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//分页查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定：查询指定档案类型的档案信息 失败：");
				}
				request.setAttribute("archivesInfos", archivesInfos);
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
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 公开鉴定登记
	 * @return String
	 */
	public String openRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//构建页面参数
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "构建页面参数出错。");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//检测用户信息是否为空
			if (pFlag) {
				pErrPos = 4;
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session用户信息非法为空。");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 5;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//鉴定管理->公开鉴定：查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->公开鉴定：查询指定档案类型的档案信息 失败：");
				}
				
				pErrPos = 6;
				//需要进行操作的档案
				List<Integer> archivesNBXHs = new ArrayList<Integer>();
				String NBXH = "";
				String openFlag = "";
				if (pFlag && archivesInfos.size()>=1) {
					for(ArchivesInfo archivesInfo : archivesInfos) {
						NBXH = request.getParameter("NBXH"+archivesInfo.getNBXH());
						openFlag = request.getParameter("openFlag"+archivesInfo.getNBXH());
						
						//进行取消公开操作
						if ("1".equals(PublicFlag) && !StringTool.checkNull(openFlag)) {	
							archivesNBXHs.add(Integer.valueOf(NBXH));
						} 
						
						//进行公开操作
						if ("0".equals(PublicFlag) && StringTool.checkNull(openFlag) && openFlag.equals("on")) { 
							archivesNBXHs.add(Integer.valueOf(NBXH));
						}
					}
				}
				
				//执行开放鉴定登记操作
				if (pFlag && archivesNBXHs.size() >= 1) {
					pErrPos = 7;
					//记录开放鉴定明细
					Map<String, String> opinion = new HashMap<String, String>();
					
//						@param opinion 公共参数 传递参数：	鉴定日期AppraisalDate、鉴定原因AppraisalReason、鉴定人AppraisalPersion
					//构建公共参数
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//鉴定管理->公开鉴定 批量更新档案密级信息
					if (archivesInfoSavedManageService.updateBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, PublicFlag, opinion, pErrInfo) == false ) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action 鉴定管理->公开鉴定 批量更新档案密级信息 失败：");
					}
				}
			}
				
			//开放鉴定登记完成后：查询指定档案类型的档案信息
			if (pFlag) {
				pErrPos = 9;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				dataPageInfo.setCurrentPage(1);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//分页查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->公开鉴定：查询指定档案类型的档案信息 失败：");
				}
				request.setAttribute("archivesInfos", archivesInfos);
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

		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 划控鉴定登记查询前，构建页面参数
	 * @return String
	 */
	public String buildBeforeControlAreaSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "开放登记前查询，构建页面参数失败：");
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
		
		resultURL = "/JDGL/controlAreaRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * 划控鉴定前 分页查询档案信息
	 * @return
	 */
	public String searchBeforeControlAreaRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//构建页面参数
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "构建页面参数出错。");
				}
			}
				
			HttpServletRequest request = ServletActionContext.getRequest();;
			UserInfo userInfo = (UserInfo) request .getSession().getAttribute("userInfo");
			//检测用户信息是否为空
			if (pFlag) {
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session用户信息非法为空。");
				}
			}
			
			//鉴定管理->开放鉴定：查询指定档案类型的档案信息
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//分页查询指定档案类型的档案信息
				if (archivesInfoSavedManageService.queryClassifiedForControlAreaAppraisal(userInfo, archivesType,  new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->划控鉴定：查询指定档案类型的档案信息 失败：");
				}
				request.setAttribute("archivesInfos", archivesInfos);
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
		
		resultURL = "/JDGL/controlAreaRegister.jsp";
		return SUCCESS;
	}
	
	
	/**
	 * 鉴定登记前查询指定档案类型的档案集合
	 * @return boolean
	 */
	private boolean searchBeforeRegister(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//判断是否需要构建字典
			if (pFlag) {
				pErrPos = 3;
				//获取所有档案形成部门集合
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
				
				//获取保存期限字典表
				LinkedHashMap<Integer,RetentionPeriod> retentionPeriodMaps = new LinkedHashMap<Integer, RetentionPeriod>();
				if (pFlag) {
					if (retentionPeriodManageService.findRetentionPeriods(retentionPeriodMaps, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action 查找所有的保管期限信息 失败：");
					}
				}
				retentionPeriods.addAll(retentionPeriodMaps.values());
			}
			
			//根据档案类型编号设置：页面查询表单域、分页显示数据项。
			if (pFlag) {
				pErrPos = 2;
				if (this.setHtmlCodeByArchivesTypeId(nodeId, null) == false) {
					pFlag = false;
					pErrInfo.getContent().append("根据档案类型编号设置页面参数 失败。");
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
		return pFlag;
	}
	
	/**
	 * 获取鉴定管理->鉴定登记信息 菜单树
	 * @return String
	 */
	public String getAppraisalSearchTree() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tree = CreateTreeUtil.getArchiveAppraisalTree();
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","archiveAppraisalAction_switchAppraisalSearch.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	/**
	 * 获取鉴定管理->鉴定登记信息 选择查询鉴定类型
	 * @return String
	 */
	public String switchAppraisalSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取所有档案形成部门集合
			if (pFlag) {
				pErrPos = 2;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
			}
			
			//获取所有部门类型
			if (pFlag) {
				pErrPos = 3;
				//获取所有的一级档案分类（一级类目）
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}
			
			//根据菜单节点id 选择查询页面
			if (pFlag) {
				pErrPos = 4;
				switch (nodeId) {
				//存毁鉴定查询
				case 11:
					resultURL = "/JDGL/saveDestroySearch.jsp";
					break;
					
				//开放鉴定查询
				case 12:
					resultURL = "/JDGL/publicSearch.jsp";
					break;
					
				//公开鉴定查询
				case 13:
					resultURL = "/JDGL/openSearch.jsp";
					break;
				
				//划控鉴定查询
				case 14:
					resultURL = "/JDGL/controlAreaSearch.jsp";
					break;
					
				//默认存毁鉴定查询
				default:
					resultURL = "/JDGL/saveDestroySearch.jsp";
					break;
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

		System.out.println(pErrInfo.toString());
		return SUCCESS;
	}
	
	/**
	 * 未鉴定登记查询 构建查询参数
	 * @param params	键包含：档号archivesID、题名title、 档案类型archivesTypeId、档案形成部门formationDepartmentID、
	 * 					鉴定开始日期AppraisalDate、 鉴定截止日期AppraisalDateEnd
	 * @param pErrInfo
	 * @return
	 */
	private boolean buildParamsMapForAppraisalSearch(Map<String, String> params, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			if (pFlag) {
				//检查是否 使用档号查询条件
				if(StringTool.checkNull(archivesID)) {
					params.put("archivesID", archivesID);
				}
				
				System.out.println("title="+title);
				
				//检查是否 使用 题名 查询条件
				if(StringTool.checkNull(title)) {
					params.put("title", title);
				}
				
				//检查是否 使用 档案类型 条件
				if(archivesTypeId >= 1) {
					params.put("archivesTypeId", ""+archivesTypeId);
				}
				
				//检查是否 使用 档案形成部门 条件
				if(formationDepartmentID >= 1) {
					params.put("formationDepartmentID", ""+formationDepartmentID);
				}
				
				//检查是否 使用 鉴定开始日期 条件
				if (StringTool.checkNull(AppraisalDate)) {
					params.put("AppraisalDate", ""+AppraisalDate);
				}
				
				//检查是否 使用 鉴定截止日期 条件
				if (StringTool.checkNull(AppraisalDateEnd)) {
					params.put("AppraisalDateEnd", ""+AppraisalDateEnd);
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

		return pFlag;
	}
	
	/**
	 * 存毁鉴定查询
	 * @return String
	 */
	public String saveDestroySearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取所有档案形成部门集合
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->存毁鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
			}
			
			//获取所有部门类型
			if (pFlag) {
				pErrPos = 4;
				//获取所有的一级档案分类（一级类目）
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//未鉴定登记查询 构建查询参数
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 构建查询参数  失败：");
				}
				
				//分页查询存毁鉴定登记信息
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails = new ArrayList<AppraisalKeepDestroyDetail>();
					if (appraisalKeepDestroyDetailManageService.findWithPage(params, dataPageInfo, appraisalKeepDestroyDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "分页查询存毁鉴定登记信息 失败：");
					}
					ServletActionContext.getRequest().setAttribute("appraisalKeepDestroyDetails", appraisalKeepDestroyDetails);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroySearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * 存毁鉴定详情查询
	 * @return
	 */
	public String saveDestroySearchDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalKeepDestroyDetail appraisalKeepDestroyDetail = new AppraisalKeepDestroyDetail();
				//根据唯一标识查找存毁鉴定明细情况信息
				if (appraisalKeepDestroyDetailManageService.findAppraisalKeepDestroyDetailByID(appraisalDetailId, appraisalKeepDestroyDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 根据唯一标识查找存毁鉴定明细情况信息 失败：");
				}
				ServletActionContext.getRequest().setAttribute("appraisalKeepDestroyDetail", appraisalKeepDestroyDetail);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroySearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * 开放鉴定查询
	 * @return String
	 */
	public String publicSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取所有档案形成部门集合
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->开放鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
			}
			
			//获取所有部门类型
			if (pFlag) {
				pErrPos = 4;
				//获取所有的一级档案分类（一级类目）
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//未鉴定登记查询 构建查询参数
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 构建查询参数  失败：");
				}
				
				//开放鉴定查询 公开标志为1
				params.put("PublicFlag", "1");
				
				//分页查询存毁鉴定登记信息
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalPublicDetail> appraisalPublicDetails = new ArrayList<AppraisalPublicDetail>();
					if (appraisalPublicDetailManageService.findWithPage(params, dataPageInfo, appraisalPublicDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "分页查询开放鉴定登记信息 失败：");
					}
					
					ServletActionContext.getRequest().setAttribute("appraisalPublicDetails", appraisalPublicDetails);
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

		System.out.println(pErrInfo.toString());
	
		resultURL = "/JDGL/publicSearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * 开放鉴定详情查询
	 * @return String
	 */
	public String publicSearchDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalPublicDetail appraisalPublicDetail = new AppraisalPublicDetail();
				//根据唯一标识查找档案公开/开放鉴定明细情况表的实体类信息
				if (appraisalPublicDetailManageService.findAppraisalPublicDetailByID(appraisalDetailId, 1, appraisalPublicDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 根据唯一标识查找档案公开/开放鉴定明细情况表的实体类信息 失败：");
				}
				ServletActionContext.getRequest().setAttribute("appraisalPublicDetail", appraisalPublicDetail);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/publicSearch_detail.jsp";
		return SUCCESS;
	}

	/**
	 * 公开鉴定查询
	 * @return String
	 */
	public String openSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取所有档案形成部门集合
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->公开鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
			}
			
			//获取所有部门类型
			if (pFlag) {
				pErrPos = 4;
				//获取所有的一级档案分类（一级类目）
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//未鉴定登记查询 构建查询参数
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 构建查询参数  失败：");
				}
				
				//开放鉴定查询 公开标志为1
				params.put("PublicFlag", "0");
				
				//分页查询存毁鉴定登记信息
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalPublicDetail> appraisalPublicDetails = new ArrayList<AppraisalPublicDetail>();
					if (appraisalPublicDetailManageService.findWithPage(params, dataPageInfo, appraisalPublicDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "分页查询公开鉴定登记信息 失败：");
					}
					
					ServletActionContext.getRequest().setAttribute("appraisalPublicDetails", appraisalPublicDetails);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/openSearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * 公开鉴定详情查询
	 * @return
	 */
	public String openSearchDetail() {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalPublicDetail appraisalPublicDetail = new AppraisalPublicDetail();
				//根据唯一标识查找档案公开/开放鉴定明细情况表的实体类信息
				if (appraisalPublicDetailManageService.findAppraisalPublicDetailByID(appraisalDetailId, 0, appraisalPublicDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 根据唯一标识查找档案公开/开放鉴定明细情况表的实体类信息 失败：");
				}
				ServletActionContext.getRequest().setAttribute("appraisalPublicDetail", appraisalPublicDetail);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/openSearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * 划控鉴定查询
	 * @return String
	 */
	public String controlAreaSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取所有档案形成部门集合
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 鉴定管理->划控鉴定登记：查找系统中所有档案形成部门信息 失败：");
				}
			}
			
			//获取所有部门类型
			if (pFlag) {
				pErrPos = 4;
				//获取所有的一级档案分类（一级类目）
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//未鉴定登记查询 构建查询参数
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 构建查询参数  失败：");
				}
				
				//分页查询存毁鉴定登记信息
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalUseScopesDetail> appraisalUseScopesDetails = new ArrayList<AppraisalUseScopesDetail>();
					if (appraisalUseScopesDetailManageService.findWithPage(params, dataPageInfo, appraisalUseScopesDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "分页查询划控鉴定登记信息 失败：");
					}
					ServletActionContext.getRequest().setAttribute("appraisalUseScopesDetails", appraisalUseScopesDetails);
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
		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/controlAreaSearch.jsp";
		return SUCCESS;
		
	}
	
	public String controlAreaSearchDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalUseScopesDetail appraisalUseScopesDetail = new AppraisalUseScopesDetail();
				String NBXHStr = request.getParameter("NBXH");
				int NBXH = 0;
				if (StringTool.checkNull(NBXHStr)) {
					NBXH = Integer.valueOf(NBXHStr);
				}
				
				if (appraisalUseScopesDetailManageService.findAppraisalUseScopesDetailByByNBXH(archivesTypeId, NBXH, appraisalUseScopesDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action 根据档案内部序号查找档案划分控制鉴定明细情况表的实体类信息 失败：");
				}
				
				ServletActionContext.getRequest().setAttribute("appraisalUseScopesDetail", appraisalUseScopesDetail);
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

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/controlAreaSearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * 根据档案类型编号设置：页面查询表单域、分页显示数据项。
	 * @param archivesTypeId 档案类型标号
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean setHtmlCodeByArchivesTypeId(int archivesTypeId, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//数据验证
			if(archivesTypeId <= 0 ){
				pFlag = false;
				pErrInfo.getContent().append("找不到档案分类编号，nodeId传递失败");
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				String htmlCode = this.getHtmlCodeByType(archivesTypeId, archivesInfoQueryConditions);
				
				//将参数传至页面
				request .setAttribute("htmlCode", htmlCode);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
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
		return pFlag;
	}
	
	
	/**
	 * 构建指定档案类型档案查询条件对象类集合
	 * @param archivesTypeId 档案类型id
	 * @param filterParams 正则过滤页面表单项
	 * @param archivesInfoQueryConditions 返回档案类型档案查询条件对象类集合
	 * @return 处理成功返回true，否则返回false
	 */
	@SuppressWarnings("unchecked")
	private boolean buildArchivesInfoQueryConditions(int archivesTypeId, List<String> filterParams, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//输入为空标识，用于过滤掉那些无输入的字段
		boolean inputTrue = false;
		try {
			if (pFlag) {
				pErrPos = 2;
				
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
				Enumeration<String> enumeration = request.getParameterNames();//取出所有的页面传入的条件的名字数组
				String [] values = null;//保存某个数据项里面的vlue值
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				boolean filterFlag = false;
				
				while(enumeration.hasMoreElements()){
					filterFlag = false;
					inputTrue = false;
					
					parameterName = enumeration.nextElement();//取出条件的名字
					
					System.out.println("1212parameterName="+parameterName+"  "+!filterParams.contains(parameterName));
					
					
					//过滤隐藏参数、以及其他参数(鉴定人等)，只加载检索条件参数
					if(filterParams.contains(parameterName)) {
						continue;
					}
					
					
					//正则过滤 传递的NBXH*、publicFlag*一组参数
					for(String param : filterParams) {
						if(parameterName.matches(param)) {
							filterFlag = true;
						}
					}
					
					//如果条件为不需要过滤的 构建查询参数
					if (!filterFlag) {
						//取出条件所对应的值 该值为dataItem的Id
						values = request.getParameterValues(parameterName);
						dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
						
						System.out.println("parameterName="+parameterName);
						System.out.println("values="+values[0]);
						System.out.println("dataItem="+dataItem);
						
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
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
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

		return pFlag;
	}
	
	//工具方法
	/**
	 * 根据档案类别生成页面html查询代码
	 */
	private String getHtmlCodeByType(int archivesTypeId, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String htmlCode = "";
		//得到html代码
		SystemInitializer systemInitializer = SystemInitializer.getInstance();
		Map<String, ArchivesTypeDataItem> dataItems = null;
		//平面结构的档案分类集合，所有档案分类都无层次关系的直接存放在集合中
		if( systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery()== null){
			htmlCode = "<script>alert('没有可查询的数据项！');</script>";
		}else{
			dataItems = systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();
			htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions);
		}
		return htmlCode;
	}
	
	/**
	 * 检查档案信息归档表的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForArchivesInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案信息归档表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			//检查部门信息表的BLL业务逻辑对象的依赖注入
			if (departmentInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"部门信息表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			//检查保管期限管理服务对象的依赖注入
			if (retentionPeriodManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"保管期限数据字典表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (appraisalKeepDestroyDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"存毁鉴定明细情况表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (appraisalPublicDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案公开/开放鉴定明细情况表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (appraisalUseScopesDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"档案划分控制鉴定明细情况表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}

		return pFlag;
	}

	public int getSwitchRegisterId() {
		return switchRegisterId;
	}

	public void setSwitchRegisterId(int switchRegisterId) {
		this.switchRegisterId = switchRegisterId;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	public int getArchivesTypeId() {
		return archivesTypeId;
	}

	public void setArchivesTypeId(int archivesTypeId) {
		this.archivesTypeId = archivesTypeId;
	}

	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}


	public int getFormationDepartmentID() {
		return formationDepartmentID;
	}


	public void setFormationDepartmentID(int formationDepartmentID) {
		this.formationDepartmentID = formationDepartmentID;
	}


	public List<DepartmentInfo> getFormationDepartments() {
		return formationDepartments;
	}


	public void setFormationDepartments(List<DepartmentInfo> formationDepartments) {
		this.formationDepartments = formationDepartments;
	}


	public List<RetentionPeriod> getRetentionPeriods() {
		return retentionPeriods;
	}


	public void setRetentionPeriods(List<RetentionPeriod> retentionPeriods) {
		this.retentionPeriods = retentionPeriods;
	}


	public String getAppraisalDate() {
		return AppraisalDate;
	}

	public void setAppraisalDate(String appraisalDate) {
		AppraisalDate = appraisalDate;
	}

	public String getAppraisalPersion() {
		return AppraisalPersion;
	}
	
	public void setAppraisalPersion(String appraisalPersion) {
		AppraisalPersion = appraisalPersion;
	}
	
	public String getAppraisalReason() {
		return AppraisalReason;
	}

	public void setAppraisalReason(String appraisalReason) {
		AppraisalReason = appraisalReason;
	}

	public String getPublicFlag() {
		return PublicFlag;
	}

	public void setPublicFlag(String publicFlag) {
		PublicFlag = publicFlag;
	}


	public List<ArchivesType> getArchivesTypes() {
		return archivesTypes;
	}


	public void setArchivesTypes(List<ArchivesType> archivesTypes) {
		this.archivesTypes = archivesTypes;
	}


	public String getAppraisalDateEnd() {
		return AppraisalDateEnd;
	}


	public void setAppraisalDateEnd(String appraisalDateEnd) {
		AppraisalDateEnd = appraisalDateEnd;
	}


	public String getArchivesID() {
		return archivesID;
	}


	public void setArchivesID(String archivesID) {
		this.archivesID = archivesID;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getAppraisalDetailId() {
		return appraisalDetailId;
	}


	public void setAppraisalDetailId(int appraisalDetailId) {
		this.appraisalDetailId = appraisalDetailId;
	}
}