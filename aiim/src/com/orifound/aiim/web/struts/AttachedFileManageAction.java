package com.orifound.aiim.web.struts;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.FileRemoteManageService;
import com.orifound.aiim.bll.service.IAttachedFileManageService;
import com.orifound.aiim.entity.ArchivesInfoAttachedFile;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.HandleResult;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.web.util.WebCommonUtil;



/**
 * ԭ�Ĺ���Action
 *
 */
public class AttachedFileManageAction extends BaseAction {

	private static final long serialVersionUID = 7856658886978717104L;
	
	static Log logger = LogFactory.getLog(AttachedFileManageAction.class);

	public static FileRemoteManageService fileRemoteManageService;
	
	@Autowired
	private IAttachedFileManageService attachedFileManageService;
	
	private ArchivesInfoAttachedFile archivesInfoAttachedFile = new ArchivesInfoAttachedFile();

	public ArchivesInfoAttachedFile getArchivesInfoAttachedFile() {
		return archivesInfoAttachedFile;
	}

	public void setArchivesInfoAttachedFile(ArchivesInfoAttachedFile archivesInfoAttachedFile) {
		this.archivesInfoAttachedFile = archivesInfoAttachedFile;
	}


	private String resultURL;
	
	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}

    /**
     * �ϴ�ԭ��
     * @return
     * @throws Exception
     */
    public String saveArchivesInfoAttachedFile() throws Exception { 
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesInfoAttachedFile.getArchivesTypeID());	
			if (pFlag) {
				archivesInfoAttachedFile.setAttachedTime(new Date());
            	if(attachedFileManageService.addArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false){
            		pFlag = false;
            		pErrInfo.getContent().insert(0, "����ʧ��:");
            	}
			}
			
			if (pFlag) {
				print("�ϴ��ɹ���");
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
     * �������е�ԭ��
     * @return
     * @throws Exception
     */
    public List<ArchivesInfoAttachedFile> findAllAttachedFile(int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------����ԭ����Ϣ------------------------");
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("archivesTypeID: "+archivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles = null;
		ArchivesType archivesType = null;
		try {
			archivesInfoAttachedFiles = new ArrayList<ArchivesInfoAttachedFile>();
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, NBXH, archivesInfoAttachedFiles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ҵ�����Ϣʧ��");
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
				
				logger.error(pErrInfo.toString());
				throw new Exception(pErrInfo.toShortString());
			}
			//���پֲ�����
			throwable = null;
		}
		return archivesInfoAttachedFiles;
    }
    
    /**
     * ����NBXHɾ��ԭ��
     * @return
     * @throws Exception
     */
    public String delAttachedFileByNBXHDWR(int fileID, int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------ɾ��ԭ����Ϣ------------------------");
    	System.out.println("fileID: "+fileID);
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("archivesTypeID: "+archivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		String filePath = "";
		try {
			pErrPos = 1;
	    	
	    	ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
	    	ArchivesInfoAttachedFile archivesInfoAttachedFile = new ArchivesInfoAttachedFile();
	    	
	    	archivesInfoAttachedFile.setID(fileID);
	    	
	    	if (pFlag) {
				if (attachedFileManageService.findArchivesInfoAttachedFileById(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ԭ����Ϣʧ��: ");
				}
			}
	    	
	    	if (pFlag) {
				if (attachedFileManageService.deleteArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile.getID(), pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��ԭ����Ϣʧ��: ");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				filePath = WebCommonUtil.getAttachedFilePathOnServer(archivesInfoAttachedFile, SystemInitializer.getInstance().getAttachedFileTempSavePaths(), archivesType);
				Service srvcModel = new ObjectServiceFactory().create(FileRemoteManageService.class);
				XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
				
				ResourceBundle rb = ResourceBundle.getBundle("aiim");
				String serviceURL = rb.getString("FileRemoteWebServiceURL");
				FileRemoteManageService fileRemoteManageService =  (FileRemoteManageService)factory.create(srvcModel,serviceURL);
				HandleResult handleResult = fileRemoteManageService.deleteFile(filePath);
				if (handleResult.getSuccess() == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��ԭ���ļ�ʧ��: ");
				} 
				System.out.println("success");
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
				
				logger.error(pErrInfo.toString());
				throw new Exception(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return "ɾ���ɹ���";
    }
    
    /**
     * ����NBXH����ԭ��
     * @return
     * @throws Exception
     */
    public String downLoadAttachedFileByNBXHDWR(int fileID, int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------����ԭ����Ϣ------------------------");
    	System.out.println("fileID: "+fileID);
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("archivesTypeID: "+archivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		String filePath = "";
		try {
			pErrPos = 1;
	    	
	    	ArchivesType archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesTypeID);
	    	ArchivesInfoAttachedFile archivesInfoAttachedFile = new ArchivesInfoAttachedFile();
	    	
	    	archivesInfoAttachedFile.setID(fileID);
	    	
	    	if (pFlag) {
				if (attachedFileManageService.findArchivesInfoAttachedFileById(archivesType, archivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ԭ����Ϣʧ��: ");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				filePath = WebCommonUtil.getAttachedFilePathOnServer(archivesInfoAttachedFile, SystemInitializer.getInstance().getAttachedFileTempSavePaths(), archivesType);
				Service srvcModel = new ObjectServiceFactory().create(FileRemoteManageService.class);
				XFireProxyFactory factory = new XFireProxyFactory(XFireFactory.newInstance().getXFire());
				
				ResourceBundle rb = ResourceBundle.getBundle("aiim");
				String serviceURL = rb.getString("FileRemoteWebServiceURL");
				fileRemoteManageService =  (FileRemoteManageService)factory.create(srvcModel,serviceURL);
				HandleResult handleResult = fileRemoteManageService.decryptFile(filePath);
				if (handleResult.getSuccess() == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ԭ���ļ�ʧ��: ");
				}else{
					return handleResult.getDecryptFileAddress();
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
				
				logger.error(pErrInfo.toString());
				throw new Exception(pErrInfo.toString());
			}
			//���پֲ�����
			throwable = null;
		}
		return null;
    }
    
    /**
     * ����ļ��ϴ�·��
     * @return
     * @throws Exception
     */
    public String getSavedPath() throws Exception{	
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		ArchivesType archivesType = null;
		String savedPath = "";
		try {
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(getParameterForInt("archivesTypeID"));
		    savedPath = WebCommonUtil.getAttachedFileSavePath(SystemInitializer.getInstance().getAttachedFileTempSavePaths(), archivesType);
		    if (pFlag) {
		    	print(savedPath);
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
			}
			//���پֲ�����
			throwable = null;
		}
		return null;   
    }
    
    /**
     * ��ȡ�ļ�������http��ַ
     * @param session
     * @return
     * @throws Exception
     */
    public String getServiceURLDWR(HttpSession session)throws Exception {
    	ResourceBundle rb = ResourceBundle.getBundle("aiim");
    	return rb.getString("FileRemoteWebServiceURL");
    }
}
