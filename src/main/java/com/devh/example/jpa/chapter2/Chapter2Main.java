package com.devh.example.jpa.chapter2;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter2Main {
	
	/**
	 * Persistence 
	 * 	- persistence.xml 설정 정보 조회
	 * 	- EntityManagerFactory 생성
	 * 		- EntityManager 생성
	 */
	
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		// persistence.xml에서 이름이 jpa인 영속성 유닛을 찾아 엔티티 매니저 팩토리 생성
		// 이 때 커넥션 풀까지 생성되므로 엔티티 매니저 팩토리의 생성 비용은 매우 크다.
		// 따라서 엔티티 매니저 팩토리는 애플리케이션 전체에서 딱 한번만 생성하고 공유해서 사용한다.
		// 여려 스레드가 동시에 접근해도 안전 (thread safe)
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		// 엔티티 매니저 생성
		// 내부 커넥션을 유지하면서 데이터베이스와 통신
		// 커넥션과 밀접한 관계가 있으므로 스레드 간에 공유하거나 재사용하면 안됨.
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 획득
		// JPA는 항상 트랜잭션 안에서 데이터를 변경해야함.
		// 그렇지 않으면 예외 발생
		EntityTransaction tx = em.getTransaction();
		
		try {
			// 트랜잭션 시작
			tx.begin();
			// 비즈니스 로직
			logic(em);
			// 트랜잭션 커밋
			tx.commit();
		} catch (Exception e) {
			// 트랜잭션 롤백
			tx.rollback();
		} finally {
			// 엔티티 매니저 종료
			em.close();
		}
		
		// 엔티티 매니저 종료
		// 애플리케이션을 종료할 때 팩토리도 종료
		emf.close();
	}
	
	private static void logic(EntityManager em) {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("h");
		member.setAge(31);
		
		// 등록
		// JPA는 매핑정보를 분석하여 insert문 생성
		em.persist(member);
		
		// 수정
		// JPA는 update문 생성
		// 엔티티를 추적하여 값이 변경되면 쿼리를 실행
		member.setAge(29);
		
		// 한 건 조회
		// find는 조회할 엔티티 타입과 @Id에 대응되는 필드 식별자 값으로 조회
		Member findMember = em.find(Member.class, id);
		System.out.println("findMember="+findMember.getUsername()+", age="+findMember.getAge());
		
		// 목록 조회 (JPQL - 엔티티 대상으로 쿼리)
		List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
		System.out.println("members.size="+members.size());
		
		// 삭제
		// JPA는 delete문 생성
		em.remove(members);
	}
}
