package com.example.anish.assistant.myCalendar.model;

import android.support.annotation.Nullable;

/**
 * Created by anish on 29-12-2016.
 */

public class MyCalRequest extends MyCalendar {

    private long EventId;
    private String Title;
    private String Desctiption;
    private String ReminderDate;
    private Long ReminderDateMili;

    public long getEventId() {
        return EventId;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDesctiption() {
        return Desctiption;
    }

    public void setDesctiption(String desctiption) {
        Desctiption = desctiption;
    }

    public String getReminderDate() {
        return ReminderDate;
    }

    public void setReminderDate(String reminderDate) {
        ReminderDate = reminderDate;
    }

    public Long getReminderDateMili() {
        return ReminderDateMili;
    }

    public void setReminderDateMili(Long reminderDateMili) {
        ReminderDateMili = reminderDateMili;
    }

    @Override
    public long EventId() {
        return EventId;
    }

    @Nullable
    @Override
    public String Title() {
        return Title;
    }

    @Nullable
    @Override
    public String Desctiption() {
        return Desctiption;
    }

    @Nullable
    @Override
    public String ReminderDate() {
        return ReminderDate;
    }

    @Nullable
    @Override
    public Long ReminderDateMili() {
        return ReminderDateMili;
    }

    @Override
    public String toString() {
        return "MyCalendar{"
                + "EventId=" + EventId + ", "
                + "Title=" + Title + ", "
                + "Desctiption=" + Desctiption + ", "
                + "ReminderDate=" + ReminderDate + ", "
                + "ReminderDateMili=" + ReminderDateMili
                + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof MyCalendar) {
            MyCalendar that = (MyCalendar) o;
            return (this.EventId == that.EventId())
                    && ((this.Title == null) ? (that.Title() == null) : this.Title.equals(that.Title()))
                    && ((this.Desctiption == null) ? (that.Desctiption() == null) : this.Desctiption.equals(that.Desctiption()))
                    && ((this.ReminderDate == null) ? (that.ReminderDate() == null) : this.ReminderDate.equals(that.ReminderDate()))
                    && ((this.ReminderDateMili == null) ? (that.ReminderDateMili() == null) : this.ReminderDateMili.equals(that.ReminderDateMili()));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= (this.EventId >>> 32) ^ this.EventId;
        h *= 1000003;
        h ^= (Title == null) ? 0 : this.Title.hashCode();
        h *= 1000003;
        h ^= (Desctiption == null) ? 0 : this.Desctiption.hashCode();
        h *= 1000003;
        h ^= (ReminderDate == null) ? 0 : this.ReminderDate.hashCode();
        h *= 1000003;
        h ^= (ReminderDateMili == null) ? 0 : this.ReminderDateMili.hashCode();
        return h;
    }


}
