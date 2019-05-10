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
 * �������������ࣨAction��
 * @author tyb
 *
 */
public class ArchiveAppraisalAction extends ActionSupport {
	
	private int pageSize = 3;

	private static final long serialVersionUID = 2611010082743690512L;
	
	static Log logger = LogFactory.getLog(ArchiveAppraisalAction.class);
	
	/**
	 * ע�� �����鵵��Ϣ�Ĺ���������
	 */
	@Autowired
	private IArchivesInfoSavedManageService archivesInfoSavedManageService;
	
	/**
	 * ע�벿����Ϣ����������
	 */
	@Autowired
	private IDepartmentInfoManageService departmentInfoManageService;
	
	/**
	 * ע�뱣�����޹���������
	 */
	@Autowired
	private IRetentionPeriodManageService retentionPeriodManageService;
	
	/**
	 * ע���ټ�����ϸ�������������
	 */
	@Autowired
	private IAppraisalKeepDestroyDetailManageService appraisalKeepDestroyDetailManageService;
	
	/**
	 * ע�밸����/���ż�����ϸ������ʵ�������������
	 */
	@Autowired
	private IAppraisalPublicDetailManageService appraisalPublicDetailManageService;
	
	/**
	 * ע�뵵�����ֿ��Ƽ�����ϸ������ʵ�������������
	 */
	@Autowired
	private IAppraisalUseScopesDetailManageService appraisalUseScopesDetailManageService;
	
	
	// ��ҳʵ��BEAN
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	/**
	 * ��������id
	 * 1����ټ����Ǽ�
	 * 2�����ż����Ǽ�
	 * 3�����������Ǽ�
	 * 4�����ؼ����Ǽ�
	 */
	private int switchRegisterId;
	
	/**
	 * �����Ǽ���Ϣ�� �˵��ڵ�ֵ
	 * 1����ټ�����ѯ
	 * 2�����ż�����ѯ
	 * 3������������ѯ
	 * 4�����ؼ�����ѯ
	 */
	private int nodeId;

	/**
	 * ����Action��תURL��ַ
	 */
	private String resultURL;
	
	/**
	 * ��������id
	 */
	private int archivesTypeId;
	
	/**
	 * �����γɲ���id
	 */
	private int formationDepartmentID;
	
	/**
	 * �����γɲ��ż���
	 */
	private List<DepartmentInfo> formationDepartments = new ArrayList<DepartmentInfo>();
	
	/**
	 * �������������ֵ��
	 */
	private List<RetentionPeriod> retentionPeriods = new ArrayList<RetentionPeriod>();
	
	/**
	 * ���е���һ��������Ϣ
	 */
	private List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
	
	/**
	 * ��������
	 */
	private String AppraisalDate;
	
	/**
	 * ��ֹ��������
	 */
	private String AppraisalDateEnd;
	
	/**
	 * ������
	 */
	private String AppraisalPersion;
	
	/**
	 * ��������
	 */
	private String AppraisalReason;
	
	/**
	 * �Ƿ񿪷�
	 */
	private String PublicFlag;
	
	/**
	 * ������ѯ  ����id
	 */
	private String archivesID;
	
	/**
	 * ������ѯ  ����
	 */
	private String title;
	
