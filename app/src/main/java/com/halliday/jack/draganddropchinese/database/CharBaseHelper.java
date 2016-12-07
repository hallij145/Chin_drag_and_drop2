package com.halliday.jack.draganddropchinese.database;

import android.content.Context;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by CharlieC on December/7/16.
 */

public class CharBaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "draganddropchinese.db";
    private static final int DATABASE_VERSION = 1;

    public CharBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}