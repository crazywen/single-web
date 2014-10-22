/*
 * Copyright (c) 2010 by XUANWU INFORMATION TECHNOLOGY CO.
 *             All rights reserved
 */
package com.crazy.singleweb.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil {

	/**
	 * Number of hours in a standard day.
	 */
	public static final int HOURS_PER_DAY = 24;
	/**
	 * Number of milliseconds in a standard second.
	 */
	public static final long MILLIS_PER_SECOND = 1000;
	/**
	 * Number of milliseconds in a standard minute.
	 */
	public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;
	/**
	 * Number of milliseconds in a standard hour.
	 */
	public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;
	/**
	 * Number of milliseconds in a standard day.
	 */
	public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;

	public enum DateTimeType {
		DateTime(0), // 日期时间
		Date(1), // 日期
		Time(2), // 时间
		Month(3), // 年月
		ExcelDefaultDate(4), // Excel时间格式
		CnTxtDefaultDate(5);// 中文时间格式

		private int index;

		private DateTimeType(int index) {
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		public static DateTimeType getType(int index) {
			switch (index) {
			case 1:
				return Date;
			case 2:
				return Time;
			case 3:
				return Month;
			case 4:
				return ExcelDefaultDate;
			case 5:
				return CnTxtDefaultDate;
			default:
				return DateTime;
			}
		}
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = LoggerFactory
			.getLogger(DateUtil.class);

	private static final SimpleDateFormat sdfDate = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static final SimpleDateFormat sdfTime = new SimpleDateFormat(
			"HH:mm:ss");

	private static final SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static final SimpleDateFormat sdfMonth = new SimpleDateFormat(
			"yyyy-MM");

	private static final SimpleDateFormat sdfExcelDefaultDate = new SimpleDateFormat(
			"yyyy/MM/dd");

	private static final SimpleDateFormat sdfCnTxtDefaultDate = new SimpleDateFormat(
			"yyyy年MM月dd日");

	public static String format(Date date, DateTimeType type) {
		if (date == null) {
			return "";
		}
		switch (type) {
		case Date:
			return sdfDate.format(date);
		case Time:
			return sdfTime.format(date);
		case Month:
			return sdfMonth.format(date);
		case ExcelDefaultDate:
			return sdfExcelDefaultDate.format(date);
		case CnTxtDefaultDate:
			return sdfCnTxtDefaultDate.format(date);
		default:
			return sdf.format(date);
		}
	}

	public static Date parse(String str, DateTimeType type) {
		if (StringUtils.isBlank(str))
			return null;
		try {
			switch (type) {
			case Date:
				return sdfDate.parse(str);
			case Time:
				return sdfTime.parse(str);
			case ExcelDefaultDate:
				return sdfExcelDefaultDate.parse(str);
			case CnTxtDefaultDate:
				return sdfCnTxtDefaultDate.parse(str);
			default:
				return sdf.parse(str);
			}
		} catch (ParseException e) {
			logger.error("Parse date error:" + str);
			return null;
			// throw new RuntimeException("Parse date error.", e);
		}
	}

	public static Date tranDate(long time) {
		if (time == 0)
			return null;
		return new Date(time);
	}

	public static long parseDate(Date time) {
		return time == null ? 0 : time.getTime();
	}

	public static void sleepWithoutInterrupte(long time) {
		while (true) {
			try {
				Thread.sleep(time);
				break;
			} catch (InterruptedException e) {
				logger.error("Interrupted when sleep! this will be ignored!", e);
			}
		}
	}

	public static void sleepNanoWithoutInterrupte(long nanoseconds) {
		while (true) {
			try {
				TimeUnit.NANOSECONDS.sleep(nanoseconds);
				break;
			} catch (InterruptedException e) {
				logger.error("Interrupted when sleep! this will be ignored!", e);
			}
		}
	}

	public static String getCurrentDate() {
		return sdf.format(new Date());
	}

	public static String getCurrentDayBegin() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return sdf.format(begin.getTime());
	}

	public static Date getCurDayBeginDate() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return begin.getTime();
	}

	public static String getCurrentDayEnd() {
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		return sdf.format(end.getTime());
	}

	public static Date getCurDayEndDate() {
		Calendar end = Calendar.getInstance();
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		return end.getTime();
	}

	public static int getCurMonth() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.MONTH) + 1;
	}

	public static String getRealCurMonthFirstDay() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, 1);
		return sdfDate.format(begin.getTime());
	}

	public static String getRealCurMonthFirstDayTime() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return sdf.format(begin.getTime());
	}

	public static String getCurMonthFirstDay() {
		Calendar begin = Calendar.getInstance();
		if (begin.get(Calendar.DAY_OF_MONTH) == 1) {
			begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
		}
		begin.set(Calendar.DAY_OF_MONTH, 1);
		return sdfDate.format(begin.getTime());
	}

	public static String getCurMonthFirstDayTime() {
		Calendar begin = Calendar.getInstance();
		if (begin.get(Calendar.DAY_OF_MONTH) == 1) {
			begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
		}
		begin.set(Calendar.DAY_OF_MONTH, 1);
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return sdf.format(begin.getTime());
	}

	public static String getCurPreviousDay() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.DAY_OF_MONTH, begin.get(Calendar.DAY_OF_MONTH) - 1);
		return sdfDate.format(begin.getTime());
	}

	public static Date parseDate(String date) {
		try {
			if (StringUtils.isEmpty(date)) {
				return null;
			}
			return sdfDate.parse(date);
		} catch (ParseException e) {
			logger.error("Parse date error:" + date);
			return null;
		}
	}

	public static Date parseMonth(String date) {
		try {
			if (StringUtils.isEmpty(date)) {
				return null;
			}
			return sdfMonth.parse(date);
		} catch (ParseException e) {
			logger.error("Parse date error:" + date);
			return null;
		}
	}

	public static String getCurYearFirstMonth() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.MONTH, 0);
		return sdfMonth.format(begin.getTime());
	}

	public static String getCurPreviousMonth() {
		Calendar begin = Calendar.getInstance();
		int month = begin.get(Calendar.MONTH);
		if (month > 1)
			begin.set(Calendar.MONTH, begin.get(Calendar.MONTH) - 1);
		return sdfMonth.format(begin.getTime());
	}

	public static String getCurYearMonth() {
		Calendar begin = Calendar.getInstance();
		return sdfMonth.format(begin.getTime());
	}

	public static String getDayBeginTime() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 0);
		begin.set(Calendar.MINUTE, 0);
		begin.set(Calendar.SECOND, 0);
		return sdfTime.format(begin.getTime());
	}

	public static String getDayEndTime() {
		Calendar begin = Calendar.getInstance();
		begin.set(Calendar.HOUR_OF_DAY, 23);
		begin.set(Calendar.MINUTE, 59);
		begin.set(Calendar.SECOND, 59);
		return sdfTime.format(begin.getTime());
	}

	public static String toMonthFirstDay(String month) {
		return month + "-01";
	}

	public static String toMonthLastDay(String month) {
		try {
			Date d = sdfMonth.parse(month);
			Calendar cal = Calendar.getInstance();
			cal.setTime(d);
			cal.add(Calendar.MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			return sdfDate.format(cal.getTime());
		} catch (ParseException e) {
			logger.error("Parse date error:" + month);
			return null;
		}
	}

	public static int getDays(String beginDate, String endDate) {
		try {
			Date d1 = sdfDate.parse(beginDate);
			Date d2 = sdfDate.parse(endDate);
			return (int) ((d2.getTime() - d1.getTime()) / (3600L * 1000 * 24)) + 1;
		} catch (ParseException e) {
			logger.error("Parse date error:" + beginDate + " / " + endDate);
			return 0;
		}
	}

	public static String getCurDay() {
		return sdfDate.format(new Date());
	}

	public static int getMonths(String beginDate, String endDate) {
		try {
			Date d1 = sdfMonth.parse(beginDate);
			Date d2 = sdfMonth.parse(endDate);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(d1);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(d2);

			int beginDateYear = c1.get(Calendar.YEAR);
			int beginDateMonth = c1.get(Calendar.MONTH);
			int endDateYear = c2.get(Calendar.YEAR);
			int endDateMonth = c2.get(Calendar.MONTH);
			if (beginDateYear == endDateYear) {
				return endDateMonth - beginDateMonth + 1;
			} else {
				return (endDateYear - beginDateYear) * 12
						+ (endDateMonth - beginDateMonth) + 1;
			}
		} catch (ParseException e) {
			logger.error("Parse date error:" + beginDate + " / " + endDate);
			return 0;
		}
	}

	/**
	 * 取得几天前的时间
	 */
	public static Date getAfterDate(Integer day) {
		Date date = new Date();
		if (day == null) {
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		date = cal.getTime();
		return date;
	}

	/**
	 * 取得几天前的时间
	 */
	public static Date getPrefixStartDate(Integer day) {
		Date date = new Date();
		if (day == null) {
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, -day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		date = cal.getTime();
		return date;
	}

	/**
	 * 取得几天前的时间
	 */
	public static Date getAfterEndDate(Integer day) {
		Date date = new Date();
		if (day == null) {
			return date;
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		date = cal.getTime();
		return date;
	}

	/**
	 * 获得两个时间之间的天数，不计算时分秒
	 */
	public static int getDays(Date beginDate, Date endDate) {
		if (beginDate == null || endDate == null)
			return 0;
		beginDate = parse(format(beginDate, DateTimeType.Date),
				DateTimeType.Date);
		endDate = parse(format(endDate, DateTimeType.Date), DateTimeType.Date);
		return (int) ((endDate.getTime() - beginDate.getTime()) / (3600 * 1000 * 24));
	}
}
