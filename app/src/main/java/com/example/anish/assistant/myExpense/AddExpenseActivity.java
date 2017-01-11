package com.example.anish.assistant.myExpense;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityAddExpenseBinding;
import com.example.anish.assistant.myExpense.model.MyExpense;
import com.example.anish.assistant.myExpense.model.MyExpenseRequest;

import java.text.ParseException;

/**
 * Created by anish on 11-01-2017.
 */

public class AddExpenseActivity extends AppCompatActivity {
    ActivityAddExpenseBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_expense);
        init();
    }

    private void init() {
        binding.txtDate.setText(DateHelper.getCurrentDateTime(true, false));
        binding.txtDate.setFocusable(false);

        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyExpenseRequest myExpenseRequest = new MyExpenseRequest();
                myExpenseRequest.setSpendOn(UIHelper.toStringEditText(binding.txtSpendOn));
                myExpenseRequest.setAmount(UIHelper.toDoubleEditText(binding.txtAmount));
                myExpenseRequest.setBudget(5000.0);
                myExpenseRequest.setDiscription(UIHelper.toStringEditText(binding.txtDesc));
                myExpenseRequest.setExpenseDate(UIHelper.toStringEditText(binding.txtDate));
                myExpenseRequest.setMonth(DateHelper.getCurrentDateTime(false, true));
                try {
                    myExpenseRequest.setExpenseDateMili(DateHelper.getTimeInMili(DateHelper.dd_MM_yyyy, UIHelper.toStringEditText(binding.txtDate)));
                    MyExpense.insert(myExpenseRequest);
                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(AddExpenseActivity.this, "Sorry expense not added.", Toast.LENGTH_SHORT).show();
                }
                delayOneSec();
            }
        });

    }

    private void delayOneSec() {

        Handler handler = new Handler();
        handler.postDelayed(() -> finish(), 1500);
    }

    public void clickDate(View view) {
        DateHelper.getDateFromDialog(this, binding.txtDate);
    }
}
