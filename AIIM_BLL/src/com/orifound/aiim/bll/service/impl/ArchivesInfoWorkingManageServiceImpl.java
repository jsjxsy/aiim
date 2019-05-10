/**
 * 
 */
package com.orifound.aiim.bll.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.orifound.aiim.bll.service.IArchivesInfoWorkProcedureManageService;
import com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService;
import com.orifound.aiim.bll.service.ICurrentContentIDManageService;
import com.orifound.aiim.bll.util.CommonUtil;
import com.orifound.aiim.dal.dao.IArchivesInfoWorkingDao;
import com.orifound.aiim.entity.ArchivesInfo;
import com.orifound.aiim.entity.ArchivesInfoQueryCondition;
import com.orifound.aiim.entity.ArchivesInfoWorkProcedure;
import com.orifound.aiim.entity.ArchivesType;
import com.orifound.aiim.entity.ArchivesTypeEx;
import com.orifound.aiim.entity.DataPageInfo;
import com.orifound.aiim.entity.EnumArchivesInfoType;
import com.orifound.aiim.entity.EnumColumnDataType;
import com.orifound.aiim.entity.EnumSystemDataItem;
import com.orifound.aiim.entity.EnumWorkFlowStatus;
import com.orifound.aiim.entity.EnumWorkingUserIDType;
import com.orifound.aiim.entity.ErrInfo;
import com.orifound.aiim.entity.FieldValue;
import com.orifound.aiim.entity.IntegerEx;

/**
 * ����������ĵ�����Ϣ�������ʵ����
 *
 */
public class ArchivesInfoWorkingManageServiceImpl implements IArchivesInfoWorkingManageService
{
	/**
	 * ���캯��
	 */
	public ArchivesInfoWorkingManageServiceImpl()
	{

	}

	/**
	 * ��DAO����ע��Ĺ��캯��
	 */
	public ArchivesInfoWorkingManageServiceImpl(IArchivesInfoWorkingDao archivesInfoWorkingDao)
	{
		this.archivesInfoWorkingDao = archivesInfoWorkingDao;
	}
	
	/**
	 * �����鵵����������ݷ��ʶ���
	 */
	private IArchivesInfoWorkingDao archivesInfoWorkingDao = null;

	/**
	 * ��ȡ����ֵ�������鵵����������ݷ��ʶ���
	 * @return �����鵵����������ݷ��ʶ���
	 */
	public IArchivesInfoWorkingDao getArchivesInfoWorkingDao()
	{
		return archivesInfoWorkingDao;
	}

	/**
	 * ��������ֵ�������鵵����������ݷ��ʶ���
	 * @param entityDao �����鵵����������ݷ��ʶ���
	 */
	public void setArchivesInfoWorkingDao(IArchivesInfoWorkingDao archivesInfoWorkingDao)
	{
		this.archivesInfoWorkingDao = archivesInfoWorkingDao;
	}
	
	/**
	 * �����鵵������Ϣ����������
	 */
	private IArchivesInfoWorkProcedureManageService archivesInfoWorkProcedureManageService = null;

	/**
	 * ��������ֵ�������鵵������Ϣ����������
	 * @param archivesInfoWorkProcedureManageService �����鵵������Ϣ����������
	 */
	public void setArchivesInfoWorkProcedureManageService(IArchivesInfoWorkProcedureManageService archivesInfoWorkProcedureManageService)
	{
		this.archivesInfoWorkProcedureManageService = archivesInfoWorkProcedureManageService;
	}

	/**
	 * ��ȡ����ֵ�������鵵������Ϣ����������
	 * @return �����鵵������Ϣ����������
	 */
	public IArchivesInfoWorkProcedureManageService getArchivesInfoWorkProcedureManageService()
	{
		return archivesInfoWorkProcedureManageService;
	}
	
	/**
	 * ��ǰ�������Ϣ����������
	 */
	private ICurrentContentIDManageService currentContentIDManageService = null;

	/**
	 * ��������ֵ����ǰ�������Ϣ����������
	 * @param currentContentIDManageService ��ǰ�������Ϣ����������
	 */
	public void setCurrentContentIDManageService(ICurrentContentIDManageService currentContentIDManageService)
	{
		this.currentContentIDManageService = currentContentIDManageService;
	}

	/**
	 * ��ȡ����ֵ����ǰ�������Ϣ����������
	 * @return ��ǰ�������Ϣ����������
	 */
	public ICurrentContentIDManageService getCurrentContentIDManageService()
	{
		return currentContentIDManageService;
	}
	
