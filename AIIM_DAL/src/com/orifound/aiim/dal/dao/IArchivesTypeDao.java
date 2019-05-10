/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_ArchivesType表的DAO接口定义
 *
 */
public interface IArchivesTypeDao {

	/**
	 * DAO接口定义：添加一种新的档案分类
	 * @param archivesType 要添加的档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：删除指定的档案分类
	 * @param archivesType 要删除的档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：更新指定的档案分类
	 * @param archivesType 要更新的档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(ArchivesType archivesType,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：查找所有的一级档案分类（一级类目）
	 * @param archivesTypes 返回查找成功的档案分类集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findForLevel1(LinkedHashMap<Integer,ArchivesType> archivesTypes,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：查找指定档案分类的所有下一级档案分类
	 * @param archivesTypeID 档案分类编号
	 * @param childArchivesTypes 返回查找成功的下一级档案分类集合，以档案分类编号作为关键字
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findForChild(int archivesTypeID, LinkedHashMap<Integer, ArchivesType> childArchivesTypes,ErrInfo pErrInfo);
	
	/**
	 * DAO接口定义：根据档案分类编号查找指定的档案分类信息
	 * @param pID 指定的档案分类编号
	 * @param archivesType 要删除的档案分类
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, ArchivesType archivesType,ErrInfo pErrInfo);
	
}
