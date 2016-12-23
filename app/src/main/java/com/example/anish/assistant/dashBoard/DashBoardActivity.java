package com.example.anish.assistant.dashBoard;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.CheckNetwork;
import com.example.anish.assistant.databinding.ActivityDashboardBinding;
import com.example.anish.assistant.myCalendar.MyCalendarActivity;
import com.example.anish.assistant.myNotes.MyNotesActivity;

/**
 * Created by anish on 30-11-2016.
 */

public class DashBoardActivity extends AppCompatActivity {
    ActivityDashboardBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
    }

    public void clickNotes(View view) {
        Intent intent = new Intent(DashBoardActivity.this, MyNotesActivity.class);
        startActivity(intent);
    }

    public void clickMyCalendar(View view) {
        Intent intent = new Intent(DashBoardActivity.this, MyCalendarActivity.class);
        startActivity(intent);
    }

    Boolean chechAgain = false;
    Snackbar snackbar, snackbar1,snackbar3;

    public void clickCheckInternet(View view) {

        if (!CheckNetwork.isInternetAvailable(this)) {
            snackbar = Snackbar.make(binding.activityMain, "No Internet...!", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Check Again", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (CheckNetwork.isInternetAvailable(DashBoardActivity.this)) {
                                snackbar1 = Snackbar.make(binding.activityMain, "Good To go.. :)", Snackbar.LENGTH_LONG);
                                snackbar1.show();
                            } else {
                                snackbar3= Snackbar.make(binding.activityMain, "Still no internet connection.. :(", Snackbar.LENGTH_LONG);
                                snackbar3.show();
                            }
                        }
                    });
            snackbar.show();
        } else {
            snackbar1=Snackbar.make(binding.activityMain, "Good To go.. :)", Snackbar.LENGTH_LONG);
            snackbar1.show();
        }


    }
}
