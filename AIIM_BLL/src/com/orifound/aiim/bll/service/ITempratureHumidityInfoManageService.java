package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * 库房温度湿度信息管理服务的接口定义
 *
 */
public interface ITempratureHumidityInfoManageService {

	/**
	 * 添加一个新的库房温度湿度信息
	 * @param tempratureHumidityInfo 新添加的库房温度湿度信息信息
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean addTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * 删除指定的库房温度湿度信息
	 * @param tempratureHumidityInfo 要删除的库房温度湿度信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean deleteTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * 修改指定的库房温度湿度信息
	 * @param tempratureHumidityInfo 修改后的库房温度湿度信息信息，其唯一标识字段必须赋值
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean updateTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * 查找所有的库房温度湿度信息信息
	 * @param tempratureHumidityInfos 返回查找成功的库房温度湿度信息集合
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTempratureHumidityInfos(List<TempratureHumidityInfo> tempratureHumidityInfos, ErrInfo pErrInfo);

	/**
	 * 根据唯一标识查找库房温度湿度信息信息
	 * @param pID 指定的唯一标识
	 * @param tempratureHumidityInfo 返回查找成功的库房温度湿度信息信息 
	 * @param pErrInfo 返回处理失败的错误信息
	 * @return 处理成功返回true，否则返回false
	 */
	boolean findTempratureHumidityInfoByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);
	
	
	/**
	 * 根据条件查找库房温度湿度信息信息集合
	 * @param whereSQL
	 * @param tempratureHumidityInfos
	 * @param dataPageInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findTempratureHumidityInfosByCondition(String whereSQL,List<TempratureHumidityInfo> tempratureHumidityInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);
	

}
