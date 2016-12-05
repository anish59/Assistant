package com.example.anish.assistant.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityNotesItemBinding;
import com.example.anish.assistant.model.Notes;

import java.util.List;

/**
 * Created by anish on 30-11-2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    ActivityNotesItemBinding binding;
    Context mContext;
    List<Notes> notesList;

    public NotesAdapter(Context mContext, List<Notes> stringList) {
        this.mContext = mContext;
        this.notesList = stringList;
    }

    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        binding= DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.activity_notes_item,parent,false);
        View itemView=binding.getRoot();
        return new NotesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.topic.setText("Topic: "+ notesList.get(position).Title());
        holder.detail.setText("Detail: "+ notesList.get(position).Description());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {


        TextView topic, detail;

        public NotesViewHolder(View itemView) {
            super(itemView);
            topic = binding.txtTopic;
            detail = binding.txtDetail;
        }
    }
}
