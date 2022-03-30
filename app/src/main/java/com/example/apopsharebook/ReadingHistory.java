package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ReadingHistory extends AppCompatActivity {

    List<ReadingHistoryList> rhList;
    ListView listView;

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
                startActivity(new Intent(ReadingHistory.this,UpdateBook2.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(ReadingHistory.this,BorrowBook2.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(ReadingHistory.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(ReadingHistory.this,UserAccount.class));
            }
            return true;
        });

        //ListView using ArrayList
        rhList = new ArrayList<>();
        rhList.add(new ReadingHistoryList(R.drawable.cover01,"Cloud Cuckoo Land","Anthony Doerr",
                "Scribner","2021","Fiction","mary11","2022-03-06"));
        rhList.add(new ReadingHistoryList(R.drawable.cover02,"One Two Three","Laurie Frankel",
                "Henry Holt and Co","2021","NonFiction","tom22","2022-03-08"));
        rhList.add(new ReadingHistoryList(R.drawable.cover03,"A Court of Silver Flames","Sarah J. Maas",
                "Bloomsbury Publishing","2021","Fiction","allen33","2022-03-19"));
        rhList.add(new ReadingHistoryList(R.drawable.cover04,"Under the Whispering Door","TJ Klune",
                "Tor Books","2021","Fiction","joe55","2022-03-21"));

        listView = findViewById(R.id.ReadingHistoryListView);
        ReadingHistoryListAdapter adapter = new ReadingHistoryListAdapter(this,R.layout.readinghistory_list_item,rhList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }
}