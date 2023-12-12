package com.kosta.catdog.entity;

import java.math.BigDecimal;
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
public class Review {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	num;
	@Column
    private String desId; // 디자이너 id
	@Column
	private Integer	resNum;
	@Column
	private String	userId;
	@Column
	private String	content;
	@Column
	private String	afterImg;
	@Column
	private Date date;
	@Column
	private String	tag; // 해시태그
	@Column
	private Integer star; // 별점
}
