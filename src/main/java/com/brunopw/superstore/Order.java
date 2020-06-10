package com.brunopw.superstore;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public Order() {
        this.date = ZonedDateTime.now();
    }

    public Order(User buyer, User seller, List itens) {
        this();
        this.buyer = buyer;
        this.seller = seller;
        this.itens = itens;
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

    public double calculateDiscount(double discount, double totalPrice) {
        return totalPrice - ((discount / 100) * totalPrice);
    }

    public Double getTotalPrice() {
        double totalItensPrice = getItens().stream().mapToDouble(i -> (i.getPrice() * i.getQuantity())).sum();
        double totalPrice = calculateDiscount(getDiscount(), totalItensPrice);
        BigDecimal totalPriceBd = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);

        return totalPriceBd.doubleValue();
    }
}
