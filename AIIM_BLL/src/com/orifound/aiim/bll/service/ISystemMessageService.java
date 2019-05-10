/**
 * 
 */
package com.orifound.aiim.bll.service;

import java.util.*;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.SystemMessage;
import com.orifound.aiim.entity.SystemMessageQueryCondition;

/**
 * 系统消息服务的接口定义
 *
 */
public interface ISystemMessageService {
	
	/**
	 * 查找发送给指定用户的所有系统消息（分页获取）
	 * @param userID 用户的编号数组
	 * @param dataPageInfo 数据分页显示信息
	 * @param systemMessages 返回查找成功后的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemMessages(int[] userID,DataPageInfo dataPageInfo, List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * 查找发送给指定用户的所有满足查询条件的系统消息（分页获取）
	 * @param userID 用户的编号数组
	 * @param systemMessageQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param systemMessages 返回查找成功后的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemMessages(int[] userID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo,List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * 阅读指定编号的系统消息<br>
	 * 返回指定编号的系统消息对象，同时将该消息的阅读标志设置为已读
	 * @param systemMessageID 指定的系统消息编号
	 * @param systemMessage 返回指定编号的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean readSystemMessage(int systemMessageID,SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * 发送系统消息
	 * @param systemMessage 要发送的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean sendSystemMessage(SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * 对指定的用户角色群发系统消息<br>
	 * 属于该角色的用户都将收到该系统消息
	 * @param userRolesID 指定的角色编号数组
	 * @param systemMessage 要发送的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean sendSystemMessage(int[] userRolesID, SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * 发布公告<br>
	 * 对所有当前的系统用户都发送一条系统消息，消息内容就是公告内容。
	 * @param systemMessage 公告内容
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean givePublicNotices(SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * 删除指定的系统消息
	 * @param systemMessages 要删除的系统消息集合，其消息成员的编号属性必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteSystemMessage(List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * 清空指定用户的所有系统消息
	 * @param userID 用户的编号数组
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean clearSystemMessages(int[] userID,ErrInfo pErrInfo);
	
	/**
	 * 统计指定用户的未读消息数量
	 * @param userID
	 * @return 返回未读消息的总数量
	 */
	int countUnread(int[] userID);
	
	/**
	 * 查找发送给指定用户的所有满足查询条件的系统消息（分页获取）
	 * @param pUserID 用户的编号
	 * @param systemMessageQueryCondition 查询条件
	 * @param dataPageInfo 数据分页显示信息
	 * @param systemMessages 返回查找成功后的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemMessagesByUserID(int pUserID,SystemMessageQueryCondition systemMessageQueryCondition,DataPageInfo dataPageInfo,List<SystemMessage> systemMessages,ErrInfo pErrInfo);
	
	/**
	 * 根据系统消息唯一编号查找系统消息
	 * @param pID 消息编号
	 * @param systemMessage 返回查找成功后的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findSystemMessageByID(int pID,SystemMessage systemMessage,ErrInfo pErrInfo);
	
	/**
	 * 根据ID号更新系统消息字段
	 * @param systemMessage 返回查找成功后的系统消息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateSystemMessage(SystemMessage systemMessage,ErrInfo pErrInfo);
}
