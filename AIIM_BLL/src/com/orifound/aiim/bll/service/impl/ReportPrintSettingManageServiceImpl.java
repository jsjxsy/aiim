package com.orifound.aiim.bll.service.impl;

import java.util.List;

import com.orifound.aiim.bll.service.IReportPrintSettingManageService;
import com.orifound.aiim.dal.dao.IReportPrintSettingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportPrintSetting;

public class ReportPrintSettingManageServiceImpl implements IReportPrintSettingManageService{
	
	/**
	 * 构造函数
	 */
	public ReportPrintSettingManageServiceImpl() {

	}

	/**
	 * 带DAO依赖注入的构造函数
	 */
	public ReportPrintSettingManageServiceImpl(IReportPrintSettingDao reportPrintSettingDao) {
		this.reportPrintSettingDao = reportPrintSettingDao;
	}
	 
	/**
	 * TableName表的数据访问对象
	 */
	private IReportPrintSettingDao reportPrintSettingDao = null;

	/**
	 * 获取属性值：TableName表的数据访问对象
	 * @return TableName表的数据访问对象
	 */
	public IReportPrintSettingDao getReportPrintSettingDao() {
		return reportPrintSettingDao;
	}

	/**
	 * 设置属性值：TableName表的数据访问对象
	 * @param reportPrintSettingDao TableName表的数据访问对象
	 */
	public void setReportPrintSettingDao(IReportPrintSettingDao reportPrintSettingDao) {
		this.reportPrintSettingDao = reportPrintSettingDao;
	}
	 /**
	 * 检查ReportPrintSetting的DAO依赖注入（DAO Depandency Injection）
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	private boolean checkDaoInjectionForReportPrintSetting(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//检查是否有进行DAO的依赖注入
			pErrPos = 1;
			if (reportPrintSettingDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("ReportPrintSetting的DAO非法为空，请检查是否有进行依赖注入或赋值。");
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
	public boolean deleteReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findReportPrintSettingByID(int pID, ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findReportPrintSettings(List<ReportPrintSetting> pReportPrintSettings, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//开始处理 1...
			pErrPos = 1;
			if (pFlag) {
				if (checkDaoInjectionForReportPrintSetting(pErrInfo) == false ) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "报表打印设置的DAO，请检查依赖注入失败：");
				}
			}

			//开始处理2...
			if (pFlag) {
				pErrPos = 2;
				if (reportPrintSettingDao.findAll(pReportPrintSettings, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "查询报表打印设置表失败：");
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
	public boolean saveReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateReportPrintSetting(ReportPrintSetting ReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
