package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IStocktakingManageService;
import com.orifound.aiim.dal.dao.IStockReportAddressNotMatchDao;
import com.orifound.aiim.dal.dao.IStockReportArchivesBoxNotMatchDao;
import com.orifound.aiim.dal.dao.IStockReportArchivesCountDao;
import com.orifound.aiim.dal.dao.IStockReportPaperNotExistDao;
import com.orifound.aiim.dal.dao.IStockReportSystemNotExistDao;
import com.orifound.aiim.dal.dao.IStocktakingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
import com.orifound.aiim.entity.StockReportArchivesCount;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import com.orifound.aiim.entity.StockReportSystemNotExist;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
/**
 * �ⷿ�̵�ҵ���߼�ʵ����
 * @author Administrator
 *
 */
public class StocktakingManageServiceImpl implements IStocktakingManageService {
	
	/**
	 * ���캯��
	 */
	public StocktakingManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public StocktakingManageServiceImpl(IStocktakingDao stocktakingDao) {
		this.stocktakingDao = stocktakingDao;
	}
	
	/**
	 * �ⷿ�̵�Dao
	 */
	private IStocktakingDao stocktakingDao = null;
	

	public IStocktakingDao getStocktakingDao() {
		return stocktakingDao;
	}

	public void setStocktakingDao(IStocktakingDao stocktakingDao) {
		this.stocktakingDao = stocktakingDao;
	}
	/********************** Daoע��  *****************************/
	
	/**
	 * �ⷿ�̵�- �ⷿ�̵㵵��������Ϣ���DAO�ӿڶ���
	 */
	private IStockReportArchivesCountDao stockReportArchivesCountDao = null;
	
	public IStockReportArchivesCountDao getStockReportArchivesCountDao() {
		return stockReportArchivesCountDao;
	}

	public void setStockReportArchivesCountDao(IStockReportArchivesCountDao stockReportArchivesCountDao) {
		this.stockReportArchivesCountDao = stockReportArchivesCountDao;
	}

	/**
	 * �̵㱨��-ϵͳ�в��ڼܵ�����Ϣ���DAO�ӿڶ���
	 */
	private IStockReportSystemNotExistDao stockReportSystemNotExistDao = null;
	
	public IStockReportSystemNotExistDao getStockReportSystemNotExistDao() {
		return stockReportSystemNotExistDao;
	}

	public void setStockReportSystemNotExistDao(IStockReportSystemNotExistDao stockReportSystemNotExistDao) {
		this.stockReportSystemNotExistDao = stockReportSystemNotExistDao;
	}

	/**
	 * �ⷿ�̵�-ʵ�ﵵ�����ڼ���Ϣ���DAO�ӿڶ���
	 */
	private IStockReportPaperNotExistDao stockReportPaperNotExistDao = null;
		
	public IStockReportPaperNotExistDao getStockReportPaperNotExistDao() {
		return stockReportPaperNotExistDao;
	}

	public void setStockReportPaperNotExistDao(IStockReportPaperNotExistDao stockReportPaperNotExistDao) {
		this.stockReportPaperNotExistDao = stockReportPaperNotExistDao;
	}
	
	/**
	 * �ⷿ�̵�-�ϼ�λ�ò�ƥ����Ϣ���DAO�ӿڶ���
	 */
	private IStockReportAddressNotMatchDao stockReportAddressNotMatchDao = null;
	
	public IStockReportAddressNotMatchDao getStockReportAddressNotMatchDao() {
		return stockReportAddressNotMatchDao;
	}

	public void setStockReportAddressNotMatchDao(IStockReportAddressNotMatchDao stockReportAddressNotMatchDao) {
		this.stockReportAddressNotMatchDao = stockReportAddressNotMatchDao;
	}

	/**
	 * �ⷿ�̵�-����װ�в�ƥ����Ϣ���DAO�ӿڶ���
	 */
	private IStockReportArchivesBoxNotMatchDao stockReportArchivesBoxNotMatchDao = null;
	
	public IStockReportArchivesBoxNotMatchDao getStockReportArchivesBoxNotMatchDao() {
		return stockReportArchivesBoxNotMatchDao;
	}

