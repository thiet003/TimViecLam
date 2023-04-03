package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.timviec.R;

public class ShowCVActivity extends AppCompatActivity {
    private ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cvactivity);
        img = findViewById(R.id.img_cv_show);
        String imagePath = getIntent().getStringExtra("image_path");
        Glide.with(this).load(imagePath).into(img);
    }
}