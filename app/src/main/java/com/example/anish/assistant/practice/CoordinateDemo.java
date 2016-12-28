package com.example.anish.assistant.practice;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.PracticeLayoutBinding;

/**
 * Created by anish on 28-12-2016.
 */

public class CoordinateDemo extends AppCompatActivity
{
    PracticeLayoutBinding binding;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.practice_layout);
    }
}
