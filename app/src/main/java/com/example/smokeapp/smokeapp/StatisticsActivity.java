package com.example.smokeapp.smokeapp;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends Activity {

    TextView aaname;
    User user;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

         setContentView(R.layout.activity_statistics);
         user = MainActivity.user;
         aaname = (TextView) findViewById(R.id.AnzeigeName);
         aaname.setText(user.name);

    }

}
