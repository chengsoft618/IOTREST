package cn.cjtblog.exception;

import java.text.ParseException;

public class DateTimeFormatException extends RuntimeException {

	public DateTimeFormatException(ParseException e) {
		super(e);
	}

}
