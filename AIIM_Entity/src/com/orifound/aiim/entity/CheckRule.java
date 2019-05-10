package com.orifound.aiim.entity;

    
/**
 * У����������ֵ���ʵ����
 */
public class CheckRule
{
    /**
     * ���캯��
     */
    public CheckRule()
    {
        
    }
    
	/**
	* ���ֶβ����Ĺ��캯��
	* @param iD У�������
	* @param name У���������
	* @param maskString �����ַ���
	* @param regExpressionString ������ʽ
	*/
	public CheckRule(int iD,String name,String maskString,String regExpressionString)
	{
		// Table Name: DD_CheckRule
		// Columns List,Can Used in SELECT SQL: ID,Name,MaskString,RegExpressionString
		// Columns List,Can Used in INSERT SQL: :ID,:Name,:MaskString,:RegExpressionString
		// Columns List,Can Used in UPDATE SQL: ID=:ID,Name=:Name,MaskString=:MaskString,RegExpressionString=:RegExpressionString

		this(name, maskString, regExpressionString);
		this.iD = iD;
	}
	
	/**
	* ���ֶβ����Ĺ��캯��
	* @param name У���������
	* @param maskString �����ַ���
	* @param regExpressionString ������ʽ
	*/
	public CheckRule(String name,String maskString,String regExpressionString)
	{
		this.name = name;
		this.maskString = maskString;
		this.regExpressionString = regExpressionString;
	}

	/**
	 * ��Ա�����ڼ����еĹؼ���
	 */
	private Object keyInCol=null;

	/**
	 * ��ȡ����ֵ����Ա�����ڼ����еĹؼ���
	 * @return ��Ա�����ڼ����еĹؼ���
	 */
	public Object getKeyInCol()
	{
		return keyInCol;
	}

	/**
	 * ��������ֵ����Ա�����ڼ����еĹؼ���
	 * @param keyInCol ��Ա�����ڼ����еĹؼ���
	 */
	public void setKeyInCol(Object keyInCol)
	{
		this.keyInCol = keyInCol;
	}

	/**
	 * ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	private Object tag=null;

	/**
	 * ��ȡ����ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @return ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public Object getTag()
	{
		return tag;
	}

	/**
	 * ��������ֵ����������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 * @param tag ��������ĸ��Ӷ��󣬿�����������һЩ������Ϣ
	 */
	public void setTag(Object tag)
	{
		this.tag = tag;
	}

	/**
	 * У�������
	 */
	private int iD=0;

	/**
	 * ��ȡ����ֵ��У�������
	 * @return У�������
	 */
	public int getID()
	{
		return iD;
	}

	/**
	 * ��������ֵ��У�������
	 * @param iD У�������
	 */
	public void setID(int iD)
	{
		this.iD = iD;
	}

	/**
	 * У���������
	 */
	private String name=null;

	/**
	 * ��ȡ����ֵ��У���������
	 * @return У���������
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * ��������ֵ��У���������
	 * @param name У���������
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * �����ַ���
	 */
	private String maskString=null;

	/**
	 * ��ȡ����ֵ�������ַ���
	 * @return �����ַ���
	 */
	public String getMaskString()
	{
		return maskString;
	}

	/**
	 * ��������ֵ�������ַ���
	 * @param maskString �����ַ���
	 */
	public void setMaskString(String maskString)
	{
		this.maskString = maskString;
	}

	/**
	 * ������ʽ
	 */
	private String regExpressionString=null;

	/**
	 * ��ȡ����ֵ��������ʽ
	 * @return ������ʽ
	 */
	public String getRegExpressionString()
	{
		return regExpressionString;
	}

	/**
	 * ��������ֵ��������ʽ
	 * @param regExpressionString ������ʽ
	 */
	public void setRegExpressionString(String regExpressionString)
	{
		this.regExpressionString = regExpressionString;
	}

	/**
	 * clone
	 * @return ��¡��ǰ����ʵ����õ����¶���
	 */
	public CheckRule clone()
	{
		CheckRule item = new CheckRule(iD,name,maskString,regExpressionString);
		item.setKeyInCol(keyInCol);
		item.setTag(tag);

		return item;
	}

	/**
	* ��ָ�������¡��������������ֵ
	* @param checkRule ָ���Ķ���Դ
	*/
	public void cloneFrom(CheckRule checkRule)
	{
		this.iD = checkRule.getID();
		this.name = checkRule.getName();
		this.maskString = checkRule.getMaskString();
		this.regExpressionString = checkRule.getRegExpressionString();
	}

    
}



