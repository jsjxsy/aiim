package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.ezmorph.bean.MorphDynaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IPaperTransferManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.bll.util.ArchivesInfoFactory;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.entity.ArchivesBoxLabel;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumPaperTransferBatchesDealStatus;
import com.orifound.aiim.entity.EnumSystemRole;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
import com.orifound.aiim.entity.PaperTransferBatchesDetail;
import com.orifound.aiim.entity.PaperTransferBatchesQueryCondition;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserChargeDepartmentInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class YJJSAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	
	static Log logger = LogFactory.getLog(YJJSAction.class);
	
	/**
	 * 移交接收义务逻辑接口
	 */
	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	@Autowired
	private IUserInfoManageService userInfoManageService;
	/**
	 * 批次信息对象
	 */
	private PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
	
	public PaperTransferBatch getPaperTransferBatch() {
		return paperTransferBatch;
	}

	public void setPaperTransferBatch(PaperTransferBatch paperTransferBatch) {
		this.paperTransferBatch = paperTransferBatch;
	}
	
	/**
	 * 结果要返回的页面
	 */
	private String resultURL;
	
	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	/**
	 * 用于返回接收json串
	 */
	private String jsonResult;
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	/**
	 * 页面传入的要加入清单批次号
	 */
	private String [] batNos;

	public void setBatNos(String[] batNos) {
		this.batNos = batNos;
	}
	
	/**
	 * 页面传入的内部序号数组
	 */
	private int[] NBXHS;

	public int[] getNBXHS() {
		return NBXHS;
	}

	public void setNBXHS(int[] nBXHS) {
		NBXHS = nBXHS;
	}

	/**
	 * 查询范围对象
	 */
	private PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition = new PaperTransferBatchesQueryCondition();

	public PaperTransferBatchesQueryCondition getPaperTransferBatchesQueryCondition() {
		return paperTransferBatchesQueryCondition;
	}

	public void setPaperTransferBatchesQueryCondition(
			PaperTransferBatchesQueryCondition paperTransferBatchesQueryCondition) {
		this.paperTransferBatchesQueryCondition = paperTransferBatchesQueryCondition;
	}

	/**
	 * 分页信息对象
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	/**
	 * 形成部门得到档案分类树，并指定处理树事件地址<br/>
	 * 适用于形成部门档案移交
	 * @return 档案类型tree 带有节点：著录审核通过、著录审核未通过、移交未通过
	 * @throws Exception
	 */
	public String getArchivesTypeTreeForXCBMDAYJ() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = null;
		Map<Integer, ArchivesTypeEx> archivesTypeExs = null;
		
		try {
			pErrPos = 1;
			userInfo = (UserInfo) request.getSession(false).getAttribute("userInfo");

			//得到所有档案分类对象的子节点构造archivesTypeExs集合
			archivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
			if(userInfo.getArchivesTypes() != null && userInfo.getArchivesTypes().size()>0){

				CommonUtil.getChildPlaneArchivesTypeExs(userInfo.getArchivesTypes(), archivesTypeExs, pErrInfo);
				
			}
			
			//调用业务逻辑取得个分类下的三个属性
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferManageService.statPaperCheckBackCountAndInputCheckReslut(userInfo.getChargeUserIDs(), archivesTypeExs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "得到档案分类树失败");
				}
			}
			
			//创建树
			if (pFlag) {
				pErrPos = 3;
				
				LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
				
				//将平面的转化为树状
				CommonUtil.convertPlaneArchivesTypeExsToTree(archivesTypeExs, treeArchivesTypeExs, pErrInfo);
				
				//构建树
				String tree = CreateTreeUtil.getArchivesTypeTreeWithProperty(new ArrayList<ArchivesTypeEx>(treeArchivesTypeExs.values()));
				request.setAttribute("proceseAction","YJJSAction_findArchivesForXCBMDAYJ.action");
				request.setAttribute("tree",tree);
				resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				
				//记录日志
				logger.error(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 形成部门得到著录审核通过、著录审核未通过、和移交未通过的档案信息<br/>
	 * 适用于形成部门档案移交
	 * @return 
	 * @throws Exception
	 */
	public String findArchivesForXCBMDAYJ() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = ((UserInfo)request.getSession(false).getAttribute("userInfo"));
		int state = 0;
		int archivesTypeID = 0;
		int [] userIds ={};
		List<ArchivesInfo> archivesInfos = null;
		ArchivesType archivesType = null;
		IntegerEx archivesInfosSum = null;
		
		try {
			pErrPos = 1;
			if (request.getParameter("state") == null) {
				pFlag = false;
			}else{
			    state = Integer.parseInt(request.getParameter("state"));
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (request.getParameter("archivesTypeID") == null) {
					pFlag = false;
				}else{
					archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
				}
			}

			//得到代工信息，和档案类型对象
			if (pFlag) {
				pErrPos = 3;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				userIds = ((UserInfo)request.getSession(false).getAttribute("userInfo")).getChargeUserIDs();
			}
			
			//调用业务逻辑获取档案信息
			if (pFlag) {
				pErrPos = 4;
				archivesInfos = new ArrayList<ArchivesInfo>();
				dataPageInfo.setPageSize(15);
				
				if (paperTransferManageService.findArchivesInfosByEnumWorkFlowStatus(userIds, archivesType, EnumWorkFlowStatus.getEnumElement(state), dataPageInfo,"UserID1", archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"查找档案信息失败");
				}
			}
			
			//调用业务逻辑得到当前批次中档案总数量
			if (pFlag) {
				pErrPos = 5;
				if (EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.业务指导室著录审核通过 || EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.业务指导室接收审核打回 ) {
					//统计当前移交批次中的档案信息数量
					archivesInfosSum = new IntegerEx();
					if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,false, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"统计当前移交批次中的档案信息数量失败");
					}else{
						request.setAttribute("sum", archivesInfosSum.getValue());
					}
				}
			}
			
			if(pFlag){
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
				request.setAttribute("archivesInfos", archivesInfos);
				if (EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.业务指导室著录审核通过) {
					resultURL = "/YJJSGL/x_DAYJ_SHTG.jsp";
				}else if(EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.业务指导室著录审核打回){
					resultURL = "/YJJSGL/x_DAYJ_SHWTG.jsp";
				}else if(EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.业务指导室接收审核打回){
					resultURL = "/YJJSGL/x_DAYJ_YJWTG.jsp";
				}
				request.setAttribute("archivesTypeID", archivesTypeID);
				request.setAttribute("state", state);
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				
				//记录日志
				logger.error(pErrInfo.toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案形成部门档案加入移交清单<br/>
	 * 适用于形成部门的档案移交
	 */
	public String addArchivesToTransferList() throws Exception{
		System.out.println("-------------加入移交清单--------------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		
		System.out.println("archivesTypeID:"+request.getParameter("archivesTypeID"));
		
		List<ArchivesInfo> archivesInfos = null;
		ArchivesType archivesType = null;
		try {
			//检查档案类型
			pErrPos = 1;
			UserInfo userInfo = ((UserInfo)session.getAttribute("userInfo"));
			String deptType = request.getParameter("deptType");
			
            if (request.getParameter("archivesTypeID") == null) {
				pFlag = false;
				pErrInfo.getContent().append("得到档案类型ID失败");
			}else if("".equals(request.getParameter("archivesTypeID"))){
				pFlag = false;
				pErrInfo.getContent().append("得到档案类型ID失败");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeID")));
			}
            
            //检查传入的档案内部序号数组
            if (pFlag) {
				if (NBXHS == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要加入移交清单的档案信息");
				}else if(NBXHS.length <=0){
					pFlag = false;
					pErrInfo.getContent().append("请选择要加入移交清单的档案信息");
				}else{
					ArchivesInfo archivesInfo = null;
					archivesInfos = new ArrayList<ArchivesInfo>();
					for (int NBXH : NBXHS) {
						archivesInfo = new ArchivesInfo(archivesType);
						archivesInfo.setNBXH(NBXH);
						archivesInfos.add(archivesInfo);
					}
				}
			}
            
            //调用业务逻辑加入移交清单
			if (pFlag) {
				if("XCBM".equals(deptType)){
					if (paperTransferManageService.addToPaperTransferBatchForOutside(userInfo, archivesType, archivesInfos,false, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "加入到移交清单失败");
					}
				}else if("YWZDS".equals(deptType)){
					if (paperTransferManageService.addToPaperTransferBatchForOutside(userInfo, archivesType, archivesInfos,true, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "加入到移交清单失败");
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
				
				if(pErrInfo.getException() != null){
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 从档案清单中移除批次档案详细  url:"YJGL/YJJSAction_removeFromQDList.action"
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String removeFromQDList() throws Exception {
		System.out.println("----------从清单中移除------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("NBXH="+request.getParameter("NBXH")+"&archivesTypeID="+request.getParameter("archivesTypeID")+"&transferBatNo="+request.getParameter("transferBatNo"));
		
		ArchivesType archivesType = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			int NBXH = Integer.parseInt(request.getParameter("NBXH"));
			String transferBatNo = request.getParameter("transferBatNo");
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			int userID = ((UserInfo)request.getSession(false).getAttribute("userInfo")).getUserID();
			String deptType = this.getRequest().getParameter("deptType");
			
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			
			EnumWorkFlowStatus enumWorkFlowStatus = null;
			if ("YWZDS".equals(deptType)) {
				enumWorkFlowStatus = EnumWorkFlowStatus.著录完成;
			}else if("XCBM".equals(deptType)){
				enumWorkFlowStatus = EnumWorkFlowStatus.形成部门将档案从清单中移除;
			}
			
			//开始处理2...
			if (pFlag) {
				if(paperTransferManageService.removeArchiveSInfoFromQDList(NBXH,archivesType,transferBatNo,userID,enumWorkFlowStatus,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"移除档案信息失败");
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
				
				if(pErrInfo.getException() != null){
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 确认移交批次
	 * @return
	 * @throws Exception
	 */
	public String confirmTransferPaper() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletResponse response = ServletActionContext.getResponse();
		HttpServletRequest request = ServletActionContext.getRequest();
		response.setCharacterEncoding("utf-8");
		
		try {
			//开始处理 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
			String deptType = request.getParameter("deptType");
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if ("XCBM".equals(deptType)) {
					if (paperTransferManageService.confirmTransferPaperForOutside(paperTransferBatch.getBatNo(), userInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "确认移交失败");
						System.out.println(pErrInfo.toString());
					}
				}else if ("YWZDS".equals(deptType)) {
					if (paperTransferManageService.confirmTransferPaperForInside(paperTransferBatch.getBatNo(), userInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "确认移交失败");
						System.out.println(pErrInfo.toString());
					}
				}	
			}
			if(pFlag){
				print("移交完成！");
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
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 业务指导室得到移交部门树，并指定处理树事件地址<br/>
	 * 适用于业务指导室接受登记
	 * @return 各形成部门tree
	 * @throws Exception
	 */
	public String getDeptTreeForYWZDSJSDJ() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
		
		Map<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos = userInfo.getChargeDepartmentInfos();
		
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>(userChargeDepartmentInfos.values());
		
		String tree = CreateTreeUtil.getDeptTree(departmentInfos);
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","YJJSAction_findTransferOverBatsOutside.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	/**
	 * 业务指导室查找形成部门移交过来的批次<br/>
	 * 适用于业务指导室接受登记
	 * @return
	 * @throws Exception
	 */
	public String findTransferOverBatsOutside() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = "";
		int deptId = 0;
		try {
			deptId = Integer.parseInt(request.getParameter("nodeId"));
			
			System.out.println("------------------业务指导室查找形成部门的移交批次--------------------------------------");
			System.out.println("nodeId: "+deptId);
			
			//调用业务逻辑
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForOutside(deptId,EnumPaperTransferBatchesDealStatus.确认移交,paperTransferBatches,false, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"查找移交批次信息失败");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 业务指导室得到部门树，并指定处理树事件地址<br/>
	 * 适用于业务指导室查看接收审核
	 * @return 各形成部门tree
	 * @throws Exception
	 */
	public String getDeptTreeForYWZDSJSSH() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
		
		UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
		
		Map<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos = userInfo.getChargeDepartmentInfos();
		
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>(userChargeDepartmentInfos.values());
		
		String tree = CreateTreeUtil.getDeptTree(departmentInfos);
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","YJJSAction_findRegisteredBatsOutside.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	/**
	 * 业务指导室根据部门查找接收登记完成的移交批次<br/>
	 * 适用于业务指导室接收审核
	 * @return
	 * @throws Exception
	 */
	public String findRegisteredBatsOutside() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		List<PaperTransferBatch> paperTransferBatches = null;
		try {
			pErrPos = 1;
			int deptID = Integer.parseInt(request.getParameter("nodeId"));

			if (pFlag) {
				pErrPos = 2;
				paperTransferBatches = new ArrayList<PaperTransferBatch>();
				//调用业务逻辑
				if(paperTransferManageService.findPaperTransferBatchesForOutside(deptID, EnumPaperTransferBatchesDealStatus.接收登记完成, paperTransferBatches,false, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "");
				}
			}
			request.setAttribute("paperTransferBatches", paperTransferBatches);
			request.setAttribute("deptType", "YWZDS");
			resultURL = "/YJJSGL/JSSH.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;	
	}
	
	/**
	 * 业务指导室得到移交部门树，并指定处理树事件地址<br/>
	 * 适用于业务指导室档案移交 
	 * @return 各形成部门tree
	 * @throws Exception
	 */
	public String getDeptTreeForYWZDSDAYJ() throws Exception{
		 HttpServletRequest request = ServletActionContext.getRequest();
		
		UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
		
		Map<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos = userInfo.getChargeDepartmentInfos();
		
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>(userChargeDepartmentInfos.values());
		
		String tree = CreateTreeUtil.getDeptTree(departmentInfos);
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","YJJSAction_findExamineOverBatByDept.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	/**
	 * 业务指导室 根据部门查找审核通过待移交的批次<br>
	 * 适用于业务指导室的档案移交
	 * @return 
	 * @throws Exception
	 */
	public String findExamineOverBatByDept() throws Exception{
		String message = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();

		IntegerEx archivesInfosSum = null;
		UserInfo userInfo = (UserInfo)request.getSession(false).getAttribute("userInfo");
		//得到部门ID
		int deptID = Integer.parseInt(request.getParameter("nodeId"));
		System.out.println("部门ID： "+deptID);
		
		List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
		
		//调用业务逻辑 查找审核完成的批次信息
		if (pFlag) {
			if (paperTransferManageService.findPaperTransferBatchesForOutside(deptID, EnumPaperTransferBatchesDealStatus.接收审核完成, paperTransferBatches,false, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "");
				System.out.println(pErrInfo.toString());
			}
		}
		
		//统计当前批次档案总数量
		if(pFlag){
			archivesInfosSum = new IntegerEx();
			if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(), archivesInfosSum,true, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "统计数量失败");
				System.out.println(pErrInfo.toString());
			}
		}
		request.setAttribute("sum", archivesInfosSum.getValue());
		request.setAttribute("message", message);
		request.setAttribute("paperTransferBatches", paperTransferBatches);
		resultURL = "/YJJSGL/y_DAYJ.jsp";
		
		if (pErrInfo.getException() != null) {
			request.setAttribute("pErrInfo", pErrInfo);
			resultURL = "/error.jsp";
		} else {
			request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
		}
		logger.error(pErrInfo.getContent());
		return SUCCESS;
	}
	
	/**
	 * 业务指导室加入移交清单
	 * @return 
	 * @throws Exception
	 */
	public String insertBatIntoNew() throws Exception{
		System.out.println("--------业务指导室加入移交清单---------------------------------");
		String message = "";
		boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(false);
		
		//IntegerEx archivesInfosSum = null; 
		
		if(batNos == null){
			pFlag = false;
			message = "请选择要移交的批次！";
		}else{
			pFlag = true;
		}
		
		//取出userID
		UserInfo userInfo = ((UserInfo)session.getAttribute("userInfo"));

		if (pFlag) {	
			//调用业务逻辑
			if(paperTransferManageService.addToPaperTransferBatchForIntside(userInfo,  batNos, pErrInfo) == false){
				message = "加入清单失败！";
				pFlag = false;
				System.out.println(pErrInfo.toString());
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 根据批次号查找批次详细信息
	 * 接受登记
	 * @return
	 * @throws Exception
	 */
	public String findByBatNO() throws Exception {
		System.out.println("-------------------------------------------------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		//String message = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		String batNo = "";
		try {
			//开始处理 1...
			pErrPos = 1;
			if (request.getParameter("batNo") == null || request.getParameter("batNo").equals("")) {
				batNo = null;
				pFlag = false;
				pErrInfo.getContent().append("请输入要登记的批次号！");
			} else{
				batNo = request.getParameter("batNo");
			}
			
			if(pFlag){
				if(paperTransferManageService.findPaperTransferBatchByBatchNo(batNo, paperTransferBatch, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"按批次号查询批次失败");
				}
			}
			request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
			request.setAttribute("paperTransferBatch",paperTransferBatch);
			request.setAttribute("message", pErrInfo.toShortString());
			request.setAttribute("batNo", batNo);
			resultURL = "/YJJSGL/dlg_JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 业务指导室得到移交部门树，并指定处理树事件地址<br/>
	 * 适用于业务指导室查看接收记录
	 * @return 各形成部门tree
	 * @throws Exception
	 */
	public String getDeptTreeForYWZDSJSJL() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
		
		UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
		
		Map<Integer,UserChargeDepartmentInfo> userChargeDepartmentInfos = userInfo.getChargeDepartmentInfos();
		
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>(userChargeDepartmentInfos.values());
		
		String tree = CreateTreeUtil.getDeptTree(departmentInfos);
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","YJJSAction_findReceivedOutside.action");
		resultURL = "/left.jsp";

		return SUCCESS;
	}
	
	/**
	 * 业务指导室查找接收审核完成且已移交的批次<br/>
	 * 适用于业务指导室对形成部门的管理 功能业务指导室查看接收记录
	 * @return 
	 * @throws Exception
	 */
	public String findReceivedOutside() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		List<PaperTransferBatch> paperTransferBatches  = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			//得到部门ID
			int departmentID = Integer.parseInt(request.getParameter("nodeId"));
			
			//设置页号和每页显示记录数
			dataPageInfo.setPageSize(20);
			
			paperTransferBatches = new ArrayList<PaperTransferBatch>();
			//调用业务逻辑
			if(paperTransferManageService.findPaperTransferBatchesForOutside(departmentID, EnumPaperTransferBatchesDealStatus.接收登记完成, paperTransferBatchesQueryCondition, dataPageInfo,paperTransferBatches, pErrInfo) == false){
				pFlag = false;
				pErrInfo.getContent().insert(0, "查找接收记录失败");
				System.out.println(pErrInfo.toString());
			};
			
			request.setAttribute("paperTransferBatches", paperTransferBatches);
			request.setAttribute("deptId", departmentID);
			request.setAttribute("paperTransferBatches", paperTransferBatches);

			resultURL = "/YJJSGL/CKJSJL.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 查看当前移交清单    当前用户当前的移交清单    
	 * @return
	 * @throws Exception
	 */
	public String findCurrentQD() throws Exception{
		System.out.println("-----查找当前移交清单-------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = this.getRequest();
		System.out.println("type: "+request.getParameter("type"));
		
		String deptType = "";
		String stateType = "";
		String type = "";
		
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		UserInfo userInfo = null;
		try {
			//检查参数
			pErrPos = 1;
			
			userInfo  = (UserInfo)request.getSession(false).getAttribute("userInfo");
			deptType = request.getParameter("deptType");
			stateType = request.getParameter("stateType");
			type = request.getParameter("type");

			boolean insideFlag = false;
			
			if("YWZDS".equals(deptType)){
				insideFlag = true;
			}
			
			//调用业务逻辑得到档案分类信息
			if (pFlag) {
				//需传入参数 移交清单内档案类型
				paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				//调用业务逻辑方法取得指定批次分类信息
				if (paperTransferManageService.findCurrentPaperTransferBatchesArchvTypeDetails(userInfo.getUserID(),paperTransferBatch, paperTransferBatchesArchvTypeDetails,insideFlag, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "取得指定批次分类信息失败");
					System.out.println(pErrInfo.getContent());
				}
			}
			
			if (pFlag) {
				request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
				request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
			}
			
			request.setAttribute("type", type);
			request.setAttribute("deptType", deptType);
			request.setAttribute("stateType", stateType);
			request.setAttribute("batNo", paperTransferBatch.getBatNo());
			resultURL = "/YJJSGL/dlg_YJQD.jsp";
			
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;	
	}
		
	/**
	 * 查找指定批次中指定档案分类的档案信息
	 * @return 指定批次中指定分类的档案信息列表
	 */
	public String findArchivesInfosByArchivesTypeAndBatNo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();//接收错误信息

		HttpServletRequest request = ServletActionContext.getRequest();
		ArchivesType archivesType = new ArchivesType();
		try {
			//开始处理 1...
			pErrPos = 1;
			archivesType.setID(Integer.parseInt(request.getParameter("archivesTypeID")));
			String batNo = request.getParameter("batNo");
			//String deptType = request.getParameter("deptType");

			List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
			if("1".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.尚未审核);
			}else if("2".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.尚未审核);
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("3".equals(request.getParameter("stateType"))){//业务指导室查看当前清单
				enumCheckResults.add(EnumCheckResult.尚未审核);
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
			}else if("4".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("5".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}else if("6".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
			}
			
			//接收返回档案信息列表
			List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
			
			//调用业务逻辑
			if (pFlag) {
				if(paperTransferManageService.findPaperTransferBatchesDetails(batNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找失败");
					System.out.println(pErrInfo.toString());
				}
     		}
			
			resultURL = "/YJJSGL/dlg_YJQD_right.jsp";
			request.setAttribute("deptType", request.getParameter("deptType"));
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("stateType", request.getParameter("stateType"));
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 接收登记，验证批次各个分类档案数量
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String modifyBatches() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = null;
		String batNo  = "";
		try {
			pErrPos = 1;
			paperTransferBatchesArchvTypeDetails = new ArrayList<PaperTransferBatchesArchvTypeDetail>();
			batNo = request.getParameter("batNo");
			UserInfo userInfo = (UserInfo)request.getSession(false).getAttribute("userInfo"); 
			
			if (pFlag) {
				pErrPos = 2;
				//获得页面表单参数并构造移交批次分类信息对象集合
				PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail = null;
				Map parameterMap = request.getParameterMap();
				for (Iterator iterator = parameterMap.keySet().iterator(); iterator.hasNext();) {
					String key = (String)iterator.next();
					
					if(!"batNo".equals(key)){
						paperTransferBatchesArchvTypeDetail = new PaperTransferBatchesArchvTypeDetail();
						String value =  ((String[]) parameterMap.get(key))[0];
						
						paperTransferBatchesArchvTypeDetail.setTransferBatNo(batNo);
						paperTransferBatchesArchvTypeDetail.setArchivesTypeID(Integer.parseInt(key));
						try{
							paperTransferBatchesArchvTypeDetail.setTransferTotal(Integer.parseInt(value));
						}catch (Exception e) {
							pFlag = false;
							pErrInfo.getContent().append("输入"+key+"的档案数量不合法");
						}		
						paperTransferBatchesArchvTypeDetails.add(paperTransferBatchesArchvTypeDetail);
					}	
				}
			}

			//调用业务逻辑处理
			if (pFlag) {
				pErrPos = 3;
				if (paperTransferManageService.registerPaperReceive(userInfo,batNo,paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "登记失败");
				}
			}
			
			if (pFlag) {
				response.getWriter().print("登记完成！");
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
				
				if (pErrInfo.getException() != null) {
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
			}

			//销毁局部变量
			throwable = null;
		}
		return null;		
	}
	
	/**
	 * 查找当前要审核的批次中该档案的分类中的档案信息<br/>
	 * 适合接收审核
	 * @return 返回档案列表
	 * @throws Exception
	 */
	public String findArchivesInfosByArchivesTypeForSH() throws Exception{
        boolean pFlag = true;
    	ErrInfo pErrInfo = new ErrInfo();//接收错误信息
    	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//接收页面传入档案类型
		ArchivesType archivesType = new ArchivesType();
		archivesType.setID(Integer.parseInt(request.getParameter("archivesTypeID")));
		
		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		
		//接收返回档案信息列表
		List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
	
		List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
		if("YWZDS".equals(deptType)){
			enumCheckResults.add(EnumCheckResult.尚未审核);
			enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
		}else if("DAGLS".equals(deptType)){
			enumCheckResults.add(EnumCheckResult.业务指导室审核通过);
			//enumCheckResults.add(EnumCheckResult.档案管理室审核通过);
		}
		
		//调用业务逻辑
		pFlag = paperTransferManageService.findPaperTransferBatchesDetails(batNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo);
		
		if (pFlag) {
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
		} else {
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
			//处理。。。。。。。。。。。。。。。。。。
		}
        resultURL = "/YJJSGL/dlg_JSSH_right.jsp";
        request.setAttribute("archivesTypeID",archivesType.getID());
        request.setAttribute("batNo", batNo);
        request.setAttribute("deptType", deptType);
        
        if (pErrInfo.getException() != null) {
			request.setAttribute("pErrInfo", pErrInfo);
			resultURL = "/error.jsp";
		} else {
			request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
		}
		logger.error(pErrInfo.getContent());
		
		return SUCCESS;
	}
	
	/**
	 * 查看移交记录<br/>
	 * 档案形成部门和业务指导室可通用该功能
	 * @return
	 * @throws Exception
	 */
	public String findTransferOverBatches() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		//得到request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		List<PaperTransferBatch> paperTransferBatches = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			String deptType = request.getParameter("deptType");
			//设置页号和每页显示记录数
			dataPageInfo.setPageSize(20);

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				// 调用业务逻辑得到rowCount值和列表
				paperTransferBatches = new ArrayList<PaperTransferBatch>();

				if ("XCBM".equals(deptType)) {
					if(paperTransferManageService.findPaperTransferBatchesForOutside(userInfo.getDepartmentID(), 
							EnumPaperTransferBatchesDealStatus.确认移交,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							paperTransferBatches,
							pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"查找移交批次信息失败");
						System.out.println(pErrInfo.toString());
					}
				}else if ("YWZDS".equals(deptType)) {
					List<PaperTransferBatch> pPaperTransferBatches = new ArrayList<PaperTransferBatch>();
					//查找改用户馆内移交批次
					if(paperTransferManageService.findPaperTransferBatchesForInside(userInfo.getUserID(), 
							EnumPaperTransferBatchesDealStatus.确认移交,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							pPaperTransferBatches,
							true,pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"查找移交批次信息失败");
						System.out.println(pErrInfo.toString());
					}
					paperTransferBatches.addAll(pPaperTransferBatches);
					//查找改用户馆外移交批次
					if(paperTransferManageService.findPaperTransferBatchesForInside(userInfo.getUserID(), 
							EnumPaperTransferBatchesDealStatus.确认移交,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							pPaperTransferBatches,
							false,pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"查找移交批次信息失败");
						System.out.println(pErrInfo.toString());
					}
					
					//组合
					for (int i = 0; i < pPaperTransferBatches.size(); i++) {
						if(!paperTransferBatches.contains(pPaperTransferBatches.get(i))){
							paperTransferBatches.add(pPaperTransferBatches.get(i));
						}
					}
					
				}
			}
			
			request.setAttribute("deptType", deptType);
			request.setAttribute("paperTransferBatches", paperTransferBatches);
			resultURL = "/YJJSGL/CKYJJL.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 得到业务指导室归档验收树
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String getArchivesTypeTreeForYWZDSGDYS() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = null;
		Map<Integer, ArchivesTypeEx> archivesTypeExs = null;
		
		try {
			pErrPos = 1;
			userInfo = (UserInfo) request.getSession(false).getAttribute("userInfo");

			//得到所有档案分类对象的子节点构造archivesTypeExs集合
			archivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
			if(userInfo.getArchivesTypes() != null && userInfo.getArchivesTypes().size()>0){

				CommonUtil.getChildPlaneArchivesTypeExs(userInfo.getArchivesTypes(), archivesTypeExs, pErrInfo);
				
			}
			
			//调用业务逻辑取得个分类下的三个属性
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferManageService.statPaperCheckBackCount(userInfo.getChargeUserIDs(),"UserID2",archivesTypeExs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "得到档案分类树失败");
				}
			}
			
			//创建树
			if (pFlag) {
				pErrPos = 3;
				
				LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
				
				//将平面的转化为树状
				CommonUtil.convertPlaneArchivesTypeExsToTree(archivesTypeExs, treeArchivesTypeExs, pErrInfo);
				
				//构建树
				String tree = CreateTreeUtil.getArchivesTypeTreeWithPropertyCheckBack(new ArrayList<ArchivesTypeEx>(treeArchivesTypeExs.values()));
				request.setAttribute("proceseAction","YJJSAction_findArchivesForYWZDSGDYS.action");
				request.setAttribute("tree",tree);
				resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
					request.setAttribute("message", pErrInfo.toShortString());
				}
				
				//记录日志
				logger.error(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 业务指导室查询归档验收结果
	 * @return
	 * @throws Exception
	 */
	public String findArchivesForYWZDSGDYS() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		IntegerEx archivesInfosSum = null;
		List<ArchivesInfo> archivesInfos = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			String archivesTypeID = request.getParameter("archivesTypeID");
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(archivesTypeID));
			UserInfo userInfo = (UserInfo)request.getSession(false).getAttribute("userInfo");
			int [] userIds = {userInfo.getUserID()};

			//开始处理2...
			if (pFlag) {
				dataPageInfo.setPageSize(20);
				archivesInfos = new ArrayList<ArchivesInfo>();
				
				//接收审核未通过
				if(paperTransferManageService.findArchivesInfosByEnumWorkFlowStatus(userIds, archivesType, EnumWorkFlowStatus.档案管理室接收审核打回, dataPageInfo,"UserID2", archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询档案信息失败");
					System.out.println(pErrInfo.toString());
				}
			}
			
			//调用业务逻辑得到当前批次中档案总数量
			if (pFlag) {
				pErrPos = 5;
			
				//统计当前移交批次中的档案信息数量
				archivesInfosSum = new IntegerEx();
				if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,true, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"统计当前移交批次中的档案信息数量失败");
				}else{
					request.setAttribute("sum", archivesInfosSum.getValue());
				}
			}
			

			request.setAttribute("archivesTypeID", archivesTypeID);
			request.setAttribute("dataItems", archivesType.getDataItemsForListDisplay());
		    request.setAttribute("archivesInfos", archivesInfos);
			resultURL = "/YJJSGL/GDYSJG.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案管理室得到业务指导室用户树<br>
	 * 档案管理是接收登记
	 * @return
	 * @throws Exception
	 */
	public String getUserTreeForDAGLSJSDJ() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.业务指导专员角色,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找用户信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findTransferOverBatsInside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案管理室按用户查找还未登记的移交批次
	 * @return
	 * @throws Exception
	 */
	public String findTransferOverBatsInside() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = "";
		int userID = 0;
		try {
			userID = Integer.parseInt(request.getParameter("nodeId"));
			
			System.out.println("------------------业务指导室查找形成部门的移交批次--------------------------------------");
			System.out.println("nodeId: "+userID);
			
			//调用业务逻辑
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForInside(userID,EnumPaperTransferBatchesDealStatus.确认移交,paperTransferBatches,true, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"查找移交批次信息失败");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		//return "toDJ";
		return SUCCESS;
	}

	/**
	 * 档案管理室得到业务指导室用户树<br>
	 * 档案管理是接收登记
	 * @return
	 * @throws Exception
	 */
	public String getUserTreeForDAGLSJSSH() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.业务指导专员角色,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找用户信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findRegisteredBatsIntside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案管理室按用户查找登记完成的移交批次<br>
	 * 档案管理室接收审核
	 * @return
	 * @throws Exception
	 */
	public String findRegisteredBatsIntside() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String message = "";
		int userID = 0;
		try {
			userID = Integer.parseInt(request.getParameter("nodeId"));
			
			System.out.println("------------------业务指导室查找形成部门的移交批次--------------------------------------");
			System.out.println("nodeId: "+userID);
			
			//调用业务逻辑
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForInside(userID,EnumPaperTransferBatchesDealStatus.接收登记完成,paperTransferBatches,true, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"查找移交批次信息失败");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
			request.setAttribute("deptType", "DAGLS");
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSSH.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		//return "toDJ";
		return SUCCESS;
	}
	
	/**
	 * 查找指定批次内的档案分类信息 <br/>适用接受审核
	 * @return 返回批次内档案类别列表
	 * @throws Exception
	 */
	public String findArchivesTypesByBatNo() throws Exception{
		System.out.println("------查找登记完成的批次信息详细-------------------------------");
		//String message = "";
		boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();

		//接收type 用于页面区别是查看还是移交
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		
		//需传入参数
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//调用业务逻辑方法取得指定批次分类信息
		paperTransferBatch.setBatNo(batNo);
		if( paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			pFlag = false;
			pErrInfo.getContent().insert(0, "查找批次分类详细信息失败");
			System.out.println(pErrInfo.toString());
		}
		//将分类信息和默认分类中的档案信息放入request中
		if(pFlag){
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		}else{
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
			//处理。。。。。。
		}
		request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
		request.setAttribute("batNo", batNo);
		request.setAttribute("deptType", deptType);
		resultURL = "/YJJSGL/dlg_JSSH.jsp";
		
		if (pErrInfo.getException() != null) {
			request.setAttribute("pErrInfo", pErrInfo);
			resultURL = "/error.jsp";
		} else {
			request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
		}
		logger.error(pErrInfo.getContent());
		return SUCCESS;
	}
	
	/**
	 * 查找指定批次内的档案分类信息 <br/>适用接受审核
	 * @return 返回批次内档案类别列表
	 * @throws Exception
	 */
	public String findQDByBatNo() throws Exception{
		System.out.println("------查找登记完成的批次信息详细-------------------------------");
		//String message = "";
		boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();

		//接收type 用于页面区别是查看还是移交
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		String stateType = request.getParameter("stateType");
		String type = request.getParameter("type");
		
		//需传入参数
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//调用业务逻辑方法取得指定批次分类信息
		paperTransferBatch.setBatNo(batNo);
		if( paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			pFlag = false;
			pErrInfo.getContent().insert(0, "查找批次分类详细信息失败");
		}
		//将分类信息和默认分类中的档案信息放入request中
		if(pFlag){
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		}else{
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
			//处理。。。。。。
		}
		request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
		request.setAttribute("batNo", batNo);
		request.setAttribute("type", type);
		request.setAttribute("deptType", deptType);
		request.setAttribute("stateType", stateType);
		resultURL = "/YJJSGL/dlg_YJQD.jsp";
		
		if (pErrInfo.getException() != null) {
			request.setAttribute("pErrInfo", pErrInfo);
			resultURL = "/error.jsp";
		} else {
			request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
		}
		logger.error(pErrInfo.getContent());
		return SUCCESS;
	}
	
	/**
	 * 档案管理室得到业务指导室用户树<br>
	 * 档案管理是接收登记
	 * @return
	 * @throws Exception
	 */
	public String getUserTreeForDAGLSJSML() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.业务指导专员角色,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找用户信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findReceivedInside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 档案管理室查找接收审核完成的批次<br/>
	 * 档案管理是查看接收记录
	 * @return 
	 * @throws Exception
	 */
	public String findReceivedInside() throws Exception{
		ErrInfo pErrInfo = new ErrInfo();
		
		System.out.println(paperTransferBatchesQueryCondition.getTransferDateBegin());
		System.out.println(paperTransferBatchesQueryCondition.getTransferDateEnd());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//得到部门ID
		int userID = Integer.parseInt(request.getParameter("nodeId"));
		System.out.println(userID);
		
		//设置页号和每页显示记录数
		dataPageInfo.setPageSize(20);
		
		List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
		
		//调用业务逻辑
		if(paperTransferManageService.findPaperTransferBatchesForInside(userID, EnumPaperTransferBatchesDealStatus.接收审核完成, paperTransferBatchesQueryCondition, dataPageInfo,paperTransferBatches,true, pErrInfo) == false){
			//pFlag = false;
			pErrInfo.getContent().insert(0, "查找接收记录失败");
			System.out.println(pErrInfo.toString());
		};
		
		request.setAttribute("paperTransferBatches", paperTransferBatches);
		request.setAttribute("userID", userID);
		request.setAttribute("paperTransferBatches", paperTransferBatches);

		resultURL = "/YJJSGL/CKJSJL.jsp";
		
		if (pErrInfo.getException() != null) {
			request.setAttribute("pErrInfo", pErrInfo);
			resultURL = "/error.jsp";
		} else {
			request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
		}
		logger.error(pErrInfo.getContent());
		return SUCCESS;
	}
	
	
	
	
	
	
	
	
	
	
	/**
	 * 生成档号
	 * @return
	 * @throws Exception
	 */
	public String generateArchivesNO() throws Exception{
		System.out.println("---------生成档号-----------------");
		
		String message = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("batNo: "+ request.getParameter("batNo"));
		System.out.println("archivesTypeID: "+ request.getParameter("archivesTypeID"));
		
		String batNo = request.getParameter("batNo");
		
		//接收传入的档案类型
		ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeID").toString()));
		
		//调用业务逻辑
		if (paperTransferManageService.generateArchivesID(batNo, archivesType, pErrInfo) == false) {
			pFlag = false;
			pErrInfo.getContent().insert(0, "生成档号失败！");
			System.out.println(pErrInfo.toString());
		}
		
		if(pFlag){
			message = "生成档号成功！";
		}else{
			message = pErrInfo.toShortString();
			//处理
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * 按照内部序号和档案类型查找档案信息
	 * @return 返回一卷档案信息
	 * @throws Exception
	 */
	public String findArchives() throws Exception{
	//	String message = "";
		//boolean pFlag = false;
		
		return "";
	}
	
	/**
	 * 根据盒条码得到盒标签的档号起止范围
	 * @return
	 * @throws Exception
	 */
	public String getArchivesBoxLabel() throws Exception{
		//String messasge = "";
		//boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String archivesBoxBarcode = request.getParameter("archivesBoxBarcode");
		
		//System.out.println(archivesBoxBarcode);
		
		ArchivesBoxLabel archivesBoxLabel = new ArchivesBoxLabel(); 
		
		if(paperTransferManageService.findArchivesBoxLabel(archivesBoxBarcode, archivesBoxLabel, pErrInfo) == false){
			System.out.println(pErrInfo.toShortString());
		}
		
		JSONObject jsonObject = JSONObject.fromObject(archivesBoxLabel);
		
		jsonResult=jsonObject.toString();
		
		System.out.println(jsonResult);
		return SUCCESS;
	}
	
	/**
	 * 档案装盒
	 * @return
	 * @throws Exception
	 */
	public String archivesBoxing() throws Exception{
		//archivesBoxing(List<ArchivesInfo> archivesInfos,String archivesBoxBarcode,ErrInfo pErrInfo);
		return SUCCESS;
	}
    
	/**
	 * 得到业务指导室的用户tree,并指定事件的处理Action<br/>
	 * 适用功能关联档案条码
	 * @return
	 * @throws Exception
	 */
	public String getYWZDSUserTreeForGLTM() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//开始处理 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.业务指导专员角色,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找用户信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findRegisterOverBatsByInside.action");
			resultURL = "/left.jsp";
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
		return SUCCESS;
	}
	
	/**
	 * 档案管理室查找接收审核完成的档案信息<br/>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findRegisterOverBatsByInside() throws Exception{
		String message = "";
		ErrInfo pErrInfo = new ErrInfo();
		boolean pFlag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		
		int userId = Integer.parseInt(request.getParameter("nodeId"));
		System.out.println(userId);
		
		dataPageInfo.setPageSize(20);
		
		List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
		
		pFlag = paperTransferManageService.findPaperTransferBatchesForInside(userId, EnumPaperTransferBatchesDealStatus.接收审核完成, paperTransferBatchesQueryCondition, dataPageInfo, paperTransferBatches,true, pErrInfo);
		
		if(pFlag){
			request.setAttribute("paperTransferBatches", paperTransferBatches);
		}else{
			message = "异常！";
		}
		request.setAttribute("message", message);
		resultURL = "/YJJSGL/GLDATM.jsp";
		return SUCCESS;
	}
	
	/**
	 * 查找指定批次内的档案分类信息 <br/>
	 * 粘贴档案条码
	 * @return 返回批次内档案类别列表
	 * @throws Exception
	 */
	public String findArchivesTypesByBatNoForGL() throws Exception{
		//String message = "";
		//boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();

		//接收type 用于页面区别是查看还是移交
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		paperTransferBatch.setBatNo(batNo);
		//需传入参数
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//调用业务逻辑方法取得指定批次分类信息
		if(paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			//pFlag = false;
			System.out.println(pErrInfo.toString());
		}
		//将分类信息和默认分类中的档案信息放入request中
	
		request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		
		request.setAttribute("batNo", batNo);
		request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
		resultURL = "/YJJSGL/dlg_GLDATM.jsp";
		return SUCCESS;
	}
	
	/**
	 * 档案与条码关联
	 * @return
	 * @throws Exception
	 */
	public String pasteArchivesBarcode() throws Exception{
		// boolean pFlag = false; 
		 ErrInfo pErrInfo = new ErrInfo();
		 HttpServletRequest request = ServletActionContext.getRequest();
		 
		// String batNo = request.getParameter("batNo");
		 String archivesID  = request.getParameter("archivesID");
		 int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
		 String barcode = request.getParameter("barcode");
		 
		 ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
		 
		 ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
		 
		 if(paperTransferManageService.pasteArchivesBarcode(archivesID,archivesType,barcode,archivesInfo,pErrInfo) == false){
			 System.out.println(pErrInfo.toString());
		 }
		
		 JSONObject jsonObject = JSONObject.fromObject(archivesInfo);
		 jsonResult =jsonObject.toString();

		 return SUCCESS;
	}
	
	
	
	
	
	/**
	 * 档案装盒，适合将一份档案装入盒中
	 * @return
	 * @throws Exception
	 */
	public String putArchiveToBox() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		StoreroomArchivesInfo storeroomArchivesInfo = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			String archivesBarcode = request.getParameter("archivesBarcode");
			String boxBarCode = request.getParameter("archivesBoxBarcode");
			
			//开始处理2...
			if (pFlag) {
				List<String> archivesBarCodes = new ArrayList<String>();
				archivesBarCodes.add(archivesBarcode);
				pErrPos = 2;
				if(paperTransferManageService.archivesBoxing(archivesBarCodes, boxBarCode, pErrInfo) == false){
				   System.out.println(pErrInfo.toString());	
				}
			}
			
			//查询档案信息
			if (pFlag) {
				storeroomArchivesInfo = new StoreroomArchivesInfo();
				storeroomArchivesInfo.setArchivesBarcode(archivesBarcode);
				if (storeroomArchivesInfoManageService.findByBarcode(storeroomArchivesInfo, pErrInfo) == false) {
					System.out.println(pErrInfo.toShortString());	
				}
			}
			
			if (pFlag) {
				JSONObject jsonObject = JSONObject.fromObject(storeroomArchivesInfo);
				jsonResult = jsonObject.toString();
				System.out.println(jsonResult);
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
		return SUCCESS;
	}
	
	/**
	 * 档案装盒，适合将多份档案装入盒中
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "static-access" })
	public String putArchivesToBox() throws Exception{
		//String message = "";
		//boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();
		
		JSONObject jsonObject = JSONObject.fromObject(jsonResult);
		JSONArray jsonArray = jsonObject.getJSONArray("archives");
		List<MorphDynaBean> list = jsonArray.toList(jsonArray);
	
		List<String> barcodes = null;
		try{
			barcodes = new ArrayList<String>();
		for (MorphDynaBean morphDynaBean : list) {
			barcodes.add((String) morphDynaBean.get("barcode"));
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String archivesBoxBarcode = jsonObject.getString("boxBarcode");
		
		if(paperTransferManageService.archivesBoxing(barcodes, archivesBoxBarcode,pErrInfo) == false){
		   System.out.println(pErrInfo.toString());	
		}
		
		System.out.println(jsonResult);
		return SUCCESS;
	}
	
	/**
	 * 将档案从盒中移除
	 * @return
	 * @throws Exception
	 */
	public String removeFromBox() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		try {
			//开始处理 1...
			pErrPos = 1;
			String archivesBarcodeStrs [] = request.getParameterValues("archivesBarcodes");

			List<String> archivesBarcodes = new ArrayList<String>();
			for (String archivesBarcode : archivesBarcodeStrs) {
				archivesBarcodes.add(archivesBarcode);
			}
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(storeroomArchivesInfoManageService.updateArchivesBoxBarcode(archivesBarcodes, null, pErrInfo) == false){
					pFlag = false;
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
			}

			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 根据案卷查找卷内文件目录
	 * @return 返回卷内文件目录列表
	 * @throws Exception
	 */
	public String findFilesByArchives() throws Exception{
		//String message = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		//ArchivesType archivesType = new ArchivesType();		
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
//test	
System.out.println(request.getParameter("archivesTypeID"));
System.out.println(request.getParameter("NBXH"));
		try {			
			pErrPos = 1;
			//获取URL传递过来的参数
			int archivesTypeID =Integer.parseInt(request.getParameter("archivesTypeID"));
			//int archivesInfoNBXH = Integer.parseInt(request.getParameter("NBXH"));//档案内部序号
			pErrPos = 2;
			
			/*//查询出该案卷下的卷内文件列表			
			if (pFlag) {			
				if(archivesManageService.findChildArchivesInfosByNBXH(archivesType, archivesInfoNBXH, archivesInfos, pErrInfo)){					
					setArchiveInfos(archivesInfos);//生成模拟数据
					
				}else{
					pFlag = false;
				}
			}*/
			
			pErrPos = 3;
			//将结果返回到页面
			if(pFlag){
				request.setAttribute("archivesInfos", archivesInfos);
				request.setAttribute("recordSize",archivesInfos.size());
				request.setAttribute("archivesTypeID",archivesTypeID);
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
				tempBuilder.append(" , ErrPos: ");
				tempBuilder.append(pErrPos);
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}			
		return "toJN";
	}
	
//////////////DWR      ////////////////////
	/**
	 * 接受审核通过
	 */
	public int paperCheckPassDWR(int archivesTypeID,int NBXH,String batNo,String deptType,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		UserInfo userInfo = null;
		System.out.println("接收审核： "+deptType);
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;

		try {
			pErrPos = 1;

			userInfo = (UserInfo)session.getAttribute("userInfo");
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//得到内部序号的集合构造业务逻辑所需档案信息对象集合
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("得到案卷内部序号失败");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//设置档案信息工作流为著录审核
			if (pFlag) {
				if("YWZDS".equals(deptType)){
					if (paperTransferManageService.paperCheckPass(userInfo.getUserID(), archivesType, archivesInfo, batNo, EnumCheckResult.业务指导室审核通过, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接收审核完成失败");
					}
				}else if("DAGLS".equals(deptType)){
					if (paperTransferManageService.paperCheckPass(userInfo.getUserID(), archivesType, archivesInfo, batNo, EnumCheckResult.档案管理室审核通过, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接收审核完成失败");
					}
				}
				
			}
			
			if (pFlag) {
				return 1;
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
				
				throw new Exception(pErrInfo.toShortString());
			}
			//销毁局部变量
			throwable = null;
		}
		return 1;
	}

	//DWR:审核不通过
	public int paperCheckBackDWR(int archivesTypeID , int NBXH , String backReason,String batNo ,String deptType,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		int success = 0;
		UserInfo userInfo = null;
		
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;
		try {
			pErrPos = 1;
			userInfo = (UserInfo)session.getAttribute("userInfo");
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("得到档案类型失败！");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//得到内部序号的集合构造业务逻辑所需档案信息对象集合
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("得到案卷内部序号失败");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//设置档案信息工作流为著录审核
			if (pFlag) {	
				if("YWZDS".equals(deptType)){
					if (paperTransferManageService.paperCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason,batNo, EnumCheckResult.业务指导室审核打回,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接收审核打回失败");
					}
				}else if("DAGLS".equals(deptType)){
					if (paperTransferManageService.paperCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason,batNo, EnumCheckResult.档案管理室审核打回,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"设置档案工作流为接收审核打回失败");
					}
				}
			}
			
			if (pFlag) {
				success = 1;
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
				
				throw new Exception(pErrInfo.getContent().toString());
			}
			//销毁局部变量
			throwable = null;
		}
		return success;
	}
	
	/**
	 * 查找指定批次指定档案分类下没有条码的的第一条档案信息的档号
	 * @return
	 * @throws Exception
	 */
	public String findTopArchivesByBatNoWhitOutBarcode() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		
		ArchivesInfo archivesInfo = null;
		try {
			//开始处理 1...
			pErrPos = 1;
			String batNo = request.getParameter("batNo");
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			System.out.println("batNo:"+batNo +"   archivesTypeID: "+archivesTypeID);

			//调用业务逻辑得到档号..
			if (pFlag) {
				pErrPos = 2;
				archivesInfo = new ArchivesInfo(archivesType);
				if(paperTransferManageService.findTopArchivesByBatNoWhitOutBarcode(batNo, archivesTypeID, archivesInfo,pErrInfo) == false){
					pFlag = false;
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				JSONObject jsonObject = JSONObject.fromObject(archivesInfo);
				jsonResult = jsonObject.toString();
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
		return SUCCESS;
	}
	
	/**
	 * 根据盒条码找档案信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public String findArchivesInfoByBoxBarcode() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomArchivesInfo> storeroomArchivesInfos = null;
		try {
			//获得参数
			pErrPos = 1;
			String archivesBoxBarcode = request.getParameter("archivesBoxBarcode");

			//调用业务逻辑查找档案信息
			if (pFlag) {
				pErrPos = 2;
				storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
				if (storeroomArchivesInfoManageService.findByBoxBarcode(archivesBoxBarcode,storeroomArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据盒条码查找档案信息失败：");
					System.out.println(pErrInfo.toString());
				}
			}
			
			request.setAttribute("archivesBoxBarcode", archivesBoxBarcode);
			request.setAttribute("storeroomArchivesInfos", storeroomArchivesInfos);
			resultURL = "/YJJSGL/DAZH.jsp";
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
		return SUCCESS;
	}
}
