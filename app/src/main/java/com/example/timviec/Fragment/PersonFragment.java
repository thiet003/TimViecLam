package com.example.timviec.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.timviec.Activities.EditCVActivity;
import com.example.timviec.Activities.EditProfileActivity;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Activities.EditIntroduceActivity;
import com.example.timviec.Model.User;
import com.example.timviec.R;
import com.example.timviec.database.JobFavoriteDatabase;
import com.example.timviec.database.JobRecuimentDatabase;
import com.example.timviec.database.UserDatabase;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.util.List;


public class PersonFragment extends Fragment {
    private View mView;
    MainActivity mainActivity;
    private RoundedImageView person_img_user;
    private TextView person_txt_name,person_txt_address,person_txt_gender,person_txt_surname;
    //Introduce;
    private TextView person_icon_editIntroduce,person_icon_editExprience,person_icon_address;
    private TextView person_txt_introduce,person_txt_exp,person_txt_addresssub;
    private TextView person_btn_editInfor;
    //Dinalog
    private TextView a1,a2,a3,a4,a5,a6,a7;
    private TextView as1,as2,as3,as4;
    private TextView person_cntJF,person_cntJR;
    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()==RESULT_OK)
                    {
                        Intent data = result.getData();
                        Bundle bundle = data.getExtras();
                        String name = (String) bundle.get("name");
                        String surname = (String) bundle.get("surname");
                        String addres = (String) bundle.get("address");
                        String gender = (String) bundle.get("gender");
                        List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                        User user1 = listUser.get(0);
                        String img = (String) bundle.get("img");
                        if(img.compareTo("not")!=0)
                        {
                            user1.setImgUri(img);
                            Uri uri = Uri.parse(img);
                            Glide.with(mainActivity)
                                    .load(uri)
                                    .into(person_img_user);
                        }
                        person_txt_name.setText(name);
                        person_txt_surname.setText(surname);
                        person_txt_address.setText(addres);
                        person_txt_gender.setText(gender);
                        user1.setFactName(name);
                        user1.setSurName(surname);
                        user1.setAddress(addres);
                        user1.setGender(gender);
                        UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                        if(gender.compareTo("Nam")==0)
                        {
                            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_men);
                            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                        }
                        else if(gender.compareTo("Nữ")==0)
                        {
                            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_women);
                            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                        }
                        else
                        {
                            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_not);
                            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
                        }
                    }
                    if (result.getResultCode()==100)
                    {
                        Intent data = result.getData();
                        String text = data.getStringExtra("text");
                        if (!text.isEmpty())
                        {
                            person_txt_introduce.setText(text);
                            List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                            User user1 = listUser.get(0);
                            user1.setIntroduce((String) text);
                            UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                        }
                        else
                        {
                            person_txt_introduce.setText("Chưa cập nhật");
                            person_txt_introduce.setText(text);
                            List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                            User user1 = listUser.get(0);
                            user1.setIntroduce("Chưa cập nhật");
                            UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                        }
                    }
                }
            });
    public PersonFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_person, container, false);

        //Ánh xạ View
        person_img_user = mView.findViewById(R.id.person_img_user);
        person_txt_name = mView.findViewById(R.id.person_txt_name);
        person_txt_surname = mView.findViewById(R.id.person_txt_surname);
        person_txt_address = mView.findViewById(R.id.person_txt_address);
        person_txt_gender = mView.findViewById(R.id.person_txt_gender);
        person_btn_editInfor = mView.findViewById(R.id.person_btn_editInfo);
        mainActivity = (MainActivity) getActivity();
        //Introduce
        person_icon_editIntroduce = mView.findViewById(R.id.person_icon_editIntroduce);
        person_txt_introduce = mView.findViewById(R.id.person_txt_introduce);
        person_icon_editExprience = mView.findViewById(R.id.person_icon_editExprience);
        person_txt_exp = mView.findViewById(R.id.person_txt_exp);
        person_icon_address = mView.findViewById(R.id.person_icon_address);
        person_txt_addresssub = mView.findViewById(R.id.person_txt_addresssub);

        person_cntJF = mView.findViewById(R.id.person_cntJF);
        person_cntJR = mView.findViewById(R.id.person_cntJR);

