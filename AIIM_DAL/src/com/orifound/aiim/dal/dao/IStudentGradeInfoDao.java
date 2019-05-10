/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.StudentGradeInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 学生成绩信息表的DAO接口定义
 *
 */
public interface IStudentGradeInfoDao {

	/**
	 * Dao接口定义：添加学生成绩信息
	 * @param studentGradeInfo 要添加的学生成绩信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的学生成绩信息
	 * @param studentGradeInfo 要删除的学生成绩信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的学生成绩信息
	 * @param studentGradeInfo 要更新的学生成绩信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的学生成绩信息
	 * @param studentGradeInfos 返回查找成功的学生成绩信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找学生成绩信息
	 * @param pID 指定的唯一标识
	 * @param studentGradeInfo 返回查找成功的学生成绩信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);
	
	/**
	 * 插入学生所有课程成绩信息
	 * @param XH	学号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveStudentAllGrade(String XH, ErrInfo pErrInfo);
	
	/**
	 * 查询指定学生的所有课程成绩信息
	 * @param XH	学号
	 * @param studentGradeInfos	返回查找成功的学生成绩信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentAllGrade(String XH, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);
	
	/**
	 * 批量更新学生所有课程成绩信息
	 * @param XH	学号
	 * @param studentGradeInfos	批量更新的学生成绩信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStudentAllGrade(String XH, List<StudentGradeInfo> studentGradeInfos, ErrInfo pErrInfo);

	/**
	 * 查询公选课程信息(过滤已经选择的公选课)
	 * @param name 课程名称
	 * @param XH 过滤学生已经选择的公选课
	 *  @param ElectivesFlag 公共选修课标志
	 * @param courses	返回查找成功的课程信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllElectivesCourseByName(String name, String XH, String ElectivesFlag, List<Course> courses, ErrInfo pErrInfo);
	
	/**
	 * 批量插入公选课程
	 * @param XH 学号
	 * @param courseNames 课程名称数组
	 * @param electivesCourses 返回插入成功的公选课程集合信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveElectivesCourse(String XH, String[] courseNames, List<StudentGradeInfo> electivesCourses, ErrInfo pErrInfo);
}
