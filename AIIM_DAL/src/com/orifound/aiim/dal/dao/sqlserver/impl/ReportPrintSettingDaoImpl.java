package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IReportPrintSettingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportPrintSetting;

public class ReportPrintSettingDaoImpl extends JdbcDaoSupport implements IReportPrintSettingDao {

	
	/**
	 * ���캯��
	 */
	public ReportPrintSettingDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ReportPrintSettingDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ReportPrintSettingMapper implements RowMapper<ReportPrintSetting>
	{
		
		@Override
		public ReportPrintSetting mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int titleFontSize = rs.getInt("TitleFontSize");
			int tableRowHeight = rs.getInt("TableRowHeight");
			int tableFontSize = rs.getInt("TableFontSize");
			
			return new ReportPrintSetting(titleFontSize,tableRowHeight,tableFontSize);
		}
	}
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null) {
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	/**
	 * ��ѯ�����еı����ӡ���ñ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM ReportPrintSetting";
	@Override
	public boolean delete(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<ReportPrintSetting> pReportPrintSettings, ErrInfo pErrInfo) {
	boolean pFlag = true;
	int pErrPos = 0;
	Throwable throwable = new Throwable();

	try {
		//���JDBC����Դ�Ƿ���ȷ����ע��
		if (CheckDataSourceInjection(pErrInfo) == false) {
			pFlag = false;
		}

		//ִ��SQL���
		if (pFlag) {
			pErrPos = 2;
			JdbcTemplate jdbcTemplate = getJdbcTemplate();
			List<ReportPrintSetting> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new ReportPrintSettingMapper());

			//���ز�ѯ���
			if (pEntitys.size() > 0) {
				pReportPrintSettings.addAll(pEntitys);
			}

			//���پֲ�����
			jdbcTemplate = null;
		}
	} catch (BadSqlGrammarException e) {
		//SQL����﷨����
		pFlag = false;
		pErrInfo.getContent().append(e.toString());
		pErrInfo.setException(e);
		pErrInfo.setBadSQL(e.getSql());
	} catch (Exception e) {
		//�����쳣����
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
	public boolean findByID(int pID, ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ReportPrintSetting pReportPrintSetting, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
