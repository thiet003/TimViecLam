package com.example.timviec.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.timviec.Fragment.HomeFragment;
import com.example.timviec.Fragment.MyWorkFragment;
import com.example.timviec.Fragment.NotionFragment;
import com.example.timviec.Fragment.PersonFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment item1 = new HomeFragment();
        Fragment item2 = new MyWorkFragment();
        Fragment item3 = new NotionFragment();
        Fragment item4 = new PersonFragment();

        switch (position)
        {
            case 0:
                return item1;
            case 1:
                return item2;
            case 2:
                return item3;
            case 3:
                return item4;
            default:
                return item1;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
