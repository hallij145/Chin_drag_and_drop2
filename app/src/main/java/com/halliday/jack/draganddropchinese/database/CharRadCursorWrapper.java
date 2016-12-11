package com.halliday.jack.draganddropchinese.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.halliday.jack.draganddropchinese.Character;
import com.halliday.jack.draganddropchinese.Radical;

/**
 * Created by CharlieC on December/7/16.
 */

public class CharRadCursorWrapper extends CursorWrapper {

    public CharRadCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Character getCharacter() {
        int uuid = getInt(getColumnIndex(CharRadDbSchema.CharTable.Cols.UUID));
        String pinyin = getString(getColumnIndex(CharRadDbSchema.CharTable.Cols.PINYIN));
        String english = getString(getColumnIndex(CharRadDbSchema.CharTable.Cols.ENGLISH));
        String charac = getString(getColumnIndex(CharRadDbSchema.CharTable.Cols.CHARAC));
        int rad1 = getInt(getColumnIndex(CharRadDbSchema.CharTable.Cols.RADICAL1));
        int rad2 = getInt(getColumnIndex(CharRadDbSchema.CharTable.Cols.RADICAL2));
        int isuser = getInt(getColumnIndex(CharRadDbSchema.CharTable.Cols.ISUSER));

        Character character = new Character();
        character.setUUID(uuid);
        character.setPinyin(pinyin);
        character.setEnglish(english);
        character.setRad1(rad1);
        character.setRad2(rad2);
        character.setCharac(charac);
        if (isuser == 1){
            character.setUser(true);
        }
        else{
            character.setUser(false);
        }

        return character;
    }

    public Radical getRadical() {
        int uuid = getInt(getColumnIndex(CharRadDbSchema.RadTable.Cols.UUID));
        String pinyin = getString(getColumnIndex(CharRadDbSchema.RadTable.Cols.PINYIN));
        String english = getString(getColumnIndex(CharRadDbSchema.RadTable.Cols.ENGLISH));
        String charac = getString(getColumnIndex(CharRadDbSchema.RadTable.Cols.CHARAC));

        Radical radical = new Radical();
        radical.setUUID(uuid);
        radical.setPinyin(pinyin);
        radical.setCharacter(charac);
        radical.setEnglish(english);

        return radical;
    }
}