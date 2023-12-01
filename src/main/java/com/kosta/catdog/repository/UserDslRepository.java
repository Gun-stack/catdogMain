package com.kosta.catdog.repository;

import com.kosta.catdog.entity.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.kosta.catdog.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDslRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;


	public User findByName(String name) {
		QUser user = QUser.user;
		return jpaQueryFactory.selectFrom(user)
				.where(user.name.eq(name)).fetchOne();
	}

	public User findById(String id){
		QUser user = QUser.user;
		return jpaQueryFactory.selectFrom(user)
				.where(user.id.eq(id)).fetchOne();
	}

	public User findByNickname(String nickname){
		QUser user = QUser.user;
		return jpaQueryFactory.selectFrom(user)
				.where(user.nickName.eq(nickname)).fetchOne();
	}






}
