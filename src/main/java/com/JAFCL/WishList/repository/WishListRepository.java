package com.JAFCL.WishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JAFCL.WishList.entity.WishList;
import java.util.List;
import java.util.Optional;


public interface WishListRepository extends JpaRepository<WishList, Long> {
    Optional<WishList> findByIdProducto(int idProducto);
}
