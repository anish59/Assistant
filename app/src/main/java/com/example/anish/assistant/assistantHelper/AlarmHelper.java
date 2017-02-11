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
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.CustomTools.AppConstants;
import com.example.anish.assistant.myCalendar.MyCalendarActivity;
import com.example.anish.assistant.myCalendar.UpdateCalendarEventActivity;
import com.example.anish.assistant.myCalendar.model.MyCalendar;
import com.example.anish.assistant.myCalendar.model.ReminderPojo;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by anish on 27-12-2016.
 */

public class AlarmHelper extends BroadcastReceiver {
    //    public static ReminderPojo reminderPojo = new ReminderPojo();
    private AlarmManager alarmManager;


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Event ID:(oR) ", intent.getLongExtra(IntentConstants.eventId, AppConstants.DefaultId) + "::" + intent.getStringExtra(IntentConstants.topic));

        /*if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction())) {
            List<MyCalendar> eventLists = MyCalendar.getAllEvents();
            for (MyCalendar myEventLists : eventLists) {
                setNotificationAlarm(context
                        ,myEventLists.EventId()
                        ,myEventLists.Title()
                        ,myEventLists.Desctiption()
                        ,DateHelper.parseDate(myEventLists.ReminderDate()
                        ,DateHelper.MMM_MM_dd_yyyy_h_mm_a));
            }
        } else {
            showNotification(context
                    , intent.getStringExtra(IntentConstants.topic)
                    , intent.getStringExtra(IntentConstants.desc)
                    , intent.getLongExtra(IntentConstants.eventId, AppConstants.DefaultId));
        }*/
        showNotification(context
                , intent.getStringExtra(IntentConstants.topic)
                , intent.getStringExtra(IntentConstants.desc)
                , intent.getLongExtra(IntentConstants.eventId, AppConstants.DefaultId));


    }

    private void showNotification(Context context, String Title, String subTitle, long eventId) {
        Intent intent = new Intent(context, UpdateCalendarEventActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(IntentConstants.eventId, eventId);


        Log.e("Event ID:(sN) ", eventId + "");
//        intent.putExtra(IntentConstants.topic, Title);
//        intent.putExtra(IntentConstants.desc, subTitle);
//        intent.putExtra(IntentConstants.reminderDate, reminderDate);
        int requestCode = 0;
        PendingIntent pendingIntent = PendingIntent.getActivity(context, requestCode, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder noBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.calendar_notification)
                .setContentTitle(Title)
                .setContentText(subTitle)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        noBuilder.setDefaults(Notification.DEFAULT_ALL);
//        noBuilder.setDefaults(Notification.DEFAULT_SOUND);//to make sound
        noBuilder.setVibrate(new long[]{1000, 1000, 1000, 1000});//to vibrate
        noBuilder.setLights(Color.WHITE,1000,500);
//        noBuilder.setPriority(Notification.P);

        //For solving conflicting two or more reminder(notification) at a time.
        Random random = new Random();
        int m = random.nextInt(9999 - 1000) + 1000;
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(m, noBuilder.build()); //0 = ID of notification

    }

    public void setNotificationAlarm(Context mContext, long calId, String title, String description, Date date) {
        cancelAlarm(mContext, calId);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Intent myIntent = new Intent(mContext, AlarmHelper.class);
        myIntent.putExtra(IntentConstants.eventId, calId);// eventId is also calendarId
        myIntent.putExtra(IntentConstants.topic, title);
        myIntent.putExtra(IntentConstants.desc, description);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mContext, (int) calId, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 0, alarmIntent);
        AlarmManager alarmManager = getAlarmManager(mContext);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), alarmIntent);
    }

    private AlarmManager getAlarmManager(Context context) {

        if (this.alarmManager == null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return alarmManager;
    }

    public void cancelAlarm(Context context, long alarmID) {
        Intent intent = new Intent(context, AlarmHelper.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, (int) alarmID, intent, 0);
        AlarmManager alarmManager = getAlarmManager(context);
        alarmManager.cancel(pendingIntent);
    }
}


/*

    //        PendingIntent pendingIntent = PendingIntent.getService(mContext, 0, myIntent, 0);
    Calendar cal = Calendar.getInstance();
cal.set(reminderPojo.getrYear(),
        reminderPojo.getrMonth(),
        reminderPojo.getrDay(),
        reminderPojo.getrHour(),
        reminderPojo.getrMinute(), 0);
        Log.e("$$$$-->", "hour:->" + reminderPojo.getrHour() + " Day:" + reminderPojo.getrDay());
        Log.e("Event ID:(gR) ",calId+"");
*/
