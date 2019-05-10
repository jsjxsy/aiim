/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.MessageType;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 系统消息类型管理服务的接口定义
 *
 */
public interface IMessageTypeManageService {

	/**
	 * 添加一个新的系统消息类型
	 * @param pMessageType 新添加的系统消息类型信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean saveMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * 删除指定的系统消息类型
	 * @param pMessageType 要删除的系统消息类型，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * 修改指定的系统消息类型
	 * @param pMessageType 修改后的系统消息类型信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateMessageType(MessageType pMessageType, ErrInfo pErrInfo);

	/**
	 * 查找所有的系统消息类型信息
	 * @param pMessageTypes 返回查找成功的系统消息类型集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMessageTypes(List<MessageType> pMessageTypes, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找系统消息类型信息
	 * @param pID 指定的唯一标识
	 * @param pMessageType 返回查找成功的系统消息类型信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMessageTypeByID(int pID, MessageType pMessageType, ErrInfo pErrInfo);

}
