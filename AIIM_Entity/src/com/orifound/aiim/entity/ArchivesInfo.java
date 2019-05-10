package com.orifound.aiim.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
    
/**
 * 档案信息实体类
 */
public class ArchivesInfo
{
   
	/**
	* 带字段参数的构造函数
	* @param nBXH 内部序号
	* @param backupDiskNo 备份盘号
	* @param workFlowStatus 档案管理工作流状态
	* @param contentID 案卷号
	* @param contentIDText 案卷号文本
	* @param subContentID 卷内文件序号
	* @param subContentIDText 卷内文件序号文本
	* @param parentFlag 案卷标志
	* @param parentNBXH 所属案卷的内部序号
	* @param archivesFondsID 全宗编号
	* @param archivesTypeID 档案分类编号
	* @param archivesTypeCode 档案分类编码
	* @param archivesID 档号
	* @param title 题名
	* @param subContentCount 卷内文件数量
	* @param pageSum 档案页数
	* @param retentionPeriodID 保管期限编号
	* @param retentionPeriodText 保管期限文本
	* @param retentionEndYear 保管期截止年度
	* @param secrecyID 档案密级编号
	* @param secrecyText 档案密级文本
	* @param formationYear 档案形成年度
	* @param formationDepartmentID 档案形成部门
	* @param formationDepartment 档案形成部门名称
	* @param saveDate 归档日期
	* @param fixedFlag 修复标志
	* @param sendBackReason 打回原因
	* @param barcode 档案条码
	* @param deleteFlag 删除标志
	* @param publicFlag 开放标志
	* @param userID1 用户编号1
	* @param userID2 用户编号2
	* @param userID3 用户编号3
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
	 * 构造函数<br>
	 * 注意：该构造函数适用于业务逻辑层和数据访问层内部访问，也可适用于表示层根据内部序号查询档案信息的应用场景<br>
	 * 表示层在保存档案信息你时应该通过业务逻辑层的ArchivesInfoFactory工厂类来生产出ArchivesInfo对象<br>
	 */
	public ArchivesInfo()
	{
		
	}
	
	/**
	 * 带字段的构造函数<br>
	 * 自动构建好数据项集合，各数据项都是空值或其本身的缺省值<br>
	 * 注意：该构造函数仅适用于业务逻辑层和数据访问层内部访问，<br>
	 * 表示层不能够使用该构造函数，而应该通过业务逻辑层的ArchivesInfoFactory工厂类来生产出ArchivesInfo对象
	 * @param archivesType 指定的档案分类信息，必须是具备完整的属性值，不能够仅有档案分类编号属性值
     */
    public ArchivesInfo(ArchivesType archivesType)
    {
    	if (archivesType!=null)
		{
			if (archivesType.getDataItemsForAll()!=null)
			{
				//构建表记录行的字段值集合，以字段名作为关键字
				Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
				for (ArchivesTypeDataItem item : archivesType.getDataItemsForAll().values())
				{
					FieldValue fieldValue=new FieldValue(item);
					//设置缺省值
					if (item.getDefaultValue()!=null)
					{
						//如果缺省值是获取系统时间，那么特殊处理一下
						//如果将来扩展有其他特殊的缺省值，也在此进行处理
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
					
					//设置档案分类的属性值
					if (item.getSystemDataItemType()==EnumSystemDataItem.档案分类编号)
					{
						fieldValue.setValue(String.valueOf(archivesType.getID()));
					}
					
					//添加至表记录行的字段值集合
					pRowFieldsValues.put(fieldValue.getColumnName(), fieldValue);
				}
				
				//赋予属性值
				this.rowFieldsValues=pRowFieldsValues;
				
				pRowFieldsValues=null;
			}
		}
    }
    
	/**
	 * 成员对象在集合中的关键字
	 */
	private Object keyInCol=null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * @param keyInCol 成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag=null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * @param tag 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}
	

