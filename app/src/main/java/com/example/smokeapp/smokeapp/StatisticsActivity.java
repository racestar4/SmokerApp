package com.example.smokeapp.smokeapp;

import android.app.Activity;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class StatisticsActivity extends Activity {

    TextView aaname;
    TextView aage;
    TextView aasize;
    TextView aweight;
    User user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

         setContentView(R.layout.activity_statistics);
         user = MainActivity.user;

         aaname = (TextView) findViewById(R.id.AnzeigeName);
         aaname.setText(user.name);

         aage = (TextView) findViewById(R.id.AnzeigeAge);
         aage.setText(Integer.toString(user.age));

         aasize = (TextView) findViewById(R.id.AnzeigeSize);
         aasize.setText(Integer.toString(user.size));

         aweight = (TextView) findViewById(R.id.AnzeigeWeight);
         aweight.setText(Integer.toString(user.weight));

    }

}
