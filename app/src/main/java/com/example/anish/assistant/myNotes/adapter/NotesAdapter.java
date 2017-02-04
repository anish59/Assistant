package com.example.anish.assistant.myNotes.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.databinding.ActivityNotesItemBinding;
import com.example.anish.assistant.myNotes.model.Notes;

import java.util.List;

/**
 * Created by anish on 30-11-2016.
 */

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    ActivityNotesItemBinding binding;
    Context mContext;
    List<Notes> notesList;
    OnItemClickedListener mOnItemClickedListener;

    public interface OnItemClickedListener {
        void onItemClicked(int position);
    }

    public NotesAdapter(Context mContext, List<Notes> stringList, OnItemClickedListener mOnItemClickedListener) {
        this.mContext = mContext;
        this.notesList = stringList;
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

       /* holder.topic.setText("Topic: "+ notesList.get(position).Title());
        holder.detail.setText("Detail: "+ notesList.get(position).Description());*/

        Notes notes = notesList.get(position);
        holder.setDetails(notes,position);


    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {

        private ActivityNotesItemBinding binding;

        public NotesViewHolder(ActivityNotesItemBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
            /*topic = binding.txtTopic;
            detail = binding.txtDetail;*/
        }

        public void setDetails(Notes notes,int position) {
            binding.txtTopic.setText(notes.Title());
            binding.txtDetail.setText(notes.Description());
            binding.txtDate.setText(DateHelper.formatDate(notes.NoteDate(),DateHelper.MMM_MM_dd_yyyy_h_mm_a,DateHelper.MMMM_dd_yyyy));

      /*  Log.e("pos-=",position+"");

        Log.e("position->",notesList.get(position).NoteId()+"");*/


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
