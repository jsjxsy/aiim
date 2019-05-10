/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.ICurrentBarcodeDao;
import com.orifound.aiim.entity.CurrentBarcode;
import com.orifound.aiim.entity.EnumBarcodeType;
import com.orifound.aiim.entity.ErrInfo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

/**
 * ��������Ϣ���DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class CurrentBarcodeDaoImpl extends JdbcDaoSupport implements
		ICurrentBarcodeDao {
	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */

	private class CurrentBarcodeMapper implements RowMapper<CurrentBarcode>
	{
		
		@Override
		public CurrentBarcode mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int barcodeType = rs.getInt("BarcodeType");
			int currentBarcodeNo = rs.getInt("CurrentBarcodeNo");
			
			return new CurrentBarcode(EnumBarcodeType.getEnumElement(barcodeType),currentBarcodeNo);
		}
	}
	
	/**
	 * ���캯��
	 */
	public CurrentBarcodeDaoImpl() {
	
	}
	
	/**
	 * ������Դ�Ĺ��캯��
	 * @param dataSource ����Դ
	 */
	public CurrentBarcodeDaoImpl(DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	
	//SQL���
	/**
	 * ����ָ���������͵�������Ϣ��SQL���
	 */
	private final String SQL_UPDATE = "UPDATE CurrentBarcode SET CurrentBarcodeNo=:CurrentBarcodeNo WHERE BarcodeType=:BarcodeType";
	/**
	 * ��ѯָ���������͵�������Ϣ��SQL���
	 */
	private final String SQL_SELECT = "SELECT * FROM CurrentBarcode WHERE BarcodeType=? ";
	
	/**
	 * ����ָ���������͵�������Ϣ��SQL���
	 */
	private final String SQL_INSERT="INSERT INTO CurrentBarcode(BarcodeType,CurrentBarcodeNo) VALUES(:BarcodeType,:CurrentBarcodeNo)";
	
	//"UPDATE Config SET ConfigValue=:ConfigValue WHERE ID=:ID";
	// Table Name: CurrentBarcode
	// Columns List,Can Used in SELECT SQL: BarcodeType,CurrentBarcodeNo
	// Columns List,Can Used in INSERT SQL: :BarcodeType,:CurrentBarcodeNo
	// Columns List,Can Used in UPDATE SQL: BarcodeType=:BarcodeType,CurrentBarcodeNo=:CurrentBarcodeNo

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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#findByBarcodeType(com.orifound.aiim.entity.EnumBarcodeType, com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override	
	public boolean findByBarcodeType(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
				
			// ���������Ϣ�Ƿ��ʼ��	
			pErrPos = 1;
			if(currentBarcode==null){
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ����δ��ʼ����");
			}else{
				if(currentBarcode.getBarcodeType()==EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("����������δ��ֵ��");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<CurrentBarcode> currentBarcodes=jdbcTemplate.query(SQL_SELECT,new CurrentBarcodeMapper(),currentBarcode.getBarcodeType().getEnumValue());
				
				//���ز�ѯ���
				if (currentBarcodes.size() > 0) {
					currentBarcode.cloneFrom(currentBarcodes.get(0));					
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
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#update(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean update(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			// ���������Ϣ�Ƿ�ֵ	
			pErrPos = 1;
			if(currentBarcode==null){
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ����δ��ʼ����");
			}else{
				if(currentBarcode.getBarcodeType()== EnumBarcodeType.NONE){
					pFlag = false;
					pErrInfo.getContent().append("�������ͷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);

				//���ز�ѯ���
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				paramSource.addValue("CurrentBarcodeNo", currentBarcode.getCurrentBarcodeNo(), Types.INTEGER);
				paramSource.addValue("BarcodeType", currentBarcode.getBarcodeType().getEnumValue(), Types.INTEGER);
				 //CurrentBarcodeNo WHERE BarcodeType=:BarcodeType
				pErrPos = 3;
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
	 * @see com.orifound.aiim.dal.dao.ICurrentBarcodeDao#update(com.orifound.aiim.entity.CurrentBarcode, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean save(CurrentBarcode currentBarcode, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			// ���������Ϣ�Ƿ�ֵ			
				pErrPos = 1;
				if(currentBarcode==null){
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ����δ��ʼ����");
				}else{
					if(currentBarcode.getBarcodeType()== EnumBarcodeType.NONE){
						pFlag = false;
						pErrInfo.getContent().append("��������δ��ֵ��");
					}
				}
			
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("BarcodeType", currentBarcode.getBarcodeType().getEnumValue(),Types.INTEGER);
				paramSource.addValue("CurrentBarcodeNo", currentBarcode.getCurrentBarcodeNo(),Types.INTEGER);				
				
				pErrPos = 3;
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
