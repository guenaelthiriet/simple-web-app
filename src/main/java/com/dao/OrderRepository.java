package com.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by Guenael Thiriet on 2016-04-17.
 */

public interface OrderRepository extends PagingAndSortingRepository<CustomerOrder, Long> {

    List<CustomerOrder> findByCustomer(Customer customer);

//    List<CustomerOrder> findByDate(Date orderDate);

}