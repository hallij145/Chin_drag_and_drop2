package com.halliday.jack.draganddropchinese;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CharlieC on November/18/16.
 */

public class CharacterListFragment extends Fragment {
    private RecyclerView mCharRecyclerView;
    private CharacterAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_char_list, container, false);

        mCharRecyclerView = (RecyclerView) view.findViewById(R.id.myCharList);

        mCharRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    public void updateUI() {
        CharacterLab characterLab = CharacterLab.get(getActivity());
        List<Character> characters = characterLab.getCombCharacters();

        mAdapter = new CharacterAdapter(characters);
        mCharRecyclerView.setAdapter(mAdapter);
    }

    private class CharacterHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public Character mCharacter;
        public TextView mTitleTextView;
        public TextView mEnglishTextView;
        public TextView mPinYinTextView;

        public CharacterHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.char_item_list_title_text_view);
            mEnglishTextView = (TextView) itemView.findViewById(R.id.english_text_view);
            mPinYinTextView = (TextView) itemView.findViewById(R.id.pinyin_text_view);


            itemView.setOnClickListener(this);
        }

        public void bindCharacter(Character character) {
            mCharacter = character;
            mTitleTextView.setText(mCharacter.getCharac());
            mEnglishTextView.setText(mCharacter.getEnglish());
            mPinYinTextView.setText(mCharacter.getPinyin());
        }

        @Override
        public void onClick(View v) {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext()); //Read Update
            alertDialog.setTitle("Save Character");
            alertDialog.setMessage("Add Character to User Dictionary?");

            alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface alertDialog, int which) {
                    CharacterLab characterLab = CharacterLab.get(getContext());
                    Log.i("CHARACTERLISTFRAGMENT1",Boolean.toString(mCharacter.isUser()));
                    characterLab.setCharacterUserTrue(mCharacter);
                    Log.i("CHARACTERLISTFRAGMENT2",Boolean.toString(mCharacter.isUser()));
                }});
            alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface alertDialog, int which) {
                    //do nothing
                }});

            alertDialog.show();
        }
    }

    private class CharacterAdapter extends RecyclerView.Adapter<CharacterHolder> {
        private List<Character> mCharacters;
        public CharacterAdapter(List<Character> characters) {
            mCharacters = characters;
        }
        @Override
        public CharacterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.char_item_list, parent, false);
            return new CharacterHolder(view);
        }

        @Override
        public void onBindViewHolder(CharacterHolder holder, int position) {
            Character character = mCharacters.get(position);
            holder.bindCharacter(character);
        }

        @Override
        public int getItemCount() {
            return mCharacters.size();
        }

        public void setCharacters(List<Character> characters) {
            mCharacters = characters;
        }
    }
}
