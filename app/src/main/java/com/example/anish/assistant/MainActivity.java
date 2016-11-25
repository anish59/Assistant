package com.example.anish.assistant;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.anish.assistant.databinding.ActivityMainBinding;
import com.example.anish.assistant.model.Notes;
import com.example.anish.assistant.model.NotesRequest;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    NotesRequest notesRequest;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //binding.txtTitle.getText().toString();
                /*notesRequest=new NotesRequest();
                notesRequest.setTitle(binding.txtTitle.getText().toString());
                notesRequest.setDescription(binding.txtDesc.getText().toString());
                Notes.insertInNotes(notesRequest);
                Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();*/

                if((binding.txtTitle.getText().toString().trim()).matches(emailPattern))
                {
                    Toast.makeText(MainActivity.this, "Valid Email", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this, "invalid Email", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
