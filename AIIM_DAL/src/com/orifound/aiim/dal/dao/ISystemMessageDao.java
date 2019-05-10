/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemMessageQueryCondition;

/**
 * 系统消息表的DAO接口定义
 *
 */
public interface ISystemMessageDao {

	/**
	 * Dao接口定义：添加系统消息
	 * @param pSystemMessage 要添加的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的系统消息
	 * @param pSystemMessage 要删除的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的系统消息
	 * @param pSystemMessage 要更新的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(SystemMessage pSystemMessage, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的系统消息
	 * @param pSystemMessages 返回查找成功的系统消息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<SystemMessage> pSystemMessages, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找系统消息
	 * @param pID 指定的唯一标识
	 * @param pSystemMessage 返回查找成功的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, SystemMessage pSystemMessage, ErrInfo pErrInfo);
	
	/**
	 * 根据用户编号查询系统消息集合
	 * @param pUserID 用户编号
	 * @param systemMessageQueryCondition 系统消息查询条件
	 * @param dataPageInfo 分页集合
	 * @param systemMessages 返回查找成功的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByUserID(int pUserID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,ErrInfo pErrInfo);

}
