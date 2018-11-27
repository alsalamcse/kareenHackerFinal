package com.owayed.kareen.kareenhackerfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etTitle,etText,etDate;
    private TextView tvImportant,tvNecessary;
    private SeekBar skbrImportant,skbrNecessary;
    private Button btnSave,btnDatePicker;
    private int mYear,mMonth,mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etTitle = findViewById(R.id.etTitle);
        etText = findViewById(R.id.etText);
        tvImportant = findViewById(R.id.tvImportant);
        tvNecessary = findViewById(R.id.tvNecessary);
        skbrImportant = findViewById(R.id.skbrImportant);
        skbrNecessary = findViewById(R.id.skbrNecessary);
        etDate = findViewById(R.id.etDate);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
            }

            private void dataHandler() {
                boolean isok = true;
                String title= etTitle.getText().toString();
                String text = etText.getText().toString();
                String date = etDate.getText().toString();
                int sImportant=skbrImportant.getProgress();
                int sNecessary=skbrNecessary.getProgress();

                if (title.length() ==0) {
                    etTitle.setError("You have to write a title");
                    isok = false;
                }
                if (text.length()==0) {
                    etText.setError("you have to write a text");
                    isok = false;
                }
            }
        });

    }
}
