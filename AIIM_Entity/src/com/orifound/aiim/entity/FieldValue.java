/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 字段值定义类
 *
 */
public class FieldValue extends ArchivesTypeDataItem
{
	/**
	 * 构造函数
	 * @param archivesTypeDataItem 档案分类数据项
	 */
	public FieldValue(ArchivesTypeDataItem archivesTypeDataItem)
	{
		super.cloneFrom(archivesTypeDataItem);
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
	 * 数据项（字段）的值
	 */
	private String value = null;

	/**
	 * 设置属性值：数据项（字段）的值
	 * @param value 数据项（字段）的值
	 */
	public void setValue(String value)
	{
		this.value = value;
	}

	/**
	 * 获取属性值：数据项（字段）的值
	 * @return 数据项（字段）的值
	 */
	public String getValue()
	{
		return value;
	}

	/**
	 * clone
	 * @return 克隆当前对象实例后得到的新对象
	 */
	public FieldValue clone()
	{
		FieldValue item = new FieldValue(this);
		item.setValue(value);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}
	
	/**
	* 从指定对象克隆，复制所有属性值
	* @param fieldValue 指定的对象源
	*/
	public void cloneFrom(FieldValue fieldValue)
	{
		super.cloneFrom(fieldValue);
		this.value=fieldValue.getValue();
		this.keyInCol=fieldValue.getKeyInCol();
		this.tag=fieldValue.getTag();
	}
}
