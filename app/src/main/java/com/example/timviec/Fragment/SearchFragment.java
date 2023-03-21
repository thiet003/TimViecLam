package com.example.timviec.Fragment;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Adapter.JobAdapter;
import com.example.timviec.Model.Job;
import com.example.timviec.R;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    SearchView searchView;
    private RecyclerView search_rcv;
    private JobAdapter jobAdapter;
    private View mView;
    MainActivity mainActivity;
    public SearchFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_search, container, false);
        search_rcv = mView.findViewById(R.id.seach_rcv_job);
        searchView = mView.findViewById(R.id.search_viewer);
        mainActivity = (MainActivity) getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity);
        search_rcv.setLayoutManager(linearLayoutManager);
        jobAdapter = new JobAdapter();
        jobAdapter.setData(getListJob(), new JobAdapter.IClickFavoriteJobListener()
        {
            @Override
            public void onClickFavoriteJob(ImageView img_favorite, Job job) {

            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                jobAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //jobAdapter.getFilter().filter(newText);
                return false;
            }
        });

        search_rcv.setAdapter(jobAdapter);
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