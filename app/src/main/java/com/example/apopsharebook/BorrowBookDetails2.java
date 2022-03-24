package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.Timestamp;
import java.util.Date;

public class BorrowBookDetails2 extends AppCompatActivity {
    String title, author, genre, pub, pubYear, owner, status, senderId;
    int bookId;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow_book_details2);

        TextView txtTitle=findViewById(R.id.txtBDetailTitle);
        TextView txtAuthor=findViewById(R.id.txtBDetailAuthor);
        TextView txtGenre=findViewById(R.id.txtBDetailGenre);
        TextView txtPub=findViewById(R.id.txtBDetailPublisher);
        TextView txtPubYear=findViewById(R.id.txtBDetailPubYear);
        TextView txtOwner=findViewById(R.id.txtBDetailOwner);
        TextView txtStatus=findViewById(R.id.txtBDetailStatus);
        Button btnBorrow=findViewById(R.id.btnBorrow);
        Button btnGiveAway=findViewById(R.id.btnBDetailGiveAway);
        Button btnMessage=findViewById(R.id.btnMessage);
        Button btnSend=findViewById(R.id.btnSubmitMsg);
        EditText writeMsg=findViewById(R.id.edTxtWriteMsg);


        Intent i= getIntent();
        if (i != null) {
            title=getIntent().getStringExtra("title");
            txtTitle.setText("Title: "+title);
            author=getIntent().getStringExtra("author");
            txtAuthor.setText("Author: "+author);
            genre=getIntent().getStringExtra("genre");
            txtGenre.setText("Category: "+ genre);
            pub=getIntent().getStringExtra("pub");
            txtPub.setText("Publisher: "+pub);
            pubYear=getIntent().getStringExtra("pubYear");
            txtPubYear.setText("Published: "+pubYear);
            owner=getIntent().getStringExtra("owner");
            txtOwner.setText("Owner ID: " +owner);
            status=getIntent().getStringExtra("status");
            txtStatus.setText("Status: " +status);
            bookId=getIntent().getIntExtra("bookId",0);
            if(status.equals("available") || status.equals("on loan")){
                btnBorrow.setVisibility(View.VISIBLE);
            }
            else if(status.equals("give away")){
                btnGiveAway.setVisibility(View.VISIBLE);
            }

        }
        //Borrow button sends borrow request automatically
        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(BorrowBookDetails2.this);
                senderId=sharedPreferences.getString("userId","NA");

                Timestamp t = new Timestamp(System.currentTimeMillis());
                Date d=new Date(t.getTime());
                String date=d.toString();
                db=new Database(BorrowBookDetails2.this);
                db.sendBorrowRequest(owner, senderId, title,date, bookId );

            }
        });

      //Message button enables user to send personalized message
      btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMsg.setVisibility(View.VISIBLE);
                btnSend.setVisibility(View.VISIBLE);
                /*Intent i = new Intent(BorrowBookDetails2.this,Message.class);
                i.putExtra("bookId",bookId);
                startActivity(i);*/
            }
        });

      btnSend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              String msgContent=writeMsg.getText().toString();
              db=new Database(BorrowBookDetails2.this);
              //db.sendPersonalizedMsg(owner, senderId, date, bookId,msgContent....);
          }
      });
    }
}