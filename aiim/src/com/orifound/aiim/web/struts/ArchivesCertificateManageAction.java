package com.orifound.aiim.web.struts;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesCertificateInfoManageService;
import com.orifound.aiim.bll.service.ICertificateStudentManageService;
import com.orifound.aiim.bll.service.IStudentGradeInfoManageService;
import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.bll.service.IArchivesCertificateRegisterManageService;
import com.orifound.aiim.entity.ArchivesCertificateInfo;
import com.orifound.aiim.entity.ArchivesCertificateRegister;
import com.orifound.aiim.entity.CertificateType;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.DateQuerycondition;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;
import com.orifound.aiim.entity.StudentGradeInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.StringTool;
import com.orifound.aiim.web.util.WebCommonUtil;

/**
 * ������֤���� Action
 *
 */
public class ArchivesCertificateManageAction extends BaseAction{
	private static final long serialVersionUID = -3340651233191995620L;
	
	static Log logger = LogFactory.getLog(AttachedFileManageAction.class);
	
	/**
	 * ע�뵵����������Ϣ������������
	 */
	@Autowired
	private IArchivesCertificateInfoManageService archivesCertificateInfoManageService;
	
	/**
	 * ע���֤ѧ����Ϣ����������
	 */
	@Autowired
	private ICertificateStudentManageService certificateStudentManageService;
	
	/**
	 * ע��ѧ���ɼ���Ϣ�����������
	 */
	@Autowired
	private IStudentGradeInfoManageService studentGradeInfoManageService;
	
	/**
	 * ע�뵵����������Ϣ������������
	 */
	@Autowired
	private IArchivesCertificateRegisterManageService archivesCertificateRegisterManageService;
	
	/**
	 * ���صĽ��URL
	 */
	private String resultURL;
	
	/**
	 * �˵��ڵ�ֵ
	 */
	private String nodeId;
	
	/**
	 * ������֤���ͼ���
	 */
	private List<CertificateType> certificateTypes = new ArrayList<CertificateType>();
	
	/**
	 * ������֤���ͼ���JSON��ʽ
	 */
	private String certificateTypesJSON ;
	
	/**
	 * ������id
	 */
	private int userID;
	
	/**
	 * Ӧ�ɽ��
	 */
	private double shouldCharge=0;
	
	/**
	 * ʵ���շ�
	 */
	private double realCharge=0;
	
	/**
	 * ��Ʊ����
	 */
	private String invoiceSN=null;
	
	/**
	 * ��ע
	 */
	private String remark=null;
	
	/**
	 * ��֤��ϸJSON��ʽ
	 */
	private String certificateInfoArrayJSON;
	
	/**
	 * ����������
	 */
	private String personName;
	
	/**
	 * ֤����
	 */
	private String cardId;
	
	/**
	 * ��֤����id
	 */
	private String certificateTypeId;
	
	/**
	 * ��֤�Ǽ�id
	 */
	private int certificateRegID; 
	
	/**
	 * ��֤��Ϣid
	 */
	private int certificateInfoID;
	
	/**
     * ��File��������װ����ϴ��ļ�����󣬽��ձ��ļ���
     */
    private File[] upload;   
  
    /**
     * ��String��������װ����ϴ��ļ���
     */
    private String[] uploadFileName;   

    /**
     * ��String��������װ����ϴ��ļ�����  
     */
    private String[] uploadContentType;  
	
	/**
     * �����ļ���Ŀ¼·��
     */
    private String savePath;   
    
    /**
     * Ҫ���ص��ļ���    
     */
    private String downloadFileName; 
    
    /**
     * ��װ�����ļ�����   
     */
    private String downloadContentType; 
    
    /**
     * ��װҪ���ص��ļ�
     */
    private File downloadFile;
    
    /**
     * ֤���ļ�����
     */
    private String certificateFileName;
    /**
     * ������ɱ�־
     */
    private boolean dealedFlag;
    
    /**
     * ѧ��
     */
    private String XH;
	
	/**
	 * ��֤�Ǽ���Ϣ����
	 */
	private List<ArchivesCertificateRegister> archivesCertificateRegisters = new ArrayList<ArchivesCertificateRegister>();
	
	/**
	 * ѧ����Ϣ
	 */
	private CertificateStudent certificateStudent = new CertificateStudent();
	
	/**
	 * רҵ��Ϣ����
	 */
	private List<Major> majors = new ArrayList<Major>();
	
	/**
	 * ѧԺ��Ϣ����
	 */
	private List<College> colleges = new ArrayList<College>();
	
