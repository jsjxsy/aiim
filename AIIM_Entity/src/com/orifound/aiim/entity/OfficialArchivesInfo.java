package com.orifound.aiim.entity;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 公文档案登记表实体类
 */
public class OfficialArchivesInfo {
	/**
	 * 构造函数
	 */
	public OfficialArchivesInfo() {

	}

	/**
	 * 带字段参数的构造函数
	 * 
	 * @param nBXH
	 * @param docNo
	 * @param title
	 * @param formationYear
	 *            档案形成年度
	 * @param pageSum
	 *            档案页数
	 * @param retentionPeriodID
	 *            保管期限编号
	 * @param retentionPeriodText
	 *            保管期限文本
	 * @param retentionEndYear
	 *            保管期截止年度
	 * @param secrecyID
	 *            档案密级编号
	 * @param secrecyText
	 *            档案密级文本
	 * @param formationDepartmentID
	 *            档案形成部门
	 * @param formationDepartment
	 *            档案形成部门名称
	 * @param docTypeID
	 * @param docTypeText
	 * @param keyWord
	 *            主题词
	 * @param fullText
	 *            全文信息
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
	 * 带字段的构造函数<br>
	 * 自动构建好数据项集合，各数据项都是空值或其本身的缺省值<br>
	 * 注意：该构造函数仅适用于业务逻辑层和数据访问层内部访问，<br>
	 * 表示层不能够使用该构造函数，而应该通过业务逻辑层的OfficialArchivesInfoFactory工厂类来生产出OfficialArchivesInfo对象
	 * @param officialArchivesType 指定的档案分类信息，必须是具备完整的属性值，不能够仅有档案分类编号属性值
     */
    public OfficialArchivesInfo(OfficialArchivesType officialArchivesType)
    {
    	if (officialArchivesType!=null)
		{
			if (officialArchivesType.getDataItemsForAll()!=null)
			{
				//构建表记录行的字段值集合，以字段名作为关键字
				Map<String, FieldValue> pRowFieldsValues = new HashMap<String, FieldValue>();
				for (ArchivesTypeDataItem item : officialArchivesType.getDataItemsForAll().values())
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
						fieldValue.setValue(String.valueOf(officialArchivesType.getID()));
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
	private Object keyInCol = null;

	/**
	 * 获取属性值：成员对象在集合中的关键字
	 * 
	 * @return 成员对象在集合中的关键字
	 */
	public Object getKeyInCol() {
		return keyInCol;
	}

	/**
	 * 设置属性值：成员对象在集合中的关键字
	 * 
	 * @param keyInCol
	 *            成员对象在集合中的关键字
	 */
	public void setKeyInCol(Object keyInCol) {
		this.keyInCol = keyInCol;
	}

	/**
	 * 该数据项的附加对象，可以用来保存一些附加信息
	 */
	private Object tag = null;

	/**
	 * 获取属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * 
	 * @return 该数据项的附加对象，可以用来保存一些附加信息
	 */
	public Object getTag() {
		return tag;
	}

	/**
	 * 设置属性值：该数据项的附加对象，可以用来保存一些附加信息
	 * 
	 * @param tag
	 *            该数据项的附加对象，可以用来保存一些附加信息
	 */
	public void setTag(Object tag) {
		this.tag = tag;
	}

	/**
	 * 获取属性值：
	 * 
	 * @return
	 */
	public int getNBXH() {
		int nBXH = 0;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.内部序号.getEnumValue();
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
	 * 设置属性值：内部序号
	 * 
	 * @param nBXH
	 *            内部序号
	 */
	public void setNBXH(int nBXH) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.内部序号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(String.valueOf(nBXH));
			}
		}
	}


	
	/**
	 * 获取属性值：文号
	 * 
	 * @return 文号
	 */
	public String getDocNo() {
		String docNo = null;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.文号.getEnumValue();
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
	 * 设置属性值：文号
	 * 
	 * @param docNo
	 *            文号
	 */
	public void setDocNo(String docNo) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.文号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(docNo);
			}
		}
	}

	/**
	 * 获取属性值：题名
	 * 
	 * @return 题名
	 */
	public String getTitle() {
		String title = null;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.题名.getEnumValue();
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
	 * 设置属性值：题名
	 * 
	 * @param title
	 *            题名
	 */
	public void setTitle(String title) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.题名.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(title);
			}
		}
	}

	/**
	 * 获取属性值：档案形成年度
	 * 
	 * @return 档案形成年度
	 */
	public int getFormationYear() {
		int formationYear = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案形成年度.getEnumValue();
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
	 * 设置属性值：档案形成年度
	 * 
	 * @param formationYear
	 *            档案形成年度
	 */
	public void setFormationYear(int formationYear) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案形成年度.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(formationYear));
			}
		}
	}

	/**
	 * 获取属性值：档案页数
	 * 
	 * @return 档案页数
	 */
	public int getPageSum() {
		int pageSum = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案页数.getEnumValue();
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
	 * 设置属性值：档案页数
	 * 
	 * @param pageSum
	 *            档案页数
	 */
	public void setPageSum(int pageSum) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案页数.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(pageSum));
			}
		}
	}

	/**
	 * 获取属性值：保管期限编号
	 * 
	 * @return 保管期限编号
	 */
	public int getRetentionPeriodID() {
		int retentionPeriodID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.保管期限编号.getEnumValue();
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
	 * 设置属性值：保管期限编号
	 * 
	 * @param retentionPeriodID
	 *            保管期限编号
	 */
	public void setRetentionPeriodID(int retentionPeriodID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.保管期限编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(retentionPeriodID));
			}
		}
	}

	/**
	 * 获取属性值：保管期限文本
	 * 
	 * @return 保管期限文本
	 */
	public String getRetentionPeriodText() {
		String retentionPeriodText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.保管期限文本.getEnumValue();
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
	 * 设置属性值：保管期限文本
	 * 
	 * @param retentionPeriodText
	 *            保管期限文本
	 */
	public void setRetentionPeriodText(String retentionPeriodText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.保管期限文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(retentionPeriodText);
			}
		}
	}

	/**
	 * 获取属性值：保管期截止年度
	 * 
	 * @return 保管期截止年度
	 */
	public int getRetentionEndYear() {
		int retentionEndYear = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.保管期截止年度.getEnumValue();
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
	 * 设置属性值：保管期截止年度
	 * 
	 * @param retentionEndYear
	 *            保管期截止年度
	 */
	public void setRetentionEndYear(int retentionEndYear) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.保管期截止年度.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(retentionEndYear));
			}
		}
	}

	/**
	 * 获取属性值：档案密级编号
	 * 
	 * @return 档案密级编号
	 */
	public int getSecrecyID() {
		int secrecyID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案密级编号.getEnumValue();
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
	 * 设置属性值：档案密级编号
	 * 
	 * @param secrecyID
	 *            档案密级编号
	 */
	public void setSecrecyID(int secrecyID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案密级编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(secrecyID));
			}
		}
	}

	/**
	 * 获取属性值：档案密级文本
	 * 
	 * @return 档案密级文本
	 */
	public String getSecrecyText() {
		String secrecyText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案密级文本.getEnumValue();
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
	 * 设置属性值：档案密级文本
	 * 
	 * @param secrecyText
	 *            档案密级文本
	 */
	public void setSecrecyText(String secrecyText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案密级文本.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(secrecyText);
			}
		}
	}

	/**
	 * 获取属性值：档案形成部门编号
	 * 
	 * @return 档案形成部门编号
	 */
	public int getFormationDepartmentID() {
		int formationDepartmentID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案形成部门编号.getEnumValue();
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
	 * 设置属性值：档案形成部门编号
	 * 
	 * @param formationDepartmentID
	 *            档案形成部门编号
	 */
	public void setFormationDepartmentID(int formationDepartmentID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案形成部门编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(formationDepartmentID));
			}
		}
	}

	/**
	 * 获取属性值：档案形成部门名称
	 * 
	 * @return 档案形成部门名称
	 */
	public String getFormationDepartment() {
		String formationDepartment = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.档案形成部门名称.getEnumValue();
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
	 * 设置属性值：档案形成部门名称
	 * 
	 * @param formationDepartment
	 *            档案形成部门名称
	 */
	public void setFormationDepartment(String formationDepartment) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.档案形成部门名称.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(formationDepartment);
			}
		}
	}

	/**
	 * 设置属性值：主题词
	 * 
	 * @param keyWord
	 *            主题词
	 */
	public void setKeyWord(String keyWord) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.主题词.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(keyWord);
			}
		}
	}

	/**
	 * 获取属性值：主题词
	 * 
	 * @return 主题词
	 */
	public String getKeyWord() {
		String keyWord = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.主题词.getEnumValue();
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
	 * 设置属性值：全文
	 * 
	 * @param fullText
	 *            全文
	 */
	public void setFullText(String fullText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.全文.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(fullText);
			}
		}
	}

	/**
	 * 获取属性值：全文
	 * 
	 * @return 全文
	 */
	public String getFullText() {
		String fullText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.全文.getEnumValue();
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
	 * 获取属性值：文种编号
	 * 
	 * @return 文中编号
	 */
	public int getDocTypeID() {
		int docTypeID = 0;
		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.文种编号.getEnumValue();
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
	 * 设置属性值：文种编号
	 * 
	 * @param docTypeID 文种编号
	 */
	public void setDocTypeID(int docTypeID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.文种编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(docTypeID));
			}
		}
	}

	/**
	 * 获取属性值：文种
	 * 
	 * @return 文种
	 */
	public String getDocTypeText() {
		String docTypeText = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.文种.getEnumValue();
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
	 * 设置属性值：文种
	 * 
	 * @param docTypeText 文种
	 */
	public void setDocTypeText(String docTypeText) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.文种.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(docTypeText));
			}
		}
	}

	/**
	 * 获取属性值：登记日期
	 * 
	 * @return 登记日期
	 */
	public Date getRegDate() {
		Date regDate = null;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.登记日期.getEnumValue();
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
	 * 设置属性值：登记日期
	 * 
	 * @param saveDate
	 *            登记日期
	 */
	public void setRegDate(Date regDate) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.登记日期.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				SimpleDateFormat dateFormater = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				rowFieldsValues.get(pFieldName).setValue(dateFormater.format(regDate));
			}
		}
	}

	/**
	 * 获取属性值：登记人员编号
	 * 
	 * @return 登记人员编号
	 */
	public int getRegUserID() {
		int regUserID = 0;

		try {
			if (rowFieldsValues != null) {
				String pFieldName = EnumSystemDataItem.登记人员编号.getEnumValue();
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
	 * 设置属性值：登记人员编号
	 * 
	 * @param regUserID
	 *            登记人员编号
	 */
	public void setRegUserID(int regUserID) {
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.登记人员编号.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						String.valueOf(regUserID));
			}
		}
	}

	/**
	 * 获取属性值：归档标志
	 * @return 归档标志
	 */
	public boolean getSavedFlag()
	{
			boolean savedFlag=false;
		
		try
		{
			if (rowFieldsValues!=null)
			{
				String pFieldName=EnumSystemDataItem.归档标志.getEnumValue();
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
	 * 设置属性值：归档标志
	 * @param savedFlag 归档标志
	 */
	public void setSavedFlag(boolean savedFlag)
	{
		if (rowFieldsValues != null) {
			String pFieldName = EnumSystemDataItem.归档标志.getEnumValue();
			if (rowFieldsValues.containsKey(pFieldName)) {
				rowFieldsValues.get(pFieldName).setValue(
						savedFlag?"1":"0");
			}
		}
	}
	

	
	/**
	 * clone
	 * 
	 * @return 克隆当前对象实例后得到的新对象
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
	 * 从指定对象克隆，复制所有属性值
	 * 
	 * @param officialArchivesInfo
	 *            指定的对象源
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
	 * 表记录行的字段值集合，以字段名作为关键字
	 */
	private Map<String, FieldValue> rowFieldsValues = null;

	/**
	 * 设置属性值：表记录行的字段值集合，以字段名作为关键字
	 * 
	 * @param rowFieldsValues
	 *            表记录行的字段值集合，以字段名作为关键字
	 */
	public void setRowFieldsValues(Map<String, FieldValue> rowFieldsValues) {
		this.rowFieldsValues = rowFieldsValues;
	}

	/**
	 * 获取属性值：表记录行的字段值集合，以字段名作为关键字
	 * 
	 * @return 表记录行的字段值集合，以字段名作为关键字
	 */
	public Map<String, FieldValue> getRowFieldsValues() {
		return rowFieldsValues;
	}
	
}