package com.halliday.jack.draganddropchinese;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.TabHost;

import java.util.HashMap;

/**
 * Created by CharlieC on November/18/16.
 */

public class DictionaryActivity extends FragmentActivity {
    private DictionaryListFragment mDictionaryListFragment;
    private UserListFragment mUserListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        FragmentManager fm = getSupportFragmentManager();
        Fragment user_dict_fragment = fm.findFragmentById(R.id.user_dictionary);
        Fragment lib_dict_fragment = fm.findFragmentById(R.id.library_dictionary);

        if (lib_dict_fragment == null || user_dict_fragment == null) {
            FragmentTransaction trans = fm.beginTransaction();
            if (lib_dict_fragment == null) {
                mDictionaryListFragment = new DictionaryListFragment();
                trans.add(R.id.library_dictionary, mDictionaryListFragment);
            }
            if (user_dict_fragment == null) {
                mUserListFragment = new UserListFragment();
                trans.add(R.id.user_dictionary, mUserListFragment);
            }
            trans.commit();
        }
    }
}
