package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UserAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);


        ImageButton go_back = findViewById(R.id.btnBack);
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        ImageButton btnMessage = findViewById(R.id.btnMessageIcon);
        ImageButton btnUserEdit = findViewById(R.id.btnUserEdit);
        Button btnReadingHistory = findViewById(R.id.btnReadingHistory);
        Button btnRequestHistory = findViewById(R.id.btnRequestHistory);
        Button btnCurrentLoan = findViewById(R.id.btnCurrentLoan);

        //button event for go back main page
        go_back.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,MainMenu.class)));
        btnMessage.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,Message.class)));
        btnUserEdit.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,EditAccount.class)));
        btnReadingHistory.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,ReadingHistory.class)));
        btnRequestHistory.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,RequestHistory.class)));
        btnCurrentLoan.setOnClickListener(v -> startActivity(new Intent(UserAccount.this,CurrentLoan.class)));

        //the bottom menu bar to link the pages
        bottom_menu.setSelectedItemId(R.id.menu_user_account);
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(UserAccount.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(UserAccount.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(UserAccount.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(UserAccount.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(UserAccount.this,UserAccount.class));
            }
            return true;
        });
    }
}