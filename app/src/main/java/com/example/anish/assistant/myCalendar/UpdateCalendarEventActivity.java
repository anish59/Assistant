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
import com.example.anish.assistant.assistantHelper.CustomTools.AppConstants;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.IntentConstants;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityUpdateEventBinding;
import com.example.anish.assistant.myCalendar.model.MyCalRequest;
import com.example.anish.assistant.myCalendar.model.MyCalendar;

/**
 * Created by anish on 30-12-2016.
 */

public class UpdateCalendarEventActivity extends AppCompatActivity {
    ActivityUpdateEventBinding binding;
    private long intent_Eventid = AppConstants.DefaultId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_event);
        UIHelper.initToolbarWithBackNavigation(this, binding.toolbar, "Update reminder");
        init();
    }

    private void init() {
        getDataFromIntent();

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UIHelper.toStringEditText(binding.txtTitle).isEmpty()) {
                    Toast.makeText(UpdateCalendarEventActivity.this, "Please enter title.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (intent_Eventid == AppConstants.DefaultId) {
                    Toast.makeText(UpdateCalendarEventActivity.this, "Someting went wrong ", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    MyCalRequest myCalRequest = new MyCalRequest();
                    myCalRequest.setTitle(UIHelper.toStringEditText(binding.txtTitle));
                    myCalRequest.setDesctiption(UIHelper.toStringEditText(binding.txtDesc));

                    String dateNTime = DateHelper.formatDate(UIHelper.toStringButton(binding.btnDate)
                            + "-" + UIHelper.toStringButton(binding.btnTime), DateHelper.dd_mm_yyyy_hh_mm, DateHelper.MMM_MM_dd_yyyy_h_mm_a);
                    myCalRequest.setReminderDate(dateNTime);
                    myCalRequest.setReminderDateMili(DateHelper.getTimeInMili(DateHelper.MMM_MM_dd_yyyy_h_mm_a, dateNTime));
                    myCalRequest.setEventId(intent_Eventid);

                    MyCalendar.update(myCalRequest);
                    MyCalendar myCalendar = MyCalendar.getEventById(intent_Eventid);
                    AlarmHelper alarmHelper = new AlarmHelper();
                    alarmHelper.setNotificationAlarm(UpdateCalendarEventActivity.this
                            , myCalendar.EventId()
                            , myCalendar.Title()
                            , myCalendar.Desctiption()
                            , DateHelper.parseDate(myCalendar.ReminderDate(), DateHelper.MMM_MM_dd_yyyy_h_mm_a));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                delayOneSec();

            }
        });
        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (intent_Eventid != AppConstants.DefaultId) {
                    MyCalendar.delete(intent_Eventid);
                    AlarmHelper alarmHelper = new AlarmHelper();
                    alarmHelper.cancelAlarm(UpdateCalendarEventActivity.this, intent_Eventid);
                    Toast.makeText(UpdateCalendarEventActivity.this, "Reminder deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(UpdateCalendarEventActivity.this, "Someting went wrong ", Toast.LENGTH_SHORT).show();
                }
                delayOneSec();
            }
        });
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        intent_Eventid = intent.getLongExtra(IntentConstants.eventId, AppConstants.DefaultId);
        if (intent_Eventid != AppConstants.DefaultId) {
            MyCalendar myCalendar = MyCalendar.getEventById(intent_Eventid);
            String splitDate = DateHelper.formatDate(myCalendar.ReminderDate(), DateHelper.MMM_MM_dd_yyyy_h_mm_a, DateHelper.splitDateNTimeFormat);
            binding.btnDate.setText(splitDate.split(":")[0]);
            binding.btnTime.setText(String.format("%s:%s", splitDate.split(":")[1], splitDate.split(":")[2]));
            binding.txtTitle.setText(myCalendar.Title());
            binding.txtDesc.setText(myCalendar.Desctiption());
        } else {
            Toast.makeText(UpdateCalendarEventActivity.this, "Someting went wrong ", Toast.LENGTH_SHORT).show();
        }
    }


    private void delayOneSec() {
        Handler handler = new Handler();
        handler.postDelayed(() -> finish(), 1500);
    }

    public void clickDate(View view) {
        DateHelper.getDateFromDialog(this, binding.btnDate);
    }

    public void clickTime(View view) {
        DateHelper.getTimeFromDialog(this, binding.btnTime);
    }
}
