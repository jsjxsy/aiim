/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.CertificateStudent;
import com.orifound.aiim.entity.College;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Major;

/**
 * 出证学生信息表的DAO接口定义
 *
 */
public interface ICertificateStudentDao {

	/**
	 * Dao接口定义：添加出证学生信息
	 * @param certificateStudent 要添加的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的出证学生信息
	 * @param certificateStudent 要删除的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的出证学生信息
	 * @param certificateStudent 要更新的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(CertificateStudent certificateStudent, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的出证学生信息
	 * @param certificateStudents 返回查找成功的出证学生信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<CertificateStudent> certificateStudents, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找出证学生信息
	 * @param pID 指定的唯一标识
	 * @param certificateStudent 返回查找成功的出证学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(String pID, CertificateStudent certificateStudent, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找所有的专业信息
	 * @param majors 返回查找成功的专业信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllMajor(List<Major> majors, ErrInfo pErrInfo);
	
	/**
	 * Dao接口定义：查找所有的学院信息
	 * @param colleges 返回查找成功的学院信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllCollege(List<College> colleges, ErrInfo pErrInfo);
}