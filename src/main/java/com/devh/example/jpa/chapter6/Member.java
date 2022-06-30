package com.devh.example.jpa.chapter6;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

//@Entity
public class Member {
	@Id @Column(name = "MEMBER_ID")
	private String id;
	private String username;
	
	@OneToMany(mappedBy = "member")
	private List<Orders> orders = new ArrayList<Orders>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	
}
