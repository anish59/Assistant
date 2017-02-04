package com.example.anish.assistant.myCalendar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.AlarmHelper;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityAddEventBinding;
import com.example.anish.assistant.myCalendar.model.MyCalRequest;
import com.example.anish.assistant.myCalendar.model.MyCalendar;

/**
 * Created by anish on 29-12-2016.
 */

public class AddCalendarEventActivity extends AppCompatActivity {
    ActivityAddEventBinding binding;
    private String selectedTime;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_event);
        UIHelper.initToolbarWithBackNavigation(this, binding.toolbar, "Add Event");
        init();
    }

    private void init() {
        binding.insert.setVisibility(View.GONE);
        binding.txtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isReady = binding.insert.getText().toString().trim().length() > 1;
                if (isReady) {
                    binding.insert.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (UIHelper.toStringEditText(binding.txtTitle).isEmpty()) {
                    Toast.makeText(AddCalendarEventActivity.this, "Please enter title.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (UIHelper.toStringButton(binding.btnDate).equals(getString(R.string.date))
                        || UIHelper.toStringButton(binding.btnDate).equals("")) {
                    Toast.makeText(AddCalendarEventActivity.this, "Please select reminder date.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(UIHelper.toStringButton(binding.btnTime).equals(getString(R.string.time))
                        || UIHelper.toStringButton(binding.btnTime).equals(""))) {
                    selectedTime = UIHelper.toStringButton(binding.btnTime);
                } else {
                    selectedTime = "00:00";
                }

                String dateNTime = DateHelper.formatDate(UIHelper.toStringButton(binding.btnDate)
                        + "-" + selectedTime, DateHelper.dd_mm_yyyy_hh_mm, DateHelper.MMM_MM_dd_yyyy_h_mm_a);

//        Log.e("$$DateOutput->", datentime+"in milli->"+DateHelper.getTimeInMili(DateHelper.MMM_MM_dd_yyyy_h_mm_a,datentime));
                try {
                    MyCalRequest myCalRequest = new MyCalRequest();
                    myCalRequest.setTitle(UIHelper.toStringEditText(binding.txtTitle));
                    myCalRequest.setDesctiption(UIHelper.toStringEditText(binding.txtDesc));
                    myCalRequest.setReminderDate(dateNTime);
                    myCalRequest.setReminderDateMili(DateHelper.getTimeInMili(DateHelper.MMM_MM_dd_yyyy_h_mm_a, dateNTime));

                    MyCalendar.insertInMyCalendar(myCalRequest);
                    Log.e("lastId", MyCalendar.getLastCase() + "");
                    MyCalendar myCalendar2 = MyCalendar.getLastCase();
                    AlarmHelper alarmHelper = new AlarmHelper();
                    alarmHelper.setNotificationAlarm(AddCalendarEventActivity.this
                            , myCalendar2.EventId()
                            , myCalendar2.Title()
                            , myCalendar2.Desctiption()
                            , DateHelper.parseDate(myCalendar2.ReminderDate(),DateHelper.MMM_MM_dd_yyyy_h_mm_a));
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("SQL error-->", e.getMessage());
                }
                delayOneSec();
            }
        });
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
