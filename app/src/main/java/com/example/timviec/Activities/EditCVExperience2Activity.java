package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.Model.Experience;
import com.example.timviec.R;

import java.io.Serializable;

public class EditCVExperience2Activity extends AppCompatActivity {
    EditText company,service,startTime,endTime,desc;
    TextView cancel,save;
    TextView w1,w2,w3,w4,w5;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvexperience2);
        company = findViewById(R.id.editexp_edtcompany);
        service = findViewById(R.id.editexp_edtservice);
        startTime = findViewById(R.id.editexp_edtstarttime);
        endTime = findViewById(R.id.editexp_edtendtime);
        desc = findViewById(R.id.editexp_edtdesc);
        cancel = findViewById(R.id.editcvexp_cancel);
        save = findViewById(R.id.editcvexp_save2);
        w1 = findViewById(R.id.editcvexp_w1);
        w2 = findViewById(R.id.editcvexp_w2);
        w3 = findViewById(R.id.editcvexp_w3);
        w4 = findViewById(R.id.editcvexp_w4);
        w5 = findViewById(R.id.editcvexp_w5);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(company.getText());
                String s2 = String.valueOf(service.getText());
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
                    Experience experience = new Experience(s1,s3+" - "+s4,s2,s5);
                    Intent intent= new Intent(EditCVExperience2Activity.this,EditCVExperienceActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("exp", (Serializable) experience);
                    intent.putExtras(bundle);
                    setResult(401,intent);
                    finish();
                }
            }
        });
    }
}