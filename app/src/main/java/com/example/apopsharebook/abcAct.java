package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class abcAct extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abc);
        TextView out = findViewById(R.id.textView3);
        Intent i = getIntent();
        Bundle args = i.getBundleExtra("BUNDLE");
        if (!args.isEmpty()) {
            ArrayList<String> prefs = (ArrayList<String>) args.getSerializable("ARRAYLIST");
            out.setText(prefs.get(0)+"\n"+prefs.get(1)+"\n"+prefs.get(2));
        }
    }
}