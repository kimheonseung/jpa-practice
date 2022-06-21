package com.devh.example.jpa.chapter4;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/*
 * create table MY_SEQUENCE ( 
 *   sequence_name varchar(255) not null,
 *   next_val bigint,
 *   primary_key ( sequence_name )
 * );
 */
@Entity
// 키 생성 전용 테이블을 하나 만들고, 이름과 값으로 사용할 컬럼을 만들어 시퀀스를 흉내내는 전략
// 값이 없으면 알아서 insert 초기화를 해줌
// SEQUENCE 전략과 비교하여 데이터베이스와 한번 더 통신 (SELECT 후 다음값 증가)
// sequence_name (pkColumnName)     |  next_val (valueColumnName)
//   BOARD_SEQ    (pkColumnValue)   |    2      (initialValue)
//   MEMBER_SEQ                     |   10
//   PRODUCT_SEQ                    |   50
//     ...
@TableGenerator(
		name = "BOARD_SEQ_GENERATOR",
		table = "MY_SEQUENCES",
		pkColumnValue = "BOARD_SEQ", allocationSize = 1
)
public class BoardA {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BOARD_SEQ_GENERATOR")
	// AUTO 전략
	// 	- 방언에 따라 IDENTITY, SEQUENCE, TABLE 전략중 하나를 자동으로 선택
	// 	- ORACLE은 SEQUENCE, MySQL은 IDENTITY
	// 	- 데이터베이스가 변경되어도 코드를 수정할 필요가 없어 개발 초기나 프로토타입에 편리하게 개발 그낭
	// 	- AUTO를 사용할 때 SEQUENCE나 TABLE 전략이 선택되면 시퀀스나 키 생성용 테이블을 하이버네이트가 알아서 생성해줌
	// @GeneratedValue(strategy = GenerationType.AUTO)
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
