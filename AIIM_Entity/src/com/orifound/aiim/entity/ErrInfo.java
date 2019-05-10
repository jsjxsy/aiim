/**
 * 
 */
package com.orifound.aiim.entity;

/**
 * 错误信息对象类
 * 
 */
public class ErrInfo {

	/**
	 * 错误信息的详细内容
	 */
	private StringBuilder content = new StringBuilder();

	/**
	 * 导致该错误发生的异常信息
	 */
	private Exception exception=null;

	/**
	 * 构造函数
	 */
	public ErrInfo() {
		
	}

	/**
	 * @return 获取属性值：错误信息的详细内容
	 */
	public StringBuilder getContent() {
		return content;
	}

	/**
	 * @param exception
	 *            设置属性值：导致该错误发生的异常信息
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

	/**
	 * @return 获取属性值：导致该错误发生的异常信息
	 */
	public Exception getException() {
		return exception;
	}

	@Override
	public String toString() {
		
		//如果有错误的SQL语句则拼接在错误描述中
		if (badSQL.isEmpty()==false){
			content.append("错误的SQL语句：");
			content.append(badSQL);
		}
		
		return content.toString();
	}

	/**
	 * 获取最底层错误信息
	 * 
	 * @return 错误信息中最末尾箭头后面的字符串
	 */
	public String toShortString() {
		String shortString;

		int index = 0;
		index = content.lastIndexOf("-->");

		if (index > 0) {
			shortString = content.toString().substring(index + 3);
		} else {
			shortString = content.toString();
		}

		return shortString;
	}
	
	/**
	 * 执行出错的SQL语句
	 */
	private String badSQL = "";

	/**
	 * 设置属性值：执行出错的SQL语句
	 * @param badSQL 执行出错的SQL语句
	 */
	public void setBadSQL(String badSQL) {
		this.badSQL = badSQL;
	}

	/**
	 * 获取属性值：执行出错的SQL语句
	 * @return 执行出错的SQL语句
	 */
	public String getBadSQL() {
		return badSQL;
	}

	/**
	 * 清除当前错误信息
	 * @param pErrInfo 处理失败的错误原因描述
	 * @return 处理成功返回true，否则返回false
	 */
	public void clear() {
		content.delete(0, content.length());
		exception=null;
		badSQL="";
	}

	

}
