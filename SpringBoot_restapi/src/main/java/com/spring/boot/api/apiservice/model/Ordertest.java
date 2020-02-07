package com.spring.boot.api.apiservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "ORDERTEST")
public class Ordertest {
	@Id
    @GeneratedValue
    @Column(name = "oid")
    private long id;
	
	@Column(name = "oname")
	private String oname;
	
	// @ManyToOne(targetEntity=Crudtest.class, fetch=FetchType.LAZY) // LAZY 설정때문에 json형변환에 문제 생김
	@ManyToOne
	@JoinColumn(name = "id", nullable = false, updatable = false)
	private Crudtest crudtest;

	public Ordertest() {
	}
	
	@Builder
	public Ordertest(String oname, Crudtest crudtest) {
		this.oname = oname;
		this.crudtest = crudtest;
	}
	
}
