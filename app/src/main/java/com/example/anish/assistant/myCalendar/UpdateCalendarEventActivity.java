package com.example.anish.assistant.myCalendar;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.IntentConstants;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityUpdateEventBinding;

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
        String splitdate = DateHelper.formatDate(intent.getStringExtra(IntentConstants.reminderDate)
                , DateHelper.MMM_MM_dd_yyyy_h_mm_a, DateHelper.splitDateNTime);
        binding.btnDate.setText(splitdate.split(":")[0]);
        binding.btnTime.setText(splitdate.split(":")[1]);
        binding.txtTitle.setText(IntentConstants.topic);
        binding.txtDesc.setText(IntentConstants.desc);
    }

    public void clickUpdate(View view) {

    }

    public void clickDelete(View view) {

    }
}
