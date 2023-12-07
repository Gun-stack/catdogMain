package com.kosta.catdog.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
public class Designer {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num; // 고유번호
	@Column	
	private Integer sId; // 사업자 등록번호
	@Column(unique=true)
	private String id;
	@Column
	private String name;
	@Column(unique=true)
	private String desNickname; // 디자이너 닉네임
	@Column(unique=true) 
	private String email;
	@Column
	private String password;
	@Column(unique=true)
	private Integer tel;
	@Column
	private String profImg;
	@Column
	private String workTime; // 근무시간
	@Column
	private Integer reviewCnt;
	@Column
	private Integer bookmarkCnt;
	@Column
	private BigDecimal star; // 별점
	@Column
	private String info; // 프로필 정보 등록
	@Column
	private String state; // 회원유지: true, 탈퇴: false
	@Column(unique=true)
	private String kId; // 카카오톡 아이디 연동
	@Column
	private String position; // 직책
	@Column
	private String tag;	// 해시태그
	@Column
	private String role;	// ROLE_DES or ROLE_SHOP
}
