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

import com.orifound.aiim.dal.dao.IStockReportAddressNotMatchDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportAddressNotMatch;
/**
 * �ⷿ�̵�-�ϼ�λ�ò�ƥ����Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class StockReportAddressNotMatchDaoImpl extends JdbcDaoSupport implements IStockReportAddressNotMatchDao {
	/**
	 * ���캯��
	 */
	public StockReportAddressNotMatchDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StockReportAddressNotMatchDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StockReportAddressNotMatchMapper implements RowMapper<StockReportAddressNotMatch>
	{
		
		@Override
		public StockReportAddressNotMatch mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			int paperAddressID = rs.getInt("PaperAddressID");
			String paperAddressFullName = rs.getString("PaperAddressFullName");
			int systemAddressID = rs.getInt("SystemAddressID");
			String systemAddressFullName = rs.getString("SystemAddressFullName");
			
			return new StockReportAddressNotMatch(stocktakingID,archivesBoxBarcode,paperAddressID,paperAddressFullName,systemAddressID,systemAddressFullName);
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
	// Table Name: StocktakingReport_AddressNotMatch
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,PaperAddressID=:PaperAddressID,PaperAddressFullName=:PaperAddressFullName,SystemAddressID=:SystemAddressID,SystemAddressFullName=:SystemAddressFullName
	/**
	 * ����ϼ�λ�ò�ƥ����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_AddressNotMatch(StocktakingID,ArchivesBoxBarcode,PaperAddressID,PaperAddressFullName,SystemAddressID,SystemAddressFullName)" +
			" VALUSE(:StocktakingID,:ArchivesBoxBarcode,:PaperAddressID,:PaperAddressFullName,:SystemAddressID,:SystemAddressFullName)";
	@Override
	public boolean add(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportAddressNotMatch.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stockReportAddressNotMatch.getArchivesBoxBarcode());
				parameterSource.addValue("PaperAddressID", stockReportAddressNotMatch.getPaperAddressID());
				parameterSource.addValue("PaperAddressFullName", stockReportAddressNotMatch.getPaperAddressFullName());
				parameterSource.addValue("SystemAddressID", stockReportAddressNotMatch.getSystemAddressID());
				parameterSource.addValue("SystemAddressFullName",stockReportAddressNotMatch.getSystemAddressFullName());

				namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource);
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
	 * �������е��ϼ�λ�ò�ƥ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_findAll = "SELECT * FROM StocktakingReport_AddressNotMatch WHERE StocktakingID = ?";
	@Override
	public boolean findAll(int stocktakingID, List<StockReportAddressNotMatch> stockReportAddressNotMatchs, ErrInfo pErrInfo) {
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
				List<StockReportAddressNotMatch> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportAddressNotMatchMapper(),stocktakingID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stockReportAddressNotMatchs.addAll(pEntitys);
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
	public boolean update(StockReportAddressNotMatch stockReportAddressNotMatch, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ִ���ϼ�λ�ò�ƥ����Ϣ����̵��SQL���
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_AddressNotMatch WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_AddressNotMatch "+
			"  SELECT A.StocktakingID,A.ArchivesBoxBarcode , "+
			"  	B.ID as PaperAddressID,B.FullName AS PaperAddressFullName , "+
			"  	C.StoreAddressID ,C.StoreAddressFullName  "+
			"  	FROM StocktakingAddressBoxDetails A ,DD_StoreroomStructure B ,StoreAddressInfo C "+  
			"  		WHERE StocktakingID=? and A.StoreAddressBarcode =B.Barcode AND A.ArchivesBoxBarcode = C.ArchivesBoxBarcode AND B.ID <> C.StoreAddressID ";
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

	
	
}
