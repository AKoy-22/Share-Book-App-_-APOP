package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RequestHistory extends AppCompatActivity {
    List<RequestHistoryList> rList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_history);

        ImageButton go_back = findViewById(R.id.btnBack);
        BottomNavigationView bottom_menu = findViewById(R.id.bottom_menu);
        ImageButton btnMessage = findViewById(R.id.btnMessageIcon);

        //button event for go back main page
        go_back.setOnClickListener(v -> startActivity(new Intent(RequestHistory.this,UserAccount.class)));
        btnMessage.setOnClickListener(v -> startActivity(new Intent(RequestHistory.this,Message.class)));

        //the bottom menu bar to link the pages
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(RequestHistory.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(RequestHistory.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(RequestHistory.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(RequestHistory.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(RequestHistory.this,UserAccount.class));
            }
            return true;
        });

        //ListView using ArrayList
        rList = new ArrayList<>();
        rList.add(new RequestHistoryList("Cloud Cuckoo Land","Rent Out", "mary11"));
        rList.add(new RequestHistoryList("One Two Three","Rent Out","tom22"));
        rList.add(new RequestHistoryList("A Court of Silver Flames","Give Away","allen33"));
        rList.add(new RequestHistoryList("Under the Whispering Door","Give Away","joe55"));

        listView = findViewById(R.id.RequestHistoryListView);
        RequestHistoryListAdapter adapter = new RequestHistoryListAdapter(this,R.layout.requesthistory_list_item,rList);
        listView.setAdapter(adapter);
        listView.setDivider(null);
    }
}