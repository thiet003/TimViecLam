package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.Model.Activity;
import com.example.timviec.R;

import java.io.Serializable;

public class EditCVActivities2Activity extends AppCompatActivity {
    EditText name,role,startTime,endTime,desc;
    TextView cancel,save;
    TextView w1,w2,w3,w4,w5;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvactivities2);
        name = findViewById(R.id.editacti_edtname);
        role = findViewById(R.id.editacti_edtrole);
        startTime = findViewById(R.id.editacti_edtstarttime);
        endTime = findViewById(R.id.editacti_edtendtime);
        desc = findViewById(R.id.editacti_edtdesc);
        cancel = findViewById(R.id.editcvacti_cancel);
        save = findViewById(R.id.editcvacti_save2);
        w1 = findViewById(R.id.editcvacti_w1);
        w2 = findViewById(R.id.editcvacti_w2);
        w3 = findViewById(R.id.editcvacti_w3);
        w4 = findViewById(R.id.editcvacti_w4);
        w5 = findViewById(R.id.editcvacti_w5);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(name.getText());
                String s2 = String.valueOf(role.getText());
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
                    Activity activity = new Activity(s1,s3+" - "+s4,s2,s5);
                    Intent intent= new Intent(EditCVActivities2Activity.this,EditCVActivitiesActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("acti", (Serializable) activity);
                    intent.putExtras(bundle);
                    setResult(501,intent);
                    finish();
                }
            }
        });
    }
}