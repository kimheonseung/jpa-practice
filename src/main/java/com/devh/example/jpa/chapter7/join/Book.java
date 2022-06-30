package com.devh.example.jpa.chapter7.join;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

//@Entity
@DiscriminatorValue("B")
// 기본적으로 자식은 부모테이블의 ID 컬럼명을 그대로 사용하는데,
// 만약 자식 테이블의 기본키 컬럼명을 변경하고 싶으면 @PrimaryKeyJoinColumn을 사용
@PrimaryKeyJoinColumn(name = "BOOK_ID")
public class Book extends Item {
	private String author;
	private String isbn;
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
}
