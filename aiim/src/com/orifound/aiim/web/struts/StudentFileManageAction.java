package com.orifound.aiim.web.struts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IStudentFileManageService;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumImportType;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.MoveOutInfo;
import com.orifound.aiim.entity.StudentInfo;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.WebCommonUtil;

public class StudentFileManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(StudentFileManageAction.class);
	
	@Autowired
	private IStudentFileManageService studentFileManageService;
	
	private DataPageInfo dataPageInfo = new DataPageInfo();

	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}

	/**
     * ��File��������װ�ϴ��ļ�����󣬽��ձ��ļ���
     */
    private File excel;   
  
    /**
     * ��String��������װ�ϴ��ļ���
     */
    private String excelFileName;   

    /**
     * ��String��������װ�ϴ��ļ�����  
     */
    private String excelContentType;   
	
	/**
	 * ���ص�ҳ���ַ
	 */
	private String resultURL;
	
	public File getExcel() {
		return excel;
	}

	public void setExcel(File excel) {
		this.excel = excel;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public String getExcelContentType() {
		return excelContentType;
	}

	public void setExcelContentType(String excelContentType) {
		this.excelContentType = excelContentType;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	//����ѧ���Ǽ���Ա��
	public String getStudentRigisterTree()throws Exception{
		ErrInfo pErrInfo = new ErrInfo();
		
		List<String> collegeNames = new ArrayList<String>();
		
		if(studentFileManageService.findCollege(collegeNames,pErrInfo) == false){
			System.out.println(pErrInfo.toString());
		}
		
		String tree = CreateTreeUtil.getCollegeTree(collegeNames);
		setAttribute("tree", tree);
		setAttribute("proceseAction", "studentFileManageAction_findStudentRigister.action");
		
		resultURL = "/personalFileManage/left.jsp";

		return SUCCESS;//��ת����ߣ���ʾת����Ա������
	}
	
	/**
	 * �õ�������ѧ�ǼǷ�����
	 * @return
	 * @throws Exception
	 */
	public String getStudentTypeTreeForOutRigister()throws Exception{
		UserInfo userInfo = getSessionAttr("userInfo",UserInfo.class);
		
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(WebCommonUtil.getStudentTypes(archivesTypes));
		setAttribute("tree", tree);
		setAttribute("proceseAction", "studentFileManageAction_findMoveOutInfo.action?moveOutWay="+getParameterForInt("moveOutWay"));
		resultURL = "/personalFileManage/studentTypeTree.jsp";

		return SUCCESS;//��ת����ߣ���ʾת����Ա������
	}
	
	/**
	 * ���ҵǼ���ɵ�ѧ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findStudentRigister() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = this.getRequest();
		List<StudentInfo> studentInfos = null;
		try {
			//��ȡ����ֵ
			pErrPos = 1;
			String xyName = java.net.URLDecoder.decode(request.getParameter("xyName"),"UTF-8");

			System.out.println(xyName);
			dataPageInfo.setPageSize(15);
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				studentInfos = new ArrayList<StudentInfo>();
				if(studentFileManageService.find(xyName,EnumWorkFlowStatus.���µ���������¼���,dataPageInfo,studentInfos,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯѧ������ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			request.setAttribute("studentInfos",studentInfos);
			request.setAttribute("xyName",xyName);
			resultURL = "/personalFileManage/addDocToFile.jsp";
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
	 * ��������ѧ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String importStudentInfo() throws Exception{
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = this.getRequest();	
		int importType = Integer.parseInt(request.getParameter("importType"));
		System.out.println("importType: "+importType);
		ArchivesType archivesType = null;
		try {
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (this.getExcel() == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}
			}
			
			IntegerEx sum = null;
			if (pFlag) {
				archivesType = WebCommonUtil.getPersonTypeByImportType(EnumImportType.getEnumElement(importType));
	        	Excel excel = new Excel(this.getExcel());
	        	sum = new IntegerEx();
	        	if (studentFileManageService.importStudentInfo(excel,importType,archivesType,sum,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ʧ��: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				request.setAttribute("message", "<script>alert('����������" + sum.getValue() + "����')</script>");
			}
			resultURL = "/personalFileManage/studentRigister.jsp";
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
	 * ����ѧ�����������ļ�
	 * @return
	 * @throws Exception
	 */
	public String finDoc() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		StudentInfo studentInfo = null;
		try {
			//���ղ���
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");

			//����ҵ���߼���ȡ�����ļ�
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���Ҿ����ļ�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			this.getRequest().setAttribute("studentInfo", studentInfo);
			resultURL = "/personalFileManage/dlg_AddDoc.jsp";
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
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ����ѧ�����������ļ�
	 * @return
	 * @throws Exception
	 */
	public String addDocs() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<Integer> ids = new ArrayList<Integer>();
		try {
			//���ղ���
			pErrPos = 1;
			String [] studentDocIds = this.getParameterValues("studentDocs");
			int nbxh = getParameterForInt("nbxh");

			if (studentDocIds == null || studentDocIds.length == 0) {
				pFlag = false;
				pErrInfo.getContent().append("��ѡ�����ϣ� ");
			}
			
			if (pFlag) {
				pErrPos = 2;
				for (int i = 0; i < studentDocIds.length; i++) {
					ids.add(Integer.parseInt(studentDocIds[i]));
				}
			}
			
			//����ҵ���߼���ȡ�����ļ�
			if (pFlag) {
				pErrPos = 3;
				
				if (studentFileManageService.addDocs(nbxh,ids,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��Ӿ����ļ�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.print("��ӳɹ���");
			}
			//resultURL = "/personalFileManage/dlg_AddDoc.jsp";
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
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return null;
	}
	
	/**
	 * ת���Ǽ�
	 * @return
	 * @throws Exception
	 */
	public String moveOutRegister() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//��ʼ����2...
			int importType = getParameterForInt("importType");
			UserInfo userInfo = getSessionAttr("userInfo", UserInfo.class);
			System.out.println("importType: "+importType);
			ArchivesType archivesType = WebCommonUtil.getPersonTypeByImportType(EnumImportType.getEnumElement(importType));
			
			if (pFlag) {
				pErrPos = 2;
				if (this.getExcel() == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}
			}
			
			IntegerEx sum = null;
			if (pFlag) {
	        	Excel excel = new Excel(this.getExcel());
	        	sum = new IntegerEx();
	        	if (studentFileManageService.moveOutRegister(userInfo.getUserID(),excel,importType,archivesType,sum,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�Ǽ�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {;
				print("����������" + sum.getValue() + "����");
			}
			//resultURL = "/personalFileManage/outAddrRigister.jsp";
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
					print(pErrInfo.toString());
				}else{
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
	 * ����ת������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findMoveOutInfo()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<MoveOutInfo> moveOutInfos = null;
		
		int archivesTypeID = getParameterForInt("archivesTypeID");
		ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
		int minNum = 0;
		int maxNum = 0;
		try {
			//��ò���
			pErrPos = 1;
			int moveOutWay = getParameterForInt("moveOutWay");

			switch (moveOutWay) {
			case 0:
				resultURL = "/personalFileManage/outAddrRigister.jsp";
				break;
			case 1:
				resultURL = "/personalFileManage/emsOutRigister.jsp";
				break;
			case 2:
				resultURL = "/personalFileManage/jyOutRigister.jsp";
				minNum = getParameterForInt("minNum");
				maxNum = getParameterForInt("maxNum");
				setAttribute("minNum", minNum);
				setAttribute("maxNum", maxNum);
				break;
			default:
				resultURL = "/error.jsp";
				break;
			}
			
			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				moveOutInfos = new ArrayList<MoveOutInfo>();
				boolean moveOutFlag = false;//�Ƿ�ת����־
				dataPageInfo.setPageSize(13);
				if (studentFileManageService.findMoveOutInfo(moveOutWay,moveOutFlag,archivesType,dataPageInfo, minNum , maxNum, moveOutInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯת����Ϣʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.setAttribute("moveOutInfos", moveOutInfos);
				setAttribute("archivesTypeID", archivesTypeID);
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ����ID����ת������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findMoveOutInfoById()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		MoveOutInfo moveOutInfo = null;
		try {
			//��ò���
			pErrPos = 1;
			int id = getParameterForInt("id");

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				moveOutInfo = new MoveOutInfo();
				if (studentFileManageService.findMoveOutInfoById(id,moveOutInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯת����Ϣʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("moveOutInfo", moveOutInfo);
			}
			resultURL = "/personalFileManage/outDetailInfo.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ����ת������ת����ʽ
	 * @return
	 * @throws Exception
	 */
	public String updateMoveOutWay () throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//��ò���
			pErrPos = 1;
			int id = getParameterForInt("id");
			int moveOutWay = getParameterForInt("moveOutWay");

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.updateMoveOutWay(id,moveOutWay,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯת����Ϣʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				print("���³ɹ���");
			}
			resultURL = "/personalFileManage/outDetailInfo.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ��ת�����������к�
	 * @return
	 * @throws Exception
	 */
	public String updateSN () throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//��ò���
			pErrPos = 1;
			int id = getParameterForInt("id");
			String SN = getParameterForString("SN");

			//����ҵ���߼�
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.updateSN(id,SN,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ת�������к�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				print("���³ɹ���");
			}
			//resultURL = "/personalFileManage/outDetailInfo.jsp";
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
					print(pErrInfo.toString());
				}else{
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
	 * �����Ǽǲ�ѯ
	 * @return
	 * @throws Exception
	 */
	public String delayOutQuery () throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<StudentInfo> studentInfos = null;
		try {
			//��ò���
			pErrPos = 1;
			String studentId = getParameterForString("studentId");
			String studentName = getParameterForString("studentName");

			//����ҵ���߼���ѯѧ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				
				List<EnumWorkFlowStatus> enumWorkFlowStatus = new ArrayList<EnumWorkFlowStatus>();
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������ļ���¼���);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������Ǽ����);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ���ת���Ǽ����);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������ļ���¼���);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ�����ֹ�ʼ�);
				
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStudentId(studentId);
				studentInfo.setStudentName(studentName);
				
				studentInfos = new ArrayList<StudentInfo>();
				if (studentFileManageService.findStudentInfos(studentInfo,enumWorkFlowStatus,studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯѧ��������Ϣʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("studentInfos", studentInfos);
				setAttribute("studentId", studentId);
				setAttribute("studentName", studentName);
			}
			resultURL = "/personalFileManage/delayOutRigister.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ��ѯѧ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findStudentInfos() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<StudentInfo> studentInfos = null;
		try {
			//��ò���
			pErrPos = 1;
			String studentId = getParameterForString("studentId");
			String studentName = getParameterForString("studentName");
			String SN = getParameterForString("SN");

			//����ҵ���߼���ѯѧ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				
				List<EnumWorkFlowStatus> enumWorkFlowStatus = new ArrayList<EnumWorkFlowStatus>();
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ���������¼���);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������ļ���¼���);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������Ǽ����);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ���ת���Ǽ����);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ��������ļ���¼���);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.���µ�����ֹ�ʼ�);
				
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStudentId(studentId);
				studentInfo.setStudentName(studentName);
				studentInfo.setSN(SN);
				
				studentInfos = new ArrayList<StudentInfo>();
				if (studentFileManageService.findStudentInfos(studentInfo,enumWorkFlowStatus,studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯѧ��������Ϣʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("studentInfos", studentInfos);
				setAttribute("studentId", studentId);
				setAttribute("studentName", studentName);
				setAttribute("SN", SN);
			}
			resultURL = "/personalFileManage/outQuery.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ����ѧ������������״̬
	 * @return
	 * @throws Exception
	 */
	public String updateState() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<StudentInfo> studentInfos = null;
		try {
			//��ò���
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			int state = getParameterForInt("state");

			//����ҵ���߼���ѯѧ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.findStudentInfos(nbxh,EnumWorkFlowStatus.getEnumElement(state),pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ���������״̬ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("studentInfos", studentInfos);
			}
			resultURL = "/personalFileManage/studentList.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ��ӡѧ�����������ļ���Ϣ
	 * @return
	 * @throws Exception
	 */
	public String printStudent() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		StudentInfo studentInfo = null;
		try {
			//���ղ���
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");

			//����ҵ���߼���ȡ�����ļ�
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���Ҿ����ļ�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			//����ҵ���߼���ȡ�����ļ�
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���Ҿ����ļ�ʧ��: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			this.getRequest().setAttribute("studentInfo", studentInfo);
			resultURL = "/personalFileManage/print_student.jsp";
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
