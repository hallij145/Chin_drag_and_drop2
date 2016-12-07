package com.halliday.jack.draganddropchinese;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenuActivity extends AppCompatActivity {
    private ImageButton mStartButton;
    private ImageButton mDictionaryButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mStartButton = (ImageButton) findViewById(R.id.Start);
        mDictionaryButton = (ImageButton) findViewById(R.id.Dictionary);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), StartActivity.class);
                startActivity(i);
            }
        });

        mDictionaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), TabsFragmentActivity.class);
                startActivity(i);
            }
        });

    }

}
