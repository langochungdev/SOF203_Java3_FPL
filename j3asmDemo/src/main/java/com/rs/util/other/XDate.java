/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rs.util.other;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author ndhlt
 */
public class XDate {
    
    public static String VN_DATE_1 = "dd/MM/yyyy";
    public static String VN_DATE_2 = "dd-MM-yyyy";
    public static String VN_DATE_3 = "E dd-MM-yyyy";
    public static String TIME = "hh:mm:ss";

    private static final SimpleDateFormat formater = new SimpleDateFormat();

    public static Date toDate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String pattern) {
        formater.applyPattern(pattern);
        return formater.format(date);
    }

    public static Date addDays(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static boolean validate(String date, String pattern) {
        try {
            formater.applyPattern(pattern);
            formater.parse(date);
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

}
