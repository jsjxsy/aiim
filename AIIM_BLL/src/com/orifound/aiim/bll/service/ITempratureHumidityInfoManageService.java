package com.orifound.aiim.bll.service;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�¶�ʪ����Ϣ�������Ľӿڶ���
 *
 */
public interface ITempratureHumidityInfoManageService {

	/**
	 * ���һ���µĿⷿ�¶�ʪ����Ϣ
	 * @param tempratureHumidityInfo ����ӵĿⷿ�¶�ʪ����Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ŀⷿ�¶�ʪ����Ϣ
	 * @param tempratureHumidityInfo Ҫɾ���Ŀⷿ�¶�ʪ����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ŀⷿ�¶�ʪ����Ϣ
	 * @param tempratureHumidityInfo �޸ĺ�Ŀⷿ�¶�ʪ����Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateTempratureHumidityInfo(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * �������еĿⷿ�¶�ʪ����Ϣ��Ϣ
	 * @param tempratureHumidityInfos ���ز��ҳɹ��Ŀⷿ�¶�ʪ����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTempratureHumidityInfos(List<TempratureHumidityInfo> tempratureHumidityInfos, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҿⷿ�¶�ʪ����Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param tempratureHumidityInfo ���ز��ҳɹ��Ŀⷿ�¶�ʪ����Ϣ��Ϣ 
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findTempratureHumidityInfoByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);
	
	
	/**
	 * �����������ҿⷿ�¶�ʪ����Ϣ��Ϣ����
	 * @param whereSQL
	 * @param tempratureHumidityInfos
	 * @param dataPageInfo
	 * @param pErrInfo
	 * @return
	 */
	boolean findTempratureHumidityInfosByCondition(String whereSQL,List<TempratureHumidityInfo> tempratureHumidityInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);
	

}
