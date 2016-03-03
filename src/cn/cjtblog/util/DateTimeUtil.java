package cn.cjtblog.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;
import java.util.Date;

import cn.cjtblog.exception.DateTimeFormatException;

public class DateTimeUtil {
	private static final String DEFAULTDATETIMEFORMAT="yyyy/MM/dd HH:mm:ss:SSS";
	public static Date format(String dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULTDATETIMEFORMAT);
		try {
			Date d=sdf.parse(dateTime);
			return d;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			throw new DateTimeFormatException(e);
		}
	}

}
