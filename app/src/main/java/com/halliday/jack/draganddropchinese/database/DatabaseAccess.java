package com.halliday.jack.draganddropchinese.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.halliday.jack.draganddropchinese.Character;

/**
 * Created by CharlieC on December/7/16.
 */

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new CharBaseHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public CharRadCursorWrapper queryCharacters(String whereClause, String[] whereArgs) {
        open();
        Cursor cursor = database.query(
                CharRadDbSchema.CharTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CharRadCursorWrapper(cursor);
    }

    public CharRadCursorWrapper queryRadicals(String whereClause, String[] whereArgs) {
        open();
        Cursor cursor = database.query(
                CharRadDbSchema.RadTable.NAME,
                null, // Columns - null selects all columns
                whereClause,
                whereArgs,
                null, // groupBy
                null, // having
                null  // orderBy
        );
        return new CharRadCursorWrapper(cursor);
    }

    private static ContentValues getContentValues(Character character) {
        ContentValues values = new ContentValues();
        values.put(CharRadDbSchema.CharTable.Cols.UUID, character.getUUID());
        values.put(CharRadDbSchema.CharTable.Cols.ENGLISH, character.getEnglish());
        values.put(CharRadDbSchema.CharTable.Cols.PINYIN, character.getPinyin());
        values.put(CharRadDbSchema.CharTable.Cols.ISUSER, character.isUser());
        values.put(CharRadDbSchema.CharTable.Cols.CHARAC, character.getCharac());
        values.put(CharRadDbSchema.CharTable.Cols.RADICAL1, character.getRad1());
        values.put(CharRadDbSchema.CharTable.Cols.RADICAL2, character.getRad2());

        return values;
    }

    public void updateCharacterInfo(Character character){
        boolean isuser = character.isUser();
        int isUser = 0;

        if(isuser) isUser = 1;

        ContentValues values = getContentValues(character);
        open();
        database.update(CharRadDbSchema.CharTable.NAME, values, CharRadDbSchema.CharTable.Cols.ISUSER + " = ?", new String[] { Integer.toString(isUser) });
        Log.i("DATABASEACCESS", Boolean.toString(character.isUser()));
        close();

    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

}