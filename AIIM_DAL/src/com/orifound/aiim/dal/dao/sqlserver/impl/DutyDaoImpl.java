/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IDutyDao;
import com.orifound.aiim.entity.Duty;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ְ����Ϣ�����ֵ��DAOʵ����(SQL ƽ̨ʵ��)
 * @author tyb
 *
 */
public class DutyDaoImpl extends JdbcDaoSupport implements IDutyDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class DutyMapper implements RowMapper<Duty>
	{
		@Override
		public Duty mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new Duty(iD,name,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public DutyDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public DutyDaoImpl(DataSource dataSource) {
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
	 * ��ѯְ����Ϣ�����ֵ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_Duty";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDutyDao#delete(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDutyDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<Duty> dutys, ErrInfo pErrInfo) {
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
				List<Duty> pEntitys=jdbcTemplate.query(SQL_SELECT_ALL,new DutyMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					dutys.addAll(pEntitys);
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDutyDao#findByID(int, com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDutyDao#save(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDutyDao#update(com.orifound.aiim.entity.Duty, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(Duty duty, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

}
