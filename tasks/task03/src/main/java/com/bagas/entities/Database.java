package com.bagas.entities;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class Database {

	private List<User> users;
	
	private List<Credit> credits;
	
	private List<Discount> discounts;
	
	private List<Event> events;
	
	private List<Transaction> transactions;

	public Database() {
		users = new ArrayList<User>();
		credits = new ArrayList<Credit>();
		discounts = new ArrayList<Discount>();
		events = new ArrayList<Event>();
		transactions = new ArrayList<Transaction>();
	}
}
