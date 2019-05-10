package com.orifound.aiim.dal.dao;

import java.util.*;
import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 区域表的DAO接口定义
 *
 */
public interface IAreaDao {

	/**
	 * Dao接口定义：查找所有的Area
	 * @param areas 返回查找成功的Area集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAllArea(List<Area> pAreas, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找Area
	 * @param pID 指定的唯一标识
	 * @param area 返回查找成功的Area
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findAreaByID(int pID, Area pArea, ErrInfo pErrInfo);

}
