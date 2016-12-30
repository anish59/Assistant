package com.example.anish.assistant.assistantHelper;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.example.anish.assistant.myCalendar.model.ReminderPojo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by anish on 07-12-2016.
 */

public class DateHelper
{
    public static ReminderPojo reminderPojo = new ReminderPojo();
    public static int mYear, mMonth, mDay, mHour, mMinute;
    public static final String MMM_MM_dd_yyyy_h_mm_a="MMM MM dd, yyyy h:mm a";
    public static final String splitDateNTime="dd-MM-yyyy:hh-mm";
    public static final String dd_mm_yyyy_hh_mm="dd-MM-yyyy-hh:mm";
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
                button.setText(String.format("%s-%s-%s", dayOfMonth, monthOfYear+1, year));
                reminderPojo.setrDay(dayOfMonth);
                reminderPojo.setrMonth(monthOfYear+1);
                reminderPojo.setrYear(year);
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
                reminderPojo.setrHour(hourOfDay);
                reminderPojo.setrMinute(minute);
            }
        }, mHour, mMinute, false);
//        timePickerDialog.set
        timePickerDialog.show();
    }

}
