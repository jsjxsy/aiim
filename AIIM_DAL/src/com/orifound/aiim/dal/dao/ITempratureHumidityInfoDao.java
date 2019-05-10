package com.orifound.aiim.dal.dao;

import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ��ʪ�ȵǼ���Ϣ���DAO�ӿڶ���
 *
 */
public interface ITempratureHumidityInfoDao {

	/**
	 * Dao�ӿڶ��壺��ӿⷿ��ʪ�ȵǼ���Ϣ
	 * @param tempratureHumidityInfo Ҫ��ӵĿⷿ��ʪ�ȵǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean add(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺ɾ��ָ���Ŀⷿ��ʪ�ȵǼ���Ϣ
	 * @param tempratureHumidityInfo Ҫɾ���Ŀⷿ��ʪ�ȵǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean delete(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����ָ���Ŀⷿ��ʪ�ȵǼ���Ϣ
	 * @param tempratureHumidityInfo Ҫ���µĿⷿ��ʪ�ȵǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean update(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

	/**
	 * ����������ѯ�ⷿ��ʪ�ȵǼ���Ϣ
	 * @param whereSQL ��ѯ����
	 * @param tempratureHumidityInfos ���ز��ҳɹ��Ŀⷿ��ʪ�ȵǼ���Ϣ
	 * @param dataPageInfo ���ڷ�ҳ�Ķ���
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByCondition(String whereSQL, List<TempratureHumidityInfo> tempratureHumidityInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * Dao�ӿڶ��壺����Ψһ��ʶ���ҿⷿ��ʪ�ȵǼ���Ϣ
	 * @param tempratureHumidityInfo ���ز��ҳɹ��Ŀⷿ��ʪ�ȵǼ���Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo);

}
