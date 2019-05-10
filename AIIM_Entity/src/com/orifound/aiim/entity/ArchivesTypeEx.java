package com.orifound.aiim.entity;

import java.util.LinkedHashMap;

/**
 * ��չ�˵ĵ�������ʵ�������<br>
 * �����ڸ����Ե�������Ϊ���������
 */
public class ArchivesTypeEx extends ArchivesType {

	/**
	 * ���캯��
	 * @param archivesType ��������
	 */
	public ArchivesTypeEx(ArchivesType archivesType){
		cloneFrom(archivesType);
		
		//��¡���ӷ���
		if (archivesType.getChildArchivesTypes()!=null)
		{
			if (archivesType.getChildArchivesTypes().size()>0)
			{
				LinkedHashMap<Integer, ArchivesTypeEx> children=new LinkedHashMap<Integer, ArchivesTypeEx>();
				for (ArchivesType childItem : archivesType.getChildArchivesTypes().values())
				{
					children.put(childItem.getID(),new ArchivesTypeEx(childItem.clone()));
				}
				
				setChildArchivesTypeExs(children);
			}
		}
	}
	
	/**
	 * ���캯��
	 * @param archivesTypeID ����������
	 */
	public ArchivesTypeEx(int archivesTypeID){
		setID(archivesTypeID);
	}
	
	/**
	 * ���캯��
	 * @param archivesTypeID ����������
	 * @param name ������������
	 */
	public ArchivesTypeEx(int archivesTypeID,String name){
		this(archivesTypeID);
		setName(name);
	}
	
	/**
	 * ���캯��
	 * @param archivesTypeID ����������
	 * @param name ������������
	 * @param fullCode ����������룬����JX14
	 */
	public ArchivesTypeEx(int archivesTypeID,String name,String fullCode){
		this(archivesTypeID,name);
		setFullCode(fullCode);
	}
	
	/**
	 * ���캯��
	 */
	public ArchivesTypeEx(){
		
	}
	
	/**
	 * ��ǰ����������Ҫ������¼��˵ĵ�������
	 */
	private int inputCheckNeedArchivesInfoCount = 0;

	/**
	 * ��������ֵ����ǰ����������Ҫ������¼��˵ĵ�������
	 * @param inputCheckNeedArchivesInfoCount ��ǰ����������Ҫ������¼��˵ĵ�������
	 */
	public void setInputCheckNeedArchivesInfoCount(int inputCheckNeedArchivesInfoCount) {
		this.inputCheckNeedArchivesInfoCount = inputCheckNeedArchivesInfoCount;
	}

	/**
	 * ��ȡ����ֵ����ǰ����������Ҫ������¼��˵ĵ�������
	 * @return ��ǰ����������Ҫ������¼��˵ĵ�������
	 */
	public int getInputCheckNeedArchivesInfoCount() {
		return inputCheckNeedArchivesInfoCount;
	}

	
	
	/**
	 * ��ǰ����������¼���ͨ���ĵ�������
	 */
	private int inputCheckPassArchivesInfoCount = 0;

	/**
	 * ��������ֵ����ǰ����������¼���ͨ���ĵ�������
	 * @param inputCheckPassArchivesInfoCount ��ǰ����������¼���ͨ���ĵ�������
	 */
	public void setInputCheckPassArchivesInfoCount(int inputCheckPassArchivesInfoCount) {
		this.inputCheckPassArchivesInfoCount = inputCheckPassArchivesInfoCount;
	}

	/**
	 * ��ȡ����ֵ����ǰ����������¼���ͨ���ĵ�������
	 * @return ��ǰ����������¼���ͨ���ĵ�������
	 */
	public int getInputCheckPassArchivesInfoCount() {
		return inputCheckPassArchivesInfoCount;
	}

	/**
	 * ��ǰ����������¼��˴�أ�δͨ�����ĵ�������
	 */
	private int inputCheckBackArchivesInfoCount = 0;

