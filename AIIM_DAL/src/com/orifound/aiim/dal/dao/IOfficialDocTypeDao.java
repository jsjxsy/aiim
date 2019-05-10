/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.LinkedHashMap;

import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 公文文种字典表的DAO接口定义
 *
 */
public interface IOfficialDocTypeDao
{

	/**
	 * Dao接口定义：添加公文文种
	 * @param officialDocType 要添加的公文文种
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的公文文种
	 * @param officialDocType 要删除的公文文种
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的公文文种
	 * @param officialDocType 要更新的公文文种
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(OfficialDocType officialDocType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的公文文种
	 * @param officialDocTypes 返回查找成功的公文文种集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(LinkedHashMap<Integer,OfficialDocType> officialDocTypes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找公文文种
	 * @param pDocTypeID 指定的公文文种编号
	 * @param officialDocType 返回查找成功的公文文种
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pDocTypeID, OfficialDocType officialDocType, ErrInfo pErrInfo);

}
