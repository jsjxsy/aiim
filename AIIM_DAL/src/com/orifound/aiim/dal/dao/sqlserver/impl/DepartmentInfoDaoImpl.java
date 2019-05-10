/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.*;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.orifound.aiim.dal.dao.IDepartmentInfoDao;
import com.orifound.aiim.entity.DepartmentInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ������ϢDAOʵ����
 *
 */
public class DepartmentInfoDaoImpl extends JdbcDaoSupport implements IDepartmentInfoDao
{
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class DepartmentInfoMapper implements RowMapper<DepartmentInfo>
	{
		
		@Override
		public DepartmentInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			String name = rs.getString("Name");
			String remark = rs.getString("Remark");
			
			return new DepartmentInfo(iD,name,remark);
		}
	}
	
	/**
	 * ���캯��
	 */
	public DepartmentInfoDaoImpl()
	{

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public DepartmentInfoDaoImpl(DataSource dataSource)
	{
		setDataSource(dataSource);
	}
	
	/**
	 * ���JDBC����Դ������ע�루JDBC DataSource Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean CheckDataSourceInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//�������Դ�Ƿ�Ϊ��
			pErrPos = 1;
			if (getDataSource() == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * ��ѯ���в�����Ϣ��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM DD_DepartmentInfo ORDER BY ID";
	
	/**
	 * ���Ų�ѯ��SQL���
	 */
	private final String SQL_SELECT_By_ID = "select * from DD_DepartmentInfo where ID = ?";
	
	
	/**
	 * ����DD_DepartmentInfo��¼��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO DD_DepartmentInfo(Name,Remark) VALUES(:Name,:Remark)";
	
	/**
	 * ɾ��DD_DepartmentInfo��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM DD_DepartmentInfo WHERE ID = ?";
	
	/**
	 * ����DD_DepartmentInfo��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE DD_DepartmentInfo SET(Name=:Name,Remark=:Remark) WHERE ID=:ID";

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDepartmentInfoDao#save(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			if (departmentInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ��!");
			}
			if (departmentInfo.getName() == null) {
				pFlag = false;
				pErrInfo.getContent().append("�������ƷǷ�Ϊ��!");
			}
			
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//"INSERT INTO DD_DepartmentInfo(Name,Remark) VALUES(:Name,:Remark)";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", departmentInfo.getName(),Types.VARBINARY);
				mapSqlParameterSource.addValue("Remark", departmentInfo.getRemark(),Types.VARCHAR);
				namedParameterJdbcTemplate.update(SQL_INSERT, mapSqlParameterSource);
				

				//���پֲ�����
				namedParameterJdbcTemplate = null;
				mapSqlParameterSource = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDepartmentInfoDao#delete(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
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
				//"DELETE FROM DD_DepartmentInfo WHERE ID = ?";
				jdbcTemplate.update(SQL_DELETE,departmentInfo.getID());

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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDepartmentInfoDao#update(com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("������ϢDaoע��Ƿ�Ϊ�գ�");
			}
			
			if (departmentInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ�գ�");
			}
			if (pFlag) {
	              if (departmentInfo.getName() == null || departmentInfo.getName().equals("")) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ƷǷ�Ϊ�գ�");
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//"UPDATE DD_DepartmentInfo SET(Name=:Name,Remark=:Remark) WHERE ID=:ID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
				mapSqlParameterSource.addValue("Name", departmentInfo.getName());
				mapSqlParameterSource.addValue("Remark", departmentInfo.getRemark());
				namedParameterJdbcTemplate.update(SQL_UPDATE, mapSqlParameterSource);
				//���پֲ�����
				mapSqlParameterSource = null;
				namedParameterJdbcTemplate = null;
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IDepartmentInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findAll(List<DepartmentInfo> departmentInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//ִ��SQL���
			if (pFlag)
			{
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<DepartmentInfo> pDepartmentInfos=jdbcTemplate.query(SQL_SELECT_ALL,new DepartmentInfoMapper());
				
				//���ز�ѯ���
				if (pDepartmentInfos.size() > 0)
				{
					departmentInfos.addAll(pDepartmentInfos);
				}

				//���پֲ�����
				pDepartmentInfos=null;
				jdbcTemplate = null;
			}
		}
		catch (BadSqlGrammarException e)
		{
			//SQL����﷨����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
			pErrInfo.setBadSQL(e.getSql());
		}
		catch (Exception e)
		{
			//�����쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{

			//ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				//��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
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
	 * @see com.orifound.aiim.dal.dao.IDepartmentInfoDao#findByID(int, com.orifound.aiim.entity.DepartmentInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(int pID, DepartmentInfo departmentInfo, ErrInfo pErrInfo)
	{

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().append("����daoע��Ƿ�Ϊ��");
			}
			
			if (pFlag) {
				if (pID == 0) {
					pFlag = false;
					pErrInfo.getContent().append("���ű�ŷǷ�Ϊ��!");
				}
			}
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<DepartmentInfo> pDepartmentInfos = jdbcTemplate.query(SQL_SELECT_By_ID,new DepartmentInfoMapper(),pID);
				//���ز�ѯ���
				if (pDepartmentInfos.size() > 0) {
					departmentInfo = pDepartmentInfos.get(0);
				}

				//���پֲ�����
				jdbcTemplate = null;
				pDepartmentInfos = null;
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
