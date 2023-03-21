package com.example.timviec.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.example.timviec.R;
import com.example.timviec.Adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {
    //Khai bao ahbottomNavi
    private AHBottomNavigation ahBottomNavigation;
    private AHBottomNavigationViewPager ahBottomNavigationViewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh xa cac thanh phan
        ahBottomNavigation = findViewById(R.id.AHBottomNavigation);
        ahBottomNavigationViewPager = findViewById(R.id.AHBottomNavigationViewPager);

        adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ahBottomNavigationViewPager.setAdapter(adapter);

        //Creat item ahbottomNavi

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("Trang chủ", R.drawable.home_icon, R.color.yess);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("Tìm kiếm", R.drawable.search_icon, R.color.yess);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("Việc của tôi", R.drawable.work_icon, R.color.yess);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("Tạo CV", R.drawable.file_icon, R.color.yess);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem("Hồ sơ", R.drawable.person_icon, R.color.yess);
        // Add items

        ahBottomNavigation.addItem(item1);
        ahBottomNavigation.addItem(item2);
        ahBottomNavigation.addItem(item3);
        ahBottomNavigation.addItem(item4);
        ahBottomNavigation.addItem(item5);

        ahBottomNavigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        ahBottomNavigation.setAccentColor(Color.parseColor("#000000"));
        ahBottomNavigation.setInactiveColor(Color.parseColor("#5e5e56"));

        AHNotification notification = new AHNotification.Builder()
                .setText("1")
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.red))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        ahBottomNavigation.setNotification(notification, 3);

        //xu li su kien vuot

        ahBottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                ahBottomNavigationViewPager.setCurrentItem(position);
                return true;
            }
        });

        ahBottomNavigationViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ahBottomNavigation.setCurrentItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}