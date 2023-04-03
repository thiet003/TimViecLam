
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

import com.example.timviec.Adapter.CertificateAdapter2;
import com.example.timviec.Model.Certificate;
import com.example.timviec.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EditCVPrizeActivity extends AppCompatActivity {
    private RecyclerView rcv_prize;
    private List<Certificate> mList;
    private CertificateAdapter2 certificateAdapter;
    private TextView save,add,delete;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode()==601)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        Certificate certificate = (Certificate) bundle.get("prize");
                        mList.add(certificate);
                        certificateAdapter.setData(mList);
                        rcv_prize.setAdapter(certificateAdapter);
                    }
                }
            });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvprize);
        rcv_prize = findViewById(R.id.editcvprize_rcvPrize);
        mList = new ArrayList<>();
        certificateAdapter = new CertificateAdapter2();
        add = findViewById(R.id.editcvprize_addskill);
        save = findViewById(R.id.editcvprize_save);
        delete = findViewById(R.id.editcvprize_deleteall);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_prize.setLayoutManager(linearLayoutManager);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        List<Certificate> list = (List<Certificate>) bundle.getSerializable("prize");
        mList = list;

        certificateAdapter.setData(mList);
        rcv_prize.setAdapter(certificateAdapter);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mList = new ArrayList<>();
                certificateAdapter.setData(mList);
                rcv_prize.setAdapter(certificateAdapter);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditCVPrizeActivity.this, EditCVPrize2Activity.class);
                activityResultLauncher.launch(intent1);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mList!=null)
                {
                    Intent intent2 = new Intent(EditCVPrizeActivity.this, EditCVActivity.class);
                    Bundle bundle1 = new Bundle();
                    bundle.putSerializable("prizeList",(Serializable) mList);
                    intent2.putExtras(bundle);
                    setResult(108,intent2);
                    finish();
                }
            }
        });
    }
}