package com.example.anish.assistant.myExpense;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityExpenseItemBinding;
import com.example.anish.assistant.myExpense.model.MyExpense;

import java.util.List;

/**
 * Created by anish on 12-01-2017.
 */

public class MyExpenseAdapter extends RecyclerView.Adapter<MyExpenseAdapter.MyViewHolder> {

    public List<MyExpense> myExpenseList;
    public Context context;
    private OnClickedListener mOnClickListener;
    public interface OnClickedListener {
        void onItemClick(MyExpense myExpense);
    }

    public MyExpenseAdapter(List<MyExpense> myExpenseList, Context context, OnClickedListener mOnClickListener) {
        this.myExpenseList = myExpenseList;
        this.context = context;
        this.mOnClickListener = mOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return null;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bindTo(myExpenseList.get(position), mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        private ActivityExpenseItemBinding activityExpenseItemBinding;

        public MyViewHolder(ActivityExpenseItemBinding itemView) {
            super(itemView.getRoot());
            activityExpenseItemBinding=itemView;
        }

        static MyViewHolder create(LayoutInflater inflater,ViewGroup parent)
        {
            ActivityExpenseItemBinding binding=ActivityExpenseItemBinding.inflate(inflater,parent,false);
            return new MyViewHolder(binding);
        }


        public void bindTo(MyExpense myExpense, OnClickedListener mOnClickListener) {
            if(myExpense!=null)
            {
                activityExpenseItemBinding.txtSpendOn.setText("Spend For: "+myExpense.SpendOn());
                activityExpenseItemBinding.txtAmount.setText("Amount: "+myExpense.Amount());
                activityExpenseItemBinding.txtDetail.setText(myExpense.Discription());
                activityExpenseItemBinding.txtDate.setText(myExpense.ExpenseDate());
                activityExpenseItemBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

            }
        }
    }


}
