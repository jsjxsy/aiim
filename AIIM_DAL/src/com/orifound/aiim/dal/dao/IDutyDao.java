/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 职务信息数据字典表的DAO接口定义
 *
 */
public interface IDutyDao {

	/**
	 * Dao接口定义：添加职务信息数据字典
	 * @param duty 要添加的职务信息数据字典
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的职务信息数据字典
	 * @param duty 要删除的职务信息数据字典
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的职务信息数据字典
	 * @param duty 要更新的职务信息数据字典
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(Duty duty, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的职务信息数据字典集合
	 * @param dutys 返回查找成功的职务信息数据字典集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<Duty> dutys, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找职务信息数据字典
	 * @param pID 指定的唯一标识
	 * @param duty 返回查找成功的职务信息数据字典
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, Duty duty, ErrInfo pErrInfo);

}
