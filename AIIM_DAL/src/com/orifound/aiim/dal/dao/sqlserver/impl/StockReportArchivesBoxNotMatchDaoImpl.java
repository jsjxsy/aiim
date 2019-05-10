package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStockReportArchivesBoxNotMatchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportArchivesBoxNotMatch;
/**
 * �ⷿ�̵�-����װ�в�ƥ����Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class StockReportArchivesBoxNotMatchDaoImpl extends JdbcDaoSupport implements IStockReportArchivesBoxNotMatchDao {

	/**
	 * ���캯��
	 */
	public StockReportArchivesBoxNotMatchDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StockReportArchivesBoxNotMatchDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StockReportArchivesBoxNotMatchMapper implements RowMapper<StockReportArchivesBoxNotMatch>
	{
		
		@Override
		public StockReportArchivesBoxNotMatch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String paperBoxBarcode = rs.getString("PaperBoxBarcode");
			String systemBoxBarcode = rs.getString("SystemBoxBarcode");
			String fullName = rs.getString("FullName");
			StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch = new StockReportArchivesBoxNotMatch(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,paperBoxBarcode,systemBoxBarcode);
			stockReportArchivesBoxNotMatch.setTag(fullName);
			return stockReportArchivesBoxNotMatch;
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
	// Table Name: StocktakingReport_ArchivesBoxNotMatch
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,PaperBoxBarcode=:PaperBoxBarcode,SystemBoxBarcode=:SystemBoxBarcode
	/**
	 * ��ӵ���װ�в�ƥ����Ϣ��SQL���
	 */
	private final String SQL_SELECT = "INSERT INTO StocktakingReport_ArchivesBoxNotMatch(StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,PaperBoxBarcode,SystemBoxBarcode) " +
			" VALUES(:StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:PaperBoxBarcode,:SystemBoxBarcode)";
	@Override
	public boolean add(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo) {
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
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource parameterSource = new MapSqlParameterSource();
				parameterSource.addValue("StocktakingID", stockReportArchivesBoxNotMatch.getStocktakingID());
				parameterSource.addValue("ArchivesBarcode", stockReportArchivesBoxNotMatch.getArchivesBarcode());
				parameterSource.addValue("ArchivesTypeID", stockReportArchivesBoxNotMatch.getArchivesTypeID());
				parameterSource.addValue("NBXH", stockReportArchivesBoxNotMatch.getNBXH());
				parameterSource.addValue("ArchivesID", stockReportArchivesBoxNotMatch.getArchivesID());
				parameterSource.addValue("Title", stockReportArchivesBoxNotMatch.getTitle());
				parameterSource.addValue("PaperBoxBarcode", stockReportArchivesBoxNotMatch.getPaperBoxBarcode());
				parameterSource.addValue("SystemBoxBarcode", stockReportArchivesBoxNotMatch.getSystemBoxBarcode());
				
				namedParameterJdbcTemplate.update(SQL_SELECT, parameterSource);

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				parameterSource = null;
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

	/**
	 * ִ�е���װ�в�ƥ����Ϣ����̵��SQL
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_ArchivesBoxNotMatch WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_ArchivesBoxNotMatch " + 
		" select A.StocktakingID,A.ArchivesBarcode ,B.ArchivesTypeID,B.NBXH,B.ArchivesID,B.Title,A.ArchivesBoxBarcode AS PaperBoxBarcode ,B.ArchivesBoxBarcode " +
		" FROM StocktakingArchivesDetails A,StoreroomArchivesInfo B  " +
		" WHERE A.StocktakingID=? AND A.ArchivesBarcode = B.ArchivesBarcode AND A.ArchivesBoxBarcode<>B.ArchivesBoxBarcode " ;
	@Override
	public boolean executeStocktakingByStocktakingID(int stocktakingID, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_UPDATE_deleteAll,stocktakingID);
				
				jdbcTemplate.update(SQL_UPDATE_stocktaking,stocktakingID);

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

	/**
	 * �������еĵ���װ�в�ƥ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_findAll = "SELECT A.*,B.FullName FROM StocktakingReport_ArchivesBoxNotMatch A,DD_ArchivesType B WHERE StocktakingID=? AND A.ArchivesTypeID=B.ID";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportArchivesBoxNotMatch> stockReportArchivesBoxNotMatchs, ErrInfo pErrInfo) {
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
				List<StockReportArchivesBoxNotMatch> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportArchivesBoxNotMatchMapper(),stocktakingID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stockReportArchivesBoxNotMatchs.addAll(pEntitys);
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
	public boolean update(StockReportArchivesBoxNotMatch stockReportArchivesBoxNotMatch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
