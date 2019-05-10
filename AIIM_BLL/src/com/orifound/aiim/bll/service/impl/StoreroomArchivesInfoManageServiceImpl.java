/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;

/**
 * �ⷿ�����������ӿ�ʵ����
 * @author Administrator
 *
 */
public class StoreroomArchivesInfoManageServiceImpl implements IStoreroomArchivesInfoManageService {
	
	/**
	 * ���캯��
	 */
	public StoreroomArchivesInfoManageServiceImpl() {

	}
	
	/**
	 * �ⷿ������Ϣ������ݷ��ʶ���
	 */
	private IStoreroomArchivesInfoDao storeroomArchivesInfoDao = null;

	/**
	 * ��ȡ����ֵ���ⷿ������Ϣ������ݷ��ʶ���
	 * @return �ⷿ������Ϣ������ݷ��ʶ���
	 */
	public IStoreroomArchivesInfoDao getStoreroomArchivesInfoDao() {
		return storeroomArchivesInfoDao;
	}

	/**
	 * ��������ֵ���ⷿ������Ϣ������ݷ��ʶ���
	 * @param storeroomArchivesInfoDao �ⷿ������Ϣ������ݷ��ʶ���
	 */
	public void setStoreroomArchivesInfoDao(IStoreroomArchivesInfoDao storeroomArchivesInfoDao) {
		this.storeroomArchivesInfoDao = storeroomArchivesInfoDao;
	}
	
	/**
	 * ���ⷿ������Ϣ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (storeroomArchivesInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ������Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#delete(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#findByNBXH(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				if (storeroomArchivesInfoDao.findByBarcode(storeroomArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͨ�������������ѯ�ⷿ������Ϣʧ��: ");
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
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	
	
	
	

	@Override
	public boolean findStoreroomArchivesInfoByNBXH(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				if (storeroomArchivesInfoDao.findByNBXH(storeroomArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͨ��NBXH��ѯ�ⷿ������Ϣʧ��: ");
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
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#save(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#update(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomArchivesInfoManageService#updateStoreStatus(com.orifound.aiim.entity.StoreroomArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStoreStatusByArchivesBarcode(StoreroomArchivesInfo storeroomArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				if (storeroomArchivesInfoDao.updateStoreStatusByArchivesBarcode(storeroomArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���¿ⷿ������Ϣ�Ĺݲ�״̬ʧ��: ");
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
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	@Override
	public boolean updateArchivesBoxBarcode(List<String> archivesBarcodes, String archivesBoxBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (storeroomArchivesInfoDao.updateArchivesBoxBarcode(archivesBarcodes, archivesBoxBarcode, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���¿ⷿ������Ϣ�ĺ�����ʧ��: ");
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
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findByBoxBarcode(String archivesBoxBarcode, List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (storeroomArchivesInfoDao.findByBoxBarcode(archivesBoxBarcode, storeroomArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݺ�������ҵ�����Ϣʧ��: ");
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
			}
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

}
