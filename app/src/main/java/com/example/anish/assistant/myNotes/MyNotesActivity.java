package com.example.anish.assistant.myNotes;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.example.anish.assistant.R;
import com.example.anish.assistant.myNotes.adapter.NotesAdapter;
import com.example.anish.assistant.assistantHelper.SimpleDividerItemDecoration;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityNotesListBinding;
import com.example.anish.assistant.myNotes.model.Notes;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anish on 30-11-2016.
 */

public class MyNotesActivity extends AppCompatActivity {
    List<Notes> notesList;
    Notes notes = null;
    ActivityNotesListBinding binding;
    NotesAdapter notesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_notes_list);
        UIHelper.initToolbarWithBackNavigation(this,binding.toolbar,"My Notes");
        init();
    }

    private void init() {
        notesList = new ArrayList<>();
        notesList = Notes.getAllNotes();
        binding.rvNotes.setLayoutManager(new LinearLayoutManager(this));
        Log.e("size", notesList.size() + "");
        binding.rvNotes.addItemDecoration(new SimpleDividerItemDecoration(this));
        binding.rvNotes.setItemViewCacheSize(0);
        notesAdapter = new NotesAdapter(this, notesList, new NotesAdapter.OnItemClickedListener() {
            @Override
            public void onItemClicked(int position) {
                Intent intent = new Intent(MyNotesActivity.this, UpdateMyNoteActivity.class);
                String topic = notesList.get(position).Title();
                String desc = notesList.get(position).Description();
                Long noteId = notesList.get(position).NoteId();

                intent.putExtra("topic", topic);
                intent.putExtra("desc", desc);
                intent.putExtra("noteId", noteId);
                intent.putExtra("status", "status");
                Log.e("##-pos->", position + " " + topic);
                Log.e("##-id->", notesList.get(position).NoteId() + "");
                startActivity(intent);
            }
        });
        binding.rvNotes.setAdapter(notesAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        notesList.clear();
        notesList.addAll(notes.getAllNotes());
        notesAdapter.notifyDataSetChanged();
    }

    public void openAddNote(View view) {
        Intent intent = new Intent(MyNotesActivity.this, AddNoteActivity.class);
        startActivity(intent);
    }
}
