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

public class LoginActivity extends AppCompatActivity {
    private EditText edt_user_login, edt_password_login;
    private Button login_btn;
    private TextView toSignupText,msg_login_error;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Anh xa view
        edt_user_login = findViewById(R.id.edt_username_login);
        edt_password_login =findViewById(R.id.edt_password_login);
        login_btn = findViewById(R.id.btn_login);
        toSignupText = findViewById(R.id.to_signup_text);
        msg_login_error = findViewById(R.id.msg_login_error);
        toSignupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToSignUp = new Intent(LoginActivity.this, Signup_Activity.class);
                startActivity(intentToSignUp);
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToMain = new Intent(LoginActivity.this, MainActivity.class);
                String username = edt_user_login.getText().toString();
                String password = edt_password_login.getText().toString();
                if(checkLogin(username,password)==true)
                {
                    startActivity(intentToMain);
                }
                else
                {
                    msg_login_error.setText("Tên đăng nhập hoặc mật khẩu không đúng!");
                }
            }
        });
    }
    private boolean checkLogin(String username, String password)
    {
        if (username.equals("admin") && password.equals("123456")) {
            return true;
        } else {
            return false;
        }
    }

}