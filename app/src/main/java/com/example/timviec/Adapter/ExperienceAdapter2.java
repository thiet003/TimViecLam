package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Experience;
import com.example.timviec.R;

import java.util.List;

public class ExperienceAdapter2  extends RecyclerView.Adapter<ExperienceAdapter2.ExperienceViewHolder> {
    List<Experience> mList;
    public void setData(List<Experience> list)
    {
        mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ExperienceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.experience_item2,parent,false);
        return new ExperienceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExperienceViewHolder holder, int position) {
        Experience experience = mList.get(position);
        if(experience == null)
        {
            return;
        }
        holder.company.setText(experience.getExperience_company());
        holder.time.setText(experience.getExperience_time());
        holder.service.setText(experience.getExperience_service());
        holder.detail.setText(experience.getExperience_detail());
    }

    @Override
    public int getItemCount() {
        if (mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    public class ExperienceViewHolder extends RecyclerView.ViewHolder {
        private TextView company,time,service,detail;
        public ExperienceViewHolder(@NonNull View itemView) {
            super(itemView);
            company = itemView.findViewById(R.id.exp_school);
            time = itemView.findViewById(R.id.exp_time);
            service = itemView.findViewById(R.id.exp_service);
            detail = itemView.findViewById(R.id.exp_desc);
        }
    }
}
