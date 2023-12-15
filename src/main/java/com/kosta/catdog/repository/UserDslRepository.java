package com.kosta.catdog.repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import com.querydsl.core.Tuple;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Pet;
import com.kosta.catdog.entity.QDesGallery;
import com.kosta.catdog.entity.QDesigner;
import com.kosta.catdog.entity.QPet;
import com.kosta.catdog.entity.QReservation;
import com.kosta.catdog.entity.QReview;
import com.kosta.catdog.entity.QShop;
import com.kosta.catdog.entity.QUser;
import com.kosta.catdog.entity.QUserGallery;
import com.kosta.catdog.entity.QUserGalleryComment;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.Shop;
import com.kosta.catdog.entity.User;
import com.kosta.catdog.entity.UserGallery;
import com.kosta.catdog.entity.UserGalleryComment;
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

    public User findById(String id) {
        QUser user = QUser.user;
        return jpaQueryFactory.selectFrom(user)
                .where(user.id.eq(id)).fetchOne();
    }

    public User findByNickname(String nickname) {
        QUser user = QUser.user;
        return jpaQueryFactory.selectFrom(user)
                .where(user.nickname.eq(nickname)).fetchOne();
    }

    public User findByNum(Integer num) {
        QUser user = QUser.user;
        return jpaQueryFactory.selectFrom(user)
                .where(user.num.eq(num)).fetchOne();
    }

    public User findById_AndPassword(String id, String password) {
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

    public List<Designer> findDesListBySId(String sId) {
        QDesigner des = QDesigner.designer;
        return jpaQueryFactory.selectFrom(des)
                .where(des.sId.eq(sId))
                .fetch();


    }


    @Transactional
    public void updateReservationIsReview(Integer resNum) {
        QReservation reservation = QReservation.reservation;
        jpaQueryFactory.update(reservation).set(reservation.isReview, 1)
                .where(reservation.num.eq(resNum))
                .execute();
        entityManager.flush();
        entityManager.clear();


    }

    @Transactional
    public void modifyRole(String id) {
        QUser user = QUser.user;
        jpaQueryFactory.update(user)
                .set(user.roles, user.roles)
                .where(user.id.eq(id))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    public UserGallery findUserGallery(Integer num) {
        QUserGallery userGallery = QUserGallery.userGallery;
        return jpaQueryFactory.selectFrom(userGallery)
                .where(userGallery.num.eq(num))
                .fetchOne();
    }


    // DesGallery
    public DesGallery findDesGallery(Integer num) {
        QDesGallery desGallery = QDesGallery.desGallery;
        return jpaQueryFactory.selectFrom(desGallery)
                .where(desGallery.num.eq(num))
                .fetchOne();
    }

    public List<DesGallery> findDesGalleryListShopPage(Integer num, int offset, int limit) {
        QDesGallery desGallery = QDesGallery.desGallery;
        QDesigner designer = QDesigner.designer;
        QShop shop = QShop.shop;

        return jpaQueryFactory.selectFrom(desGallery)
                .from(desGallery)
                .join(designer)
                .on(desGallery.desId.eq(designer.id))
                .join(shop)
                .on(designer.sId.eq(shop.sId))
                .where(shop.num.eq(num))
                .orderBy(desGallery.date.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    public List<DesGallery> findDesGalleryListDesignerPage(Integer num, int offset, int limit) {
        QDesGallery desGallery = QDesGallery.desGallery;
        QDesigner designer = QDesigner.designer;
        return jpaQueryFactory.selectFrom(desGallery)
                .from(desGallery)
                .join(designer)
                .on(desGallery.desId.eq(designer.id))
                .where(designer.num.eq(num))
                .orderBy(desGallery.date.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    // Review
    public Review findReview(Integer num) {
        QReview review = QReview.review;
        return jpaQueryFactory.selectFrom(review)
                .where(review.num.eq(num))
                .fetchOne();
    }

    public List<Review> findReviewListByShopOrderByDateDesc(Integer num, int offset, int limit) {
        QReview review = QReview.review;
        QDesigner designer = QDesigner.designer;
        QShop shop = QShop.shop;
        return jpaQueryFactory.selectFrom(review)
                .from(review)
                .join(designer)
                .on(review.desId.eq(designer.id))
                .join(shop)
                .on(designer.sId.eq(shop.sId))
                .where(shop.num.eq(num))
                .orderBy(review.date.desc())
                .offset(offset)
                .limit(limit)
                .fetch();
    }

    public List<Review> findReviewListByDesignerOrderByDateDesc(Integer num, int offset, int limit) {
        QReview review = QReview.review;
        QDesigner designer = QDesigner.designer;
        return jpaQueryFactory.selectFrom(review)
                .from(review)
                .join(designer)
                .on(review.desId.eq(designer.id))
                .where(designer.num.eq(num))
                .orderBy(review.date.desc())
                .offset(offset)
                .limit(limit)
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
    public List<Pet> findPetsByUserID(String userId) {
        QUser user = QUser.user;
        QPet pet = QPet.pet;
        return jpaQueryFactory.selectFrom(pet)
                .join(user)
                .on(pet.userNum.eq(user.num))
                .where(user.id.eq(userId))
                .fetch();
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

        return avgStarCount
                ;
    }

    //리뷰 등록시 평균별점
    @Transactional
    public void UpdateStarByDesNumAndReviewStar(Integer desNum, Review review) {
        QDesigner designer = QDesigner.designer;

        BigDecimal desStar = jpaQueryFactory.select(designer.star)
                .from(designer)
                .where(designer.num.eq(desNum))
                .fetchOne();
        Integer revCnt = jpaQueryFactory.select(designer.reviewCnt)
                .from(designer)
                .where(designer.num.eq(desNum))
                .fetchOne();

        desStar = (desStar.multiply(BigDecimal.valueOf(revCnt)).add(BigDecimal.valueOf(review.getStar())))
                .divide(BigDecimal.valueOf(revCnt + 1), 2, RoundingMode.HALF_UP);

        // 이제 변경된 별점 및 리뷰 카운트를 데이터베이스에 저장해야 합니다.
        // (디자이너에 대한 속성인 star 및 reviewCnt가 있는 가정하에 작성된 코드입니다)
        jpaQueryFactory.update(designer)
                .set(designer.star, desStar)
                .set(designer.reviewCnt, revCnt + 1)
                .where(designer.num.eq(desNum))
                .execute();
    }

    //리뷰 수정시 평균별점 반영
    @Transactional
    public void UpdateStarByDesNumAndReviewStarModi(Integer desNum, Review review) {
        QDesigner designer = QDesigner.designer;
        QReview review1 = QReview.review;

        Designer des = jpaQueryFactory.selectFrom(designer).where(designer.num.eq(desNum)).fetchOne();

        List<Review> allReviews = jpaQueryFactory.selectFrom(review1)
                .where(review1.desId.eq(des.getId())).fetch();


        // 리뷰의 모든 별점을 더한 값
        BigDecimal totalStars = allReviews.stream()
                .map(reviewInList -> BigDecimal.valueOf(reviewInList.getStar()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 새로운 리뷰의 별점을 추가
        totalStars = totalStars.add(BigDecimal.valueOf(review.getStar()));

        // 리뷰의 개수
        int reviewCount = allReviews.size() + 1;

        // 평균 별점 계산
        BigDecimal averageStars = totalStars.divide(BigDecimal.valueOf(reviewCount), 2, RoundingMode.HALF_UP);

        // 디자이너 업데이트
        jpaQueryFactory.update(designer)
                .set(designer.star, averageStars)
                .set(designer.reviewCnt, reviewCount)
                .where(designer.num.eq(desNum))
                .execute();

    }


    //designer by Id

    public Designer FindDesignerById(String desId) {
        QDesigner designer = QDesigner.designer;
        return jpaQueryFactory.selectFrom(designer)
                .where(designer.id.eq(desId))
                .fetchOne();
    }

    public Tuple findDesById(String desId) {
		QUser user = QUser.user;
        QDesigner designer = QDesigner.designer;
        return (Tuple) jpaQueryFactory.select(user.roles, user.name)
                .from(user)
                .join(designer)
                .on(user.id.eq(designer.id))
                .where(user.id.eq(desId))
                .fetchOne();
    }


    //shop
    public Shop FindShopBySid(String sid) {
        QShop shop = QShop.shop;
        return jpaQueryFactory.selectFrom(shop)
                .where(shop.sId.eq(sid))
                .fetchOne();

    }


    //review by resnum
    public Review FindReviewByResNum(Integer resNum) {
        QReview review = QReview.review;
        return jpaQueryFactory.selectFrom(review)
                .where(review.resNum.eq(resNum))
                .fetchOne();
    }


    //pet
    public Pet FindPetByuserIdAndPetName(Integer userNum, String petName) {
        QUser user = QUser.user;
        QPet pet = QPet.pet;
        return jpaQueryFactory.selectFrom(pet)
                .where(pet.userNum.eq(userNum).and(pet.name.eq(petName)))
                .fetchOne();

    }



	
	// UserGalleryComment
	public List<UserGalleryComment> findComment(Integer num) {
		QUserGallery userGallery = QUserGallery.userGallery;
		QUserGalleryComment userGalleryComment = QUserGalleryComment.userGalleryComment;
		return jpaQueryFactory.selectFrom(userGalleryComment)
				.join(userGallery)
				.on(userGallery.num.eq(userGalleryComment.num))
				.where(userGallery.num.eq(userGalleryComment.num))
				.fetch();
	}
}
