package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Login extends AppCompatActivity {
Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //creating Database object
        db=new Database(this);
        Button btnLoginMain = findViewById(R.id.btnLogin1);
        //Instantiating SharedPreference Object and editor to write persistent data
        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);

        btnLoginMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView userId = findViewById(R.id.inpEmail);
                String uId = userId.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("userId", uId);
                editor.commit();
                startActivity(new Intent(Login.this, MainMenu.class));
            }




        });
    }



}