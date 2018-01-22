package com.friday.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;


/*
 * 区分以前时间工具类
 */
public class DateUtil {

	public static final String FORMAT_YMD = "yyyy-MM-dd";

	public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");

    public static final SimpleDateFormat YMDHHMMSS = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 
    public static DateFormat departDateFormat = new SimpleDateFormat("yyyyMMdd");
    
    public static DateFormat departDateFormat2 = new SimpleDateFormat("yyyyMM");
    
    /**
     * 当前日期
     * 
     * @return
     */
    public static Date getCurDate() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

	/**
	 * 当前日期
	 *
	 * @return
	 */
	public static Date getPreDate() {
		Date date = getCurDate();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return cal.getTime();
	}

	/**
	 *时间戳转换为时间Date
	 * @return
	 */
	public static String dateLongToDate(long longDate){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long time = new Long(longDate);
		String d = format.format(time);
		return d;
	}
	/**
	 *时间戳转换为时间Date
	 * @return
	 */
	public static String dateLongToDate(long longDate,String patternStr){
		SimpleDateFormat format = new SimpleDateFormat(patternStr);
		Long time = new Long(longDate);
		String d = format.format(time);
		return d;
	}

	/** ** 字符串转时间 *** */

    /**
     * Date转换成字符串
     * 
     * @param patternStr
     *            yyyy-MM-dd
     * @param date
     *            Object
     * @return
     */
    public static String formatDate(String patternStr, Date date) {
        try {
            if (date == null) {
                date = getCurDate();
            }
            SimpleDateFormat sdf = new SimpleDateFormat(patternStr);
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    /** ** 时间转字符串 *** */

    /**
     * 将字符串转化为时间
     * 
     * @param patternStr
     *            yyyy-MM-dd
     * @param dateString
     *            2012-12-12
     * @return
     */
    public static Date parse(String patternStr, String dateString) {
        try {
            return new SimpleDateFormat(patternStr).parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    /**
	 * 将字符串转化为时间
	 */
	public static Date parse(String dateString) {
		try {
			if (dateString.length() <= 6) {
				return new SimpleDateFormat("yyyyMM").parse(dateString);
			} else if (dateString.indexOf("-") < 0) {
				return new SimpleDateFormat("yyyyMMdd").parse(dateString);
			} else if (dateString.length() == 7 && dateString.indexOf("-") > 0) {
				return new SimpleDateFormat("yyyy-MM").parse(dateString);

			} else {
				return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

    /**
     * 将日期转换00:00 或者23:59
     * @param Date
     * @param parseFlag 0,24
     * @return
     */
    public static Date parseStartOrEnd(Date date, String parseFlag) {
        try {
            if(date==null){
                date = getCurDate();
            }
            StringBuffer sbf = new StringBuffer();
            sbf.append(formatDate("yyyy-MM-dd", date));
            if ("0".equals(parseFlag)) {
                sbf.append(" 00:00:00");
            } else {
                sbf.append(" 23:59:59");
            }
            return parse("yyyy-MM-dd HH:mm:ss", sbf.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /** ** 时间比较 *** */

    /**
     * 比较时间
     * 
     * @param date
     * @param anotherDate
     * @return 小与-1 等于0 大于 1
     */
    public static int compareToDate(Date date, Date anotherDate) {
        if (date == null) {
            return -2;
        }
        if (anotherDate == null) {
            return -3;
        }
        return date.compareTo(anotherDate);
    }

    /** ** 时间运算 *** */

    /**
     * 返回指定时间前一个月的时间
     * 
     * @param Date
     *            date
     */
    public static Date parsePreMonthTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        return cal.getTime();
    }

    /**
     * 返回指定时间前三个月的时间
     * 
     * @param Date
     *            date
     */
    public static Date parsePre3MonthTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -3);
        return cal.getTime();
    }

	/**
	 * 年龄转天数
	 * @param year
	 * @return
	 */
	public static int parseYeartoDay(Integer year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -year);
		int day = daysBetween(new Date(), cal.getTime());
		return day;
	}

    /**
     * 返回当天日期加上或减去（-1）指定天数的日期
     * 
     * @param day
     * @return
     */
    public static Date getCurDateByDay(int day) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, day);
        return c.getTime();
    }

    /**
     * 返回当前日期指定分钟数
     * 
     * @param args
     */
    public static Date getCurDateByMM(Date curDate, int MM) {
        if (curDate == null) {
            curDate = getCurDate();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(curDate);
        c.add(Calendar.MINUTE, MM);
        return c.getTime();
    }

    /**
	 * 指定日期 加上天数后的日期
	 * @param day
	 * @param date
	 */
	public static Date addDayOnDate(Date date, int day) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, day);
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * 指定日期 加上分钟数后的日期
	 * @param min
	 * @param date
	 */
	public static Date addMinOnDate(Date date, Integer min) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, min);
			return calendar.getTime();
		}
		return null;
	}
	/**
	 * 指定日期 减去分钟数后的日期
	 * @param min
	 * @param date
	 */
	public static Date subtractMinOnDate(Date date, Integer min) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MINUTE, -min);
			return calendar.getTime();
		}
		return null;
	}
	
	/**
	 * 减去天数
	 * @param date
	 * @param day
	 * @return
	 */
	public static Date subtractDayOnDate(Date date, int day) {
		if(date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - day);
			return calendar.getTime();
		}
		return null;
	}

	/**
	 * 指定日期 加上时间后的日期
	 * 
	 * @param day
	 * @param date
	 */
	public static Date addTimeOnDate(Date date, int time) {
		if (date != null) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.HOUR, time);
			return calendar.getTime();
		}
		return null;
	}

	
	
	/**
	 * 获取两个日期之间的日期,包含开始和结束日期
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static List<String> amongDate(String startDate,String endDate){
		String dateFormat = "yyyy-MM-dd";
		SimpleDateFormat format = new SimpleDateFormat(dateFormat);	
		List<String> dates=new ArrayList<String>();
		dates.add(startDate);
		if(startDate.equals(endDate)){	
			//System.out.println("两个日期相等!");	
			return dates;	
		}				
		String tmp;		
		if(startDate.compareTo(endDate) > 0){  
			//确保 startDate的日期不晚于endDate	
			tmp = startDate; startDate = endDate; endDate = tmp;
		}
		
		try {
			tmp = format.format(format.parse(startDate).getTime() + 3600*24*1000);
			int num = 0;    
			while(tmp.compareTo(endDate) < 0){  
				//System.out.println(tmp); 
				dates.add(tmp);
				num++;       
				tmp = format.format(format.parse(tmp).getTime() + 3600*24*1000);     
			}
			dates.add(endDate);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return dates;
	}
	/**
	 * 获取日期星期
	 * @param date
	 * @return
	 */
	public static String parseWeek(Date date) {
		// final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
		// "星期六" };
		// final String dayNames[] = { "Sunday", "Monday", "Tuesday",
		// "Wednesday", "Thursday", "Friday", "Saturday" };
		// final String dayNames[] = { "SUN.", "MON.", "TUES.", "WED.",
		// "THURS.", "FRI.", "SAT." };
		final String dayNames[] = { "SUN", "MON", "TUES", "WED", "THURS", "FRI", "SAT" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayNames[dayOfWeek - 1];
	}
	/** 两个日期之间的相隔天数 */
	public static int daysBetween(Date data1, Date data2) {
		Calendar cNow = Calendar.getInstance();
		Calendar cReturnDate = Calendar.getInstance();
		cNow.getTimeInMillis();
		if (data1.after(data2)) {
			cNow.setTime(data1);
			cReturnDate.setTime(data2);
		} else {
			cNow.setTime(data2);
			cReturnDate.setTime(data1);
		}

		setTimeToMidnight(cNow);
		setTimeToMidnight(cReturnDate);
		long todayMs = cNow.getTimeInMillis();
		long returnMs = cReturnDate.getTimeInMillis();
		long intervalMs = todayMs - returnMs;
		return millisecondsToDays(intervalMs);
	}
	/** 两个日期之间的相隔天数 */
	public static int daysOfTwo(Date fDate, Date oDate) {

		Calendar aCalendar = Calendar.getInstance();

		aCalendar.setTime(fDate);

		int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);

		aCalendar.setTime(oDate);

		int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);

		return day2 - day1;

	}
	
	private static void setTimeToMidnight(Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
	}
	
	private static int millisecondsToDays(long intervalMs) {
		return (int) (intervalMs / (1000 * 86400));
	}
    /**
     * 将 util.date转换为 sql.date
     */
	public static Date dateToDate(Date date) {  
		
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		String str = df.format(date);
		date = java.sql.Date.valueOf(str);  
	    return date; 
    } 
	

    /**
     * 将字符串日期时间格式转换成Date
     * @param date            yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date formatStringToDate(String date) {
    	Date result = null;
    	try {
    		result = YMDHHMMSS.parse(date);
    	} catch (ParseException e) {
    		e.printStackTrace();
    	}
    	return result;
    }
	
    /**
     * 两个时间相差分钟数
     * 
     * @return
     */
    public static Long subMinute(Date max, Date min) {
        Long sub = max.getTime() - min.getTime();
        return (sub / 60000);
    }
    /**字符串转date*/
   	public static Date formatStringToDate(String date,String partten) {
       	Date result = null;
       	try {
       		result = new SimpleDateFormat(partten).parse(date);
       	} catch (ParseException e) {
       		e.printStackTrace();
       	}
       	return result;
      }
   	public static boolean subMinutes(Date max,Date min){
		Calendar calMax=Calendar.getInstance();//使用日历类
		calMax.setTime(max);
		int maxHour = calMax.get(calMax.HOUR_OF_DAY);//获得时
		int maxMinute = calMax.get(calMax.MINUTE);//获得分
		
		Calendar calMin = Calendar.getInstance();
		calMin.setTime(min);
		int minHour = calMin.get(calMin.HOUR_OF_DAY);
		int minMinute = calMin.get(calMin.MINUTE);
		
        if(maxHour>minHour){
        	return true;
        }else{
        	if(maxHour==minHour){
        		if(maxMinute>minMinute){
        			return true;
        		}
        		else{
        			return false;
        		}
        	}else{
        		return false;
        	}
        }
	}
   	public static boolean isSameDay(Date day1, Date day2) {
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    String ds1 = sdf.format(day1);
	    String ds2 = sdf.format(day2);
	    if (ds1.equals(ds2)) {
	        return true;
	    } else {
	        return false;
	    }
	}
   	
   	/**计算两个日期的间隔小时数*/
   	public static Long subHour(String beginDate, String endDate) {
   		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   		Date begin = null;
   		Date end = null;
   		try {
   			begin = dfs.parse(beginDate);
   			end = dfs.parse(endDate);
   		} catch (ParseException e) {
   			e.printStackTrace();
   		}

   		long between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
   		long hour1 = between % (24 * 3600) / 3600;
   		return hour1;
   	}
   	
   	public static Date parseDateTime(String datestr, String timeStr) {
        Calendar date = Calendar.getInstance();
        date.setTime(dateParse(datestr));
        final int time = Integer.parseInt(timeStr);
        date.set(Calendar.HOUR_OF_DAY, time / 100);
        date.set(Calendar.MINUTE, time % 100);
        date.set(Calendar.MINUTE, 31);
        return date.getTime();
    }
    public static Date dateParse(String dayString) {
        try {
            if (dayString.length() > 5) {
                return new SimpleDateFormat("ddMMMyy", Locale.ENGLISH).parse(dayString);
            } else {
                final String str = new StringBuffer(dayString).append(
                        String.valueOf(Calendar.getInstance().get(Calendar.YEAR)).substring(2)).toString();
                return new SimpleDateFormat("ddMMMyy", Locale.ENGLISH).parse(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

   	/**获取两个日期的全部月份*/
   	public static List<String> getMonthBetween(String minDate, String maxDate) {
   	    ArrayList<String> result = new ArrayList<String>();
   	    try{
   	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月

    	    Calendar min = Calendar.getInstance();
    	    Calendar max = Calendar.getInstance();

    	    min.setTime(sdf.parse(minDate));
    	    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

    	    max.setTime(sdf.parse(maxDate));
    	    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

    	    Calendar curr = min;
    	    while (curr.before(max)) {
    	     result.add(sdf.format(curr.getTime()));
    	     curr.add(Calendar.MONTH, 1);
    	    }
   	    }catch(Exception e){
   	    	e.printStackTrace();
   	    }
   	    return result;
   	  }
   	
    /**
     * 返回指定时间前 N 个月的时间
     * 
     * @param Date
     *            date
     */
    public static Date MonthComputeTime(Date date,int num) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, num);
        return cal.getTime();
    }
   	
	 /**
     * 取得当前日期所在周的第一天日期
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return addDayOnDate(calendar.getTime(),1);
    }
    /**
     * 取得当前日期所在周的最后一天日期
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_WEEK,
                      calendar.getFirstDayOfWeek()); // Sunday
        return addDayOnDate(calendar.getTime(),7);
    }

	/**
	 * 判断字符串是否为日期格式，格式以type变量为准，如type="-"，则为yyyy-MM-dd的格式
	 * @param str
	 * @param type
	 * @return
	 */
	public static boolean isDateFormat(String str,String type) {
		boolean b = false ;
		if(StringUtil.isNull(str)) return b ;
		b = Pattern.matches("[1-9][0-9]{3}"+type+"(0[1-9]|1[0-2])"+type+"(0[1-9]|[1-2][0-9]|3[0-1])", str.toLowerCase());
		return b ;
	}
	
	public static boolean isDateTimeFormat(String str) {
		try{  
            Date date = (Date)YMDHHMMSS.parse(str);  
            return str.equals(YMDHHMMSS.format(date));  
        }catch(Exception e){  
            return false;  
        }  
	}
	
	public static String getStringDate(){
		SimpleDateFormat myFmt=new SimpleDateFormat("yyMMddHHmmss");
		return myFmt.format(new Date());
	}

	public static long getBegintime(long datetime,int dates){
		if(datetime == 0l){
			datetime = getCurDate().getTime();
		}
		Date date = DateUtil.parseStartOrEnd(new Date(datetime), "0");
		return DateUtil.subtractDayOnDate(date, dates).getTime();
	}
	
	public static Date strToDate(String strDate,String partten){
		SimpleDateFormat sdf = new SimpleDateFormat(partten);
		Date date=null;
		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取当日零点的时间戳
	 * @return
     */
	public static Long getTodayZeroHour(){
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 001);
		return cal.getTimeInMillis();
	}
	public static void main(String[] args){
		System.out.println(parseYeartoDay(28));
		/*System.out.println(DateUtil.daysBetween(DateUtil.parse("yyyy-MM-dd", "2017-08-22"),new Date()));
		System.out.println(DateUtil.daysOfTwo(DateUtil.parse("yyyy-MM-dd", "2017-08-22"),new Date()));*/

//		System.out.println(subtractDayOnDate(new Date(),5));
		//System.out.println(subHour("2015-08-26 13:00:00","2015-08-26 16:00:00"));
		//销售时限问题
//		int salesDay = 1;
//		String salesTime = "12:00:00";
//		Date datr=DateUtil.parse("yyyy-MM-dd HH:mm:ss","2016-11-01 00:00:00") ;
//		Date compareDate = DateUtil.subtractDayOnDate(datr,salesDay);
//		compareDate = DateUtil.parse("yyyy-MM-dd HH:mm:ss", DateUtil.formatDate("yyyy-MM-dd", compareDate)+" "+salesTime);
//		System.out.println( DateUtil.compareToDate(compareDate, new Date()));
//		1480315632219(Long), 1480920432219(Long)
//		Long finishtime = DateUtil.parseStartOrEnd(new Date(System.currentTimeMillis()),"").getTime();//默认完成时间是系统当前时间
//		Long createtime = DateUtil.getBegintime(finishtime,7);//系统当前时间往后一周时间
//		System.out.println(DateUtil.formatDate(DateUtil.FORMAT_YMDHMS,new Date(finishtime)));
//		System.out.println(DateUtil.formatDate(DateUtil.FORMAT_YMDHMS,new Date(createtime)));
		/*try {
			System.out.println(YMDHHMMSS.parse("2017-05-13 15:30:30").getTime()+"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(DateUtil.strToDate("0000-00-00", "yyyy-MM-dd"));
	*/
	}

	//算出当前年龄
	public static  int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}

	/**
	 *	字符串转时间戳
	 * @param dateTimeStr
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static Long parseTime(String dateTimeStr ,String pattern) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date d=sdf.parse(dateTimeStr);
		return d.getTime();
	}

	/**
	 *
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int differentDaysByMillisecond(Date date1,Date date2)
	{
		int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
		return days;
	}


//	//获取昨天的开始时间
//	public static String getBeginDayOfYesterday() {
//		Calendar cal = new GregorianCalendar();
//		cal.setTime(getDayBegin());
//		cal.add(Calendar.DAY_OF_MONTH, -1);
//		return com.ykly.common.utils.DateUtil.DatetoString(cal.getTime());
//	}
//	//获取昨天的结束时间
//	public static String getEndDayOfYesterDay() {
//		Calendar cal = new GregorianCalendar();
//		cal.setTime(getDayEnd());
//		cal.add(Calendar.DAY_OF_MONTH, -1);
//		return com.ykly.common.utils.DateUtil.DatetoString(cal.getTime());
//	}
	//获取当天的开始时间
	public static Date getDayBegin() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	//获取当天的结束时间
	public static Date getDayEnd() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

}