	/**
	 * ������Ϣid
	 */
	private int appraisalDetailId;
	
	
	/**
	 * �����ѯ:�õ�����������
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
			//��ȡ���е�������
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			
			
			//���������༯�Ϲ���������������javascript����
			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
			
			//������ͨ��request����ҳ��
			request.setAttribute("tree", tree);

			//���õ������Ͳ˵� ���ӵ�ַ
			StringBuffer proceseAction = new StringBuffer("archiveAppraisalAction_");
			
			//ѡ������Ǽ�����
			switch (switchRegisterId) {
			//��ټ����Ǽ�
			case 1:
				proceseAction.append("searchBeforesaveDestroyRegister").append(".action");
				break;
				
			//���ż����Ǽ�
			case 2:
				proceseAction.append("buildBeforePublicSearch").append(".action");
				break;
				
			//���������Ǽ�
			case 3:
				proceseAction.append("buildBeforeOpenSearch").append(".action");
				break;
				
			//���ؼ����Ǽ�	
			case 4:
				proceseAction.append("buildBeforeControlAreaSearch").append(".action");
				break;	
			//����Ĭ��Ϊ��ټ����Ǽ�	
			default:
				proceseAction.append("searchBeforesaveDestroyRegister").append(".action");
				break;
			}
			request.setAttribute("proceseAction", proceseAction.toString());
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
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
	 * ��ټ����Ǽ�ǰ��ѯָ���������͵Ĺ��ڵ���
	 * @return String
	 */
	public String searchBeforesaveDestroyRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//��ѯָ��������������й��ڵ���
			if (pFlag) {
				dataPageInfo.setPageSize(pageSize);
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ���ѯָ���������͵Ĺ��ڵ�����Ϣ ʧ�ܣ�");
				}
				HttpServletRequest request = ServletActionContext.getRequest();
				request.setAttribute("archivesInfos", archivesInfos);
			}
			
			//��ȡ�����γɲ��ż��ϡ����������ֵ��������ʾ��
			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ŵǼ�ǰ��ѯ������ҳ�����ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		resultURL = "/JDGL/saveDestroyRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ټ��� ��ӡ��ѯ
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}
			LinkedHashMap<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(nodeId).getDataItemsForListDisplay();
			
			//������Ҫ��ӡ��ʾ��������
			Map<String, ArchivesTypeDataItem> viewDataItems = new LinkedHashMap<String, ArchivesTypeDataItem>();
			//����ȫ�ں�
			ArchivesTypeDataItem dataItem = dataItems.get(EnumSystemDataItem.ȫ�ڱ��.getEnumValue());
			dataItem.setDisplayText("ȫ�ں�");
			dataItem.setInputEditBoxWidth(45);
			viewDataItems.put(EnumSystemDataItem.ȫ�ڱ��.getEnumValue(), dataItem);
			
			//���ӵ���
			dataItem = dataItems.get(EnumSystemDataItem.����.getEnumValue());
			dataItem.setInputEditBoxWidth(110);
			viewDataItems.put(EnumSystemDataItem.����.getEnumValue(), dataItem);
			
			//��������
			dataItem = dataItems.get(EnumSystemDataItem.����.getEnumValue());
			dataItem.setInputEditBoxWidth(150);
			dataItem.setKeyInCol("class='archivesinfoTitle'");
			viewDataItems.put(EnumSystemDataItem.����.getEnumValue(), dataItem);
			
			//���ӵ���ҳ��
			dataItem = dataItems.get(EnumSystemDataItem.����ҳ��.getEnumValue());
			dataItem.setDisplayText("ҳ��");
			dataItem.setInputEditBoxWidth(30);
			viewDataItems.put(EnumSystemDataItem.����ҳ��.getEnumValue(), dataItem);
			
			//���ӱ��������ı�
			dataItem = dataItems.get(EnumSystemDataItem.���������ı�.getEnumValue());
			dataItem.setDisplayText("��������");
			dataItem.setInputEditBoxWidth(60);
			viewDataItems.put(EnumSystemDataItem.���������ı�.getEnumValue(), dataItem);
			
			//���ӵ����ܼ��ı�
			dataItem = dataItems.get(EnumSystemDataItem.�����ܼ��ı�.getEnumValue());
			dataItem.setDisplayText("�ܼ�");
			dataItem.setInputEditBoxWidth(40);
			viewDataItems.put(EnumSystemDataItem.�����ܼ��ı�.getEnumValue(), dataItem);
			
			request.setAttribute("dataItems", viewDataItems);

			//��ѯָ��������������й��ڵ���
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
//				dataPageInfo.setCurrentPage(1);
//				dataPageInfo.setPageSize(12);
				//��ѯ���й��ڵ���
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, null, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ���ѯָ���������͵Ĺ��ڵ�����Ϣ ʧ�ܣ�");
				}
				ServletActionContext.getRequest().setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			e.printStackTrace();
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroyPrint.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ټ����Ǽ�
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//��ѯָ��������������й��ڵ���
			if (pFlag) {
				dataPageInfo.setPageSize(pageSize);
				
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ���ѯָ���������͵Ĺ��ڵ�����Ϣ ʧ�ܣ�");
				}
				
				String retentionPeriodId = null;
				String NBXH = null;
				Map<Integer[], Integer> saveArchives = new LinkedHashMap<Integer[], Integer>();
				Integer[] integers = new Integer[2];
				integers[0] = nodeId;
				List<Integer[]> destoryArchives = new ArrayList<Integer[]>();
				
				//���������ټ�����ϸ�����
				Map<Integer, Map<String, String>> batchArchives = new LinkedHashMap<Integer, Map<String,String>>();
				Map<String, String> batchArchivesInner = new HashMap<String, String>();
				
				//ѭ�� ��ȡҳ���ύ���ڲ�����Լ��������� ����
				for(ArchivesInfo info : archivesInfos) {
					retentionPeriodId = request.getParameter("retentionPeriod"+info.getNBXH());
				
					System.out.println("retentionPeriodId="+retentionPeriodId);
					
					NBXH = request.getParameter("NBXH"+info.getNBXH());
					
					//�ж��Ƿ����ñ�������
					if(StringTool.checkNull(retentionPeriodId)==false || StringTool.checkNull(NBXH)==false) {
						pFlag = false;
						pErrInfo.getContent().append("����ҳ��ı�������û��ȫ�����ã��ύʧ�ܡ�");
						break;
					} else {
						//���¹�������
						integers = new Integer[2];
						integers[0] = nodeId;
						integers[1] = info.getNBXH();
						
						//�ж��Ƿ�Ϊ���ٵ���
						if(retentionPeriodId.equals("0")) {	//���ٵ���
							destoryArchives.add(integers);
							batchArchivesInner.put("AppraisalDeletedFlag", "1");//���ٱ�־
						} else {
							saveArchives.put(integers, Integer.valueOf(retentionPeriodId));
							batchArchivesInner.put("AppraisalDeletedFlag", "0");//���ٱ�־
						}
						//�µı�������id
						batchArchivesInner.put("NewRetentionPeriodID", retentionPeriodId);
						
						//����һ�������ϸ��¼����
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
					
//					@param batchArchives ����������Ϣ �����ڲ���š�ֵMap���ϣ��Ƿ�����AppraisalDeletedFlag���±�������ID(NewRetentionPeriodID)
//					@param opinion �������� ���ݲ�����	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
					//������������
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//�������µ����Ĵ����Ϣ
					if (pFlag) {
						if (archivesInfoSavedManageService.updateBatchRetentionPeriod(userInfo, archivesType, saveArchives, destoryArchives, batchArchives, opinion, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "Action�������µ�����Ϣ�ı������� ʧ�ܣ�");
						}
					}
				}
				
			}
			
			
			//��ټ�����ɺ� ��ҳ��ѯ��һҳ
			if (pFlag) {
				dataPageInfo.setCurrentPage(1);
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				if (archivesInfoSavedManageService.queryClassifiedForSaveDestroyAppraisal(archivesType, formationDepartmentID, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ���ѯָ���������͵Ĺ��ڵ�����Ϣ ʧ�ܣ�");
				}
				
				request.setAttribute("archivesInfos", archivesInfos);
			}
			
			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ŵǼ�ǰ��ѯ������ҳ�����ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/saveDestroyRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ż����Ǽǲ�ѯǰ������ҳ�����
	 * @return String
	 */
	public String buildBeforePublicSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ŵǼ�ǰ��ѯ������ҳ�����ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����ҳ�����
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
			
			
			//������� һ�����
			filterParams.add("NBXH[0-9]*");
			filterParams.add("openFlag[0-9]*");
			
			if (buildArchivesInfoQueryConditions(nodeId, filterParams, archivesInfoQueryConditions , pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "����ָ���������͵�����ѯ���������༯�� ʧ�ܣ�");
			}
			
			//���ݵ������ͱ�����ã�ҳ���ѯ���򡢷�ҳ��ʾ�����
			if (pFlag) {
				pErrPos = 2;
				if (this.setHtmlCodeByArchivesTypeId(nodeId, archivesInfoQueryConditions) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ݵ������ͱ������ҳ����� ʧ�ܡ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ���ż����Ǽ�ǰ ��ѯ������Ϣ
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			
			//����ҳ�����
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ҳ���������");
				}
			}
			
			//����ָ���������͵�����ѯ���������༯��
			if (pFlag) {
				UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
				//����û���Ϣ�Ƿ�Ϊ��
				if (pFlag) {
					if (userInfo==null || userInfo.getUserID()<=0) {
						pFlag = false;
						pErrInfo.getContent().append("session�û���Ϣ�Ƿ�Ϊ�ա�");
					}
				}
				
				//��������->���ż�������ѯָ���������͵ĵ�����Ϣ
				if (pFlag) {
					ArchivesType archivesType = new ArchivesType();
					archivesType.setID(nodeId);
					dataPageInfo.setPageSize(pageSize);
					List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
					
					//��ҳ��ѯָ���������͵ĵ�����Ϣ
					if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action ��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
					}
					request.setAttribute("archivesInfos", archivesInfos);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ż����Ǽ�
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//����ҳ�����
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ҳ���������");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//����û���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				pErrPos = 4;
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag) {
				pErrPos = 5;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��ҳ��ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				
				pErrPos = 6;
				//��Ҫ���в����ĵ���
				List<Integer> archivesNBXHs = new ArrayList<Integer>();
				String NBXH = "";
				String openFlag = "";
				if (pFlag && archivesInfos.size()>=1) {
					for(ArchivesInfo archivesInfo : archivesInfos) {
						NBXH = request.getParameter("NBXH"+archivesInfo.getNBXH());
						openFlag = request.getParameter("openFlag"+archivesInfo.getNBXH());
						
						//����ȡ�����Ų���
						if ("1".equals(PublicFlag) && !StringTool.checkNull(openFlag)) {	
							archivesNBXHs.add(Integer.valueOf(NBXH));
						} 
						
						//���п��Ų���
						if ("0".equals(PublicFlag) && StringTool.checkNull(openFlag) && openFlag.equals("on")) { 
							archivesNBXHs.add(Integer.valueOf(NBXH));
						}
					}
				}
				
				//ִ�п��ż����Ǽǲ���
				if (pFlag && archivesNBXHs.size() >= 1) {
					pErrPos = 7;
					//��¼���ż�����ϸ
					Map<String, String> opinion = new HashMap<String, String>();
					
//						@param opinion �������� ���ݲ�����	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
					//������������
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//�޸ĵ������ű�־
					if (archivesInfoSavedManageService.updateBatchForPublicAppraisal(userInfo, archivesType, archivesNBXHs, PublicFlag, opinion, pErrInfo) == false ) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action ��������->���ż��� �������µ���������Ϣ ʧ�ܣ�");
					}
				}
			}
				
			//���ż����Ǽ���ɺ󣺲�ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 9;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				dataPageInfo.setCurrentPage(1);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��ҳ��ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForPublicAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				request.setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/publicRegister.jsp";
		return SUCCESS;
	}

	/**
	 * ���������Ǽǲ�ѯǰ������ҳ�����
	 * @return String
	 */
	public String buildBeforeOpenSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ŵǼ�ǰ��ѯ������ҳ�����ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���������Ǽ�ǰ ��ѯ������Ϣ
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			
			//����ҳ�����
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ҳ���������");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//����û���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������->���ż�������ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��ҳ��ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ż�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				request.setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���������Ǽ�
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
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//����ҳ�����
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ҳ���������");
				}
			}
				
			UserInfo userInfo = (UserInfo) request.getSession().getAttribute("userInfo");
			//����û���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				pErrPos = 4;
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			
			if (pFlag) {
				pErrPos = 5;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��������->������������ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->������������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				
				pErrPos = 6;
				//��Ҫ���в����ĵ���
				List<Integer> archivesNBXHs = new ArrayList<Integer>();
				String NBXH = "";
				String openFlag = "";
				if (pFlag && archivesInfos.size()>=1) {
					for(ArchivesInfo archivesInfo : archivesInfos) {
						NBXH = request.getParameter("NBXH"+archivesInfo.getNBXH());
						openFlag = request.getParameter("openFlag"+archivesInfo.getNBXH());
						
						//����ȡ����������
						if ("1".equals(PublicFlag) && !StringTool.checkNull(openFlag)) {	
							archivesNBXHs.add(Integer.valueOf(NBXH));
						} 
						
						//���й�������
						if ("0".equals(PublicFlag) && StringTool.checkNull(openFlag) && openFlag.equals("on")) { 
							archivesNBXHs.add(Integer.valueOf(NBXH));
						}
					}
				}
				
				//ִ�п��ż����Ǽǲ���
				if (pFlag && archivesNBXHs.size() >= 1) {
					pErrPos = 7;
					//��¼���ż�����ϸ
					Map<String, String> opinion = new HashMap<String, String>();
					
//						@param opinion �������� ���ݲ�����	��������AppraisalDate������ԭ��AppraisalReason��������AppraisalPersion
					//������������
					opinion.put("AppraisalDate", AppraisalDate);
					opinion.put("AppraisalPersion", AppraisalPersion);
					opinion.put("AppraisalReason", AppraisalReason);
					
					//��������->�������� �������µ����ܼ���Ϣ
					if (archivesInfoSavedManageService.updateBatchForOpenAppraisal(userInfo, archivesType, archivesNBXHs, PublicFlag, opinion, pErrInfo) == false ) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action ��������->�������� �������µ����ܼ���Ϣ ʧ�ܣ�");
					}
				}
			}
				
			//���ż����Ǽ���ɺ󣺲�ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 9;
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				dataPageInfo.setCurrentPage(1);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��ҳ��ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForOpenAppraisal(userInfo, archivesType, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), PublicFlag, dataPageInfo, archivesInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->������������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				request.setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		
		resultURL = "/JDGL/openRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ؼ����Ǽǲ�ѯǰ������ҳ�����
	 * @return String
	 */
	public String buildBeforeControlAreaSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (searchBeforeRegister(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ŵǼ�ǰ��ѯ������ҳ�����ʧ�ܣ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		resultURL = "/JDGL/controlAreaRegister.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ؼ���ǰ ��ҳ��ѯ������Ϣ
	 * @return
	 */
	public String searchBeforeControlAreaRegister() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//����ҳ�����
			Map<String, ArchivesInfoQueryCondition> archivesInfoQueryConditions = new LinkedHashMap<String, ArchivesInfoQueryCondition>();
			if (pFlag) {
				if (buildPageParams(archivesInfoQueryConditions, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ҳ���������");
				}
			}
				
			HttpServletRequest request = ServletActionContext.getRequest();;
			UserInfo userInfo = (UserInfo) request .getSession().getAttribute("userInfo");
			//����û���Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (userInfo==null || userInfo.getUserID()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("session�û���Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//��������->���ż�������ѯָ���������͵ĵ�����Ϣ
			if (pFlag) {
				ArchivesType archivesType = new ArchivesType();
				archivesType.setID(nodeId);
				dataPageInfo.setPageSize(pageSize);
				List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
				
				//��ҳ��ѯָ���������͵ĵ�����Ϣ
				if (archivesInfoSavedManageService.queryClassifiedForControlAreaAppraisal(userInfo, archivesType,  new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ؼ�������ѯָ���������͵ĵ�����Ϣ ʧ�ܣ�");
				}
				request.setAttribute("archivesInfos", archivesInfos);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		
		resultURL = "/JDGL/controlAreaRegister.jsp";
		return SUCCESS;
	}
	
	
	/**
	 * �����Ǽ�ǰ��ѯָ���������͵ĵ�������
	 * @return boolean
	 */
	private boolean searchBeforeRegister(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo) == false) {
				pFlag = false;
			}

			//�ж��Ƿ���Ҫ�����ֵ�
			if (pFlag) {
				pErrPos = 3;
				//��ȡ���е����γɲ��ż���
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
				
				//��ȡ���������ֵ��
				LinkedHashMap<Integer,RetentionPeriod> retentionPeriodMaps = new LinkedHashMap<Integer, RetentionPeriod>();
				if (pFlag) {
					if (retentionPeriodManageService.findRetentionPeriods(retentionPeriodMaps, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "Action �������еı���������Ϣ ʧ�ܣ�");
					}
				}
				retentionPeriods.addAll(retentionPeriodMaps.values());
			}
			
			//���ݵ������ͱ�����ã�ҳ���ѯ���򡢷�ҳ��ʾ�����
			if (pFlag) {
				pErrPos = 2;
				if (this.setHtmlCodeByArchivesTypeId(nodeId, null) == false) {
					pFlag = false;
					pErrInfo.getContent().append("���ݵ������ͱ������ҳ����� ʧ�ܡ�");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	/**
	 * ��ȡ��������->�����Ǽ���Ϣ �˵���
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
	 * ��ȡ��������->�����Ǽ���Ϣ ѡ���ѯ��������
	 * @return String
	 */
	public String switchAppraisalSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡ���е����γɲ��ż���
			if (pFlag) {
				pErrPos = 2;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ȡ���в�������
			if (pFlag) {
				pErrPos = 3;
				//��ȡ���е�һ���������ࣨһ����Ŀ��
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}
			
			//���ݲ˵��ڵ�id ѡ���ѯҳ��
			if (pFlag) {
				pErrPos = 4;
				switch (nodeId) {
				//��ټ�����ѯ
				case 11:
					resultURL = "/JDGL/saveDestroySearch.jsp";
					break;
					
				//���ż�����ѯ
				case 12:
					resultURL = "/JDGL/publicSearch.jsp";
					break;
					
				//����������ѯ
				case 13:
					resultURL = "/JDGL/openSearch.jsp";
					break;
				
				//���ؼ�����ѯ
				case 14:
					resultURL = "/JDGL/controlAreaSearch.jsp";
					break;
					
				//Ĭ�ϴ�ټ�����ѯ
				default:
					resultURL = "/JDGL/saveDestroySearch.jsp";
					break;
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		return SUCCESS;
	}
	
	/**
	 * δ�����Ǽǲ�ѯ ������ѯ����
	 * @param params	������������archivesID������title�� ��������archivesTypeId�������γɲ���formationDepartmentID��
	 * 					������ʼ����AppraisalDate�� ������ֹ����AppraisalDateEnd
	 * @param pErrInfo
	 * @return
	 */
	private boolean buildParamsMapForAppraisalSearch(Map<String, String> params, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			if (pFlag) {
				//����Ƿ� ʹ�õ��Ų�ѯ����
				if(StringTool.checkNull(archivesID)) {
					params.put("archivesID", archivesID);
				}
				
				System.out.println("title="+title);
				
				//����Ƿ� ʹ�� ���� ��ѯ����
				if(StringTool.checkNull(title)) {
					params.put("title", title);
				}
				
				//����Ƿ� ʹ�� �������� ����
				if(archivesTypeId >= 1) {
					params.put("archivesTypeId", ""+archivesTypeId);
				}
				
				//����Ƿ� ʹ�� �����γɲ��� ����
				if(formationDepartmentID >= 1) {
					params.put("formationDepartmentID", ""+formationDepartmentID);
				}
				
				//����Ƿ� ʹ�� ������ʼ���� ����
				if (StringTool.checkNull(AppraisalDate)) {
					params.put("AppraisalDate", ""+AppraisalDate);
				}
				
				//����Ƿ� ʹ�� ������ֹ���� ����
				if (StringTool.checkNull(AppraisalDateEnd)) {
					params.put("AppraisalDateEnd", ""+AppraisalDateEnd);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ��ټ�����ѯ
	 * @return String
	 */
	public String saveDestroySearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡ���е����γɲ��ż���
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->��ټ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ȡ���в�������
			if (pFlag) {
				pErrPos = 4;
				//��ȡ���е�һ���������ࣨһ����Ŀ��
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//δ�����Ǽǲ�ѯ ������ѯ����
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ������ѯ����  ʧ�ܣ�");
				}
				
				//��ҳ��ѯ��ټ����Ǽ���Ϣ
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalKeepDestroyDetail> appraisalKeepDestroyDetails = new ArrayList<AppraisalKeepDestroyDetail>();
					if (appraisalKeepDestroyDetailManageService.findWithPage(params, dataPageInfo, appraisalKeepDestroyDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ҳ��ѯ��ټ����Ǽ���Ϣ ʧ�ܣ�");
					}
					ServletActionContext.getRequest().setAttribute("appraisalKeepDestroyDetails", appraisalKeepDestroyDetails);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroySearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ټ��������ѯ
	 * @return
	 */
	public String saveDestroySearchDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalKeepDestroyDetail appraisalKeepDestroyDetail = new AppraisalKeepDestroyDetail();
				//����Ψһ��ʶ���Ҵ�ټ�����ϸ�����Ϣ
				if (appraisalKeepDestroyDetailManageService.findAppraisalKeepDestroyDetailByID(appraisalDetailId, appraisalKeepDestroyDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ����Ψһ��ʶ���Ҵ�ټ�����ϸ�����Ϣ ʧ�ܣ�");
				}
				ServletActionContext.getRequest().setAttribute("appraisalKeepDestroyDetail", appraisalKeepDestroyDetail);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/saveDestroySearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ż�����ѯ
	 * @return String
	 */
	public String publicSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡ���е����γɲ��ż���
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ż����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ȡ���в�������
			if (pFlag) {
				pErrPos = 4;
				//��ȡ���е�һ���������ࣨһ����Ŀ��
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//δ�����Ǽǲ�ѯ ������ѯ����
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ������ѯ����  ʧ�ܣ�");
				}
				
				//���ż�����ѯ ������־Ϊ1
				params.put("PublicFlag", "1");
				
				//��ҳ��ѯ��ټ����Ǽ���Ϣ
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalPublicDetail> appraisalPublicDetails = new ArrayList<AppraisalPublicDetail>();
					if (appraisalPublicDetailManageService.findWithPage(params, dataPageInfo, appraisalPublicDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ҳ��ѯ���ż����Ǽ���Ϣ ʧ�ܣ�");
					}
					
					ServletActionContext.getRequest().setAttribute("appraisalPublicDetails", appraisalPublicDetails);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
	
		resultURL = "/JDGL/publicSearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ż��������ѯ
	 * @return String
	 */
	public String publicSearchDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalPublicDetail appraisalPublicDetail = new AppraisalPublicDetail();
				//����Ψһ��ʶ���ҵ�������/���ż�����ϸ������ʵ������Ϣ
				if (appraisalPublicDetailManageService.findAppraisalPublicDetailByID(appraisalDetailId, 1, appraisalPublicDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ����Ψһ��ʶ���ҵ�������/���ż�����ϸ������ʵ������Ϣ ʧ�ܣ�");
				}
				ServletActionContext.getRequest().setAttribute("appraisalPublicDetail", appraisalPublicDetail);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/publicSearch_detail.jsp";
		return SUCCESS;
	}

	/**
	 * ����������ѯ
	 * @return String
	 */
	public String openSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡ���е����γɲ��ż���
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���������Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ȡ���в�������
			if (pFlag) {
				pErrPos = 4;
				//��ȡ���е�һ���������ࣨһ����Ŀ��
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//δ�����Ǽǲ�ѯ ������ѯ����
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ������ѯ����  ʧ�ܣ�");
				}
				
				//���ż�����ѯ ������־Ϊ1
				params.put("PublicFlag", "0");
				
				//��ҳ��ѯ��ټ����Ǽ���Ϣ
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalPublicDetail> appraisalPublicDetails = new ArrayList<AppraisalPublicDetail>();
					if (appraisalPublicDetailManageService.findWithPage(params, dataPageInfo, appraisalPublicDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ҳ��ѯ���������Ǽ���Ϣ ʧ�ܣ�");
					}
					
					ServletActionContext.getRequest().setAttribute("appraisalPublicDetails", appraisalPublicDetails);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/openSearch.jsp";
		return SUCCESS;
	}
	
	/**
	 * �������������ѯ
	 * @return
	 */
	public String openSearchDetail() {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				AppraisalPublicDetail appraisalPublicDetail = new AppraisalPublicDetail();
				//����Ψһ��ʶ���ҵ�������/���ż�����ϸ������ʵ������Ϣ
				if (appraisalPublicDetailManageService.findAppraisalPublicDetailByID(appraisalDetailId, 0, appraisalPublicDetail , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ����Ψһ��ʶ���ҵ�������/���ż�����ϸ������ʵ������Ϣ ʧ�ܣ�");
				}
				ServletActionContext.getRequest().setAttribute("appraisalPublicDetail", appraisalPublicDetail);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/openSearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ؼ�����ѯ
	 * @return String
	 */
	public String controlAreaSearch() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjectionForArchivesInfo(pErrInfo ) == false) {
				pFlag = false;
			}

			//��ȡ���е����γɲ��ż���
			if (pFlag) {
				pErrPos = 3;
				formationDepartments = new ArrayList<DepartmentInfo>();
				if (departmentInfoManageService.findFormationDepartments(formationDepartments, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��������->���ؼ����Ǽǣ�����ϵͳ�����е����γɲ�����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ȡ���в�������
			if (pFlag) {
				pErrPos = 4;
				//��ȡ���е�һ���������ࣨһ����Ŀ��
				archivesTypes = new ArrayList<ArchivesType>(SystemInitializer.getInstance().getArchivesTypes().values());
			}

			//δ�����Ǽǲ�ѯ ������ѯ����
			if (pFlag) {
				pErrPos = 5;
				Map<String, String> params = new HashMap<String, String>();
				if (buildParamsMapForAppraisalSearch(params , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ������ѯ����  ʧ�ܣ�");
				}
				
				//��ҳ��ѯ��ټ����Ǽ���Ϣ
				if (pFlag) {
					dataPageInfo.setPageSize(pageSize);
					
					List<AppraisalUseScopesDetail> appraisalUseScopesDetails = new ArrayList<AppraisalUseScopesDetail>();
					if (appraisalUseScopesDetailManageService.findWithPage(params, dataPageInfo, appraisalUseScopesDetails , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ҳ��ѯ���ؼ����Ǽ���Ϣ ʧ�ܣ�");
					}
					ServletActionContext.getRequest().setAttribute("appraisalUseScopesDetails", appraisalUseScopesDetails);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
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
			//����Ƿ��н���BLL����ע��
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
					pErrInfo.getContent().insert(0, "Action ���ݵ����ڲ���Ų��ҵ������ֿ��Ƽ�����ϸ������ʵ������Ϣ ʧ�ܣ�");
				}
				
				ServletActionContext.getRequest().setAttribute("appraisalUseScopesDetail", appraisalUseScopesDetail);
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		System.out.println(pErrInfo.toString());
		resultURL = "/JDGL/controlAreaSearch_detail.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ݵ������ͱ�����ã�ҳ���ѯ���򡢷�ҳ��ʾ�����
	 * @param archivesTypeId �������ͱ��
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setHtmlCodeByArchivesTypeId(int archivesTypeId, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//������֤
			if(archivesTypeId <= 0 ){
				pFlag = false;
				pErrInfo.getContent().append("�Ҳ������������ţ�nodeId����ʧ��");
			}

			if (pFlag) {
				pErrPos = 2;
				HttpServletRequest request = ServletActionContext.getRequest();
				
				String htmlCode = this.getHtmlCodeByType(archivesTypeId, archivesInfoQueryConditions);
				
				//����������ҳ��
				request .setAttribute("htmlCode", htmlCode);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	
	/**
	 * ����ָ���������͵�����ѯ���������༯��
	 * @param archivesTypeId ��������id
	 * @param filterParams �������ҳ�����
	 * @param archivesInfoQueryConditions ���ص������͵�����ѯ���������༯��
	 * @return ����ɹ�����true�����򷵻�false
	 */
	@SuppressWarnings("unchecked")
	private boolean buildArchivesInfoQueryConditions(int archivesTypeId, List<String> filterParams, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//����Ϊ�ձ�ʶ�����ڹ��˵���Щ��������ֶ�
		boolean inputTrue = false;
		try {
			if (pFlag) {
				pErrPos = 2;
				
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
				Enumeration<String> enumeration = request.getParameterNames();//ȡ�����е�ҳ�洫�����������������
				String [] values = null;//����ĳ�������������vlueֵ
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				boolean filterFlag = false;
				
				while(enumeration.hasMoreElements()){
					filterFlag = false;
					inputTrue = false;
					
					parameterName = enumeration.nextElement();//ȡ������������
					
					System.out.println("1212parameterName="+parameterName+"  "+!filterParams.contains(parameterName));
					
					
					//�������ز������Լ���������(�����˵�)��ֻ���ؼ�����������
					if(filterParams.contains(parameterName)) {
						continue;
					}
					
					
					//������� ���ݵ�NBXH*��publicFlag*һ�����
					for(String param : filterParams) {
						if(parameterName.matches(param)) {
							filterFlag = true;
						}
					}
					
					//�������Ϊ����Ҫ���˵� ������ѯ����
					if (!filterFlag) {
						//ȡ����������Ӧ��ֵ ��ֵΪdataItem��Id
						values = request.getParameterValues(parameterName);
						dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
						
						System.out.println("parameterName="+parameterName);
						System.out.println("values="+values[0]);
						System.out.println("dataItem="+dataItem);
						
						//��������Ϊ�յĲ�ѯ����
						for(int i=0;i<values.length;i++){
							if(!"".equals(values[i])){	
								inputTrue = true;
							}
						}
						
						//�����������ݵĲ�ѯ����ʵ������archivesInfoQueryCondition���󲢼ӵ�archivesInfoQueryConditions������
						if(inputTrue){
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);//����archivesInfoQueryCondition����
							
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
			//�쳣����
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	//���߷���
	/**
	 * ���ݵ����������ҳ��html��ѯ����
	 */
	private String getHtmlCodeByType(int archivesTypeId, Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String htmlCode = "";
		//�õ�html����
		SystemInitializer systemInitializer = SystemInitializer.getInstance();
		Map<String, ArchivesTypeDataItem> dataItems = null;
		//ƽ��ṹ�ĵ������༯�ϣ����е������඼�޲�ι�ϵ��ֱ�Ӵ���ڼ�����
		if( systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery()== null){
			htmlCode = "<script>alert('û�пɲ�ѯ�������');</script>";
		}else{
			dataItems = systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();
			htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions);
		}
		return htmlCode;
	}
	
	/**
	 * ��鵵����Ϣ�鵵���ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForArchivesInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (archivesInfoSavedManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"������Ϣ�鵵���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			//��鲿����Ϣ���BLLҵ���߼����������ע��
			if (departmentInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"������Ϣ���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			//��鱣�����޹��������������ע��
			if (retentionPeriodManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�������������ֵ���BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (appraisalKeepDestroyDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"��ټ�����ϸ������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (appraisalPublicDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"��������/���ż�����ϸ������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (appraisalUseScopesDetailManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"�������ֿ��Ƽ�����ϸ������BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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