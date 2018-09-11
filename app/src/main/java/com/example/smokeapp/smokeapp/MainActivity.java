package com.example.smokeapp.smokeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

public class  MainActivity extends AppCompatActivity {

    User user;
    JSONObject jsonObject;
    DataBaseConnection dataBaseConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

      //  Button config = findViewById(R.id.config);
     //   Button statistics =findViewById(R.id.statistic);

        dataBaseConnection = new DataBaseConnection();

        if ( user == null) {
            user = new User();
            setContentView(R.layout.activity_config);
        }else {
            setContentView(R.layout.activity_main);
        }

        try {
            jsonObject = new JSONObject(dataBaseConnection.doInBackground("userInformation", "bob"));
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    // On Click statistics
    public void statistics (View view){

        setContentView(R.layout.activity_statistics);
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
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
}
