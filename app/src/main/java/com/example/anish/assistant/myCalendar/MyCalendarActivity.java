package com.example.anish.assistant.myCalendar;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.databinding.ActivityMycalendarBinding;
import com.example.anish.assistant.model.Notes;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by anish on 09-12-2016.
 */

public class MyCalendarActivity extends AppCompatActivity {

    ActivityMycalendarBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mycalendar);
        List<Notes> notesList;
        notesList = Notes.getAllNotes();

        Event ev;
        for (int i = 0; i < notesList.size(); i++) {
            ev = new Event(Color.GREEN, notesList.get(i).NoteDateMili());
            binding.compactCalendarView.addEvent(ev);
        }
    }

    public void onPreviousMonth(View view) {
        binding.compactCalendarView.showPreviousMonth();
    }

    public void onNextMonthClick(View view) {
        binding.compactCalendarView.showNextMonth();
    }
}


       /* calendar.setTime(DateHelper.parseDate("Dec 12 08, 2016 12:26 AM", "MMM MM dd, yyyy h:mm a"));
        calendar.setTime(DateHelper.parseDate("Dec 12 10, 2016 12:26 AM", "MMM MM dd, yyyy h:mm a"));*/

/*
        Event ev1 = null, ev2 = null;
        try {
            ev1 = new Event(Color.GREEN, DateHelper.getTimeInMili("Dec 12 11, 2016 12:26 AM", "MMM MM dd, yyyy h:mm a"));
            ev2 = new Event(Color.GREEN, DateHelper.getTimeInMili("Dec 12 11, 2016 12:26 AM", "MMM MM dd, yyyy h:mm a"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        binding.compactCalendarView.addEvent(ev1);
        binding.compactCalendarView.addEvent(ev2);

//        Event ev2 = new Event(Color.GREEN, 1433704251000L);
//        binding.compactCalendarView.addEvent(ev2);

        binding.compactCalendarView.addEvent(new Event(Color.BLACK, calendar.getTime().getTime()));
*/