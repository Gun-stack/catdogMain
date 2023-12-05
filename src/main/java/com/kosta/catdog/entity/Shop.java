package com.kosta.catdog.entity;

import java.io.File;
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
public class Shop {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num; // 고유번호
	@Column(unique=true)
	private Integer sId; // 사업자 등록번호
	@Column(unique=true)
	private String id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String addressRoad;
	@Column
	private String addressDetail;
	@Column
	private String postCode;
	@Column
	private String tel;
	@Column
	private String workTime; // 운영시간
	@Column
	private String lunchTime;
	@Column
	private String notice; // 공지사항
	@Column
	private String info; // 매장정보
	@Column
	private String menu; // 가격표
	@Column
	private String state; // 회원유지: true, 탈퇴: false
	@Column
	private String profImg; // 프로필 이미지
	@Column
	private String bgImg; // 배경 이미지
	@Column( precision = 9, scale =6)
	private BigDecimal lat; // 위도
	@Column( precision = 9, scale =6)
	private BigDecimal lon; // 경도
	@Column
	private BigDecimal star; // 별점
	@Column
	private String tag; // 해시태그	
}
