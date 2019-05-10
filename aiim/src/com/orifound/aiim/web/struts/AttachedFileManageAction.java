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
 * 原文管理Action
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
     * 上传原文
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
			//开始处理 1...
			pErrPos = 1;
			archivesType = SystemInitializer.getInstance().getPlaneArchivesTypes().get(archivesInfoAttachedFile.getArchivesTypeID());	
			if (pFlag) {
				archivesInfoAttachedFile.setAttachedTime(new Date());
            	if(attachedFileManageService.addArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile, pErrInfo) == false){
            		pFlag = false;
            		pErrInfo.getContent().insert(0, "保存失败:");
            	}
			}
			
			if (pFlag) {
				print("上传成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//销毁局部变量
			throwable = null;
		}
		return null;   
    }
	

    /**
     * 查找所有的原文
     * @return
     * @throws Exception
     */
    public List<ArchivesInfoAttachedFile> findAllAttachedFile(int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------查找原文信息------------------------");
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

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (attachedFileManageService.findArchivesInfoAttachedFiles(archivesType, NBXH, archivesInfoAttachedFiles, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找档案信息失败");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//销毁局部变量
			throwable = null;
		}
		return archivesInfoAttachedFiles;
    }
    
    /**
     * 根据NBXH删除原文
     * @return
     * @throws Exception
     */
    public String delAttachedFileByNBXHDWR(int fileID, int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------删除原文信息------------------------");
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
					pErrInfo.getContent().insert(0, "查找原文信息失败: ");
				}
			}
	    	
	    	if (pFlag) {
				if (attachedFileManageService.deleteArchivesInfoAttachedFile(archivesType, archivesInfoAttachedFile.getID(), pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除原文信息失败: ");
				}
			}

			//开始处理2...
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
					pErrInfo.getContent().insert(0, "删除原文文件失败: ");
				} 
				System.out.println("success");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//销毁局部变量
			throwable = null;
		}
		return "删除成功！";
    }
    
    /**
     * 根据NBXH下载原文
     * @return
     * @throws Exception
     */
    public String downLoadAttachedFileByNBXHDWR(int fileID, int NBXH , int archivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------下载原文信息------------------------");
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
					pErrInfo.getContent().insert(0, "查找原文信息失败: ");
				}
			}

			//开始处理2...
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
					pErrInfo.getContent().insert(0, "解密原文文件失败: ");
				}else{
					return handleResult.getDecryptFileAddress();
				} 
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//销毁局部变量
			throwable = null;
		}
		return null;
    }
    
    /**
     * 获得文件上传路径
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
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");
				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
			//销毁局部变量
			throwable = null;
		}
		return null;   
    }
    
    /**
     * 获取文件服务器http地址
     * @param session
     * @return
     * @throws Exception
     */
    public String getServiceURLDWR(HttpSession session)throws Exception {
    	ResourceBundle rb = ResourceBundle.getBundle("aiim");
    	return rb.getString("FileRemoteWebServiceURL");
    }
}
