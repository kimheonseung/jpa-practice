package com.devh.example.jpa.chapter7.single;

public class Chapter7SingleMain {
	public static void main(String[] args) {
		/**
		 * 상속관계 매핑 전략 - Single-Table Strategy
		 * 	- 테이블을 하나만 사용
		 * 	- 구분컬럼을 통해 자식데이터 구분
		 * 	- 조회시 조인을 사용하지 않아 가장 빠름
		 * 장점
		 * 	- 조인이 필요없어 조회성능이 빠름
		 * 	- 조회 쿼리가 단순
		 * 단점
		 * 	- 자식 엔티티가 매핑한 컬럼은 모두 nullable
		 * 	- 단일 테이블에 모든것을 저장하므로 테이블이 커질 수 있음 - 상황에 따라 조회 성능이 오히려 느려질 수 있음
		 * 특징
		 * 	- 구분컬럼을 반드시 사용해야함
		 * 	- DiscriminatorValue를 지정하지 않으면 기본 엔티티명이 사용됨
		 */
	}
}
