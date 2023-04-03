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

public class SkillAdapter2 extends RecyclerView.Adapter<SkillAdapter2.SkillViewHolder> {
    private List<Skill> mList;
    @NonNull
    @Override
    public SkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_item2,parent,false);
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
        holder.skill_name2.setText(skill.getName_skill());
        holder.skill_desc2.setText(skill.getDesc_skill());
    }

    @Override
    public int getItemCount() {
        if(mList != null) return mList.size();
        return 0;
    }
    public class SkillViewHolder extends RecyclerView.ViewHolder {
        private TextView skill_name2, skill_desc2;
        public SkillViewHolder(@NonNull View itemView) {
            super(itemView);
            skill_name2 = itemView.findViewById(R.id.skill_name2);
            skill_desc2 = itemView.findViewById(R.id.skill_desc2);
        }
    }
}
