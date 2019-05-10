package com.orifound.aiim.web.util;

/**
 * <p>Title: StringTool 字符串工具类 </p>
 * <p>Description: 提供对字符串的各种操作</p>
 */

public class StringTool {
	
	private StringTool() {
	}
	
	/**
	 * 检测字符串是否为空
	 * <p>不为空返回true</p>
	 * @param string 监测的字符串
	 * @return boolean
	 */
	public static boolean checkNull(String string) {
		if(string!=null && !"".equals(string.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * 取出字符串前后的空格
	 * @param string
	 * @return
	 */
	public static String trimSpace(String string) {
		if(checkNull(string)) {
			return string.trim();
		}
		return "";
	}
}