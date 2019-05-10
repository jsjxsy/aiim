package com.orifound.aiim.web.struts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IOfficialTemplateManageService;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesType;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import com.orifound.aiim.entity.SystemInitializer;
import com.orifound.aiim.entity.UserInfo;
import com.orifound.aiim.web.util.CreateTreeUtil;

public class OfficialTemplateAction extends ActionSupport implements ModelDriven<OfficialTemplate>{
	private static final long serialVersionUID = 1L;
	static Log logger = LogFactory.getLog(IntegratedQueryAction.class);
	
	@Autowired
	private IOfficialTemplateManageService officialTemplateManageService;
	
	private IConfigManageService configManageService;
	
	public IConfigManageService getConfigManageService() {
		return configManageService;
	}

	public void setConfigManageService(IConfigManageService configManageService) {
		this.configManageService = configManageService;
	}

	/**
	 * 获得收发文类别树 并指定处理函数
	 * @return
	 * @throws Exception
	 */
	/**
	 * 检查Entity的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjectionForOfficialTemplate(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (officialTemplateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialTemplate的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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
			}
		}

		return pFlag;
	}
	
	public String getDocCenterTypeTree() throws Exception {
		LinkedHashMap<Integer, OfficialArchivesType> officialArchivesTypes = SystemInitializer.getInstance().getOfficialArchivesTypes();
		List<DepartmentInfo> departmentInfos = SystemInitializer.getInstance().getDepartmentInfos();
		LinkedHashMap<Integer, OfficialDocType> officialDocTypes = SystemInitializer.getInstance().getOfficialDocTypes();
		String tree = CreateTreeUtil.getDocCenterTree(officialArchivesTypes, officialDocTypes, departmentInfos);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("tree", tree);
		request.setAttribute("proceseAction", "officialTemplateAction_findAllOfficialTemplate.action?type=" + request.getParameter("type"));
		return "ownLeft";
	}
	

	/**
	 * Dao接口定义：删除指定的模板
	 * @param officialTemplate 要删除的模板
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public String deleteOfficialTemplate()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
        String result="";
		try {
			if (pFlag) {
				if(officialTemplateManageService.deleteOfficialTemplate(officialTemplate, pErrInfo)==false){					
					pFlag = false;//调用业务逻辑出错，标识为失败
					pErrInfo.getContent().insert(0, "");
					result=ERROR;
				}else{
					result=SUCCESS;
				}
			}
		} catch (Exception e) {
			//其他异常错误
			result=ERROR;
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

		return result;
	}

	/**
	 * 灯芯公文模板
	 * @return
	 * @throws Exception
	 */
	public String updateOfficialTemplate()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
        String result="";
		try {
			if (pFlag) {
				if(officialTemplateManageService.updateOfficialTemplate(officialTemplate, pErrInfo)==false){					
					pFlag = false;//调用业务逻辑出错，标识为失败
					pErrInfo.getContent().insert(0, "");
					result=ERROR;
				}else{
					result=SUCCESS;
				}
			}
		} catch (Exception e) {
			//其他异常错误
			result=ERROR;
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

		return result;
	}

	/**
	 * 查找所有公文模板
	 * @return
	 * @throws Exception
	 */
	public String findAllOfficialTemplate()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
	    String result="";
	    List<OfficialTemplate> officialTemplates = new ArrayList<OfficialTemplate>();
	    int type=0;
		try {
			HttpServletRequest request=ServletActionContext.getRequest();
			if (pFlag) {
				String typeString=request.getParameter("type");
				if (typeString == null || typeString.equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("公文模板类型编号非法为空！");
				}else {
					type=Integer.parseInt(typeString);
				}
			}
			OfficialDocType officialDocType = SystemInitializer.getInstance().getOfficialDocTypes().get(type);
			
			if (pFlag) {
				if(officialTemplateManageService.findOfficialTemplates(officialDocType,officialTemplates, pErrInfo)==false){					
					pFlag = false;//调用业务逻辑出错，标识为失败
					pErrInfo.getContent().insert(0, "查询模板管理失败！");
					result=ERROR;
				}else{
					result="toTemplates";
					request.setAttribute("officialTemplates",officialTemplates );
					request.setAttribute("type", type);
				}
			}
		} catch (Exception e) {
			//其他异常错误
			result=ERROR;
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

		return result;
	}
	
