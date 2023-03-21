package com.example.timviec.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.R;


public class PersonFragment extends Fragment {
    private View mView;
    private ImageView person_img_user;
    private TextView person_txt_name,person_txt_address,person_txt_gender;
    Button person_btn_editInfor;
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
        person_txt_address = mView.findViewById(R.id.person_txt_address);
        person_txt_gender = mView.findViewById(R.id.person_txt_gender);
        person_btn_editInfor = mView.findViewById(R.id.person_btn_editInfo);


        return mView;
    }
}