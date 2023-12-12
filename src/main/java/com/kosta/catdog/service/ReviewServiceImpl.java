package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.kosta.catdog.entity.PetFileVO;
import com.kosta.catdog.entity.Review;
import com.kosta.catdog.entity.ReviewFileVO;
import com.kosta.catdog.repository.ReviewFileVORepository;
import com.kosta.catdog.repository.ReviewRepository;
import com.kosta.catdog.repository.UserDslRepository;

@Service
public class ReviewServiceImpl implements ReviewService {
	
	
	@Autowired
	private UserDslRepository userDslRepository;
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ReviewFileVORepository fileVORepository;
	
	@Override
	public Review postReview(Review review , MultipartFile file ) throws Exception {
		String dir = "c:/kkw/upload/review/";
		if(file !=null) {
		String fileNums="";
		Date today = Date.valueOf(LocalDate.now());
		ReviewFileVO fileVO = new ReviewFileVO();
		fileVO.setDir(dir);
		fileVO.setName(file.getOriginalFilename());
		fileVO.setSize(file.getSize());
		fileVO.setType(file.getContentType());
		fileVO.setDate(today);
		fileVORepository.save(fileVO);
		
		File uploadFile= new File(dir+fileVO.getNum());
		file.transferTo(uploadFile);
		if(!fileNums.equals(""))
			fileNums += ",";
		fileNums += fileVO.getNum();
		
		review.setAfterImg(fileNums);
		}
		//디자이너 넘버찾기
		Integer desNum = userDslRepository.FindDesignerById(review.getDesId()).getNum();
		System.out.println(desNum);
		//리뷰저장
		reviewRepository.save(review);
		//디자이너 넘버와 리뷰 별점으로 디자이너 별점과 리뷰갯수 올려주기
		userDslRepository.UpdateStarByDesNumAndReviewStar(desNum, review);
		//예약에 리뷰 여부 등록
		userDslRepository.updateReservationIsReview(review.getResNum());
		return review;
	}
	
	@Override
	public Review modifyReveiw(Review review, MultipartFile file) throws Exception {
		String dir = "c:/kkw/upload/review/";
		System.out.println(review);
		Review newReview = reviewRepository.findById(review.getNum()).get();
		
		if(file !=null) {
		String fileNums="";
		Date today = Date.valueOf(LocalDate.now());
		ReviewFileVO fileVO = new ReviewFileVO();
		
		fileVO.setDir(dir);
		fileVO.setName(file.getOriginalFilename());
		fileVO.setSize(file.getSize());
		fileVO.setType(file.getContentType());
		fileVO.setDate(today);
		fileVORepository.save(fileVO);
		
		File uploadFile= new File(dir+fileVO.getNum());
		file.transferTo(uploadFile);
		if(!fileNums.equals(""))
			fileNums += ",";
		fileNums += fileVO.getNum();
		
		review.setAfterImg(fileNums);
		}
		//디자이너 넘버찾기
		Integer desNum = userDslRepository.FindDesignerById(review.getDesId()).getNum();
		//리뷰저장
		review.setNum(newReview.getNum());
		reviewRepository.save(review);
		
		
		//디자이너 넘버와 리뷰 별점으로 디자이너 별점과 리뷰갯수 올려주기
		
		
		userDslRepository.UpdateStarByDesNumAndReviewStarModi(desNum, review);
		
		
		
		return review;
	}
		
	
	@Override
	public Review findReview(Integer num) throws Exception {
		return userDslRepository.findReview(num);
		
	}

	

	@Override
	public void deleteReview(Integer num) throws Exception {
		reviewRepository.deleteById(num);
	}

	@Override
	public void fileView(Integer num, OutputStream out) throws Exception {
try {
			
			Integer fileNum = Integer.parseInt( reviewRepository.findById(num).get().getAfterImg() );
			Optional<ReviewFileVO> fileVoOptional  = fileVORepository.findById(fileNum);
			ReviewFileVO fileVo = fileVoOptional.get();
//			FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
			FileInputStream fis = new FileInputStream(fileVo.getDir()+fileNum);//폴더에서 가져오기 
			FileCopyUtils.copy(fis, out);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	

	@Override
	public List<Review> reviewListByShopOrderByDateDesc(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findReviewListByShopOrderByDateDesc(num, offset, limit);
	}
	
	@Override
	public List<Review> reviewListByDesignerOrderByDateDesc(Integer num, int offset, int limit) throws Exception {
		return userDslRepository.findReviewListByDesignerOrderByDateDesc(num, offset, limit);
	}
}