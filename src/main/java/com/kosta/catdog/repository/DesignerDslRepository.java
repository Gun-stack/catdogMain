package com.kosta.catdog.repository;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.QDesigner;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

@Repository
public class DesignerDslRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;
    @Autowired
    EntityManager entityManager;
    @Transactional
    public void modifyDes(Designer des){
        QDesigner qdes = QDesigner.designer;
        jpaQueryFactory.update(qdes)
                .set(qdes.sId, des.getSId())
                .where(qdes.id.eq(des.getId()))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public void modifyDesPosition(Designer des){
        QDesigner qdes = QDesigner.designer;
        jpaQueryFactory.update(qdes)
                .set(qdes.position, des.getPosition())
                .where(qdes.num.eq(des.getNum()))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }

    @Transactional
    public void deleteDesigner(Integer num, String sId){
        QDesigner qdes = QDesigner.designer;
        jpaQueryFactory.update(qdes)
                .set(qdes.sId, "0")
                .where(qdes.sId.eq(sId).and(qdes.num.eq(num)))
                .execute();
        entityManager.flush();
        entityManager.clear();
    }
}
