package com.halliday.jack.draganddropchinese;

import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class Character {

    private UUID mUUID;
    private String pinyin;
    private String english;
    private Boolean isUser = false;
    private Boolean isCombination = false;

    Character(){
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

    public Boolean isUser() {
        return isUser;
    }

    public void setIsUser(Boolean isuser) {
        this.isUser = isuser;
    }

    public Boolean isCombination() {
        return isCombination;
    }

    public void setIsCombination(Boolean iscomb){
        this.isCombination = iscomb;
    }
}
