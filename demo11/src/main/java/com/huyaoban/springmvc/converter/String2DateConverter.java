package com.huyaoban.springmvc.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

public class String2DateConverter implements Converter<String, Date> {
	private String datePattern;

	public String2DateConverter(String datePattern) {
		this.datePattern = datePattern;
	}

	@Override
	public Date convert(String source) {
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
