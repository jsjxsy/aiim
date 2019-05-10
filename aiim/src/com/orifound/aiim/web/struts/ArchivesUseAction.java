package com.orifound.aiim.web.struts;

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IArchivesInfoQueryService;
import com.orifound.aiim.bll.service.IArchivesUseManageService;
import com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService;
import com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService;
import com.orifound.aiim.bll.service.IArchivesUsePurposeManageService;
import com.orifound.aiim.bll.service.IArchivesUseRequestDetailManageService;
import com.orifound.aiim.bll.service.IArchivesUseRequestManageService;
import com.orifound.aiim.bll.service.IArchivesUseWayManageService;
import com.orifound.aiim.bll.service.IAttachedFileManageService;
import com.orifound.aiim.bll.service.IAttachedFileUseRequestPassInfoManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IStoreAddressInfoManageService;
import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.bll.service.IUserInfoManageService;
import com.orifound.aiim.bll.service.IUserRolesInfoManageService;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.ArchivesUseExpiredUserInfo;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUsePurpose;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.ArchivesUseRegisterQueryCondition;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestDetail;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.ArchivesUseWay;
import com.orifound.aiim.entity.AttachedFileUseRequestPassInfo;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumCheckResult;
import com.orifound.aiim.entity.EnumStoreStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.entity.UserRolesInfo;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;
import com.orifound.aiim.web.util.WebCommonUtil;
/**
 * 档案利用控制类(Action)
 * @author Administrator
 *
 */
public class ArchivesUseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	
///////////容器注入的类///////////
	/**
	 * 档案上架位置信息管理服务的接口定义
	 */
	@Autowired
	private IStoreAddressInfoManageService storeAddressInfoManageService;
	
	
	
	/**
	 * 用户所属角色管理服务的接口定义
	 */
	@Autowired
	private IUserRolesInfoManageService userRolesInfoManageService;
	
	/**
	 * 档案利用目的字典表管理服务类
	 */
	@Autowired
	private IArchivesUsePurposeManageService archivesUsePurposeManageService;
	
	/**
	 * 用户信息管理服务
	 */
	@Autowired
	private IUserInfoManageService userInfoManageService; 
	/**
	 * 档案利用方式字典表管理服务类
	 */
	@Autowired
	private IArchivesUseWayManageService archivesUseWayManageService;
	/**
	 * 档案利用人信息管理服务类
	 */
	@Autowired
	private IArchivesUsePersonInfoManageService archivesUsePersonInfoManageService;
	
	/**
	 * 系统配置管理服务类
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * 档案利用管理服务类
	 */
	@Autowired
	private IArchivesUseManageService archivesUseManageService;
	
	/**
	 * 库房档案信息的管理服务类
	 */
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	/**
	 * 实物档案利用出去明细管理服务类
	 */
	@Autowired
	private IArchivesUseOutInfoManageService archivesUseOutInfoManageService;
	
	/**
	 * 档案利用申请单明细业管理服务类
	 */
	@Autowired
	private IArchivesUseRequestDetailManageService archivesUseRequestDetailManageService;
	
	/**
	 * 档案利用申请单信息管理服务类
	 */
	@Autowired
	private IArchivesUseRequestManageService archivesUseRequestManageService;
	
	/**
	 * 档案归档信息的管理服务
	 */
	@Autowired
	private IArchivesInfoQueryService archivesInfoQueryService;
	
	/**
	 * 电子原文管理服务 
	 */
	@Autowired
	private IAttachedFileManageService	attachedFileManageService;

	/**
	 * 电子原文申请通过管理服务
	 */
	@Autowired
	private IAttachedFileUseRequestPassInfoManageService attachedFileUseRequestPassInfoManageService;
	
	/**
	 * 用于返回接收json串
	 */
//	private String jsonResult;
//	
//	public String getJsonResult() {
//		return jsonResult;
//	}
//
//	public void setJsonResult(String jsonResult) {
//		this.jsonResult = jsonResult;
//	}
//	
//	/**
//	 * 加入利用清单传入的内部序号数组
//	 */
//	int [] NBXHS;
//
//	public int[] getNBXHS() {
//		return NBXHS;
//	}
//
//	public void setNBXHS(int[] nbxhs) {
//		NBXHS = nbxhs;
//	}
//	

//	private String userName ;  //利用人姓名
//	private int[] IDs = {};    //申请单编号数组
//	
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//	
//	public int[] getIDs() {
//		return IDs;
//	}
//
//	public void setIDs(int[] iDs) {
//		IDs = iDs;
//	}


	/**
	 * 分页实体类
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * 档案利用人信息实体
	 */
//	ArchivesUsePersonInfo archivesUsePersonInfo = new ArchivesUsePersonInfo();	
	
	/**
	 * 获取档案利用人信息
	 * @return
	 */
//	public ArchivesUsePersonInfo getArchivesUsePersonInfo() {
//		return archivesUsePersonInfo;
//	}
//
//	/**
//	 * 设置档案利用人信息
//	 * @param archivesUsePersonInfo
//	 */
//	public void setArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo) {
//		this.archivesUsePersonInfo = archivesUsePersonInfo;
//	}

	/**
	 * 档案利用申请单查询条件实体类
	 */
	ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
		
	public ArchivesUseRequestQueryCondition getArchivesUseRequestQueryCondition() {
		return archivesUseRequestQueryCondition;
	}

	public void setArchivesUseRequestQueryCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition) {
		this.archivesUseRequestQueryCondition = archivesUseRequestQueryCondition;
	}

	/**
	 * 档案利用登记信息查询条件对象<br>
	 * 用于传输查询条件
	 */
	private ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition = new ArchivesUseRegisterQueryCondition();
	
	public ArchivesUseRegisterQueryCondition getArchivesUseRegisterQueryCondition() {
		return archivesUseRegisterQueryCondition;
	}

	public void setArchivesUseRegisterQueryCondition(
			ArchivesUseRegisterQueryCondition archivesUseRegisterQueryCondition) {
		this.archivesUseRegisterQueryCondition = archivesUseRegisterQueryCondition;
	}
	
	/**
	 * 档案利用信息查询条件对象
	 */
	private ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition = new ArchivesUseInfoQueryCondition();
	
	public ArchivesUseInfoQueryCondition getArchivesUseInfoQueryCondition() {
		return archivesUseInfoQueryCondition;
	}

	public void setArchivesUseInfoQueryCondition(
			ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition) {
		this.archivesUseInfoQueryCondition = archivesUseInfoQueryCondition;
	}
	


	
