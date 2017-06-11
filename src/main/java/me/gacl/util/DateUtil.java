package me.gacl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String year2second = "yyyy-MM-dd HH:mm:ss";
	
	public static final String year2day = "yyyy-MM-dd";
	
	
	public static String date2String(String format,Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String createDate = sdf.format(date);
		return createDate;
	}
	
	public static Date string2Date(String type,String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat(type);
		return sdf.parse(date);
	}
}
