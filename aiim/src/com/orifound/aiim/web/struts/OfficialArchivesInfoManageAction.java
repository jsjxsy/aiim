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
 * ���Ĺ���-�����ĵ����� ������(Action)
 * 
 * @author xsy
 */
public class OfficialArchivesInfoManageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(OfficialArchivesInfoManageAction.class);

	@Autowired
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService; // ���ĵ�������ҵ����
	@Autowired
	private IOfficialArchivesTypeSavedMappingManageService officialArchivesTypeSavedMappingManageService;// ���ĵ�������ӳ�����
	private DataPageInfo dataPageInfo = new DataPageInfo();// ��ҳʵ��BEAN

	private int[] NBXHS;// ����������Ҫ���յ��ڲ����

	private String reslutStr;// ���ؽ��

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
	 * ��ȡ���������<br/>
	 * ���ĵǼǺ͹��Ĺ鵵
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
	 * ��ȡ���Ĺ鵵��<br/>
	 * �ĵ�����
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
	 * ���ݷ���õ�������¼ҳ���ѯ����HTML����
	 * 
	 * @param
	 * @return string ��ת��ҳ��
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
			// �ж�officialArchivesTypeID�Ƿ�Ƿ�
			if (pFlag) {
				pErrPos = 1;
				if (request.getParameter("officialArchivesTypeID") == null && request.getParameter("officialArchivesTypeID").equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("officialArchivesTypeID��ʧ��");
				} else {
					officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
				}
			}

			// �ж�docType�Ƿ�Ƿ�
			if (pFlag) {
				pErrPos = 2;
				docType = request.getParameter("type");
				if (docType == null || docType.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("docType��ʧ��");
				}
			}

			if ("DJ".equals(docType)) {
				reslutStr = "/GWGL/officialArchivesRegManage.jsp";
				System.out.println("���ĵǼ�");
			} else if ("GD".equals(docType)) {
				reslutStr = "/GWGL/officialArachivesFileManage.jsp";
				System.out.println("���Ĺ鵵");
			}

			// �ж��Ƿ��п�������¼��ѯ��������
			if (pFlag) {
				dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForInputQuery();
				pErrPos = 3;
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("�˷�����û�п�����¼��ѯ�������");
				}
			}
			// ��ȥ��ǰ�û����ڲ��ŵ���Ϣ
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			if (pFlag) {
				if (userInfo == null) {
					pFlag = false;
					pErrInfo.getContent().append("��½�û��Ƿ�Ϊ��!");
				}
			}

			if (pFlag) {
				if (userInfo.getDepartmentID() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�ŷǷ�Ϊ��!");
				} else {
					FormationDepartmentID = userInfo.getDepartmentID();
				}
			}

			// �õ�html����
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
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
	 * ����ҳ��������ѯ����¼��ɵĵ�����Ϣ<br/>
	 * ���ù��ܵ�������
	 * 
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findOfficialArchivesInfosByCondition() throws Exception {
		System.out.println("--��ѯ��¼��ɵĹ��ĵ�����Ϣ��---------------------------");

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
			// ���ҳ�����
			pErrPos = 1;
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));

			type = request.getParameter("type");

			OfficialArchivesType officialArchivesType = new OfficialArchivesType(officialArchivesTypeID);

			dataPageInfo.setPageSize(10);

			pErrPos = 2;
			officialArchivesInfoQueryConditions = new TreeMap<String, OfficialArchivesInfoQueryCondition>();
			OfficialArchivesInfoQueryCondition officialArchivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// ȡ�����е�ҳ�洫�����������������
			String[] values = null;// ����ĳ�������������vlueֵ
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;
			String FormationDepartmentID = null;

			FormationDepartmentID = request.getParameter("FormationDepartmentID");
			if (pFlag) {
				if (FormationDepartmentID == null || FormationDepartmentID.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�ŷǷ�Ϊ�գ�");
				}
			}
			// ��ϵͳ������ȡ����������¼��ѯ����¼��
			dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForInputQuery();
			dataItemsForAll = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			// ��ʼ�����ѯ��������
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// ȡ������������
				if (!"officialArchivesTypeID".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// ȡ����������Ӧ��ֵ
					// ��ֵΪdataItem��Id

					dataItem = dataItems.get(parameterName);// ȡ��������Ӧ��dataItem

					if (dataItem != null && values.length > 1) {// ����ֵ

						if (values[0] != "" && values[1] != "") {// ����Ϊ��
							// ��ֵ����archivesInfoQueryCondition
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							officialArchivesInfoQueryCondition.setMinValue(values[0]);
							officialArchivesInfoQueryCondition.setMaxValue(values[1]);
							officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						} else if (values[0] == "" && values[1] == "") {// ��Ϊ�ղ�������
							System.out.println("��Ϊ�ղ�������");

						} else {// ��һΪ��
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							if (values[0] == "") {
								officialArchivesInfoQueryCondition.setValue(values[1]);
							} else {
								officialArchivesInfoQueryCondition.setValue(values[0]);
							}
							officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}

					} else if (dataItem != null && values[0] != "") {// ֻ��һ��ֵ
						officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
						officialArchivesInfoQueryCondition.setValue(values[0]);
						officialArchivesInfoQueryConditions.put(parameterName, officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
					}

				}
			}

			// ��ӹ̶������γɲ��Ų�ѯ����
			if (pFlag) {
				dataItem = dataItemsForAll.get(EnumSystemDataItem.�����γɲ��ű��.getEnumValue());
				officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
				officialArchivesInfoQueryCondition.setValue(FormationDepartmentID);
				officialArchivesInfoQueryConditions.put(EnumSystemDataItem.�����γɲ��ű��.getEnumValue(), officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition����
			}

			// ����ҵ���߼�
			pErrPos = 3;
			officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			if (officialArchivesInfoManageService.findOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID),
					new ArrayList<OfficialArchivesInfoQueryCondition>(officialArchivesInfoQueryConditions.values()), dataPageInfo, officialArchivesInfos, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
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
			// �쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			// ���پֲ�����
			throwable = null;
		}
		return reslutStr;
	}

	/**
	 * ����ɾ��������¼��Ϣ<br/>
	 * ajax����
	 */
	public String deleteOfficialArchivesInfos() throws Exception {
		System.out.println("--ɾ����Ϣ----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ

		HttpServletRequest request = ServletActionContext.getRequest();
		OfficialArchivesType officialArchivesType = null;
		List<OfficialArchivesInfo> officialArchivesInfos = null;
		OfficialArchivesInfo officialArchivesInfo = null;
		int officialArchivesTypeID = 0;

		try {
			// ���ҳ�����
			pErrPos = 1;
			try {
				officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			} catch (Exception e) {
				pErrInfo.getContent().append("��ȡ����ʧ�ܣ�");
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXHS == null || NBXHS.length <= 0) {
					pErrInfo.getContent().append("��ѡ��Ҫɾ���ĵ�����Ϣ��");
					pFlag = false;
				}
			}

			// ����ҵ���߼��������
			if (pFlag) {
				pErrPos = 3;

				// ����archivesType
				officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);

				// ����archivesInfos����
				officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
				for (int nbxh : NBXHS) {
					officialArchivesInfo = new OfficialArchivesInfo(officialArchivesType);
					officialArchivesInfo.setNBXH(nbxh);
					officialArchivesInfos.add(officialArchivesInfo);
				}
			}

			// ����ҵ���߼�ɾ��������Ϣ
			if (pFlag) {
				pErrPos = 4;
				if (officialArchivesInfoManageService.deleteOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID),
						officialArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("ɾ��������Ϣʧ��");
				}
			}

			// ���óɹ�
			if (pFlag) {
				pErrPos = 5;
				HttpServletResponse response = ServletActionContext.getResponse();
				response.getWriter().print("ɾ���ɹ���");
			}

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;

				ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
			}
			// ���پֲ�����
			throwable = null;
		}
		return null;
	}

	/**
	 * �򿪹��ĵ�����Ϣ�鵵ҳ��
	 * 
	 * @return �鵵ҳ��
	 * @throws Exception
	 *             �쳣
	 */
	public String archivingOfficialArchivesInfos() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			List<ArchivesFonds> archivesFonds = null;
			List<ArchivesType> archivesTypes = null;
			int NBXH = Integer.parseInt(request.getParameter("NBXH"));
			// ��ʼ���� 1...
			pErrPos = 1;
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����ŷǷ�Ϊ�գ�");
			}
			if (NBXH == 0) {
				pFlag = false;
				pErrInfo.getContent().append("�ڲ���ŷǷ�Ϊ�գ�");
			}
			// ��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer, OfficialArchivesTypeSavedMapping>();
				if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ���ĵ�������Ĺ鵵ӳ���ϵʧ�ܣ�");
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
					pErrInfo.getContent().append("��ȡȫ�ڱ��ʧ�ܣ�");
				}
			}
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("archivesTypes", archivesTypes);
			request.setAttribute("NBXH", NBXH);
			request.setAttribute("archivesFonds", archivesFonds);

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
					ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}
		return "success";
	}

	/**
	 * �򿪹��ĵ�����Ϣ�鵵ҳ��
	 * 
	 * @return �鵵ҳ��
	 * @throws Exception
	 *             �쳣
	 */
	public String archivingBatchOfficialArchivesInfos() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ
		try {

			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			List<ArchivesFonds> archivesFonds = null;
			List<ArchivesType> archivesTypes = null;
			// ��ʼ���� 1...
			pErrPos = 1;
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����ŷǷ�Ϊ�գ�");
			}
			// ��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				Map<Integer, OfficialArchivesTypeSavedMapping> officialArchivesTypeSavedMappings = new HashMap<Integer, OfficialArchivesTypeSavedMapping>();
				if (officialArchivesTypeSavedMappingManageService.findArchivesTypesByOfficialArchivesTypeID(officialArchivesTypeID, officialArchivesTypeSavedMappings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡ���ĵ�������Ĺ鵵ӳ���ϵʧ�ܣ�");
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
					pErrInfo.getContent().append("��ȡȫ�ڱ��ʧ�ܣ�");
				}
			}
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("archivesTypes", archivesTypes);
			request.setAttribute("archivesFonds", archivesFonds);

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
					ServletActionContext.getResponse().getWriter().print(pErrInfo.getContent().toString());
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}
		return "success";
	}

	/**
	 * �򿪴�ӡ�ֶ�ѧ�����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String printPage() throws Exception {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ
		try {
			// ��ʼ���� 1...
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			request.setAttribute("dataItems", dataItems);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			// ��ʼ����2...
			if (pFlag) {
				pErrPos = 2;

			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return SUCCESS;
	}

	/**
	 * ��ӡ����
	 * 
	 * @return
	 * @throws Exception
	 */
	public String print() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ
		try {
			// ��ʼ���� 1...
			pErrPos = 1;
			HttpServletRequest request = ServletActionContext.getRequest();
			String[] columnName = request.getParameterValues("selectFlag");
			if (pFlag) {
				if (columnName == null) {
					pFlag = false;
					pErrInfo.getContent().append("ѡ��Ĵ�ӡ��ΰ�գ�");
				}

			}
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));
			if (officialArchivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("officialArchivesTypeID�Ƿ�Ϊ��!");
			}
			List<OfficialArchivesInfo> officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
			// ��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoManageService.findAll(officialArchivesType, officialArchivesInfos, pErrInfo) == false) {
					System.out.println("ʧ��");
				} else {
					System.out.println("�ɹ�");
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
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}

		return "success";

	}

}
