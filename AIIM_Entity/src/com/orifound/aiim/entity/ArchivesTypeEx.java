package com.orifound.aiim.entity;

import java.util.LinkedHashMap;

/**
 * 扩展了的档案分类实体对象类<br>
 * 适用于各种以档案分类为索引的情况
 */
public class ArchivesTypeEx extends ArchivesType {

	/**
	 * 构造函数
	 * @param archivesType 档案分类
	 */
	public ArchivesTypeEx(ArchivesType archivesType){
		cloneFrom(archivesType);
		
		//克隆其子分类
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
	 * 构造函数
	 * @param archivesTypeID 档案分类编号
	 */
	public ArchivesTypeEx(int archivesTypeID){
		setID(archivesTypeID);
	}
	
	/**
	 * 构造函数
	 * @param archivesTypeID 档案分类编号
	 * @param name 档案分类名称
	 */
	public ArchivesTypeEx(int archivesTypeID,String name){
		this(archivesTypeID);
		setName(name);
	}
	
	/**
	 * 构造函数
	 * @param archivesTypeID 档案分类编号
	 * @param name 档案分类名称
	 * @param fullCode 完整分类代码，例如JX14
	 */
	public ArchivesTypeEx(int archivesTypeID,String name,String fullCode){
		this(archivesTypeID,name);
		setFullCode(fullCode);
	}
	
	/**
	 * 构造函数
	 */
	public ArchivesTypeEx(){
		
	}
	
	/**
	 * 当前档案分类需要进行著录审核的档案数量
	 */
	private int inputCheckNeedArchivesInfoCount = 0;

	/**
	 * 设置属性值：当前档案分类需要进行著录审核的档案数量
	 * @param inputCheckNeedArchivesInfoCount 当前档案分类需要进行著录审核的档案数量
	 */
	public void setInputCheckNeedArchivesInfoCount(int inputCheckNeedArchivesInfoCount) {
		this.inputCheckNeedArchivesInfoCount = inputCheckNeedArchivesInfoCount;
	}

	/**
	 * 获取属性值：当前档案分类需要进行著录审核的档案数量
	 * @return 当前档案分类需要进行著录审核的档案数量
	 */
	public int getInputCheckNeedArchivesInfoCount() {
		return inputCheckNeedArchivesInfoCount;
	}

	
	
	/**
	 * 当前档案分类著录审核通过的档案数量
	 */
	private int inputCheckPassArchivesInfoCount = 0;

	/**
	 * 设置属性值：当前档案分类著录审核通过的档案数量
	 * @param inputCheckPassArchivesInfoCount 当前档案分类著录审核通过的档案数量
	 */
	public void setInputCheckPassArchivesInfoCount(int inputCheckPassArchivesInfoCount) {
		this.inputCheckPassArchivesInfoCount = inputCheckPassArchivesInfoCount;
	}

	/**
	 * 获取属性值：当前档案分类著录审核通过的档案数量
	 * @return 当前档案分类著录审核通过的档案数量
	 */
	public int getInputCheckPassArchivesInfoCount() {
		return inputCheckPassArchivesInfoCount;
	}

	/**
	 * 当前档案分类著录审核打回（未通过）的档案数量
	 */
	private int inputCheckBackArchivesInfoCount = 0;

	/**
	 * 设置属性值：当前档案分类著录审核打回（未通过）的档案数量
	 * @param inputCheckBackArchivesInfo 当前档案分类著录审核打回（未通过）的档案数量
	 */
	public void setInputCheckBackArchivesInfoCount(int inputCheckBackArchivesInfoCount) {
		this.inputCheckBackArchivesInfoCount = inputCheckBackArchivesInfoCount;
	}

	/**
	 * 获取属性值：当前档案分类著录审核打回（未通过）的档案数量
	 * @return 当前档案分类著录审核打回（未通过）的档案数量
	 */
	public int getInputCheckBackArchivesInfoCount() {
		return inputCheckBackArchivesInfoCount;
	}

	/**
	 * 当前档案分类实物档案接收审核未通过的档案数量
	 */
	private int paperCheckBackArchivesInfoCount = 0;

	/**
	 * 设置属性值：当前档案分类实物档案接收审核未通过的档案数量
	 * @param paperCheckBackArchivesInfoCount 当前档案分类实物档案接收审核未通过的档案数量
	 */
	public void setPaperCheckBackArchivesInfoCount(int paperCheckBackArchivesInfoCount) {
		this.paperCheckBackArchivesInfoCount = paperCheckBackArchivesInfoCount;
	}

	/**
	 * 获取属性值：当前档案分类实物档案接收审核未通过的档案数量
	 * @return 当前档案分类实物档案接收审核未通过的档案数量
	 */
	public int getPaperCheckBackArchivesInfoCount() {
		return paperCheckBackArchivesInfoCount;
	}

	/**
	 * 实物档案移交清单中当前档案分类的档案数量
	 */
	private int paperArchivesInfosCount = 0;

	/**
	 * 设置属性值：实物档案移交清单中当前档案分类的档案数量
	 * @param archivesInfosCount 实物档案移交清单中当前档案分类的档案数量
	 */
	public void setPaperArchivesInfosCount(int paperArchivesInfosCount) {
		this.paperArchivesInfosCount = paperArchivesInfosCount;
	}

	/**
	 * 获取属性值：实物档案移交清单中当前档案分类的档案数量
	 * @return 实物档案移交清单中当前档案分类的档案数量
	 */
	public int getPaperArchivesInfosCount() {
		return paperArchivesInfosCount;
	}
	
	/**
	 * 跨门类查询时当前档案分类的结果集记录数
	 */
	private int queryCrossClassifiedResultRecordCount = 0;

	/**
	 * 设置属性值：跨门类查询时当前档案分类的结果集记录数
	 * @param queryCrossClassifiedResultRecordCount 跨门类查询时当前档案分类的结果集记录数
	 */
	public void setQueryCrossClassifiedResultRecordCount(int queryCrossClassifiedResultRecordCount) {
		this.queryCrossClassifiedResultRecordCount = queryCrossClassifiedResultRecordCount;
	}

	/**
	 * 获取属性值：跨门类查询时当前档案分类的结果集记录数
	 * @return 跨门类查询时当前档案分类的结果集记录数
	 */
	public int getQueryCrossClassifiedResultRecordCount() {
		return queryCrossClassifiedResultRecordCount;
	}

	/**
	 * 上级扩展档案分类
	 */
	private ArchivesTypeEx parentArchivesTypeEx = null;

	/**
	 * 获取属性值：上级扩展档案分类
	 */
	public ArchivesTypeEx getParentArchivesTypeEx() {
		return parentArchivesTypeEx;
	}

	/**
	 * 设置属性值：上级扩展档案分类
	 */
	public void setParentArchivesTypeEx(ArchivesTypeEx parentArchivesTypeEx) {
		this.parentArchivesTypeEx = parentArchivesTypeEx;
	}

	/**
	 * 下级扩展档案分类集合
	 */
	private LinkedHashMap<Integer, ArchivesTypeEx> childArchivesTypeExs=null;

	/**
	 * 设置属性值：下级扩展档案分类集合
	 * 
	 * @param childArchivesTypeExs
	 *            下级扩展档案分类集合
	 */
	public void setChildArchivesTypeExs(
			LinkedHashMap<Integer, ArchivesTypeEx> childArchivesTypeExs) {
		this.childArchivesTypeExs = childArchivesTypeExs;
		
		//把其下属扩展档案分类的父扩展档案分类属性都进行统一赋值
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
	 * 获取属性值：下级扩展档案分类集合
	 */
	public LinkedHashMap<Integer, ArchivesTypeEx> getChildArchivesTypeExs() {
		return childArchivesTypeExs;
	}

	
	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public ArchivesTypeEx clone()
	{
		ArchivesType archivesType = super.clone();
		ArchivesTypeEx item=new ArchivesTypeEx(archivesType);
		
		item.setKeyInCol(this.getKeyInCol());
		item.setTag(this.getTag());
		
		//克隆ArchivesTypeEx对象扩展的属性值，将来有新的扩展属性应该添加至此进行克隆
		item.setInputCheckNeedArchivesInfoCount(inputCheckNeedArchivesInfoCount);
		item.setInputCheckPassArchivesInfoCount(inputCheckPassArchivesInfoCount);
		item.setInputCheckBackArchivesInfoCount(inputCheckBackArchivesInfoCount);
		item.setPaperCheckBackArchivesInfoCount(paperCheckBackArchivesInfoCount);
		item.setPaperArchivesInfosCount(paperArchivesInfosCount);
		item.setQueryCrossClassifiedResultRecordCount(queryCrossClassifiedResultRecordCount);

		//其子分类需要严格克隆下来，因为可能会改变它
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
	* 从指定对象克隆，复制所有属性值
	* @param archivesTypeDataItem 指定的对象源
	*/
	public void cloneFrom(ArchivesTypeEx archivesTypeEx)
	{
		super.cloneFrom(archivesTypeEx);
		this.parentArchivesTypeEx=archivesTypeEx.getParentArchivesTypeEx();
		this.childArchivesTypeExs=archivesTypeEx.getChildArchivesTypeExs();
		
		//克隆ArchivesTypeEx对象扩展的属性值
		cloneExtendProperty(archivesTypeEx);
	}
	
	/**
	* 从指定对象克隆扩展的属性值
	* @param archivesTypeDataItem 指定的对象源
	*/
	public void cloneExtendProperty(ArchivesTypeEx archivesTypeEx)
	{
		//克隆ArchivesTypeEx对象扩展的属性值，将来有新的扩展属性应该添加至此进行克隆
		this.inputCheckNeedArchivesInfoCount=archivesTypeEx.getInputCheckNeedArchivesInfoCount();
		this.inputCheckPassArchivesInfoCount=archivesTypeEx.getInputCheckPassArchivesInfoCount();
		this.inputCheckBackArchivesInfoCount=archivesTypeEx.getInputCheckBackArchivesInfoCount();
		this.paperCheckBackArchivesInfoCount=archivesTypeEx.getPaperCheckBackArchivesInfoCount();
		this.paperArchivesInfosCount=archivesTypeEx.getPaperArchivesInfosCount();
		this.queryCrossClassifiedResultRecordCount=archivesTypeEx.getQueryCrossClassifiedResultRecordCount();
	}
}
