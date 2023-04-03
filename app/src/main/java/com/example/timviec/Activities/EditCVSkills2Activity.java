package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.Model.Skill;
import com.example.timviec.R;

import java.io.Serializable;

public class EditCVSkills2Activity extends AppCompatActivity {
    private EditText name,desc;
    TextView cancle, save,w1,w2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvskills2);
        name = findViewById(R.id.editcv_skill_edtname);
        desc = findViewById(R.id.editcv_skill_edtdesc);
        cancle = findViewById(R.id.editcvskill_cancel);
        save = findViewById(R.id.editcvskill_save2);
        w1 = findViewById(R.id.editcvskill_w1);
        w2 = findViewById(R.id.editcvskill_w2);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = String.valueOf(name.getText());
                String s2 = String.valueOf(desc.getText());
                Skill skill = new Skill(s1,s2);
                System.out.println(skill.getDesc_skill());
                if(s1.isEmpty())
                {
                    w1.setVisibility(View.VISIBLE);
                    w2.setVisibility(View.GONE);
                }
                else if(s2.isEmpty())
                {
                    w2.setVisibility(View.VISIBLE);
                    w1.setVisibility(View.GONE);
                }
                else
                {
                    Intent intent = new Intent(EditCVSkills2Activity.this,EditCVSkillsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("skilll",(Serializable) skill);
                    intent.putExtras(bundle);
                    setResult(201,intent);
                    finish();
                }
            }
        });
    }
}