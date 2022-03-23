package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class AdminMenu extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_menu);

		Button btnCreateUser = findViewById(R.id.btnCreateUser);
		Button btnViewUser = findViewById(R.id.btnViewUser);

		btnCreateUser.setOnClickListener(v -> startActivity(new Intent(AdminMenu.this,AdminCreateUser.class)));
		btnViewUser.setOnClickListener(v -> startActivity(new Intent(AdminMenu.this,AdminUserList.class)));
	}
}