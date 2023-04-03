package com.example.timviec.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.timviec.Activities.InforJobActivity;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Adapter.JobAdapter;
import com.example.timviec.DataFromInternet;
import com.example.timviec.DataFromInternet2;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobDetail;
import com.example.timviec.Model.JobFavorite;
import com.example.timviec.Model.User;
import com.example.timviec.R;
import com.example.timviec.Activities.SearchActivity;
import com.example.timviec.database.JobDatabase;
import com.example.timviec.database.JobFavoriteDatabase;
import com.example.timviec.database.UserDatabase;
import com.example.timviec.my_interface.IClickFavoriteJobListener;
import com.example.timviec.my_interface.IClickItemJob;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment  {
    RoundedImageView img_user;
    private TextView userFactname;
    public static List<Job> mainListJob,list1,list2,list3,list4,list5,list6;
    private LinearLayout listPages;
    private TextView page1,page2,page3,page4,page5,page6;
    private Toolbar home_toolbar;
    private ImageView toSearchActivity;
    private RecyclerView rcv_job;
    private View mView;
    private MainActivity mainActivity;
    private JobAdapter jobAdapter;
    private List<Job> mList1;
    private Job mJob;
    private JobDetail mJobDetail;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_home, container, false);

        page1 = mView.findViewById(R.id.page1);
        page2 = mView.findViewById(R.id.page2);
        page3 = mView.findViewById(R.id.page3);
        page4 = mView.findViewById(R.id.page4);
        page5 = mView.findViewById(R.id.page5);
        page6 = mView.findViewById(R.id.page6);
        listPages = mView.findViewById(R.id.listPage);
        userFactname = mView.findViewById(R.id.user_factName);
        img_user = mView.findViewById(R.id.img_user);
        mainActivity = (MainActivity) getActivity();
        List<User> listUser = UserDatabase.getInstance(mainActivity).userDAO().checkUser("thietdong264");
        User user = listUser.get(0);
        String name = user.getFactName() + " "+user.getSurName();
        userFactname.setText(name);
        Uri uri = Uri.parse(user.getImgUri());
        if (getActivity() != null && !getActivity().isDestroyed()){
            // Sử dụng Glide để tải ảnh vào ImageView
            Glide.with(mainActivity)
                    .load(uri)
                    .into(img_user);
        }


        home_toolbar = mView.findViewById(R.id.home_toolbar);
        toSearchActivity =mView.findViewById(R.id.toSearchActivity);
        home_toolbar.setElevation(4f);

        rcv_job = mView.findViewById(R.id.rcv_job);

        jobAdapter = new JobAdapter();
        toSearchActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mainActivity, SearchActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("listJobs", (Serializable) mainActivity.getmList());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv_job.setLayoutManager(linearLayoutManager);
        System.out.println(mainActivity.getmList().size());
        mList1 = new ArrayList<>();
        if (mainActivity.getmList().size()!=0)
        {
            for(int i=0;i<25;i++)
            {
                mList1.add(mainActivity.getmList().get(i));
            }
        }
        else
        {
            listPages.setVisibility(View.GONE);
        }
        jobAdapter.setDataforHome(new IClickItemJob() {
            @Override
            public void onClickItemJob(Job job) {
                onClicktoInforActivity(job);
            }
        }, mList1,mainActivity);
        page1.setBackgroundResource(R.drawable.circle_click2);
        // Xet su kien chuyen page cua list viec lam
        page1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page1.setBackgroundResource(R.drawable.circle_click2);

                page2.setBackgroundResource(R.drawable.circle_click);
                page3.setBackgroundResource(R.drawable.circle_click);
                page4.setBackgroundResource(R.drawable.circle_click);
                page5.setBackgroundResource(R.drawable.circle_click);
                page6.setBackgroundResource(R.drawable.circle_click);

                setListforAdapter(0,25);
            }
        });
        page2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page2.setBackgroundResource(R.drawable.circle_click2);

                page1.setBackgroundResource(R.drawable.circle_click);
                page3.setBackgroundResource(R.drawable.circle_click);
                page4.setBackgroundResource(R.drawable.circle_click);
                page5.setBackgroundResource(R.drawable.circle_click);
                page6.setBackgroundResource(R.drawable.circle_click);
                setListforAdapter(25,50);
            }
        });
        page3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page3.setBackgroundResource(R.drawable.circle_click2);

                page2.setBackgroundResource(R.drawable.circle_click);
                page1.setBackgroundResource(R.drawable.circle_click);
                page4.setBackgroundResource(R.drawable.circle_click);
                page5.setBackgroundResource(R.drawable.circle_click);
                page6.setBackgroundResource(R.drawable.circle_click);
                setListforAdapter(50,75);

            }
        });
        page4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page4.setBackgroundResource(R.drawable.circle_click2);
                page2.setBackgroundResource(R.drawable.circle_click);
                page3.setBackgroundResource(R.drawable.circle_click);
                page1.setBackgroundResource(R.drawable.circle_click);
                page5.setBackgroundResource(R.drawable.circle_click);
                page6.setBackgroundResource(R.drawable.circle_click);
                setListforAdapter(75,100);
            }
        });
        page5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page5.setBackgroundResource(R.drawable.circle_click2);
                page2.setBackgroundResource(R.drawable.circle_click);
                page3.setBackgroundResource(R.drawable.circle_click);
                page4.setBackgroundResource(R.drawable.circle_click);
                page1.setBackgroundResource(R.drawable.circle_click);
                page6.setBackgroundResource(R.drawable.circle_click);
                setListforAdapter(100,125);
            }
        });
        page6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page6.setBackgroundResource(R.drawable.circle_click2);

                page2.setBackgroundResource(R.drawable.circle_click);
                page3.setBackgroundResource(R.drawable.circle_click);
                page4.setBackgroundResource(R.drawable.circle_click);
                page5.setBackgroundResource(R.drawable.circle_click);
                page1.setBackgroundResource(R.drawable.circle_click);
                setListforAdapter(125,150);
            }
        });
        rcv_job.setAdapter(jobAdapter);
        return mView;
    }

    @Override
    public void onResume() {
        super.onResume();
        jobAdapter.setDataforHome(new IClickItemJob() {
            @Override
            public void onClickItemJob(Job job) {
                onClicktoInforActivity(job);
            }
        }, mList1,mainActivity);
    }

    private void setListforAdapter(int l, int r)
    {
        mList1 = new ArrayList<>();
        if (mainActivity.getmList().size()!=0)
        {
            for(int i=l;i<r;i++)
            {
                mList1.add(mainActivity.getmList().get(i));
            }
        }
        jobAdapter.setDataforHome(new IClickItemJob() {
            @Override
            public void onClickItemJob(Job job) {
                onClicktoInforActivity(job);
            }
        }, mList1,mainActivity);
    }
    private void onClicktoInforActivity(Job job)
    {
        String url = job.getLink();
        System.out.println(url);
        List<JobFavorite> jobFavorites = JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().checkFavoriteJob(url);
        boolean checker = jobFavorites != null && !jobFavorites.isEmpty();
        String name = job.getName();
        String img = job.getResourceId();
        String salary = job.getSalary();
        String address = job.getAddress();
        String company = job.getCompany();
        String time = job.getUpdateTime();
        Job newJob = new Job();
        newJob.setName(name);
        newJob.setResourceId(img);
        newJob.setSalary(salary);
        newJob.setAddress(address);
        newJob.setCompany(company);
        newJob.setUpdateTime(time);
        DataFromInternet2 dataFromInternet2 = new DataFromInternet2(new DataFromInternet2.ICPutData2() {
            @Override
            public void putToInforActivity(JobDetail jobDetail) {
                mJobDetail = jobDetail;
                newJob.setJdetail(jobDetail);
                Intent intent = new Intent(mainActivity, InforJobActivity.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("jobs",newJob);
                bundle.putBoolean("checker",checker);
                bundle.putString("url",url);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        },url);
        dataFromInternet2.execute();



    }
}