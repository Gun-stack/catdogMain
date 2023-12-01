package com.kosta.catdog.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kosta.catdog.entity.DesGallery;
import com.kosta.catdog.entity.Designer;
import com.kosta.catdog.entity.Reservation;
import com.kosta.catdog.entity.Review;

@Service
public class DesignerServiceImpl implements DesignerService {

	@Override
	public void addDesignerInfo(Designer designer) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<DesGallery> desGalleryListByDesigner(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Review> reviewListByDesigner(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reservation> reservationListByDate(Date date) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reservation findReservation(Integer num) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
