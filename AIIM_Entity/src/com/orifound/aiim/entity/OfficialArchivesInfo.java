package com.orifound.aiim.entity;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ���ĵ����ǼǱ�ʵ����
 */
public class OfficialArchivesInfo {
	/**
	 * ���캯��
	 */
	public OfficialArchivesInfo() {

	}

	/**
	 * ���ֶβ����Ĺ��캯��
	 * 
	 * @param nBXH
	 * @param docNo
	 * @param title
	 * @param formationYear
	 *            �����γ����
	 * @param pageSum
	 *            ����ҳ��
	 * @param retentionPeriodID
	 *            �������ޱ��
	 * @param retentionPeriodText
	 *            ���������ı�
	 * @param retentionEndYear
	 *            �����ڽ�ֹ���
	 * @param secrecyID
	 *            �����ܼ����
	 * @param secrecyText
	 *            �����ܼ��ı�
	 * @param formationDepartmentID
	 *            �����γɲ���
	 * @param formationDepartment
	 *            �����γɲ�������
	 * @param docTypeID
	 * @param docTypeText
	 * @param keyWord
	 *            �����
	 * @param fullText
	 *            ȫ����Ϣ
	 * @param regDate
	 * @param regUserID
	 * @saveFlag
	 */
	public OfficialArchivesInfo(int nBXH, String docNo, String title,
			int formationYear, int pageSum, int retentionPeriodID,
			String retentionPeriodText, int retentionEndYear, int secrecyID,
			String secrecyText, int formationDepartmentID,
			String formationDepartment, int docTypeID, String docTypeText,
			String keyWord, String fullText, Date regDate, int regUserID,boolean savedFlag) {
		// Table Name: OfficialArchivesInfo_TypeCode
		// Columns List,Can Used in SELECT SQL:
		// NBXH,DocNo,Title,FormationYear,PageSum,RetentionPeriodID,RetentionPeriodText,RetentionEndYear,SecrecyID,SecrecyText,FormationDepartmentID,FormationDepartment,DocTypeID,DocTypeText,KeyWord,FullText,RegDate,RegUserID
		// Columns List,Can Used in INSERT SQL:
		// :NBXH,:DocNo,:Title,:FormationYear,:PageSum,:RetentionPeriodID,:RetentionPeriodText,:RetentionEndYear,:SecrecyID,:SecrecyText,:FormationDepartmentID,:FormationDepartment,:DocTypeID,:DocTypeText,:KeyWord,:FullText,:RegDate,:RegUserID
		// Columns List,Can Used in UPDATE SQL:
		// NBXH=:NBXH,DocNo=:DocNo,Title=:Title,FormationYear=:FormationYear,PageSum=:PageSum,RetentionPeriodID=:RetentionPeriodID,RetentionPeriodText=:RetentionPeriodText,RetentionEndYear=:RetentionEndYear,SecrecyID=:SecrecyID,SecrecyText=:SecrecyText,FormationDepartmentID=:FormationDepartmentID,FormationDepartment=:FormationDepartment,DocTypeID=:DocTypeID,DocTypeText=:DocTypeText,KeyWord=:KeyWord,FullText=:FullText,RegDate=:RegDate,RegUserID=:RegUserID

		setNBXH(nBXH);
		setDocNo(docNo);
		setTitle(title);
		setFormationYear(formationYear);
		setPageSum(pageSum);
		setRetentionEndYear(retentionEndYear);
		setFormationDepartment(formationDepartment);
		setFormationDepartmentID(formationDepartmentID);
		setDocTypeID(docTypeID);
		setDocTypeText(docTypeText);
		setKeyWord(keyWord);
		setFullText(fullText);
		setRegDate(regDate);
		setRegUserID(regUserID);
		setSavedFlag(savedFlag);
	}
	
	/**
	 * ���ֶεĹ��캯��<br>
	 * �Զ�������������ϣ���������ǿ�ֵ���䱾���ȱʡֵ<br>
	 * ע�⣺�ù��캯����������ҵ���߼�������ݷ��ʲ��ڲ����ʣ�<br>
	 * ��ʾ�㲻�ܹ�ʹ�øù��캯������Ӧ��ͨ��ҵ���߼����OfficialArchivesInfoFactory��������������OfficialArchivesInfo����
	 * @param officialArchivesType ָ���ĵ���������Ϣ�������Ǿ߱�����������ֵ�����ܹ����е�������������ֵ
     */
    public OfficialArchivesInfo(OfficialArchivesType officialArchivesType)
    {
    	if (officialArchivesType!=null)
		{
			if (officialArchivesType.getDataItemsForAll()!=null)
			{
				//�������¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
				Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
				for (ArchivesTypeDataItem item : officialArchivesType.getDataItemsForAll().values())
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
						fieldValue.setValue(String.valueOf(officialArchivesType.getID()));
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
	private Object keyInCol = null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * 
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * 
	 * @param keyInCol
	 *            ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag = null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * 
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * 
	 * @param tag
	 *            ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * ��ȡ����ֵ��
	 * 
	 * @return
	 */
	public int getNBXH() {
		int nBXH = 0;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�ڲ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						nBXH = Integer.parseInt(rowFieldsValues.get(pFieldName)
								.getValue());
					}
				}
			}
		} catch (Exception e) {
			nBXH = 0;
		}

