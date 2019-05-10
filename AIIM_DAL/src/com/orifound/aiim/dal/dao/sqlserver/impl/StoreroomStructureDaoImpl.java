/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IStoreroomStructureDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.StoreroomStructure;

/**
 * �ⷿ�ṹ��Ϣ��DD_StoreroomStructure����DAOʵ���ࣨSQL Serverƽ̨��
 *
 */
public class StoreroomStructureDaoImpl  extends JdbcDaoSupport implements IStoreroomStructureDao {
	
	
	
		private class StoreroomStructureMapper implements RowMapper<StoreroomStructure>
		{
			
			@Override
			public StoreroomStructure mapRow(ResultSet rs, int rowNum) throws SQLException
			{
				int iD = rs.getInt("ID");
				String name = rs.getString("Name");
				int parentID = rs.getInt("ParentID");
				String barcode = rs.getString("Barcode");
				String fullName = rs.getString("FullName");
				int totalSize = rs.getInt("TotalSize");
				int usedSize = rs.getInt("UsedSize");
				boolean roomFlag = rs.getBoolean("RoomFlag");
				boolean endFlag = rs.getBoolean("EndFlag");
				int roomID = rs.getInt("RoomID");//
				String remark = rs.getString("Remark");
				
				return new StoreroomStructure(iD,name,parentID,barcode,fullName,totalSize,usedSize,roomFlag,roomID,endFlag,remark);
			}
		}
	
		
		/**
		 * ���캯��
		 */
		public  StoreroomStructureDaoImpl() {

		}

		/**
		 * ������Դ�Ĺ��캯��
		 * @param dataSource ����Դ
		 */
		public  StoreroomStructureDaoImpl(DataSource dataSource) {
			setDataSource(dataSource);
		}
		
	
		
		//SQL
		// Table Name: DD_StoreroomStructure
		// Columns List,Can Used in SELECT SQL: ID,Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,RoomID,EndFlag,Remark
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:ParentID,:Barcode,:FullName,:TotalSize,:UsedSize,:RoomFlag,:RoomID,:EndFlag,:Remark
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,ParentID=:ParentID,Barcode=:Barcode,FullName=:FullName,TotalSize=:TotalSize,UsedSize=:UsedSize,RoomFlag=:RoomFlag,RoomID=:RoomID,EndFlag=:EndFlag,Remark=:Remark

		/**
		 * ����ⷿ�ṹ��Ϣ��SQL���
		 */
		private final String SQL_INSERT = "INSERT INTO DD_StoreroomStructure(Name,ParentID,Barcode,FullName,TotalSize,UsedSize,RoomFlag,EndFlag,Remark) VALUES(:Name,:ParentID,:Barcode,:FullName,:TotalSize,:UsedSize,:RoomFlag,:EndFlag,:Remark)";
		
