package com.devh.example.jpa.chapter7.tpc;

public class Chapter7TPCMain {
	public static void main(String[] args) {
		/**
		 * 상속관계 매핑 전략 - Table-Per-Concrete-Class Strategy
		 * 	- 자식 엔티티마다 테이블을 만듦
		 * 	- 각 자식 테이블에 필요한 컬럼을 모두 생성
		 * 	- 일반적으로 추천하지 않는 방법임
		 * 장점
		 * 	- 서브타입을 구분해야할 때 효과적
		 * 	- not null 제약조건 사용가능
		 * 단점
		 * 	- 여러 자식테이블을 함께 조회할 때 성능이 느림 (UNION SQL 사용해야함)
		 * 	- 자식테이블을 통합해서 쿼리하기 어려움
		 * 특징
		 * 	- 구분컬럼을 사용하지 않음
		 */
	}
}
