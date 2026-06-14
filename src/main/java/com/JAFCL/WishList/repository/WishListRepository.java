package com.JAFCL.WishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JAFCL.WishList.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
    
}
