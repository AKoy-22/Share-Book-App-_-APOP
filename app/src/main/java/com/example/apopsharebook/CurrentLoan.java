package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class CurrentLoan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_loan);

        ImageButton go_back = findViewById(R.id.btnBack);
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        ImageButton btnMessage = findViewById(R.id.btnMessageIcon);

        //button event for go back main page
        go_back.setOnClickListener(v -> startActivity(new Intent(CurrentLoan.this,UserAccount.class)));
        btnMessage.setOnClickListener(v -> startActivity(new Intent(CurrentLoan.this,Message.class)));

        //the bottom menu bar to link the pages
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(CurrentLoan.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(CurrentLoan.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(CurrentLoan.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(CurrentLoan.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(CurrentLoan.this,UserAccount.class));
            }
            return true;
        });
    }
}