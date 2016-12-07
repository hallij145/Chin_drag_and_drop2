package com.halliday.jack.draganddropchinese;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by CharlieC on November/18/16.
 */

public class RadicalListFragment extends Fragment {
    private RecyclerView mRadicalRecyclerView;
    private RadicalAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rad_list, container, false);

        mRadicalRecyclerView = (RecyclerView) view.findViewById(R.id.myRadList);

        mRadicalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI() {
        RadicalLab radicalLab = RadicalLab.get(getActivity());
        List<Radical> radicals = radicalLab.getRadicals();

        mAdapter = new RadicalAdapter(radicals);
        mRadicalRecyclerView.setAdapter(mAdapter);
    }

    private class RadicalHolder extends RecyclerView.ViewHolder {
        public Radical mRadical;
        public TextView mTitleTextView;
        public RadicalHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.rad_item_list_title_text_view);
        }

        public void bindRadical(Radical radical) {
            mRadical = radical;
            mTitleTextView.setTextSize(40);
            mTitleTextView.setText(mRadical.getCharacter());
        }
    }

    private class RadicalAdapter extends RecyclerView.Adapter<RadicalHolder> {
        private List<Radical> mRadicals;
        public RadicalAdapter(List<Radical> radicals) {
            mRadicals = radicals;
        }
        @Override
        public RadicalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.rad_item_list, parent, false);
            return new RadicalHolder(view);
        }

        @Override
        public void onBindViewHolder(RadicalHolder holder, int position) {
            Radical radical = mRadicals.get(position);
            holder.bindRadical(radical);
        }

        @Override
        public int getItemCount() {
            return mRadicals.size();
        }

        public void setRadicals(List<Radical> radicals) {
            mRadicals = radicals;
        }
    }

}
