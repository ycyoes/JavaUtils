package com.ycyoes.utils;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * 日期工具类
 * @author ycyoes
 * @version 2021-10-28
 */
public class DateUtil {
    public static void main(String[] args) {
        getCurrentDate();
        getDetailDate();
        handleSpecialDate("2022", "10", "28");
        boolean compareResult = compareDate(LocalDate.now(), LocalDate.of(2021,10,28));
        System.out.println("compare result: " + compareResult);
        getCurrentTime();
    }

    public static void nextWeek() {

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
