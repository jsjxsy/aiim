/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.IStudentGradeInfoManageService;
import com.orifound.aiim.dal.dao.IStudentGradeInfoDao;
import com.orifound.aiim.bll.util.StringTool;
import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentGradeInfo;

/**
 * 学生成绩信息表管理服务实现类
 *
 */
public class StudentGradeInfoManageServiceImpl implements IStudentGradeInfoManageService {
	
	/**
	 * 注入学生成绩信息表的DAO
	 */
	@Autowired
	private IStudentGradeInfoDao studentGradeInfoDao;
	
	/**
	 * 构造函数
	 */
	public StudentGradeInfoManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public StudentGradeInfoManageServiceImpl(IStudentGradeInfoDao studentGradeInfoDao) {
		this.studentGradeInfoDao = studentGradeInfoDao;
	}
	
	/**
	 * 检查学生成绩信息的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForStudentGradeInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (studentGradeInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("学生成绩信息的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#deleteStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#findStudentGradeInfoByID(int, com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStudentGradeInfoByID(int pID,
			StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#findStudentGradeInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStudentGradeInfos(
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#saveStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStudentGradeInfoManageService#updateStudentGradeInfo(com.orifound.aiim.entity.StudentGradeInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStudentGradeInfo(StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findStudentAllGrade(String XH,
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测学号是否为空
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("学号非法为空。");
				}
			}

			//查询指定学生的所有课程成绩信息
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.findStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询指定学生的所有课程成绩信息 失败：");
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

		return pFlag;
	}

	@Override
	public boolean saveStudentAllGrade(String XH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测学号是否为空
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("学号非法为空。");
				}
			}
			
			//插入学生所有课程成绩信息
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.saveStudentAllGrade(XH, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "插入学生所有课程成绩信息 失败:");
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

		return pFlag;
	}

	@Override
	public boolean updateStudentAllGrade(String XH,
			List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测学号是否为空
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("学号非法为空。");
				}
			}

			//批量更新学生所有课程成绩信息
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.updateStudentAllGrade(XH, studentGradeInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "批量更新学生所有课程成绩信息 失败：");
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

		return pFlag;
	}

	@Override
	public boolean findAllElectivesCourseByName(String name, String XH, String ElectivesFlag, List<Course> courses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测公共选修课标志
			if (pFlag) {
				if (StringTool.checkNull(ElectivesFlag) == false) {
					pFlag = false;
					pErrInfo.getContent().append("公共选修课标志非法为空。");
				}
			}

			//查询公选课程信息
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.findAllElectivesCourseByName(name, XH, ElectivesFlag, courses, pErrInfo) == false) {
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
		return pFlag;
	}

	@Override
	public boolean saveElectivesCourse(String XH, String[] courseNames, List<StudentGradeInfo> electivesCourses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForStudentGradeInfo(pErrInfo) == false) {
				pFlag = false;
			}
		
			//检测学号
			if (pFlag) {
				if (StringTool.checkNull(XH) == false) {
					pFlag = false;
					pErrInfo.getContent().append("学号非法为空。");
				}
			}
			
			//检测公选课程名称数组
			if (pFlag) {
				if (courseNames==null || courseNames.length<=0) {
					pFlag = false;
					pErrInfo.getContent().append("公选课程名称数组非法为空。");
				}
			}

			//批量插入公选课程
			if (pFlag) {
				pErrPos = 2;
				if (studentGradeInfoDao.saveElectivesCourse(XH, courseNames, electivesCourses, pErrInfo) == false) {
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

		return pFlag;
	}
}