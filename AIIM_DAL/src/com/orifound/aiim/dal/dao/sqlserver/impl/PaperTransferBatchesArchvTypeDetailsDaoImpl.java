package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IPaperTransferBatchesArchvTypeDetailsDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.PaperTransferBatch;
import com.orifound.aiim.entity.PaperTransferBatchesArchvTypeDetail;
/**
 * ������Ϣ������ϸ��DAOʵ����
 * @author Administrator
 *
 */
public class PaperTransferBatchesArchvTypeDetailsDaoImpl extends JdbcDaoSupport implements IPaperTransferBatchesArchvTypeDetailsDao {

	/**
	 * �����κź͵�������������η�����ϸ��Ϣ
	 */
	private static final String SQL_SELECT_findByArchivesTypeAndBatNO = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=? AND ArchivesTypeID=?";
	
	/**
	 * �������η�����ϸ��Ϣ�е�����Ϣ��������
	 */
	private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=" +
			"(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) " +
			"WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * �������η�����ϸ��Ϣ�е�����Ϣ��������
	 */
	private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetailsWithSubBat = "UPDATE PaperTransferBatchesArchvTypeDetails SET TransferTotal=" +
	"(SELECT COUNT(*) FROM PaperTransferBatchesDetails where (TransferBatNo=:TransferBatNo OR TransferBatNo IN (SELECT SubBatNo FROM PaperTransferSubBatches WHERE BatNo=:TransferBatNo)) AND ArchivesTypeID=:ArchivesTypeID AND ReceiveCheckResult<>2) " +
	"WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
	
	/**
	 * �����µ����η�����ϸ��Ϣ
	 */
	private static final String SQL_INSERT_PaperTransferBatchesArchvTypeDetail = "INSERT INTO PaperTransferBatchesArchvTypeDetails (TransferBatNo,ArchivesTypeID,TransferTotal) VALUES(?,?,?)";
	
