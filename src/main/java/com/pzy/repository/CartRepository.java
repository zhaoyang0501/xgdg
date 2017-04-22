package com.pzy.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.pzy.entity.Cart;
import com.pzy.entity.Order;
public interface CartRepository extends PagingAndSortingRepository<Cart, Long>,JpaSpecificationExecutor<Cart>{
	public List<Cart> findByOrder(Order order);
}

