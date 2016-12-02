package com.halliday.jack.draganddropchinese;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class CharacterLab {
    private static CharacterLab sCharacterLab;
    private List<Character> mCharacters;
    private List<Character> mUserCharacters;
    private List<Character> mCombination;

    public static CharacterLab get(Context context) {
        if (sCharacterLab == null) {
            sCharacterLab = new CharacterLab(context);
        }
        return sCharacterLab;
    }

    private CharacterLab(Context context) {
        mCharacters = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Character character = new Character();
            character.setPinyin("C_Pinyin" + i);
            mCharacters.add(character);
        }
        mUserCharacters = new ArrayList<>();
        for (Character character : mCharacters){
            if (character.isUser()){
                mUserCharacters.add(character);
            }
        }
        mCombination = new ArrayList<>();
        for (Character character : mCharacters){
            if (character.isCombination()){
                mCombination.add(character);
            }
        }
    }

    public List<Character> getCharacters() {
        return mCharacters;
    }

    public List<Character> getUserCharacters(){
        return mUserCharacters;
    }

    public List<Character> getCombinationCharacters(){
        return mCombination;
    }

    public Character getCharacter(UUID id) {
        for (Character character : mCharacters) {
            if (character.getUUID().equals(id)) {
                return character;
            }
        }
        return null;
    }

}