//----------------   DWR  -------------------
	/**
	 * DWR调用：添加档案利用人信息
	 * @return
	 * @throws Exception
	 */
	public int addArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo ,HttpServletRequest request) throws Exception{
		System.out.println(archivesUsePersonInfo.getAreaID()+archivesUsePersonInfo.getDepartment()+archivesUsePersonInfo.getEmail());
		String message = "";//用于返回执行结果；如果执行成功则返回""，不成功则返回错误信息
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesUsePersonInfoManageService.add(archivesUsePersonInfo, pErrInfo)==false){
					pFlag = true;
					pErrInfo.getContent().insert(0, "添加档案利用人信息失败：");					
				}else{
					System.out.println("success: 添加档案利用人信息成功");
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
				System.out.println(pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return archivesUsePersonInfo.getID();
	}
	
	/**
	 * DWR 调用：根据姓名查询档案利用人信息
	 * @return
	 * @throws Exception
	 */
	public List<ArchivesUsePersonInfo> findArchivesUsePersonInfoByIDCardNo(ArchivesUsePersonInfo archivesUsePersonInfo ,HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ArchivesUsePersonInfo> archivesUsePersonInfos = new ArrayList<ArchivesUsePersonInfo>();
				
		try {
			//开始处理 1...
			pErrPos = 1;
						
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if(archivesUsePersonInfoManageService.findByIDCardNo(archivesUsePersonInfo.getIDCardNo(), archivesUsePersonInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据利用人证件号查询利用人信息失败。");
					System.out.println("ErrorInfo:"+pErrInfo.toString());					
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
		return archivesUsePersonInfos;
	}

	/**
	 * DWR调用：利用登记时扫描枪扫描条码加入利用清单
	 * @return
	 * @throws Exception
	 *///findArchivesByBarcode
	public StoreroomArchivesInfo findArchivesByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if(storeroomArchivesInfo==null){
				pFlag = false;
				pErrInfo.getContent().append("库房档案信息未初始化。");
			}else{
				//验证条形码是否为空
				if("".equals( storeroomArchivesInfo.getArchivesBarcode().trim() ) ){
					pFlag = false;
					pErrInfo.getContent().append("档案条码不能为空");
				}
			}			

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if(storeroomArchivesInfoManageService.findByBarcode(storeroomArchivesInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条形码查询库房档案信息失败：");
					storeroomArchivesInfo.setNBXH(0);//如果查询失败，则将NBXH设为0；供页面判断操作成功与否
					storeroomArchivesInfo.setTitle(pErrInfo.toShortString());//将错误信息通过Title传到页面
				}else{					
//					System.out.println("findArchivesByBarcode success!");
//					ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();
//					archivesUseOutInfo.setArchivesBarcode(storeroomArchivesInfo.getArchivesBarcode());
//					if(archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo)==false){
//						pFlag = false;
//						pErrInfo.getContent().insert(0,"根据档案条形码查询档案利用出去信息失败：");
//					}else{
//						//如果利用档案利用出去信息中存在该条形码所指定的档案，则不能再执行借出操作
//						System.out.println("NBXH:"+archivesUseOutInfo.getNBXH());
//						if(archivesUseOutInfo.getNBXH()!=0){
//							System.out.println("throw exception ");
//							throw  new Exception("该档案已经借出或正在被查档利用，请先执行归还操作！");							
//						}
//					}					
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
		return storeroomArchivesInfo;
	}
	
	/**
	 * 从利用清单(Session)中删除指定的档案
	 * @param archivesTypeId  
	 * @param NBXH
	 * @param archivesUseWay
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String delArchivesToUseList(int archivesTypeId,int NBXH,int archivesUseWay,HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		String message = "";
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpSession session = request.getSession(false);
		List<StoreroomArchivesInfo>	archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		List<StoreroomArchivesInfo>	useList = new ArrayList<StoreroomArchivesInfo>();
		try {
			//开始处理 1...
			pErrPos = 1;
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if(session == null){
					pFlag = false;
					pErrInfo.getContent().append("获取Session失败！");
				}else{
					archivesUseList =(List<StoreroomArchivesInfo>) session.getAttribute("archivesUseList");
				}				
			}
			
			//删除archivesUseList中的指定的档案信息
			if (pFlag) {
				pErrPos = 3;
				int numm = 1;
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					if(storeroomArchivesInfo.getArchivesTypeID()==archivesTypeId
							&& storeroomArchivesInfo.getNBXH()==NBXH 
							&& storeroomArchivesInfo.getTag().equals(archivesUseWay)){
						//删除满足条件的档案信息
						archivesUseList.remove(storeroomArchivesInfo);
						System.out.println(numm++);
						System.out.println("archivesUseList.size():"+archivesUseList.size());
						break;
						//useList.add(storeroomArchivesInfo);
					}
				}
			}
			
			//将处理后的利用列表更新到Session中去
			if(pFlag){
				pErrPos = 4;
				session.setAttribute("archivesUseList", archivesUseList);
				message = "删除成功！";
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
System.out.println(pErrInfo.toString());
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
		}
		System.out.println(message);
		return message;
	}
	
	/**
	 * 将档案利用信息添加到利用清单<br/>从库房档案信息中取档案信息，<br>将tag用来存放档案利用方式
	 * <br>说明：可根据archivesTypeIdAndNBXHs串以及archivesUseWay来<br>实现借档、查档、查看原文的申请<br>
	 * archivesTypeIdAndNBXHs为空时，可以实现查看档案的功能。
	 * @return 执行结果，添加成功！
	 * @throws Exception
	 */
	public String addArchivesToUseList() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWord = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		int archivesUseWay = 0;
		String[] archivesTypeIdAndNBXHs = {};//用于接收archivesTypeId和NBXH数组；格式：TypeID1:NBXH1;TypeID2:NBXH2;
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		
		try {
			pErrPos = 1;
			if (pFlag) {
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("获取Session失败！");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");

					if (archivesUseList==null) {
						archivesUseList =  new ArrayList<StoreroomArchivesInfo>(); 
					}
				}
			}
			
			//获取参数
			if (pFlag) {
				pErrPos = 2;
				//接收archivesTypeId和NBXH数组
				System.out.println("archivesTypeIdAndNBXHs:"+request.getParameter("archivesTypeIdAndNBXHs")+":");
				if(request.getParameter("archivesTypeIdAndNBXHs")!= null ){
					archivesTypeIdAndNBXHs = request.getParameter("archivesTypeIdAndNBXHs").trim().split(";");
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取档案标识信息失败！");
				}
				
				//接收利用方式
				if(request.getParameter("archivesUseWay")!=null && !"".equals(request.getParameter("archivesUseWay").trim())){
					archivesUseWay = Integer.parseInt(request.getParameter("archivesUseWay").trim());
				}else{
					pFlag = false;
					pErrInfo.getContent().append("获取利用方式失败！");
				}				
			}
			
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 3;
				int archivesTypeId = 0;
				int NBXH = 0;
				StoreroomArchivesInfo storeroomArchivesInfo = null;
				
				if(!"".equals(request.getParameter("archivesTypeIdAndNBXHs").trim())){//当选中档案时
					for (String IDNBXH : archivesTypeIdAndNBXHs) {
						archivesTypeId = Integer.parseInt(IDNBXH.split(":")[0]);
						NBXH = Integer.parseInt(IDNBXH.split(":")[1]);
						storeroomArchivesInfo = new StoreroomArchivesInfo();
						storeroomArchivesInfo.setArchivesTypeID(archivesTypeId);
						storeroomArchivesInfo.setNBXH(NBXH);
						if(storeroomArchivesInfoManageService.findStoreroomArchivesInfoByNBXH(storeroomArchivesInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"根据档案分类编号和NBXH查找档案库房位置信息失败：");
						}else{//查询档案信息成功后，设置档案利用方式,并加入到档案列表中						
							storeroomArchivesInfo.setTag(archivesUseWay);
							//check该档案是否已经存在
							if(checkArchivesInfoExistInUseList(storeroomArchivesInfo, archivesUseList)==true){
								archivesUseList.add(storeroomArchivesInfo);
								//archivesUseList.re
							}						
						}
					}
				}
				
			}
			
			//将获取的结果保存到Session中
			if (pFlag) {
				pErrPos = 4;
				session.setAttribute("archivesUseList", archivesUseList);
System.out.println("archivesUseList.size()2-->:"+archivesUseList.size());
			}
			
			//将结果返回到页面
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("applyFlag", archivesUseWay==2);//工作人员和利用申请用户标识
				request.setAttribute("archivesUseList", archivesUseList);
				request.setAttribute("useSize", archivesUseList.size());
				forWord = "toShowUseList";//查阅申请页面
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
				forWord = "error";
	System.out.println(pErrInfo.toString());
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWord;
	}
	
	/**
	 * 根据档案利用清单打印调卷单<br/>
	 * 从SESSION中取基本信息<br/>
	 * 再根据基本信息取馆藏位置<br/>
	 * 适用于档案管理室现场利用<br/>
	 * @return
	 * @throws Exception
	 */
	public String printLocationOfUseList() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		List<StoreroomArchivesInfo> storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();    

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("获取Session失败！");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");
					if (archivesUseList==null) {
						pFlag = false;
						pErrInfo.getContent().append("获取利用列表信息失败！");
					}else if(archivesUseList.size()==0){
						pFlag = false;
						pErrInfo.getContent().append("没有可被打印的记录！");
					}
				}
			}
			//档号；题名；盒条码；位置；馆藏状态
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				StoreAddressInfo storeAddressInfo ;
				
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					storeAddressInfo = new StoreAddressInfo();
					storeAddressInfo.setArchivesBoxBarcode(storeroomArchivesInfo.getArchivesBoxBarcode());
					if(storeAddressInfoManageService.findStoreAddressInfoByBoxBarcode(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"根据档案盒形码获取上架位置信息失败：");
					}
					String fullName = storeAddressInfo.getStoreAddressFullName();
					storeroomArchivesInfo.setTag(fullName);
					System.out.println("df");
					storeroomArchivesInfos.add(storeroomArchivesInfo);
					
				}
				
			}
			
			if (pFlag) {
				pErrPos = 3;
				System.out.println("打印位置信息...");
				for (StoreroomArchivesInfo storeroomArchivesInfo : storeroomArchivesInfos) {
					System.out.println("Boxbarcode:"+storeroomArchivesInfo.getArchivesBoxBarcode());
					System.out.println("tag:"+ storeroomArchivesInfo.getTag().toString());
					System.out.println("title:"+storeroomArchivesInfo.getTitle());
				}
			}
			
			//将数据传递到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeroomArchivesInfos", storeroomArchivesInfos);
				session.removeAttribute("archivesUseList");//如果操作成功就将SESSION中的利用列表清空
				forWard = "toPrintStoreroomLocation";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return forWard;
	}

	
	
	/**  
	 * 在线申请审批:查询第一条未审批在线申请单信息,先申请先审批<br>
	 * @return 在线申请单列表
	 * @throws Exception
	 */
	public String findOnLineUseLists() throws Exception{
		System.out.println("findOnLineUseLists----<");
		String forward = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();	
		ArchivesInfo archivesInfo = null ;
		IntegerEx recordsNum = new IntegerEx();
		ArchivesUseRequestDetail archivesUseRequestDetail = new ArchivesUseRequestDetail();
		String htmlCode = "";
		ArchivesType archivesType = null;
		List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles = new ArrayList<ArchivesInfoAttachedFile>();
		try {
			//开始处理 1...
			pErrPos = 1;

			//获取第一条待审核的档案
			if (pFlag) {
				pErrPos = 2;
				if(archivesUseRequestDetailManageService.findOneNotExamineArchivesUseRequestDetail(recordsNum, archivesUseRequestDetail, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找未审核的档案利用申请明细失败：");
				}

			}
			
			//获取档案详细信息，并生成用于在页面上显示的HTML代码
			if(pFlag){
				pErrPos = 3;				
				if(recordsNum.getValue()>0){
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesUseRequestDetail.getArchivesTypeID());
					archivesInfo = new ArchivesInfo(archivesType);
					Map<String, ArchivesTypeDataItem> dataItems = archivesType.getDataItemsForInput();
					//获取档案信息
					if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, archivesUseRequestDetail.getNBXH(), archivesInfo, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"根据内部序号查找档案信息失败：");
					}
					//生成页面上显示的，带有数据的HTML字符串
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
				}
			}
			
			//获取原文电子文件集合
			if (pFlag) {
				pErrPos = 4;
				if(recordsNum.getValue()>0){
					if(attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, archivesUseRequestDetail.getNBXH(), archivesInfoAttachedFiles, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取指定文件的原文电子文件信息失败：");
					}
				}
			}
			
			//获取归档后的原文电子文件的存放路径
			if (pFlag) {
				pErrPos = 5;
				if(recordsNum.getValue()>0){
					if(attachedFileManageService.getSavedArchivesInfoAttachedFilesSavePath(archivesType, archivesInfoAttachedFiles, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"获取归档后的原文电子文件的存放路径失败：");
					}
				}
			}
			
			//将查询结果等信息返回到页面
			if(pFlag){
				pErrPos = 6;		
				if(recordsNum.getValue()>0){
					request.setAttribute("archivesUseRequestDetail",archivesUseRequestDetail);
					request.setAttribute("userInfo", archivesUseRequestDetail.getArchivesUseRequest().getUserInfo());
					request.setAttribute("archivesInfoAttachedFiles", archivesInfoAttachedFiles);
					request.setAttribute("attachedFileSize",archivesInfoAttachedFiles.size());
					request.setAttribute("archivesInfo",archivesInfo);				
					request.setAttribute("htmlCode",htmlCode);
				}
				request.setAttribute("recordsNum", recordsNum.getValue());
				forward = "toCheckApplicationItem";  //转到申请审批
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
				System.out.println(pErrInfo.toString());
			}
		}
		return forward;
	}
	
	 


	/**
	 * DWR调用：新增利用登记:查档/借档登记
	 * @param archivesUseRegister
	 * @param archivesBarcodes 档案条形码集合字符串
	 * @param useDays 要用的天数
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public String addArchivesUseRegister(ArchivesUseRegister archivesUseRegister,String archivesBarcodes,int useDays, HttpServletRequest request) throws Exception{
		System.out.println("addArchivesUseRegister is execute success!");
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		String messge = "";
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);		
		String[] barcodes ={};
		try {
			//数据验证
			pErrPos = 1;
			if("".equals(archivesBarcodes)){
				pFlag = false;
				pErrInfo.getContent().append("没有任何被利用的档案，请输入档案码！");
			}else{
				//获取档案条形码数组
				barcodes = archivesBarcodes.split(":");
			}			

			if(archivesUseRegister == null){
				pFlag = false;
				pErrInfo.getContent().append("利用登记信息实体未初始化！");
			}

			//调用业务逻辑,添加档案利用登记信息
			if (pFlag) {
				pErrPos = 2;
				archivesUseRegister.setUseDate(new Date());
				archivesUseRegister.setUseArchivesCount(barcodes.length);
				archivesUseRegister.setManagerUserID(userInfo.getUserID());
				if(archivesUseManageService.addArchivesUseRegister(archivesUseRegister, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加档案利用登记信息失败：");
				}
				
				for(int i=0;i<barcodes.length;i++){
					//声明库房档案信息，可通过条形码获得档案基本信息
					StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
					storeroomArchivesInfo.setArchivesBarcode(barcodes[i]);
					
					//根据档案条形码获取库房档案基本信息
					if(storeroomArchivesInfoManageService.findByBarcode(storeroomArchivesInfo, pErrInfo)){
						//根据库房档案信息构造 实物档案利用出去明细情况实体
						ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();						
						archivesUseOutInfo.setArchivesBarcode(storeroomArchivesInfo.getArchivesBarcode());
						archivesUseOutInfo.setArchivesID(storeroomArchivesInfo.getArchivesID());
						archivesUseOutInfo.setArchivesTypeID(storeroomArchivesInfo.getArchivesTypeID());
						archivesUseOutInfo.setBorrowFlag(archivesUseRegister.getBorrowFlag());
						archivesUseOutInfo.setNBXH(storeroomArchivesInfo.getNBXH());
						//archivesUseOutInfo.setPageSum()//档案总页数
						archivesUseOutInfo.setShouldReturnDate(new Date(new Date().getTime()+(long)useDays*24*3600*1000));
						archivesUseOutInfo.setTitle(storeroomArchivesInfo.getTitle());
						archivesUseOutInfo.setUseRegID(archivesUseRegister.getID());
						
						EnumStoreStatus storeStatus = EnumStoreStatus.查档利用中;
						if(archivesUseRegister.getBorrowFlag()){
							storeStatus = EnumStoreStatus.借阅利用中;
						}
						storeroomArchivesInfo.setStoreStatus(storeStatus);
						//调用业务逻辑：更新指定档案条形码库房档案信息的馆藏状态
						if(storeroomArchivesInfoManageService.updateStoreStatusByArchivesBarcode(storeroomArchivesInfo, pErrInfo) == false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"更新库房档案信息的馆藏状态失败：");
						}
					
						//添加利用出去详细信息
						if(archivesUseOutInfoManageService.addArchivesUseOutInfo(archivesUseOutInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"添加一个新的实物档案利用出去明细失败：");
						}else{
							System.out.println("添加一个新的实物档案利用出去明细成功："+i);
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
System.out.println(pErrInfo.toString());
				messge = pErrInfo.toShortString();
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return messge;

	}
	
	/**
	 * 查找利用(借档/查档)登记记录<br>
	 * 单击菜单 时显示所有已登记的利用信息 
	 * @return
	 * @throws Exception
	 */
	public String findUseList() throws Exception{
		String forward = "";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUseRegister> archivesUseRegisters = new ArrayList<ArchivesUseRegister>();

		String useType = "";
		try {
		
			pErrPos = 1;
			//区分借档还是借阅			
			if(request.getParameter("useType")==null || "".equals(request.getParameter("useType"))){
				pFlag = false;
				pErrInfo.getContent().append("");
			}else{
				useType = request.getParameter("useType");
				//设置借档/查档标识
				if("JD".equals(useType)){
					forward  = "toJDList";
					archivesUseRegisterQueryCondition.setBorrowFlag(true);
				}else if("CD".equals(useType)){
					forward  = "toCDList";
					archivesUseRegisterQueryCondition.setBorrowFlag(false);
				}
			}
			

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findArchivesUseRegisters(archivesUseRegisterQueryCondition, dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"查询档案利用登记信息失败：");
				}else{
					System.out.println("查询档案利用登记信息成功！findArchivesUseRegisters");
					for(int i = 0;i<archivesUseRegisters.size();i++){
						System.out.println("getManagerUserID:"+archivesUseRegisters.get(i).getManagerUserID());
						System.out.println("getUseDate:"+archivesUseRegisters.get(i).getUseDate());
						System.out.println("Name:"+archivesUseRegisters.get(i).getArchivesUsePersonInfo().getName());
						System.out.println("Department:"+archivesUseRegisters.get(i).getArchivesUsePersonInfo().getDepartment());
					}
				}
				
			}
			
			if(pFlag){
				request.setAttribute("archivesUseRegisters",archivesUseRegisters);
			
				
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
				System.out.println("error: "+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}
		return forward;
	}
	
	
	
	/**
	 * 根据ID查找利用登记信息<br/>
	 * 对应功能 登记列表页面查看详细
	 * @return
	 * @throws Exception
	 */
	public String findArchivesUseRegisterByID() throws Exception{
		String forward = "";		
		String useTypeText = "借档";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//利用目的
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();//档案利用登记信息
		String useType = "";
		int registerID = 0;//利用登记编号
		boolean useOutDateFlag = false;//默认查询未过期的所有利用明细信息
		boolean useNearOutDateFlag = false;//到期预警标识
		int dayNum = 4;//到期预警的天数
		try {
			//开始处理 1...
			pErrPos = 1;
			if("".equals(request.getParameter("useType")) || request.getParameter("useType")==null){
				pFlag = false;
				pErrInfo.getContent().append("获取利用方式失败！");
			}else{
				useType = request.getParameter("useType");
				if("CD".equals(useType)){
					useTypeText = "查档";
				}
			}
			
			if("".equals(request.getParameter("registerID")) || request.getParameter("registerID")==null){
				pFlag = false;
				pErrInfo.getContent().append("获取利用登记编号失败！");
			}else{
				registerID = Integer.parseInt(request.getParameter("registerID"));
				archivesUseRegister.setID(registerID);
			}
			
			if(!(request.getParameter("useOutDate")==null) && !"".equals(request.getParameter("useOutDate"))){
				//设置过期标识
				useOutDateFlag = "true".equals(request.getParameter("useOutDate").trim());
				useNearOutDateFlag = "near".equals(request.getParameter("useOutDate").trim());
			}
			

			////调用业务逻辑	，生成利用目的
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"初始化档案利用目的失败：");
				}	
			}
			
			if(pFlag){
				pErrPos = 3;
				if(archivesUseManageService.findArchivesUseRegister(archivesUseRegister, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据利用编号查询档案利用信息失败：");
				}
			}
			
			List<ArchivesUseOutInfo> archivesUseOutInfos = new ArrayList<ArchivesUseOutInfo>();
			//调用业务逻辑，查询出档案利用出去明细信息集合
			if(pFlag){
				pErrPos = 4;				
				if(archivesUseOutInfoManageService.findArchivesUseOutInfosByRegisterID(archivesUseRegister.getID(), archivesUseOutInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据利用登记编号查找利用出去明细信息失败：");
				}
			}
			
			List<ArchivesUseOutInfo> dateOutArchivesUseOutInfos = new ArrayList<ArchivesUseOutInfo>();
			if (pFlag) {
				pErrPos = 5;
				
				//构造过期档案列表
				if(useOutDateFlag){
					for (int i= 0;i<archivesUseOutInfos.size();i++) {
						if(archivesUseOutInfos.get(i).getShouldReturnDate().before(new Date())){
							dateOutArchivesUseOutInfos.add(archivesUseOutInfos.get(i));
						}
					}
					archivesUseOutInfos.clear();
					archivesUseOutInfos.addAll(dateOutArchivesUseOutInfos);
				}
				
				//构造到期预警档案列表
				if(useNearOutDateFlag){
					for (int i= 0;i<archivesUseOutInfos.size();i++) {
						long tim = archivesUseOutInfos.get(i).getShouldReturnDate().getTime()-(new Date()).getTime();
						if(tim < (long)dayNum*24*3600*1000 && tim > 0 ){
							dateOutArchivesUseOutInfos.add(archivesUseOutInfos.get(i));
						}
					}
					archivesUseOutInfos.clear();
					archivesUseOutInfos.addAll(dateOutArchivesUseOutInfos);
				}
				
			}
			
			
			//将结果返回至页面
			if (pFlag) {
				pErrPos = 3;
				System.out.println("request.setAttributrchivesUseRegister");
			//	request.setAttribute("operate", request.getParameter("operate"));
				request.setAttribute("archivesUseRegister",archivesUseRegister);
				request.setAttribute("archivesUseOutInfos",archivesUseOutInfos);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("useType",useType);//利用方式：JD：借档；CD：查档
				request.setAttribute("useTypeText", useTypeText);//利用方式文本
				
				forward = "toDJView";
				if(useOutDateFlag){
					forward = "toOutDateDJView";
				}
				System.out.println("forWard:"+forward);
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

			System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
System.out.println(pErrInfo.toString());
			//销毁局部变量
			throwable = null;
		}	
		return forward;	
		
	}
	
	
	/**
	 * DWR:添加在线利用申请（单击利用信息登记页面中的确定按钮时触发）
	 * @return
	 * @throws Exception
	 */
	public String addRegisterInfoForOnlineApply(UserInfo userInfo,String userDepartment, String requestReason, HttpServletRequest request) throws Exception{
	//public String addRegisterInfoForOnlineApply() throws Exception{
		/*
		 * 所有的用户（包括匿名用户和已注册用户）
		 * 步骤：1、获取用户信息。如果页面上有传userID号，则直接用此ID来进行登记，如果没有则要先添加用户到用户信息表中，并给赋权限，并返回用户的ID
		 *       2、将利用信息写到登记表中
		 *       3、将利用信息写到在线申请详细表中
		 *       4、如果是原文申请的话还要将信息写到原文申请表中      
		 *       
		 */		 
		boolean pFlag = true;
		int pErrPos = 0;
		String result = "";
		String forWard = "";
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpSession session = request.getSession();
		int userID = userInfo.getUserID();
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();//Session 中存放的档案信息集合
		ArchivesUseRequest archivesUseRequest = new ArchivesUseRequest();
		try {
			pErrPos = 1;
			
			//begin:检查SESSION中利用列表中是否有数据
			archivesUseList = (List<StoreroomArchivesInfo>)session.getAttribute("archivesUseList");
			if(archivesUseList == null ){
				throw new Exception("获取利用档案列表失败！");
			}else if(archivesUseList.size() == 0){
				throw new Exception("利用列表中没有可被利用的档案，请先向利用利用中添加待利用的档案！");
			}	
			//1:检查用户信息，保证用户编号正确
			if (pFlag) {
				pErrPos = 2;
				if (userID == 0) {	
					//检查用户名是否存在
					if(userInfoManageService.saveUserInfo(userInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"添加利用人信息失败：");
					}else{//成功添加用户
						userID = userInfo.getUserID();
						UserRolesInfo userRolesInfo = new UserRolesInfo();
						userRolesInfo.setUserID(userID);
						userRolesInfo.setRolesID(SystemInitializer.getInstance().getAnonymouseUserRoleID());
System.out.println("userRolesInfo.getRolesID():"+userRolesInfo.getRolesID());
						//对用户授权
						if(userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"添加一个新的用户角色信息失败：");
						}
					}					
				}
			}
			
			//2:将利用信息写到利用登记表中
			if (pFlag) {
				pErrPos = 3;	
				archivesUseRequest.setRequestReason(requestReason);
				archivesUseRequest.setRequestTime(new Date());
				archivesUseRequest.setUserID(userID);
				archivesUseRequest.setUserDepartment(userDepartment);//用户信息中
				if (archivesUseRequestManageService.addArchivesUseRequest(archivesUseRequest, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"添加档案利用申请单信息失败：");
				}
			}
			
			//将利用信息写到在线申请详细表中
			if (pFlag) {
				pErrPos = 4;							
				ArchivesUseRequestDetail archivesUseRequestDetail;
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					//获取档案分类信息
					ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(storeroomArchivesInfo.getArchivesTypeID());
					//获取档案信息
					ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);				
					if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, storeroomArchivesInfo.getNBXH(), archivesInfo, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"根据内部序号查找档案信息失败：");
					}
					//添加档案利用申请单明细
					archivesUseRequestDetail = new ArchivesUseRequestDetail();
					archivesUseRequestDetail.setArchivesID(archivesInfo.getArchivesID());
					archivesUseRequestDetail.setArchivesTypeID(archivesInfo.getArchivesTypeID());
					archivesUseRequestDetail.setTitle(archivesInfo.getTitle());
					archivesUseRequestDetail.setNBXH(archivesInfo.getNBXH());
					archivesUseRequestDetail.setRequestID(archivesUseRequest.getID());
					archivesUseRequestDetail.setSecrecyID(archivesInfo.getSecrecyID());
					archivesUseRequestDetail.setUseWayID((Integer)storeroomArchivesInfo.getTag());
					
					if(archivesUseRequestDetailManageService.addArchivesUseRequestDetail(archivesUseRequestDetail, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"添加档案利用申请单明细失败：");
					}else{//添加成功,添加电子原文的申请通过信息是在审批申请的时候完成的
//						if(archivesUseRequestDetail.getUseWayID()==3){
//							AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo = new AttachedFileUseRequestPassInfo();
//							attachedFileUseRequestPassInfo.setArchivesID(archivesInfo.getArchivesID());
//							attachedFileUseRequestPassInfo.setArchivesTypeID(archivesInfo.getArchivesTypeID());
//							//设置原文有效使用时间,公式：现在时间+可利用时间天数
//							Date now = new Date();
//							attachedFileUseRequestPassInfo.setExpirationDate(new Date(now.getTime()+ (long)7*24*3600*1000));
//							attachedFileUseRequestPassInfo.setNBXH(archivesInfo.getNBXH());
//							attachedFileUseRequestPassInfo.setTitle(archivesInfo.getTitle());
//							attachedFileUseRequestPassInfo.setUserID(archivesUseRequest.getUserID());
//							if(attachedFileUseRequestPassInfoManageService.addAttachedFileUseRequestPassInfo(attachedFileUseRequestPassInfo, pErrInfo)==false){
//								pFlag = false;
//								pErrInfo.getContent().insert(0,"添加档案利用申请单明细失败：");
//							}
//
//						}
					}
				}
			}
			
			//清除Session中存放的利用档案列表
			if(pFlag){
				pErrPos = 5;
				session.removeAttribute("archivesUseList");
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
		}

		return "";
	}
	
	
	/**
	 * 得到在线申请利用的用户信息登记页面所需的初始数据<br>
	 * @return 
	 * @throws Exception
	 */
	public String getRegistUserInfoDataForOnlineApply() throws Exception{
		String forWard = "";	
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//利用目的
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		String useType = "";
		try {
			//开始处理 1...
			
			if (pFlag) {
				pErrPos = 1;
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("获取Session失败！");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");
					if (archivesUseList==null) {
						pFlag = false;
						pErrInfo.getContent().append("获取利用列表信息失败！");
					}else if(archivesUseList.size()==0){
						pFlag = false;
						pErrInfo.getContent().append("没有可被打印的记录！");
					}
				}
			}

			////调用业务逻辑	
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"初始化档案利用目的失败：");
				}	
			}
			
			
			
			
			//将结果返回至页面
			if (pFlag) {
				pErrPos = 3;
				//如果是匿名账户，就将其用户编号设为0，以供页面验证
				if(userInfo.getAnonymouseFlag()==false){
					//userInfo.setUserID(0);
					userInfo = new UserInfo();
				}
				request.setAttribute("userInfo", userInfo);
				request.setAttribute("anonymouseFlag",true);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);//利用目的				
				forWard = "registUserInfoForOnlineApply";
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
				forWard = "error";
				request.setAttribute("pErrInfo",pErrInfo);
