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
 * 档案出证管理 Action
 *
 */
public class ArchivesCertificateManageAction extends BaseAction{
	private static final long serialVersionUID = -3340651233191995620L;
	
	static Log logger = LogFactory.getLog(AttachedFileManageAction.class);
	
	/**
	 * 注入档案利用人信息管理与服务对象
	 */
	@Autowired
	private IArchivesCertificateInfoManageService archivesCertificateInfoManageService;
	
	/**
	 * 注入出证学生信息管理服务对象
	 */
	@Autowired
	private ICertificateStudentManageService certificateStudentManageService;
	
	/**
	 * 注入学生成绩信息表管理服务对象
	 */
	@Autowired
	private IStudentGradeInfoManageService studentGradeInfoManageService;
	
	/**
	 * 注入档案利用人信息管理与服务对象
	 */
	@Autowired
	private IArchivesCertificateRegisterManageService archivesCertificateRegisterManageService;
	
	/**
	 * 返回的结果URL
	 */
	private String resultURL;
	
	/**
	 * 菜单节点值
	 */
	private String nodeId;
	
	/**
	 * 档案出证类型集合
	 */
	private List<CertificateType> certificateTypes = new ArrayList<CertificateType>();
	
	/**
	 * 档案出证类型集合JSON格式
	 */
	private String certificateTypesJSON ;
	
	/**
	 * 利用人id
	 */
	private int userID;
	
	/**
	 * 应缴金额
	 */
	private double shouldCharge=0;
	
	/**
	 * 实际收费
	 */
	private double realCharge=0;
	
	/**
	 * 发票代码
	 */
	private String invoiceSN=null;
	
	/**
	 * 备注
	 */
	private String remark=null;
	
	/**
	 * 出证明细JSON格式
	 */
	private String certificateInfoArrayJSON;
	
	/**
	 * 利用人姓名
	 */
	private String personName;
	
	/**
	 * 证件号
	 */
	private String cardId;
	
	/**
	 * 出证类型id
	 */
	private String certificateTypeId;
	
	/**
	 * 出证登记id
	 */
	private int certificateRegID; 
	
	/**
	 * 出证信息id
	 */
	private int certificateInfoID;
	
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
     * 包装要下载的文件
     */
    private File downloadFile;
    
    /**
     * 证明文件名称
     */
    private String certificateFileName;
    /**
     * 制作完成标志
     */
    private boolean dealedFlag;
    
    /**
     * 学号
     */
    private String XH;
	
	/**
	 * 出证登记信息集合
	 */
	private List<ArchivesCertificateRegister> archivesCertificateRegisters = new ArrayList<ArchivesCertificateRegister>();
	
	/**
	 * 学生信息
	 */
	private CertificateStudent certificateStudent = new CertificateStudent();
	
	/**
	 * 专业信息集合
	 */
	private List<Major> majors = new ArrayList<Major>();
	
	/**
	 * 学院信息集合
	 */
	private List<College> colleges = new ArrayList<College>();
	
	/**
	 * 学生信息更新标志
	 * y:更新、n:保存
	 */
	private String updateStudentFlag = "n";
	
	/**
	 * 学生成绩信息数组json格式
	 */
	private String gradeArrayJSON;
	
	/**
	 * 分页集合
	 */
	private DataPageInfo dataPageInfo = new DataPageInfo();
	
	public DataPageInfo getDataPageInfo() {
		return dataPageInfo;
	}

	public void setDataPageInfo(DataPageInfo dataPageInfo) {
		this.dataPageInfo = dataPageInfo;
	}
	
	/**
	 * 日期查询条件
	 */
	private DateQuerycondition dateQuerycondition = new DateQuerycondition();
	
	public DateQuerycondition getDateQuerycondition() {
		return dateQuerycondition;
	}

	public void setDateQuerycondition(DateQuerycondition dateQuerycondition) {
		this.dateQuerycondition = dateQuerycondition;
	}

