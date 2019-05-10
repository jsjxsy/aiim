/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.orifound.aiim.bll.service.ICertificateStudentManageService;
import com.orifound.aiim.dal.dao.ICertificateStudentDao;
import com.orifound.aiim.dal.util.StringTool;
import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * 出证学生信息管理服务实现类
 *
 */
public class CertificateStudentManageServiceImpl implements ICertificateStudentManageService {

	/**
	 * 注入出证学生信息表的DAO
	 */
	@Autowired
	private ICertificateStudentDao certificateStudentDao;
	
	/**
	 * 构造函数
	 */
	public CertificateStudentManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public CertificateStudentManageServiceImpl(ICertificateStudentDao certificateStudentDao) {
		this.certificateStudentDao = certificateStudentDao;
	}
	
	/**
	 * 检查学生信息表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForCertificateStudent(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (certificateStudentDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("学生信息表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#deleteCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#findCertificateStudentByID(int, com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateStudentByID(String pID,
			CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (StringTool.checkNull(pID) == false) {
					pFlag = false;
					pErrInfo.getContent().append("唯一标识非法为空。");
				}
			}
			
			//根据唯一标识查找出证学生信息
			if (pFlag) {
				if (certificateStudentDao.findByID(pID, certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "根据唯一标识查找出证学生信息 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#findCertificateStudents(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findCertificateStudents(
			List<CertificateStudent> certificateStudents, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#saveCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveCertificateStudent(
			CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测学生信息
			if (pFlag) {
				if (certificateStudent==null || StringTool.checkNull(certificateStudent.getXH())==false) {
					pFlag = false;
					pErrInfo.getContent().append("学生信息非法为空。");
				}
			}

			//添加出证学生信息
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.save(certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "添加出证学生信息 失败：");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.ICertificateStudentManageService#updateCertificateStudent(com.orifound.aiim.entity.CertificateStudent, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}
			
			//检测学生信息
			if (pFlag) {
				if (certificateStudent==null || StringTool.checkNull(certificateStudent.getXH())==false) {
					pFlag = false;
					pErrInfo.getContent().append("学生信息非法为空。");
				}
			}

			//更新指定的出证学生信息
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.update(certificateStudent, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "更新指定的出证学生信息 失败：");
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
	public boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			//查找所有的学院信息
			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.findAllCollege(colleges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的学院信息 失败：");
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
	public boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//检查是否有进行DAO依赖注入
			if (checkDaoInjectionForCertificateStudent(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				pErrPos = 2;
				if (certificateStudentDao.findAllMajor(majors, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的专业信息 失败：");
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