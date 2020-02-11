package com.spring.boot.api.apiservice.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.JPQLQuery;
import com.spring.boot.api.apiservice.model.Crudtest;
import com.spring.boot.api.apiservice.model.QCrudtest;
import com.spring.boot.api.apiservice.repository.CrudtestRepository;


@Service
public class QueryDSLService extends QuerydslRepositorySupport {

	@PersistenceContext
    private EntityManager em;
	
	@Autowired
	private CrudtestRepository crudtestRepository;
	
	public QueryDSLService() {
		super(Crudtest.class);
	}
	
	@Transactional(readOnly = true)
	public List<Crudtest> selectByUsername(Crudtest crudtest) throws Exception {
		QCrudtest qCrudtest = QCrudtest.crudtest;
		
		JPQLQuery<Crudtest> jpqlQuery  = from(qCrudtest);
		jpqlQuery.where(qCrudtest.username.eq(crudtest.getUsername()));
		List<Crudtest> resultList = jpqlQuery.fetch();
		
		return resultList;
	}

	@Transactional(readOnly = true)
	public Optional<Crudtest> selectOneById(Crudtest crudtest) throws Exception {
		Predicate predicate = QCrudtest.crudtest
				.id.eq(crudtest.getId());

		return crudtestRepository.findOne(predicate);
	}
	
	@Transactional(readOnly = false)
	public void updateById(Crudtest crudtest) throws Exception {
		Crudtest crudtestEntity = em.find(Crudtest.class, crudtest.getId());
		assertThat(crudtest.getUsername(), is(crudtestEntity.getUsername()));
		
		// 엔티티 매니저 flush 하지 않아도 아래 구문 실행 시 DB 업데이트 한다. (영속성 컨텍스트에 있는 엔티티는 수정되지 않는다.)
		QCrudtest qCrudtest = QCrudtest.crudtest;
		update(qCrudtest).where(qCrudtest.id.eq(crudtest.getId())).set(qCrudtest.username, "dsltestname").execute();
		
		// 기존 username
		crudtestEntity = em.find(Crudtest.class, crudtest.getId());
		assertThat(crudtest.getUsername(), is(crudtestEntity.getUsername()));
		
		// 영속성 컨텍스트 초기화
		em.clear();
		// 엔티티 초기화 진행
		crudtestEntity = em.find(Crudtest.class, crudtest.getId());
		assertThat("dsltestname", is(crudtestEntity.getUsername()));
	}
	
	@Transactional(readOnly = false)
	public void deleteById(Crudtest crudtest) throws Exception {
		QCrudtest qCrudtest = QCrudtest.crudtest;
		delete(qCrudtest).where(qCrudtest.id.eq(crudtest.getId())).execute();
		
		Crudtest crudtestEntity = em.find(Crudtest.class, crudtest.getId());
		assertNull(crudtestEntity);
		
	}
}
