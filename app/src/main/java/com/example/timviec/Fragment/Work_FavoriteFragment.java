package com.example.timviec.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.timviec.Activities.InforJobActivity;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Adapter.JobAdapter;
import com.example.timviec.DataFromInternet2;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobDetail;
import com.example.timviec.Model.JobFavorite;
import com.example.timviec.R;
import com.example.timviec.database.JobFavoriteDatabase;
import com.example.timviec.my_interface.IClickFavoriteJobListener;
import com.example.timviec.my_interface.IClickItemJob;

import java.util.ArrayList;
import java.util.List;

public class Work_FavoriteFragment extends Fragment {
    private View mView;
    private RecyclerView rcv_job_fvt;
    private List<Job> mList;
    private JobAdapter jobAdapter;
    MainActivity mainActivity;
    private TextView tv_fvt;
    private JobDetail mJobDetail;
    public Work_FavoriteFragment() {
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
        mView = inflater.inflate(R.layout.fragment_work__favorite, container, false);
        mList = new ArrayList<>();
        rcv_job_fvt = mView.findViewById(R.id.rcv_job_fvt);
        tv_fvt = mView.findViewById(R.id.tv_fvt);

        tv_fvt.setText("Việc bạn yêu thích");
        mainActivity = (MainActivity) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv_job_fvt.setLayoutManager(linearLayoutManager);
        jobAdapter = new JobAdapter();
        jobAdapter.setDataforHome(new IClickItemJob() {
            @Override
            public void onClickItemJob(Job job) {
                onClicktoInforActivity(job);
            }
        },getListFavorite(),mainActivity);
        System.out.println("Tong size: " + mList.size());
        rcv_job_fvt.setAdapter(jobAdapter);
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
        },getListFavorite(),mainActivity);
    }

    private List<Job> getListFavorite()
    {
        mList = new ArrayList<>();
        int dem=0;
        List<JobFavorite> jobFavoriteList = JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().getListFavoriteJob();
        for(JobFavorite jobFavorite : jobFavoriteList)
        {
            Job job = new Job(jobFavorite.getResourceId(), jobFavorite.getLink(), jobFavorite.getName(), jobFavorite.getSalary(), jobFavorite.getAddress(), jobFavorite.getCompany(),jobFavorite.getUpdateTime());
            mList.add(job);
        }
        return mList;
    }
    private void onClicktoInforActivity(Job job)
    {
        String url = job.getLink();
        List<JobFavorite> jobFavorites = JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().checkFavoriteJob(url);
        boolean checker = jobFavorites != null && !jobFavorites.isEmpty();
        System.out.println(url);
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