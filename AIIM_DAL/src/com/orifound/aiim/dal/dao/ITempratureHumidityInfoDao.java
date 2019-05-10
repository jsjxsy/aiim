package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房温湿度登记信息表的DAO接口定义
 *
 */
public interface ITempratureHumidityInfoDao {

	/**
	 * Dao接口定义：添加库房温湿度登记信息
	 * @param tempratureHumidityInfo 要添加的库房温湿度登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean add(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：删除指定的库房温湿度登记信息
	 * @param tempratureHumidityInfo 要删除的库房温湿度登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean delete(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：更新指定的库房温湿度登记信息
	 * @param tempratureHumidityInfo 要更新的库房温湿度登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean update(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * 根据条件查询库房温湿度登记信息
	 * @param whereSQL 查询条件
	 * @param tempratureHumidityInfos 返回查找成功的库房温湿度登记信息
	 * @param dataPageInfo 用于分页的对象
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByCondition(String whereSQL, List<TempratureHumidityInfo> tempratureHumidityInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * Dao接口定义：根据唯一标识查找库房温湿度登记信息
	 * @param tempratureHumidityInfo 返回查找成功的库房温湿度登记信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

}
