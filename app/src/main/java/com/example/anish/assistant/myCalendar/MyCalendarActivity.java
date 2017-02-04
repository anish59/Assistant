package com.example.anish.assistant.myCalendar;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.IntentConstants;
import com.example.anish.assistant.assistantHelper.SimpleDividerItemDecoration;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityMycalendarBinding;
import com.example.anish.assistant.myCalendar.adapters.EventsAdapter;
import com.example.anish.assistant.myCalendar.model.MyCalendar;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by anish on 09-12-2016.
 */

public class MyCalendarActivity extends AppCompatActivity {

    ActivityMycalendarBinding binding;
    List<MyCalendar> eventLists, events;
    EventsAdapter eventsAdapter;

    @Override
    protected void onResume() {
        super.onResume();
    /*    if(events.size()>0)
        {
            events.clear();
        }*/
        if (eventLists == null) {
            eventLists = MyCalendar.getAllEvents();
            addEventsOnCalendar(eventLists);

        } else {
            eventLists.clear();//clearing past list value
            binding.includeMyCal.compactCalendarView.removeAllEvents();
            eventLists = MyCalendar.getAllEvents();
            addEventsOnCalendar(eventLists);
        }

        getEventOnDate(binding.includeMyCal.compactCalendarView.getCurrentDate());
        eventsAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mycalendar);
        UIHelper.initToolbarWithBackNavigation(this, binding.toolbar, "My Calendar");


        getMonth();
//        addEventsOnCalendar();

        binding.includeMyCal.compactCalendarView.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                getEventOnDate(dateClicked);
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                getMonth();
            }
        });
    }

    private void addEventsOnCalendar(List<MyCalendar> notesList) {
        Event ev;
        for (int i = 0; i < notesList.size(); i++) {
            ev = new Event(Color.GREEN, notesList.get(i).ReminderDateMili());
            binding.includeMyCal.compactCalendarView.addEvent(ev);

        }
    }

    private void getEventOnDate(Date dateSelected) {
        String matchDate = DateHelper.formatDate(dateSelected, DateHelper.MMM_MM_dd_yyyy);
        events = new ArrayList<MyCalendar>();
        events = MyCalendar.getAllEventsDateWise(matchDate);
        for (int i = 0; i < events.size(); i++) {
            Log.e("###events:", events.get(i) + "");
        }

        binding.includeMyCal.rvEventList.setLayoutManager(new LinearLayoutManager(MyCalendarActivity.this));
        binding.includeMyCal.rvEventList.addItemDecoration(new SimpleDividerItemDecoration(MyCalendarActivity.this));
        binding.includeMyCal.rvEventList.setItemViewCacheSize(0);
        eventsAdapter = new EventsAdapter(MyCalendarActivity.this, events, new EventsAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(MyCalendarActivity.this, UpdateCalendarEventActivity.class);
//                String topic = events.get(position).Title();
//                String desc = events.get(position).Desctiption();
//                String reminderDate = events.get(position).ReminderDate();
                long eventId = events.get(position).EventId();

                intent.putExtra(IntentConstants.eventId, eventId);
//                intent.putExtra(IntentConstants.topic, topic);
//                intent.putExtra(IntentConstants.desc, desc);
//                intent.putExtra(IntentConstants.reminderDate, reminderDate);
                intent.putExtra("status", "status");
                startActivity(intent);
            }
        });
        binding.includeMyCal.rvEventList.setAdapter(eventsAdapter);
    }

    public void onPreviousMonth(View view) {
        binding.includeMyCal.compactCalendarView.showPreviousMonth();
        getMonth();
    }

    public void onNextMonthClick(View view) {
        binding.includeMyCal.compactCalendarView.showNextMonth();
        getMonth();
    }

    private void getMonth() {
        binding.includeMyCal.currentMonth.setText(DateHelper.formatDate(binding.includeMyCal.compactCalendarView.getFirstDayOfCurrentMonth(), DateHelper.MonthFormat));
        Log.e("###date->", DateHelper.formatDate(binding.includeMyCal.compactCalendarView.getFirstDayOfCurrentMonth(), DateHelper.MonthFormat));
    }

    public void onDateClicked(View view) {

    }

    public void openAddEvent(View view) {
        startActivity(new Intent(this, AddCalendarEventActivity.class));
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
        binding.compactCalendarView.clickAddEvent(ev1);
        binding.compactCalendarView.clickAddEvent(ev2);

//        Event ev2 = new Event(Color.GREEN, 1433704251000L);
//        binding.compactCalendarView.clickAddEvent(ev2);

        binding.compactCalendarView.clickAddEvent(new Event(Color.BLACK, calendar.getTime().getTime()));
*/