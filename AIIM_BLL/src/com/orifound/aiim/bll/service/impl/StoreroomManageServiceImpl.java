/**
 * 
 */
package com.orifound.aiim.bll.service.impl;
import java.util.Date;
import java.util.List;

import com.orifound.aiim.bll.service.IStoreAddressInfoManageService;
import com.orifound.aiim.bll.service.IStoreroomManageService;
import com.orifound.aiim.dal.dao.IStoreAddressInfoDao;
import com.orifound.aiim.dal.dao.IStoreroomArchivesInfoDao;
import com.orifound.aiim.dal.dao.IStoreroomStructureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreAddressInfo;
import com.orifound.aiim.entity.StoreroomArchivesInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * �ⷿ�������ʵ����
 *
 */
public class StoreroomManageServiceImpl implements IStoreroomManageService {
	
	/**
	 * ���캯��
	 */
	public StoreroomManageServiceImpl() {
	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public StoreroomManageServiceImpl(IStoreroomStructureDao storeroomStructureDao) {
		this.storeroomStructureDao = storeroomStructureDao;
	}
	
	/**
	 * �����ϼ�λ����Ϣ���DAO�ӿ�
	 */
	IStoreAddressInfoDao storeAddressInfoDao = null;

	public IStoreAddressInfoDao getStoreAddressInfoDao() {
		return storeAddressInfoDao;
	}

	public void setStoreAddressInfoDao(IStoreAddressInfoDao storeAddressInfoDao) {
		this.storeAddressInfoDao = storeAddressInfoDao;
	}

	/**
	 * �ⷿ������Ϣ�� ��DAO�ӿ�<br/>
	 * ���ע��
	 */
	IStoreroomArchivesInfoDao storeroomArchivesInfoDao = null;
	
	public IStoreroomArchivesInfoDao getStoreroomArchivesInfoDao() {
		return storeroomArchivesInfoDao;
	}

	public void setStoreroomArchivesInfoDao(IStoreroomArchivesInfoDao storeroomArchivesInfoDao) {
		this.storeroomArchivesInfoDao = storeroomArchivesInfoDao;
	}

	/**
	 * �ⷿ�ṹ��Ϣ���DAO�ӿ�
	 * ���ע��
	 */
	IStoreroomStructureDao storeroomStructureDao = null;
	
	public IStoreroomStructureDao getStoreroomStructureDao() {
		return storeroomStructureDao;
	}

	public void setStoreroomStructureDao(IStoreroomStructureDao storeroomStructureDao) {
		this.storeroomStructureDao = storeroomStructureDao;
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
			if (storeroomStructureDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ�ṹ��Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			if (storeroomArchivesInfoDao==null) {
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ������Ϣ�� ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomArchivesInfos(com.orifound.aiim.entity.StoreroomArchivesInfoQueryCondition, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomArchivesInfosByCondition(
			String whereSQL,
			List<StoreroomArchivesInfo> storeroomArchivesInfos, ErrInfo pErrInfo) {
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
				if (storeroomArchivesInfoDao.findByCondition(whereSQL, storeroomArchivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݲ�ѯ������ѯ�ⷿ������Ϣʧ��: ");
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
System.out.println("error:" + pErrInfo.toString());
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomStructureByID(int, com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomStructureByID(int storeroomStructureID,
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findByID(storeroomStructure, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݿⷿ�ṹ��Ų��ҿⷿ�ṹ��ϸ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStoreroomStructureChildrenByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStoreroomStructureChildrenByID(int storeroomStructureID,
			List<StoreroomStructure> children, ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findChildrenByID(storeroomStructureID, children, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݿⷿ�ṹ��Ų������¼��ⷿ�ṹʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#findStorerooms(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStorerooms(List<StoreroomStructure> storeRooms,ErrInfo pErrInfo) {
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
				if (storeroomStructureDao.findStorerooms(storeRooms, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������еĿⷿ��Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#registerStoreAddress(java.lang.String, java.lang.String, com.orifound.aiim.entity.StoreAddressInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean registerStoreAddress(String archivesBoxBarcode,String storeAddressBarcode,ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StoreAddressInfo storeAddressInfo = new StoreAddressInfo();
	
		/**
		 * 1�������豸�������ѯ�ⷿ�ṹ��Ϣ
		 * 2�������������Ƿ��Ѿ��������ϼ�λ����Ϣ����
		 * 3�������Ϻ�����������������ִ�в��뻹�Ǹ��²���
		 */
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			StoreroomStructure storeroomStructure = new StoreroomStructure();
			storeroomStructure.setBarcode(storeAddressBarcode);
			//�����������ȡ�ⷿ�ṹ��Ϣ,��������ϼ�λ����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (storeroomStructureDao.findByBarcode(storeroomStructure, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݿⷿ�豸��������ҿⷿ�ṹ��ϸ��Ϣʧ��: ");
				}else{
					if(storeroomStructure.getID()==0){//û���ҵ��豸������
						pFlag = false;
						pErrInfo.getContent().append("�����豸������û���ҵ���Ӧ���豸��Ϣ���豸�����룺"+storeAddressBarcode);
					}else{//�ҵ��豸�����룬�����ϼ�λ����Ϣ
						storeAddressInfo.setArchivesBoxBarcode(archivesBoxBarcode);
						storeAddressInfo.setPutTime(new Date());
						storeAddressInfo.setStoreAddressFullName(storeroomStructure.getFullName());
						storeAddressInfo.setStoreAddressID(storeroomStructure.getID());
					}
				}
			}
			
			//�����������Ƿ����,�����ݴ������ִ����Ӧ�Ĳ���
			if(pFlag){
				pErrPos = 3;
				if(storeAddressInfoDao.checkBoxBarcodeExist(archivesBoxBarcode, pErrInfo)==true){//�������,ִ�и��²��� 
					//�����ϼ�λ����Ϣ
					if(storeAddressInfoDao.update(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("����ָ���ĵ����ϼ�λ����Ϣʧ�ܣ���������Ϊ��"+archivesBoxBarcode);
					}
				}else{//�����ڣ�ִ�в������
					//�����ϼ�λ����Ϣ
					if(storeAddressInfoDao.add(storeAddressInfo, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("��ӵ����ϼ�λ����Ϣʧ�ܣ���������Ϊ��"+archivesBoxBarcode);
					}
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
	 * @see com.orifound.aiim.bll.service.IStoreroomManageService#updateStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStoreroomStructure(
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStoreroomStructureUsedSizeByBarcode(String storeAddressBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StoreroomStructure storeroomStructure = null;

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			/**
			 * 0�������豸�������ȡ�豸��Ϣ
			 * 1��������ײ��豸���ÿռ�
			 * 2��ѭ���������ϲ��豸���ÿռ�
			 */
			
			//��ȡ�ⷿ�豸��Ϣ
			if (pFlag) {
				pErrPos = 2;
				storeroomStructure = new StoreroomStructure();
				storeroomStructure.setBarcode(storeAddressBarcode);
				if (storeroomStructureDao.findByBarcode(storeroomStructure, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���ݿⷿ�豸��������ҿⷿ�ṹ��ϸ��Ϣ");
				}
			}

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 3;
				if (storeroomStructureDao.updateUsedSizeByBarcode(storeAddressBarcode, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͨ�������������ײ��豸�����ÿռ�ʧ��: ");
				}
			}
			
			
			//���¸��ڵ����ÿռ�
			if (pFlag) {
				pErrPos = 4;
				do {
					//���µ�ǰ�豸�ĸ��ڵ����ÿռ�
					if (storeroomStructureDao.updateUsedSizeByID(storeroomStructure.getParentID(), pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"ͨ���豸���("+storeroomStructure.getParentID()+")�������ÿռ�ʧ�ܣ�");
					}
					
					//�����豸��Ż�ȡ���ڵ���豸��Ϣ
					storeroomStructure.setID(storeroomStructure.getParentID());
					if (storeroomStructureDao.findByID(storeroomStructure, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"�����豸���("+storeroomStructure.getID()+")�ҿⷿ�ṹ��ϸ��Ϣ");
					}
				} while (storeroomStructure.getParentID()!=0);//������Ƕ���ⷿ�豸������������丸�ڵ�����ÿռ�
				
			
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
