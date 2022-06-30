package com.devh.example.jpa.chapter7.join;

import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

//@Entity
// 상속매핑은 부모 클래스에 @Inheritance를 사용해야함
// 현재는 Join 전략을 사용
@Inheritance(strategy = InheritanceType.JOINED)
// 자식을 구분할 컬럼 지정 name을 지정하지 않으면 기본으로 DTYPE으로 만들어줌
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
	@Id @GeneratedValue
	@Column(name = "ITEM_ID")
	private Long id;
	
	private String name;
	private int price;
	
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
}
