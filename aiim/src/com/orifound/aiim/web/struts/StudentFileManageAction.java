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
     * 用File数组来封装上传文件域对象，接收表单文件域
     */
    private File excel;   
  
    /**
     * 用String数组来封装上传文件名
     */
    private String excelFileName;   

    /**
     * 用String数组来封装上传文件类型  
     */
    private String excelContentType;   
	
	/**
	 * 返回的页面地址
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

	//构造学生登记人员类
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

		return SUCCESS;//跳转到左边，显示转出人员分类树
	}
	
	/**
	 * 得到新生入学登记分类树
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

		return SUCCESS;//跳转到左边，显示转出人员分类树
	}
	
	/**
	 * 查找登记完成的学生信息
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
			//获取参数值
			pErrPos = 1;
			String xyName = java.net.URLDecoder.decode(request.getParameter("xyName"),"UTF-8");

			System.out.println(xyName);
			dataPageInfo.setPageSize(15);
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				studentInfos = new ArrayList<StudentInfo>();
				if(studentFileManageService.find(xyName,EnumWorkFlowStatus.人事档案案卷著录完成,dataPageInfo,studentInfos,pErrInfo) == false){
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询学生档案失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			request.setAttribute("studentInfos",studentInfos);
			request.setAttribute("xyName",xyName);
			resultURL = "/personalFileManage/addDocToFile.jsp";
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
				
				if (pErrInfo.getException() != null) {
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}	
		return SUCCESS;
	}
	
	/**
	 * 批量导入学生信息
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
			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (this.getExcel() == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}
			}
			
			IntegerEx sum = null;
			if (pFlag) {
				archivesType = WebCommonUtil.getPersonTypeByImportType(EnumImportType.getEnumElement(importType));
	        	Excel excel = new Excel(this.getExcel());
	        	sum = new IntegerEx();
	        	if (studentFileManageService.importStudentInfo(excel,importType,archivesType,sum,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "导入失败: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {
				request.setAttribute("message", "<script>alert('共导入数据" + sum.getValue() + "条！')</script>");
			}
			resultURL = "/personalFileManage/studentRigister.jsp";
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
					request.setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					request.setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;   
	}
	
	/**
	 * 查找学生档案卷内文件
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
			//接收参数
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");

			//调用业务逻辑获取卷内文件
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找卷内文件失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			this.getRequest().setAttribute("studentInfo", studentInfo);
			resultURL = "/personalFileManage/dlg_AddDoc.jsp";
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
				
				if (pErrInfo.getException() != null) {
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 查找学生档案卷内文件
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
			//接收参数
			pErrPos = 1;
			String [] studentDocIds = this.getParameterValues("studentDocs");
			int nbxh = getParameterForInt("nbxh");

			if (studentDocIds == null || studentDocIds.length == 0) {
				pFlag = false;
				pErrInfo.getContent().append("请选择资料！ ");
			}
			
			if (pFlag) {
				pErrPos = 2;
				for (int i = 0; i < studentDocIds.length; i++) {
					ids.add(Integer.parseInt(studentDocIds[i]));
				}
			}
			
			//调用业务逻辑获取卷内文件
			if (pFlag) {
				pErrPos = 3;
				
				if (studentFileManageService.addDocs(nbxh,ids,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加卷内文件失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.print("添加成功！");
			}
			//resultURL = "/personalFileManage/dlg_AddDoc.jsp";
		} catch (Exception e) {
			//异常错误
			e.printStackTrace();
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
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 转出登记
	 * @return
	 * @throws Exception
	 */
	public String moveOutRegister() throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//开始处理2...
			int importType = getParameterForInt("importType");
			UserInfo userInfo = getSessionAttr("userInfo", UserInfo.class);
			System.out.println("importType: "+importType);
			ArchivesType archivesType = WebCommonUtil.getPersonTypeByImportType(EnumImportType.getEnumElement(importType));
			
			if (pFlag) {
				pErrPos = 2;
				if (this.getExcel() == null) {
					pFlag = false;
					pErrInfo.getContent().append("请选择要上传的文件");
				}
			}
			
			IntegerEx sum = null;
			if (pFlag) {
	        	Excel excel = new Excel(this.getExcel());
	        	sum = new IntegerEx();
	        	if (studentFileManageService.moveOutRegister(userInfo.getUserID(),excel,importType,archivesType,sum,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "登记失败: ");
					System.out.println(pErrInfo.toString());
				}       	
	        }  
			
			if (pFlag) {;
				print("共导入数据" + sum.getValue() + "条！");
			}
			//resultURL = "/personalFileManage/outAddrRigister.jsp";
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
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 查找转出单信息
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
			//获得参数
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
			
			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				moveOutInfos = new ArrayList<MoveOutInfo>();
				boolean moveOutFlag = false;//是否转出标志
				dataPageInfo.setPageSize(13);
				if (studentFileManageService.findMoveOutInfo(moveOutWay,moveOutFlag,archivesType,dataPageInfo, minNum , maxNum, moveOutInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询转出信息失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				this.setAttribute("moveOutInfos", moveOutInfos);
				setAttribute("archivesTypeID", archivesTypeID);
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 根据ID查找转出单信息
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
			//获得参数
			pErrPos = 1;
			int id = getParameterForInt("id");

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				moveOutInfo = new MoveOutInfo();
				if (studentFileManageService.findMoveOutInfoById(id,moveOutInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询转出信息失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("moveOutInfo", moveOutInfo);
			}
			resultURL = "/personalFileManage/outDetailInfo.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 更新转出单的转出方式
	 * @return
	 * @throws Exception
	 */
	public String updateMoveOutWay () throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//获得参数
			pErrPos = 1;
			int id = getParameterForInt("id");
			int moveOutWay = getParameterForInt("moveOutWay");

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.updateMoveOutWay(id,moveOutWay,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询转出信息失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				print("更新成功！");
			}
			resultURL = "/personalFileManage/outDetailInfo.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 给转出单关联序列号
	 * @return
	 * @throws Exception
	 */
	public String updateSN () throws Exception{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		
		try {
			//获得参数
			pErrPos = 1;
			int id = getParameterForInt("id");
			String SN = getParameterForString("SN");

			//调用业务逻辑
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.updateSN(id,SN,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "关联转出单序列号失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				print("更新成功！");
			}
			//resultURL = "/personalFileManage/outDetailInfo.jsp";
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
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return null;
	}
	
	/**
	 * 缓发登记查询
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
			//获得参数
			pErrPos = 1;
			String studentId = getParameterForString("studentId");
			String studentName = getParameterForString("studentName");

			//调用业务逻辑查询学生信息
			if (pFlag) {
				pErrPos = 2;
				
				List<EnumWorkFlowStatus> enumWorkFlowStatus = new ArrayList<EnumWorkFlowStatus>();
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案卷内文件著录完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案缓发登记完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案转出登记完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案卷内文件著录完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案终止邮寄);
				
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStudentId(studentId);
				studentInfo.setStudentName(studentName);
				
				studentInfos = new ArrayList<StudentInfo>();
				if (studentFileManageService.findStudentInfos(studentInfo,enumWorkFlowStatus,studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询学生档案信息失败: ");
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 查询学生档案信息
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
			//获得参数
			pErrPos = 1;
			String studentId = getParameterForString("studentId");
			String studentName = getParameterForString("studentName");
			String SN = getParameterForString("SN");

			//调用业务逻辑查询学生信息
			if (pFlag) {
				pErrPos = 2;
				
				List<EnumWorkFlowStatus> enumWorkFlowStatus = new ArrayList<EnumWorkFlowStatus>();
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案案卷著录完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案卷内文件著录完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案缓发登记完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案转出登记完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案卷内文件著录完成);
				enumWorkFlowStatus.add(EnumWorkFlowStatus.人事档案终止邮寄);
				
				StudentInfo studentInfo = new StudentInfo();
				studentInfo.setStudentId(studentId);
				studentInfo.setStudentName(studentName);
				studentInfo.setSN(SN);
				
				studentInfos = new ArrayList<StudentInfo>();
				if (studentFileManageService.findStudentInfos(studentInfo,enumWorkFlowStatus,studentInfos,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询学生档案信息失败: ");
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 设置学生档案工作流状态
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
			//获得参数
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");
			int state = getParameterForInt("state");

			//调用业务逻辑查询学生信息
			if (pFlag) {
				pErrPos = 2;
				if (studentFileManageService.findStudentInfos(nbxh,EnumWorkFlowStatus.getEnumElement(state),pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "设置档案工作流状态失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			if (pFlag) {
				setAttribute("studentInfos", studentInfos);
			}
			resultURL = "/personalFileManage/studentList.jsp";
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
					setAttribute("pErrInfo", pErrInfo);
					resultURL = "/error.jsp";
				} else {
					setAttribute("message", "<script>alert(\""+ pErrInfo.toShortString() + "!\");</script>");
				}
				logger.error(pErrInfo.getContent());
			}
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
	
	/**
	 * 打印学生档案卷内文件信息
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
			//接收参数
			pErrPos = 1;
			int nbxh = getParameterForInt("nbxh");

			//调用业务逻辑获取卷内文件
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找卷内文件失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			//调用业务逻辑获取卷内文件
			if (pFlag) {
				pErrPos = 2;
				studentInfo = new StudentInfo();
				if (studentFileManageService.findDocByNBXH(nbxh,studentInfo,pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找卷内文件失败: ");
					System.out.println(pErrInfo.toString());
				}
			}
			
			this.getRequest().setAttribute("studentInfo", studentInfo);
			resultURL = "/personalFileManage/print_student.jsp";
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
			//销毁局部变量
			throwable = null;
		}
		return SUCCESS;
	}
}
