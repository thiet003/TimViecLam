package com.example.timviec.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.timviec.Adapter.MyWorkPageAdapter;
import com.example.timviec.R;
import com.google.android.material.tabs.TabLayout;

public class MyWorkFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;
    public MyWorkFragment() {
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
        mView =  inflater.inflate(R.layout.fragment_my_work, container, false);

        tabLayout = mView.findViewById(R.id.mywork_tablayout);
        viewPager = mView.findViewById(R.id.mywork_viewpager);
        MyWorkPageAdapter myWorkPageAdapter = new MyWorkPageAdapter(getChildFragmentManager());
        myWorkPageAdapter.addFragment(new Work_FavoriteFragment(), "Đã lưu");
        myWorkPageAdapter.addFragment(new Work_RecuitmentFragment(), "Đã ứng tuyển");
        viewPager.setAdapter(myWorkPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }
}