package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.Excel;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.TeacherDocsInfo;
import com.orifound.aiim.entity.TeacherDocsType;
import com.orifound.aiim.entity.TeacherInfo;

public interface ITeacherFileManagService {

	/**
	 * 导入教职工档案信息
	 * @param excel 要导入的Excel文件对象
	 * @param importType 导入类型
	 * @param archivesType 所属档案类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean importTeacherInfo(Excel excel, int importType, ArchivesType archivesType, IntegerEx sum, ErrInfo pErrInfo);

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
	 * 根据内部序号查询教职工档案信息
	 * @param teacherInfo 返回的教职工信息 也要作为查询条件NBXH的载体
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTeacherInfoByNBXH(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	/**
	 * 查询所有的档案材料分类信息
	 * @param teacherDocsTypes
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsType(List<TeacherDocsType> teacherDocsTypes,ErrInfo pErrInfo);

	/**
	 * 向教职工档案信息中添加卷内文件
	 * @param nbxh
	 * @param teacherDocsInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean addDoc(int nbxh, TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	/**
	 * 根据内部序号查找卷内材料信息
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsInfoByNBXH(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo);

	/**
	 * 删除卷内材料信息
	 * @param docIds
	 * @param pErrInfo
	 * @return
	 */
	boolean delDoc(int[] docIds, ErrInfo pErrInfo);

	boolean addTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean updateTeacherInfo(TeacherInfo teacherInfo, ErrInfo pErrInfo);

	boolean findTeacherDocById(TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

	boolean updateTeacherDocInfo(TeacherDocsInfo teacherDocsInfo,ErrInfo pErrInfo);
	
	boolean setMoveOut(int nbxh, ErrInfo pErrInfo);
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
	 * 根据内部序号查找卷内材料信息打印
	 * @param nbxh
	 * @param teacherDocsInfos
	 * @param pErrInfo
	 * @return
	 */
	boolean findTeacherDocsInfoByNBXHForPrint(int nbxh, List<TeacherDocsInfo> teacherDocsInfos, ErrInfo pErrInfo);

	boolean batAddTeacherDocsInfo(List<String>  gzhList,TeacherDocsInfo teacherDocsInfo, ErrInfo pErrInfo);

}
