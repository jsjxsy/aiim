package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import com.opensymphony.xwork2.ActionSupport;
import com.orifound.aiim.bll.service.IArchivesInfoQueryService;
import com.orifound.aiim.bll.service.IAttachedFileManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IUserDefinedSearchManageService;
import com.orifound.aiim.bll.service.impl.ArchivesInfoWorkingManageServiceImpl;
import com.orifound.aiim.bll.service.impl.AttachedFileAccessControlServiceImpl;
import com.orifound.aiim.bll.service.impl.UseScopesAccessControlServiceImpl;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserDefinedSearch;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.testDate.ReturnTree;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.GenerateHtmlCodeUtil;
import com.orifound.aiim.web.util.WebCommonUtil;
import com.orifound.commons.acservice.IAttachedFileAccessControlService;
import com.orifound.commons.acservice.IUseScopesAccessControlService;

/**
 * �ۺϲ�ѯAction
 * @author Administrator
 *
 */
public class IntegratedQueryAction extends ActionSupport{

	private static final long serialVersionUID = 1L;

	
	static Log logger = LogFactory.getLog(IntegratedQueryAction.class);
	/**
	 * ��ҳ��Ϣ��
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	/**
	 * ͨ��ע�⣬ע�뻮�ط��ʿ��ƹ������
	 */
	private IUseScopesAccessControlService useScopesAccessControlService;
	
	/**
	 * ͨ��ע�⣬ע��ϵͳ���ù������
	 */
	@Autowired
	private IConfigManageService configManageService ;

	/**
	 * ͨ��ע�⣬ע�뵵����ѯ������
	 */
	@Autowired
	private IArchivesInfoQueryService archivesInfoQueryService;
	
	/**
	 * ͨ��ע�⣬ע���û��Զ����ѯ���������
	 */
	@Autowired
	private IUserDefinedSearchManageService  userDefinedSearchManageService;
	
	/**
	 * ����ԭ�ĵ����ļ����ʿ��ƹ��������
	 */
	@Autowired
	private IAttachedFileAccessControlService attachedFileAccessControlService;
	
	/**
	 * ͨ��ע�⣬ע��ԭ�Ĺ��������
	 */
	@Autowired
	private IAttachedFileManageService attachedFileManageService; 

