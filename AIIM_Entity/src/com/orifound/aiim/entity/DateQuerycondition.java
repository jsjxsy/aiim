package com.orifound.aiim.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;




/**
 * �����ڶ���Ϊ��ѯ����
 * @author Administrator
 *
 */
public class DateQuerycondition {
	//��ȡ���ڸ�ʽΪyyyy-MM-dd ��������һ
	private Date beginTime = getCurrentMonday() ;
	
	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	//��ȡ���ڸ�ʽΪyyyy-MM-dd ����
	private Date endTime =  getDateFormat(new Date());

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	private static int weeks = 0;
	  
	 private  int getMondayPlus() {
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
    private  Date getCurrentMonday(){
        weeks = 0;
        int mondayPlus = getMondayPlus();
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.add(GregorianCalendar.DATE, mondayPlus);
        Date monday = currentDate.getTime();
        DateFormat df = DateFormat.getDateInstance();
        String preMonday = df.format(monday);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
        Date mon= null;
		try {
			mon = sdf.parse(preMonday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return mon;
    }
	
	private Date getDateFormat(Date date){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");
		String date2 = dateFormat.format(date);
		
		date2=date2.substring(0, 9)+" 23:59:59";
		Date today = null;
		try {
			today = dateFormat.parse(date2);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return today;
	}
}
