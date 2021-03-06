package com.halliday.jack.draganddropchinese;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class Character {

    private List<Object> mChildrenList;
    private int mUUID;
    private String pinyin;
    private String english;
    private boolean isUser;
    private int rad1;
    private int rad2;
    private String charac;

    public Character(){
    }

    public Character(int uuid){
        mUUID = uuid;
    }

    public int getUUID() {
        return mUUID;
    }

    public void setUUID(int UUID) {
        mUUID = UUID;
    }

    public String getCharac() {
        return charac;
    }

    public void setCharac(String charac) {
        this.charac = charac;
    }

    public int getRad1() {
        return rad1;
    }

    public void setRad1(int rad1) {
        this.rad1 = rad1;
    }

    public int getRad2() {
        return rad2;
    }

    public void setRad2(int rad2) {
        this.rad2 = rad2;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public boolean isUser() {
        return isUser;
    }

    public void setUser(boolean isuser) {
        this.isUser = isuser;
    }

}

