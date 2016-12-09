package com.example.anish.assistant.dashBoard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityDashboardBinding;
import com.example.anish.assistant.myCalendar.MyCalendarActivity;
import com.example.anish.assistant.myNotes.MyNotesActivity;

/**
 * Created by anish on 30-11-2016.
 */

public class DashBoardActivity extends AppCompatActivity
{
    ActivityDashboardBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_dashboard);
    }

    public void clickNotes(View view)
    {
        Intent intent=new Intent(DashBoardActivity.this, MyNotesActivity.class);
        startActivity(intent);
    }

    public void clickMyCalendar(View view)
    {
        Intent intent=new Intent(DashBoardActivity.this, MyCalendarActivity.class);
        startActivity(intent);
    }
}
