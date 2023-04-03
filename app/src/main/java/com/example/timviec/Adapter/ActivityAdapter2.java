package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Activity;
import com.example.timviec.R;

import java.util.List;

public class ActivityAdapter2 extends RecyclerView.Adapter<ActivityAdapter2.ActivityViewHolder> {
    List<Activity> mList;
    public void setData(List<Activity> list)
    {
        mList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity2,parent,false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        Activity activity = mList.get(position);
        holder.name.setText(activity.getActivity_name());
        holder.time.setText(activity.getActivity_time());
        holder.role.setText(activity.getActivity_role());
        holder.detail.setText(activity.getActivity_detail());
    }

    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    public class ActivityViewHolder extends RecyclerView.ViewHolder {
        private TextView name,time,role,detail;
        public ActivityViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.acti_name);
            time = itemView.findViewById(R.id.acti_time);
            role = itemView.findViewById(R.id.acti_role);
            detail = itemView.findViewById(R.id.acti_desc);
        }
    }
}
