package com.stylefeng.guns.modular.business.util;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.apache.ibatis.javassist.expr.NewArray;

/**
 * 日志处理工具
 * @author djb
 *
 */
public class CalendarUtil {
	/**
	 * 有双休，节假日的时间增加
	 * 根据开始日期 ，需要的工作日天数 ，放假天数，计算工作截止日期，并返回截止日期
	 * @param startDate 开始日期
	 * @param workDay 工作日天数(需要增加的天数，默认周末放假)
	 * @param holiday 放假日期，举例：key值格式为2017-10-1 value为国庆节
	 * @return Date类型
	 * @createTime 2017-8-22
	 * @author djb
	 */
	public static Date getWorkDay(Date startDate, int workDay,HashMap<String, String> holiday) {
	    Calendar c1 = Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    c1.setTime(startDate);

	    for (int i = 0; i < workDay; i++) {
	    	c1.add(Calendar.DAY_OF_MONTH, 1);
	        String compare=(df.format(c1.getTime()).split(" "))[0]; //取日期部分
	        
	        if (Calendar.SATURDAY == c1.get(Calendar.DAY_OF_WEEK) || Calendar.SUNDAY == c1.get(Calendar.DAY_OF_WEEK)||holiday.containsKey(compare)) {      
	        	System.out.println((holiday.get(compare)==null?"周末":holiday.get(compare))+"放假："+df.format(c1.getTime()) + " " + getWeekOfDate(c1.getTime()));
                i--;
	            continue;
	        } 
	    }

	    System.out.println("最终日期为："+df.format(c1.getTime()) + " " + getWeekOfDate(c1.getTime()));
	    return c1.getTime();
	}
	
	/**
	 * 无双休，节假日的时间增加
	 * 根据开始日期 ，需要的工作日天数 ，计算工作截止日期，并返回截止日期
	 * @param startDate 开始日期
	 * @param workDay 工作日天数(需要增加的天数，默认周末放假)
	 * @return Date类型
	 * @createTime 2017-8-22
	 * @author djb
	 */
	public static Date getDay(Date startDate, int workDay) {
	    Calendar c1 = Calendar.getInstance();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    c1.setTime(startDate);

	    for (int i = 0; i < workDay; i++) {
	    	c1.add(Calendar.DAY_OF_MONTH, 1); 
	    }

	    System.out.println("最终日期为："+df.format(c1.getTime()) + " " + getWeekOfDate(c1.getTime()));
	    return c1.getTime();
	}
	 
	/**
	 * 根据日期，获取星期几
	 * @param dt
	 * @return String类型
	 * @createTime 2017-8-22
	 * @author djb
	 */
	public static String getWeekOfDate(Date dt) {
	    String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(dt);
	    int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
	    if (w < 0) w = 0;
	    return weekDays[w];
	}
	
	/**
	 * 返回当前年份的后两位
	 * @param dt
	 * @return String类型
	 * @createTime 2017-8-22
	 * @author djb
	 */
	public static String getYear() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String date=(df.format(new Date()).split(" "))[0]; //取日期部分
	    String result=""+date.charAt(2)+date.charAt(3);
	    return result;
	}
	
}