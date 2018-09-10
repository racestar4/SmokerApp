package com.example.smokeapp.smokeapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Toast;

import org.json.JSONObject;

public class  MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User user = new User();
        Button config = (Button) findViewById(R.id.config);
        Button statistics = (Button) findViewById(R.id.statistic);
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(dataBaseConnection.doInBackground("userInformation", "bob"));
        }catch (Exception e){
            System.out.println(e.toString());
        }
        config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_config);
            }
        });

        statistics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_statistics);
                Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void saveSettings(View view){
        Toast.makeText(this, "Button1 Clicked!", Toast.LENGTH_SHORT).show();



        setContentView(R.layout.activity_main);
    }
}
