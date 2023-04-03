package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.Model.Certificate;
import com.example.timviec.R;

import java.io.Serializable;

public class EditCVPrize2Activity extends AppCompatActivity {
    private EditText name,time;
    TextView cancle, save,w1,w2;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cvprize2);
        name = findViewById(R.id.editcvprize_edtname);
        time = findViewById(R.id.editcvprize_edttime);
        cancle = findViewById(R.id.editcvprize_cancel);
        save = findViewById(R.id.editcvprize_save2);
        w1 = findViewById(R.id.editcvprize_w1);
        w2 = findViewById(R.id.editcvprize_w2);
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
                String s2 = String.valueOf(time.getText());
                Certificate certificate = new Certificate(s2,s1);
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
                    Intent intent = new Intent(EditCVPrize2Activity.this,EditCVPrizeActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("prize",(Serializable) certificate);
                    intent.putExtras(bundle);
                    setResult(601,intent);
                    finish();
                }
            }
        });
    }
}