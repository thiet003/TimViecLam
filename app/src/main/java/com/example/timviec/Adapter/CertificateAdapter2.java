package com.example.timviec.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timviec.Model.Certificate;
import com.example.timviec.R;

import java.util.List;

public class CertificateAdapter2 extends RecyclerView.Adapter<CertificateAdapter2.CertificateViewHolder>{
    List<Certificate> mList;
    public void setData(List<Certificate> list)
    {
        mList = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CertificateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.certificate_item2,parent,false);
        return new CertificateViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CertificateViewHolder holder, int position) {
        Certificate certificate = mList.get(position);
        if(certificate==null)
        {
            return;
        }
        holder.time.setText(certificate.getCertificate_time());
        holder.detail.setText(certificate.getCertificate_detail());
    }

    @Override
    public int getItemCount() {
        if(mList !=null)
        {
            return mList.size();
        }
        return 0;
    }

    public class CertificateViewHolder extends RecyclerView.ViewHolder {
        private TextView time,detail;
        public CertificateViewHolder(@NonNull View itemView) {
            super(itemView);
            time = itemView.findViewById(R.id.prize_time);
            detail =itemView.findViewById(R.id.prize_desc);
        }
    }
}