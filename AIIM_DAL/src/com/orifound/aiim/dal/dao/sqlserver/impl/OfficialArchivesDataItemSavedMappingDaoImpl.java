package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialArchivesDataItemSavedMappingDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialArchivesDataItemSavedMapping;

/**
 * ���ĵ����������ӳ���DAOʵ����
 * 
 * @author xsy
 * 
 */
public class OfficialArchivesDataItemSavedMappingDaoImpl extends JdbcDaoSupport
		implements IOfficialArchivesDataItemSavedMappingDao {
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	private class OfficialArchivesDataItemSavedMappingMapper implements
			RowMapper<OfficialArchivesDataItemSavedMapping> {

		@Override
		public OfficialArchivesDataItemSavedMapping mapRow(ResultSet rs,
				int rowNum) throws SQLException {
			int iD = rs.getInt("ID");
			int archivesTypeSavedMappingID = rs
					.getInt("ArchivesTypeSavedMappingID");
			int srcOfficialArchivesTypeDataItemID = rs
					.getInt("SrcOfficialArchivesTypeDataItemID");
			int desArchivesTypeDataItemID = rs
					.getInt("DesArchivesTypeDataItemID");
			String columnName=rs.getString("columnName");

			return new OfficialArchivesDataItemSavedMapping(iD,
					archivesTypeSavedMappingID,
					srcOfficialArchivesTypeDataItemID,
					desArchivesTypeDataItemID,
					columnName);
		}
	}

	/**
	 * ���캯��
	 */
	public OfficialArchivesDataItemSavedMappingDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * 
	 * @param dataSource
	 *            ����Դ
	 */
	public OfficialArchivesDataItemSavedMappingDaoImpl(DataSource dataSource) {
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
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
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
	 * ��ѯ���еĹ��ĵ�������ϵ�Ǹ�����ӳ���SQL���
	 */
	private final String SQL_SELECT_By_ArchivesTypeSavedMappingID  = "SELECT a.ID,ArchivesTypeSavedMappingID, SrcOfficialArchivesTypeDataItemID, DesArchivesTypeDataItemID, b.ColumnName"
                      +" FROM  DD_OfficialArchivesDataItemSavedMapping AS a ,"
                      +" DD_DataItem AS b WHERE a.ArchivesTypeSavedMappingID=? AND a.SrcOfficialArchivesTypeDataItemID = b.ID ";
                      
	@Override
	public boolean delete(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean findByArchivesTypeSavedMappingID (Integer archivesTypeSavedMappingID , Map<Integer,OfficialArchivesDataItemSavedMapping> officialArchivesDataItemSavedMappings, ErrInfo pErrInfo){
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
				List<OfficialArchivesDataItemSavedMapping> pOfficialArchivesDataItemSavedMappings=jdbcTemplate.query(SQL_SELECT_By_ArchivesTypeSavedMappingID,new OfficialArchivesDataItemSavedMappingMapper(),archivesTypeSavedMappingID);
				
				//���ز�ѯ���
				if (pOfficialArchivesDataItemSavedMappings.size() > 0) {
					for (OfficialArchivesDataItemSavedMapping item : pOfficialArchivesDataItemSavedMappings) {
						officialArchivesDataItemSavedMappings.put(item.getDesArchivesTypeDataItemID(), item);
					}
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
	public boolean findByID(
			int pID,
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean save(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(
			OfficialArchivesDataItemSavedMapping officialArchivesDataItemSavedMapping,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
