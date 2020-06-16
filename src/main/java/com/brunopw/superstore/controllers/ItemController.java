package com.brunopw.superstore.controllers;

import com.brunopw.superstore.Item;
import com.brunopw.superstore.ItemNotFoundException;
import com.brunopw.superstore.services.ItemService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
//@RequestMapping("/v1/itens")
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    private List<Item> findAll(@RequestParam(required = false) String name) {
        if (name != null) {
            return itemService.findByNameContaining(name);
        }
        return itemService.findAll();
    }

    @PostMapping
    private Item newItem(@Valid @RequestBody Item newItem) {
        if(!itemService.existsByName(newItem.getName())) {
            return itemService.save(newItem);
        }
        return null;
    }

    @GetMapping("/{id}")
    private Item findById(@PathVariable String id) {

        return itemService.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    @PutMapping("/{id}")
    private Item replaceItem(@RequestBody Item newItem, @PathVariable String id) {

        return itemService.findById(id)
                .map(item -> {
                    item.setName(newItem.getName());
                    item.setPrice(newItem.getPrice());
                    item.setQuantity(newItem.getQuantity());
                    return itemService.save(item);
                })
                .orElseGet(() -> {
                    newItem.setId(id);
                    return itemService.save(newItem);
                });
    }

    @DeleteMapping("/{id}")
    private void deleteItem(@PathVariable String id) {
        itemService.deleteById(id);
    }
}
