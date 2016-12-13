package com.example.anish.assistant.myNotes;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityAddnoteBinding;
import com.example.anish.assistant.model.Notes;
import com.example.anish.assistant.model.NotesRequest;

import java.text.SimpleDateFormat;

public class AddNoteActivity extends AppCompatActivity {
    ActivityAddnoteBinding binding;
    NotesRequest notesRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_addnote);
        UIHelper.initToolbarWithBackNavigation(this,binding.toolbar,"Add Notes");
        binding.insert.setVisibility(View.GONE);

        binding.txtTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                boolean isReady = binding.insert.getText().toString().length()>3;
                if(isReady)
                {
                    binding.insert.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }

        });
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                notesRequest.setNoteDate(DateHelper.getCurrentDateTime());
                notesRequest.setNoteDateMili(DateHelper.getCurrentDateTimeInMili());


                try {
                    Notes.insertInNotes(notesRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(AddNoteActivity.this, "Sorry your note was not saved!!", Toast.LENGTH_SHORT).show();
                }
                delayOneSec();
            }

        });
    }

    private void delayOneSec() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        }, 1500);
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
