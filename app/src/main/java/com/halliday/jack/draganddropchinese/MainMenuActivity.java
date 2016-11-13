package com.halliday.jack.draganddropchinese;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {
    private Button mStartButton;
    private Button mDictionaryButton;
    private Button mSettingsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mStartButton = (Button) findViewById(R.id.StartButton);
        mDictionaryButton = (Button) findViewById(R.id.Dictionary);
        mSettingsButton = (Button) findViewById(R.id.Settings);

    }
}
