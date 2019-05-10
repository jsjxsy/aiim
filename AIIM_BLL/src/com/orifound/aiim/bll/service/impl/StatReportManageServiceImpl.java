package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IStatReportManageService;
import com.orifound.aiim.dal.dao.IReportResultArchivesCollectionDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesDestroyDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesPublicDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesSavedDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesTypeUseDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesUsePersonDao;
import com.orifound.aiim.dal.dao.IReportResultArchivesUsePurposeDao;
import com.orifound.aiim.dal.dao.IReportResultCertificateChargeDao;
import com.orifound.aiim.dal.dao.IReportResultDepartmentSavedDao;
import com.orifound.aiim.dal.dao.IReportResultOfficialArchivesInputDao;
import com.orifound.aiim.dal.dao.IReportResultPersionalArchivesMoveOutDao;
import com.orifound.aiim.dal.dao.IReportResultStoreroomUseDao;
import com.orifound.aiim.dal.dao.IReportResultSystemVisitDao;
import com.orifound.aiim.dal.dao.IReportResultTempratureHumidityForMonthDao;
import com.orifound.aiim.dal.dao.IReportResultTempratureHumidityForYearDao;
import com.orifound.aiim.dal.dao.IReportResultWorkProcedureDao;
import com.orifound.aiim.dal.dao.IStatReportDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportResultArchivesCollection;
import com.orifound.aiim.entity.ReportResultArchivesDestroy;
import com.orifound.aiim.entity.ReportResultArchivesPublic;
import com.orifound.aiim.entity.ReportResultArchivesSaved;
import com.orifound.aiim.entity.ReportResultArchivesTypeUse;
import com.orifound.aiim.entity.ReportResultArchivesUsePerson;
import com.orifound.aiim.entity.ReportResultArchivesUsePurpose;
import com.orifound.aiim.entity.ReportResultCertificateCharge;
import com.orifound.aiim.entity.ReportResultDepartmentSaved;
import com.orifound.aiim.entity.ReportResultOfficialArchivesInput;
import com.orifound.aiim.entity.ReportResultPersionalArchivesMoveOut;
import com.orifound.aiim.entity.ReportResultStoreroomUse;
import com.orifound.aiim.entity.ReportResultSystemVisit;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForMonth;
import com.orifound.aiim.entity.ReportResultTempratureHumidityForYear;
import com.orifound.aiim.entity.ReportResultWorkProcedure;
import com.orifound.aiim.entity.StatReport;

public class StatReportManageServiceImpl implements IStatReportManageService {
	
	/**
	 * ���캯��
	 */
	public StatReportManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public StatReportManageServiceImpl(IStatReportDao statReportDao) {
		this.statReportDao = statReportDao;
	}
	
	/**
	 * ͳ�Ʊ����������ݷ��ʶ���
	 */
	private IStatReportDao statReportDao = null;

	/**
	 * ��ȡ����ֵ��ͳ�Ʊ����������ݷ��ʶ���
	 * @return ͳ�Ʊ����������ݷ��ʶ���
	 */
	public IStatReportDao getStatReportDao() {
		return statReportDao;
	}

	/**
	 * ��������ֵ��ͳ�Ʊ����������ݷ��ʶ���
	 * @param statReportDao ͳ�Ʊ����������ݷ��ʶ���
	 */
	public void setStatReportDao(IStatReportDao statReportDao) {
		this.statReportDao = statReportDao;
	}
	
	//�������ϱ��������DAO�ӿڶ���
	private  IReportResultArchivesCollectionDao reportResultArchivesCollectionDao;
	
	public IReportResultArchivesCollectionDao getReportResultArchivesCollectionDao() {
		return reportResultArchivesCollectionDao;
	}

	public void setReportResultArchivesCollectionDao(IReportResultArchivesCollectionDao reportResultArchivesCollectionDao) {
		this.reportResultArchivesCollectionDao = reportResultArchivesCollectionDao;
	}
	
