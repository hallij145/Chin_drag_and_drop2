package com.halliday.jack.draganddropchinese;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by CharlieC on November/18/16.
 */

public class RadicalListFragment extends Fragment {
    private RecyclerView mRadicalRecyclerView;
    private RadicalAdapter mAdapter;
    private Callbacks mCallbacks;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface Callbacks{
        void onRadicalSelected(int radical);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
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

    private class RadicalHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        public Radical mRadical;
        public TextView mTitleTextView;
        public RadicalHolder(View itemView) {
            super(itemView);
            mTitleTextView = (TextView) itemView.findViewById(R.id.rad_item_list_title_text_view);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        public void bindRadical(Radical radical) {
            mRadical = radical;
            mTitleTextView.setTextSize(40);
            mTitleTextView.setText(mRadical.getCharacter());
        }

        @Override
        public void onClick(View v) {
            mCallbacks.onRadicalSelected(mRadical.getUUID());
        }

        @Override
        public boolean onLongClick(View v) {
            Context context = getContext();
            CharSequence text = "("+this.mRadical.getPinyin()+") "+this.mRadical.getEnglish();
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context,text,duration);
            toast.show();
            return true;
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
