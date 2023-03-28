package com.example.timviec;

import android.os.AsyncTask;

import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobDetail;

import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.List;

public class DataFromInternet2 extends AsyncTask<Void,Void, JobDetail> {
    private String url;
    private ICPutData2 icPutData2;
    @Override
    protected JobDetail doInBackground(Void... voids) {
                    org.jsoup.nodes.Document document2 = null;
            try {
                document2 = Jsoup.connect(url).get();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            String cnt = document2.select(".box-item").get(1).select("span").text();
            String form = document2.select(".box-item").get(2).select("span").text();
            String edu = document2.select(".box-item").get(5).select("span").text();
            String addresss =document2.select(".box-address").text();
            String tmp = addresss.substring(17);
            String tmp2 = chuyen(tmp);
            String desc = document2.select(".content-tab").first().select("p").text();
            String descc = document2.select(".content-tab").first().select("ul").text();
            if(desc.length()==0) desc =descc;
            String desc2 = chuyen(desc);
            String request = document2.select(".content-tab").get(1).select("p").text();
            String requestt = document2.select(".content-tab").get(1).select("ul").text();
            if (request.length()==0) request = requestt;
            String request2 = chuyen(request);
            String benefit = document2.select(".content-tab").get(2).select("p").text();
            String benefitt = document2.select(".content-tab").get(2).select("ul").text();
            if (benefit.length()==0) benefit = benefitt;
            String benefit2 = chuyen(benefit);
            JobDetail jobDetail = new JobDetail(form,cnt,edu,tmp2,desc2,request2,benefit2);
        return jobDetail;
    }
    public interface ICPutData2{
        void putToInforActivity(JobDetail jobDetail);
    }
    public DataFromInternet2(ICPutData2 icPutData2,String url)
    {
        this.icPutData2 = icPutData2;
        this.url = url;
    }
    protected void onPostExecute(JobDetail jobDetail) {
        if (icPutData2 != null) {
            icPutData2.putToInforActivity(jobDetail);
        }
    }
    private String chuyen(String desc)
    {
        String desc2 ="";
        for(int i=0;i<desc.length();i++)
        {
            if(i!=0 && desc.charAt(i)=='-' && desc.charAt(i-1)=='.')
            {
                desc2 += "\n";

            }
            else if(i>1 && desc.charAt(i)=='-' && desc.charAt(i-1)==' ' && desc.charAt(i-2)=='.')
            {
                desc2 += "\n";

            }
            else if(i>1 && desc.charAt(i)=='-' && desc.charAt(i-1)==' ' && desc.charAt(i-2)=='…')
            {
                desc2 += "\n";
            }
            else if(i>1 && desc.charAt(i)=='-' && desc.charAt(i-1)==' ' && desc.charAt(i-2)==';')
            {
                desc2 += "\n";
            }
            else if(desc.charAt(i)=='•')
            {
                desc2 += "\n";
            }
            desc2 +=desc.charAt(i);
        }
        return desc2;
    }
}