	private IReportResultArchivesUsePersonDao reportResultArchivesUsePersonDao;
	
	public IReportResultArchivesUsePersonDao getReportResultArchivesUsePersonDao() {
		return reportResultArchivesUsePersonDao;
	}

	public void setReportResultArchivesUsePersonDao(IReportResultArchivesUsePersonDao reportResultArchivesUsePersonDao) {
		this.reportResultArchivesUsePersonDao = reportResultArchivesUsePersonDao;
	}

	private IReportResultArchivesTypeUseDao reportResultArchivesTypeUseDao;
	
	public IReportResultArchivesTypeUseDao getReportResultArchivesTypeUseDao() {
		return reportResultArchivesTypeUseDao;
	}

	public void setReportResultArchivesTypeUseDao(IReportResultArchivesTypeUseDao reportResultArchivesTypeUseDao) {
		this.reportResultArchivesTypeUseDao = reportResultArchivesTypeUseDao;
	}
   
	private IReportResultArchivesDestroyDao reportResultArchivesDestroyDao;
	
	public IReportResultArchivesDestroyDao getReportResultArchivesDestroyDao() {
		return reportResultArchivesDestroyDao;
	}

	public void setReportResultArchivesDestroyDao(IReportResultArchivesDestroyDao reportResultArchivesDestroyDao) {
		this.reportResultArchivesDestroyDao = reportResultArchivesDestroyDao;
	}
	
	private IReportResultArchivesPublicDao reportResultArchivesPublicDao;
	
	public IReportResultArchivesPublicDao getReportResultArchivesPublicDao() {
		return reportResultArchivesPublicDao;
	}

	public void setReportResultArchivesPublicDao(IReportResultArchivesPublicDao reportResultArchivesPublicDao) {
		this.reportResultArchivesPublicDao = reportResultArchivesPublicDao;
	}
	
	private IReportResultArchivesSavedDao reportResultArchivesSavedDao;

	public IReportResultArchivesSavedDao getReportResultArchivesSavedDao() {
		return reportResultArchivesSavedDao;
	}

	public void setReportResultArchivesSavedDao(IReportResultArchivesSavedDao reportResultArchivesSavedDao) {
		this.reportResultArchivesSavedDao = reportResultArchivesSavedDao;
	}
	
	private IReportResultArchivesUsePurposeDao reportResultArchivesUsePurposeDao;
	
	public IReportResultArchivesUsePurposeDao getReportResultArchivesUsePurposeDao() {
		return reportResultArchivesUsePurposeDao;
	}

	public void setReportResultArchivesUsePurposeDao(IReportResultArchivesUsePurposeDao reportResultArchivesUsePurposeDao) {
		this.reportResultArchivesUsePurposeDao = reportResultArchivesUsePurposeDao;
	}
	
	private IReportResultCertificateChargeDao reportResultCertificateChargeDao;
	
	public IReportResultCertificateChargeDao getReportResultCertificateChargeDao() {
		return reportResultCertificateChargeDao;
	}

	public void setReportResultCertificateChargeDao(IReportResultCertificateChargeDao reportResultCertificateChargeDao) {
		this.reportResultCertificateChargeDao = reportResultCertificateChargeDao;
	}
	
	private IReportResultDepartmentSavedDao reportResultDepartmentSavedDao;
	
	public IReportResultDepartmentSavedDao getReportResultDepartmentSavedDao() {
		return reportResultDepartmentSavedDao;
	}

	public void setReportResultDepartmentSavedDao(IReportResultDepartmentSavedDao reportResultDepartmentSavedDao) {
		this.reportResultDepartmentSavedDao = reportResultDepartmentSavedDao;
	}

	private IReportResultPersionalArchivesMoveOutDao reportResultPersionalArchivesMoveOutDao;
	
