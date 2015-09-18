package com.french.egs.haykn.myweather.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by haykn on 9/18/15.
 */
public class Util {

    public static double fahrenheitToCelsius(double fahrenheit) {
        return ((fahrenheit - 32)*5)/9;
    }
    public static String milsToHour(long seconds) {
        long mils = seconds * 1000l; // TODO: Need  to check if the data from json is introduced in milliseconds or in seconds
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(mils);
        String am_pm;
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        if(calendar.get(Calendar.AM_PM) == 0) {
            am_pm = "AM";
        } else {
            am_pm = "PM";
        }
        return hour + ":" + minute + " " + am_pm;

    }

    public static String getWeekShortDay(long seconds) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEE", Locale.getDefault());
        return formatter.format(new Date(seconds * 1000L));
    }

}