	/**
	 * ��������ֵ����ǰ����������¼��˴�أ�δͨ�����ĵ�������
	 * @param inputCheckBackArchivesInfo ��ǰ����������¼��˴�أ�δͨ�����ĵ�������
	 */
	public void setInputCheckBackArchivesInfoCount(int inputCheckBackArchivesInfoCount) {
		this.inputCheckBackArchivesInfoCount = inputCheckBackArchivesInfoCount;
	}

	/**
	 * ��ȡ����ֵ����ǰ����������¼��˴�أ�δͨ�����ĵ�������
	 * @return ��ǰ����������¼��˴�أ�δͨ�����ĵ�������
	 */
	public int getInputCheckBackArchivesInfoCount() {
		return inputCheckBackArchivesInfoCount;
	}

	/**
	 * ��ǰ��������ʵ�ﵵ���������δͨ���ĵ�������
	 */
	private int paperCheckBackArchivesInfoCount = 0;

	/**
	 * ��������ֵ����ǰ��������ʵ�ﵵ���������δͨ���ĵ�������
	 * @param paperCheckBackArchivesInfoCount ��ǰ��������ʵ�ﵵ���������δͨ���ĵ�������
	 */
	public void setPaperCheckBackArchivesInfoCount(int paperCheckBackArchivesInfoCount) {
		this.paperCheckBackArchivesInfoCount = paperCheckBackArchivesInfoCount;
	}

	/**
	 * ��ȡ����ֵ����ǰ��������ʵ�ﵵ���������δͨ���ĵ�������
	 * @return ��ǰ��������ʵ�ﵵ���������δͨ���ĵ�������
	 */
	public int getPaperCheckBackArchivesInfoCount() {
		return paperCheckBackArchivesInfoCount;
	}

	/**
	 * ʵ�ﵵ���ƽ��嵥�е�ǰ��������ĵ�������
	 */
	private int paperArchivesInfosCount = 0;

	/**
	 * ��������ֵ��ʵ�ﵵ���ƽ��嵥�е�ǰ��������ĵ�������
	 * @param archivesInfosCount ʵ�ﵵ���ƽ��嵥�е�ǰ��������ĵ�������
	 */
	public void setPaperArchivesInfosCount(int paperArchivesInfosCount) {
		this.paperArchivesInfosCount = paperArchivesInfosCount;
	}

	/**
	 * ��ȡ����ֵ��ʵ�ﵵ���ƽ��嵥�е�ǰ��������ĵ�������
	 * @return ʵ�ﵵ���ƽ��嵥�е�ǰ��������ĵ�������
	 */
	public int getPaperArchivesInfosCount() {
		return paperArchivesInfosCount;
	}
	
	/**
	 * �������ѯʱ��ǰ��������Ľ������¼��
	 */
	private int queryCrossClassifiedResultRecordCount = 0;

	/**
	 * ��������ֵ���������ѯʱ��ǰ��������Ľ������¼��
	 * @param queryCrossClassifiedResultRecordCount �������ѯʱ��ǰ��������Ľ������¼��
	 */
	public void setQueryCrossClassifiedResultRecordCount(int queryCrossClassifiedResultRecordCount) {
		this.queryCrossClassifiedResultRecordCount = queryCrossClassifiedResultRecordCount;
	}

	/**
	 * ��ȡ����ֵ���������ѯʱ��ǰ��������Ľ������¼��
	 * @return �������ѯʱ��ǰ��������Ľ������¼��
	 */
	public int getQueryCrossClassifiedResultRecordCount() {
		return queryCrossClassifiedResultRecordCount;
	}

	/**
	 * �ϼ���չ��������
	 */
	private ArchivesTypeEx parentArchivesTypeEx = null;

	/**
	 * ��ȡ����ֵ���ϼ���չ��������
	 */
	public ArchivesTypeEx getParentArchivesTypeEx() {
		return parentArchivesTypeEx;
	}

	/**
	 * ��������ֵ���ϼ���չ��������
	 */
	public void setParentArchivesTypeEx(ArchivesTypeEx parentArchivesTypeEx) {
		this.parentArchivesTypeEx = parentArchivesTypeEx;
	}

	/**
	 * �¼���չ�������༯��
	 */
	private LinkedHashMap<Integer, ArchivesTypeEx> childArchivesTypeExs=null;

