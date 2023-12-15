package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;

public interface DesignerRepository extends JpaRepository<Designer, Integer> {
    //id(num)을 사용하여 회원 정보 조회
	Designer findByNum(Integer num);
}
