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
 * �������ÿ�����(Action)
 * @author Administrator
 *
 */
public class ArchivesUseAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	
	
///////////����ע�����///////////
	/**
	 * �����ϼ�λ����Ϣ�������Ľӿڶ���
	 */
	@Autowired
	private IStoreAddressInfoManageService storeAddressInfoManageService;
	
	
	
	/**
	 * �û�������ɫ�������Ľӿڶ���
	 */
	@Autowired
	private IUserRolesInfoManageService userRolesInfoManageService;
	
	/**
	 * ��������Ŀ���ֵ����������
	 */
	@Autowired
	private IArchivesUsePurposeManageService archivesUsePurposeManageService;
	
	/**
	 * �û���Ϣ�������
	 */
	@Autowired
	private IUserInfoManageService userInfoManageService; 
	/**
	 * �������÷�ʽ�ֵ����������
	 */
	@Autowired
	private IArchivesUseWayManageService archivesUseWayManageService;
	/**
	 * ������������Ϣ���������
	 */
	@Autowired
	private IArchivesUsePersonInfoManageService archivesUsePersonInfoManageService;
	
	/**
	 * ϵͳ���ù��������
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * �������ù��������
	 */
	@Autowired
	private IArchivesUseManageService archivesUseManageService;
	
	/**
	 * �ⷿ������Ϣ�Ĺ��������
	 */
	@Autowired
	private IStoreroomArchivesInfoManageService storeroomArchivesInfoManageService;
	
	/**
	 * ʵ�ﵵ�����ó�ȥ��ϸ���������
	 */
	@Autowired
	private IArchivesUseOutInfoManageService archivesUseOutInfoManageService;
	
	/**
	 * �����������뵥��ϸҵ���������
	 */
	@Autowired
	private IArchivesUseRequestDetailManageService archivesUseRequestDetailManageService;
	
	/**
	 * �����������뵥��Ϣ���������
	 */
	@Autowired
	private IArchivesUseRequestManageService archivesUseRequestManageService;
	
	/**
	 * �����鵵��Ϣ�Ĺ������
	 */
	@Autowired
	private IArchivesInfoQueryService archivesInfoQueryService;
	
	/**
	 * ����ԭ�Ĺ������ 
	 */
	@Autowired
	private IAttachedFileManageService	attachedFileManageService;

	/**
	 * ����ԭ������ͨ���������
	 */
	@Autowired
	private IAttachedFileUseRequestPassInfoManageService attachedFileUseRequestPassInfoManageService;
	
	/**
	 * ���ڷ��ؽ���json��
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
//	 * ���������嵥������ڲ��������
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

//	private String userName ;  //����������
//	private int[] IDs = {};    //���뵥�������
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
	 * ��ҳʵ����
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * ������������Ϣʵ��
	 */
//	ArchivesUsePersonInfo archivesUsePersonInfo = new ArchivesUsePersonInfo();	
	
	/**
	 * ��ȡ������������Ϣ
	 * @return
	 */
