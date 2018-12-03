package com.owayed.kareen.kareenhackerfinal;

import android.app.DatePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

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
                String title = etTitle.getText().toString();
                String text = etText.getText().toString();
                String date = etDate.getText().toString();
                int sImportant = skbrImportant.getProgress();
                int sNecessary = skbrNecessary.getProgress();

                if (title.length() == 0) {
                    etTitle.setError("You have to write a title");
                    isok = false;
                }
                if (text.length() == 0) {
                    etText.setError("you have to write a text");
                    isok = false;
                }
                if (isok) {

                    MyTask task = new MyTask();
                    task.setDueDate(new Date(date));
                    task.setText(text);
                    task.setTirle(title);
                    task.setImportant(sImportant);
                    task.setNecessary(sNecessary);
                    FirebaseAuth auth = FirebaseAuth.getInstance();
                    task.setOwner(auth.getCurrentUser().getEmail());

                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
                    String key = reference.child("MyTask").push().getKey();
                    task.setKey(key);
                    reference.child("MyTask").child(key).setValue(task).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddTaskActivity.this, "add Successful", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(AddTaskActivity.this, "add failes", Toast.LENGTH_LONG).show();

                            }

                        }
                    });

                }
            }

            public void onClick(View view) {
                if (view == btnDatePicker) {
                    final Calendar c = Calendar.getInstance();
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int dayOfMonth, int monthOfYear, int year) {
                            etDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    },
                            mYear, mMonth, mDay);
                    datePickerDialog.show();


                }
            }
        }