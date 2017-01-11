package com.example.anish.assistant.myExpense.fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.FragmentExpenseThisMonthBinding;
import com.example.anish.assistant.myExpense.AddExpenseActivity;

/**
 * Created by anish on 09-01-2017.
 */

public class ExpenseThisMonthFragment extends Fragment {
    FragmentExpenseThisMonthBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_expense_this_month, container, false);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddExpenseActivity.class));
            }
        });

        return binding.getRoot();


    }

}
