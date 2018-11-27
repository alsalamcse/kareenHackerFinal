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

public class LogInActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private Button btnlogin, btnsignup;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPasswprd);
        btnlogin = findViewById(R.id.btnlogin);
        btnsignup = findViewById(R.id.btnsignup);
        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LogInActivity.this, StartActivity.class);
                startActivity(i);
                Intent intent = new Intent(LogInActivity.this, SignUpActivity.class);
                startActivity(intent);
                dataHandler();
                btnlogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i2 = new Intent(LogInActivity.this, SignUpActivity.class);
                        startActivity(i2);
                    }
                });


            }

            private void dataHandler() {
                boolean isok = true;
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                signIn(email, password);
                if (email.length() < 4 || email.indexOf('@') < 0 || email.indexOf('.') < 0) {
                    etEmail.setError("Wrong Email");
                    isok = false;
                }
                if (password.length() < 8) {
                    etPassword.setError("Have to be at least 8 char");
                    isok = false;
                }
            }

            private void signIn(String email, String password)
            {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(LogInActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(LogInActivity.this,"Sign in Succeeful",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(LogInActivity.this,StartActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(LogInActivity.this,"Log in Failed"+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });


    }
}
