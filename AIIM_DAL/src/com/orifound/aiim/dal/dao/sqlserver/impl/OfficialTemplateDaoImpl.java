package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IOfficialTemplateDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.OfficialDocType;
import com.orifound.aiim.entity.OfficialTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class OfficialTemplateDaoImpl extends JdbcDaoSupport implements IOfficialTemplateDao{
	
	
	/**
	 * ���캯��
	 */
	public OfficialTemplateDaoImpl() {

	}

	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public OfficialTemplateDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	 /**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 *
	 */

	 private class OfficialTemplateMapper implements
	 RowMapper<OfficialTemplate>
	 {
				
	 @Override
	 public OfficialTemplate mapRow(ResultSet rs, int rowNum) throws
	 SQLException
	 {
	 int iD = rs.getInt("ID");
	 String title = rs.getString("Title");
	 int docType = rs.getInt("DocType");
	 String provider = rs.getString("Provider");
	 Date createDate = rs.getTimestamp("CreateDate");
	 String fileName = rs.getString("FileName");
	 String remark = rs.getString("Remark");
					
	 return new OfficialTemplate(iD,title,docType,provider,createDate,fileName,remark);
	 }
	 }
	 /**
	 * Feature��SQL���
	 */
	private final String SQL_DELETE = "DELETE FROM OfficialTemplate  where ID = ? ";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_BY_NAME = "SELECT * FROM OfficialTemplate WHERE DocType = ? and Title LIKE ?";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_ALL = "SELECT * FROM OfficialTemplate where DocType = ?";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE OfficialTemplate SET Title=:Title,DocType=:DocType,Provider=:Provider,createDate=:createDate,FileName=:FileName,Remark=:Remark WHERE ID=:ID";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO OfficialTemplate(Title,DocType,Provider,createDate,FileName,Remark) VALUES(:Title,:DocType,:Provider,:createDate,:FileName,:Remark);";
	
	/**
	 * Feature��SQL���
	 */
	private final String SQL_SELECT_By_ID = "select * from OfficialTemplate where ID = ?";
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
	public boolean delete(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			JdbcTemplate jdbcTemplate = getJdbcTemplate();
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ��ŷǷ�Ϊ�ա�");
				}else{
	
					jdbcTemplate.update(SQL_DELETE,officialTemplate.getID());
				}
			}
			jdbcTemplate = null;
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
	public boolean findAll(OfficialDocType officialDocType, List<OfficialTemplate> pOfficialTemplates, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
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
				List<OfficialTemplate> officialTemplates = jdbcTemplate.query(SQL_SELECT_ALL,new OfficialTemplateMapper(),officialDocType.getID());
				if (officialTemplates.size() >0) {
					pOfficialTemplates.addAll(officialTemplates);
				}
				//���پֲ�����
				officialTemplates = null;
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
	public boolean findByID(int pID, OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
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
				List<OfficialTemplate> pOfficialTemplats=jdbcTemplate.query(SQL_SELECT_By_ID,new OfficialTemplateMapper(),pID);

				//���ز�ѯ���
				if (pOfficialTemplats.size() > 0) {
					officialTemplate.cloneFrom(pOfficialTemplats.get(0)) ;
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
	public boolean save(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getTitle() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}else{
					if (officialTemplate.getTitle().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (officialTemplate.getDocType()== 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ַǷ�Ϊ�ա�");
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 3;
				if (officialTemplate.getProvider()== null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ���ṩ�߷Ƿ�Ϊ�ա�");
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 4;
				if (officialTemplate.getCreateDate() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}
			}
			
			//���ȫ�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 5;
				if (officialTemplate.getFileName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}else{
					if(officialTemplate.getFileName().trim().length()==0){
						pFlag = false;
						pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 6;
				//SQL_INSERT = "INSERT INTO OfficialTemplate(Title,DocType,Provider,createDate,FileName,Remark) VALUES(:Title,:DocType,:Provider,:createDate,:FileName,:Remark);";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Title", officialTemplate.getTitle(),Types.VARCHAR);
				paramSource.addValue("DocType", officialTemplate.getDocType(), Types.INTEGER);
				paramSource.addValue("Provider", officialTemplate.getProvider(), Types.VARCHAR);
				paramSource.addValue("createDate", officialTemplate.getCreateDate(), Types.DATE);
				paramSource.addValue("FileName", officialTemplate.getFileName(), Types.VARCHAR);
				paramSource.addValue("Remark", officialTemplate.getRemark(), Types.VARCHAR);
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource);
				
				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
	public boolean update(OfficialTemplate officialTemplate, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			
			if (pFlag)
			{
				pErrPos = 1;
				if (officialTemplate.getID() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ��ŷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 2;
				if (officialTemplate.getTitle() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}else{
					if (officialTemplate.getTitle().trim().length()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			if (pFlag)
			{
				pErrPos = 3;
				if (officialTemplate.getDocType()== 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ַǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 4;
				if (officialTemplate.getProvider()== null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ���ṩ�߷Ƿ�Ϊ�ա�");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 5;
				if (officialTemplate.getCreateDate() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}
			}
			
			if (pFlag)
			{
				pErrPos = 6;
				if (officialTemplate.getFileName() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
				}else{
					if(officialTemplate.getFileName().trim().length()==0){
						pFlag = false;
						pErrInfo.getContent().append("����ģ�����ƷǷ�Ϊ�ա�");
					}
				}
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//SQL_UPDATE = "UPDATE OfficialTemplate SET Title=:Title,DocType=:DocType,Provider=:Provider,createDate=:createDate,FileName=:FileName,Remark=:Remark WHERE ID=:ID";
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Title", officialTemplate.getTitle(),Types.VARCHAR);
				paramSource.addValue("DocType", officialTemplate.getDocType(), Types.INTEGER);
				paramSource.addValue("Provider", officialTemplate.getProvider(), Types.VARBINARY);
				paramSource.addValue("createDate", officialTemplate.getDocType(), Types.DATE);
				paramSource.addValue("FileName", officialTemplate.getFileName(), Types.VARBINARY);
				paramSource.addValue("Remark", officialTemplate.getRemark(), Types.VARBINARY);
				paramSource.addValue("ID", officialTemplate.getID(), Types.INTEGER);
				namedParameterJdbcTemplate.update(SQL_UPDATE, paramSource);
				
				//���پֲ�����
				paramSource=null;
				namedParameterJdbcTemplate=null;
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
	public boolean findByName(OfficialDocType officialDocType,String title, List<OfficialTemplate> pOfficialTemplates, ErrInfo pErrInfo) {
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
				pErrPos = 6;
				JdbcTemplate jdbcTemplate=getJdbcTemplate();
				List<OfficialTemplate> officialTemplates=jdbcTemplate.query(SQL_SELECT_BY_NAME, new OfficialTemplateMapper(), new Object[]{officialDocType.getID(),"%"+title+"%"});
				pOfficialTemplates.addAll(officialTemplates);
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
