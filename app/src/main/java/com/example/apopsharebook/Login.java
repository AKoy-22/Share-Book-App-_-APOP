package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLoginMain = findViewById(R.id.btnLogin1);

        btnLoginMain.setOnClickListener(v -> startActivity(new Intent(Login.this,MainMenu.class)));
    }
}