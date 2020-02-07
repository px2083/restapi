package com.spring.boot.api.apiservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.model.QCrudtest;
import com.spring.boot.api.apiservice.repository.CrudtestRepository;

@Service
public class QueryDSLService extends QuerydslRepositorySupport {

	@Autowired
	private CrudtestRepository crudtestRepository;
	
	public QueryDSLService() {
		super(Crudtest.class);
	}
	
	public List<Crudtest> selectByUsername(Crudtest crudtest) {
		QCrudtest qCrudtest = QCrudtest.crudtest;
		
		JPQLQuery<Crudtest> jpqlQuery  = from(qCrudtest);
		jpqlQuery.where(qCrudtest.username.eq(crudtest.getUsername()));
		List<Crudtest> resultList = jpqlQuery.fetch();
		
		return resultList;
	}

	public Optional<Crudtest> selectOneById(Crudtest crudtest) {
		Predicate predicate = QCrudtest.crudtest
				.id.eq(crudtest.getId());

		return crudtestRepository.findOne(predicate);
	}
}
