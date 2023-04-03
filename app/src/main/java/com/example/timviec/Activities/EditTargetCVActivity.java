package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.timviec.R;

public class EditTargetCVActivity extends AppCompatActivity {
    private EditText editcvtarget_edt_text;
    private TextView cancel, save;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_target_cvactivity);
        editcvtarget_edt_text = findViewById(R.id.editcvtarget_edt_text);
        cancel = findViewById(R.id.editcvtarget_cacel);
        save = findViewById(R.id.editcvtarget_save);

        Intent intent = getIntent();
        String target = intent.getStringExtra("target");
        editcvtarget_edt_text.setText(target);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditTargetCVActivity.this, EditCVActivity.class);
                String taget = String.valueOf(editcvtarget_edt_text.getText());
                intent1.putExtra("target",taget);
                setResult(102,intent1);
                finish();
            }
        });
    }
}