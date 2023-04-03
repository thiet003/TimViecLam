package com.example.timviec.Activities;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.provider.MediaStore;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVActivity extends AppCompatActivity {
    private TextView editcv_tv_save,editcv_tv_cancle;
    private List<Skill> mListSkill;
    private List<Education> mListEdu;
    private List<Experience> mListExp;
    private List<Activity> mListActi;
    private List<Certificate> mListPrize;
    private TextView name,work,pn,email,address,gender,birthday,web;
    private RoundedImageView imgview_cv;
    private TextView editcv_tv_target;
    private RecyclerView rcv_skill;
    private RecyclerView rcv_education;
    private RecyclerView rcv_experience;
    private RecyclerView rcv_certificate;

    private RecyclerView rcv_activity;
    private SkillAdapter skillAdapter;
    private EducationAdapter educationAdapter;
    private ExperienceAdapter experienceAdapter;
    private ActivityAdapter activityAdapter;
    private CertificateAdapter certificateAdapter;
    private TextView tv_edit_cv;
    private LinearLayout my_cv;

    //Dinalog
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==101)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        name.setText((String) bundle.get("name"));
                        work.setText((String) bundle.get("work"));
                        pn.setText((String) bundle.get("pn"));
                        address.setText((String) bundle.get("address"));
                        gender.setText((String) bundle.get("gender"));
                        birthday.setText((String) bundle.get("birthday"));
                        email.setText((String) bundle.get("email"));
                        web.setText((String) bundle.get("web"));

                        dinalog_tv_name.setText((String) bundle.get("name"));
                        dinalog_tv_work.setText((String) bundle.get("work"));
                    }
                    else if(result.getResultCode()==102)
                    {
                        Intent data = result.getData();
                        String target = (String) data.getStringExtra("target");
                        editcv_tv_target.setText(target);
                    }
                    else if(result.getResultCode()==103)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        mListSkill = (List<Skill>) bundle.get("skillList");
                        skillAdapter.setData(mListSkill);
                        rcv_skill.setAdapter(skillAdapter);
                    }
                    else if(result.getResultCode()==104)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        mListEdu = (List<Education>) bundle.get("eduList");
                        educationAdapter.setData(mListEdu);
                        rcv_education.setAdapter(educationAdapter);
                    }
                    else if(result.getResultCode()==106)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        mListExp = (List<Experience>) bundle.get("expList");
                        System.out.println("do dai tiep:"+mListExp.size());
                        experienceAdapter.setData(mListExp);
                        rcv_experience.setAdapter(experienceAdapter);
                    }
                    else if(result.getResultCode()==107)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        mListActi = (List<Activity>) bundle.get("actiList");
                        activityAdapter.setData(mListActi);
                        rcv_activity.setAdapter(activityAdapter);
                    }
                    else if(result.getResultCode()==108)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        mListPrize = (List<Certificate>) bundle.get("prizeList");
                        certificateAdapter.setData(mListPrize);
                        rcv_certificate.setAdapter(certificateAdapter);
                    }
                    else if (result.getResultCode() == RESULT_OK && result.getData() != null && result.getData().getData() != null) {
                        Uri uri = result.getData().getData();
                        // Hiển thị ảnh được chọn trên app
                        Glide.with(EditCVActivity.this).load(uri)
                        .into(imgview_cv);
                    }
                }
            });
    private ImageView img_close;
    private TextView dinalog_tv_commu,dinalog_tv_name,dinalog_tv_work;
    private TextView dinalog_tv_target, dinalog_tv_skills,dinalog_tv_exper,dinalog_tv_acti,dinalog_tv_prize,dinalog_tv_edu;
    private RoundedImageView dinalog_img;


    private LinearLayout bottomSheet;
    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvactivity);

        editcv_tv_save = findViewById(R.id.editcv_tv_save);
        editcv_tv_cancle = findViewById(R.id.editcv_tv_cancle);
        name = findViewById(R.id.editcv_tv_nameuser);
        work = findViewById(R.id.editcv_tv_jobuser);
        pn = findViewById(R.id.editcv_tv_pn);
        email = findViewById(R.id.editcv_tv_email);
        address = findViewById(R.id.editcv_tv_address);
        gender = findViewById(R.id.editcv_tv_gender);
        birthday = findViewById(R.id.editcv_tv_birthdate);
        web = findViewById(R.id.editcv_tv_linkfb);
        imgview_cv = findViewById(R.id.imgview_cv);

        editcv_tv_target = findViewById(R.id.editcv_tv_target);
        my_cv = findViewById(R.id.my_cv);


        rcv_skill = findViewById(R.id.rcv_skill);
        rcv_education = findViewById(R.id.rcv_education);
        rcv_experience = findViewById(R.id.rcv_experience);
        rcv_activity = findViewById(R.id.rcv_activity);
        rcv_certificate = findViewById(R.id.rcv_certificate);



        skillAdapter = new SkillAdapter();
        educationAdapter = new EducationAdapter();
        experienceAdapter = new ExperienceAdapter();
        activityAdapter = new ActivityAdapter();
        certificateAdapter = new CertificateAdapter();
        getListSkill();
        skillAdapter.setData(mListSkill);
        getListEducation();
        educationAdapter.setData(mListEdu);
        getListExperience();
        experienceAdapter.setData(mListExp);
        getListActivity();
        activityAdapter.setData(mListActi);
        getListCertificate();
        certificateAdapter.setData(mListPrize);
        rcv_skill.setAdapter(skillAdapter);
        rcv_education.setAdapter(educationAdapter);
        rcv_experience.setAdapter(experienceAdapter);
        rcv_activity.setAdapter(activityAdapter);
        rcv_certificate.setAdapter(certificateAdapter);

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



        tv_edit_cv = findViewById(R.id.tv_edit_cv);
        editcv_tv_cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        editcv_tv_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Tạo một file PDF mới trên bộ nhớ ngoài
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "my.pdf");

                try {
                    // Khởi tạo PdfDocument để bắt đầu ghi tài liệu PDF
                    PdfDocument pdfDocument = new PdfDocument();
                    View vieww = findViewById(R.id.my_cv);
                    PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(vieww.getWidth(), vieww.getHeight(), 1).create();
                    PdfDocument.Page page = pdfDocument.startPage(pageInfo);

                    // Vẽ View lên canvas
                    vieww.draw(page.getCanvas());

                    // Đóng trang và lưu tài liệu PDF vào file
                    pdfDocument.finishPage(page);
                    pdfDocument.writeTo(new FileOutputStream(file));
                    pdfDocument.close();

                    // Tạo Uri cho file PDF và chia sẻ tệp qua các ứng dụng khác
//                    Uri uri = FileProvider.getUriForFile(EditCVActivity.this, getApplicationContext().getPackageName() + ".provider", file);
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.setType("application/pdf");
//                    intent.putExtra(Intent.EXTRA_STREAM, uri);
//                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); // Cấp quyền truy cập tệp cho ứng dụng nhận
//                    startActivity(Intent.createChooser(intent, "Share PDF using"));
                    Uri uri = FileProvider.getUriForFile(EditCVActivity.this, getApplicationContext().getPackageName() + ".provider", file);
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setDataAndType(uri, "application/pdf");
                    intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    Toast.makeText(EditCVActivity.this,"Tạo CV thành công",Toast.LENGTH_SHORT).show();
                    startActivity(intent);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        //Dinalog
        View viewDialog = getLayoutInflater().inflate(R.layout.addcv_bottomsheet,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EditCVActivity.this);
        bottomSheetDialog.setContentView(viewDialog);
        tv_edit_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        img_close = viewDialog.findViewById(R.id.close_bottomsheetCV);
        //Xu li thong tin cho dinalog
        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.cancel();
            }
        });
        dinalog_tv_name = viewDialog.findViewById(R.id.dinalog_tv_name);
        dinalog_tv_work = viewDialog.findViewById(R.id.dinalog_tv_work);
        dinalog_tv_commu = viewDialog.findViewById(R.id.dinalog_tv_commu);
        dinalog_tv_target = viewDialog.findViewById(R.id.dinalog_tv_target);
        dinalog_tv_skills = viewDialog.findViewById(R.id.dinalog_tv_skills);
        dinalog_tv_edu = viewDialog.findViewById(R.id.dinalog_tv_edu);
        dinalog_tv_exper = viewDialog.findViewById(R.id.dinalog_tv_exper);
        dinalog_tv_acti = viewDialog.findViewById(R.id.dinalog_tv_acti);
        dinalog_tv_prize = viewDialog.findViewById(R.id.dinalog_tv_prize);
        dinalog_img = viewDialog.findViewById(R.id.dinalog_img);

        dinalog_tv_commu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(EditCVActivity.this, EditCommunicationsActivity.class);
                activityResultLauncher.launch(myintent);
            }
        });
        dinalog_tv_target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(EditCVActivity.this, EditTargetCVActivity.class);
                String target = (String) editcv_tv_target.getText();
                myintent.putExtra("target",target);
                activityResultLauncher.launch(myintent);

            }
        });
        dinalog_tv_skills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(EditCVActivity.this, EditCVSkillsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("skills", (Serializable) mListSkill);
                myintent.putExtras(bundle);
                activityResultLauncher.launch(myintent);
            }
        });
        dinalog_tv_edu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(EditCVActivity.this, EditCVEduActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("edu", (Serializable) mListEdu);
                myintent.putExtras(bundle);
                activityResultLauncher.launch(myintent);
            }
        });
        dinalog_tv_exper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(EditCVActivity.this, EditCVExperienceActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("exp", (Serializable) mListExp);
                myintent1.putExtras(bundle);
                activityResultLauncher.launch(myintent1);
            }
        });
        dinalog_tv_acti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(EditCVActivity.this, EditCVActivitiesActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("acti", (Serializable) mListActi);
                myintent1.putExtras(bundle);
                activityResultLauncher.launch(myintent1);
            }
        });
        dinalog_tv_prize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent1 = new Intent(EditCVActivity.this, EditCVPrizeActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("prize", (Serializable) mListPrize);
                myintent1.putExtras(bundle);
                activityResultLauncher.launch(myintent1);
            }
        });
        dinalog_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });
    }

    private void getListCertificate() {
        mListPrize = new ArrayList<>();
        List<Certificate> list = new ArrayList<>();
        list.add(new Certificate("2013:","Giải nhì ICPC PTIT"));
        mListPrize = list;
    }

    private void getListEducation() {
        mListEdu = new ArrayList<>();
        List<Education> list = new ArrayList<>();
        list.add(new Education("Học viện PTIT","10/2018 - 5/2023","Công nghệ thông tin",
                "Tốt nghiệp loại Giỏi, điểm trung bình 8.0"));
        mListEdu = list;
    }

    private void getListActivity() {
        mListActi = new ArrayList<>();
        List<Activity> list = new ArrayList<>();
        list.add(new Activity("CLB Lập trình PTIT","12/2021 - Hiện tại","Thành viên",
                "Tham gia học tập và hoạt động, cùng nhau phát triển\n Chia sẻ kiến thức đến " +
                        "với các bạn trong trường"));
        mListActi = list;
    }

    private void getListExperience() {
        mListExp = new ArrayList<>();
        List<Experience> list = new ArrayList<>();
        list.add(new Experience("Công ty Viettel","1/2022 - 1/2023","Kĩ sư phần mềm",
                "- Phối hợp với các bộ phận khác để đưa ra ý tưởng cho các mẫu thiết kế phần mềm, ứng dụng mới.\n" +
                        "- Xây dựng phần mềm, ứng dụng mới bằng các ngôn ngữ lập trình thích hợp.\n" +
                        "- Phát triển và xây dựng các tính năng mới cho ứng dụng."));
        list.add(new Experience("Công ty FPT Software","2/2023 - Hiện tại","Kĩ sư phần mềm",
                "- Nâng cấp phần mềm và các hệ thống để đảm bảo tính bảo mật và hiệu quả hơn.\n" +
                        "- Phối hợp với các Content/Technical Writers để viết các tài liệu hỗ trợ người dùng.\n" +
                        "- Kiểm tra và bảo trì các chương trình, ứng dụng định kỳ, tiến hành sửa lỗi khi có vấn đề xảy ra."));
        mListExp = list;
    }

    private void getListSkill() {
        mListSkill = new ArrayList<>();
        List<Skill> list = new ArrayList<>();
        list.add(new Skill("Tiếng anh","Khả năng giao tiếp tiếng anh trôi chảy"));
        list.add(new Skill("Ngôn ngữ Java","Java Swing cơ bản"));
        list.add(new Skill("Lập trình web","Thành thạo lập trình các trang web cơ bản"));
        mListSkill = list;
    }
}