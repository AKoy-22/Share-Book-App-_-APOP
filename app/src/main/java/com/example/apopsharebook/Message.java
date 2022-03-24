package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Message extends AppCompatActivity {

    Database db;
    String userId;
    Cursor c;
    String msgTitle, msgDate, msgFrom;
    ArrayList<String> msgTitlesList, msgDatesList, msgFromList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        msgTitlesList=new ArrayList<String>();
        msgDatesList=new ArrayList<String>();
        msgFromList=new ArrayList<String>();

        db=new Database(this);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        userId=sharedPreferences.getString("userId","NA");
        c=db.viewMessages(userId);

        if(c.getCount()>0){
            while(c.moveToNext()){
                msgTitle=c.getString(6);
                msgTitlesList.add(msgTitle);
                msgDate=c.getString(4);
                msgDatesList.add(msgDate);
                /*msgFrom=c.getString(2);
                msgFromList.add(msgFrom);*/
            }


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

        for(int i=0; i< msgTitlesList.size(); i++) {
            HashMap<String,String> hashmap = new HashMap<>();
            hashmap.put("title",msgTitlesList.get(i));
            hashmap.put("date",msgDatesList.get(i));
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
}