	/**
	 * ������ָ�����������е����η�����ϸ��Ϣ
	 */
	private static final String SQL_SELECT_PaperTransferBatchesArchvTypeDetails = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=?";
	
	
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class PaperTransferBatchesArchvTypeDetailMapper implements RowMapper<PaperTransferBatchesArchvTypeDetail>
	{
		@Override
		public PaperTransferBatchesArchvTypeDetail mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String transferBatNo = rs.getString("TransferBatNo");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int transferTotal = rs.getInt("TransferTotal");
			int receiveTotal = rs.getInt("ReceiveTotal");
			boolean archivesIDMaked  = rs.getBoolean("ArchivesIDMaked");
			
			return new PaperTransferBatchesArchvTypeDetail(iD,transferBatNo,archivesTypeID,transferTotal,receiveTotal,archivesIDMaked);
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

	@Override
	public boolean findByArchivesTypeAndBatNO(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if(paperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if("".equals(paperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ�������κŷǷ�Ϊ��");
				}else if(paperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ���󵵰�����ŷǷ�Ϊ��");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//String SQL_SELECT_findByArchivesTypeAndBatNO = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=? AND ArchivesTypeID=?";
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails = getJdbcTemplate().query(SQL_SELECT_findByArchivesTypeAndBatNO, 
																								new PaperTransferBatchesArchvTypeDetailMapper(), 
																								paperTransferBatchesArchvTypeDetail.getTransferBatNo(),
																								paperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				if(paperTransferBatchesArchvTypeDetails .size()>0){
					paperTransferBatchesArchvTypeDetail.cloneFrom(paperTransferBatchesArchvTypeDetails.get(0));
				}
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean update(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if("".equals(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ�������κŷǷ�Ϊ��");
				}else if(pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ���󵵰�����ŷǷ�Ϊ��");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TransferBatNo", pPaperTransferBatchesArchvTypeDetail.getTransferBatNo());
				paramSource.addValue("ArchivesTypeID",pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_PaperTransferBatchesArchvTypeDetails, paramSource);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean add(PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if(paperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if("".equals(paperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ�������κŷǷ�Ϊ��");
				}else if(paperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ���󵵰�����ŷǷ�Ϊ��");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_INSERT_PaperTransferBatchesArchvTypeDetail = 
				//"INSERT INTO PaperTransferBatchesArchvTypeDetails (TransferBatNo,ArchivesTypeID,TransferTotal) VALUES(?,?,?)";
				getJdbcTemplate().update(SQL_INSERT_PaperTransferBatchesArchvTypeDetail, 
						paperTransferBatchesArchvTypeDetail.getTransferBatNo(),
						paperTransferBatchesArchvTypeDetail.getArchivesTypeID(),
						paperTransferBatchesArchvTypeDetail.getTransferTotal());
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findCurrentPaperTransferBatchesArchvTypeDetails(PaperTransferBatch paperTransferBatch,Map<Integer, PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (paperTransferBatch == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ��");
				}else if(paperTransferBatch.getBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�������κŷǷ�Ϊ��");
				}else if("".equals(paperTransferBatch.getBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�������κŷǷ�Ϊ��");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_SELECT_PaperTransferBatchesArchvTypeDetails = "SELECT * FROM PaperTransferBatchesArchvTypeDetails WHERE TransferBatNo=?";
				List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetailList = getJdbcTemplate().query(SQL_SELECT_PaperTransferBatchesArchvTypeDetails,new PaperTransferBatchesArchvTypeDetailMapper(), paperTransferBatch.getBatNo());
				for (PaperTransferBatchesArchvTypeDetail paperTransferBatchesArchvTypeDetail : paperTransferBatchesArchvTypeDetailList) {
					paperTransferBatchesArchvTypeDetails.put(paperTransferBatchesArchvTypeDetail.getID(), paperTransferBatchesArchvTypeDetail);
				}
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, ErrInfo pErrInfo) {
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
				String sql = "UPDATE PaperTransferBatchesArchvTypeDetails SET ArchivesIDMaked='true' WHERE TransferBatNo=? AND ArchivesTypeID=?";
				getJdbcTemplate().update(sql,paperTransferBatNo,archivesTypeID);
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean findByArchivesIDMaked(String paperTransferBatNo, int archivesTypeID, List<PaperTransferBatchesArchvTypeDetail> paperTransferBatchesArchvTypeDetails, ErrInfo pErrInfo) {
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
				String sql = "SELECT * FROM PaperTransferBatchesArchvTypeDetails  WHERE TransferBatNo=? AND ArchivesTypeID=? AND ArchivesIDMaked='false'";
				List<PaperTransferBatchesArchvTypeDetail> list = getJdbcTemplate().query(sql,new PaperTransferBatchesArchvTypeDetailMapper(),paperTransferBatNo,archivesTypeID);
				if (list.size()>0) {
					paperTransferBatchesArchvTypeDetails.addAll(list);
				}
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
			pErrInfo.getContent().append(e.getMessage());
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
	public boolean updateWhithSubBat(PaperTransferBatchesArchvTypeDetail pPaperTransferBatchesArchvTypeDetail, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (pFlag) {
				if (pPaperTransferBatchesArchvTypeDetail == null) {
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo() == null){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ����Ƿ�Ϊ��");
				}else if("".equals(pPaperTransferBatchesArchvTypeDetail.getTransferBatNo())){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ�������κŷǷ�Ϊ��");
				}else if(pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID() == 0){
					pFlag = false;
					pErrInfo.getContent().append("������ϸ��Ϣ���󵵰�����ŷǷ�Ϊ��");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//private static final String SQL_UPDATE_PaperTransferBatchesArchvTypeDetails= "UPDATE  PaperTransferBatchesArchvTypeDetails SET TransferTotal=(SELECT COUNT(*) FROM PaperTransferBatchesDetails where TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID) WHERE TransferBatNo=:TransferBatNo AND ArchivesTypeID=:ArchivesTypeID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("TransferBatNo", pPaperTransferBatchesArchvTypeDetail.getTransferBatNo());
				paramSource.addValue("ArchivesTypeID",pPaperTransferBatchesArchvTypeDetail.getArchivesTypeID());
				
				namedParameterJdbcTemplate.update(SQL_UPDATE_PaperTransferBatchesArchvTypeDetailsWithSubBat, paramSource);
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
			pErrInfo.getContent().append(e.getMessage());
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
