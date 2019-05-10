package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseRequestManageService;
import com.orifound.aiim.dal.dao.IArchivesUseRequestDao;
import com.orifound.aiim.entity.ArchivesUseRequest;
import com.orifound.aiim.entity.ArchivesUseRequestQueryCondition;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ���������������뵥��Ϣҵ���߼�ʵ����
 * @author Administrator
 *
 */
public class ArchivesUseRequestManageServiceImpl implements IArchivesUseRequestManageService {
	
	/**
	 * ���캯��
	 */
	public ArchivesUseRequestManageServiceImpl() {

	}
	
	/**
	 * �����������뵥��Ϣ������ݷ��ʶ���
	 */
	private IArchivesUseRequestDao archivesUseRequestDao = null;

	/**
	 * ��ȡ����ֵ��TableName������ݷ��ʶ���
	 * @return TableName������ݷ��ʶ���
	 */ 
	public IArchivesUseRequestDao getArchivesUseRequestDao() {
		return archivesUseRequestDao;
	}
	
	/**
	 * ��������ֵ��TableName������ݷ��ʶ���
	 * @param archivesUseRequestDao TableName������ݷ��ʶ���
	 */
	public void setArchivesUseRequestDao(IArchivesUseRequestDao archivesUseRequestDao) {
		this.archivesUseRequestDao = archivesUseRequestDao;
	}
	
	
	
	/**
	 * ���ҵ�����������ݷ������Ƿ�ע��ɹ���DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
	
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesUseRequestDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����������뵥��Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
				if (pErrInfo.getException()!=null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}
		}
		return pFlag;
	}

	

	@Override
	public boolean addArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				if (archivesUseRequestDao.add(archivesUseRequest, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ӵ����������뵥��Ϣʧ��: ");
				}
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
	public boolean deleteArchivesUseRequest(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	@Override
	public boolean findArchivesUseRequestsByCondition(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition, DataPageInfo dataPageInfo,
			List<ArchivesUseRequest> archivesUseRequests, ErrInfo pErrInfo) {
		System.out.println("safadfadf");
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}

			//����DAO�ӿ�
			if (pFlag) {
				String querySQL = getQuerySQL(archivesUseRequestQueryCondition);
				System.out.println("querySQL:"+querySQL);
				if (archivesUseRequestDao.findByQueryCondition(querySQL, dataPageInfo, archivesUseRequests, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݲ�ѯ�������ҵ����������뵥��Ϣʧ��: ");
				}
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
System.out.println("error:"+pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	@Override
	public boolean findArchivesUseRequestByID(ArchivesUseRequest archivesUseRequest, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}


	/***
	 * �������ò�ѯ��������SQL
	 * @param archivesUseRequestQueryCondition �������ò�ѯ����
	 * @return querySQL ���ع����SQL��䣬��and��ʼ 
	 */
	private String getQuerySQL(ArchivesUseRequestQueryCondition archivesUseRequestQueryCondition){
			String querySQL = "";	
		
			//���뵥���
			if(archivesUseRequestQueryCondition.getID()!=null && !"".equals(archivesUseRequestQueryCondition.getID().trim())){
				querySQL += " AND ID LIKE '" + archivesUseRequestQueryCondition.getID().trim() + "%'";
			}
			
			//֤���Ų�ѯ
			if (archivesUseRequestQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseRequestQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseRequestQueryCondition.getIDCardNo().trim() + "%'";
			}

			//������ʼʱ��
			if(archivesUseRequestQueryCondition.getRequestTimeBegin()!=null ){
				querySQL += " AND RequestTime >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRequestQueryCondition.getRequestTimeBegin())   + "'";
			}
			
			//���ý���ʱ��
			if(archivesUseRequestQueryCondition.getRequestTimeEnd()!=null ){
				querySQL += " AND RequestTime <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseRequestQueryCondition.getRequestTimeEnd() )   + "'";
			}
			
			//��������
			if(archivesUseRequestQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseRequestQueryCondition.getUserDepartment().trim())){
				querySQL += " AND UserDepartment LIKE '%" + archivesUseRequestQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//����
			if(archivesUseRequestQueryCondition.getUserRealName()!=null && !"".equals(archivesUseRequestQueryCondition.getUserRealName().trim())){
				querySQL += " AND RealName LIKE '%" + archivesUseRequestQueryCondition.getUserRealName().trim() + "%'";
			}
		return querySQL;
	}


}
