package com.JAFCL.WishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.JAFCL.WishList.entity.WishList;


public interface WishListRepository extends JpaRepository<WishList, Long> {
    
}
