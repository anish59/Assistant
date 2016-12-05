package com.example.anish.assistant.myNotes;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityAddnoteBinding;
import com.example.anish.assistant.model.Notes;
import com.example.anish.assistant.model.NotesRequest;

public class AddNoteActivity extends AppCompatActivity {
    ActivityAddnoteBinding binding;
    NotesRequest notesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addnote);
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                binding.txtTitle.getText().toString();
                if (binding.txtTitle.getText().toString().trim().equals("")
                        || binding.txtTitle.getText().toString().trim() == null) {
                    Toast.makeText(AddNoteActivity.this, "Please Enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (binding.txtDesc.getText().toString().trim().equals("")
                        || binding.txtDesc.getText().toString().trim() == null) {
                    Toast.makeText(AddNoteActivity.this, "Please Enter Description", Toast.LENGTH_SHORT).show();
                    return;
                }
                notesRequest = new NotesRequest();
                notesRequest.setTitle(binding.txtTitle.getText().toString());
                notesRequest.setDescription(binding.txtDesc.getText().toString());
                try {
                    Notes.insertInNotes(notesRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AddNoteActivity.this, "Sorry your note was not saved!!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
/*
//    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if((binding.txtTitle.getText().toString().trim()).matches(emailPattern))
                {
                    Toast.makeText(AddNoteActivity.this, "Valid Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(AddNoteActivity.this, "invalid Email", Toast.LENGTH_SHORT).show();
                }
*/
