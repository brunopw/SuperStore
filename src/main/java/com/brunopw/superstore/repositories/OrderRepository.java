package com.brunopw.superstore.repositories;

import com.brunopw.superstore.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByBuyerNameContaining(String buyerName);
}
