package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.MoveOutInfo;

/**
 * 转出登记表DAO接口
 * @author Administrator
 *
 */
public interface IMoveOutRegisterDao {

	/**
	 * 添加转出但信息
	 * @param moveOutInfo 要添加的转出单 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addMoveOutInfo(MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * 根据公司名称查找转出单信息
	 * @param companyName 公司名称
	 * @param moveOutInfo 查询成功返回的转出单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMoveOutInfoByCompanyName(String companyName,ArchivesType archivesType, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * 更新当前转出当中档案总数量
	 * @param id 要更新的转出单的ID
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTotalArchives(int id, ErrInfo pErrInfo);

	/**
	 * 查询指定状态的指定转出方式的转出单信息
	 * @param moveOutWay 转出方式
	 * @param moveOutFlag 转出状态
	 * @param moveOutInfos 返回的转出单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMoveOutInfoByMoveOutWay(int moveOutWay, boolean moveOutFlag,ArchivesType archivesType,DataPageInfo dataPageInfo,int minNum ,int maxNum,List<MoveOutInfo> moveOutInfos, ErrInfo pErrInfo);

	/**
	 * 根据ID查找转出单信息
	 * @param id 转出单编号
	 * @param moveOutInfo 返回的转出单信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findMoveOutInfoById(int id, MoveOutInfo moveOutInfo, ErrInfo pErrInfo);

	/**
	 * 更新转出单转出方式
	 * @param id 转出单编号
	 * @param moveOutWay 转出方式
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateMoveOutWay(int id, int moveOutWay, ErrInfo pErrInfo);

	/**
	 * 更新转出单序列号
	 * @param id 转出单编号
	 * @param sN 转出单序列号
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateSN(int id, String sN, ErrInfo pErrInfo);
}
