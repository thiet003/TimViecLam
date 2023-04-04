package com.example.timviec.Adapter;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobFavorite;
import com.example.timviec.R;
import com.example.timviec.database.JobFavoriteDatabase;
import com.example.timviec.my_interface.IClickFavoriteJobListener;
import com.example.timviec.my_interface.IClickItemJob;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> implements Filterable {

    private List<Job> mListJob;
    private List<Job> mListJobOld;
    private List<Job> fvtListJob;
    private IClickFavoriteJobListener iClickFavoriteJobListener;
    private IClickItemJob iClickItemJob;
    private MainActivity mainActivity;



    public JobAdapter(){};
    public JobAdapter(List<Job> list,MainActivity mainActivity)
    {
        this.mListJob = list;
        this.mainActivity = mainActivity;
        notifyDataSetChanged();
    }
    public void setDataforFavorite(List<Job> list)
    {
        this.mListJob = list;
        notifyDataSetChanged();
    }
    public void setDataforHome(IClickItemJob iClickItemJob,List<Job> list,MainActivity mainActivity)
    {
        this.mListJob = list;
        this.iClickItemJob = iClickItemJob;
        this.mainActivity = mainActivity;
        notifyDataSetChanged();
    }
    public void setData(List<Job> list)
    {
        this.mListJob = null;
        this.mListJobOld = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,parent,false);
        return new JobViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final JobViewHolder holder, int position) {
        Job job = mListJob.get(position);
        if (job == null) {
            return;
        }
//
        holder.tv_job_name.setText(job.getName());
        holder.tv_job_salary.setText(job.getSalary());
        holder.tv_job_address.setText(job.getAddress());
        holder.tv_job_company.setText(job.getCompany());
        holder.tv_job_updateTime.setText(job.getUpdateTime());
        Glide.with(holder.img_job.getContext())
                .load(job.getResourceId())
                .into(holder.img_job);
        List<JobFavorite> jobFavorites = JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().checkFavoriteJob(job.getLink());
        boolean checker = jobFavorites != null && !jobFavorites.isEmpty();
        if (checker==true)
        {
            holder.img_favorite_job.setImageResource(R.drawable.favorite_icon);
        }
        else
        {
            holder.img_favorite_job.setImageResource(R.drawable.favorite_border_icon);
        }
        //Xu li bat xu kien
        holder.layout_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickItemJob.onClickItemJob(job);
            }
        });
        holder.img_favorite_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checker==true)
                {
                    holder.img_favorite_job.setImageResource(R.drawable.favorite_border_icon);
                    JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().deleteFavoriteJob(job.getLink());
                }
                else
                {
                    holder.img_favorite_job.setImageResource(R.drawable.favorite_icon);
                    JobFavorite jobFavorite = new JobFavorite(job.getLink(), job.getResourceId(), job.getName(), job.getSalary(), job.getAddress(), job.getCompany(), job.getUpdateTime());
                    JobFavoriteDatabase.getInstance(mainActivity).jobFavoriteDAO().insertFavoriteJob(jobFavorite);
                }
                notifyDataSetChanged();

            }
        });
    }
    @Override
    public int getItemCount() {
        if(mListJob != null )
        {
            return mListJob.size();
        }
        return 0;
    }

    public class JobViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_job,img_favorite_job;
        RelativeLayout layout_item;
        private TextView tv_job_name,tv_job_salary,tv_job_address,tv_job_company,tv_job_updateTime;
        @SuppressLint("ResourceType")
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item =itemView.findViewById(R.id.layout_job);
            img_job = itemView.findViewById(R.id.img_job);
            tv_job_name = itemView.findViewById(R.id.tv_job_name);
            tv_job_salary = itemView.findViewById(R.id.tv_job_salary);
            tv_job_address = itemView.findViewById(R.id.tv_job_address);
            tv_job_company = itemView.findViewById(R.id.tv_job_company);
            tv_job_updateTime = itemView.findViewById(R.id.tv_job_updateTime);
            img_favorite_job = itemView.findViewById(R.id.img_favorite_job);
        }
    }
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String strSearch = charSequence.toString();
                if(strSearch.isEmpty())
                {
                    mListJob = null;
                }
                else
                {
                    List<Job> list = new ArrayList<>();
                    for(Job job : mListJobOld)
                    {
                        boolean check1 = job.getName().toLowerCase().contains(strSearch.toLowerCase());
                        boolean check2 = job.getAddress().toLowerCase().contains(strSearch.toLowerCase());
                        boolean check3 = job.getSalary().toLowerCase().contains(strSearch.toLowerCase());
                        boolean check4 = job.getCompany().toLowerCase().contains(strSearch.toLowerCase());
                        boolean check5 = job.getUpdateTime().toLowerCase().contains(strSearch.toLowerCase());
                        if(check1 || check2 || check3 || check4 || check5)
                        {
                            list.add(job);
                        }
                    }
                    mListJob = list;
                }
               FilterResults filterResults = new FilterResults();
                filterResults.values = mListJob;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mListJob = (List<Job>) filterResults.values;
                notifyDataSetChanged();
            }
        };

    }

    public static class SkillAdapter {
        public class SkillViewHolder extends RecyclerView.ViewHolder {
            private View skill_name;
            private String skill_desc;

            public SkillViewHolder(@NonNull View itemView) {
                super(itemView);
                skill_name = itemView.findViewById(R.id.skill_name);
            }
        }
    }
}
