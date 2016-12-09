package com.example.anish.assistant.myCalendar;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.databinding.ActivityMycalendarBinding;
import com.example.anish.assistant.model.Notes;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by anish on 09-12-2016.
 */

public class MyCalendarActivity extends AppCompatActivity {

    ActivityMycalendarBinding binding;
    List<Notes> notesList, notes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mycalendar);

        notesList = Notes.getAllNotes();
        getCurrentMonth();
        Event ev;
        for (int i = 0; i < notesList.size(); i++) {
            ev = new Event(Color.GREEN, notesList.get(i).NoteDateMili());
            binding.compactCalendarView.addEvent(ev);
        }

        binding.compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
//                Toast.makeText(MyCalendarActivity.this, dateClicked+"", Toast.LENGTH_SHORT).show();
                String matchDate = DateHelper.formatDate(dateClicked, DateHelper.MMM_MM_dd_yyyy);
                notes = new ArrayList<Notes>();
                notes = Notes.getAllNotesByDate(matchDate);
                for (int i = 0; i < notes.size(); i++) {
                    Log.e("###notes:", notes.get(i) + "");
                }

            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

            }
        });
    }

    public void onPreviousMonth(View view) {
        binding.compactCalendarView.showPreviousMonth();
        getCurrentMonth();
    }

    public void onNextMonthClick(View view) {
        binding.compactCalendarView.showNextMonth();
        getCurrentMonth();
    }

    private void getCurrentMonth() {
        binding.currentMonth.setText(DateHelper.formatDate(binding.compactCalendarView.getFirstDayOfCurrentMonth(), DateHelper.MonthFormat));
        Log.e("###date->", DateHelper.formatDate(binding.compactCalendarView.getFirstDayOfCurrentMonth(), DateHelper.MonthFormat));
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