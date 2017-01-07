package com.example.nonu.anew;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void press_number(View view){
        Intent numIntent = new Intent(this, Numbers.class);
        startActivity(numIntent);
    }

    public void press_phrases(View view){
        Intent phrIntent = new Intent(this, Phrases.class);
        startActivity(phrIntent);
    }

    public void press_family(View view){
        Intent famIntent = new Intent(this, Family.class);
        startActivity(famIntent);
    }

    public void press_colors(View view){
        Intent colorIntent = new Intent(this, Colors.class);
        startActivity(colorIntent);
    }
}
