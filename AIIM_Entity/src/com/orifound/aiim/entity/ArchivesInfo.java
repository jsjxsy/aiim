package com.orifound.aiim.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    
/**
 * ������Ϣʵ����
 */
public class ArchivesInfo
{
   
	/**
	* ���ֶβ����Ĺ��캯��
	* @param nBXH �ڲ����
	* @param backupDiskNo �����̺�
	* @param workFlowStatus ������������״̬
	* @param contentID �����
	* @param contentIDText ������ı�
	* @param subContentID �����ļ����
	* @param subContentIDText �����ļ�����ı�
	* @param parentFlag �����־
	* @param parentNBXH ����������ڲ����
	* @param archivesFondsID ȫ�ڱ��
	* @param archivesTypeID ����������
	* @param archivesTypeCode �����������
	* @param archivesID ����
	* @param title ����
	* @param subContentCount �����ļ�����
	* @param pageSum ����ҳ��
	* @param retentionPeriodID �������ޱ��
	* @param retentionPeriodText ���������ı�
	* @param retentionEndYear �����ڽ�ֹ���
	* @param secrecyID �����ܼ����
	* @param secrecyText �����ܼ��ı�
	* @param formationYear �����γ����
	* @param formationDepartmentID �����γɲ���
	* @param formationDepartment �����γɲ�������
	* @param saveDate �鵵����
	* @param fixedFlag �޸���־
	* @param sendBackReason ���ԭ��
	* @param barcode ��������
	* @param deleteFlag ɾ����־
	* @param publicFlag ���ű�־
	* @param userID1 �û����1
	* @param userID2 �û����2
	* @param userID3 �û����3
	*/
	public ArchivesInfo(int nBXH,int backupDiskNo,int workFlowStatus,int contentID,String contentIDText,int subContentID,String subContentIDText,boolean parentFlag,int parentNBXH,String archivesFondsID,int archivesTypeID,String archivesTypeCode,String archivesID,String title,int subContentCount,int pageSum,int retentionPeriodID,String retentionPeriodText,int retentionEndYear,int secrecyID,String secrecyText,int formationYear,int formationDepartmentID,String formationDepartment,Date saveDate,boolean fixedFlag,String sendBackReason,String barcode,boolean deleteFlag,boolean publicFlag,int userID1,int userID2,int userID3)
	{
		// Table Name: ArchivesInfoWorking_TypeCode
		// Columns List,Can Used in SELECT SQL: NBXH,BackupDiskNo,WorkFlowStatus,ContentID,ContentIDText,SubContentID,SubContentIDText,ParentFlag,ParentNBXH,ArchivesFondsID,ArchivesTypeID,ArchivesTypeCode,ArchivesID,Title,SubContentCount,PageSum,RetentionPeriodID,RetentionPeriodText,RetentionEndYear,SecrecyID,SecrecyText,FormationYear,FormationDepartmentID,FormationDepartment,SaveDate,FixedFlag,SendBackReason,Barcode,DeleteFlag,PublicFlag,UserID1,UserID2,UserID3
		// Columns List,Can Used in INSERT SQL: :NBXH,:BackupDiskNo,:WorkFlowStatus,:ContentID,:ContentIDText,:SubContentID,:SubContentIDText,:ParentFlag,:ParentNBXH,:ArchivesFondsID,:ArchivesTypeID,:ArchivesTypeCode,:ArchivesID,:Title,:SubContentCount,:PageSum,:RetentionPeriodID,:RetentionPeriodText,:RetentionEndYear,:SecrecyID,:SecrecyText,:FormationYear,:FormationDepartmentID,:FormationDepartment,:SaveDate,:FixedFlag,:SendBackReason,:Barcode,:DeleteFlag,:PublicFlag,:UserID1,:UserID2,:UserID3
		// Columns List,Can Used in UPDATE SQL: NBXH=:NBXH,BackupDiskNo=:BackupDiskNo,WorkFlowStatus=:WorkFlowStatus,ContentID=:ContentID,ContentIDText=:ContentIDText,SubContentID=:SubContentID,SubContentIDText=:SubContentIDText,ParentFlag=:ParentFlag,ParentNBXH=:ParentNBXH,ArchivesFondsID=:ArchivesFondsID,ArchivesTypeID=:ArchivesTypeID,ArchivesTypeCode=:ArchivesTypeCode,ArchivesID=:ArchivesID,Title=:Title,SubContentCount=:SubContentCount,PageSum=:PageSum,RetentionPeriodID=:RetentionPeriodID,RetentionPeriodText=:RetentionPeriodText,RetentionEndYear=:RetentionEndYear,SecrecyID=:SecrecyID,SecrecyText=:SecrecyText,FormationYear=:FormationYear,FormationDepartmentID=:FormationDepartmentID,FormationDepartment=:FormationDepartment,SaveDate=:SaveDate,FixedFlag=:FixedFlag,SendBackReason=:SendBackReason,Barcode=:Barcode,DeleteFlag=:DeleteFlag,PublicFlag=:PublicFlag,UserID1=:UserID1,UserID2=:UserID2,UserID3=:UserID3

		setNBXH(nBXH);
		setBackupDiskNo(backupDiskNo);
		setWorkFlowStatus(EnumWorkFlowStatus.getEnumElement(workFlowStatus));
		setContentID(contentID);
		setContentIDText(contentIDText);
		setSubContentID(subContentID);
		setSubContentIDText(subContentIDText);
		setParentFlag(parentFlag);
		setParentNBXH(parentNBXH);
		setArchivesFondsID(archivesFondsID);
		setArchivesTypeID(archivesTypeID);
		setArchivesTypeCode(archivesTypeCode);
		setArchivesID(archivesID);
		setTitle(title);
		setSubContentCount(subContentCount);
		setPageSum(pageSum);
		setRetentionPeriodID(retentionPeriodID);
		setRetentionPeriodText(retentionPeriodText);
		setRetentionEndYear(retentionEndYear);
		setSecrecyID(secrecyID);
		setFormationYear(formationYear);
		setFormationDepartmentID(formationDepartmentID);
		setSaveDate(saveDate);
		setFixedFlag(fixedFlag);
		setSendBackReason(sendBackReason);
		setBarcode(barcode);
		setDeleteFlag(deleteFlag);
		setPublicFlag(publicFlag);
		setUserID1(userID1);
		setUserID2(userID2);
		setUserID3(userID3);
	}

