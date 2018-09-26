package com.example.smokeapp.smokeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;
import com.google.gson.*;

public class  MainActivity extends AppCompatActivity {

    User user;
    JSONObject jsonObject;
    static String result;

    boolean loggedIn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

// wenn user existiert auf main seite, wenn kein user existiert auf config seite.
// ( Eindeutige unterscheidung nötig, Multiple User


        if ( user == null) {
            user = new User();
            setContentView(R.layout.activity_config);
        }else {
            setContentView(R.layout.activity_main);
        }

    }

    // On Click statistics
    public void statistics (View view){
        setContentView(R.layout.activity_statistics);
     //   Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
    }
    //On Click info

    public void info(View view){
        setContentView(R.layout.activity_info);
    }
    //On Click config
     public void config (View view) {
        setContentView(R.layout.activity_config);
     }
     /*public void back ( View view ){
        setContentView(R.layout.activity_main);
     }
     */

     public void switchReg( View view ){
        setContentView(R.layout.activity_reg);
     }

        @Override
        public void onBackPressed() {
            if (loggedIn) {
                setContentView(R.layout.activity_main);
            } else {
                setContentView(R.layout.activity_config);
            }
        }
        public void smoked(View view){
        Toast.makeText(this,"Du raucher", Toast.LENGTH_SHORT).show();
        }

        public void switchLog(View view){
        setContentView(R.layout.activity_config);
        }


        // On Click save user settings
        public void registerUser (View view){

            EditText tv1 =  findViewById(R.id.InputName);
            EditText tv2 =  findViewById(R.id.InputPin);
            EditText tv3 =  findViewById(R.id.InputAge);
            EditText tv4 =  findViewById(R.id.InputHeight);
            EditText tv5 =  findViewById(R.id.InputWeight);


            if ( !tv1.getText().toString().equals("")) {
                user.name = tv1.getText().toString();
            }
            if( !tv2.getText().toString().equals("")) {
                user.pin = Integer.parseInt(tv2.getText().toString());
            }
            if ( !tv3.getText().toString().equals("")) {
                user.age = Integer.parseInt(tv3.getText().toString());
            }
            if ( !tv4.getText().toString().equals("")) {
                user.size = Integer.parseInt(tv4.getText().toString());
            }
            if ( !tv5.getText().toString().equals("")) {
                user.weight = Integer.parseInt(tv5.getText().toString());
            }

            Gson gson = new Gson();
            new DataBaseConnection().execute("saveUserInformation",gson.toJson(user));
            loggedIn = true;
            setContentView(R.layout.activity_main);
        }
}
