package com.orifound.aiim.entity;

/**
 * Excel�����ݿ��ӳ���ϵ����
 * @author Administrator
 *
 */
public class ImportDataitemsMapping {

	/**
	 * ӳ���ϵ���
	 */
	private int id;
	
	/**
	 * �������ͱ�־
	 */
	private int importTypeFlag;
	
	/**
	 * �����Դ�ֶ���
	 */
	private String srcExcelFieldName;
	
	/**
	 * �����Ŀ����ֶ���
	 */
	private String desTableFiedName;
	
	/**
	 * �ؼ��ֱ�־
	 */
	private boolean keyFlag;
	
	/**
	 * �ֶζ�Ӧ��ֵ
	 */
	private String value;

	/**
	 * ���캯��
	 */
    public ImportDataitemsMapping(){

	}
	
	/**
	 * ���캯��
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
     * �õ����ֵ
     * @return
     */
	public int getId() {
		return id;
	}

	/**
	 * ���ñ��ֵ
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * �õ���������
	 * @return
	 */
	public int getImportTypeFlag() {
		return importTypeFlag;
	}

	/**
	 * ���õ�������
	 * @param importTypeFlag
	 */
	public void setImportTypeFlag(int importTypeFlag) {
		this.importTypeFlag = importTypeFlag;
	}

	/**
	 * ��õ���Դ�ֶ���
	 * @return
	 */
	public String getSrcExcelFieldName() {
		return srcExcelFieldName;
	}

	/**
	 * ���õ���Դ�ֶ���
	 * @param srcExcelFieldName
	 */
	public void setSrcExcelFieldName(String srcExcelFieldName) {
		this.srcExcelFieldName = srcExcelFieldName;
	}

	/**
	 * ��õ���Ŀ����ֶ���
	 * @return
	 */
	public String getDesTableFiedName() {
		return desTableFiedName;
	}

	/**
	 * ���õ���Ŀ����ֶ���
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
	 * ��õ���ֵ
	 * @return
	 */
	public String getValue() {
		return value;
	}

	/**
	 * ���õ���ֵ
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
