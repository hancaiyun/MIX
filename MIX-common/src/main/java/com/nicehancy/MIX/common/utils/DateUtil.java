package com.nicehancy.MIX.common.utils;

import com.nicehancy.MIX.common.constant.DatePatternConstant;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具类
 * <p>
 * 1、获取当前日期
 * 2、获取当前时间 格式： yyyyMMddHHmmss
 * 3、获取当前时间 格式： 自定义
 * 4、将字符串转换成固定格式时间
 * 5、将字符串转换成固定格式时间
 * 6、Date类型转换String 格式：yyyyMMdd
 * 7、Date类型转换String 格式：自定义
 * 8、增加时间
 * 9、按秒偏移,根据{@code source}得到{@code seconds}秒之后的日期<Br>
 * 10、根据传入的时分秒毫秒获取固定的当日时间点
 * </p>
 */
public class DateUtil {
    /**
     * 日志记录器
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private DateUtil() {
    }

    /**
     * 获取当前日期
     *
     * @return 当前日期
     */
    public static Date getCurrentDate() {
        return DateTime.now().toDate();
    }

    /**
     * 获取当前时间 格式： yyyyMMddHHmmss
     *
     * @return 字符日期 格式：yyyyMMddHHmmss
     */
    public static String getCurrent() {
        return getCurrent(DatePatternConstant.FULL_PATTERN);
    }

    /**
     * 获取当前时间 格式： 自定义
     *
     * @param pattern 时间格式
     * @return 自定义格式的当前时间
     */
    public static String getCurrent(String pattern) {
        return DateTime.now().toString(pattern);
    }

    /**
     * 将字符串转换成固定格式时间
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return 转换后日期
     */
    public static Date parse(String date, String pattern) {

        DateTime dateTime = parseTime(date, pattern);
        if (dateTime == null) {
            return null;
        }
        return dateTime.toDate();
    }

    /**
     * 将字符串转换成固定格式时间
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return 转换后日期
     */
    public static DateTime parseTime(String date, String pattern) {
        return DateTimeFormat.forPattern(pattern).parseDateTime(date);
    }

    /**
     * Date类型转换String 格式：yyyyMMdd
     *
     * @param date 日期
     * @return String类型
     */
    public static String defaultFormat(Date date) {

        if (date == null) {
            return null;
        }
        return new DateTime(date).toString(DatePatternConstant.DATE_PATTERN);
    }

    /**
     * Date类型转换String 格式：自定义
     *
     * @param date    日期
     * @param pattern 自定义格式
     * @return String类型
     */
    public static String format(Date date, String pattern) {

        if (date == null) {
            return null;
        }
        return new DateTime(date).toString(pattern);
    }


