package com.halliday.jack.draganddropchinese;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TabHost;

import java.util.HashMap;

/**
 * Created by wolfk_000 on 12/5/2016.
 */

public class TabsFragmentActivity extends AppCompatActivity implements TabHost.OnTabChangeListener{

    private TabHost mTabHost;
    private HashMap mapTabInfo = new HashMap();
    private TabInfo mLastTab = null;

    private class TabInfo {
        private String mTag;
        private Class mClass;
        private Bundle mArgs;
        private Fragment fragment;
        TabInfo(String tag, Class cls, Bundle args) {
            this.mTag = tag;
            this.mClass = cls;
            this.mArgs = args;
        }
    }

    class TabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        /**
         * @param context
         */
        public TabFactory(Context context) {
            mContext = context;
        }

        /** (non-Javadoc)
         * @see android.widget.TabHost.TabContentFactory#createTabContent(java.lang.String)
         */
        public View createTabContent(String tag) {
            View v = new View(mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }

    }
    /** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Step 1: Inflate layout
        setContentView(R.layout.tabs_layout);
        // Step 2: Setup TabHost
        initialiseTabHost(savedInstanceState);
        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
        }
    }

    /** (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onSaveInstanceState(android.os.Bundle)
     */
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("tab", mTabHost.getCurrentTabTag()); //save the tab selected
        super.onSaveInstanceState(outState);
    }

    /**
     * Step 2: Setup TabHost
     */

    private void initialiseTabHost(Bundle args) {
        mTabHost = (TabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup();
        TabInfo tabInfo = null;
        TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab1").setIndicator("All"), ( tabInfo = new TabInfo("Tab1", DictionaryListFragment.class, args)));
        this.mapTabInfo.put(tabInfo.mTag, tabInfo);
        TabsFragmentActivity.addTab(this, this.mTabHost, this.mTabHost.newTabSpec("Tab2").setIndicator("Saved"), ( tabInfo = new TabInfo("Tab2", UserListFragment.class, args)));
        this.mapTabInfo.put(tabInfo.mTag, tabInfo);

        this.onTabChanged("All"); //default to dict tab

        mTabHost.setOnTabChangedListener(this);
    }

    /**
     //  * @param activity
     // * @param tabHost
     // * @param tabSpec
     // * @param mClass
     // * @param mArgs
     */
    private static void addTab(TabsFragmentActivity activity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
        // Attach a Tab view factory to the spec
        tabSpec.setContent(activity.new TabFactory(activity));
        String tag = tabSpec.getTag();

        // Check to see if we already have a fragment for this tab, probably
        // from a previously saved state.  If so, deactivate it, because our
        // initial state is that a tab isn't shown.
        tabInfo.fragment = activity.getSupportFragmentManager().findFragmentByTag(tag);
        if (tabInfo.fragment != null && !tabInfo.fragment.isDetached()) {
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            ft.detach(tabInfo.fragment);
            ft.commit();
            activity.getSupportFragmentManager().executePendingTransactions();
        }

        tabHost.addTab(tabSpec);
    }

    /** (non-Javadoc)
     * @see android.widget.TabHost.OnTabChangeListener#onTabChanged(java.lang.String)
     */
    public void onTabChanged(String tag) {

        TabInfo newTab = (TabInfo) this.mapTabInfo.get(tag);
        if (mLastTab != newTab) { //if you clicked the other tab
            FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
            if (mLastTab != null) { // if the current tab exists (safe)
                if (mLastTab.fragment != null) { //if the current tab fragment exists
                    ft.detach(mLastTab.fragment);
                }
            }
            if (newTab != null) { //if the new tab exists
                if (newTab.fragment == null) { // if the new tab fragment exists
                    newTab.fragment = Fragment.instantiate(this, newTab.mClass.getName(), newTab.mArgs);
                    ft.add(R.id.realtabcontent, newTab.fragment, newTab.mTag);
                } else {
                    ft.attach(newTab.fragment);
                }
            }

            mLastTab = newTab; //else, if the same tab is clicked, don't do anything
            ft.commit();
            this.getSupportFragmentManager().executePendingTransactions();
        }
    }
}
