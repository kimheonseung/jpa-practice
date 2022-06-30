package com.devh.example.jpa.chapter7.msc;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {
	/*
	 * id, name 두 공통 속성을 부모로 모음
	 * 객체들이 주로 사용하는 매핑정보를 정의
	 * 부모로부터 물려받은 매핑정보를 재정의하려면 @AttributeOverrides나 @AttributeOverride를 사용하고,
	 * 연관관계를 재정의 하려면 @AssociationOverrides나 @AssociationOverride를 사용
	 */
	@Id @GeneratedValue
	private Long id;
	private String name;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
