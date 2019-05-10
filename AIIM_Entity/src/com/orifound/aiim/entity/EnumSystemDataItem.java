/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 系统固有的数据项枚举<br>
 * 例如：内部序号、档案管理工作流状态、案卷号、案卷号格式化文本、卷内文件序号、案卷标志、所属案卷的内部序号、全宗编号等等<br>
 * 以字段名称作为枚举值
 */
public enum EnumSystemDataItem
{

	/**
	 * 什么也不是的枚举成员（未知的枚举）
	 */
	NONE("NONE"),

	// 定义其他枚举成员
	
	/**
	 * 内部序号	NBXH
	 */
	内部序号("NBXH"),
	
	/**
	 * 备份盘号	BackupDiskNo
	 */
	备份盘号("BackupDiskNo"),
	
	/**
	 * 档案管理工作流状态	WorkFlowStatus
	 */
	档案管理工作流状态("WorkFlowStatus"),
	
	/**
	 * 案卷号	ContentID
	 */
	案卷号("ContentID"),
	
	/**
	 * 案卷号格式化文本	ContentIDText
	 */
	案卷号格式化文本("ContentIDText"),
	
	/**
	 * 卷内文件序号	SubContentID
	 */
	卷内文件序号("SubContentID"),
	
	/**
	 * 卷内文件序号格式化文本	SubContentIDText
	 */
	卷内文件序号格式化文本("SubContentIDText"),
	
	/**
	 * 案卷标志	ParentFlag
	 */
	案卷标志("ParentFlag"),
	
	/**
	 * 所属案卷的内部序号	ParentNBXH
	 */
	所属案卷的内部序号("ParentNBXH"),
	
	/**
	 * 全宗数字编号	FondsID
	 */
	全宗数字编号("FondsID"),
	
	/**
	 * 全宗编号	ArchivesFondsID
	 */
	全宗编号("ArchivesFondsID"),
	
	/**
	 * 档案分类编号	ArchivesTypeID
	 */
	档案分类编号("ArchivesTypeID"),
	
	/**
	 * 实体分类号	ArchivesTypeCode
	 */
	实体分类号("ArchivesTypeCode"),
	
	/**
	 * 档号	ArchivesID
	 */
	档号("ArchivesID"),
	
	/**
	 * 题名	Title
	 */
	题名("Title"),
	
	/**
	 * 卷内文件数量	SubContentCount
	 */
	卷内文件数量("SubContentCount"),
	
	/**
	 * 档案页数	PageSum
	 */
	档案页数("PageSum"),
	
	/**
	 * 保管期限编号	RetentionPeriodID
	 */
	保管期限编号("RetentionPeriodID"),
	
	/**
	 * 保管期限文本	RetentionPeriodText
	 */
	保管期限文本("RetentionPeriodText"),
	
	/**
	 * 保管期截止年度	RetentionEndYear
	 */
	保管期截止年度("RetentionEndYear"),
	
	/**
	 * 档案密级编号	SecrecyID
	 */
	档案密级编号("SecrecyID"),
	
	/**
	 * 责任者	Responsibility
	 */
	责任者("Responsibility"),
	
	/**
	 * 档案密级文本	SecrecyText
	 */
	档案密级文本("SecrecyText"),
	
	/**
	 * 档案形成部门编号	FormationDepartmentID
	 */
	档案形成部门编号("FormationDepartmentID"),
	
	/**
	 * 档案形成部门名称	FormationDepartment
	 */
	档案形成部门名称("FormationDepartment"),
	
	/**
	 * 档案形成年度	FormationYear
	 */
	档案形成年度("FormationYear"),
	
	//夏妙--2010-8-11--新增枚举对象：档案形成时间
	/**
	 * 档案形成时间	FormationDate
	 */
	档案形成时间("FormationDate"),
	
	/**
	 * 载体编号	CarrierID
	 */
	载体编号("CarrierID"),
	
	/**
	 * 载体文本	CarrierText
	 */
	载体文本("CarrierText"),
	
	/**
	 * 主题词	KeyWord
	 */
	主题词("KeyWord"),
	
	/**
	 * 全文	FullText
	 */
	全文("FullText"),
	
	/**
	 * 归档日期	SaveDate
	 */
	归档日期("SaveDate"),
	
	/**
	 * 修复标志	FixedFlag
	 */
	修复标志("FixedFlag"),
	
	/**
	 * 打回原因	SendBackReason
	 */
	打回原因("SendBackReason"),
	
	/**
	 * 档案条码	Barcode
	 */
	档案条码("Barcode"),
	
	/**
	 * 删除标志	DeleteFlag
	 */
	删除标志("DeleteFlag"),
	
	/**
	 * 开放标志	PublicFlag
	 */
	开放标志("PublicFlag"),
	
	/**
	 * 工作人员1	UserID1<br>
	 * 存储档案信息著录人员、提交送审人员、实物档案移交人员的用户编号，整个档案归档过程中原则上属于馆外业务环节的工作人员信息用该字段存储。
	 */
	工作人员1("UserID1"),
	
	/**
	 * 工作人员2	UserID2<br>
	 * 存储档案信息著录审核人员、实物接收审核人员、实物档案移交人员的用户编号，整个档案归档过程中原则上属于业务指导室的业务环节的工作人员信息用该字段存储。
	 */
	工作人员2("UserID2"),
	
	/**
	 * 工作人员3	UserID3<br>
	 * 存储档案管理室的实物档案接收审核人员、上架入库人员、实物档案利用经办人员的用户编号，整个档案归档过程中原则上属于档案管理室的业务环节的工作人员信息用该字段存储。
	 */
	工作人员3("UserID3"),
	
	/**
	 * 文号	DocNo
	 */
	文号("DocNo"),
	
	/**
	 * 文种编号	DocTypeID
	 */
	文种编号("DocTypeID"),
	
	/**
	 * 文种	DocTypeText
	 */
	文种("DocTypeText"),
	
	/**
	 * 登记日期	RegDate
	 */
	登记日期("RegDate"),
	
	/**
	 * 登记人员编号   RegUserID
	 */
	登记人员编号("RegUserID"),
	
	/**
	 * 归档标志   SavedFlag
	 */
	归档标志("SavedFlag");

	/**
	 * 构造函数
	 * @param enumValue 枚举值，以字段名称作为枚举值
	 */
	private EnumSystemDataItem(String enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * 枚举值，以字段名称作为枚举值
	 */
	private String enumValue;

	/**
	 * 获取属性值：枚举值，以字段名称作为枚举值
	 * @return 枚举值，以字段名称作为枚举值
	 */
	public String getEnumValue()
	{
		return enumValue;
	}

	/**
	 * 根据枚举值获取枚举成员
	 * @param enumValue 枚举值，以字段名称作为枚举值
	 * @return 对应该枚举值的枚举成员对象
	 */
	public static EnumSystemDataItem getEnumElement(String enumValue)
	{
		EnumSystemDataItem pEnumElement = EnumSystemDataItem.NONE;

		for (EnumSystemDataItem item : EnumSystemDataItem.values())
		{
			if (item.getEnumValue().equals(enumValue))
			{
				pEnumElement = item;
				break;
			}
		}

		return pEnumElement;
	}
}
