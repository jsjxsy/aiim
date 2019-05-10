package com.orifound.aiim.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title: TimeTool ʱ�乤����  </p>
 * <p>Description: �ṩ��ʱ���ת���Լ���������</p>
 */

public class TimeTool {
	
	private static String pattern = "yyyy-MM-dd";
	
	private TimeTool() {
	}
	
	/**
	 * ���ص�ǰʱ���ַ���
	 * <p>Ĭ�ϸ�ʽ(yyyy-MM-dd)</p>
	 * @return String
	 */
	public static String getDefalutDate() {
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	
	/**
	 * ����ʱ��ָ����ʽ�ַ���
	 * <p>Ĭ�ϸ�ʽ(yyyy-MM-dd)</p>
	 * @param pattern		��Ĭ��ʱ���ʽ
	 * @param dateString	ʱ��
	 * @return String 	ʱ���ַ���
	 */
	public static String getDefalutDate(String pattern, Date date) {
		return (new SimpleDateFormat(pattern)).format(date);
	}
	
	/**
	 * ����ʱ���ַ�������Ĭ�ϸ�ʽʱ���ַ���
	 * <p>Ĭ�ϸ�ʽ(yyyy-MM-dd)</p>
	 * @param pattern		��Ĭ��ʱ���ʽ
	 * @param dateString	ʱ�����ַ���
	 * @return String 	ʱ���ַ���
	 */
	public static String getDefalutDate(String pattern, String dateString) {
		return (new SimpleDateFormat(pattern)).format(getDateFromString(pattern, dateString));
	}
	
	/**
	 * ����ָ����ʽ�ĵ�ǰʱ���ַ���
	 * @param pattern ʱ���ʽ
	 * @return String
	 */
	public static String displayDateTime(String pattern) {
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	
	/**
	 * Ĭ�ϸ�ʽ(yyyy-MM-dd)ʱ���ַ���ת��ΪDate����
	 * @param strDate ʱ���ַ�����ʾ
	 * @return Date
	 * @throws Exception
	 */
	public static Date getDateFromString(String strDate)  {
		try {
			return (new SimpleDateFormat(pattern)).parse(strDate);
		} catch (ParseException e) {
			try {
				throw new Exception("ʱ���ַ���ת��ʧ��",e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ʱ���ַ���ת��ΪDate����
	 * @param pattern ʱ���ʽ
	 * @param strDate ʱ���ַ�����ʾ
	 * @return Date
	 * @throws Exception
	 */
	public static Date getDateFromString(String pattern, String strDate) {
		try {
			return (new SimpleDateFormat(pattern)).parse(strDate);
		} catch (ParseException e) {
			try {
				throw new Exception("ʱ���ַ���ת��ʧ��",e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * ͨ�����ջ�ȡ����
	 * @param pattern ʱ���ʽ
	 * @param birthday ����
	 * @return
	 */
	public static int getAge(String pattern, String birthday) throws Exception {
		int nowYear = Calendar.getInstance().get(Calendar.YEAR);
		Calendar birth =  Calendar.getInstance();
		birth.setTime(getDateFromString(pattern, birthday));
		int birthYear = birth.get(Calendar.YEAR);
		return nowYear - birthYear==0 ? 1 : nowYear - birthYear;
	}
	
	/**
	 * ��ȡ���µ����һ��
	 * @return String
	 */
	public static String getLastDay() {
		Calendar curCal = Calendar.getInstance();
        curCal.set(Calendar.DATE, 1);
        curCal.roll(Calendar.DATE, -1);
        Date endTime = curCal.getTime();
        return getDefalutDate(pattern, endTime);
	}
	
	/**
	 * ��ȡ���µĵ�һ��
	 * @return String
	 */
	public static String getFirstDay() {
		Calendar curCal = Calendar.getInstance();
        curCal.set(Calendar.DAY_OF_MONTH, 1);
        Date endTime = curCal.getTime();
        return getDefalutDate(pattern, endTime);
	}
	
	public String getPattern() {
		return pattern;
	}
	
	/**
	 * ��ȡ�������Сʱ��
	 * @param pattrn
	 * @param date	ʱ���ʽ(yyyy-MM-dd)
	 * @return
	 * @throws ParseException 
	 */
	public static Date getMinTime(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(date+" 00:00:01");
	}
	
	/**
	 * ��ȡ��������ʱ��
	 * @param pattrn
	 * @param date	ʱ���ʽ(yyyy-MM-dd)
	 * @return
	 * @throws ParseException 
	 */
	public static Date getMaxTime(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(date+" 23:59:59");
	}
	
	
	 private static int weeks = 0;
	  
	 private static int getMondayPlus() {
	        Calendar cd = Calendar.getInstance();
	        // ��ý�����һ�ܵĵڼ��죬�������ǵ�һ�죬����һ�ǵڶ���......
	        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == 1) {
	            return -6;
	        } else {
	            return 2 - dayOfWeek;
	        
	           }
	    }
	
	// ��ñ�������һ������
    public static String getCurrentMonday(){
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        return preMonday;
    }
}