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
	private IOfficialArchivesInfoManageService officialArchivesInfoManageService; // ���ĵ�������ҵ����
	
	/**
	 * ���ص�json��
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
	 * ��ҳ����
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * ����ĵ�ģ�������
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
	 * ��������ģ��
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
	 * ɾ��ģ�� Ajax����
	 * @return
	 * @throws Exception
	 */
	public String delTemplates() throws Exception{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

        System.out.println("delete");
		response.getWriter().print("ɾ���ɹ���");
		return null;
	}

	/**
	 * ɾ��ģ�� Ajax����
	 * @return
	 * @throws Exception
	 */
	public String addTemplate() throws Exception{

		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
		System.out.println("add");
		response.getWriter().print("����ɹ���");
		return null;
	}

	/**
	 * ����շ�������� ��ָ��������
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
	 * ��ѯ�շ��Ĺ���
	 * @return
	 * @throws Exception
	 */
	public String findDoc() throws Exception{
		System.out.println("--------�����շ���---------------");
		String forWard = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		
		dataPageInfo.setPageSize(10);
		dataPageInfo.setRowCount(100);
		
		String type = request.getParameter("type");
	
		request.setAttribute("type", type);
		return forWard;
	}
	
	/**
	 * ����շ���
	 * @return
	 * @throws Exception
	 */
	public String addDoc() throws Exception{
		System.out.println("--------�����շ���---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
        System.out.println(request.getParameter("NBXH"));
		String type = request.getParameter("type");
		System.out.println(type);
		if("SW".equals(type)){
			System.out.println("����");
		}else if("FW".equals(type)){
			System.out.println("����");
		}
		//request.setAttribute("type", type);
		return null;
	}
	
	/**
	 * ɾ���շ���
	 * @return
	 * @throws Exception
	 */
	public String delDoc() throws Exception{
		System.out.println("--------ɾ���շ���---------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");

		String type = request.getParameter("type");
		if("SW".equals(type)){
			System.out.println("����");
		}else if("FW".equals(type)){
			System.out.println("����");
		}
		
		if(NBXHs != null){
			for (int NBSH : NBXHs) {
				System.out.println(NBSH);
			}	
		}
		
		response.getWriter().print("ɾ���ɹ���");
		request.setAttribute("type", type);
		return null;
	}
	
	
	/**
	 * ����ID�����շ���
	 * @return
	 * @throws Exception
	 */
	public String findDocBuyId() throws Exception{
		System.out.println("--------��NBXH�����շ���---------------");
		HttpServletRequest request = ServletActionContext.getRequest();

		String type = request.getParameter("type");
        String NBXH = request.getParameter("NBXH");
        
        System.out.println(type+"  "+NBXH);
        jsonResult = "{'NBXH':'"+NBXH+"'}";
		request.setAttribute("type", type);
		return SUCCESS;
	}

	/**
	 * �鵵
	 * @return
	 * @throws Exception
	 */
	public String archivingDoc() throws Exception{
		System.out.println("-------------���Ĺ鵵-----------------------");
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("utf-8");
        
		String type = request.getParameter("type");
		System.out.println(request.getParameter("archivesTypeId"));
		if("SW".equals(type)){
			System.out.println("����");
		}else if("FW".equals(type)){
			System.out.println("����");
		}
		
		if(NBXHs != null){
			for (int NBSH : NBXHs) {
				System.out.println(NBSH);
			}	
		}
		
		response.getWriter().print("�鵵�ɹ���");
		return null;
	}
	
	/**
	 * ����ҳ��������ѯ����¼��ɵĵ�����Ϣ<br/> 
	 * ���ù��ܵ�������
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
			//���ҳ�����
			pErrPos = 1;
			int officialArchivesTypeID = Integer.parseInt(request.getParameter("officialArchivesTypeID"));

			
			OfficialArchivesType officialArchivesType = new OfficialArchivesType(officialArchivesTypeID);

			dataPageInfo.setPageSize(10);

			pErrPos = 2;
			officialArchivesInfoQueryConditions = new TreeMap<String, OfficialArchivesInfoQueryCondition>();
			OfficialArchivesInfoQueryCondition officialArchivesInfoQueryCondition = null;

			Enumeration enumeration = request.getParameterNames();// ȡ�����е�ҳ�洫�����������������
			String[] values = null;// ����ĳ�������������vlueֵ
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;
			//��ϵͳ������ȡ����������¼��ѯ����¼��
			dataItems = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForUseQuery();
			dataItemsForAll=SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForAll();
			
			//��ʼ�����ѯ��������
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// ȡ������������
				if (!"officialArchivesTypeID".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// ȡ����������Ӧ��ֵ
					// ��ֵΪdataItem��Id
					dataItem = dataItems.get(parameterName);// ȡ��������Ӧ��dataItem
					if (dataItem != null && values.length > 1) {//����ֵ
						
						if (values[0] != "" && values[1] != "") {//����Ϊ��
							// ��ֵ����archivesInfoQueryCondition
						    officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							officialArchivesInfoQueryCondition.setMinValue(values[0]);
							officialArchivesInfoQueryCondition.setMaxValue(values[1]);
							officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}else{//��һΪ��
							officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							if(values[0] == ""){
								officialArchivesInfoQueryCondition.setValue(values[1]);
							}else{
								officialArchivesInfoQueryCondition.setValue(values[0]);
							}
							officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}
						
					}else if(dataItem != null && values[0] != ""){//ֻ��һ��ֵ
						officialArchivesInfoQueryCondition = new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
						officialArchivesInfoQueryCondition.setValue(values[0]);
						officialArchivesInfoQueryConditions.put(parameterName,officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
					}
				}
			}
			
			//�̶���ӵ����γɲ��ű�Ų�ѯ����
			String FormationDepartmentID = request.getParameter("FormationDepartmentID");
			if(FormationDepartmentID == null || FormationDepartmentID.equals("")){
				pErrInfo.getContent().append("�����γɲ��ű�ŷǷ�Ϊ�գ�");
			}
			
			dataItem=dataItemsForAll.get(EnumSystemDataItem.�����γɲ��ű��.getEnumValue());
			officialArchivesInfoQueryCondition=new OfficialArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
			officialArchivesInfoQueryCondition.setValue(FormationDepartmentID);
			officialArchivesInfoQueryConditions.put(EnumSystemDataItem.�����γɲ��ű��.getEnumValue(), officialArchivesInfoQueryCondition);// ����archivesInfoQueryCondition����
			//��ӵ��������ܼ���ѯ����
			dataItem=dataItemsForAll.get(EnumSystemDataItem.�����ܼ����.getEnumValue());
			officialArchivesInfoQueryCondition=new OfficialArchivesInfoQueryCondition(dataItem);
			String SecrecyID=String.valueOf(SystemInitializer.getInstance().getOpenArchivesSecrecy().getID());
			officialArchivesInfoQueryCondition.setValue(SecrecyID);
			officialArchivesInfoQueryConditions.put(EnumSystemDataItem.�����ܼ����.getEnumValue(), officialArchivesInfoQueryCondition);
			
			
			
			
			// ����ҵ���߼�
			pErrPos = 3;
			officialArchivesInfos = new ArrayList<OfficialArchivesInfo>();
			if (officialArchivesInfoManageService.findOfficialArchivesInfos(officialArchivesType, EnumOfficialArchivesInfoTableType.getEnumElement(officialArchivesTypeID), 
					new ArrayList<OfficialArchivesInfoQueryCondition>(officialArchivesInfoQueryConditions.values()), dataPageInfo, officialArchivesInfos, pErrInfo) == false)
			{
				pFlag = false;
				resultStr = "error";
				pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
			}

			request.setAttribute("dataItems", SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("FormationDepartmentID", FormationDepartmentID);
			request.setAttribute("officialArchivesInfos", officialArchivesInfos);
			request.setAttribute("officialArchivesTypeID", officialArchivesTypeID);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateOfficialArchivesHtmlCode(dataItems, officialArchivesInfoQueryConditions));
			resultStr="success";
		} catch (Exception e) {
			resultStr = "error";
			// �쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
					resultStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""
							+ pErrInfo.toShortString() + "!\");</script>");
				}
			}
			// ���پֲ�����
			throwable = null;
		}
		return resultStr;
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
		Integer departmentInfoID = null;
		String reslutStr="";
		String htmlCode = "";
		Map<String, ArchivesTypeDataItem> dataItems = null;
		try {
		    //�ж�officialArchivesTypeID�Ƿ�Ƿ�
			if(pFlag){
			 pErrPos = 1;
			 if(request.getParameter("officialArchivesTypeID") == null
			    &&request.getParameter("officialArchivesTypeID").equals("")){
			    pFlag = false;
			    pErrInfo.getContent().append("officialArchivesTypeID��ʧ��");
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
					 pErrInfo.getContent().append("FormationDepartmentID��ʧ��");
				  }else{
					  departmentInfoID = Integer.parseInt(request
								.getParameter("FormationDepartmentID"));
				  }
				}
		
			
			
			// �ж��Ƿ��п�������¼��ѯ��������
			if (pFlag) {
				dataItems = SystemInitializer.getInstance()
						.getOfficialArchivesTypes().get(officialArchivesTypeID)
						.getDataItemsForUseQuery();
				
				pErrPos = 3;
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("�˷�����û�п�����¼��ѯ�������");
				}
			}

			// �õ�html����
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
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
