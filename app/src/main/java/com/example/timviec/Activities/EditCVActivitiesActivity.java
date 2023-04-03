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

import com.example.timviec.Adapter.ActivityAdapter2;
import com.example.timviec.Model.Activity;
import com.example.timviec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVActivitiesActivity extends AppCompatActivity {
    private RecyclerView rcv_acti;
    private List<Activity> mList;
    private ActivityAdapter2 activityAdapter;
    private TextView save,add,delete;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Bundle bundle = data.getExtras();
                    Activity activity = (Activity) bundle.get("acti");
                    mList.add(activity);
                    activityAdapter.setData(mList);
                    rcv_acti.setAdapter(activityAdapter);

                }
            });
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvactivities);
        rcv_acti = findViewById(R.id.editcvacti_rcvActi);
        mList = new ArrayList<>();
        activityAdapter = new ActivityAdapter2();
        add = findViewById(R.id.editcvacti_add);
        save = findViewById(R.id.editcvacti_save);
        delete = findViewById(R.id.editcvacti_deleteall);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Activity> list = (List<Activity>) bundle.getSerializable("acti");
        mList = list;



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_acti.setLayoutManager(linearLayoutManager);
        activityAdapter.setData(mList);
        rcv_acti.setAdapter(activityAdapter);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = new ArrayList<>();
                activityAdapter.setData(mList);
                rcv_acti.setAdapter(activityAdapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditCVActivitiesActivity.this, EditCVActivities2Activity.class);
                activityResultLauncher.launch(intent1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mList!=null)
                {
                    Intent intent2 = new Intent(EditCVActivitiesActivity.this, EditCVActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("actiList",(Serializable) mList);
                    intent2.putExtras(bundle1);
                    setResult(107,intent2);
                    finish();
                }
            }
        });
    }
}