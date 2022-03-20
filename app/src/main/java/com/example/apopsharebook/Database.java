package com.example.apopsharebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper  {
    SQLiteDatabase sqLiteDatabase;
    final static String DATABASE_NAME="APOP.db";
    final static int DATABASE_VERSION=6;
    //USER TABLE
    final static String U_TABLE="User_table";
    final static String U_UserId="UserId"; //PK - email
    final static String U_Pw="Password";
    final static String U_FName="FName";
    final static String U_LName="LName";
    final static String U_Address="Address";
    final static String U_UserType="UserType"; //User or Admin

    //BOOK TABLE
    final static String B_TABLE="Book_table";
    final static String B_BookId="BookId"; //PK autoincrement
    final static String B_ISBN="ISBN";  //integer
    final static String B_Title="Title";
    final static String B_Genre="Genre";
    final static String B_Author="Author";
    final static String B_Publisher="Publisher";
    final static String B_PubYear="PubYear";
    final static String B_Location="Location";
    final static String B_OwnerId="OwnerId"; //one of UserIds FK
    final static String B_Status="Status"; //on Loan or Available

    //LOAN TABLE
    final static String L_TABLE="Loan_table";
    final static String L_LoanId="LoanId"; //PK auto increment
    final static String L_BookId="BookId"; //FK integer
    final static String L_BorrowerId="BorrowerId"; //one of UserIds FK
    final static String L_StartDate="StartDate";
    final static String L_ReturnDate="ReturnDate";
    final static String L_Price="Price";

    //PREFERENCE TABLE
    final static String P_TABLE="Preference_table";
    final static String P_UserId="UserId";
    final static String P_Preference="Preference";

    //READING HISTORY TABLE
    final static String R_TABLE="RHistory_table";
    final static String R_UserId="UserId";
    final static String R_BookId="BookId";
    final static String R_Note="Note";

    //MESSAGE TABLE
    final static String M_TABLE="Message_table";
    final static String M_MessageId="MessageId"; //PK integer autoincrement
    final static String M_SenderId="SenderId"; //One of userId FK
    final static String M_ReceiverId="ReceiverId"; //One of userId FK
    final static String M_BookId="BookId"; //FK integer
    final static String M_MsgDate="MsgDate";



    public Database(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db=this.getWritableDatabase();
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
        if (!db.isReadOnly()) {
            // Enable foreign key constraints
            db.execSQL("PRAGMA foreign_keys=ON");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //create User Table
       String createTableQuery=" CREATE TABLE "+U_TABLE+"("+ U_UserId+ " text PRIMARY KEY,"+ U_Pw+" text,"+U_FName+" text,"
                +U_LName+" text,"+U_Address+" text,"+U_UserType+" text)";
        sqLiteDatabase.execSQL(createTableQuery);
        //create Book Table
        createTableQuery=" CREATE TABLE "+B_TABLE+ "("+ B_BookId+ " integer,"+ B_ISBN+" integer,"+B_Title+" text,"
                +B_Genre+" text,"+B_Author+" text,"+B_Publisher+" text,"+B_PubYear+" integer,"+B_OwnerId+" text,"+B_Status+" text,"+B_Location+" text,"+
                "PRIMARY KEY ("+ B_BookId+ "), FOREIGN KEY ("+B_OwnerId+") REFERENCES "+U_TABLE+" ("+U_UserId+"))";
        sqLiteDatabase.execSQL(createTableQuery);
        //create Loan Table
        createTableQuery=" CREATE TABLE "+L_TABLE+ "("+ L_LoanId+ " integer,"+ L_BookId+" integer,"+L_BorrowerId+" text,"
                +L_StartDate+" text,"+L_ReturnDate+" text,"+L_Price+" text,"+
                "PRIMARY KEY ("+ L_LoanId+ "), FOREIGN KEY ("+L_BookId+") REFERENCES "+B_TABLE+" ("+B_BookId+"),  " +
                "FOREIGN KEY ("+L_BorrowerId+") REFERENCES "+U_TABLE+" ("+U_UserId+"))";
        sqLiteDatabase.execSQL(createTableQuery);
        //create Preference Table
        createTableQuery=" CREATE TABLE "+P_TABLE+ "("+ P_UserId+ " text,"+ P_Preference+" text,"+
                "PRIMARY KEY ("+ P_UserId+ ","+P_Preference+"), FOREIGN KEY ("+P_UserId+") REFERENCES "+U_TABLE+" ("+U_UserId+"))";
        sqLiteDatabase.execSQL(createTableQuery);
        //create Reading History Table
        createTableQuery=" CREATE TABLE "+R_TABLE+ "("+ R_UserId+ " text,"+ R_BookId+" integer,"+ R_Note+" text,"+
                "PRIMARY KEY ("+ R_UserId+ ","+R_BookId+"), FOREIGN KEY ("+R_UserId+") REFERENCES "+U_TABLE+" ("+U_UserId+"))";
        sqLiteDatabase.execSQL(createTableQuery);
        //create Message Table
        createTableQuery=" CREATE TABLE "+M_TABLE+ "("+ M_MessageId+ " integer,"+ M_SenderId+" integer,"+ M_ReceiverId+" text,"+M_BookId+" integer,"+ M_MsgDate+" text,"+
                "PRIMARY KEY ("+M_MessageId+"), FOREIGN KEY ("+M_SenderId+") REFERENCES "+U_TABLE+" ("+U_UserId+"), " +
                "FOREIGN KEY ("+M_ReceiverId+") REFERENCES "+U_TABLE+" ("+U_UserId+"))";
        sqLiteDatabase.execSQL(createTableQuery);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+U_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+B_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+L_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+P_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+R_TABLE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+M_TABLE);
        onCreate(sqLiteDatabase);

    }
    public boolean addUser(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(U_UserId,"prabh@xzy.com");
        value.put(U_Address,"2212 22nd Street, Surrey");
        value.put(U_Pw,"abcd");
        value.put(U_FName,"Prabhjit");
        value.put(U_LName,"Singh");
        value.put(U_UserType,"user");

        long r = sqLiteDatabase.insert(U_TABLE,null,value);
        if(r>0)
            return true;
        else
            return false;
    }

    public boolean addBook(int isbn,String title,String genre,String Author, String Publisher, int PubYear,String OwnerId
    ,String status, String location){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(B_ISBN,isbn);
        value.put(B_Title,title);
        value.put(B_Genre,genre);
        value.put(B_Author,Author);
        value.put(B_Publisher, Publisher);
        value.put(B_PubYear,PubYear);
        value.put(B_OwnerId,OwnerId);
        value.put(B_Status,status);
        value.put(B_Location,location);

        long r = sqLiteDatabase.insert(B_TABLE,null,value);
        if(r>0)
            return true;
        else
            return false;
    }

    //searching book by pickup location - Borrow Book Activity -- NOT WORKING
    public Cursor searchBookByLocation(String loc){
        SQLiteDatabase sqdb=this.getWritableDatabase();
        //sqLiteDatabase=this.getWritableDatabase();
        String query="SELECT Title, Author, Genre FROM "+B_TABLE+" WHERE Location='"+loc+"'";
        Cursor c=sqdb.rawQuery(query,null);
        return c;
    }
    //for testing purpose
    public void manuallyAddBook(){
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(B_ISBN,987654321);
        value.put(B_Title,"The Ride of a Lifetime");
        value.put(B_Genre,"Business");
        value.put(B_Author,"Robert Iger");
        value.put(B_Publisher,  "Random House");
        value.put(B_PubYear,2019);
        value.put(B_OwnerId,"prabh@xzy.com");
        value.put(B_Status,"available");
        value.put(B_Location,"Burnaby");

        long r = sqLiteDatabase.insert(B_TABLE,null,value);
    }

}
