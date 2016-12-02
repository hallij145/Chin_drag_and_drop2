package com.halliday.jack.draganddropchinese;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by CharlieC on November/18/16.
 */

public class StartActivity extends FragmentActivity {
    private RadicalListFragment mRadicalListFragment;
    private CharacterListFragment mCharacterListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        FragmentManager fm = getSupportFragmentManager();
        Fragment list_fragment = fm.findFragmentById(R.id.rad_list_container);
        Fragment combination_list_fragment = fm.findFragmentById(R.id.combination_list_container);

        if (list_fragment == null|| combination_list_fragment == null){
            FragmentTransaction trans = fm.beginTransaction();
            if (list_fragment == null){
                mRadicalListFragment = new RadicalListFragment();
                trans.add(R.id.rad_list_container, mRadicalListFragment);
            }
            if (combination_list_fragment == null){
                mCharacterListFragment = new CharacterListFragment();
                trans.add(R.id.combination_list_container, mCharacterListFragment);
            }
            trans.commit();
        }
    }
}
