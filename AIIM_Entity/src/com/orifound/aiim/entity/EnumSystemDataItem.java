/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * ϵͳ���е�������ö��<br>
 * ���磺�ڲ���š�������������״̬������š�����Ÿ�ʽ���ı��������ļ���š������־������������ڲ���š�ȫ�ڱ�ŵȵ�<br>
 * ���ֶ�������Ϊö��ֵ
 */
public enum EnumSystemDataItem
{

	/**
	 * ʲôҲ���ǵ�ö�ٳ�Ա��δ֪��ö�٣�
	 */
	NONE("NONE"),

	// ��������ö�ٳ�Ա
	
	/**
	 * �ڲ����	NBXH
	 */
	�ڲ����("NBXH"),
	
	/**
	 * �����̺�	BackupDiskNo
	 */
	�����̺�("BackupDiskNo"),
	
	/**
	 * ������������״̬	WorkFlowStatus
	 */
	������������״̬("WorkFlowStatus"),
	
	/**
	 * �����	ContentID
	 */
	�����("ContentID"),
	
	/**
	 * ����Ÿ�ʽ���ı�	ContentIDText
	 */
	����Ÿ�ʽ���ı�("ContentIDText"),
	
	/**
	 * �����ļ����	SubContentID
	 */
	�����ļ����("SubContentID"),
	
	/**
	 * �����ļ���Ÿ�ʽ���ı�	SubContentIDText
	 */
	�����ļ���Ÿ�ʽ���ı�("SubContentIDText"),
	
	/**
	 * �����־	ParentFlag
	 */
	�����־("ParentFlag"),
	
	/**
	 * ����������ڲ����	ParentNBXH
	 */
	����������ڲ����("ParentNBXH"),
	
	/**
	 * ȫ�����ֱ��	FondsID
	 */
	ȫ�����ֱ��("FondsID"),
	
	/**
	 * ȫ�ڱ��	ArchivesFondsID
	 */
	ȫ�ڱ��("ArchivesFondsID"),
	
	/**
	 * ����������	ArchivesTypeID
	 */
	����������("ArchivesTypeID"),
	
	/**
	 * ʵ������	ArchivesTypeCode
	 */
	ʵ������("ArchivesTypeCode"),
	
	/**
	 * ����	ArchivesID
	 */
	����("ArchivesID"),
	
	/**
	 * ����	Title
	 */
	����("Title"),
	
	/**
	 * �����ļ�����	SubContentCount
	 */
	�����ļ�����("SubContentCount"),
	
	/**
	 * ����ҳ��	PageSum
	 */
	����ҳ��("PageSum"),
	
	/**
	 * �������ޱ��	RetentionPeriodID
	 */
	�������ޱ��("RetentionPeriodID"),
	
	/**
	 * ���������ı�	RetentionPeriodText
	 */
	���������ı�("RetentionPeriodText"),
	
	/**
	 * �����ڽ�ֹ���	RetentionEndYear
	 */
	�����ڽ�ֹ���("RetentionEndYear"),
	
	/**
	 * �����ܼ����	SecrecyID
	 */
	�����ܼ����("SecrecyID"),
	
	/**
	 * ������	Responsibility
	 */
	������("Responsibility"),
	
	/**
	 * �����ܼ��ı�	SecrecyText
	 */
	�����ܼ��ı�("SecrecyText"),
	
	/**
	 * �����γɲ��ű��	FormationDepartmentID
	 */
	�����γɲ��ű��("FormationDepartmentID"),
	
	/**
	 * �����γɲ�������	FormationDepartment
	 */
	�����γɲ�������("FormationDepartment"),
	
	/**
	 * �����γ����	FormationYear
	 */
	�����γ����("FormationYear"),
	
	//����--2010-8-11--����ö�ٶ��󣺵����γ�ʱ��
	/**
	 * �����γ�ʱ��	FormationDate
	 */
	�����γ�ʱ��("FormationDate"),
	
	/**
	 * ������	CarrierID
	 */
	������("CarrierID"),
	
	/**
	 * �����ı�	CarrierText
	 */
	�����ı�("CarrierText"),
	
	/**
	 * �����	KeyWord
	 */
	�����("KeyWord"),
	
	/**
	 * ȫ��	FullText
	 */
	ȫ��("FullText"),
	
	/**
	 * �鵵����	SaveDate
	 */
	�鵵����("SaveDate"),
	
	/**
	 * �޸���־	FixedFlag
	 */
	�޸���־("FixedFlag"),
	
	/**
	 * ���ԭ��	SendBackReason
	 */
	���ԭ��("SendBackReason"),
	
	/**
	 * ��������	Barcode
	 */
	��������("Barcode"),
	
	/**
	 * ɾ����־	DeleteFlag
	 */
	ɾ����־("DeleteFlag"),
	
	/**
	 * ���ű�־	PublicFlag
	 */
	���ű�־("PublicFlag"),
	
	/**
	 * ������Ա1	UserID1<br>
	 * �洢������Ϣ��¼��Ա���ύ������Ա��ʵ�ﵵ���ƽ���Ա���û���ţ����������鵵������ԭ�������ڹ���ҵ�񻷽ڵĹ�����Ա��Ϣ�ø��ֶδ洢��
	 */
	������Ա1("UserID1"),
	
	/**
	 * ������Ա2	UserID2<br>
	 * �洢������Ϣ��¼�����Ա��ʵ����������Ա��ʵ�ﵵ���ƽ���Ա���û���ţ����������鵵������ԭ��������ҵ��ָ���ҵ�ҵ�񻷽ڵĹ�����Ա��Ϣ�ø��ֶδ洢��
	 */
	������Ա2("UserID2"),
	
	/**
	 * ������Ա3	UserID3<br>
	 * �洢���������ҵ�ʵ�ﵵ�����������Ա���ϼ������Ա��ʵ�ﵵ�����þ�����Ա���û���ţ����������鵵������ԭ�������ڵ��������ҵ�ҵ�񻷽ڵĹ�����Ա��Ϣ�ø��ֶδ洢��
	 */
	������Ա3("UserID3"),
	
	/**
	 * �ĺ�	DocNo
	 */
	�ĺ�("DocNo"),
	
	/**
	 * ���ֱ��	DocTypeID
	 */
	���ֱ��("DocTypeID"),
	
	/**
	 * ����	DocTypeText
	 */
	����("DocTypeText"),
	
	/**
	 * �Ǽ�����	RegDate
	 */
	�Ǽ�����("RegDate"),
	
	/**
	 * �Ǽ���Ա���   RegUserID
	 */
	�Ǽ���Ա���("RegUserID"),
	
	/**
	 * �鵵��־   SavedFlag
	 */
	�鵵��־("SavedFlag");

	/**
	 * ���캯��
	 * @param enumValue ö��ֵ�����ֶ�������Ϊö��ֵ
	 */
	private EnumSystemDataItem(String enumValue)
	{
		this.enumValue = enumValue;
	}

	/**
	 * ö��ֵ�����ֶ�������Ϊö��ֵ
	 */
	private String enumValue;

	/**
	 * ��ȡ����ֵ��ö��ֵ�����ֶ�������Ϊö��ֵ
	 * @return ö��ֵ�����ֶ�������Ϊö��ֵ
	 */
	public String getEnumValue()
	{
		return enumValue;
	}

	/**
	 * ����ö��ֵ��ȡö�ٳ�Ա
	 * @param enumValue ö��ֵ�����ֶ�������Ϊö��ֵ
	 * @return ��Ӧ��ö��ֵ��ö�ٳ�Ա����
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
