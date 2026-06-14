package com.JAFCL.WishList.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.JAFCL.WishList.entity.Catalog;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    
}
