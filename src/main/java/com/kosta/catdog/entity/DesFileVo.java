package com.kosta.catdog.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="DESFILE")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DesFileVo {
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