	/**
	 * 构建 档案利用->出证管理 菜单树
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
	 * 根据nodeId值
	 * 判断获取哪个情况监测 
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
			//构建菜单树
			getTree();
			
			switch (node) {
			case 1:
				//出证管理
				break;
			case 11:
				//收费登记
				priceRegist();
				resultURL = "/DALY/balanceRegist.jsp";
				break;
			case 12:
				//出证制作
				certificateMake();
				break;
			case 13:
				//收费统计
				balanceStat();
				resultURL = "/DALY/chargeCollect.jsp";
				break;
			default:
				break;
			}
		}
		return SUCCESS;
	}

	//收费登记
	private void priceRegist() {
	}
	
	/**
	 * 收费结算
	 */
	public String priceBalance() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		HttpServletRequest request = getRequest();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				UserInfo userInfo = WebCommonUtil.getUserInfo(request, pErrInfo);
				//构建档案出证登记实体
				ArchivesCertificateRegister certificateRegister = new ArchivesCertificateRegister(0,userID,shouldCharge,realCharge,new Date(),invoiceSN, userInfo.getUserID(),remark);
				//构建档案出证明细信息
				List<ArchivesCertificateInfo> archivesCertificateInfos = new ArrayList<ArchivesCertificateInfo>();
				
				JSONArray jsonArray = JSONArray.fromObject(certificateInfoArrayJSON);
				if (jsonArray==null || jsonArray.size()<=0) {
					pFlag = false;
					pErrInfo.getContent().append("JSON数组转换失败。");
				}
				//循环解析json对象
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
					pErrInfo.getContent().insert(0, "Action 出证收费登记 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/CZDJ.jsp";
		return SUCCESS;
	}
	
	/**
	 * 出证制作
	 */
	public String certificateMake() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				archivesCertificateInfoManageService.findCertificateTypes(certificateTypes, new ErrInfo());
				
				Map<String, String> queryString = new HashMap<String, String>();
				//利用人姓名
				if (StringTool.checkNull(personName)) {
					queryString.put("personName", personName);
				}
				//利用人证件号
				if (StringTool.checkNull(cardId)) {
					queryString.put("cardId", cardId);
				}
				
