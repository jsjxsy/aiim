/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;

import com.orifound.aiim.bll.service.IArchivesInfoQueryService;
import com.orifound.aiim.bll.service.IUserDefinedSearchManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoSavedDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeCountInfo;
import com.orifound.aiim.entity.ArchivesTypeDataItem;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.aiim.entity.UserInfo;

/**
 * �����ۺϲ�ѯ����ʵ����
 *
 */
public class ArchivesInfoQueryServiceImpl implements IArchivesInfoQueryService
{
	
	/**
	 * ���캯��
	 */
	public ArchivesInfoQueryServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesInfoQueryServiceImpl(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * ��鵵���鵵��Ϣ��DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkInjection(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoSavedDao == null) {
				pFlag = false;
				pErrInfo.getContent().append("�����鵵��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
		}

		return pFlag;
	}
	
	/**
	 * �����鵵��Ϣ������ݷ��ʶ���
	 */
	private IArchivesInfoSavedDao archivesInfoSavedDao = null;

	/**
	 * ��ȡ����ֵ�������鵵��Ϣ������ݷ��ʶ���
	 * @return �����鵵��Ϣ������ݷ��ʶ���
	 */
	public IArchivesInfoSavedDao getArchivesInfoSavedDao() {
		return archivesInfoSavedDao;
	}

	/**
	 * ��������ֵ�������鵵��Ϣ������ݷ��ʶ���
	 * @param archivesInfoSavedDao �����鵵��Ϣ������ݷ��ʶ���
	 */
	public void setArchivesInfoSavedDao(IArchivesInfoSavedDao archivesInfoSavedDao) {
		this.archivesInfoSavedDao = archivesInfoSavedDao;
	}
	
	/**
	 * �û��Զ���������ѯ���������
	 */
	private IUserDefinedSearchManageService userDefinedSearchManageService = null;
		
	public IUserDefinedSearchManageService getUserDefinedSearchManageService() {
		return userDefinedSearchManageService;
	}
	
	public void setUserDefinedSearchManageService(IUserDefinedSearchManageService userDefinedSearchManageService) {
		this.userDefinedSearchManageService = userDefinedSearchManageService;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findArchivesInfoByBarcode(java.lang.String, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByBarcode(String archivesBarcode,
			ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findArchivesInfoByNBXH(com.orifound.aiim.entity.ArchivesType, int, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByNBXH(ArchivesType archivesType,
			int NBXH, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
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
				if (archivesInfoSavedDao.findByNBXH(NBXH, archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ڲ���Ų��ҵ�����Ϣʧ��: ");
				}
			}
			
			System.out.println("-->"+archivesInfo.getSecrecyID());
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#findQueryDataItems(java.util.List, java.util.LinkedHashMap, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findQueryDataItems(List<ArchivesType> archivesTypes,LinkedHashMap<String, ArchivesTypeDataItem> archivesTypeDataItems, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub425044
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#queryClassified(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
/*
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,ErrInfo pErrInfo)
	{
		//������֤
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StringBuilder querySQL = new StringBuilder(); 
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoSavedDao(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypes == null || archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}
			
			//����ѯ��������������޸�����
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoUseQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ѯ������Ϣʧ��: ");
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}


			//��֤����������Ϣ�Ƿ���ϵͳ�д���
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : archivesTypes) {
					if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
					}
				}				
			}
			
			getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo);
			
			//����DAO�ӿ�
			if (pFlag)
			{
				//if (archivesInfoSavedDao.queryClassified(userInfo, archivesType, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
				if (archivesInfoSavedDao.queryClassified(userInfo, archivesTypes, querySQL.toString(), dataPageInfo, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ѯʧ��: ");
				}
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
	*/
	
	
	/**
	 * ����whereSQL����ѯָ����������ĵ�������<br>
	 * whereSQL��ʽ��and a>b and c=23
	 */
	@Override
	public boolean queryClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,
			String querySQL,
			DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos,
			ErrInfo pErrInfo)
	{
		//������֤
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				pErrPos = 2;
				if (archivesTypes == null || archivesTypes.size() == 0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣδ��ʼ����");
				}
			}
			
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				pErrPos =3;
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣδ��ʼ����");
				}
			}		
			
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}

			
			//��֤����������Ϣ�Ƿ���ϵͳ�д���
			if (pFlag)
			{
				pErrPos = 5;
				for (ArchivesType archivesType : archivesTypes) {
					if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
					}
				}				
			}
			
			
			//����DAO�ӿ�
			if (pFlag)
			{
				pErrPos = 6;
				if (archivesInfoSavedDao.queryClassified(userInfo, archivesTypes, querySQL, dataPageInfo, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ѯʧ��: ");
				}
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
	
	
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findChildArchivesInfosByNBXH(int, com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findChildArchivesInfosByNBXH(int pNBXH,ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (archivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}

			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+archivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesType.getID());
				}
			}
			
			//����DAO�ӿڲ��Ҿ����ļ�
			if (pFlag)
			{
				if (archivesInfoSavedDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ��������Ϣʧ��: ");
				}
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
	
	

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoQueryService#queryCrossClassified(java.util.List, java.util.List, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean queryCrossClassified(UserInfo userInfo, List<ArchivesType> archivesTypes,List<ArchivesInfoQueryCondition> archivesInfoQueryConditions,List<ArchivesTypeCountInfo> archivesTypeCountInfos,StringBuilder querySQL,ErrInfo pErrInfo){
		boolean pFlag = true;
		IntegerEx countNum =new IntegerEx();		
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false){
				pFlag = false;
			}

			
			//����SQL��ѯ����
			if (pFlag) {
				pErrPos = 2;
				 if(getSqlForArchivesInfoInputQueryConditions(archivesInfoQueryConditions, querySQL, pErrInfo)==false){
					 pFlag = false;
					 pErrInfo.getContent().append("���쵵����Ϣ��¼��ѯ������SQLƬ��ʧ�ܡ�");			
				 }
			}
			
			
			//����DAO���ݷ���
			if (pFlag) {
				pErrPos = 3;
				for (ArchivesType archivesType : archivesTypes) {
					if(archivesInfoSavedDao.queryCountByClassified(userInfo, archivesType,querySQL, countNum, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("���ݲ�ѯ����ͳ��" + archivesType.getFullName() + "��ĵ�������ʧ�ܡ�");
					}else{
						//�������������ͳ����Ϣ
						ArchivesTypeCountInfo archivesTypeCountInfo = new ArchivesTypeCountInfo();
						archivesTypeCountInfo.setArchivesType(archivesType);
						archivesTypeCountInfo.setCountNum(countNum.getValue());
						archivesTypeCountInfo.setQuerySQL(querySQL.toString());
						if(countNum.getValue()>0){//���н�����ķ�����뵽�б���ȥ
							archivesTypeCountInfos.add(archivesTypeCountInfo);
						}						
						System.out.println("�������ѯ�����ݲ�ѯ����ͳ�Ƴ�ָ������ĵ��������ɹ���");
						
					}
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
System.out.println("errrorInfo: "+ pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	
	
	@Override
	public boolean queryCrossClassifiedByKeyWord(UserInfo userInfo, List<ArchivesType> archivesTypes, String keyWord, List<ArchivesTypeCountInfo> archivesTypeCountInfos,
			ErrInfo pErrInfo) {
		boolean pFlag = true;
		IntegerEx countNum =new IntegerEx();		
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		StringBuilder querySQL = null; 

		try {
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkInjection(pErrInfo) == false)
			{
				pFlag = false;
			}

			//����DAO���ݷ���
			if (pFlag) {
				pErrPos = 3;
				for (ArchivesType archivesType : archivesTypes) {
					querySQL = new StringBuilder();
					//��ȡSQL���
					getKeyQuerySQL(keyWord, archivesType.getDataItemsForUseQuery(), querySQL, pErrInfo);
					//���ݲ�ѯ����ͳ�Ƴ�ָ������ĵ�������
					if(archivesInfoSavedDao.queryCountByClassified(userInfo, archivesType, querySQL , countNum, pErrInfo)==false){
						pFlag = false;
						pErrInfo.getContent().append("���ݲ�ѯ����ͳ��" + archivesType.getFullName() + "��ĵ�������ʧ�ܡ�");
					}else{
						//�������������ͳ����Ϣ
						ArchivesTypeCountInfo archivesTypeCountInfo = new ArchivesTypeCountInfo();
						archivesTypeCountInfo.setArchivesType(archivesType);
						archivesTypeCountInfo.setCountNum(countNum.getValue());
						archivesTypeCountInfo.setQuerySQL(querySQL.toString());
						if(countNum.getValue()>0){//���н�����ķ�����뵽�б���ȥ
							archivesTypeCountInfos.add(archivesTypeCountInfo);
						}					
						System.out.println("�ؼ��ּ��������ݲ�ѯ����ͳ�Ƴ�ָ������ĵ��������ɹ���");
					}
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
System.out.println("errrorInfo: "+ pErrInfo.toString());
			}

			//���پֲ�����
			throwable = null;
		}
		return pFlag;
	}
	
	/**
	 * ���쵵����Ϣ��¼��ѯ������SQLƬ��
	 * 
	 * @param archivesInfoQueryConditions
	 *            ������¼��ѯ��������
	 * @param querySQL
	 *            ���ع���õĲ�ѯ����SQLƬ��
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private static boolean getSqlForArchivesInfoInputQueryConditions(List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, StringBuilder querySQL, ErrInfo pErrInfo)
	{

		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		

		try
		{
			//���û�����ݾͷ��ؿմ�
			if(archivesInfoQueryConditions == null ){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else if(archivesInfoQueryConditions.size()==0){
				System.out.println("archivesInfoQueryConditions--null");
				return true;
			}else{
				for (ArchivesInfoQueryCondition item : archivesInfoQueryConditions)
				{
					// ����Ƿ�Χ��ѯ��������Сֵ�����ֵ����
					if (item.getDataItem().getRangeQueryFlag())
					{
						// ���赥���ŵ����
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ��������Χ
							querySQL.append(" BETWEEN ");
							querySQL.append(item.getMinValue());
							querySQL.append(" AND ");
							querySQL.append(item.getMaxValue());
						}
						// ��Ҫ�����ŵ����
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ��������Χ
							querySQL.append(" BETWEEN ");
							querySQL.append("'");
							querySQL.append(item.getMinValue());
							querySQL.append("'");
							querySQL.append(" AND ");
							querySQL.append("'");
							querySQL.append(item.getMaxValue());
							querySQL.append("'");
						}
					}
					// ������Ƿ�Χ��ѯ������ֵ����
					else
					{
						// ���赥���ŵ����
						if (item.getDataItem().getColumnDataType() == EnumColumnDataType.ʵ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.����
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.����ֵ)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ������
							querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
							querySQL.append(item.getValue());
						}
						// ��Ҫ�����ŵ����
						else if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ�� || item.getDataItem().getColumnDataType() == EnumColumnDataType.�ַ���
								|| item.getDataItem().getColumnDataType() == EnumColumnDataType.�ı�)
						{
							// ƴ���߼����з� AND OR
							querySQL.append(item.getIsAND() ? " AND " : " OR ");
							// ƴ���ֶ���
							querySQL.append(item.getDataItem().getColumnName());
							// ƴ������
							if (item.getDataItem().getColumnDataType() == EnumColumnDataType.����ʱ��)
							{
								querySQL.append(" = "); // ��������ȱʡʹ�þ�ȷƥ��
								querySQL.append("'");
								querySQL.append(item.getValue());
								querySQL.append("'");
							}
							else
							{
								querySQL.append(" LIKE "); // �ַ�����ȱʡʹ��ģ��ƥ��
								querySQL.append("'%");
								querySQL.append(item.getValue());
								querySQL.append("%'");
							}
						}
					}
				}
			}
			
			
		}
		catch (Exception e)
		{
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		}
		finally
		{
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0)
			{
				StackTraceElement[] stackTraceElements = throwable.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null)
				{
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}
				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			throwable = null;
		}
		System.out.println("getSqlForArchivesInfoInputQueryConditions: querySQL:" + querySQL);
		return pFlag;
	}

	
	/**
	 * ��ȡ�ؼ��ּ�����SQL���
	 * @param keyWord �û�����Ĺؼ���
	 * @param dataItemsForUseQuery �����ڲ�ѯ���ֶ�
	 * @param querySQL	����SQL���
	 * @param pErrInfo ���ش�����Ϣ
	 * @return
	 */
	private boolean getKeyQuerySQL(String keyWord,LinkedHashMap<String, ArchivesTypeDataItem> dataItemsForUseQuery,StringBuilder querySQL, ErrInfo pErrInfo){
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			
			//�������Ϊ�գ����ѯ���е�����querySQLҲΪ�մ�
			if("".equals(keyWord.trim())){
				return true;
			}
			
			pErrPos = 1;
			//�������������������ϵļ����Ƿ��ѳ�ʼ��
			if(dataItemsForUseQuery==null){
				pFlag = false;
				pErrInfo.getContent().append("�����������������ϵļ���δ��ʼ��");
			}
			
			if (pFlag) {
				Iterator<String> columnNames = dataItemsForUseQuery.keySet().iterator();			
				int index = 0;//��¼�Ƿ��ǵ�һ��
				//querySQL��ʽ��AND (Title LIKE '%xx%' OR Ftm LIKE '%xx%')
				while(columnNames.hasNext()){
					String columnName = columnNames.next();
					index ++;
					if(index==1){
						querySQL.append( " AND ( " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}else{
						querySQL.append( " OR " + columnName + " LIKE " + " '%" + keyWord.trim() + "%' ");
					}
				}
				querySQL.append(")");
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
	
	
	
	
	
}