	/**
	 * �����ѯ:�õ�����������
	 * @return 
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action")
	public String getArchivesTypeTree()throws Exception{
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//��application��ȡ�����е�������
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
			
			
			//���������༯�Ϲ���������������javascript����
			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
			
			//������ͨ��request����ҳ��
			request.setAttribute("tree", tree);
			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
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
		return SUCCESS;
	}
	
	/**
	 * �ؼ��ֲ�ѯ:�õ�����������
	 * @return 
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getArchivesTypeTree.action")
//	public String getKeyQueryArchivesTypeTree()throws Exception{
//		String htmlCode = "";
//		boolean pFlag = true;
//		int pErrPos = 0;
//		Throwable throwable = new Throwable();
//		ErrInfo pErrInfo = new ErrInfo();
//		HttpServletRequest request = ServletActionContext.getRequest();
//		ServletContext application = ServletActionContext.getServletContext();
//		
//		try {		
//			pErrPos = 1;			
//			//��application��ȡ�����е�������
//			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
//						
//			//���������༯�Ϲ���������������javascript����
//			String tree  = CreateTreeUtil.getArchivesTypeTree(archivesTypes);
//			
//			//������ͨ��request����ҳ��
//			request.setAttribute("tree", tree);
//			
//			request.setAttribute("proceseAction", "integratedQueryAction_findArchivesByKeyWord.action");
//			
//		} catch (Exception e) {
//			//�쳣����
//			pFlag = false;
//			pErrInfo.getContent().append(e.toString());
//			pErrInfo.setException(e);
//		} finally {
//			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
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
//				
//				if(pErrInfo.getException() != null){
//					logger.error(pErrInfo.toString());
//					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
//				}else{
//					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
//				}
//				request.setAttribute("htmlCode",htmlCode);
//			}
//		}
//		return SUCCESS;
//	}
	
	/**
	 * �Զ���������ѯ : �õ�ָ���û������Զ���Ĳ�ѯ��
	 * @return
	 * @throws Exception
	 */
	public String getUserDefinedSearchTree() throws Exception{
		String htmlCode = "";
		String forWard = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<UserDefinedSearch> userDefinedSearchs = new ArrayList<UserDefinedSearch>();
		
		try {		

			pErrPos = 1;
			//����û��Ƿ����
			if(userInfo==null){
				pFlag = false;				
			}

			//����ҵ���߼�
			if(pFlag){
				pErrPos = 2;
				if(userDefinedSearchManageService.findUserDefinedSearchsByUserID(userInfo.getUserID(), userDefinedSearchs, pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().insert(0,"��ȡ�û��Զ���Ĳ�ѯ����ʧ�ܣ�");
				}else{
					forWard = "toDefinedQuery";
				}	
			}
	
			//���������༯�Ϲ���������������javascript����
			String tree  = CreateTreeUtil.getArchivesUserDefinedSearchTree(userDefinedSearchs);

			//������ͨ��request����ҳ��
			request.setAttribute("tree", tree);
			
			request.setAttribute("proceseAction", "integratedQueryAction_findArchivesByDefinedQuery.action");
			
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
		return forWard;
		
	} 
	

	
	/**
	 * �������ѯ: �õ���CheckBox�ĵ���������	 
	 * @return
	 * @throws Exception
	 */
//	@UCLkey(value = "guest")
	public String getArchivesTypeTreeWithCheckBox() throws Exception{	
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//��application��ȡ�����е�������
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
		
			//���������༯�Ϲ���������������javascript����
			String tree  = CreateTreeUtil.getArchivesTypeCheckBoxTree(archivesTypes);		
			
			//������ͨ��request����ҳ��
			request.setAttribute("tree", tree);			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
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
		return "checkBox_Tree";
	}
	
	/**
	 * �������ѯ: �õ���CheckBox�ĵ���������	 
	 * @return
	 * @throws Exception
	 */
//	@UCLkey(value = "guest")
	public String getDefinedQueryArchivesTypeTreeWithCheckBox() throws Exception{	
		String htmlCode = "";
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = ServletActionContext.getRequest();
		ServletContext application = ServletActionContext.getServletContext();
		
		try {		
			pErrPos = 1;			
			//��application��ȡ�����е�������
			List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(((SystemInitializer)application.getAttribute("systemInitializer")).getArchivesTypes().values());
			
			//���������༯�Ϲ���������������javascript����
			String tree  = CreateTreeUtil.getArchivesTypeCheckBoxTree(archivesTypes);		
			
			//������ͨ��request����ҳ��
			request.setAttribute("tree", tree);			
			request.setAttribute("proceseAction", "integratedQueryAction_getFindHtmlCodeBuyArchivesTypeId.action");
			
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
		return "toDlg_DefinedQuery";
	}
	
	/**
	 * �Զ���������ѯ<br/>
	 * �õ���ǰ���û����Զ���������ѯ��
	 * @return
	 * @throws Exception
	 */
	//@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_getConditionTree.action")//����
//	public String getConditionTree() throws Exception{
//        HttpServletRequest request = ServletActionContext.getRequest();
//		
//		String tree = ReturnTree.getConditionTree();
//		
//		request.setAttribute("tree", tree);
//		request.setAttribute("proceseAction", "");
//		
//		return "";
//	}
	
	
	
	
	/**
	 * �����ѯ-->�������ڵ��¼����õ�ָ�������¿ɲ�ѯ��������
	 */
	public String getFindHtmlCodeBuyArchivesTypeId() throws Exception {
		System.out.println("getFindHtmlCodeBuyArchivesTypeId is successful!!!!");
		String htmlCode = "";		
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId;//����������	
	
		try {
			
			pErrPos = 1;
			//������֤
			if( request.getParameter("nodeId")==null || "".equals(request.getParameter("nodeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("�Ҳ������������ţ�nodeId����ʧ��");
			}
			
			
			//���ݵ������������Ӧ�Ĳ�ѯ����
			if(pFlag){
				pErrPos = 2;
				archivesTypeId = Integer.parseInt(request.getParameter("nodeId"));
				htmlCode = this.getHtmlCodeByType(archivesTypeId,null);
				//����������ҳ��
				request.setAttribute("htmlCode", htmlCode);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
				request.setAttribute("archivesTypeId", archivesTypeId);
				request.setAttribute("initialPage",true );
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
				System.out.println("error:"+pErrInfo.toString());
			}
		}
		return "toFLCX";
	}

	/**
	 * ����������ѯ�������¼����õ�����ID 
	 *  �õ��˷����¿ɲ�ѯ��������
	 * @param pErrInfo ���ش���ʧ�ܵĴ���ԭ������
	 * @return
	 */
	public String getFindHtmlCodeBuyArchivesTypeIds(int [] archivesTypeIds,HttpSession session) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String htmlCode = "";
		List<ArchivesType> archivesTypes = null;
		TreeMap<String, ArchivesTypeDataItem> archivesTypeDataItems = null;
		try {
			pErrPos = 1;
			//HttpSession session = ServletActionContext.getRequest().getSession(false);
			session.setAttribute("archivesTypeIds", archivesTypeIds);			
			
			
			if(archivesTypeIds == null || archivesTypeIds.length == 0){
				pFlag = false;
				pErrInfo.getContent().append("δѡ�񵵰����ͣ�");
			}

			//����ArchivesType���ϲ�����ҵ���߼�
			if(pFlag){
				pErrPos = 2;
				archivesTypes = new ArrayList<ArchivesType>();
				ArchivesType archivesType = null;
				for (int archivesTypeId : archivesTypeIds) {
					archivesType = new ArchivesType();
					archivesType.setID(archivesTypeId);
					archivesTypes.add(archivesType);
				}
				archivesTypeDataItems = new TreeMap<String, ArchivesTypeDataItem>();
			}
			
			if(pFlag){
				pErrPos = 3;
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(archivesTypeDataItems, null);
			}
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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
			}
		}
		return htmlCode;
	}
	
	/**
	 * �����ѯ--> 
	 * �򵥲�ѯ:���ݵ������ͺ��������ҵ�����Ϣ<br/>
	 * �߼���ѯ:���ݵ������ͺ��������ҵ�����Ϣ<br/>
	 * @return ������Ϣ�б�
	 * @throws Exception
	 */
//	@UCLkey(value = "/aiim/ZHCX/integratedQueryAction_findArchivesByCondition.action")//����
	@SuppressWarnings("unchecked")
	public String findArchivesByCondition() throws Exception{
		int pErrPos = 0;
		boolean pFlag= true;
		boolean pAdvanceQueryFlag = false;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String advanceQueryCondition = "";
		String forWard = "";
		String message = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();		
		boolean inputTrue = false;//����Ϊ�ձ�ʶ�����ڹ��˵���Щ��������ֶ�
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		StringBuilder querySQL = new StringBuilder();//����ѯ��������ת����SQL����WHERE����
	

		try{
			
			//������֤		
			if(archivesInfoQueryService==null){
				pFlag = false;
				pErrInfo.getContent().append("�ۺϲ�ѯ������ע��ʧ�ܡ�");
			}
			
			//��鲢��ȡ�߼���ѯ����			
			if(request.getParameter("advanceQueryCondition") == null || "".equals(request.getParameter("advanceQueryCondition"))){
				advanceQueryCondition = "";
			}else{//���ղ���advanceQueryCondition�ɹ�
				advanceQueryCondition = request.getParameter("advanceQueryCondition").trim();
			}
						
			pErrPos = 1;
			//���ݵ��������ţ����쵵���������			
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("���������Ż�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeId�ɹ�
				archivesType = new ArchivesType();
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
				//archivesType.setID(archivesTypeId);
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
			}
			
			//�жϵ�ǰ��ѡ��ĵ��������ǲ�����ĩ�ڵ㣬�ֱ����������¼�
			if (pFlag) {				
				if(archivesType.getEndFlag()==true){//��ǰ���������ĩ�ڵ�
					archivesTypes.add(archivesType);				
				}else{//��ǰ����Ĳ���ĩ�ڵ㣬�ͻ�ýڵ��µ����е����������
					Map<Integer, ArchivesType> endArchivesTypes = new HashMap<Integer, ArchivesType>();
					//�Ӹ��û��л�ȡ�������࣬ȡ��ǰѡ�������ӽڵ㣬ͨ�����߷���ȡ��������ĩ�㼯��
					CommonUtil.getEndArchivesTypesByTree(userInfo.getArchivesTypes().get(archivesTypeId).getChildArchivesTypes(), endArchivesTypes, pErrInfo);
					archivesTypes.addAll(endArchivesTypes.values());
				}
			}
			
			//���ʿ��ƣ����ָ���ĵ�����Դ�����Ƿ���Է���
			if (pFlag) {
				for (ArchivesType type : archivesTypes) {
					if(userInfo.getAccessControlService().checkArchivesTypeACL(type.getID())==false){
						pFlag = false;
						pErrInfo.getContent().append("�û���Ȩ����" + type.getFullName() + "��������");
					}
				}				
			}
						
			//�жϲ�ѯ��ʽ���򵥲�ѯ/�߼���ѯ
			if(pFlag){
				pErrPos = 2;
				if("".equals(advanceQueryCondition)){//����߼���ѯ�ַ���Ϊ�գ���˵���Ǽ򵥲�ѯ
					pAdvanceQueryFlag = false;
				}else{//����Ϊ�գ���˵���Ǹ߼���ѯ
					pAdvanceQueryFlag = true;
				}				
			}			
			
			//����Ǽ򵥲�ѯ�͹����������϶�������Ǹ߼���ѯ���˹���ʡ��
			if(pFlag){
				pErrPos = 3;
				if(pAdvanceQueryFlag == false){				
					archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
					ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
					Enumeration enumeration = request.getParameterNames();//ȡ�����е�ҳ�洫�����������������
					String [] values = null;//����ĳ�������������vlueֵ
					ArchivesTypeDataItem dataItem = null;
					String parameterName = null;
					
					while(enumeration.hasMoreElements()){
						inputTrue = false;
						parameterName = (String)enumeration.nextElement();//ȡ������������					
						//�������ز���archivesTypeId\dataPageInfo.currentPage\advanceQueryCondition(���ڴ��ݸ߼���ѯ����)
						if( !"advanceQueryCondition".equals(parameterName) && !"archivesTypeId".equals(parameterName) && !"dataPageInfo.currentPage".equals(parameterName)){
							values = request.getParameterValues(parameterName);//ȡ����������Ӧ��ֵ ��ֵΪdataItem��Id
							dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
																
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
			}
			
			//����ѯ��������������޸�����
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkArchivesInfoUseQueryConditions(new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ѯ������Ϣʧ��: ");
				}
			}
			
			//����ѯ����ת����SQL����е�WHERE����
			if (pFlag) {
				pErrPos = 5;
				getSqlForArchivesInfoInputQueryConditions(new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), querySQL, pErrInfo);
			}
			
			//����ҵ���߼�
			if(pFlag){	
				pErrPos = 6;
				dataPageInfo.setPageSize(10);
				if(pAdvanceQueryFlag){//�߼���ѯ
					if(archivesInfoQueryService.queryClassified(userInfo, archivesTypes,advanceQueryCondition , dataPageInfo, archivesInfos, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("�����ѯ���߼���ѯʧ�ܣ�");
					}					
				}else{//�򵥲�ѯ
					//if(archivesInfoQueryService.queryClassified(userInfo,archivesTypes, new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()), dataPageInfo, archivesInfos, pErrInfo)==false){
					if(archivesInfoQueryService.queryClassified(userInfo,archivesTypes, querySQL.toString(), dataPageInfo, archivesInfos, pErrInfo)==false){	
						pFlag = false;
						pErrInfo.getContent().append("�����ѯ���򵥲�ѯʧ�ܣ�");
					}
				}	
			}
			
			
			//��ҵ���߼���ȡ�Ľ�����ظ�ҳ�� 			
	        if(pFlag){
	        	pErrPos = 7;
				request.setAttribute("htmlCode", this.getHtmlCodeByType(archivesTypeId,archivesInfoQueryConditions));
				request.setAttribute("archivesInfos", archivesInfos);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesType.getID()).getDataItemsForListDisplay());
				request.setAttribute("archivesTypeId", archivesTypeId);
				request.setAttribute("advanceQueryCondition", advanceQueryCondition);
				forWard  = "toFLCX";//��ת�������ѯҳ��
			}
		}catch(Exception e){
			//�����쳣����			
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
		}finally{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				if(pErrInfo.getException() != null){
					logger.warn(pErrInfo.toString());//��¼��־
					request.setAttribute("pErrInfo",pErrInfo);
					forWard  = "error";
				}else{
					message = pErrInfo.toShortString();
					request.setAttribute("htmlCode", this.getHtmlCodeByType(archivesTypeId,archivesInfoQueryConditions));
					request.setAttribute("errMsg", message);
					forWard  = "toFLCX";
				}
			}
		}
		return forWard;
	}

	/**
	 * ����QuerySQL��ѯ�����͵��������ѯ������Ϣ	<br>
	 * ���ڿ������ѯ���Զ���������ѯ�в鿴�������������ϸ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findArchivesByQuerySQL() throws Exception{
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();	
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String forWard = "";
		boolean pFlag = true;
		int pErrPos = 0;		
		String htmlCode = "";		
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		UserInfo userInfo = null;
		String querySQL = ""; //��ѯ����
		String keyWordSQL = "";//���ڲ�ѯ�Ĺؼ���
		String whereSQL = "";//���ڲ�ѯ��WHERE ���
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);//��������userInfoʵ��

		try {
		
			pErrPos = 1;	
			//��ȡ����������
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ����������ʧ�ܣ�");
			}else{					
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
			}				
			//��֤
			if(request.getParameter("keyWordSQL") == null && request.getParameter("querySQL") == null ){
				pFlag = false;
				pErrInfo.getContent().append("���ڲ�ѯ�Ĺؼ��ֻ��ѯ�����Ƿ�Ϊ�գ�");
			}
			
			//��ȡquerySQL��ѯ����
			if(request.getParameter("querySQL") != null &&  !"".equals(request.getParameter("querySQL").trim()) ){
				querySQL = request.getParameter("querySQL").trim();
			}	
			
			//��ȡkeyWordSQL�ؼ���
			if( request.getParameter("keyWordSQL") != null && !"".equals(request.getParameter("keyWordSQL").trim()) ){
				keyWordSQL = request.getParameter("keyWordSQL").trim();
			}	
			
			
			//�������archivesTypes
			if(pFlag){
				pErrPos = 2;				 
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId);
				archivesTypes.add(archivesType);
			}
			
			//����ҵ���߼�
			if(pFlag){
				pErrPos = 3;
				//����ҳ��ÿҳ��ʾ�ļ�¼��
				dataPageInfo.setPageSize(10);
				if( !"".equals(querySQL) ){//ͨ����ѯ������ѯ
					whereSQL = querySQL;
				}else{//ͨ���ؼ��ּ�����ѯ
					StringBuilder stringBuilderSQL = new StringBuilder();
					getKeyQuerySQL(keyWordSQL, archivesType.getDataItemsForUseQuery(), stringBuilderSQL, pErrInfo);
					whereSQL = stringBuilderSQL.toString();
				}
				
				//����ͨ��SQL������ѯָ��������Ϣ
				if(archivesInfoQueryService.queryClassified(userInfo, archivesTypes,whereSQL , dataPageInfo, archivesInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("����SQL������ѯ������Ϣʧ�ܣ�");
				}				
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 4;
				request.setAttribute("dataPageInfo", dataPageInfo);
				request.setAttribute("archivesInfos",archivesInfos);
				request.setAttribute("archivesType", archivesType);
				request.setAttribute("querySQL", querySQL);
				request.setAttribute("keyWordSQL",keyWordSQL);
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
				forWard  = "toListDetail";//��ת���鿴ָ���������ϸ������Ϣ
			}			
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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
		return forWard;		
	}


	
	/**
	 * �������ѯ: -> ��ȡ���������������������ļ�¼��
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String getCountInfoByArchivesTypes() throws Exception{
		String forWard = "";
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
		String htmlCode = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = null;
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		boolean inputTrue = false;//����Ϊ�ձ�ʶ�����ڹ��˵���Щ��������ֶ�
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		String[] strArchivesTypeIds = {};//�������മ
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		

		try {
			//��ȡ������archivesTypeIdstr�������മ��strNameValues����ѯ������
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("���������ż��ϻ�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeIdstr�ɹ�
				System.out.println("archivesTypeIdstr: "+request.getParameter("archivesTypeIdstr"));
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//ȡ��һ������������
			}	
			
			//��ȡ��������,�����뵽�������༯����
			if (pFlag) {
				pErrPos = 2;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);					
				}
			}
			
			//�����������϶���
			if(pFlag){	
				pErrPos = 3;						
				archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;
				Enumeration enumeration = request.getParameterNames();//ȡ�����е�ҳ�洫�����������������
				String [] values = null;//����ĳ�������������vlueֵ
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				
				while(enumeration.hasMoreElements()){
					inputTrue = false;
					parameterName = (String)enumeration.nextElement();//ȡ������������					
					//�������ز���archivesTypeId\dataPageInfo.currentPage\advanceQueryCondition(���ڴ��ݸ߼���ѯ����)
					if(!"archivesTypeIdstr".equals(parameterName)){
						values = request.getParameterValues(parameterName);//ȡ����������Ӧ��ֵ ��ֵΪdataItem��Id
						dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
															
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
								archivesInfoQueryCondition.setMinValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								archivesInfoQueryCondition.setMaxValue(new String(values[1].getBytes("ISO8859-1"),"utf-8"));
							}else{
								archivesInfoQueryCondition.setValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);
						}
						
					}
				}				
			}
			
			
			//����ҵ���߼�ִ�в�ѯ����
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().append("��ѯ���������ĸ��������������ʧ�ܡ�");
				}else{
					forWard = "toKMLCX";
				}
			}
			
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//���ý��������
				request.setAttribute("beginFlag", false);
				request.setAttribute("querySQL", querySQL.toString());
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));
			}			
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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

		return forWard;
		
	}
	
	/**
	 * �ؼ��ּ�����λ�ã��������У�<br>
	 * ����ƥ�����пɲ�ѯ�ֶΣ�ͳ�����������е�������
	 * @return
	 * @throws Exception
	 */
	public String findArchivesCountsByKeyWord() throws Exception{

		String htmlCode = "";		
		String forWord = "toKMLCX";
		HttpServletRequest request = ServletActionContext.getRequest();
		boolean pFlag = true;
		int pErrPos = 0;
		ArchivesType archivesType = new ArchivesType();
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId = 0;//����������	
		List<ArchivesInfo> archivesInfos = new ArrayList<ArchivesInfo>();	
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery = null;
		//StringBuilder querySQL = new StringBuilder();//���ڲ�ѯ��SQL���
		String keyWord = "";
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		String[] strArchivesTypeIds = {};//�������മ
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		
		try {			
			//��ȡ������archivesTypeIdstr�������മ��strNameValues����ѯ������
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("���������ż��ϻ�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeIdstr�ɹ�
				System.out.println("archivesTypeIdstr: "+request.getParameter("archivesTypeIdstr"));
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//ȡ��һ������������
			}	
			
			//��ȡ�ؼ���
			if(request.getParameter("keyWord") == null){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�ؼ���ʧ�ܣ�");
			}else{
				keyWord = request.getParameter("keyWord").trim();
			}
			
			//��ȡ��������,�����뵽�������༯����
			if (pFlag) {
				pErrPos = 2;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);
				}
			}
			
			//����ҵ���߼�
			if(pFlag){
				pErrPos = 3;
				if(archivesInfoQueryService.queryCrossClassifiedByKeyWord(userInfo, archivesTypes, keyWord, archivesTypeCountInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�ؼ��ּ���ʧ�ܣ�");
				}else{
					System.out.println("archivesTypeCountInfos.size():"+archivesTypeCountInfos.size());
					if(archivesTypeCountInfos.size()>0){
						System.out.println(archivesTypeCountInfos.get(0).getCountNum()+":::"+archivesTypeCountInfos.get(0).getQuerySQL());
					}
				}
			}
			
			//���ݵ������������Ӧ�Ĳ�ѯ����
			if(pFlag){
				pErrPos = 3;
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//���ý��������
				request.setAttribute("keyWordSQL", request.getParameter("keyWord"));
				
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));				
				request.setAttribute("beginFlag", false);
	//			request.setAttribute("querySQL", querySQL.toString());
		//		request.setAttribute("resultSize",archivesInfos.size());//��¼������
		//		request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
		//		request.setAttribute("archivesTypeId", archivesTypeId);
			}	
			
			
//			
//			//���keyWordΪnull����ִ�в�ѯ����
//			if(request.getParameter("keyWord")!=null){
//				keyWord = request.getParameter("keyWord");
//				
//				dataItemsForUseQuery = archivesType.getDataItemsForUseQuery();
//				if(getKeyQuerySQL(keyWord, dataItemsForUseQuery, querySQL, pErrInfo)==false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0,"����SQL������");
//				}
//				
//				//����ҵ���߼�
//				if (pFlag) {
//					dataPageInfo.setPageSize(10);
//					if(archivesInfoQueryService.queryClassified(userInfo, archivesType,querySQL.toString() , dataPageInfo, archivesInfos, pErrInfo)==false){
//						pFlag = false;
//						pErrInfo.getContent().insert(0,"ͨ��SQL����ѯ������Ϣʧ�ܣ�");
//					}
//				}
//					
//			}
//			
//			//���ݵ������������Ӧ�Ĳ�ѯ����
//			if(pFlag){
//				pErrPos = 3;
//				request.setAttribute("keyWord", request.getParameter("keyWord"));
//				request.setAttribute("archivesInfos", archivesInfos);
//				request.setAttribute("resultSize",archivesInfos.size());//��¼������
//				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForListDisplay());
//				request.setAttribute("archivesTypeId", archivesTypeId);
//			}		

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
				System.out.println("ERROR:"+pErrInfo.toString());
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.toString());
					htmlCode = "<script>alert('"+pErrInfo.toString()+" "+pErrInfo.getException()+"!');</script>";
				}else{
					htmlCode = "<script>alert('"+pErrInfo.toShortString()+"!');</script>";
				}
				request.setAttribute("htmlCode",htmlCode);
			}
		}
		return forWord;
	}
	
	
	
	/**
	 * �Զ���������ѯ: -> �����Զ���������ѯ
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String addUserDefinedQuery() throws Exception{
		
		boolean pFlag = true;
		ErrInfo pErrInfo = new ErrInfo();
		int pErrPos = 0;
		Throwable throwable = new Throwable();	
		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
		String htmlCode = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions = new TreeMap<String, ArchivesInfoQueryCondition>();
		int archivesTypeId = 0;
		ArchivesType archivesType =null ;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		boolean inputTrue = false;//����Ϊ�ձ�ʶ�����ڹ��˵���Щ��������ֶ�
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		String[] strArchivesTypeIds = {};//�������മ
		String queryName = "";//����
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();

		try {
			//��ȡ������archivesTypeIdstr�������മ��strNameValues����ѯ������
			pErrPos = 1;
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("���������ż��ϻ�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeIdstr�ɹ�
				strArchivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
				archivesTypeId = Integer.parseInt(strArchivesTypeIds[0]);//ȡ��һ������������
			}	
			
			if(request.getParameter("queryName") == null || "".equals(request.getParameter("queryName"))){
				pFlag = false;
				pErrInfo.getContent().append("�Զ����������Ʋ���Ϊ�գ�");
			}else{//���ղ���archivesTypeIdstr�ɹ�
				queryName = request.getParameter("queryName");
			}	
			
			//�����û��Զ���������ѯ����
			if (pFlag) {
				pErrPos = 2;
				userDefinedSearch.setName(queryName);
				userDefinedSearch.setArchivesIDString(request.getParameter("archivesTypeIdstr"));
				userDefinedSearch.setUserID(userInfo.getUserID());			
			}
			
			
			//����Զ������������Ƿ����
			if (pFlag) {
				pErrPos = 3;
				if(userDefinedSearchManageService.checkQueryNameExist(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "");
				}else{
					if(userDefinedSearch.getID()!=0){
						pFlag = false;
						pErrInfo.getContent().insert(0,"'" + queryName + "'" + "�������Ѿ����ڣ���Ļ��������ԣ�");
					}
				}
				
			}
			
			
			//��ȡ��������,�����뵽�������༯����
			if (pFlag) {
				pErrPos = 4;
				for(int i= 0;i<strArchivesTypeIds.length;i++){
					archivesType = new ArchivesType();
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(strArchivesTypeIds[i]));
					archivesTypes.add(archivesType);
				}
			}
	
			
			//�����������϶���
			if(pFlag){
				pErrPos = 5;
				ArchivesInfoQueryCondition archivesInfoQueryCondition = null;				
				Enumeration enumeration = request.getParameterNames();//ȡ�����е�ҳ�洫�����������������
				String [] values = null;//����ĳ�������������vlueֵ
				ArchivesTypeDataItem dataItem = null;
				String parameterName = null;
				
				while(enumeration.hasMoreElements()){
					inputTrue = false;
					parameterName = (String)enumeration.nextElement();//ȡ������������
					if(!"archivesTypeIdstr".equals(parameterName) && !"queryName".equals(parameterName) && !"archivesTypeId".equals(parameterName)){
						values = request.getParameterValues(parameterName);//ȡ����������Ӧ��ֵ ��ֵΪdataItem��Id
												
						//��������Ϊ�յĲ�ѯ����
						for(int i=0;i<values.length;i++){
							System.out.println(":"+values[i]+":");
							if(!"".equals(values[i])){							
								inputTrue = true;
							}
						}
						
						//�����������ݵĲ�ѯ����ʵ������archivesInfoQueryCondition���󲢼ӵ�archivesInfoQueryConditions������
						if(inputTrue){
							dataItem = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(parameterName);
							archivesInfoQueryCondition = new ArchivesInfoQueryCondition(dataItem);//����archivesInfoQueryCondition����
							
							if(values.length>1){
								archivesInfoQueryCondition.setMinValue(values[0]);
								archivesInfoQueryCondition.setMaxValue(values[1]);
								//archivesInfoQueryCondition.setMinValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								//archivesInfoQueryCondition.setMaxValue(new String(values[1].getBytes("ISO8859-1"),"utf-8"));
							}else{
								//archivesInfoQueryCondition.setValue(new String(values[0].getBytes("ISO8859-1"),"utf-8"));
								archivesInfoQueryCondition.setValue(values[0]);
							}
							archivesInfoQueryConditions.put(parameterName,archivesInfoQueryCondition);
						}	
					}
				}				
			}
			
			
			//����ҵ���߼���ȡSQL���
			if (pFlag) {
				pErrPos = 6;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(archivesInfoQueryConditions.values()),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;				
					pErrInfo.getContent().insert(0,"����SQL���ʧ�ܣ�");
				}else{
					//��SQL�ַ��������û��Զ���������ѯ������
					userDefinedSearch.setWhereString(querySQL.toString());					
				}
			}
			
			
			
			//����ҵ���߼�����Զ���������ѯ
			if (pFlag) {
				pErrPos = 7;
				if(userDefinedSearchManageService.addUserDefinedSearch(userDefinedSearch, pErrInfo)==false){
					pFlag = false;				
					pErrInfo.getContent().insert(0,"�����Զ���������ѯʧ�ܣ�");
				}
			}

			//Ajax���ã�����ִ�н����ҳ��
			if(pFlag){
				ServletActionContext.getResponse().getWriter().print("��ӳɹ���");
			}else{
				ServletActionContext.getResponse().getWriter().print("���ʧ�ܣ�" + pErrInfo.toShortString());
			}
			
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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

		return null;
		
	}
	
	
	
	
	
	/**
	 * ����ָ�����������Ų��ҿ����ڸ߼���ѯ���������
	 * @return
	 * @throws Exception
	 */
	public String getAdvanceQueryItems() throws Exception{
	
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//ҳ��ȥ��
	//	String message = "";//���ڷ��ظ�ҳ�������Ϣ
		int archivesTypeId = 0;
		
		try {
			pErrPos = 1;
			//���ݵ��������ţ����쵵���������			
			if(request.getParameter("archivesTypeId") == null || "".equals(request.getParameter("archivesTypeId"))){
				pFlag = false;
				pErrInfo.getContent().append("���������Ż�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeId�ɹ�
				archivesTypeId = Integer.parseInt(request.getParameter("archivesTypeId"));
			}

			
			//����ȡ�����ݷ��ص�ҳ��
			if(pFlag){	
				pErrPos = 2;
				request.setAttribute("dataItems", SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery());
				request.setAttribute("archivesTypeId",archivesTypeId);
				forWard = "toAdvance";
				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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
				
		return forWard;
	}
	

	/**
	 * �������ѯ -->��ȡ������ѯ�ֶ�
	 * @return
	 * @throws Exception
	 */
	public String getPublicQueryItems() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//ҳ��ȥ��
		String[] str_archivesTypeIds = {};
		Map<String,ArchivesTypeDataItem> archivesTypeDataItems = null;
		List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList = new ArrayList<Map<String,ArchivesTypeDataItem>>();
		Map<String, ArchivesTypeDataItem> dataItems =  new LinkedHashMap<String, ArchivesTypeDataItem> ();
		
		try {
			pErrPos = 1;
			//���ݵ����������б����쵵���������			
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("���������Ż�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeId�ɹ�
				str_archivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
			}
	
			
			//���ݷ������б����쵵��������󼯺�
			if(pFlag){
				pErrPos = 2;
				for (int i=0 ;i<str_archivesTypeIds.length;i++) {
					archivesTypeDataItems =  SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i])).getDataItemsForUseQuery();
					archivesTypeDataItemsList.add(archivesTypeDataItems);//���õ��������µ����ò�ѯ������ϼӵ��б���
				}
				
				//���ù��߷�����ȡ������������
				if(getPublicDataItems(archivesTypeDataItemsList,dataItems,pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("��ȡ����������Ĺ���������ʧ�ܣ�");
				}			
			}
			
			if (pFlag) {
				pErrPos = 3;
				//ͨ����������ѯHTML����
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);			
			}			
			
			//����ȡ�����ݷ��ص�ҳ��
			if(pFlag){		
				pErrPos = 4;
				request.setAttribute("archivesTypeId", str_archivesTypeIds[0]);
				request.setAttribute("htmlCode", htmlCode);
				forWard = "toSimple";				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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
			}
		}				
		return forWard;
	}
	
	/**
	 * �Զ���������ѯ-->��ȡ������ѯ�ֶ�
	 * @return
	 * @throws Exception
	 */
	public String getPublicQueryItemsForDefinedQuery() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//ҳ��ȥ��
		String[] str_archivesTypeIds = {};
		Map<String,ArchivesTypeDataItem> archivesTypeDataItems = null;
		List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList = new ArrayList<Map<String,ArchivesTypeDataItem>>();
		Map<String, ArchivesTypeDataItem> dataItems =  new LinkedHashMap<String, ArchivesTypeDataItem> ();
		
		try {
			pErrPos = 1;
			//���ݵ����������б����쵵���������			
			if(request.getParameter("archivesTypeIdstr") == null || "".equals(request.getParameter("archivesTypeIdstr"))){
				pFlag = false;
				pErrInfo.getContent().append("���������Ż�ȡʧ�ܣ�");
			}else{//���ղ���archivesTypeId�ɹ�
				str_archivesTypeIds = request.getParameter("archivesTypeIdstr").split(":");
			}
	
			
			//���ݷ������б����쵵��������󼯺�
			if(pFlag){
				pErrPos = 2;
				for (int i=0 ;i<str_archivesTypeIds.length;i++) {
					archivesTypeDataItems =  SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i])).getDataItemsForUseQuery();
					archivesTypeDataItemsList.add(archivesTypeDataItems);//���õ��������µ����ò�ѯ������ϼӵ��б���
				}
				
				//���ù��߷�����ȡ������������
				if(getPublicDataItems(archivesTypeDataItemsList,dataItems,pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().append("��ȡ����������Ĺ���������ʧ�ܣ�");
				}			
			}
			
			if (pFlag) {
				pErrPos = 3;
				//ͨ����������ѯHTML����
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems,null);			
			}			
			
			//����ȡ�����ݷ��ص�ҳ��
			if(pFlag){				
				request.setAttribute("archivesTypeId", str_archivesTypeIds[0]);
				request.setAttribute("htmlCode", htmlCode);
				request.setAttribute("archivesTypeIdstr", request.getParameter("archivesTypeIdstr"));
				forWard = "toDefinedQueryResult";
				
			}else{
				forWard = "error";
			}
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
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
			}
		}				
		return forWard;
	}

	
	/**
	 * �û��Զ���������ѯ <br>
	 * ��ѯ�����������������
	 * @return
	 * @throws Exception
	 */
	public String findArchivesByDefinedQuery() throws Exception{
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();	
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String htmlCode = "";
		int pErrPos = 0;
		boolean pFlag= true;
		String forWard = "";//ҳ��ȥ��
		String[] str_archivesTypeIds = {};
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		int definedSearchID = 0;
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>();
		List<ArchivesTypeCountInfo> archivesTypeCountInfos = new ArrayList<ArchivesTypeCountInfo>();
		StringBuilder querySQL = new StringBuilder();// ��ѯ������SQLƬ��
	
		
		try {
			
			pErrPos = 1;
			//������֤
			if( request.getParameter("nodeId")==null || "".equals(request.getParameter("nodeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("�Ҳ����û��Զ����ѯ������ţ�nodeId����ʧ��");
			}
			
			
			
			//����ҵ���߼�����ȡ�û��Զ����ѯ��������
			if(pFlag){
				pErrPos = 2;
				definedSearchID = Integer.parseInt(request.getParameter("nodeId"));
				//����ű��浽������
				userDefinedSearch.setID(definedSearchID);
				if (userDefinedSearchManageService.findUserDefinedSearchByID(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����û��Զ����ѯ����ʧ�ܣ�");
				}
			}
			
			
			//�����ѯ�����Ͳ��ż���
			if (pFlag) {	
				pErrPos = 3;
				
				//�����ѯ����
				querySQL.append(userDefinedSearch.getWhereString());
				
				//�������ѯ�ĵ�������
				str_archivesTypeIds = userDefinedSearch.getArchivesIDString().split(":");
				for (int i = 0; i < str_archivesTypeIds.length; i++) {
					//��ϵͳ��ʼ���л�ȡ�������Ͷ���
					ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(str_archivesTypeIds[i]));
					archivesTypes.add(archivesType);					
				}
			}
			
			
			//����ҵ���߼�ִ�в�ѯ����
			if (pFlag) {
				pErrPos = 4;
				if(archivesInfoQueryService.queryCrossClassified(userInfo,archivesTypes,new ArrayList<ArchivesInfoQueryCondition>(),archivesTypeCountInfos,querySQL,pErrInfo)==false){
					pFlag = false;
					forWard = "error";
					pErrInfo.getContent().append("��ѯ���������ĸ��������������ʧ�ܡ�");
				}else{
					forWard = "toUserDefinedQuery";
				}
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesTypeCountInfos", archivesTypeCountInfos);
				request.setAttribute("resultNum",archivesTypeCountInfos.size());//���ý��������
				request.setAttribute("beginFlag", false);
				request.setAttribute("querySQL", querySQL.toString());
				request.setAttribute("archivesTypeIdstr", userDefinedSearch.getArchivesIDString());
				
			}	
			
		} catch (Exception e) {
			//�쳣����
			e.printStackTrace();
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			forWard = "error";
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
		return forWard;
	}
	
	/**
	 * ���ݵ��������ź�NBXH��ʾ������Ϣ<br>���������ĵ���ԭ�ļ��б�
	 * archivesTypeId,NBXH,
	 * @return
	 * @throws Exception
	 */
	public String findArchivesInfoByNBXH() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		String forWard = "";//ҳ��ȥ��
		ArchivesType archivesType = new ArchivesType();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		ArchivesInfo archivesInfo = new ArchivesInfo();
		int NBXH = 0;
		boolean viewAttachedFileFlag = false;//����ԭ�ı�־
		List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles = new ArrayList<ArchivesInfoAttachedFile>(); 
		String htmlCode = "";

		try {
			
			pErrPos = 1;
			//��ȡ����������Ϣ
			if( request.getParameter("archivesTypeId")==null || "".equals(request.getParameter("archivesTypeId")) ){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ����������ʧ��");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(Integer.parseInt(request.getParameter("archivesTypeId")));
			}
			
			//��ȡNBXH
			if( request.getParameter("NBXH")==null || "".equals(request.getParameter("NBXH")) ){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�����ı��ʧ��");
			}else{
				NBXH = Integer.parseInt(request.getParameter("NBXH"));
			}

			Map<String, ArchivesTypeDataItem> dataItems = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesType.getID()).getDataItemsForInput();
			if(dataItems == null){
				pErrPos = 2;
				pFlag = false;
				pErrInfo.getContent().append("�˷����¿���¼������Ϊ�գ�");
			}
			
			//��ȡ������Ϣ��������������ҳ������ʾ��HTML����
			if(pFlag){
				pErrPos = 3;	
				archivesInfo = new ArchivesInfo(archivesType);
				//��ȡ������Ϣ
				if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, NBXH, archivesInfo, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ڲ���Ų��ҵ�����Ϣʧ�ܣ�");
				}
				//����ҳ������ʾ�ģ��������ݵ�HTML�ַ���
				htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
			}			
			
			//���ʵ���������Ϣ��Ȩ�޿���
			if (pFlag) {
				pErrPos = 4;
				//���ܻ�������⣬����ע��������
				StringBuilder errInfo = new StringBuilder();
				//����û��Ƿ���Ȩ���ʵ���
				if(userInfo.getAccessControlService().checkArchivesInfoACL(archivesType.getID(), NBXH, archivesInfo.getSecrecyID(), userInfo.getUserID(), useScopesAccessControlService, errInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, errInfo.toString());
				}
			}
		   System.out.println("----->"+archivesInfo.getSecrecyID());
			//��ȡԭ�ĵ����ļ�����
			if (pFlag) {
				pErrPos = 5;
				if(attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, NBXH, archivesInfoAttachedFiles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡָ���ļ���ԭ�ĵ����ļ���Ϣʧ�ܣ�");
				}
			}
			
			//��ȡ�鵵���ԭ�ĵ����ļ��Ĵ��·��
			if (pFlag) {
				pErrPos = 6;
				if(attachedFileManageService.getSavedArchivesInfoAttachedFilesSavePath(archivesType, archivesInfoAttachedFiles, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�鵵���ԭ�ĵ����ļ��Ĵ��·��ʧ�ܣ�");
				}
			}
			
//			List<Config> pConfigs = new ArrayList<Config>();
//			//�������ļ��л�ȡԭ�ĵ����ļ��ĸ�Ŀ¼
//			if (pFlag) {
//				pErrPos = 6;
//				if(configManageService.findConfigByConfigType("AttachedFileReSavedForUsePath", pConfigs, pErrInfo)==false){
//					pFlag = false;
//					pErrInfo.getContent().insert(0, "�������ļ��л�ȡԭ�ĵ����ļ��ĸ�Ŀ¼ʧ�ܣ�");
//				}else{
//					if(pConfigs.size()<1){
//						pFlag = false;
//						pErrInfo.getContent().append("�����ļ�û������ԭ�ĵ����ļ��ĸ�Ŀ¼��");
//					}
//				}
//			}
//			
//			
//			//����ԭ�ĵ����ļ��Ĵ洢·��
//			if (pFlag) {
//				pErrPos = 7;				
//				for (ArchivesInfoAttachedFile archivesInfoAttachedFile : archivesInfoAttachedFiles) {
//					String attachedFileReSavedForUsePath = "";//ԭ�ĵ����ļ���·��
//					//1��ȡ��Ŀ¼
//					for (Config config : pConfigs) {
//						//System.out.println(config.getMaxValue());
////						//System.out.println(config.getMinValue());
////						int max = Integer.parseInt(config.getMaxValue());
////						int min = Integer.parseInt(config.getMinValue());
////						int target =Integer.parseInt( new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime()));
////System.out.println(max+":"+min +":"+target);
//						Date maxDate = new SimpleDateFormat("yyyyMMdd").parse(config.getMaxValue());
//						Date minDate =  new SimpleDateFormat("yyyyMMdd").parse(config.getMinValue());
//						if(archivesInfoAttachedFile.getResaveTime().after(minDate) && archivesInfoAttachedFile.getResaveTime().before(maxDate)){
//						//if(target>min && target<max){
//							attachedFileReSavedForUsePath = config.getConfigValue();
//						}
//					}	
//					//ȡ�ļ������Ŀ¼
//					if ("".equals(attachedFileReSavedForUsePath)) {
//						pFlag = false;
//						pErrInfo.getContent().append("û���ҵ�ԭ�ĵ����ļ��ĸ�Ŀ¼��");
//					}else{
//						//2������Ŀ¼ :����/ʱ��/1_78_G01-2010-DQ11-27.pdf
//						attachedFileReSavedForUsePath += archivesType.getAttachedFileSavePath() ;
//						attachedFileReSavedForUsePath += "\\" + new SimpleDateFormat("yyyyMMdd").format(archivesInfoAttachedFile.getResaveTime());
//						attachedFileReSavedForUsePath += "\\" + archivesInfoAttachedFile.getResaveFileName();
//						//���ļ���·�����õ�ԭ����Ϣ�����tag����
//						archivesInfoAttachedFile.setTag(attachedFileReSavedForUsePath);
//						System.out.println(archivesInfoAttachedFile.getTag());
//					}					
//				}
//			}
			
			//ԭ�ĵ����ļ��ķ���Ȩ�޿���
			if (pFlag) {
				pErrPos = 8;
				//IAttachedFileAccessControlService attachedFileAccessControlService = new AttachedFileAccessControlServiceImpl();
				if(attachedFileAccessControlService.checkArchivesAttachedFileACL(archivesType.getID(), NBXH, userInfo.getUserID()) == true){
					viewAttachedFileFlag = true;
				}
			}
			
			//����Ҫ�����ݷ��ص�ҳ��
			if (true) {
				System.out.println(htmlCode);
				request.setAttribute("pFlag", pFlag);             
				request.setAttribute("archivesInfo",archivesInfo);
				request.setAttribute("viewAttachedFileFlag",viewAttachedFileFlag);
				request.setAttribute("archivesInfoAttachedFiles",archivesInfoAttachedFiles);
				request.setAttribute("attachedFileSize", archivesInfoAttachedFiles.size());
				request.setAttribute("htmlCode",htmlCode);
				forWard = "toItem";//ָ��Ҫ���ص�ҳ��
			}			
			
		} catch (Exception e) {			
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				forWard = "error";//�����ִ���ͷ��ص�����ҳ��
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
System.out.println(pErrInfo.toString());
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}
	
	
	
	//���߷���
	/**
	 * ���ݵ����������ҳ��html��ѯ����
	 */
	private String getHtmlCodeByType(int archivesTypeId,Map<String,ArchivesInfoQueryCondition> archivesInfoQueryConditions){
		String htmlCode = "";

		//�õ�application����
		ServletContext application = ServletActionContext.getServletContext();

		//�õ�html����
		SystemInitializer systemInitializer = (SystemInitializer) application.getAttribute("systemInitializer");
		Map<String, ArchivesTypeDataItem> dataItems = null;
		if( systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery()== null){
			htmlCode = "<script>alert('û�пɲ�ѯ�������');</script>";
		}else{
			dataItems = systemInitializer.getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery();
			htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfoQueryConditions);
		}
		return htmlCode;
	}
	
	
	
	
	
	
	////////////  DWR  ////////////
	
	/**
	 * �����ڲ���Ų�ѯָ������ľ����ļ���Ϣ����
	 * 
	 * @param archivesInfo
	 *            ����İ�����Ϣ
	 * @return archivesInfos ��ѯ���ľ����ļ���Ϣ����
	 */
	public List<ArchivesInfo> findChildArchivesInfosByNBXH(int archivesTypeID,int NBXH,HttpSession session) throws Exception {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<ArchivesInfo> archivesInfos = null;
		ArchivesType archivesType = null;
		
		try {
			pErrPos = 1;
			if (archivesTypeID == 0) {
				pFlag = false;
				pErrInfo.getContent().append("��õ�������ʧ��");
			}else{
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
			}

			if (pFlag) {
				pErrPos = 2;
				if (NBXH == 0) {
					pFlag = false;
					pErrInfo.getContent().append("��õ����ڲ����ʧ��");
				}
			}
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 3;
				archivesInfos = new ArrayList<ArchivesInfo>();
				if(archivesInfoQueryService.findChildArchivesInfosByNBXH(NBXH, archivesType, archivesInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��õ��������ļ�ʧ��");
				}
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
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
		}
		System.out.println(archivesInfos.size());
		return archivesInfos;
	}

	/**
	 * ��֤�߼���ѯ�����������ĺϷ���
	 * @param archivesTypeId ����������
	 * @param columnName �ֶ���
	 * @param txtValue �����ֵ���ַ���������
	 */
	public String validateAdvanceQueryInput(int archivesTypeId,String columnName,String txtValue, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//����ִ�н��,��֤ͨ��ʱ���ؽ��Ϊ��������ͨ���򷵻ش�����ʾ��Ϣ
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			pErrPos = 1;
		
			//��֤�����������Ƿ��Ѹ�ֵ
			if(pFlag){				
				if(archivesTypeId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�õ�����������Ϣʧ�ܣ�");
				}			
			}
			
			
			//����columnName��Ӧ���������ͣ�Ч��������ĺϷ��ԡ�
			if(pFlag){
				pErrPos = 2;
				//����Ч��
				if(SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getColumnDataType()==EnumColumnDataType.����){
					if(CommonUtil.isInteger(txtValue)==false){//��������ֵ��������
						pFlag = false;
						//pErrInfo.getContent().append("���������ָ�ʽ���ԣ���������ȷ�����֣�");
						String displayText = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getDisplayText();
						str_Result ="'"+ displayText+"'�ָ�ʽ������������ȷ�����֣�";

					}
				}
				
				//����Ч��
				if(SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getColumnDataType()==EnumColumnDataType.����ʱ��){
					if(CommonUtil.isDate(txtValue)==false){
						pFlag = false;
						String displayText1 = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeId).getDataItemsForUseQuery().get(columnName).getDisplayText();
						//pErrInfo.getContent().append("���������ڵĸ�ʽ������������ȷ������(yyyy-MM-dd)��");
						str_Result = "'"+displayText1+"'��ʽ������������ȷ������(yyyy-MM-dd)��";
					}
				}
			}
			
		} catch (Exception e) {
			
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
		

	 /**
	 * ɾ�����ָ�����Զ���������ѯ
	 * @param queryID
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public String deleteDefinedQueryByID(int queryID, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//����ִ�н��,��֤ͨ��ʱ���ؽ��Ϊ��������ͨ���򷵻ش�����ʾ��Ϣ
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		UserDefinedSearch userDefinedSearch = new UserDefinedSearch();
		
		
		try {
			pErrPos = 1;
		
			//��֤�����������Ƿ��Ѹ�ֵ
			if(queryID == 0){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ�û��Զ���������ѯ���ʧ�ܣ�");
			}else{
				userDefinedSearch.setID(queryID);
			}	
			
			//����ҵ���߼���ִ��ɾ������
			if (pFlag) {
				pErrPos = 2;
				if(userDefinedSearchManageService.deleteUserDefinedSearch(userDefinedSearch, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ�����ָ�����û��Զ���������ѯʧ�ܣ�");					
				}
			}
			
		} catch (Exception e) {
			
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				str_Result = pErrInfo.toShortString();
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
	
	
	/**
	 * ��֤�������ѯ�е������������ϴ�
	 * @param archivesTypeId ����������
	 * @param columnName �ֶ���
	 * @param txtValue �����ֵ���ַ���������
	 */
	public String validateQueryInputStr(String strNameValues, HttpSession session) throws Exception{
		boolean pFlag = true;
		String str_Result="";//����ִ�н��,��֤ͨ��ʱ���ؽ��Ϊ��������ͨ���򷵻ش�����ʾ��Ϣ
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		int archivesTypeId = 0;	//����������
		String[] nameValues = {};//����-ֵ�� ����
		try {
			pErrPos = 1;
			nameValues = strNameValues.split(";");
			//ȡarchivesTypeIdֵ����һ������������archivesTypeId
			archivesTypeId = Integer.parseInt(nameValues[0].split(":")[1]);
			
			//��֤����
			if(pFlag){
				pErrPos = 2;
				for(int i = 1;i<nameValues.length;i++){
					if(nameValues[i].split(":").length==2){
						//������֤����������ʾ�ϲ���һ��
						str_Result += validateAdvanceQueryInput(archivesTypeId, nameValues[i].split(":")[0],nameValues[i].split(":")[1], session);
				
					}
				}
			}			
		
		} catch (Exception e) {
			
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.getMessage());
			pErrInfo.setException(e);
			
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				tempBuilder.append(" ErrPos: ");
				tempBuilder.append(pErrPos);
				tempBuilder.append(", ");

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				
				//�쳣�ʹ�����
				if(pErrInfo.getException() != null){
					logger.error(pErrInfo.getContent().toString());
					throw new Exception(pErrInfo.getContent().toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
		}
		return str_Result;
	}
	

	/**
	 * ��ȡ�ؼ��ּ�����SQL���
	 * @param keyWord �û�����Ĺؼ���
	 * @param dataItemsForUseQuery �����ڲ�ѯ���ֶ�
	 * @param querySQL	����SQL���
	 * @param pErrInfo ���ش�����Ϣ
	 * @return
	 */
	private boolean getKeyQuerySQL(String keyWord,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery,StringBuilder querySQL, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//�������Ϊ�գ����ѯ���е�����querySQLҲΪ�մ�
			if("".equals(keyWord.trim())){
				return true;
			}
			
			pErrPos = 1;
			//�������������������ϵļ����Ƿ��ѳ�ʼ��
			if(dataItemsForUseQuery==null){
				pFlag = false;
				pErrInfo.getContent().append("�����������������ϵļ���δ��ʼ��");
			}
			
			if (pFlag) {
				Iterator<String> columnNames = dataItemsForUseQuery.keySet().iterator();			
				int index = 0;//��¼�Ƿ��ǵ�һ��
				//querySQL��ʽ��AND (Title LIKE '%xx%' OR Ftm LIKE '%xx%')
				while(columnNames.hasNext()){
					String columnName = columnNames.next();
					index ++;
					if(index==1){
						querySQL.append( " AND ( " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}else{
						querySQL.append( " OR " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}
				}
				querySQL.append(")");
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
		return pFlag;
	}

	/**
	 * ��ȡ�����������еĹ���������
	 * @param archivesTypeDataItemsList �����������������ϵļ��ϣ��ṹ��List< Map<> >��
	 * @param dataItems ���ع�����������
	 * @param pErrInfo 
	 * @return
	 */
	private boolean getPublicDataItems(List<Map<String,ArchivesTypeDataItem>> archivesTypeDataItemsList , Map<String, ArchivesTypeDataItem> dataItems , ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			pErrPos = 1;
			//�������������������ϵļ����Ƿ��ѳ�ʼ��
			if(archivesTypeDataItemsList==null){
				pFlag = false;
				pErrInfo.getContent().append("�����������������ϵļ���δ��ʼ��");
			}
			
			//��鹫�����������Ƿ��ѳ�ʼ��
			if(dataItems==null){
				pFlag = false;
				pErrInfo.getContent().append("�������������δ��ʼ��");
			}
			
			
			//��ȡ���е��������еĹ���������
			if (pFlag) {
				pErrPos = 2;
				if(archivesTypeDataItemsList.size()==1){//��ֻѡ����һ������ʱ
					dataItems.putAll(archivesTypeDataItemsList.get(0));
				}else{
					//����һ������������������������������з�������������һһƥ��
					String columnName = "";//keyֵ
					Iterator<String> iterator = archivesTypeDataItemsList.get(0).keySet().iterator();					
					while(iterator.hasNext()){
						int numFlag = 1;
						columnName = iterator.next();
						
						//��columnName���������з�����������������бȽ�
						for(int i = 1;i<archivesTypeDataItemsList.size();i++){
							Iterator<String> targetIterator = archivesTypeDataItemsList.get(i).keySet().iterator();
							while (targetIterator.hasNext()) {
								if(columnName.equals(targetIterator.next())){//����ͬ��keyֵ����־��1
									numFlag++;									
								}								
							}
						}
						
						//�������������ÿ�����������¶��еĻ��ͽ�����ӵ��������
						if (numFlag == archivesTypeDataItemsList.size()) {//ÿ��������ʱ��־ӦΪ��������ĸ���
							dataItems.put(columnName, archivesTypeDataItemsList.get(0).get(columnName));
						}	
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
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	
	
	/**
	 * ���쵵����Ϣ��¼��ѯ������SQLƬ��
	 * 
	 * @param archivesInfoQueryConditions
	 *            ������¼��ѯ��������
	 * @param querySQL
	 *            ���ع���õĲ�ѯ����SQLƬ��
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		

		try
		{
			//���û�����ݾͷ��ؿմ�
			if(archivesInfoQueryConditions == null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else{
				for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
				{
					// ����Ƿ�Χ��ѯ��������Сֵ�����ֵ����
					if (item.getDataItem().getRangeQueryFlag())
					{
						// ���赥���ŵ����
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ��������Χ
							querySQL.append(" BETWEEN ");
							querySQL.append(item.getMinValue());
							querySQL.append(" AND ");
							querySQL.append(item.getMaxValue());
						}
						// ��Ҫ�����ŵ����
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ��������Χ
							querySQL.append(" BETWEEN ");
							querySQL.append("'");
							querySQL.append(item.getMinValue());
							querySQL.append("'");
							querySQL.append(" AND ");
							querySQL.append("'");
							querySQL.append(item.getMaxValue());
							querySQL.append("'");
						}
					}
					// ������Ƿ�Χ��ѯ������ֵ����
					else
					{
						// ���赥���ŵ����
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ������
							querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
							querySQL.append(item.getValue());
						}
						// ��Ҫ�����ŵ����
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ������
							if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ��)
							{
								querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
								querySQL.append("'");
								querySQL.append(item.getValue());
								querySQL.append("'");
							}
							else
							{
								querySQL.append(" LIKE "); // �ַ�����ȱʡʹ��ģ��ƥ��
								querySQL.append("'%");
								querySQL.append(item.getValue());
								querySQL.append("%'");
							}
						}
					}
				}
			}
			
			
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
		System.out.println("getSqlForArchivesInfoInputQueryConditions: querySQL:" + querySQL);
		return pFlag;
	}

	
	
	
	
	/**
	 * ������
	 * @return
	 * @throws Exception
	 */
	public String test() throws Exception{
		System.out.println("test-->>>>");
		return "error";
	}
	
	
	
	
}
