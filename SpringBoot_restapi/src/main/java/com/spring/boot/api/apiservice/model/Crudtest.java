package com.spring.boot.api.apiservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "CRUDTEST")
public class Crudtest {
	@Id
	@GeneratedValue
	Long id;
	
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

	@Override
	public String toString() {
		return "Crudtest [id=" + id + ", userid=" + userid + ", username=" + username + ", toString()="
				+ super.toString() + "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
