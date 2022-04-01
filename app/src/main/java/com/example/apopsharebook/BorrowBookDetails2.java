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
import android.widget.Toast;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class BorrowBookDetails2 extends AppCompatActivity {
    String title, author, genre, pub, pubYear, owner, status, senderId;
    int bookId;
    Database db;
    boolean success,success2;




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
        //Borrow option is chosen, message sent automatically to the owner
        btnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE,30);
                Date retDate = cal.getTime();

                Random r = new Random();
                int low = 10;
                int high = 100;
                int price = r.nextInt(high-low) + low;

                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(BorrowBookDetails2.this);
                senderId=sharedPreferences.getString("userId","NA");
                String borrowerId = sharedPreferences.getString("userId","NA");

                Timestamp t = new Timestamp(System.currentTimeMillis());
                Date d=new Date(t.getTime());
                String StartDate=d.toString();
                String returnDate = retDate.toString();

                String request="Borrow request has been sent from";
                String type="Borrow Request";

                db=new Database(BorrowBookDetails2.this);
                success=db.sendMessage(owner, senderId,StartDate, bookId ,request,type);

                if(success){
                    Toast.makeText(BorrowBookDetails2.this,"Borrow Request has been sent.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(BorrowBookDetails2.this,"Message not sent.Please try again.", Toast.LENGTH_LONG).show();
                }
                success2 = db.addToLoanTable(bookId,borrowerId,StartDate,returnDate,Integer.toString(price));
            }
        });

        //Give-Away option is chosen, message sent automatically to the owner
        btnGiveAway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(BorrowBookDetails2.this);
                senderId=sharedPreferences.getString("userId","NA");



                Timestamp t = new Timestamp(System.currentTimeMillis());
                Date d=new Date(t.getTime());
                String date=d.toString();
                String content=senderId+" wants to have your book titled :"+title;
                String type="Give-Away Request";
                db=new Database(BorrowBookDetails2.this);
                success=db.sendMessage(owner, senderId, date, bookId ,content, type);

                if(success){
                    Toast.makeText(BorrowBookDetails2.this,"Give-Away Request has been sent.", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(BorrowBookDetails2.this,"Message not sent.Please try again.", Toast.LENGTH_LONG).show();
                }
            }
        });

     //Send Personalized Message option is chosen. Clicking  button enables user to send personalized message
      btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMsg.setVisibility(View.VISIBLE);
                btnSend.setVisibility(View.VISIBLE);
                btnBorrow.setVisibility(View.GONE);
                btnGiveAway.setVisibility(View.GONE);
            }
        });
      //method to submit personalized message
      btnSend.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(BorrowBookDetails2.this);
              senderId=sharedPreferences.getString("userId","NA");

              String msgContent=writeMsg.getText().toString();
              Timestamp t = new Timestamp(System.currentTimeMillis());
              Date d=new Date(t.getTime());
              String date=d.toString();
              String type="Personalized Message";
              db=new Database(BorrowBookDetails2.this);
              success=db.sendMessage(owner, senderId, date, bookId ,msgContent,type);
              if(success){
                  Toast.makeText(BorrowBookDetails2.this,"Personalized message has been sent.", Toast.LENGTH_LONG).show();
              }
              else{
                  Toast.makeText(BorrowBookDetails2.this,"Message not sent.Please try again.", Toast.LENGTH_LONG).show();
              }
          }
      });
    }
}