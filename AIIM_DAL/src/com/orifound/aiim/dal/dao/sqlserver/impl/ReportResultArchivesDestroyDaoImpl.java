package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IReportResultArchivesDestroyDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.ReportResultArchivesDestroy;

public class ReportResultArchivesDestroyDaoImpl extends JdbcDaoSupport implements IReportResultArchivesDestroyDao {
	
	/**
	 * ���캯��
	 */
	public ReportResultArchivesDestroyDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public ReportResultArchivesDestroyDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ReportResultArchivesDestroyMapper implements RowMapper<ReportResultArchivesDestroy>
	{
		
		@Override
		public ReportResultArchivesDestroy mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int reportID = rs.getInt("ReportID");
			String reportTitle = rs.getString("ReportTitle");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			String archivesTypeName = rs.getString("ArchivesTypeName");
			int columeCount = rs.getInt("ColumeCount");
			int pieceCount = rs.getInt("PieceCount");
			int historyColumeCount = rs.getInt("HistoryColumeCount");
			int historyPieceCount = rs.getInt("HistoryPieceCount");
			
			return new ReportResultArchivesDestroy(iD,reportID,reportTitle,archivesTypeID,archivesTypeName,columeCount,pieceCount,historyColumeCount,historyPieceCount);
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
	  * ��ѯ���еı���ͳ�ƽ���������������SQL���
	  */
	private final String SQL_SELECT_ALL = "SELECT A.*,B.ReportTitle FROM ReportResult_ArchivesDestroy A , DD_StatReport B where A.ReportID = B.ID";
	@Override
	public boolean delete(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findAll(List<ReportResultArchivesDestroy> pReportResultArchivesDestroys, ErrInfo pErrInfo) {
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
				List<ReportResultArchivesDestroy> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new ReportResultArchivesDestroyMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
						pReportResultArchivesDestroys.addAll(pEntitys);
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
	public boolean findByID(int pID, ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(ReportResultArchivesDestroy pReportResultArchivesDestroy, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
