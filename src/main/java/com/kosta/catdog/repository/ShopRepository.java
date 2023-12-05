package com.kosta.catdog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kosta.catdog.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Integer> {

}
