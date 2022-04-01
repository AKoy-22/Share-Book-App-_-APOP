package com.example.apopsharebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class ReadingHistoryListAdapter extends ArrayAdapter {
	Context context;
	int resource;
	List<Books> list;

	public ReadingHistoryListAdapter(Context context, int resource, List<Books> list) {
		super(context, resource, list);
		this.context = context;
		this.resource = resource;
		this.list = list;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(resource,null);

		ImageView cover = view.findViewById(R.id.imgBookCover);
		TextView title = view.findViewById(R.id.txtBookTitle);
		TextView author = view.findViewById(R.id.txtBookAuthor);
		TextView timeSpent = view.findViewById(R.id.txtTimeSpent);
		TextView isbn = view.findViewById(R.id.txtBookIsbn);

		Books bookList = list.get(position);

		cover.setImageResource(R.drawable.book_cover_2);
		title.setText(bookList.getTitle());
		author.setText(bookList.getAuthor());
		isbn.setText(bookList.getIsbn());
		timeSpent.setText(Double.toString(bookList.getTime()));

		return view;
	}
}
