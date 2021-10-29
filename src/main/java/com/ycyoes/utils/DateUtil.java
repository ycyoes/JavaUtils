package com.ycyoes.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * 日期工具类
 * @author ycyoes
 * @version 2021-10-28
 */
public class DateUtil {
    private static LocalDate currentDate = LocalDate.now();
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final LocalDateTime CURRENT_DATE_TIME = LocalDateTime.now();

    public static void main(String[] args) {
        getCurrentDate();
        getDetailDate();
        handleSpecialDate("2022", "10", "28");
        boolean compareResult = compareDate(LocalDate.now(), LocalDate.of(2021,10,28));
        System.out.println("compare result: " + compareResult);
        getCurrentTime();
        System.out.println(nextWeek());
        System.out.println(isLearYear());
        System.out.println(LocalDateTime.now());
        System.out.println(getTimeStrNow("yyyy-MM-dd"));
        System.out.println(getTimeStrNow("yyyy-MM-dd HH:MM:ss"));
    }

    //通过LocalDateTime获取当前格式化时间

    public  static String getTimeStrNow(String pattern){
        //DateTimeFormatter替换了SimpleDateFormat, 因为线程安全问题。
        if (StringUtils.isNotBlank(pattern)) {
            return CURRENT_DATE_TIME.format(DateTimeFormatter.ofPattern(pattern));
        }
        return CURRENT_DATE_TIME.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

    /**
     * 检查今年是否为闰年
     * @return
     */
    public static boolean isLearYear() {
        return currentDate.isLeapYear();
    }


    /**
     * 当前日期基础上，计算下周日期
     * @return
     */
    public static LocalDate nextWeek() {
        return currentDate.plus(1, ChronoUnit.WEEKS);
    }

    /**
     * 日期比较
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean compareDate(LocalDate date1, LocalDate date2) {
        return date1.equals(date2);
    }

    /**
     * 获取特定日期
     *
     * @param year  年
     * @param month 月
     * @param day   日
     * @return  LocalDate日期
     */
    public static LocalDate handleSpecialDate(String year, String month, String day) {
        LocalDate dateOfBirth = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month), Integer.valueOf(day));
        System.out.println("The special date is: " + dateOfBirth);
        return dateOfBirth;
    }

    /**
     * 获取年、月、日信息
     */
    public static void getDetailDate() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.printf("Year: %d Month: %d day: %d t %n", year, month, day);
    }

    public static void getCurrentTime() {
        System.out.println(LocalTime.now());
    }

    /**
     * 获取当前日期 - 年-月-日
     */
    public static LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
