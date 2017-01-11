package com.example.anish.assistant.myExpense.model;

import android.support.annotation.Nullable;

/**
 * Created by anish on 11-01-2017.
 */

public class MyExpenseRequest extends MyExpense {

    private long ExpenseId;
    private Double Budget;
    private String SpendOn;
    private Double Amount;
    private String Month;
    private String ExpenseDate;
    private Long ExpenseDateMili;
    private String Discription;

    public long getExpenseId() {
        return ExpenseId;
    }

    public void setExpenseId(long expenseId) {
        ExpenseId = expenseId;
    }

    public Double getBudget() {
        return Budget;
    }

    public void setBudget(Double budget) {
        Budget = budget;
    }

    public String getSpendOn() {
        return SpendOn;
    }

    public void setSpendOn(String spendOn) {
        SpendOn = spendOn;
    }

    public Double getAmount() {
        return Amount;
    }

    public void setAmount(Double amount) {
        Amount = amount;
    }

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public String getExpenseDate() {
        return ExpenseDate;
    }

    public void setExpenseDate(String expenseDate) {
        ExpenseDate = expenseDate;
    }

    public Long getExpenseDateMili() {
        return ExpenseDateMili;
    }

    public void setExpenseDateMili(Long expenseDateMili) {
        ExpenseDateMili = expenseDateMili;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    @Override
    public long ExpenseId() {
        return ExpenseId;
    }

    @Nullable
    @Override
    public Double Budget() {
        return Budget;
    }

    @Nullable
    @Override
    public String SpendOn() {
        return SpendOn;
    }

    @Nullable
    @Override
    public Double Amount() {
        return Amount;
    }

    @Nullable
    @Override
    public String Month() {
        return Month;
    }

    @Nullable
    @Override
    public String ExpenseDate() {
        return ExpenseDate;
    }

    @Nullable
    @Override
    public Long ExpenseDateMili() {
        return ExpenseDateMili;
    }

    @Nullable
    @Override
    public String Discription() {
        return Discription;
    }

    @Override
    public String toString() {
        return "MyExpense{"
                + "ExpenseId=" + ExpenseId + ", "
                + "Budget=" + Budget + ", "
                + "SpendOn=" + SpendOn + ", "
                + "Amount=" + Amount + ", "
                + "Month=" + Month + ", "
                + "ExpenseDate=" + ExpenseDate + ", "
                + "ExpenseDateMili=" + ExpenseDateMili + ", "
                + "Discription=" + Discription
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof MyExpense) {
            MyExpense that = (MyExpense) o;
            return (this.ExpenseId == that.ExpenseId())
                    && ((this.Budget == null) ? (that.Budget() == null) : this.Budget.equals(that.Budget()))
                    && ((this.SpendOn == null) ? (that.SpendOn() == null) : this.SpendOn.equals(that.SpendOn()))
                    && ((this.Amount == null) ? (that.Amount() == null) : this.Amount.equals(that.Amount()))
                    && ((this.Month == null) ? (that.Month() == null) : this.Month.equals(that.Month()))
                    && ((this.ExpenseDate == null) ? (that.ExpenseDate() == null) : this.ExpenseDate.equals(that.ExpenseDate()))
                    && ((this.ExpenseDateMili == null) ? (that.ExpenseDateMili() == null) : this.ExpenseDateMili.equals(that.ExpenseDateMili()))
                    && ((this.Discription == null) ? (that.Discription() == null) : this.Discription.equals(that.Discription()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (this.ExpenseId >>> 32) ^ this.ExpenseId;
        h *= 1000003;
        h ^= (Budget == null) ? 0 : this.Budget.hashCode();
        h *= 1000003;
        h ^= (SpendOn == null) ? 0 : this.SpendOn.hashCode();
        h *= 1000003;
        h ^= (Amount == null) ? 0 : this.Amount.hashCode();
        h *= 1000003;
        h ^= (Month == null) ? 0 : this.Month.hashCode();
        h *= 1000003;
        h ^= (ExpenseDate == null) ? 0 : this.ExpenseDate.hashCode();
        h *= 1000003;
        h ^= (ExpenseDateMili == null) ? 0 : this.ExpenseDateMili.hashCode();
        h *= 1000003;
        h ^= (Discription == null) ? 0 : this.Discription.hashCode();
        return h;
    }
}
