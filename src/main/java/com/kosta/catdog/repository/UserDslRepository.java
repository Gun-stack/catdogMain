package com.kosta.catdog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.QDesGallery;
import com.kosta.catdog.entity.QDesigner;
import com.kosta.catdog.entity.QPet;
import com.kosta.catdog.entity.QReservation;
import com.kosta.catdog.entity.QReview;
import com.kosta.catdog.entity.QUser;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserDslRepository {

	@Autowired
	private JPAQueryFactory jpaQueryFactory;

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
	public Review findReviewByDesigner(Integer num) {
		QReview review = QReview.review;
		return jpaQueryFactory.selectFrom(review)
				.where(review.num.eq(num))
				.fetchOne();
	}

	public List<Review> findReviewListByDesigner(Integer num) {
		QReview review = QReview.review;
		QDesigner designer = QDesigner.designer;
		return jpaQueryFactory.selectFrom(review)
				.join(designer)
				.on(review.desId.eq(designer.id))
				.where(designer.num.eq(num))
				.fetch();
	}

	// Reservation
	public List<Reservation> findReservationListByDesignerAndDate(Integer num, Date date) {
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
