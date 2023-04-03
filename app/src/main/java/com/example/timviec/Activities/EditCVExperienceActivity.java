package com.example.timviec.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.timviec.Adapter.ExperienceAdapter2;
import com.example.timviec.Model.Experience;
import com.example.timviec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVExperienceActivity extends AppCompatActivity {
    private RecyclerView rcv_exp;
    private List<Experience> mList;
    private ExperienceAdapter2 experienceAdapter;
    private TextView save,add,delete;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    Experience experience = (Experience) bundle.get("exp");
                    mList.add(experience);
                    System.out.println(mList.size());
                    experienceAdapter.setData(mList);
                    rcv_exp.setAdapter(experienceAdapter);

                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvexperience);
        rcv_exp = findViewById(R.id.editcvexp_rcvExp);
        mList = new ArrayList<>();
        experienceAdapter = new ExperienceAdapter2();
        add = findViewById(R.id.editcvexp_add);
        save = findViewById(R.id.editcvexp_save);
        delete = findViewById(R.id.editcvexp_deleteall);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Experience> list = (List<Experience>) bundle.getSerializable("exp");
        mList = list;



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_exp.setLayoutManager(linearLayoutManager);
        experienceAdapter.setData(mList);
        rcv_exp.setAdapter(experienceAdapter);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = new ArrayList<>();
                experienceAdapter.setData(mList);
                rcv_exp.setAdapter(experienceAdapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditCVExperienceActivity.this, EditCVExperience2Activity.class);
                activityResultLauncher.launch(intent1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mList!=null)
                {
                    Intent intent2 = new Intent(EditCVExperienceActivity.this, EditCVActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("expList",(Serializable) mList);
                    intent2.putExtras(bundle1);
                    setResult(106,intent2);
                    finish();
                }
            }
        });
    }
}