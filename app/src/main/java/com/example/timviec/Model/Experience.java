package com.example.timviec.Model;

public class Experience {
    String experience_company,experience_time,experience_service,experience_detail;

    public String getExperience_company() {
        return experience_company;
    }

    public void setExperience_company(String experience_company) {
        this.experience_company = experience_company;
    }

    public String getExperience_time() {
        return experience_time;
    }

    public void setExperience_time(String experience_time) {
        this.experience_time = experience_time;
    }

    public String getExperience_service() {
        return experience_service;
    }

    public void setExperience_service(String experience_service) {
        this.experience_service = experience_service;
    }

    public String getExperience_detail() {
        return experience_detail;
    }

    public void setExperience_detail(String experience_detail) {
        this.experience_detail = experience_detail;
    }

    public Experience(String experience_company, String experience_time, String experience_service, String experience_detail) {
        this.experience_company = experience_company;
        this.experience_time = experience_time;
        this.experience_service = experience_service;
        this.experience_detail = experience_detail;
    }
}
