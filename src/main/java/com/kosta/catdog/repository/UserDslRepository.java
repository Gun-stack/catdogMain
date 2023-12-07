package com.kosta.catdog.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.QDesGallery;
import com.kosta.catdog.entity.QDesigner;
import com.kosta.catdog.entity.QPet;
import com.kosta.catdog.entity.QReservation;
import com.kosta.catdog.entity.QReview;
import com.kosta.catdog.entity.QShop;
import com.kosta.catdog.entity.QUser;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserDslRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	@Autowired
	EntityManager entityManager;

	// User
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
				.where(user.nickname.eq(nickname)).fetchOne();
	}

	public User findById_AndPassword(String id, String password){
		QUser user = QUser.user;
		return jpaQueryFactory.selectFrom(user)
				.where(user.id.eq(id).and(user.password.eq(password))).fetchOne();
	}
	
	public String findIdByEmail(String email) {
		QUser user = QUser.user;
		return jpaQueryFactory.select(user.id)
				.from(user)
				.where(user.email.eq(email))
				.fetchOne();				
	}
	
	@Transactional
	public void modifyNickname(Integer num, String nickname) {
		QUser user = QUser.user;
		jpaQueryFactory.update(user)
			.set(user.nickname, nickname)
			.where(user.num.eq(num))
			.execute();
		entityManager.flush();
		entityManager.clear();
	}
	
	@Transactional
	public void modifyTel(Integer num, String tel) {
		QUser user = QUser.user;
		jpaQueryFactory.update(user)
			.set(user.tel, tel)
			.where(user.num.eq(num))
			.execute();
		entityManager.flush();
		entityManager.clear();
	}
	
	@Transactional
	public void modifyPassword(Integer num, String password) {
		QUser user = QUser.user;
		jpaQueryFactory.update(user)
			.set(user.password, password)
			.where(user.num.eq(num))
			.execute();
		entityManager.flush();
		entityManager.clear();
	}

	// DesGallery
	public DesGallery findDesGalleryByDesigner(Integer num) {
		QDesGallery desGallery = QDesGallery.desGallery;
		return jpaQueryFactory.selectFrom(desGallery)
				.where(desGallery.num.eq(num))
				.fetchOne();
	}

	public List<DesGallery> findDesGalleryListByDesigner(String desId) {
		QDesGallery desGallery = QDesGallery.desGallery;
		return jpaQueryFactory.selectFrom(desGallery)
				.where(desGallery.desId.eq(desId))
				.fetch();
	}
	
	// Review
	public Review findReview(Integer num) {
		QReview review = QReview.review;
		return jpaQueryFactory.selectFrom(review)
				.where(review.num.eq(num))
				.fetchOne();
	}
	
	public List<Review> findReviewListByDesignerOrderByDateDesc(Integer num) {
		QReview review = QReview.review;
		QDesigner designer = QDesigner.designer;
		return jpaQueryFactory.selectFrom(review)
				.join(designer)
				.on(review.desId.eq(designer.id))
				.where(designer.num.eq(num))
				.orderBy(review.date.desc())
				.fetch();
	}

	// Reservation
	public List<Reservation> findReservationListByDesigner_AndDate(Integer num, Date date) {
		QReservation reservation = QReservation.reservation;
		QDesigner designer = QDesigner.designer;
		return jpaQueryFactory.selectFrom(reservation)
				.join(designer)
				.on(reservation.desId.eq(designer.id))
				.where(designer.num.eq(num).and(reservation.date.eq(date)))
				.fetch();

				
	}
	
	public List<Reservation> findReservationListByUserId(String userId) {
		QReservation reservation = QReservation.reservation;
		return jpaQueryFactory.selectFrom(reservation)
				.where(reservation.userId.eq(userId))
				.fetch();
	}
	

	//pet
	public List<Pet> findPetsByUserID(String userId){
	QUser user = QUser.user;
	QPet pet = QPet.pet;
	return jpaQueryFactory.selectFrom(pet)
			.join(user)
			.on(pet.UserNum.eq(user.num))
			.where(user.id.eq(userId))
			.fetch();
	}
	

	}
	
	// Designer
	public Double findAvgStarCountByDesigner(Integer num) {
	    QDesigner designer = QDesigner.designer;
	    QReview review = QReview.review;

	    List<Integer> starList = jpaQueryFactory.select(review.star)
	            .from(review)
	            .join(designer)
	            .on(review.desId.eq(designer.id))
	            .where(designer.num.eq(num))
	            .fetch();

	    Double avgStarCount = starList.stream()
	            .mapToInt(Integer::intValue)
	            .average()
	            .orElse(0.0); // 리스트가 비어 있는 경우 기본값 0.0 반환

	    return avgStarCount;
	}
	
	// Shop
//	@Transactional
//	public void addDesignerToShop(String id, String position) {
//		QDesigner designer = QDesigner.designer;
//		QShop shop = QShop.shop;
//		jpaQueryFactory.update(designer)
//			.set(designer.sId, sId)
//	}

	
}