	/**
	 * ѧ����Ϣ���±�־
	 * y:���¡�n:����
	 */
	private String updateStudentFlag = "n";
	
	/**
	 * ѧ���ɼ���Ϣ����json��ʽ
	 */
	private String gradeArrayJSON;
	
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
	 * ���ڲ�ѯ����
	 */
	private DateQuerycondition dateQuerycondition = new DateQuerycondition();
	
	public DateQuerycondition getDateQuerycondition() {
		return dateQuerycondition;
	}

	public void setDateQuerycondition(DateQuerycondition dateQuerycondition) {
		this.dateQuerycondition = dateQuerycondition;
	}

	/**
	 * ���� ��������->��֤���� �˵���
	 * @return
	 */
	public String getTree() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String tree = CreateTreeUtil.getCertificateTree();
		request.setAttribute("tree",tree);
		request.setAttribute("proceseAction","archivesCertificateManageAction_manage.action");
		resultURL = "/left.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����nodeIdֵ
	 * �жϻ�ȡ�ĸ������� 
	 * @return
	 */
	public String manage() {
		if(StringTool.checkNull(nodeId)) {
			Integer node = 0;
			try {
				node = Integer.valueOf(nodeId);
				
				archivesCertificateInfoManageService.findCertificateTypes(certificateTypes, new ErrInfo());
				certificateTypesJSON = JSONArray.fromObject(certificateTypes).toString();
			} catch (Exception e) {
				node = 1;
				e.printStackTrace();
			}
			//�����˵���
			getTree();
			
			switch (node) {
			case 1:
				//��֤����
				break;
			case 11:
				//�շѵǼ�
				priceRegist();
				resultURL = "/DALY/balanceRegist.jsp";
				break;
			case 12:
				//��֤����
				certificateMake();
				break;
			case 13:
				//�շ�ͳ��
				balanceStat();
				resultURL = "/DALY/chargeCollect.jsp";
				break;
			default:
				break;
			}
		}
		return SUCCESS;
	}

	//�շѵǼ�
	private void priceRegist() {
	}
	
	/**
	 * �շѽ���
	 */
	public String priceBalance() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = getRequest();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
				//����������֤�Ǽ�ʵ��
				ArchivesCertificateRegister certificateRegister = new ArchivesCertificateRegister(0,userID,shouldCharge,realCharge,new Date(),invoiceSN, userInfo.getUserID(),remark);
				//����������֤��ϸ��Ϣ
				List<ArchivesCertificateInfo> archivesCertificateInfos = new ArrayList<ArchivesCertificateInfo>();
				
				JSONArray jsonArray = JSONArray.fromObject(certificateInfoArrayJSON);
				if (jsonArray==null || jsonArray.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("JSON����ת��ʧ�ܡ�");
				}
				//ѭ������json����
				ArchivesCertificateInfo archivesCertificateInfo = null;
				JSONObject jsonObject = null;
				if (pFlag) {
					System.out.println(certificateInfoArrayJSON);
					 for(int i = 0; i < jsonArray.size(); i++){   
						 jsonObject = jsonArray.getJSONObject(i);
						 archivesCertificateInfo = new ArchivesCertificateInfo();
						 archivesCertificateInfo.setCertificateTypeID(jsonObject.getInt("certificateTypeID"));
						 archivesCertificateInfo.setTotal(jsonObject.getInt("total"));
						 archivesCertificateInfo.setExpressFlag(jsonObject.getBoolean("expressFlag"));
						 
						 archivesCertificateInfos.add(archivesCertificateInfo);
			       }   
				}
				
