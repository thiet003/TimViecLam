package com.example.timviec.Model;

import java.io.Serializable;

public class JobDetail implements Serializable {
     private String form;
    private String count;

    public JobDetail(String form, String count, String education, String adressDetail, String desc, String request, String benefit) {
        this.form = form;
        this.count = count;
        this.education = education;
        this.adressDetail = adressDetail;
        this.desc = desc;
        this.request = request;
        this.benefit = benefit;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getAdressDetail() {
        return adressDetail;
    }

    public void setAdressDetail(String adressDetail) {
        this.adressDetail = adressDetail;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    public String getBenefit() {
        return benefit;
    }

    public void setBenefit(String benefit) {
        this.benefit = benefit;
    }

    private String education;
    private String adressDetail;
    private String desc;
    private String request;
    private String benefit;
}
