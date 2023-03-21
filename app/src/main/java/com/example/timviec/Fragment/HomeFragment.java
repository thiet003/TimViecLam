package com.example.timviec.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toolbar;

import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Adapter.JobAdapter;
import com.example.timviec.Model.Job;
import com.example.timviec.R;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private Toolbar home_toolbar;
    private RecyclerView rcv_job;
    private View mView;
    private MainActivity mainActivity;
    private JobAdapter jobAdapter;
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

        home_toolbar = mView.findViewById(R.id.home_toolbar);
        home_toolbar.setElevation(4f);

        rcv_job = mView.findViewById(R.id.rcv_job);

        mainActivity = (MainActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        rcv_job.setLayoutManager(linearLayoutManager);
        jobAdapter = new JobAdapter();
        jobAdapter.setDataforHome(getListJob(), new JobAdapter.IClickFavoriteJobListener()
        {
            @Override
            public void onClickFavoriteJob(ImageView img_favorite, Job job) {

            }
        });

        rcv_job.setAdapter(jobAdapter);

        return mView;
    }
    private List<Job> getListJob()
    {
        List<Job> list = new ArrayList<>();
        list.add(new Job(R.drawable.demo,"CÔNG TY SƠN VIỆT ÚC TUYỂN NHÂN VIÊN KHO","7.000.000 đ - 10.000.000 đ/tháng","Quận Hà Đông, Hà Nội","Số lượng: 2","Cập nhật ngày hôm nay"));
        list.add(new Job(R.drawable.demo,"Công ty Tấn Cường tuyển lao động phổ thông để sản xuất băng dính","5.000.000 đ - 10.000.000 đ/tháng","Quận Long Biên, Hà Nội","Số lượng: 5","Cập nhật ngày hôm nay"));
        list.add(new Job(R.drawable.demo,"Tuyển công nhân vận hành máy đóng gói","8.000.000 đ - 10.000.000 đ/tháng","Huyện Hoài Đức, Hà Nội","Số lượng: 2","Cập nhật ngày hôm nay"));
        list.add(new Job(R.drawable.demo,"Cần tuyển gấp 15 công nhân đóng gói bánh kẹo cho công ty","7.000.000 đ - 8.000.000 đ/tháng","Quận Đống Đa, Hà Nội","Số lượng: 15","Cập nhật ngày hôm nay"));
        list.add(new Job(R.drawable.demo,"Tuyển nam nữ đóng gói, dán tem, bánh kẹo , theo ca hoặc hành chính","9.000.000 đ - 12.000.000 đ/tháng","Quận Hoàng Mai, Hà Nội","Số lượng: 20","Cập nhật ngày hôm nay"));
        list.add(new Job(R.drawable.demo,"Xưởng Gia Công Bao Bì Tuyển 8 NV Nhận Phong Bì, Vỏ Hộp Về Làm Tại Nhà","6.000.000 đ - 12.000.000 đ/tháng","Quận Hoàng Mai, Hà Nội","Số lượng: 8","Cập nhật ngày hôm nay"));
        return list;
    }
}