	public IReportResultPersionalArchivesMoveOutDao getReportResultPersionalArchivesMoveOutDao() {
		return reportResultPersionalArchivesMoveOutDao;
	}

	public void setReportResultPersionalArchivesMoveOutDao(IReportResultPersionalArchivesMoveOutDao reportResultPersionalArchivesMoveOutDao) {
		this.reportResultPersionalArchivesMoveOutDao = reportResultPersionalArchivesMoveOutDao;
	}
	
	private IReportResultStoreroomUseDao reportResultStoreroomUseDao;
	
	public IReportResultStoreroomUseDao getReportResultStoreroomUseDao() {
		return reportResultStoreroomUseDao;
	}

	public void setReportResultStoreroomUseDao(IReportResultStoreroomUseDao reportResultStoreroomUseDao) {
		this.reportResultStoreroomUseDao = reportResultStoreroomUseDao;
	}
	
	private IReportResultOfficialArchivesInputDao reportResultOfficialArchivesInputDao;
	
	public IReportResultOfficialArchivesInputDao getReportResultOfficialArchivesInputDao() {
		return reportResultOfficialArchivesInputDao;
	}

	public void setReportResultOfficialArchivesInputDao(IReportResultOfficialArchivesInputDao reportResultOfficialArchivesInputDao) {
		this.reportResultOfficialArchivesInputDao = reportResultOfficialArchivesInputDao;
	}

	private IReportResultSystemVisitDao reportResultSystemVisitDao;
	
	public IReportResultSystemVisitDao getReportResultSystemVisitDao() {
		return reportResultSystemVisitDao;
	}

	public void setReportResultSystemVisitDao(IReportResultSystemVisitDao reportResultSystemVisitDao) {
		this.reportResultSystemVisitDao = reportResultSystemVisitDao;
	}
	
	private IReportResultTempratureHumidityForMonthDao reportResultTempratureHumidityForMonthDao;
	
	public IReportResultTempratureHumidityForMonthDao getReportResultTempratureHumidityForMonthDao() {
		return reportResultTempratureHumidityForMonthDao;
	}

	public void setReportResultTempratureHumidityForMonthDao(IReportResultTempratureHumidityForMonthDao reportResultTempratureHumidityForMonthDao) {
		this.reportResultTempratureHumidityForMonthDao = reportResultTempratureHumidityForMonthDao;
	}

	private IReportResultTempratureHumidityForYearDao reportResultTempratureHumidityForYearDao;
	
	public IReportResultTempratureHumidityForYearDao getReportResultTempratureHumidityForYearDao() {
		return reportResultTempratureHumidityForYearDao;
	}

	public void setReportResultTempratureHumidityForYearDao(IReportResultTempratureHumidityForYearDao reportResultTempratureHumidityForYearDao) {
		this.reportResultTempratureHumidityForYearDao = reportResultTempratureHumidityForYearDao;
	}
	
	private IReportResultWorkProcedureDao reportResultWorkProcedureDao;
	
	public IReportResultWorkProcedureDao getReportResultWorkProcedureDao() {
		return reportResultWorkProcedureDao;
	}

	public void setReportResultWorkProcedureDao(IReportResultWorkProcedureDao reportResultWorkProcedureDao) {
		this.reportResultWorkProcedureDao = reportResultWorkProcedureDao;
	}
	

