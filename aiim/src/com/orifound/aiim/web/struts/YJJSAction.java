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
	 * �ƽ����������߼��ӿ�
	 */
	@Autowired
	private IPaperTransferManageService paperTransferManageService;
	
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	@Autowired
	private IUserInfoManageService userInfoManageService;
	/**
	 * ������Ϣ����
	 */
	private PaperTransferBatch paperTransferBatch = new PaperTransferBatch();
	
	public PaperTransferBatch getPaperTransferBatch() {
		return paperTransferBatch;
	}

	public void setPaperTransferBatch(PaperTransferBatch paperTransferBatch) {
		this.paperTransferBatch = paperTransferBatch;
	}
	
	/**
	 * ���Ҫ���ص�ҳ��
	 */
	private String resultURL;
	
	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	/**
	 * ���ڷ��ؽ���json��
	 */
	private String jsonResult;
	
	public String getJsonResult() {
		return jsonResult;
	}

	public void setJsonResult(String jsonResult) {
		this.jsonResult = jsonResult;
	}
	
	/**
	 * ҳ�洫���Ҫ�����嵥���κ�
	 */
	private String [] batNos;

	public void setBatNos(String[] batNos) {
		this.batNos = batNos;
	}
	
	/**
	 * ҳ�洫����ڲ��������
	 */
	private int[] NBXHS;

	public int[] getNBXHS() {
		return NBXHS;
	}

	public void setNBXHS(int[] nBXHS) {
		NBXHS = nBXHS;
	}

	/**
	 * ��ѯ��Χ����
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
	 * ��ҳ��Ϣ����
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	/**
	 * �γɲ��ŵõ���������������ָ���������¼���ַ<br/>
	 * �������γɲ��ŵ����ƽ�
	 * @return ��������tree ���нڵ㣺��¼���ͨ������¼���δͨ�����ƽ�δͨ��
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

			//�õ����е������������ӽڵ㹹��archivesTypeExs����
			archivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
			if(userInfo.getArchivesTypes() != null && userInfo.getArchivesTypes().size()>0){

				CommonUtil.getChildPlaneArchivesTypeExs(userInfo.getArchivesTypes(), archivesTypeExs, pErrInfo);
				
			}
			
			//����ҵ���߼�ȡ�ø������µ���������
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferManageService.statPaperCheckBackCountAndInputCheckReslut(userInfo.getChargeUserIDs(), archivesTypeExs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�õ�����������ʧ��");
				}
			}
			
			//������
			if (pFlag) {
				pErrPos = 3;
				
				LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
				
				//��ƽ���ת��Ϊ��״
				CommonUtil.convertPlaneArchivesTypeExsToTree(archivesTypeExs, treeArchivesTypeExs, pErrInfo);
				
				//������
				String tree = CreateTreeUtil.getArchivesTypeTreeWithProperty(new ArrayList<ArchivesTypeEx>(treeArchivesTypeExs.values()));
				request.setAttribute("proceseAction","YJJSAction_findArchivesForXCBMDAYJ.action");
				request.setAttribute("tree",tree);
				resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				
				//��¼��־
				logger.error(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * �γɲ��ŵõ���¼���ͨ������¼���δͨ�������ƽ�δͨ���ĵ�����Ϣ<br/>
	 * �������γɲ��ŵ����ƽ�
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

			//�õ�������Ϣ���͵������Ͷ���
			if (pFlag) {
				pErrPos = 3;
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				userIds = ((UserInfo)request.getSession(false).getAttribute("userInfo")).getChargeUserIDs();
			}
			
			//����ҵ���߼���ȡ������Ϣ
			if (pFlag) {
				pErrPos = 4;
				archivesInfos = new ArrayList<ArchivesInfo>();
				dataPageInfo.setPageSize(15);
				
				if (paperTransferManageService.findArchivesInfosByEnumWorkFlowStatus(userIds, archivesType, EnumWorkFlowStatus.getEnumElement(state), dataPageInfo,"UserID1", archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ҵ�����Ϣʧ��");
				}
			}
			
			//����ҵ���߼��õ���ǰ�����е���������
			if (pFlag) {
				pErrPos = 5;
				if (EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.ҵ��ָ������¼���ͨ�� || EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴�� ) {
					//ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����
					archivesInfosSum = new IntegerEx();
					if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,false, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����ʧ��");
					}else{
						request.setAttribute("sum", archivesInfosSum.getValue());
					}
				}
			}
			
			if(pFlag){
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID).getDataItemsForListDisplay());
				request.setAttribute("archivesInfos", archivesInfos);
				if (EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��) {
					resultURL = "/YJJSGL/x_DAYJ_SHTG.jsp";
				}else if(EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.ҵ��ָ������¼��˴��){
					resultURL = "/YJJSGL/x_DAYJ_SHWTG.jsp";
				}else if(EnumWorkFlowStatus.getEnumElement(state) == EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴��){
					resultURL = "/YJJSGL/x_DAYJ_YJWTG.jsp";
				}
				request.setAttribute("archivesTypeID", archivesTypeID);
				request.setAttribute("state", state);
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				
				//��¼��־
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * �����γɲ��ŵ��������ƽ��嵥<br/>
	 * �������γɲ��ŵĵ����ƽ�
	 */
	public String addArchivesToTransferList() throws Exception{
		System.out.println("-------------�����ƽ��嵥--------------------------------");
		
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
			//��鵵������
			pErrPos = 1;
			UserInfo userInfo = ((UserInfo)session.getAttribute("userInfo"));
			String deptType = request.getParameter("deptType");
			
            if (request.getParameter("archivesTypeID") == null) {
				pFlag = false;
				pErrInfo.getContent().append("�õ���������IDʧ��");
			}else if("".equals(request.getParameter("archivesTypeID"))){
				pFlag = false;
				pErrInfo.getContent().append("�õ���������IDʧ��");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeID")));
			}
            
            //��鴫��ĵ����ڲ��������
            if (pFlag) {
				if (NBXHS == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�����ƽ��嵥�ĵ�����Ϣ");
				}else if(NBXHS.length <=0){
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�����ƽ��嵥�ĵ�����Ϣ");
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
            
            //����ҵ���߼������ƽ��嵥
			if (pFlag) {
				if("XCBM".equals(deptType)){
					if (paperTransferManageService.addToPaperTransferBatchForOutside(userInfo, archivesType, archivesInfos,false, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "���뵽�ƽ��嵥ʧ��");
					}
				}else if("YWZDS".equals(deptType)){
					if (paperTransferManageService.addToPaperTransferBatchForOutside(userInfo, archivesType, archivesInfos,true, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "���뵽�ƽ��嵥ʧ��");
					}
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
				
				if(pErrInfo.getException() != null){
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * �ӵ����嵥���Ƴ����ε�����ϸ  url:"YJGL/YJJSAction_removeFromQDList.action"
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public String removeFromQDList() throws Exception {
		System.out.println("----------���嵥���Ƴ�------------------------");
		
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		System.out.println("NBXH="+request.getParameter("NBXH")+"&archivesTypeID="+request.getParameter("archivesTypeID")+"&transferBatNo="+request.getParameter("transferBatNo"));
		
		ArchivesType archivesType = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			int NBXH = Integer.parseInt(request.getParameter("NBXH"));
			String transferBatNo = request.getParameter("transferBatNo");
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			int userID = ((UserInfo)request.getSession(false).getAttribute("userInfo")).getUserID();
			String deptType = this.getRequest().getParameter("deptType");
			
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			
			EnumWorkFlowStatus enumWorkFlowStatus = null;
			if ("YWZDS".equals(deptType)) {
				enumWorkFlowStatus = EnumWorkFlowStatus.��¼���;
			}else if("XCBM".equals(deptType)){
				enumWorkFlowStatus = EnumWorkFlowStatus.�γɲ��Ž��������嵥���Ƴ�;
			}
			
			//��ʼ����2...
			if (pFlag) {
				if(paperTransferManageService.removeArchiveSInfoFromQDList(NBXH,archivesType,transferBatNo,userID,enumWorkFlowStatus,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�Ƴ�������Ϣʧ��");
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
				
				if(pErrInfo.getException() != null){
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * ȷ���ƽ�����
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
			//��ʼ���� 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo)ServletActionContext.getRequest().getSession(false).getAttribute("userInfo");
			String deptType = request.getParameter("deptType");
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if ("XCBM".equals(deptType)) {
					if (paperTransferManageService.confirmTransferPaperForOutside(paperTransferBatch.getBatNo(), userInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ȷ���ƽ�ʧ��");
						System.out.println(pErrInfo.toString());
					}
				}else if ("YWZDS".equals(deptType)) {
					if (paperTransferManageService.confirmTransferPaperForInside(paperTransferBatch.getBatNo(), userInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "ȷ���ƽ�ʧ��");
						System.out.println(pErrInfo.toString());
					}
				}	
			}
			if(pFlag){
				print("�ƽ���ɣ�");
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
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
				logger.error(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * ҵ��ָ���ҵõ��ƽ�����������ָ���������¼���ַ<br/>
	 * ������ҵ��ָ���ҽ��ܵǼ�
	 * @return ���γɲ���tree
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
	 * ҵ��ָ���Ҳ����γɲ����ƽ�����������<br/>
	 * ������ҵ��ָ���ҽ��ܵǼ�
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
			
			System.out.println("------------------ҵ��ָ���Ҳ����γɲ��ŵ��ƽ�����--------------------------------------");
			System.out.println("nodeId: "+deptId);
			
			//����ҵ���߼�
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForOutside(deptId,EnumPaperTransferBatchesDealStatus.ȷ���ƽ�,paperTransferBatches,false, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ҵ��ָ���ҵõ�����������ָ���������¼���ַ<br/>
	 * ������ҵ��ָ���Ҳ鿴�������
	 * @return ���γɲ���tree
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
	 * ҵ��ָ���Ҹ��ݲ��Ų��ҽ��յǼ���ɵ��ƽ�����<br/>
	 * ������ҵ��ָ���ҽ������
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
				//����ҵ���߼�
				if(paperTransferManageService.findPaperTransferBatchesForOutside(deptID, EnumPaperTransferBatchesDealStatus.���յǼ����, paperTransferBatches,false, pErrInfo) == false){	
					pFlag = false;
					pErrInfo.getContent().insert(0, "");
				}
			}
			request.setAttribute("paperTransferBatches", paperTransferBatches);
			request.setAttribute("deptType", "YWZDS");
			resultURL = "/YJJSGL/JSSH.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;	
	}
	
	/**
	 * ҵ��ָ���ҵõ��ƽ�����������ָ���������¼���ַ<br/>
	 * ������ҵ��ָ���ҵ����ƽ� 
	 * @return ���γɲ���tree
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
	 * ҵ��ָ���� ���ݲ��Ų������ͨ�����ƽ�������<br>
	 * ������ҵ��ָ���ҵĵ����ƽ�
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
		//�õ�����ID
		int deptID = Integer.parseInt(request.getParameter("nodeId"));
		System.out.println("����ID�� "+deptID);
		
		List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
		
		//����ҵ���߼� ���������ɵ�������Ϣ
		if (pFlag) {
			if (paperTransferManageService.findPaperTransferBatchesForOutside(deptID, EnumPaperTransferBatchesDealStatus.����������, paperTransferBatches,false, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "");
				System.out.println(pErrInfo.toString());
			}
		}
		
		//ͳ�Ƶ�ǰ���ε���������
		if(pFlag){
			archivesInfosSum = new IntegerEx();
			if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(), archivesInfosSum,true, pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "ͳ������ʧ��");
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
	 * ҵ��ָ���Ҽ����ƽ��嵥
	 * @return 
	 * @throws Exception
	 */
	public String insertBatIntoNew() throws Exception{
		System.out.println("--------ҵ��ָ���Ҽ����ƽ��嵥---------------------------------");
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
			message = "��ѡ��Ҫ�ƽ������Σ�";
		}else{
			pFlag = true;
		}
		
		//ȡ��userID
		UserInfo userInfo = ((UserInfo)session.getAttribute("userInfo"));

		if (pFlag) {	
			//����ҵ���߼�
			if(paperTransferManageService.addToPaperTransferBatchForIntside(userInfo,  batNos, pErrInfo) == false){
				message = "�����嵥ʧ�ܣ�";
				pFlag = false;
				System.out.println(pErrInfo.toString());
			}
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �������κŲ���������ϸ��Ϣ
	 * ���ܵǼ�
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (request.getParameter("batNo") == null || request.getParameter("batNo").equals("")) {
				batNo = null;
				pFlag = false;
				pErrInfo.getContent().append("������Ҫ�Ǽǵ����κţ�");
			} else{
				batNo = request.getParameter("batNo");
			}
			
			if(pFlag){
				if(paperTransferManageService.findPaperTransferBatchByBatchNo(batNo, paperTransferBatch, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����κŲ�ѯ����ʧ��");
				}
			}
			request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
			request.setAttribute("paperTransferBatch",paperTransferBatch);
			request.setAttribute("message", pErrInfo.toShortString());
			request.setAttribute("batNo", batNo);
			resultURL = "/YJJSGL/dlg_JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ҵ��ָ���ҵõ��ƽ�����������ָ���������¼���ַ<br/>
	 * ������ҵ��ָ���Ҳ鿴���ռ�¼
	 * @return ���γɲ���tree
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
	 * ҵ��ָ���Ҳ��ҽ��������������ƽ�������<br/>
	 * ������ҵ��ָ���Ҷ��γɲ��ŵĹ��� ����ҵ��ָ���Ҳ鿴���ռ�¼
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
			//��ʼ���� 1...
			pErrPos = 1;
			//�õ�����ID
			int departmentID = Integer.parseInt(request.getParameter("nodeId"));
			
			//����ҳ�ź�ÿҳ��ʾ��¼��
			dataPageInfo.setPageSize(20);
			
			paperTransferBatches = new ArrayList<PaperTransferBatch>();
			//����ҵ���߼�
			if(paperTransferManageService.findPaperTransferBatchesForOutside(departmentID, EnumPaperTransferBatchesDealStatus.���յǼ����, paperTransferBatchesQueryCondition, dataPageInfo,paperTransferBatches, pErrInfo) == false){
				pFlag = false;
				pErrInfo.getContent().insert(0, "���ҽ��ռ�¼ʧ��");
				System.out.println(pErrInfo.toString());
			};
			
			request.setAttribute("paperTransferBatches", paperTransferBatches);
			request.setAttribute("deptId", departmentID);
			request.setAttribute("paperTransferBatches", paperTransferBatches);

			resultURL = "/YJJSGL/CKJSJL.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * �鿴��ǰ�ƽ��嵥    ��ǰ�û���ǰ���ƽ��嵥    
	 * @return
	 * @throws Exception
	 */
	public String findCurrentQD() throws Exception{
		System.out.println("-----���ҵ�ǰ�ƽ��嵥-------");
		
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
			//������
			pErrPos = 1;
			
			userInfo  = (UserInfo)request.getSession(false).getAttribute("userInfo");
			deptType = request.getParameter("deptType");
			stateType = request.getParameter("stateType");
			type = request.getParameter("type");

			boolean insideFlag = false;
			
			if("YWZDS".equals(deptType)){
				insideFlag = true;
			}
			
			//����ҵ���߼��õ�����������Ϣ
			if (pFlag) {
				//�贫����� �ƽ��嵥�ڵ�������
				paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
				//����ҵ���߼�����ȡ��ָ�����η�����Ϣ
				if (paperTransferManageService.findCurrentPaperTransferBatchesArchvTypeDetails(userInfo.getUserID(),paperTransferBatch, paperTransferBatchesArchvTypeDetails,insideFlag, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ȡ��ָ�����η�����Ϣʧ��");
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;	
	}
		
	/**
	 * ����ָ��������ָ����������ĵ�����Ϣ
	 * @return ָ��������ָ������ĵ�����Ϣ�б�
	 */
	public String findArchivesInfosByArchivesTypeAndBatNo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();//���մ�����Ϣ

		HttpServletRequest request = ServletActionContext.getRequest();
		ArchivesType archivesType = new ArchivesType();
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			archivesType.setID(Integer.parseInt(request.getParameter("archivesTypeID")));
			String batNo = request.getParameter("batNo");
			//String deptType = request.getParameter("deptType");

			List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
			if("1".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.��δ���);
			}else if("2".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.��δ���);
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("3".equals(request.getParameter("stateType"))){//ҵ��ָ���Ҳ鿴��ǰ�嵥
				enumCheckResults.add(EnumCheckResult.��δ���);
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
			}else if("4".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("5".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}else if("6".equals(request.getParameter("stateType"))){
				enumCheckResults.add(EnumCheckResult.�������������ͨ��);
			}
			
			//���շ��ص�����Ϣ�б�
			List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
			
			//����ҵ���߼�
			if (pFlag) {
				if(paperTransferManageService.findPaperTransferBatchesDetails(batNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ʧ��");
					System.out.println(pErrInfo.toString());
				}
     		}
			
			resultURL = "/YJJSGL/dlg_YJQD_right.jsp";
			request.setAttribute("deptType", request.getParameter("deptType"));
			request.setAttribute("type", request.getParameter("type"));
			request.setAttribute("stateType", request.getParameter("stateType"));
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���յǼǣ���֤���θ������൵������
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
				//���ҳ��������������ƽ����η�����Ϣ���󼯺�
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
							pErrInfo.getContent().append("����"+key+"�ĵ����������Ϸ�");
						}		
						paperTransferBatchesArchvTypeDetails.add(paperTransferBatchesArchvTypeDetail);
					}	
				}
			}

			//����ҵ���߼�����
			if (pFlag) {
				pErrPos = 3;
				if (paperTransferManageService.registerPaperReceive(userInfo,batNo,paperTransferBatchesArchvTypeDetails, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�Ǽ�ʧ��");
				}
			}
			
			if (pFlag) {
				response.getWriter().print("�Ǽ���ɣ�");
			}
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
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
					print(pErrInfo.toString());
				}else{
					print(pErrInfo.toShortString());
				}
			}

			//���پֲ�����
			throwable = null;
		}
		return null;		
	}
	
	/**
	 * ���ҵ�ǰҪ��˵������иõ����ķ����еĵ�����Ϣ<br/>
	 * �ʺϽ������
	 * @return ���ص����б�
	 * @throws Exception
	 */
	public String findArchivesInfosByArchivesTypeForSH() throws Exception{
        boolean pFlag = true;
    	ErrInfo pErrInfo = new ErrInfo();//���մ�����Ϣ
    	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//����ҳ�洫�뵵������
		ArchivesType archivesType = new ArchivesType();
		archivesType.setID(Integer.parseInt(request.getParameter("archivesTypeID")));
		
		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		
		//���շ��ص�����Ϣ�б�
		List<PaperTransferBatchesDetail> paperTransferBatchesDetails = new ArrayList<PaperTransferBatchesDetail>();
	
		List<EnumCheckResult> enumCheckResults = new ArrayList<EnumCheckResult>();
		if("YWZDS".equals(deptType)){
			enumCheckResults.add(EnumCheckResult.��δ���);
			enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
		}else if("DAGLS".equals(deptType)){
			enumCheckResults.add(EnumCheckResult.ҵ��ָ�������ͨ��);
			//enumCheckResults.add(EnumCheckResult.�������������ͨ��);
		}
		
		//����ҵ���߼�
		pFlag = paperTransferManageService.findPaperTransferBatchesDetails(batNo, archivesType, paperTransferBatchesDetails,enumCheckResults, pErrInfo);
		
		if (pFlag) {
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
		} else {
			request.setAttribute("paperTransferBatchesDetails", paperTransferBatchesDetails);
			//��������������������������������������
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
	 * �鿴�ƽ���¼<br/>
	 * �����γɲ��ź�ҵ��ָ���ҿ�ͨ�øù���
	 * @return
	 * @throws Exception
	 */
	public String findTransferOverBatches() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		//�õ�request����
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		List<PaperTransferBatch> paperTransferBatches = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
			String deptType = request.getParameter("deptType");
			//����ҳ�ź�ÿҳ��ʾ��¼��
			dataPageInfo.setPageSize(20);

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				// ����ҵ���߼��õ�rowCountֵ���б�
				paperTransferBatches = new ArrayList<PaperTransferBatch>();

				if ("XCBM".equals(deptType)) {
					if(paperTransferManageService.findPaperTransferBatchesForOutside(userInfo.getDepartmentID(), 
							EnumPaperTransferBatchesDealStatus.ȷ���ƽ�,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							paperTransferBatches,
							pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
						System.out.println(pErrInfo.toString());
					}
				}else if ("YWZDS".equals(deptType)) {
					List<PaperTransferBatch> pPaperTransferBatches = new ArrayList<PaperTransferBatch>();
					//���Ҹ��û������ƽ�����
					if(paperTransferManageService.findPaperTransferBatchesForInside(userInfo.getUserID(), 
							EnumPaperTransferBatchesDealStatus.ȷ���ƽ�,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							pPaperTransferBatches,
							true,pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
						System.out.println(pErrInfo.toString());
					}
					paperTransferBatches.addAll(pPaperTransferBatches);
					//���Ҹ��û������ƽ�����
					if(paperTransferManageService.findPaperTransferBatchesForInside(userInfo.getUserID(), 
							EnumPaperTransferBatchesDealStatus.ȷ���ƽ�,
							paperTransferBatchesQueryCondition,
							dataPageInfo,
							pPaperTransferBatches,
							false,pErrInfo) == false){
						
						pFlag =	false;
						pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
						System.out.println(pErrInfo.toString());
					}
					
					//���
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * �õ�ҵ��ָ���ҹ鵵������
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
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

			//�õ����е������������ӽڵ㹹��archivesTypeExs����
			archivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
			if(userInfo.getArchivesTypes() != null && userInfo.getArchivesTypes().size()>0){

				CommonUtil.getChildPlaneArchivesTypeExs(userInfo.getArchivesTypes(), archivesTypeExs, pErrInfo);
				
			}
			
			//����ҵ���߼�ȡ�ø������µ���������
			if (pFlag) {
				pErrPos = 2;
				if (paperTransferManageService.statPaperCheckBackCount(userInfo.getChargeUserIDs(),"UserID2",archivesTypeExs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�õ�����������ʧ��");
				}
			}
			
			//������
			if (pFlag) {
				pErrPos = 3;
				
				LinkedHashMap<Integer, ArchivesTypeEx> treeArchivesTypeExs = new LinkedHashMap<Integer, ArchivesTypeEx>();
				
				//��ƽ���ת��Ϊ��״
				CommonUtil.convertPlaneArchivesTypeExsToTree(archivesTypeExs, treeArchivesTypeExs, pErrInfo);
				
				//������
				String tree = CreateTreeUtil.getArchivesTypeTreeWithPropertyCheckBack(new ArrayList<ArchivesTypeEx>(treeArchivesTypeExs.values()));
				request.setAttribute("proceseAction","YJJSAction_findArchivesForYWZDSGDYS.action");
				request.setAttribute("tree",tree);
				resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					resultURL = "/YJJSGL/archivesTypeTreeForXCBMDAYJ.jsp";
					request.setAttribute("message", pErrInfo.toShortString());
				}
				
				//��¼��־
				logger.error(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ҵ��ָ���Ҳ�ѯ�鵵���ս��
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
			//��ʼ���� 1...
			pErrPos = 1;
			String archivesTypeID = request.getParameter("archivesTypeID");
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(archivesTypeID));
			UserInfo userInfo = (UserInfo)request.getSession(false).getAttribute("userInfo");
			int [] userIds = {userInfo.getUserID()};

			//��ʼ����2...
			if (pFlag) {
				dataPageInfo.setPageSize(20);
				archivesInfos = new ArrayList<ArchivesInfo>();
				
				//�������δͨ��
				if(paperTransferManageService.findArchivesInfosByEnumWorkFlowStatus(userIds, archivesType, EnumWorkFlowStatus.���������ҽ�����˴��, dataPageInfo,"UserID2", archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ������Ϣʧ��");
					System.out.println(pErrInfo.toString());
				}
			}
			
			//����ҵ���߼��õ���ǰ�����е���������
			if (pFlag) {
				pErrPos = 5;
			
				//ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����
				archivesInfosSum = new IntegerEx();
				if (paperTransferManageService.staArchivesInfosSumByCurrentTransferBat(userInfo.getUserID(),archivesInfosSum,true, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ͳ�Ƶ�ǰ�ƽ������еĵ�����Ϣ����ʧ��");
				}else{
					request.setAttribute("sum", archivesInfosSum.getValue());
				}
			}
			

			request.setAttribute("archivesTypeID", archivesTypeID);
			request.setAttribute("dataItems", archivesType.getDataItemsForListDisplay());
		    request.setAttribute("archivesInfos", archivesInfos);
			resultURL = "/YJJSGL/GDYSJG.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���������ҵõ�ҵ��ָ�����û���<br>
	 * ���������ǽ��յǼ�
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
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.ҵ��ָ��רԱ��ɫ,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findTransferOverBatsInside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���������Ұ��û����һ�δ�Ǽǵ��ƽ�����
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
			
			System.out.println("------------------ҵ��ָ���Ҳ����γɲ��ŵ��ƽ�����--------------------------------------");
			System.out.println("nodeId: "+userID);
			
			//����ҵ���߼�
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForInside(userID,EnumPaperTransferBatchesDealStatus.ȷ���ƽ�,paperTransferBatches,true, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSDJ.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		//return "toDJ";
		return SUCCESS;
	}

	/**
	 * ���������ҵõ�ҵ��ָ�����û���<br>
	 * ���������ǽ��յǼ�
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
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.ҵ��ָ��רԱ��ɫ,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findRegisteredBatsIntside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���������Ұ��û����ҵǼ���ɵ��ƽ�����<br>
	 * ���������ҽ������
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
			
			System.out.println("------------------ҵ��ָ���Ҳ����γɲ��ŵ��ƽ�����--------------------------------------");
			System.out.println("nodeId: "+userID);
			
			//����ҵ���߼�
			List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
			
			if (paperTransferManageService.findPaperTransferBatchesForInside(userID,EnumPaperTransferBatchesDealStatus.���յǼ����,paperTransferBatches,true, pErrInfo) == false) {
				pFlag =	false;
				pErrInfo.getContent().insert(0,"�����ƽ�������Ϣʧ��");
				System.out.println(pErrInfo.toString());
			}
			
			request.setAttribute("message",message);
			request.setAttribute("deptType", "DAGLS");
		    request.setAttribute("paperTransferBatches", paperTransferBatches);
		    resultURL = "/YJJSGL/JSSH.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		//return "toDJ";
		return SUCCESS;
	}
	
	/**
	 * ����ָ�������ڵĵ���������Ϣ <br/>���ý������
	 * @return ���������ڵ�������б�
	 * @throws Exception
	 */
	public String findArchivesTypesByBatNo() throws Exception{
		System.out.println("------���ҵǼ���ɵ�������Ϣ��ϸ-------------------------------");
		//String message = "";
		boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();

		//����type ����ҳ�������ǲ鿴�����ƽ�
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		
		//�贫�����
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//����ҵ���߼�����ȡ��ָ�����η�����Ϣ
		paperTransferBatch.setBatNo(batNo);
		if( paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			pFlag = false;
			pErrInfo.getContent().insert(0, "�������η�����ϸ��Ϣʧ��");
			System.out.println(pErrInfo.toString());
		}
		//��������Ϣ��Ĭ�Ϸ����еĵ�����Ϣ����request��
		if(pFlag){
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		}else{
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
			//��������������
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
	 * ����ָ�������ڵĵ���������Ϣ <br/>���ý������
	 * @return ���������ڵ�������б�
	 * @throws Exception
	 */
	public String findQDByBatNo() throws Exception{
		System.out.println("------���ҵǼ���ɵ�������Ϣ��ϸ-------------------------------");
		//String message = "";
		boolean pFlag = false;
		ErrInfo pErrInfo = new ErrInfo();

		//����type ����ҳ�������ǲ鿴�����ƽ�
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		String deptType = request.getParameter("deptType");
		String stateType = request.getParameter("stateType");
		String type = request.getParameter("type");
		
		//�贫�����
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//����ҵ���߼�����ȡ��ָ�����η�����Ϣ
		paperTransferBatch.setBatNo(batNo);
		if( paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			pFlag = false;
			pErrInfo.getContent().insert(0, "�������η�����ϸ��Ϣʧ��");
		}
		//��������Ϣ��Ĭ�Ϸ����еĵ�����Ϣ����request��
		if(pFlag){
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		}else{
			request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
			//��������������
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
	 * ���������ҵõ�ҵ��ָ�����û���<br>
	 * ���������ǽ��յǼ�
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
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.ҵ��ָ��רԱ��ɫ,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findReceivedInside.action");
			resultURL = "/left.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���������Ҳ��ҽ��������ɵ�����<br/>
	 * ���������ǲ鿴���ռ�¼
	 * @return 
	 * @throws Exception
	 */
	public String findReceivedInside() throws Exception{
		ErrInfo pErrInfo = new ErrInfo();
		
		System.out.println(paperTransferBatchesQueryCondition.getTransferDateBegin());
		System.out.println(paperTransferBatchesQueryCondition.getTransferDateEnd());
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		//�õ�����ID
		int userID = Integer.parseInt(request.getParameter("nodeId"));
		System.out.println(userID);
		
		//����ҳ�ź�ÿҳ��ʾ��¼��
		dataPageInfo.setPageSize(20);
		
		List<PaperTransferBatch> paperTransferBatches = new ArrayList<PaperTransferBatch>();
		
		//����ҵ���߼�
		if(paperTransferManageService.findPaperTransferBatchesForInside(userID, EnumPaperTransferBatchesDealStatus.����������, paperTransferBatchesQueryCondition, dataPageInfo,paperTransferBatches,true, pErrInfo) == false){
			//pFlag = false;
			pErrInfo.getContent().insert(0, "���ҽ��ռ�¼ʧ��");
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
	 * ���ɵ���
	 * @return
	 * @throws Exception
	 */
	public String generateArchivesNO() throws Exception{
		System.out.println("---------���ɵ���-----------------");
		
		String message = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		
		System.out.println("batNo: "+ request.getParameter("batNo"));
		System.out.println("archivesTypeID: "+ request.getParameter("archivesTypeID"));
		
		String batNo = request.getParameter("batNo");
		
		//���մ���ĵ�������
		ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeID").toString()));
		
		//����ҵ���߼�
		if (paperTransferManageService.generateArchivesID(batNo, archivesType, pErrInfo) == false) {
			pFlag = false;
			pErrInfo.getContent().insert(0, "���ɵ���ʧ�ܣ�");
			System.out.println(pErrInfo.toString());
		}
		
		if(pFlag){
			message = "���ɵ��ųɹ���";
		}else{
			message = pErrInfo.toShortString();
			//����
		}
		response.getWriter().print(message);
		return null;
	}
	
	/**
	 * �����ڲ���ź͵������Ͳ��ҵ�����Ϣ
	 * @return ����һ������Ϣ
	 * @throws Exception
	 */
	public String findArchives() throws Exception{
	//	String message = "";
		//boolean pFlag = false;
		
		return "";
	}
	
	/**
	 * ���ݺ�����õ��б�ǩ�ĵ�����ֹ��Χ
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
	 * ����װ��
	 * @return
	 * @throws Exception
	 */
	public String archivesBoxing() throws Exception{
		//archivesBoxing(List<ArchivesInfo> archivesInfos,String archivesBoxBarcode,ErrInfo pErrInfo);
		return SUCCESS;
	}
    
	/**
	 * �õ�ҵ��ָ���ҵ��û�tree,��ָ���¼��Ĵ���Action<br/>
	 * ���ù��ܹ�����������
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
			//��ʼ���� 1...
			pErrPos = 1;
			List<UserInfo> userInfos = new ArrayList<UserInfo>();

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (userInfoManageService.findUserBySystemRole(EnumSystemRole.ҵ��ָ��רԱ��ɫ,userInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����û���Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			String tree = CreateTreeUtil.getUserTree(userInfos);
			request.setAttribute("tree",tree);
			request.setAttribute("proceseAction","YJJSAction_findRegisterOverBatsByInside.action");
			resultURL = "/left.jsp";
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
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���������Ҳ��ҽ��������ɵĵ�����Ϣ<br/>
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
		
		pFlag = paperTransferManageService.findPaperTransferBatchesForInside(userId, EnumPaperTransferBatchesDealStatus.����������, paperTransferBatchesQueryCondition, dataPageInfo, paperTransferBatches,true, pErrInfo);
		
		if(pFlag){
			request.setAttribute("paperTransferBatches", paperTransferBatches);
		}else{
			message = "�쳣��";
		}
		request.setAttribute("message", message);
		resultURL = "/YJJSGL/GLDATM.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����ָ�������ڵĵ���������Ϣ <br/>
	 * ճ����������
	 * @return ���������ڵ�������б�
	 * @throws Exception
	 */
	public String findArchivesTypesByBatNoForGL() throws Exception{
		//String message = "";
		//boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();

		//����type ����ҳ�������ǲ鿴�����ƽ�
		HttpServletRequest request = ServletActionContext.getRequest();

		String batNo = request.getParameter("batNo");
		paperTransferBatch.setBatNo(batNo);
		//�贫�����
		Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = new HashMap<Integer, PaperTransferBatchesArchvTypeDetail>();
		
		//����ҵ���߼�����ȡ��ָ�����η�����Ϣ
		if(paperTransferManageService.findPaperTransferBatchesArchvTypeDetails(paperTransferBatch, paperTransferBatchesArchvTypeDetails, pErrInfo) == false){
			//pFlag = false;
			System.out.println(pErrInfo.toString());
		}
		//��������Ϣ��Ĭ�Ϸ����еĵ�����Ϣ����request��
	
		request.setAttribute("paperTransferBatchesArchvTypeDetails", paperTransferBatchesArchvTypeDetails);
		
		request.setAttribute("batNo", batNo);
		request.setAttribute("archivesTypes", SystemInitializer.getInstance().getPlaneArchivesTypes());
		resultURL = "/YJJSGL/dlg_GLDATM.jsp";
		return SUCCESS;
	}
	
	/**
	 * �������������
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
	 * ����װ�У��ʺϽ�һ�ݵ���װ�����
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
			//��ʼ���� 1...
			pErrPos = 1;
			String archivesBarcode = request.getParameter("archivesBarcode");
			String boxBarCode = request.getParameter("archivesBoxBarcode");
			
			//��ʼ����2...
			if (pFlag) {
				List<String> archivesBarCodes = new ArrayList<String>();
				archivesBarCodes.add(archivesBarcode);
				pErrPos = 2;
				if(paperTransferManageService.archivesBoxing(archivesBarCodes, boxBarCode, pErrInfo) == false){
				   System.out.println(pErrInfo.toString());	
				}
			}
			
			//��ѯ������Ϣ
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
			}

			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ����װ�У��ʺϽ���ݵ���װ�����
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
	 * �������Ӻ����Ƴ�
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
			//��ʼ���� 1...
			pErrPos = 1;
			String archivesBarcodeStrs [] = request.getParameterValues("archivesBarcodes");

			List<String> archivesBarcodes = new ArrayList<String>();
			for (String archivesBarcode : archivesBarcodeStrs) {
				archivesBarcodes.add(archivesBarcode);
			}
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(storeroomArchivesInfoManageService.updateArchivesBoxBarcode(archivesBarcodes, null, pErrInfo) == false){
					pFlag = false;
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
			}

			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * ���ݰ�����Ҿ����ļ�Ŀ¼
	 * @return ���ؾ����ļ�Ŀ¼�б�
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
			//��ȡURL���ݹ����Ĳ���
			int archivesTypeID =Integer.parseInt(request.getParameter("archivesTypeID"));
			//int archivesInfoNBXH = Integer.parseInt(request.getParameter("NBXH"));//�����ڲ����
			pErrPos = 2;
			
			/*//��ѯ���ð����µľ����ļ��б�			
			if (pFlag) {			
				if(archivesManageService.findChildArchivesInfosByNBXH(archivesType, archivesInfoNBXH, archivesInfos, pErrInfo)){					
					setArchiveInfos(archivesInfos);//����ģ������
					
				}else{
					pFlag = false;
				}
			}*/
			
			pErrPos = 3;
			//��������ص�ҳ��
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
	 * �������ͨ��
	 */
	public int paperCheckPassDWR(int archivesTypeID,int NBXH,String batNo,String deptType,HttpSession session) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		UserInfo userInfo = null;
		System.out.println("������ˣ� "+deptType);
		ArchivesType archivesType = null;
		ArchivesInfo archivesInfo = null;

		try {
			pErrPos = 1;

			userInfo = (UserInfo)session.getAttribute("userInfo");
			
			if(pFlag){
			    pErrPos = 2;
				if(archivesTypeID == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//�õ��ڲ���ŵļ��Ϲ���ҵ���߼����赵����Ϣ���󼯺�
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("�õ������ڲ����ʧ��");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {
				if("YWZDS".equals(deptType)){
					if (paperTransferManageService.paperCheckPass(userInfo.getUserID(), archivesType, archivesInfo, batNo, EnumCheckResult.ҵ��ָ�������ͨ��, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ����������ʧ��");
					}
				}else if("DAGLS".equals(deptType)){
					if (paperTransferManageService.paperCheckPass(userInfo.getUserID(), archivesType, archivesInfo, batNo, EnumCheckResult.�������������ͨ��, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ����������ʧ��");
					}
				}
				
			}
			
			if (pFlag) {
				return 1;
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
				
				throw new Exception(pErrInfo.toShortString());
			}
			//���پֲ�����
			throwable = null;
		}
		return 1;
	}

	//DWR:��˲�ͨ��
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
					pErrInfo.getContent().append("�õ���������ʧ�ܣ�");
				}else{
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				}
			}
			
			//�õ��ڲ���ŵļ��Ϲ���ҵ���߼����赵����Ϣ���󼯺�
			if(pFlag){
				pErrPos = 5;
				if(NBXH ==0){
					pFlag = false;
					pErrInfo.getContent().append("�õ������ڲ����ʧ��");
				}else{
					archivesInfo = ArchivesInfoFactory.newArchivsInfo(archivesTypeID, NBXH);
				}			
			}

			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {	
				if("YWZDS".equals(deptType)){
					if (paperTransferManageService.paperCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason,batNo, EnumCheckResult.ҵ��ָ������˴��,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ������˴��ʧ��");
					}
				}else if("DAGLS".equals(deptType)){
					if (paperTransferManageService.paperCheckBack(userInfo.getUserID(), archivesType, archivesInfo, backReason,batNo, EnumCheckResult.������������˴��,pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���õ���������Ϊ������˴��ʧ��");
					}
				}
			}
			
			if (pFlag) {
				success = 1;
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
				
				throw new Exception(pErrInfo.getContent().toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return success;
	}
	
	/**
	 * ����ָ������ָ������������û������ĵĵ�һ��������Ϣ�ĵ���
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
			//��ʼ���� 1...
			pErrPos = 1;
			String batNo = request.getParameter("batNo");
			int archivesTypeID = Integer.parseInt(request.getParameter("archivesTypeID"));
			
			ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			System.out.println("batNo:"+batNo +"   archivesTypeID: "+archivesTypeID);

			//����ҵ���߼��õ�����..
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
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���ݺ������ҵ�����Ϣ
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public String findArchivesInfoByBoxBarcode() throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		HttpServletRequest request = ServletActionContext.getRequest();
		List<StoreroomArchivesInfo> storeroomArchivesInfos = null;
		try {
			//��ò���
			pErrPos = 1;
			String archivesBoxBarcode = request.getParameter("archivesBoxBarcode");

			//����ҵ���߼����ҵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				storeroomArchivesInfos = new ArrayList<StoreroomArchivesInfo>();
				if (storeroomArchivesInfoManageService.findByBoxBarcode(archivesBoxBarcode,storeroomArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݺ�������ҵ�����Ϣʧ�ܣ�");
					System.out.println(pErrInfo.toString());
				}
			}
			
			request.setAttribute("archivesBoxBarcode", archivesBoxBarcode);
			request.setAttribute("storeroomArchivesInfos", storeroomArchivesInfos);
			resultURL = "/YJJSGL/DAZH.jsp";
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
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
}
