package com.example.smokeapp.smokeapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;
import com.google.gson.*;

public class  MainActivity extends AppCompatActivity {

    User user;
    JSONObject jsonObject;
    public static String result ="";

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

        public void fillUserInfo(){

     //    TextView aname = (TextView) findViewById(R.id.AnzeigeName);
            //   TextView aage = (TextView) findViewById(R.id.AnzeigeAlter);

       //  aname.setText(user.name.toString());
      //   aage.setText(Integer.toString(user.age));
        }


        public void login(View view){

         System.out.println("login button clicked");
         EditText name = findViewById(R.id.LoginName);
         EditText pin = findViewById(R.id.LoginPin);

            if ( !name.getText().toString().equals("") && !pin.getText().toString().equals("") ) {
                user.name = name.getText().toString();
                user.pin = Integer.parseInt(pin.getText().toString());
                System.out.println("database connection");

                Gson gson = new Gson();
                this.result = null;
                new DataBaseConnection().execute("userInformation", gson.toJson(user));
                System.out.println("database connection succeded");
            }

                while( this.result == null){
                System.out.println("waiting");
                }

            if ( this.result != null) {
                 String[]output = result.split("\\}");
                 output[1] = output[1].substring(2);
                 this.result = output[1];
            }
                this.result = "{ "  + this.result + " }";


                        Gson gson = new Gson();
                        user.name="";

                        user = gson.fromJson(this.result, User.class);
                        System.out.println(this.result);

                       if ( user.size != 0){
                           loggedIn = true;
                           System.out.println(user.name+ " " + user.age+ " " +user.size+ " " +user.weight);
                           fillUserInfo();
                           setContentView(R.layout.activity_main);

                       }else{
                           Toast.makeText(this,"Fehlerhafter Login", Toast.LENGTH_LONG);
                       }


            }

        // On Click register user settings
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
            String send = gson.toJson(user);
            System.out.println(send);
            new DataBaseConnection().execute("saveUserInformation",send);
            loggedIn = true;
            setContentView(R.layout.activity_main);
        }
}
