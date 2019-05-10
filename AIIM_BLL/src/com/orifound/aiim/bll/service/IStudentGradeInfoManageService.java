/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.Course;
import com.orifound.aiim.entity.StudentGradeInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 学生成绩信息表管理服务的接口定义
 *
 */
public interface IStudentGradeInfoManageService {

	/**
	 * 添加一个新的学生成绩信息表
	 * @param studentGradeInfo 新添加的学生成绩信息表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的学生成绩信息表
	 * @param studentGradeInfo 要删除的学生成绩信息表，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的学生成绩信息表
	 * @param studentGradeInfo 修改后的学生成绩信息表信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateStudentGradeInfo(StudentGradeInfo studentGradeInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的学生成绩信息表信息
	 * @param studentGradeInfos 返回查找成功的学生成绩信息表集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentGradeInfos(List<StudentGradeInfo> studentGradeInfos,
			ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找学生成绩信息表信息
	 * @param pID 指定的唯一标识
	 * @param studentGradeInfo 返回查找成功的学生成绩信息表信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentGradeInfoByID(int pID, StudentGradeInfo studentGradeInfo,
			ErrInfo pErrInfo);
	
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