	/**
	 * ��������ֵ���¼���չ�������༯��
	 * 
	 * @param childArchivesTypeExs
	 *            �¼���չ�������༯��
	 */
	public void setChildArchivesTypeExs(
			LinkedHashMap<Integer, ArchivesTypeEx> childArchivesTypeExs) {
		this.childArchivesTypeExs = childArchivesTypeExs;
		
		//����������չ��������ĸ���չ�����������Զ�����ͳһ��ֵ
		if (childArchivesTypeExs!=null)
		{
			if (childArchivesTypeExs.size()>0)
			{
				for (ArchivesTypeEx item : childArchivesTypeExs.values())
				{
					item.setParentArchivesTypeEx(this);
				}
			}
		}
	}

	/**
	 * ��ȡ����ֵ���¼���չ�������༯��
	 */
	public LinkedHashMap<Integer, ArchivesTypeEx> getChildArchivesTypeExs() {
		return childArchivesTypeExs;
	}

	
	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public ArchivesTypeEx clone()
	{
		ArchivesType archivesType = super.clone();
		ArchivesTypeEx item=new ArchivesTypeEx(archivesType);
		
		item.setKeyInCol(this.getKeyInCol());
		item.setTag(this.getTag());
		
		//��¡ArchivesTypeEx������չ������ֵ���������µ���չ����Ӧ��������˽��п�¡
		item.setInputCheckNeedArchivesInfoCount(inputCheckNeedArchivesInfoCount);
		item.setInputCheckPassArchivesInfoCount(inputCheckPassArchivesInfoCount);
		item.setInputCheckBackArchivesInfoCount(inputCheckBackArchivesInfoCount);
		item.setPaperCheckBackArchivesInfoCount(paperCheckBackArchivesInfoCount);
		item.setPaperArchivesInfosCount(paperArchivesInfosCount);
		item.setQueryCrossClassifiedResultRecordCount(queryCrossClassifiedResultRecordCount);

		//���ӷ�����Ҫ�ϸ��¡��������Ϊ���ܻ�ı���
		if (childArchivesTypeExs!=null)
		{
			if (childArchivesTypeExs.size()>0)
			{
				LinkedHashMap<Integer, ArchivesTypeEx> children=new LinkedHashMap<Integer, ArchivesTypeEx>();
				for (ArchivesTypeEx childItem : childArchivesTypeExs.values())
				{
					children.put(childItem.getID(), childItem.clone());
				}
				
				item.setChildArchivesTypeExs(children);
			}
		}
		
		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param archivesTypeDataItem ָ���Ķ���Դ
	*/
	public void cloneFrom(ArchivesTypeEx archivesTypeEx)
	{
		super.cloneFrom(archivesTypeEx);
		this.parentArchivesTypeEx=archivesTypeEx.getParentArchivesTypeEx();
		this.childArchivesTypeExs=archivesTypeEx.getChildArchivesTypeExs();
		
		//��¡ArchivesTypeEx������չ������ֵ
		cloneExtendProperty(archivesTypeEx);
	}
	
	/**
	* ��ָ�������¡��չ������ֵ
	* @param archivesTypeDataItem ָ���Ķ���Դ
	*/
	public void cloneExtendProperty(ArchivesTypeEx archivesTypeEx)
	{
		//��¡ArchivesTypeEx������չ������ֵ���������µ���չ����Ӧ��������˽��п�¡
		this.inputCheckNeedArchivesInfoCount=archivesTypeEx.getInputCheckNeedArchivesInfoCount();
		this.inputCheckPassArchivesInfoCount=archivesTypeEx.getInputCheckPassArchivesInfoCount();
		this.inputCheckBackArchivesInfoCount=archivesTypeEx.getInputCheckBackArchivesInfoCount();
		this.paperCheckBackArchivesInfoCount=archivesTypeEx.getPaperCheckBackArchivesInfoCount();
		this.paperArchivesInfosCount=archivesTypeEx.getPaperArchivesInfosCount();
		this.queryCrossClassifiedResultRecordCount=archivesTypeEx.getQueryCrossClassifiedResultRecordCount();
	}
}
