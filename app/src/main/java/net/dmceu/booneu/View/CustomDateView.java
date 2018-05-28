package net.dmceu.booneu.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Delux on 3/6/2018.
 */

public class CustomDateView {
    //Normal SET

    public static String setReturn(String strDate)
    {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        String dateData = null;
        try {
            Calendar today = Calendar.getInstance();
            Date date = df.parse(strDate);

            today.setTime(date);

            dateData = df.format(date.getTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return dateData;
    }


    public static String setDate(String strDate)
    {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0,month=0,day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s-%s-%s", day,month,year);
    }

    public static String setDay(String strDay)
    {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int day=0;
        try {
            Date date = df.parse(strDay);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", day);
    }

    public static String setMonth(String strMonth)
    {

        DateFormat df = new SimpleDateFormat("MMMM");

        int month=0;
        try {
            Date date = df.parse(strMonth);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            month = c.get(Calendar.MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", month);
    }

    public static String setYear(String strYear)
    {

        DateFormat df = new SimpleDateFormat("yyyy");

        int year=0;
        try {
            Date date = df.parse(strYear);
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            year = c.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", year);
    }

    //THAI SET

    public static String dateThai(String strDate)
    {
        String Months[] =
                {
                        "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
                        "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
                        "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0,month=0,day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);
            month = c.get(Calendar.MONTH);
            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s %s %s", day,Months[month],year);//Thai get +543
    }

    public static String dayThai(String strDate)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int day=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            day = c.get(Calendar.DATE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", day);
    }

    public static String monthThai(String strDate)
    {
        String Months[] =
                {
                        "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
                        "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
                        "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int month=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            month = c.get(Calendar.MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", Months[month]);
    }

    public static String yearThai(String strDate)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            year = c.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", year);//Thai get +543
    }

    public static String timeShot(String strDate)
    {

        DateFormat df = new SimpleDateFormat("HH:mm");

        int hour=0,minute=0;
        try {
            Date date = df.parse(strDate);
            Calendar c = Calendar.getInstance();
            c.setTime(date);

            hour = c.get(Calendar.HOUR_OF_DAY);
            minute = c.get(Calendar.MINUTE);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s.%02d", hour,minute);  //%02d or %s
    }

    public static String setTitle(CalendarDay day)
    {
        String Months[] =
                {
                        "มกราคม", "กุมภาพันธ์", "มีนาคม", "เมษายน",
                        "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม",
                        "กันยายน", "ตุลาคม", "พฤศจิกายน", "ธันวาคม"};

        int month=0 , year=0;
        month = day.getMonth();
        year = day.getYear();
        return String.format("%s %s",Months[month] , year);//Thai get +543
    }

    public static String setY(String date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int year=0;
        try {
            Date dat = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dat);

            year = c.get(Calendar.YEAR);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", year);
    }

    public static String setM(String date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int month=0;
        try {
            Date dat = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dat);

            month = c.get(Calendar.MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", month);
    }

    public static String setD(String date)
    {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        int day=0;
        try {
            Date dat = df.parse(date);
            Calendar c = Calendar.getInstance();
            c.setTime(dat);

            day = c.get(Calendar.DAY_OF_MONTH);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return String.format("%s", day);
    }

}
