package com.example.anish.assistant.myCalendar;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.AlarmHelper;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.IntentConstants;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityUpdateEventBinding;
import com.example.anish.assistant.myCalendar.model.MyCalRequest;
import com.example.anish.assistant.myCalendar.model.MyCalendar;

import java.text.ParseException;

/**
 * Created by anish on 30-12-2016.
 */

public class UpdateCalendarEventActivity extends AppCompatActivity {
    ActivityUpdateEventBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_event);
        UIHelper.initToolbarWithBackNavigation(this, binding.toolbar, "Update reminder");
        init();
    }

    private void init() {
        getDataFromIntent();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        String splitDate = DateHelper.formatDate(intent.getStringExtra(IntentConstants.reminderDate)
                , DateHelper.MMM_MM_dd_yyyy_h_mm_a, DateHelper.splitDateNTimeFormat);
        binding.btnDate.setText(splitDate.split(":")[0]);
        binding.btnTime.setText(String.format("%s:%s", splitDate.split(":")[1], splitDate.split(":")[2]));
        binding.txtTitle.setText(intent.getStringExtra(IntentConstants.topic));
        binding.txtDesc.setText(intent.getStringExtra(IntentConstants.desc));
    }

    public void clickUpdate(View view) throws ParseException {
        if (UIHelper.toStringEditText(binding.txtTitle).isEmpty()) {
            Toast.makeText(this, "Please enter title.", Toast.LENGTH_SHORT).show();
            return;
        }

        MyCalRequest myCalRequest = new MyCalRequest();
        myCalRequest.setTitle(UIHelper.toStringEditText(binding.txtTitle));
        myCalRequest.setDesctiption(UIHelper.toStringEditText(binding.txtDesc));

        String dateNTime = DateHelper.formatDate(UIHelper.toStringButton(binding.btnDate)
                + "-" + UIHelper.toStringButton(binding.btnTime), DateHelper.dd_mm_yyyy_hh_mm, DateHelper.MMM_MM_dd_yyyy_h_mm_a);
        myCalRequest.setReminderDate(dateNTime);
        myCalRequest.setReminderDateMili(DateHelper.getTimeInMili(DateHelper.MMM_MM_dd_yyyy_h_mm_a, dateNTime));
        Intent intent = getIntent();
        myCalRequest.setEventId(intent.getLongExtra(IntentConstants.eventId, -1));
        try {
            MyCalendar.update(myCalRequest);
            MyCalendar myCalendar = MyCalendar.getLastCase();
            AlarmHelper alarmHelper = new AlarmHelper();
            alarmHelper.getReminder(this, (int) myCalendar.EventId()
                    , myCalendar.Title(), myCalendar.Desctiption(), myCalendar.ReminderDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
        delayOneSec();
    }

    private void delayOneSec() {
        Handler handler = new Handler();
        handler.postDelayed(() -> finish(), 1500);
    }

    public void clickDelete(View view) {
        Intent intent = getIntent();
        MyCalendar.delete(intent.getLongExtra(IntentConstants.eventId, 0));
        AlarmHelper alarmHelper=new AlarmHelper();
        alarmHelper.cancelAlarm(this,intent.getLongExtra(IntentConstants.eventId, 0));
        Toast.makeText(this, "Reminder deleted", Toast.LENGTH_SHORT).show();
        delayOneSec();
    }

    public void clickDate(View view) {
        DateHelper.getDateFromDialog(this, binding.btnDate);
    }

    public void clickTime(View view) {
        DateHelper.getTimeFromDialog(this, binding.btnTime);
    }
}
