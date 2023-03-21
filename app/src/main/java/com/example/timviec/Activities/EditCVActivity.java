package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Adapter;

import com.example.timviec.Adapter.ActivityAdapter;
import com.example.timviec.Adapter.CertificateAdapter;
import com.example.timviec.Adapter.EducationAdapter;
import com.example.timviec.Adapter.ExperienceAdapter;
import com.example.timviec.Adapter.SkillAdapter;
import com.example.timviec.Model.Activity;
import com.example.timviec.Model.Certificate;
import com.example.timviec.Model.Education;
import com.example.timviec.Model.Experience;
import com.example.timviec.Model.Skill;
import com.example.timviec.R;

import java.util.ArrayList;
import java.util.List;

public class EditCVActivity extends AppCompatActivity {
    private RecyclerView rcv_skill;
    private RecyclerView rcv_education;
    private RecyclerView rcv_experience;
    private RecyclerView rcv_certificate;
    private SkillAdapter skillAdapter;
    private EducationAdapter educationAdapter;
    private ExperienceAdapter experienceAdapter;
    private ActivityAdapter activityAdapter;
    private CertificateAdapter certificateAdapter;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvactivity);
        rcv_skill = findViewById(R.id.rcv_skill);
        rcv_education = findViewById(R.id.rcv_education);
        rcv_experience = findViewById(R.id.rcv_experience);
        RecyclerView rcv_activity = findViewById(R.id.rcv_activity);
        rcv_certificate = findViewById(R.id.rcv_certificate);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_skill.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager3 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager4 = new LinearLayoutManager(this);
        LinearLayoutManager linearLayoutManager5 = new LinearLayoutManager(this);

        rcv_education.setLayoutManager(linearLayoutManager2);
        rcv_experience.setLayoutManager(linearLayoutManager3);
        rcv_activity.setLayoutManager(linearLayoutManager4);
        rcv_certificate.setLayoutManager(linearLayoutManager5);

        skillAdapter = new SkillAdapter();
        educationAdapter = new EducationAdapter();
        experienceAdapter = new ExperienceAdapter();
        activityAdapter = new ActivityAdapter();
        certificateAdapter = new CertificateAdapter();

        skillAdapter.setData(getListSkill());
        educationAdapter.setData(getListEducation());
        experienceAdapter.setData(getListExperience());
        activityAdapter.setData(getListActivity());
        certificateAdapter.setData(getListCertificate());

        rcv_skill.setAdapter(skillAdapter);
        rcv_education.setAdapter(educationAdapter);
        rcv_experience.setAdapter(experienceAdapter);
        rcv_activity.setAdapter(activityAdapter);
        rcv_certificate.setAdapter(certificateAdapter);

    }

    private List<Certificate> getListCertificate() {
        List<Certificate> list = new ArrayList<>();
        list.add(new Certificate("2013:","Giải nhì ICPC PTIT"));
        return list;
    }

    private List<Education> getListEducation() {
        List<Education> list = new ArrayList<>();
        list.add(new Education("Học viện PTIT","10/2018 - 5/2023","Quản trị doanh nghiệp",
                "Tốt nghiệp loại Giỏi, điểm trung bình 8.0"));
        return list;
    }

    private List<Activity> getListActivity() {
        List<Activity> list = new ArrayList<>();
        list.add(new Activity("Thành viên CLB Lập trình PTIT","12/2021 - Hiện tại","Thành viên",
                "Tham gia học tập và hoạt động, cùng nhau phát triển\n Chia sẻ kiến thức đến " +
                        "với các bạn trong trường"));
        return list;
    }

    private List<Experience> getListExperience() {
        List<Experience> list = new ArrayList<>();
        list.add(new Experience("Công ty Viettel","10/2022 - 1/2023","Nhân viên bán hàng",
                "-Hỗ trợ bài viết quảng cáo sản phẩm qua kênh facebook, các forum,...\n " +
                        "-Giới thiệu, tư vấn sản phẩm, giải đáp các vấn đề thắc mắc của khách hàng qua điện thoại và email."));
        list.add(new Experience("Công ty Viettel","10/2022 - 1/2023","Nhân viên bán hàng",
                "-Hỗ trợ bài viết quảng cáo sản phẩm qua kênh facebook, các forum,...\n " +
                        "-Giới thiệu, tư vấn sản phẩm, giải đáp các vấn đề thắc mắc của khách hàng qua điện thoại và email."));
        return list;
    }

    private List<Skill> getListSkill() {
        List<Skill> list = new ArrayList<>();
        list.add(new Skill("Tin học văn phòng","-Sử dụng thành thạo các công cụ Word,Excel,PowerPoint"));
        list.add(new Skill("Tiếng anh","-Khả năng giao tiếp tiếng anh trôi chảy"));
        list.add(new Skill("Ngôn ngữ Java","-Java Swing cơ bản"));
        return list;
    }
}