//        if (person_txt_introduce.getText().toString().isEmpty()) person_txt_introduce.setVisibility(View.GONE);
        List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
        User user = listUser.get(0);
        Uri uri = Uri.parse(user.getImgUri());
        Glide.with(mainActivity)
                        .load(uri)
                                .into(person_img_user);
        person_txt_name.setText(user.getFactName());
        person_txt_surname.setText(user.getSurName());
        person_txt_address.setText(user.getAddress());
        person_txt_gender.setText(user.getGender());
        person_txt_introduce.setText(user.getIntroduce());
        person_txt_addresssub.setText(user.getAddressSub());
        person_txt_exp.setText(user.getExp());
        String exp = (String) person_txt_exp.getText();
        String adress = (String) person_txt_addresssub.getText();
        if(exp.compareTo("Chưa cập nhật")!=0) person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
        if(adress.compareTo("Chưa cập nhật")!=0) person_txt_addresssub.setBackgroundResource(R.drawable.exp_tv);
        if(user.getGender().compareTo("Nam")==0)
        {
            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_men);
            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        else if(user.getGender().compareTo("Nữ")==0)
        {
            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_women);
            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        else
        {
            Drawable drawable = getResources().getDrawable(R.drawable.gender_icon_not);
            person_txt_gender.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        }
        person_icon_editIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToIntroduce = new Intent(mainActivity, EditIntroduceActivity.class);
                intentToIntroduce.putExtra("text",person_txt_introduce.getText());
                activityResultLauncher.launch(intentToIntroduce);
            }
        });
        person_btn_editInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, EditProfileActivity.class);
                Bundle bundle = new Bundle();
                RoundedDrawable roundedDrawable = (RoundedDrawable) person_img_user.getDrawable();
                Bitmap bitmap = roundedDrawable.toBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                bundle.putByteArray("img", byteArray);
                bundle.putString("name", person_txt_name.getText().toString());
                bundle.putString("sur", person_txt_surname.getText().toString());
                bundle.putString("address", person_txt_address.getText().toString());
                bundle.putString("gender", person_txt_gender.getText().toString());
                intent.putExtras(bundle);
                activityResultLauncher.launch(intent);
            }
        });

        //Dinalog
        View viewDialog = getLayoutInflater().inflate(R.layout.exp_bottomsheet,null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(mainActivity);
        bottomSheetDialog.setContentView(viewDialog);
        a1 = viewDialog.findViewById(R.id.address1);
        a2 = viewDialog.findViewById(R.id.address2);
        a3 = viewDialog.findViewById(R.id.address3);
        a4 = viewDialog.findViewById(R.id.address4);
        a5 = viewDialog.findViewById(R.id.address5);
        a6 = viewDialog.findViewById(R.id.address6);
        a7 = viewDialog.findViewById(R.id.address7);
        person_icon_editExprience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a1.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a1.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a2.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a2.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a3.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a3.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a4.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a4.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a5.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a5.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a6.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a6.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_exp.setText(a7.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setExp((String) a7.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_exp.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog.cancel();
            }
        });
        //Dinalog2
        View viewDialog2 = getLayoutInflater().inflate(R.layout.addresssub_bottomsheet,null);
        BottomSheetDialog bottomSheetDialog2 = new BottomSheetDialog(mainActivity);
        bottomSheetDialog2.setContentView(viewDialog2);
        as1 = viewDialog2.findViewById(R.id.address1);
        as2 = viewDialog2.findViewById(R.id.address2);
        as3 = viewDialog2.findViewById(R.id.address3);
        as4 = viewDialog2.findViewById(R.id.address4);
        person_icon_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog2.show();
            }
        });
        as1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_addresssub.setText(as1.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setAddressSub((String) as1.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_addresssub.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog2.cancel();
            }
        });
        as2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_addresssub.setText(as2.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setAddressSub((String) as2.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_addresssub.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog2.cancel();
            }
        });
        as3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_addresssub.setText(as3.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setAddressSub((String) as3.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_addresssub.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog2.cancel();
            }
        });
        as4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                person_txt_addresssub.setText(as4.getText());
                List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
                User user1 = listUser.get(0);
                user1.setAddressSub((String) as4.getText());
                UserDatabase.getInstance(mainActivity).userDAO().updateUser(user1);
                person_txt_addresssub.setBackgroundResource(R.drawable.exp_tv);
                bottomSheetDialog2.cancel();
            }
        });
        int cnt1 = JobRecuimentDatabase.getInstance(mainActivity).jobRecuimentDAO().getJRCount();
        int cnt2 = JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().getJFCount();
        person_cntJR.setText(String.valueOf(cnt1));
        person_cntJF.setText(String.valueOf(cnt2));
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
//        if (person_txt_introduce.getText().toString().isEmpty())
//        {
//            person_txt_introduce.setVisibility(View.GONE);
//        }
//        else
//        {
//            person_txt_introduce.setVisibility(View.VISIBLE);
//        }
    }
}