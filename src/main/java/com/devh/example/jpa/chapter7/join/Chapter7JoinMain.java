package com.devh.example.jpa.chapter7.join;

public class Chapter7JoinMain {
	public static void main(String[] args) {
		/**
		 * 상속관계 매핑 전략 - Joined Strategy
		 * 	- 슈퍼타입-서브타입을 각각 모두 테이블로 만들고, 조회할 때 조인을 사용
		 * 	- 자식테이블이 부모테이블의 기본키를 받아 기본키 + 외래키로 사용하는 전략
		 * 	- 타입을 구분하는 컬럼을 추가해야함
		 * 장점
		 * 	- 테이블이 정규화됨
		 * 	- 외래키 참조 무결성 제약조건을 활용
		 * 	- 저장공간의 효율적 사용
		 * 단점
		 * 	- 조회시 조인이 많이 사용되어 성능 저하
		 * 	- 조회 쿼리가 복잡함
		 * 	- 데이터 등록시 INSERT SQL 두번 실행
		 * 특징
		 * 	- JPA 표준 명세는 구분 컬럼 (DiscriminatorColumn) 을 사용하도록 하지만, 하이버네이트를 포함한 몇 구현체는 구분컬럼 없이도 작동함 
		 */
	}
}
