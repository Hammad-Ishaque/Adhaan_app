package com.example.usama.adhaan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class dua extends AppCompatActivity {

    public TextView heading;
    public TextView dua;
    public TextView translation;

    int position;
    String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dua);

        final String[] name_array=getResources().getStringArray(R.array.urdu);
        final String[] dua_array= getResources().getStringArray(R.array.dua);
        final String[] translation_array= getResources().getStringArray(R.array.translation);

        heading= findViewById(R.id.heading);
        dua=findViewById(R.id.dua);
        translation= findViewById(R.id.translation);

        Bundle extra= getIntent().getExtras();
        position= extra.getInt("get");

        heading.setText(name_array[position]);
        dua.setText(dua_array[position]);
        translation.setText(translation_array[position]);

    }
}