		/**
		 * ɾ���ⷿ�ṹ��Ϣ��SQL���
		 */
		private final String SQL_DELETE = "DELETE FROM DD_StoreroomStructure WHERE ID=?";
		
		
		
		
		/**
		 * ���¿ⷿ�ṹ��Ϣ��SQL���
		 */
		private final String SQL_UPDATE = "UPDATE DD_StoreroomStructure SET Name=:Name,FullName=:FullName,TotalSize=:TotalSize,RoomFlag=:RoomFlag,EndFlag=:EndFlag,Remark=:Remark WHERE ID=:ID";
		
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
					pErrInfo.getContent()
							.append("JDBC����Դ�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#addStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addStoreroomStructure(StoreroomStructure storeroomStructure,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(storeroomStructure==null){
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ�ṹ��Ϣδ��ʼ����");				
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;				
				
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", storeroomStructure.getName(),Types.VARCHAR);
				paramSource.addValue("ParentID", storeroomStructure.getParentID(),Types.INTEGER);
				paramSource.addValue("Barcode", storeroomStructure.getBarcode(),Types.VARCHAR);
				paramSource.addValue("FullName", storeroomStructure.getFullName(),Types.VARCHAR);	
				paramSource.addValue("TotalSize", storeroomStructure.getTotalSize(),Types.INTEGER);
				paramSource.addValue("UsedSize", storeroomStructure.getUsedSize(),Types.INTEGER);
				paramSource.addValue("RoomFlag", storeroomStructure.getRoomFlag(),Types.BOOLEAN);
				paramSource.addValue("EndFlag", storeroomStructure.getEndFlag(),Types.BOOLEAN);
				paramSource.addValue("Remark", storeroomStructure.getRemark(),Types.VARCHAR);				
				
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


	/**
	 * �����豸�������ѯ�豸��Ϣ��SQL���
	 */
	private final String SQL_SELECT_findByBarcode = " SELECT * FROM DD_StoreroomStructure WHERE Barcode = ?";

	@Override
	public boolean findByBarcode(StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {
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
				List<StoreroomStructure> pEntitys=jdbcTemplate.query(SQL_SELECT_findByBarcode,new StoreroomStructureMapper(),storeroomStructure.getBarcode());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					storeroomStructure.cloneFrom(pEntitys.get(0));
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
	 * ���ݿⷿ�ṹID��ѯ�ⷿ�ṹ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_findStoreroomStructureByID = "SELECT * FROM DD_StoreroomStructure WHERE ID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStoreroomStructureByID(int, com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID(StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//���ⷿ�豸���Ϊ��0
			if(storeroomStructure==null){
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ�ṹ��Ϣδ��ʼ����");
			}
			else{
				if(storeroomStructure.getID()==0){
					pFlag = false;
					pErrInfo.getContent().append("�ⷿ�豸��ŷǷ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<StoreroomStructure> storeroomStructures = jdbcTemplate.query(SQL_SELECT_findStoreroomStructureByID,new StoreroomStructureMapper(),storeroomStructure.getID());

				//���ز�ѯ���
				if (storeroomStructures.size() > 0) {
					storeroomStructure.cloneFrom(storeroomStructures.get(0));
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

	
	/**
	 * ��ѯָ������¼��ⷿ�ṹ��Ϣ���ϵ�SQL���
	 */
	private final String SQL_SELECT_findChildrenByID = "SELECT * FROM DD_StoreroomStructure WHERE ParentID=?";
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStoreroomStructureChildrenByID(int, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildrenByID(int storeroomStructureID,
			List<StoreroomStructure> children, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if (pFlag) {
				pErrPos = 2;
				JdbcTemplate jdbcTemplate = getJdbcTemplate();
				List<StoreroomStructure> storeroomStructures=jdbcTemplate.query(SQL_SELECT_findChildrenByID,new StoreroomStructureMapper(),storeroomStructureID);

				//���ز�ѯ���
				if (storeroomStructures.size() > 0) {
					children.addAll(storeroomStructures);
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
	 * ��ѯ���пⷿ��Ϣ��SQL���
	 */
	private final String SQL_SELECT_findStorerooms = " SELECT * FROM DD_StoreroomStructure WHERE RoomFlag = 1";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#findStorerooms(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findStorerooms(List<StoreroomStructure> storeRooms,
			ErrInfo pErrInfo) {
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
				List<StoreroomStructure> storeroomStructures=jdbcTemplate.query(SQL_SELECT_findStorerooms,new StoreroomStructureMapper());

				//���ز�ѯ���
				if (storeroomStructures.size() > 0) {
					storeRooms.addAll(storeroomStructures);
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
	 * @see com.orifound.aiim.dal.dao.IStoreroomStructureDao#updateStoreroomStructure(com.orifound.aiim.entity.StoreroomStructure, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateStoreroomStructure(
			StoreroomStructure storeroomStructure, ErrInfo pErrInfo) {

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			if(storeroomStructure ==null){
				pFlag = false;
				pErrInfo.getContent().append("�ⷿ�ṹ��Ϣδ��ʼ����");
			}else{
				if(storeroomStructure.getID()==0){
					pFlag = false;
					pErrInfo.getContent().append("�ⷿ�ṹID�Ƿ�Ϊ�ա�");
				}
			}

			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				//JdbcTemplate jdbcTemplate = getJdbcTemplate();
				//List<Entity> pEntitys=jdbcTemplate.query(SQL_SELECT_BYDATASOURCEID,new DataSourceItemMapper(),pDataSourceID);
			//	"UPDATE DD_StoreroomStructure SET Name=:Name,FullName=:FullName,TotalSize=:TotalSize,RoomFlag=:RoomFlag,EndFlag=:EndFlag,Remark=:Remark WHERE ID=:ID";
				NamedParameterJdbcTemplate namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(getDataSource());
				MapSqlParameterSource paramSource=new MapSqlParameterSource();
				paramSource.addValue("Name", storeroomStructure.getName(),Types.VARCHAR);
				paramSource.addValue("FullName", storeroomStructure.getFullName(),Types.VARCHAR);
				paramSource.addValue("TotalSize", storeroomStructure.getTotalSize(),Types.INTEGER);
				paramSource.addValue("RoomFlag", storeroomStructure.getRoomFlag(),Types.BOOLEAN);
				paramSource.addValue("EndFlag", storeroomStructure.getEndFlag(),Types.BOOLEAN);
				paramSource.addValue("Remark", storeroomStructure.getRemark(),Types.VARCHAR);
				paramSource.addValue("ID", storeroomStructure.getID(),Types.INTEGER);
				
				pErrPos = 3;
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

	/**
	 * ͨ�������������ײ��豸�����ÿռ��SQL���
	 */
	private final String SQL_UPDATE_usedSize_ByBarcode = " UPDATE DD_StoreroomStructure "+
		"  SET UsedSize=(SELECT COUNT(*) FROM StoreAddressInfo A,DD_StoreroomStructure B WHERE B.Barcode='%1$s' AND a.StoreAddressID=B.ID) "+
		"  WHERE Barcode='%1$s'";
	@Override
	public boolean updateUsedSizeByBarcode(String storeAddressBarcode, ErrInfo pErrInfo) {
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
				String sql = String.format(SQL_UPDATE_usedSize_ByBarcode, storeAddressBarcode);
System.out.println("ͨ�������������ײ��豸�����ÿռ��SQL���:"+sql);
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
	 * ͨ���豸��Ÿ������ÿռ��SQL���
	 */
	private final String SQL_UPDATE_usedSize_ByID = "UPDATE DD_StoreroomStructure "+
			" SET UsedSize =( "+
			" SELECT SUM(UsedSize) FROM DD_StoreroomStructure WHERE ParentID= %1$s "+
			" ) WHERE ID=%1$s";
	@Override
	public boolean updateUsedSizeByID(int storeAddressID, ErrInfo pErrInfo) {
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
				String sql = String.format(SQL_UPDATE_usedSize_ByID,storeAddressID);
				System.out.println("ͨ�������������ײ��豸�����ÿռ��SQL���:"+sql);
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
	
	
	
	

}
