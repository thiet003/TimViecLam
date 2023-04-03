package com.example.timviec.Model;

import android.net.Uri;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public User( String userName, String surName, String imgUri, String factName, String address, String gender, String introduce, String exp, String addressSub) {
        this.userName = userName;
        this.surName = surName;
        this.imgUri = imgUri;
        this.factName = factName;
        this.address = address;
        this.gender = gender;
        this.introduce = introduce;
        this.exp = exp;
        this.addressSub = addressSub;
    }

    private String userName;

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    private String surName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    public String getFactName() {
        return factName;
    }

    public void setFactName(String factName) {
        this.factName = factName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAddressSub() {
        return addressSub;
    }

    public void setAddressSub(String addressSub) {
        this.addressSub = addressSub;
    }

    private String imgUri;
    @Ignore
    public User(String userName, String imgUri, String factName, String address, String gender, String introduce, String exp, String addressSub) {
        this.userName = userName;
        this.imgUri = imgUri;
        this.factName = factName;
        this.address = address;
        this.gender = gender;
        this.introduce = introduce;
        this.exp = exp;
        this.addressSub = addressSub;
    }

    private String factName;
    private String address;
    private String gender;
    private String introduce;
    private String exp;
    private String addressSub;


}
