package com.example.timviec.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.timviec.Fragment.HomeFragment;
import com.example.timviec.Fragment.MyWorkFragment;
import com.example.timviec.Fragment.NotionFragment;
import com.example.timviec.Fragment.PersonFragment;
import com.example.timviec.Fragment.SearchFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new HomeFragment();
            case 1:
                return new SearchFragment();
            case 2:
                return new MyWorkFragment();
            case 3:
                return new NotionFragment();
            case 4:
                return new PersonFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
