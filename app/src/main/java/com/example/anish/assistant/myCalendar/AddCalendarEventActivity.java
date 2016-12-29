package com.example.anish.assistant.myCalendar;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityAddEventBinding;
import com.example.anish.assistant.myCalendar.model.MyCalRequest;
import com.example.anish.assistant.myCalendar.model.MyCalendar;

/**
 * Created by anish on 29-12-2016.
 */

public class AddCalendarEventActivity extends AppCompatActivity
{
    ActivityAddEventBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_add_event);
        UIHelper.initToolbarWithBackNavigation(this,binding.toolbar,"Add Event");
    }


    public void addEvent(View view)
    {
//        MyCalRequest myCalRequest=new MyCalRequest();
//        myCalRequest.setTitle(UIHelper.toStringEditText(binding.txtTitle));
//        myCalRequest.setDesctiption(UIHelper.toStringEditText(binding.txtDesc));
//        myCalRequest.setReminderDate(UIHelper.toStringEditText(binding.));
//        MyCalendar.insertInMyCalendar();
//        Log.e("DateInput",)
    }
}
