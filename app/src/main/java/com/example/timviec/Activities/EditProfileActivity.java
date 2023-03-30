package com.example.timviec.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.timviec.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class EditProfileActivity extends AppCompatActivity {
    private ImageView img_edtprofile;
    private ImageView fromEditProfiletoPerson;
    private TextView edit_img_icon;
    private EditText edt_profile_name;
    private EditText edt_profile_surname;
    private EditText edit_profile_address;
    private TextView edit_profile_gender;
    private Button btn_save_profile;
    private LinearLayout bottomSheet;
    private Intent myIntent;
    private TextView gender1,gender2,gender3,gender4;
    private TextView editprofile_warning_empty1,editprofile_warning_empty2,editprofile_warning_empty3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        fromEditProfiletoPerson = findViewById(R.id.fromEditProfiletoPerson);
        editprofile_warning_empty1 = findViewById(R.id.editprofile_warning_empty1);
        editprofile_warning_empty2 = findViewById(R.id.editprofile_warning_empty2);
        editprofile_warning_empty3 = findViewById(R.id.editprofile_warning_empty3);
        img_edtprofile = findViewById(R.id.img_edtprofile);
        edit_img_icon = findViewById(R.id.edit_img_icon);
        edt_profile_name = findViewById(R.id.edt_profile_name);
        edt_profile_surname = findViewById(R.id.edt_profile_surname);
        edit_profile_address = findViewById(R.id.edt_profile_address);
        edit_profile_gender = findViewById(R.id.tv_profile_gender);
        btn_save_profile = findViewById(R.id.btn_saveprofile);
        bottomSheet = findViewById(R.id.bottomSheet_gender);
        View viewDialog = getLayoutInflater().inflate(R.layout.layout_bottom_sheet,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(EditProfileActivity.this);
        bottomSheetDialog.setCancelable(false);
        bottomSheetDialog.setContentView(viewDialog);
        gender1 = viewDialog.findViewById(R.id.gender1);
        gender2 = viewDialog.findViewById(R.id.gender2);
        gender3 = viewDialog.findViewById(R.id.gender3);
        gender4 = viewDialog.findViewById(R.id.gender4);
        edit_profile_gender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bottomSheetDialog.show();
            }
        });
        gender1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_gender.setText(gender1.getText());
                bottomSheetDialog.cancel();
            }
        });
        gender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_gender.setText(gender2.getText());
                bottomSheetDialog.cancel();
            }
        });
        gender3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_gender.setText(gender3.getText());
                bottomSheetDialog.cancel();
            }
        });
        gender4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_profile_gender.setText(gender4.getText());
                bottomSheetDialog.cancel();
            }
        });
        //Nhan du lieu
        myIntent = getIntent();
        Bundle bundle = myIntent.getExtras();
        edit_profile_address.setText(bundle.get("address").toString());
        edit_profile_gender.setText(bundle.get("gender").toString());

        //Click button save
        btn_save_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name1 = String.valueOf(edt_profile_name.getText());
                String name2 = String.valueOf(edt_profile_surname.getText());
                String address = String.valueOf(edit_profile_address.getText());
                String gender = String.valueOf(edit_profile_gender.getText());
                if(name1.isEmpty())
                {
                    editprofile_warning_empty1.setVisibility(View.VISIBLE);
                    editprofile_warning_empty2.setVisibility(View.GONE);
                    editprofile_warning_empty3.setVisibility(View.GONE);
                }
                else if(name2.isEmpty())
                {
                    editprofile_warning_empty2.setVisibility(View.VISIBLE);
                    editprofile_warning_empty1.setVisibility(View.GONE);
                }
                else if(address.isEmpty())
                {
                    editprofile_warning_empty3.setVisibility(View.VISIBLE);
                    editprofile_warning_empty1.setVisibility(View.GONE);
                    editprofile_warning_empty2.setVisibility(View.GONE);
                }
                else
                {
                    Intent intent1 = new Intent(EditProfileActivity.this,MainActivity.class);
                    System.out.println(name1);
                    Bundle bundle1 = new Bundle();
                    bundle.putString("name",name1);
                    bundle.putString("surname",name2);
                    bundle.putString("address",address);
                    bundle.putString("gender",gender);
                    intent1.putExtras(bundle);
                    setResult(RESULT_OK,intent1);
                    finish();
                }
            }
        });
        fromEditProfiletoPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}