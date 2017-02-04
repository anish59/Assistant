package com.example.anish.assistant.myNotes;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.R;
import com.example.anish.assistant.assistantHelper.DateHelper;
import com.example.anish.assistant.assistantHelper.UIHelper;
import com.example.anish.assistant.databinding.ActivityUpdateMynoteBinding;
import com.example.anish.assistant.myNotes.model.Notes;
import com.example.anish.assistant.myNotes.model.NotesRequest;

/**
 * Created by anish on 05-12-2016.
 */

public class UpdateMyNoteActivity extends AppCompatActivity {
    ActivityUpdateMynoteBinding binding;
    private String topic, desc;
    private long noteId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_mynote);
        UIHelper.initToolbarWithBackNavigation(this,binding.toolbar,"Update Note");

        Intent intent = getIntent();
        topic = intent.getStringExtra("topic");
        desc = intent.getStringExtra("desc");
        noteId = intent.getLongExtra("noteId", 0);

        init();


    }

    private void init() {

        binding.txtTitle.setText(topic);
        binding.txtDesc.setText(desc);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.txtTitle.getText().toString().trim().equals("")
                        || binding.txtTitle.getText().toString().trim() == null) {
                    Toast.makeText(UpdateMyNoteActivity.this, "Please enter Title", Toast.LENGTH_SHORT).show();
                    return;
                }

                NotesRequest notesRequest = new NotesRequest();
                notesRequest.setNoteId(noteId);
                notesRequest.setTitle(binding.txtTitle.getText().toString().trim());
                notesRequest.setDescription(binding.txtDesc.getText().toString().trim());
                notesRequest.setNoteDate(DateHelper.getCurrentDateTime(false,false));
                notesRequest.setNoteDateMili(DateHelper.getCurrentDateTimeInMili());
                try {
                    Notes.update(notesRequest);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(UpdateMyNoteActivity.this, "Notes not updated", Toast.LENGTH_SHORT).show();
                }
                delayOneSec();
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Notes.delete(noteId);
                } catch (Exception e) {
                    Toast.makeText(UpdateMyNoteActivity.this, "Unable To Delete the Note!", Toast.LENGTH_SHORT).show();
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
        }, 1000);
    }
}
/*MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(500)
                        .cornerRadius(100) // 56 dp
                        .width(100) // 56 dp
                        .height(100) // 56 dp
                        .color(R.color.yellow) // normal state color
                        .colorPressed(R.color.colorAccent) // pressed state color
                        .icon(R.drawable.success); // icon
                binding.btnUpdate.morph(circle);
*/