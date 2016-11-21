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
    }

    public List<Character> getCharacters() {
        return mCharacters;
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
