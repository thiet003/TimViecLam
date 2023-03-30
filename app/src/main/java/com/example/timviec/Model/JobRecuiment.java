package com.example.timviec.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "jobRecuiment")
public class JobRecuiment {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public JobRecuiment(String link, String resourceId, String name, String salary, String address, String company, String updateTime) {
        this.link = link;
        this.resourceId = resourceId;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.company = company;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    private String link;
    private String resourceId;
    private String name;
    private String salary;
    private String address;
    private String company;
    private String updateTime;
}
