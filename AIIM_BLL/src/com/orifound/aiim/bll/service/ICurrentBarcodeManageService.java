package com.orifound.aiim.bll.service;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ��������Ϣ�������Ľӿڶ���
 *
 */
public interface ICurrentBarcodeManageService {

	
	/**
	 * �޸�ָ������������Ϣ
	 * @param currentBarcode �޸ĺ����������Ϣ��Ϣ����Ψһ��ʶ�ֶα��븳ֵ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean updateCurrentBarcode(CurrentBarcode currentBarcode, ErrInfo pErrInfo);

	
	/**
	 * ����Ψһ��ʶ������������Ϣ��Ϣ
	 * @param pID ָ����Ψһ��ʶ
	 * @param currentBarcode ���ز��ҳɹ�����������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean findCurrentBarcodeByBarcodeType(CurrentBarcode currentBarcode,
			ErrInfo pErrInfo);
	
	/**
	 * ��ӡָ�����ͺ�������������
	 * @param pID ָ����Ψһ��ʶ
	 * @param currentBarcode ���ز��ҳɹ�����������Ϣ��Ϣ
	 * @param pErrInfo ���ش���ʧ�ܵĴ�����Ϣ
	 * @return ����ɹ�����true�����򷵻�false
	 */
	boolean printBarcode(int num,CurrentBarcode currentBarcode,
			ErrInfo pErrInfo);

}
