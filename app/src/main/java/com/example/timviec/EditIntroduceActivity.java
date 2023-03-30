package com.example.timviec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.timviec.Activities.MainActivity;

public class EditIntroduceActivity extends AppCompatActivity {
    private ImageView back;
    private EditText edt_introduce;
    private Button btn_save__introduce;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_introduce);
        back = findViewById(R.id.fromIntroduceToPerson);
        edt_introduce = findViewById(R.id.edt_introduce);
        btn_save__introduce = findViewById(R.id.btn_saveIntroduce);

        Intent myIntent = getIntent();
        edt_introduce.setText(myIntent.getStringExtra("text"));
        btn_save__introduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = String.valueOf(edt_introduce.getText());
                Intent intent2 =  new Intent(EditIntroduceActivity.this, MainActivity.class);
                intent2.putExtra("text",text);
                setResult(100,intent2);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}