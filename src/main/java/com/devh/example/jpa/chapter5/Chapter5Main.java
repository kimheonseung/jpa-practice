package com.devh.example.jpa.chapter5;

import java.util.List;

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
			deleteRelationEntity(em);
			
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
	
	private static void testFind(EntityManager em) {
		/*
		 * 연관관계가 있는 엔티티를 조회하는 방법
		 * 	- 1. 객체 그래프 탐색
		 * 	- 2. 객체지향 쿼리 사용 (JPQL)
		 */
		
		Member member1 = em.find(Member.class, "member1");
		Team team = member1.getTeam();
		System.out.println("teamId: " + team.getId() + ", teamName: " + team.getName());
	}
	
	private static void testJPQL(EntityManager em) {
		String jpql = "select m from Member m join m.team t where t.name=:teamName";
		List<Member> resultList = em.createQuery(jpql, Member.class)
				.setParameter("teamName", "팀1")
				.getResultList();
		
		for(Member m : resultList) {
			System.out.println("[query] member.username: " + m.getUsername());
		}
	}
	
	private static void updateRelation(EntityManager em) {
		// 새로운 팀2
		Team team2 = new Team("team2", "팀2");
		em.persist(team2);
		
		// 회원1에 새로운 팀2 설정
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(team2);
	}
	
	private static void deleteRelation(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		member1.setTeam(null);
	}
	
	private static void deleteRelationEntity(EntityManager em) {
		Member member1 = em.find(Member.class, "member1");
		Member member2 = em.find(Member.class, "member2");
		member1.setTeam(null);
		member2.setTeam(null);
		Team team = em.find(Team.class, "team1");
		em.remove(team);
	}
}
