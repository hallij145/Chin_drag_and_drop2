package com.halliday.jack.draganddropchinese;

import android.content.ContentValues;
import android.content.Context;
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

public class RadicalLab {
    private static RadicalLab sRadicalLab;
    private Context mContext;
    DatabaseAccess mDatabaseAccess;


    public static RadicalLab get(Context context) {
        if (sRadicalLab == null) {
            sRadicalLab = new RadicalLab(context);
        }
        return sRadicalLab;
    }

    private RadicalLab(Context context) {
        mContext = context.getApplicationContext();
        mDatabaseAccess = DatabaseAccess.getInstance(mContext);
    }

    public List<Radical> getRadicals() {
        List<Radical> radicals = new ArrayList<>();
        CharRadCursorWrapper cursor = mDatabaseAccess.queryRadicals(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                radicals.add(cursor.getRadical());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        mDatabaseAccess.close();
        return radicals;
    }

    public Radical getRadical(int id) {
        CharRadCursorWrapper cursor = mDatabaseAccess.queryRadicals(
                CharRadDbSchema.RadTable.Cols.UUID + " = ?", new String[] { Integer.toString(id) }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getRadical();
        } finally {
            cursor.close();
            mDatabaseAccess.close();
        }
    }

}
