package com.kosta.catdog.config;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.querydsl.jpa.impl.JPAQueryFactory;

@Configuration
public class QuerydslConfig {
	@Autowired
	EntityManager entityManeger;
	
	@Bean
	public JPAQueryFactory jpaQueryFactory() {
		return new  JPAQueryFactory(entityManeger);
	}
	
	
}
