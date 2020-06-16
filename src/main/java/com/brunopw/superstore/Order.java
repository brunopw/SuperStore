package com.brunopw.superstore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.util.List;

@Document
public class Order {

    @Id
    private String id;
    private User buyer;
    private User seller;
    private ZonedDateTime date;
    private List<Item> itens;
    private Double discount;
    private Double totalPrice;

    public Order() {
        this.date = ZonedDateTime.now();
        this.discount = 0d;
    }

    public Order(User buyer, User seller, List itens) {
        this();
        this.buyer = buyer;
        this.seller = seller;
        this.itens = itens;
    }

    public Order(User buyer, User seller, ZonedDateTime date) {
        this();
        this.buyer = buyer;
        this.seller = seller;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