	/**
	 * ���캯��<br>
	 * ע�⣺�ù��캯��������ҵ���߼�������ݷ��ʲ��ڲ����ʣ�Ҳ�������ڱ�ʾ������ڲ���Ų�ѯ������Ϣ��Ӧ�ó���<br>
	 * ��ʾ���ڱ��浵����Ϣ��ʱӦ��ͨ��ҵ���߼����ArchivesInfoFactory��������������ArchivesInfo����<br>
	 */
	public ArchivesInfo()
	{
		
	}
	
	/**
	 * ���ֶεĹ��캯��<br>
	 * �Զ�������������ϣ���������ǿ�ֵ���䱾���ȱʡֵ<br>
	 * ע�⣺�ù��캯����������ҵ���߼�������ݷ��ʲ��ڲ����ʣ�<br>
	 * ��ʾ�㲻�ܹ�ʹ�øù��캯������Ӧ��ͨ��ҵ���߼����ArchivesInfoFactory��������������ArchivesInfo����
	 * @param archivesType ָ���ĵ���������Ϣ�������Ǿ߱�����������ֵ�����ܹ����е�������������ֵ
     */
    public ArchivesInfo(ArchivesType archivesType)
    {
    	if (archivesType!=null)
		{
			if (archivesType.getDataItemsForAll()!=null)
			{
				//�������¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
				Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
				for (ArchivesTypeDataItem item : archivesType.getDataItemsForAll().values())
				{
					FieldValue fieldValue=new FieldValue(item);
					//����ȱʡֵ
					if (item.getDefaultValue()!=null)
					{
						//���ȱʡֵ�ǻ�ȡϵͳʱ�䣬��ô���⴦��һ��
						//���������չ�����������ȱʡֵ��Ҳ�ڴ˽��д���
						if (item.getDefaultValue().equalsIgnoreCase("getdate()"))
						{
							SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							fieldValue.setValue(dateFormater.format(new Date()));
						}
						else 
						{
							fieldValue.setValue(item.getDefaultValue());
						}
					}
					
					//���õ������������ֵ
					if (item.getSystemDataItemType()==EnumSystemDataItem.����������)
					{
						fieldValue.setValue(String.valueOf(archivesType.getID()));
					}
					
					//��������¼�е��ֶ�ֵ����
					pRowFieldsValues.put(fieldValue.getColumnName(), fieldValue);
				}
				
				//��������ֵ
				this.rowFieldsValues=pRowFieldsValues;
				
				pRowFieldsValues=null;
			}
		}
    }
    
	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}
	

	/**
	 * ��ȡ����ֵ���ڲ����
	 * @return �ڲ����
	 */
	public int getNBXH()
	{
		int nBXH=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�ڲ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						nBXH=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			nBXH=0;
		}
		
		return nBXH;
	}

	/**
	 * ��������ֵ���ڲ����
	 * @param nBXH �ڲ����
	 */
	public void setNBXH(int nBXH)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�ڲ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(nBXH));
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ�������̺�
	 * @return �����̺�
	 */
	public int getBackupDiskNo()
	{
		int backupDiskNo=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����̺�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						backupDiskNo=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			backupDiskNo=0;
		}
		
		return backupDiskNo;
	}

	/**
	 * ��������ֵ�������̺�
	 * @param backupDiskNo �����̺�
	 */
	public void setBackupDiskNo(int backupDiskNo)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����̺�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(backupDiskNo));
			}
		}
	}

	/**
	 * ��ȡ����ֵ��������������״̬
	 * @return ������������״̬
	 */
	public EnumWorkFlowStatus getWorkFlowStatus()
	{
		EnumWorkFlowStatus workFlowStatus=EnumWorkFlowStatus.NONE;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������������״̬.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						workFlowStatus=EnumWorkFlowStatus.getEnumElement(Integer.parseInt(rowFieldsValues.get(pFieldName).getValue()));
					}
				}
			}
		}
		catch (Exception e)
		{
			workFlowStatus=EnumWorkFlowStatus.NONE;
		}
		
		return workFlowStatus;
	}

	/**
	 * ��������ֵ��������������״̬
	 * @param workFlowStatus ������������״̬
	 */
	public void setWorkFlowStatus(EnumWorkFlowStatus workFlowStatus)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������������״̬.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(workFlowStatus.getEnumValue()));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������
	 * @return �����
	 */
	public int getContentID()
	{
		int contentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						contentID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			contentID=0;
		}
		
		return contentID;
	}

	/**
	 * ��������ֵ�������
	 * @param contentID �����
	 */
	public void setContentID(int contentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(contentID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ��������ı�
	 * @return ������ı�
	 */
	public String getContentIDText()
	{
		String contentIDText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����Ÿ�ʽ���ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					contentIDText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			contentIDText="";
		}
		
		return contentIDText;
	}

	/**
	 * ��������ֵ��������ı�
	 * @param contentIDText ������ı�
	 */
	public void setContentIDText(String contentIDText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����Ÿ�ʽ���ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(contentIDText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ļ����
	 * @return �����ļ����
	 */
	public int getSubContentID()
	{
		int subContentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ļ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						subContentID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			subContentID=0;
		}
		
		return subContentID;
	}

	/**
	 * ��������ֵ�������ļ����
	 * @param subContentID �����ļ����
	 */
	public void setSubContentID(int subContentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ļ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(subContentID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ļ�����ı�
	 * @return �����ļ�����ı�
	 */
	public String getSubContentIDText()
	{
		String subContentIDText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ļ���Ÿ�ʽ���ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					subContentIDText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			subContentIDText="";
		}
		
		return subContentIDText;
	}

	/**
	 * ��������ֵ�������ļ�����ı�
	 * @param subContentIDText �����ļ�����ı�
	 */
	public void setSubContentIDText(String subContentIDText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ļ���Ÿ�ʽ���ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(subContentIDText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������־
	 * @return �����־
	 */
	public boolean getParentFlag()
	{
		boolean parentFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����־.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						if (rowFieldsValues.get(pFieldName).getValue().equals("0"))
						{
							parentFlag=false;
						}
						else
						{
							parentFlag=true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			parentFlag=false;
		}
		
		return parentFlag;
	}

	/**
	 * ��������ֵ�������־
	 * @param parentFlag �����־
	 */
	public void setParentFlag(boolean parentFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����־.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(parentFlag?"1":"0");
			}
		}
	}

	/**
	 * ��ȡ����ֵ������������ڲ����
	 * @return ����������ڲ����
	 */
	public int getParentNBXH()
	{
		int parentNBXH=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����������ڲ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						parentNBXH=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			parentNBXH=0;
		}
		
		return parentNBXH;
	}

	/**
	 * ��������ֵ������������ڲ����
	 * @param parentNBXH ����������ڲ����
	 */
	public void setParentNBXH(int parentNBXH)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����������ڲ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(parentNBXH));
			}
		}
	}
	

	/**
	 * ��ȡ����ֵ��ȫ�����ֱ��
	 * @return ȫ�����ֱ��
	 */
	public int getFondsID()
	{
		int fondsID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.ȫ�����ֱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						fondsID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			fondsID=0;
		}
		
		return fondsID;
	}

	/**
	 * ��������ֵ��ȫ�����ֱ��
	 * @param fondsID ȫ�����ֱ��
	 */
	public void setFondsID(int fondsID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.ȫ�����ֱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(fondsID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ��ȫ�ڱ��
	 * @return ȫ�ڱ��
	 */
	public String getArchivesFondsID()
	{
		String archivesFondsID=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.ȫ�ڱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					archivesFondsID=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			archivesFondsID="";
		}
		
		return archivesFondsID;
	}

	/**
	 * ��������ֵ��ȫ�ڱ��
	 * @param archivesFondsID ȫ�ڱ��
	 */
	public void setArchivesFondsID(String archivesFondsID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.ȫ�ڱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesFondsID);
			}
		}
	}

	/**
	 * ��ȡ����ֵ������������
	 * @return ����������
	 */
	public int getArchivesTypeID()
	{
		int archivesTypeID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						archivesTypeID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			archivesTypeID=0;
		}
		
		return archivesTypeID;
	}

	/**
	 * ��������ֵ������������
	 * @param archivesTypeID ����������
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(archivesTypeID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ������������루ʵ�����ţ�
	 * @return ����������루ʵ�����ţ�
	 */
	public String getArchivesTypeCode()
	{
		String archivesTypeCode=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.ʵ������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					archivesTypeCode=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			archivesTypeCode="";
		}
		
		return archivesTypeCode;
	}

	/**
	 * ��������ֵ������������루ʵ�����ţ�
	 * @param archivesTypeCode ����������루ʵ�����ţ�
	 */
	public void setArchivesTypeCode(String archivesTypeCode)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.ʵ������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesTypeCode);
			}
		}
	}

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getArchivesID()
	{
		String archivesID=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					archivesID=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			archivesID="";
		}
		
		return archivesID;
	}

	/**
	 * ��������ֵ������
	 * @param archivesID ����
	 */
	public void setArchivesID(String archivesID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesID);
			}
		}
	}

	/**
	 * ��ȡ����ֵ������
	 * @return ����
	 */
	public String getTitle()
	{
		String title=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					title=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			title="";
		}
		
		return title;
	}

	/**
	 * ��������ֵ������
	 * @param title ����
	 */
	public void setTitle(String title)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(title);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ļ�����
	 * @return �����ļ�����
	 */
	public int getSubContentCount()
	{
		int subContentCount=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ļ�����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						subContentCount=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			subContentCount=0;
		}
		
		return subContentCount;
	}

	/**
	 * ��������ֵ�������ļ�����
	 * @param subContentCount �����ļ�����
	 */
	public void setSubContentCount(int subContentCount)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ļ�����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(subContentCount));
			}
		}
	}

	/**
	 * ��ȡ����ֵ������ҳ��
	 * @return ����ҳ��
	 */
	public int getPageSum()
	{
		int pageSum=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.����ҳ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						pageSum=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			pageSum=0;
		}
		
		return pageSum;
	}

	/**
	 * ��������ֵ������ҳ��
	 * @param pageSum ����ҳ��
	 */
	public void setPageSum(int pageSum)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.����ҳ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(pageSum));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���������ޱ��
	 * @return �������ޱ��
	 */
	public int getRetentionPeriodID()
	{
		int retentionPeriodID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�������ޱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						retentionPeriodID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			retentionPeriodID=0;
		}
		
		return retentionPeriodID;
	}

	/**
	 * ��������ֵ���������ޱ��
	 * @param retentionPeriodID �������ޱ��
	 */
	public void setRetentionPeriodID(int retentionPeriodID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�������ޱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(retentionPeriodID));
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ�����������ı�
	 * @return ���������ı�
	 */
	public String getRetentionPeriodText()
	{
		String retentionPeriodText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.���������ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					retentionPeriodText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			retentionPeriodText=null;
		}
		
		return retentionPeriodText;
	}

	/**
	 * ��������ֵ�����������ı�
	 * @param retentionPeriodText ���������ı�
	 */
	public void setRetentionPeriodText(String retentionPeriodText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.���������ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(retentionPeriodText);
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ�������ڽ�ֹ���
	 * @return �����ڽ�ֹ���
	 */
	public int getRetentionEndYear()
	{
		int retentionEndYear=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ڽ�ֹ���.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						retentionEndYear=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			retentionEndYear=0;
		}
		
		return retentionEndYear;
	}

	/**
	 * ��������ֵ�������ڽ�ֹ���
	 * @param retentionEndYear �����ڽ�ֹ���
	 */
	public void setRetentionEndYear(int retentionEndYear)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ڽ�ֹ���.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(retentionEndYear));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ܼ����
	 * @return �����ܼ����
	 */
	public int getSecrecyID()
	{
		int secrecyID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ܼ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						secrecyID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			secrecyID=0;
		}
		
		return secrecyID;
	}

	/**
	 * ��������ֵ�������ܼ����
	 * @param secrecyID �����ܼ����
	 */
	public void setSecrecyID(int secrecyID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ܼ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(secrecyID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ܼ��ı�
	 * @return �����ܼ��ı�
	 */
	public String getSecrecyText()
	{
		String secrecyText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ܼ��ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					secrecyText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			secrecyText=null;
		}
		
		return secrecyText;
	}

	/**
	 * ��������ֵ�������ܼ��ı�
	 * @param secrecyText �����ܼ��ı�
	 */
	public void setSecrecyText(String secrecyText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ܼ��ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(secrecyText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γ����
	 * @return �����γ����
	 */
	public int getFormationYear()
	{
		int formationYear=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����γ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					formationYear=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
				}
			}
		}
		catch (Exception e)
		{
			formationYear=0;
		}
		
		return formationYear;
	}

	/**
	 * ��������ֵ�������γ����
	 * @param formationYear �����γ����
	 */
	public void setFormationYear(int formationYear)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����γ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(formationYear));
			}
		}
	}
	
	//����--2010-8-11--�������ԣ������γ�ʱ��
	/**
	 * ��ȡ����ֵ�������γ�ʱ��
	 * @return �����γ�ʱ��
	 */
	public String getFormationDate()
	{
		String formationDate=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����γ�ʱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					formationDate=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			formationDate="";
		}
		
		return formationDate;
	}

	/**
	 * ��������ֵ�������γ�ʱ��
	 * @param formationDate �����γ�ʱ��
	 */
	public void setFormationDate(String formationDate)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����γ�ʱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(formationDate);
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public String getResponsibility()
	{
		String responsibility=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					responsibility=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			responsibility=null;
		}
		
		return responsibility;
	}

	/**
	 * ��������ֵ��������
	 * @param responsibility ������
	 */
	public void setResponsibility(String responsibility)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(responsibility);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γɲ��ű��
	 * @return �����γɲ��ű��
	 */
	public int getFormationDepartmentID()
	{
		int formationDepartmentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����γɲ��ű��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						formationDepartmentID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			formationDepartmentID=0;
		}
		
		return formationDepartmentID;
	}

	/**
	 * ��������ֵ�������γɲ��ű��
	 * @param formationDepartmentID �����γɲ��ű��
	 */
	public void setFormationDepartmentID(int formationDepartmentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����γɲ��ű��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(formationDepartmentID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γɲ�������
	 * @return �����γɲ�������
	 */
	public String getFormationDepartment()
	{
		String formationDepartment=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����γɲ�������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					formationDepartment=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			formationDepartment=null;
		}
		
		return formationDepartment;
	}

	/**
	 * ��������ֵ�������γɲ�������
	 * @param formationDepartment �����γɲ�������
	 */
	public void setFormationDepartment(String formationDepartment)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����γɲ�������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(formationDepartment);
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ��������
	 * @return ������
	 */
	public int getCarrierID()
	{
		int carrierID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						carrierID=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			carrierID=0;
		}
		
		return carrierID;
	}

	/**
	 * ��������ֵ��������
	 * @param carrierID ������
	 */
	public void setCarrierID(int carrierID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(carrierID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ı�
	 * @return �����ı�
	 */
	public String getCarrierText()
	{
		String carrierText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					carrierText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			carrierText=null;
		}
		
		return carrierText;
	}
	

	/**
	 * ��������ֵ�������ı�
	 * @param carrierText �����ı�
	 */
	public void setCarrierText(String carrierText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(carrierText);
			}
		}
	}

	/**
	 * ��������ֵ�������
	 * @param keyWord �����
	 */
	public void setKeyWord(String keyWord)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(keyWord);
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ�������
	 * @return �����
	 */
	public String getKeyWord()
	{
		String keyWord=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					keyWord=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			keyWord=null;
		}
		
		return keyWord;
	}

	/**
	 * ��������ֵ��ȫ��
	 * @param fullText ȫ��
	 */
	public void setFullText(String fullText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.ȫ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(fullText);
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ��ȫ��
	 * @return ȫ��
	 */
	public String getFullText()
	{
		String fullText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.ȫ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					fullText=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			fullText=null;
		}
		
		return fullText;
	}
	

	/**
	 * ��ȡ����ֵ���鵵����
	 * @return �鵵����
	 */
	public Date getSaveDate()
	{
		Date saveDate=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�鵵����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						saveDate=dateFormater.parse(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			saveDate=null;
		}
		
		return saveDate;
	}

	/**
	 * ��������ֵ���鵵����
	 * @param saveDate �鵵����
	 */
	public void setSaveDate(Date saveDate)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�鵵����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				rowFieldsValues.get(pFieldName).setValue(dateFormater.format(saveDate));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���޸���־
	 * @return �޸���־
	 */
	public boolean getFixedFlag()
	{
		boolean fixedFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�޸���־.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						if (rowFieldsValues.get(pFieldName).getValue().equals("0"))
						{
							fixedFlag=false;
						}
						else
						{
							fixedFlag=true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			fixedFlag=false;
		}
		
		return fixedFlag;
	}

	/**
	 * ��������ֵ���޸���־
	 * @param fixedFlag �޸���־
	 */
	public void setFixedFlag(boolean fixedFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.�޸���־.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(fixedFlag?"1":"0");
			}
		}
	}

	/**
	 * ��ȡ����ֵ�����ԭ��
	 * @return ���ԭ��
	 */
	public String getSendBackReason()
	{
		String sendBackReason=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.���ԭ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					sendBackReason=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			sendBackReason="";
		}
		
		return sendBackReason;
	}

	/**
	 * ��������ֵ�����ԭ��
	 * @param sendBackReason ���ԭ��
	 */
	public void setSendBackReason(String sendBackReason)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.���ԭ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(sendBackReason);
			}
		}
	}

	/**
	 * ��ȡ����ֵ����������
	 * @return ��������
	 */
	public String getBarcode()
	{
		String barcode=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.��������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					barcode=rowFieldsValues.get(pFieldName).getValue();
				}
			}
		}
		catch (Exception e)
		{
			barcode="";
		}
		
		return barcode;
	}

	/**
	 * ��������ֵ����������
	 * @param barcode ��������
	 */
	public void setBarcode(String barcode)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.��������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(barcode);
			}
		}
	}

	/**
	 * ��ȡ����ֵ��ɾ����־
	 * @return ɾ����־
	 */
	public boolean getDeleteFlag()
	{
		boolean deleteFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.ɾ����־.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						if (rowFieldsValues.get(pFieldName).getValue().equals("0"))
						{
							deleteFlag=false;
						}
						else
						{
							deleteFlag=true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			deleteFlag=false;
		}
		
		return deleteFlag;
	}

	/**
	 * ��������ֵ��ɾ����־
	 * @param deleteFlag ɾ����־
	 */
	public void setDeleteFlag(boolean deleteFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.ɾ����־.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(deleteFlag?"1":"0");
			}
		}
	}

	/**
	 * ��ȡ����ֵ�����ű�־
	 * @return ���ű�־
	 */
	public boolean getPublicFlag()
	{
		boolean publicFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.���ű�־.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						if (rowFieldsValues.get(pFieldName).getValue().equals("0"))
						{
							publicFlag=false;
						}
						else
						{
							publicFlag=true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			publicFlag=false;
		}
		
		return publicFlag;
	}

	/**
	 * ��������ֵ�����ű�־
	 * @param publicFlag ���ű�־
	 */
	public void setPublicFlag(boolean publicFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.���ű�־.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(publicFlag?"1":"0");
			}
		}
	}
	
	/**
	 * ��ȡ����ֵ���û����1
	 * @return �û����1
	 */
	public int getUserID1()
	{
		int userID1=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������Ա1.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						userID1=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			userID1=0;
		}
		
		return userID1;
	}

	/**
	 * ��������ֵ���û����1
	 * @param userID1 �û����1
	 */
	public void setUserID1(int userID1)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������Ա1.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID1));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���û����2
	 * @return �û����2
	 */
	public int getUserID2()
	{
		int userID2=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������Ա2.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						userID2=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			userID2=0;
		}
		
		return userID2;
	}

	/**
	 * ��������ֵ���û����2
	 * @param userID2 �û����2
	 */
	public void setUserID2(int userID2)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������Ա2.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID2));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���û����3
	 * @return �û����3
	 */
	public int getUserID3()
	{
		int userID3=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.������Ա3.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						userID3=Integer.parseInt(rowFieldsValues.get(pFieldName).getValue());
					}
				}
			}
		}
		catch (Exception e)
		{
			userID3=0;
		}
		
		return userID3;
	}

	/**
	 * ��������ֵ���û����3
	 * @param userID3 �û����3
	 */
	public void setUserID3(int userID3)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.������Ա3.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID3));
			}
		}
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesInfo clone()
	{
		ArchivesInfo returnItem = new ArchivesInfo();
		Map<String, FieldValue> pRowFieldsValues=new HashMap<String, FieldValue>();
		if (rowFieldsValues!=null)
		{
			for (FieldValue item : rowFieldsValues.values())
			{
				FieldValue cloneItem=item.clone();
				pRowFieldsValues.put(cloneItem.getColumnName(), cloneItem);
			}
		}

		returnItem.setRowFieldsValues(pRowFieldsValues);
		returnItem.setKeyInCol(keyInCol);
		returnItem.setTag(tag);

		return returnItem;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesInfo ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesInfo archivesInfo)
	{
		Map<String, FieldValue> pRowFieldsValues=new HashMap<String, FieldValue>();
		if (archivesInfo.getRowFieldsValues()!=null)
		{
			for (FieldValue item : archivesInfo.getRowFieldsValues().values())
			{
				FieldValue cloneItem=item.clone();
				pRowFieldsValues.put(cloneItem.getColumnName(), cloneItem);
			}
		}
		this.rowFieldsValues=pRowFieldsValues;
		this.keyInCol=archivesInfo.getKeyInCol();
		this.tag=archivesInfo.getTag();
	}


	/**
	 * ��ǰ�������ӵ�ԭ�ĵ����ļ���Ϣ
	 */
	private List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles;
	
	/**
	 * ��������ֵ����ǰ�������ӵ�ԭ�ĵ����ļ���Ϣ
	 * @param archivesInfoAttachedFiles ��ǰ�������ӵ�ԭ�ĵ����ļ���Ϣ
	 */
	public void setArchivesInfoAttachedFiles(
			List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles) {
		this.archivesInfoAttachedFiles = archivesInfoAttachedFiles;
	}
	
	/**
	 * ��ȡ����ֵ����ǰ�������ӵ�ԭ�ĵ����ļ���Ϣ
	 * @return ��ǰ�������ӵ�ԭ�ĵ����ļ���Ϣ
	 */
	public List<ArchivesInfoAttachedFile> getArchivesInfoAttachedFiles() {
		return archivesInfoAttachedFiles;
	}

	/**
	 * �����ļ���Ϣ
	 */
	private List<ArchivesInfo> childArchivesInfos = null;

	/**
	 * ��������ֵ�������ļ���Ϣ
	 * @param childArchivesInfos �����ļ���Ϣ
	 */
	public void setChildArchivesInfos(List<ArchivesInfo> childArchivesInfos) {
		this.childArchivesInfos = childArchivesInfos;
	}

	/**
	 * ��ȡ����ֵ�������ļ���Ϣ
	 * @return �����ļ���Ϣ
	 */
	public List<ArchivesInfo> getChildArchivesInfos() {
		return childArchivesInfos;
	}

	/**
	 * ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	private Map<String, FieldValue> rowFieldsValues = null;

	/**
	 * ��������ֵ�����¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 * @param rowFieldsValues ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setRowFieldsValues(Map<String, FieldValue> rowFieldsValues)
	{
		this.rowFieldsValues = rowFieldsValues;
	}

	/**
	 * ��ȡ����ֵ�����¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 * @return ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	public Map<String, FieldValue> getRowFieldsValues()
	{
		return rowFieldsValues;
	}
	
	/**
	 * ����ǰ�����������
	 */
	private int totalYears;

	public int getTotalYears() {
		return totalYears;
	}

	public void setTotalYears(int totalYears) {
		this.totalYears = totalYears;
	}
	
	/**
	 * �ϼ�λ����������
	 */
	private String StoreAddressFullName;

	public String getStoreAddressFullName() {
		return StoreAddressFullName;
	}

	public void setStoreAddressFullName(String storeAddressFullName) {
		StoreAddressFullName = storeAddressFullName;
	}
	
	/**
	 * ҵ��ָ���Ҳ�������
	 */
	private String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	@Override
	public boolean equals(Object obj) {
		ArchivesInfo archivesInfo = (ArchivesInfo)obj;
		return (this.getNBXH() == archivesInfo.getNBXH() && this.getArchivesTypeID() == archivesInfo.getArchivesTypeID());
	}
}