package com.example.apopsharebook;

public class ReadingHistoryList {
	int cover;
	String title, author, publisher, year, category, owner, date;

	public ReadingHistoryList(int co, String t, String a, String p, String y, String ca, String o, String d) {
		this.cover = co;
		this.title = t;
		this.author = a;
		this.publisher = p;
		this.year = y;
		this.category = ca;
		this.owner = o;
		this.date = d;
	}

	public int getCover() {
		return cover;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getPublisher() {
		return publisher;
	}

	public String getYear() {
		return year;
	}

	public String getCategory() {
		return category;
	}

	public String getOwner() {
		return owner;
	}

	public String getDate() {
		return date;
	}
}
