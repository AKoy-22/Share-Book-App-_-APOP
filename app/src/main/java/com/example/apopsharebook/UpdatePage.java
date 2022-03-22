package com.example.apopsharebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class UpdatePage extends AppCompatActivity {

    Database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_page);
        db = new Database(this);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String Author = intent.getStringExtra("author");
        String year = intent.getStringExtra("year");
        String isbn = intent.getStringExtra("isbn");
        String publisher = intent.getStringExtra("pub");
        int genrePos = intent.getIntExtra("genPos",0);
        int statPos = intent.getIntExtra("statPos",0);
        String bookId = intent.getStringExtra("bookId");
        Button btnUpdateBook = findViewById(R.id.btnUpdateChanges);

        EditText inpTitle = findViewById(R.id.editBookTitle);
        EditText inpAutor = findViewById(R.id.editBookAuthor);
        EditText inpYear = findViewById(R.id.editBookYear);
        EditText inpIsbn = findViewById(R.id.editBookISBN);
        EditText inpPulisher = findViewById(R.id.editBookPublisher);
        Spinner genre = findViewById(R.id.editBookCategory);
        Spinner status = findViewById(R.id.chooseBookStatus);

        inpTitle.setText(title);
        inpAutor.setText(Author);
        inpYear.setText(year);
        inpIsbn.setText(isbn);
        inpPulisher.setText(publisher);
        genre.setSelection(genrePos);
        status.setSelection(statPos);

        btnUpdateBook.setOnClickListener(new View.OnClickListener() {
            boolean isUpdated;
            @Override
            public void onClick(View view) {
                isUpdated = db.updateRec(inpTitle.getText().toString(),inpAutor.getText().toString(),
                        genre.getSelectedItem().toString(), status.getSelectedItem().toString(),
                        inpYear.getText().toString(),inpIsbn.getText().toString(),
                        inpPulisher.getText().toString(),bookId);

                if(isUpdated)
                    Toast.makeText(UpdatePage.this,
                            Html.fromHtml("<big>record is updated</big>"),
                            Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(UpdatePage.this, "record is not updated",
                            Toast.LENGTH_SHORT).show();

            }
        });
    }
}