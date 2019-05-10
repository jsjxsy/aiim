package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentInfo;

/**
 * 学生信息表DAO接口
 * @author Administrator
 *
 */
public interface IStudentInfoDao {

	/**
	 * 批量添加学生
	 * @param studentInfos 要添加的学生，去其importDataitemsMappings
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * 查找所有的学生信息，分页显示
	 * @param xyName 学院名称
	 * @param dataPageInfo 分页对象
	 * @param studentInfos 返回的学生信息对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean find(String xyName, EnumWorkFlowStatus enumWorkFlowStatus, DataPageInfo dataPageInfo, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * 按照内部序号查找学生档案信息
	 * @param nbxh
	 * @param studentInfo
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * 更新档案工作流状态
	 * @param nbxh 要更新的档案内部序号
	 * @param enumWorkFlowStatus 档案工作流状态
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateWorkFlowStatus(int nbxh, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);
	
	/**
	 * 将学生信息添加到库房档案信息表中
	 * @param nbxh 要添加的档案内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addStudentInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo); 

	/**
	 * 找出学生所在的学院
	 * @param collegeNames
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findCollege(List<String> collegeNames, ErrInfo pErrInfo);

	/**
	 * 更新学生档案的转出信息
	 * @param studentInfo 要更新的学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateMoveOutInfo(StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * 根据转出单编号查找学生信息
	 * @param moveOutId 转出但编号
	 * @param studentInfos 查询返回的学生信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentInfoByMoveOutId(int moveOutId, List<StudentInfo> studentInfos, ErrInfo pErrInfo);

	/**
	 * 根据转出单更新档案工作流状态
	 * @param moveOutRegID 转出单编号 
	 * @param enumWorkFlowStatus 工作流状态
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateWorkFlowStatusByMoveOutRegID(int moveOutRegID, EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo);

	/**
	 * 查询学生档案信息
	 * @param studentInfo 要查询的学生档案信息的条件
	 * @param enumWorkFlowStatus 指定要查询那些状态的学生档案信息
	 * @param studentInfos 返回的学生档案信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findStudentInfos(StudentInfo studentInfo,List<EnumWorkFlowStatus> enumWorkFlowStatus,List<StudentInfo> studentInfos, ErrInfo pErrInfo);
}
