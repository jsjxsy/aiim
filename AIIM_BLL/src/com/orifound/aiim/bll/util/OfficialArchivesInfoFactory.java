/**
 * 
 */
package com.orifound.aiim.bll.util;

import com.orifound.aiim.entity.OfficialArchivesInfo;
import com.orifound.aiim.entity.OfficialArchivesType;

/**
 * ���ĵ�����Ϣ���󹤳�
 *
 */
public class OfficialArchivesInfoFactory
{
	/**
	 * ����һ��ָ�����������µĵ�����Ϣ����<br>
	 * �Զ������õ�����Ϣ�����µ�������ϣ���������ǿ�ֵ���䱾���ȱʡֵ
	 * @param archivesTypeID ����������
	 * @return �߱�������ϵĵ�����Ϣ����
	 * @throws Exception 
	 */
	public static OfficialArchivesInfo newOfficialArchivsInfo(int officialArchivesTypeID) throws Exception
	{
		boolean pFlag = true;

		//������Ϣ
		StringBuilder pErrInfo=new StringBuilder();
		OfficialArchivesType officialArchivesType=null; //������������
		OfficialArchivesInfo officialArchivesInfo=null; //���������ĵ�����Ϣ����
		
		try
		{
			//��鹫�ĵ����������Ƿ�Ϊ0
			if (officialArchivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.append("���������ŷǷ�Ϊ0");
			}
			
			//���ϵͳ��ʼ��������Ĺ��ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}
			
			//���㹫�ĵ�������
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getOfficialArchivesTypes().containsKey(officialArchivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.append("ϵͳ�в����ڱ��Ϊ "+officialArchivesTypeID+" �ĵ�������");
				}
				else
				{
					//�����ȡ�Ĺ��ĵ���������Ϣ
					officialArchivesType=CommonUtil.getSystemInitializer().getOfficialArchivesTypes().get(officialArchivesTypeID);
					
					if (officialArchivesType.getDataItemsForAll()==null)
					{
						pFlag = false;
						pErrInfo.append("���������Ӧ��������ϷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��������������ϵĵ�����Ϣ����
			if (pFlag)
			{
				officialArchivesInfo=new OfficialArchivesInfo(officialArchivesType);
			}
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.append(e.toString());
		}
		finally
		{
			//���پֲ�����
			officialArchivesType=null;
			
			//�׳��쳣��Ϣ
			if (pFlag == false && pErrInfo.length() > 0)
			{
				throw new Exception(pErrInfo.toString());
			}
		}

		return officialArchivesInfo;
	}
	
	/**
	 * ����һ��ָ�����ĵ��������µĵ�����Ϣ���󣬲������ڲ��������ֵ<br>
	 * �Զ������ù��ĵ�����Ϣ�����µ�������ϣ���������ǿ�ֵ���䱾���ȱʡֵ
	 * @param officialArchivesTypeID ���ĵ���������
	 * @param pNBXH ���ĵ����ڲ����
	 * @return �߱�������ϵĹ��ĵ�����Ϣ����
	 * @throws Exception 
	 */
	public static OfficialArchivesInfo newOfficialArchivsInfo(int officialArchivesTypeID,int pNBXH) throws Exception
	{
		boolean pFlag = true;

		//������Ϣ
		StringBuilder pErrInfo=new StringBuilder();
		//���صĵ�����Ϣ����
		OfficialArchivesInfo officialArchivesInfo=null;
		
		try
		{
			if (pNBXH<=0)
			{
				pFlag = false;
				pErrInfo.append("�����ڲ���ŷǷ�Ϊ0");
			}
			
			if (pFlag)
			{
				officialArchivesInfo=newOfficialArchivsInfo(officialArchivesTypeID);
				if (officialArchivesInfo!=null)
				{
					officialArchivesInfo.setNBXH(pNBXH);
				}
				else 
				{
					pFlag = false;
					pErrInfo.append("�´����ĵ�����Ϣ����Ƿ�Ϊ�ա�");
				}
			}
			
		}
		catch (Exception e)
		{
			//�쳣����
			pFlag = false;
			pErrInfo.append(e.toString());
		}
		finally
		{
			//�׳��쳣��Ϣ
			if (pFlag == false && pErrInfo.length() > 0)
			{
				throw new Exception(pErrInfo.toString());
			}
		}

		return officialArchivesInfo;
	}
}
