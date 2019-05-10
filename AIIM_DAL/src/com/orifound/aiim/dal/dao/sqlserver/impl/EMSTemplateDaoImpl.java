package com.orifound.aiim.dal.dao.sqlserver.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.orifound.aiim.dal.dao.IEMSTemplateDao;
import com.orifound.aiim.entity.EMS;
import com.orifound.aiim.entity.ErrInfo;

public class EMSTemplateDaoImpl extends JdbcDaoSupport implements IEMSTemplateDao {
	
	/**
	 * ��ѯ�������ʵ�����ӳ����
	 *
	 */
	private class EMSMapper implements RowMapper<EMS>{
		@Override
		public EMS mapRow(ResultSet rs, int rowNum) throws SQLException {
			String acceptanceOffice = rs.getString("AcceptanceOffice");
			String acceptancePersonName = rs.getString("AcceptancePersonName");
			String fromPersonName = rs.getString("FromPersonName");
			String fromPersonPhone = rs.getString("FromPersonPhone");
			String fromCompanyName = rs.getString("FromCompanyName");
			String fromCompanyAddr = rs.getString("FromCompanyAddr");
			String fromPostCode = rs.getString("FromPostCode");
			String contents = rs.getString("Contents");
			int contentTol = rs.getInt("ContentTol");
			String remailPersonName = rs.getString("RemailPersonName");
			
			//String receivePersonName = rs.getString("ReceivePersonName");
			String receivePersonName = "";
			String receivePersonPhone = rs.getString("ReceivePersonPhone");
			String receiveCompanyName = rs.getString("ReceiveCompanyName");
			String receiveCompanyAddr = rs.getString("ReceiveCompanyAddr");
			String receiveCityName = rs.getString("ReceiveCityName");
			String receivePostCode = rs.getString("ReceivePostCode");
			return new EMS(acceptanceOffice,acceptancePersonName,fromPersonName, fromPersonPhone, fromCompanyName, fromCompanyAddr, fromPostCode, contents, contentTol, remailPersonName, receivePersonName, receivePersonPhone, receiveCompanyName, receiveCompanyAddr, receiveCityName, receivePostCode);
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
	public boolean findEMSinfos(int[] ids, List<EMS> emsInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<Integer> IDS = new ArrayList<Integer>();
		try {
			//���JDBC����Դ�Ƿ���ȷ����ע��
			if (CheckDataSourceInjection(pErrInfo) == false) {
				pFlag = false;
			}

			for (int i = 0; i < ids.length; i++) {
				IDS.add(ids[i]);
				
			}
			
			//ִ��SQL���
			if (pFlag) {
				pErrPos = 2;
				String sql = "SELECT AcceptanceOffice,FromPersonName,FromPersonPhone,FromCompanyName,FromCompanyAddr,FromPostCode,Contents,RemailPersonName" +
						",AcceptancePersonName,M.dayjbm AS ReceivePersonName,M.lxdh AS ReceivePersonPhone,M.dayjbm AS ReceiveCompanyName,M.dayjdz AS ReceiveCompanyAddr," +
						"M.dwszd AS ReceiveCityName,M.yzbm AS ReceivePostCode,M.TotalArchives AS ContentTol FROM DD_EMSTemplate E,MoveOutRegister M WHERE M.ID IN (:IDS)";
				
				MapSqlParameterSource paramSource = new MapSqlParameterSource();
				paramSource.addValue("IDS", IDS);
				
				List<EMS> pEmsInfos = new NamedParameterJdbcTemplate(getDataSource()).query(sql, paramSource, new EMSMapper());
				if (pEmsInfos.size() > 0) {
					emsInfos.addAll(pEmsInfos);
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
}
