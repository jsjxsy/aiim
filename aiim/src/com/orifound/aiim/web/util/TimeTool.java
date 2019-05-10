package com.orifound.aiim.web.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * <p>Title: TimeTool 时间工具类  </p>
 * <p>Description: 提供对时间的转换以及其他操作</p>
 */

public class TimeTool {
	
	private static String pattern = "yyyy-MM-dd";
	
	private TimeTool() {
	}
	
	/**
	 * 返回当前时间字符串
	 * <p>默认格式(yyyy-MM-dd)</p>
	 * @return String
	 */
	public static String getDefalutDate() {
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	
	/**
	 * 返回时间指定格式字符串
	 * <p>默认格式(yyyy-MM-dd)</p>
	 * @param pattern		非默认时间格式
	 * @param dateString	时间
	 * @return String 	时间字符串
	 */
	public static String getDefalutDate(String pattern, Date date) {
		return (new SimpleDateFormat(pattern)).format(date);
	}
	
	/**
	 * 根据时间字符串返回默认格式时间字符串
	 * <p>默认格式(yyyy-MM-dd)</p>
	 * @param pattern		非默认时间格式
	 * @param dateString	时间是字符串
	 * @return String 	时间字符串
	 */
	public static String getDefalutDate(String pattern, String dateString) {
		return (new SimpleDateFormat(pattern)).format(getDateFromString(pattern, dateString));
	}
	
	/**
	 * 返回指定格式的当前时间字符串
	 * @param pattern 时间格式
	 * @return String
	 */
	public static String displayDateTime(String pattern) {
		return (new SimpleDateFormat(pattern)).format(new Date());
	}
	
	/**
	 * 默认格式(yyyy-MM-dd)时间字符串转换为Date类型
	 * @param strDate 时间字符串表示
	 * @return Date
	 * @throws Exception
	 */
	public static Date getDateFromString(String strDate)  {
		try {
			return (new SimpleDateFormat(pattern)).parse(strDate);
		} catch (ParseException e) {
			try {
				throw new Exception("时间字符串转换失败",e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 时间字符串转换为Date类型
	 * @param pattern 时间格式
	 * @param strDate 时间字符串表示
	 * @return Date
	 * @throws Exception
	 */
	public static Date getDateFromString(String pattern, String strDate) {
		try {
			return (new SimpleDateFormat(pattern)).parse(strDate);
		} catch (ParseException e) {
			try {
				throw new Exception("时间字符串转换失败",e);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 通过生日获取年龄
	 * @param pattern 时间格式
	 * @param birthday 生日
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
	 * 获取当月的最后一天
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
	 * 获取当月的第一天
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
	 * 获取当天的最小时间
	 * @param pattrn
	 * @param date	时间格式(yyyy-MM-dd)
	 * @return
	 * @throws ParseException 
	 */
	public static Date getMinTime(String date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.parse(date+" 00:00:01");
	}
	
	/**
	 * 获取当天的最大时间
	 * @param pattrn
	 * @param date	时间格式(yyyy-MM-dd)
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
	        // 获得今天是一周的第几天，星期日是第一天，星期一是第二天......
	        int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
	        if (dayOfWeek == 1) {
	            return -6;
	        } else {
	            return 2 - dayOfWeek;
	        
	           }
	    }
	
	// 获得本周星期一的日期
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