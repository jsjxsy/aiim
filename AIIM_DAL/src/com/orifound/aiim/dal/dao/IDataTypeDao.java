/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.*;

import com.orifound.aiim.entity.DataType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * DD_DataType表的DAO接口定义
 *
 */
public interface IDataTypeDao
{

	/**
	 * Dao接口定义：添加数据项字段类型
	 * @param dataType 要添加的数据项字段类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean save(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的数据项字段类型
	 * @param dataType 要删除的数据项字段类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean delete(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的数据项字段类型
	 * @param dataType 要更新的数据项字段类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean update(DataType dataType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的数据项字段类型
	 * @param dataTypes 返回查找成功的数据项字段类型集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findAll(List<DataType> dataTypes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找数据项字段类型
	 * @param pID 指定的唯一标识
	 * @param dataType 返回查找成功的数据项字段类型
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	public boolean findByID(int pID, DataType dataType, ErrInfo pErrInfo);

}
