package com.example.anish.assistant.myExpense.model;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.anish.assistant.MyExpenseModel;
import com.example.anish.assistant.dataBaseHelper.DatabaseManager;
import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 11-01-2017.
 */
@AutoValue
public abstract class MyExpense implements MyExpenseModel {
    public static final Factory<MyExpense> MY_EXPENSE_FACTORY = new Factory<>(AutoValue_MyExpense::new);
    public static final RowMapper<MyExpense> MY_EXPENSE_ROW_MAPPER = MY_EXPENSE_FACTORY.select_all_expenseMapper();

    public static void insert(MyExpenseRequest myExpenseRequest) {
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        try {
            sqLiteDatabase.insert(MyExpense.TABLE_NAME, null, MyExpense.MY_EXPENSE_FACTORY.marshal()
                    .Budget(myExpenseRequest.Budget())
                    .SpendOn(myExpenseRequest.SpendOn())
                    .Amount(myExpenseRequest.Amount())
                    .Discription(myExpenseRequest.Discription())
                    .Month(myExpenseRequest.getMonth())
                    .ExpenseDate(myExpenseRequest.ExpenseDate())
                    .ExpenseDateMili(myExpenseRequest.getExpenseDateMili())
                    .asContentValues());
            DatabaseManager.getInstance().closeDatabase();
        } catch (Exception e) {
            Log.e("MyExpense dbError:", e.getMessage());
        }
    }

    public static List<MyExpense> getAllMyExpenses() {
        List<MyExpense> myExpenses = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(MyExpense.SELECT_ALL_EXPENSE, new String[]{});
        while (cursor.moveToNext()) {
            myExpenses.add(MyExpense.MY_EXPENSE_ROW_MAPPER.map(cursor));
        }
        DatabaseManager.getInstance().closeDatabase();
        return myExpenses;
    }

}
