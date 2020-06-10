package com.brunopw.superstore.services;

import com.brunopw.superstore.Item;
import com.brunopw.superstore.repositories.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public Item save(Item newItem) {
        return itemRepository.save(newItem);
    }

    public Optional<Item> findById(String id) {
        return itemRepository.findById(id);
    }

    public void deleteById(String id) {
        itemRepository.deleteById(id);
    }

}
