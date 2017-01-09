package com.example.anish.assistant.myExpense;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.assistantHelper.ViewPagerAdapter;
import com.example.anish.assistant.databinding.ActivityMyExpenseBinding;
import com.example.anish.assistant.myExpense.fragment.ExpenseThisMonthFragment;

/**
 * Created by anish on 09-01-2017.
 */

public class MyExpenseActivity extends AppCompatActivity {
    private ViewPagerAdapter adapter;
    ActivityMyExpenseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_expense);
        init();
    }

    private void init() {
        UIHelper.initToolbarWithBackNavigation(this,binding.toolbar,"My Expense");
        setUpViewPager(binding.viewpager);
        binding.tabs.setupWithViewPager(binding.viewpager);
    }

    private void setUpViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExpenseThisMonthFragment(), "Expense This Month");
        adapter.addFragment(new ExpenseThisMonthFragment(), "Monthly Expense");
        adapter.addFragment(new ExpenseThisMonthFragment(), "Yearly Expenses");
        viewPager.setAdapter(adapter);
    }
}