	/**
	 * ��鵵���鵵�������DAO����ע�루DAO Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkDaoInjectionForArchivesInfoWorking(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н���DAO������ע��
			pErrPos = 1;
			if (archivesInfoWorkingDao == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����鵵�������DAO�Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	/**
	 * �����ص�ҵ���߼���������ע�루BLL Depandency Injection��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkBllInjection(ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����Ƿ��н��е�ǰ�������Ϣҵ���߼����������ע��
			pErrPos = 1;
			if (currentContentIDManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("��ǰ�������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
			}
			
			//����Ƿ��н��е����鵵������Ϣҵ���߼����������ע��
			pErrPos = 2;
			if (archivesInfoWorkProcedureManageService == null)
			{
				pFlag = false;
				pErrInfo.getContent().append("�����鵵������Ϣ��ManageService����ҵ���߼����󣩷Ƿ�Ϊ�գ������Ƿ��н�������ע���ֵ��");
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
	
	/**
	 * ���õ�����������Ա�����Ϣ<br>
	 * ֱ�Ӷ�ָ���ĵ�����Ϣ�Ķ�Ӧ���û�������Ը�ֵ
	 * @param userID ������������Ա���
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForUserID(int userID,ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//����û�����Ƿ���ֵ
			pErrPos = 1;
			if (userID<=0)
			{
				pFlag = false;
				pErrInfo.getContent().append("��������Ĺ�����Ա��ŷǷ�Ϊ�ա�");
			}

			//���ݵ��������Ĺ�����״̬��ȷ����θ�ֵ������Ա���
			if (pFlag)
			{
				if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����Ĺ�����״̬��Ϣ�Ƿ�Ϊ�ա�");
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.��¼��� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.�ύ������� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ�) 
				{
					archivesInfo.setUserID1(userID);
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ҵ��ָ������¼��˴�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ҵ��ָ������¼���ͨ�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ҵ��ָ���ҽ������ͨ�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ�) 
				{
					archivesInfo.setUserID2(userID);
				}
				else if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.���������ҽ�����˴�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.���������ҽ������ͨ�� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.�����ϼ������� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.������ԤԼ���� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.�������鵵���� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.�������������� ||
						archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.�����ѹ黹) 
				{
					archivesInfo.setUserID3(userID);
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
	 * ��ȡָ�����ֶ�ֵ���������Դ���Ŷ�Ӧ�ĵ��ı�ֵ
	 * @param dataItemName ����������
	 * @param dataSourceID ����Դ���
	 * @param dataSourceName ����Դ����
	 * @param dataSourceItemID ����Դ����
	 * @param dataSourceItemText ���ػ�ȡ�ɹ�������Դ���ı�
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean getDataSourceItemText(String dataItemName, int dataSourceID,String dataSourceName,int dataSourceItemID,StringBuilder dataSourceItemText, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 7;
			if (dataSourceID<=0)
			{
				//pFlag = false;
				//pErrInfo.getContent().append(dataItemName+" �����������Դδ���á�");
			}
			else 
			{
				if (CommonUtil.getSystemInitializer().getDataSources().containsKey(dataSourceID)==false)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ����Դ�ֵ��в����� "+dataSourceName+" ����Դ��");
				}
			}
			
			//�����Ӧ����Դ��
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append(dataSourceName+" ����Դ�����Ա���ϷǷ�Ϊ�ա�");
				}
				else 
				{
					if (CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems().containsKey(dataSourceItemID)==false)
					{
						pFlag = false;
						pErrInfo.getContent().append(dataSourceName+" ����Դ�����Ա�в����ڱ��Ϊ "+dataSourceItemID+" ���");
					}
					else 
					{
						//��������Դ���Ŷ�Ӧ���ı�ֵ
						dataSourceItemText.append(CommonUtil.getSystemInitializer().getDataSources().get(dataSourceID).getDataSourceItems().get(dataSourceItemID).getName());
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
	
	/**
	 * ���ñ����ڽ�ֹ���<br>
	 * ���ݵ����γ���Ⱥͱ��������Զ����㲢�����䱣���ڽ�ֹ���
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForRetentionEndYear(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int retentionYears=0;//�������ޱ�Ŷ�Ӧ��ʵ�ʱ�������

		try
		{
			//����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ�ա�");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵���γ�����Ƿ�Ϊ0
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ0");
				}
			}
			
			//��鱣�������ֵ���Ϣ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getRetentionPeriods()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ�����еı��������ֵ���Ϣ�Ƿ�Ϊ�գ����ȳ�ʼ����");
				}
			}

			//���ݱ������ޱ�ż��㱣������
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ�ա�");
				}
				//���㱣�����ޱ�Ŷ�Ӧ��ʵ�ʱ�������
				else
				{
					pErrPos = 2;
					if (CommonUtil.getSystemInitializer().getRetentionPeriods().containsKey(archivesInfo.getRetentionPeriodID())==false)
					{
						pFlag = false;
						pErrInfo.getContent().append("ϵͳ�����ڱ������ޱ��Ϊ "+archivesInfo.getRetentionPeriodID()+" �Ķ��塣");
					}
					else 
					{
						retentionYears=CommonUtil.getSystemInitializer().getRetentionPeriods().get(archivesInfo.getRetentionPeriodID()).getTotalYears();
					}
				}
			}
			
			//���ݵ����γ���Ⱥͱ��������Զ����㲢�����䱣���ڽ�ֹ���
			if (pFlag)
			{
				pErrPos = 3;
				int retentionEndYear=archivesInfo.getFormationYear()+retentionYears;
				archivesInfo.setRetentionEndYear(retentionEndYear);
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
	 * ���ò����������ֶ�ֵ��Ӧ���ı����ֶ�ֵ<br>
	 * ���籣�������ı����������ܼ��ı������γɲ����ı����ȵ�<br>
	 * ע�⣺����������Ÿ�ʽ���ı����������ļ���Ÿ�ʽ���ı���<br>
	 * ���øú���֮ǰӦ���ȶԵ�����Ϣ�����������Լ�鴦��
	 * @param archivesType ���������ĵ���������Ϣ
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForAssociateDataItem(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
				}
			}
			
			//���ϵͳ��ʼ�������������Դ�����Ƿ���ֵ
			if (pFlag)
			{
				if (CommonUtil.getSystemInitializer().getDataSources()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("ϵͳ��ʼ�����������Դ���ϷǷ�Ϊ�գ����Ƚ���ϵͳ��ʼ��������");
				}
			}
			
			//ѭ�����������й�����Ӧ�ı��ֶε�������������Ӧ���ı��ֶ�ֵ���ܽ��и�ֵ����
			if (pFlag)
			{
				for (FieldValue item : archivesInfo.getRowFieldsValues().values())
				{
					//���õ�����������ֶ�ֵ
					if (item.getSystemDataItemType()==EnumSystemDataItem.����������)
					{
						pErrPos = 6;
						archivesInfo.setArchivesTypeCode(archivesType.getFullCode());
					}
					
					//���ñ��������ı��ֶ�ֵ
					else if (item.getSystemDataItemType()==EnumSystemDataItem.�������ޱ��)
					{
						pErrPos = 7;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("�������ޱ��", item.getDataSourceID(), "��������", archivesInfo.getRetentionPeriodID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"��ȡ�������ޱ�Ŷ�Ӧ���ı�ʧ��: ");
						}
						else
						{
							//���ñ��������ı�ֵ
							archivesInfo.setRetentionPeriodText(dataSourceItemText.toString());
						}
					}
					
					//���õ����ܼ��ı��ֶ�ֵ
					else if (item.getSystemDataItemType()==EnumSystemDataItem.�����ܼ����)
					{
						pErrPos = 8;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("�����ܼ����", item.getDataSourceID(), "�����ܼ�", archivesInfo.getSecrecyID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"��ȡ�����ܼ���Ŷ�Ӧ���ı�ʧ��: ");
						}
						else 
						{
							//���õ����ܼ��ı�ֵ
							archivesInfo.setSecrecyText(dataSourceItemText.toString());
						}
					}
					
					//���õ����γɲ����ı��ֶ�ֵ
					else if (item.getSystemDataItemType()==EnumSystemDataItem.�����γɲ��ű��)
					{
						pErrPos = 9;
						StringBuilder dataSourceItemText=new StringBuilder();
						if (getDataSourceItemText("�����γɲ��ű��", item.getDataSourceID(), "�����γɲ���", archivesInfo.getFormationDepartmentID(), dataSourceItemText, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0,"��ȡ�����γɲ��ű�Ŷ�Ӧ���ı�ʧ��: ");
						}
						else 
						{
							//���õ����γɲ��������ı�ֵ
							archivesInfo.setFormationDepartment(dataSourceItemText.toString());
						}
					}
					
					//�����������ϵͳ��������Դ�ֶΣ����Ҵ�����������ı��ֶΣ���ֱ��ʹ������Դ��ȡ�ı�ֵ
					else if (item.getAssociateTextColumnName()!=null)
					{
						if (item.getAssociateTextColumnName().length()>0)
						{
							//������������й������ı��ֶΣ������������ֵΪnull����Ҫ�ݴ���ʾ���޷��������Ӧ���ı��ֶ�ֵ
							if (item.getValue()==null)
							{
								pFlag = false;
								pErrInfo.getContent().append("�����"+item.getDisplayText()+"�������˹������ı��ֶΣ�"+item.getAssociateTextColumnName()+"������ֵȴ�Ƿ�Ϊ�գ��޷��������Ӧ���ı��ֶ�ֵ��������δ���������Ч�ĸ�ֵ������ڵ��������������ֵ���δ��������ȱʡֵ��");
							}
							else 
							{
								pErrPos =10;
								StringBuilder dataSourceItemText=new StringBuilder();
								if (getDataSourceItemText(item.getDisplayText(), item.getDataSourceID(), item.getDisplayText(),Integer.valueOf(item.getValue()), dataSourceItemText, pErrInfo)==false)
								{
									pFlag = false;
									pErrInfo.getContent().insert(0,"��ȡ"+item.getDisplayText()+"��Ӧ���ı�ʧ��: ");
								}
								else 
								{
									//���ö�Ӧ�ı�ֵ
									if (archivesInfo.getRowFieldsValues().containsKey(item.getAssociateTextColumnName()))
									{
										archivesInfo.getRowFieldsValues().get(item.getAssociateTextColumnName()).setValue(dataSourceItemText.toString());
									}
								}
							}
						}
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
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ���ð���Ŷ�Ӧ�ĸ�ʽ���ı����ֶ�ֵ<br>
	 * ���ɵ���ʱ��Ҫ���øú������д���
	 * @param archivesType ���������ĵ���������Ϣ
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	/*private boolean setValueForContentIDText(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
				}
			}
			
			//��鰸�������ֵ�Ƿ�Ϊ0
			if (pFlag)
			{
				if (archivesInfo.getContentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�İ���ŷǷ�Ϊ0");
				}
			}
			
			//���㰸��Ŷ�Ӧ���ı��ֶ�ֵ���ܽ��и�ֵ����
			if (pFlag)
			{
				pErrPos = 2;
				String contentIDText= String.valueOf(archivesInfo.getContentID());
				if (archivesType.getContentIDFormatLength()>0)
				{
					contentIDText=String.format("%0"+String.valueOf(archivesType.getContentIDFormatLength()+"d"), archivesInfo.getContentID());
				}
				archivesInfo.setContentIDText(contentIDText);
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
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}*/
	
