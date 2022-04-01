package com.example.apopsharebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter{
 //Class level variables
    private LayoutInflater layoutInflator;
    private int image;

    ArrayList<String> titlesList;
    ArrayList<String> authorsList;
    ArrayList<String> genreList;

    private ItemClickListener iClickListener; //interface object

  //Constructor
    public RecyclerAdapter(Context context,int img, ArrayList<String> titles, ArrayList<String> authors, ArrayList<String> genre){
        layoutInflator= LayoutInflater.from(context);
        image=img;
        titlesList=new ArrayList<String>();
        titlesList=titles;
        authorsList=new ArrayList<String>();
        authorsList=authors;
        genreList=new ArrayList<String>();
        genreList=genre;


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflator.inflate(R.layout.recyclerview,parent,false);
        VH vh=new VH(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((VH)holder).imgView.setImageResource(R.drawable.book_cover_1);
        TextView title=holder.itemView.findViewById(R.id.bTitle);
        TextView author=holder.itemView.findViewById(R.id.bAuthor);
        TextView genre=holder.itemView.findViewById(R.id.bGenre);
        title.setText(titlesList.get(position));
        author.setText(authorsList.get(position));
        genre.setText(genreList.get(position));
        /*title.setText(titleArr[position]);
        author.setText(authorArr[position]);
        genre.setText(genreArr[position]);*/
    }

    @Override
    public int getItemCount() {
        return titlesList.size();
    }
    //setClickListener
    public void setClickListener(ItemClickListener iClickListener){
        this.iClickListener=iClickListener;
    }
    //getItem??

    //inner class to make image clickable
    public class VH extends RecyclerView.ViewHolder implements View

            .OnClickListener{
        ImageView imgView;
        public VH(@NonNull View itemView) {
            super(itemView);
            imgView=itemView.findViewById(R.id.imgView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(iClickListener!=null){
                iClickListener.onItemClick(view,getAdapterPosition());
            }
        }
    }
    //inner interfacae
    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }
}
