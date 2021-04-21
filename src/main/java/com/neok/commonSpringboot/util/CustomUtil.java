package com.neok.commonSpringboot.util;

import java.util.Calendar;
import java.util.Date;

public class CustomUtil {
	public static Date dateAdd(Date curDate, int day) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(curDate);
        cal.add(Calendar.DATE, day);
		return cal.getTime();
	}
}
