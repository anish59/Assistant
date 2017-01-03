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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.anish.assistant.R;
import com.example.anish.assistant.myCalendar.MyCalendarActivity;
import com.example.anish.assistant.myCalendar.UpdateCalendarEventActivity;
import com.example.anish.assistant.myCalendar.model.ReminderPojo;

import java.util.Calendar;
import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

/**
 * Created by anish on 27-12-2016.
 */

public class AlarmHelper extends BroadcastReceiver {
    public static ReminderPojo reminderPojo = new ReminderPojo();
    private AlarmManager alarmManager;


    @Override
    public void onReceive(Context context, Intent intent) {

        showNotification(context, intent.getStringExtra(IntentConstants.topic), intent.getStringExtra(IntentConstants.desc)
                , intent.getLongExtra(IntentConstants.eventId, -1), intent.getStringExtra(IntentConstants.reminderDate));

    }

    private void showNotification(Context context, String Title, String subTitle, long eventId, String reminderDate) {
        Intent intent = new Intent(context, UpdateCalendarEventActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(IntentConstants.topic, Title);
        intent.putExtra(IntentConstants.desc, subTitle);
        intent.putExtra(IntentConstants.eventId, eventId);
        intent.putExtra(IntentConstants.reminderDate, reminderDate);
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

    public void getReminder(Context mContext, int calId, String title, String description, String reminderDate) {
        cancelAlarm(mContext, calId);
        AlarmManager alarmManager = getAlarmManager(mContext);

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
        myIntent.putExtra(IntentConstants.topic, title);
        myIntent.putExtra(IntentConstants.desc, description);
        myIntent.putExtra(IntentConstants.eventId, calId);
        myIntent.putExtra(IntentConstants.reminderDate, reminderDate);
        PendingIntent alarmIntent = PendingIntent.getBroadcast(mContext, calId, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 0, alarmIntent);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), alarmIntent);
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
//        initBootAlarm(context);
    }
}
