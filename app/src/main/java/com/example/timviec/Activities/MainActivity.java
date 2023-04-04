package com.example.timviec.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.timviec.DataFromInternet;
import com.example.timviec.JobSingleton;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobRecuiment;
import com.example.timviec.Model.User;
import com.example.timviec.R;
import com.example.timviec.Adapter.ViewPagerAdapter;
import com.example.timviec.database.JobDatabase;
import com.example.timviec.database.JobRecuimentDAO;
import com.example.timviec.database.JobRecuimentDatabase;
import com.example.timviec.database.UserDAO;
import com.example.timviec.database.UserDatabase;
import com.example.timviec.my_interface.IClickRecuimentJob;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements IClickRecuimentJob {

    public List<Job> getmList() {
        return mList;
    }

    public void setmList(List<Job> mList) {
        this.mList = mList;
    }

    //Khai bao ahbottomNavi
    private List<Job> mList;
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    @Override
    protected void onPostResume() {
        super.onPostResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh xa view
        navigationView = findViewById(R.id.AHBottomNavigation);
        viewPager = findViewById(R.id.AHBottomNavigationViewPager);
        setupViewPager();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.action_mywork:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.action_mycv:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.action_person:
                        viewPager.setCurrentItem(3);
                        break;
                }
                return true;
            }
        });
        
        DataFromInternet dataFromInternet = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?sort=top_related",this);
        dataFromInternet.execute();
        DataFromInternet dataFromInternet2 = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?salary=0&exp=0&sort=top_related&page=2",this);
        dataFromInternet2.execute();
        DataFromInternet dataFromInternet3 = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?salary=0&exp=0&sort=top_related&page=3",this);
        dataFromInternet3.execute();
        DataFromInternet dataFromInternet4 = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?salary=0&exp=0&sort=top_related&page=4",this);
        dataFromInternet4.execute();
        DataFromInternet dataFromInternet5 = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?salary=0&exp=0&sort=top_related&page=5",this);
        dataFromInternet5.execute();
        DataFromInternet dataFromInternet6 = new DataFromInternet(new DataFromInternet.ICPutData() {
            @Override
            public void putToFragment(List<Job> list) {

            }
        },"https://www.topcv.vn/tim-viec-lam-thuc-tap-sinh-kc10026?salary=0&exp=0&sort=top_related&page=6",this);
        dataFromInternet6.execute();
        mList = JobDatabase.getInstance(this).jobDAO().getListJob();
        for(int i=0;i<mList.size();i++)
        {
            mList.get(i).setFavorite(false);
        }
        System.out.println(mList.size());
        JobDatabase.getInstance(MainActivity.this).jobDAO().deleteAllJob();

        //Check user
        List<User> listUser = UserDatabase.getInstance(MainActivity.this).userDAO().checkUser("thietdong264");
        if(listUser==null || listUser.isEmpty())
        {
            Resources resources = getResources();
            Uri imageUri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                    resources.getResourcePackageName(R.drawable.demo2) + '/' +
                    resources.getResourceTypeName(R.drawable.demo2) + '/' +
                    resources.getResourceEntryName(R.drawable.demo2));
            String uri = imageUri.toString();
            User user = new User("thietdong264","User",uri,"Name","Not found","Không xác định","Chưa cập nhật","Chưa cập nhật","Chưa cập nhật");
            UserDatabase.getInstance(MainActivity.this).userDAO().insertUser(user);
        }


    }
    private void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        navigationView.getMenu().findItem(R.id.action_home);
                        break;
                    case 1:
                        navigationView.getMenu().findItem(R.id.action_mywork);
                        break;
                    case 2:
                        navigationView.getMenu().findItem(R.id.action_mycv);
                    case 3:
                        navigationView.getMenu().findItem(R.id.action_person);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClickRecuiment(Job job) {

    }
}