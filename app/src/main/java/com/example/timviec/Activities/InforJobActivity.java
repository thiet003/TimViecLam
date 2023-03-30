package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.timviec.JobSingleton;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobFavorite;
import com.example.timviec.R;
import com.example.timviec.database.JobFavoriteDatabase;
import com.example.timviec.my_interface.IClickRecuimentJob;

import java.util.List;

public class InforJobActivity extends AppCompatActivity {
    private ImageView back,tym,salary_icon,form_icon,cnt_icon,edu_icon,address_icon;
    private TextView salary,form,cnt,edu,address,desc,request,benefit,tWeb_btn;
    private TextView name,company;
    private ImageView img;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_job);
        tWeb_btn = findViewById(R.id.tWeb_btn);
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
        String url = (String) bundle.get("url");
        boolean checker = (boolean) bundle.get("checker");
        System.out.println(checker);
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
        Job newJob = new Job(job.getResourceId(),url, job.getName(), job.getSalary(), job.getAddress(), job.getCompany(),job.getUpdateTime());
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (checker==true)
        {
            tym.setColorFilter(Color.parseColor("#db6b8d"));
            tym.setImageResource(R.drawable.favorite_icon);
        }
        else
        {
            tym.setColorFilter(Color.parseColor("#db6b8d"));
            tym.setImageResource(R.drawable.favorite_border_icon);
        }
        tWeb_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JobSingleton.getInstance().setJob(newJob);
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(url));
                startActivity(intent1);

            }
        });
//        tym.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (checker==true)
//                {
//                    tym.setColorFilter(Color.parseColor("#db6b8d"));
//                    tym.setImageResource(R.drawable.favorite_border_icon);
//                    JobFavoriteDatabase.getInstance(InforJobActivity.this).jobFavoriteDAO().deleteFavoriteJob(job.getLink());
//                }
//                else
//                {
//                    tym.setColorFilter(Color.parseColor("#db6b8d"));
//                    tym.setImageResource(R.drawable.favorite_icon);
//                    JobFavorite jobFavorite = new JobFavorite(job.getLink(), job.getResourceId(), job.getName(), job.getSalary(), job.getAddress(), job.getCompany(), job.getUpdateTime());
//                    JobFavoriteDatabase.getInstance(InforJobActivity.this).jobFavoriteDAO().insertFavoriteJob(jobFavorite);
//                }
//            }
//        });



    }
}