package com.orifound.aiim.web.struts;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.xfire.XFireFactory;
import org.codehaus.xfire.client.XFireProxyFactory;
import org.codehaus.xfire.service.Service;
import org.codehaus.xfire.service.binding.ObjectServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.FileRemoteManageService;
import com.orifound.aiim.bll.service.IOfficialArchivesInfoAttachedFileManageService;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.HandleResult;
import com.orifound.aiim.entity.OfficialArchivesInfoAttachedFile;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.web.util.WebCommonUtil;

/**
 * 原文管理DWR控制类
 * 
 *
 */
public class OfficialArchivesAttachedFileManageAction extends BaseAction {

	private static final long serialVersionUID = 7856658886978717104L;
	
	static Log logger = LogFactory.getLog(OfficialArchivesAttachedFileManageAction.class);
	

	@Autowired
	private IOfficialArchivesInfoAttachedFileManageService officialArchivesInfoAttachedFileManageService;
	
	public static FileRemoteManageService fileRemoteManageService;
	
	
	private OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile = new OfficialArchivesInfoAttachedFile();



	public OfficialArchivesInfoAttachedFile getOfficialArchivesInfoAttachedFile() {
		return officialArchivesInfoAttachedFile;
	}

	public void setOfficialArchivesInfoAttachedFile(OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile) {
		this.officialArchivesInfoAttachedFile = officialArchivesInfoAttachedFile;
	}

	private OfficialArchivesType officialArchivesType = new OfficialArchivesType();
	
	public OfficialArchivesType getOfficialArchivesType() {
		return officialArchivesType;
	}

	public void setOfficialArchivesType(OfficialArchivesType officialArchivesType) {
		this.officialArchivesType = officialArchivesType;
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
    public String saveOfficialArchivesInfoAttachedFile() throws Exception { 
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		OfficialArchivesType  officialArchivesType2 = new OfficialArchivesType();
		
		try {
			//开始处理 1...
			pErrPos = 1;
			officialArchivesType2 = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesType.getID());	
			if (pFlag) {
				officialArchivesInfoAttachedFile.setAttachedTime(new Date());
            	if(officialArchivesInfoAttachedFileManageService.saveOfficialArchivesInfoAttachedFile(officialArchivesType2, officialArchivesInfoAttachedFile, pErrInfo) == false){
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
     * 文件下载
     * @return
     * @throws Exception
     */
    public String downLoadAttachedFileByNBXHDWR(int fileID, int NBXH , int officialArchivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------下载原文信息------------------------");
    	System.out.println("fileID: "+fileID);
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("officialArchivesTypeID: "+officialArchivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		String filePath = "";
		try {
			pErrPos = 1;
	    	
	    	OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
	    	OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile = new OfficialArchivesInfoAttachedFile();
	    	
	    	officialArchivesInfoAttachedFile.setID(fileID);
	    	
	    	if (pFlag) {
				if (officialArchivesInfoAttachedFileManageService.findOfficialArchivesInfoAttachedFileByID(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找原文信息失败: ");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				filePath = WebCommonUtil.getOfficialAttachedFilePathOnServer(officialArchivesInfoAttachedFile, SystemInitializer.getInstance().getAttachedFileTempSavePaths(), officialArchivesType);
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
     * 查找所有的原文
     * @return
     * @throws Exception
     */
    public List<OfficialArchivesInfoAttachedFile> findAllAttachedFile(int NBXH , int officialArchivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------查找原文信息------------------------");
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("officialArchivesTypeID: "+officialArchivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		List<OfficialArchivesInfoAttachedFile> officialArchivesInfoAttachedFiles = null;
		OfficialArchivesType officialArchivesType = null;
		try {
			officialArchivesInfoAttachedFiles = new ArrayList<OfficialArchivesInfoAttachedFile>();
			pErrPos = 1;
			officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (officialArchivesInfoAttachedFileManageService.findOfficialArchivesInfoAttachedFiles(officialArchivesType, NBXH, officialArchivesInfoAttachedFiles, pErrInfo) == false) {
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
		return officialArchivesInfoAttachedFiles;
    }
    
    /**
     * 根据NBXH删除原文
     * @return
     * @throws Exception
     */
    public String delAttachedFileByNBXHDWR(int fileID, int NBXH , int officialArchivesTypeID,HttpSession session) throws Exception {
    	System.out.println("------删除原文信息------------------------");
    	System.out.println("fileID: "+fileID);
    	System.out.println("NBXH: "+NBXH);
    	System.out.println("officialArchivesTypeID: "+officialArchivesTypeID);
    	
    	boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		String filePath = "";
		try {
			pErrPos = 1;
	    	
	    	OfficialArchivesType officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(officialArchivesTypeID);
	    	OfficialArchivesInfoAttachedFile officialArchivesInfoAttachedFile = new OfficialArchivesInfoAttachedFile();
	    	officialArchivesInfoAttachedFile.setID(fileID);
	    	if (pFlag) {
				if (officialArchivesInfoAttachedFileManageService.findOfficialArchivesInfoAttachedFileByID(officialArchivesType, officialArchivesInfoAttachedFile, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找原文信息失败");
				}
			}
	    	int officialArchivesInfoAttachedFileID = officialArchivesInfoAttachedFile.getID();
	    	if (pFlag) {
				if (officialArchivesInfoAttachedFileManageService.deleteOfficialArchivesInfoAttachedFile(officialArchivesType, officialArchivesInfoAttachedFileID, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "删除原文信息失败");
				}
			}
	    	
	    	//删除文件
	    	if (pFlag) {
	    		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    		filePath = WebCommonUtil.getOfficialArchivesAttachedFileBasePath(officialArchivesInfoAttachedFile.getAttachedTime(),SystemInitializer.getInstance().getAttachedFileTempSavePaths(), officialArchivesType)
	            + dateFormat.format(officialArchivesInfoAttachedFile.getAttachedTime())+"\\"
		    	+ officialArchivesInfoAttachedFile.getOriFileName();
		    	
		    	File file = new File(filePath);
		    	
		    	if (!file.delete()) {
		    		pFlag = false;
					pErrInfo.getContent().insert(0, "删除原文文件（"+filePath+"）失败");
				}
			}
	    	

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				filePath = WebCommonUtil.getOfficialAttachedFilePathOnServer(officialArchivesInfoAttachedFile, SystemInitializer.getInstance().getAttachedFileTempSavePaths(), officialArchivesType);
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
		return "删除成功";
    }
    
    @Override
	public void addActionError(String anErrorMessage) {
		if (anErrorMessage.startsWith("the request was rejected because its size")) {
			Matcher m = Pattern.compile("\\d+").matcher(anErrorMessage);
			String s1 = "";
			if (m.find()) {
				s1 = m.group();
			}
			String s2 = "";
			if (m.find()) {
				s2 = m.group();
			}
			super.addActionError("你上传的文件（"+s1+"）超过允许的大小（"+s2+"）");
		}else{
			super.addActionError(anErrorMessage);
		}
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
		
		OfficialArchivesType officialArchivesType = null;
		String savedPath = "";
		try {
			pErrPos = 1;
			officialArchivesType = SystemInitializer.getInstance().getOfficialArchivesTypes().get(getParameterForInt("officialArchivesTypeID"));
		    savedPath = WebCommonUtil.getAttachedFileSavePath(SystemInitializer.getInstance().getAttachedFileTempSavePaths(), officialArchivesType);
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
