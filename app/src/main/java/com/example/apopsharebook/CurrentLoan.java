package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CurrentLoan extends AppCompatActivity {

    List<CurrentLoanList> clList;
    ListView listView;

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

        //Using ListView and ArrayList
        String[] from = {"cover","title","author","publisher","year","category","owner","date"};
        int[] to = {R.id.imgBookCover,R.id.txtBookTitle,R.id.txtBookAuthor,R.id.txtBookPublisher,
                    R.id.txtBookYear,R.id.txtBookCategory,R.id.txtBookOwner,R.id.txtBorrowBookDate};

        clList = new ArrayList<>();
        clList.add(new CurrentLoanList(R.drawable.cover01,"Cloud Cuckoo Land","Anthony Doerr",
                "Scribner","2021","Fiction","mary11","2022-03-06"));
        clList.add(new CurrentLoanList(R.drawable.cover02,"One Two Three","Laurie Frankel",
                "Henry Holt and Co","2021","NonFiction","tom22","2022-03-08"));
        clList.add(new CurrentLoanList(R.drawable.cover03,"A Court of Silver Flames","Sarah J. Maas",
                "Bloomsbury Publishing","2021","Fiction","allen33","2022-03-19"));
        clList.add(new CurrentLoanList(R.drawable.cover04,"Under the Whispering Door","TJ Klune",
                "Tor Books","2021","Fiction","joe55","2022-03-21"));

        listView = findViewById(R.id.CureentLoanListView);
        CurrentLoanListAdapter adapter = new CurrentLoanListAdapter(this,R.layout.currentloan_list_item,clList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }
}