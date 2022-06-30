package com.devh.example.jpa.chapter5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//@Entity
public class Member {
	@Id
	@Column(name = "MEMBER_ID")
	private String id;
	private String username;
	
	public Member(String id, String username) {
		this.id = id;
		this.username = username;
	}
	
	// ManyToOne
	// 	- optional: false로 두면 연관된 엔티티가 항상 있어야 함(기본 true)
	// 	- fetch, cascade ..
	@ManyToOne // 다대일
	// JoinColumn
	// 	- name: 매핑할 외래 키 이름(기본 - 필드명_참조테이블의기본키컬럼명)
	// 	- referencedColumnName: 외래키가 참조하는 대상 테이블의 컬럼명(기본 - 참조하는 테이블의 기본 키 컬럼명)
	// JoinColumn 생략하는 경우
	// 	- team_TEAM_ID 외래키를 사용하게 됨
	@JoinColumn(name = "TEAM_ID") // 외래키 매핑. 회원과 팀 테이블은 TEAM_ID로 연관관계를 맺음 
	private Team team;

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

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
