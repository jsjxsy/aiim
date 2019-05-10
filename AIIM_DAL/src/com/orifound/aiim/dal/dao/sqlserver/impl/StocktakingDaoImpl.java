package com.orifound.aiim.dal.dao.sqlserver.impl;

import javax.sql.DataSource;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IStocktakingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingAddressBoxDetail;
import com.orifound.aiim.entity.StocktakingArchivesDetail;
/**
 * �ⷿ�̵�Daoʵ����
 * @author Administrator
 *
 */
public class StocktakingDaoImpl extends JdbcDaoSupport implements IStocktakingDao {
	/**
	 * ���캯��
	 */
	public StocktakingDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StocktakingDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StocktakingAddressBoxDetailsMapper implements RowMapper<StocktakingAddressBoxDetail>
	{
		
		@Override
		public StocktakingAddressBoxDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String storeAddressBarcode = rs.getString("StoreAddressBarcode");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			
			return new StocktakingAddressBoxDetail(stocktakingID,storeAddressBarcode,archivesBoxBarcode);
		}
	}
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StocktakingArchivesDetailsMapper implements RowMapper<StocktakingArchivesDetail>
	{
		
		@Override
		public StocktakingArchivesDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			
			return new StocktakingArchivesDetail(stocktakingID,archivesBoxBarcode,archivesBarcode);
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
	
	
	
/////////////////////////////��������/////////////// ////////////////////

	/**
	 * �жϵ������������Ƿ��Ѿ����ڵ�SQL���
	 */
	private final String SQL_SELECT_checkAddressBoxDetailExist = "SELECT COUNT(*) FROM StocktakingAddressBoxDetails WHERE ArchivesBoxBarcode=? AND StocktakingID=?";

	@Override
	public boolean checkAddressBoxDetailExist(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		boolean resultFlag = false;
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
				int rowNum = jdbcTemplate.queryForInt(SQL_SELECT_checkAddressBoxDetailExist,stocktakingAddressBoxDetail.getArchivesBoxBarcode(),stocktakingAddressBoxDetail.getStocktakingID());
				System.out.println("rowNum1:"+rowNum);
				if (rowNum >0) {
					resultFlag = true;
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
		return resultFlag;
	}

	// Table Name: StocktakingAddressBoxDetails
	// Columns List,Can Used in SELECT SQL: StocktakingID,StoreAddressBarcode,StocktakingAddressBoxDetails
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,StoreAddressBarcode=:StoreAddressBarcode,ArchivesBoxBarcode=:ArchivesBoxBarcode

	/**
	 *  ���¿ⷿ�̵���豸λ���뵵������ϸ��SQL���
	 */
	private final String SQL_UPDATE_AddressBoxDetail = " UPDATE StocktakingAddressBoxDetails SET StoreAddressBarcode=:StoreAddressBarcode WHERE ArchivesBoxBarcode=:ArchivesBoxBarcode AND StocktakingID=:StocktakingID ";
	@Override
	public boolean updateAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingAddressBoxDetail.getStocktakingID());
				parameterSource.addValue("StoreAddressBarcode", stocktakingAddressBoxDetail.getStoreAddressBarcode());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingAddressBoxDetail.getArchivesBoxBarcode());

				namedParameterJdbcTemplate.update(SQL_UPDATE_AddressBoxDetail, parameterSource);
				//���پֲ�����
				
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
	 * ��ӿⷿ�̵���豸λ���뵵������ϸ��SQL���
	 */
	private final String SQL_INSERT_AddressBoxDetail = "INSERT INTO StocktakingAddressBoxDetails(StocktakingID,StoreAddressBarcode,ArchivesBoxBarcode) VALUES(:StocktakingID,:StoreAddressBarcode,:ArchivesBoxBarcode)";
	@Override
	public boolean addAddressBoxDetail(StocktakingAddressBoxDetail stocktakingAddressBoxDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingAddressBoxDetail.getStocktakingID());
				parameterSource.addValue("StoreAddressBarcode",stocktakingAddressBoxDetail.getStoreAddressBarcode());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingAddressBoxDetail.getArchivesBoxBarcode());
				
				namedParameterJdbcTemplate.update(SQL_INSERT_AddressBoxDetail, parameterSource);

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
	 * �жϵ�������Ϣ�Ƿ���ڵ�SQL���
	 */
	private final String SQL_SELECT_checkArchivesDetailExist = " SELECT COUNT(*) FROM StocktakingArchivesDetails WHERE ArchivesBarcode=? AND StocktakingID=? ";
	@Override
	public boolean checkArchivesDetailExist(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		boolean resultFlag = false;
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
				int rowNum = jdbcTemplate.queryForInt(SQL_SELECT_checkArchivesDetailExist,stocktakingArchivesDetail.getArchivesBarcode(),stocktakingArchivesDetail.getStocktakingID());
				System.out.println("rowNum:"+rowNum);
				if (rowNum>0) {
					resultFlag = true;
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
System.out.println("pErrInfo.toString():"+pErrInfo.toString());
		return resultFlag;
	}
	
	// Table Name: StocktakingArchivesDetails
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBoxBarcode,ArchivesBarcode
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBoxBarcode=:ArchivesBoxBarcode,ArchivesBarcode=:ArchivesBarcode
	/**
	 * ���¿ⷿ�̵�ĵ������뵵������ϸ��SQL���
	 */
	private final String SQL_UPDATE_ArchivesDetail = " UPDATE StocktakingArchivesDetails SET ArchivesBoxBarcode=:ArchivesBoxBarcode WHERE StocktakingID=:StocktakingID AND ArchivesBarcode=:ArchivesBarcode ";
	@Override
	public boolean updateArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingArchivesDetail.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingArchivesDetail.getArchivesBoxBarcode());
				parameterSource.addValue("ArchivesBarcode", stocktakingArchivesDetail.getArchivesBarcode());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_ArchivesDetail, parameterSource);

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
	 * ��ӿⷿ�̵�ĵ������뵵������ϸ��SQL���
	 */
	private final String SQL_INSERT_ArchivesDetail = "INSERT INTO StocktakingArchivesDetails(StocktakingID,ArchivesBoxBarcode,ArchivesBarcode) VALUES(:StocktakingID,:ArchivesBoxBarcode,:ArchivesBarcode)";
	@Override
	public boolean addArchivesDetail(StocktakingArchivesDetail stocktakingArchivesDetail, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stocktakingArchivesDetail.getStocktakingID());
				parameterSource.addValue("ArchivesBoxBarcode", stocktakingArchivesDetail.getArchivesBoxBarcode());
				parameterSource.addValue("ArchivesBarcode", stocktakingArchivesDetail.getArchivesBarcode());
				
				namedParameterJdbcTemplate.update(SQL_INSERT_ArchivesDetail, parameterSource);
				
				

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
	
	
	//////////////////////////////�����̵㱨��////////////////////////////////
	

}