	/**
	 * 获取属性值：内部序号
	 * @return 内部序号
	 */
	public int getNBXH()
	{
		int nBXH=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.内部序号.getEnumValue();
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
	 * 设置属性值：内部序号
	 * @param nBXH 内部序号
	 */
	public void setNBXH(int nBXH)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.内部序号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(nBXH));
			}
		}
	}
	
	/**
	 * 获取属性值：备份盘号
	 * @return 备份盘号
	 */
	public int getBackupDiskNo()
	{
		int backupDiskNo=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.备份盘号.getEnumValue();
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
	 * 设置属性值：备份盘号
	 * @param backupDiskNo 备份盘号
	 */
	public void setBackupDiskNo(int backupDiskNo)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.备份盘号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(backupDiskNo));
			}
		}
	}

	/**
	 * 获取属性值：档案管理工作流状态
	 * @return 档案管理工作流状态
	 */
	public EnumWorkFlowStatus getWorkFlowStatus()
	{
		EnumWorkFlowStatus workFlowStatus=EnumWorkFlowStatus.NONE;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案管理工作流状态.getEnumValue();
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
	 * 设置属性值：档案管理工作流状态
	 * @param workFlowStatus 档案管理工作流状态
	 */
	public void setWorkFlowStatus(EnumWorkFlowStatus workFlowStatus)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案管理工作流状态.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(workFlowStatus.getEnumValue()));
			}
		}
	}

	/**
	 * 获取属性值：案卷号
	 * @return 案卷号
	 */
	public int getContentID()
	{
		int contentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.案卷号.getEnumValue();
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
	 * 设置属性值：案卷号
	 * @param contentID 案卷号
	 */
	public void setContentID(int contentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.案卷号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(contentID));
			}
		}
	}

	/**
	 * 获取属性值：案卷号文本
	 * @return 案卷号文本
	 */
	public String getContentIDText()
	{
		String contentIDText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.案卷号格式化文本.getEnumValue();
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
	 * 设置属性值：案卷号文本
	 * @param contentIDText 案卷号文本
	 */
	public void setContentIDText(String contentIDText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.案卷号格式化文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(contentIDText);
			}
		}
	}

	/**
	 * 获取属性值：卷内文件序号
	 * @return 卷内文件序号
	 */
	public int getSubContentID()
	{
		int subContentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.卷内文件序号.getEnumValue();
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
	 * 设置属性值：卷内文件序号
	 * @param subContentID 卷内文件序号
	 */
	public void setSubContentID(int subContentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.卷内文件序号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(subContentID));
			}
		}
	}

	/**
	 * 获取属性值：卷内文件序号文本
	 * @return 卷内文件序号文本
	 */
	public String getSubContentIDText()
	{
		String subContentIDText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.卷内文件序号格式化文本.getEnumValue();
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
	 * 设置属性值：卷内文件序号文本
	 * @param subContentIDText 卷内文件序号文本
	 */
	public void setSubContentIDText(String subContentIDText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.卷内文件序号格式化文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(subContentIDText);
			}
		}
	}

	/**
	 * 获取属性值：案卷标志
	 * @return 案卷标志
	 */
	public boolean getParentFlag()
	{
		boolean parentFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.案卷标志.getEnumValue();
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
	 * 设置属性值：案卷标志
	 * @param parentFlag 案卷标志
	 */
	public void setParentFlag(boolean parentFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.案卷标志.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(parentFlag?"1":"0");
			}
		}
	}

	/**
	 * 获取属性值：所属案卷的内部序号
	 * @return 所属案卷的内部序号
	 */
	public int getParentNBXH()
	{
		int parentNBXH=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.所属案卷的内部序号.getEnumValue();
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
	 * 设置属性值：所属案卷的内部序号
	 * @param parentNBXH 所属案卷的内部序号
	 */
	public void setParentNBXH(int parentNBXH)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.所属案卷的内部序号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(parentNBXH));
			}
		}
	}
	

	/**
	 * 获取属性值：全宗数字编号
	 * @return 全宗数字编号
	 */
	public int getFondsID()
	{
		int fondsID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.全宗数字编号.getEnumValue();
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
	 * 设置属性值：全宗数字编号
	 * @param fondsID 全宗数字编号
	 */
	public void setFondsID(int fondsID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.全宗数字编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(fondsID));
			}
		}
	}

	/**
	 * 获取属性值：全宗编号
	 * @return 全宗编号
	 */
	public String getArchivesFondsID()
	{
		String archivesFondsID=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.全宗编号.getEnumValue();
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
	 * 设置属性值：全宗编号
	 * @param archivesFondsID 全宗编号
	 */
	public void setArchivesFondsID(String archivesFondsID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.全宗编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesFondsID);
			}
		}
	}

	/**
	 * 获取属性值：档案分类编号
	 * @return 档案分类编号
	 */
	public int getArchivesTypeID()
	{
		int archivesTypeID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案分类编号.getEnumValue();
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
	 * 设置属性值：档案分类编号
	 * @param archivesTypeID 档案分类编号
	 */
	public void setArchivesTypeID(int archivesTypeID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案分类编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(archivesTypeID));
			}
		}
	}

	/**
	 * 获取属性值：档案分类编码（实体分类号）
	 * @return 档案分类编码（实体分类号）
	 */
	public String getArchivesTypeCode()
	{
		String archivesTypeCode=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.实体分类号.getEnumValue();
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
	 * 设置属性值：档案分类编码（实体分类号）
	 * @param archivesTypeCode 档案分类编码（实体分类号）
	 */
	public void setArchivesTypeCode(String archivesTypeCode)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.实体分类号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesTypeCode);
			}
		}
	}

	/**
	 * 获取属性值：档号
	 * @return 档号
	 */
	public String getArchivesID()
	{
		String archivesID=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档号.getEnumValue();
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
	 * 设置属性值：档号
	 * @param archivesID 档号
	 */
	public void setArchivesID(String archivesID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(archivesID);
			}
		}
	}

	/**
	 * 获取属性值：题名
	 * @return 题名
	 */
	public String getTitle()
	{
		String title=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.题名.getEnumValue();
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
	 * 设置属性值：题名
	 * @param title 题名
	 */
	public void setTitle(String title)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.题名.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(title);
			}
		}
	}

	/**
	 * 获取属性值：卷内文件数量
	 * @return 卷内文件数量
	 */
	public int getSubContentCount()
	{
		int subContentCount=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.卷内文件数量.getEnumValue();
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
	 * 设置属性值：卷内文件数量
	 * @param subContentCount 卷内文件数量
	 */
	public void setSubContentCount(int subContentCount)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.卷内文件数量.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(subContentCount));
			}
		}
	}

	/**
	 * 获取属性值：档案页数
	 * @return 档案页数
	 */
	public int getPageSum()
	{
		int pageSum=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案页数.getEnumValue();
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
	 * 设置属性值：档案页数
	 * @param pageSum 档案页数
	 */
	public void setPageSum(int pageSum)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案页数.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(pageSum));
			}
		}
	}

	/**
	 * 获取属性值：保管期限编号
	 * @return 保管期限编号
	 */
	public int getRetentionPeriodID()
	{
		int retentionPeriodID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.保管期限编号.getEnumValue();
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
	 * 设置属性值：保管期限编号
	 * @param retentionPeriodID 保管期限编号
	 */
	public void setRetentionPeriodID(int retentionPeriodID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.保管期限编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(retentionPeriodID));
			}
		}
	}
	
	/**
	 * 获取属性值：保管期限文本
	 * @return 保管期限文本
	 */
	public String getRetentionPeriodText()
	{
		String retentionPeriodText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.保管期限文本.getEnumValue();
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
	 * 设置属性值：保管期限文本
	 * @param retentionPeriodText 保管期限文本
	 */
	public void setRetentionPeriodText(String retentionPeriodText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.保管期限文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(retentionPeriodText);
			}
		}
	}
	
	/**
	 * 获取属性值：保管期截止年度
	 * @return 保管期截止年度
	 */
	public int getRetentionEndYear()
	{
		int retentionEndYear=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.保管期截止年度.getEnumValue();
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
	 * 设置属性值：保管期截止年度
	 * @param retentionEndYear 保管期截止年度
	 */
	public void setRetentionEndYear(int retentionEndYear)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.保管期截止年度.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(retentionEndYear));
			}
		}
	}

	/**
	 * 获取属性值：档案密级编号
	 * @return 档案密级编号
	 */
	public int getSecrecyID()
	{
		int secrecyID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案密级编号.getEnumValue();
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
	 * 设置属性值：档案密级编号
	 * @param secrecyID 档案密级编号
	 */
	public void setSecrecyID(int secrecyID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案密级编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(secrecyID));
			}
		}
	}

	/**
	 * 获取属性值：档案密级文本
	 * @return 档案密级文本
	 */
	public String getSecrecyText()
	{
		String secrecyText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案密级文本.getEnumValue();
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
	 * 设置属性值：档案密级文本
	 * @param secrecyText 档案密级文本
	 */
	public void setSecrecyText(String secrecyText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案密级文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(secrecyText);
			}
		}
	}

	/**
	 * 获取属性值：档案形成年度
	 * @return 档案形成年度
	 */
	public int getFormationYear()
	{
		int formationYear=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案形成年度.getEnumValue();
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
	 * 设置属性值：档案形成年度
	 * @param formationYear 档案形成年度
	 */
	public void setFormationYear(int formationYear)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案形成年度.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(formationYear));
			}
		}
	}
	
	//夏妙--2010-8-11--新增属性：档案形成时间
	/**
	 * 获取属性值：档案形成时间
	 * @return 档案形成时间
	 */
	public String getFormationDate()
	{
		String formationDate=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案形成时间.getEnumValue();
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
	 * 设置属性值：档案形成时间
	 * @param formationDate 档案形成时间
	 */
	public void setFormationDate(String formationDate)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案形成时间.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(formationDate);
			}
		}
	}
	
	/**
	 * 获取属性值：责任者
	 * @return 责任者
	 */
	public String getResponsibility()
	{
		String responsibility=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.责任者.getEnumValue();
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
	 * 设置属性值：责任者
	 * @param responsibility 责任者
	 */
	public void setResponsibility(String responsibility)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.责任者.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(responsibility);
			}
		}
	}

	/**
	 * 获取属性值：档案形成部门编号
	 * @return 档案形成部门编号
	 */
	public int getFormationDepartmentID()
	{
		int formationDepartmentID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案形成部门编号.getEnumValue();
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
	 * 设置属性值：档案形成部门编号
	 * @param formationDepartmentID 档案形成部门编号
	 */
	public void setFormationDepartmentID(int formationDepartmentID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案形成部门编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(formationDepartmentID));
			}
		}
	}

	/**
	 * 获取属性值：档案形成部门名称
	 * @return 档案形成部门名称
	 */
	public String getFormationDepartment()
	{
		String formationDepartment=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案形成部门名称.getEnumValue();
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
	 * 设置属性值：档案形成部门名称
	 * @param formationDepartment 档案形成部门名称
	 */
	public void setFormationDepartment(String formationDepartment)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案形成部门名称.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(formationDepartment);
			}
		}
	}
	
	/**
	 * 获取属性值：载体编号
	 * @return 载体编号
	 */
	public int getCarrierID()
	{
		int carrierID=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.载体编号.getEnumValue();
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
	 * 设置属性值：载体编号
	 * @param carrierID 载体编号
	 */
	public void setCarrierID(int carrierID)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.载体编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(carrierID));
			}
		}
	}

	/**
	 * 获取属性值：载体文本
	 * @return 载体文本
	 */
	public String getCarrierText()
	{
		String carrierText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.载体文本.getEnumValue();
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
	 * 设置属性值：载体文本
	 * @param carrierText 载体文本
	 */
	public void setCarrierText(String carrierText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.载体文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(carrierText);
			}
		}
	}

	/**
	 * 设置属性值：主题词
	 * @param keyWord 主题词
	 */
	public void setKeyWord(String keyWord)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.主题词.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(keyWord);
			}
		}
	}
	
	/**
	 * 获取属性值：主题词
	 * @return 主题词
	 */
	public String getKeyWord()
	{
		String keyWord=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.主题词.getEnumValue();
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
	 * 设置属性值：全文
	 * @param fullText 全文
	 */
	public void setFullText(String fullText)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.全文.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(fullText);
			}
		}
	}
	
	/**
	 * 获取属性值：全文
	 * @return 全文
	 */
	public String getFullText()
	{
		String fullText=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.全文.getEnumValue();
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
	 * 获取属性值：归档日期
	 * @return 归档日期
	 */
	public Date getSaveDate()
	{
		Date saveDate=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.归档日期.getEnumValue();
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
	 * 设置属性值：归档日期
	 * @param saveDate 归档日期
	 */
	public void setSaveDate(Date saveDate)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.归档日期.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				SimpleDateFormat dateFormater=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				rowFieldsValues.get(pFieldName).setValue(dateFormater.format(saveDate));
			}
		}
	}

	/**
	 * 获取属性值：修复标志
	 * @return 修复标志
	 */
	public boolean getFixedFlag()
	{
		boolean fixedFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.修复标志.getEnumValue();
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
	 * 设置属性值：修复标志
	 * @param fixedFlag 修复标志
	 */
	public void setFixedFlag(boolean fixedFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.修复标志.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(fixedFlag?"1":"0");
			}
		}
	}

	/**
	 * 获取属性值：打回原因
	 * @return 打回原因
	 */
	public String getSendBackReason()
	{
		String sendBackReason=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.打回原因.getEnumValue();
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
	 * 设置属性值：打回原因
	 * @param sendBackReason 打回原因
	 */
	public void setSendBackReason(String sendBackReason)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.打回原因.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(sendBackReason);
			}
		}
	}

	/**
	 * 获取属性值：档案条码
	 * @return 档案条码
	 */
	public String getBarcode()
	{
		String barcode=null;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.档案条码.getEnumValue();
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
	 * 设置属性值：档案条码
	 * @param barcode 档案条码
	 */
	public void setBarcode(String barcode)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.档案条码.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(barcode);
			}
		}
	}

	/**
	 * 获取属性值：删除标志
	 * @return 删除标志
	 */
	public boolean getDeleteFlag()
	{
		boolean deleteFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.删除标志.getEnumValue();
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
	 * 设置属性值：删除标志
	 * @param deleteFlag 删除标志
	 */
	public void setDeleteFlag(boolean deleteFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.删除标志.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(deleteFlag?"1":"0");
			}
		}
	}

	/**
	 * 获取属性值：开放标志
	 * @return 开放标志
	 */
	public boolean getPublicFlag()
	{
		boolean publicFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.开放标志.getEnumValue();
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
	 * 设置属性值：开放标志
	 * @param publicFlag 开放标志
	 */
	public void setPublicFlag(boolean publicFlag)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.开放标志.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(publicFlag?"1":"0");
			}
		}
	}
	
	/**
	 * 获取属性值：用户编号1
	 * @return 用户编号1
	 */
	public int getUserID1()
	{
		int userID1=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.工作人员1.getEnumValue();
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
	 * 设置属性值：用户编号1
	 * @param userID1 用户编号1
	 */
	public void setUserID1(int userID1)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.工作人员1.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID1));
			}
		}
	}

	/**
	 * 获取属性值：用户编号2
	 * @return 用户编号2
	 */
	public int getUserID2()
	{
		int userID2=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.工作人员2.getEnumValue();
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
	 * 设置属性值：用户编号2
	 * @param userID2 用户编号2
	 */
	public void setUserID2(int userID2)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.工作人员2.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID2));
			}
		}
	}

	/**
	 * 获取属性值：用户编号3
	 * @return 用户编号3
	 */
	public int getUserID3()
	{
		int userID3=0;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.工作人员3.getEnumValue();
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
	 * 设置属性值：用户编号3
	 * @param userID3 用户编号3
	 */
	public void setUserID3(int userID3)
	{
		if (rowFieldsValues!=null)
		{
			String pFieldName=EnumSystemDataItem.工作人员3.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName))
			{
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(userID3));
			}
		}
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
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
	* 从指定对象克隆，复制所有属性值
	* @param archivesInfo 指定的对象源
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
	 * 当前档案附加的原文电子文件信息
	 */
	private List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles;
	
	/**
	 * 设置属性值：当前档案附加的原文电子文件信息
	 * @param archivesInfoAttachedFiles 当前档案附加的原文电子文件信息
	 */
	public void setArchivesInfoAttachedFiles(
			List<ArchivesInfoAttachedFile> archivesInfoAttachedFiles) {
		this.archivesInfoAttachedFiles = archivesInfoAttachedFiles;
	}
	
	/**
	 * 获取属性值：当前档案附加的原文电子文件信息
	 * @return 当前档案附加的原文电子文件信息
	 */
	public List<ArchivesInfoAttachedFile> getArchivesInfoAttachedFiles() {
		return archivesInfoAttachedFiles;
	}

	/**
	 * 卷内文件信息
	 */
	private List<ArchivesInfo> childArchivesInfos = null;

	/**
	 * 设置属性值：卷内文件信息
	 * @param childArchivesInfos 卷内文件信息
	 */
	public void setChildArchivesInfos(List<ArchivesInfo> childArchivesInfos) {
		this.childArchivesInfos = childArchivesInfos;
	}

	/**
	 * 获取属性值：卷内文件信息
	 * @return 卷内文件信息
	 */
	public List<ArchivesInfo> getChildArchivesInfos() {
		return childArchivesInfos;
	}

	/**
	 * 表记录行的字段值集合，以字段名作为关键字
	 */
	private Map<String, FieldValue> rowFieldsValues = null;

	/**
	 * 设置属性值：表记录行的字段值集合，以字段名作为关键字
	 * @param rowFieldsValues 表记录行的字段值集合，以字段名作为关键字
	 */
	public void setRowFieldsValues(Map<String, FieldValue> rowFieldsValues)
	{
		this.rowFieldsValues = rowFieldsValues;
	}

	/**
	 * 获取属性值：表记录行的字段值集合，以字段名作为关键字
	 * @return 表记录行的字段值集合，以字段名作为关键字
	 */
	public Map<String, FieldValue> getRowFieldsValues()
	{
		return rowFieldsValues;
	}
	
	/**
	 * 鉴定前保管期限年度
	 */
	private int totalYears;

	public int getTotalYears() {
		return totalYears;
	}

	public void setTotalYears(int totalYears) {
		this.totalYears = totalYears;
	}
	
	/**
	 * 上架位置完整名称
	 */
	private String StoreAddressFullName;

	public String getStoreAddressFullName() {
		return StoreAddressFullName;
	}

	public void setStoreAddressFullName(String storeAddressFullName) {
		StoreAddressFullName = storeAddressFullName;
	}
	
	/**
	 * 业务指导室部门名称
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