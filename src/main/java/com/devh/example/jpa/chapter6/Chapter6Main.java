package com.devh.example.jpa.chapter6;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter6Main {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			testSave(em);
			testFind(em);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
	
	private static void testSave(EntityManager em) {
		Member member1 = new Member();
		member1.setId("member1");
		member1.setUsername("회원1");
		em.persist(member1);
		
		Product productA = new Product();
		productA.setId("productA");
		productA.setName("상품1");
		em.persist(productA);
		
		Orders order = new Orders();
		order.setMember(member1);
		order.setProduct(productA);
		order.setOrderAmount(2);
		em.persist(order);
	}
	
	private static void testFind(EntityManager em) {
		Long orderId = 1L;
		Orders order = em.find(Orders.class, orderId);
		
		Member member = order.getMember();
		Product product = order.getProduct();
		
		System.out.println("member: " + member.getUsername());
		System.out.println("product: " + product.getName());
		System.out.println("orderAmount: " + order.getOrderAmount());
	}
}
