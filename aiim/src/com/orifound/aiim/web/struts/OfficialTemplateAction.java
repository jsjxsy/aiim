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
	 * ����շ�������� ��ָ��������
	 * @return
	 * @throws Exception
	 */
	/**
	 * ���Entity��ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjectionForOfficialTemplate(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���BLLҵ���߼����������ע��
			pErrPos = 1;
			if (officialTemplateManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("OfficialTemplate��BLLҵ���߼�����Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * Dao�ӿڶ��壺ɾ��ָ����ģ��
	 * @param officialTemplate Ҫɾ����ģ��
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
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
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
					pErrInfo.getContent().insert(0, "");
					result=ERROR;
				}else{
					result=SUCCESS;
				}
			}
		} catch (Exception e) {
			//�����쳣����
			result=ERROR;
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

		return result;
	}

	/**
	 * ��о����ģ��
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
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
					pErrInfo.getContent().insert(0, "");
					result=ERROR;
				}else{
					result=SUCCESS;
				}
			}
		} catch (Exception e) {
			//�����쳣����
			result=ERROR;
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

		return result;
	}

	/**
	 * �������й���ģ��
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
					pErrInfo.getContent().append("����ģ�����ͱ�ŷǷ�Ϊ�գ�");
				}else {
					type=Integer.parseInt(typeString);
				}
			}
			OfficialDocType officialDocType = SystemInitializer.getInstance().getOfficialDocTypes().get(type);
			
			if (pFlag) {
				if(officialTemplateManageService.findOfficialTemplates(officialDocType,officialTemplates, pErrInfo)==false){					
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
					pErrInfo.getContent().insert(0, "��ѯģ�����ʧ�ܣ�");
					result=ERROR;
				}else{
					result="toTemplates";
					request.setAttribute("officialTemplates",officialTemplates );
					request.setAttribute("type", type);
				}
			}
		} catch (Exception e) {
			//�����쳣����
			result=ERROR;
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

		return result;
	}
	
	/**
	 * ����title���ҹ���ģ��
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
        int type=0;//����ģ������
        List<OfficialTemplate> officialTemplates = null;
     
       
        //����Ƿ��н���BLL����ע��
		if (checkBllInjectionForOfficialTemplate(pErrInfo) == false) {
			pFlag = false;
			pErrInfo.getContent().insert(0, "OfficialTemplate��BLL��Daoע��ʧ��:");
		}
		
		HttpServletRequest request=ServletActionContext.getRequest();
		if (pFlag) {
			String typeString=request.getParameter("type");
			if (typeString == null || typeString.equals("")) {
				pFlag = false;
				pErrInfo.getContent().append("����ģ�����ͱ�ŷǷ�Ϊ�գ�");
			}else {
				type=Integer.parseInt(typeString);
			}
		}
		//��ȡ����
		if (pFlag) {
			 title = request.getParameter("title");
		}
		try {
			if (pFlag) {
				officialTemplates = new ArrayList<OfficialTemplate>();
				OfficialDocType officialDocType = SystemInitializer.getInstance().getOfficialDocTypes().get(type);
				if (officialTemplateManageService.findOfficialTemplateByName(officialDocType,title,officialTemplates, pErrInfo)==false) {
					pFlag = false;//����ҵ���߼�������ʶΪʧ��
					result=ERROR;
				}else{
					result="toTemplates";
					request.setAttribute("type", type);
					request.setAttribute("officialTemplates",officialTemplates );
				}
			}
			
		} catch (Exception e) {
			//�����쳣����
			result=ERROR;
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

		return result;
	}
	
	/**
	 * ��ģ��
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
	 * �ϴ��ļ������С
	 */
	private static final int BUFFER_SIZE=16*1024; 
    
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
     * Ҫ�����ļ���·��
     */
    private String filePath;   
    
    /**
     * ��װҪ���ص��ļ�
     */
    private File downLoadFile;
    
    /**
     * ���ص�URL·��
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
	 * �Լ���װ��һ����Դ�ļ������Ƴ�Ŀ���ļ�����   
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
     * �ϴ�ԭ��
     * @return
     * @throws Exception
     */
    public String uploadFile() throws Exception { 
    	System.out.println("------�ϴ�����ģ��------------------------");
        HttpServletRequest request = ServletActionContext.getRequest();
    	
        boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();

		File[] srcFiles = null;
		DateFormat format = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String date=format.format(new Date());
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//��ȡ�����ϴ�·��

			String path = getOfficialTemplateSaveBasePath()+"\\OfficialTemplates";
			
		    String title = request.getParameter("title");
		    if (pFlag) {
		    	 if("".equals(path)){
		    		 pFlag = false;
		    		 pErrInfo.getContent().append("���ԭ����ʱ�洢·��ʧ�ܣ��������ݿ�����");
		    	 }else{
		    		 setSavePath(path);
		    	 }
			}
		    
			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (this.getUpload() == null) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}else if(this.getUpload().length == 0) {
					pFlag = false;
					pErrInfo.getContent().append("��ѡ��Ҫ�ϴ����ļ�");
				}else{
					srcFiles = this.getUpload();
				}  
			}
			if (pFlag) {
				 // ����ÿ��Ҫ�ϴ����ļ�   
		        for (int i = 0; i < srcFiles.length; i++) {  

		            // ����ָ����·�������ļ�   
		        	String fileName=this.getUploadFileName()[i];
		            String dstPath = getSavePath()+"\\"+date+"_"+fileName; 
		            File dstFile = new File(dstPath); 
		           
		            //�ϴ��ļ����ڵ��ļ���getSavePath()
		            File filePath = new File(getSavePath());
		            //�����ļ���
		            if(!filePath.exists()) {
		            	if(filePath.mkdirs() == false){
		            		 pErrInfo.getContent().append("����Ŀ¼"+getSavePath()+"ʧ�ܣ�");
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
				          //�����ļ�
			            if(copy(srcFiles[i], dstFile)){   
			            	if(officialTemplateManageService.saveOfficialTemplate(officialTemplate, pErrInfo)==false){					
								pFlag = false;//����ҵ���߼�������ʶΪʧ��
								pErrInfo.getContent().insert(0, "���湫��ģ����Ϣʧ��!");
								resultURL=ERROR;
			            	}
			            } 
					}
		        }
			}
			resultURL = "/GWGL/GWGL_templateManage.jsp";
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
			}
			//���پֲ�����
			throwable = null;
		}
		return "true";   
    }
    
    /**
     * �ļ����ط��ص�IO��
     * @return
     * @throws Exception
     */
    public InputStream getInputStream() throws Exception
    {
		return new BufferedInputStream(new FileInputStream(downLoadFile));
    }
    /**
     * �ļ�����
     * @return
     * @throws Exception
     */
    public String downLoadFile() throws Exception { 
    	System.out.println("------����ԭ��------------------------");
    	ErrInfo pErrInfo = new ErrInfo();
    	HttpServletRequest request = ServletActionContext.getRequest();
    	//��ȡ�����ϴ�·��
		List<Config> officialTemplateFilePath = new ArrayList<Config>();
		if (configManageService.findConfigByConfigType("OfficialTemplateFilePath", officialTemplateFilePath, pErrInfo) == false)
		{
			pErrInfo.getContent().insert(0, "��ȡ����ģ���ϴ�Ŀ¼��Ϣʧ��: ");
		}
		
    	int id=Integer.parseInt(request.getParameter("ID"));
    	OfficialTemplate template = new OfficialTemplate();
    	if(officialTemplateManageService.findOfficialTemplateByID(id, template, pErrInfo) == false){
    		pErrInfo.getContent().insert(0,"��ѯ����ģ����Ϣʧ��:");
    	}
    	filePath = getOfficialTemplateSaveBasePath()+"\\OfficialTemplates\\"+template.getFileName();
    	downloadContentType = "application/octet-stream";//�����������ļ����� 
    	downLoadFile = new File(getFilePath());
    	if(!downLoadFile.isFile()){
    		pErrInfo.getContent().insert(0,"Ҫ���صĹ����ļ������ڣ�");
    		return ERROR;
    	}
    	downloadFileName = new String(downLoadFile.getName().getBytes(), "ISO8859-1"); //������Ҫ����Ȼ�ļ���Ϊ����
        return SUCCESS;   
    }

    /**
     * ��ȡ����ģ��ĸ�����·��
     * @return
     */
    private String getOfficialTemplateSaveBasePath(){
    	ResourceBundle rb = ResourceBundle.getBundle("aiim");
    	return rb.getString("AppData");
    }


}
