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
	 * 构造函数
	 */
	public StatReportManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public StatReportManageServiceImpl(IStatReportDao statReportDao) {
		this.statReportDao = statReportDao;
	}
	
	/**
	 * 统计表报定义表的数据访问对象
	 */
	private IStatReportDao statReportDao = null;

	/**
	 * 获取属性值：统计表报定义表的数据访问对象
	 * @return 统计表报定义表的数据访问对象
	 */
	public IStatReportDao getStatReportDao() {
		return statReportDao;
	}

	/**
	 * 设置属性值：统计表报定义表的数据访问对象
	 * @param statReportDao 统计表报定义表的数据访问对象
	 */
	public void setStatReportDao(IStatReportDao statReportDao) {
		this.statReportDao = statReportDao;
	}
	
	//档案集合报表结果表的DAO接口定义
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
	 * 检查统计报表的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForStatReport(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (statReportDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("统计报表的DAO非法为空，请检查是否有进行依赖注入或赋值。");
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "统计报表业务逻辑层Dao注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (statReportDao.findAll(pStatReports, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查找所有的统计报表信息失败：");
				}
			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesCollectionDao.findAll(pReportResultArchivesCollections, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－案卷档案馆藏情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesUsePersonDao.findAll(pReportResultArchivesUsePersons, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－案卷档案馆藏情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesTypeUseDao.findAll(pReportResultArchivesTypeUses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－档案分类利用情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesUsePurposeDao.findAll(pReportResultArchivesUsePurposes, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－档案利用目的情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesSavedDao.findAll(pReportResultArchivesSaveds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－案卷档案归档情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultDepartmentSavedDao.findAll(pReportResultDepartmentSaveds, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－部门归档情况 失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesDestroyDao.findAll(pReportResultArchivesDestroys, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－档案销毁情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultArchivesPublicDao.findAll(pReportResultArchivesPublics, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－档案开放情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultCertificateChargeDao.findAll(pReportResultCertificateCharges, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－出证收费情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultOfficialArchivesInputDao.findAll(pReportResultOfficialArchivesInputs, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－公文登记情况 失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultPersionalArchivesMoveOutDao.findAll(pReportResultPersionalArchivesMoveOuts, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－人事档案转出情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultStoreroomUseDao.findAll(pReportResultStoreroomUses, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－库房设施利用情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultSystemVisitDao.findAll(pReportResultSystemVisits, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－系统访问情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultTempratureHumidityForMonthDao.findAll(pReportResultTempratureHumidityForMonths, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－库房温湿度月度变化情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultTempratureHumidityForYearDao.findAll(pReportResultTempratureHumidityForYears, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－库房温湿度年度变化情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
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
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForStatReport(pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "档案集合报表结果表的DAO注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportResultWorkProcedureDao.findAll(pReportResultWorkProcedures, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询所有的报表统计结果表－工作情况失败：");
				}

			}
		} catch (Exception e) {
			//异常错误
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			//拼接详细的错误描述信息，包括类名/方法名/错误位置
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//如果属于异常错误，则需要在错误信息中加入错误位置标记信息
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//销毁局部变量
			throwable = null;
		}

		return pFlag;
	}



}
