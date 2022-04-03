package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class AdminMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu2);

        Button btnCreateUser = findViewById(R.id.btnCreateUser);
        Button btnViewUser = findViewById(R.id.btnViewUser);
        ImageButton btnMessage = findViewById(R.id.btnMessageIcon);

        btnCreateUser.setOnClickListener(v -> startActivity(new Intent(AdminMenu.this,AdminCreateUser.class)));
        btnViewUser.setOnClickListener(v -> startActivity(new Intent(AdminMenu.this,AdminUserList.class)));
        btnMessage.setOnClickListener(v -> startActivity(new Intent(AdminMenu.this,Message.class)));
    }
}