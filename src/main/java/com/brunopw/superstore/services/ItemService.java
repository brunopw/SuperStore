package com.brunopw.superstore.services;

import com.brunopw.superstore.Item;
import com.brunopw.superstore.repositories.ItemRepository;
import org.springframework.data.domain.Example;
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

    public Item saveOrUpdate(Item newItem) {
        Example itemExample = Example.of(new Item(newItem.getName()));
        List<Item> itens = itemRepository.findAll(itemExample);
        if(!itens.isEmpty()){
            newItem.setId(itens.get(0).getId());
        }
        return itemRepository.save(newItem);
    }

    public Optional<Item> findById(String id) {
        return itemRepository.findById(id);
    }

    public void deleteById(String id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findByNameContaining(String name) {
        return itemRepository.findByNameContaining(name);
    }

    public List<Item> findByName(String name) {
        return itemRepository.findByName(name);
    }

    public boolean existsByName(String name) {
        if(itemRepository.findByName(name).isEmpty()) return false;
        return true;
    }
}
