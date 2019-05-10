package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStockReportPaperNotExistDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportPaperNotExist;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
/**
 * �ⷿ�̵�-ʵ�ﵵ�����ڼ���Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class StockReportPaperNotExistDaoImpl extends JdbcDaoSupport implements IStockReportPaperNotExistDao {

	/**
	 * ���캯��
	 */
	public StockReportPaperNotExistDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StockReportPaperNotExistDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StockReportPaperNotExistsMapper implements RowMapper<StockReportPaperNotExist>
	{
		
		@Override
		public StockReportPaperNotExist mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String title = rs.getString("Title");
			String archivesBoxBarcode = rs.getString("ArchivesBoxBarcode");
			String storeAddressFullName = rs.getString("StoreAddressFullName");
			String fullName = rs.getString("FullName");
			StockReportPaperNotExist stockReportPaperNotExist =new StockReportPaperNotExist(stocktakingID,archivesBarcode,archivesTypeID,nBXH,archivesID,title,archivesBoxBarcode,storeAddressFullName);
			stockReportPaperNotExist.setTag(fullName);
			return stockReportPaperNotExist;
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
	// Table Name: StocktakingReport_PaperNotExists
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesBarcode=:ArchivesBarcode,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,Title=:Title,ArchivesBoxBarcode=:ArchivesBoxBarcode,StoreAddressFullName=:StoreAddressFullName
	/**
	 * ���ʵ�ﵵ�����ڼ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_PaperNotExists(StocktakingID,ArchivesBarcode,ArchivesTypeID,NBXH,ArchivesID,Title,ArchivesBoxBarcode,StoreAddressFullName)" +
			" VALUES(:StocktakingID,:ArchivesBarcode,:ArchivesTypeID,:NBXH,:ArchivesID,:Title,:ArchivesBoxBarcode,:StoreAddressFullName) ";
	@Override
	public boolean add(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportPaperNotExist.getStocktakingID());
				parameterSource.addValue("ArchivesBarcode", stockReportPaperNotExist.getStocktakingID());
				parameterSource.addValue("ArchivesTypeID", stockReportPaperNotExist.getArchivesTypeID());
				parameterSource.addValue("NBXH", stockReportPaperNotExist.getNBXH());
				parameterSource.addValue("ArchivesID", stockReportPaperNotExist.getArchivesID());
				parameterSource.addValue("Title", stockReportPaperNotExist.getTitle());
				parameterSource.addValue("ArchivesBoxBarcode", stockReportPaperNotExist.getArchivesBoxBarcode());
				parameterSource.addValue("StoreAddressFullName", stockReportPaperNotExist.getStoreAddressFullName());
				
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

	@Override
	public boolean delete(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * �������е�ʵ�ﵵ�����ڼ���Ϣ��SQL���
	 */
	private final String SQL_SELECT_findAll = "SELECT A.* ,B.FullName  FROM StocktakingReport_PaperNotExists A,DD_ArchivesType B WHERE StocktakingID=?  AND A.ArchivesTypeID=B.ID ";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportPaperNotExist> stockReportPaperNotExists, ErrInfo pErrInfo) {
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
				List<StockReportPaperNotExist> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportPaperNotExistsMapper(),stocktakingID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stockReportPaperNotExists.addAll(pEntitys);
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
	public boolean findByID(int pID, StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(StockReportPaperNotExist stockReportPaperNotExist, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ʵ�ﵵ�����ڼܵ�����̵��SQL���
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_PaperNotExists WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "INSERT INTO StocktakingReport_PaperNotExists "+
		"  select E.*  from( "+
		"  select %1$s as Stocktaking,C.ArchivesBarcode,C.ArchivesTypeID,C.NBXH,C.ArchivesID,C.Title,C.ArchivesBoxBarcode,B.StoreAddressFullName from DD_StoreroomStructure A ,StoreAddressInfo B,StoreroomArchivesInfo C "+ 
		"  where  RoomID=(SELECT StoreroomID FROM StocktakingInfo WHERE ID= %1$s ) and EndFlag=1  and A.ID = B.StoreAddressID and B.ArchivesBoxBarcode = C.ArchivesBoxBarcode  and c.StoreStatus=1) E "+
		"  left join StocktakingArchivesDetails F on E.ArchivesBarcode =f.ArchivesBarcode and f.StocktakingID = %1$s "+
		"  where StocktakingID is NULL ";
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
				
				String sql = String.format(SQL_UPDATE_stocktaking, stocktakingID);
				System.out.println("sql:"+sql);
				jdbcTemplate.update(sql);

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
