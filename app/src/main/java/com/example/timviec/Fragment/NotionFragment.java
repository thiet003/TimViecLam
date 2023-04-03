package com.example.timviec.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.timviec.Activities.EditCVActivity;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.R;


public class NotionFragment extends Fragment {
    private TextView btn_creatCV;
    private View mView;
    MainActivity mainActivity;
    public NotionFragment() {
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
        mView = inflater.inflate(R.layout.fragment_notion, container, false);
        btn_creatCV = mView.findViewById(R.id.btn_CreatCV);
        mainActivity = (MainActivity) getActivity();
        btn_creatCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, EditCVActivity.class);
                startActivity(intent);
            }
        });

        return mView;
    }
}