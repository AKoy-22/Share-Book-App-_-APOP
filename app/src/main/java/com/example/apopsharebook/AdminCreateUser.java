package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class AdminCreateUser extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_create_user);

		ImageButton btnMessage = findViewById(R.id.btnMessageIcon);
		ImageButton go_back = findViewById(R.id.btnBack);

		btnMessage.setOnClickListener(v -> startActivity(new Intent(AdminCreateUser.this,Message.class)));
		go_back.setOnClickListener(v -> startActivity(new Intent(AdminCreateUser.this,AdminMenu.class)));
	}
}