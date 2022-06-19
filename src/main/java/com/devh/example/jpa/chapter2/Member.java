package com.devh.example.jpa.chapter2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

// 해당 클래스는 테이블과 매핑됨을 명시
@Entity
// 매핑할 테이블 정보를 명시
@Table(name = "MEMBER")
public class Member {
	
	// 테이블의 기본키를 명시 (식별자 필드)
	@Id
	// 컬럼 매핑 명시
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME")
	private String username;
	// 매핑  어노테이션이 없더라도 해당 필드명으로 매핑 (대소문자 구분이 없어야 작동)
	private Integer age;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

}
