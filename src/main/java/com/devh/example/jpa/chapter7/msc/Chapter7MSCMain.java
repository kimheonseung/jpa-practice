package com.devh.example.jpa.chapter7.msc;

public class Chapter7MSCMain {
	public static void main(String[] args) {
		/**
		 * 상속관계 매핑 전략 - @MappedSuperClass
		 * 	- 부모 클래스는 테이블과 매핑하지 않고, 부모 클래스를 상속받는 자식 클래스에게 매핑정보만 제공
		 * 	- 단순히 매핑정보를 상속할 목적으로만 사용
		 * 장점
		 * 	- 서브타입을 구분해야할 때 효과적
		 * 	- not null 제약조건 사용가능
		 * 단점
		 * 	- 여러 자식테이블을 함께 조회할 때 성능이 느림 (UNION SQL 사용해야함)
		 * 	- 자식테이블을 통합해서 쿼리하기 어려움
		 * 특징
		 * 	- 테이블과 매핑되지 않고 자식클래스에 엔티티 매핑정보를 상속하기 위해 사용
		 * 	- MappedSuperClass로 지정된 클래스는 엔티티가 아니므로 em.find나 JPQL에서 사용 불가
		 * 	- 해당 클래스는 직접 사용할 일이 거의 없으므로 추상 클래스로 만드는것을 권장
		 * 
		 * @Entity는 @Entity이거나 @MappedSuperClass 만 상속받을 수 있다
		 */
	}
}
