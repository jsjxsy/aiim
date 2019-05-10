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
 * ��������-�ļ����� ������(Action)
 * 
 * @author
 */
public class ArchivesInfoManageAction extends BaseAction {
	
	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(ArchivesInfoManageAction.class);

	@Autowired
	private IArchivesInfoWorkingManageService archivesInfoWorkingManageService; // �ļ�����ҵ����

	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
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
	 * ��ȡ����������<br/> 
	 * �ļ�����
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
	 * ��ȡ����������<br/> 
	 * ��¼��˵���������
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getArchivesTypeTreeForZLSH() throws Exception {
		System.out.println("-----�õ�����������------------------------");
		
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
	 * ��ѯ���ύ����ĵ�����Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return 
	 */
	public String getArchivesInfosForZLSH() throws Exception{
		System.out.println("-------�����ύ����ĵ�����Ϣ--------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		System.out.println("archivesTypeID�� "+getParameterForInt("archivesTypeID"));
		
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
					pErrInfo.getContent().insert(0,"��ѯ����¼�ĵ�����Ϣʧ��");
				}
			}
			
			reslutStr = "/DAGL/ZLSH.jsp";
			setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			setAttribute("archivesInfos", archivesInfos);
			setAttribute("archivesTypeID", archivesTypeID);
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ��ȡ������Ϣ������ <br/> 
	 * �������
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
	 * ���ݷ���õ�������¼ҳ���ѯ����HTML����
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
			// pErrInfo.getContent().append("archivesTypeID��ʧ��");
			// }
			//			
			// if (pFlag) {
			// pErrPos = 2;
			// if(request.getParameter("fileType") == null){
			// pFlag = false;
			// pErrInfo.getContent().append("fileType��ʧ��");
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

			// �ж��Ƿ��п�������¼��ѯ��������
			if (pFlag) {
				pErrPos = 4;
				dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForInputQuery();
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("�˷�����û�п�����¼��ѯ�������");
				}
			}

