package com.devh.example.jpa.chapter4;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint (
		name = "NAME_AGE_UNIQUE", 
		columnNames = {"NAME", "AGE"}
		)})
public class Member {
	@Id
	// 기본 키 생성을 데이터베이스에 위임
	// PostgreSQL, SQL Server, DB2
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME", nullable = false, length = 10)
	private String username;
	private Integer age;
	
	// 추가
	// 회원은 일반 회원과 관리자로 구분
	// 가입일과 수정일
	// 회선 설명 필드 (길이제한 X)
	
	// enum 사용시 Enumerated 사용
	@Enumerated(EnumType.STRING)
	private RoleType roleType;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastModifiedDate;
	// CLOB, BLOB 타입 매핑
	@Lob
	private String description;
	
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
	public RoleType getRoleType() {
		return roleType;
	}
	public void setRoleType(RoleType roleType) {
		this.roleType = roleType;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
