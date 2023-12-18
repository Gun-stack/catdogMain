package com.kosta.catdog.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class UserGalleryComment {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	num;
	@Column
	private Integer	galleryNum;
	@Column
	private String	userId;
	@Column
	private String	userNickname;
	@Column
	private String	content;
	@Column
	private Date date;
}
