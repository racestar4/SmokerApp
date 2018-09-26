package com.example.smokeapp.smokeapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONObject;

public class  MainActivity extends AppCompatActivity {

    User user;
    JSONObject jsonObject;

    public static String result = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


     //  Button config = findViewById(R.id.config);
     //   Button statistics =findViewById(R.id.statistic);


        if ( user == null) {
            user = new User();
            setContentView(R.layout.activity_config);
        }else {
            setContentView(R.layout.activity_main);
            //new DataBaseConnection().execute("userInformation", user.name);
        }

    }

    // On Click statistics
    public void statistics (View view){
        try{
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            System.out.println("ERROR with RESULT");
            System.out.println(e.toString());
        }
        setContentView(R.layout.activity_statistics);
    }

    //On Click config
     public void config (View view) {
        setContentView(R.layout.activity_config);
     }

        // On Click save user settings
        public void saveSettings (View view){

            TextView tv1 =  findViewById(R.id.InputName);
            TextView tv2 =  findViewById(R.id.InputSize);

            if ( !tv1.getText().toString().equals("")) {
                user.name = tv1.getText().toString();
            }
            if( !tv2.getText().toString().equals("")) {
                user.height = Integer.parseInt(tv2.getText().toString());
            }
            Toast.makeText(this, "Name ist : " + user.name+ " und bin " + user.height + " groß.", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);
        }

    public void setResult(String result) {
        this.result = result;
    }
}
