package com.example.timviec.Model;

public class Education {
    String education_school,education_time, education_degree,education_detail;

    public String getEducation_school() {
        return education_school;
    }

    public void setEducation_school(String education_school) {
        this.education_school = education_school;
    }

    public String getEducation_time() {
        return education_time;
    }

    public void setEducation_time(String education_time) {
        this.education_time = education_time;
    }

    public String getEducation_degree() {
        return education_degree;
    }

    public void setEducation_degree(String education_degree) {
        this.education_degree = education_degree;
    }

    public String getEducation_detail() {
        return education_detail;
    }

    public void setEducation_detail(String education_detail) {
        this.education_detail = education_detail;
    }

    public Education(String education_school, String education_time, String education_degree, String education_detail) {
        this.education_school = education_school;
        this.education_time = education_time;
        this.education_degree = education_degree;
        this.education_detail = education_detail;
    }
}
