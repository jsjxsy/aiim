/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.dal.dao.IAttachedFileUseRequestPassInfoDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.commons.acservice.IAttachedFileAccessControlService;

/**
 * ����ԭ�ĵ����ļ����ʿ��Ʒ���ʵ����
 *
 */
public class AttachedFileAccessControlServiceImpl implements IAttachedFileAccessControlService
{
	/**
	 * ���캯��
	 */
	public AttachedFileAccessControlServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public AttachedFileAccessControlServiceImpl(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao)
	{
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}

	/**
	 * ����ԭ������ͨ����Ϣ������ݷ��ʶ���
	 */
	private IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao = null;

	/**
	 * ��ȡ����ֵ������ԭ������ͨ����Ϣ������ݷ��ʶ���
	 * @return ����ԭ������ͨ����Ϣ������ݷ��ʶ���
	 */
	public IAttachedFileUseRequestPassInfoDao getAttachedFileUseRequestPassInfoDao()
	{
		return attachedFileUseRequestPassInfoDao;
	}

	/**
	 * ��������ֵ������ԭ������ͨ����Ϣ������ݷ��ʶ���
	 * @param attachedFileUseRequestPassInfoDao ����ԭ������ͨ����Ϣ������ݷ��ʶ���
	 */
	public void setAttachedFileUseRequestPassInfoDao(IAttachedFileUseRequestPassInfoDao attachedFileUseRequestPassInfoDao)
	{
		this.attachedFileUseRequestPassInfoDao = attachedFileUseRequestPassInfoDao;
	}
	
	/**
	 * ��鵵��ԭ������ͨ����Ϣ���DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (attachedFileUseRequestPassInfoDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("����ԭ������ͨ����Ϣ���DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.commons.acservice.IAttachedFileAccessControlService#checkArchivesAttachedFileACL(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean checkArchivesAttachedFileACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
	{
		boolean checkPass=false; //ACL���ͨ��
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		ErrInfo pErrInfo=new ErrInfo();

		int pArchivesTypeID=0;
		int pNBXH=0;
		int pUserID=0;
		IntegerEx pACLCount=new IntegerEx();
		
		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjection(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//ת����������
			if (pFlag)
			{
				pArchivesTypeID=Integer.parseInt(String.valueOf(archivesType));
				pNBXH=Integer.parseInt(String.valueOf(archivesInfoIdentity));
				pUserID=Integer.parseInt(String.valueOf(user));
			}
			
			//����DAO�ӿڽ��в�ѯ
			if (pFlag)
			{
				pErrPos = 2;
				if (attachedFileUseRequestPassInfoDao.findCountByRequestPassInfo(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯԭ������ACLʧ��: ");
				}
			}
			
			//���践��ֵ
			if (pFlag)
			{
				//������ڸ��û�����ָ������ԭ��ͨ������Ч��Ϣ�����ʾ���Է���ԭ����Ϣ
				if (pACLCount.getValue()>0)
				{
					checkPass=true;
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
				
				//�׳��쳣
				throw new Exception(pErrInfo.toShortString());
			}

			//���پֲ�����
			throwable = null;
		}

		return checkPass;
	}

}
