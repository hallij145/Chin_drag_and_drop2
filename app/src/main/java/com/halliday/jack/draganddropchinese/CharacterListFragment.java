package com.halliday.jack.draganddropchinese;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CharlieC on November/18/16.
 */

public class CharacterListFragment extends Fragment {
    private RecyclerView mCharRecyclerView;
    private CharacterAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_char_list, container, false);

        mCharRecyclerView = (RecyclerView) view.findViewById(R.id.myCharList);

        mCharRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CharacterLab characterLab = CharacterLab.get(getActivity());
        List<Character> characters = characterLab.getCombinationCharacters();

        mAdapter = new CharacterAdapter(characters);
        mCharRecyclerView.setAdapter(mAdapter);
    }

    private class CharacterHolder extends RecyclerView.ViewHolder {
        public Character mCharacter;
        public TextView mTitleTextView;
        public CharacterHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.char_item_list_title_text_view);
        }

        public void bindCharacter(Character character) {
            mCharacter = character;
            mTitleTextView.setTextSize(25);
            mTitleTextView.setText(mCharacter.getCharac());
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
