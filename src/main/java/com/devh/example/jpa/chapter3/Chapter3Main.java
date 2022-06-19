package com.devh.example.jpa.chapter3;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.devh.example.jpa.chapter2.Member;

public class Chapter3Main {

	/*
	 * flush()
	 * 	- 영속성 컨텍스트의 변경 내용을 데이터베이스에 반영한다.
	 * 	- 1. 변경감지가 동작해서 영속성 컨텍스트에 있는 모든 엔티티를 스냅샷과 비교해서 수정된 엔티티를 찾고, 쓰기지연 SQL 저장소에 등록
	 * 	- 2. 쓰기지연 SQL 저장소의 쿼리를 DB에 전송
	 * 	- 플러시는 다음 세가지에 의해 실행된다.
	 * 		- em.flush(); (직접 호출)
	 * 			- 강제로 실행
	 * 			- 테스트나 다른 프레임워크와 JPA를 함께 사용하는 경우를 제외하곤 거의 사용하지 않음
	 * 		- 트랜잭션 커밋
	 * 			- flush로 쓰기지연 SQL을 모두 전송한 뒤 DB 트랜잭션 커밋
	 * 		- JPQL 쿼리 실행
	 * 			- persist를 통해 영속상태로 만든 엔티티들이 있는 상황에서 JPQL 실행 시, 데이터베이스에서 엔티티를 조회한다.
	 * 				이 때, 영속상태의 엔티티들의 사항을 먼저 반영해야하므로, flush가 먼저 실행된다.
	 * 
	 * flush 모드 옵션
	 * 	- javax.persistence.FlushModeType
	 * 	- FlushModeType.AUTO
	 * 		커밋이나 쿼리 실행 시 플러시 (기본)
	 * 	- FlushModeType.COMMIT
	 * 		커밋할 때만 플러시
	 * => em.setFlushMode(FlushModeType.COMMIT);
	 */
	
	public static void main(String[] args) {
		// 엔티티 매니저 팩토리 생성
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");
		// 엔티티 매니저 생성
		EntityManager em = emf.createEntityManager();
		// 트랜잭션 획득
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			logicDelete(em);
			// 커밋하는 순간 데이터베이스에 INSERT SQL을 보낸다.
			// 커밋 전까지 내부 쿼리 저장소에 INSERT SQL을 모아둔다.
			// => 트랜잭션 쓰기 지연 (transactional write-behind)
			// commit
			// - flush (변경 내용의 동기화 - SQL 저장소의 쿼리를 데이터베이스로 전송)
			// - 실제 데이터베이스의 트랜잭션 커밋
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}

	}

	private static void logicEquals(EntityManager em) {
		String id = "id1";
		Member member = new Member();
		member.setId(id);
		member.setUsername("h");
		member.setAge(31);
		// -------- 비영속 상태

		// 1차 캐시에 저장됨
		em.persist(member);
		// -------- 영속 상태
		// => 1차 캐시, 동일성 보장, 트랜잭션을 지원하는 쓰기 지연, 변경 감지, 지연 로딩 가능

		// 준영속
		// em.close(), em.clear()를 호출하여도 엔티티는 준영속상태
		// em.detach(member);

		// 영속 엔티티의 동일성 보장
		// 만약 1차 캐시에서 찾지 못하면, 데이터베이스에서 조회하여 1차 캐시에 저장 후 엔티티 반환
		Member a = em.find(Member.class, id);
		Member b = em.find(Member.class, id);
		System.out.println("a == b: " + (a == b));

	}

	private static void logicCreate(EntityManager em) {
		// 엔티티 등록
		Member memberA = new Member();
		memberA.setId("idA");
		memberA.setUsername("a");
		memberA.setAge(10);

		Member memberB = new Member();
		memberB.setId("idB");
		memberB.setUsername("b");
		memberB.setAge(20);

		em.persist(memberA);
		em.persist(memberB);
		// 여기까지 INSERT SQL을 데이터베이스에 보내지 않는다.
	}
	
	private static void logicUpdate(EntityManager em) {
		// 엔티티 수정
		Member memberA = em.find(Member.class, "idA");
		memberA.setUsername("hello");
		memberA.setAge(50);
		
		// em.update(); 이런 코드가 있어야 하지 않을까?
		// 변경감지 (dirty checking)
		// 	- JPA는 엔티티를 영속성 컨텍스트에 보관할 때, 최초 상태를 복사해 저장(스냅샷)
		// 	- 플러시 지점에서 스냅샷과 엔티티를 비교하여 변경된 엔티티를 찾는다. (영속상태인 엔티티만 가능)
		// 즉, 아래 순서가 실행된다.
		// 	- 트랜잭션 커밋 -> 플러시 호출
		// 	- 엔티티와 스냅샷을 비교하여 변경된 엔티티 감지
		// 	- 변경된 엔티티가 있으면 수정 쿼리를 생성하여 쓰기 지연 SQL 저장소에 보관
		// 	- 쓰기 지연 저장소의 SQL을 데이터베이스로 전송
		// 	- 데이터베이스 트랜잭션 커밋
		
		// update는 수정할 컬럼만으로 update문이 생성되지 않는다.
		// 모든 필드를 사용하여 update문을 생성한다.
		// 데이터 전송량이 증가하는 단점이 있지만, 다음과 같은 장점이 존재한다.
		// 	- 수정 쿼리가 항상 같으므로 애플리케이션 로딩 시점에 수정 쿼리를 미리 생성해두고 재사용 할 수 있다.
		// 	- 데이터베이스에 동일한 쿼리를 보내면 데이터베이스는 이전에 한번 파싱된 쿼리를 재사용한다.
		// 	- 수정된 데이터만 사용하여 동적 update문을 작성하려면, @org.hibernate.annotations.DynamicUpdate를 사용한다.
		// 	- 비슷한 기능으로 @DynamicInsert도 있음 (데이터가 존재하는 - null이 아닌 필드만으로 INSERT SQL생성)
	}
	
	private static void logicDelete(EntityManager em) {
		// 삭제 대상 조회
		Member memberA = em.find(Member.class, "idA");
		em.remove(memberA);
		// 삭제된 엔티티는 재사용하지 말고 GC의 대상이 되도록 두는 것이 좋다.
	}
	
}
