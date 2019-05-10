package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TeacherInfo;

public interface ITeacherInfoDao {

	boolean add(List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);

	/**
	 * 按条件查询教职工档案信息
	 * @param teacherInfo 封装查询条件  其中工资号和姓名必须有值
	 * @param archivesType 档案分类对象
	 * @param dataPageInfo 分页对象
	 * @param teacherInfos 返回的档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTeacherInfos(TeacherInfo teacherInfo,ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);
	
	/**
	 * 查询所有待归档的教职工档案信息
	 * @param archivesType 档案分类对象
	 * @param dataPageInfo 分页对象
	 * @param teacherInfos 返回的档案信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTeacherInfosForArchiving(ArchivesType archivesType, DataPageInfo dataPageInfo,List<TeacherInfo> teacherInfos, ErrInfo pErrInfo);
	
	/**
	 * 将教职工信息添加到库房档案信息表中
	 * @param nbxh 要添加的档案内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addTeacherInfoToStoreroomArchivesInfo(int nbxh, ErrInfo pErrInfo);
	
	/**
	 * 更新教职工档案的工作流状态
	 * @param nbxh 要添加的档案内部序号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTeacherInfoStatus(int nbxh,int workFlowStatus , ErrInfo pErrInfo);
	/**
	 * 根据内部序号查询教职工档案信息
	 * @param teacherInfo 返回的教职工信息 也要作为查询条件NBXH的载体
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	/**
	 * 更新教职工档案信息
	 * @param teacherInfo 返回的教职工信息 也要作为查询条件NBXH的载体
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean add(TeacherInfo teacherInfo, ErrInfo pErrInfo);
}