System.out.println("pErrInfo.toString():"+pErrInfo.toString());
			}

			//销毁局部变量
			throwable = null;
		}	
		return forWard;
	}
	
	
	/**
	 * 得到 查档/借档 页面初始化数据<br>
	 * 利用目的集合 所属区域集合
	 * @return 
	 * @throws Exception
	 */
	public String findDefaultDataForDJ() throws Exception{
		String forWard = "";		
		String useTypeText = "借档";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//利用目的
		String useType = "";
		int useDays = 0;
		try {
			//开始处理 1...
			pErrPos = 1;
			if("".equals(request.getParameter("useType")) || request.getParameter("useType")==null){
				pFlag = false;
				pErrInfo.getContent().append("获取利用方式失败！");
			}else{
				useType = request.getParameter("useType");
				if("CD".equals(useType)){
					useTypeText = "查档";
				}
			}

			////调用业务逻辑	
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"初始化档案利用目的失败：");
				}else{
					System.out.println("初始化档案利用目的成功！种数共:"+ archivesUsePurposes.size());
				}		
			}
			
			//获取利用天数
			if (pFlag) {
				pErrPos = 3;
				if("JD".equals(useType)){//如果是借档，则利用天数要从配置表中取
					List<Config> pConfigs = new ArrayList<Config>();
					if(configManageService.findConfigByConfigType("UseRenewPeriodDays", pConfigs, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"从配置表中取档案利用的续借期限失败：");
					}else{
						if(pConfigs.size()>0){
							useDays = Integer.parseInt(pConfigs.get(0).getConfigValue());
						}else{
							pFlag = false;
							pErrInfo.getContent().insert(0,"配置表未找到档案利用的续借期限(UseRenewPeriodDays)：");
						}
					}
				}else{//如果是查档，则利用天数为1
					useDays = 1;
				}		
			}
			
			//将结果返回至页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("useDays", useDays);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("useType",useType);//利用方式：JD：借档；CD：查档
				request.setAttribute("useTypeText", useTypeText);//利用方式文本
				
				forWard = "toJDDJ";
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
		return forWard;
	}
	
	/**
	 * 跳转到档案利用查询<br>获取一些系统参数
	 * @return
	 * @throws Exception
	 */
	public String toArchivesUseQuery() throws Exception{
		String forward = "";		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();		 	
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();
		List<ArchivesUseWay> archivesUseWays = new ArchivesUseWay().getAllArchivesUseWay();
		
		try {
			pErrPos = 1;			
			
			//获取利用目的		
			if (pFlag) {
				pErrPos = 2;
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取利用目的失败:");
				}				
			}
			

			if(pFlag){
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("archivesUseWays",archivesUseWays);//利用方式固化在实体类中
				forward = "toLYCX";
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
		return forward;		
	}
	
	
	/**
	 * 根据条件查询档案利用出去明细记录<br/>
	 * 
	 * @return
	 * @throws Exception
	 */
	public String findArchivesInfoUseList() throws Exception{		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		List<ArchivesUseOutInfo> archivesUseOutInfos = new ArrayList<ArchivesUseOutInfo>();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();
		List<ArchivesUseWay> archivesUseWays = new ArrayList<ArchivesUseWay>();
		try {
			//开始处理 1...
			pErrPos = 1;
			
			//获取利用目的		
			if (pFlag) {
				pErrPos = 2;
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取利用目的失败:");
				}				
			}
			
			if (pFlag) {
				pErrPos = 3;
				if(archivesUseWayManageService.findAllArchivesUseWay(archivesUseWays, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"获取所有利用方式失败：");
				}
			
			}

			//调用业务逻辑，执行查询操作
			if (pFlag) {
				pErrPos = 4;
				dataPageInfo.setPageSize(15);
				if(archivesUseOutInfoManageService.findArchivesUseOutInfosByQueryCondition(archivesUseInfoQueryCondition, dataPageInfo, archivesUseOutInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条件查询档案利用明细信息失败：");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesUseOutInfos", archivesUseOutInfos);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("archivesUseWays",archivesUseWays);
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
		return "toLYCX";
	}
	

	/**
	 * 根据条件查询用户的在线申请单列表（在线申请单查询）
	 * @return
	 * @throws Exception
	 */
	public String findOnlineArchivesUseRequesList() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUseRequest> archivesUseRequests = new ArrayList<ArchivesUseRequest>();
		
		try {
			//开始处理 1...
			pErrPos = 1;

			//调用业务逻辑，执行查询操作
			if (pFlag) {
				pErrPos = 4;
				dataPageInfo.setPageSize(15);
				if(archivesUseRequestManageService.findArchivesUseRequestsByCondition(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条件查询档案利用明细信息失败：");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesUseRequests", archivesUseRequests);
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
		return "toQueryOnline";
	}
	
	
	/**
	 * 根据条件查询用户的在线申请单列表（在线申请单查询）
	 * @return
	 * @throws Exception
	 */
	public String showRequestDetailListByRequestID() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUseRequestDetail> archivesUseRequestDetails = new ArrayList<ArchivesUseRequestDetail>();
		String requestID = "";
		try {
			//开始处理 1...
			pErrPos = 1;
			if(request.getParameter("requestID")!=null && !"".equals(request.getParameter("requestID").trim())){
				requestID = request.getParameter("requestID").trim();
			}else{
				pFlag = false;
				pErrInfo.getContent().append("在线申请单编号非法出为空！");
			}

			//调用业务逻辑，执行查询操作
			if (pFlag) {
				pErrPos = 2;				
				if(archivesUseRequestDetailManageService.findArchivesUseRequestDetailsByRequestID(requestID, archivesUseRequestDetails, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"根据条件查询档案利用明细信息失败：");
				}
			}
			
			//将数据返回到页面
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("archivesUseRequestDetails", archivesUseRequestDetails);
				request.setAttribute("recordSize", archivesUseRequestDetails.size());
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
System.out.println(pErrInfo.toString());
			//销毁局部变量
			throwable = null;
		} 		
		return "toOnlineRequestDetailList";
	}
	
	/**
	 * 跳转到续借档案页面<br>获取一些系统参数
	 * @return
	 * @throws Exception
	 */
	public String toRenewArchives() throws Exception{
		String forward = "";		
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();		
		List<Config> pConfigs = new ArrayList<Config>(); 		
		int dayNum = 0;
		try {
			pErrPos = 1;			
			
			//调用业务逻辑获取续借天数
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("UseRenewPeriodDays", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"从配置表中获取续借天数失败：");
				}else{
					dayNum = Integer.parseInt(pConfigs.get(0).getConfigValue());
				}
			}
			
			//将数据返回到页面
			if(pFlag){
				request.setAttribute("dayNum",dayNum);
				forward = "toXJDJ";
			
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
		return forward;		
	}
	
	/**
	 * DWR调用：续借档案
	 * 所需要的参数：daysNum:续借天数；archivesBarcode:档案条形码
	 * @throws Exception
	 */
	public ArchivesUseOutInfo renewArchives(int daysNum,String archivesBarcode,HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		System.out.println(daysNum);
		System.out.println(archivesBarcode);
		
		
		try {
			//开始处理 1...
			pErrPos = 1;

			//调用业务逻辑执行续借操作
			if (pFlag) {
				pErrPos = 2;
				archivesUseOutInfo.setArchivesBarcode(archivesBarcode);
				if(archivesUseManageService.renewArchivesByBarcode(daysNum, archivesUseOutInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行续借操作失败：");
				}else{
					System.out.println("执行续借操作成功！");
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
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
			System.out.println(archivesUseOutInfo.getTitle());
		}
		return archivesUseOutInfo;
	}
	
	/*
	 * DWR调用：归还（借出/查看利用）实物档案
	 */
	public ArchivesUseOutInfo returnArchives(String archivesBarcode,HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		
		System.out.println(archivesBarcode);
		int daysNum = 0;
		
		
		try {
			//开始处理 1...
			pErrPos = 1;

			//调用业务逻辑执行续借操作
			if (pFlag) {
				pErrPos = 2;
				archivesUseOutInfo.setArchivesBarcode(archivesBarcode);
				if(archivesUseManageService.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"执行归还操作失败：");
				}else{
					System.out.println("执行归还操作成功！");
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
				throw new Exception(pErrInfo.toShortString());
			}

			//销毁局部变量
			throwable = null;
			System.out.println(archivesUseOutInfo.getTitle());
		}
		return archivesUseOutInfo;
	}
	
	
	
	/**
	 * 到期预警：将到期的档案信息查询出来
	 * @return
	 * @throws Exception
	 */
	public String findExpiringArchivesUseInfos() throws Exception{
		System.out.println("findExpiringArchivesUseInfos!");
		String message = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;		
		Throwable throwable = new Throwable();
		List<ArchivesUseRegister> archivesUseRegisters = new ArrayList<ArchivesUseRegister>();
		HttpServletRequest request = ServletActionContext.getRequest();
		int dayNum = 0;//到期预警的天数；从数据库中取
		List<Config> pConfigs = new ArrayList<Config>();

		try {
			//开始处理 1...
			pErrPos = 1;
			
			//获取续借天数
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("UseAdvanceDueWarningDays", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"从配置表中获取到期提前预警的天数失败：");
				}else{
					dayNum = Integer.parseInt(pConfigs.get(0).getConfigValue());
				}
			}
System.out.println("dayNum:"+dayNum);

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findExpiringArchivesUseRegister(dayNum,dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找需要过期催还的档案利用人信息失败：");
				}else{
					System.out.println("archivesUseManageService.findExpiringArchivesUseRegister success!");
					System.out.println("archivesUseRegisters.size():"+archivesUseRegisters.size());
				}
			}
			
			//将数据返回到页面
			if(pFlag){
				pErrPos = 3;
				request.setAttribute("archivesUseRegisters",archivesUseRegisters);
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return "toDQYJ";
	}
	
	/**
	 * 过期催还：查找过期催还的档案利用人信息
	 * @return
	 * @throws Exception
	 */	
	public String findArchivesUseExpiredUserInfos() throws Exception{
		String forWord = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;		
		Throwable throwable = new Throwable();
		List<ArchivesUseRegister> archivesUseRegisters = new ArrayList<ArchivesUseRegister>();
		HttpServletRequest request = ServletActionContext.getRequest();

		try {
			//开始处理 1...
			pErrPos = 1;
			

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findArchivesUseExpiredUseRegister(dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找需要过期催还的档案利用人信息失败：");
				}else{
					System.out.println("archivesUseManageService.findArchivesUseExpiredUserInfos success!");
				}
			}
			
			if(pFlag){
				pErrPos = 3;
				request.setAttribute("archivesUseRegisters",archivesUseRegisters);
				forWord = "toGQCH";
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
				forWord = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}
		return forWord;
	}
	


	

	/**
	 * 查找未审核的档案利用申请明细信息
	 * @return
	 * @throws Exception
	 */
//	public String findArchivesUseRequestDetailsByName() throws Exception{
//		String result = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		ErrInfo pErrInfo = new ErrInfo();
//		Throwable throwable = new Throwable();
//		DataPageInfo dataPageInfo = new DataPageInfo();
//		List<ArchivesUseRequest> archivesUseRequests = new ArrayList<ArchivesUseRequest>();
//		DataItem dataItem = new DataItem();//著录数据项实体
//		if(userName == null){
//			userName = "";
//		}
//		dataItem.setColumnName(userName);//设置列名
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//开始处理 1...
//			pErrPos = 1;
//			
//
//			//调用业务逻辑：根据条件查询用户信息集合
//			if (pFlag) {
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//					//生成测试数据
//					//setArchivesUseRequests(archivesUseRequests);
//System.out.println("--> findArchivesUseRequestDetailsByName   userName: " +userName);
//					result = "success";
//				}else{
//					pFlag = false;//调用业务逻辑出错，标识为失败
//				}
//			}			
//			pErrPos = 2;
//			//将查询结果等信息返回到页面
//			if(pFlag){
//				request.setAttribute("archivesUseRequests",archivesUseRequests);	
//				request.setAttribute("recordSize",archivesUseRequests.size());
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

	
	/**
	 * 打印调卷单
	 * @return
	 * @throws Exception
	 */

//	public String printArchivesUseRequestDetails() throws Exception{
//		String result = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		ErrInfo pErrInfo = new ErrInfo();
//		Throwable throwable = new Throwable();
//		DataPageInfo dataPageInfo = new DataPageInfo();
//		List<ArchivesUseRequest> archivesUseRequests = new ArrayList<ArchivesUseRequest>();
//		DataItem dataItem = new DataItem();//著录数据项实体
//
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//开始处理 1...
//			pErrPos = 1;
//			
//
//			//调用业务逻辑：根据条件查询用户信息集合
//			if (pFlag) {
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//					//生成测试数据
//				//	setArchivesUseRequests(archivesUseRequests);
//
//					result = "success";
//				}else{
//					pFlag = false;//调用业务逻辑出错，标识为失败
//				}
//			}			
//			pErrPos = 2;
//			//将查询结果等信息返回到页面
//			if(pFlag){
//				request.setAttribute("archivesUseRequests",archivesUseRequests);	
//				request.setAttribute("recordSize",archivesUseRequests.size());
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
	 * 撤销多个档案利用登记信息（申请审批通过））
	 * @return
	 * @throws Exception
	 */
//	public String unregisterArchivesUses() throws Exception{
//		String result = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		ErrInfo pErrInfo = new ErrInfo();
//		Throwable throwable = new Throwable();
//		DataPageInfo dataPageInfo = new DataPageInfo();
//		List<ArchivesUseRegister> archivesUseRegisters = new ArrayList<ArchivesUseRegister>();
//		List<ArchivesUseRequest> archivesUseRequests = new ArrayList<ArchivesUseRequest>();
//		DataItem dataItem = new DataItem();//著录数据项实体
////		if(userName == null){
////			userName = "";
////		}
////		dataItem.setColumnName(userName);//设置列名
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//开始处理 1...
//			pErrPos = 1;
//			ArchivesUseRegister register = null;
////			for(int i = 0;i<IDs.length;i++){
////				register = new ArchivesUseRegister();
////				register.setID(IDs[i]);
////				archivesUseRegisters.add(register);
////System.out.println("ID: "+register.getID());
////			}
////			
//			pErrPos = 2;
//
//			//调用业务逻辑：根据条件查询用户信息集合
//			if (pFlag) {
//				
//				if(true/*archivesUseManageService.unregisterArchivesUse(archivesUseRegisters, pErrInfo)*/){
//					//生成测试数据
//				//	setArchivesUseRequests(archivesUseRequests);
//
//					result = "success";
//				}else{
//					pFlag = false;//调用业务逻辑出错，标识为失败
//				}
//			}			
//			pErrPos = 3;
//			//调用业务逻辑：查询数据
//			if(pFlag){
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//
//				//	setArchivesUseRequests(archivesUseRequests);
//					
//				}else{
//					pFlag = false;//调用业务逻辑出错，标识为失败
//					result = "error";
//				}
//			}
//			//将查询结果等信息返回到页面
//			if(pFlag){
//				request.setAttribute("archivesUseRequests",archivesUseRequests);	
//				request.setAttribute("recordSize",archivesUseRequests.size());
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

	
	
	/**
	 * DWR: 对用户提交的在线申请进行审批
	 * @param ID 申请单明细编号
	 * @param checkResult 审批结果
	 * @param backReason 打回理由
	 * @return 0/1/2:  0代表执行失败；1代表通过；2代表不通过
	 * @throws Exception
	 */
	public int checkArchivesUseRequestDetail(int ID ,int checkResult,String backReason,HttpServletRequest request)throws Exception{
		int result = checkResult;
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();	
		backReason = backReason.trim();//去掉字符串中的空格
		ArchivesUseRequestDetail archivesUseRequestDetail = new ArchivesUseRequestDetail();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
System.out.println("ID:"+ID);
				
		try {		
			pErrPos = 1;			
			//将参数封装到对象中去						
			archivesUseRequestDetail.setID(ID);//申请单明细编号
			archivesUseRequestDetail.setCheckTime(new Date());//审批时间
			archivesUseRequestDetail.setCheckResult(checkResult);//审批结果
			archivesUseRequestDetail.setBackReason(backReason);//打回理由
			archivesUseRequestDetail.setCheckUserID(userInfo.getUserID());//审批人编号

			//调用业务逻辑：根据条件查询用户信息集合
			if (pFlag) {
				pErrPos = 2;
				if(archivesUseRequestDetailManageService.checkArchivesUseRequestDetail(archivesUseRequestDetail, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"更新指定的档案利用申请单明细的审批结果失败：");
				}else{
					System.out.println("更新指定的档案利用申请单明细的审批结果成功！");					
				}
			}
		
		} catch (Exception e) {
			result = 0;
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
				throw new  Exception(pErrInfo.toShortString());
			}
		}
		return result;
	}
	
	/**
	 * 判断利用清单中是否有该档案的利用信息，如果没有，则返回true
	 * @param archivesTypeId 档案分类编号
	 * @param NBXH 
	 * @param storeroomArchivesInfos 利用清单
	 * @return
	 */
	private boolean checkArchivesInfoExistInUseList(StoreroomArchivesInfo info,List<StoreroomArchivesInfo> storeroomArchivesInfos){
		boolean pFlag = true;
		for (StoreroomArchivesInfo storeroomArchivesInfo : storeroomArchivesInfos) {
			if(storeroomArchivesInfo.getArchivesTypeID()==info.getArchivesTypeID() && storeroomArchivesInfo.getNBXH()==info.getNBXH() && storeroomArchivesInfo.getTag().equals(info.getTag())){
				pFlag = false;
			}
		}		
		return pFlag;
	}
	

}
