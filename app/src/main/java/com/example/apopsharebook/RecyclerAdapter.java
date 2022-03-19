package com.example.apopsharebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class RecyclerAdapter extends RecyclerView.Adapter{
 //Class level variables
    private LayoutInflater layoutInflator;
    private int image;
    private String [] titleArr;
    private String [] authorArr;
    private String [] genreArr;
    private ItemClickListener iClickListener; //interface object

  //Constructor
    public RecyclerAdapter(Context context,int img, String [] titles, String [] authors, String [] genres){
        layoutInflator= LayoutInflater.from(context);
        image=img;
        titleArr=titles;
        authorArr=authors;
        genreArr=genres;
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
        title.setText(titleArr[position]);
        author.setText(authorArr[position]);
        genre.setText(genreArr[position]);
    }

    @Override
    public int getItemCount() {
        return titleArr.length;
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
