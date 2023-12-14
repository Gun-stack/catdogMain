package com.kosta.catdog.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

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
public class UserGallery {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Integer num; // 갤러리 번호
	@Column
    private String userId; // 디자이너 id
	@Column
    private String img; // 사진 이미지 파일 경로
	@Column
    private Integer likeCnt; // 좋아요 수
	@Column
    private Integer commentCnt; // 댓글 수
	@Column
    private String tag; // 해시태그
	@Column
    private Date date; //작성일
	@Column
	private String dir;
	@Column
	private String name;
	@Column
	private Long size;
	@Column
	private String type;
	@Column
    private String content;
//	@OneToMany(mappedBy = "userGallery", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
//	@OrderBy("date desc") // 댓글 정렬
//	private List<UserGalleryComment> comment;	
}