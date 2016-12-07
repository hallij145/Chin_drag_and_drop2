package com.halliday.jack.draganddropchinese;

import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class Radical {

    private int mUUID;
    private String pinyin;
    private String english;
    private String charac;

    public Radical(){
    }

    public int getUUID() {
        return mUUID;
    }

    public void setUUID(int UUID) {
        mUUID = UUID;
    }

    public String getCharacter() {
        return charac;
    }

    public void setCharacter(String charac) {
        this.charac = charac;
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

}
