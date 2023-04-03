package com.example.timviec.Model;

import java.io.Serializable;

public class Activity implements Serializable {
    String activity_name,activity_time,activity_role,activity_detail;

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public String getActivity_time() {
        return activity_time;
    }

    public void setActivity_time(String activity_time) {
        this.activity_time = activity_time;
    }

    public String getActivity_role() {
        return activity_role;
    }

    public void setActivity_role(String activity_role) {
        this.activity_role = activity_role;
    }

    public String getActivity_detail() {
        return activity_detail;
    }

    public void setActivity_detail(String activity_detail) {
        this.activity_detail = activity_detail;
    }

    public Activity(String activity_name, String activity_time, String activity_role, String activity_detail) {
        this.activity_name = activity_name;
        this.activity_time = activity_time;
        this.activity_role = activity_role;
        this.activity_detail = activity_detail;
    }
}
