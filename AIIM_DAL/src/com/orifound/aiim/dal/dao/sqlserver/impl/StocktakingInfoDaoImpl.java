package com.orifound.aiim.dal.dao.sqlserver.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IStocktakingInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StocktakingInfo;
/**
 * �ⷿ�̵㹤����Ϣ���DAOʵ����
 * @author Administrator
 *
 */
public class StocktakingInfoDaoImpl extends JdbcDaoSupport implements IStocktakingInfoDao {
	
	/**
	 * ���캯��
	 */
	public StocktakingInfoDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public StocktakingInfoDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class StocktakingInfoMapperExtendStoreroomName implements RowMapper<StocktakingInfo>
	{
		
		@Override
		public StocktakingInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int storeroomID = rs.getInt("StoreroomID");
			Date stocktakingDate = rs.getTimestamp("StocktakingDate");
			boolean stocktakedFlag = rs.getBoolean("StocktakedFlag");
			String storeroomName = rs.getString("StoreroomName");
			
			return new StocktakingInfo(iD,storeroomID,storeroomName,stocktakingDate,stocktakedFlag);
		}
	}
	
	private class StocktakingInfoMapper implements RowMapper<StocktakingInfo>
	{
		
		@Override
		public StocktakingInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int storeroomID = rs.getInt("StoreroomID");
			Date stocktakingDate = rs.getTimestamp("StocktakingDate");
			boolean stocktakedFlag = rs.getBoolean("StocktakedFlag");
			
			return new StocktakingInfo(iD,storeroomID,stocktakingDate,stocktakedFlag);
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
	 * ��ӿⷿ�̵㹤����Ϣ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO StocktakingInfo(StoreroomID,StocktakingDate,StocktakedFlag)" +
			" VALUES(:StoreroomID,:StocktakingDate,:StocktakedFlag)";

	@Override
	public boolean add(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				parameterSource.addValue("StoreroomID",stocktakingInfo.getStoreroomID());
				parameterSource.addValue("StocktakingDate", stocktakingInfo.getStocktakingDate());
				parameterSource.addValue("StocktakedFlag", stocktakingInfo.getStocktakedFlag());
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				
				namedParameterJdbcTemplate.update(SQL_INSERT, parameterSource,keyHolder);
				stocktakingInfo.setID(keyHolder.getKey().intValue());
				
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);

				

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
	 * ɾ���ⷿ�̵㹤����Ϣ��SQL���
	 */
	private final String SQL_DELETE = "DELETE StocktakingInfo WHERE ID=?";
	@Override
	public boolean delete(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				int effectRomNum = jdbcTemplate.update(SQL_DELETE, stocktakingInfo.getID());				
				System.out.println(effectRomNum);
				if(effectRomNum>0){
					System.out.println("ɾ���ɹ���");
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
	// Table Name: StocktakingInfo
	// Columns List,Can Used in SELECT SQL: ID,StoreroomID,StocktakingDate,StocktakedFlag
	// Columns List,Can Used in INSERT SQL: :ID,:StoreroomID,:StocktakingDate,:StocktakedFlag
	// Columns List,Can Used in UPDATE SQL: ID=:ID,StoreroomID=:StoreroomID,StocktakingDate=:StocktakingDate,StocktakedFlag=:StocktakedFlag

	/**
	 * �������еĿⷿ�̵㹤����Ϣ��SQL���
	 */ //%1$s:��ʼ��¼����%2$s����β��¼��
	private final String SQL_SELECT_findAll = "SELECT * FROM " +
			" ( SELECT  A.*,B.Name AS StoreroomName ,ROW_NUMBER() over(order by StocktakingDate desc ,A.ID desc) as RowNum " +
			" FROM StocktakingInfo A ,DD_StoreroomStructure B  WHERE A.StoreroomID= B.ID)TEMPTABLE " +
			" WHERE ROWNUM BETWEEN %1$s and %2$s ";
	//��ѯ���пⷿ�̵���Ϣ������
	private final String SQL_SELECT_findAll_count = "SELECT  COUNT(*) FROM StocktakingInfo ";
	
	@Override
	public boolean findAll(List<StocktakingInfo> stocktakingInfos, DataPageInfo dataPageInfo, ErrInfo pErrInfo) {
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
				int rowCount = jdbcTemplate.queryForInt(SQL_SELECT_findAll_count);
				dataPageInfo.setRowCount(rowCount);
				String sql = String.format(SQL_SELECT_findAll, (dataPageInfo.getCurrentPage()-1)*dataPageInfo.getPageSize()+1,dataPageInfo.getCurrentPage()* dataPageInfo.getPageSize());
				List<StocktakingInfo> pEntitys=jdbcTemplate.query(sql,new StocktakingInfoMapperExtendStoreroomName());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stocktakingInfos.addAll(pEntitys);
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
	 * ����Ψһ��Ż�ȡ�ⷿ�̵㹤����Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByID = "SELECT A.* ,B.Name AS StoreroomName FROM StocktakingInfo A,DD_StoreroomStructure B  WHERE A.StoreroomID = B.ID AND A.ID = ? ";
	@Override
	public boolean findByID( StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				List<StocktakingInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByID,new StocktakingInfoMapperExtendStoreroomName(),stocktakingInfo.getID());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					stocktakingInfo.cloneFrom(pEntitys.get(0));
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
	 * ����ָ���Ŀⷿ�̵�״̬��SQL���
	 */
	private final String SQL_UPDATE_stocktakedFlag = "UPDATE StocktakingInfo SET StocktakedFlag = 1 WHERE ID=?";
	@Override
	public boolean update(StocktakingInfo stocktakingInfo, ErrInfo pErrInfo) {
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
				jdbcTemplate.update(SQL_UPDATE_stocktakedFlag,stocktakingInfo.getID());

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
