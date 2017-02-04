package com.example.anish.assistant.assistantHelper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.anish.assistant.myCalendar.model.ReminderPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anish on 07-12-2016.
 */

public class DateHelper {
    //    D	day in year	(Number)	189
//    E	day of week	(Text)	E/EE/EEE:Tue, EEEE:Tuesday, EEEEE:T
//    F	day of week in month	(Number)	2 (2nd Wed in July)
//    G	era designator	(Text)	AD
//    H	hour in day (0-23)	(Number)	0
//    K	hour in am/pm (0-11)	(Number)	0
//    L	stand-alone month	(Text)	L:1 LL:01 LLL:Jan LLLL:January LLLLL:J
//    M	month in year	(Text)	M:1 MM:01 MMM:Jan MMMM:January MMMMM:J
//    S	fractional seconds	(Number)	978
//    W	week in month	(Number)	2
//    Z	time zone (RFC 822)	(Time Zone)	Z/ZZ/ZZZ:-0800 ZZZZ:GMT-08:00 ZZZZZ:-08:00
//    a	am/pm marker	(Text)	PM
//    c	stand-alone day of week	(Text)	c/cc/ccc:Tue, cccc:Tuesday, ccccc:T
//    d	day in month	(Number)	10
//    h	hour in am/pm (1-12)	(Number)	12
//    k	hour in day (1-24)	(Number)	24
//    m	minute in hour	(Number)	30
//    s	second in minute	(Number)	55
//    w	week in year	(Number)	27
//    y	year	(Number)	yy:10 y/yyy/yyyy:2010
//    z	time zone	(Time Zone)	z/zz/zzz:PST zzzz:Pacific Standard Time
//    '	escape for text	(Delimiter)	'Date=':Date=
//            ''	single quote	d(Literal)	'o''clock':o'clock

    //    public static ReminderPojo reminderPojo = new ReminderPojo();
    public static int mYear, mMonth, mDay, mHour, mMinute;
    public static final String MMM_MM_dd_yyyy_h_mm_a = "MMM MM dd, yyyy h:mm a";
    public static final String dd_MM_yyyy = "dd-MM-yyyy";
    public static final String MMM = "MMM";
    public static final String splitDateNTimeFormat = "dd-MM-yyyy:hh:mm"; //Split from first ':'
    public static final String dd_mm_yyyy_hh_mm = "dd-MM-yyyy-hh:mm";
    public static final String MMMM_dd_yyyy = "MMMM dd, yyyy";
    public static final String MonthFormat = "MMM - yyyy";
    public static final String MMM_MM_dd_yyyy = "MMM MM dd, yyyy";

    public static String getCurrentDateTime(boolean needOnlyDate, boolean needOnlyMonth) {

        long date = System.currentTimeMillis();
        SimpleDateFormat sdf;

        if (needOnlyDate) sdf = new SimpleDateFormat(dd_MM_yyyy);
        else if (needOnlyMonth) sdf = new SimpleDateFormat(MMM);
        else sdf = new SimpleDateFormat(MMM_MM_dd_yyyy_h_mm_a);
        String dateString = sdf.format(date);
        Log.d("##date->", dateString);
        return dateString;
//        notesRequest.setNoteDate(dateString);
    }

    public static long getCurrentDateTimeInMili() {
        long date = System.currentTimeMillis();
        Log.d("##date->", date + "");
        return date;
    }

    public static String formatDate(String inputDate, String inputFormatString, String outputFormatString) {
        SimpleDateFormat inputFormat = new SimpleDateFormat(inputFormatString);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputFormatString);

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

        SimpleDateFormat sdf = new SimpleDateFormat(givenDateString);

        Date mDate = sdf.parse(inputDate);
        long timeInMilliseconds = mDate.getTime();
        return timeInMilliseconds;
    }

    public static Long getTimeInMili(Date date) {

        long timeInMilliseconds = date.getTime();
        return timeInMilliseconds;
    }


    public static void getDateFromDialog(Context mContext, final Button button) {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                datePicker.setMinDate(System.currentTimeMillis() - 1000);
                button.setText(String.format("%s-%s-%s", dayOfMonth, monthOfYear + 1, year));
//                reminderPojo.setrDay(dayOfMonth);
//                reminderPojo.setrMonth(monthOfYear + 1);
//                reminderPojo.setrYear(year);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    public static void getDateFromDialog(Context mContext, final EditText editText) {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                datePicker.setMinDate(System.currentTimeMillis() - 1000);
                editText.setText(String.format("%s-%s-%s", dayOfMonth, monthOfYear + 1, year));
//                reminderPojo.setrDay(dayOfMonth);
//                reminderPojo.setrMonth(monthOfYear + 1);
//                reminderPojo.setrYear(year);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    public static void getTimeFromDialog(Context mContext, final Button button) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                button.setText(String.format("%s:%s", hourOfDay, minute));
//                reminderPojo.setrHour(hourOfDay);
//                reminderPojo.setrMinute(minute);
            }
        }, mHour, mMinute, false);
//        timePickerDialog.set
        timePickerDialog.show();
    }

    public static void getTimeFromDialog(Context mContext, final EditText editText) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                editText.setText(String.format("%s:%s", hourOfDay, minute));
//                reminderPojo.setrHour(hourOfDay);
//                reminderPojo.setrMinute(minute);
            }
        }, mHour, mMinute, false);
//        timePickerDialog.set
        timePickerDialog.show();
    }

}