				if (archivesCertificateInfoManageService.findArchivesCertificateRegistersByQuery(queryString, archivesCertificateRegisters, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询出证登记信息失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		
		resultURL = "/DALY/certificateMake.jsp";
		return SUCCESS;
	}
	
	/**
	 * 出证制作详情
	 * @return
	 */
	public String certificateMakeDetail() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//根据出证登记id查找档案出证信息
			if (pFlag) {
				pErrPos = 2;
				List<ArchivesCertificateInfo> archivesCertificateInfos = new ArrayList<ArchivesCertificateInfo>();
				if (archivesCertificateInfoManageService.findArchivesCertificateInfosByRegisterId(certificateRegID, archivesCertificateInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, " 根据出证登记id查找档案出证信息 失败：");
				} else {
					getRequest().setAttribute("archivesCertificateInfos", archivesCertificateInfos);
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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

		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/certificateMake_detail.jsp";
		return SUCCESS;
	}
	
	 /**
     * 文件下载返回的IO流
     * @return
     * @throws Exception
     */
    public InputStream getInputStream() throws Exception
    {
		return new BufferedInputStream(new FileInputStream(downloadFile));
    }

	/**
	 * 出证文件下载
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
				downloadContentType = "application/octet-stream";//无限制下载文件类型 
				 /**
			     * 要下载文件的路径
			     */
			    String filePath = null;
			    
			    dealedFlag = false;
			    certificateFileName = "学生毕业证明.doc";
				/*判断是否制作完成*/
				if (dealedFlag) { //使用webservice下载文件
					
				} else {	//下载模板文件
					filePath = getRequest().getSession().getServletContext().getRealPath("/certificateTempFile/"+certificateFileName);
				}
				
				downloadFile = new File(filePath);
				if (downloadFile==null || downloadFile.isDirectory() || downloadFile.exists()==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "下载的出证文件不存在！");
				} else {
					String fileName = downloadFile.getName();
					DateFormat dateFormat = new SimpleDateFormat("yyyyMMddhhmmssSSS");
					//文件后缀".txt"
					String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
					//文件名"xxxxx_20100829090909333"
					String fileName_front = fileName.substring(0, fileName.lastIndexOf('.'))+"_"+dateFormat.format(new Date());
					downloadFileName = fileName_front+ext;
			    	downloadFileName = new String(downloadFileName.getBytes(), "ISO8859-1"); //这句很重要，不然文件名为乱码
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo .getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		System.out.println(pErrInfo.toString());
		return SUCCESS;
	}
	
	/**
	 * 出证文件上传
	 * @return
	 */
	public String uploadFile() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			if (pFlag) {
				//检查是否有进行BLL依赖注入
				if (checkBllInjection(pErrInfo) == false) {
					pFlag = false;
				}
			}
			
			if (pFlag) {
				pErrPos = 1;
				//根据id查询出证详情
				ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo();
				if (archivesCertificateInfoManageService.findArchivesCertificateInfoByID(certificateInfoID, archivesCertificateInfo , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "据唯一标识查找档案出证明细情况信息 失败：");
				}
				
				//检测是否存在上传文件
				if (pFlag) {
					if (upload.length <= 0) {
						pFlag = false;
						pErrInfo.getContent().append("上传文件不存在");
					} 
				}
				
				//修改指定的档案出证明细情况
				if (pFlag) {
					archivesCertificateInfo.setCertificateFileName(uploadFileName[0]);
					archivesCertificateInfo.setFileUploadDate(new Date());
					archivesCertificateInfo.setDealedFlag(true);
					if (archivesCertificateInfoManageService.updateArchivesCertificateInfo(archivesCertificateInfo, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().append("修改指定的档案出证明细情况 失败：");
					}
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		
		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/certificateMake_upload.jsp";
		return SUCCESS;
	}
	
	/**
	 * 根据学号查询学生信息
	 * @return String
	 */
	public String findStudentInfo() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//查找所有的专业信息
			if (pFlag) {
				if (certificateStudentManageService.findAllMajor(majors, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的专业信息 失败：");
				}
			}
			
			//查找所有的学院信息
			if (pFlag) {
				if (certificateStudentManageService.findAllCollege(colleges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的学院信息 失败：");
				}
			}

			if (pFlag) {
				pErrPos = 2;
				//根据唯一标识查找出证学生信息信息
				if (StringTool.checkNull(XH)) {
					certificateStudent = new CertificateStudent();
					if (certificateStudentManageService.findCertificateStudentByID(XH, certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据唯一标识查找出证学生信息信息 失败：");
					} else {
						//检测是否存在学生信息
						if (StringTool.checkNull(certificateStudent.getXH())) {
							//设置更新标志
							updateStudentFlag = "y";
						}
					}
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/certificateMake_studentInfo.jsp";
		return SUCCESS;
	}
	
	/**
	 * 新增/修改学生信息
	 * @return
	 */
	public String saveOrUpdateStudentInfo() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			
			//判断学号
			if (pFlag) {
				pErrPos = 2;
				if (StringTool.checkNull(certificateStudent.getXH()) == false) {
					pFlag = false;
					pErrInfo.getContent().append("获取页面参数学号非法为空。");
				}
			}
			
			//根据唯一标识查找出证学生信息
			CertificateStudent certificateStudentOther = null;
			if (pFlag) {
				certificateStudentOther = new CertificateStudent();
				if (certificateStudentManageService.findCertificateStudentByID(certificateStudent.getXH(), certificateStudentOther, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找出证学生信息信息 失败：");
				}
			}
			
			if (pFlag) {
				//设置学生对应的出证信息id
				certificateStudent.setCertificateInfoID(certificateInfoID);
				//判断是否存在学生信息
				if (StringTool.checkNull(updateStudentFlag) && "y".equals(updateStudentFlag)) {
					//更新学生信息
					if (certificateStudentManageService.updateCertificateStudent(certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "修改指定的出证学生信息 失败：");
					}
				} else {
					//新增学生信息
					if (certificateStudentManageService.saveCertificateStudent(certificateStudent, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "保存指定的出证学生信息 失败：");
					}
				}
			}
			
			//更新关联的档案出证信息
			if (pFlag) {
				ArchivesCertificateInfo archivesCertificateInfo = new ArchivesCertificateInfo();
				archivesCertificateInfo.setID(certificateInfoID);
				archivesCertificateInfo.setXH(certificateStudent.getXH());
				if (archivesCertificateInfoManageService.updateArchivesCertificateInfoXH(archivesCertificateInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的档案出证信息学号 失败：");
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		
		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/certificateMake_studentInfo.jsp";
		return SUCCESS;
	}
	
	/**
	 * 查询学生成绩(不包含公选课)
	 * @return
	 */
	public String findGrade() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}
			//根据唯一标识查找出证学生信息
			if (pFlag) {
				if (certificateStudentManageService.findCertificateStudentByID(XH, certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找出证学生信息 失败：");
				}
			}
			
			//查询指定学生的所有课程成绩信息
			List<StudentGradeInfo> studentGradeInfos = null;
			if (pFlag) {
				studentGradeInfos = new ArrayList<StudentGradeInfo>();
				if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询指定学生的所有课程成绩信息 失败：");
				}
			}
			
			//判断是否存在学生课程成绩信息
			if (pFlag) {
				if (studentGradeInfos.size() <= 0) {
					//插入学生所有课程成绩信息
					if (pFlag) {
						pErrPos = 2;
						if (studentGradeInfoManageService.saveStudentAllGrade(XH, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "插入学生所有课程成绩信息 失败：");
						}
					}
					
					//查询指定学生的所有课程成绩信息
					if (pFlag) {
						studentGradeInfos = new ArrayList<StudentGradeInfo>();
						if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0, "查询指定学生的所有课程成绩信息 失败：");
						}
					}
				}
			}
			getRequest().setAttribute("studentGradeInfos", studentGradeInfos);
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		
		System.out.println(pErrInfo.toString());
		resultURL = "/DALY/certificateMake_grade.jsp";
		return SUCCESS;
	}
	
	/**
	 * 更新学生成绩
	 * @return String
	 */
	public String updateGrage() {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
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
				
				
				
				//批量更新学生所有课程成绩信息
				if (studentGradeInfos.size() >= 1) {
					if (studentGradeInfoManageService.updateStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "批量更新学生所有课程成绩信息 失败：");
					}
				}

				//查询指定学生的所有课程成绩信息
				if (pFlag) {
					studentGradeInfos = new ArrayList<StudentGradeInfo>();
					if (studentGradeInfoManageService.findStudentAllGrade(XH, studentGradeInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "查询指定学生的所有课程成绩信息 失败：");
					}
				}
				getRequest().setAttribute("studentGradeInfos", studentGradeInfos);
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
		resultURL = "/DALY/certificateMake_grade.jsp";
		return SUCCESS;
	}
	
	/**
	 * 收费汇总
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
				pErrInfo.getContent().insert(0, "查找符合查询条件的档案出证登记信息集合失败：");
			}
		}
		request.setAttribute("dateQuerycondition", dateQuerycondition);
		request.setAttribute("archivesCertificateRegisters", pArchivesCertificateRegisters);
	}
	
	/**
	 * 收费汇总
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
				pErrInfo.getContent().insert(0, "查找符合查询条件的利用人的档案出证登记信息集合失败：");
			}
		}
		request.setAttribute("ManagerUserID", pManagerUserID);
		request.setAttribute("dateQuerycondition", dateQuerycondition);
		resultURL = "/DALY/archivesCertificateRegister.jsp";
		request.setAttribute("archivesCertificateRegisters", pArchivesCertificateRegisters);
		return SUCCESS;
	}
	
	/**
	 * 获取收费明细信息
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
				pErrInfo.getContent().append("出证登记编号非法为0！");
				resultURL ="/error.jsp";
			}
		}
		
		if (pflag) {
			if (archivesCertificateInfoManageService.findArchivesCertificateInfoByCertificateRegID(pCertificateRegID, archivesCertificateInfo, pErrInfo)==false) {
				pflag = false;
				pErrInfo.getContent().insert(0,"出证登记编号非法为0！");
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
	 * 检查档案出证的业务逻辑对象依赖注入（BLL Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行BLL业务逻辑对象的依赖注入
			pErrPos = 1;
			if (archivesCertificateInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("档案出证的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (certificateStudentManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append("学生信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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