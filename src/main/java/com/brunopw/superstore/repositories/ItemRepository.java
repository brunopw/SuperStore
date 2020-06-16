package com.brunopw.superstore.repositories;

import com.brunopw.superstore.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

    List<Item> findByNameContaining(String name);

    List<Item> findByName(String name);
}
