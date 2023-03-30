package com.example.timviec.Fragment;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.Activities.EditProfileActivity;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.EditIntroduceActivity;
import com.example.timviec.R;


public class PersonFragment extends Fragment {
    private View mView;
    MainActivity mainActivity;
    private ImageView person_img_user;
    private TextView person_txt_name,person_txt_address,person_txt_gender,person_txt_surname;
    //Introduce;
    private ImageView person_icon_editIntroduce;
    private TextView person_txt_introduce,person_txt_introduceAdd;
    private TextView person_btn_editInfor;
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
                        person_txt_name.setText(name);
                        person_txt_surname.setText(surname);
                        person_txt_address.setText(addres);
                        person_txt_gender.setText(gender);
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
                            person_txt_introduceAdd.setVisibility(View.GONE);
                            person_txt_introduce.setVisibility(View.VISIBLE);
                            person_txt_introduce.setText(text);
                        }
                        else
                        {
                            person_txt_introduceAdd.setVisibility(View.VISIBLE);
                            person_txt_introduce.setVisibility(View.GONE);
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
        person_txt_introduceAdd = mView.findViewById(R.id.person_txt_introduceADD);
        if (person_txt_introduce.getText().toString().isEmpty()) person_txt_introduce.setVisibility(View.GONE);
         person_icon_editIntroduce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentToIntroduce = new Intent(mainActivity, EditIntroduceActivity.class);
                intentToIntroduce.putExtra("text",person_txt_introduce.getText());
                activityResultLauncher.launch(intentToIntroduce);
            }
        });
        person_txt_introduceAdd.setOnClickListener(new View.OnClickListener() {
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
                bundle.putString("address", person_txt_address.getText().toString());
                bundle.putString("gender", person_txt_gender.getText().toString());
                intent.putExtras(bundle);
                activityResultLauncher.launch(intent);
            }
        });


        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (person_txt_introduce.getText().toString().isEmpty())
        {
            person_txt_introduce.setVisibility(View.GONE);
        }
        else
        {
            person_txt_introduce.setVisibility(View.VISIBLE);
        }
    }
}