package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class ReadingHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reading_history);

        ImageButton go_back = findViewById(R.id.btnBack);
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        ImageButton btnMessage = findViewById(R.id.btnMessageIcon);

        //button event for go back main page
        go_back.setOnClickListener(v -> startActivity(new Intent(ReadingHistory.this,UserAccount.class)));
        btnMessage.setOnClickListener(v -> startActivity(new Intent(ReadingHistory.this,Message.class)));

        //the bottom menu bar to link the pages
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(ReadingHistory.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(ReadingHistory.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(ReadingHistory.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(ReadingHistory.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(ReadingHistory.this,UserAccount.class));
            }
            return true;
        });
    }
}