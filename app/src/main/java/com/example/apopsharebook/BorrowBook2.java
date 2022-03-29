package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class BorrowBook2 extends AppCompatActivity implements RecyclerAdapter.ItemClickListener{

    int img=R.drawable.book_cover_1;
    Button btnSearch;
    ImageButton btnBack, btnMsgIcon;
    BottomNavigationView bottom_menu;
    Spinner spnLoc;
    RecyclerAdapter adapter;
    Database database;
    Cursor c;
    String inpLoc, title, author, genre, pub, pubYear, owner, status;
    int bookId;
    ArrayList<String> bTitles, bAuthors, bGenres, bPub, bPubYear, bOwner, bStatus;
    ArrayList<Integer> bIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book2);

        btnSearch=findViewById(R.id.btnSearch);
        spnLoc=findViewById(R.id.spnLoc);
        database=new Database(this);
        //database.addUser();
        //database.manuallyAddBook();
        database.manuallyAddPref();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bTitles=new ArrayList<String>();
                bAuthors=new ArrayList<String >();
                bGenres=new ArrayList<String>();
                bPub=new ArrayList<String>();
                bPubYear=new ArrayList<String>();
                bOwner=new ArrayList<String>();
                bStatus=new ArrayList<String>();
                bIds=new ArrayList<Integer>();


                inpLoc=spnLoc.getSelectedItem().toString();
                c=database.searchBookByLocation(inpLoc);

                if(c.getCount()>0){
                    while(c.moveToNext()){
                        bTitles.add(c.getString(0));
                        bAuthors.add(c.getString(1));
                        bGenres.add(c.getString(2));
                        bPub.add(c.getString(3));
                        bPubYear.add(c.getString(4));
                        bOwner.add(c.getString(5));
                        bStatus.add(c.getString(6));
                        bIds.add(c.getInt(7));
                       // bId.add(c.getInt(7));
                    }
                }

                RecyclerView recyclerView=findViewById(R.id.recyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(BorrowBook2.this));
                adapter=new RecyclerAdapter(BorrowBook2.this, img,bTitles, bAuthors, bGenres);
                adapter.setClickListener(BorrowBook2.this);
                recyclerView.setAdapter(adapter);
            }
        });

        //Clicking back button will return to Main Menu activity
        btnBack=findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BorrowBook2.this,MainMenu.class));
            }
        });
        //Clicking message image icon will open Message activity
        btnMsgIcon=findViewById(R.id.btnMessageIcon);
        btnMsgIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BorrowBook2.this,Message.class));
            }
        });


        //the bottom menu bar to link the pages
        bottom_menu=findViewById(R.id.bottom_menu);
        bottom_menu.setOnItemSelectedListener(menuItem -> {
            if(menuItem.getItemId() == R.id.menu_add_book) {
                startActivity(new Intent(BorrowBook2.this,AddBook.class));
            } else if(menuItem.getItemId() == R.id.menu_update_book) {
                startActivity(new Intent(BorrowBook2.this,UpdateBook.class));
            } else if(menuItem.getItemId() == R.id.menu_borrow_book) {
                startActivity(new Intent(BorrowBook2.this,BorrowBook.class));
            } else if(menuItem.getItemId() == R.id.menu_reading_tracker) {
                startActivity(new Intent(BorrowBook2.this,ReadingTracker.class));
            } else if(menuItem.getItemId() == R.id.menu_user_account) {
                startActivity(new Intent(BorrowBook2.this,UserAccount.class));
            }
            return true;
        });

    }

    @Override
    public void onItemClick(View view, int position) {
        title=bTitles.get(position);
        author=bAuthors.get(position);
        genre=bGenres.get(position);
        pub=bPub.get(position);
        pubYear=bPubYear.get(position);
        owner=bOwner.get(position);
        status=bStatus.get(position);
        bookId=bIds.get(position);
        Intent i = new Intent(BorrowBook2.this, BorrowBookDetails2.class);
        i.putExtra("title",title);
        i.putExtra("author", author);
        i.putExtra("genre",genre);
        i.putExtra("pub",pub);
        i.putExtra("pubYear",pubYear);
        i.putExtra("owner",owner);
        i.putExtra("status",status);
        i.putExtra("bookId",bookId);
        startActivity(i);

    }

}