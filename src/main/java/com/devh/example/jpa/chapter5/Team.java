package com.devh.example.jpa.chapter5;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

//@Entity
public class Team {
	@Id
	@Column(name = "TEAM_ID")
	private String id;
	private String name;

	public Team() {}

	public Team(String id, String name) {
		this.id = id;
		this.name = name;
	}

	// mappedBy는 양방향 매핑일 때, 반대쪽 매핑의 필드 이름을 값으로 줌
	// 반대쪽 매핑이 Member.team이므로
	// 데이터베이스는 외래키 하나로 양쪽이 서로 조인이 가능
	// 객체는 서로 다른 단방향 연관관계 2개를 로직으로 잘 묶어 양방향으로 보이게 함
	// 어떤 연관관계를 주인으로 정할지는 mappedBy 속성을 사용하면 된다.
	// 주인은 외래키 관리자를 뜻한다.
	// 주인만이 데이터베이스 연관관계와 매핑되고, 외래키를 관리(뜽록/수정/삭제)할 수 있다.
	// 반면 주인이 아닌 쪽은 읽기만 가능하다.
	// 주인은 mappedBy 속성을 사용하지 않는다.
	// 주인이 아니면 mappedBy 속성을 사용해서 속성의 값으로 연관관계의 주인을 지정해야 한다.
	// Member 엔티티의 team 필드가 주인임을 명시
	// 주인은 테이블에 외래키가 있는 곳으로 정해야 한다.
	// 데이터베이스 테이블의 다대일, 일대다 관계에서는
	// 항상 '다'쪽이 외래키를 가진다.
	// 즉, '다'쪽이 항상 주인이 된다 (@ManyToOne)
	// 따라서, @ManyToOne 에는 mappedBy 속성이 없다.
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<Member> getMembers() {
		return members;
	}

	public void setMembers(List<Member> members) {
		this.members = members;
	}
}