	/**
	 * ���þ����ļ���Ŷ�Ӧ�ĸ�ʽ���ı����ֶ�ֵ<br>
	 * @param archivesType ���������ĵ���������Ϣ
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean setValueForSubContentIDText(ArchivesType archivesType,ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		
		try
		{
			//����м�¼�ֶμ����Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo.getRowFieldsValues()==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
			}
			else 
			{
				if (archivesInfo.getRowFieldsValues().size()==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���м�¼�ֶμ��ϷǷ�Ϊ��");
				}
			}
			
			//�������ļ��������ֵ�Ƿ�Ϊ0
			if (pFlag)
			{
				if (archivesInfo.getSubContentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ľ����ļ���ŷǷ�Ϊ0");
				}
			}
			
			//��������ļ���Ŷ�Ӧ���ı��ֶ�ֵ���ܽ��и�ֵ����
			if (pFlag)
			{
				pErrPos = 2;
				String subContentIDText= String.valueOf(archivesInfo.getSubContentID());
				if (archivesType.getSubContentIDFormatLength()>0)
				{
					subContentIDText=String.format("%0"+String.valueOf(archivesType.getSubContentIDFormatLength()+"d"), archivesInfo.getSubContentID());
				}
				//���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵ
				archivesInfo.setSubContentIDText(subContentIDText);
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
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/**
	 * ����������Ϣʱ�ĵ�������������������Լ����ȱʡֵ����
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkArchivesInfoSystemDataItemsForSave(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵵����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
			}
			
			//��鹤����״̬�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getWorkFlowStatus()==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ĺ�����״̬�Ƿ�Ϊ�ա�");
				}
			}
			
			//���ȫ�����ֱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFondsID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ��ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵��������Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ĵ��������ŷǷ�Ϊ0");
				}
			}
			
			//��鱣�����ޱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ0");
				}
			}

			//��鵵���ܼ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ܼ���ŷǷ�Ϊ0");
				}
			}
			
			//��鵵���γ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵���γɲ��ű���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γɲ��ű�ŷǷ�Ϊ0");
				}
			}
			
			//����ȱʡֵ��ǿ����ʾ��ֵ������ȱʡֵ����Ϊget�����ֶ�ֵ��ʱ���н���ת��������valueֵ��һ��������ֵ��
			
			//����������null����ʹ��ȱʡֵ0���򰸾���ı�ʹ�ÿմ���������ı��ֶβ�����ΪNULL��
			if (pFlag)
			{
				if (archivesInfo.getContentID()==0)
				{
					archivesInfo.setContentID(0);
					archivesInfo.setContentIDText("");
				}
			}
			
			//��������־��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getParentFlag()==false)
				{
					archivesInfo.setParentFlag(false);
				}
			}
			
			//�������������ڲ������null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getParentNBXH()==0)
				{
					archivesInfo.setParentNBXH(0);
				}
			}
			
			//��������ļ�������null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getSubContentCount()==0)
				{
					archivesInfo.setSubContentCount(0);
				}
			}
			
			//����ҳ��Ϊnullʱʹ��ȱʡֵ0���
			if (pFlag)
			{
				if (archivesInfo.getPageSum()==0)
				{
					archivesInfo.setPageSum(0);
				}
			}

			//�����鵵����Ϊnull����ʹ��ȱʡֵ��ǰʱ�����
			if (pFlag)
			{
				if (archivesInfo.getSaveDate()==null)
				{
					archivesInfo.setSaveDate(new Date());
				}
			}
			
			//����޸���־��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getFixedFlag()==false)
				{
					archivesInfo.setFixedFlag(false);
				}
			}
			
			//���ɾ����־��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getDeleteFlag()==false)
				{
					archivesInfo.setDeleteFlag(false);
				}
			}
			
			//������ű�־��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getPublicFlag()==false)
				{
					archivesInfo.setPublicFlag(false);
				}
			}
			
			//���UserID2��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getUserID2()==0)
				{
					archivesInfo.setUserID2(0);
				}
			}
			
			//���UserID3��null����ʹ��ȱʡֵ0
			if (pFlag)
			{
				if (archivesInfo.getUserID3()==0)
				{
					archivesInfo.setUserID3(0);
				}
			}
			
			//����������ֵ�Ƿ���ڿմ�
			
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
	 * ���µ�����Ϣʱ�ĵ�������������������Լ��
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkArchivesInfoSystemDataItemsForUpdate(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵵����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
			}
			
			//���ȫ�ڱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFondsID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ��ȫ�����ֱ�ŷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵��������Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ĵ��������ŷǷ�Ϊ0");
				}
			}
			
			//����ڲ�����Ƿ���ֵ
			if (pFlag)
			{
				if (archivesInfo.getNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
				}
			}
			
			//��鱣�����ޱ���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getRetentionPeriodID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�ı������ޱ�ŷǷ�Ϊ0");
				}
			}

			//��鵵���ܼ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getSecrecyID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ܼ���ŷǷ�Ϊ0");
				}
			}
			
			//��鵵���γ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFormationYear()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γ���ȷǷ�Ϊ�ա�");
				}
			}
			
			//��鵵���γɲ��ű���Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getFormationDepartmentID()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���γɲ��ű�ŷǷ�Ϊ0");
				}
			}
			
			//����ҳ��Ϊnullʱʹ��ȱʡֵ0���
			if (pFlag)
			{
				if (archivesInfo.getPageSum()==0)
				{
					archivesInfo.setPageSum(0);
				}
			}

			//�����鵵����Ϊnull����ʹ��ȱʡֵ��ǰʱ�����
			if (pFlag)
			{
				if (archivesInfo.getSaveDate()==null)
				{
					archivesInfo.setSaveDate(new Date());
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
	 * ��鵵����Ϣ��������ֵ���͵���ȷ��
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkArchivesInfoDataItemValueType(ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			//��鵵����Ϣ�Ƿ�Ϊ��
			pErrPos = 1;
			if (archivesInfo==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
			}

			//����������ֶ�ֵ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo.getRowFieldsValues()==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ֶ�ֵ���ϷǷ�Ϊ�ա�");
				}
			}
			
			//�����ֶ�ֵ������һ��飨�ַ������ı����Ϳ��Բ��ؼ�飬�������֣��������ͱ����飩
			if (pFlag)
			{
				for (FieldValue item : archivesInfo.getRowFieldsValues().values())
				{
					//����ǿ�ֵ�򲻼��
					if (item.getValue()==null)
					{
						continue;
					}
					
					//�м����������������飨��Ϊ����¼�����������������ֶΣ����ҿ��ܻḳ��մ�ֵ���ǺϷ��ģ�������ݴ������
					if(item.getSystemDataItemType() == EnumSystemDataItem.�ڲ����  
							||  item.getSystemDataItemType() == EnumSystemDataItem.����������ڲ����
							||  item.getSystemDataItemType() == EnumSystemDataItem.����ҳ��){
						
						continue;
					}

					//if (item.getColumnDataType() == EnumColumnDataType.�ַ��� || item.getColumnDataType() == EnumColumnDataType.�ı�
					if (item.getColumnDataType() == EnumColumnDataType.����ʱ��)
					{
						if (CommonUtil.isDate(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" ����������ֵ����ǰֵΪ: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.ʵ��)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" ��������ֵ����ǰֵΪ: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.����ֵ)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" ������0��1����ǰֵΪ: "+item.getValue());
						}
					}
					else if (item.getColumnDataType() == EnumColumnDataType.����)
					{
						if (CommonUtil.isDouble(item.getValue())==false)
						{
							pFlag = false;
							pErrInfo.getContent().append(item.getDisplayText()+" ��������������ǰֵΪ: "+item.getValue());
						}
					}
					
					//����һ�����δͨ��������ѭ��
					if (pFlag==false)
					{
						break;
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
	
	/**
	 * ��鵵����Ϣ����Ƿ���ȷ
	 * @param enumArchivesInfoType ������Ϣ���
	 * @param archivesInfo ������Ϣ����
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	private boolean checkArchivesInfoType(EnumArchivesInfoType enumArchivesInfoType,ArchivesInfo archivesInfo,ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos =1;
			if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ���Ƿ�Ϊ�ա�");
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.��������)
			{
				pErrPos =2;
				archivesInfo.setParentFlag(true);
				if (archivesInfo.getParentNBXH()>0)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������Ӧ�ô�������������ڲ������Ϣ��");
				}
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.�ļ�������)
			{
				pErrPos =3;
				archivesInfo.setParentFlag(false);
				if (archivesInfo.getParentNBXH()>0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�ļ���������Ӧ�ô�������������ڲ������Ϣ��");
				}
			}
			else if (enumArchivesInfoType==EnumArchivesInfoType.�����ļ�)
			{
				pErrPos =4;
				archivesInfo.setParentFlag(false);
				if (archivesInfo.getParentNBXH()<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ļ������������ڲ���ŷǷ�Ϊ0");
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
	 * ���ù�����״̬��־�����浵���鵵�����еĹ���״̬����־�����鵵�����е�ָ�������Ѿ��������<br>
	 * ҵ���߼������µ�����Ϣ���еĵ���������״̬�͹�����Ա��Ϣ�������浵���鵵������Ϣ
	 * 
	 * @param enumArchivesInfoType ������Ϣ���࣬���ļ�������
	 * @param pArchivesTypeID ����������
	 * @param pNBXH �ڲ����
	 * @param userID �û����
	 * @param enumWorkFlowStatus ������״̬
	 * @param pErrInfo ����ʧ�ܵĴ���ԭ������
	 * @return ����ɹ�����true�����򷵻�false
	 */
	public boolean setFlagForWorkFlow(EnumArchivesInfoType enumArchivesInfoType, int pArchivesTypeID,  int[] pNBXHs, int userID,EnumWorkFlowStatus enumWorkFlowStatus, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		EnumWorkingUserIDType enumWorkingUserIDType=null;  //�����Ǹ��û�����ֶ���
		ArchivesType archivesType=null; //������������

		try
		{
			//��鵵����Ϣ�����Ƿ�Ϊ��
			pErrPos = 1;
			/*if (enumArchivesInfoType==null)
			{
				pFlag = false;
				pErrInfo.getContent().append("������Ϣ����Ƿ�Ϊ�ա�");
			}*/
	
			//��鵵�������Ƿ�Ϊ��
			if (pFlag)
			{
				if (pArchivesTypeID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("���������ŷǷ�Ϊ0");
				}
			}
			
			//����ڲ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (pNBXHs.length==0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����ڲ���ŷǷ�Ϊ�ա�");
				}
			}
			
