package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BorrowBookDetails2 extends AppCompatActivity {
    String title, author, genre, pub, pubYear, owner, status;
    // int bookId;
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
            txtOwner.setText("Owner ID" +owner);
            status=getIntent().getStringExtra("status");
            txtStatus.setText("Status:" +status);
            //bookId=getIntent().getIntExtra("bookId",0);

            if(status.equals("available") || status.equals("on loan")){
                btnBorrow.setVisibility(View.VISIBLE);
            }
            else if(status.equals("give away")){
                btnGiveAway.setVisibility(View.VISIBLE);
            }

        }



      /* btnMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(BorrowBookDetails.this,Message.class);
                i.putExtra("bookId",bookId);
                startActivity(i);
            }
        });*/
    }
}