/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService;
import com.orifound.aiim.dal.dao.IArchivesUseOutInfoDao;
import com.orifound.aiim.entity.ArchivesUseInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesUseOutInfo;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.ErrInfo;

/**
 * ʵ�ﵵ�����ó�ȥ��ϸ��������ʵ����
 * @author Administrator
 *
 */
public class ArchivesUseOutInfoManageServiceImpl implements IArchivesUseOutInfoManageService {
	/**
	 * ���캯��
	 */
	public ArchivesUseOutInfoManageServiceImpl() {

	}
	
	
	/**
	 * ʵ�ﵵ�����ó�ȥ��ϸ������ݷ��ʶ���
	 */
	private IArchivesUseOutInfoDao archivesUseOutInfoDao = null;

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ�����ó�ȥ��ϸ������ݷ��ʶ���
	 * @return ʵ�ﵵ�����ó�ȥ��ϸ������ݷ��ʶ���
	 */
	public IArchivesUseOutInfoDao getArchivesUseOutInfoDao() {
		return archivesUseOutInfoDao;
	}

	/**
	 * ��������ֵ��TableName������ݷ��ʶ���
	 * @param ArchivesUseOutInfoDao TableName������ݷ��ʶ���
	 */
	public void setArchivesUseOutInfoDao(IArchivesUseOutInfoDao archivesUseOutInfoDao) {
		this.archivesUseOutInfoDao = archivesUseOutInfoDao;
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
			if (archivesUseOutInfoDao==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("ʵ�ﵵ�����ó�ȥ��ϸ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	

	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#addArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean addArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
				pErrPos = 2;
				if (archivesUseOutInfoDao.add(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ʵ�ﵵ�����ó�ȥ��ϸʧ�ܣ� ");
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
	public boolean returnArchivesByBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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

			//����DAO�ӿ�:�黹ʵ�ﵵ��
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.returnArchivesByBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "ʵ�ﵵ�����ó�ȥ��ϸʧ�ܣ� ");
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
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#deleteArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesUseOutInfo(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#findArchivesUseOutInfoByID(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfoByID(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
    public boolean findArchivesUseOutInfoByArchivesBarcode(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo){
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
			
			if(archivesUseOutInfo!=null && "".equals(archivesUseOutInfo.getArchivesBarcode())){
				pFlag = false;
				pErrInfo.getContent().append("ʵ�ﵵ�����ó�ȥ��ϸ�����еĵ���������δ��ֵ��");
			}
			

			//����DAO�ӿ�,���ݵ����������ѯ���ó�ȥ��ϸ��Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.findByArchivesBarcode(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO:���ݵ����������ѯ�������ó�ȥ��ϸ��Ϣʧ��: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	
	@Override
	public boolean findArchivesUseOutInfosByRegisterID(int registerID,List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
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
				if (archivesUseOutInfoDao.findByRegisterID(registerID, archivesUseOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO�������õǼǱ�Ų�ѯ�������ó�ȥ��ϸ�б�ʧ��: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#findArchivesUseOutInfos(java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesUseOutInfosByQueryCondition(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition, DataPageInfo dataPageInfo, List<ArchivesUseOutInfo> archivesUseOutInfos, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		String querySQL = "";

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false) {
				pFlag = false;
			}
			
			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				//����ѯ��������ת����SQL����
				dataPageInfo.setPageSize(15);
				querySQL = getQuerySQL(archivesUseInfoQueryCondition);
System.out.println("querySQL:"+querySQL);
				if (archivesUseOutInfoDao.findByQueryCondition(querySQL, dataPageInfo, archivesUseOutInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "DAO�������õǼǱ�Ų�ѯ�������ó�ȥ��ϸ�б�ʧ��: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesUseOutInfoManageService#updateArchivesUseOutInfo(com.orifound.aiim.entity.ArchivesUseOutInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateShouldReturnDate(ArchivesUseOutInfo archivesUseOutInfo, ErrInfo pErrInfo) {
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
			
			if("".equals(archivesUseOutInfo.getArchivesBarcode())){
				pFlag = false;
				pErrInfo.getContent().append("����������Ƿ�Ϊ��");
			} 

			//����DAO�ӿ�
			if (pFlag) {
				pErrPos = 2;
				if (archivesUseOutInfoDao.updateShouldReturnDate(archivesUseOutInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݵ�����������µ����黹ʱ��ʧ��: ");
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
System.out.println(pErrInfo.toString());
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			//���پֲ�����
			throwable = null;
		}

		return pFlag;
	}
	

	/***
	 * �������ò�ѯ��������SQL
	 * @param archivesUseRegisterQueryCondition �������ò�ѯ����
	 * @return querySQL ���ع����SQL��䣬��and��ʼ 
	 */
	private String getQuerySQL(ArchivesUseInfoQueryCondition archivesUseInfoQueryCondition){
			String querySQL = "";	
		//	AND Name LIKE '%��%' 
		//  AND IDCardNo LIKE '33%'
		//	AND ArchivesID LIKE 'G01%' 
		//	AND Title LIKE '%T%' 
		//	AND Department LIKE '%��%' 
		//	AND A.BorrowFlag=1 
		//	AND PurposeID=4 
		//	AND UseDate>'2010-5-5' 
		//	AND UseDate<'2010-8-5'		
			System.out.println("useWay"+archivesUseInfoQueryCondition.getUseWayID());
			//ȫ��/�赵/�鵵�ж�
			if(archivesUseInfoQueryCondition.getUseWayID()==1){//�赵
				querySQL += " AND C.BorrowFlag = 1 ";
			}else if(archivesUseInfoQueryCondition.getUseWayID()==2){//�鵵
				querySQL += " AND C.BorrowFlag = 0 ";
			}
			System.out.println("useWay"+archivesUseInfoQueryCondition.getUseWayID());
			
			//֤���Ų�ѯ
			if (archivesUseInfoQueryCondition.getIDCardNo()!=null && !"".equals(archivesUseInfoQueryCondition.getIDCardNo().trim()) ) {
				querySQL += " AND IDCardNo LIKE '" + archivesUseInfoQueryCondition.getIDCardNo().trim() + "%'";
			}

			//������ʼʱ��
			if(archivesUseInfoQueryCondition.getUseDateBegin()!=null ){
				querySQL += " AND UseDate >= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseInfoQueryCondition.getUseDateBegin() )   + "'";
			}
			
			//���ý���ʱ��
			if(archivesUseInfoQueryCondition.getUseDateEnd()!=null ){
				querySQL += " AND UseDate <= '" + new SimpleDateFormat("yyyy-MM-dd").format(archivesUseInfoQueryCondition.getUseDateEnd() )   + "'";
			}
			
			//��������
			if(archivesUseInfoQueryCondition.getUserDepartment()!=null && !"".equals(archivesUseInfoQueryCondition.getUserDepartment().trim())){
				querySQL += " AND Department LIKE '%" + archivesUseInfoQueryCondition.getUserDepartment().trim() + "%'";
			}
			
			//����
			if(archivesUseInfoQueryCondition.getUserRealName()!=null && !"".equals(archivesUseInfoQueryCondition.getUserRealName().trim())){
				querySQL += " AND Name LIKE '%" + archivesUseInfoQueryCondition.getUserRealName().trim() + "%'";
			}
			
			//����
			if(archivesUseInfoQueryCondition.getArchivesID()!=null && !"".equals(archivesUseInfoQueryCondition.getArchivesID().trim())){
				querySQL += " AND ArchivesID LIKE '" + archivesUseInfoQueryCondition.getArchivesID().trim() + "%'";
			}
			
			//����
			if(archivesUseInfoQueryCondition.getTitle()!=null && !"".equals(archivesUseInfoQueryCondition.getTitle().trim())){
				querySQL += " AND Title LIKE '%" + archivesUseInfoQueryCondition.getTitle().trim() + "%'";
			}
			
			//����Ŀ��
			if(archivesUseInfoQueryCondition.getPurposeID()!=0){
				querySQL += " AND PurposeID = " + archivesUseInfoQueryCondition.getPurposeID();
			}
			
		return querySQL;
	}


}
