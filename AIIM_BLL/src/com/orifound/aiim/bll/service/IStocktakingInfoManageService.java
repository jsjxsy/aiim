package com.orifound.aiim.bll.service;
import java.util.List;

import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.StocktakingInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * �ⷿ�̵㹤����Ϣ�������Ľӿڶ���
 *
 */
public interface IStocktakingInfoManageService {

	/**
	 * ���һ���µĿⷿ�̵㹤����Ϣ
	 * @param stocktakingInfo ����ӵĿⷿ�̵㹤����Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean addStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * ɾ��ָ���Ŀⷿ�̵㹤����Ϣ
	 * @param stocktakingInfo Ҫɾ���Ŀⷿ�̵㹤����Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean deleteStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * �޸�ָ���Ŀⷿ�̵㹤�����̵�״̬����"δ�̵�"����Ϊ"���̵�"
	 * @param stocktakingInfo �޸ĺ�Ŀⷿ�̵㹤����Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateStocktakingInfo(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

	/**
	 * �������еĿⷿ�̵㹤����Ϣ��Ϣ
	 * @param stocktakingInfos ���ز��ҳɹ��Ŀⷿ�̵㹤����Ϣ����
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStocktakingInfos(List<StocktakingInfo> stocktakingInfos,DataPageInfo dataPageInfo, ErrInfo pErrInfo);

	/**
	 * ����Ψһ��ʶ���ҿⷿ�̵㹤����Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param stocktakingInfo ���ز��ҳɹ��Ŀⷿ�̵㹤����Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findStocktakingInfoByID(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo);

}
