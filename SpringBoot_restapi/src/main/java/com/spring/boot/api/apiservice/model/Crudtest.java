package com.spring.boot.api.apiservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "CRUDTEST")
public class Crudtest {
	
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name = "userid")
	private String userid;
	@Column(name = "username")
	private String username;
	
	public Crudtest() {
	}
	
	@Builder
	public Crudtest(String username, String userid) {
		this.userid = userid;
		this.username = username;
	}
}
