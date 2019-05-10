package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StudentInfo;

public interface IStudentDocsInfoDao {

	/**
	 *  根据内部序号查找学生档案卷内文件
	 * @param nbxh 学生档案内部序号
	 * @param studentInfo 返回的学生档案和卷内文件信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findDocByNBXH(int nbxh, StudentInfo studentInfo, ErrInfo pErrInfo);

	/**
	 * 更新卷内文件存在状态
	 * @param ids
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateExistsFlag(List<Integer> ids, ErrInfo pErrInfo);
	
}
