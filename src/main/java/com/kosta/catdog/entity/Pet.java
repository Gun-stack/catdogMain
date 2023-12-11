package com.kosta.catdog.entity;

import java.math.BigDecimal;

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
public class Pet {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num; 
	
	private String img; // 사진 이미지 파일 경로
	private String name;
	private String breed; // 품종
	
	private Integer age; // 나이
	
	private Boolean dogOrCat; // 강아지 고양이 구분 
	private Boolean gender; // 성별 
	private Boolean neuter; // 중성화 여부
	
	private String petNote; // 펫 특이사항 
	
	private Integer userNum; // 주인(유저) 번호 
//	@Column//( precision = 4, scale =2)
	private BigDecimal weight; // 몸무게 
	private String vaccine; // 백신 
}
