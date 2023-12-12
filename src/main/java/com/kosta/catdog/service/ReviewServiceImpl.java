package com.kosta.catdog.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

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
	private ReviewFileVORepository reviewFileVoRepository;
	
	@Override
	public Review registerReview(Review review, List<MultipartFile> files) throws Exception {
		String dir = "c:/mj/upload/review";
		if(files!=null && files.size() !=0 ) {
			String fileNums="";
			for(MultipartFile file : files) {
				
			Date today = Date.valueOf(LocalDate.now());
				
			ReviewFileVO fileVO = new ReviewFileVO();
			fileVO.setDir(dir);
			fileVO.setName(file.getOriginalFilename());
			fileVO.setSize(file.getSize());
			fileVO.setType(file.getContentType());
			fileVO.setDate(today);
			reviewFileVoRepository.save(fileVO);
			
			File uploadFile= new File(dir+fileVO.getNum());
			file.transferTo(uploadFile);
			if(!fileNums.equals(""))
				fileNums += ",";
			fileNums += fileVO.getNum();
			}
			review.setAfterImg(fileNums);
		}
		reviewRepository.save(review);
		return review;
	}

	@Override
	public Review findReview(Integer num) throws Exception {
		return userDslRepository.findReview(num);
	}

	@Override
	public void modifyReveiw(Review review) throws Exception {
		reviewRepository.save(review);
	}

	@Override
	public void deleteReview(Integer num) throws Exception {
		reviewRepository.deleteById(num);
	}

	@Override
	   public void fileView(Integer num, OutputStream out) throws Exception {
	      try {
//	         Optional<PetFileVO> fileVoOptional  = petFileVORepository.findById(num);
//	         PetFileVO fileVo = fileVoOptional.get();
	         
//	         FileCopyUtils.copy(fileVo.getData(), out); //데이타 뿌려주기
//	         FileInputStream fis = new FileInputStream(fileVo.getDir()+num);//폴더에서 가져오기 
	         
	         String dir = "c:/mj/upload/review";
	         FileInputStream fis = new FileInputStream(dir+num);
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