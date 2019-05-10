package com.orifound.aiim.entity;

/**
 * Excel和数据库的映射关系对象
 * @author Administrator
 *
 */
public class ImportDataitemsMapping {

	/**
	 * 映射关系编号
	 */
	private int id;
	
	/**
	 * 导入类型标志
	 */
	private int importTypeFlag;
	
	/**
	 * 导入的源字段名
	 */
	private String srcExcelFieldName;
	
	/**
	 * 导入的目标表字段名
	 */
	private String desTableFiedName;
	
	/**
	 * 关键字标志
	 */
	private boolean keyFlag;
	
	/**
	 * 字段对应的值
	 */
	private String value;

	/**
	 * 构造函数
	 */
    public ImportDataitemsMapping(){

	}
	
	/**
	 * 构造函数
	 * @param id
	 * @param importTypeFlag
	 * @param srcExcelFieldName
	 * @param desTableFiedName
	 * @param keyFlag
	 */
    public ImportDataitemsMapping(int id,int importTypeFlag,String srcExcelFieldName,String desTableFiedName,boolean keyFlag){
		this.id = id;
		this.importTypeFlag = importTypeFlag;
		this.srcExcelFieldName = srcExcelFieldName;
		this.desTableFiedName = desTableFiedName;
		this.keyFlag = keyFlag;
	}
	
    /**
     * 得到编号值
     * @return
     */
	public int getId() {
		return id;
	}

	/**
	 * 设置编号值
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 得到导出类型
	 * @return
	 */
	public int getImportTypeFlag() {
		return importTypeFlag;
	}

	/**
	 * 设置到处类型
	 * @param importTypeFlag
	 */
	public void setImportTypeFlag(int importTypeFlag) {
		this.importTypeFlag = importTypeFlag;
	}

	/**
	 * 获得导入源字段名
	 * @return
	 */
	public String getSrcExcelFieldName() {
		return srcExcelFieldName;
	}

	/**
	 * 设置导入源字段名
	 * @param srcExcelFieldName
	 */
	public void setSrcExcelFieldName(String srcExcelFieldName) {
		this.srcExcelFieldName = srcExcelFieldName;
	}

	/**
	 * 获得导入目标表字段名
	 * @return
	 */
	public String getDesTableFiedName() {
		return desTableFiedName;
	}

	/**
	 * 设置导入目标表字段名
	 * @param desTableFiedName
	 */
	public void setDesTableFiedName(String desTableFiedName) {
		this.desTableFiedName = desTableFiedName;
	}

	public boolean getKeyFlag() {
		return keyFlag;
	}

	public void setKeyFlag(boolean keyFlag) {
		this.keyFlag = keyFlag;
	}

	/**
	 * 获得导入值
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 设置倒入值
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	public void cloneFrom(ImportDataitemsMapping importDataitemsMapping)
	{
		this.id = importDataitemsMapping.getId();
		this.importTypeFlag = importDataitemsMapping.getImportTypeFlag();
		this.srcExcelFieldName = importDataitemsMapping.getSrcExcelFieldName();
		this.desTableFiedName = importDataitemsMapping.getDesTableFiedName();
		this.keyFlag = importDataitemsMapping.getKeyFlag();
		this.value = importDataitemsMapping.getValue();
	}
}
