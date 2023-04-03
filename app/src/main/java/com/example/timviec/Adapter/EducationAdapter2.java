package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Education;
import com.example.timviec.R;

import java.util.List;

public class EducationAdapter2 extends RecyclerView.Adapter<EducationAdapter2.EducationViewHolder>{
    private List<Education> mList;
    public void setData(List<Education> list)
    {
        mList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public EducationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.education_item2,parent,false);
        return new EducationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EducationViewHolder holder, int position) {
        Education education = mList.get(position);
        if(education == null)
        {
            return;
        }
        holder.school.setText(education.getEducation_school());
        holder.time.setText(education.getEducation_time());
        holder.degree.setText(education.getEducation_degree());
        holder.detail.setText(education.getEducation_detail());
    }
    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }
    public class EducationViewHolder extends RecyclerView.ViewHolder {
        private TextView school,time,degree,detail;
        public EducationViewHolder(@NonNull View itemView) {
            super(itemView);
            school = itemView.findViewById(R.id.edu_school);
            time = itemView.findViewById(R.id.edu_time);
            degree = itemView.findViewById(R.id.edu_degree);
            detail = itemView.findViewById(R.id.edu_desc);
        }
    }
}