			// �õ�html����
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
					pErrInfo.getContent().insert(0,"ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����ʧ��");
				}else{
					request.setAttribute("sum", archivesInfosSum.getValue());
				}
			}
			
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("archivesTypeID", archivesTypeID);
			request.setAttribute("fileType", fileType);

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
	 * ����ҳ��������ѯ����¼��ɵĵ�����Ϣ<br/> 
	 * ���ù��ܵ�������
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByCondition() throws Exception {
		System.out.println("--��ѯ��¼��ɵĵ�����Ϣ��---------------------------");
		
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
			//���ҳ�����
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

			Enumeration enumeration = request.getParameterNames();// ȡ�����е�ҳ�洫�����������������
			String[] values = null;// ����ĳ�������������vlueֵ
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//��ϵͳ������ȡ����������¼��ѯ����¼��
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForInputQuery();

			
			//��ʼ�����ѯ��������
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// ȡ������������
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// ȡ����������Ӧ��ֵ
					// ��ֵΪdataItem��Id

					dataItem = dataItems.get(parameterName);// ȡ��������Ӧ��dataItem
					
					if (dataItem != null && values.length > 1) {//����ֵ
						
						if (values[0] != "" && values[1] != "") {//����Ϊ��
							// ��ֵ����archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						} else if(values[0] == "" && values[1] == ""){//��Ϊ�ղ�������
							

						}else{//��һΪ��
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}
						
					}else if(dataItem != null && values[0] != ""){//ֻ��һ��ֵ
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
					}
				}
			}

			// ����ҵ���߼�
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(fileType),
																		EnumWorkFlowStatus.��¼���, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
			}

			IntegerEx archivesInfosSum = null;
			if (pFlag) {
				pErrPos = 4;
				archivesInfosSum = new IntegerEx();
				if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,false, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����ʧ��");
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// ���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}

	/**
	 * ����ɾ��������¼��Ϣ<br/>
	 * ajax����
	 */
	public String deleteArchivesInfos() throws Exception {
		System.out.println("--ɾ��������Ϣ----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ

		HttpServletRequest request = this.getRequest();
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		ArchivesInfo archivesInfo = null;
		int fileType = 0;
		int archivesTypeID = 0;
		
		try {
			//���ҳ�����
			pErrPos = 1;
			try {
				fileType = Integer.parseInt(request.getParameter("fileType"));
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			} catch (Exception e) {
				pErrInfo.getContent().append("��ȡ����ʧ�ܣ�");
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXHS == null || NBXHS.length<=0) {
					pErrInfo.getContent().append("��ѡ��Ҫɾ���ĵ�����Ϣ��");
					pFlag = false;
				}
			}

			//����ҵ���߼��������
			if (pFlag) {
				pErrPos = 3;

				// ����archivesType
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

				// ����archivesInfos����
				archivesInfos = new ArrayList<ArchivesInfo>();
				for (int nbxh : NBXHS) {
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(nbxh);
					archivesInfos.add(archivesInfo);
				}
			}

			// ����ҵ���߼�ɾ��������Ϣ
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoWorkingManageService.deleteArchivesInfos(archivesType, EnumArchivesInfoType.getEnumElement(fileType), archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().append("ɾ��������Ϣʧ��");
				}
			}

			// ���óɹ�
			if (pFlag) {
				pErrPos = 5;
				HttpServletResponse response = this.getResponse();
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
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			// ���پֲ�����
			throwable = null;
		}
		return null;
	}

	/**
	 * �����ύ�����¼��Ϣ<br/>
	 * ajax����
	 * @return
	 * @throws Exception
	 */
	public String submitToInputCheck() throws Exception {
		System.out.println("--�ύ����----------------------------");

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ

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

			//ȡ��ҳ�����
			pErrPos = 2;
			try {
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			} catch (Exception e) {
				pFlag = false;
				pErrInfo.getContent().append("��ò���ʧ�ܣ�");
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
					pErrInfo.getContent().append("��ѡ��Ҫ�ύ����ĵ�����");
				}
			}

			//����archivesType��archivesInfos����
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

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 5;
				if(archivesInfoWorkingManageService.submitToInputCheck(userId, archivesType,enumArchivesInfoType , archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�ύ����ʧ��");
				}
			}

			//���óɹ�
			if (pFlag) {
				pErrPos = 6;
				ServletActionContext.getResponse().getWriter().print("�ύ�ɹ���");
			}

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
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}

			// ���پֲ�����
			throwable = null;
		}
		return null;
	}

	/**
	 * �������<br/>
	 * ajax����
	 * @return
	 * @throws Exception
	 */
	public String brokeArchivesInfo() throws Exception {
		System.out.println("-�������----------------------------");
		boolean pFlag = true;
		pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo(); // ������Ϣ

		HttpServletRequest request = this.getRequest();
		ArchivesType archivesType = null;
		List<ArchivesInfo> archivesInfos = null;
		ArchivesInfo archivesInfo = null;
		int archivesTypeID = 0;
		UserInfo userInfo = null;
		try {
			pErrPos = 1;
			 userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
			
			//���ҳ�����
			if (request.getParameter("archivesTypeID")==null || request.getParameter("archivesTypeID")==""){
				pFlag = false;
				pErrInfo.getContent().append("��ò���archivesTypeIDʧ�ܣ�");
			}
			else{
				archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			}

			if (pFlag) {
				pErrPos = 3;
				if (NBXHS == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ���ĵ�����");
				}
			}

			//����ҵ���߼��������
			if (pFlag) {
				pErrPos = 4;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

				archivesInfos = new ArrayList<ArchivesInfo>();
				for (int nbxh : NBXHS) {
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID,nbxh);
					archivesInfos.add(archivesInfo);
				}
			}

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 5;
				if (archivesInfoWorkingManageService.brokeArchivesInfo(archivesType, archivesInfos,userInfo.getUserID(), pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ʧ��: ");
				}
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
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
					print(pErrInfo.getContent().toString());
				} else {
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.getContent());
			}
			// ���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * �鵵��Ϣ���
	 * @return
	 * @throws Exception
	 */
	public String savedCallBack()throws Exception{
		System.out.println("--------------------------�鵵��Ϣ��أ�-----------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		ArchivesType archivesType = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");
			int archivesTypeID = Integer.parseInt(this.getRequest().getParameter("archivesTypeId"));
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				if (archivesInfoSavedManageService.savedCallBack(NBXHS,userInfo,archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ص�����Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.print("�ɹ���");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * ��ù鵵��Ϣ�޸���
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
	 * ���ݷ���õ������޸�ҳ���ѯ����HTML����
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

			// �ж��Ƿ��п�������¼��ѯ��������
			if (pFlag) {
				pErrPos = 4;
				dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForUseQuery();
				if (dataItems == null) {
					pFlag = false;
					pErrInfo.getContent().append("�˷�����û�п�����¼��ѯ�������");
				}
			}

			// �õ�html����
			if (pFlag) {
				pErrPos = 5;
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);
			}
			reslutStr = "/DAGL/savedArchivesEdit.jsp";
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
			request.setAttribute("htmlCode", htmlCode);
			request.setAttribute("archivesTypeID", archivesTypeID);
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
	 * ����ҳ��������ѯ����¼��ɵĵ�����Ϣ<br/> 
	 * ���ù��ܵ�������
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByConditionForEdit() throws Exception {
		System.out.println("--��ѯ��¼��ɵĵ�����Ϣ��---------------------------");
		
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
			//���ҳ�����
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

			Enumeration enumeration = request.getParameterNames();// ȡ�����е�ҳ�洫�����������������
			String[] values = null;// ����ĳ�������������vlueֵ
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//��ϵͳ������ȡ����������¼��ѯ����¼��
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();

			
			//��ʼ�����ѯ��������
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// ȡ������������
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// ȡ����������Ӧ��ֵ
					// ��ֵΪdataItem��Id

					dataItem = dataItems.get(parameterName);// ȡ��������Ӧ��dataItem
					
					if (dataItem != null && values.length > 1) {//����ֵ
						
						if (values[0] != "" && values[1] != "") {//����Ϊ��
							// ��ֵ����archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						} else if(values[0] == "" && values[1] == ""){//��Ϊ�ղ�������
							

						}else{//��һΪ��
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}
						
					}else if(dataItem != null && values[0] != ""){//ֻ��һ��ֵ
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
					}
				}
			}

			// ����ҵ���߼�
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(-1),
																		EnumWorkFlowStatus.�鵵��Ϣ����޸�, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
			}
			reslutStr = "/DAGL/savedArchivesEdit.jsp";
			request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
			request.setAttribute("archivesInfos", archivesInfos);
			request.setAttribute("archivesTypeID", archivesTypeId);
			request.setAttribute("htmlCode", GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions));
		} catch (Exception e) {
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\"" + pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// ���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * �����鵵
	 */
	public String placeOnArchives() throws Exception{
		System.out.println("------------�鵵------------------------");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			int archivesTypeID = Integer.parseInt(this.getRequest().getParameter("archivesTypeID"));
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			
			System.out.println("archivesTypeID: " + archivesTypeID);
			System.out.println("NBXHS����ĳ��ȣ� " + NBXHS.length);
			//����ҵ���߼��鵵
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoSavedManageService.saveArchivesInfos(NBXHS[0],archivesType,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ� ");
					System.out.println(pErrInfo.toString());
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * �õ�Ҫ���İ�����Ϣ<br/> 
	 * ���ù��ܵ�������
	 * @param
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String findArchivesInfosByConditionForChaJuan() throws Exception {
		System.out.println("--��ѯ��¼��ɵĵ�����Ϣ��---------------------------");
		
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
			//���ҳ�����
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

			Enumeration enumeration = request.getParameterNames();// ȡ�����е�ҳ�洫�����������������
			String[] values = null;// ����ĳ�������������vlueֵ
			ArchivesTypeDataItem dataItem = null;
			String parameterName = null;

			//��ϵͳ������ȡ����������¼��ѯ����¼��
			dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForInputQuery();

			
			//��ʼ�����ѯ��������
			while (enumeration.hasMoreElements()) {
				parameterName = (String) enumeration.nextElement();// ȡ������������
				if (!"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)) {
					values = request.getParameterValues(parameterName);// ȡ����������Ӧ��ֵ
					// ��ֵΪdataItem��Id

					dataItem = dataItems.get(parameterName);// ȡ��������Ӧ��dataItem
					
					if (dataItem != null && values.length > 1) {//����ֵ
						
						if (values[0] != "" && values[1] != "") {//����Ϊ��
							// ��ֵ����archivesInfoQueryCondition
						    archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							archivesInfoQueryCondition.setMinValue(values[0]);
							archivesInfoQueryCondition.setMaxValue(values[1]);
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						} else if(values[0] == "" && values[1] == ""){//��Ϊ�ղ�������
							

						}else{//��һΪ��
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
							if(values[0] == ""){
								archivesInfoQueryCondition.setValue(values[1]);
							}else{
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
						}
						
					}else if(dataItem != null && values[0] != ""){//ֻ��һ��ֵ
						archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);// ����archivesInfoQueryCondition����
						archivesInfoQueryCondition.setValue(values[0]);
						archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);// ����archivesInfoQueryCondition�ļ���
					}
				}
			}

			// ����ҵ���߼�
			pErrPos = 3;
			archivesInfos = new ArrayList<ArchivesInfo>();
			if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(1),
																		EnumWorkFlowStatus.��¼���, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos,
																		pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
			}

			if (pFlag) {
				pErrPos = 4;
				archivesInfos1 = new ArrayList<ArchivesInfo>();
				if(archivesInfoWorkingManageService.findArchivesInfos( userIds, 
																		archivesType,  
																		EnumArchivesInfoType.getEnumElement(1),
																		EnumWorkFlowStatus.�鵵��Ϣ����޸�, 
																		new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), 
																		dataPageInfo, 
																		archivesInfos1,
																		pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����������ѯ������Ϣʧ�ܣ�");
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
					request.setAttribute("pErrInfo", pErrInfo);
					reslutStr = "/error.jsp";
				} else {
					request.setAttribute("htmlCode", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			// ���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���
	 */
	public String insertFileToArchives()throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = this.getRequest();
		try {
			//���ҳ�����
			pErrPos = 1;
			int parentNBXH = Integer.parseInt(request.getParameter("parentNBXH"));
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			UserInfo userInfo = (UserInfo)this.getSession(false).getAttribute("userInfo");

			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			//����ҵ���߼����
			if (pFlag) {
				if(archivesInfoWorkingManageService.insertFileToArchives(userInfo.getUserID(),archivesType,parentNBXH,NBXHS,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ʧ��: ");
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
			//���پֲ�����
			throwable = null;
		}
		return null;
	}
}
