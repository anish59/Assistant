package com.example.anish.assistant.myExpense.fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.FragmentExpenseThisMonthBinding;

/**
 * Created by anish on 09-01-2017.
 */

public class ExpenseThisMonthFragment extends Fragment
{
    FragmentExpenseThisMonthBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding= DataBindingUtil.inflate(inflater, R.layout.fragment_expense_this_month,container,false);
        return binding.getRoot();
    }
}
