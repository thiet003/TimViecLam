package com.example.timviec.Model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "job")
public class Job implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String link;



    private String resourceId;
    private String name;
    private String salary;
    private String address;
    private String company;
    private String updateTime;
    @Ignore
    private JobDetail Jdetail;
    @Ignore
    private boolean isFavorite;
    public  Job(){};
    public Job(String resourceId, String link, String name, String salary, String address, String company, String updateTime) {
        this.resourceId = resourceId;
        this.link = link;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.company = company;
        this.updateTime = updateTime;
    }

    public Job(String resourceId, String name, String salary, String address, String company, String updateTime, JobDetail jdetail) {
        this.resourceId = resourceId;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.company = company;
        this.updateTime = updateTime;
        Jdetail = jdetail;
        this.isFavorite = false;
    }


    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }



    public JobDetail getJdetail() {
        return Jdetail;
    }

    public void setJdetail(JobDetail jdetail) {
        Jdetail = jdetail;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public boolean getIsFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }


    public Job(String resourceId, String name, String salary, String address, String company, String updateTime) {
        this.resourceId = resourceId;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.company= company;
        this.updateTime = updateTime;
        isFavorite = false;
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

    public void setCompany(String count) {
        this.company = count;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
