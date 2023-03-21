package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Skill;
import com.example.timviec.R;

import java.util.List;

public class SkillAdapter extends RecyclerView.Adapter<SkillAdapter.SkillViewHolder>{
    private List<Skill> mList;
    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item,parent,false);
        return new SkillViewHolder(view);
    }

    public void setData(List<Skill> list)
    {
        this.mList = list;
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(@NonNull SkillViewHolder holder, int position) {
        Skill skill = mList.get(position);
        if(skill == null)
        {
            return;
        }
        holder.skill_name.setText(skill.getName_skill());
        holder.skill_desc.setText(skill.getDesc_skill());
    }

    @Override
    public int getItemCount() {
        if(mList != null) return mList.size();
        return 0;
    }

    public class SkillViewHolder extends RecyclerView.ViewHolder {
        private TextView skill_name, skill_desc;
        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skill_name = itemView.findViewById(R.id.skill_name);
            skill_desc = itemView.findViewById(R.id.skill_desc);
        }
    }
}
