package com.alokit.participate.core.util;

import java.util.Date;

public class DateUtil {
	public static Date offsetSecond(Date date, int offset) {
		 date.setSeconds(date.getSeconds() + offset);
		 return date;
	}
}
