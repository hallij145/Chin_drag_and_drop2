package com.halliday.jack.draganddropchinese;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CharlieC on November/18/16.
 */

public class StartActivity extends AppCompatActivity implements RadicalListFragment.Callbacks{
    private RadicalListFragment mRadicalListFragment;
    private CharacterListFragment mCharacterListFragment;
    private boolean isSecond = false;
    public TextView mTitle;
    public TextView mEnglish;
    public TextView mPinYin;
    public String title = "Character";
    public String english = "English";
    public String pinyin = "Pinyin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mTitle = (TextView) findViewById(R.id.char_item_list_title);
        mEnglish = (TextView) findViewById(R.id.english);
        mPinYin = (TextView) findViewById(R.id.pinyin);

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

    @Override
    protected void onPause() {
        super.onPause();
        CharacterLab characterLab = CharacterLab.get(this);
        characterLab.clearCombinationList();
    }


    @Override
    public void onRadicalSelected(int radical) {
        CharacterLab characterLab = CharacterLab.get(this);

        if(isSecond){
            characterLab.getCombinationsFromSecondRadical(radical);
            isSecond = false;
            if(!characterLab.getCombCharacters().isEmpty()){
                mTitle.setText(title);
                mEnglish.setText(english);
                mPinYin.setText(pinyin);
            }
        }
        else{
            characterLab.clearCombinationList();
            characterLab.getCharactersFromRadicalID(radical);
            isSecond = true;
            mTitle.setText("");
            mEnglish.setText("");
            mPinYin.setText("");
        }
        mCharacterListFragment.updateUI();
    }

}
