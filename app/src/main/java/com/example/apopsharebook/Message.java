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

public class Message extends AppCompatActivity {

    //fake data
    String[] messageTitle = {"A request from abc0123","A message from cvb3der","A message from 412dd2"};
    String[] messageDate = {"2022-03-02","2022-03-08","2022-03-11","2022-03-21"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ImageButton go_back = findViewById(R.id.btnBack);
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);

        //button event for go back main page
        go_back.setOnClickListener(v -> startActivity(new Intent(Message.this,MainMenu.class)));

        //the bottom menu bar to link the pages
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(Message.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(Message.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(Message.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(Message.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(Message.this,UserAccount.class));
            }
            return true;
        });

        //ListView
        List<HashMap<String,String>> messageList = new ArrayList<>();

        for(int i=0; i<messageTitle.length; i++) {
            HashMap<String,String> hashmap = new HashMap<>();
            hashmap.put("title",messageTitle[i]);
            hashmap.put("date",messageDate[i]);
            messageList.add(hashmap);
        }

        String[] from = {"title","date"};
        int[] to = {R.id.txtMessage,R.id.txtMessageDate};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(),messageList,
                R.layout.message_list_item,from,to);

        ListView listView = findViewById(R.id.CureentLoanListView);
        listView.setAdapter(adapter);

    }
}