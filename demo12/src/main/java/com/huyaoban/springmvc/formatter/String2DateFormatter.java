package com.huyaoban.springmvc.formatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

public class String2DateFormatter implements Formatter<Date> {
	private String datePattern;

	public String2DateFormatter(String datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public String print(Date date, Locale locale) {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		return sdf.format(date);
	}

	@Override
	public Date parse(String source, Locale locale) throws ParseException {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
			return sdf.parse(source);
		} catch (ParseException e) {
			throw new IllegalArgumentException(
					"Invalid date format. Please use this pattern"
							+ datePattern);
		}
	}

}
