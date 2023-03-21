package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.R;

public class Signup_Activity extends AppCompatActivity {
    private EditText  edt_username_signup,edt_email_signup,edt_password_signup;
    private Button signup_btn;
    private TextView to_login_txt;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //Anh xa;
        edt_username_signup = findViewById(R.id.edt_username_signup);
        edt_email_signup = findViewById(R.id.edt_email_signup);
        edt_password_signup = findViewById(R.id.edt_password_signup);
        signup_btn = findViewById(R.id.btn_signup);
        to_login_txt = findViewById(R.id.to_login_txt);
        to_login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToLogin = new Intent(Signup_Activity.this, LoginActivity.class);
                startActivity(intentToLogin);
            }
        });
    }
}