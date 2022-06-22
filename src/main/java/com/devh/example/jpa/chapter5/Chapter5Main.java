package com.devh.example.jpa.chapter5;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter5Main {
	public static void main(String[] args) {
//		Member member1 = new Member("member1", "회원1");
//		Member member2 = new Member("member2", "회원2");
//		Team team1 = new Team("team1", "팀1");
//		member1.setTeam(team1);
//		member2.setTeam(team1);
//		Team findTeam = member1.getTeam();
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			testSave(em);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
		
	}
	
	private static void testSave(EntityManager em) {
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);
		
		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		em.persist(member2);
	}
}
