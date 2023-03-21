package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Job;
import com.example.timviec.R;

import java.util.ArrayList;
import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.JobViewHolder> implements Filterable {

    private List<Job> mListJob;
    private List<Job> mListJobOld;
    private IClickFavoriteJobListener iClickFavoriteJobListener;



    public interface IClickFavoriteJobListener{
        void onClickFavoriteJob(ImageView img_favorite, Job job);
    }
    public void setDataforHome(List<Job> list, IClickFavoriteJobListener listener)
    {
        this.mListJob = list;
        this.iClickFavoriteJobListener = listener;
        notifyDataSetChanged();
    }
    public void setData(List<Job> list, IClickFavoriteJobListener listener)
    {
        this.mListJob = null;
        this.mListJobOld = list;
        this.iClickFavoriteJobListener = listener;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_job,parent,false);
        return new JobViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = mListJob.get(position);
        if(job == null)
        {
            return;
        }
        holder.img_job.setImageResource(job.getResourceId());
        holder.tv_job_name.setText(job.getName());
        holder.tv_job_salary.setText(job.getSalary());
        holder.tv_job_address.setText(job.getAddress());
        holder.tv_job_count.setText(job.getCount());
        holder.tv_job_updateTime.setText(job.getUpdateTime());

        //Xu li bat xu kien
        holder.img_favorite_job.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iClickFavoriteJobListener.onClickFavoriteJob(holder.img_favorite_job,job);
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
        private TextView tv_job_name,tv_job_salary,tv_job_address,tv_job_count,tv_job_updateTime;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            img_job = itemView.findViewById(R.id.img_job);
            tv_job_name = itemView.findViewById(R.id.tv_job_name);
            tv_job_salary = itemView.findViewById(R.id.tv_job_salary);
            tv_job_address = itemView.findViewById(R.id.tv_job_address);
            tv_job_count = itemView.findViewById(R.id.tv_job_count);
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
                        if(job.getName().toLowerCase().contains(strSearch.toLowerCase()))
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
