/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 整数扩展对象类<br>
 * 适用于作为函数的参数类型，以实现参数变量的地址传递
 *
 */
public class IntegerEx
{
	/**
	 * 构造函数
	 */
	public IntegerEx()
	{
	}

	/**
	 * 构造函数
	 * @param value 整数的值
	 */
	public IntegerEx(int value)
	{
		this.value=value;
	}
	
	/**
	 * 整数值
	 */
	private int value = 0;

	/**
	 * 设置属性值：整数值
	 * @param value 整数值
	 */
	public void setValue(int value)
	{
		this.value = value;
	}

	/**
	 * 获取属性值：整数值
	 * @return 整数值
	 */
	public int getValue()
	{
		return value;
	}

	

}
