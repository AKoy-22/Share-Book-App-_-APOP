package com.example.apopsharebook;

public class RequestHistoryList {
	String title, status, user;

	public RequestHistoryList(String t, String s, String u){
		this.title = t;
		this.status = s;
		this.user = u;
	}

	public String getTitle() {
		return title;
	}

	public String getStatus() {
		return status;
	}

	public String getUser() {
		return user;
	}
}
