package com.example.anish.assistant.login;

import android.app.ActionBar;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityLogInBinding;

/**
 * Created by anish on 29-11-2016.
 */

public class LoginActivity extends AppCompatActivity {
    ActivityLogInBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);
        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        /*ActionBar actionBar = getActionBar();
        actionBar.hide();*/


    }
}
