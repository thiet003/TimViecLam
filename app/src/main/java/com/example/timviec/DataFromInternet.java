package com.example.timviec;

import android.os.AsyncTask;

import com.example.timviec.Activities.MainActivity;
import com.example.timviec.Model.Job;
import com.example.timviec.Model.JobDetail;
import com.example.timviec.database.JobDatabase;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataFromInternet extends AsyncTask<Void,Void,List<Job>> {
    private ICPutData icPutData;
    private MainActivity mainActivity;
    private String url;

    @Override
    protected List<Job> doInBackground(Void... voids) {
        List<Job> myList = new ArrayList<>();
        org.jsoup.nodes.Document document1 = null;
        try {
            document1 = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = document1.getElementsByClass("job-item-default");
        for (Element e : elements) {
            String image = e.select(".w-100").attr("src");
            String name = e.select(".title").select("span").get(0).text();
            String company = e.select(".company").text();
            String address = e.select(".address").text();
            String salary = e.select(".title-salary").text();
            String deadline = e.select(".time").text();


            String link = e.select(".title").select("a").attr("href");
//            org.jsoup.nodes.Document document2 = null;
//            try {
//                document2 = Jsoup.connect(link).get();
//            } catch (IOException ex) {
//                throw new RuntimeException(ex);
//            }
//            String cnt = document2.select(".box-item").get(1).select("span").text();
//            String form = document2.select(".box-item").get(2).select("span").text();
//            String edu = document2.select(".box-item").get(5).select("span").text();
//            String addresss =document2.select(".box-address").text();
//            String tmp = addresss.substring(17);
//            String desc = document2.select(".content-tab").first().select("p").text();
//            String request = document2.select(".content-tab").get(1).select("p").text();
//            String benefit = document2.select(".content-tab").get(2).select("p").text();

            myList.add(new Job(image,link,name,salary,address,company,deadline));
            JobDatabase.getInstance(mainActivity).jobDAO().insertJob(new Job(image,link,name,salary,address,company,deadline));
        }
        return myList;
    }

    public interface ICPutData{
        void putToFragment(List<Job> list);
    }
    public DataFromInternet(ICPutData icPutData,String url,MainActivity mainActivity)
    {
        this.icPutData = icPutData;
        this.url = url;
        this.mainActivity = mainActivity;
    }
    protected void onPostExecute(List<Job> list) {
        if (icPutData != null) {
            icPutData.putToFragment(list);
        }
    }
}
