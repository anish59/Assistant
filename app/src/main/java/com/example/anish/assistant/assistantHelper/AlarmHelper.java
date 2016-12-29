package com.example.anish.assistant.assistantHelper;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.anish.assistant.R;
import com.example.anish.assistant.myCalendar.MyCalendarActivity;
import com.example.anish.assistant.myCalendar.model.ReminderPojo;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by anish on 27-12-2016.
 */

public class AlarmHelper extends BroadcastReceiver {
    public static ReminderPojo reminderPojo = new ReminderPojo();
    public static int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    public void onReceive(Context context, Intent intent) {


//        showNotification(context);

    }

    private void showNotification(Context context,String Title,String subTitle) {
        Intent intent = new Intent(context, MyCalendarActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.calendar_notification)
                .setContentTitle(Title)
                .setContentText(subTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        noBuilder.setDefaults(Notification.DEFAULT_SOUND);

       //For solving conflicting two or more reminder at a time.
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(m, noBuilder.build()); //0 = ID of notification

    }

    public static void getDateFromDialog(Context mContext, final TextView tv) {
        final Calendar c = Calendar.getInstance();

        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                datePicker.setMinDate(System.currentTimeMillis() - 1000);
                tv.setText(String.format("%s-%s-%s", dayOfMonth, monthOfYear, year));
                reminderPojo.setrDay(dayOfMonth);
                reminderPojo.setrMonth(monthOfYear);
                reminderPojo.setrYear(year);
            }
        }, mYear, mMonth, mDay);

        datePickerDialog.show();
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
    }

    public static void getTimeFromDialog(Context mContext, final TextView tv) {
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(mContext, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                tv.setText(String.format("%s-%s", hourOfDay, minute));
                reminderPojo.setrHour(hourOfDay);
                reminderPojo.setrMinute(minute);
            }
        }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public static void getReminder(Context mContext, int CalId) {
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(ALARM_SERVICE);
//        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, myIntent, 0);
        Calendar cal = Calendar.getInstance();
        cal.set(reminderPojo.getrYear(),
                reminderPojo.getrMonth(),
                reminderPojo.getrDay(),
                reminderPojo.getrHour(),
                reminderPojo.getrMinute(),
                0);
        Log.e("$$$$-->", "hour:->" + reminderPojo.getrHour() + " Day:" + reminderPojo.getrDay());
        Intent myIntent = new Intent(mContext, AlarmHelper.class);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mContext, CalId, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 0, alarmIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
    }

}
