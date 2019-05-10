/**
 * 
 */
package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.MessageType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 系统消息类型表的DAO接口定义
 *
 */
public interface IMessageTypeDao {

	/**
	 * Dao接口定义：添加MessageType
	 * @param pMessageType 要添加的MessageType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean save(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的MessageType
	 * @param pMessageType 要删除的MessageType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的MessageType
	 * @param pMessageType 要更新的MessageType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：查找所有的MessageType
	 * @param pMessageTypes 返回查找成功的MessageType集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAll(List<MessageType> pMessageTypes, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找MessageType
	 * @param pID 指定的唯一标识
	 * @param pMessageType 返回查找成功的MessageType
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(int pID, MessageType pMessageType, ErrInfo pErrInfo);

}
