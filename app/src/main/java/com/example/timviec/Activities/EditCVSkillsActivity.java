package com.example.timviec.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.timviec.Adapter.SkillAdapter2;
import com.example.timviec.Model.Skill;
import com.example.timviec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVSkillsActivity extends AppCompatActivity {
    private RecyclerView rcv_skills;
    private List<Skill> mList;
    private SkillAdapter2 skillAdapter;
    private TextView save,add,delete;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==201)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        Skill skill = (Skill) bundle.get("skilll");
                        mList.add(skill);
                        skillAdapter.setData(mList);
                        rcv_skills.setAdapter(skillAdapter);
                    }
                }
            });
    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvskills);
        rcv_skills = findViewById(R.id.editcvskill_rcvSkills);
        mList = new ArrayList<>();
        skillAdapter = new SkillAdapter2();
        add = findViewById(R.id.editcvskill_addskill);
        save = findViewById(R.id.editcvskills_save);
        delete = findViewById(R.id.editcvskill_deleteall);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_skills.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Skill> list = (List<Skill>) bundle.getSerializable("skills");
        mList = list;

        skillAdapter.setData(mList);
        rcv_skills.setAdapter(skillAdapter);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = new ArrayList<>();
                skillAdapter.setData(mList);
                rcv_skills.setAdapter(skillAdapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditCVSkillsActivity.this, EditCVSkills2Activity.class);
                activityResultLauncher.launch(intent1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mList!=null)
                {
                    Intent intent2 = new Intent(EditCVSkillsActivity.this,EditCVActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle.putSerializable("skillList",(Serializable) mList);
                    intent2.putExtras(bundle);
                    setResult(103,intent2);
                    finish();
                }
            }
        });


    }
}