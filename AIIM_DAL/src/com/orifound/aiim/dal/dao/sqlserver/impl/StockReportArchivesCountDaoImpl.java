package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.orifound.aiim.dal.dao.IStockReportArchivesCountDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StockReportArchivesCount;
/**
 * �ⷿ�̵�- �ⷿ�̵㵵��������Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class StockReportArchivesCountDaoImpl extends JdbcDaoSupport implements IStockReportArchivesCountDao {

	/**
	 * ���캯��
	 */
	public StockReportArchivesCountDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StockReportArchivesCountDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StockReportArchivesCountMapper implements RowMapper<StockReportArchivesCount>
	{
		
		@Override
		public StockReportArchivesCount mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int stocktakingID = rs.getInt("StocktakingID");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int paperVolumeCount = rs.getInt("PaperVolumeCount");
			int paperPieceCount = rs.getInt("PaperPieceCount");
			int systemVolumeCount = rs.getInt("SystemVolumeCount");
			int systemPieceCount = rs.getInt("SystemPieceCount");
			String fullName = rs.getString("FullName");
			StockReportArchivesCount stockReportArchivesCount =new StockReportArchivesCount(stocktakingID,archivesTypeID,paperVolumeCount,paperPieceCount,systemVolumeCount,systemPieceCount);
			stockReportArchivesCount.setTag(fullName);
			return  stockReportArchivesCount;
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

	// Table Name: StocktakingReport_ArchivesCount
	// Columns List,Can Used in SELECT SQL: StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount
	// Columns List,Can Used in INSERT SQL: :StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount
	// Columns List,Can Used in UPDATE SQL: StocktakingID=:StocktakingID,ArchivesTypeID=:ArchivesTypeID,PaperVolumeCount=:PaperVolumeCount,PaperPieceCount=:PaperPieceCount,SystemVolumeCount=:SystemVolumeCount,SystemPieceCount=:SystemPieceCount

	/**
	 * ��ӿⷿ�̵㵵��������Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingReport_ArchivesCount(StocktakingID,ArchivesTypeID,PaperVolumeCount,PaperPieceCount,SystemVolumeCount,SystemPieceCount)" +
						" VALUES(:StocktakingID,:ArchivesTypeID,:PaperVolumeCount,:PaperPieceCount,:SystemVolumeCount,:SystemPieceCount)";
	@Override
	public boolean add(StockReportArchivesCount stockReportArchivesCounty, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StocktakingID", stockReportArchivesCounty.getStocktakingID());
				parameterSource.addValue("ArchivesTypeID", stockReportArchivesCounty.getArchivesTypeID());
				parameterSource.addValue("PaperVolumeCount", stockReportArchivesCounty.getPaperVolumeCount());
				parameterSource.addValue("PaperPieceCount", stockReportArchivesCounty.getPaperPieceCount());
				parameterSource.addValue("SystemVolumeCount", stockReportArchivesCounty.getSystemVolumeCount());
				parameterSource.addValue("SystemPieceCount", stockReportArchivesCounty.getSystemPieceCount());
				
				namedParameterJdbcTemplate.update(SQL_INSERT,parameterSource);

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
	 * ִ�пⷿ����������Ϣ�̵��SQL���
	 */
	private final String  SQL_UPDATE_deleteAll = " DELETE FROM StocktakingReport_ArchivesCount WHERE StocktakingID=?";
	private final String SQL_UPDATE_stocktaking = "insert into StocktakingReport_ArchivesCount " +
			" select %1$s as StocktakingID,AA.ArchivesTypeID ,BB.paper1,BB.paper0,AA.sys1,AA.sys0 "+
				" from "+
				" (select ArchivesTypeID, "+
				" count(case ParentFlag when 1 then 1 end) as sys1, "+
				" count(case ParentFlag when 0 then 1 end) as sys0 "+
				" from DD_StoreroomStructure A ,StoreAddressInfo B,StoreroomArchivesInfo C "+ 
				" where  RoomID=(SELECT StoreroomID FROM StocktakingInfo WHERE ID=%1$s)  "+
				" and EndFlag=1  and A.ID = B.StoreAddressID and B.ArchivesBoxBarcode = C.ArchivesBoxBarcode  and c.StoreStatus=1 "+ 
				" group by ArchivesTypeID )AA "+
				" left join  "+
				" ( "+
				" select ArchivesTypeID, "+
				" count(case ParentFlag when 1 then 1 end) as paper1, "+
				" count(case ParentFlag when 0 then 1 end) as paper0 "+
				" 	from StoreroomArchivesInfo A1,StocktakingArchivesDetails B1 "+ 
				" 	where A1.ArchivesBarcode = B1.ArchivesBarcode and StocktakingID=%1$s  and a1.StoreStatus =1 "+ 
				" 	group by A1.ArchivesTypeID "+ 
				" ) BB on AA.ArchivesTypeID=BB.ArchivesTypeID ";
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
				jdbcTemplate.update(SQL_UPDATE_deleteAll,stocktakingID);//ɾ���ϴ�ͳ�Ƶ�������Ϣ
				String sql = String.format(SQL_UPDATE_stocktaking, stocktakingID);
				System.out.println(sql);
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

	/**
	 * �������еĿⷿ�̵㵵��������Ϣ��SQL���
	 */
	private final String SQL_SELECT_findAll = "SELECT A.* ,B.FullName FROM StocktakingReport_ArchivesCount A,DD_ArchivesType B WHERE StocktakingID=?  AND A.ArchivesTypeID=B.ID ORDER BY FullName ";
	@Override
	public boolean findAll(int stocktakingID,List<StockReportArchivesCount> stockReportArchivesCountys, ErrInfo pErrInfo) {
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
				List<StockReportArchivesCount> pEntitys=jdbcTemplate.query(SQL_SELECT_findAll,new StockReportArchivesCountMapper(),stocktakingID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stockReportArchivesCountys.addAll(pEntitys);
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

}
