/**
 * 
 */
package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.util.Date;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.ArchivesUsePersonInfo;
import com.orifound.aiim.entity.ArchivesUseRegister;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ʵ�ﵵ�����ó�ȥ��ϸDao�ӿ�ʵ����
 * @author Administrator
 *
 */
public class ArchivesUseOutInfoDaoImpl extends JdbcDaoSupport implements IArchivesUseOutInfoDao {
	/**
	 * ���캯��
	 */
	public ArchivesUseOutInfoDaoImpl() {

	}	
	
	/**
	 * ��ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesUseOutInfoMapper implements RowMapper<ArchivesUseOutInfo>
	{		
		@Override
		public ArchivesUseOutInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
			int iD = rs.getInt("ID");
			int useRegID = rs.getInt("UseRegID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String title = rs.getString("Title");
			int pageSum = rs.getInt("PageSum");
			Date shouldReturnDate = rs.getTimestamp("ShouldReturnDate");
			
			return new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate);
		}
	}
	
	/**
	 * ȫӳ��������ѯ�������ʵ�����ӳ���������������DAOʵ������
	 * 
	 */
	private class ArchivesUseOutInfoFullMapper implements RowMapper<ArchivesUseOutInfo>
	{		
		@Override
		public ArchivesUseOutInfo mapRow(ResultSet rs, int rowNum) throws SQLException
		{
//outinfo	C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID,
//Person    B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel,
//Register	A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark  
			//=====����ArchivesUseOutInfo=====
			int iD = rs.getInt("ID");
			int useRegID = rs.getInt("UseRegID");
			boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int archivesTypeID = rs.getInt("ArchivesTypeID");
			int nBXH = rs.getInt("NBXH");
			String archivesID = rs.getString("ArchivesID");
			String archivesBarcode = rs.getString("ArchivesBarcode");
			String title = rs.getString("Title");
			int pageSum = rs.getInt("PageSum");
			Date shouldReturnDate = rs.getTimestamp("ShouldReturnDate");
			Date returnDate = rs.getTimestamp("ReturnDate");
			ArchivesUseOutInfo archivesUseOutInfo =  new ArchivesUseOutInfo(iD,useRegID,borrowFlag,archivesTypeID,nBXH,archivesID,archivesBarcode,title,pageSum,shouldReturnDate,returnDate);
			//======����ArchivesUseOutInfo=======
			
			int userID = rs.getInt("UsePersonID");
			String name = rs.getString("Name");
			String iDCardNo = rs.getString("IDCardNo");
			int iDCardTypeID = rs.getInt("IDCardTypeID");
			String department = rs.getString("Department");
			String tel = rs.getString("Tel");
			String email = rs.getString("Email");
			int areaID = rs.getInt("AreaFlag");
			ArchivesUsePersonInfo archivesUsePersonInfo = new ArchivesUsePersonInfo(userID, name, iDCardNo, iDCardTypeID, department, tel, email, areaID);
			//======����ArchivesUseOutInfo=======
			int registerID = rs.getInt("UseRegID");
			//boolean borrowFlag = rs.getBoolean("BorrowFlag");
			int purposeID = rs.getInt("PurposeID");
			int usePersonsCount = rs.getInt("UsePersonsCount");
			int useArchivesCount = rs.getInt("UseArchivesCount");
			Date useDate = rs.getTimestamp("UseDate");
			int usePersonID = rs.getInt("UsePersonID");
			int managerUserID = rs.getInt("ManagerUserID");
			String remark = rs.getString("Remark");			
			String purposeText = rs.getString("Purpose");
			String managerUserName = "";
			ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister(registerID, borrowFlag, purposeID, usePersonsCount, useArchivesCount, useDate, usePersonID, managerUserID, remark, managerUserName, purposeText);
			//ArchivesUseRegister archivesUseRegister = new ArchivesUseRegister(registerID, borrowFlag, purposeID, usePersonsCount, useArchivesCount, useDate, usePersonID, managerUserID, remark);
			//����������Ϣ�ҵ����õǼ���Ϣ��
			archivesUseRegister.setArchivesUsePersonInfo(archivesUsePersonInfo);
			//�����õǼ���Ϣ�ҵ�����������Ϣ��ϸ��
			archivesUseOutInfo.setArchivesUseRegister(archivesUseRegister);
			 return archivesUseOutInfo;
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
	
	


	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#delete(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean delete(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * ����������ѯ�������ó�ȥ��ϸ��Ϣ��SQL���
	 */
//	private final String SQL_SELECT_findByQueryCondition1 = "SELECT "+
//		" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
//		" B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
//		" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark "+  
//		" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID "+
//		" AND ArchivesID LIKE 'G01%' AND A.BorrowFlag=1 order by ID ASC ";
	//%1$s:��ѯ���� 
	private  String SQL_SELECT_findByQueryCondition_COUNT = " SELECT COUNT(C.ID) "+ 
	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID = B.ID AND C.UseRegID = A.ID  %1$s ";
	
	
	//%1$s:��ѯ����(��and��ͷ )��%2$s��ҳ��С��<br>%3$s���Ѿ���ȡ���ļ�¼����<br>
	private  String SQL_SELECT_findByQueryCondition =	 
	"SELECT TOP %2$s "+
	" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
	" B.AreaFlag ,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
	" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark, "+  
	" D.Purpose "+
	" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C,DD_ArchivesUsePurpose D WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID AND A.PurposeID =D.ID"+
	//��ҳ����
	" AND C.ID IN ( "+
	" SELECT  C.ID FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID %1$s "+ 
	" AND C.ID > "+
	" ( "+
	" SELECT ISNULL(MAX(ID),0) "+
	"  FROM  "+
	"  ( "+
	"  SELECT TOP %3$s C.ID "+
	"   FROM "+ 
	"   ArchivesUseRegister A, "+
	"   ArchivesUsePersonInfo B, "+
	"   (SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C "+ 
	"   WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID %1$s  ORDER BY ID ASC "+
	" ) AS T "+
	" ))ORDER BY C.ID ASC ";
	
	//δ�޸�borrowFlag
//	private  String SQL_SELECT_findByQueryCondition =	 
//		"SELECT TOP %2$s "+
//		" C.ID,C.ArchivesBarcode,C.ArchivesID,C.ArchivesTypeID,C.BorrowFlag,C.NBXH,C.PageSum,C.ReturnDate,C.ShouldReturnDate,C.Title,C.UseRegID, "+
//		" B.AreaID,B.Department,B.Email,B.IDCardNo,B.IDCardTypeID,B.Name,B.Tel, "+
//		" A.PurposeID,A.UsePersonsCount,A.UseArchivesCount,A.UseDate,A.UsePersonID,A.ManagerUserID,A.Remark "+  
//		" FROM ArchivesUseRegister A,ArchivesUsePersonInfo B,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C WHERE A.UsePersonID =B.ID AND C.UseRegID = A.ID "+
//		//��ҳ����
//		" AND C.ID IN ( "+
//		" SELECT  C1.ID FROM ArchivesUseRegister A1,ArchivesUsePersonInfo B1,(SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C1 WHERE A1.UsePersonID =B1.ID AND C1.UseRegID = A1.ID %1$s "+ 
//		" AND C.ID > "+
//		" ( "+
//		" SELECT ISNULL(MAX(ID),0) "+
//		"  FROM  "+
//		"  ( "+
//		"  SELECT TOP %3$s C2.ID "+
//		"   FROM "+ 
//		"   ArchivesUseRegister A2, "+
//		"   ArchivesUsePersonInfo B2, "+
//		"   (SELECT *,NULL AS ReturnDate FROM ArchivesUseOutInfo UNION SELECT * FROM ArchivesUseReturnInfo) C2 "+ 
//		"   WHERE A2.UsePersonID =B2.ID AND C2.UseRegID = A2.ID %1$s  ORDER BY ID ASC "+
//		" ) AS T "+
//		" ))ORDER BY C.ID ASC ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#findAll(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByQueryCondition(String querySQL,DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		String localSql = "";
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
				localSql = String.format(SQL_SELECT_findByQueryCondition_COUNT,querySQL);
System.out.println("localSql1:"+ localSql);
				//��ȡ�ܼ�¼��
				int pRowCount = jdbcTemplate.queryForInt(localSql);
				dataPageInfo.setRowCount(pRowCount);
System.out.println("pRowCount:"+pRowCount);
				
				localSql = String.format(SQL_SELECT_findByQueryCondition,querySQL, dataPageInfo.getPageSize(), (dataPageInfo.getCurrentPage() - 1) * dataPageInfo.getPageSize());
System.out.println("localSql2:"+ localSql);
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(localSql,new ArchivesUseOutInfoFullMapper());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {					
					archivesUseOutInfos.addAll(pEntitys);
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
System.out.println("error:"+ pErrInfo.toString());
			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#findByID(int, com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findByID( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
		/**
	 * ���ݵ����������ѯ��ʵ�ﵵ�����ó�ȥ��ϸ��SQL���
	 */
	private final String SQL_SELECT_findByArchivesBarcode = "SELECT * FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	@Override
	public boolean findByArchivesBarcode( ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
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
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByArchivesBarcode,new ArchivesUseOutInfoMapper(),archivesUseOutInfo.getArchivesBarcode());

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseOutInfo.cloneFrom(pEntitys.get(0));
				}else{
					pFlag = false;
					pErrInfo.getContent().insert(0,"û���ҵ������롮" + archivesUseOutInfo.getArchivesBarcode() + "����ָ���ĵ������ó�ȥ��ϸ  ��¼��");
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
	 * �������õǼǱ�Ų�ѯ����ʵ�ﵵ�����ó�ȥ��ϸ��SQL���
	 */
	private final String SQL_SELECT_findByRegisterID = "SELECT * FROM ArchivesUseOutInfo WHERE UseRegID = ?";
	
	@Override
	public boolean findByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
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
				List<ArchivesUseOutInfo> pEntitys=jdbcTemplate.query(SQL_SELECT_findByRegisterID,new ArchivesUseOutInfoMapper(),registerID);

				//���ز�ѯ���
				if (pEntitys.size() > 0) {
					archivesUseOutInfos.addAll(pEntitys);
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
	 * ���ʵ�ﵵ�����ó�ȥ��ϸ��SQL���
	 */
	private final String SQL_INSERT = "INSERT INTO ArchivesUseOutInfo(UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate) " +
			"VALUES(:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate)";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#save(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean add(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
	
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}
// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getDataSource());
				paramSource.addValue("UseRegID", archivesUseOutInfo.getUseRegID());
				paramSource.addValue("BorrowFlag", archivesUseOutInfo.getBorrowFlag());
				paramSource.addValue("ArchivesTypeID", archivesUseOutInfo.getArchivesTypeID());
				paramSource.addValue("NBXH", archivesUseOutInfo.getNBXH());
				paramSource.addValue("ArchivesID", archivesUseOutInfo.getArchivesID());
				paramSource.addValue("ArchivesBarcode", archivesUseOutInfo.getArchivesBarcode());
				paramSource.addValue("Title", archivesUseOutInfo.getTitle());
				paramSource.addValue("PageSum", archivesUseOutInfo.getPageSum());
				paramSource.addValue("ShouldReturnDate", archivesUseOutInfo.getShouldReturnDate());
				pErrPos = 3;
				GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
				namedParameterJdbcTemplate.update(SQL_INSERT, paramSource,keyHolder);
				archivesUseOutInfo.setID(keyHolder.getKey().intValue());
			
				
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
	// Table Name: ArchivesUseOutInfo
	// Columns List,Can Used in SELECT SQL: ID,UseRegID,BorrowFlag,ArchivesTypeID,NBXH,ArchivesID,ArchivesBarcode,Title,PageSum,ShouldReturnDate
	// Columns List,Can Used in INSERT SQL: :ID,:UseRegID,:BorrowFlag,:ArchivesTypeID,:NBXH,:ArchivesID,:ArchivesBarcode,:Title,:PageSum,:ShouldReturnDate
	// Columns List,Can Used in UPDATE SQL: ID=:ID,UseRegID=:UseRegID,BorrowFlag=:BorrowFlag,ArchivesTypeID=:ArchivesTypeID,NBXH=:NBXH,ArchivesID=:ArchivesID,ArchivesBarcode=:ArchivesBarcode,Title=:Title,PageSum=:PageSum,ShouldReturnDate=:ShouldReturnDate
	
	/**
	 * �黹����������¼�����ó�ȥ���Ƶ��黹���õ������е�SQL���
	 */
	private final String SQL_INSERT_returnArchivesByBarcode = "INSERT INTO ArchivesUseReturnInfo SELECT *,GETDATE() FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	/**
	 * �黹�����������ó�ȥ���еļ�¼ɾ����SQL���
	 */
	private final String SQL_DELETE_returnArchivesByBarcode = "DELETE FROM ArchivesUseOutInfo WHERE ArchivesBarcode = ?";
	
	@Override
	public boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				int rowsNum = jdbcTemplate.update(SQL_INSERT_returnArchivesByBarcode, archivesUseOutInfo.getArchivesBarcode());
				pErrPos = 3;
				if (rowsNum > 0) {
					//����ɹ���ɾ�����ó�ȥ��ϸ��Ϣ
					jdbcTemplate.update(SQL_DELETE_returnArchivesByBarcode, archivesUseOutInfo.getArchivesBarcode());
					
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
	 * ���ݵ�����������µ���Ӧ�黹ʱ���SQL���
	 */
	private final String SQL_UPDATE_updateShouldReturnDate = "UPDATE ArchivesUseOutInfo SET ShouldReturnDate = ? WHERE ArchivesBarcode = ? ";
	/* (non-Javadoc)
	 * @see com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao#update(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				int rowsNum = jdbcTemplate.update(SQL_UPDATE_updateShouldReturnDate, archivesUseOutInfo.getShouldReturnDate(),archivesUseOutInfo.getArchivesBarcode());
				
				//���ز�ѯ���
				if (rowsNum == 0) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ִ��SQL��û���ҵ�ָ������������Ӧ�ĵ�������ȥ���¹黹����");
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
	
	

}
