package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IAreaDao;
import com.orifound.aiim.entity.Area;
import com.orifound.aiim.entity.ErrInfo;

public class AreaDaoImpl extends JdbcDaoSupport implements IAreaDao {

	/**
	 * ��ѯ�������ʵ�����ӳ����
	 *
	 */
	private class AreaMapper implements RowMapper<Area>
	{
		
		@Override
		public Area mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			boolean defaultSelectedFlag = rs.getBoolean("DefaultSelectedFlag");
			int areaFlag = rs.getInt("AreaFlag");
			
			return new Area(iD,name,defaultSelectedFlag,areaFlag);
		}
	}
	
	/**
	 * ���캯��
	 */
	public AreaDaoImpl() {

	}
	
	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public AreaDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	/**
	 * ��ѯArea���е����м�¼��SQL���
	 */
	private final String SQL_SELECT_ALL = "select * from DD_Area";
	
	/**
	 * ����ID��ѯArea���еļ�¼��SQL���
	 */
	private final String SQL_SELECT_BY_ID = "select * from DD_Area where ID=?";
	
	
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
	
	@Override
	public boolean findAllArea(List<Area> pAreas, ErrInfo pErrInfo) {
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
				pErrPos = 1;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<Area> areas = jdbcTemplate.query(SQL_SELECT_ALL, new AreaMapper());

				//���ز�ѯ���
				if (areas.size() > 0) {
					pAreas.addAll(areas);
				}

				//���پֲ�����
				areas = null;
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

	@Override
	public boolean findAreaByID(int pId, Area pArea, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//��鴫����������Ƿ�Ϊ��
			if (pFlag) {
				pErrPos = 1;
				if(pId == 0){
					pFlag = false;
					pErrInfo.getContent().append("�����������û�и�ֵ");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
			
				List<Area> areas = jdbcTemplate.query(SQL_SELECT_BY_ID, new AreaMapper(),pId);
				
				if(areas.size() > 0){
					pArea.cloneFrom(areas.get(0));
				}
				
				//���پֲ�����
				areas = null;
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

}