			//����û�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (userID<=0)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����鵵������Ա���û���ŷǷ�Ϊ0");
				}
			}

			//���ݵ��������Ĺ�����״̬��ȷ����θ�ֵ������Ա���
			if (pFlag)
			{
				if (enumWorkFlowStatus==EnumWorkFlowStatus.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("�����Ĺ�����״̬��Ϣ�Ƿ�Ϊ�ա�");
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.��¼��� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�ύ������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.����������ƽ��嵥) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ������¼��˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ������¼���ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ���ҽ�����˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ҵ��ָ���ҽ������ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.ȷ�Ͻ���ʵ�ﵵ���Ĺ����ƽ� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�鵵��Ϣ����޸�) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID2;
				}
				else if (enumWorkFlowStatus==EnumWorkFlowStatus.���������ҽ�����˴�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.���������ҽ������ͨ�� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�����ϼ������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.������ԤԼ���� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�������鵵���� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�������������� ||
						enumWorkFlowStatus==EnumWorkFlowStatus.�����ѹ黹) 
				{
					enumWorkingUserIDType=EnumWorkingUserIDType.UserID3;
				}else if(enumWorkFlowStatus == EnumWorkFlowStatus.�γɲ��Ž��������嵥���Ƴ�){
					   enumWorkFlowStatus = EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��;
					   enumWorkingUserIDType=EnumWorkingUserIDType.UserID1;
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(pArchivesTypeID, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(pArchivesTypeID);
				}
			}
			
			//����DAO�ӿڣ����µ����Ĺ�����״̬���û������Ϣ
			if (pFlag)
			{
				if (archivesInfoWorkingDao.updateWorkFlowUserIDByNBXH(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���µ���������״̬���û������Ϣʧ��: ");
				}
			}
			
			//����ǰ�����������Ҫ��������ļ��Ĺ�����״̬���û���Ž���ͬ�����´���
			if (pFlag)
			{
				if (enumArchivesInfoType != null)
				{
					if (enumArchivesInfoType==EnumArchivesInfoType.��������)
					{
						if (archivesInfoWorkingDao.updateWorkFlowUserIDForChild(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
						{
							pFlag = false;
							pErrInfo.getContent().insert(0, "���¾����ļ��ĵ���������״̬���û������Ϣʧ��: ");
						}
					}
				}
				else
				{
					if (archivesInfoWorkingDao.updateWorkFlowUserIDForChild(archivesType, pNBXHs, enumWorkingUserIDType, userID, enumWorkFlowStatus, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "���¾����ļ��ĵ���������״̬���û������Ϣʧ��: ");
					}
				}	
			}
			
			//���õ����鵵������Ϣҵ���߼��ӿڣ����浵���鵵������Ϣ
			if (pFlag)
			{
				pErrPos = 9;
				for (int i = 0; i < pNBXHs.length; i++)
				{
					ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure(pNBXHs[i], pArchivesTypeID, userID, enumWorkFlowStatus.getEnumValue());
					if (archivesInfoWorkProcedureManageService.saveArchivesInfoWorkProcedure(archivesInfoWorkProcedure, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "���浵���鵵������Ϣʧ��: ");
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
			archivesType=null;
			throwable = null;
		}

		return pFlag;
	}
	
	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findArchivesInfos( int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType,com.orifound.aiim.entity.EnumWorkFlowStatus, java.util.List, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfos( int[] userID, ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType,EnumWorkFlowStatus enumWorkFlowStatus,
			List<ArchivesInfoQueryCondition> archivesInfoQueryConditions, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
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
			
			//������ݷ�ҳ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (dataPageInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("���ݷ�ҳ��Ϣ�Ƿ�Ϊ�ա�");
				}
			}
			
			//����û�����Ƿ���ֵ
			if (pFlag)
			{
				if (userID==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("�û������Ϣ�Ƿ�Ϊ�ա�");
				}
				else
				{
					if (userID.length==0)
					{
						pFlag = false;
						pErrInfo.getContent().append("�û������Ϣ�Ƿ�Ϊ�ա�");
					}
				}
			}
			
			//����ѯ��������������޸�����
			if (pFlag)
			{
				if (CommonUtil.checkArchivesInfoInputQueryConditions(archivesInfoQueryConditions, pErrInfo)==false)
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
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesInfoWorkingDao.find(enumArchivesInfoType, userID, archivesType, enumWorkFlowStatus, archivesInfoQueryConditions, dataPageInfo, archivesInfos, pErrInfo)==false)
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findArchivesInfoByNBXH(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findArchivesInfoByNBXH(int pNBXH, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
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
			
			//����DAO�ӿ�
			if (pFlag)
			{
				if (archivesInfoWorkingDao.findByNBXH(pNBXH, archivesType, archivesInfo, pErrInfo)==false)
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#appendArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean appendArchivesInfos(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#brokeArchivesInfo(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean brokeArchivesInfo(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, int userID, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//���õ�����������Ϣ
			if (pFlag)
			{
				if (archivesType == null)
				{
					pFlag = false;
					pErrInfo.getContent().append("����������ϢΪ��");
				}
			}
			
			//��鵵��������ID�Ƿ�ֵ
			if (pFlag) {
				if (archivesType.getID() <=0) {
					pFlag = false;
					pErrInfo.getContent().append("����������Ϣ���û�и�ֵ");
				}
			}
			
			//��鴫���archivesInfos�Ƿ�Ϊ��
			if (pFlag) {
				if (archivesInfos == null) {
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���϶���û�и�ֵ");
				}
			}
			
			if (pFlag) {
				for (int i = 0; i < archivesInfos.size(); i++) {
					archivesInfos.get(i).setParentNBXH(0);
					archivesInfos.get(i).setContentID(0);
					archivesInfos.get(i).setContentIDText("");
					archivesInfos.get(i).setSubContentID(0);
					archivesInfos.get(i).setSubContentIDText("");
				}
			}
			
			//����dao���¾���Ŀ¼��parentNBXH�ֶ�ֵ
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos,userID,EnumWorkFlowStatus.��¼���, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"����DAO���¾����ļ�parentNBXH�ֶ�ֵʧ�ܣ�");
				}
			}
			
			//����daoɾ��������Ϣ
//			if (pFlag) {
//				if (archivesInfoWorkingDao.batchDelArchives(archivesType, archivesInfos, pErrInfo) == false) {
//					pFlag = false;
//					pErrInfo.getContent().insert(0,"����DAOɾ��������Ϣʧ�ܣ�");
//				}
//			}	
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#deleteArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfo(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
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
			
			
			//��鵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ͷǷ�Ϊ�գ�������ȷɾ���ĵ����ǰ������ļ������Ǿ����ļ���������");
				}
			}
			
			//��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfo==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ�Ƿ�Ϊ�ա�");
				}
				else 
				{
					//����ڲ�����Ƿ���ֵ
					if (archivesInfo.getNBXH()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ���ڲ���ŷǷ�Ϊ0");
					}
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
			
			//����DAO�ӿ�ɾ��������Ϣ����������������Ϣ��ԭ�ĵ����ļ���Ϣ����¼ԭ��ɾ����¼�ȣ�����ɾ��ԭ�ĵ����ļ���Ϣ����¼ԭ��ɾ����¼������δʵ��
			
			//��ɾ����������������Ϣ
			if (pFlag)
			{
				ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure();
				archivesInfoWorkProcedure.setArchivesTypeID(archivesType.getID());
				archivesInfoWorkProcedure.setNBXH(archivesInfo.getNBXH());
				if (archivesInfoWorkProcedureManageService.deleteArchivesInfoWorkProcedure(enumArchivesInfoType, archivesInfoWorkProcedure, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "ɾ����������������Ϣʧ��: ");
				}
			}
			
			//TODO: ��ʵ�֣�ɾ��ԭ�ĵ����ļ���Ϣ������ԭ�ĵ����ļ�ɾ����¼��ȷ����̨�����ܹ���ȡ����ɾ�������ļ���
			
			//�����ܹ�ɾ�������鵵�������¼
			if (pFlag)
			{
				if (archivesInfoWorkingDao.deleteParentAndChild(archivesType, archivesInfo, pErrInfo)==false)
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#deleteArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean deleteArchivesInfos(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
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
			
			//��鵵����Ϣ�����Ƿ�Ϊ��
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.NONE)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ͷǷ�Ϊ�գ�������ȷɾ���ĵ����ǰ������ļ������Ǿ����ļ���������");
				}
			}
			
			//��鵵����Ϣ�Ƿ�Ϊ��
			if (pFlag)
			{
				if (archivesInfos==null)
				{
					pFlag = false;
					pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
				}
				else 
				{
					//����ڲ�����Ƿ���ֵ
					if (archivesInfos.size()<=0)
					{
						pFlag = false;
						pErrInfo.getContent().append("������Ϣ���ϷǷ�Ϊ�ա�");
					}
				}
			}
			
			//���õ���ɾ���Ľӿڷ�����ѭ�����ɾ��
			if (pFlag)
			{
				for (ArchivesInfo item : archivesInfos)
				{
					if (deleteArchivesInfo(archivesType, enumArchivesInfoType, item, pErrInfo)==false)
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckNeedArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckNeedArchivesInfos(int[] deptIDs, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//����DAO���Ҵ���¼��˵ĵ�����Ϣ
			if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingDao.findArchivesInfosByEnumWorkFlowStatus(archivesType,deptIDs, EnumWorkFlowStatus.�ύ�������, dataPageInfo, archivesInfos, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ݹ�����״̬���ҵ�����Ϣʧ��");
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
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
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
				if (archivesInfoWorkingDao.findChildrenByNBXH(pNBXH, archivesType, archivesInfos, pErrInfo)==false)
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#combineArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean combineArchivesInfos(int userID, ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			if(archivesInfos == null || archivesInfos.size()<=0){
				pFlag = false;
				pErrInfo.getContent().append("�����Ҫ�������Ϣ���϶���Ϊ�գ������Ƿ�ֵ");
			}
			
			//���ñ��浱ǰ������Ϣ
			if (pFlag)
			{
				pErrPos = 2;
				if (this.saveArchivesInfo(userID, archivesType, EnumArchivesInfoType.��������, archivesInfo, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵����Ϣʧ��: ");
				}
			}
			
			if (pFlag) {
				//ArchivesInfo cArchivesInfo = null;
				for (int i = 0; i < archivesInfos.size(); i++) {
					//cArchivesInfo =  archivesInfos.get(i);
					
					//����parentNBXH
					archivesInfos.get(i).setParentNBXH(archivesInfo.getNBXH());
					archivesInfos.get(i).setContentID(0);
					archivesInfos.get(i).setContentIDText("");
					
					//�����ļ����ı�
					//IntegerEx maxSubContentID=new IntegerEx();
//					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, archivesInfo.getNBXH(), maxSubContentID, pErrInfo)==false)
//					{
//						pFlag = false;
//						pErrInfo.getContent().insert(0, "��ȡ�������ļ����ֵʧ��: ");
//					}
//					else 
//					{
						//����ǰ�������ļ����ֵ+1����������ļ��������ֵ
						archivesInfos.get(i).setSubContentID(i+1);
				//	}
					
					if (pFlag) {
						//���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵ
						if (pFlag)
						{
							if (setValueForSubContentIDText(archivesType, archivesInfos.get(i), pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵʧ��: ");
							}
						}
					}
				}
			}
			
			//�����ļ����ı������������ڲ����
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos, userID, EnumWorkFlowStatus.��¼���, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ļ����ı������������ڲ����: ");
				}
			}
			//����DAO���
		/*	if (pFlag) {
				pErrPos = 2;
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesInfo.getNBXH(),archivesType, archivesInfos, pErrInfo) == false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ʧ��: ");
				}
			}*/
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#mergeArchivesInfos(com.orifound.aiim.entity.ArchivesType, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean mergeArchivesInfos(ArchivesType archivesType, List<ArchivesInfo> archivesInfos, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#saveArchivesInfo(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean saveArchivesInfo(int userID, ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����Ƿ��н���ҵ���߼����������ע��
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//��鵵����Ϣ����Ƿ�Ϊ�գ������ð����־
			if (pFlag)
			{
				if (checkArchivesInfoType(enumArchivesInfoType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ�����ʧ��: ");
				}
			}
			
			//���õ�����������Ա�����Ϣ
			if (pFlag)
			{
				if (setValueForUserID(userID, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ�����������Ա�����Ϣʧ��: ");
				}
			}
			
			//���õ�����������Ϣ
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					archivesInfo.setArchivesTypeID(archivesType.getID());
				}
			}
			
			//�����������ֶ�ֵ�������Ƿ���ȷ�����������ֶβ��ܹ�������ĸ�ַ������͵�ֵ
			if (pFlag)
			{
				if (checkArchivesInfoDataItemValueType(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ�������ֶ�ֵ���ͼ��δͨ��: ");
				}
			}
			
			//���Ҫ����ĵ�����Ϣ��ϵͳ�����������ֶ�ֵ��������
			if (pFlag)
			{
				pErrPos = 2;
				if (checkArchivesInfoSystemDataItemsForSave(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ������: ");
				}
			}
			
			//���õ��������ڽ�ֹ���
			if (pFlag)
			{
				if (setValueForRetentionEndYear(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ��������ڽ�ֹ���ʧ��: ");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 4;
				if (CommonUtil.checkArchivesType(archivesInfo.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfo.getArchivesTypeID());
				}
			}
			
			//���ò�������������ֶε��ı�ֵ����������Ÿ�ʽ���ı��������ļ���Ÿ�ʽ���ı���ʵ�����š����������ı��������ܼ��ı��������γɲ������ơ��Լ������Զ��������������ı��ֶ�ֵ��
			if (pFlag)
			{
				pErrPos = 6;
				if (setValueForAssociateDataItem(archivesType,archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù����������ֶε��ı�ֵʧ��: ");
				}
			}
			
			//����Ǿ����ļ���Ҫ�ȼ���������������������ļ���ţ�Ȼ��+1��Ϊ���ļ���¼�ľ����ļ�����ֶ�ֵ������������ļ���ŵĸ�ʽ���ı�
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.�����ļ�)
				{
					pErrPos = 5;
					IntegerEx maxSubContentID=new IntegerEx();
					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, archivesInfo.getParentNBXH(), maxSubContentID, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "��ȡ�������ļ����ֵʧ��: ");
					}
					else 
					{
						//����ǰ�������ļ����ֵ+1����������ļ��������ֵ
						archivesInfo.setSubContentID(maxSubContentID.getValue()+1);
					}
				}
			}
			
			//���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵ
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.�����ļ�)
				{
					if (setValueForSubContentIDText(archivesType, archivesInfo, pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵʧ��: ");
					}
				}
			}
			
			//����DAO�ӿڱ��浱ǰ������Ϣ
			if (pFlag)
			{
				pErrPos = 8;
				if (archivesInfoWorkingDao.save(archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵����Ϣʧ��: ");
				}
			}
			
			//�����ǰ�����Ǿ����ļ�����ô��Ҫͬ����������������ľ����ļ�����������ҳ���ֶ�ֵ
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.�����ļ�)
				{
					pErrPos = 9;
					if (archivesInfoWorkingDao.updateSubContentCountAndPageSum(archivesType, archivesInfo.getParentNBXH(), pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "������������ľ����ļ���������ҳ��ʧ��: ");
					}
				}
			}
			
			//���õ����鵵������Ϣҵ���߼��ӿڣ����浵���鵵������Ϣ
			if (pFlag)
			{
				pErrPos = 10;
				ArchivesInfoWorkProcedure archivesInfoWorkProcedure=new ArchivesInfoWorkProcedure(archivesInfo.getNBXH(), archivesInfo.getArchivesTypeID(), archivesInfo.getUserID1(), archivesInfo.getWorkFlowStatus().getEnumValue());
				if (archivesInfoWorkProcedureManageService.saveArchivesInfoWorkProcedure(archivesInfoWorkProcedure, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵���鵵������Ϣʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#updateArchivesInfo(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean updateArchivesInfo(ArchivesType archivesType, EnumArchivesInfoType enumArchivesInfoType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����Ƿ��н���ҵ���߼����������ע��
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//��鵵����Ϣ����Ƿ�Ϊ�գ������ð����־
			if (pFlag)
			{
				if (checkArchivesInfoType(enumArchivesInfoType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ�����ʧ��: ");
				}
			}
			
			//���õ�����������Ϣ
			if (pFlag)
			{
				if (archivesInfo.getArchivesTypeID()<=0)
				{
					archivesInfo.setArchivesTypeID(archivesType.getID());
				}
			}
			
			//�����������ֶ�ֵ�������Ƿ���ȷ�����������ֶβ��ܹ�������ĸ�ַ������͵�ֵ
			if (pFlag)
			{
				if (checkArchivesInfoDataItemValueType(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ�������ֶ�ֵ���ͼ��δͨ��: ");
				}
			}
			
			//���Ҫ����ĵ�����Ϣ��ϵͳ�����������ֶ�ֵ��������
			if (pFlag)
			{
				pErrPos = 2;
				if (checkArchivesInfoSystemDataItemsForUpdate(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "������Ϣ������: ");
				}
			}
			
			//���õ��������ڽ�ֹ���
			if (pFlag)
			{
				if (setValueForRetentionEndYear(archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ��������ڽ�ֹ���ʧ��: ");
				}
			}
			
			//��ȡ����������Ϣ
			if (pFlag)
			{
				pErrPos = 3;
				if (CommonUtil.checkArchivesType(archivesInfo.getArchivesTypeID(), pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���������źϷ��Լ��ʧ��: ");
				}
				else
				{
					archivesType=CommonUtil.getSystemInitializer().getPlaneArchivesTypes().get(archivesInfo.getArchivesTypeID());
				}
			}
			
			//���ò�������������ֶε��ı�ֵ������ʵ�����š����������ı��������ܼ��ı��������γɲ������ơ��Լ������Զ��������������ı��ֶ�ֵ��
			if (pFlag)
			{
				pErrPos = 5;
				if (setValueForAssociateDataItem(archivesType,archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���ù����������ֶε��ı�ֵʧ��: ");
				}
			}
			
			//����DAO�ӿڸ��µ�ǰ������Ϣ
			if (pFlag)
			{
				pErrPos = 7;
				if (archivesInfoWorkingDao.update(archivesType, archivesInfo, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���浵����Ϣʧ��: ");
				}
			}
			
			//�����ǰ�����Ǿ����ļ�����ô��Ҫͬ����������������ľ����ļ�����������ҳ���ֶ�ֵ
			if (pFlag)
			{
				if (enumArchivesInfoType==EnumArchivesInfoType.�����ļ�)
				{
					pErrPos = 8;
					if (archivesInfoWorkingDao.updateSubContentCountAndPageSum(archivesType, archivesInfo.getParentNBXH(), pErrInfo)==false)
					{
						pFlag = false;
						pErrInfo.getContent().insert(0, "������������ľ����ļ���������ҳ��ʧ��: ");
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

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#separateArchivesInfos(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean separateArchivesInfos(ArchivesType archivesType, ArchivesInfo parentArchivesInfo, List<ArchivesInfo> childArchivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#splitArchivesInfos(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.util.List, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean splitArchivesInfos(ArchivesType archivesType, ArchivesInfo parentArchivesInfo, List<ArchivesInfo> childArchivesInfos, ArchivesInfo desArchivesInfo,
			ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#submitToInputCheck(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.EnumArchivesInfoType, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean submitToInputCheck(int userID, ArchivesType archivesType,EnumArchivesInfoType enumArchivesInfoType, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();
		int[] pArrayNBXHs=null;

		try
		{
			pErrPos = 1;
			//����Ƿ��н���DAO����ע��
			if (checkDaoInjectionForArchivesInfoWorking(pErrInfo) == false)
			{
				pFlag = false;
			}
			
			//����Ƿ��н���ҵ���߼����������ע��
			if (pFlag)
			{
				if (checkBllInjection(pErrInfo) == false)
				{
					pFlag = false;
				}
			}
			
			//�����ڲ��������
			if (pFlag)
			{
				pArrayNBXHs=new int[archivesInfos.size()];
				int i=0;
				for (ArchivesInfo item : archivesInfos)
				{
					pArrayNBXHs[i]=item.getNBXH();
					i=i+1;
				}
			}
			
			//�����ύ��������״̬
			if (pFlag)
			{
				pErrPos = 2;
				if (setFlagForWorkFlow(enumArchivesInfoType, archivesType.getID(), pArrayNBXHs, userID, EnumWorkFlowStatus.�ύ�������, pErrInfo)==false)
				{
					pFlag = false;
					pErrInfo.getContent().insert(0, "���õ���������״̬ʧ��: ");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#statInputCheckNeedCount(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean statInputCheckNeedCount(int[] userID, Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#inputCheckPass(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean inputCheckPass(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {	
				if (this.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()}, userID, EnumWorkFlowStatus.ҵ��ָ������¼���ͨ��, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ��¼������ʧ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#inputCheckBack(int, com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, java.lang.String, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean inputCheckBack(int userID, ArchivesType archivesType, ArchivesInfo archivesInfo, String backReason, ErrInfo pErrInfo)
	{
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;

			//д���ԭ��
			if (pFlag) {	
				if (archivesInfoWorkingDao.updateArchivesBackReason(archivesInfo.getNBXH(),archivesType,backReason,pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���µ�����Ϣ���ԭ��ʧ��");
				}
			}
			
			//���õ�����Ϣ������Ϊ��¼���
			if (pFlag) {	
				if (this.setFlagForWorkFlow(null, archivesType.getID(), new int []{archivesInfo.getNBXH()},userID, EnumWorkFlowStatus.ҵ��ָ������¼��˴��, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���õ���������Ϊ��¼������ʧ��");
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
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#statInputCheckResult(int[], java.util.Map, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean statInputCheckResult(int[] userID, Map<Integer, ArchivesTypeEx> archivesTypeEx, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckPassArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckPassArchivesInfos(int[] userID, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#findInputCheckBackArchivesInfos(int[], com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.DataPageInfo, java.util.List, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean findInputCheckBackArchivesInfos(int[] userID, ArchivesType archivesType, DataPageInfo dataPageInfo, List<ArchivesInfo> archivesInfos, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#fixArchivesInfoForInputCheckBack(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean fixArchivesInfoForInputCheckBack(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.orifound.aiim.bll.service.IArchivesInfoWorkingManageService#fixArchivesInfoForPaperCheckBack(com.orifound.aiim.entity.ArchivesType, com.orifound.aiim.entity.ArchivesInfo, com.orifound.aiim.entity.ErrInfo)
	 */
	@Override
	public boolean fixArchivesInfoForPaperCheckBack(ArchivesType archivesType, ArchivesInfo archivesInfo, ErrInfo pErrInfo)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateArchivesInfoFixedFlag(ArchivesType archivesType, int NBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			pErrPos = 1;

			//д���ԭ��
			if (pFlag) {	
				if (archivesInfoWorkingDao.updateArchivesInfoFixedFlag(archivesType, NBXH, pErrInfo)== false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"���µ�����Ϣ�޸���־ʧ��");
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
	public boolean deleteParentAndChild(ArchivesType archivesType, int nBXH, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		try {
			//��ʼ���� 1...
			pErrPos = 1;
			

			//��ʼ����2...
			if (pFlag) {
				pErrPos = 2;
				ArchivesInfo archivesInfo = new ArchivesInfo(archivesType);
				archivesInfo.setNBXH(nBXH);
				if (archivesInfoWorkingDao.delete(archivesType, archivesInfo, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0,"ɾ��������Ϣʧ�ܣ� ");
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
	public boolean insertFileToArchives(int userID, ArchivesType archivesType, int parentNBXH, int[] nBXHS, ErrInfo pErrInfo) {
		boolean pFlag = true;
		int pErrPos = 0;
		Throwable throwable = new Throwable();

		List<ArchivesInfo> archivesInfos = null;
		try {
			//��ʼ���� 1...
			pErrPos = 1;
			
			IntegerEx maxSubContentID=new IntegerEx();
			if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, parentNBXH, maxSubContentID, pErrInfo)==false)
			{
				pFlag = false;
				pErrInfo.getContent().insert(0, "��ȡ�������ļ����ֵʧ��: ");
			}
			
			if (pFlag) {	
				archivesInfos = new ArrayList<ArchivesInfo>();
				ArchivesInfo archivesInfo = null;
				for (int i = 0; i < nBXHS.length; i++) {
					archivesInfo = new ArchivesInfo(archivesType);
					archivesInfo.setNBXH(nBXHS[i]);
					//����parentNBXH
					archivesInfo.setParentNBXH(parentNBXH);
					archivesInfo.setContentID(0);
					archivesInfo.setContentIDText("");
					
					//�����ļ����ı�
//					IntegerEx maxSubContentID=new IntegerEx();
//					if (archivesInfoWorkingDao.findMaxSubContentID(archivesType, parentNBXH, maxSubContentID, pErrInfo)==false)
//					{
//						pFlag = false;
//						pErrInfo.getContent().insert(0, "��ȡ�������ļ����ֵʧ��: ");
//					}
//					else 
//					{
//						//����ǰ�������ļ����ֵ+1����������ļ��������ֵ
						archivesInfo.setSubContentID(maxSubContentID.getValue()+1);
//					}
					
					if (pFlag) {
						//���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵ
						if (pFlag)
						{
							if (setValueForSubContentIDText(archivesType, archivesInfo, pErrInfo)==false)
							{
								pFlag = false;
								pErrInfo.getContent().insert(0, "���þ����ļ���Ÿ�ʽ���ı��ֶ�ֵʧ��: ");
							}
						}
					}
					archivesInfos.add(archivesInfo);
					maxSubContentID.setValue(maxSubContentID.getValue()+1);
				}
			}
			
			//�����ļ����ı������������ڲ����
			if (pFlag) {
				if (archivesInfoWorkingDao.batchUpdateParentNBXH(archivesType, archivesInfos, userID, EnumWorkFlowStatus.��¼���, pErrInfo) == false) {
					pFlag = false;
					pErrInfo.getContent().insert(0, "�����ļ����ı������������ڲ����: ");
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
