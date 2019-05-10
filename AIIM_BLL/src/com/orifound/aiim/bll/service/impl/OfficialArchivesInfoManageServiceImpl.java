package com.orifound.aiim.bll.service.impl;

import java.util.Date;
import java.util.List;


import com.orifound.aiim.bll.service.IOfficialArchivesInfoManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IOfficialArchivesInfoDao;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumOfficialArchivesInfoTableType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesInfoQueryCondition;
import com.orifound.aiim.entity.OfficialArchivesType;

public class OfficialArchivesInfoManageServiceImpl
		implements IOfficialArchivesInfoManageService {
	/**
	 * ���ĵ����ǼǱ� �����ݷ��ʶ���
	 */
	private IOfficialArchivesInfoDao officialArchivesInfoDao = null;

	/**
	 * ��ȡ����ֵ�����ĵ����ǼǱ� �����ݷ��ʶ���
	 * 
	 * @return ���ĵ����ǼǱ� �����ݷ��ʶ���
	 */
	public IOfficialArchivesInfoDao getOfficialArchivesInfoDao() {
		return officialArchivesInfoDao;
	}

	/**
	 * ��������ֵ�����ĵ����ǼǱ� �����ݷ��ʶ���
	 * 
	 * @param officialArchivesInfoDao
	 *            ���ĵ����ǼǱ� �����ݷ��ʶ���
	 */
	public void setOfficialArchivesInfoDao(
			IOfficialArchivesInfoDao officialArchivesInfoDao) {
		this.officialArchivesInfoDao = officialArchivesInfoDao;
	}

	/**
	 * ���캯��
	 */
	public OfficialArchivesInfoManageServiceImpl() {

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public OfficialArchivesInfoManageServiceImpl(
			IOfficialArchivesInfoDao officialArchivesInfoDao) {
		this.officialArchivesInfoDao = officialArchivesInfoDao;
	}

	/**
	 * ���OfficialArchivesInfo��DAO����ע�루DAO Depandency Injection��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForOfficialArchivesInfo(ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (officialArchivesInfoDao == null) {
				pFlag = false;
				pErrInfo.getContent().append(
						"OfficialArchivesInfo��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
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
	 * ���OfficialArchivesInfo��DAO����ע�루DAO Depandency Injection��
	 * @officialArchivesType ���ĵ�������
	 * @enumOfficialArchivesInfoTableType ���ĵ���������Ϣ������ö��
	 * @officialArchivesInfo ���ĵ����ǼǱ�ʵ����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean deleteOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			
			//��鵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumOfficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ͷǷ�Ϊ�գ�������ȷɾ���ĵ����ǰ������ļ������Ǿ����ļ���������");
				}
			}
			
			//��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					//����ڲ�����Ƿ���ֵ
					if (officialArchivesInfo.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
					}
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
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
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+officialArchivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			
			//TODO: ��ʵ�֣�ɾ��ԭ�ĵ����ļ���Ϣ������ԭ�ĵ����ļ�ɾ����¼��ȷ����̨�����ܹ���ȡ����ɾ�������ļ���
			
			//�����ܹ�ɾ�������鵵�������¼
			if (pFlag)
			{
				if (officialArchivesInfoDao.deleteParentAndChild(officialArchivesType, officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ��������Ϣʧ��: ");
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

	@Override
	public boolean findOfficialArchivesInfoByID(int pID,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean findOfficialArchivesInfos(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,List<OfficialArchivesInfoQueryCondition> officialArchivesInfoQueryConditions, DataPageInfo dataPageInfo,List<OfficialArchivesInfo> officialArchivesInfos,
			ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			
			//����ѯ��������������޸�����
			if (pFlag)
			{
				if (CommonUtil.checkOfficialArchivesInfoInputQueryConditions(officialArchivesInfoQueryConditions, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "����ѯ������Ϣʧ��: ");
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
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
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+officialArchivesType.getID()+" �ĵ�������");
				}
				else
				{
					//���µ������������
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (officialArchivesInfoDao.find(enumOfficialArchivesInfoTableType, officialArchivesType, officialArchivesInfoQueryConditions, dataPageInfo, officialArchivesInfos, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ȡָ��������״̬�ĵ�����Ϣʧ��: ");
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

	/**
	 *@userID �ý���ID��
     *@officialArchivesType �����ĵ�����
	 *@enumOfficialArchivesInfoType ���ĵ���������Ϣ������ö��
	 *@officialArchivesInfo ���ĵ�����Ϣ
	 *@ErrInfo pErrInfo ��������Ϣ
	 */
	public boolean saveOfficialArchivesInfo(int userID,
			OfficialArchivesType officialArchivesType,
			EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		try {
			pErrPos = 1;
			// ����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesInfoDAO����ע��ʧ��:");
			}

			// ���ù��ĵ�����������Ա�����Ϣ
			if (pFlag) {
				if (setValueForUserID(userID, officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù��ĵ�����������Ա�����Ϣʧ��: ");
				}
			}

			// ���ù��ĵ�����������Ϣ
			if (pFlag) {
				if (officialArchivesInfo.getDocTypeID() <= 0) {
					officialArchivesInfo.setDocTypeID(officialArchivesType
							.getID());
				}
			}

			// �����������ֶ�ֵ�������Ƿ���ȷ�����������ֶβ��ܹ�������ĸ�ַ������͵�ֵ
			if (pFlag) {
				if (checkOfficialArchivesInfoDataItemValueType(
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ�������ֶ�ֵ���ͼ��δͨ��: ");
				}
			}

			// ���Ҫ����ĵ�����Ϣ��ϵͳ�����������ֶ�ֵ��������
			if (pFlag) {
				pErrPos = 2;
				if (checkOfficialArchivesInfoSystemDataItemsForSave(
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ������: ");
				}
			}

			// ���õ��������ڽ�ֹ���
			if (pFlag) {
				pErrPos = 3;
				if (setValueForRetentionEndYear(officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ��������ڽ�ֹ���ʧ��: ");
				}
			}
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkOfficialArchivesType(officialArchivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}

			// ���ò�������������ֶε��ı�ֵ����������Ÿ�ʽ���ı��������ļ���Ÿ�ʽ���ı���ʵ�����š����������ı��������ܼ��ı��������γɲ������ơ��Լ������Զ��������������ı��ֶ�ֵ��
			if (pFlag) {
				pErrPos = 5;
				if (setValueForAssociateDataItem(officialArchivesType,
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù����������ֶε��ı�ֵʧ��: ");
				}
			}

			// ����DAO�ӿڱ��浱ǰ������Ϣ
			if (pFlag) {
				pErrPos = 5;
				
				if (officialArchivesInfoDao.save(officialArchivesType,
						officialArchivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵����Ϣʧ��: ");
				}
			}

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}
	
	/**
	 * ��鵵����Ϣ����Ƿ���ȷ
	 * @param enumArchivesInfoType ������Ϣ���
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkOfficialArchivesInfoType(EnumOfficialArchivesInfoTableType enumOffficialArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos =1;
			if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����Ϣ���Ƿ�Ϊ�ա�");
			}else if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.���ĵ����ǼǱ�)
			{
				pErrPos =3;
//				officialArchivesInfo.setParentFlag(false);
//				if (officialArchivesInfo.getParentNBXH()>0)
//				{
//					pFlag = false;
//					pErrInfo.getContent().append("�ļ���������Ӧ�ô�������������ڲ������Ϣ��");
//				}
			}
			else if (enumOffficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.���ĵ���ԭ����Ϣ��)
			{
				pErrPos =4;
//				officialArchivesInfo.setParentFlag(false);
//				if (officialArchivesInfo.getParentNBXH()<=0)
//				{
//					pFlag = false;
//					pErrInfo.getContent().append("�����ļ������������ڲ���ŷǷ�Ϊ0");
//				}
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
	 * ���¹��ĵ�����Ϣʱ�ĵ�������������������Լ��
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkOfficialArchivesInfoSystemDataItemsForUpdate(OfficialArchivesInfo officialArchivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵵����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (officialArchivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
			}
			
			
			
			//����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (officialArchivesInfo.getNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//��鱣�����ޱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ0");
				}
			}

			//��鵵���ܼ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ܼ���ŷǷ�Ϊ0");
				}
			}
			
			//��鵵���γ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵���γɲ��ű���Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γɲ��ű�ŷǷ�Ϊ0");
				}
			}
			
			//����ҳ��Ϊnullʱʹ��ȱʡֵ0���
			if (pFlag)
			{
				if (officialArchivesInfo.getPageSum()==0)
				{
					officialArchivesInfo.setPageSum(0);
				}
			}

			//�����鵵����Ϊnull����ʹ��ȱʡֵ��ǰʱ�����
			if (pFlag)
			{
				if (officialArchivesInfo.getRegDate()==null)
				{
					officialArchivesInfo.setRegDate(new Date());
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
	
	/**
	 * ���¹��ĵ�����Ϣʱ�ĵ�������������������Լ��
	 * @param officialArchivesType ���ĵ�����Ϣ����
	 * @param officialArchivesInfo ���ĵ�����Ϣ�Զ���
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean updateOfficialArchivesInfo(OfficialArchivesType officialArchivesType,EnumOfficialArchivesInfoTableType enumOfficalArchivesInfoTableType,OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			
			//��鹫�ĵ�����Ϣ����Ƿ�Ϊ�գ������ð����־
			if (pFlag)
			{
				pErrPos = 2;
				if (checkOfficialArchivesInfoType(enumOfficalArchivesInfoTableType, officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ĵ�����Ϣ�����ʧ��: ");
				}
			}
			
			
			//�����������ֶ�ֵ�������Ƿ���ȷ�����������ֶβ��ܹ�������ĸ�ַ������͵�ֵ
			if (pFlag)
			{
				pErrPos = 3;
				if (checkOfficialArchivesInfoDataItemValueType(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ĵ�����Ϣ�������ֶ�ֵ���ͼ��δͨ��: ");
				}
			}
			
			//���Ҫ����ĵ�����Ϣ��ϵͳ�����������ֶ�ֵ��������
			if (pFlag)
			{
				pErrPos = 4;
				if (checkOfficialArchivesInfoSystemDataItemsForUpdate(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ĵ�����Ϣ������: ");
				}
			}
			
			//���õ��������ڽ�ֹ���
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueForRetentionEndYear(officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù��ĵ��������ڽ�ֹ���ʧ��: ");
				}
			}
			
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 6;
				if (CommonUtil.checkOfficialArchivesType(officialArchivesType.getID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ĵ��������źϷ��Լ��ʧ��: ");
				}
				else
				{
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//���ò�������������ֶε��ı�ֵ������ʵ�����š����������ı��������ܼ��ı��������γɲ������ơ��Լ������Զ��������������ı��ֶ�ֵ��
			if (pFlag)
			{
				pErrPos = 7;
				if (setValueForAssociateDataItem(officialArchivesType,officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù����������ֶε��ı�ֵʧ��: ");
				}
			}
			
			//����DAO�ӿڸ��µ�ǰ������Ϣ
			if (pFlag)
			{
				pErrPos = 8;
				if (officialArchivesInfoDao.update(officialArchivesType, officialArchivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵����Ϣʧ��: ");
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

	/**
	 * ��鹫�ĵ�����Ϣ��������ֵ���͵���ȷ��
	 * 
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkOfficialArchivesInfoDataItemValueType(
			OfficialArchivesInfo officialarchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ��鹫�ĵ�����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (officialarchivesInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����Ϣ�Ƿ�Ϊ�ա�");
			}

			// ����������ֶ�ֵ�����Ƿ�Ϊ��
			if (pFlag) {
				if (officialarchivesInfo.getRowFieldsValues() == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ֶ�ֵ���ϷǷ�Ϊ�ա�");
				}
			}

			// �����ֶ�ֵ������һ��飨�ַ������ı����Ϳ��Բ��ؼ�飬�������֣��������ͱ����飩
			if (pFlag) {
				for (FieldValue item : officialarchivesInfo
						.getRowFieldsValues().values()) {
					// ����ǿ�ֵ�򲻼��
					if (item.getValue() == null) {
						continue;
					}

					// �м����������������飨��Ϊ����¼�����������������ֶΣ����ҿ��ܻḳ��մ�ֵ���ǺϷ��ģ�������ݴ������
					if (item.getSystemDataItemType() == EnumSystemDataItem.�ڲ����
							|| item.getSystemDataItemType() == EnumSystemDataItem.����ҳ��) {

						continue;
					}

					// if (item.getColumnDataType() == EnumColumnDataType.�ַ��� ||
					// item.getColumnDataType() == EnumColumnDataType.�ı�
					if (item.getColumnDataType() == EnumColumnDataType.����ʱ��) {
						if (CommonUtil.isDate(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " ����������ֵ����ǰֵΪ: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.ʵ��) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " ��������ֵ����ǰֵΪ: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.����ֵ) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " ������0��1����ǰֵΪ: "
											+ item.getValue());
						}
					} else if (item.getColumnDataType() == EnumColumnDataType.����) {
						if (CommonUtil.isDouble(item.getValue()) == false) {
							pFlag = false;
							pErrInfo.getContent().append(
									item.getDisplayText() + " ��������������ǰֵΪ: "
											+ item.getValue());
						}
					}

					// ����һ�����δͨ��������ѭ��
					if (pFlag == false) {
						break;
					}
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}

	/**
	 * �������ĵ�����Ϣʱ�ĵ�������������������Լ����ȱʡֵ����
	 * 
	 * @param archivesInfo
	 *            ������Ϣ����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkOfficialArchivesInfoSystemDataItemsForSave(
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ��鹫�ĵ�����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (officialArchivesInfo == null) {
				pFlag = false;
				pErrInfo.getContent().append("���ĵ�����Ϣ�Ƿ�Ϊ�ա�");
			}
			if (pFlag) {
				if (officialArchivesInfo.getDocNo() == null) {
					pFlag = false;
					pErrInfo.getContent().append("���ĵ�����Ϣ���ĺŷǷ�Ϊ��!");
				}
			}

			// ��鵵��������Ϣ�Ƿ�Ϊ��
			if (pFlag) {
				if (officialArchivesInfo.getDocTypeID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ĵ��������ŷǷ�Ϊ0");
				}
			}

			// ��鱣�����ޱ���Ƿ�Ϊ��
			if (pFlag) {
				if (officialArchivesInfo.getRetentionPeriodID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ0");
				}
			}

			// ��鵵���ܼ�����Ƿ�Ϊ��
			if (pFlag) {
				if (officialArchivesInfo.getSecrecyID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ܼ���ŷǷ�Ϊ0");
				}
			}

			// ��鵵���γ�����Ƿ�Ϊ��
			if (pFlag) {
				if (officialArchivesInfo.getFormationYear() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ�ա�");
				}
			}

			// ��鵵���γɲ��ű���Ƿ�Ϊ��
			if (pFlag) {
				if (officialArchivesInfo.getFormationDepartmentID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γɲ��ű�ŷǷ�Ϊ0");
				}
			}

			// ����ȱʡֵ��ǿ����ʾ��ֵ������ȱʡֵ����Ϊget�����ֶ�ֵ��ʱ���н���ת��������valueֵ��һ��������ֵ��

			// ����ҳ��Ϊnullʱʹ��ȱʡֵ0���
			if (pFlag) {
				if (officialArchivesInfo.getPageSum() == 0) {
					officialArchivesInfo.setPageSum(0);
				}
			}
			// �����鵵����Ϊnull����ʹ��ȱʡֵ��ǰʱ�����
			if (pFlag) {
				if (officialArchivesInfo.getRegDate() == null) {
					officialArchivesInfo.setRegDate(new Date());
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}

	/**
	 * ���ò����������ֶ�ֵ��Ӧ���ı����ֶ�ֵ<br>
	 * ���籣�������ı����������ܼ��ı������γɲ����ı����ȵ�<br>
	 * ע�⣺����������Ÿ�ʽ���ı����������ļ���Ÿ�ʽ���ı���<br>
	 * ���øú���֮ǰӦ���ȶԵ�����Ϣ�����������Լ�鴦��
	 * 
	 * @param archivesType
	 *            ���������ĵ���������Ϣ
	 * @param archivesInfo
	 *            ������Ϣ����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForAssociateDataItem(
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (officialArchivesInfo.getRowFieldsValues() == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
			} else {
				if (officialArchivesInfo.getRowFieldsValues().size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
				}
			}

			// ���ϵͳ��ʼ�������������Դ�����Ƿ���ֵ
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getDataSources() == null) {
					pFlag = false;
					pErrInfo.getContent().append(
							"ϵͳ��ʼ�����������Դ���ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
			}
			// ѭ�����������й�����Ӧ�ı��ֶε�������������Ӧ���ı��ֶ�ֵ���ܽ��и�ֵ����
			if (pFlag) {
				for (FieldValue item : officialArchivesInfo
						.getRowFieldsValues().values()) {
					// ���õ�����������ֶ�ֵ
					if (item.getSystemDataItemType() == EnumSystemDataItem.����������) {
						pErrPos = 6;
						// officialArchivesInfo.setArchivesTypeCode(officialArchivesType.getFullCode());
					}

					// ���ñ��������ı��ֶ�ֵ
					else if (item.getSystemDataItemType() == EnumSystemDataItem.�������ޱ��) {
						pErrPos = 7;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText("�������ޱ��", item
								.getDataSourceID(), "��������",
								officialArchivesInfo.getRetentionPeriodID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent()
									.insert(0, "��ȡ�������ޱ�Ŷ�Ӧ���ı�ʧ��: ");
						} else {
							// ���ñ��������ı�ֵ
							officialArchivesInfo
									.setRetentionPeriodText(dataSourceItemText
											.toString());
						}
					}

					// ���õ����ܼ��ı��ֶ�ֵ
					else if (item.getSystemDataItemType() == EnumSystemDataItem.�����ܼ����) {
						pErrPos = 8;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText("�����ܼ����", item
								.getDataSourceID(), "�����ܼ�",
								officialArchivesInfo.getSecrecyID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent()
									.insert(0, "��ȡ�����ܼ���Ŷ�Ӧ���ı�ʧ��: ");
						} else {
							// ���õ����ܼ��ı�ֵ
							officialArchivesInfo
									.setSecrecyText(dataSourceItemText
											.toString());
						}// ���õ����γɲ����ı��ֶ�ֵ
					}else if (item.getSystemDataItemType() == EnumSystemDataItem.�����γɲ��ű��) {
						pErrPos = 9;
						StringBuilder dataSourceItemText = new StringBuilder();
						if (getDataSourceItemText(
								"�����γɲ��ű��",
								item.getDataSourceID(),
								"�����γɲ���",
								officialArchivesInfo.getFormationDepartmentID(),
								dataSourceItemText, pErrInfo) == false) {
							pFlag = false;
							pErrInfo.getContent().insert(0,
									"��ȡ�����γɲ��ű�Ŷ�Ӧ���ı�ʧ��: ");
						} else {
							// ���õ����γɲ��������ı�ֵ
							officialArchivesInfo
									.setFormationDepartment(dataSourceItemText
											.toString());
						}
					}

					// �����������ϵͳ��������Դ�ֶΣ����Ҵ�����������ı��ֶΣ���ֱ��ʹ������Դ��ȡ�ı�ֵ
					else if (item.getAssociateTextColumnName() != null) {
						if (item.getAssociateTextColumnName().length() > 0) {
							// ������������й������ı��ֶΣ������������ֵΪnull����Ҫ�ݴ���ʾ���޷��������Ӧ���ı��ֶ�ֵ
							if (item.getValue() == null) {
								pFlag = false;
								pErrInfo.getContent().append(
										"�����" + item.getDisplayText() + "�������˹������ı��ֶΣ�" + item.getAssociateTextColumnName()
												+ "������ֵȴ�Ƿ�Ϊ�գ��޷��������Ӧ���ı��ֶ�ֵ��������δ���������Ч�ĸ�ֵ������ڵ��������������ֵ���δ��������ȱʡֵ��");
							} else {
								pErrPos = 10;
								StringBuilder dataSourceItemText = new StringBuilder();
								if (getDataSourceItemText(item.getDisplayText(), item.getDataSourceID(), item.getDisplayText(), Integer.valueOf(item.getValue()),
										dataSourceItemText, pErrInfo) == false) {
									pFlag = false;
									pErrInfo.getContent().insert(0, "��ȡ" + item.getDisplayText() + "��Ӧ���ı�ʧ��: ");
								} else {
									// ���ö�Ӧ�ı�ֵ
									if (officialArchivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName())) {
										officialArchivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName()).setValue(dataSourceItemText.toString());
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
					tempBuilder.append(" ErrPos: ");
					tempBuilder.append(pErrPos);
					tempBuilder.append(", ");
				}

				pErrInfo.getContent().insert(0, tempBuilder.toString());
				tempBuilder = null;
			}

			// ���پֲ�����
			officialArchivesType = null;
			throwable = null;
		}

		return pFlag;
	}

	/**
	 * ���ñ����ڽ�ֹ���<br>
	 * ���ݵ����γ���Ⱥͱ��������Զ����㲢�����䱣���ڽ�ֹ���
	 * 
	 * @param archivesInfo
	 *            ������Ϣ����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForRetentionEndYear(
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int retentionYears = 0;// �������ޱ�Ŷ�Ӧ��ʵ�ʱ�������

		try {
			// ����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (officialArchivesInfo.getRowFieldsValues() == null) {
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ�ա�");
			} else {
				if (officialArchivesInfo.getRowFieldsValues().size() == 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ�ա�");
				}
			}

			// ��鵵���γ�����Ƿ�Ϊ0
			if (pFlag) {
				if (officialArchivesInfo.getFormationYear() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ0");
				}
			}

			// ��鱣�������ֵ���Ϣ
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getRetentionPeriods() == null) {
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ�����еı��������ֵ���Ϣ�Ƿ�Ϊ�գ����ȳ�ʼ����");
				}
			}

			// ���ݱ������ޱ�ż��㱣������
			if (pFlag) {
				if (officialArchivesInfo.getRetentionPeriodID() <= 0) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ�ա�");
				}
				// ���㱣�����ޱ�Ŷ�Ӧ��ʵ�ʱ�������
				else {
					pErrPos = 2;
					if (CommonUtil.getSystemInitializer().getRetentionPeriods().containsKey(officialArchivesInfo.getRetentionPeriodID()) == false) {
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ�����ڱ������ޱ��Ϊ " + officialArchivesInfo.getRetentionPeriodID() + " �Ķ��塣");
					} else {
						retentionYears = CommonUtil.getSystemInitializer().getRetentionPeriods().get(officialArchivesInfo.getRetentionPeriodID()).getTotalYears();
					}
				}
			}

			// ���ݵ����γ���Ⱥͱ��������Զ����㲢�����䱣���ڽ�ֹ���
			if (pFlag) {
				pErrPos = 3;
				int retentionEndYear = officialArchivesInfo.getFormationYear()
						+ retentionYears;
				officialArchivesInfo.setRetentionEndYear(retentionEndYear);
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}

	/**
	 * ��ȡָ�����ֶ�ֵ���������Դ���Ŷ�Ӧ�ĵ��ı�ֵ
	 * 
	 * @param dataItemName
	 *            ����������
	 * @param dataSourceID
	 *            ����Դ���
	 * @param dataSourceName
	 *            ����Դ����
	 * @param dataSourceItemID
	 *            ����Դ����
	 * @param dataSourceItemText
	 *            ���ػ�ȡ�ɹ�������Դ���ı�
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getDataSourceItemText(String dataItemName,
			int dataSourceID, String dataSourceName, int dataSourceItemID,
			StringBuilder dataSourceItemText, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 7;
			if (dataSourceID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append(dataItemName + " �����������Դδ���á�");
			} else {
				if (CommonUtil.getSystemInitializer().getDataSources()
						.containsKey(dataSourceID) == false) {
					pFlag = false;
					pErrInfo.getContent().append(
							"ϵͳ����Դ�ֵ��в����� " + dataSourceName + " ����Դ��");
				}
			}

			// �����Ӧ����Դ��
			if (pFlag) {
				if (CommonUtil.getSystemInitializer().getDataSources().get(
						dataSourceID).getDataSourceItems() == null) {
					pFlag = false;
					pErrInfo.getContent().append(
							dataSourceName + " ����Դ�����Ա���ϷǷ�Ϊ�ա�");
				} else {
					if (CommonUtil.getSystemInitializer().getDataSources().get(
							dataSourceID).getDataSourceItems().containsKey(
							dataSourceItemID) == false) {
						pFlag = false;
						pErrInfo.getContent().append(
								dataSourceName + " ����Դ�����Ա�в����ڱ��Ϊ "
										+ dataSourceItemID + " ���");
					} else {
						// ��������Դ���Ŷ�Ӧ���ı�ֵ
						dataSourceItemText.append(CommonUtil
								.getSystemInitializer().getDataSources().get(
										dataSourceID).getDataSourceItems().get(
										dataSourceItemID).getName());
					}
				}
			}
		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}

	/**
	 * ���ù��ĵ�����������Ա�����Ϣ<br>
	 * ֱ�Ӷ�ָ���ĵ�����Ϣ�Ķ�Ӧ���û�������Ը�ֵ
	 * 
	 * @param userID
	 *            ���ĵ�����������Ա���
	 * @param officialArchivesInfo
	 *            ���ĵ�����Ϣ����
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForUserID(int userID,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			// ����û�����Ƿ���ֵ
			pErrPos = 1;
			if (userID <= 0) {
				pFlag = false;
				pErrInfo.getContent().append("��������Ĺ�����Ա��ŷǷ�Ϊ�ա�");
			}else {
				officialArchivesInfo.setRegUserID(userID);
				System.out.println("user-->id-->"+officialArchivesInfo.getRegUserID());
			}

		} catch (Exception e) {
			// �쳣����
			pFlag = false;
			pErrInfo.getContent().append(e.toString());
			pErrInfo.setException(e);
		} finally {
			// ƴ����ϸ�Ĵ���������Ϣ����������/������/����λ��
			if (pFlag == false && pErrInfo.getContent().length() > 0) {
				StackTraceElement[] stackTraceElements = throwable
						.getStackTrace();
				StringBuilder tempBuilder = new StringBuilder(
						stackTraceElements[0].getClassName());
				tempBuilder.append(".");
				tempBuilder.append(stackTraceElements[0].getMethodName());
				tempBuilder.append("-->");

				// ��������쳣��������Ҫ�ڴ�����Ϣ�м������λ�ñ����Ϣ
				if (pErrInfo.getException() != null) {
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

		return pFlag;
	}

	/**
	 * ���ù��ĵ�����������Ա�����Ϣ<br>
	 * ֱ�Ӷ�ָ���ĵ�����Ϣ�Ķ�Ӧ���û�������Ը�ֵ
	 * 
	 * @param pNBXH
	 *            ���ĵ������ڲ����
	 * @param officialArchivesType
	 *            ���ĵ���������
	 * @param  officialArchivesInfo
	 *            ���ĵ�����Ϣ
	 * @param pErrInfo
	 *            ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean findOfficialArchivesInfoByNBXH(int pNBXH,
			OfficialArchivesType officialArchivesType,
			OfficialArchivesInfo officialArchivesInfo, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鹫�ĵ��������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ���������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���ĵ��������ŷǷ�Ϊ0");
					}
				}
			}
			
			//����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (pNBXH<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ĵ����ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ������Ĺ��ĵ������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ��ʼ������Ĺ��ĵ������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}

			//��ȡ���ĵ���������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(officialArchivesType.getID())==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ�в����ڱ��Ϊ "+officialArchivesType.getID()+" �Ĺ��ĵ�������");
				}
				else
				{
					//���µ������������
					officialArchivesType = CommonUtil.getSystemInitializer()
					.getOfficialArchivesTypes().get(officialArchivesType.getID());
				}
			}
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (officialArchivesInfoDao.findByNBXH(pNBXH, officialArchivesType,
						officialArchivesInfo, pErrInfo)==false)
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

	@Override
	public boolean deleteOfficialArchivesInfos(OfficialArchivesType officialArchivesType, EnumOfficialArchivesInfoTableType enumOfficialArchivesInfoTableType,
			List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//��鵵�������Ƿ��и�ֵ
			if (pFlag)
			{
				if (officialArchivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					if (officialArchivesType.getID()==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
					}
				}
			}
			
			//��鵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumOfficialArchivesInfoTableType==EnumOfficialArchivesInfoTableType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ͷǷ�Ϊ�գ�������ȷɾ���ĵ����ǰ������ļ������Ǿ����ļ���������");
				}
			}
			
			//��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (officialArchivesInfos==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
				}
				else 
				{
					//����ڲ�����Ƿ���ֵ
					if (officialArchivesInfos.size()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
					}
				}
			}
			
			//���õ���ɾ���Ľӿڷ�����ѭ�����ɾ��
			if (pFlag)
			{
				for (OfficialArchivesInfo item : officialArchivesInfos)
				{
					if (deleteOfficialArchivesInfo(officialArchivesType, enumOfficialArchivesInfoTableType, item, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "ɾ��ָ��������Ϣʧ��: ");
						break; //��һ�����������ѭ��
					}
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

	@Override
	public boolean findAll(OfficialArchivesType offcialArchivesType, List<OfficialArchivesInfo> officialArchivesInfos, ErrInfo pErrInfo) {
		// TODO Auto-generated method stub
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForOfficialArchivesInfo(pErrInfo) == false) {
				pFlag = false;
				pErrInfo.getContent().insert(0,"OfficialArchivesInfo����ҵ���daoע��ʧ��!");
			}

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				if (offcialArchivesType == null) {
					pFlag = false;
					pErrInfo.getContent().append("offcialArchivesType�Ƿ�Ϊ��!");
				}
			}
			if (pFlag) {
				if (officialArchivesInfoDao.findAll(offcialArchivesType, officialArchivesInfos, pErrInfo)==false) {
					pFlag=false;
					pErrInfo.getContent().insert(0,"officialArchivesInfoDao��ѯ���м�¼ʧ��");
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

	
}
