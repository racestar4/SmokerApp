package com.example.smokeapp.smokeapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class InfoActivity extends Activity {

    ListView lView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // load the layout
            setContentView(R.layout.activity_info);

            System.out.println("asdasdajajasfhasfhahskhasf");
            setContentView(R.layout.activity_info);
            String[] functions = getResources().getStringArray(R.array.list_arrayasd);
            ListAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, functions);
        }
}
