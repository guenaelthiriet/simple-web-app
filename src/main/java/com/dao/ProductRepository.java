package com.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Guenael Thiriet on 2016-04-17.
 */

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {

//    List<Product> findByProductName(String productName);
}