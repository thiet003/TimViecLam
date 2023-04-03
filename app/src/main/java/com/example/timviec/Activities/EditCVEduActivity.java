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

import com.example.timviec.Adapter.EducationAdapter2;
import com.example.timviec.Model.Education;
import com.example.timviec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVEduActivity extends AppCompatActivity {
    private RecyclerView rcv_edu;
    private List<Education> mList;
    private EducationAdapter2 eduAdapter;
    private TextView save,add,delete;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    Education education = (Education) bundle.get("edu");
                    mList.add(education);
                    eduAdapter.setData(mList);
                    rcv_edu.setAdapter(eduAdapter);

                }
            });
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvedu);
        rcv_edu = findViewById(R.id.editcvskill_rcvEdu);
        mList = new ArrayList<>();
        eduAdapter = new EducationAdapter2();
        add = findViewById(R.id.editcvedu_add);
        save = findViewById(R.id.editcvedu_save);
        delete = findViewById(R.id.editcvedu_deleteall);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Education> list = (List<Education>) bundle.getSerializable("edu");
        mList = list;

        eduAdapter.setData(mList);
        rcv_edu.setAdapter(eduAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_edu.setLayoutManager(linearLayoutManager);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = new ArrayList<>();
                eduAdapter.setData(mList);
                rcv_edu.setAdapter(eduAdapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditCVEduActivity.this, EditCVEdu2Activity.class);
                activityResultLauncher.launch(intent1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mList!=null)
                {
                    Intent intent2 = new Intent(EditCVEduActivity.this, EditCVActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle.putSerializable("eduList",(Serializable) mList);
                    intent2.putExtras(bundle);
                    setResult(104,intent2);
                    finish();
                }
            }
        });
    }
}