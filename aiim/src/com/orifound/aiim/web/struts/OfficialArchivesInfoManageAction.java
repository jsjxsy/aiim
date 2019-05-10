package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.orifound.aiim.bll.service.IOfficialArchivesTypeSavedMappingManageService;
import com.orifound.aiim.entity.ArchivesFonds;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialArchivesTypeSavedMapping;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;

/**
 * 公文管理-公文文档管理 控制类(Action)
 * 
 * @author xsy
 */
public class OfficialArchivesInfoManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(OfficialArchivesInfoManageAction.class);

	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService; // 公文档案管理业务类
	@Autowired
	private IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService;// 公文档案保存映射管理
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
	 * 获取公文类别树<br/>
	 * 公文登记和公文归档
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getOfficialArchivesTypeTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = new LinkedHashMap<Integer, OfficialArchivesType>();
		officialArchivesTypes = SystemInitializer.getInstance().getOfficialArchivesTypes();
		String tree = CreateTreeUtil.getOfficialArchivesTypeTree(officialArchivesTypes);
		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction", "officialArchivesInfoManageAction_getHtmlCodeForInputQuery.action?type=" + request.getParameter("type"));
		return SUCCESS;
	}

	/**
	 * 获取公文归档树<br/>
	 * 文档中心
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getDocCenterTypeTree() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		LinkedHashMap<Integer, OfficialDocType> officialDocTypes = new LinkedHashMap<Integer, OfficialDocType>();
		LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = new LinkedHashMap<Integer, OfficialArchivesType>();
		List<DepartmentInfo> departmentInfos = new ArrayList<DepartmentInfo>();
		officialArchivesTypes = SystemInitializer.getInstance().getOfficialArchivesTypes();
		officialDocTypes = SystemInitializer.getInstance().getOfficialDocTypes();
		departmentInfos = SystemInitializer.getInstance().getDepartmentInfos();
		String tree = CreateTreeUtil.getDocCenterTree(officialArchivesTypes, officialDocTypes, departmentInfos);
		request.setAttribute("tree", tree);
		request.setAttribute("center", "ZX");
		request.setAttribute("proceseAction", "officialArchivesInfoManageAction_getHtmlCodeForInputQuery.action");
		return SUCCESS;
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
		String docType = "";
		String htmlCode = "";
		Map<String, ArchivesTypeDataItem> dataItems = null;
		int FormationDepartmentID = 0;
		try {
			// 判断officialArchivesTypeID是否非法
			if (pFlag) {
				pErrPos = 1;
				if (request.getParameter("officialArchivesTypeID") == null && request.getParameter("officialArchivesTypeID").equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("officialArchivesTypeID丢失！");
				} else {
					officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
				}
			}

			// 判断docType是否非法
			if (pFlag) {
				pErrPos = 2;
				docType = request.getParameter("type");
				if (docType == null || docType.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("docType丢失！");
				}
			}

			if ("DJ".equals(docType)) {
				reslutStr = "/GWGL/officialArchivesRegManage.jsp";
				System.out.println("公文登记");
			} else if ("GD".equals(docType)) {
				reslutStr = "/GWGL/officialArachivesFileManage.jsp";
				System.out.println("公文归档");
			}

			// 判断是否有可用于著录查询的数据项
			if (pFlag) {
				dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForInputQuery();
				pErrPos = 3;
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("此分类下没有可用著录查询的数据项！");
				}
			}
			// 后去当前用户所在部门的信息
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("登陆用户非法为空!");
				}
			}

			if (pFlag) {
				if (userInfo.getDepartmentID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("部门编号非法为空!");
				} else {
					FormationDepartmentID = userInfo.getDepartmentID();
				}
			}

			// 得到html代码
			if (pFlag) {
				pErrPos = 4;
				htmlCode = GenerateHtmlCodeUtil.GenerateOfficialHtmlCode(dataItems, null);
			}
			request.setAttribute("FormationDepartmentID", FormationDepartmentID);
			request.setAttribute("dataItems", SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("docType", docType);

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
					logger.error(pErrInfo.getContent());
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					htmlCode = "<script>alert(\"" + pErrInfo.toShortString() + "!\");</script>";
					request.setAttribute("htmlCode", htmlCode);
				}
			}
			throwable = null;
		}

		return SUCCESS;
	}

	/**
	 * 根据页面条件查询已著录完成的档案信息<br/>
	 * 适用功能档案管理
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findOfficialArchivesInfosByCondition() throws Exception {
		System.out.println("--查询著录完成的公文档案信息！---------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String type;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<OfficialArchivesInfo> officialArchivesInfos = null;
		Map<String, OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions = null;
		Map<String, ArchivesTypeDataItem> dataItems = null;
		Map<String, ArchivesTypeDataItem> dataItemsForAll = null;

		try {
			// 获得页面参数
			pErrPos = 1;
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));

			type = request.getParameter("type");

			OfficialArchivesType officialArchivesType = new OfficialArchivesType(officialArchivesTypeID);

			dataPageInfo.setPageSize(10);

			pErrPos = 2;
			officialArchivesInfoQueryConditions = new TreeMap<String, OfficialArchivesInfoQueryCondition>();
			OfficialArchivesInfoQueryCondition officialArchivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// 取出所有的页面传入的条件的名字数组
			String[] values = null;// 保存某个数据项里面的vlue值
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;
			String FormationDepartmentID = null;

			FormationDepartmentID = request.getParameter("FormationDepartmentID");
			if (pFlag) {
				if (FormationDepartmentID == null || FormationDepartmentID.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("部门编号非法为空！");
				}
			}
			// 从系统数据中取出可用于著录查询的著录项
			dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForInputQuery();
			dataItemsForAll = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			// 开始构造查询条件集合
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// 取出条件的名字
				if (!"officialArchivesTypeID".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// 取出条件所对应的值
					// 该值为dataItem的Id

					dataItem = dataItems.get(parameterName);// 取得与表单项对应的dataItem

					if (dataItem != null && values.length > 1) {// 两个值

						if (values[0] != "" && values[1] != "") {// 都不为空
							// 把值放入archivesInfoQueryCondition
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							officialArchivesInfoQueryCondition.setMinValue(values[0]);
							officialArchivesInfoQueryCondition.setMaxValue(values[1]);
							officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						} else if (values[0] == "" && values[1] == "") {// 都为空不做处理
							System.out.println("都为空不做处理");

						} else {// 有一为空
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
							if (values[0] == "") {
								officialArchivesInfoQueryCondition.setValue(values[1]);
							} else {
								officialArchivesInfoQueryCondition.setValue(values[0]);
							}
							officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
						}

					} else if (dataItem != null && values[0] != "") {// 只有一个值
						officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
						officialArchivesInfoQueryCondition.setValue(values[0]);
						officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition的集合
					}

				}
			}

			// 添加固定档案形成部门查询条件
			if (pFlag) {
				dataItem = dataItemsForAll.get(EnumSystemDataItem.档案形成部门编号.getEnumValue());
				officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// 构造archivesInfoQueryCondition对象
				officialArchivesInfoQueryCondition.setValue(FormationDepartmentID);
				officialArchivesInfoQueryConditions.put(EnumSystemDataItem.档案形成部门编号.getEnumValue(), officialArchivesInfoQueryCondition);// 构造archivesInfoQueryCondition对象
			}

			// 调用业务逻辑
			pErrPos = 3;
			officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			if (officialArchivesInfoManageService.findOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID),
					new ArrayList<OfficialArchivesInfoQueryCondition>(officialArchivesInfoQueryConditions.values()), dataPageInfo, officialArchivesInfos, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "根据条件查询档案信息失败：");
			}

			request.setAttribute("dataItems", SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("officialArchivesInfos", officialArchivesInfos);
			request.setAttribute("FormationDepartmentID", FormationDepartmentID);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, officialArchivesInfoQueryConditions));
			if (type.equals("DJ")) {
				reslutStr = "toDJ";
			} else if (type.equals("GD")) {
				reslutStr = "toGD";
			} else {
				reslutStr = ERROR;
			}

		} catch (Exception e) {
			// 异常错误
			e.printStackTrace();
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
					logger.error(pErrInfo.getContent());
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\"" + pErrInfo.toShortString() + "!\");</script>");
				}
			}
			// 销毁局部变量
			throwable = null;
		}
		return reslutStr;
	}

	/**
	 * 批量删除档案著录信息<br/>
	 * ajax调用
	 */
	public String deleteOfficialArchivesInfos() throws Exception {
		System.out.println("--删除信息----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息

		HttpServletRequest request = ServletActionContext.getRequest();
		OfficialArchivesType officialArchivesType = null;
		List<OfficialArchivesInfo> officialArchivesInfos = null;
		OfficialArchivesInfo officialArchivesInfo = null;
		int officialArchivesTypeID = 0;

		try {
			// 获得页面参数
			pErrPos = 1;
			try {
				officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			} catch (Exception e) {
				pErrInfo.getContent().append("获取参数失败！");
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXHS == null || NBXHS.length <= 0) {
					pErrInfo.getContent().append("请选择要删除的档案信息！");
					pFlag = false;
				}
			}

			// 构造业务逻辑所需参数
			if (pFlag) {
				pErrPos = 3;

				// 构造archivesType
				officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);

				// 构造archivesInfos集合
				officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
				for (int nbxh : NBXHS) {
					officialArchivesInfo = new OfficialArchivesInfo(officialArchivesType);
					officialArchivesInfo.setNBXH(nbxh);
					officialArchivesInfos.add(officialArchivesInfo);
				}
			}

			// 调用业务逻辑删除档案信息
			if (pFlag) {
				pErrPos = 4;
				if (officialArchivesInfoManageService.deleteOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID),
						officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("删除档案信息失败");
				}
			}

			// 调用成功
			if (pFlag) {
				pErrPos = 5;
				HttpServletResponse response = ServletActionContext.getResponse();
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

				ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
			}
			// 销毁局部变量
			throwable = null;
		}
		return null;
	}

	/**
	 * 打开公文档案信息归档页面
	 * 
	 * @return 归档页面
	 * @throws Exception
	 *             异常
	 */
	public String archivingOfficialArchivesInfos() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			List<ArchivesFonds> archivesFonds = null;
			List<ArchivesType> archivesTypes = null;
			int NBXH = Integer.parseInt(request.getParameter("NBXH"));
			// 开始处理 1...
			pErrPos = 1;
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案编号非法为空！");
			}
			if (NBXH == 0) {
				pFlag = false;
				pErrInfo.getContent().append("内部序号非法为空！");
			}
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer, OfficialArchivesTypeSavedMapping>();
				if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取公文档案分类的归档映射关系失败！");
				} else {
					archivesTypes = new ArrayList<ArchivesType>();
					for (OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping : officialArchivesTypeSavedMappings.values()) {
						int archivesTypeID = officialArchivesTypeSavedMapping.getArchivesTypeID();
						ArchivesType archivesType = SystemInitializer.getInstance().getArchivesTypes().get(archivesTypeID);
						archivesTypes.add(archivesType);
					}
				}
			}
			if (pFlag) {
				archivesFonds = SystemInitializer.getInstance().getArchivesFondss();
				if (archivesFonds == null) {
					pErrPos = 3;
					pFlag = false;
					pErrInfo.getContent().append("获取全宗编号失败！");
				}
			}
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("archivesTypes", archivesTypes);
			request.setAttribute("NBXH", NBXH);
			request.setAttribute("archivesFonds", archivesFonds);

		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
					ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return "success";
	}

	/**
	 * 打开公文档案信息归档页面
	 * 
	 * @return 归档页面
	 * @throws Exception
	 *             异常
	 */
	public String archivingBatchOfficialArchivesInfos() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			List<ArchivesFonds> archivesFonds = null;
			List<ArchivesType> archivesTypes = null;
			// 开始处理 1...
			pErrPos = 1;
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("公文档案编号非法为空！");
			}
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer, OfficialArchivesTypeSavedMapping>();
				if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取公文档案分类的归档映射关系失败！");
				} else {
					archivesTypes = new ArrayList<ArchivesType>();
					for (OfficialArchivesTypeSavedMapping officialArchivesTypeSavedMapping : officialArchivesTypeSavedMappings.values()) {
						int archivesTypeID = officialArchivesTypeSavedMapping.getArchivesTypeID();
						ArchivesType archivesType = SystemInitializer.getInstance().getArchivesTypes().get(archivesTypeID);
						archivesTypes.add(archivesType);
					}
				}
			}
			if (pFlag) {
				archivesFonds = new ArrayList<ArchivesFonds>();
				archivesFonds = SystemInitializer.getInstance().getArchivesFondss();
				if (archivesFonds == null) {
					pErrPos = 3;
					pFlag = false;
					pErrInfo.getContent().append("获取全宗编号失败！");
				}
			}
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("archivesTypes", archivesTypes);
			request.setAttribute("archivesFonds", archivesFonds);

		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
					ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// 销毁局部变量
			throwable = null;
		}
		return "success";
	}

	/**
	 * 打开打印字段学则界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String printPage() throws Exception {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息
		try {
			// 开始处理 1...
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			request.setAttribute("dataItems", dataItems);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;

			}
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
			}

			// 销毁局部变量
			throwable = null;
		}

		return SUCCESS;
	}

	/**
	 * 打印界面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String print() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // 错误信息
		try {
			// 开始处理 1...
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			String[] columnName = request.getParameterValues("selectFlag");
			if (pFlag) {
				if (columnName == null) {
					pFlag = false;
					pErrInfo.getContent().append("选择的打印项伟空！");
				}

			}
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeID非法为空!");
			}
			List<OfficialArchivesInfo> officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
			// 开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoManageService.findAll(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					System.out.println("失败");
				} else {
					System.out.println("成功");
				}
			}
			Map<String, String> colName = new HashMap<String, String>();
			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			for (int i = 0; i < columnName.length; i++) {
				if (dataItems.containsKey(columnName[i])) {
					colName.put(columnName[i], dataItems.get(columnName[i]).getDisplayText());
				}
			}
			request.setAttribute("colName", colName);
			request.setAttribute("officialArchivesInfos", officialArchivesInfos);
		} catch (Exception e) {
			// 异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
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
			}

			// 销毁局部变量
			throwable = null;
		}

		return "success";

	}

}
