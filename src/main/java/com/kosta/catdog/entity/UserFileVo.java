package com.kosta.catdog.entity;

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
@Entity(name="USERFILE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamicInsert
@DynamicUpdate
@Builder
public class UserFileVo {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer num;
	@Column	
	private String dir;
	@Column
	private String name;
	@Column
	private Long size;
	@Column
	private String type;
	@Column
	private Date date;
	@Column
	private byte[] data;
}
