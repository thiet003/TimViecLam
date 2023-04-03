package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.Model.Education;
import com.example.timviec.R;

public class EditCVEdu2Activity extends AppCompatActivity {
    EditText school,degree,startTime,endTime,desc;
    TextView cancel,save;
    TextView w1,w2,w3,w4,w5;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvedu2);
        school = findViewById(R.id.editedu_edtschool);
        degree = findViewById(R.id.editedu_edtdegree);
        startTime = findViewById(R.id.editedu_edtstarttime);
        endTime = findViewById(R.id.editedu_edtendtime);
        desc = findViewById(R.id.editedu_edtdesc);
        cancel = findViewById(R.id.editcvedu_cancel);
        save = findViewById(R.id.editcvedu_save2);
        w1 = findViewById(R.id.editcvedu_w1);
        w2 = findViewById(R.id.editcvedu_w2);
        w3 = findViewById(R.id.editcvedu_w3);
        w4 = findViewById(R.id.editcvedu_w4);
        w5 = findViewById(R.id.editcvedu_w5);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(school.getText());
                String s2 = String.valueOf(degree.getText());
                String s3 = String.valueOf(startTime.getText());
                String s4 = String.valueOf(endTime.getText());
                String s5 = String.valueOf(desc.getText());
                if(s1.isEmpty())
                {
                    w1.setVisibility(View.VISIBLE);
                    w2.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                }
                else if(s2.isEmpty())
                {
                    w2.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                }
                else if(s3.isEmpty())
                {
                    w3.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                }
                else if(s4.isEmpty())
                {
                    w4.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                }
                else if(s5.isEmpty())
                {
                    w5.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                }
                else
                {
                    Education education = new Education(s1,s3+" - "+s4,s2,s5);
                    Intent intent= new Intent(EditCVEdu2Activity.this,EditCVEduActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("edu",education);
                    intent.putExtras(bundle);
                    setResult(301,intent);
                    finish();
                }
            }
        });
    }
}