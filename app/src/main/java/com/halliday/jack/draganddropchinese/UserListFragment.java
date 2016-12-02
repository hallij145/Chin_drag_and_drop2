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

public class UserListFragment extends Fragment {
    private RecyclerView mUserRecyclerView;
    private UserAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list, container, false);

        mUserRecyclerView = (RecyclerView) view.findViewById(R.id.myUserList);

        mUserRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        CharacterLab characterLab = CharacterLab.get(getActivity());
        List<Character> characters = characterLab.getUserCharacters();

        mAdapter = new UserAdapter(characters);
        mUserRecyclerView.setAdapter(mAdapter);
    }

    private class UserHolder extends RecyclerView.ViewHolder {
        public Character mDictionary;
        public TextView mTitleTextView;
        public UserHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.user_item_list_title_text_view);
        }

        public void bindDictionary(Character character) {
            mDictionary = character;
            mTitleTextView.setText(mDictionary.getPinyin());
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder> {
        private List<Character> mCharacters;
        public UserAdapter(List<Character> characters) {
            mCharacters = characters;
        }
        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.user_item_list, parent, false);
            return new UserHolder(view);
        }

        @Override
        public void onBindViewHolder(UserHolder holder, int position) {
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
