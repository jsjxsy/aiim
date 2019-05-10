package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.ITempratureHumidityInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.TempratureHumidityInfo;
/**
 * �ⷿ��ʪ�ȵǼ���Ϣ���DAOʵ��
 * @author Administrator
 *
 */
public class TempratureHumidityInfoDaoImpl extends JdbcDaoSupport implements ITempratureHumidityInfoDao {
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class TempratureHumidityInfoMapper implements RowMapper<TempratureHumidityInfo>
	{
		
		@Override
		public TempratureHumidityInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String storeroomID = rs.getString("StoreroomID");
			Date measureDate = rs.getTimestamp("MeasureDate");
			String measureTime = rs.getString("MeasureTime");
			double temperature = rs.getBigDecimal("Temperature").doubleValue();
			double humidity = rs.getBigDecimal("Humidity").doubleValue();
			Date recordTime = rs.getTimestamp("RecordTime");
			
			return new TempratureHumidityInfo(iD,storeroomID,measureDate,measureTime,temperature,humidity,recordTime);
		}
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class TempratureHumidityInfoMapperExtendStoreroom implements RowMapper<TempratureHumidityInfo>
	{
		
		@Override
		public TempratureHumidityInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String storeroomID = rs.getString("StoreroomID");
			String storeroomName = rs.getString("StoreroomName");
			Date measureDate = rs.getTimestamp("MeasureDate");
			String measureTime = rs.getString("MeasureTime");
			double temperature = rs.getBigDecimal("Temperature").doubleValue();
			double humidity = rs.getBigDecimal("Humidity").doubleValue();
			Date recordTime = rs.getTimestamp("RecordTime");
			
			return new TempratureHumidityInfo(iD, storeroomID, storeroomName, measureDate, measureTime, temperature, humidity, recordTime);  
		}
	}

	/**
	 * ���캯��
	 */
	public TempratureHumidityInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public TempratureHumidityInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
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
	 * ��ӿⷿ��ʪ�ȵǼ���Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO TempratureHumidityInfo(StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity) VALUES(:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity)";
	@Override
	public boolean add(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			//:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(
						getDataSource());
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("StoreroomID", tempratureHumidityInfo.getStoreroomID());
				paramSource.addValue("MeasureDate", tempratureHumidityInfo.getMeasureDate());
				paramSource.addValue("MeasureTime", tempratureHumidityInfo.getMeasureTime());
				paramSource.addValue("Temperature", tempratureHumidityInfo.getTemperature());
				paramSource.addValue("Humidity", tempratureHumidityInfo.getHumidity());
				
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				tempratureHumidityInfo.setID(keyHolder.getKey().intValue());

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
	 * ɾ���ⷿ�����Ǽ���Ϣ��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM TempratureHumidityInfo WHERE ID = ?";
	@Override
	public boolean delete(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_DELETE,tempratureHumidityInfo.getID());		

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
	 * ����������ѯ�ⷿ�¶ȡ�ʪ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByCondition_count = "select count(*) from TempratureHumidityInfo where 1=1  %1$s ";


	/**
	 * ����������ѯ�ⷿ�¶ȡ�ʪ����Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByCondition = "select * from( select A.* , B.Name AS StoreroomName ,ROW_NUMBER() over(order by MeasureDate desc ,A.ID desc) as RowNum from " +
					" TempratureHumidityInfo A,DD_StoreroomStructure B  where A.StoreroomID=B.ID  %1$s )TEMPTABLE where RowNum between %2$s and %3$s ";
	@Override
	public boolean findByCondition(String whereSQL, List<TempratureHumidityInfo> tempratureHumidityInfos, DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String sql = "";

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				sql = String.format(SQL_SELECT_findByCondition_count,whereSQL);
				int rowCount = jdbcTemplate.queryForInt(sql);
				dataPageInfo.setRowCount(rowCount);
				
				sql = String.format(SQL_SELECT_findByCondition,whereSQL,(dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize() + 1, dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());
				List<TempratureHumidityInfo> pEntitys = jdbcTemplate.query(sql,new TempratureHumidityInfoMapperExtendStoreroom());
				
				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					tempratureHumidityInfos.addAll(pEntitys);	
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

	
	/**
	 * ͨ����Ų�ѯ�ⷿ������Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByID = "SELECT * FROM TempratureHumidityInfo WHERE ID =?";
	@Override
	public boolean findByID(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				List<TempratureHumidityInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByID,new TempratureHumidityInfoMapper(),tempratureHumidityInfo.getID());


				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					tempratureHumidityInfo.cloneFrom(pEntitys.get(0));
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

	// Table Name: TempratureHumidityInfo
	// Columns List,Can Used in SELECT SQL: ID,StoreroomID,MeasureDate,MeasureTime,Temperature,Humidity,RecordTime
	// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:MeasureDate,:MeasureTime,:Temperature,:Humidity,:RecordTime
	// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime,Temperature=:Temperature,Humidity=:Humidity,RecordTime=:RecordTime

	/**
	 * ���¿ⷿ������Ϣ��SQL���
	 */
	private final String SQL_UPDATE = " UPDATE TempratureHumidityInfo SET StoreroomID=:StoreroomID,MeasureDate=:MeasureDate,MeasureTime=:MeasureTime ,Temperature=:Temperature ,Humidity=:Humidity  WHERE ID=:ID ";
	@Override
	public boolean update(TempratureHumidityInfo tempratureHumidityInfo, ErrInfo pErrInfo) {
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
				MapSqlParameterSource paramSource = new MapSqlParameterSource(); // SQL�Ĳ���Դ����
				paramSource.addValue("ID", tempratureHumidityInfo.getID());
				paramSource.addValue("StoreroomID", tempratureHumidityInfo.getStoreroomID());
				paramSource.addValue("MeasureDate", tempratureHumidityInfo.getMeasureDate());
				paramSource.addValue("MeasureTime", tempratureHumidityInfo.getMeasureTime());
				paramSource.addValue("Temperature", tempratureHumidityInfo.getTemperature());
				paramSource.addValue("Humidity", tempratureHumidityInfo.getHumidity());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				paramSource = null;
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
