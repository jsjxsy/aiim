/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * 出证学生信息管理服务的接口定义
 *
 */
public interface ICertificateStudentManageService {

	/**
	 * 添加一个新的出证学生信息
	 * @param certificateStudent 新添加的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * 删除指定的出证学生信息
	 * @param certificateStudent 要删除的出证学生信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * 修改指定的出证学生信息
	 * @param certificateStudent 修改后的出证学生信息，其唯一标识字段必须赋值
	 * @param archivesCertificateInfo 关联的出证信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateCertificateStudent(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * 查找所有的出证学生信息
	 * @param certificateStudents 返回查找成功的出证学生信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCertificateStudents(List<CertificateStudent> certificateStudents,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找出证学生信息
	 * @param pID 指定的唯一标识
	 * @param certificateStudent 返回查找成功的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCertificateStudentByID(String pID, CertificateStudent certificateStudent,
			ErrInfo pErrInfo);

	
	/**
	 * 查找所有的专业信息
	 * @param majors 返回查找成功的专业信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo);
	
	/**
	 * 查找所有的学院信息
	 * @param colleges 返回查找成功的学院信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo);
}