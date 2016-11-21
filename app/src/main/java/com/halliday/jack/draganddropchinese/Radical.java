package com.halliday.jack.draganddropchinese;

import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class Radical {

    private UUID mUUID;
    private String pinyin;
    private String english;

    public Radical(){
        mUUID = UUID.randomUUID();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public void setUUID(UUID UUID) {
        mUUID = UUID;
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
