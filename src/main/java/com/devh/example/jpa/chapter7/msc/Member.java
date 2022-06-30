package com.devh.example.jpa.chapter7.msc;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
// 부모에게 상속받은 id 속성의 컬럼명을 MEMBER_ID로 재정의
//@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID"))
// 둘 이상을 재정의하려면
@AttributeOverrides({
	@AttributeOverride(name = "id", column = @Column(name = "MEMBER_ID")),
	@AttributeOverride(name = "name", column = @Column(name = "MEMBER_NAME"))
})
public class Member extends BaseEntity {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
