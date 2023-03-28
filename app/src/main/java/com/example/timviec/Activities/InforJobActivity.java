package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.timviec.Model.Job;
import com.example.timviec.R;

public class InforJobActivity extends AppCompatActivity {
    ImageView back,tym,salary_icon,form_icon,cnt_icon,edu_icon,address_icon;
    TextView salary,form,cnt,edu,address,desc,request,benefit;
    TextView name,company;
    ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_job);
        img = findViewById(R.id.infor_img);
        name = findViewById(R.id.infor_tv_name);
        company = findViewById(R.id.infor_tv_company);
        back = findViewById(R.id.fromInforToHome);
        tym = findViewById(R.id.favorite_infor_icon);
        salary_icon = findViewById(R.id.img_info_salary);
        form_icon = findViewById(R.id.img_infor_form);
        cnt_icon = findViewById(R.id.img_infor_cnt);
        edu_icon = findViewById(R.id.img_infor_edu);
        address_icon = findViewById(R.id.img_infor_address);
        salary = findViewById(R.id.infor_salary);
        form = findViewById(R.id.infor_tv_form);
        cnt = findViewById(R.id.infor_tv_cnt);
        desc = findViewById(R.id.infor_tv_desc);
        edu = findViewById(R.id.infor_tv_edu);
        address = findViewById(R.id.infor_tv_addess);
        request = findViewById(R.id.infor_tv_request);
        benefit = findViewById(R.id.tv_infor_benefit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Job job = (Job) bundle.get("jobs");
        Glide.with(img.getContext())
                        .load(job.getResourceId())
                                .into(img);
        name.setText(job.getName());
        company.setText(job.getCompany());
        salary.setText(job.getSalary());
        if(job.getJdetail().getForm()!=null)
        {
            form.setText(job.getJdetail().getForm());
        }
        cnt.setText(job.getJdetail().getCount());
        edu.setText(job.getJdetail().getEducation());
        address.setText(job.getJdetail().getAdressDetail());
        desc.setText(job.getJdetail().getDesc());
        request.setText(job.getJdetail().getRequest());
        benefit.setText(job.getJdetail().getBenefit());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if(job.getIsFavorite()==true)
        {
            tym.setColorFilter(Color.parseColor("#db6b8d"));
            tym.setImageResource(R.drawable.favorite_icon);
            //iClickFavoriteJobListener.onClickFavoriteJob(holder.img_favorite_job, job);
        }
        else
        {
            tym.setColorFilter(Color.parseColor("#db6b8d"));
            tym.setImageResource(R.drawable.favorite_border_icon);
            //job.setFavorite(false);
        }
        tym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(job.getIsFavorite()==false)
                {
                    job.setFavorite(true);
                    tym.setColorFilter(Color.parseColor("#db6b8d"));
                    tym.setImageResource(R.drawable.favorite_icon);
                    //iClickFavoriteJobListener.onClickFavoriteJob(holder.img_favorite_job, job);
                }
                else
                {
                    tym.setColorFilter(Color.parseColor("#db6b8d"));
                    tym.setImageResource(R.drawable.favorite_border_icon);
                    job.setFavorite(false);
                }
            }
        });



    }
}