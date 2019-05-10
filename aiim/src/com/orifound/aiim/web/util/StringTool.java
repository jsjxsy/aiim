package com.orifound.aiim.web.util;

/**
 * <p>Title: StringTool �ַ��������� </p>
 * <p>Description: �ṩ���ַ����ĸ��ֲ���</p>
 */

public class StringTool {
	
	private StringTool() {
	}
	
	/**
	 * ����ַ����Ƿ�Ϊ��
	 * <p>��Ϊ�շ���true</p>
	 * @param string �����ַ���
	 * @return boolean
	 */
	public static boolean checkNull(String string) {
		if(string!=null && !"".equals(string.trim())) {
			return true;
		}
		return false;
	}
	
	/**
	 * ȡ���ַ���ǰ��Ŀո�
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