package com.kosta.catdog.entity;

import java.sql.Time;
import java.sql.Date;

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
public class Reservation {	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer num; // 고유번호
	@Column
	private String userId; // 예약자 아이디
    @Column
    private String desId; // 디자이너 id
    @Column
    private String petName;
    @Column
    private String sId;
    @Column
    private String shopName;
    @Column
    private Date date; // 예약 날짜
    @Column
    private Time time; // 예약 시간
    @Column
    private String status; // 예약 상태
    @Column
    private String refImg; // 스타일 이미지
    @Column
    private String refText; // 스타일 내용
    @Column
    private String notice;
    @Column
    private Integer isReview;
	
	
}