//	public ArchivesUsePersonInfo getArchivesUsePersonInfo() {
//		return archivesUsePersonInfo;
//	}
//
//	/**
//	 * ���õ�����������Ϣ
//	 * @param archivesUsePersonInfo
//	 */
//	public void setArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo) {
//		this.archivesUsePersonInfo = archivesUsePersonInfo;
//	}

	/**
	 * �����������뵥��ѯ����ʵ����
	 */
	ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
		
	public ArchivesUseRequestQueryCondition getArchivesUseRequestQueryCondition() {
		return archivesUseRequestQueryCondition;
	}

	public void setArchivesUseRequestQueryCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition) {
		this.archivesUseRequestQueryCondition = archivesUseRequestQueryCondition;
	}

	/**
	 * �������õǼ���Ϣ��ѯ��������<br>
	 * ���ڴ����ѯ����
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
	 * ����������Ϣ��ѯ��������
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
	 * DWR���ã���ӵ�����������Ϣ
	 * @return
	 * @throws Exception
	 */
	public int addArchivesUsePersonInfo(ArchivesUsePersonInfo archivesUsePersonInfo ,HttpServletRequest request) throws Exception{
		System.out.println(archivesUsePersonInfo.getAreaID()+archivesUsePersonInfo.getDepartment()+archivesUsePersonInfo.getEmail());
		String message = "";//���ڷ���ִ�н�������ִ�гɹ��򷵻�""�����ɹ��򷵻ش�����Ϣ
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if(archivesUsePersonInfoManageService.add(archivesUsePersonInfo, pErrInfo)==false){
					pFlag = true;
					pErrInfo.getContent().insert(0, "��ӵ�����������Ϣʧ�ܣ�");					
				}else{
					System.out.println("success: ��ӵ�����������Ϣ�ɹ�");
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
				System.out.println(pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return archivesUsePersonInfo.getID();
	}
	
	/**
	 * DWR ���ã�����������ѯ������������Ϣ
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
			//��ʼ���� 1...
			pErrPos = 1;
						
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if(archivesUsePersonInfoManageService.findByIDCardNo(archivesUsePersonInfo.getIDCardNo(), archivesUsePersonInfos, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����������֤���Ų�ѯ��������Ϣʧ�ܡ�");
					System.out.println("ErrorInfo:"+pErrInfo.toString());					
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
		return archivesUsePersonInfos;
	}

	/**
	 * DWR���ã����õǼ�ʱɨ��ǹɨ��������������嵥
	 * @return
	 * @throws Exception
	 *///findArchivesByBarcode
	public StoreroomArchivesInfo findArchivesByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, HttpServletRequest request) throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if(storeroomArchivesInfo==null){
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ������Ϣδ��ʼ����");
			}else{
				//��֤�������Ƿ�Ϊ��
				if("".equals( storeroomArchivesInfo.getArchivesBarcode().trim() ) ){
					pFlag = false;
					pErrInfo.getContent().append("�������벻��Ϊ��");
				}
			}			

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if(storeroomArchivesInfoManageService.findByBarcode(storeroomArchivesInfo, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����������ѯ�ⷿ������Ϣʧ�ܣ�");
					storeroomArchivesInfo.setNBXH(0);//�����ѯʧ�ܣ���NBXH��Ϊ0����ҳ���жϲ����ɹ����
					storeroomArchivesInfo.setTitle(pErrInfo.toShortString());//��������Ϣͨ��Title����ҳ��
				}else{					
//					System.out.println("findArchivesByBarcode success!");
//					ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();
//					archivesUseOutInfo.setArchivesBarcode(storeroomArchivesInfo.getArchivesBarcode());
//					if(archivesUseOutInfoManageService.findArchivesUseOutInfoByArchivesBarcode(archivesUseOutInfo, pErrInfo)==false){
//						pFlag = false;
//						pErrInfo.getContent().insert(0,"���ݵ����������ѯ�������ó�ȥ��Ϣʧ�ܣ�");
//					}else{
//						//������õ������ó�ȥ��Ϣ�д��ڸ���������ָ���ĵ�����������ִ�н������
//						System.out.println("NBXH:"+archivesUseOutInfo.getNBXH());
//						if(archivesUseOutInfo.getNBXH()!=0){
//							System.out.println("throw exception ");
//							throw  new Exception("�õ����Ѿ���������ڱ��鵵���ã�����ִ�й黹������");							
//						}
//					}					
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
		return storeroomArchivesInfo;
	}
	
	/**
	 * �������嵥(Session)��ɾ��ָ���ĵ���
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
			//��ʼ���� 1...
			pErrPos = 1;
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if(session == null){
					pFlag = false;
					pErrInfo.getContent().append("��ȡSessionʧ�ܣ�");
				}else{
					archivesUseList =(List<StoreroomArchivesInfo>) session.getAttribute("archivesUseList");
				}				
			}
			
			//ɾ��archivesUseList�е�ָ���ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 3;
				int numm = 1;
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					if(storeroomArchivesInfo.getArchivesTypeID()==archivesTypeId
							&& storeroomArchivesInfo.getNBXH()==NBXH 
							&& storeroomArchivesInfo.getTag().equals(archivesUseWay)){
						//ɾ�����������ĵ�����Ϣ
						archivesUseList.remove(storeroomArchivesInfo);
						System.out.println(numm++);
						System.out.println("archivesUseList.size():"+archivesUseList.size());
						break;
						//useList.add(storeroomArchivesInfo);
					}
				}
			}
			
			//�������������б���µ�Session��ȥ
			if(pFlag){
				pErrPos = 4;
				session.setAttribute("archivesUseList", archivesUseList);
				message = "ɾ���ɹ���";
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
System.out.println(pErrInfo.toString());
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
		}
		System.out.println(message);
		return message;
	}
	
	/**
	 * ������������Ϣ��ӵ������嵥<br/>�ӿⷿ������Ϣ��ȡ������Ϣ��<br>��tag������ŵ������÷�ʽ
	 * <br>˵�����ɸ���archivesTypeIdAndNBXHs���Լ�archivesUseWay��<br>ʵ�ֽ赵���鵵���鿴ԭ�ĵ�����<br>
	 * archivesTypeIdAndNBXHsΪ��ʱ������ʵ�ֲ鿴�����Ĺ��ܡ�
	 * @return ִ�н������ӳɹ���
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
		String[] archivesTypeIdAndNBXHs = {};//���ڽ���archivesTypeId��NBXH���飻��ʽ��TypeID1:NBXH1;TypeID2:NBXH2;
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		
		try {
			pErrPos = 1;
			if (pFlag) {
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡSessionʧ�ܣ�");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");

					if (archivesUseList==null) {
						archivesUseList =  new ArrayList<StoreroomArchivesInfo>(); 
					}
				}
			}
			
			//��ȡ����
			if (pFlag) {
				pErrPos = 2;
				//����archivesTypeId��NBXH����
				System.out.println("archivesTypeIdAndNBXHs:"+request.getParameter("archivesTypeIdAndNBXHs")+":");
				if(request.getParameter("archivesTypeIdAndNBXHs")!= null ){
					archivesTypeIdAndNBXHs = request.getParameter("archivesTypeIdAndNBXHs").trim().split(";");
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ������ʶ��Ϣʧ�ܣ�");
				}
				
				//�������÷�ʽ
				if(request.getParameter("archivesUseWay")!=null && !"".equals(request.getParameter("archivesUseWay").trim())){
					archivesUseWay = Integer.parseInt(request.getParameter("archivesUseWay").trim());
				}else{
					pFlag = false;
					pErrInfo.getContent().append("��ȡ���÷�ʽʧ�ܣ�");
				}				
			}
			
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 3;
				int archivesTypeId = 0;
				int NBXH = 0;
				StoreroomArchivesInfo storeroomArchivesInfo = null;
				
				if(!"".equals(request.getParameter("archivesTypeIdAndNBXHs").trim())){//��ѡ�е���ʱ
					for (String IDNBXH : archivesTypeIdAndNBXHs) {
						archivesTypeId = Integer.parseInt(IDNBXH.split(":")[0]);
						NBXH = Integer.parseInt(IDNBXH.split(":")[1]);
						storeroomArchivesInfo = new StoreroomArchivesInfo();
						storeroomArchivesInfo.setArchivesTypeID(archivesTypeId);
						storeroomArchivesInfo.setNBXH(NBXH);
						if(storeroomArchivesInfoManageService.findStoreroomArchivesInfoByNBXH(storeroomArchivesInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"���ݵ��������ź�NBXH���ҵ����ⷿλ����Ϣʧ�ܣ�");
						}else{//��ѯ������Ϣ�ɹ������õ������÷�ʽ,�����뵽�����б���						
							storeroomArchivesInfo.setTag(archivesUseWay);
							//check�õ����Ƿ��Ѿ�����
							if(checkArchivesInfoExistInUseList(storeroomArchivesInfo, archivesUseList)==true){
								archivesUseList.add(storeroomArchivesInfo);
								//archivesUseList.re
							}						
						}
					}
				}
				
			}
			
			//����ȡ�Ľ�����浽Session��
			if (pFlag) {
				pErrPos = 4;
				session.setAttribute("archivesUseList", archivesUseList);
System.out.println("archivesUseList.size()2-->:"+archivesUseList.size());
			}
			
			//��������ص�ҳ��
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("applyFlag", archivesUseWay==2);//������Ա�����������û���ʶ
				request.setAttribute("archivesUseList", archivesUseList);
				request.setAttribute("useSize", archivesUseList.size());
				forWord = "toShowUseList";//��������ҳ��
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
				forWord = "error";
	System.out.println(pErrInfo.toString());
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWord;
	}
	
	/**
	 * ���ݵ��������嵥��ӡ����<br/>
	 * ��SESSION��ȡ������Ϣ<br/>
	 * �ٸ��ݻ�����Ϣȡ�ݲ�λ��<br/>
	 * �����ڵ����������ֳ�����<br/>
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
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡSessionʧ�ܣ�");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");
					if (archivesUseList==null) {
						pFlag = false;
						pErrInfo.getContent().append("��ȡ�����б���Ϣʧ�ܣ�");
					}else if(archivesUseList.size()==0){
						pFlag = false;
						pErrInfo.getContent().append("û�пɱ���ӡ�ļ�¼��");
					}
				}
			}
			//���ţ������������룻λ�ã��ݲ�״̬
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				StoreAddressInfo storeAddressInfo ;
				
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					storeAddressInfo = new StoreAddressInfo();
					storeAddressInfo.setArchivesBoxBarcode(storeroomArchivesInfo.getArchivesBoxBarcode());
					if(storeAddressInfoManageService.findStoreAddressInfoByBoxBarcode(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"���ݵ����������ȡ�ϼ�λ����Ϣʧ�ܣ�");
					}
					String fullName = storeAddressInfo.getStoreAddressFullName();
					storeroomArchivesInfo.setTag(fullName);
					System.out.println("df");
					storeroomArchivesInfos.add(storeroomArchivesInfo);
					
				}
				
			}
			
			if (pFlag) {
				pErrPos = 3;
				System.out.println("��ӡλ����Ϣ...");
				for (StoreroomArchivesInfo storeroomArchivesInfo : storeroomArchivesInfos) {
					System.out.println("Boxbarcode:"+storeroomArchivesInfo.getArchivesBoxBarcode());
					System.out.println("tag:"+ storeroomArchivesInfo.getTag().toString());
					System.out.println("title:"+storeroomArchivesInfo.getTitle());
				}
			}
			
			//�����ݴ��ݵ�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("storeroomArchivesInfos", storeroomArchivesInfos);
				session.removeAttribute("archivesUseList");//��������ɹ��ͽ�SESSION�е������б����
				forWard = "toPrintStoreroomLocation";
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return forWard;
	}

	
	
	/**  
	 * ������������:��ѯ��һ��δ�����������뵥��Ϣ,������������<br>
	 * @return �������뵥�б�
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
			//��ʼ���� 1...
			pErrPos = 1;

			//��ȡ��һ������˵ĵ���
			if (pFlag) {
				pErrPos = 2;
				if(archivesUseRequestDetailManageService.findOneNotExamineArchivesUseRequestDetail(recordsNum, archivesUseRequestDetail, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "����δ��˵ĵ�������������ϸʧ�ܣ�");
				}

			}
			
			//��ȡ������ϸ��Ϣ��������������ҳ������ʾ��HTML����
			if(pFlag){
				pErrPos = 3;				
				if(recordsNum.getValue()>0){
					archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesUseRequestDetail.getArchivesTypeID());
					archivesInfo = new ArchivesInfo(archivesType);
					Map<String, ArchivesTypeDataItem> dataItems = archivesType.getDataItemsForInput();
					//��ȡ������Ϣ
					if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, archivesUseRequestDetail.getNBXH(), archivesInfo, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"�����ڲ���Ų��ҵ�����Ϣʧ�ܣ�");
					}
					//����ҳ������ʾ�ģ��������ݵ�HTML�ַ���
					htmlCode = GenerateHtmlCodeUtil.GenerateHtmlCode(dataItems, archivesInfo,null);
				}
			}
			
			//��ȡԭ�ĵ����ļ�����
			if (pFlag) {
				pErrPos = 4;
				if(recordsNum.getValue()>0){
					if(attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, archivesUseRequestDetail.getNBXH(), archivesInfoAttachedFiles, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡָ���ļ���ԭ�ĵ����ļ���Ϣʧ�ܣ�");
					}
				}
			}
			
			//��ȡ�鵵���ԭ�ĵ����ļ��Ĵ��·��
			if (pFlag) {
				pErrPos = 5;
				if(recordsNum.getValue()>0){
					if(attachedFileManageService.getSavedArchivesInfoAttachedFilesSavePath(archivesType, archivesInfoAttachedFiles, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"��ȡ�鵵���ԭ�ĵ����ļ��Ĵ��·��ʧ�ܣ�");
					}
				}
			}
			
			//����ѯ�������Ϣ���ص�ҳ��
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
				forward = "toCheckApplicationItem";  //ת����������
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
	 * DWR���ã��������õǼ�:�鵵/�赵�Ǽ�
	 * @param archivesUseRegister
	 * @param archivesBarcodes ���������뼯���ַ���
	 * @param useDays Ҫ�õ�����
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
			//������֤
			pErrPos = 1;
			if("".equals(archivesBarcodes)){
				pFlag = false;
				pErrInfo.getContent().append("û���κα����õĵ����������뵵���룡");
			}else{
				//��ȡ��������������
				barcodes = archivesBarcodes.split(":");
			}			

			if(archivesUseRegister == null){
				pFlag = false;
				pErrInfo.getContent().append("���õǼ���Ϣʵ��δ��ʼ����");
			}

			//����ҵ���߼�,��ӵ������õǼ���Ϣ
			if (pFlag) {
				pErrPos = 2;
				archivesUseRegister.setUseDate(new Date());
				archivesUseRegister.setUseArchivesCount(barcodes.length);
				archivesUseRegister.setManagerUserID(userInfo.getUserID());
				if(archivesUseManageService.addArchivesUseRegister(archivesUseRegister, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ӵ������õǼ���Ϣʧ�ܣ�");
				}
				
				for(int i=0;i<barcodes.length;i++){
					//�����ⷿ������Ϣ����ͨ���������õ���������Ϣ
					StoreroomArchivesInfo storeroomArchivesInfo = new StoreroomArchivesInfo();
					storeroomArchivesInfo.setArchivesBarcode(barcodes[i]);
					
					//���ݵ����������ȡ�ⷿ����������Ϣ
					if(storeroomArchivesInfoManageService.findByBarcode(storeroomArchivesInfo, pErrInfo)){
						//���ݿⷿ������Ϣ���� ʵ�ﵵ�����ó�ȥ��ϸ���ʵ��
						ArchivesUseOutInfo archivesUseOutInfo = new ArchivesUseOutInfo();						
						archivesUseOutInfo.setArchivesBarcode(storeroomArchivesInfo.getArchivesBarcode());
						archivesUseOutInfo.setArchivesID(storeroomArchivesInfo.getArchivesID());
						archivesUseOutInfo.setArchivesTypeID(storeroomArchivesInfo.getArchivesTypeID());
						archivesUseOutInfo.setBorrowFlag(archivesUseRegister.getBorrowFlag());
						archivesUseOutInfo.setNBXH(storeroomArchivesInfo.getNBXH());
						//archivesUseOutInfo.setPageSum()//������ҳ��
						archivesUseOutInfo.setShouldReturnDate(new Date(new Date().getTime()+(long)useDays*24*3600*1000));
						archivesUseOutInfo.setTitle(storeroomArchivesInfo.getTitle());
						archivesUseOutInfo.setUseRegID(archivesUseRegister.getID());
						
						EnumStoreStatus storeStatus = EnumStoreStatus.�鵵������;
						if(archivesUseRegister.getBorrowFlag()){
							storeStatus = EnumStoreStatus.����������;
						}
						storeroomArchivesInfo.setStoreStatus(storeStatus);
						//����ҵ���߼�������ָ������������ⷿ������Ϣ�Ĺݲ�״̬
						if(storeroomArchivesInfoManageService.updateStoreStatusByArchivesBarcode(storeroomArchivesInfo, pErrInfo) == false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"���¿ⷿ������Ϣ�Ĺݲ�״̬ʧ�ܣ�");
						}
					
						//������ó�ȥ��ϸ��Ϣ
						if(archivesUseOutInfoManageService.addArchivesUseOutInfo(archivesUseOutInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"���һ���µ�ʵ�ﵵ�����ó�ȥ��ϸʧ�ܣ�");
						}else{
							System.out.println("���һ���µ�ʵ�ﵵ�����ó�ȥ��ϸ�ɹ���"+i);
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
System.out.println(pErrInfo.toString());
				messge = pErrInfo.toShortString();
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return messge;

	}
	
	/**
	 * ��������(�赵/�鵵)�ǼǼ�¼<br>
	 * �����˵� ʱ��ʾ�����ѵǼǵ�������Ϣ 
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
			//���ֽ赵���ǽ���			
			if(request.getParameter("useType")==null || "".equals(request.getParameter("useType"))){
				pFlag = false;
				pErrInfo.getContent().append("");
			}else{
				useType = request.getParameter("useType");
				//���ý赵/�鵵��ʶ
				if("JD".equals(useType)){
					forward  = "toJDList";
					archivesUseRegisterQueryCondition.setBorrowFlag(true);
				}else if("CD".equals(useType)){
					forward  = "toCDList";
					archivesUseRegisterQueryCondition.setBorrowFlag(false);
				}
			}
			

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findArchivesUseRegisters(archivesUseRegisterQueryCondition, dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ѯ�������õǼ���Ϣʧ�ܣ�");
				}else{
					System.out.println("��ѯ�������õǼ���Ϣ�ɹ���findArchivesUseRegisters");
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
				System.out.println("error: "+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return forward;
	}
	
	
	
	/**
	 * ����ID�������õǼ���Ϣ<br/>
	 * ��Ӧ���� �Ǽ��б�ҳ��鿴��ϸ
	 * @return
	 * @throws Exception
	 */
	public String findArchivesUseRegisterByID() throws Exception{
		String forward = "";		
		String useTypeText = "�赵";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//����Ŀ��
		ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister();//�������õǼ���Ϣ
		String useType = "";
		int registerID = 0;//���õǼǱ��
		boolean useOutDateFlag = false;//Ĭ�ϲ�ѯδ���ڵ�����������ϸ��Ϣ
		boolean useNearOutDateFlag = false;//����Ԥ����ʶ
		int dayNum = 4;//����Ԥ��������
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if("".equals(request.getParameter("useType")) || request.getParameter("useType")==null){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ���÷�ʽʧ�ܣ�");
			}else{
				useType = request.getParameter("useType");
				if("CD".equals(useType)){
					useTypeText = "�鵵";
				}
			}
			
			if("".equals(request.getParameter("registerID")) || request.getParameter("registerID")==null){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ���õǼǱ��ʧ�ܣ�");
			}else{
				registerID = Integer.parseInt(request.getParameter("registerID"));
				archivesUseRegister.setID(registerID);
			}
			
			if(!(request.getParameter("useOutDate")==null) && !"".equals(request.getParameter("useOutDate"))){
				//���ù��ڱ�ʶ
				useOutDateFlag = "true".equals(request.getParameter("useOutDate").trim());
				useNearOutDateFlag = "near".equals(request.getParameter("useOutDate").trim());
			}
			

			////����ҵ���߼�	����������Ŀ��
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ʼ����������Ŀ��ʧ�ܣ�");
				}	
			}
			
			if(pFlag){
				pErrPos = 3;
				if(archivesUseManageService.findArchivesUseRegister(archivesUseRegister, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ñ�Ų�ѯ����������Ϣʧ�ܣ�");
				}
			}
			
			List<ArchivesUseOutInfo> archivesUseOutInfos = new ArrayList<ArchivesUseOutInfo>();
			//����ҵ���߼�����ѯ���������ó�ȥ��ϸ��Ϣ����
			if(pFlag){
				pErrPos = 4;				
				if(archivesUseOutInfoManageService.findArchivesUseOutInfosByRegisterID(archivesUseRegister.getID(), archivesUseOutInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�������õǼǱ�Ų������ó�ȥ��ϸ��Ϣʧ�ܣ�");
				}
			}
			
			List<ArchivesUseOutInfo> dateOutArchivesUseOutInfos = new ArrayList<ArchivesUseOutInfo>();
			if (pFlag) {
				pErrPos = 5;
				
				//������ڵ����б�
				if(useOutDateFlag){
					for (int i= 0;i<archivesUseOutInfos.size();i++) {
						if(archivesUseOutInfos.get(i).getShouldReturnDate().before(new Date())){
							dateOutArchivesUseOutInfos.add(archivesUseOutInfos.get(i));
						}
					}
					archivesUseOutInfos.clear();
					archivesUseOutInfos.addAll(dateOutArchivesUseOutInfos);
				}
				
				//���쵽��Ԥ�������б�
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
			
			
			//�����������ҳ��
			if (pFlag) {
				pErrPos = 3;
				System.out.println("request.setAttributrchivesUseRegister");
			//	request.setAttribute("operate", request.getParameter("operate"));
				request.setAttribute("archivesUseRegister",archivesUseRegister);
				request.setAttribute("archivesUseOutInfos",archivesUseOutInfos);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("useType",useType);//���÷�ʽ��JD���赵��CD���鵵
				request.setAttribute("useTypeText", useTypeText);//���÷�ʽ�ı�
				
				forward = "toDJView";
				if(useOutDateFlag){
					forward = "toOutDateDJView";
				}
				System.out.println("forWard:"+forward);
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

			System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
System.out.println(pErrInfo.toString());
			//���پֲ�����
			throwable = null;
		}	
		return forward;	
		
	}
	
	
	/**
	 * DWR:��������������루����������Ϣ�Ǽ�ҳ���е�ȷ����ťʱ������
	 * @return
	 * @throws Exception
	 */
	public String addRegisterInfoForOnlineApply(UserInfo userInfo,String userDepartment, String requestReason, HttpServletRequest request) throws Exception{
	//public String addRegisterInfoForOnlineApply() throws Exception{
		/*
		 * ���е��û������������û�����ע���û���
		 * ���裺1����ȡ�û���Ϣ�����ҳ�����д�userID�ţ���ֱ���ô�ID�����еǼǣ����û����Ҫ������û����û���Ϣ���У�������Ȩ�ޣ��������û���ID
		 *       2����������Ϣд���ǼǱ���
		 *       3����������Ϣд������������ϸ����
		 *       4�������ԭ������Ļ���Ҫ����Ϣд��ԭ���������      
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
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();//Session �д�ŵĵ�����Ϣ����
		ArchivesUseRequest archivesUseRequest = new ArchivesUseRequest();
		try {
			pErrPos = 1;
			
			//begin:���SESSION�������б����Ƿ�������
			archivesUseList = (List<StoreroomArchivesInfo>)session.getAttribute("archivesUseList");
			if(archivesUseList == null ){
				throw new Exception("��ȡ���õ����б�ʧ�ܣ�");
			}else if(archivesUseList.size() == 0){
				throw new Exception("�����б���û�пɱ����õĵ�����������������������Ӵ����õĵ�����");
			}	
			//1:����û���Ϣ����֤�û������ȷ
			if (pFlag) {
				pErrPos = 2;
				if (userID == 0) {	
					//����û����Ƿ����
					if(userInfoManageService.saveUserInfo(userInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"�����������Ϣʧ�ܣ�");
					}else{//�ɹ�����û�
						userID = userInfo.getUserID();
						UserRolesInfo userRolesInfo = new UserRolesInfo();
						userRolesInfo.setUserID(userID);
						userRolesInfo.setRolesID(SystemInitializer.getInstance().getAnonymouseUserRoleID());
System.out.println("userRolesInfo.getRolesID():"+userRolesInfo.getRolesID());
						//���û���Ȩ
						if(userRolesInfoManageService.saveUserRolesInfo(userRolesInfo, pErrInfo)==false){
							pFlag = false;
							pErrInfo.getContent().insert(0,"���һ���µ��û���ɫ��Ϣʧ�ܣ�");
						}
					}					
				}
			}
			
			//2:��������Ϣд�����õǼǱ���
			if (pFlag) {
				pErrPos = 3;	
				archivesUseRequest.setRequestReason(requestReason);
				archivesUseRequest.setRequestTime(new Date());
				archivesUseRequest.setUserID(userID);
				archivesUseRequest.setUserDepartment(userDepartment);//�û���Ϣ��
				if (archivesUseRequestManageService.addArchivesUseRequest(archivesUseRequest, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ӵ����������뵥��Ϣʧ�ܣ�");
				}
			}
			
			//��������Ϣд������������ϸ����
			if (pFlag) {
				pErrPos = 4;							
				ArchivesUseRequestDetail archivesUseRequestDetail;
				for (StoreroomArchivesInfo storeroomArchivesInfo : archivesUseList) {
					//��ȡ����������Ϣ
					ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(storeroomArchivesInfo.getArchivesTypeID());
					//��ȡ������Ϣ
					ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);				
					if (archivesInfoQueryService.findArchivesInfoByNBXH(archivesType, storeroomArchivesInfo.getNBXH(), archivesInfo, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"�����ڲ���Ų��ҵ�����Ϣʧ�ܣ�");
					}
					//��ӵ����������뵥��ϸ
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
						pErrInfo.getContent().insert(0,"��ӵ����������뵥��ϸʧ�ܣ�");
					}else{//��ӳɹ�,��ӵ���ԭ�ĵ�����ͨ����Ϣ�������������ʱ����ɵ�
//						if(archivesUseRequestDetail.getUseWayID()==3){
//							AttachedFileUseRequestPassInfo attachedFileUseRequestPassInfo = new AttachedFileUseRequestPassInfo();
//							attachedFileUseRequestPassInfo.setArchivesID(archivesInfo.getArchivesID());
//							attachedFileUseRequestPassInfo.setArchivesTypeID(archivesInfo.getArchivesTypeID());
//							//����ԭ����Чʹ��ʱ��,��ʽ������ʱ��+������ʱ������
//							Date now = new Date();
//							attachedFileUseRequestPassInfo.setExpirationDate(new Date(now.getTime()+ (long)7*24*3600*1000));
//							attachedFileUseRequestPassInfo.setNBXH(archivesInfo.getNBXH());
//							attachedFileUseRequestPassInfo.setTitle(archivesInfo.getTitle());
//							attachedFileUseRequestPassInfo.setUserID(archivesUseRequest.getUserID());
//							if(attachedFileUseRequestPassInfoManageService.addAttachedFileUseRequestPassInfo(attachedFileUseRequestPassInfo, pErrInfo)==false){
//								pFlag = false;
//								pErrInfo.getContent().insert(0,"��ӵ����������뵥��ϸʧ�ܣ�");
//							}
//
//						}
					}
				}
			}
			
			//���Session�д�ŵ����õ����б�
			if(pFlag){
				pErrPos = 5;
				session.removeAttribute("archivesUseList");
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
				forWard = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
				System.out.println(pErrInfo.toString());
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
		}

		return "";
	}
	
	
	/**
	 * �õ������������õ��û���Ϣ�Ǽ�ҳ������ĳ�ʼ����<br>
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
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//����Ŀ��
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
		List<StoreroomArchivesInfo> archivesUseList = new ArrayList<StoreroomArchivesInfo>();
		String useType = "";
		try {
			//��ʼ���� 1...
			
			if (pFlag) {
				pErrPos = 1;
				if (session==null) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡSessionʧ�ܣ�");
				}else{
					archivesUseList = (List<StoreroomArchivesInfo> )session.getAttribute("archivesUseList");
					if (archivesUseList==null) {
						pFlag = false;
						pErrInfo.getContent().append("��ȡ�����б���Ϣʧ�ܣ�");
					}else if(archivesUseList.size()==0){
						pFlag = false;
						pErrInfo.getContent().append("û�пɱ���ӡ�ļ�¼��");
					}
				}
			}

			////����ҵ���߼�	
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ʼ����������Ŀ��ʧ�ܣ�");
				}	
			}
			
			
			
			
			//�����������ҳ��
			if (pFlag) {
				pErrPos = 3;
				//����������˻����ͽ����û������Ϊ0���Թ�ҳ����֤
				if(userInfo.getAnonymouseFlag()==false){
					//userInfo.setUserID(0);
					userInfo = new UserInfo();
				}
				request.setAttribute("userInfo", userInfo);
				request.setAttribute("anonymouseFlag",true);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);//����Ŀ��				
				forWard = "registUserInfoForOnlineApply";
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
				forWard = "error";
				request.setAttribute("pErrInfo",pErrInfo);
System.out.println("pErrInfo.toString():"+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}	
		return forWard;
	}
	
	
	/**
	 * �õ� �鵵/�赵 ҳ���ʼ������<br>
	 * ����Ŀ�ļ��� �������򼯺�
	 * @return 
	 * @throws Exception
	 */
	public String findDefaultDataForDJ() throws Exception{
		String forWard = "";		
		String useTypeText = "�赵";
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesUsePurpose> archivesUsePurposes = new ArrayList<ArchivesUsePurpose>();//����Ŀ��
		String useType = "";
		int useDays = 0;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if("".equals(request.getParameter("useType")) || request.getParameter("useType")==null){
				pFlag = false;
				pErrInfo.getContent().append("��ȡ���÷�ʽʧ�ܣ�");
			}else{
				useType = request.getParameter("useType");
				if("CD".equals(useType)){
					useTypeText = "�鵵";
				}
			}

			////����ҵ���߼�	
			if (pFlag) {
				pErrPos = 2;		
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ʼ����������Ŀ��ʧ�ܣ�");
				}else{
					System.out.println("��ʼ����������Ŀ�ĳɹ���������:"+ archivesUsePurposes.size());
				}		
			}
			
			//��ȡ��������
			if (pFlag) {
				pErrPos = 3;
				if("JD".equals(useType)){//����ǽ赵������������Ҫ�����ñ���ȡ
					List<Config> pConfigs = new ArrayList<Config>();
					if(configManageService.findConfigByConfigType("UseRenewPeriodDays", pConfigs, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"�����ñ���ȡ�������õ���������ʧ�ܣ�");
					}else{
						if(pConfigs.size()>0){
							useDays = Integer.parseInt(pConfigs.get(0).getConfigValue());
						}else{
							pFlag = false;
							pErrInfo.getContent().insert(0,"���ñ�δ�ҵ��������õ���������(UseRenewPeriodDays)��");
						}
					}
				}else{//����ǲ鵵������������Ϊ1
					useDays = 1;
				}		
			}
			
			//�����������ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("useDays", useDays);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("useType",useType);//���÷�ʽ��JD���赵��CD���鵵
				request.setAttribute("useTypeText", useTypeText);//���÷�ʽ�ı�
				
				forWard = "toJDDJ";
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
		return forWard;
	}
	
	/**
	 * ��ת���������ò�ѯ<br>��ȡһЩϵͳ����
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
			
			//��ȡ����Ŀ��		
			if (pFlag) {
				pErrPos = 2;
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ����Ŀ��ʧ��:");
				}				
			}
			

			if(pFlag){
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("archivesUseWays",archivesUseWays);//���÷�ʽ�̻���ʵ������
				forward = "toLYCX";
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
		return forward;		
	}
	
	
	/**
	 * ����������ѯ�������ó�ȥ��ϸ��¼<br/>
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
			//��ʼ���� 1...
			pErrPos = 1;
			
			//��ȡ����Ŀ��		
			if (pFlag) {
				pErrPos = 2;
				if (archivesUsePurposeManageService.findAllArchivesUsePurpose(archivesUsePurposes, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ����Ŀ��ʧ��:");
				}				
			}
			
			if (pFlag) {
				pErrPos = 3;
				if(archivesUseWayManageService.findAllArchivesUseWay(archivesUseWays, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"��ȡ�������÷�ʽʧ�ܣ�");
				}
			
			}

			//����ҵ���߼���ִ�в�ѯ����
			if (pFlag) {
				pErrPos = 4;
				dataPageInfo.setPageSize(15);
				if(archivesUseOutInfoManageService.findArchivesUseOutInfosByQueryCondition(archivesUseInfoQueryCondition, dataPageInfo, archivesUseOutInfos, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����������ѯ����������ϸ��Ϣʧ�ܣ�");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesUseOutInfos", archivesUseOutInfos);
				request.setAttribute("archivesUsePurposes",archivesUsePurposes);
				request.setAttribute("archivesUseWays",archivesUseWays);
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
		return "toLYCX";
	}
	

	/**
	 * ����������ѯ�û����������뵥�б��������뵥��ѯ��
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
			//��ʼ���� 1...
			pErrPos = 1;

			//����ҵ���߼���ִ�в�ѯ����
			if (pFlag) {
				pErrPos = 4;
				dataPageInfo.setPageSize(15);
				if(archivesUseRequestManageService.findArchivesUseRequestsByCondition(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����������ѯ����������ϸ��Ϣʧ�ܣ�");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 5;
				request.setAttribute("archivesUseRequests", archivesUseRequests);
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
		return "toQueryOnline";
	}
	
	
	/**
	 * ����������ѯ�û����������뵥�б��������뵥��ѯ��
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
			//��ʼ���� 1...
			pErrPos = 1;
			if(request.getParameter("requestID")!=null && !"".equals(request.getParameter("requestID").trim())){
				requestID = request.getParameter("requestID").trim();
			}else{
				pFlag = false;
				pErrInfo.getContent().append("�������뵥��ŷǷ���Ϊ�գ�");
			}

			//����ҵ���߼���ִ�в�ѯ����
			if (pFlag) {
				pErrPos = 2;				
				if(archivesUseRequestDetailManageService.findArchivesUseRequestDetailsByRequestID(requestID, archivesUseRequestDetails, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����������ѯ����������ϸ��Ϣʧ�ܣ�");
				}
			}
			
			//�����ݷ��ص�ҳ��
			if (pFlag) {
				pErrPos = 3;
				request.setAttribute("archivesUseRequestDetails", archivesUseRequestDetails);
				request.setAttribute("recordSize", archivesUseRequestDetails.size());
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
System.out.println(pErrInfo.toString());
			//���پֲ�����
			throwable = null;
		} 		
		return "toOnlineRequestDetailList";
	}
	
	/**
	 * ��ת�����赵��ҳ��<br>��ȡһЩϵͳ����
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
			
			//����ҵ���߼���ȡ��������
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("UseRenewPeriodDays", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ñ��л�ȡ��������ʧ�ܣ�");
				}else{
					dayNum = Integer.parseInt(pConfigs.get(0).getConfigValue());
				}
			}
			
			//�����ݷ��ص�ҳ��
			if(pFlag){
				request.setAttribute("dayNum",dayNum);
				forward = "toXJDJ";
			
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
		return forward;		
	}
	
	/**
	 * DWR���ã����赵��
	 * ����Ҫ�Ĳ�����daysNum:����������archivesBarcode:����������
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
			//��ʼ���� 1...
			pErrPos = 1;

			//����ҵ���߼�ִ���������
			if (pFlag) {
				pErrPos = 2;
				archivesUseOutInfo.setArchivesBarcode(archivesBarcode);
				if(archivesUseManageService.renewArchivesByBarcode(daysNum, archivesUseOutInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ���������ʧ�ܣ�");
				}else{
					System.out.println("ִ����������ɹ���");
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
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
			System.out.println(archivesUseOutInfo.getTitle());
		}
		return archivesUseOutInfo;
	}
	
	/*
	 * DWR���ã��黹�����/�鿴���ã�ʵ�ﵵ��
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
			//��ʼ���� 1...
			pErrPos = 1;

			//����ҵ���߼�ִ���������
			if (pFlag) {
				pErrPos = 2;
				archivesUseOutInfo.setArchivesBarcode(archivesBarcode);
				if(archivesUseManageService.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ�й黹����ʧ�ܣ�");
				}else{
					System.out.println("ִ�й黹�����ɹ���");
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
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
			System.out.println(archivesUseOutInfo.getTitle());
		}
		return archivesUseOutInfo;
	}
	
	
	
	/**
	 * ����Ԥ���������ڵĵ�����Ϣ��ѯ����
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
		int dayNum = 0;//����Ԥ���������������ݿ���ȡ
		List<Config> pConfigs = new ArrayList<Config>();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			//��ȡ��������
			if (pFlag) {
				pErrPos = 2;
				if(configManageService.findConfigByConfigType("UseAdvanceDueWarningDays", pConfigs, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"�����ñ��л�ȡ������ǰԤ��������ʧ�ܣ�");
				}else{
					dayNum = Integer.parseInt(pConfigs.get(0).getConfigValue());
				}
			}
System.out.println("dayNum:"+dayNum);

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findExpiringArchivesUseRegister(dayNum,dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ҫ���ڴ߻��ĵ�����������Ϣʧ�ܣ�");
				}else{
					System.out.println("archivesUseManageService.findExpiringArchivesUseRegister success!");
					System.out.println("archivesUseRegisters.size():"+archivesUseRegisters.size());
				}
			}
			
			//�����ݷ��ص�ҳ��
			if(pFlag){
				pErrPos = 3;
				request.setAttribute("archivesUseRegisters",archivesUseRegisters);
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return "toDQYJ";
	}
	
	/**
	 * ���ڴ߻������ҹ��ڴ߻��ĵ�����������Ϣ
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
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				dataPageInfo.setPageSize(15);
				if(archivesUseManageService.findArchivesUseExpiredUseRegister(dataPageInfo, archivesUseRegisters, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ҫ���ڴ߻��ĵ�����������Ϣʧ�ܣ�");
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
				forWord = "error";
				request.setAttribute("pErrInfo", pErrInfo);
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}
		return forWord;
	}
	


	

	/**
	 * ����δ��˵ĵ�������������ϸ��Ϣ
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
//		DataItem dataItem = new DataItem();//��¼������ʵ��
//		if(userName == null){
//			userName = "";
//		}
//		dataItem.setColumnName(userName);//��������
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//��ʼ���� 1...
//			pErrPos = 1;
//			
//
//			//����ҵ���߼�������������ѯ�û���Ϣ����
//			if (pFlag) {
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//					//���ɲ�������
//					//setArchivesUseRequests(archivesUseRequests);
//System.out.println("--> findArchivesUseRequestDetailsByName   userName: " +userName);
//					result = "success";
//				}else{
//					pFlag = false;//����ҵ���߼�������ʶΪʧ��
//				}
//			}			
//			pErrPos = 2;
//			//����ѯ�������Ϣ���ص�ҳ��
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
	 * ��ӡ����
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
//		DataItem dataItem = new DataItem();//��¼������ʵ��
//
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//��ʼ���� 1...
//			pErrPos = 1;
//			
//
//			//����ҵ���߼�������������ѯ�û���Ϣ����
//			if (pFlag) {
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//					//���ɲ�������
//				//	setArchivesUseRequests(archivesUseRequests);
//
//					result = "success";
//				}else{
//					pFlag = false;//����ҵ���߼�������ʶΪʧ��
//				}
//			}			
//			pErrPos = 2;
//			//����ѯ�������Ϣ���ص�ҳ��
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
	 * ��������������õǼ���Ϣ����������ͨ������
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
//		DataItem dataItem = new DataItem();//��¼������ʵ��
////		if(userName == null){
////			userName = "";
////		}
////		dataItem.setColumnName(userName);//��������
//		ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition = new ArchivesUseRequestQueryCondition();
//
//		HttpServletRequest request = ServletActionContext.getRequest();	
//		try {
//			//��ʼ���� 1...
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
//			//����ҵ���߼�������������ѯ�û���Ϣ����
//			if (pFlag) {
//				
//				if(true/*archivesUseManageService.unregisterArchivesUse(archivesUseRegisters, pErrInfo)*/){
//					//���ɲ�������
//				//	setArchivesUseRequests(archivesUseRequests);
//
//					result = "success";
//				}else{
//					pFlag = false;//����ҵ���߼�������ʶΪʧ��
//				}
//			}			
//			pErrPos = 3;
//			//����ҵ���߼�����ѯ����
//			if(pFlag){
//				if(true/*archivesUseManageService.findArchivesUseRequests(archivesUseRequestQueryCondition, dataPageInfo, archivesUseRequests, pErrInfo)*/){
//
//				//	setArchivesUseRequests(archivesUseRequests);
//					
//				}else{
//					pFlag = false;//����ҵ���߼�������ʶΪʧ��
//					result = "error";
//				}
//			}
//			//����ѯ�������Ϣ���ص�ҳ��
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
	 * DWR: ���û��ύ�����������������
	 * @param ID ���뵥��ϸ���
	 * @param checkResult �������
	 * @param backReason �������
	 * @return 0/1/2:  0����ִ��ʧ�ܣ�1����ͨ����2����ͨ��
	 * @throws Exception
	 */
	public int checkArchivesUseRequestDetail(int ID ,int checkResult,String backReason,HttpServletRequest request)throws Exception{
		int result = checkResult;
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();	
		backReason = backReason.trim();//ȥ���ַ����еĿո�
		ArchivesUseRequestDetail archivesUseRequestDetail = new ArchivesUseRequestDetail();
		UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
System.out.println("ID:"+ID);
				
		try {		
			pErrPos = 1;			
			//��������װ��������ȥ						
			archivesUseRequestDetail.setID(ID);//���뵥��ϸ���
			archivesUseRequestDetail.setCheckTime(new Date());//����ʱ��
			archivesUseRequestDetail.setCheckResult(checkResult);//�������
			archivesUseRequestDetail.setBackReason(backReason);//�������
			archivesUseRequestDetail.setCheckUserID(userInfo.getUserID());//�����˱��

			//����ҵ���߼�������������ѯ�û���Ϣ����
			if (pFlag) {
				pErrPos = 2;
				if(archivesUseRequestDetailManageService.checkArchivesUseRequestDetail(archivesUseRequestDetail, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"����ָ���ĵ����������뵥��ϸ���������ʧ�ܣ�");
				}else{
					System.out.println("����ָ���ĵ����������뵥��ϸ����������ɹ���");					
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
	 * �ж������嵥���Ƿ��иõ�����������Ϣ�����û�У��򷵻�true
	 * @param archivesTypeId ����������
	 * @param NBXH 
	 * @param storeroomArchivesInfos �����嵥
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
