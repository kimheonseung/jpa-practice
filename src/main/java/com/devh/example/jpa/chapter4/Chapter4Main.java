package com.devh.example.jpa.chapter4;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Chapter4Main {
	public static void main(String[] args) {
		System.out.println("Chapter4");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		
		emf.close();
	}
}
