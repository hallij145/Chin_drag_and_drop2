package com.halliday.jack.draganddropchinese;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by CharlieC on November/18/16.
 */

public class RadicalLab {
    private static RadicalLab sRadicalLab;
    private List<Radical> mRadicals;

    public static RadicalLab get(Context context) {
        if (sRadicalLab == null) {
            sRadicalLab = new RadicalLab(context);
        }
        return sRadicalLab;
    }

    private RadicalLab(Context context) {
        mRadicals = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Radical radical = new Radical();
            radical.setPinyin("R" + i);
            mRadicals.add(radical);
        }
    }

    public List<Radical> getRadicals() {
        return mRadicals;
    }
    public Radical getRadical(UUID id) {
        for (Radical radical : mRadicals) {
            if (radical.getUUID().equals(id)) {
                return radical;
            }
        }
        return null;
    }
}
