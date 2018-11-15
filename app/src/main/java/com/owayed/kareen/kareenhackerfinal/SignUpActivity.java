package com.owayed.kareen.kareenhackerfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {
    private EditText etFirst,etLast,etPhone,etEmail,etPassword;
    private Button btnsave;
    FirebaseAuth auth;
    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etFirst = findViewById(R.id.etFirst);
        etLast = findViewById(R.id.etLast);
        etPhone = findViewById(R.id.etPhone);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnsave = findViewById(R.id.btnSave);
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(SignUpActivity.this,StartActivity.class);
                startActivity(i2);
                dataHandler();
            }
        });
    }

    private void dataHandler() {
        boolean isok = true;
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String fname = etFirst.getText().toString();
        String lname = etLast.getText().toString();
        String phone = etPhone.getText().toString();
        if (email.length() < 4 || email.indexOf('@') < 0 || email.indexOf('.') < 0) {
            etEmail.setError("Wrong Email");
            isok = false;
        }
        if (password.length() < 8) {
            etPassword.setError("Have to be at least 8 char");
            isok = false;
        }
        if (isok) {
            creatAcount(email, password);
        }
    }


        private void creatAcount(String email, String password)
        {
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignUpActivity.this, "Authentication successful", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Authenication faild" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        task.getException().printStackTrace();
                    }


                }
            });
}

    }