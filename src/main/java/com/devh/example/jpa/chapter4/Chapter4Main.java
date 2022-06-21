package com.devh.example.jpa.chapter4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Chapter4Main {
	public static void main(String[] args) {
		System.out.println("Chapter4");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		EntityManager em = emf.createEntityManager();
		try {
			EntityTransaction tx = em.getTransaction();
			tx.begin();
			
			logic(em);
			
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
		}
		emf.close();
	}
	
	private static void logic(EntityManager em) {
		Board board = new Board();
		em.persist(board);
		System.out.println("board.id: " + board.getId());
	}
	
	/*
	 * 정리
	 * 	- 영속성 컨텍스트는 엔티티를 식별자 값으로 구분하므로 식별자 값이 반드시 필요하다.
	 * 	- persist 호출 후 식별자 할당 전략은 다음과 같다.
	 * 		- 직접 할당
	 * 			persist 호출 전 애플리케이션에서 직접 식별자 값을 할당. 값이 없으면 예외 발생
	 * 		- SEQUENCE
	 * 			데이터베이스 시퀀스에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장
	 * 		- TABLE
	 * 			데이터베이스 시퀀스 생성용 테이블에서 식별자 값을 획득한 후 영속성 컨텍스트에 저장
	 * 		- IDENTITY
	 * 			데이터베이스에 엔티티를 저장해서 식별자 값을 획득한 후 영속성 컨텍스트에 저장 (저장을 해야만 식별자를 얻을 수 있음)
	 */
}
