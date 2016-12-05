package com.example.anish.assistant.myNotes;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dd.morphingbutton.MorphingButton;
import com.example.anish.assistant.R;
import com.example.anish.assistant.databinding.ActivityAddnoteBinding;
import com.example.anish.assistant.databinding.ActivityUpdateNoteBinding;

/**
 * Created by anish on 05-12-2016.
 */

public class UpdateMyNoteActivity extends AppCompatActivity {
    ActivityAddnoteBinding binding;
    private String topic,desc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_addnote);

        Intent intent = getIntent();
        String status = intent.getStringExtra("status");
        topic = intent.getStringExtra("topic");
        desc = intent.getStringExtra("desc");

//        if (status.equals("update")) {
            binding.insert.setVisibility(View.GONE);
            binding.btnUpdate.setVisibility(View.VISIBLE);
            binding.btnDelete.setVisibility(View.VISIBLE);
            init();
//        }


    }

    private void init() {

        binding.txtTitle.setText(topic);
        binding.txtDesc.setText(desc);

        binding.btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
            }
        });

        binding.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
  /*              MorphingButton.Params circle = MorphingButton.Params.create()
                        .duration(500)
                        .cornerRadius(100) // 56 dp
                        .width(100) // 56 dp
                        .height(100) // 56 dp
                        .color(R.color.yellow) // normal state color
                        .colorPressed(R.color.colorAccent) // pressed state color
                        .icon(R.drawable.success); // icon
                binding.btnDelete.morph(circle);
*/
            }
        });

    }
}