	/**
	 * ���ͳ�Ʊ����DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForStatReport(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (statReportDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ͳ�Ʊ����DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	public boolean deleteStatReport(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findStatReportByID(int pID, StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findStatReports(List<StatReport> pStatReports, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ͳ�Ʊ���ҵ���߼���Daoע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (statReportDao.findAll(pStatReports, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������е�ͳ�Ʊ�����Ϣʧ�ܣ�");
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
	public boolean saveStatReport(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatReport(StatReport pStatReport, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAllReportResultArchivesCollection(List<ReportResultArchivesCollection>  pReportResultArchivesCollections, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesCollectionDao.findAll(pReportResultArchivesCollections, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����������ݲ����ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesUsePerson(List<ReportResultArchivesUsePerson>  pReportResultArchivesUsePersons, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesUsePersonDao.findAll(pReportResultArchivesUsePersons, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����������ݲ����ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesTypeUse(List<ReportResultArchivesTypeUse> pReportResultArchivesTypeUses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesTypeUseDao.findAll(pReportResultArchivesTypeUses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�������������������ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesUsePurpose(List<ReportResultArchivesUsePurpose> pReportResultArchivesUsePurposes, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesUsePurposeDao.findAll(pReportResultArchivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ������������Ŀ�����ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesSaved(List<ReportResultArchivesSaved> pReportResultArchivesSaveds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesSavedDao.findAll(pReportResultArchivesSaveds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����������鵵���ʧ�ܣ�");
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
	public boolean findAllReportResultDepartmentSaved(List<ReportResultDepartmentSaved> pReportResultDepartmentSaveds, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultDepartmentSavedDao.findAll(pReportResultDepartmentSaveds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�������Ź鵵��� ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesDestroy(List<ReportResultArchivesDestroy> pReportResultArchivesDestroys, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesDestroyDao.findAll(pReportResultArchivesDestroys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ���������������ʧ�ܣ�");
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
	public boolean findAllReportResultArchivesPublic(List<ReportResultArchivesPublic> pReportResultArchivesPublics, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesPublicDao.findAll(pReportResultArchivesPublics, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ���������������ʧ�ܣ�");
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
	public boolean findAllReportResultCertificateCharge(List<ReportResultCertificateCharge> pReportResultCertificateCharges, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultCertificateChargeDao.findAll(pReportResultCertificateCharges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ������֤�շ����ʧ�ܣ�");
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
	public boolean findAllReportResultOfficialArchivesInput(List<ReportResultOfficialArchivesInput> pReportResultOfficialArchivesInputs, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultOfficialArchivesInputDao.findAll(pReportResultOfficialArchivesInputs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�������ĵǼ���� ʧ�ܣ�");
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
	public boolean findAllReportResultPersionalArchivesMoveOut(List<ReportResultPersionalArchivesMoveOut> pReportResultPersionalArchivesMoveOuts, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultPersionalArchivesMoveOutDao.findAll(pReportResultPersionalArchivesMoveOuts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�������µ���ת�����ʧ�ܣ�");
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
	public boolean findAllReportResultStoreroomUse(List<ReportResultStoreroomUse> pReportResultStoreroomUses, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultStoreroomUseDao.findAll(pReportResultStoreroomUses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����ⷿ��ʩ�������ʧ�ܣ�");
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
	public boolean findAllReportResultSystemVisit(List<ReportResultSystemVisit> pReportResultSystemVisits, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultSystemVisitDao.findAll(pReportResultSystemVisits, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ����ϵͳ�������ʧ�ܣ�");
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
	public boolean findAllReportResultTempratureHumidityForMonth(List<ReportResultTempratureHumidityForMonth> pReportResultTempratureHumidityForMonths, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultTempratureHumidityForMonthDao.findAll(pReportResultTempratureHumidityForMonths, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����ⷿ��ʪ���¶ȱ仯���ʧ�ܣ�");
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
	public boolean findAllReportResultTempratureHumidityForYear(List<ReportResultTempratureHumidityForYear> pReportResultTempratureHumidityForYears, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultTempratureHumidityForYearDao.findAll(pReportResultTempratureHumidityForYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����ⷿ��ʪ����ȱ仯���ʧ�ܣ�");
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
	public boolean findAllReportResultWorkProcedure(List<ReportResultWorkProcedure> pReportResultWorkProcedures, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�������ϱ��������DAOע��ʧ�ܣ�");
				}
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultWorkProcedureDao.findAll(pReportResultWorkProcedures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ���еı���ͳ�ƽ�����������ʧ�ܣ�");
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
