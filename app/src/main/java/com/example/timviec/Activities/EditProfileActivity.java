package com.example.timviec.Activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.timviec.Model.Activity;
import com.example.timviec.Model.Certificate;
import com.example.timviec.Model.Education;
import com.example.timviec.Model.Experience;
import com.example.timviec.Model.Skill;
import com.example.timviec.Model.User;
import com.example.timviec.R;
import com.example.timviec.database.UserDatabase;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class EditProfileActivity extends AppCompatActivity {
    private RoundedImageView img_edtprofile;
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
    private static Uri mUri;
    private TextView editprofile_warning_empty1,editprofile_warning_empty2,editprofile_warning_empty3;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null && result.getData().getData() != null) {
                        Uri uri = result.getData().getData();
                        mUri = uri;
                        // Hiển thị ảnh được chọn trên app
                        Glide.with(EditProfileActivity.this).load(uri)
                                .into(img_edtprofile);
                    }
                }
            });
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

        edit_img_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activityResultLauncher.launch(intent);
            }
        });
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
        edt_profile_name.setText(bundle.get("name").toString());
        edt_profile_surname.setText(bundle.get("sur").toString());
        byte[] byteArray = bundle.getByteArray("img");

        Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        img_edtprofile.setImageBitmap(bitmap);

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
//                    RoundedDrawable roundedDrawable = (RoundedDrawable) img_edtprofile.getDrawable();
//                    Bitmap bitmap = roundedDrawable.toBitmap();
//                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//                    byte[] byteArray = byteArrayOutputStream.toByteArray();
//                    bundle1.putByteArray("img", byteArray);
                    String img;
                    if(mUri != null)
                    {
                        img = mUri.toString();
                    }
                    else img = "not";
                    bundle1.putString("img",img);
                    bundle1.putString("name",name1);
                    bundle1.putString("surname",name2);
                    bundle1.putString("address",address);
                    bundle1.putString("gender",gender);
                    intent1.putExtras(bundle1);
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