package com.example.anish.assistant.dashBoard;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.myNotes.MyNotesActivity;

/**
 * Created by anish on 30-11-2016.
 */

public class DashBoardActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dashboard);
    }

    public void clickNotes(View view)
    {
        Intent intent=new Intent(DashBoardActivity.this, MyNotesActivity.class);
        startActivity(intent);
    }
}
