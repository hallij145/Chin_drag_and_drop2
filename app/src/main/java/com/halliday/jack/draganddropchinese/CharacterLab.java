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
    private List<Character> mRadCombinations = new ArrayList<>();
    private List<Character> mSecondRadCombinations = new ArrayList<>();
    private List<Character> mUserCharacters = new ArrayList<>();


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
        /*int isUser = 1;
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.ISUSER + " = ?", new String[] { Integer.toString(isUser) });
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mUserCharacters.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }*/
        return mUserCharacters;
    }

    public void getCharactersFromRadicalID(int id){
        CharRadCursorWrapper cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.RADICAL1 + " = ?", new String[] { Integer.toString(id) });
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mRadCombinations.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        cursor = mDatabaseAccess.queryCharacters(
                CharRadDbSchema.CharTable.Cols.RADICAL2 + " = ?", new String[] { Integer.toString(id) });
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                mRadCombinations.add(cursor.getCharacter());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }

        mDatabaseAccess.close();
    }

    public void getCombinationsFromSecondRadical(int id){
        for (Character character: mRadCombinations) {
            if (character.getRad1() == id || character.getRad2() == id){
                mSecondRadCombinations.add(character);
            }
        }

    }

    public List<Character> getCombCharacters(){
        return mSecondRadCombinations;
    }

    public void clearCombinationList(){
        mRadCombinations.clear();
        mSecondRadCombinations.clear();
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

    public void setCharacterUserTrue(Character character){
        character.setUser(true);
        mDatabaseAccess.updateCharacterInfo(character);
        mUserCharacters.add(character);
    }

}