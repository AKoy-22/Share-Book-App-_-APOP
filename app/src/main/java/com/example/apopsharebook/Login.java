package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {
Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //creating Database object
        db=new Database(this);
        Button btnLoginMain = findViewById(R.id.btnLogin1);

        btnLoginMain.setOnClickListener(v -> startActivity(new Intent(Login.this,MainMenu.class)));
    }
}