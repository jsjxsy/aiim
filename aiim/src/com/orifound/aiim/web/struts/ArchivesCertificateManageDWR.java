package com.orifound.aiim.web.struts;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IArchivesUsePersonInfoManageService;
import com.orifound.aiim.bll.service.IConfigManageService;
import com.orifound.aiim.bll.service.IStudentGradeInfoManageService;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.Config;
import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentGradeInfo;

/**
 * 档案出证管理DWR
 *
 */
public class ArchivesCertificateManageDWR {
	
	/**
	 * 注入 案利用人信息管理与服务对象
	 */
	@Autowired
	private IArchivesUsePersonInfoManageService archivesUsePersonInfoManageService;
	
	/**
	 * 注入学生成绩信息表管理服务对象
	 */
	@Autowired
	private IStudentGradeInfoManageService studentGradeInfoManageService;
	
	/**
	 * 注入系统配置管理服务对象
	 */
	@Autowired
	private IConfigManageService configManageService;
	
	/**
	 * 根据姓名或证件号码 查询档案利用人信息集合
	 * @param archivesUsePersonInfo	查询档案利用人条件实体
	 * @param request	web请求对象 
	 * @return List<ArchivesUsePersonInfo>
	 */
	public List<ArchivesUsePersonInfo> findArchivesUsePersonInfoByQuery(ArchivesUsePersonInfo archivesUsePersonInfo ,HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<ArchivesUsePersonInfo> archivesUsePersonInfos = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				archivesUsePersonInfos = new ArrayList<ArchivesUsePersonInfo>();
				
					//如果证件号码=='none' 	根据姓名查询
				if ("none".equals(archivesUsePersonInfo.getIDCardNo())) {
					if (archivesUsePersonInfoManageService.findByName(archivesUsePersonInfo.getName(), archivesUsePersonInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据唯名字查找档案利用人信息表 失败：");
					}
					//如果证件号码!='none' 	根据证件号码查询
				} else {
					if (archivesUsePersonInfoManageService.findByIDCardNo(archivesUsePersonInfo.getIDCardNo(), archivesUsePersonInfos , pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "根据证件号码查找档案利用人信息表 失败：");
					}
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
		return archivesUsePersonInfos;
	}
	
	/**
	 * 根据课程名称检索公选课程
	 * @param XH 学号
	 * @param courseName 课程名称
	 * @return List<Course>
	 */
	public List<Course> findElectivesCourse(String XH, String courseName, HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<Course> electivesCourses = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//查询公选课程信息
			if (pFlag) {
				pErrPos = 2;
				electivesCourses = new ArrayList<Course>();
				if (studentGradeInfoManageService.findAllElectivesCourseByName(courseName, XH, "1", electivesCourses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询公选课程信息 失败：");
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
		return electivesCourses;
	}
	
	/**
	 * 批量插入公选课程
	 * @param XH	学号
	 * @param courseNames 公选课程数名称数组
	 * @return 插入是否成功
	 */
	public List<StudentGradeInfo> saveElectivesCourse(String XH, String[] courseNames) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		List<StudentGradeInfo> studentGradeInfos = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				studentGradeInfos = new ArrayList<StudentGradeInfo>();
				if (studentGradeInfoManageService.saveElectivesCourse(XH, courseNames, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "批量插入公选课程 失败：");
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
		return studentGradeInfos;
	}
	
	/**
	 * 获取出证制作文件上传文件服务器的路径
	 * @return
	 */
	public String getUploadPath(HttpServletRequest request) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo = new ErrInfo();
		String uploadPath = null;
		try {
			pErrPos = 1;
			//检查是否有进行BLL依赖注入
			if (checkBllInjection(pErrInfo ) == false) {
				pFlag = false;
			}

			//获取指定配置项
			if (pFlag) {
				pErrPos = 2;
				List<Config> pConfigs = new ArrayList<Config>();
				if (configManageService.findConfigByConfigType("CertificateFilePath", pConfigs , pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "获取指定配置项 失败：");
				}
				
				//获取出证制作文件上传文件服务器的路径
				if (pConfigs.size() >= 1) {
					uploadPath = pConfigs.get(0).getLocalPath()+"\\"+new SimpleDateFormat("yyyyMMdd").format(new Date())+"\\";
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
		return uploadPath;
	}
	
	/**
	 * 检查Entity的业务逻辑对象依赖注入（BLL Depandency Injection）
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
			if (archivesUsePersonInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"案利用人信息的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (studentGradeInfoManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"学生成绩信息表的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
			}
			
			if (configManageService == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"系统配置管理的BLL业务逻辑对象非法为空，请检查是否有进行依赖注入或赋值。");
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
}