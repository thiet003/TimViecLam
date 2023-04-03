package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.R;

public class EditCommunicationsActivity extends AppCompatActivity {
    private EditText name,work,pn,email,address,gender,birthday,web;
    private TextView w1,w2,w3,w4,w5,w6,w7,w8,btn_cancel,btn_save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_communications);

        name = findViewById(R.id.editcommu_edt_name);
        work = findViewById(R.id.editcommu_edt_work);
        pn = findViewById(R.id.editcommu_edt_pn);
        email = findViewById(R.id.editcommu_edt_email);
        address = findViewById(R.id.editcommu_edt_address);
        gender = findViewById(R.id.editcommu_edt_gender);
        birthday = findViewById(R.id.editcommu_edt_birthday);
        web = findViewById(R.id.editcommu_edt_web);
        w1 = findViewById(R.id.editcommu_warning_empty1);
        w2 = findViewById(R.id.editcommu_warning_empty2);
        w3 = findViewById(R.id.editcommu_warning_empty3);
        w4 = findViewById(R.id.editcommu_warning_empty4);
        w5 = findViewById(R.id.editcommu_warning_empty5);
        w6 = findViewById(R.id.editcommu_warning_empty6);
        w7 = findViewById(R.id.editcommu_warning_empty7);
        w8 = findViewById(R.id.editcommu_warning_empty8);
        btn_save = findViewById(R.id.editcommu_btn_save);
        btn_cancel = findViewById(R.id.editcommu_btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(name.getText()).isEmpty())
                {
                    w1.setVisibility(View.VISIBLE);
                    w2.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(work.getText()).isEmpty())
                {
                    w2.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(pn.getText()).isEmpty())
                {
                    w3.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(email.getText()).isEmpty())
                {
                    w4.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(address.getText()).isEmpty())
                {
                    w5.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(gender.getText()).isEmpty())
                {
                    w6.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(birthday.getText()).isEmpty())
                {
                    w7.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                    w8.setVisibility(View.GONE);
                }
                else if (String.valueOf(web.getText()).isEmpty())
                {
                    w8.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                    w3.setVisibility(View.GONE);
                    w2.setVisibility(View.GONE);
                    w5.setVisibility(View.GONE);
                    w6.setVisibility(View.GONE);
                    w7.setVisibility(View.GONE);
                    w4.setVisibility(View.GONE);
                }
                else
                {
                    Intent intent1 = new Intent(EditCommunicationsActivity.this, EditCVActivity.class);
                    Bundle bundle = new Bundle();
                    String names = String.valueOf(name.getText());
                    String works = String.valueOf(work.getText());
                    String pns = String.valueOf(pn.getText());
                    String emails = String.valueOf(email.getText());
                    String addresss = String.valueOf(address.getText());
                    String genders = String.valueOf(gender.getText());
                    String birthdays = String.valueOf(birthday.getText());
                    String webs = String.valueOf(web.getText());
                    bundle.putString("name",names);
                    bundle.putString("work",works);
                    bundle.putString("pn",pns);
                    bundle.putString("email",emails);
                    bundle.putString("address",addresss);
                    bundle.putString("gender",genders);
                    bundle.putString("birthday",birthdays);
                    bundle.putString("web",webs);
                    intent1.putExtras(bundle);
                    setResult(101,intent1);
                    finish();
                }

            }
        });
    }
}