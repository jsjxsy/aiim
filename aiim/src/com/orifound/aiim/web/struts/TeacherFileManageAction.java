package com.orifound.aiim.web.struts;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ITeacherFileManagService;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumImportType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.TeacherDocsInfo;
import com.orifound.aiim.entity.TeacherDocsType;
import com.orifound.aiim.entity.TeacherInfo;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;
import com.orifound.aiim.web.util.WebCommonUtil;

public class TeacherFileManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	static Log logger = LogFactory.getLog(TeacherFileManageAction.class);
	
	@Autowired
	private ITeacherFileManagService teacherFileManagService;
	
	private String resultURL;
	
	public String getResultURL() {
		return resultURL;
	}
	
	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

	private TeacherDocsInfo teacherDocsInfo = new TeacherDocsInfo();
	
	public TeacherDocsInfo getTeacherDocsInfo() {
		return teacherDocsInfo;
	}

	public void setTeacherDocsInfo(TeacherDocsInfo teacherDocsInfo) {
		this.teacherDocsInfo = teacherDocsInfo;
	}

	private TeacherInfo teacherInfo = new TeacherInfo();
	
	public TeacherInfo getTeacherInfo() {
		return teacherInfo;
	}

	public void setTeacherInfo(TeacherInfo teacherInfo) {
		this.teacherInfo = teacherInfo;
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

	private int [] docIds;

	public int[] getDocIds() {
		return docIds;
	}

	public void setDocIds(int[] docIds) {
		this.docIds = docIds;
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

	/**
	 * �õ���ְ����¼������
	 * @return
	 * @throws Exception
	 */
	public String getTeacherTypeTree()throws Exception{
		UserInfo userInfo = getSessionAttr("userInfo",UserInfo.class);
		
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(WebCommonUtil.getTeacherTypes(archivesTypes));
		setAttribute("tree", tree);
		setAttribute("proceseAction", "teacherFileManageAction_findTeacherInfo.action");
		resultURL = "/personalFileManage/teacherTypeTree.jsp";

		return SUCCESS;//��ת����ߣ���ʾת����Ա������
	}
	
	/**
	 * �����ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String importTeacherInfo()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		HttpServletRequest request = this.getRequest();	
		int importType = getParameterForInt("importType");
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
	        	if (teacherFileManagService.importTeacherInfo(excel,importType,archivesType,sum,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ʧ��: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				request.setAttribute("message", "<script>alert('����������" + sum.getValue() + "����')</script>");
			}
			resultURL = "/personalFileManage/teacherRigister.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					request.setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;   
	}
	
	/**
	 * ��ȡ��ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTeacherInfo()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<TeacherInfo> teacherInfos = null;
		ArchivesType archivesType = null;
		TeacherInfo teacherInfo = null;
		try {
			pErrPos = 1;
			int archivesTypeID = getParameterForInt("archivesTypeID");
			String xm = getParameterForString("xm");
			String gzh = getParameterForString("gzh");
			
			if (pFlag) {
				archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
				
				dataPageInfo.setPageSize(12);
				teacherInfo = new TeacherInfo();
				teacherInfo.setXm(xm);
				teacherInfo.setGzh(gzh);
				
				teacherInfos = new ArrayList<TeacherInfo>();
	        	if (teacherFileManagService.findTeacherInfos(teacherInfo,archivesType,dataPageInfo,teacherInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				setAttribute("archivesTypeID",archivesTypeID);
				setAttribute("xm", xm);
				setAttribute("gzh", gzh);
				setAttribute("teacherInfos",teacherInfos);
			}
			
			resultURL = "/personalFileManage/teacherInfoManage.jsp";
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
					resultURL = "/error.jsp";
					setAttribute("pErrInfo", pErrInfo);
				}else{
					setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;   
	}
	
	/**
	 * �õ���ְ����¼������
	 * @return
	 * @throws Exception
	 */
	public String getTeacherTypeTreeForOutRigister()throws Exception{
		UserInfo userInfo = getSessionAttr("userInfo",UserInfo.class);
		
		List<ArchivesType> archivesTypes = new ArrayList<ArchivesType>(userInfo.getArchivesTypes().values());

		String tree = CreateTreeUtil.getArchivesTypeTree(WebCommonUtil.getTeacherTypes(archivesTypes));
		setAttribute("tree", tree);
		setAttribute("proceseAction", "/aiim/personalFileManage/teacherOutRigister.jsp");
		resultURL = "/personalFileManage/teacherTypeTree.jsp";

		return SUCCESS;//��ת����ߣ���ʾת����Ա������
	}
	
	/**
	 * ����������ȡ��ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTeacherInfoCondition()throws Exception{
		System.out.println(getParameterForInt("archivesTypeID"));
		resultURL = "/personalFileManage/teacherInfoManage.jsp";
		return SUCCESS;
	}
	
	/**
	 * ���ҽ�ְ���������ڲ���
	 * @param nbxh
	 * @param session
	 * @return
	 * @throws Exception
	 */
	public List<TeacherDocsInfo> findChildByNbxhDWR(int nbxh,HttpSession session)throws Exception{	
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<TeacherDocsInfo> teacherDocsInfos = new ArrayList<TeacherDocsInfo>();
		try {
			pErrPos = 1;
			
			if (pFlag) {
	        	if (teacherFileManagService.findTeacherDocsInfoByNBXH(nbxh,teacherDocsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
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

				logger.error(pErrInfo.toString());
				if (pErrInfo.getException() != null) {
					throw new Exception(pErrInfo.toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return teacherDocsInfos;
	}
	
	/**
	 * �����ڲ���Ų��ҽ�ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTeacherInfoByNBXHToAddFile()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		TeacherInfo teacherInfo = null;
		List<TeacherDocsType> teacherDocsTypes = null;
		try {
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			
			
			teacherDocsTypes = new ArrayList<TeacherDocsType>();
			if (teacherFileManagService.findTeacherDocsType(teacherDocsTypes,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ��ְ���������Ϸ���ʧ�� : ");
			}
			
			if (pFlag) {
				teacherInfo = new TeacherInfo();
				teacherInfo.setNBXH(nbxh);
	        	if (teacherFileManagService.findTeacherInfoByNBXH(teacherInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				setAttribute("teacherInfo",teacherInfo);
				setAttribute("teacherDocsTypes",teacherDocsTypes);
			}
			
			resultURL = "/personalFileManage/item_teacherDocAdd.jsp";
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
					resultURL = "/error.jsp";
					setAttribute("pErrInfo", pErrInfo);
				}else{
					setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;   
	}
	
	/**
	 * �����ڲ���Ų��ҽ�ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTeacherInfoByNBXH()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		TeacherInfo teacherInfo = null;
//		List<TeacherDocsType> teacherDocsTypes = null;
		try {
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			
			
//			teacherDocsTypes = new ArrayList<TeacherDocsType>();
//			if (teacherFileManagService.findTeacherDocsType(teacherDocsTypes,pErrInfo) == false) {
//				pFlag = false;
//				pErrInfo.getContent().insert(0, "��ѯ��ְ���������Ϸ���ʧ�� : ");
//			}
			
			if (pFlag) {
				teacherInfo = new TeacherInfo();
				teacherInfo.setNBXH(nbxh);
	        	if (teacherFileManagService.findTeacherInfoByNBXH(teacherInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				setAttribute("teacherInfo",teacherInfo);
				//setAttribute("teacherDocsTypes",teacherDocsTypes);
			}
			
			resultURL = "/personalFileManage/item_teacherEdit.jsp";
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
					resultURL = "/error.jsp";
					setAttribute("pErrInfo", pErrInfo);
				}else{
					setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;   
	}
	
	/**
	 * ����ְ��������Ӿ��ڲ���
	 * @return
	 * @throws Exception
	 */
	public String addDoc()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");

			
			if (pFlag) {
	        	if (teacherFileManagService.addDoc(nbxh,teacherDocsInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӽ�ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("��ӳɹ���");
			}
			//resultURL = "/personalFileManage/item_teacher.jsp";
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
	 * ɾ��ָ����ְ���������ڲ���
	 * @return
	 * @throws Exception
	 */
	public String delDoc()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			if (pFlag) {
	        	if (teacherFileManagService.delDoc(docIds,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ����ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("ɾ���ɹ���");
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
	 * ɾ��ָ����ְ���������ڲ���
	 * @return
	 * @throws Exception
	 */
	public String addTeacherInfo()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			if (pFlag) {
				teacherInfo.setSaveDate(new Date());
	        	if (teacherFileManagService.addTeacherInfo(teacherInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӽ�ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("��ӳɹ���");
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
	 * ���½�ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateTeacherInfo() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			if (pFlag) {
				teacherInfo.setSaveDate(new Date());
	        	if (teacherFileManagService.updateTeacherInfo(teacherInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���½�ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("��ӳɹ���");
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
	 * ����ID���ҽ�ְ��������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findTeacherDocByIdForEdit()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<TeacherDocsType> teacherDocsTypes = null;
		try {
			pErrPos = 1;
			
			teacherDocsTypes = new ArrayList<TeacherDocsType>();
			if (teacherFileManagService.findTeacherDocsType(teacherDocsTypes,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ��ְ���������Ϸ���ʧ�� : ");
			}
			
			if (pFlag) {
				teacherDocsInfo.setID(getParameterForInt("ID"));
	        	if (teacherFileManagService.findTeacherDocById(teacherDocsInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҽ�ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				resultURL = "/personalFileManage/item_teacherDocEdit.jsp";
				setAttribute("teacherInfo", teacherInfo);
				setAttribute("teacherDocsTypes", teacherDocsTypes);
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
					resultURL = "/error.jsp";
					setAttribute("pErrInfo", pErrInfo);
				}else{
					setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ���½�ְ�����ڲ�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String updateTeacherDocInfo()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			
			if (pFlag) {
	        	if (teacherFileManagService.updateTeacherDocInfo(teacherDocsInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���½�ְ���������ڲ���ʧ�� : ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("���³ɹ���");
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
	 * ���õ���ת��״̬
	 * @return
	 * @throws Exception
	 */
	public String setMoveOut()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		try {
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			if (pFlag) {
	        	if (teacherFileManagService.setMoveOut(nbxh,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���½�ְ������ת��״̬ʧ��: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				print("���³ɹ���");
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
	 * ��ý�ְ�����ڲ��Ϸ���
	 * @return
	 * @throws Exception
	 */
	public String getTeacherDocTypes()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		List<TeacherDocsType> teacherDocsTypes = null;
		try {
			pErrPos = 1;
			
			teacherDocsTypes = new ArrayList<TeacherDocsType>();
			if (teacherFileManagService.findTeacherDocsType(teacherDocsTypes,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ��ְ���������Ϸ���ʧ�� : ");
			}
			
			if (pFlag) {
				resultURL = "/personalFileManage/item_teacherDocBatAdd.jsp";
				setAttribute("teacherDocsTypes", teacherDocsTypes);
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
					resultURL = "/error.jsp";
					setAttribute("pErrInfo", pErrInfo);
				}else{
					setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
				logger.error(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	
	/**
	 * ��ӡ��ְ���������ڲ�����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String findChildByNbxhForPrint()throws Exception{	
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<TeacherDocsInfo> teacherDocsInfos = new ArrayList<TeacherDocsInfo>();
		List<TeacherDocsType> teacherDocsTypes = new ArrayList<TeacherDocsType>();
		try {
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			if (pFlag) {
	        	if (teacherFileManagService.findTeacherDocsInfoByNBXHForPrint(nbxh,teacherDocsInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
					resultURL = "/error.jsp";
				}else{
					getRequest().setAttribute("teacherDocsInfos", teacherDocsInfos);
				}  
	        	
	        	if (teacherFileManagService.findTeacherDocsType(teacherDocsTypes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ��ְ��������Ϣʧ�� : ");
					System.out.println(pErrInfo.toString());
					resultURL = "/error.jsp";
				}else{
					getRequest().setAttribute("teacherDocsTypes", teacherDocsTypes);
				} 
	        	resultURL = "/personalFileManage/print_teacher.jsp";
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
					throw new Exception(pErrInfo.toString());
				}else{
					throw new Exception(pErrInfo.toShortString());
				}
			}
			//���پֲ�����
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * ������Ӿ��ڲ���
	 * @return
	 * @throws Exception
	 */
	public String batAddDocs()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		String gzhStr = getParameterForString("gzhs");
		try {
			pErrPos = 1;
			gzhStr = gzhStr.replaceAll("\r", "");
			String [] args = gzhStr.split("\n");
			List<String>  gzhList =new ArrayList<String>();
			for (int i = 0; i < args.length; i++) {
				if (!"".equals(args[i].trim())) {
					gzhList.add(args[i]);
				}	
			}
			if (teacherFileManagService.batAddTeacherDocsInfo(gzhList,teacherDocsInfo,pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ѯ��ְ���������Ϸ���ʧ�� : ");
			}
			print("��ӳɹ���");
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
}
