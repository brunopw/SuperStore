package com.brunopw.superstore.calcs;

import com.brunopw.superstore.Order;

import java.time.LocalDate;

public class ClientsLivingInDublinRule implements DiscountRule {

    @Override
    public boolean apply(Order order) {
        return order.getBuyer().getAddress().equalsIgnoreCase("dublin");
    }

    @Override
    public Double calculateDiscount(Order order) {
        return 7d;
    }
}
