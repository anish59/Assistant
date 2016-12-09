package com.example.anish.assistant.assistantHelper;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by anish on 07-12-2016.
 */

public class DateHelper
{
    public static final String MMM_MM_dd_yyyy_h_mm_a="MMM MM dd, yyyy h:mm a";
    public static final String MMMM_dd_yyyy="MMMM dd, yyyy";
    public static final String MonthFormat = "MMM - yyyy";
    public static final String  MMM_MM_dd_yyyy= "MMM MM dd, yyyy";
    public static String getCurrentDateTime()
    {
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        Log.d("##date->",dateString);
        return dateString;
//        notesRequest.setNoteDate(dateString);
    }

    public static long getCurrentDateTimeInMili() {
        long date = System.currentTimeMillis();
        Log.d("##date->", date+"");
        return date;
    }

    public static String formatDate_MMMM_dd_yyyy(String inputDate) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(MMM_MM_dd_yyyy_h_mm_a);
        SimpleDateFormat outputFormat = new SimpleDateFormat(MMMM_dd_yyyy);

        Date date = null;
        String str = null;

        try {
            date = inputFormat.parse(inputDate);
            str = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return str;
    }


    public static Date parseDate(String dateStr, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        Date date = null;
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }

    public static String formatDate(Date date, String type) {
        SimpleDateFormat df = new SimpleDateFormat(type);
        String format = df.format(date);
        return format;

    }



    public static Long getTimeInMili(String givenDateString, String inputDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(inputDate);

        Date mDate = sdf.parse(givenDateString);
        long timeInMilliseconds = mDate.getTime();
        return timeInMilliseconds;
    }

    public static Long getTimeInMili(Date date) {

        long timeInMilliseconds = date.getTime();
        return timeInMilliseconds;
    }
}
