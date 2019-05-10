package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IStoreAddressInfoManageService;
import com.orifound.aiim.dal.dao.IStoreAddressInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreAddressInfo;
/**
 * �����ϼ�λ����Ϣ��������ʵ����
 * @author Administrator
 *
 */
public class StoreAddressInfoManageServiceImpl implements IStoreAddressInfoManageService {
	/**
	 * ���캯��
	 */
	public StoreAddressInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public StoreAddressInfoManageServiceImpl(IStoreAddressInfoDao storeAddressInfoDao) {
		this.storeAddressInfoDao = storeAddressInfoDao;
	}

	/**
	 * �����ϼ�λ����Ϣ���DAO�ӿڶ���
	 */
	private IStoreAddressInfoDao storeAddressInfoDao;	
	
	public IStoreAddressInfoDao getStoreAddressInfoDao() {
		return storeAddressInfoDao;
	}

	public void setStoreAddressInfoDao(IStoreAddressInfoDao storeAddressInfoDao) {
		this.storeAddressInfoDao = storeAddressInfoDao;
	}
	
	/**
	 * ���ҵ�����������ݷ������Ƿ�ע��ɹ���DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (storeAddressInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("Entity��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return pFlag;
	}

	@Override
	public boolean addStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findStoreAddressInfoByBoxBarcode(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (storeAddressInfoDao.findByBoxBarcode(storeAddressInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݺ���������ҵ����ϼ�λ����Ϣʧ��: ");
				}else{
					System.out.println("findByBoxBarcode: fullName-->"+storeAddressInfo.getStoreAddressFullName());
				}
			}
		} catch (Exception e) {
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
				System.out.println("error:"+pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean findStoreAddressInfos(List<StoreAddressInfo> storeAddressInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStoreAddressInfo(StoreAddressInfo storeAddressInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
