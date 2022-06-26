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
		EntityManager em1 = emf.createEntityManager();
		try {
			EntityTransaction tx = em1.getTransaction();
			tx.begin();
			
			testSave(em1);
			biDirection(em1);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em1.close();
		}

		EntityManager em2  = emf.createEntityManager();
		try {
			EntityTransaction tx = em2.getTransaction();
			tx = em2.getTransaction();
			tx.begin();

			biDirection(em2);

			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em2.close();
		}

		emf.close();
	}
	
	private static void testSave(EntityManager em) {
		// 팀1 저장
		Team team1 = new Team("team1", "팀1");
		em.persist(team1);

		// 양방향 연관관계는 연관관계의 주인이 외래키를 관리한다.
		// 따라서 주인이 아닌 방향은 값을 설정하지 않아도 데이터베이스 외래키 값이 정상 입력된다.
		// 그러나, 양쪽 모두 값을 입력해주는 것이 가장 안전하다. (JPA 사용하지 않는 순수 객체 상태를 고려)

		// 회원1 저장
		Member member1 = new Member("member1", "회원1");
		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		// team1.getMembers().add(member1); // 무시 (주인이 아님)
		em.persist(member1);
		
		// 회원2 저장
		Member member2 = new Member("member2", "회원2");
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		// team2.getMembers().add(member2); // 무시 (주인이 아님)
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

	private static void biDirection(EntityManager em) {
		Team team = em.find(Team.class, "team1");
		List<Member> members = team.getMembers();
		for(Member member : members) {
			System.out.println("member.username: "+member.getUsername());
		}
	}

	private static void test순수한객체_양방향(EntityManager em) {
		// 팀1
		Team team1 = new Team("team1", "팀1");
		Member member1 = new Member("member1", "회원1");
		Member member2 = new Member("member2", "회원2");

		member1.setTeam(team1); // 연관관계 설정 member1 -> team1
		// team1.getMembers().add(member1); // Member의 편의메소드 setTeam에 통합
		em.persist(member1);
		member2.setTeam(team1); // 연관관계 설정 member2 -> team1
		// team1.getMembers().add(member2); // Member의 편의메소드 setTeam에 통합
		em.persist(member2);

		List<Member> members = team1.getMembers();
		System.out.println("members.size = "+members.size());
	}
	// 단방향 매핑만으로 테이블과 객체의 연관관계 매핑은 완료됨
	// 단방향을 양방향으로 만들면 반대방향으로 객체 그래프 탐색이 가능해짐
	// 양방향 연관관계를 매핑하려면 객체에서 양쪽 방향을 모두 관리해야함

	// 연관관계의 주인을 정하는 기준
	// 	- 단방향은 외래키가 있는 곳을 기준으로 매핑
	// 	- 양방향은 '주인' 이라는 명칭이 오해가 있을 수 있음
	// 		비즈니스 로직상 더 중요하다고 해서 연관관계의 주인으로 선택하면 안됨
	// 		비즈니스 로직 중요도를 배제하고 단순히 외래키 관리자 정도의 의미만 부여
}
