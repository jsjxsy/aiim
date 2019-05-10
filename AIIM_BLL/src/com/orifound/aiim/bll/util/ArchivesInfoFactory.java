/**
 * 
 */
package com.orifound.aiim.bll.util;

import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesType;

/**
 * ������Ϣ���󹤳�
 *
 */
public class ArchivesInfoFactory
{
	/**
	 * ����һ��ָ�����������µĵ�����Ϣ����<br>
	 * �Զ������õ�����Ϣ�����µ�������ϣ���������ǿ�ֵ���䱾���ȱʡֵ
	 * @param archivesTypeID ����������
	 * @return �߱�������ϵĵ�����Ϣ����
	 * @throws Exception 
	 */
	public static ArchivesInfo newArchivsInfo(int archivesTypeID) throws Exception
	{
		boolean pFlag = true;

		//������Ϣ
		StringBuilder pErrInfo=new StringBuilder();
		ArchivesType archivesType=null; //������������
		ArchivesInfo archivesInfo=null; //���������ĵ�����Ϣ����
		
		try
		{
			//��鵵���������Ƿ�Ϊ0
			if (archivesTypeID<=0)
			{
				pFlag = false;
				pErrInfo.append("���������ŷǷ�Ϊ0");
			}
			
			//���ϵͳ��ʼ��������ĵ������༯���Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getArchivesTypes() == null)
				{
					pFlag = false;
					pErrInfo.append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
				else
				{
					if (CommonUtil.getSystemInitializer().getArchivesTypes().size() == 0)
					{
						pFlag = false;
						pErrInfo.append("ϵͳ��ʼ���������״�������༯�ϷǷ�Ϊ�գ���ȷ��ϵͳ���ݿ��д��ڵ��������ֵ���Ϣ��");
					}
				}
			}
			
			//���㵵������
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getPlaneArchivesTypes().containsKey(archivesTypeID)==false)
				{
					pFlag = false;
					pErrInfo.append("ϵͳ�в����ڱ��Ϊ "+archivesTypeID+" �ĵ�������");
				}
				else
				{
					//�����ȡ�ĵ���������Ϣ
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesTypeID);
					
					if (archivesType.getDataItemsForAll()==null)
					{
						pFlag = false;
						pErrInfo.append("���������Ӧ��������ϷǷ�Ϊ�ա�");
					}
				}
			}
			
			//��������������ϵĵ�����Ϣ����
			if (pFlag)
			{
				archivesInfo=new ArchivesInfo(archivesType);
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
			archivesType=null;
			
			//�׳��쳣��Ϣ
			if (pFlag == false && pErrInfo.length() > 0)
			{
				throw new Exception(pErrInfo.toString());
			}
		}

		return archivesInfo;
	}
	
	/**
	 * ����һ��ָ�����������µĵ�����Ϣ���󣬲������ڲ��������ֵ<br>
	 * �Զ������õ�����Ϣ�����µ�������ϣ���������ǿ�ֵ���䱾���ȱʡֵ
	 * @param archivesTypeID ����������
	 * @param pNBXH �����ڲ����
	 * @return �߱�������ϵĵ�����Ϣ����
	 * @throws Exception 
	 */
	public static ArchivesInfo newArchivsInfo(int archivesTypeID,int pNBXH) throws Exception
	{
		boolean pFlag = true;

		//������Ϣ
		StringBuilder pErrInfo=new StringBuilder();
		//���صĵ�����Ϣ����
		ArchivesInfo archivesInfo=null;
		
		try
		{
			if (pNBXH<=0)
			{
				pFlag = false;
				pErrInfo.append("�����ڲ���ŷǷ�Ϊ0");
			}
			
			if (pFlag)
			{
				archivesInfo=newArchivsInfo(archivesTypeID);
				if (archivesInfo!=null)
				{
					archivesInfo.setNBXH(pNBXH);
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

		return archivesInfo;
	}
}