		return nBXH;
	}

	/**
	 * ��������ֵ���ڲ����
	 * 
	 * @param nBXH
	 *            �ڲ����
	 */
	public void setNBXH(int nBXH) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�ڲ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(nBXH));
			}
		}
	}


	
	/**
	 * ��ȡ����ֵ���ĺ�
	 * 
	 * @return �ĺ�
	 */
	public String getDocNo() {
		String docNo = null;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�ĺ�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					docNo = rowFieldsValues.get(pFieldName).getValue();

				}
			}
		} catch (Exception e) {
			docNo = null;
		}
		return docNo;
	}

	/**
	 * ��������ֵ���ĺ�
	 * 
	 * @param docNo
	 *            �ĺ�
	 */
	public void setDocNo(String docNo) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�ĺ�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(docNo);
			}
		}
	}

	/**
	 * ��ȡ����ֵ������
	 * 
	 * @return ����
	 */
	public String getTitle() {
		String title = null;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					title = rowFieldsValues.get(pFieldName).getValue();

				}
			}
		} catch (Exception e) {
			title = null;
		}
		return title;
	}

	/**
	 * ��������ֵ������
	 * 
	 * @param title
	 *            ����
	 */
	public void setTitle(String title) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(title);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γ����
	 * 
	 * @return �����γ����
	 */
	public int getFormationYear() {
		int formationYear = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����γ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					formationYear = Integer.parseInt(rowFieldsValues.get(
							pFieldName).getValue());
				}
			}
		} catch (Exception e) {
			formationYear = 0;
		}

		return formationYear;
	}

	/**
	 * ��������ֵ�������γ����
	 * 
	 * @param formationYear
	 *            �����γ����
	 */
	public void setFormationYear(int formationYear) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����γ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(formationYear));
			}
		}
	}

	/**
	 * ��ȡ����ֵ������ҳ��
	 * 
	 * @return ����ҳ��
	 */
	public int getPageSum() {
		int pageSum = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.����ҳ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						pageSum = Integer.parseInt(rowFieldsValues.get(
								pFieldName).getValue());
					}
				}
			}
		} catch (Exception e) {
			pageSum = 0;
		}

		return pageSum;
	}

	/**
	 * ��������ֵ������ҳ��
	 * 
	 * @param pageSum
	 *            ����ҳ��
	 */
	public void setPageSum(int pageSum) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.����ҳ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(pageSum));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���������ޱ��
	 * 
	 * @return �������ޱ��
	 */
	public int getRetentionPeriodID() {
		int retentionPeriodID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�������ޱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						retentionPeriodID = Integer.parseInt(rowFieldsValues
								.get(pFieldName).getValue());
					}
				}
			}
		} catch (Exception e) {
			retentionPeriodID = 0;
		}

		return retentionPeriodID;
	}

	/**
	 * ��������ֵ���������ޱ��
	 * 
	 * @param retentionPeriodID
	 *            �������ޱ��
	 */
	public void setRetentionPeriodID(int retentionPeriodID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�������ޱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(retentionPeriodID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�����������ı�
	 * 
	 * @return ���������ı�
	 */
	public String getRetentionPeriodText() {
		String retentionPeriodText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.���������ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					retentionPeriodText = rowFieldsValues.get(pFieldName)
							.getValue();
				}
			}
		} catch (Exception e) {
			retentionPeriodText = null;
		}

		return retentionPeriodText;
	}

	/**
	 * ��������ֵ�����������ı�
	 * 
	 * @param retentionPeriodText
	 *            ���������ı�
	 */
	public void setRetentionPeriodText(String retentionPeriodText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.���������ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(retentionPeriodText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ڽ�ֹ���
	 * 
	 * @return �����ڽ�ֹ���
	 */
	public int getRetentionEndYear() {
		int retentionEndYear = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����ڽ�ֹ���.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						retentionEndYear = Integer.parseInt(rowFieldsValues
								.get(pFieldName).getValue());
					}
				}
			}
		} catch (Exception e) {
			retentionEndYear = 0;
		}

		return retentionEndYear;
	}

	/**
	 * ��������ֵ�������ڽ�ֹ���
	 * 
	 * @param retentionEndYear
	 *            �����ڽ�ֹ���
	 */
	public void setRetentionEndYear(int retentionEndYear) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����ڽ�ֹ���.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(retentionEndYear));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ܼ����
	 * 
	 * @return �����ܼ����
	 */
	public int getSecrecyID() {
		int secrecyID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����ܼ����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						secrecyID = Integer.parseInt(rowFieldsValues.get(
								pFieldName).getValue());
					}
				}
			}
		} catch (Exception e) {
			secrecyID = 0;
		}

		return secrecyID;
	}

	/**
	 * ��������ֵ�������ܼ����
	 * 
	 * @param secrecyID
	 *            �����ܼ����
	 */
	public void setSecrecyID(int secrecyID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����ܼ����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(secrecyID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������ܼ��ı�
	 * 
	 * @return �����ܼ��ı�
	 */
	public String getSecrecyText() {
		String secrecyText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����ܼ��ı�.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					secrecyText = rowFieldsValues.get(pFieldName).getValue();
				}
			}
		} catch (Exception e) {
			secrecyText = null;
		}

		return secrecyText;
	}

	/**
	 * ��������ֵ�������ܼ��ı�
	 * 
	 * @param secrecyText
	 *            �����ܼ��ı�
	 */
	public void setSecrecyText(String secrecyText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����ܼ��ı�.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(secrecyText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γɲ��ű��
	 * 
	 * @return �����γɲ��ű��
	 */
	public int getFormationDepartmentID() {
		int formationDepartmentID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����γɲ��ű��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						formationDepartmentID = Integer
								.parseInt(rowFieldsValues.get(pFieldName)
										.getValue());
					}
				}
			}
		} catch (Exception e) {
			formationDepartmentID = 0;
		}

		return formationDepartmentID;
	}

	/**
	 * ��������ֵ�������γɲ��ű��
	 * 
	 * @param formationDepartmentID
	 *            �����γɲ��ű��
	 */
	public void setFormationDepartmentID(int formationDepartmentID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����γɲ��ű��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(formationDepartmentID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������γɲ�������
	 * 
	 * @return �����γɲ�������
	 */
	public String getFormationDepartment() {
		String formationDepartment = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����γɲ�������.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					formationDepartment = rowFieldsValues.get(pFieldName)
							.getValue();
				}
			}
		} catch (Exception e) {
			formationDepartment = null;
		}

		return formationDepartment;
	}

	/**
	 * ��������ֵ�������γɲ�������
	 * 
	 * @param formationDepartment
	 *            �����γɲ�������
	 */
	public void setFormationDepartment(String formationDepartment) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����γɲ�������.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(formationDepartment);
			}
		}
	}

	/**
	 * ��������ֵ�������
	 * 
	 * @param keyWord
	 *            �����
	 */
	public void setKeyWord(String keyWord) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(keyWord);
			}
		}
	}

	/**
	 * ��ȡ����ֵ�������
	 * 
	 * @return �����
	 */
	public String getKeyWord() {
		String keyWord = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					keyWord = rowFieldsValues.get(pFieldName).getValue();
				}
			}
		} catch (Exception e) {
			keyWord = null;
		}

		return keyWord;
	}

	/**
	 * ��������ֵ��ȫ��
	 * 
	 * @param fullText
	 *            ȫ��
	 */
	public void setFullText(String fullText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.ȫ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(fullText);
			}
		}
	}

	/**
	 * ��ȡ����ֵ��ȫ��
	 * 
	 * @return ȫ��
	 */
	public String getFullText() {
		String fullText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.ȫ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					fullText = rowFieldsValues.get(pFieldName).getValue();
				}
			}
		} catch (Exception e) {
			fullText = null;
		}

		return fullText;
	}

	/**
	 * ��ȡ����ֵ�����ֱ��
	 * 
	 * @return ���б��
	 */
	public int getDocTypeID() {
		int docTypeID = 0;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.���ֱ��.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					docTypeID = Integer.parseInt(rowFieldsValues
							.get(pFieldName).getValue());
				}
			}
		} catch (Exception e) {
			docTypeID = 0;
		}

		return docTypeID;
	}

	/**
	 * ��������ֵ�����ֱ��
	 * 
	 * @param docTypeID ���ֱ��
	 */
	public void setDocTypeID(int docTypeID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.���ֱ��.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(docTypeID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ������
	 * 
	 * @return ����
	 */
	public String getDocTypeText() {
		String docTypeText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					docTypeText = rowFieldsValues.get(pFieldName).getValue();
				}
			}
		} catch (Exception e) {
			docTypeText = null;
		}

		return docTypeText;
	}

	/**
	 * ��������ֵ������
	 * 
	 * @param docTypeText ����
	 */
	public void setDocTypeText(String docTypeText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(docTypeText));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���Ǽ�����
	 * 
	 * @return �Ǽ�����
	 */
	public Date getRegDate() {
		Date regDate = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�Ǽ�����.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					if (rowFieldsValues.get(pFieldName).getValue() != null) {
						SimpleDateFormat dateFormater = new SimpleDateFormat(
								"yyyy-MM-dd HH:mm:ss");
						regDate = dateFormater.parse(rowFieldsValues.get(
								pFieldName).getValue());
					}
				}
			}
		} catch (Exception e) {
			regDate = null;
		}

		return regDate;
	}

	/**
	 * ��������ֵ���Ǽ�����
	 * 
	 * @param saveDate
	 *            �Ǽ�����
	 */
	public void setRegDate(Date regDate) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�Ǽ�����.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				SimpleDateFormat dateFormater = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				rowFieldsValues.get(pFieldName).setValue(dateFormater.format(regDate));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���Ǽ���Ա���
	 * 
	 * @return �Ǽ���Ա���
	 */
	public int getRegUserID() {
		int regUserID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.�Ǽ���Ա���.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName)) {
					regUserID = Integer.parseInt(rowFieldsValues
							.get(pFieldName).getValue());
				}
			}
		} catch (Exception e) {
			regUserID = 0;
		}

		return regUserID;
	}

	/**
	 * ��������ֵ���Ǽ���Ա���
	 * 
	 * @param regUserID
	 *            �Ǽ���Ա���
	 */
	public void setRegUserID(int regUserID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�Ǽ���Ա���.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(regUserID));
			}
		}
	}

	/**
	 * ��ȡ����ֵ���鵵��־
	 * @return �鵵��־
	 */
	public boolean getSavedFlag()
	{
			boolean savedFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.�鵵��־.getEnumValue();
				if (rowFieldsValues.containsKey(pFieldName))
				{
					if (rowFieldsValues.get(pFieldName).getValue()!=null)
					{
						if (rowFieldsValues.get(pFieldName).getValue().equals("0"))
						{
							savedFlag=false;
						}
						else
						{
							savedFlag=true;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			savedFlag=false;
		}
		
		return savedFlag;
	}

	/**
	 * ��������ֵ���鵵��־
	 * @param savedFlag �鵵��־
	 */
	public void setSavedFlag(boolean savedFlag)
	{
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.�鵵��־.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						savedFlag?"1":"0");
			}
		}
	}
	

	
	/**
	 * clone
	 * 
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public OfficialArchivesInfo clone() {
		OfficialArchivesInfo returnItem = new OfficialArchivesInfo();
		Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
		if (rowFieldsValues != null) {
			for (FieldValue item : rowFieldsValues.values()) {
				FieldValue cloneItem = item.clone();
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
	 * 
	 * @param officialArchivesInfo
	 *            ָ���Ķ���Դ
	 */
	public void cloneFrom(OfficialArchivesInfo officialArchivesInfo) {
		Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
		if (officialArchivesInfo.getRowFieldsValues() != null) {
			for (FieldValue item : officialArchivesInfo.getRowFieldsValues()
					.values()) {
				FieldValue cloneItem = item.clone();
				pRowFieldsValues.put(cloneItem.getColumnName(), cloneItem);
			}
		}
		this.rowFieldsValues = pRowFieldsValues;
		this.keyInCol = officialArchivesInfo.getKeyInCol();
		this.tag = officialArchivesInfo.getTag();
	}

	/**
	 * ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	private Map<String, FieldValue> rowFieldsValues = null;

	/**
	 * ��������ֵ�����¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 * 
	 * @param rowFieldsValues
	 *            ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	public void setRowFieldsValues(Map<String, FieldValue> rowFieldsValues) {
		this.rowFieldsValues = rowFieldsValues;
	}

	/**
	 * ��ȡ����ֵ�����¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 * 
	 * @return ���¼�е��ֶ�ֵ���ϣ����ֶ�����Ϊ�ؼ���
	 */
	public Map<String, FieldValue> getRowFieldsValues() {
		return rowFieldsValues;
	}
	
}