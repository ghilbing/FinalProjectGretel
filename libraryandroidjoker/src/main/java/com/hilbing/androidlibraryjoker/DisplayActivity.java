package com.hilbing.androidlibraryjoker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        TextView jokeTextView = findViewById(R.id.joke_tv);
        Intent intent = getIntent();
        String joke = intent.getStringExtra(getString(R.string.envelope));

        if (joke !=null){
            jokeTextView.setText(joke);
        }
        else jokeTextView.setText(R.string.no_jokes_found);
    }
}
