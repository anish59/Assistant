package com.example.anish.assistant.assistantHelper;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.anish.assistant.R;

/**
 * Created by anish on 07-12-2016.
 */

public class UIHelper {

    public static void initToolbarWithBackNavigation(final AppCompatActivity activity, Toolbar toolbar, String title) {
        toolbar.setTitle(title);
        toolbar.setTitleTextColor(Color.WHITE);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIHelper.fireIntent(activity, false);
            }
        });
    }

    public static void fireIntent(Activity context, boolean isNewActivity) {
        context.finish();
        if (!isNewActivity) {
            context.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        } else {
            context.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        }
    }

    public static String toStringEditText(TextView tv) {
        String text = tv.getText().toString().trim();
        return text;
    }
    public static double toDoubleEditText(TextView tv) {
        double text = Double.parseDouble(tv.getText().toString().trim());
        return text;
    }
    public static String toStringButton(Button button) {
        String text = button.getText().toString().trim();
        return text;
    }
}
