package com.halliday.jack.draganddropchinese;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by CharlieC on November/18/16.
 */

public class DictionaryActivity extends FragmentActivity {
    private TextView mTextView;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_dictionary);

        mTextView = (TextView) findViewById(R.id.myTextView);
        mTextView.setText("Dictionary");

    }
}
