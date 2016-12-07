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
 * Created by CharlieC on November/30/16.
 */

public class DictionaryListFragment extends Fragment {
    private RecyclerView mDictRecyclerView;
    private DictionaryAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dict_list, container, false);

        mDictRecyclerView = (RecyclerView) view.findViewById(R.id.myDictList);

        mDictRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CharacterLab characterLab = CharacterLab.get(getActivity());
        List<Character> characters = characterLab.getCharacters();

        mAdapter = new DictionaryAdapter(characters);
        mDictRecyclerView.setAdapter(mAdapter);
    }

    private class DictionaryHolder extends RecyclerView.ViewHolder {
        public Character mDictionary;
        public TextView mTitleTextView;
        public DictionaryHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.dict_item_list_title_text_view);
        }

        public void bindDictionary(Character Dictionary) {
            mDictionary = Dictionary;
            mTitleTextView.setTextSize(50);
            mTitleTextView.setText(mDictionary.getCharac());
        }
    }

    private class DictionaryAdapter extends RecyclerView.Adapter<DictionaryHolder> {
        private List<Character> mCharacters;
        public DictionaryAdapter(List<Character> characters) {
            mCharacters = characters;
        }
        @Override
        public DictionaryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.dict_item_list, parent, false);
            return new DictionaryHolder(view);
        }

        @Override
        public void onBindViewHolder(DictionaryListFragment.DictionaryHolder holder, int position) {
            Character Dictionary = mCharacters.get(position);
            holder.bindDictionary(Dictionary);
        }

        @Override
        public int getItemCount() {
            return mCharacters.size();
        }

        public void setDictionarys(List<Character> Characters) {
            mCharacters = Characters;
        }
    }
}