	/**
	 * 根据title查找公文模板
	 * @return
	 * @throws Exception
	 */
	public String findOfficialTemplateByName()throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		ErrInfo pErrInfo = new ErrInfo();
		Throwable throwable = new Throwable();
        String result="";
        String title="";
        int type=0;//公文模板类型
        List<OfficialTemplate> officialTemplates = null;
     
       
        //检查是否有进行BLL依赖注入
		if (checkBllInjectionForOfficialTemplate(pErrInfo) == false) {
			pFlag = false;
			pErrInfo.getContent().insert(0, "OfficialTemplate的BLL层Dao注入失败:");
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();
		if (pFlag) {
			String typeString=request.getParameter("type");
			if (typeString == null || typeString.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("公文模板类型编号非法为空！");
			}else {
				type=Integer.parseInt(typeString);
			}
		}
		//获取标题
		if (pFlag) {
			 title = request.getParameter("title");
		}
		try {
			if (pFlag) {
				officialTemplates = new ArrayList<OfficialTemplate>();
				OfficialDocType officialDocType = SystemInitializer.getInstance().getOfficialDocTypes().get(type);
				if (officialTemplateManageService.findOfficialTemplateByName(officialDocType,title,officialTemplates, pErrInfo)==false) {
					pFlag = false;//调用业务逻辑出错，标识为失败
					result=ERROR;
				}else{
					result="toTemplates";
					request.setAttribute("type", type);
					request.setAttribute("officialTemplates",officialTemplates );
				}
			}
			
		} catch (Exception e) {
			//其他异常错误
			result=ERROR;
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

		return result;
	}
	
	/**
	 * 打开模板
	 * @return
	 * @throws Exception
	 */
	public String addTemplate() throws Exception{
		Map<Integer, OfficialDocType> officialDocTypes=new LinkedHashMap<Integer, OfficialDocType>();
		officialDocTypes=SystemInitializer.getInstance().getOfficialDocTypes();
		HttpServletRequest  request=ServletActionContext.getRequest();
		request.setAttribute("officialDocTypes", officialDocTypes);
		return "addTemplate";
	}
	
	private OfficialTemplate officialTemplate = new OfficialTemplate();
	
	@Override
	public OfficialTemplate getModel() {
		return officialTemplate;
	}
	
	/**
	 * 上传文件缓存大小
	 */
	private static final int BUFFER_SIZE=16*1024; 
    
    /**
     * 用File数组来封装多个上传文件域对象，接收表单文件域
     */
    private File[] upload;   
  
	/**
     * 用String数组来封装多个上传文件名
     */
    private String[] uploadFileName;   

    /**
     * 用String数组来封装多个上传文件类型  
     */
    private String[] uploadContentType;   
    
    /**
    
    /**
     * 保存文件的目录路径
     */
    private String savePath;   
    
    /**
     * 要下载的文件名    
     */
    private String downloadFileName; 
    
    /**
     * 封装下载文件类型   
     */
    private String downloadContentType; 
    
    /**
     * 要下载文件的路径
     */
    private String filePath;   
    
    /**
     * 包装要下载的文件
     */
    private File downLoadFile;
    
    /**
     * 返回的URL路径
     */
    private String resultURL;

	public File[] getUpload() {
		return upload;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileName;
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

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public File getDownLoadFile() {
		return downLoadFile;
	}

	public void setDownLoadFile(File downLoadFile) {
		this.downLoadFile = downLoadFile;
	}

	public String getResultURL() {
		return resultURL;
	}

	public void setResultURL(String resultURL) {
		this.resultURL = resultURL;
	}
	


	/**
	 * 自己封装的一个把源文件对象复制成目标文件对象   
	 */
    private static boolean  copy(File src, File dst) {   
        boolean result=false;   
        InputStream in = null;   
        OutputStream out = null;   
        try {   
            in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);   
            out = new BufferedOutputStream(new FileOutputStream(dst), BUFFER_SIZE);   
            byte[] buffer = new byte[BUFFER_SIZE];   
            int len = 0;   
            while ((len = in.read(buffer)) > 0) {   
                out.write(buffer, 0, len);   
            }   
            result=true;   
        } catch (Exception e) {   
            e.printStackTrace();   
            result=false;   
        } finally {   
           if (null != in) {   
                try {   
                    in.close();   
              } catch (IOException e) {   
                    e.printStackTrace();   
               }   
            }   
            if (null != out) {   
                try {   
                   out.close();   
                } catch (IOException e) {   
                    e.printStackTrace();   
                }   
            }   
        }   
        return result;   
    }   
	
    /**
     * 上传原文
     * @return
     * @throws Exception
     */
    public String uploadFile() throws Exception { 
    	System.out.println("------上传公文模板------------------------");
        HttpServletRequest request = ServletActionContext.getRequest();
    	
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		File[] srcFiles = null;
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String date=format.format(new Date());
		
		try {
			//开始处理 1...
			pErrPos = 1;
			//获取公文上传路径

			String path = getOfficialTemplateSaveBasePath()+"\\OfficialTemplates";
			
		    String title = request.getParameter("title");
		    if (pFlag) {
		    	 if("".equals(path)){
		    		 pFlag = false;
		    		 pErrInfo.getContent().append("获得原文临时存储路径失败，请检查数据库配置");
		    	 }else{
		    		 setSavePath(path);
		    	 }
			}
		    
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}else if(this.getUpload().length == 0) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}else{
					srcFiles = this.getUpload();
				}  
			}
			if (pFlag) {
				 // 处理每个要上传的文件   
		        for (int i = 0; i < srcFiles.length; i++) {  

		            // 根据指定的路径创建文件   
		        	String fileName=this.getUploadFileName()[i];
		            String dstPath = getSavePath()+"\\"+date+"_"+fileName; 
		            File dstFile = new File(dstPath); 
		           
		            //上传文件所在的文件夹getSavePath()
		            File filePath = new File(getSavePath());
		            //创建文件夹
		            if(!filePath.exists()) {
		            	if(filePath.mkdirs() == false){
		            		 pErrInfo.getContent().append("创建目录"+getSavePath()+"失败！");
		            	}
		            }
		
		            HttpSession  session=request.getSession();
					UserInfo userInfo=(UserInfo)session.getAttribute("userInfo");
					String userName=userInfo.getUserName();
					officialTemplate.setCreateDate(format.parse(date));
					officialTemplate.setProvider(userName);
					officialTemplate.setFileName(dstFile.getName());
					officialTemplate.setTitle(title);
					if (pFlag) {
				          //复制文件
			            if(copy(srcFiles[i], dstFile)){   
			            	if(officialTemplateManageService.saveOfficialTemplate(officialTemplate, pErrInfo)==false){					
								pFlag = false;//调用业务逻辑出错，标识为失败
								pErrInfo.getContent().insert(0, "保存公文模板信息失败!");
								resultURL=ERROR;
			            	}
			            } 
					}
		        }
			}
			resultURL = "/GWGL/GWGL_templateManage.jsp";
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
					resultURL = "/error.jsp";
					request.setAttribute("pErrInfo", pErrInfo);
				}else{
					request.setAttribute("message", "<script>alert('"+pErrInfo.toShortString()+"!')</script>");
				}
			}
			//销毁局部变量
			throwable = null;
		}
		return "true";   
    }
    
    /**
     * 文件下载返回的IO流
     * @return
     * @throws Exception
     */
    public InputStream getInputStream() throws Exception
    {
		return new BufferedInputStream(new FileInputStream(downLoadFile));
    }
    /**
     * 文件下载
     * @return
     * @throws Exception
     */
    public String downLoadFile() throws Exception { 
    	System.out.println("------下载原文------------------------");
    	ErrInfo pErrInfo = new ErrInfo();
    	HttpServletRequest request = ServletActionContext.getRequest();
    	//获取公文上传路径
		List<Config> officialTemplateFilePath = new ArrayList<Config>();
		if (configManageService.findConfigByConfigType("OfficialTemplateFilePath", officialTemplateFilePath, pErrInfo) == false)
		{
			pErrInfo.getContent().insert(0, "获取公文模板上传目录信息失败: ");
		}
		
    	int id=Integer.parseInt(request.getParameter("ID"));
    	OfficialTemplate template = new OfficialTemplate();
    	if(officialTemplateManageService.findOfficialTemplateByID(id, template, pErrInfo) == false){
    		pErrInfo.getContent().insert(0,"查询公文模板信息失败:");
    	}
    	filePath = getOfficialTemplateSaveBasePath()+"\\OfficialTemplates\\"+template.getFileName();
    	downloadContentType = "application/octet-stream";//无限制下载文件类型 
    	downLoadFile = new File(getFilePath());
    	if(!downLoadFile.isFile()){
    		pErrInfo.getContent().insert(0,"要下载的公文文件不存在：");
    		return ERROR;
    	}
    	downloadFileName = new String(downLoadFile.getName().getBytes(), "ISO8859-1"); //这句很重要，不然文件名为乱码
        return SUCCESS;   
    }

    /**
     * 获取公文模板的根保存路径
     * @return
     */
    private String getOfficialTemplateSaveBasePath(){
    	ResourceBundle rb = ResourceBundle.getBundle("aiim");
    	return rb.getString("AppData");
    }


}
