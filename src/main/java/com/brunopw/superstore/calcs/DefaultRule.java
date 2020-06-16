package com.brunopw.superstore.calcs;

import com.brunopw.superstore.Order;

import java.time.LocalDate;

public class DefaultRule implements DiscountRule{

    @Override
    public boolean apply(Order order) {
        return false;
    }

    @Override
    public Double calculateDiscount(Order order) {
        return 0d;
    }
}
