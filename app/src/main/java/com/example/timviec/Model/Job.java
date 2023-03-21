package com.example.timviec.Model;

public class Job {
    private int resourceId;
    private String name, salary, address, count, updateTime;

    public Job(int resourceId, String name, String salary, String address, String count, String updateTime) {
        this.resourceId = resourceId;
        this.name = name;
        this.salary = salary;
        this.address = address;
        this.count = count;
        this.updateTime = updateTime;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
