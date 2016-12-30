package com.example.anish.assistant.myCalendar.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.databinding.ActivityNotesItemBinding;
import com.example.anish.assistant.model.Notes;
import com.example.anish.assistant.myCalendar.model.MyCalendar;

import java.util.List;

/**
 * Created by anish on 30-11-2016.
 */

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.NotesViewHolder> {
    ActivityNotesItemBinding binding;
    Context mContext;
    List<MyCalendar> eventList;
    OnItemClickedListener mOnItemClickedListener;

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }

    public EventsAdapter(Context mContext, List<MyCalendar> stringList, OnItemClickedListener mOnItemClickedListener) {
        this.mContext = mContext;
        this.eventList = stringList;
        this.mOnItemClickedListener = mOnItemClickedListener;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.activity_notes_item, parent, false);
        binding.executePendingBindings();
//        View itemView=binding.getRoot();
        return new NotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {

       /* holder.topic.setText("Topic: "+ eventList.get(position).Title());
        holder.detail.setText("Detail: "+ eventList.get(position).Description());*/

        MyCalendar events = eventList.get(position);
        holder.setDetails(events,position);


    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private ActivityNotesItemBinding binding;

        public NotesViewHolder(ActivityNotesItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            /*topic = binding.txtTopic;
            detail = binding.txtDetail;*/
        }

        public void setDetails(MyCalendar events, int position) {
            binding.txtTopic.setText(events.Title());
            binding.txtDetail.setText(events.Desctiption());
            binding.txtDate.setText(DateHelper.formatDate(events.ReminderDate(),DateHelper.MMM_MM_dd_yyyy_h_mm_a,DateHelper.MMMM_dd_yyyy));

      /*  Log.e("pos-=",position+"");

        Log.e("position->",eventList.get(position).NoteId()+"");*/


           /* binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(int position) {
                    Log.e("click", notes.Title());
                    mOnItemClickedListener.onItemClicked(position);

                }
            });
            binding.executePendingBindings();*/

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickedListener.onItemClicked(position);
                }
            });
        }
    }
}
