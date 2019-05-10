/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 档案分类信息管理服务的接口定义
 *
 */
public interface IArchivesTypeManageService
{

	/**
	 * 添加一个新的档案分类信息
	 * @param archivesType 新添加的档案分类信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * 删除指定的档案分类信息
	 * @param archivesType 要删除的档案分类信息，其档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * 修改指定的档案分类信息
	 * @param archivesType 修改后的档案分类信息信息，其档案分类编号字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateArchivesType(ArchivesType archivesType, ErrInfo pErrInfo);

	/**
	 * 查找所有的档案分类信息<br>
	 * 返回的是有层次结构的档案分类信息，一级类目，二级类目等都构造好了，可以通过childArchivesTypes属性访问
	 * @param archivesTypes 返回查找成功的顶层档案分类信息集合（一级类目）
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesTypes(LinkedHashMap<Integer,ArchivesType> archivesTypes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找档案分类信息信息
	 * @param pID 指定的唯一标识
	 * @param archivesType 返回查找成功的档案分类信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findArchivesTypeByID(int pID, ArchivesType archivesType, ErrInfo pErrInfo);
	
}