	public void setStockReportArchivesBoxNotMatchDao(IStockReportArchivesBoxNotMatchDao stockReportArchivesBoxNotMatchDao) {
		this.stockReportArchivesBoxNotMatchDao = stockReportArchivesBoxNotMatchDao;
	}
	
	
	/********************** Daoע��end  *************************/
	
	

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
			if (stocktakingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ�̵��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean addStocktakingAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				if (stocktakingDao.checkAddressBoxDetailExist(stocktakingAddressBoxDetail, pErrInfo)==false) {//���в�����ִ����������
					if (stocktakingDao.addAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ӿⷿ�̵���豸λ���뵵������ϸʧ��: ");
					}
				}else{//���д�����ִ�и��²���
					if (stocktakingDao.updateAddressBoxDetail(stocktakingAddressBoxDetail, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���¿ⷿ�̵���豸λ���뵵������ϸʧ�ܣ�");
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

	@Override
	public boolean addStocktakingArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				if (stocktakingDao.checkArchivesDetailExist(stocktakingArchivesDetail, pErrInfo)==false) {//��������ִ����������
					if (stocktakingDao.addArchivesDetail(stocktakingArchivesDetail, pErrInfo) == false) {
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ӿⷿ�̵�ĵ������뵵������ϸʧ��: ");
					}
				}else{//������ִ�и��²���
					if (stocktakingDao.updateArchivesDetail(stocktakingArchivesDetail, pErrInfo)==false) {
						pFlag = false;
						pErrInfo.getContent().insert(0,"���¿ⷿ�̵�ĵ������뵵������ϸʧ�ܣ�");
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

	@Override
	public boolean executeStocktaking(int stocktakingID, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		/**
		 * �����̵�ʱ�������ݿ��н���ǰ���ε��̵�����ȫ��ɾ��
		 * 1��ִ�������̵�
		 * 2��ϵͳ�в��ڼܵ�������̵�
		 * 3��ʵ�ﵵ�����ڼ��̵�
		 * 4���ϼ�λ�ò�ƥ��ĵ����̵�
		 * 5������װ�в�ƥ�������̵�
		 */
		
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
						
			//1:�����̵�
			if (pFlag) {
				pErrPos = 2;
				if (stockReportArchivesCountDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ�пⷿ����������Ϣ�̵�ʧ�ܣ�");
				}else{
					System.out.println("ִ�пⷿ����������Ϣ�̵�ɹ���");
				}
			}
			
			//2:ϵͳ�в��ڼܵ�������̵�
			if (pFlag) {
				pErrPos = 3;
				if(stockReportSystemNotExistDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ��ϵͳ�в��ڼܵ�������̵�ʧ�ܣ�");
				}else{
					System.out.println("ϵͳ�в��ڼܵ�������̵�ɹ���");
				}
			}
			
			//3:ʵ�ﵵ�����ڼ��̵�
			if (pFlag) {
				pErrPos = 4;
				if (pFlag) {
					if(stockReportPaperNotExistDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().insert(0,"ִ��ʵ�ﵵ�����ڼܵ�������̵�ʧ�ܣ�");
					}else{
						System.out.println("ִ��ʵ�ﵵ�����ڼܵ�������̵�ɹ���");
					}
				}
			}
			
			//4:�ϼ�λ�ò�ƥ��ĵ����̵�
			if (pFlag) {
				pErrPos = 5;				
				if(stockReportAddressNotMatchDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false){
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ���ϼ�λ�ò�ƥ����Ϣ����̵�ʧ�ܣ�");
				}else{
					System.out.println("ִ���ϼ�λ�ò�ƥ����Ϣ����̵�ɹ���");
				}
			}
			
			//5:����װ�в�ƥ���̵�
			if (pFlag) {
				pErrPos = 6;
				if (stockReportArchivesBoxNotMatchDao.executeStocktakingByStocktakingID(stocktakingID, pErrInfo)==false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ�е���װ�в�ƥ����Ϣ����̵�ʧ�ܣ�");
				}else{
					System.out.println("ִ�е���װ�в�ƥ����Ϣ����̵�ɹ���");
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

	
////////////////////////   findAllStockReport   /////////////////////////////////
	@Override
	public boolean findStockReportAddressNotMatch(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo) {
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
				if (stockReportAddressNotMatchDao.findAll(stocktakingID, stockReportAddressNotMatchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е��ϼ�λ�ò�ƥ����Ϣʧ��: ");
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
	public boolean findStockReportArchivesBoxNotMatch(int stocktakingID, List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo) {
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
				if (stockReportArchivesBoxNotMatchDao.findAll(stocktakingID,stockReportArchivesBoxNotMatchs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������еĵ���װ�в�ƥ����Ϣʧ��: ");
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
	public boolean findStockReportArchivesCount(int stocktakingID, List<StockReportArchivesCount> stockReportArchivesCounts, ErrInfo pErrInfo) {
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
				if (stockReportArchivesCountDao.findAll(stocktakingID,stockReportArchivesCounts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������еĿⷿ�̵㵵��������Ϣʧ��: ");
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
	public boolean findStockReportPaperNotExist(int stocktakingID, List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo) {
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
				if (stockReportPaperNotExistDao.findAll(stocktakingID,stockReportPaperNotExists, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ʵ�ﵵ�����ڼ���Ϣʧ��: ");
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
	public boolean findStockReportSystemNotExist(int stocktakingID, List<StockReportSystemNotExist> stockReportSystemNotExists, ErrInfo pErrInfo) {
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
				if (stockReportSystemNotExistDao.findAll(stocktakingID, stockReportSystemNotExists, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ϵͳ�в��ڼܵ�����Ϣʧ��: ");
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
