package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class SearchOutput extends AppCompatActivity implements RecyclerAdapter.ItemClickListener{
    ImageButton btnBack, btnMsgIcon;
    BottomNavigationView bottom_menu;
    String keyword;

    int img=R.drawable.book_cover_1;
    RecyclerAdapter adapter;
    Database database;
    Cursor c;
    String inpLoc, title, author, genre, pub, pubYear, owner, status;
    int bookId, price;
    ArrayList<String> bTitles, bAuthors, bGenres, bPub, bPubYear, bOwner, bStatus;
    ArrayList<Integer> bIds, bPrices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_output);
        database=new Database(this);

        Intent i=getIntent();
        if(i!=null){
           keyword=getIntent().getStringExtra("searchWord");
        }
        c=database.searchBooksByTitle(keyword);

        bTitles=new ArrayList<String>();
        bAuthors=new ArrayList<String >();
        bGenres=new ArrayList<String>();
        bPub=new ArrayList<String>();
        bPubYear=new ArrayList<String>();
        bOwner=new ArrayList<String>();
        bStatus=new ArrayList<String>();
        bIds=new ArrayList<Integer>();
        bPrices=new ArrayList<Integer>();

        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                bTitles.add(c.getString(0));
                bAuthors.add(c.getString(1));
                bGenres.add(c.getString(2));
                bPub.add(c.getString(3));
                bPubYear.add(c.getString(4));
                bOwner.add(c.getString(5));
                bStatus.add(c.getString(6));
                bIds.add(c.getInt(7));
                bPrices.add(c.getInt(8));
            }
        } else if (c.getCount() == 0) {
            Toast.makeText(SearchOutput.this, "No books matching search criteria", Toast.LENGTH_LONG).show();
        }

        RecyclerView recyclerView=findViewById(R.id.recyclerViewSearch);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter=new RecyclerAdapter(this, img,bTitles, bAuthors, bGenres);
        adapter.setClickListener(SearchOutput.this);
        recyclerView.setAdapter(adapter);

    }



    @Override
    public void onItemClick(View view, int position) {

    }
}