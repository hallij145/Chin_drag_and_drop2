package com.halliday.jack.draganddropchinese;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.halliday.jack.draganddropchinese.database.CharRadCursorWrapper;
import com.halliday.jack.draganddropchinese.database.CharRadDbSchema;
import com.halliday.jack.draganddropchinese.database.DatabaseAccess;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class CharacterLab {
    private static CharacterLab sCharacterLab;
    private Context mContext;
    DatabaseAccess mDatabaseAccess;


    public static CharacterLab get(Context context) {
        if (sCharacterLab == null) {
            sCharacterLab = new CharacterLab(context);
        }
        return sCharacterLab;
    }

    private CharacterLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabaseAccess = DatabaseAccess.getInstance(mContext);
    }

    private static ContentValues getContentValues(Character character) {
        ContentValues values = new ContentValues();
        values.put(CharRadDbSchema.CharTable.Cols.UUID, character.getUUID());
        values.put(CharRadDbSchema.CharTable.Cols.PINYIN, character.getPinyin());
        values.put(CharRadDbSchema.CharTable.Cols.ENGLISH, character.getEnglish());
        values.put(CharRadDbSchema.CharTable.Cols.RADICAL1, character.getRad1());
        values.put(CharRadDbSchema.CharTable.Cols.RADICAL2, character.getRad2());
        values.put(CharRadDbSchema.CharTable.Cols.ISUSER, character.isUser());
        values.put(CharRadDbSchema.CharTable.Cols.ISCOMB, character.isCombination());
        values.put(CharRadDbSchema.CharTable.Cols.CHARAC, character.isUser());
        return values;
    }

    /*public void updateChar(Character character) {
        String uuidString = character.getUUID().toString();
        ContentValues values = getContentValues(character);
        mDatabase.update(CharRadDbSchema.CharTable.NAME, values,
                CharRadDbSchema.CharTable.Cols.UUID + " = ?",
                new String[] { uuidString });
    }*/

    public List<Character> getCharacters() {
        List<Character> characters = new ArrayList<>();
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                characters.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        mDatabaseAccess.close();
        return characters;
    }

    public List<Character> getUserCharacters(){
        int isUser = 1;
        List<Character> characters = new ArrayList<>();
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.ISCOMB + " = ?", new String[] { Integer.toString(isUser) }
        );
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                characters.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        mDatabaseAccess.close();
        return characters;
    }

    public List<Character> getCombinationCharacters(){
        int isComb = 1;
        List<Character> characters = new ArrayList<>();
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.ISCOMB + " = ?", new String[] { Integer.toString(isComb) }
        );
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                characters.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        mDatabaseAccess.close();
        return characters;
    }

    public Character getCharacter(UUID id) {
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.UUID + " = ?", new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getCharacter();
        } finally {
            cursor.close();
            mDatabaseAccess.close();
        }
    }

}