				if (archivesCertificateInfoManageService.saveArchivesCertificateInfo(certificateRegister, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "Action ��֤�շѵǼ� ʧ�ܣ�");
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
		resultURL = "/DALY/CZDJ.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��֤����
	 */
	public String certificateMake() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				archivesCertificateInfoManageService.findCertificateTypes(certificateTypes, new ErrInfo());
				
				Map<String, String> queryString = new HashMap<String, String>();
				//����������
				if (StringTool.checkNull(personName)) {
					queryString.put("personName", personName);
				}
				//������֤����
				if (StringTool.checkNull(cardId)) {
					queryString.put("cardId", cardId);
				}
				
				if (archivesCertificateInfoManageService.findArchivesCertificateRegistersByQuery(queryString, archivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��֤�Ǽ���Ϣʧ�ܣ�");
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
		
		resultURL = "/DALY/certificateMake.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��֤��������
	 * @return
	 */
	public String certificateMakeDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//���ݳ�֤�Ǽ�id���ҵ�����֤��Ϣ
			if (pFlag) {
				pErrPos = 2;
				List<ArchivesCertificateInfo> archivesCertificateInfos = new ArrayList<ArchivesCertificateInfo>();
				if (archivesCertificateInfoManageService.findArchivesCertificateInfosByRegisterId(certificateRegID, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, " ���ݳ�֤�Ǽ�id���ҵ�����֤��Ϣ ʧ�ܣ�");
				} else {
					getRequest().setAttribute("archivesCertificateInfos", archivesCertificateInfos);
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
		resultURL = "/DALY/certificateMake_detail.jsp";
		return SUCCESS;
	}
	
	 /**
     * �ļ����ط��ص�IO��
     * @return
     * @throws Exception
     */
    public InputStream getInputStream() throws Exception
    {
		return new BufferedInputStream(new FileInputStream(downloadFile));
    }

	/**
	 * ��֤�ļ�����
	 * @return
	 */
	public String downloadFile() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;

			if (pFlag) {
				pErrPos = 2;
				downloadContentType = "application/octet-stream";//�����������ļ����� 
				 /**
			     * Ҫ�����ļ���·��
			     */
			    String filePath = null;
			    
			    dealedFlag = false;
			    certificateFileName = "ѧ����ҵ֤��.doc";
				/*�ж��Ƿ��������*/
				if (dealedFlag) { //ʹ��webservice�����ļ�
					
				} else {	//����ģ���ļ�
					filePath = getRequest().getSession().getServletContext().getRealPath("/certificateTempFile/"+certificateFileName);
				}
				
				downloadFile = new File(filePath);
				if (downloadFile==null || downloadFile.isDirectory() || downloadFile.exists()==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���صĳ�֤�ļ������ڣ�");
				} else {
					String fileName = downloadFile.getName();
					DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
					//�ļ���׺".txt"
					String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
					//�ļ���"xxxxx_20100829090909333"
					String fileName_front = fileName.substring(0, fileName.lastIndexOf('.'))+"_"+dateFormat.format(new Date());
					downloadFileName = fileName_front+ext;
			    	downloadFileName = new String(downloadFileName.getBytes(), "ISO8859-1"); //������Ҫ����Ȼ�ļ���Ϊ����
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
		System.out.println(pErrInfo.toString());
		return SUCCESS;
	}
	
	/**
	 * ��֤�ļ��ϴ�
	 * @return
	 */
	public String uploadFile() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			if (pFlag) {
				//����Ƿ��н���BLL����ע��
				if (checkBllInjection(pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			if (pFlag) {
				pErrPos = 1;
				//����id��ѯ��֤����
				ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo();
				if (archivesCertificateInfoManageService.findArchivesCertificateInfoByID(certificateInfoID, archivesCertificateInfo , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��Ψһ��ʶ���ҵ�����֤��ϸ�����Ϣ ʧ�ܣ�");
				}
				
				//����Ƿ�����ϴ��ļ�
				if (pFlag) {
					if (upload.length <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("�ϴ��ļ�������");
					} 
				}
				
				//�޸�ָ���ĵ�����֤��ϸ���
				if (pFlag) {
					archivesCertificateInfo.setCertificateFileName(uploadFileName[0]);
					archivesCertificateInfo.setFileUploadDate(new Date());
					archivesCertificateInfo.setDealedFlag(true);
					if (archivesCertificateInfoManageService.updateArchivesCertificateInfo(archivesCertificateInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().append("�޸�ָ���ĵ�����֤��ϸ��� ʧ�ܣ�");
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
		resultURL = "/DALY/certificateMake_upload.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����ѧ�Ų�ѯѧ����Ϣ
	 * @return String
	 */
	public String findStudentInfo() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//�������е�רҵ��Ϣ
			if (pFlag) {
				if (certificateStudentManageService.findAllMajor(majors, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�רҵ��Ϣ ʧ�ܣ�");
				}
			}
			
			//�������е�ѧԺ��Ϣ
			if (pFlag) {
				if (certificateStudentManageService.findAllCollege(colleges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ѧԺ��Ϣ ʧ�ܣ�");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//����Ψһ��ʶ���ҳ�֤ѧ����Ϣ��Ϣ
				if (StringTool.checkNull(XH)) {
					certificateStudent = new CertificateStudent();
					if (certificateStudentManageService.findCertificateStudentByID(XH, certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҳ�֤ѧ����Ϣ��Ϣ ʧ�ܣ�");
					} else {
						//����Ƿ����ѧ����Ϣ
						if (StringTool.checkNull(certificateStudent.getXH())) {
							//���ø��±�־
							updateStudentFlag = "y";
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
		resultURL = "/DALY/certificateMake_studentInfo.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����/�޸�ѧ����Ϣ
	 * @return
	 */
	public String saveOrUpdateStudentInfo() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//�ж�ѧ��
			if (pFlag) {
				pErrPos = 2;
				if (StringTool.checkNull(certificateStudent.getXH()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("��ȡҳ�����ѧ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//����Ψһ��ʶ���ҳ�֤ѧ����Ϣ
			CertificateStudent certificateStudentOther = null;
			if (pFlag) {
				certificateStudentOther = new CertificateStudent();
				if (certificateStudentManageService.findCertificateStudentByID(certificateStudent.getXH(), certificateStudentOther, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҳ�֤ѧ����Ϣ��Ϣ ʧ�ܣ�");
				}
			}
			
			if (pFlag) {
				//����ѧ����Ӧ�ĳ�֤��Ϣid
				certificateStudent.setCertificateInfoID(certificateInfoID);
				//�ж��Ƿ����ѧ����Ϣ
				if (StringTool.checkNull(updateStudentFlag) && "y".equals(updateStudentFlag)) {
					//����ѧ����Ϣ
					if (certificateStudentManageService.updateCertificateStudent(certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "�޸�ָ���ĳ�֤ѧ����Ϣ ʧ�ܣ�");
					}
				} else {
					//����ѧ����Ϣ
					if (certificateStudentManageService.saveCertificateStudent(certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "����ָ���ĳ�֤ѧ����Ϣ ʧ�ܣ�");
					}
				}
			}
			
			//���¹����ĵ�����֤��Ϣ
			if (pFlag) {
				ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo();
				archivesCertificateInfo.setID(certificateInfoID);
				archivesCertificateInfo.setXH(certificateStudent.getXH());
				if (archivesCertificateInfoManageService.updateArchivesCertificateInfoXH(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ָ���ĵ�����֤��Ϣѧ�� ʧ�ܣ�");
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
		resultURL = "/DALY/certificateMake_studentInfo.jsp";
		return SUCCESS;
	}
	
	/**
	 * ��ѯѧ���ɼ�(��������ѡ��)
	 * @return
	 */
	public String findGrade() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			//����Ψһ��ʶ���ҳ�֤ѧ����Ϣ
			if (pFlag) {
				if (certificateStudentManageService.findCertificateStudentByID(XH, certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����Ψһ��ʶ���ҳ�֤ѧ����Ϣ ʧ�ܣ�");
				}
			}
			
			//��ѯָ��ѧ�������пγ̳ɼ���Ϣ
			List<StudentGradeInfo> studentGradeInfos = null;
			if (pFlag) {
				studentGradeInfos = new ArrayList<StudentGradeInfo>();
				if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯָ��ѧ�������пγ̳ɼ���Ϣ ʧ�ܣ�");
				}
			}
			
			//�ж��Ƿ����ѧ���γ̳ɼ���Ϣ
			if (pFlag) {
				if (studentGradeInfos.size() <= 0) {
					//����ѧ�����пγ̳ɼ���Ϣ
					if (pFlag) {
						pErrPos = 2;
						if (studentGradeInfoManageService.saveStudentAllGrade(XH, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "����ѧ�����пγ̳ɼ���Ϣ ʧ�ܣ�");
						}
					}
					
					//��ѯָ��ѧ�������пγ̳ɼ���Ϣ
					if (pFlag) {
						studentGradeInfos = new ArrayList<StudentGradeInfo>();
						if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "��ѯָ��ѧ�������пγ̳ɼ���Ϣ ʧ�ܣ�");
						}
					}
				}
			}
			getRequest().setAttribute("studentGradeInfos", studentGradeInfos);
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
		resultURL = "/DALY/certificateMake_grade.jsp";
		return SUCCESS;
	}
	
	/**
	 * ����ѧ���ɼ�
	 * @return String
	 */
	public String updateGrage() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//����Ƿ��н���BLL����ע��
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				System.out.println("gradeArrayJSON="+gradeArrayJSON);
				JSONArray array = JSONArray.fromObject(gradeArrayJSON);
				List<StudentGradeInfo> studentGradeInfos = new ArrayList<StudentGradeInfo>();
				StudentGradeInfo gradeInfo = null;
				for(int i=0; i<array.size(); i++) {
					JSONObject object = (JSONObject) array.get(i);
					gradeInfo = new StudentGradeInfo();
					gradeInfo.setID(object.getInt("iD"));
					gradeInfo.setXH(XH);
					gradeInfo.setCourseNameCN(object.getString("courseNameCN"));
					gradeInfo.setTerm(object.getString("term"));
					gradeInfo.setTotalHour(object.getString("totalHour"));
					gradeInfo.setCredit(object.getString("credit"));
					gradeInfo.setGrade(object.getString("grade"));
					
					System.out.println(gradeInfo);
					studentGradeInfos.add(gradeInfo);
				}
				
				
				
				//��������ѧ�����пγ̳ɼ���Ϣ
				if (studentGradeInfos.size() >= 1) {
					if (studentGradeInfoManageService.updateStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��������ѧ�����пγ̳ɼ���Ϣ ʧ�ܣ�");
					}
				}

				//��ѯָ��ѧ�������пγ̳ɼ���Ϣ
				if (pFlag) {
					studentGradeInfos = new ArrayList<StudentGradeInfo>();
					if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ѯָ��ѧ�������пγ̳ɼ���Ϣ ʧ�ܣ�");
					}
				}
				getRequest().setAttribute("studentGradeInfos", studentGradeInfos);
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
		resultURL = "/DALY/certificateMake_grade.jsp";
		return SUCCESS;
	}
	
	/**
	 * �շѻ���
	 */
	private void balanceStat() {
		ErrInfo pErrInfo = new ErrInfo();
		boolean pflag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesCertificateRegister> pArchivesCertificateRegisters = new ArrayList<ArchivesCertificateRegister>();
		dataPageInfo.setPageSize(10);
		if (pflag) {
			if(archivesCertificateRegisterManageService.findArchivesCertificateRegistersByCondition(dateQuerycondition, pArchivesCertificateRegisters, pErrInfo) == false){
				pflag = false;
				pErrInfo.getContent().insert(0, "���ҷ��ϲ�ѯ�����ĵ�����֤�Ǽ���Ϣ����ʧ�ܣ�");
			}
		}
		request.setAttribute("dateQuerycondition", dateQuerycondition);
		request.setAttribute("archivesCertificateRegisters", pArchivesCertificateRegisters);
	}
	
	/**
	 * �շѻ���
	 */
	public String balanceStatDetail() {
		ErrInfo pErrInfo = new ErrInfo();
		boolean pflag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ArchivesCertificateRegister> pArchivesCertificateRegisters = new ArrayList<ArchivesCertificateRegister>();
		dataPageInfo.setPageSize(10);
		int pManagerUserID = Integer.parseInt(request.getParameter("ManagerUserID"));
		if (pflag) {
			if(archivesCertificateRegisterManageService.findArchivesCertificateRegistersByCondition(pManagerUserID, dateQuerycondition, dataPageInfo, pArchivesCertificateRegisters, pErrInfo) == false){
				resultURL ="/error.jsp";
				pflag = false;
				pErrInfo.getContent().insert(0, "���ҷ��ϲ�ѯ�����������˵ĵ�����֤�Ǽ���Ϣ����ʧ�ܣ�");
			}
		}
		request.setAttribute("ManagerUserID", pManagerUserID);
		request.setAttribute("dateQuerycondition", dateQuerycondition);
		resultURL = "/DALY/archivesCertificateRegister.jsp";
		request.setAttribute("archivesCertificateRegisters", pArchivesCertificateRegisters);
		return SUCCESS;
	}
	
	/**
	 * ��ȡ�շ���ϸ��Ϣ
	 * @return
	 */
	public String findChargeCollectDetail() {
		ErrInfo pErrInfo = new ErrInfo();
		boolean pflag = true;
		HttpServletRequest request = ServletActionContext.getRequest();
		int pCertificateRegID = 0;
		ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo();
		if (pflag) {
			 pCertificateRegID = Integer.parseInt(request.getParameter("CertificateRegID"));
			if (pCertificateRegID <= 0) {
				pflag = false;
				pErrInfo.getContent().append("��֤�ǼǱ�ŷǷ�Ϊ0��");
				resultURL ="/error.jsp";
			}
		}
		
		if (pflag) {
			if (archivesCertificateInfoManageService.findArchivesCertificateInfoByCertificateRegID(pCertificateRegID, archivesCertificateInfo, pErrInfo)==false) {
				pflag = false;
				pErrInfo.getContent().insert(0,"��֤�ǼǱ�ŷǷ�Ϊ0��");
				resultURL ="/error.jsp";
			}else{
				request.setAttribute("archivesCertificateInfo", archivesCertificateInfo);
				resultURL ="/DALY/chargeCollectDetail.jsp";
			}
			
		}
		return SUCCESS;
	}
	

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public String getCertificateTypesJSON() {
		return certificateTypesJSON;
	}

	public void setCertificateTypesJSON(String certificateTypesJSON) {
		this.certificateTypesJSON = certificateTypesJSON;
	}

	public List<CertificateType> getCertificateTypes() {
		return certificateTypes;
	}

	public void setCertificateTypes(List<CertificateType> certificateTypes) {
		this.certificateTypes = certificateTypes;
	}
	
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public double getShouldCharge() {
		return shouldCharge;
	}

	public void setShouldCharge(double shouldCharge) {
		this.shouldCharge = shouldCharge;
	}

	public double getRealCharge() {
		return realCharge;
	}

	public void setRealCharge(double realCharge) {
		this.realCharge = realCharge;
	}

	public String getInvoiceSN() {
		return invoiceSN;
	}

	public void setInvoiceSN(String invoiceSN) {
		this.invoiceSN = invoiceSN;
	}

	public String getRemark() {
		return remark;
	}

	public String getCertificateInfoArrayJSON() {
		return certificateInfoArrayJSON;
	}

	public void setCertificateInfoArrayJSON(String certificateInfoArrayJSON) {
		this.certificateInfoArrayJSON = certificateInfoArrayJSON;
	}

	public int getCertificateRegID() {
		return certificateRegID;
	}

	public void setCertificateRegID(int certificateRegID) {
		this.certificateRegID = certificateRegID;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPersonName() {
		return personName;
	}

	public String getXH() {
		return XH;
	}

	public void setXH(String xH) {
		XH = xH;
	}

	public CertificateStudent getCertificateStudent() {
		return certificateStudent;
	}

	public List<Major> getMajors() {
		return majors;
	}

	public void setMajors(List<Major> majors) {
		this.majors = majors;
	}

	public List<College> getColleges() {
		return colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	public void setCertificateStudent(CertificateStudent certificateStudent) {
		this.certificateStudent = certificateStudent;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCertificateTypeId() {
		return certificateTypeId;
	}

	public void setCertificateTypeId(String certificateTypeId) {
		this.certificateTypeId = certificateTypeId;
	}

	public String getCertificateFileName() {
		return certificateFileName;
	}

	public void setCertificateFileName(String certificateFileName) {
		this.certificateFileName = certificateFileName;
	}

	public boolean isDealedFlag() {
		return dealedFlag;
	}

	public void setDealedFlag(boolean dealedFlag) {
		this.dealedFlag = dealedFlag;
	}

	/**
	 * ��鵵����֤��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (archivesCertificateInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("������֤��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			if (certificateStudentManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("ѧ����Ϣ��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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

	public List<ArchivesCertificateRegister> getArchivesCertificateRegisters() {
		return archivesCertificateRegisters;
	}

	public void setArchivesCertificateRegisters(
			List<ArchivesCertificateRegister> archivesCertificateRegisters) {
		this.archivesCertificateRegisters = archivesCertificateRegisters;
	}

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String getGradeArrayJSON() {
		return gradeArrayJSON;
	}

	public void setGradeArrayJSON(String gradeArrayJSON) {
		this.gradeArrayJSON = gradeArrayJSON;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
	}

	public int getCertificateInfoID() {
		return certificateInfoID;
	}

	public void setCertificateInfoID(int certificateInfoID) {
		this.certificateInfoID = certificateInfoID;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public String getDownloadFileName() {
		return downloadFileName;
	}

	public void setDownloadFileName(String downloadFileName) {
		this.downloadFileName = downloadFileName;
	}

	public String getDownloadContentType() {
		return downloadContentType;
	}

	public void setDownloadContentType(String downloadContentType) {
		this.downloadContentType = downloadContentType;
	}

	public File getDownloadFile() {
		return downloadFile;
	}

	public String getUpdateStudentFlag() {
		return updateStudentFlag;
	}

	public void setUpdateStudentFlag(String updateStudentFlag) {
		this.updateStudentFlag = updateStudentFlag;
	}

	public void setDownloadFile(File downloadFile) {
		this.downloadFile = downloadFile;
	}
}