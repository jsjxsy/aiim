/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import com.orifound.aiim.dal.dao.IAppraisalUseScopesDetailDao;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.IntegerEx;
import com.orifound.commons.acservice.IUseScopesAccessControlService;

/**
 * ���ط��ʿ��Ʒ���ʵ����
 *
 */
public class UseScopesAccessControlServiceImpl implements IUseScopesAccessControlService
{
	
	/**
	 * ���캯��
	 */
	public UseScopesAccessControlServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public UseScopesAccessControlServiceImpl(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao)
	{
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}

	/**
	 * ���ؼ����ĵ�����ϸ��Ϣ�����ݷ��ʶ���
	 */
	private IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao = null;

	/**
	 * ��ȡ����ֵ�����ؼ����ĵ�����ϸ��Ϣ�����ݷ��ʶ���
	 * @return ���ؼ����ĵ�����ϸ��Ϣ�����ݷ��ʶ���
	 */
	public IAppraisalUseScopesDetailDao getAppraisalUseScopesDetailDao()
	{
		return appraisalUseScopesDetailDao;
	}

	/**
	 * ��������ֵ�����ؼ����ĵ�����ϸ��Ϣ�����ݷ��ʶ���
	 * @param AppraisalUseScopesDetailDao ���ؼ����ĵ�����ϸ��Ϣ�����ݷ��ʶ���
	 */
	public void setAppraisalUseScopesDetailDao(IAppraisalUseScopesDetailDao appraisalUseScopesDetailDao)
	{
		this.appraisalUseScopesDetailDao = appraisalUseScopesDetailDao;
	}
	
	/**
	 * ��黮�ؼ����ĵ�����ϸ��Ϣ��DAO����ע�루DAO Depandency Injection��
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
			if (appraisalUseScopesDetailDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("���ؼ����ĵ�����ϸ��Ϣ��DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	 * @see com.orifound.commons.acservice.IUseScopesAccessControlService#checkArchivesInfoUseScopesACL(java.lang.Object, java.lang.Object, java.lang.Object)
	 */
	@Override
	public boolean checkArchivesInfoUseScopesACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
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
				if (appraisalUseScopesDetailDao.findCountArchivesInfoNotInUseScopesACL(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯ����ACLʧ��: ");
				}
			}
			
			//���践��ֵ
			if (pFlag)
			{
				//ͨ�����ָ���ĵ����Ƿ�������û���Ȩ���ʵĻ��ص����б��У������������Ȩ���ʣ��������������Է���
				//��������ڣ���ʾָ���ĵ�����δ�����л��أ������Ѿ����ظ������û�ʹ��
				if (pACLCount.getValue()==0)
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

	@Override
	public int findCountArchivesInfoInUseScopesACL(Object archivesType, Object archivesInfoIdentity, Object user) throws Exception
	{
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
				if (appraisalUseScopesDetailDao.findCountArchivesInfoInUseScopesACL(pUserID, pArchivesTypeID, pNBXH, pACLCount, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "��ѯָ���û��Ļ���ACLʧ��: ");
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

		return pACLCount.getValue();
	}

}
