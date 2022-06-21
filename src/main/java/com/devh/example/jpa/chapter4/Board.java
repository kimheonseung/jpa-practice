package com.devh.example.jpa.chapter4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

/*
 * CREATE TABLE BOARD (
 * 	 ID BIGINT NOT NULL PRIMARY KEY,
 *   DATA VARCHAR(255)
 * );
 * CREATE SEQUENCE BOARD_SEQ (sequenceName) START WITH 1 (initialValue) INCREMENT BY 1 (allocationValue); 
 */
@Entity
@SequenceGenerator (
		name = "BOARD_SEQ_GENERATOR",
		sequenceName = "BOARD_SEQ", // 매핑할 데이터베이스 시퀀스 이름
		initialValue = 1, allocationSize = 1
)
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BOARD_SEQ_GENERATOR")
	private Long id;
	private String data;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
}
