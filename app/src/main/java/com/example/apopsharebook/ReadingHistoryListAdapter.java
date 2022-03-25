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
	List<ReadingHistoryList> list;

	public ReadingHistoryListAdapter(Context context, int resource, List<ReadingHistoryList> list) {
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
		TextView publisher = view.findViewById(R.id.txtBookPublisher);
		TextView year = view.findViewById(R.id.txtBookYear);
		TextView category = view.findViewById(R.id.txtBookCategory);
		TextView owner = view.findViewById(R.id.txtBookOwner);
		TextView date = view.findViewById(R.id.txtBorrowBookDate);

		ReadingHistoryList clList = list.get(position);

		cover.setImageDrawable(context.getResources().getDrawable(clList.getCover(),null));
		title.setText(clList.getTitle());
		author.setText(clList.getAuthor());
		publisher.setText(clList.getPublisher());
		year.setText(clList.getYear());
		category.setText(clList.getCategory());
		owner.setText(clList.getOwner());
		date.setText(clList.getDate());

		return view;
	}
}