    /**
     * 获取当前时间
     *
     * @return Date
     */
    public static Date getCurrentDate(String pattern) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime().toString(pattern), format).toDate();
    }

    /**
     * 根据 pattern 将 dateTime 时间进行格式化
     * 用来去除时分秒，具体根据结果以 pattern 为准
     *
     * @param date payDate 时间
     * @return payDate 时间
     */
    public static Date formatToDate(Date date, String pattern) {

        if (date == null) {
            return null;
        }
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        return DateTime.parse(new DateTime(date).toString(pattern), format).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(int dayNum) {
        return new DateTime().plusDays(dayNum).toDate();
    }

    /**
     * 日期增减，负数为减
     *
     * @param dayNum 天数
     * @return 时间
     */
    public static Date plusDays(Date date, int dayNum) {
        return new DateTime(date).plusDays(dayNum).toDate();
    }

    /**
     * 月份增减，负数为减
     *
     * @param monthsNum 天数
     * @return 时间
     */
    public static Date plusMonths(Date date, int monthsNum) {
        return new DateTime(date).plusMonths(monthsNum).toDate();
    }

    /**
     * 增加时间
     *
     * @param date          时间
     * @param calendarField 时间格式
     * @param amount        间隔
     * @return 日期
     */
    public static Date addDate(Date date, int calendarField, int amount) {

        if (date == null) {
            throw new IllegalArgumentException("The date could not be null!");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);

        return c.getTime();
    }

    /**
     * 按秒偏移,根据{@code source}得到{@code seconds}秒之后的日期<Br>
     *
     * @param source  , 要求非空
     * @param seconds , 秒数,可以为负
     * @return 新创建的Date对象
     */
    public static Date addSeconds(Date source, int seconds) {
        return addDate(source, Calendar.SECOND, seconds);
    }

    /**
     * 根据传入的时分秒毫秒获取固定的当日时间点
     *
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param millisecond 毫秒
     * @return 时间点
     */
    public static Calendar getCurrentCalendar(int hour, int minute, int second, int millisecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }

    /**
     * 根据传入的时分秒毫秒获取指定日期的时间点
     *
     * @param date        日期
     * @param hour        小时
     * @param minute      分钟
     * @param second      秒
     * @param millisecond 毫秒
     * @return 时间点
     */
    public static Calendar getDateCalendar(Date date, int hour, int minute, int second, int millisecond) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        calendar.set(Calendar.MILLISECOND, millisecond);
        return calendar;
    }

    /**
     * 计算当月的最后一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static String getMonthEndDay(Date date) {
        DateTime dateTime = getDateTime(date);
        return String.valueOf(getDateTime(getMonthEnd(dateTime)).dayOfMonth().get());
    }

    /**
     * 计算当月的第一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static String getMonthBeg(Date date) {
        return String.valueOf(getDateTime(getMonthBegDay(date)).dayOfMonth().get());
    }

    /**
     * 计算当月的第一天
     *
     * @param date 日期
     * @return 当月最后一天
     */
    public static Date getMonthBegDay(Date date) {
        DateTime dateTime = getDateTime(date);
        return dateTime.plusDays(1 - dateTime.getDayOfMonth()).toDate();
    }

    /**
     * 计算当月的最后一天
     *
     * @param dateTime 日期
     * @return 日期对象
     */
    public static Date getMonthEnd(DateTime dateTime) {
        return dateTime.plusMonths(1).plusDays(-dateTime.plusMonths(1).getDayOfMonth()).toDate();
    }

    /**
     * 判断是否是每月的第一天
     *
     * @param dateTime 日期
     * @return 日期对象
     */
    public static boolean checkMonthFirstDate(DateTime dateTime) {
        return 1 == dateTime.getDayOfMonth();
    }

    /**
     * 获取DateTime日期对象
     *
     * @param date 日期
     * @return DateTime对象
     */
    public static DateTime getDateTime(Date date) {
        return DateUtil.parseTime(DateUtil.format(date,
                DatePatternConstant.DATE_PATTERN), DatePatternConstant.DATE_PATTERN);
    }

    /**
     * 返回当月最后一天的日期
     *
     * @param date 日期
     * @return 返回当月最后一天的日期
     */
    public static Date getLastDayOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return calendar.getTime();
    }

    /**
     * 两个时间之间相差距离多少天
     *
     * @param date1 时间参数 1
     * @param date2 时间参数 2
     * @return 相差天数
     */
    public static long getDistanceDays(Date date1, Date date2) {
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff;
        if (time1 < time2) {
            diff = time2 - time1;
        } else {
            diff = time1 - time2;
        }
        return diff / (1000 * 60 * 60 * 24);
    }

    /**
     * 返回日期的天数，1-31,即yyyyMMdd中的dd
     *
     * @param date 日期
     * @return 天数
     */
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DATE);
    }

    /**
     * 返回日期的月份，1-12,即yyyyMMdd中的MM
     *
     * @param date 日期
     * @return 月份
     */
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 返回日期的年,即yyyyMMdd中的yyyy
     *
     * @param date 日期
     * @return 年份
     */
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 返回上个月的最后一天
     *
     * @return 年份
     */
    public static String getLastFormat() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DatePatternConstant.DATE_PATTERN);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    /**
     * 返回上个月的第一天
     *
     * @return 年份
     */
    public static String getLastDay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat(DatePatternConstant.DATE_PATTERN);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        return format.format(calendar.getTime());
    }

    /**
     * 获取两个时间之间相差多少个月
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 相差的月数
     */
    public static int getDistanceMonths(Date startDate, Date endDate) {
        int result;

        int startYear = getYear(startDate);
        int startMonth = getMonth(startDate);
        int endYear = getYear(endDate);
        int endMonth = getMonth(endDate);
        if (startYear == endYear) {
            result = endMonth - startMonth;
        } else {
            result = (endYear - startYear) * 12 + endMonth - startMonth;
        }

        return result;
    }

    /**
     * yyyy-MM-dd HHH24:mi:ss 转换 yyyyMMdd格式
     *
     * @param date
     * @return
     */
    public static Date getStandardPatternDate(String date) {

        Date date2 = DateUtil.parse(date, DatePatternConstant.STANDARD_PATTERN);
        return DateUtil.formatToDate(date2, DatePatternConstant.DATE_PATTERN);
    }

    /**
     * yyyy/MM/dd转成 yyyy-MM-dd HHH24:mi:ss 格式
     *
     * @param date
     * @return
     */
    public static Date convert2StandardPatternDate(String date) {
        Date date2 = DateUtil.parse(date, DatePatternConstant.SLASH_PATTERN);
        return DateUtil.formatToDate(date2, DatePatternConstant.STANDARD_PATTERN);
    }

    /**
     * 校验字符串是否是合法的日期格式
     *
     * @param dateString
     * @param dateFormat
     * @return
     */
    public static boolean verifyDateFormat(String dateString, String dateFormat) {

        try {
            DateFormat formatter = new SimpleDateFormat(dateFormat);
            Date date = formatter.parse(dateString);
            return dateString.equals(formatter.format(date));
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式的数字转换成yyyy-MM-dd 00:00:00
     * @param dateTime
     * @return
     */
    public static final String convertTo0AM(String dateTime){

        if(dateTime == null){
            return null;
        }
        return dateTime.substring(0,11)+"00:00:00";
    }

    /**
     * yyyy-MM-dd HH:mm:ss格式的数字转换成yyyy-MM-dd 00:00:00
     * @param dateTime
     * @return
     */
    public static final String convertTo59AM(String dateTime){

        if(dateTime == null){
            return null;
        }
        return dateTime.substring(0,11)+"23:59:59";
    }
}