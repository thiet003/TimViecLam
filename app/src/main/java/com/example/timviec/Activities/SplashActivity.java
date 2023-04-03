package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.timviec.AppUlti;
import com.example.timviec.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadData();
    }

    private void loadData() {
        if(AppUlti.isNetworkAvailable(SplashActivity.this))
        {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent inten = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(inten);
                    finish();
                }
            },2000);
        }
        else
        {
            Toast.makeText(SplashActivity.this,"Không có kết nối Internet",Toast.LENGTH_SHORT).show();
        }
    }
}