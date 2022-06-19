package com.devh.example.jpa.chapter3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.devh.example.jpa.chapter2.Member;

public class Chapter3Merge {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
	
	/**
	 * 준영속
	 * 영속성 컨텍스트에서 분리된 상태
	 * 영속성 컨텍스트가 제공하는 기능을 사용할 수 없다.
	 * 다음 세 가지 방법으로 영속상태의 엔티티를 준영속상태로 만들 수 있다.
	 * 	- em.detach(entity); 
	 * 		특정 엔티티만 준영속 상태로 전환
	 * 		1차 캐시부터 쓰기 지연 SQL 저장소까지 모든 정보가 제거
	 * 	- em.clear()
	 * 		영속 컨텍스트를 완전히 초기화
	 * 		모든 엔티티를 준영속 상태로 만든다.
	 * 	- em.close()
	 * 		영속 컨텍스트를 종료
	 * 		모든 엔티티를 준영속 상태로 만든다.
	 * 
	 * 특징
	 * 	- 거의 비영속 상태
	 * 	- 식별값을 갖음 (이미 한번 영속 상태였으므로)
	 * 	- 지연로딩 불가
	 */
	
	/**
	 * 병합 merge()
	 * 준영속 상태의 엔티티를 다시 영속상태로 변경하려면 병합을 사용
	 * merge()는 준영속 상태의 엔티티를 받아 새로운 영속상태의 엔티티를 반환
	 * Member mergeMember = em.merge(member);
	 */
	
	public static void main(String[] args) {
		Member member = createMember("idA", "mA");
		member.setUsername("변경");
		mergeMember(member);
	}
	
	static Member createMember(String id, String username) {
		// 영속성 컨텍스트1 시작
		EntityManager em1 = emf.createEntityManager();
		EntityTransaction tx1 = em1.getTransaction();
		
		tx1.begin();
		
		Member member = new Member();
		member.setId(id);
		member.setUsername(username);
		member.setAge(1);
		
		em1.persist(member);
		tx1.commit();
		
		// 영속성 컨텍스트1 종료, member는 준영속상태
		em1.close();
		
		return member;
	}
	
	static void mergeMember(Member member) {
		// 영속성 컨텍스트2 시작
		EntityManager em2 = emf.createEntityManager();
		EntityTransaction tx2 = em2.getTransaction();
		
		tx2.begin();
		Member mergeMember = em2.merge(member);
		tx2.commit();
		
		// 준영속
		System.out.println("member="+member.getUsername());
		System.out.println("contains: "+em2.contains(member));
		
		// 영속
		System.out.println("mergeMember="+mergeMember.getUsername());
		System.out.println("contains: "+em2.contains(mergeMember));
		
		// 영속성2 컨텍스트 종료
		em2.close();
	}
}
